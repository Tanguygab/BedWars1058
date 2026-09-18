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
package com.andrei1058.bedwars.commands.subcmds.sensitive.setup

import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.util.Utils.message
import com.andrei1058.bedwars.api.util.Utils.teleportSafe
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.commands.Misc
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class SetSpawn(parent: MainCommand) : SetupCommand(parent, "setSpawn") {
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        if (args.isEmpty()) {
            sender.sendMessage("${session.prefix}${ChatColor.RED}Usage: /${parent.commandName} setSpawn <team>")
            val teams = session.config.getConfigurationSection("Team") ?: return

            for (team in teams.getKeys(false)) {
                if ("Team.$team.Spawn" in session.config) continue
                sender.message(
                    "${session.prefix}Set spawn for: ${session.getColoredTeamName(team)} ${ChatColor.getLastColors(session.prefix)}(click to set)",
                    "${ChatColor.WHITE}Set spawn for ${session.getColoredTeamName(team)}",
                    "/${parent.commandName} setSpawn $team"
                )
            }
            return
        }

        val teamName = args[0]
        if ("Team.$teamName" !in session.config) {
            sender.sendMessage("${session.prefix}${ChatColor.RED}Could not find target team: ${ChatColor.RED}${teamName}")
            val teams = session.config.getConfigurationSection("Team") ?: return

            sender.sendMessage("${session.prefix}Teams list: ")
            for (team in teams.getKeys(false)) sender.message(
                "${ChatColor.GOLD} ▪ ${session.getColoredTeamName(team)} ${ChatColor.getLastColors(session.prefix)}(click to set)",
                "${ChatColor.WHITE}Set spawn for ${session.getColoredTeamName(team)}",
                "/${parent.commandName} setSpawn $team"
            )
            return
        }

        val spawn = "Team.$teamName.Spawn"
        if (spawn in session.config) {
            Misc.removeArmorStand(
                "spawn",
                session.config.getArenaLoc(spawn)!!,
                session.config.getString(spawn)
            )
        }

        session.config.saveArenaLoc(spawn, sender.location)
        val team = session.getColoredTeamName(teamName)
        sender.sendMessage("${ChatColor.GOLD} ▪ Spawn set for: $team")
        Misc.createArmorStand(
            "$team ${ChatColor.GOLD}SPAWN SET",
            sender.location,
            session.config.stringLocationArenaFormat(sender.location)
        )

        val radius = session.config.getInt(ConfigPath.ARENA_ISLAND_RADIUS)
        val l = sender.location
        for (x in -radius..<radius) {
            val x = x.toDouble()
            for (y in -radius..<radius) {
                val y = y.toDouble()
                for (z in -radius..<radius) {
                    val b = l.clone().add(x, y, z.toDouble()).block
                    if (!plugin.versionSupport.isBed(b.type)) continue

                    sender.teleportSafe(b.location)
                    Bukkit.dispatchCommand(sender, "${parent.commandName} setBed $teamName")
                    return
                }
            }
        }

        val teams = session.config.getConfigurationSection("Team") ?: return

        val remaining = teams.getKeys(false)
            .filter { "Team.$it.Spawn" !in session.config }
            .ifEmpty { return }
            .joinToString(" ") { session.getColoredTeamName(it) }

        sender.sendMessage("${session.prefix}Remaining: $remaining")
    }
}
