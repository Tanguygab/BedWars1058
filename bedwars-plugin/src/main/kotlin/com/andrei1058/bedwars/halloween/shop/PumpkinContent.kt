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
package com.andrei1058.bedwars.halloween.shop

import com.andrei1058.bedwars.api.util.Utils.editMeta
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.shop.IBuyItem
import com.andrei1058.bedwars.api.arena.shop.IContentTier
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.shop.ShopCache
import com.andrei1058.bedwars.shop.main.CategoryContent
import com.andrei1058.bedwars.shop.main.ShopCategory
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class PumpkinContent(father: ShopCategory) : CategoryContent("null", "null", "null", null, father) {
    override val identifier = "halloween-special-pumpkin"
    override val isPermanent = false
    override val slot = (19..25)
        .union(28..34)
        .union(37..43)
        .find { father.categoryContentList.none { c -> c.slot == it } } ?: -1

    init {
        isLoaded = slot != -1
        if (isLoaded) contentTiers += OneTier()
    }


    override fun getItemStack(player: Player): ItemStack {
        val tier = contentTiers[0]

        val canAfford = calculateMoney(player, tier.currency) >= tier.price
        val translatedCurrency = Language.getMsg(player, getCurrencyMsgPath(tier))

        val buyStatus = Language.getMsg(player, if (canAfford) Messages.SHOP_LORE_STATUS_CAN_BUY
        else Messages.SHOP_LORE_STATUS_CANT_AFFORD).replace("{currency}", translatedCurrency)
        val cColor = getCurrencyColor(tier.currency)

        val pumpkin = tier.itemStack
        pumpkin.amount = 12
        pumpkin.editMeta {
            setDisplayName("${ChatColor.GOLD}${ChatColor.BOLD}Happy Halloween!")
            lore = listOf(
                "",
                "$cColor${tier.price} $translatedCurrency",
                " ",
                buyStatus
            )
        }
        return pumpkin
    }

    override fun getItemStack(player: Player, shopCache: ShopCache) = getItemStack(player)

    private class OneTier : IContentTier {
        override val price = 4
        override val currency = Material.IRON_INGOT
        override var itemStack = ItemStack(Material.PUMPKIN, 12)
        override val value = 4
        override val buyItemsList = listOf(FinalItem())
    }

    private class FinalItem : IBuyItem {
        override val isLoaded = true
        override fun give(player: Player, arena: IArena) {
            player.inventory.addItem(ItemStack(Material.PUMPKIN, 12))
        }

        override val upgradeIdentifier: String? = null
        override var itemStack: ItemStack? = null
        override val isAutoEquip = false
        override val isPermanent = false
        override val isUnbreakable = false
    }
}
