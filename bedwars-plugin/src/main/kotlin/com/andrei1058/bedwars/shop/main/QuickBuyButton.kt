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

import com.andrei1058.bedwars.Utils.editMeta
import com.andrei1058.bedwars.api.language.Language
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * Create a new quick buy button
 *
 * @param namePath  Language name path
 * @param lorePath  Language lore path.
 * @param slot      Item slot in inventory
 * @param itemStack Button ItemStack preview
 */
class QuickBuyButton(
    val slot: Int,
    private val itemStack: ItemStack,
    private val namePath: String?,
    private val lorePath: String?
) {
    /**
     * Get the quick buy button in the player's language
     */
    fun getItemStack(player: Player): ItemStack {
        val item = itemStack.clone()
        item.editMeta {
            setDisplayName(Language.getMsg(player, namePath!!))
            lore = Language.getList(player, lorePath!!)
        }
        return item
    }
}
