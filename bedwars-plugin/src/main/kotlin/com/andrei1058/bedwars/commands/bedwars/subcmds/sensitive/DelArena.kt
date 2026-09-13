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

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.api
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.api.command.SubCommand
import com.andrei1058.bedwars.arena.Misc
import com.andrei1058.bedwars.arena.Misc.msgHoverClick
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.configuration.Permissions
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.io.File

class DelArena(private val parent: ParentCommand) : SubCommand(
    "delArena",
    Permissions.PERMISSION_DEL_ARENA,
    priority = 4
) {
    init {
        displayInfo = msgHoverClick(
            "§6 ▪ §7/${parent.commandName} $subCommandName §6<worldName>",
            "§fDelete a map and its configuration.",
            "/${parent.commandName} $subCommandName",
            ClickEvent.Action.SUGGEST_COMMAND
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (!MainCommand.isLobbySet(sender)) return true
        if (args.size != 1) {
            sender.sendMsg("Usage: §o/${parent.commandName} delArena <mapName>", true)
            return true
        }
        if (!api.restoreAdapter.isWorld(args[0])) {
            sender.sendMsg(args[0] + " doesn't exist as a world folder!", true)
            return true
        }
        if (api.arenaManager.getArena(args[0]) != null) {
            sender.sendMsg("Please disable it first!", true)
            return true
        }
        val ac = File(BedWars.plugin.dataFolder, "/Arenas/" + args[0] + ".yml")
        if (!ac.exists()) {
            sender.sendMsg("This arena doesn't exist!", true)
            return true
        }
        if (sender in delArenaConfirm) {
            if (System.currentTimeMillis() - 2000 <= delArenaConfirm[sender]!!) {
                api.restoreAdapter.deleteWorld(args[0])
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

    override fun canSee(sender: CommandSender, api: com.andrei1058.bedwars.api.BedWars): Boolean {
        if (sender !is Player) return false

        if (api.arenaManager.isInArena(sender)) return false

        if (SetupSession.isInSetupSession(sender.uniqueId)) return false
        return canUse(sender)
    }

    companion object {
        private val delArenaConfirm = HashMap<Player?, Long?>()
    }
}
