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

import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.bedwars.subcmds.SubCommand
import com.andrei1058.bedwars.configuration.Permissions
import org.bukkit.command.CommandSender

class Reload(parent: MainCommand) : SubCommand(parent, "reload", Permissions.PERMISSION_RELOAD, priority = 11) {
    override val description = createDescription(
        "Reload messages.\n§cNot recommended!",
        suffix = "reload messages",
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (!isLobbySet(sender)) return true
        for (l in Language.languages) {
            l.reload()
            sender.sendMessage("§6 ▪ §7${l.langName} reloaded!")
        }
        return true
    }
}
