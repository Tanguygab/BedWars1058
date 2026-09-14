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
import com.andrei1058.bedwars.api.util.Utils.editMeta
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.upgrades.MenuContent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * Create a separator.
 *
 * @param displayItem display item.
 */
class MenuSeparator(plugin: BedWars, override val name: String, displayItem: ItemStack) : MenuContent {
    private val cleanName = name.removePrefix("separator-")
    private val displayItem = plugin.versionSupport.addCustomData(displayItem, "MCONT_$name")
    private val playerCommands = plugin.upgradesManager.configuration.getStringList("$name.on-click.player")
    private val consoleCommands = plugin.upgradesManager.configuration.getStringList("$name.on-click.console")

    init {
        arrayOf(
            Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH to "&cName not set",
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH to mutableListOf("&cLore not set")
        ).forEach { Language.saveIfNotExists(it.first + cleanName, it.second) }
    }

    override fun getDisplayItem(player: Player, team: ITeam): ItemStack {
        val i = ItemStack(displayItem)
        i.editMeta {
            setDisplayName(Language.getMsg(player, Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + cleanName))
            lore = Language.getList(player, Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + cleanName)
            addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        }
        return i
    }

    override fun onClick(player: Player, clickType: ClickType, team: ITeam) {
        val lang = Language.getLanguage(player)
        for (cmd in playerCommands) {
            if (cmd.isBlank()) continue
            Bukkit.dispatchCommand(
                player,
                cmd.replace("{playername}", player.name)
                    .replace("{player}", player.displayName)
                    .replace("{team}", team.getDisplayName(lang)
                )
            )
        }
        for (cmd in consoleCommands) {
            if (cmd.isBlank()) continue
            Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(),
                cmd.replace("{playername}", player.name)
                    .replace("{player}", player.displayName)
                    .replace("{team}", team.getDisplayName(lang)
                )
            )
        }
    }
}
