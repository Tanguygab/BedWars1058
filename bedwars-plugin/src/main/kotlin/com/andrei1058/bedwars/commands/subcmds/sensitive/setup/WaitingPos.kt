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

import com.andrei1058.bedwars.api.util.Utils.message
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class WaitingPos(parent: MainCommand) : SetupCommand(parent, "waitingPos") {
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        val pos1 = "waiting.Pos1" in session.config
        val pos2 = "waiting.Pos2" in session.config

        var message = "§c▪ §7Usage: /${parent.commandName} $name 1 or 2"
        if (args.isNotEmpty()) {
            val pos = args[0]
            if (pos == "1" || pos == "2") {
                session.config.saveArenaLoc("waiting.Pos$pos", sender.location)
                session.config.reload()
                message = "§6 ▪ §7Pos $pos set!"
            }
        }
        sender.sendMessage(message)
        if (message.startsWith("§6")) {
            if (!pos1) sender.askForPosition(1)
            else if (!pos2) sender.askForPosition(2)
        }

        if (pos1 && pos2) {
            Bukkit.dispatchCommand(sender, "${parent.commandName} cmds")
            sender.sendMessage("§6 ▪ §7Set teams spawn if you didn't!")
        }
    }

    private fun Player.askForPosition(position: Int) {
        sendMessage("§c ▪ §7Set the remaining position:")
        message(
            "§c ▪ §7/${parent.commandName} waitingPos $position",
            "§dSet pos $position",
            "/${parent.commandName} waitingPos $position"
        )
    }

    override val tabComplete = listOf("1", "2")
}
