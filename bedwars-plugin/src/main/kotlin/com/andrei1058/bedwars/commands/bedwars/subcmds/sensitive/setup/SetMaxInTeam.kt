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
package com.andrei1058.bedwars.commands.bedwars.subcmds.sensitive.setup

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.arena.SetupSession
import org.bukkit.entity.Player

class SetMaxInTeam : SetupCommand("setMaxInTeam") {
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        if (args.isNotEmpty()) {
            val max = args[0].toIntOrNull()
            if (max != null) {
                session.config.set("maxInTeam", max)
                sender.sendMessage("§6 ▪ §7Max in team set!")
                return
            }
        }
        sender.sendMessage("§c▪ §7Usage: /${BedWars.MAIN_COMMAND} setMaxInTeam <int>")
    }

    override val tabComplete = listOf("1", "2", "4")
}
