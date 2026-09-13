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

import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.events.player.PlayerGeneratorCollectEvent
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.support.version.common.VersionCommon
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Entity
import org.bukkit.entity.Item
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDropItemEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerPickupArrowEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.inventory.ItemStack

object ItemDropPickListener {

    internal fun checkServerType(player: Player) = VersionCommon.api.serverType == ServerType.MULTIARENA &&
            player.location.world!!.name.equals(VersionCommon.api.lobbyWorld, ignoreCase = true)

    internal fun checkArena(arena: IArena, player: Player) = !arena.isPlayer(player) ||
        arena.status != GameState.PLAYING ||
        arena.respawnSessions.containsKey(player)

    /**
     * @return true if event should be cancelled
     */
    private fun managePickup(item: Item, player: LivingEntity): Boolean {
        if (player !is Player) return false
        if (checkServerType(player)) return true

        val arena = VersionCommon.api.arenaManager.getArena(player) ?: return false

        if (checkArena(arena, player)) return true

        if (item.itemStack.type == Material.ARROW) {
            item.setItemStack(ItemStack(item.itemStack.type, item.itemStack.amount))
            return false
        }

        if (VersionCommon.api.versionSupport.isBed(item.itemStack.type)) {
            item.remove()
            return true
        }

        if (item.itemStack.hasItemMeta() &&
            item.itemStack.itemMeta!!.hasDisplayName() &&
            item.itemStack.itemMeta!!.displayName.startsWith("custom")
        ) {
            //Call ore pick up event
            if (VersionCommon.api.afkManager.isAFK(player)) return true //Cancel event if player is afk

            val event = PlayerGeneratorCollectEvent(player, item, arena)
            Bukkit.getPluginManager().callEvent(event)
            if (!event.isCancelled) {
                item.itemStack.itemMeta = ItemStack(item.itemStack.type).itemMeta
            }
        }
        return false
    }

    /**
     * @return true to cancel the event.
     */
    private fun manageDrop(player: Entity, item: Item): Boolean {
        if (player !is Player) return false
        if (checkServerType(player)) return true

        val arena = VersionCommon.api.arenaManager.getArena(player) ?: return false
        return checkArena(arena, player) || item.itemStack.type == Material.COMPASS
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
        fun onDrop(@Suppress("DEPRECATION") e: PlayerPickupItemEvent) {
            if (managePickup(e.item, e.player)) e.isCancelled = true
        }
    }

    // 1.13 or newer
    class EntityDrop : Listener {
        @EventHandler
        fun onDrop(e: EntityDropItemEvent) {
            if (manageDrop(e.entity, e.itemDrop)) e.isCancelled = true
        }
    }

    // 1.12 or newer
    class EntityPickup : Listener {
        @EventHandler
        fun onPickup(e: EntityPickupItemEvent) {
            if (managePickup(e.item, e.entity)) e.isCancelled = true
        }
    }

    // 1.9 or newer
    class ArrowCollect : Listener {
        @EventHandler
        fun onArrowPick(e: PlayerPickupArrowEvent) {
            if (VersionCommon.api.arenaManager.isSpectating(e.player)) e.isCancelled = true
        }
    }
}
