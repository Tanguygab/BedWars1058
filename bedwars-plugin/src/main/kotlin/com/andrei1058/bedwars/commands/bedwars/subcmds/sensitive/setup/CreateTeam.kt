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
import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.api.server.SetupType
import com.andrei1058.bedwars.arena.SetupSession
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class CreateTeam : SetupCommand("createTeam") {

    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        if (args.size < 2) {
            sender.sendMessage("§c▪ §7Usage: /${BedWars.MAIN_COMMAND} createTeam §o<name> §o<color>")
            sender.sendMessage("§6 ▪ §7Available colors: §7${getColors()}.")
            return
        }

        val color = args[1]
        if (TeamColor.entries.none { color.equals("$it", ignoreCase = true) }) {
            sender.sendMessage("§c▪ §7Invalid color!")
            sender.sendMessage("§6 ▪ §7Available colors: ${getColors()}.")
            return
        }

        val team = args[0]
        if (session.config.get("Team.$team.Color") != null) {
            sender.sendMessage("§c▪ §7$team team already exists!")
            return
        }

        session.config.set("Team.$team.Color", color.uppercase())
        sender.sendMessage("§6 ▪ §7${TeamColor.getChatColor(color)}$team §7created!")
        if (session.setupType != SetupType.ASSISTED) return

        session.config.reload()
        val teams = session.config.getConfigurationSection("Team")!!.getKeys(false).size
        session.config.set("maxInTeam", if (teams == 4) 2 else 1)
    }

    fun getColors() = TeamColor.entries.joinToString(", ") { "${it.chat}$it${ChatColor.GRAY}" }
}
