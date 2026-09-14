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

import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.shop.ShopCache
import com.andrei1058.bedwars.shop.ShopConfig
import com.andrei1058.bedwars.shop.main.CategoryContent
import com.andrei1058.bedwars.shop.main.ShopCategory
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

class QuickBuyAdd(player: Player, cc: CategoryContent) {
    init {
        ShopCategory.categoryViewers.remove(player.uniqueId)
        open(player, cc)
    }

    fun open(player: Player, cc: CategoryContent) {
        val inv = Bukkit.createInventory(null, ShopConfig.shop.invSize, Language.getMsg(player, Messages.SHOP_QUICK_ADD_NAME))
        val cache = PlayerQuickBuyCache.getQuickBuyCache(player.uniqueId)
        val sc = ShopCache.getShopCache(player.uniqueId)
        if (sc == null || cache == null) {
            player.closeInventory()
            return
        }

        inv.setItem(4, cc.getItemStack(player, sc))
        cache.addInInventory(inv, sc)

        player.openInventory(inv)
        quickBuyAdds[player.uniqueId] = cc
    }

    companion object {
        val quickBuyAdds = mutableMapOf<UUID, CategoryContent>()
    }
}
