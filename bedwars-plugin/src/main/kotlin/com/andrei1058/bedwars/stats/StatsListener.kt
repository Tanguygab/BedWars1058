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
package com.andrei1058.bedwars.stats

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent
import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.time.Instant

class StatsListener(private val plugin: BedWars, private val manager: StatsManagerImpl) : Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    fun onAsyncPreLoginEvent(event: AsyncPlayerPreLoginEvent) {
        if (event.loginResult != AsyncPlayerPreLoginEvent.Result.ALLOWED) {
            // Do nothing if login fails
            return
        }
        val stats = plugin.database.fetchStats(event.uniqueId)
        manager.put(event.uniqueId, stats)
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onPlayerLoginEvent(event: PlayerLoginEvent) {
        if (event.result == PlayerLoginEvent.Result.ALLOWED) return
        // Prevent memory leak if login fails
        manager.remove(event.player.uniqueId)
    }

    @EventHandler
    fun onBedBreak(event: PlayerBedBreakEvent) {
        val stats = manager.get(event.player.uniqueId)
        //store beds destroyed
        stats.bedsDestroyed += 1
    }

    @EventHandler
    fun onPlayerKill(event: PlayerKillEvent) {
        val victimStats = manager.get(event.victim.uniqueId)
        // If killer is not null and not equal to victim
        val killerStats = if (event.victim != event.killer && event.killer != null)
            manager.getUnsafe(event.killer!!.uniqueId)
        else null

        if (event.cause.isFinalKill) {
            //store final deaths
            victimStats.finalDeaths += 1
            //store losses
            victimStats.losses += 1
            //store games played
            victimStats.gamesPlayed += 1
            //store final kills
            if (killerStats != null) killerStats.finalKills = killerStats.finalKills + 1
        } else {
            //store deaths
            victimStats.deaths += 1
            //store kills
            if (killerStats != null) killerStats.kills = killerStats.kills + 1
        }
    }

    @EventHandler
    fun onGameEnd(event: GameEndEvent) {
        for (uuid in event.winners) {
            val player = Bukkit.getPlayer(uuid) ?: continue
            if (!player.isOnline) continue

            val stats = manager.get(uuid)

            // store wins even if is in another game because he assisted this team
            // the ones who abandoned are already removed from the winners list
            stats.wins += 1

            // store games played
            // give if he remained in this arena till the end even if was eliminated
            // for those who left games played are updated in arena leave listener
            val playerArena = plugin.arenaManager.getArena(player)
            if (playerArena != null && playerArena == event.arena) {
                stats.gamesPlayed += 1
            }
        }
    }

    @EventHandler
    fun onArenaLeave(event: PlayerLeaveArenaEvent) {
        val player = event.player

        if (event.arena.status == GameState.STARTING || event.arena.status == GameState.WAITING) return  // Game didn't start
        if (event.arena.getExTeam(player.uniqueId) == null) return  // The player didn't play this game

        val playerStats = manager.getUnsafe(player.uniqueId) ?: return
        // sometimes can be null due to scheduling delays

        // Update last play and first play (if required)
        playerStats.lastPlay = Instant.now()
        if (playerStats.firstPlay == null) {
            playerStats.firstPlay = event.arena.startTime
        }

        //save or replace stats for player - run later because PlayerKillEvent is triggered after PlayerLeaveArenaEvent
        plugin.run(async = true, delay = 10) { plugin.database.saveStats(playerStats) }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onQuit(event: PlayerQuitEvent) {
        manager.remove(event.player.uniqueId)
    }
}
