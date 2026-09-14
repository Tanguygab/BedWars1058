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

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigManager
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.configuration.GameMainOverridable

class ArenaConfig(private val plugin: BedWars, name: String, dir: String) : ConfigManager(plugin, name, dir) {
    init {
        options().setHeader(listOf(
            "${plugin.name} arena configuration file.",
            "Documentation here: https://gitlab.com/andrei1058/BedWars1058/wikis/configuration/Arena-Configuration"
        ))
        addDefault("group", "Default")
        addDefault(ConfigPath.ARENA_DISPLAY_NAME, "")
        addDefault("minPlayers", 2)
        addDefault("maxInTeam", 1)
        addDefault("allowSpectate", true)
        addDefault(ConfigPath.ARENA_SPAWN_PROTECTION, 5)
        addDefault(ConfigPath.ARENA_SHOP_PROTECTION, 1)
        addDefault(ConfigPath.ARENA_UPGRADES_PROTECTION, 1)
        addDefault(ConfigPath.ARENA_GENERATOR_PROTECTION, 1)
        addDefault(ConfigPath.ARENA_ISLAND_RADIUS, 17)
        addDefault("worldBorder", 300)
        addDefault(ConfigPath.ARENA_Y_LEVEL_KILL, -1)
        //addDefault("disableGeneratorsOnOrphanIslands", false);
        addDefault(ConfigPath.ARENA_CONFIGURATION_MAX_BUILD_Y, 180)
        addDefault(ConfigPath.ARENA_DISABLE_GENERATOR_FOR_EMPTY_TEAMS, false)
        addDefault(ConfigPath.ARENA_DISABLE_NPCS_FOR_EMPTY_TEAMS, true)
        addDefault(ConfigPath.ARENA_NORMAL_DEATH_DROPS, false)
        addDefault(ConfigPath.ARENA_USE_BED_HOLO, true)
        addDefault(ConfigPath.ARENA_ALLOW_MAP_BREAK, false)
        addDefault(ConfigPath.ARENA_GAME_RULES, listOf(
            "doDaylightCycle:false",
            "announceAdvancements:false",
            "doInsomnia:false",
            "doImmediateRespawn:true",
            "doWeatherCycle:false",
            "doFireTick:false"
        ))
        options().copyDefaults(true)
        save()

        //convert old configuration
        mapOf(
            "spawnProtection" to ConfigPath.ARENA_SPAWN_PROTECTION,
            "shopProtection" to ConfigPath.ARENA_SHOP_PROTECTION,
            "upgradesProtection" to ConfigPath.ARENA_UPGRADES_PROTECTION,
            "islandRadius" to ConfigPath.ARENA_ISLAND_RADIUS,
            "voidKill" to null,
            ConfigPath.GENERAL_CONFIGURATION_ENABLE_GEN_SPLIT to ""
        ).forEach { (old, new) ->
            if (old !in this) return@forEach
            if (new != null) set(new, get(old))
            set(old, null)
        }
    }

    @get:Suppress("SpellCheckingInspection")
    private val gameOverridables = ConfigPath::class.java.declaredFields
        .filter { it.isAnnotationPresent(GameMainOverridable::class.java) }
        .map { it[null] }
        .filterIsInstance<String>()

    fun isGameOverridable(path: String) = path in gameOverridables

    fun getGameOverridableValue(path: String): Any? {
        if (!isGameOverridable(path)) {
            throw RuntimeException("Given path is not game-overridable: $path")
        }

        return get(path, plugin.mainConfig.get(path))
    }

    fun getGameOverridableBoolean(path: String) = getGameOverridableValue(path) as? Boolean ?: false
    fun getGameOverridableString(path: String) = getGameOverridableValue(path) as? String ?: "invalid"
}
