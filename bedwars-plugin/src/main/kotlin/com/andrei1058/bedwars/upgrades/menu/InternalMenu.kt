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
package com.andrei1058.bedwars.upgrades.menu

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.upgrades.EnemyBaseEnterTrap
import com.andrei1058.bedwars.api.upgrades.MenuContent
import com.andrei1058.bedwars.api.upgrades.TeamUpgrade
import com.andrei1058.bedwars.api.upgrades.UpgradesIndex
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Create an upgrade menu for an arena group.
 *
 * @param name arena group name.
 */
class InternalMenu(private val plugin: BedWars, override val name: String) : UpgradesIndex {
    override val menuContentBySlot = HashMap<Int, MenuContent>()

    init {
        Language.saveIfNotExists(
            Messages.UPGRADES_MENU_GUI_NAME_PATH + name,
            "&8Upgrades & Traps"
        )
    }

    override fun open(player: Player) {
        val arena = plugin.arenaManager.getArena(player) ?: return
        if (!arena.isPlayer(player)) return
        val team = arena.getTeam(player) ?: return
        if (!plugin.arenaManager.isPlaying(player)) return

        val inv = Bukkit.createInventory(null, 45, Language.getMsg(player, Messages.UPGRADES_MENU_GUI_NAME_PATH + name))
        for ((key, value) in menuContentBySlot) {
            inv.setItem(key, value.getDisplayItem(player, team))
        }
        player.openInventory(inv)
        plugin.upgradesManager.setWatchingGUI(player)
    }

    override fun addContent(content: MenuContent, slot: Int) {
        if (slot !in menuContentBySlot) menuContentBySlot[slot] = content
    }

    override fun countTiers() = menuContentBySlot.values
        .asSequence()
        .filterIsInstance<TeamUpgrade>()
        .filterNot { it is EnemyBaseEnterTrap }
        .sumOf { it.tierCount }

}
