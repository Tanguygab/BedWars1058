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
package com.andrei1058.bedwars.shop.main

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.debug
import com.andrei1058.bedwars.api.arena.shop.IBuyItem
import com.andrei1058.bedwars.api.arena.shop.IContentTier
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.shop.ShopConfig
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack

class ContentTier(path: String?, tierName: String, identifier: String, yml: YamlConfiguration) : IContentTier {
    /**
     * Get tier level
     */
    override val value: Int

    /**
     * Get tier price
     */
    override val price: Int
    override var itemStack: ItemStack
    override val currency: Material

    /**
     * Get items
     */
    override val buyItemsList = mutableListOf<IBuyItem>()

    /**
     * Create a content tier for a category content
     */
    init {
        debug("Loading content tier$path")

        val plugin = BedWars.INSTANCE
        var material = yml.getString(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_MATERIAL)
        if (material == null) {
            plugin.logger.severe("tier-item material not set at $path")
            material = "AIR"
        }

        value = try {
            tierName.removePrefix("tier").toInt()
        } catch (_: Exception) {
            plugin.logger.severe("$path doesn't end with a number. It's not recognized as a tier!")
            0
        }

        if (!yml.contains(path + ConfigPath.SHOP_CONTENT_TIER_SETTINGS_COST)) {
            plugin.logger.severe("Cost not set for $path")
        }
        price = yml.getInt(path + ConfigPath.SHOP_CONTENT_TIER_SETTINGS_COST)

        var currency = yml.getString(path + ConfigPath.SHOP_CONTENT_TIER_SETTINGS_CURRENCY)
        if (currency == null) plugin.logger.severe("Currency not set for $path")
        else if (currency.isEmpty()) plugin.logger.severe("Invalid currency at $path")

        this.currency = when (currency?.lowercase()) {
            "iron", "gold", "diamond", "vault", "emerald" -> CategoryContent.getCurrency(
                yml.getString(path + ConfigPath.SHOP_CONTENT_TIER_SETTINGS_CURRENCY)!!.lowercase()
            )
            else -> {
                plugin.logger.severe("Invalid currency at $path")
                Material.IRON_INGOT
            }
        }

        val nms = plugin.versionSupport
        itemStack = nms.createItemStack(
            material,
            yml.getInt(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_AMOUNT, 1),
            yml.getInt(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_DATA).toShort()
        )


        if (yml.getBoolean(path + ConfigPath.SHOP_CONTENT_TIER_ITEM_ENCHANTED)) {
            itemStack = ShopConfig.enchantItem(itemStack)
        }

        // potion display color based on NBT tag
        val potionDisplay = yml.getString("$path.tier-item.potion-display")
        if (!potionDisplay.isNullOrEmpty()) itemStack = nms.setTag(itemStack, "Potion", potionDisplay)

        // 1.16+ custom color
        val potionColor = yml.getString("$path.tier-item.potion-color")
        if (!potionColor.isNullOrEmpty()) itemStack = nms.setTag(itemStack, "CustomPotionColor", potionColor)

        itemStack = ShopConfig.hideItemStuff(itemStack)

        var buyItem: IBuyItem?
        val section = yml.getConfigurationSection("$path.${ConfigPath.SHOP_CONTENT_BUY_ITEMS_PATH}")
        if (section != null) {
            for (s in section.getKeys(false)) {
                buyItem = BuyItem("$path.${ConfigPath.SHOP_CONTENT_BUY_ITEMS_PATH}.$s", yml, identifier, this)
                if (buyItem.isLoaded) buyItemsList.add(buyItem)
            }
        }

        val commandsPath = "$path.${ConfigPath.SHOP_CONTENT_BUY_CMDS_PATH}"
        if (yml.contains(commandsPath)) {
            buyItem = BuyCommand(commandsPath, yml, identifier)
            if (buyItem.isLoaded) buyItemsList.add(buyItem)
        }

        if (buyItemsList.isEmpty()) Bukkit.getLogger().warning("Loaded 0 buy content for: $path")
    }
}
