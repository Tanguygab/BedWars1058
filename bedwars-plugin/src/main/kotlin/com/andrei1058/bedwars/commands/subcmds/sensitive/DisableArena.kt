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

import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.commands.subcmds.SubCommand
import com.andrei1058.bedwars.configuration.Permissions
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class DisableArena(parent: MainCommand) : SubCommand(
    parent,
    "disableArena",
    Permissions.PERMISSION_ARENA_DISABLE,
    priority = 6
) {
    override val description = createDescription(
        "Disable an arena.\nThis will remove the players \nfrom the arena before disabling.",
        syntax = "<worldName>",
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (!isLobbySet(sender)) return true
        if (args.size != 1) {
            sender.sendMessage("§c▪ §7Usage: §o/${parent.commandName} $name <mapName>")
            return true
        }
        val arenaName = args[0]
        if (!plugin.restoreAdapter.isWorld(arenaName)) {
            sender.sendMessage("§c▪ §7$arenaName doesn't exist!")
            return true
        }
        val arena = plugin.arenaManager.getArena(arenaName)
        if (arena == null) {
            sender.sendMessage("§c▪ §7This arena is disabled yet!")
            return true
        }
        if (arena.status == GameState.PLAYING) {
            sender.sendMessage("§6 ▪ §7There is a game running on this Arena, please disable after the game!")
            return true
        }
        sender.sendMessage("§6 ▪ §7Disabling arena...")
        arena.disable()
        return true
    }

    override val tabComplete get() = plugin.arenaManager.arenas.keys.toList()
}
