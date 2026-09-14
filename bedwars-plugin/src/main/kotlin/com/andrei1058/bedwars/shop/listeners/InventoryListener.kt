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
import com.andrei1058.bedwars.shop.ShopCache
import com.andrei1058.bedwars.shop.ShopConfig
import com.andrei1058.bedwars.shop.main.ShopCategory
import com.andrei1058.bedwars.shop.main.ShopIndex
import com.andrei1058.bedwars.shop.quickbuy.PlayerQuickBuyCache
import com.andrei1058.bedwars.shop.quickbuy.QuickBuyAdd
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack

class InventoryListener(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.isCancelled) return

        val player = e.whoClicked
        if (player !is Player) return

        val arena = plugin.arenaManager.getArena(player) ?: return
        if (arena.isSpectator(player)) return

        val shopCache = ShopCache.getShopCache(player.uniqueId) ?: return
        val cache = PlayerQuickBuyCache.getQuickBuyCache(player.uniqueId) ?: return


        if ((player.uniqueId in ShopIndex.indexViewers ||
                player.uniqueId in ShopCategory.categoryViewers) &&
            e.clickedInventory?.type == InventoryType.PLAYER
        ) {
            e.isCancelled = true
            return
        }

        val shop = ShopConfig.shop
        if (player.uniqueId in ShopIndex.indexViewers) {
            e.isCancelled = true

            for (sc in shop.categoryList) {
                if (e.slot != sc.slot) continue
                sc.open(player, shop, shopCache)
                return
            }
            for (element in cache.elements) {
                if (element.slot != e.slot) continue

                if (e.action == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                    cache.setElement(element.slot, null)
                    player.closeInventory()
                    return
                }
                element.categoryContent!!.execute(player, shopCache, element.slot)
                return
            }
            return
        }

        if (player.uniqueId in ShopCategory.categoryViewers) {
            e.isCancelled = true
            for (sc in shop.categoryList) {
                if (shop.quickBuyButton.slot == e.slot) {
                    shop.open(player, cache, false)
                    return
                }
                if (e.slot == sc.slot) {
                    sc.open(player, shop, shopCache)
                    return
                }
                if (sc.slot != shopCache.selectedCategory) continue
                for (cc in sc.categoryContentList) {
                    if (cc.slot != e.slot) continue
                    if (e.action == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                        if (cache.hasCategoryContent(cc)) return
                        QuickBuyAdd(player, cc)
                        return
                    }
                    cc.execute(player, shopCache, cc.slot)
                    return
                }
            }
            return
        }

        val cc = QuickBuyAdd.quickBuyAdds[e.whoClicked.uniqueId] ?: return
        e.isCancelled = true
        if (PlayerQuickBuyCache.quickSlots.none { it == e.slot }) return
        cache.setElement(e.slot, cc)
        e.whoClicked.closeInventory()
    }

    @EventHandler
    fun onUpgradableMove(e: InventoryClickEvent) {
        val player = e.whoClicked
        if (player !is Player) return
        val sc = ShopCache.getShopCache(player.uniqueId) ?: return

        val inventory = e.clickedInventory
        //block moving from hotbar
        if (e.action == InventoryAction.HOTBAR_SWAP && e.click == ClickType.NUMBER_KEY && e.hotbarButton > -1) {
            val i = player.inventory.getItem(e.hotbarButton)
            if (i != null && inventory !== player.inventory && shouldCancelMovement(i, sc)) {
                e.isCancelled = true
            }
        }

        //block moving cursor item
        val cursor = e.cursor
        if (cursor != null && cursor.type != Material.AIR) {
            if (inventory == null) {
                if (shouldCancelMovement(cursor, sc)) {
                    player.closeInventory()
                    e.isCancelled = true
                }
            } else if (inventory.type != player.inventory.type && shouldCancelMovement(cursor, sc)) {
                player.closeInventory()
                e.isCancelled = true
            }
        }

        //block moving current item
        val item = e.currentItem
        if (item != null && item.type != Material.AIR) {
            if (inventory == null) {
                // is this normal? shouldn't this check for the currentItem?
                if (shouldCancelMovement(cursor, sc)) {
                    player.closeInventory()
                    e.isCancelled = true
                }
            } else if (inventory.type != player.inventory.type && shouldCancelMovement(item, sc)) {
                player.closeInventory()
                e.isCancelled = true
            }
        }

        //block moving with shift
        if (e.action == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            if (shouldCancelMovement(item, sc)) {
                if (e.view.topInventory.holder != null && e.inventory.holder === player) return
                e.isCancelled = true
            }
        }
    }

    @EventHandler
    fun onShopClose(e: InventoryCloseEvent) {
        ShopIndex.indexViewers.remove(e.player.uniqueId)
        ShopCategory.categoryViewers.remove(e.player.uniqueId)
        QuickBuyAdd.quickBuyAdds.remove(e.player.uniqueId)
    }

    companion object {
        /**
         * Check can move item outside inventory.
         * Block despawnable, permanent and start items dropping and inventory change.
         */
        fun shouldCancelMovement(i: ItemStack?, sc: ShopCache?): Boolean {
            if (i == null || sc == null) return false

            val nms = BedWars.INSTANCE.versionSupport
            if (nms.isCustomBedWarsItem(i) &&
                nms.getCustomData(i).equals("DEFAULT_ITEM", ignoreCase = true)
            ) return true

            val identifier = nms.getShopUpgradeIdentifier(i)
            return identifier != "null" && sc.getCachedItem(identifier) != null
            // the commented line below was blocking movement only if tiers amount > 1
            // return sc.getCachedItem(identifier).cc.getContentTiers().size > 1
        }
    }
}
