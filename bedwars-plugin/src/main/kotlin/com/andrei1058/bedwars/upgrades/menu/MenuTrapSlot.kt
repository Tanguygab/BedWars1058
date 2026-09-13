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
import com.andrei1058.bedwars.Utils.editMeta
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.upgrades.MenuContent
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * @param displayItem display item.
 */
class MenuTrapSlot(override val name: String, displayItem: ItemStack) : MenuContent {
    private val displayItem = BedWars.nms.addCustomData(displayItem, "MCONT_$name")
    private var trap: Int

    init {
        Language.saveIfNotExists(
            Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + name.removePrefix("trap-slot-"),
            "&cName not set"
        )
        Language.saveIfNotExists(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + name.removePrefix("trap-slot-"),
            mutableListOf("&cLore1 not set")
        )
        Language.saveIfNotExists(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + name.removePrefix("trap-slot-"),
            mutableListOf("&cLore2 not set")
        )
        trap = BedWars.api.upgradesManager.configuration.getInt("$name.trap")
        if (trap < 0) trap = 0
        if (trap != 0) trap -= 1
    }

    override fun getDisplayItem(player: Player, team: ITeam): ItemStack {
        val ebe = if (team.activeTraps.isEmpty() || team.activeTraps.size <= trap) null
        else team.activeTraps[trap]
        val item = (ebe?.itemStack ?: displayItem).clone()
        item.amount = trap + 1
        item.editMeta {
            setDisplayName(Language
                .getMsg(player, Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + name.replace("trap-slot-", ""))
                .replace("{name}", Language.getMsg(player, ebe?.nameMsgPath ?: Messages.MEANING_NO_TRAP))
                .replace("{color}", Language.getMsg(player, if (ebe == null)
                    Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD
                else Messages.FORMAT_UPGRADE_COLOR_UNLOCKED
                ))
            )

            val lore = mutableListOf<String>()
            if (ebe != null) {
                lore += Language.getList(player, ebe.loreMsgPath)
                lore += Language.getList(player,
                    Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + name.replace("trap-slot-", "")
                )
            } else {
                val upgrades = BedWars.api.upgradesManager
                val arena = team.arena.name.lowercase()
                var cost = upgrades.configuration.getInt("$arena-upgrades-settings.trap-start-price")
                if (cost == 0) cost = upgrades.configuration.getInt("default-upgrades-settings.trap-start-price")
                val curr = upgrades.configuration.getString("$arena-upgrades-settings.trap-currency")
                    ?: upgrades.configuration.getString("default-upgrades-settings.trap-currency")

                val currency: String = upgrades.getCurrencyMsg(player, cost, curr)

                if (team.activeTraps.isNotEmpty()) {
                    var multiplier = upgrades.configuration.getInt("$arena-upgrades-settings.trap-increment-price")
                    if (multiplier == 0) {
                        multiplier = upgrades.configuration.getInt("default-upgrades-settings.trap-increment-price")
                    }
                    cost += team.activeTraps.size * multiplier
                }
                for (s in Language.getList(
                    player,
                    Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + name.replace("trap-slot-", "")
                )) lore.add(s.replace("{cost}", "$cost").replace("{currency}", currency))
                lore.add("")
                for (s in Language.getList(
                    player,
                    Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + name.replace("trap-slot-", "")
                )) lore.add(s.replace("{cost}", "$cost").replace("{currency}", currency))
            }
            this.lore = lore
            addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        }
        return item
    }

    override fun onClick(player: Player, clickType: ClickType, team: ITeam) {}
}
