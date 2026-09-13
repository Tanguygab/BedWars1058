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

import com.andrei1058.bedwars.api.configuration.ConfigManager
import com.andrei1058.bedwars.api.configuration.ConfigPath
import org.bukkit.plugin.Plugin

class GeneratorsConfig(plugin: Plugin) : ConfigManager(plugin, "generators", plugin.dataFolder.path) {
    init {
        options()
            .copyDefaults(true)
            .setHeader(listOf(
                "${plugin.description.name} by andrei1058.",
                "generators.yml Documentation: https://gitlab.com/andrei1058/BedWars1058/wikis/generators-configuration",
                ""
            ))

        fun addDef(path: String, value: Any) {
            addDefault("Default.$path", value)
        }

        addDef(ConfigPath.GENERATOR_IRON_DELAY, 2)
        addDef(ConfigPath.GENERATOR_IRON_AMOUNT, 2)
        addDef(ConfigPath.GENERATOR_GOLD_DELAY, 6)
        addDef(ConfigPath.GENERATOR_GOLD_AMOUNT, 2)
        addDef(ConfigPath.GENERATOR_IRON_SPAWN_LIMIT, 32)
        addDef(ConfigPath.GENERATOR_GOLD_SPAWN_LIMIT, 7)
        addDefault(ConfigPath.GENERATOR_STACK_ITEMS, false)

        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_I_DELAY, 30)
        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_I_AMOUNT, 1)
        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_I_SPAWN_LIMIT, 4)
        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_II_DELAY, 20)
        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_II_AMOUNT, 1)
        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_II_SPAWN_LIMIT, 6)
        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_II_START, 360)
        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_III_DELAY, 15)
        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_III_AMOUNT, 1)
        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_III_SPAWN_LIMIT, 8)
        addDef(ConfigPath.GENERATOR_DIAMOND_TIER_III_START, 1080)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_I_DELAY, 70)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_I_AMOUNT, 1)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_I_SPAWN_LIMIT, 4)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_II_DELAY, 50)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_II_AMOUNT, 1)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_II_SPAWN_LIMIT, 6)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_II_START, 720)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_III_DELAY, 30)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_III_AMOUNT, 1)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_III_SPAWN_LIMIT, 8)
        addDef(ConfigPath.GENERATOR_EMERALD_TIER_III_START, 1440)
        save()
    }
}
