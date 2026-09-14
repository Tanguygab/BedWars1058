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
import com.andrei1058.bedwars.api.upgrades.UpgradeAction
import com.andrei1058.bedwars.upgrades.upgradeaction.*
import com.andrei1058.bedwars.upgrades.upgradeaction.DispatchCommand.CommandType
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffectType

/**
 * @param parentName is the parent name.
 * @param name       is the upgrade identifier.
 */
class UpgradeTier(
    parentName: String,
    val name: String,
    displayItem: ItemStack,
    val cost: Int,
    val currency: Material
) {
    val displayItem = BedWars.INSTANCE.versionSupport.addCustomData(displayItem, "MCONT_$parentName")
    val upgradeActions = mutableListOf<UpgradeAction>()

    init {
        Language.saveIfNotExists(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace(
                "{name}",
                parentName.replace("upgrade-", "")
            ).replace("{tier}", name), "&cName not set"
        )
        Language.saveIfNotExists(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace(
                "{name}",
                parentName.replace("upgrade-", "")
            ).replace("{tier}", name), mutableListOf("&cLore not set")
        )

        val plugin = BedWars.INSTANCE
        val logger = plugin.logger
        for (action in plugin.upgradesManager.configuration.getStringList("$parentName.$name.receive")) {
            val type = action.trim().lowercase().split(":")
            if (type.size < 2) continue

            val arg = type[0].trim()
            var data = type[1].trim().split(",")
            val data0 = data[0].uppercase()

            upgradeActions += when (arg) {
                "enchant-item" -> {
                    if (data.size < 3) {
                        logger.warning("Invalid $arg at upgrades2: $parentName.$name")
                        continue
                    }
                    val e = Enchantment.getByName(data0)
                    if (e == null) {
                        logger.warning("Invalid enchantment $data0 at upgrades2: $parentName.$name")
                        continue
                    }
                    val apply = when (data[2].lowercase()) {
                        "sword" -> EnchantItemAction.ApplyType.SWORD
                        "armor" -> EnchantItemAction.ApplyType.ARMOR
                        "bow" -> EnchantItemAction.ApplyType.BOW
                        else -> {
                            logger.warning("Invalid apply type ${data[2]} at upgrades2: $parentName.$name")
                            continue
                        }
                    }
                    val amplifier = data[1].toIntOrNull() ?: 1
                    EnchantItemAction(e, amplifier, apply)
                }

                "player-effect" -> {
                    if (data.size < 4) {
                        logger.warning("Invalid ${type[0]} at upgrades2: $parentName.$name")
                        continue
                    }
                    val pe = PotionEffectType.getByName(data0)
                    if (pe == null) {
                        logger.warning("Invalid potion effect ${data[0]} at upgrades2: $parentName.$name")
                        continue
                    }
                    val applyType = when (data[3].lowercase()) {
                        "team" -> PlayerEffectAction.ApplyType.TEAM
                        "base" -> PlayerEffectAction.ApplyType.BASE
                        else -> {
                            logger.warning("Invalid apply type ${data[3]} at upgrades2: $parentName.$name")
                            continue
                        }
                    }
                    val amp = data[1].toIntOrNull() ?: 1
                    val time = data[2].toIntOrNull() ?: 0
                    PlayerEffectAction(pe, amp, time, applyType)
                }

                "generator-edit" -> {
                    if (data.size < 4) {
                        logger.warning("Invalid ${type[0]} at upgrades2: $parentName.$name")
                        continue
                    }
                    val genType = when (data0) {
                        "GOLD", "G" -> GeneratorEditAction.ApplyType.GOLD
                        "IRON", "I" -> GeneratorEditAction.ApplyType.IRON
                        "EMERALD", "E" -> GeneratorEditAction.ApplyType.EMERALD
                        else -> {
                            logger.warning("Invalid generator type $data0 at upgrades2: $parentName.$name")
                            continue
                        }
                    }
                    val spawn = data[1].toIntOrNull()
                    val amount = data[2].toIntOrNull()
                    val limit = data[3].toIntOrNull()
                    if (spawn == null || amount == null || limit == null) {
                        logger.warning("Invalid generator configuration $data0 at upgrades2: $parentName.$name")
                        continue
                    }
                    GeneratorEditAction(genType, amount, spawn, limit)
                }

                "dragon" -> {
                    if (data.isEmpty()) {
                        logger.warning("Invalid ${type[0]} at upgrades2: $parentName.$name")
                        continue
                    }
                    val dragons = data0.toIntOrNull()
                    if (dragons == null) {
                        logger.warning("Invalid dragon amount at upgrades2: $parentName.$name")
                        continue
                    }
                    DragonAction(dragons)
                }

                "command" -> {
                    // once-as-console,command
                    if (data.size < 2) {
                        logger.warning("Invalid ${type[0]} at upgrades2: $parentName.$name")
                        continue
                    }
                    val cmdType = CommandType.entries.find { it.name == data0 }
                    if (cmdType == null) {
                        logger.warning("Invalid command type $data0 at upgrades2: $parentName.$name")
                        continue
                    }
                    // re-do here because the first one does a trim on data
                    // we need data with spaces
                    data = type[1].split(",")
                    DispatchCommand(cmdType, data[1])
                }
                else -> continue
            }
        }
    }
}
