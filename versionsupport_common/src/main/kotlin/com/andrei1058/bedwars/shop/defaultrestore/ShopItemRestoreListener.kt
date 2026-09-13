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
package com.andrei1058.bedwars.shop.defaultrestore

import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.support.version.common.VersionCommon
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDropItemEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerPickupItemEvent

// Used to restore default swords and bows if they are removed from the inventory and you remain with a less powerful weapon of the same kind. 1.12-.
object ShopItemRestoreListener {
    /**
     * Remove the default swords if the picked item is more powerful.
     *
     * Only return false. Default item should only be checked. Access tools should be put in chests.
     */
    fun managePickup(item: Item, player: LivingEntity) {
        if (player !is Player) return

        val arena = VersionCommon.api.arenaManager.getArena(player) ?: return
        if (arena.status != GameState.PLAYING || !arena.isPlayer(player)) return

        val versionSupport = VersionCommon.api.versionSupport
        if (!versionSupport.isSword(item.itemStack)) return

        for (item in player.inventory) {
            if (item == null || item.type == Material.AIR) continue
            if (!versionSupport.isCustomBedWarsItem(item)) continue
            if (versionSupport.getCustomData(item).equals("DEFAULT_ITEM", ignoreCase = true)) {
                player.inventory.remove(item)
                player.updateInventory()
                break
            }
        }
    }

    /**
     * If the dropped sword/ bow is a default item and is more powerful
     * than the others in the inventory give it back.
     * 
     * 
     * If the player remains without a sword give it the swords from the default items.
     * If the player remains without a bow give it bows from the default items.
     * 
     * @return true to cancel the event.
     */
    private fun manageDrop(player: Entity, item: Item): Boolean {
        if (player !is Player) return false

        val arena = VersionCommon.api.arenaManager.getArena(player) ?: return false
        if (arena.status != GameState.PLAYING || !arena.isPlayer(player)) return false

        val versionSupport = VersionCommon.api.versionSupport
        if (versionSupport.isCustomBedWarsItem(item.itemStack)
            && versionSupport.getCustomData(item.itemStack).equals("DEFAULT_ITEM", ignoreCase = true)
            && versionSupport.isSword(item.itemStack)
        ) {
            val damage = versionSupport.getDamage(item.itemStack)
            return player.inventory.none { it != null && versionSupport.isSword(it) && versionSupport.getDamage(it) >= damage }
        } else {
            if (player.inventory.none { it != null && versionSupport.isSword(it) })
                arena.getTeam(player)!!.defaultSword(player)
        }
        return false
    }


    // 1.11 or older
    class PlayerDrop : Listener {
        @EventHandler
        fun onDrop(e: PlayerDropItemEvent) {
            if (manageDrop(e.player, e.itemDrop)) e.isCancelled = true
        }
    }

    // 1.11 or older
    class PlayerPickup : Listener {
        @EventHandler
        fun onPickUp(@Suppress("DEPRECATION") e: PlayerPickupItemEvent) {
            managePickup(e.item, e.player)
        }
    }

    // 1.12 or newer
    class EntityDrop : Listener {
        @EventHandler
        fun onDrop(e: EntityDropItemEvent) {
            if (manageDrop(e.entity, e.itemDrop)) e.isCancelled = true
        }
    }

    // 1.12 or newer
    class EntityPickup : Listener {
        @EventHandler
        fun onPickUp(e: EntityPickupItemEvent) {
            managePickup(e.item, e.entity)
        }
    }

    class DefaultRestoreInvClose : Listener {
        /**
         * If the player moves a default sword or bow into another inventory
         * and he remains with a less powerful weapon, restore the lost one.
         */
        @EventHandler
        fun onInventoryClose(e: InventoryCloseEvent) {
            if (e.inventory.type == InventoryType.PLAYER) return

            val arenaManager = VersionCommon.api.arenaManager
            val player = e.player as? Player ?: return
            val arena = arenaManager.getArena(player) ?: return
            if (arena.status != GameState.PLAYING || !arena.isPlayer(player)) return

            var sword = false
            for (item in player.inventory) {
                if (item == null || item.type == Material.AIR) continue
                if (VersionCommon.api.versionSupport.isSword(item)) sword = true
            }

            if (sword) return
            val team = arena.getTeam(player)
            if (team != null && !arena.isRespawning(player)) {
                team.defaultSword(player)
            }
        }
    }
}
