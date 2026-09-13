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
package com.andrei1058.bedwars.commands.bedwars.subcmds.regular

import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.api.command.SubCommand
import com.andrei1058.bedwars.arena.ArenaGUI
import com.andrei1058.bedwars.arena.SetupSession
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdGUI(parent: ParentCommand) : SubCommand("gui", isShown = false, priority = 17) {
    init {
        displayInfo = createTC(
            "§6 ▪ §7/${parent.commandName} $subCommandName",
            "/${parent.commandName} $subCommandName",
            "§fOpens the arena GUI."
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (BedWars.INSTANCE.arenaManager.getArena(sender) != null) return false

        var group = "default"
        if (args.size == 1) {
            group = args[0]
        }

        ArenaGUI.openGui(sender, group)
        return true
    }

    override fun canSee(sender: CommandSender, api: BedWars) = sender is Player &&
            BedWars.INSTANCE.arenaManager.isInArena(sender) &&
            !SetupSession.isInSetupSession(sender.uniqueId) &&
            canUse(sender)
}
