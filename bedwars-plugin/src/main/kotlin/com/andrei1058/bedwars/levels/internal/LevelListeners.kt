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
package com.andrei1058.bedwars.levels.internal

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent
import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent
import com.andrei1058.bedwars.api.events.player.PlayerXpGainEvent
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class LevelListeners(private val plugin: BedWars) : Listener {

    private val levels = plugin.levelsConfig

    //create new level data on player join
    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerJoin(e: PlayerJoinEvent) {
        plugin.run(async = true) {
            val uuid = e.getPlayer().uniqueId
            val levelData = BedWars.remoteDatabase.getLevelData(uuid)
            PlayerLevel.getLevelByPlayer(uuid).lazyLoad((levelData[0] as Int?)!!, (levelData[1] as Int?)!!)
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerQuit(e: PlayerQuitEvent) {
        BedWars.plugin.run(async = true) {
            PlayerLevel.getLevelByPlayer(e.player.uniqueId).destroy()
        }
    }

    @EventHandler
    fun onGameEnd(e: GameEndEvent) {
        for (p in e.winners) {
            val p1 = Bukkit.getPlayer(p) ?: continue
            val level = PlayerLevel.getLevelByPlayer(p)

            val xpAmount = levels.getInt("xp-rewards.game-win")
            if (xpAmount > 0) {
                level.addXp(xpAmount, PlayerXpGainEvent.XpSource.GAME_WIN)
                p1.sendLangMsg(Messages.XP_REWARD_WIN, "{xp}" to "$xpAmount")
            }

            val bwt = e.arena.getExTeam(p1.uniqueId) ?: continue
            if (bwt.membersCache.size <= 1) continue

            val xpAmountPerTmt = levels.getInt("xp-rewards.per-teammate")
            if (xpAmountPerTmt <= 0) continue

            val tr = xpAmountPerTmt * bwt.membersCache.size
            level.addXp(tr, PlayerXpGainEvent.XpSource.PER_TEAMMATE)
            p1.sendLangMsg("xp-reward-per-teammate", "{xp}" to "$tr")
        }
        for (p in e.losers) {
            val p1 = Bukkit.getPlayer(p) ?: continue
            val bwt = e.arena.getExTeam(p1.uniqueId) ?: continue
            if (bwt.membersCache.size <= 1) continue

            val xpAmountPerTmt = levels.getInt("xp-rewards.per-teammate")
            if (xpAmountPerTmt <= 0) continue

            val tr = levels.getInt("xp-rewards.per-teammate") * bwt.membersCache.size
            PlayerLevel.getLevelByPlayer(p).addXp(tr, PlayerXpGainEvent.XpSource.PER_TEAMMATE)
            p1.sendLangMsg(Messages.XP_REWARD_PER_TEAMMATE, "{xp}" to "$tr")
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onArenaLeave(e: PlayerLeaveArenaEvent) {
        BedWars.plugin.run(async = true) { PlayerLevel.getLevelByPlayer(e.player.uniqueId).updateDatabase() }
    }

    @EventHandler
    fun onBreakBed(e: PlayerBedBreakEvent) {
        val bedDestroy = levels.getInt("xp-rewards.bed-destroyed")
        if (bedDestroy <= 0) return

        val player = e.player
        PlayerLevel.getLevelByPlayer(player.uniqueId).addXp(bedDestroy, PlayerXpGainEvent.XpSource.BED_DESTROYED)
        player.sendLangMsg(Messages.XP_REWARD_BED_DESTROY, "{xp}" to "$bedDestroy")
    }

    @EventHandler
    fun onKill(e: PlayerKillEvent) {
        val player = e.killer ?: return
        val victim = e.victim
        if (victim == player) return

        val level = PlayerLevel.getLevelByPlayer(player.uniqueId)
        if (e.cause.isFinalKill) {
            val finalKill = levels.getInt("xp-rewards.final-kill")
            if (finalKill <= 0) return
            level.addXp(finalKill, PlayerXpGainEvent.XpSource.FINAL_KILL)
            player.sendLangMsg(Messages.XP_REWARD_FINAL_KILL, "{xp}" to "$finalKill")
            return
        }

        val regularKill = levels.getInt("xp-rewards.regular-kill")
        if (regularKill <= 0) return
        level.addXp(regularKill, PlayerXpGainEvent.XpSource.REGULAR_KILL)
        player.sendLangMsg(Messages.XP_REWARD_REGULAR_KILL, "{xp}" to "$regularKill")
    }
}
