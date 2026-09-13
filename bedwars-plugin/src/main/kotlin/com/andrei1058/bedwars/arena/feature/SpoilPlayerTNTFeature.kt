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
package com.andrei1058.bedwars.arena.feature

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.potion.PotionEffectType
import java.util.LinkedList

object SpoilPlayerTNTFeature {
    private val playersWithTnt = LinkedList<Player>()
    private var enabled = false

    fun init(plugin: BedWars) {
        val enable = BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_PERFORMANCE_SPOIL_TNT_PLAYERS)
        if (enable && !enabled) {
            enabled = true
            plugin.server.pluginManager.registerEvents(TNTListener(plugin), plugin)
            plugin.server.scheduler.runTaskTimer(plugin, ParticleTask(), 20, 1L)
        }
        plugin.metrics.appendPie("tnt_spoil_enable") { "$enable" }
    }

    private class ParticleTask : Runnable {
        override fun run() {
            for (player in playersWithTnt) {
                if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) return
                BedWars.nms.playRedStoneDot(player)
            }
        }
    }

    private class TNTListener(private val plugin: BedWars) : Listener {
        @EventHandler
        fun onDie(e: PlayerKillEvent) {
            playersWithTnt.remove(e.victim)
        }

        @EventHandler
        fun onLeave(e: PlayerLeaveArenaEvent) {
            playersWithTnt.remove(e.player)
        }

        @EventHandler(ignoreCancelled = true)
        fun onPickUp(e: PlayerPickupItemEvent) {
            if (e.item.itemStack.type != Material.TNT) return
            val player = e.player
            if (player in playersWithTnt || !player.isPlaying()) return

            playersWithTnt += player
        }

        @EventHandler(ignoreCancelled = true)
        fun onDrop(e: PlayerDropItemEvent) {
            if (e.itemDrop.itemStack.type != Material.TNT) return

            val player = e.player
            if (player !in playersWithTnt || Material.TNT in player.inventory) return
            if (!player.isPlaying()) return

            playersWithTnt.remove(player)
        }

        @EventHandler(ignoreCancelled = true)
        fun onPlace(e: BlockPlaceEvent) {
            val player = e.player
            if (!player.isPlaying()) return

            if (e.itemInHand.type != Material.TNT) return

            if (player !in playersWithTnt) return
            plugin.run(delay = 1) {
                if (Material.TNT !in player.inventory) {
                    playersWithTnt.remove(player)
                }
            }
        }

        @EventHandler(ignoreCancelled = true)
        fun inventorySwitch(event: InventoryCloseEvent) {
            val player = event.player as? Player ?: return
            if (!player.isPlaying()) return

            val hasTnt = Material.TNT in player.inventory

            if (player in playersWithTnt) {
                if (!hasTnt) playersWithTnt.remove(player)
                return
            }
            if (hasTnt) playersWithTnt += player
        }

        private fun Player.isPlaying() = plugin.arenaManager.isPlaying(this)
    }

}