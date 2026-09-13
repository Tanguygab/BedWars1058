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

import com.andrei1058.bedwars.api.BedWars
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

class EnableArena(private val parent: ParentCommand) : SubCommand(
    "enableArena",
    Permissions.PERMISSION_ARENA_ENABLE,
    priority = 5
) {
    init {
        displayInfo = msgHoverClick(
            "§6 ▪ §7/${parent.commandName} $subCommandName §6<worldName>", "§fEnable an arena.",
            "/${parent.commandName} $subCommandName ", ClickEvent.Action.SUGGEST_COMMAND
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (!MainCommand.isLobbySet(sender)) return true
        if (args.size != 1) {
            sender.sendMessage("§c▪ §7Usage: §o/" + parent.commandName + " enableRotation <mapName>")
            return true
        }

        val arenaName = args[0]
        if (!BedWars.INSTANCE.restoreAdapter.isWorld(arenaName)) {
            sender.sendMsg("$arenaName doesn't exist!", true)
            return true
        }

        val arenaManager = BedWars.INSTANCE.arenaManager
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


    override fun canSee(sender: CommandSender, api: BedWars): Boolean {
        if (sender !is Player) return false

        if (api.arenaManager.isInArena(sender)) return false

        if (SetupSession.isInSetupSession(sender.uniqueId)) return false
        return canUse(sender)
    }
}
