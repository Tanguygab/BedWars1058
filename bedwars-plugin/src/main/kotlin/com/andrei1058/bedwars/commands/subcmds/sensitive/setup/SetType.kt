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
package com.andrei1058.bedwars.commands.subcmds.sensitive.setup

import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.server.SetupType
import com.andrei1058.bedwars.api.util.Utils.message
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class SetType(parent: MainCommand) : SetupCommand(parent, "setType") {

    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        if (args.isEmpty() || !available.contains(args[0])) {
            sender.sendMessage("§9 ▪ §7Usage: ${parent.commandName} $name <type>")
            sender.sendMessage("§9Available types: ")
            for (st in available) sender.message(
                "§1 ▪ §e$st §7(click to set)",
                "§dClick to make the arena $st",
                "/${parent.commandName} $name $st"
            )
            return
        }

        val groups = plugin.mainConfig.getStringList(ConfigPath.GENERAL_CONFIGURATION_ARENA_GROUPS)
        val input = args[0].lowercase().replaceFirstChar { it.uppercaseChar() }
        if (input !in groups) {
            plugin.mainConfig.set(ConfigPath.GENERAL_CONFIGURATION_ARENA_GROUPS, groups + input)
        }
        session.config["maxInTeam"] = when (input.lowercase()) {
            "Solo" -> 1
            "doubles" -> 2
            "3v3v3v3" -> 3
            "4v4v4v4" -> 4
            else -> session.config["maxInTeam"]
        }
        session.config.set("group", input)
        sender.sendMessage("§6 ▪ §7Arena group changed to: §d$input")
        if (session.setupType == SetupType.ASSISTED) Bukkit.dispatchCommand(sender, parent.commandName)
    }

    override val tabComplete get() = plugin.mainConfig
        .getStringList(ConfigPath.GENERAL_CONFIGURATION_ARENA_GROUPS)
        .plus(available)
        .distinct()

    companion object {
        private val available = arrayOf("Solo", "Doubles", "3v3v3v3", "4v4v4v4")
    }
}
