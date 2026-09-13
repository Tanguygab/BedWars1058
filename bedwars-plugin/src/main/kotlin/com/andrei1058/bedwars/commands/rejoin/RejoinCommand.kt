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
package com.andrei1058.bedwars.commands.rejoin

import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.arena.ReJoin
import com.andrei1058.bedwars.configuration.Permissions
import com.andrei1058.bedwars.configuration.Sounds.playSound
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand
import org.bukkit.entity.Player

class RejoinCommand(name: String) : BukkitCommand(name) {
    override fun execute(sender: CommandSender, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("This command is for players!")
            return true
        }

        if (!sender.hasPermission(Permissions.PERMISSION_REJOIN)) {
            sender.sendLangMsg(Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS)
            return true
        }

        val rj = ReJoin.getPlayer(sender)

        if (rj == null) {
            sender.sendLangMsg(Messages.REJOIN_NO_ARENA)
            playSound("rejoin-denied", sender)
            return true
        }

        if (!rj.canReJoin()) {
            sender.sendLangMsg(Messages.REJOIN_DENIED)
            playSound("rejoin-denied", sender)
            return true
        }

        sender.sendLangMsg(Messages.REJOIN_ALLOWED, "{arena}" to rj.arena.displayName)
        playSound("rejoin-allowed", sender)
        rj.reJoin(sender)
        return true
    }
}
