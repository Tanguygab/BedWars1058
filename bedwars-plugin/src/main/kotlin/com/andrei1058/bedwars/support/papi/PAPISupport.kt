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
package com.andrei1058.bedwars.support.papi

import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.entity.Player

object PAPISupport {
    var support: TextParser = Default()

    interface TextParser {
        fun replace(player: Player?, s: String): String
        fun replace(player: Player?, strings: List<String>): List<String>
    }

    class Default : TextParser {
        override fun replace(player: Player?, s: String) = s
        override fun replace(player: Player?, strings: List<String>) = strings
    }

    class PAPI : TextParser {
        override fun replace(player: Player?, s: String) = PlaceholderAPI.setPlaceholders(player, s)
        override fun replace(player: Player?, strings: List<String>) = PlaceholderAPI.setPlaceholders(player, strings)
    }
}
