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
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.PlayerArenaEvent
import com.andrei1058.bedwars.api.events.player.PlayerBedBugSpawnEvent
import com.andrei1058.bedwars.api.events.player.PlayerDreamDefenderSpawnEvent
import com.andrei1058.bedwars.arena.Misc.isProjectile
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

class SpecialsListener(private val plugin: BedWars) : Listener {
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    fun onSpecialInteract(e: PlayerInteractEvent) {
        val item = e.item ?: return

        if (item.type == Material.AIR) return
        val player = e.player

        val arena = plugin.arenaManager.getArena(player) ?: return
        if (arena.respawnSessions.containsKey(player) || !arena.isPlayer(player)) return

        val block = e.clickedBlock ?: return
        val location = block.location

        val shop = BedWars.shop

        if (spawn(player, item, Despawnable.SILVERFISH) {
            val event = PlayerBedBugSpawnEvent(player, arena)
            BedWars.nms.spawnSilverfish(
                location.add(0.0, 1.0, 0.0),
                event.playerTeam,
                shop.getDouble(ConfigPath.SHOP_SPECIAL_SILVERFISH_SPEED),
                shop.getDouble(ConfigPath.SHOP_SPECIAL_SILVERFISH_HEALTH),
                shop.getInt(ConfigPath.SHOP_SPECIAL_SILVERFISH_DESPAWN),
                shop.getDouble(ConfigPath.SHOP_SPECIAL_SILVERFISH_DAMAGE)
            )
            event
        }) {
            e.isCancelled = true
            return
        }

        e.isCancelled = spawn(player, item, Despawnable.GOLEM) {
            val event = PlayerDreamDefenderSpawnEvent(player, arena)
            BedWars.nms.spawnIronGolem(
                location.add(0.0, 1.0, 0.0),
                event.playerTeam,
                shop.getDouble(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_SPEED),
                shop.getDouble(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_HEALTH),
                shop.getInt(ConfigPath.SHOP_SPECIAL_IRON_GOLEM_DESPAWN),
            )
            event
        }
    }

    private fun spawn(player: Player, item: ItemStack, despawnable: Despawnable, spawn: () -> PlayerArenaEvent): Boolean {
        val shop = BedWars.shop
        val projectile = Material.valueOf(shop.getString(despawnable.material)!!)

        if (!shop.getBoolean(despawnable.enabled) ||
            isProjectile(projectile) ||
            item.type != projectile ||
            !BedWars.nms.itemStackDataCompare(item, shop.getInt(despawnable.data).toShort())
        ) return false

        Bukkit.getPluginManager().callEvent(spawn())
        if (BedWars.nms.isProjectile(item)) return true

        BedWars.nms.minusAmount(player, item, 1)
        player.updateInventory()
        return true
    }

    enum class Despawnable(
        val enabled: String,
        val material: String,
        val data: String
    ) {
        SILVERFISH(
            ConfigPath.SHOP_SPECIAL_SILVERFISH_ENABLE,
            ConfigPath.SHOP_SPECIAL_SILVERFISH_MATERIAL,
            ConfigPath.SHOP_SPECIAL_SILVERFISH_DATA
        ),
        GOLEM(
            ConfigPath.SHOP_SPECIAL_IRON_GOLEM_ENABLE,
            ConfigPath.SHOP_SPECIAL_IRON_GOLEM_MATERIAL,
            ConfigPath.SHOP_SPECIAL_IRON_GOLEM_DATA
        )
    }
}
