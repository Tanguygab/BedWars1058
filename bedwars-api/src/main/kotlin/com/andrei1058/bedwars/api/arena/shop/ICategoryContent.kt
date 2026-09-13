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
package com.andrei1058.bedwars.api.arena.shop

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

interface ICategoryContent {
    /**
     * Get content slot in category
     */
    val slot: Int

    /**
     * Get content preview item in player's language
     */
    fun getItemStack(player: Player): ItemStack

    /**
     * Check if a player has this cc to quick buy
     */
    fun hasQuick(player: Player): Boolean

    /**
     * Check if is permanent content.
     */
    val isPermanent: Boolean

    /**
     * Check if is downgradable.
     */
    val isDowngradable: Boolean

    /**
     * Get category identifier.
     */
    val identifier: String

    /**
     * Get content tiers.
     */
    val contentTiers: List<IContentTier>
}
