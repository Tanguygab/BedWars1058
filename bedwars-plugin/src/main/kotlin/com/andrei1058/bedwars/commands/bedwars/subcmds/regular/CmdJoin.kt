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

import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.api.command.SubCommand
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.configuration.Sounds
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdJoin(parent: ParentCommand) : SubCommand("join", isShown = false, priority = 19) {
    init {
        displayInfo = createTC(
            "§6 ▪ §7/${parent.commandName} join §e<random/ arena/ groupName>",
            "/${parent.commandName} $subCommandName",
            "§fJoin an arena by name or by group.\n§f/bw join random - join random arena."
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (args.isEmpty()) {
            sender.sendLangMsg(Messages.COMMAND_JOIN_USAGE)
            return true
        }

        val arg = args[0]
        val arenaManager = BedWars.INSTANCE.arenaManager
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

    override val tabComplete get() = BedWars.INSTANCE.configs.mainConfig
        .getStringList(ConfigPath.GENERAL_CONFIGURATION_ARENA_GROUPS)
        .plus(BedWars.INSTANCE.arenaManager.arenas.keys)


    override fun canSee(sender: CommandSender, api: BedWars) = sender is Player &&
            !api.arenaManager.isInArena(sender) &&
            !SetupSession.isInSetupSession(sender.uniqueId) &&
            canUse(sender)
}
