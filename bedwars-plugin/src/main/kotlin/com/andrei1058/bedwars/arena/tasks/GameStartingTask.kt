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
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.NextEvent
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.getCountDownTitle
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.tasks.StartingTask
import com.andrei1058.bedwars.arena.Arena
import com.andrei1058.bedwars.arena.generators.GeneratorOre
import com.andrei1058.bedwars.arena.team.BedWarsTeam
import com.andrei1058.bedwars.configuration.Sounds
import com.andrei1058.bedwars.support.papi.PAPISupport
import org.bukkit.Bukkit

class GameStartingTask(
    override val arena: Arena
) : Runnable, StartingTask {
    private val plugin = BedWars.INSTANCE
    override var countdown = plugin.mainConfig.getInt(ConfigPath.GENERAL_CONFIGURATION_START_COUNTDOWN_REGULAR)

    override val bukkitTask = Bukkit.getScheduler().runTaskTimer(plugin, this, 0, 20L)


    override fun run() {
        val nms = plugin.versionSupport
        if (countdown == 0) {
            arena.teamAssigner.assignTeams(arena)

            //Color bed block if possible
            //Destroy bed if team is empty
            //Spawn shops and upgrades
            //Disable generators for empty teams if required
            for (team in arena.teams) {
                nms.colorBed(team)
                if (team.members.isEmpty()) {
                    team.isBedDestroyed = true
                    if (arena.config.getBoolean(ConfigPath.ARENA_DISABLE_GENERATOR_FOR_EMPTY_TEAMS)) {
                        for (gen in team.generators) {
                            gen.disable()
                        }
                    }
                }
            }

            plugin.run(delay = 60) {
                //Enable diamond/ emerald generators
                for (og in arena.oreGenerators) {
                    if (og.type === GeneratorOre.EMERALD || og.type === GeneratorOre.DIAMOND) og.enableRotation()
                }
            }

            //Spawn players
            spawnPlayers()

            //Lobby removal
            plugin.restoreAdapter.onLobbyRemoval(arena)


            bukkitTask.cancel()
            arena.changeStatus(GameState.PLAYING)

            // Check if emerald should be first based on time
            arena.nextEvent = if (arena.upgradeDiamondsCount < arena.upgradeEmeraldsCount)
                NextEvent.DIAMOND_GENERATOR_TIER_II
            else NextEvent.EMERALD_GENERATOR_TIER_II

            //Spawn shopkeepers
            arena.teams.forEach { it.spawnNPCs() }
            return
        }

        //Send countdown
        if (countdown % 10 == 0 || countdown <= 5) {
            Sounds.playSound(
                if (countdown < 5) ConfigPath.SOUNDS_COUNTDOWN_TICK_X + countdown
                else ConfigPath.SOUNDS_COUNTDOWN_TICK,
                arena.players
            )
            for (player in arena.players) {
                val playerLang = Language.getLanguage(player)
                val titleSubtitle = getCountDownTitle(playerLang, countdown)
                nms.sendTitle(player, titleSubtitle[0], titleSubtitle[1], 0, 20, 10)
                player.sendLangMsg(Messages.ARENA_STATUS_START_COUNTDOWN_CHAT, "{time}" to countdown)
            }
        }
        --countdown
    }

    //Spawn players
    private fun spawnPlayers() {
        val nms = plugin.versionSupport
        for (team in arena.teams) {
            for (p in team.members.toList()) {
                BedWarsTeam.reSpawnInvulnerability[p.uniqueId] = System.currentTimeMillis() + 2000L
                team.firstSpawn(p)
                Sounds.playSound(ConfigPath.SOUND_GAME_START, p)
                nms.sendTitle(p, Language.getMsg(p, Messages.ARENA_STATUS_START_PLAYER_TITLE), null, 0, 30, 10)
                for (tut in Language.getList(p, Messages.ARENA_STATUS_START_PLAYER_TUTORIAL)) {
                    p.sendMessage(PAPISupport.support.replace(p, tut))
                }
            }
        }
    }

    override fun cancel() {
        bukkitTask.cancel()
    }
}
