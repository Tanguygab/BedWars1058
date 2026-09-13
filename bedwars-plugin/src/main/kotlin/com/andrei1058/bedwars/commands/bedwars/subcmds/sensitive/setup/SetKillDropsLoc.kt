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
import com.andrei1058.bedwars.Utils.message
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.server.SetupType
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.Misc.createArmorStand
import com.andrei1058.bedwars.commands.Misc.removeArmorStand
import com.andrei1058.bedwars.configuration.Sounds.playSound
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class SetKillDropsLoc(private val parent: ParentCommand) : SetupCommand("setKillDrops") {
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        val arena = session.config
        if (args.isEmpty()) {
            var foundTeam = ""
            var distance = 100.0
            val teams = session.config.getConfigurationSection("Team")
            if (teams == null) {
                sender.sendMessage(session.prefix + "Please create teams first!")
                BedWars.nms.sendTitle(sender, " ", ChatColor.RED.toString() + "Please create teams first!", 5, 40, 5)
                playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
                return
            }

            for (team in teams.getKeys(false)) {
                if (session.config.get("Team.$team.Spawn") == null) continue
                val dis = session.config.getArenaLoc("Team.$team.Spawn")!!.distance(sender.location)
                if (dis > session.config.getInt(ConfigPath.ARENA_ISLAND_RADIUS) || dis >= distance) continue
                distance = dis
                foundTeam = team
            }
            if (foundTeam.isNotEmpty()) {
                val location = session.config.getArenaLoc("Team.$foundTeam.${ConfigPath.ARENA_TEAM_KILL_DROPS_LOC}")
                if (location != null) removeArmorStand("Kill drops", location, null)

                arena.set(
                    "Team.$foundTeam.${ConfigPath.ARENA_TEAM_KILL_DROPS_LOC}",
                    arena.stringLocationArenaFormat(sender.location)
                )
                val team = session.getColoredTeamName(foundTeam)
                sender.sendMessage("${session.prefix}Kill drops set for team: $team")
                createArmorStand("${ChatColor.GOLD}Kill drops $team", sender.location, null)
                BedWars.nms.sendTitle(sender, " ", "${ChatColor.GREEN}Kill drops set for team: $team", 5, 40, 5)
                playSound(ConfigPath.SOUNDS_BOUGHT, sender)

                if (session.setupType == SetupType.ASSISTED) {
                    Bukkit.dispatchCommand(sender, parent.commandName)
                }
                return
            }

            sender.sendMessage("${session.prefix}${ChatColor.RED}Usage: /${BedWars.MAIN_COMMAND} setKillDrops <teamName>")
            return
        }

        var foundTeam = session.nearestTeam

        if (foundTeam.isEmpty()) {
            sender.sendMessage("")
            sender.sendMessage(session.prefix + ChatColor.RED + "Could not find any nearby team.")
            sender.message(
                session.prefix + "Make sure you set the team's spawn first!",
                ChatColor.WHITE.toString() + "Set a team spawn.",
                "/" + parent.commandName + " " + subCommandName + " ",
                ClickEvent.Action.SUGGEST_COMMAND
            )
            sender.message(
                session.prefix + "Or if you set the spawn and it wasn't found automatically try using: /bw " + subCommandName + " <team>",
                "Set kill drops location for a team.",
                "/" + parent.commandName + " " + subCommandName + " ",
                ClickEvent.Action.SUGGEST_COMMAND
            )
            BedWars.nms.sendTitle(sender, " ", ChatColor.RED.toString() + "Could not find any nearby team.", 5, 60, 5)
            playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
            return
        }

        if (args.size == 1) {
            if (arena.get("Team." + args[0]) == null) {
                sender.sendMessage(session.prefix + ChatColor.RED + "This team doesn't exist!")
                val teams = arena.getConfigurationSection("Team") ?: return

                sender.sendMessage(session.prefix + "Available teams: ")
                for (team in teams.getKeys(false)) {
                    sender.message(
                        "${ChatColor.GOLD} ▪ Kill drops ${session.getColoredTeamName(team)} ${ChatColor.getLastColors(session.prefix)}(click to set)",
                        "${ChatColor.WHITE}Set Kill drops for ${session.getColoredTeamName(team)}",
                        "/${BedWars.MAIN_COMMAND} setKillDrops $team",
                        ClickEvent.Action.RUN_COMMAND
                    )
                }
                return
            }
            foundTeam = args[0]
        }

        arena.set(
            "Team.$foundTeam.${ConfigPath.ARENA_TEAM_KILL_DROPS_LOC}",
            arena.stringLocationArenaFormat(sender.location)
        )
        sender.sendMessage(session.prefix + "Kill drops set for: " + session.getColoredTeamName(foundTeam))
        if (session.setupType == SetupType.ASSISTED) Bukkit.dispatchCommand(sender, parent.commandName)
        return
    }
}
