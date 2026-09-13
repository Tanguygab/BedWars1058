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
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.arena.Misc.msgHoverClick
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.configuration.Permissions
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Reload(parent: ParentCommand) : SubCommand("reload", Permissions.PERMISSION_RELOAD, priority = 11) {
    init {
        displayInfo = msgHoverClick(
            "§6 ▪ §7/${parent.commandName} $subCommandName       §8 - §ereload messages",
            "§fReload messages.\n§cNot recommended!",
            "/${parent.commandName} $subCommandName",
            ClickEvent.Action.RUN_COMMAND
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (!MainCommand.isLobbySet(sender as? Player)) return true
        for (l in Language.languages) {
            l.reload()
            sender.sendMessage("§6 ▪ §7${l.langName} reloaded!")
        }
        return true
    }

    override fun canSee(sender: CommandSender, api: BedWars): Boolean {
        if (sender is Player) {
            if (api.arenaManager.isInArena(sender)) return false
            if (SetupSession.isInSetupSession(sender.uniqueId)) return false
        }
        return canUse(sender)
    }
}
