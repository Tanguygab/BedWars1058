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

import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.bedwars.subcmds.SubCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdUpgrades(parent: MainCommand) : SubCommand(parent, "upgradesmenu") {
    override fun canSee(sender: CommandSender) = canUse(sender)

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false

        val arena = plugin.arenaManager.getArena(sender) ?: return false
        if (!arena.isPlayer(sender)) return false

        val t = arena.getTeam(sender)!!
        return if (t.teamUpgrades!!.distance(sender.location) < 4) {
            plugin.upgradesManager.getMenuForArena(arena).open(sender)
            true
        } else false
    }
}
