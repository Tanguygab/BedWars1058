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
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.server.ServerType
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.entity.ItemSpawnEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.event.weather.WeatherChangeEvent

class HungerWeatherSpawn(private val plugin: BedWars) : Listener {
    private val hungerWaiting = BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_HUNGER_WAITING)
    private val hungerIngame = BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_HUNGER_INGAME)

    @EventHandler
    fun onFoodChange(e: FoodLevelChangeEvent) {
        if (e.isCancelled) return
        val player = e.entity as? Player ?: return
        val arena = plugin.arenaManager.getArena(player)

        // Don't cancel hunger for shared mode outside of arena
        if (arena == null && BedWars.serverType == ServerType.SHARED) return

        // Cancel hunger in MULTIARENA lobby and for spectators
        if (arena == null || arena.isSpectator(player)) {
            e.isCancelled = true
            return
        }

        e.isCancelled = when (arena.status) {
            GameState.WAITING,
            GameState.STARTING,
            GameState.RESTARTING -> !hungerWaiting

            GameState.PLAYING -> !hungerIngame
        }
    }

    @EventHandler
    fun onWeatherChange(e: WeatherChangeEvent) {
        if (!e.toWeatherState()) return

        if (BedWars.serverType != ServerType.SHARED) {
            e.isCancelled = true
            return
        }

        if (plugin.arenaManager.getArenaByWorld(e.world.name) != null) {
            e.isCancelled = true
        }
    }

    @EventHandler //Used to prevent creature spawn
    fun onCreatureSpawn(e: CreatureSpawnEvent) {
        if (e.spawnReason == CreatureSpawnEvent.SpawnReason.CUSTOM) return

        if (BedWars.serverType == ServerType.BUNGEE) {
            e.isCancelled = true
            return
        }

        if (plugin.arenaManager.getArenaByWorld(e.entity.world.name) != null) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onDrink(e: PlayerItemConsumeEvent) {
        val player = e.player
        val arenaManager = plugin.arenaManager
        arenaManager.getArena(e.player) ?: return
        /* remove empty bottle */
        when (e.item.type) {
            Material.GLASS_BOTTLE -> BedWars.nms.minusAmount(player, e.item, 1)
            Material.MILK_BUCKET -> {
                e.isCancelled = true
                BedWars.nms.minusAmount(player, e.item, 1)
                val task = plugin.server.scheduler.runTaskLater(plugin, Runnable {
                    arenaManager.magicMilk.remove(player.uniqueId)
                    BedWars.debug("PlayerItemConsumeEvent player $player was removed from magicMilk")
                }, 20 * 30L).taskId
                arenaManager.magicMilk[player.uniqueId] = task
            }
            else -> {}
        }
    }

    @EventHandler //Prevent item spawning, issue #60
    fun onItemSpawn(e: ItemSpawnEvent) {
        val location = e.entity.location.world ?: return
        val arena = plugin.arenaManager.getArenaByWorld(location.name) ?: return
        if (arena.status == GameState.PLAYING) return
        e.isCancelled = true
    }
}
