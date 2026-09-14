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
import com.andrei1058.bedwars.api.util.Utils.editMeta
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.shop.ShopCache
import com.andrei1058.bedwars.shop.ShopConfig
import com.andrei1058.bedwars.shop.ShopConfig.Companion.hideItemStuff
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.UUID

class ShopCategory(val name: String, yml: YamlConfiguration) {
    /**
     * Get category slot in shop index
     */
    val slot = yml.getInt(name + ConfigPath.SHOP_CATEGORY_SLOT)
    private var itemStack: ItemStack
    private val itemNamePath = Messages.SHOP_CATEGORY_ITEM_NAME.replace("%category%", name)
    private val itemLorePath = Messages.SHOP_CATEGORY_ITEM_LORE.replace("%category%", name)
    private val invNamePath = Messages.SHOP_CATEGORY_INVENTORY_NAME.replace("%category%", name)

    /**
     * Check if category was loaded
     */
    var isLoaded: Boolean = true
        private set
    val categoryContentList = mutableListOf<CategoryContent>()

    /**
     * Load a shop category from the given path
     */
    init {
        BedWars.debug("Loading shop category: $name")

        val plugin = BedWars.INSTANCE
        var material = yml.getString(name + ConfigPath.SHOP_CATEGORY_ITEM_MATERIAL)
        if (material == null) {
            plugin.logger.severe("Category material not set at: $name")
            material = "AIR"
            isLoaded = false
        }

        if (name + ConfigPath.SHOP_CATEGORY_SLOT !in yml) {
            plugin.logger.severe("Category slot not set at: $name")
            isLoaded = false
        }

        if (slot !in 1..8) {
            plugin.logger.severe("Slot must be n > 1 and n < 9 at: $name")
            isLoaded = false
        }

        for (sc in ShopConfig.shop.categoryList) {
            if (sc.slot != slot) continue
            plugin.logger.severe("Slot is already in use at: $name")
        }

        val nms = plugin.versionSupport
        itemStack = nms.createItemStack(
            material,
            yml.getInt(name + ConfigPath.SHOP_CATEGORY_ITEM_AMOUNT, 1),
            yml.getInt(name + ConfigPath.SHOP_CATEGORY_ITEM_DATA).toShort()
        )


        if (yml.get(name + ConfigPath.SHOP_CATEGORY_ITEM_ENCHANTED) != null) {
            if (yml.getBoolean(name + ConfigPath.SHOP_CATEGORY_ITEM_ENCHANTED)) {
                itemStack = ShopConfig.enchantItem(itemStack)
            }
        }

        // potion display color based on NBT tag
        val potionDisplay = yml.getString("$name.category-item.potion-display")
        if (!potionDisplay.isNullOrEmpty()) {
            itemStack = nms.setTag(itemStack, "Potion", potionDisplay)
        }
        // 1.16+ custom color
        val potionColor = yml.getString("$name.category-item.potion-color")
        if (!potionColor.isNullOrEmpty()) {
            itemStack = nms.setTag(itemStack, "CustomPotionColor", potionColor)
        }

        itemStack = hideItemStuff(itemStack)

        val path = "$name.${ConfigPath.SHOP_CATEGORY_CONTENT_PATH}"
        for (s in yml.getConfigurationSection(path)!!.getKeys(false)) {
            val cc = CategoryContent("$path.$s", s, name, yml, this)
            if (!cc.isLoaded) continue
            categoryContentList += cc
            BedWars.debug("Adding CategoryContent: $s to Shop Category: $name")
        }
    }

    fun open(player: Player, index: ShopIndex, shopCache: ShopCache) {
        ShopIndex.indexViewers.remove(player.uniqueId)

        player.openInventory(Bukkit.createInventory(null, index.invSize, Language.getMsg(player, invNamePath)).apply {
            setItem(index.quickBuyButton.slot, index.quickBuyButton.getItemStack(player))

            for (sc in index.categoryList) {
                setItem(sc.slot, sc.getItemStack(player))
            }

            index.addSeparator(player, this)
            setItem(slot + 9, index.getSelectedItem(player))

            shopCache.selectedCategory = slot

            for (cc in categoryContentList) {
                setItem(cc.slot, cc.getItemStack(player, shopCache))
            }
        })

        if (player.uniqueId !in categoryViewers) {
            categoryViewers += player.uniqueId
        }
    }

    /**
     * Get the category preview item in player's language
     */
    fun getItemStack(player: Player): ItemStack {
        val i = itemStack.clone()
        i.editMeta {
            setDisplayName(Language.getMsg(player, itemNamePath))
            lore = Language.getList(player, itemLorePath)
        }
        return i
    }

    companion object {
        var categoryViewers = mutableListOf<UUID>()

        /**Get a category content by identifier */
        fun getCategoryContent(identifier: String?, shopIndex: ShopIndex) = shopIndex
            .categoryList
            .asSequence()
            .flatMap { it.categoryContentList }
            .find { it.identifier == identifier }

    }
}
