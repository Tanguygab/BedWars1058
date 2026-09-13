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

import com.andrei1058.bedwars.api.arena.team.ITeam
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

interface MenuContent {
    /**
     * Get identifier.
     *
     * @return content name.
     */
    val name: String

    /**
     * Item that represent the item in the GUI.
     *
     * @return item.
     */
    fun getDisplayItem(player: Player, team: ITeam): ItemStack

    /**
     * Manage what to do on click.
     */
    fun onClick(player: Player, clickType: ClickType, team: ITeam)
}
