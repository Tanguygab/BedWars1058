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
package com.andrei1058.bedwars.shop

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.debug
import com.andrei1058.bedwars.arena.Arena
import com.andrei1058.bedwars.shop.main.CategoryContent
import com.andrei1058.bedwars.shop.main.ShopCategory
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.UUID

class ShopCache(val player: UUID) {
    val cachedItems = mutableListOf<CachedItem>()
    var selectedCategory = ShopConfig.shop.quickBuyButton.slot
    private var categoryWeight = HashMap<ShopCategory, Byte>()

    init {
        shopCaches += this
    }

    fun getContentTier(identifier: String) = getCachedItem(identifier)?.tier ?: 1

    /**
     * Destroy data
     */
    fun destroy() {
        shopCaches.remove(this)
        cachedItems.clear()
        categoryWeight.clear()
    }

    /**
     * Used to give items on player respawn
     */
    fun managePermanentsAndDowngradables(arena: Arena) {
        debug("Restore permanents on death for: $player")
        cachedItems.forEach { it.manageDeath(arena) }
    }

    /**
     * Keep trace of shop items and player's tiers
     */
    inner class CachedItem(val cc: CategoryContent) {
        var tier = 1
            private set

        init {
            cachedItems += this
            debug("New Cached item ${cc.identifier} for player $player")
        }

        /**
         * Give permanents on death
         * and downgrade if necessary
         */
        fun manageDeath(arena: Arena) {
            if (!cc.isPermanent) return
            if (cc.isDowngradable && tier > 1) tier--
            debug("ShopCache Item Restore: ${cc.identifier} for $player")
            cc.giveItems(Bukkit.getPlayer(player)!!, getShopCache(player)!!, arena)
        }

        fun upgrade(slot: Int) {
            tier++
            val p = Bukkit.getPlayer(player)
            for (i in p!!.inventory.contents) {
                if (i == null) continue
                if (i.type == Material.AIR) continue
                if (BedWars.INSTANCE.versionSupport.getShopUpgradeIdentifier(i) == cc.identifier) {
                    p.inventory.remove(i)
                }
            }
            updateItem(slot, p)
            p.updateInventory()
        }

        fun updateItem(slot: Int, p: Player) {
            p.openInventory.topInventory.setItem(slot, cc.getItemStack(Bukkit.getPlayer(player)!!, getShopCache(player)!!))
        }
    }

    /**
     * Get a player's cached item
     */
    fun getCachedItem(identifier: String) = cachedItems.find { it.cc.identifier == identifier }

    /**
     * Check if the player has a cached item
     */
    fun hasCachedItem(cc: CategoryContent) = cachedItems.any { it.cc === cc }

    fun getCachedItem(cc: CategoryContent) = cachedItems.find { it.cc === cc }

    /**
     * Upgrade cached item
     * Add it if not found
     */
    fun upgradeCachedItem(cc: CategoryContent, slot: Int) {
        val ci = getCachedItem(cc.identifier)
        if (ci == null) {
            CachedItem(cc).updateItem(slot, Bukkit.getPlayer(player)!!)
            return
        }
        if (cc.contentTiers.size > ci.tier) {
            debug("Cached item upgrade for ${cc.identifier} player $player")
            ci.upgrade(slot)
        }
    }

    /**
     * Used for categories where you can't buy lower items
     * Ex. if you have bought diamond iron from it, you can't buy stone iron
     */
    fun setCategoryWeight(sc: ShopCategory, weight: Byte) {
        categoryWeight[sc] = weight
    }

    fun getCategoryWeight(sc: ShopCategory?) = categoryWeight[sc] ?: 0

    /**
     * Get permanent and non downgradable shop items.
     */
    val cachedPermanents get() = cachedItems.filter { it.cc.isPermanent }

    companion object {
        private val shopCaches = mutableListOf<ShopCache>()

        fun getShopCache(player: UUID) = shopCaches.find { it.player == player }
    }
}
