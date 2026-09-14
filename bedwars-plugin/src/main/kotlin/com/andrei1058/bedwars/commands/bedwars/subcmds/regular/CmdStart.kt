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

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.bedwars.subcmds.SubCommand
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.configuration.Permissions
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdStart(parent: MainCommand) : SubCommand(parent, "start", priority = 15) {
    override val description = createDescription(
        "Forcestart an arena.\nPermission: §c" + Permissions.PERMISSION_FORCESTART,
        suffix = "force start an arena"
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        val arena = plugin.arenaManager.getArena(sender)

        if (arena == null || !arena.isPlayer(sender)) {
            sender.sendLangMsg(Messages.COMMAND_FORCESTART_NOT_IN_GAME)
            return true
        }
        // specific permission check instead because of the specific message
        if (!sender.hasPermission(Permissions.PERMISSION_ALL) && !sender.hasPermission(Permissions.PERMISSION_FORCESTART)) {
            sender.sendLangMsg(Messages.COMMAND_FORCESTART_NO_PERM)
            return true
        }
        if (!arena.status.isPreGame()) return true
        if (arena.startingTask == null) {
            if (args.size != 1 || !args[0].equals("debug", ignoreCase = true) || !sender.isOp) return true
            arena.changeStatus(GameState.STARTING)
            BedWars.debug = true
        }
        if (arena.startingTask!!.countdown < 5) return true
        arena.startingTask!!.countdown = 5
        sender.sendLangMsg(Messages.COMMAND_FORCESTART_SUCCESS)
        return true
    }

    override fun canSee(sender: CommandSender): Boolean {
        if (sender !is Player) return false

        val arena = plugin.arenaManager.getArena(sender) ?: return false
        if (!arena.status.isPreGame() || !arena.isPlayer(sender)) return false

        if (SetupSession.isInSetupSession(sender.uniqueId)) return false

        return sender.hasPermission(Permissions.PERMISSION_FORCESTART)
    }
}
