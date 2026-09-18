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

import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.commands.subcmds.SubCommand
import com.andrei1058.bedwars.configuration.Permissions
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetLobby(parent: MainCommand) : SubCommand(
    parent,
    "setLobby",
    Permissions.PERMISSION_SETUP_ARENA,
    priority = 1
) {
    override val description get() = createDescription(
        "§aSet the main lobby. This is required but\nif you are going to use the server in §eBUNGEE mode\nthe lobby location will §enot be used.\n§eType again to replace the old spawn location.",
        status = if (plugin.mainConfig.lobbyWorldName.isEmpty()) "§c(not set)" else "§a(set)",
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (SetupSession.isInSetupSession(sender.uniqueId)) {
            sender.sendMessage("§6 ▪ §4This command can't be used in arenas. It is meant for the main lobby!")
            return true
        }
        plugin.mainConfig.saveConfigLoc("lobbyLoc", sender.location)
        sender.sendMessage("§6 ▪ §7Lobby location set!")
        plugin.mainConfig.reload()
        plugin.lobbyWorld = sender.location.getWorld()!!.name
        return true
    }
}
