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
import com.andrei1058.bedwars.Utils.editMeta
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.shop.ShopCache
import com.andrei1058.bedwars.shop.ShopManager
import com.andrei1058.bedwars.shop.main.CategoryContent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class PlayerQuickBuyCache(player: Player) {
    val elements = mutableListOf<QuickBuyElement>()
    private val emptyItem: ItemStack
    private val player = player.uniqueId
    private val task = QuickBuyTask(this.player)

    private val updateSlots = HashMap<Int, String>()

    init {
        val emptyItem = BedWars.nms.createItemStack(
            BedWars.shop.getString(ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_MATERIAL)!!,
            BedWars.shop.getInt(ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_AMOUNT),
            BedWars.shop.getInt(ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_DATA).toShort()
        )
        this.emptyItem = if (BedWars.shop.getBoolean(ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_ENCHANTED))
            ShopManager.enchantItem(emptyItem)
        else emptyItem
        quickBuyCaches[this.player] = this
    }


    /**
     * Add the player's preferences to the given inventory.
     * This will also add the red empty item.
     */
    fun addInInventory(inv: Inventory, shopCache: ShopCache) {
        val player = Bukkit.getPlayer(player) ?: return

        for (qbe in elements) {
            inv.setItem(qbe.slot, qbe.categoryContent!!.getItemStack(player, shopCache))
        }

        if (elements.size == 21) return

        val i = getEmptyItem(player)
        for (x in quickSlots) {
            if (inv.getItem(x) == null) {
                inv.setItem(x, i)
            }
        }
    }

    fun destroy() {
        elements.clear()
        task.cancel()
        quickBuyCaches.remove(player)
        pushChangesToDB()
    }

    fun setElement(slot: Int, cc: CategoryContent?) {
        elements.removeIf { it.slot == slot }
        val element = if (cc != null) {
            addQuickElement(QuickBuyElement(cc.identifier, slot))
            cc.identifier
        } else " "
        updateSlots[slot] = element
    }

    private fun getEmptyItem(player: Player): ItemStack {
        val item = emptyItem.clone()
        item.editMeta {
            setDisplayName(Language.getMsg(player, Messages.SHOP_QUICK_EMPTY_NAME))
            lore = Language.getList(player, Messages.SHOP_QUICK_EMPTY_LORE)
        }
        return item
    }

    /**
     * Check if as category content at quick buy
     */
    fun hasCategoryContent(cc: CategoryContent?) = elements.any { it.categoryContent === cc }

    /**
     * Add a quick buy element
     */
    fun addQuickElement(e: QuickBuyElement) {
        elements += e
    }

    fun pushChangesToDB() {
        BedWars.plugin.run(async = true) { BedWars.remoteDatabase.pushQuickBuyChanges(updateSlots, player, elements) }
    }

    companion object {
        val quickSlots = intArrayOf(19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43)
        private val quickBuyCaches = ConcurrentHashMap<UUID, PlayerQuickBuyCache>()

        /**
         * Get a Player Quick buy cache
         */
        fun getQuickBuyCache(uuid: UUID) = quickBuyCaches[uuid]
    }
}
