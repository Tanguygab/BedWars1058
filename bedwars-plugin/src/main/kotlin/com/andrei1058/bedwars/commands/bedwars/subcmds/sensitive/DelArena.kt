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
import java.io.File

class DelArena(parent: MainCommand) : SubCommand(
    parent,
    "delArena",
    Permissions.PERMISSION_DEL_ARENA,
    priority = 4
) {
    override val description = createDescription(
        "Delete a map and its configuration.",
        syntax = "<worldName>",
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (!isLobbySet(sender)) return true
        if (args.size != 1) {
            sender.sendMsg("Usage: §o/${parent.commandName} delArena <mapName>", true)
            return true
        }
        if (!plugin.restoreAdapter.isWorld(args[0])) {
            sender.sendMsg(args[0] + " doesn't exist as a world folder!", true)
            return true
        }
        if (plugin.arenaManager.getArena(args[0]) != null) {
            sender.sendMsg("Please disable it first!", true)
            return true
        }
        val ac = File(plugin.dataFolder, "/Arenas/" + args[0] + ".yml")
        if (!ac.exists()) {
            sender.sendMsg("This arena doesn't exist!", true)
            return true
        }
        if (sender in delArenaConfirm) {
            if (System.currentTimeMillis() - 2000 <= delArenaConfirm[sender]!!) {
                plugin.restoreAdapter.deleteWorld(args[0])
                ac.deleteRecursively()
                sender.sendMsg(args[0] + " was deleted!", true)
                return true
            }
        }
        sender.sendMsg("Type again to confirm.")
        delArenaConfirm[sender] = System.currentTimeMillis()
        return true
    }

    override val tabComplete get() = Misc.getArenas()

    companion object {
        private val delArenaConfirm = HashMap<Player?, Long?>()
    }
}
