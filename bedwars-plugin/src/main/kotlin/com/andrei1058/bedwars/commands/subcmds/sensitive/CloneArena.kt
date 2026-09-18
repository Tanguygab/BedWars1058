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
package com.andrei1058.bedwars.commands.subcmds.sensitive

import com.andrei1058.bedwars.Misc
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.commands.subcmds.SubCommand
import com.andrei1058.bedwars.configuration.Permissions
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.io.File
import java.io.IOException

class CloneArena(parent: MainCommand) : SubCommand(
    parent,
    "cloneArena",
    Permissions.PERMISSION_CLONE,
    priority = 7
) {
    override val description = createDescription(
        "Clone an existing arena.",
        syntax = "<worldName> <newName>",
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (!isLobbySet(sender)) return true
        if (args.size != 2) {
            sender.sendMsg("Usage: §o/${parent.commandName} $name <mapName> <newArena>", true)
            return true
        }
        val map = args[0]
        val clone = args[1]
        if (!plugin.restoreAdapter.isWorld(args[0])) {
            sender.sendMsg(args[0] + " doesn't exist!", true)
            return true
        }
        val mapYml = File(plugin.dataFolder, "/Arenas/$map.yml")
        val cloneYml = File(plugin.dataFolder, "/Arenas/$clone.yml")
        if (!mapYml.exists()) {
            sender.sendMsg("$map doesn't exist!", true)
            return true
        }
        if (plugin.restoreAdapter.isWorld(args[1]) && cloneYml.exists()) {
            sender.sendMsg("$clone already exist!", true)
            return true
        }
        if (args[1].contains("+")) {
            sender.sendMsg("$clone mustn't contain this symbol: " + ChatColor.RED + "+", true)
            return true
        }
        if (plugin.arenaManager.getArena(map) != null) {
            sender.sendMsg("Please disable $map first!", true)
            return true
        }
        plugin.restoreAdapter.cloneArena(map, clone)
        if (mapYml.exists()) {
            try {
                mapYml.copyTo(cloneYml, true)
            } catch (e: IOException) {
                e.printStackTrace()
                sender.sendMsg("An error occurred while copying the map's config. Check the console.", true)
            }
        }
        sender.sendMsg("Done :D.")
        return true
    }

    override val tabComplete get() = Misc.getArenas()
}
