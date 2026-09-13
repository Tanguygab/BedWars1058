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

import com.andrei1058.bedwars.BedWars.Companion.api
import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.api.command.SubCommand
import com.andrei1058.bedwars.arena.Misc.msgHoverClick
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.configuration.Permissions
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class DisableArena(private val parent: ParentCommand) : SubCommand(
    "disableArena",
    Permissions.PERMISSION_ARENA_DISABLE,
    priority = 6
) {
    init {
        displayInfo = msgHoverClick(
            "§6 ▪ §7/${parent.commandName} $subCommandName §6<worldName>",
            "§fDisable an arena.\nThis will remove the players \n§ffrom the arena before disabling.",
            "/${parent.commandName} $subCommandName ",
            ClickEvent.Action.SUGGEST_COMMAND
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (!MainCommand.isLobbySet(sender)) return true
        if (args.size != 1) {
            sender.sendMessage("§c▪ §7Usage: §o/${parent.commandName} $subCommandName <mapName>")
            return true
        }
        val arenaName = args[0]
        if (!api.restoreAdapter.isWorld(arenaName)) {
            sender.sendMessage("§c▪ §7$arenaName doesn't exist!")
            return true
        }
        val arena = api.arenaManager.getArena(arenaName)
        if (arena == null) {
            sender.sendMessage("§c▪ §7This arena is disabled yet!")
            return true
        }
        if (arena.status == GameState.PLAYING) {
            sender.sendMessage("§6 ▪ §7There is a game running on this Arena, please disable after the game!")
            return true
        }
        sender.sendMessage("§6 ▪ §7Disabling arena...")
        arena.disable()
        return true
    }

    override val tabComplete get() = api.arenaManager.arenas.keys.toList()

    override fun canSee(sender: CommandSender, api: BedWars): Boolean {
        if (sender !is Player) return false

        if (api.arenaManager.isInArena(sender)) return false

        if (SetupSession.isInSetupSession(sender.uniqueId)) return false
        return canUse(sender)
    }
}
