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
package com.andrei1058.bedwars.shop.quickbuy

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.shop.ShopConfig
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable
import java.util.UUID

class QuickBuyTask(private val uuid: UUID) : BukkitRunnable() {
    private val plugin = BedWars.INSTANCE
    init {
        runTaskLaterAsynchronously(plugin, (20 * 7).toLong())
    }

    override fun run() {
        val player = Bukkit.getPlayer(uuid)
        if (player == null || !player.isOnline) {
            cancel()
            return
        }

        val cache = PlayerQuickBuyCache.getQuickBuyCache(uuid)
        if (cache == null) {
            cancel()
            return
        }

        if (plugin.database.hasQuickBuy(uuid)) {
            // slot, identifier
            val items = plugin.database.getQuickBuySlots(uuid, PlayerQuickBuyCache.quickSlots)
            if (items.isEmpty()) return
            for ((key, value) in items) {
                if (value.isBlank()) continue
                val e = QuickBuyElement(value, key)
                if (e.isLoaded) cache.addQuickElement(e)
            }
            return
        }

        val config = plugin.shopManager.config
        val quickShop = config.getConfigurationSection(ConfigPath.SHOP_QUICK_DEFAULTS_PATH) ?: return
        for (key in quickShop.getKeys(false)) {
            val path = config.getString("${ConfigPath.SHOP_QUICK_DEFAULTS_PATH}.$key.path") ?: continue

            val slotString = config.getString("${ConfigPath.SHOP_QUICK_DEFAULTS_PATH}.$key.slot")
            val slot = slotString?.toIntOrNull()
            if (slot == null) {
                BedWars.debug("$slotString must be an integer!")
                continue
            }

            for (category in ShopConfig.shop.categoryList) {
                for (content in category.categoryContentList) {
                    if (content.identifier != path) continue
                    cache.setElement(slot, content)
                }
            }
        }
    }
}
