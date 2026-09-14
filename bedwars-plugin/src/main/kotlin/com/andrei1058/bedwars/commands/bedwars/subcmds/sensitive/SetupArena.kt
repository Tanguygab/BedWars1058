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
package com.andrei1058.bedwars.commands.bedwars.subcmds.sensitive

import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.bedwars.subcmds.SubCommand
import com.andrei1058.bedwars.configuration.Permissions
import net.md_5.bungee.api.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetupArena(parent: MainCommand) : SubCommand(
    parent,
    "setupArena",
    Permissions.PERMISSION_SETUP_ARENA,
    priority = 2
) {
    override val description = createDescription(
        "Create or edit an arena.\n'_' and '-' will not be displayed in the arena's name.",
        syntax = "<worldName>",
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (!isLobbySet(sender)) return true
        if (args.size != 1) {
            sender.sendMsg("Usage: §o/${parent.commandName} $name <mapName>", true)
            return true
        }
        val arg = args[0]
        if (arg != arg.lowercase()) {
            sender.sendMsg("§c$arg${ChatColor.GRAY} mustn't contain capital letters! Rename your folder to: ${ChatColor.GREEN}${arg.lowercase()}", true)
            return true
        }
        if ("+" in args) {
            sender.sendMsg("$arg mustn't contain this symbol: ${ChatColor.RED}+", true)
            return true
        }
        //if (!plugin.restoreAdapter.isWorld(arg)) {
        //    sender.sendMsg("$arg doesn't exist!", true)
        //    return true
        //}
        if (plugin.arenaManager.getArena(arg) != null && !plugin.autoScale) {
            sender.sendMsg("Please disable it first!", true)
            return true
        }
        if (SetupSession.isInSetupSession(sender.uniqueId)) {
            sender.sendMsg("You're already in a setup session!", true)
            return true
        }
        SetupSession(plugin, sender, arg)
        return true
    }

    override val tabComplete get() = plugin.restoreAdapter.worldsList
}
