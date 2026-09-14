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

import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.bedwars.subcmds.SubCommand
import com.andrei1058.bedwars.configuration.Sounds
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdJoin(parent: MainCommand) : SubCommand(parent, "join", priority = 19) {
    override val description = createDescription(
        "Join an arena by name or by group.\n/bw join random - join random arena.",
        syntax = "§e<random/ arena/ groupName>"
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player || SetupSession.isInSetupSession(sender.uniqueId)) return false
        if (args.isEmpty()) {
            sender.sendLangMsg(Messages.COMMAND_JOIN_USAGE)
            return true
        }

        val arg = args[0]
        val arenaManager = plugin.arenaManager
        val condition = when {
            arg.equals("random", ignoreCase = true) -> arenaManager.joinRandomArena(sender)
            MainCommand.isArenaGroup(arg) || '+' in arg -> arenaManager.joinRandomFromGroup(sender, arg)
            else -> {
                val arena = arenaManager.getArena(arg) ?: arenaManager.getArenaByWorld(arg)
                if (arena != null) {
                    arena.addPlayer(sender, false)
                    return true
                }

                sender.sendLangMsg(Messages.COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND, "name" to arg)
                return true
            }
        }

        Sounds.playSound(if (condition) {
            "join-allowed"
        } else {
            sender.sendLangMsg(Messages.COMMAND_JOIN_NO_EMPTY_FOUND)
            "join-denied"
        }, sender)
        return true
    }

    override val tabComplete get() = plugin.configs.main
        .getStringList(ConfigPath.GENERAL_CONFIGURATION_ARENA_GROUPS)
        .plus(plugin.arenaManager.arenas.keys)
}
