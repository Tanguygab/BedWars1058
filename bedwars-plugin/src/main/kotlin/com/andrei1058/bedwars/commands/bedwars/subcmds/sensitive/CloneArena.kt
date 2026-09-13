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
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.io.File
import java.io.IOException

class CloneArena(private val parent: ParentCommand) : SubCommand(
    "cloneArena",
    Permissions.PERMISSION_CLONE,
    priority = 7
) {
    init {
        displayInfo = msgHoverClick(
            "§6 ▪ §7/${parent.commandName} $subCommandName §6<worldName> <newName>",
            "§fClone an existing arena.",
            "/${parent.commandName} $subCommandName",
            ClickEvent.Action.SUGGEST_COMMAND
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (!MainCommand.isLobbySet(sender)) return true
        if (args.size != 2) {
            sender.sendMsg("Usage: §o/${parent.commandName} $subCommandName <mapName> <newArena>", true)
            return true
        }
        val map = args[0]
        val clone = args[1]
        if (!api.restoreAdapter.isWorld(args[0])) {
            sender.sendMsg(args[0] + " doesn't exist!", true)
            return true
        }
        val mapYml = File(BedWars.plugin.dataFolder, "/Arenas/$map.yml")
        val cloneYml = File(BedWars.plugin.dataFolder, "/Arenas/$clone.yml")
        if (!mapYml.exists()) {
            sender.sendMsg("$map doesn't exist!", true)
            return true
        }
        if (api.restoreAdapter.isWorld(args[1]) && cloneYml.exists()) {
            sender.sendMsg("$clone already exist!", true)
            return true
        }
        if (args[1].contains("+")) {
            sender.sendMsg("$clone mustn't contain this symbol: " + ChatColor.RED + "+", true)
            return true
        }
        if (api.arenaManager.getArena(map) != null) {
            sender.sendMsg("Please disable $map first!", true)
            return true
        }
        api.restoreAdapter.cloneArena(map, clone)
        if (mapYml.exists()) {
            try {
                mapYml.copyTo(cloneYml, true)
            } catch (e: IOException) {
                e.printStackTrace()
                sender.sendMsg("An error occurred while copying the map's config. Check the console.", true)
            }
        }
        sender.sendMsg("Done :D.")
        return true
    }

    override val tabComplete get() = Misc.getArenas()

    override fun canSee(sender: CommandSender, api: com.andrei1058.bedwars.api.BedWars): Boolean {
        if (sender !is Player) return false

        if (api.arenaManager.isInArena(sender)) return false

        if (SetupSession.isInSetupSession(sender.uniqueId)) return false
        return canUse(sender)
    }
}
