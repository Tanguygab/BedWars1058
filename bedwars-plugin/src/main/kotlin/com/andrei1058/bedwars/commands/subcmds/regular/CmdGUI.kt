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
package com.andrei1058.bedwars.commands.subcmds.regular

import com.andrei1058.bedwars.arena.ArenaGUI
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.commands.subcmds.SubCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdGUI(parent: MainCommand) : SubCommand(parent, "gui", priority = 17) {
    override val description = createDescription()

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (plugin.arenaManager.getArena(sender) != null) return false

        var group = "default"
        if (args.size == 1) {
            group = args[0]
        }

        ArenaGUI.openGui(sender, group)
        return true
    }
}
