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
import com.andrei1058.bedwars.api.configuration.ConfigPath
import org.bukkit.plugin.Plugin

class SignsConfig(plugin: Plugin, name: String, dir: String) : ConfigManager(plugin, name, dir) {
    init {
        default(
            "format" to listOf("&a[arena]", "", "&2[on]&9/&2[max] &7([type])", "[status]"),
            ConfigPath.SIGNS_STATUS_BLOCK_WAITING_MATERIAL to getForCurrentVersion("GREEN_CONCRETE", "STAINED_CLAY"),
            ConfigPath.SIGNS_STATUS_BLOCK_WAITING_DATA to 5,
            ConfigPath.SIGNS_STATUS_BLOCK_STARTING_MATERIAL to getForCurrentVersion("YELLOW_CONCRETE", "STAINED_CLAY"),
            ConfigPath.SIGNS_STATUS_BLOCK_STARTING_DATA to 14,
            ConfigPath.SIGNS_STATUS_BLOCK_PLAYING_MATERIAL to getForCurrentVersion("RED_CONCRETE", "STAINED_CLAY"),
            ConfigPath.SIGNS_STATUS_BLOCK_PLAYING_DATA to 4,
            ConfigPath.SIGNS_STATUS_BLOCK_RESTARTING_MATERIAL to getForCurrentVersion("RED_CONCRETE", "STAINED_CLAY"),
            ConfigPath.SIGNS_STATUS_BLOCK_RESTARTING_DATA to 4
        )
        options().copyDefaults(true)
        save()

        val format = getStringList("format")
        if (format.size > 4) {
            set("format", format.subList(0, 3))
        }
    }
}
