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
package com.andrei1058.bedwars.configuration

import com.andrei1058.bedwars.BedWars.Companion.getForCurrentVersion
import com.andrei1058.bedwars.api.configuration.ConfigManager
import org.bukkit.plugin.Plugin

class UpgradesConfig(plugin: Plugin) : ConfigManager(plugin, "upgrades2", plugin.dataFolder.path) {
    init {
        val elements = listOf(
            "upgrade-swords,10",
            "upgrade-armor,11",
            "upgrade-miner,12",
            "upgrade-forge,13",
            "upgrade-heal-pool,14",
            "upgrade-dragon,15",
            "category-traps,16",
            "separator-glass,18,19,20,21,22,23,24,25,26",
            "trap-slot-first,30",
            "trap-slot-second,31",
            "trap-slot-third,32"
        )
        default(
            "default-upgrades-settings.menu-content" to elements,
            "default-upgrades-settings.trap-start-price" to 1,
            "default-upgrades-settings.trap-increment-price" to 1,
            "default-upgrades-settings.trap-currency" to "diamond",
            "default-upgrades-settings.trap-queue-limit" to 3
        )

        if (isFirstTime) {
            defaultUpgrade("swords", 1, 4, "IRON_SWORD", "enchant-item: DAMAGE_ALL,1,sword")
            defaultUpgradeTiers("armor", "IRON_CHESTPLATE",
                2 to arrayOf("enchant-item: PROTECTION_ENVIRONMENTAL,1,armor"),
                4 to arrayOf("enchant-item: PROTECTION_ENVIRONMENTAL,2,armor"),
                8 to arrayOf("enchant-item: PROTECTION_ENVIRONMENTAL,3,armor"),
                16 to arrayOf("enchant-item: PROTECTION_ENVIRONMENTAL,4,armor"),
            )

            defaultUpgradeTiers("miner", getForCurrentVersion("GOLDEN_PICKAXE", "GOLD_PICKAXE"),
                2 to arrayOf("player-effect: FAST_DIGGING,0,0,team"),
                4 to arrayOf("player-effect: FAST_DIGGING,1,0,team"),
            )

            defaultUpgradeTiers("forge", "FURNACE",
                2 to arrayOf("generator-edit: iron,2,2,41", "generator-edit: gold,3,1,14"),
                4 to arrayOf("generator-edit: iron,1,2,48", "generator-edit: gold,3,2,21"),
                6 to arrayOf("generator-edit: iron,1,2,64", "generator-edit: gold,3,2,29", "generator-edit: emerald,10,1,10"),
                8 to arrayOf("generator-edit: iron,1,4,120", "generator-edit: gold,2,4,80", "generator-edit: emerald,10,2,20"),
            )

            defaultUpgrade("heal-pool", 1, 1, "BEACON", "player-effect: REGENERATION,1,0,base")
            defaultUpgrade("dragon", 1, 5, "DRAGON_EGG", "dragon: 1")



            val grayPane = getForCurrentVersion("GRAY_STAINED_GLASS_PANE", "STAINED_GLASS_PANE")
            default(
                "category-traps.category-content" to listOf(
                    "base-trap-1,10",
                    "base-trap-2,11",
                    "base-trap-3,12",
                    "base-trap-4,13",
                    "separator-back,31"
                ),
                *getDefaultDisplayItem("category-traps", "LEATHER"),

                "separator-glass.on-click" to "",
                *getDefaultDisplayItem("separator-glass", grayPane, data = 7)
            )

            listOf(
                "first",
                "second",
                "third"
            ).forEachIndexed { i, slot ->
                default(
                    "trap-slot-$slot.trap" to i+1,
                    *getDefaultDisplayItem("trap-slot-$slot", grayPane, i+1, 8)
                )
            }

            addTrap(1, "TRIPWIRE_HOOK", "player-effect: BLINDNESS,1,5,enemy", "player-effect: SLOW,1,5,enemy")
            addTrap(2, "FEATHER", "player-effect: SPEED,1,15,base")
            addTrap(
                3,
                getForCurrentVersion("REDSTONE_TORCH", "REDSTONE_TORCH_ON", "REDSTONE_TORCH"),
                "remove-effect: INVISIBILITY,enemy",
                announce = true
            )
            addTrap(4, "IRON_PICKAXE", "player-effect: SLOW_DIGGING,1,15,enemy")

            default(
                "separator-back.on-click.player" to listOf("bw upgradesmenu"),
                "separator-back.on-click.console" to listOf(""),
                *getDefaultDisplayItem("separator-back", "ARROW")
            )
        }
        options().copyDefaults(true)
        save()
    }

    private fun addTrap(i: Int, material: String, vararg effects: String, announce: Boolean = false) {
        default(
            "base-trap-$i.receive" to effects,
            *getDefaultDisplayItem("base-trap-$i", material)
        )
        if (announce) addDefault("base-trap-$i.custom-announce", true)
    }

    private fun getDefaultDisplayItem(path: String, material: String, amount: Int = 1, data: Int = 0) = arrayOf(
        "$path.display-item.material" to material,
        "$path.display-item.data" to data,
        "$path.display-item.amount" to amount
    )

    private fun defaultUpgrade(upgrade: String, tier: Int, cost: Int, material: String, vararg effects: String, currency: String = "diamond") = default(
        "upgrade-$upgrade.tier-$tier.cost" to cost,
        "$upgrade.tier-$tier.currency" to currency,
        "$upgrade.tier-$tier.receive" to effects,
        *getDefaultDisplayItem("$upgrade.tier-$tier", material, tier)
    )

    private fun defaultUpgradeTiers(upgrade: String, material: String, vararg tiers: Pair<Int, Array<String>>, currency: String = "diamond") {
        tiers.forEachIndexed { i, tier ->
            val (cost, effects) = tier
            defaultUpgrade(upgrade, i+1, cost, material, *effects, currency)
        }
    }
}
