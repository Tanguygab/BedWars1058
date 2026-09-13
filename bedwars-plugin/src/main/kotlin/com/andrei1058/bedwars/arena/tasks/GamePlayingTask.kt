/*
 * BedWars1058 - A bed wars mini-game.
 * Copyright (C) 2021 Andrei Dascălu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Contact e-mail: andrew.dascalu@gmail.com
 */
package com.andrei1058.bedwars.arena.tasks

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.AFKManager
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.NextEvent
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.player.PlayerInvisibilityPotionEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.tasks.PlayingTask
import com.andrei1058.bedwars.arena.Arena
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.potion.PotionEffectType

class GamePlayingTask(override val arena: Arena) : Runnable, PlayingTask {
    override var bedsDestroyCountdown = BedWars.config.getInt(ConfigPath.GENERAL_CONFIGURATION_BEDS_DESTROY_COUNTDOWN)
        private set
    override var dragonSpawnCountdown = BedWars.config.getInt(ConfigPath.GENERAL_CONFIGURATION_DRAGON_SPAWN_COUNTDOWN)
        private set
    override var gameEndCountdown = BedWars.config.getInt(ConfigPath.GENERAL_CONFIGURATION_GAME_END_COUNTDOWN)
        private set
    override val bukkitTask = Bukkit.getScheduler().runTaskTimer(BedWars.plugin, this, 0, 20L)

    override fun run() {
        when (arena.nextEvent) {
            NextEvent.EMERALD_GENERATOR_TIER_II, NextEvent.EMERALD_GENERATOR_TIER_III, NextEvent.DIAMOND_GENERATOR_TIER_II, NextEvent.DIAMOND_GENERATOR_TIER_III -> {
                if (arena.upgradeDiamondsCount > 0) {
                    if (--arena.upgradeDiamondsCount == 0) {
                        arena.updateNextEvent()
                    }
                }
                if (arena.upgradeEmeraldsCount > 0) {
                    if (--arena.upgradeEmeraldsCount == 0) {
                        arena.updateNextEvent()
                    }
                }
            }

            NextEvent.BEDS_DESTROY -> run {
                if (--bedsDestroyCountdown != 0) return@run
                for (player in arena.allPlayers) {
                    BedWars.nms.sendTitle(
                        player,
                        Language.getMsg(player, Messages.NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED),
                        Language.getMsg(player, Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED),
                        0,
                        40,
                        10
                    )
                    player.sendLangMsg(Messages.NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED)
                }
                for (t in arena.teams) {
                    t.isBedDestroyed = true
                }
                arena.updateNextEvent()
            }

            NextEvent.ENDER_DRAGON -> run {
                if (--dragonSpawnCountdown != 0) return@run

                for (p in arena.allPlayers) {
                    BedWars.nms.sendTitle(
                        p,
                        Language.getMsg(p, Messages.NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH),
                        Language.getMsg(p, Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH),
                        0,
                        40,
                        10
                    )
                    for (t in arena.teams) {
                        if (t.members.isEmpty()) continue
                        p.sendLangMsg(Messages.NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH,
                            "{TeamDragons}" to t.dragons,
                            "{TeamColor}" to t.color.chat,
                            "{TeamName}" to t.getDisplayName(Language.getLanguage(p))
                        )
                    }
                }
                arena.updateNextEvent()
                for (team in arena.teams) {
                    for (generator in team.generators) {
                        for (y in 0 ..< 20) {
                            generator.location.clone().subtract(0.0, y.toDouble(), 0.0).block.type = Material.AIR
                        }
                    }

                    if (team.members.isEmpty()) continue
                    (0 ..< team.dragons).forEach { _ ->
                        BedWars.nms.spawnDragon(arena.waitingLocation.add(0.0, 10.0, 0.0), team)
                    }
                }
            }

            NextEvent.GAME_END -> run {
                if (--gameEndCountdown != 0) return@run
                arena.checkWinner()
                arena.changeStatus(GameState.RESTARTING)
            }
        }

        var distance = 0
        for (team in arena.teams) {
            // spawn items
            team.generators.forEach { it.spawn() }
            if (team.size <= 1) continue

            for (p in team.members) {
                for (p2 in team.members) {
                    if (p2 === p) continue
                    if (distance == 0 || p.location.distance(p2.location).toInt() < distance) {
                        distance = p.location.distance(p2.location).toInt()
                    }
                }
                BedWars.nms.playAction(
                    p, Language.getMsg(p, Messages.FORMATTING_ACTION_BAR_TRACKING)
                        .replace("{team}", "${team.color.chat}${team.getDisplayName(Language.getLanguage(p))}")
                        .replace("{distance}", "${team.color.chat}$distance")
                        .replace("&", "§")
                )
            }

        }

        /* AFK SYSTEM FOR PLAYERS */
        val afkManager: AFKManager = BedWars.plugin.afkManager
        for (p in arena.players) {
            afkManager.setAFK(p, afkManager.getAFKTime(p) + 1)
        }

        /* RESPAWN SESSION */
        if (!arena.respawnSessions.isEmpty()) {
            for ((player, cooldown) in arena.respawnSessions) {
                if (cooldown <= 0) {
                    val arena = BedWars.plugin.arenaManager.getArena(player)
                    if (arena == null) {
                        this@GamePlayingTask.arena.respawnSessions.remove(player)
                        continue
                    }
                    val t = arena.getTeam(player)
                    if (t != null) {
                        t.respawnMember(player)
                        player.allowFlight = false
                        player.isFlying = false
                    } else arena.addSpectator(player, true, null)
                    continue
                }
                BedWars.nms.sendTitle(
                    player,
                    Language.getMsg(player, Messages.PLAYER_DIE_RESPAWN_TITLE).replace("{time}", "$cooldown"),
                    Language.getMsg(player, Messages.PLAYER_DIE_RESPAWN_SUBTITLE).replace("{time}", "$cooldown"),
                    0, 30, 10
                )
                player.sendLangMsg(
                    Language.getMsg(player, Messages.PLAYER_DIE_RESPAWN_CHAT).replace("{time}", "$cooldown")
                )
                arena.respawnSessions.replace(player, cooldown - 1)
            }
        }

        /* SPAWN ITEMS */
        arena.oreGenerators.forEach { it.spawn() }

        /* INVISIBILITY FOR ARMOR */
        if (arena.showTime.isEmpty()) return

        for ((player, duration) in arena.showTime) {
            if (duration > 0) {
                arena.showTime.replace(player, duration - 1)
                continue
            }
            for (p in player.world.players) {
                BedWars.nms.showArmor(player, p)
                //nms.showPlayer(e.getKey(), p);
            }
            player.removePotionEffect(PotionEffectType.INVISIBILITY)
            arena.showTime.remove(player)
            Bukkit.getPluginManager().callEvent(
                PlayerInvisibilityPotionEvent(
                    PlayerInvisibilityPotionEvent.Type.REMOVED,
                    player,
                    arena
                )
            )
        }
    }

    override fun cancel() = bukkitTask.cancel()
}


