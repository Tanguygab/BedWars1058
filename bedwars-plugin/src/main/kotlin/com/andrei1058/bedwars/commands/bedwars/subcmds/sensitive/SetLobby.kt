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
import com.andrei1058.bedwars.BedWars.Companion.lobbyWorld
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.api.command.SubCommand
import com.andrei1058.bedwars.arena.Misc.msgHoverClick
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.configuration.Permissions
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetLobby(parent: ParentCommand) : SubCommand(
    "setLobby",
    Permissions.PERMISSION_SETUP_ARENA,
    priority = 1
) {
    init {
        displayInfo = msgHoverClick(
            "§6 ▪ §7/${parent.commandName} $subCommandName${if (BedWars.config.lobbyWorldName.isEmpty()) " §c(not set)" else " §a(set)"}",
            "§aSet the main lobby. §fThis is required but\n§fif you are going to use the server in §eBUNGEE §fmode\n§fthe lobby location will §enot §fbe used.\n§eType again to replace the old spawn location.",
            "/${parent.commandName} $subCommandName", ClickEvent.Action.RUN_COMMAND
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (SetupSession.isInSetupSession(sender.uniqueId)) {
            sender.sendMessage("§6 ▪ §4This command can't be used in arenas. It is meant for the main lobby!")
            return true
        }
        BedWars.config.saveConfigLoc("lobbyLoc", sender.location)
        sender.sendMessage("§6 ▪ §7Lobby location set!")
        BedWars.config.reload()
        lobbyWorld = sender.location.getWorld()!!.name
        return true
    }

    override fun canSee(sender: CommandSender, api: com.andrei1058.bedwars.api.BedWars): Boolean {
        if (sender !is Player) return false

        if (api.arenaManager.isInArena(sender)) return false

        if (SetupSession.isInSetupSession(sender.uniqueId)) return false

        if (lobbyWorld.isNotEmpty()) return false

        return canUse(sender)
    }
}
