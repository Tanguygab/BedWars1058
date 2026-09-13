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
package com.andrei1058.bedwars.listeners

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.events.gameplay.EggBridgeThrowEvent
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.arena.tasks.EggBridgeTask
import org.bukkit.Bukkit
import org.bukkit.entity.Egg
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent

class EggBridge(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onLaunch(e: ProjectileLaunchEvent) {
        val egg = e.entity
        if (BedWars.serverType == ServerType.MULTIARENA && egg.world.name.equals(BedWars.lobbyWorld, ignoreCase = true)) {
            e.isCancelled = true
            return
        }
        if (egg !is Egg) return

        val shooter = egg.shooter
        if (shooter !is Player) return

        val arena = plugin.arenaManager.getArena(shooter) ?: return
        if (!arena.isPlayer(shooter)) return

        val throwEvent = EggBridgeThrowEvent(shooter, arena)
        Bukkit.getPluginManager().callEvent(throwEvent)
        if (throwEvent.isCancelled) {
            e.isCancelled = true
            return
        }

        bridges[egg] = EggBridgeTask(arena, shooter, egg, arena.getTeam(shooter)!!.color)
    }

    @EventHandler
    fun onHit(e: ProjectileHitEvent) {
        val egg = e.entity
        if (egg is Egg) removeEgg(egg)
    }

    companion object {
        /**
         * Get active egg bridges.
         *
         * @since API 11
         */
        private val bridges = mutableMapOf<Egg, EggBridgeTask>()

        /**
         * Remove an egg from the active eggs list
         * 
         * @since API 7
         */
        fun removeEgg(e: Egg) {
            bridges.remove(e)?.cancel()
        }
    }
}
