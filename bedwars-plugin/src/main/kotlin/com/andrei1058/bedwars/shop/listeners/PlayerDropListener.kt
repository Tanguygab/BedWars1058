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
package com.andrei1058.bedwars.shop.listeners

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.arena.Arena
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.ItemStack

class PlayerDropListener(private val plugin: BedWars) : Listener {
    //Prevent from dropping permanent items
    @EventHandler
    fun onDrop(e: PlayerDropItemEvent) {
        plugin.arenaManager.getArena(e.player) ?: return
        if (e.itemDrop.itemStack.isShopUpgrade())
            e.isCancelled = true
    }

    //Prevent from moving items in chests
    // This doesn't do anything though?
    @EventHandler
    fun onClose(e: InventoryCloseEvent) {
        val player = e.player
        if (player !is Player) return
        plugin.arenaManager.getArena(player) ?: return

        for (item in e.inventory) {
            if (item == null || item.type == Material.AIR) continue
            if (!item.isShopUpgrade()) return
        }
    }

    private fun ItemStack.isShopUpgrade(): Boolean {
        val identifier = BedWars.nms.getShopUpgradeIdentifier(this)
        return identifier.isNotBlank() && identifier != "null"
    }
}
