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
package com.andrei1058.bedwars.sidebar

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.serverType
import com.andrei1058.bedwars.api.events.player.*
import com.andrei1058.bedwars.api.server.ServerType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityRegainHealthEvent
import org.bukkit.event.player.PlayerJoinEvent
import kotlin.math.ceil

class ScoreboardListener(private val manager: ScoreboardManagerImpl) : Listener {
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onPlayerDamage(e: EntityDamageEvent) {
        val player = e.entity
        if (player !is Player) return

        val arena = BedWars.plugin.arenaManager.getArena(player) ?: return

        val health = ceil((player.health - e.getFinalDamage())).toInt()
        manager.refreshHealth(arena, player, health)
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onRegain(e: EntityRegainHealthEvent) {
        val player = e.entity
        if (player !is Player) return

        val arena = BedWars.plugin.arenaManager.getArena(player) ?: return

        val health = ceil(player.health + e.amount).toInt()
        manager.refreshHealth(arena, player, health)
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onReSpawn(e: PlayerReSpawnEvent) {
        val arena = e.arena
        val player = e.player
        manager.refreshHealth(arena, player, ceil(player.health).toInt())
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun reJoin(e: PlayerReJoinEvent) {
        // re-add player to scoreboard tab list
        manager.handleReJoin(e.arena, e.player)
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun arenaJoin(e: PlayerJoinArenaEvent) {
        // add player to scoreboard tab list
        manager.handleJoin(e.arena, e.player, e.isSpectator)
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun serverJoin(e: PlayerJoinEvent) {
        if (serverType == ServerType.MULTIARENA || serverType == ServerType.SHARED) {
            // add player to scoreboard tab list
            manager.applyLobbyTab(e.player)
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun arenaLeave(e: PlayerLeaveArenaEvent) {
        if (serverType == ServerType.MULTIARENA || serverType == ServerType.SHARED) {
            // add player to scoreboard tab list
            manager.applyLobbyTab(e.player)
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onBedDestroy(e: PlayerBedBreakEvent) {
        // refresh placeholders in case placeholders refresh is disabled
        manager.refreshPlaceholders(e.arena)
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onFinalKill(e: PlayerKillEvent) {
        if (!e.cause.isFinalKill) return
        // refresh placeholders in case placeholders refresh is disabled
        manager.refreshPlaceholders(e.arena)
    }
}
