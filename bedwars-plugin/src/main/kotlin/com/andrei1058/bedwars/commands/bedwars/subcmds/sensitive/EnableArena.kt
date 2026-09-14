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

import com.andrei1058.bedwars.arena.Misc
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.bedwars.subcmds.SubCommand
import com.andrei1058.bedwars.configuration.Permissions
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class EnableArena(parent: MainCommand) : SubCommand(
    parent,
    "enableArena",
    Permissions.PERMISSION_ARENA_ENABLE,
    priority = 5
) {
    override val description = createDescription(
        "Enable an arena.",
        syntax = "<worldName>"
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (!isLobbySet(sender)) return true
        if (args.size != 1) {
            sender.sendMessage("§c▪ §7Usage: §o/" + parent.commandName + " enableRotation <mapName>")
            return true
        }

        val arenaName = args[0]
        if (!plugin.restoreAdapter.isWorld(arenaName)) {
            sender.sendMsg("$arenaName doesn't exist!", true)
            return true
        }

        val arenaManager = plugin.arenaManager
        for (mm in arenaManager.enableQueue) {
            if (mm.name.equals(arenaName, ignoreCase = true)) {
                sender.sendMsg("This arena is already in the enable queue!", true)
                return true
            }
        }

        val arena = arenaManager.getArena(arenaName)
        if (arena != null) {
            sender.sendMsg("This arena is already enabled!", true)
            return true
        }
        sender.sendMessage("§6 ▪ §7Enabling arena...")
        arenaManager.loadArena(arenaName, sender)
        return true
    }

    override val tabComplete get() = Misc.getArenas()
}
