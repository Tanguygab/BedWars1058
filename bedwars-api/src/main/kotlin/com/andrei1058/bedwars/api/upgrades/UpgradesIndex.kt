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
package com.andrei1058.bedwars.api.upgrades

import org.bukkit.entity.Player

interface UpgradesIndex {
    /**
     * Get menu name.
     */
    val name: String

    /**
     * Open this menu to a player.
     * Make sure to use [com.andrei1058.bedwars.api.UpgradesManager.setWatchingGUI]
     * 
     * @param player target player.
     */
    fun open(player: Player)

    /**
     * Add content to a menu.
     * 
     * @param content content instance.
     * @param slot    where to put the content in the menu.
     * @return false if te given slot is in use.
     */
    fun addContent(content: MenuContent, slot: Int)

    /**
     * 
     * @return total amount of tiers in upgrades
     */
    fun countTiers(): Int

    val menuContentBySlot: Map<Int, MenuContent>
}
