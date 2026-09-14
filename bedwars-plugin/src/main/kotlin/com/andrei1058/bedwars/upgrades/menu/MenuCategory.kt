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
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.upgrades.MenuContent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemStack

class MenuCategory(private val plugin: BedWars, override val name: String, displayItem: ItemStack) : MenuContent {
    private val displayItem = plugin.versionSupport.addCustomData(displayItem, "MCONT_$name")

    private val menuContentBySlot = mutableMapOf<Int, MenuContent>()

    init {
        Language.saveIfNotExists(Messages.UPGRADES_CATEGORY_GUI_NAME_PATH + name.removePrefix("category-"), "&8$name")
        Language.saveIfNotExists(
            Messages.UPGRADES_CATEGORY_ITEM_NAME_PATH + name.removePrefix("category-"),
            "&cName not set"
        )
        Language.saveIfNotExists(
            Messages.UPGRADES_CATEGORY_ITEM_LORE_PATH + name.removePrefix("category-"),
            mutableListOf("&cLore not set")
        )
    }

    /**
     * Add content to a menu.
     * 
     * @param content content instance.
     * @param slot    where to put the content in the menu.
     * @return false if te given slot is in use.
     */
    fun addContent(content: MenuContent, slot: Int): Boolean {
        if (menuContentBySlot[slot] != null) return false
        menuContentBySlot[slot] = content
        return true
    }

    override fun getDisplayItem(player: Player, team: ITeam): ItemStack {
        val i = ItemStack(displayItem)
        val im = i.itemMeta ?: return i

        im.setDisplayName(
            Language.getMsg(
                player,
                Messages.UPGRADES_CATEGORY_ITEM_NAME_PATH + name.replace("category-", "")
            )
        )
        val lore = Language.getList(player, Messages.UPGRADES_CATEGORY_ITEM_LORE_PATH + name.replace("category-", "")).toMutableList()

        if (name.equals("traps", ignoreCase = true)) {
            var queueLimit = plugin.upgradesManager.configuration.getInt(team.arena.group.lowercase() + "-upgrades-settings.trap-queue-limit")
            if (queueLimit == 0) {
                queueLimit = plugin.upgradesManager.configuration.getInt("default-upgrades-settings.trap-queue-limit")
            }
            if (queueLimit == team.activeTraps.size) {
                lore += ""
                lore += Language.getMsg(player, Messages.UPGRADES_TRAP_QUEUE_LIMIT)
            }
        }
        im.lore = lore
        i.itemMeta = im
        return i
    }

    override fun onClick(player: Player, clickType: ClickType, team: ITeam) {
        val upgradesManager = plugin.upgradesManager
        if (name.equals("category-traps", ignoreCase = true)) {
            var queueLimit = upgradesManager.configuration.getInt(team.arena.group.lowercase() + "-upgrades-settings.trap-queue-limit")
            if (queueLimit == 0) {
                queueLimit = upgradesManager.configuration.getInt("default-upgrades-settings.trap-queue-limit")
            }
            if (queueLimit <= team.activeTraps.size) {
                player.sendMessage(Language.getMsg(player, Messages.UPGRADES_TRAP_QUEUE_LIMIT))
                return
            }
        }
        val inv = Bukkit.createInventory(
            null,
            45,
            Language.getMsg(player, Messages.UPGRADES_CATEGORY_GUI_NAME_PATH + name.replace("category-", ""))
        )
        for ((key, value) in menuContentBySlot) {
            inv.setItem(key, value.getDisplayItem(player, team))
        }
        player.openInventory(inv)
        upgradesManager.setWatchingGUI(player)
    }
}
