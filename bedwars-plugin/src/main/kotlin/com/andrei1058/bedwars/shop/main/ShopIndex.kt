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
import com.andrei1058.bedwars.Utils.editMeta
import com.andrei1058.bedwars.api.events.shop.ShopOpenEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.shop.ShopCache
import com.andrei1058.bedwars.shop.quickbuy.PlayerQuickBuyCache
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.UUID

/**
 * Create a shop index
 *
 * @param namePath          Message path for the shop inventory name
 * @param quickBuyButton    Player quick buy preferences cache
 * @param separatorNamePath Message path for the shop separator item name
 * @param separatorLorePath Message path for the shop separator lore name
 * @param separatorSelected ItemStack for selected category indicator
 * @param separatorStandard ItemStack for standard separator
 */
class ShopIndex(
    /**
     * Get the inventory name path
     */
    val namePath: String,
    /**
     * Get the quick buy button
     */
    val quickBuyButton: QuickBuyButton,
    private val separatorNamePath: String,
    private val separatorLorePath: String,
    var separatorSelected: ItemStack,
    var separatorStandard: ItemStack
) {
    /**
     * Get the inventory size
     */
    val invSize = 54

    /**
     * Get the shop's categories
     */
    val categoryList = mutableListOf<ShopCategory>()

    /**
     * Open this shop to a player
     * 
     * @param callEvent     true if you want to call the shop open event
     * @param quickBuyCache the player cache regarding his preferences
     * @param player        target player
     */
    fun open(player: Player, quickBuyCache: PlayerQuickBuyCache, callEvent: Boolean) {
        if (callEvent) {
            val event = ShopOpenEvent(player, BedWars.plugin.arenaManager.getArena(player)!!)
            Bukkit.getPluginManager().callEvent(event)
            if (event.isCancelled) return
        }

        val inv = Bukkit.createInventory(null, invSize, Language.getMsg(player, namePath))

        inv.setItem(quickBuyButton.slot, quickBuyButton.getItemStack(player))

        for (sc in categoryList) {
            inv.setItem(sc.slot, sc.getItemStack(player))
        }

        addSeparator(player, inv)

        inv.setItem(quickBuyButton.slot + 9, getSelectedItem(player))
        ShopCache.getShopCache(player.uniqueId)?.selectedCategory = quickBuyButton.slot

        quickBuyCache.addInInventory(inv, ShopCache.getShopCache(player.uniqueId)!!)

        player.openInventory(inv)
        if (player.uniqueId !in indexViewers) indexViewers += player.uniqueId
    }


    private fun getItem(player: Player, base: ItemStack): ItemStack {
        val item = base.clone()
        item.editMeta {
            setDisplayName(Language.getMsg(player, separatorNamePath))
            lore = Language.getList(player, separatorLorePath)
        }
        return item
    }

    /**
     * Add shop separator between categories and items
     */
    fun addSeparator(player: Player, inv: Inventory) {
        val item = getItem(player, separatorStandard)

        for (x in 9..17) {
            inv.setItem(x, item)
        }
    }

    /**
     * This is the item that indicates the selected category
     */
    fun getSelectedItem(player: Player) = getItem(player, separatorSelected)

    /**
     * Add a shop category
     */
    fun addShopCategory(sc: ShopCategory) {
        categoryList += sc
        debug("Adding shop category: $sc at slot ${sc.slot}")
    }

    companion object {
        val indexViewers = mutableListOf<UUID>()
    }
}
