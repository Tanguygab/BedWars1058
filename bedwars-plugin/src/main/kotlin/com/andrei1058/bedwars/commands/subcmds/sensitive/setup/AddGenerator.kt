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
import com.andrei1058.bedwars.api.server.SetupType
import com.andrei1058.bedwars.api.util.Utils.message
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.Misc.createArmorStand
import com.andrei1058.bedwars.configuration.Sounds.playSound
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player

class AddGenerator(parent: MainCommand) : com.andrei1058.bedwars.commands.subcmds.sensitive.setup.SetupCommand(parent, "addGenerator") {
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        val nms = plugin.versionSupport
        if (args.isEmpty() && session.setupType == SetupType.ASSISTED) {
            val team = session.nearestTeam
            if (team.isEmpty()) {
                // save emerald or diamond generator if is standing on a block of this type
                val block = sender.location.add(0.0, -1.0, 0.0).block.type
                if (block == Material.DIAMOND_BLOCK || block == Material.EMERALD_BLOCK) {
                    Bukkit.dispatchCommand(sender, "${parent.commandName} $name ${block.toString().substringBefore("_").lowercase()}")
                    return
                }

                // else send usage message
                sender.sendMessage(session.prefix + ChatColor.RED + "Could not find any nearby team.")
                sender.message(
                    "${session.prefix}Make sure you set the team's spawn first!",
                    "${ChatColor.WHITE}Set a team spawn.",
                    "/${parent.commandName} $name ",
                    ClickEvent.Action.SUGGEST_COMMAND
                )
                sender.message(
                    "${session.prefix}Or if you set the spawn and it wasn't found automatically try using: /bw addGenerator <team>",
                    "Add a team generator.",
                    "/${parent.commandName} $name ",
                    ClickEvent.Action.SUGGEST_COMMAND
                )
                sender.message(
                    "${session.prefix}Other use: /bw addGenerator <emerald/ diamond>",
                    "Add an emerald/ diamond generator.",
                    "/${parent.commandName} $name ",
                    ClickEvent.Action.SUGGEST_COMMAND
                )
                nms.sendTitle(sender, " ", "${ChatColor.RED}Could not find any nearby team.", 5, 60, 5)
                playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
                return
            }
            // save team generators
            saveTeamGen(sender.location, team, session, "Iron")
            saveTeamGen(sender.location, team, session, "Gold")
            saveTeamGen(sender.location, team, session, "Emerald")

            createArmorStand(
                "${ChatColor.GOLD}Generator set for team: ${session.getColoredTeamName(team)}",
                sender.location,
                session.config.stringLocationArenaFormat(sender.location)
            )
            sender.sendMessage(session.prefix + "Generator set for team: " + session.getColoredTeamName(team))

            Bukkit.dispatchCommand(sender, parent.commandName)

            nms.sendTitle(
                sender,
                " ",
                "${ChatColor.GREEN}Generator set for team: ${session.getColoredTeamName(team)}",
                5,
                60,
                5
            )
            playSound(ConfigPath.SOUNDS_BOUGHT, sender)
            return
        }

        if (args.size == 1 && (
            args[0].equals("diamond", ignoreCase = true) ||
            args[0].equals("emerald", ignoreCase = true)
        )) {
            val gen = args[0].lowercase().replaceFirstChar { it.uppercaseChar() }
            // add emerald or diamond generator to the list if it was not added yet
            val locations = session.config.getArenaLocations("generator.$gen")
            for (location in locations) {
                if (!session.config.compareArenaLoc(location, sender.location)) continue
                sender.sendMessage(session.prefix + ChatColor.RED + "This generator was already set!")
                nms.sendTitle(
                    sender,
                    " ",
                    ChatColor.RED.toString() + "This generator was already set!",
                    5,
                    30,
                    5
                )
                playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
                return
            }

            val saved = session.config.getStringList("generator.$gen").toMutableList()
            saved.add(session.config.stringLocationArenaFormat(sender.location))

            session.config.set("generator.$gen", saved)
            sender.sendMessage("${session.prefix}$gen generator was added!")
            createArmorStand(
                "${ChatColor.GOLD}$gen SET",
                sender.location,
                session.config.stringLocationArenaFormat(sender.location)
            )
            if (session.setupType == SetupType.ASSISTED) {
                Bukkit.dispatchCommand(sender, parent.commandName)
            }
            plugin.versionSupport.sendTitle(
                sender,
                " ",
                "${ChatColor.GOLD}$gen${ChatColor.GREEN} generator added!",
                5,
                60,
                5
            )
            playSound(ConfigPath.SOUNDS_BOUGHT, sender)
            return
        }

        if (args.isNotEmpty() && (
            args[0].equals("iron", ignoreCase = true) ||
            args[0].equals("gold", ignoreCase = true) ||
            args[0].equals("upgrade", ignoreCase = true)
        ) && session.setupType == SetupType.ADVANCED) {
            val team: String
            if (args.size == 1) {
                team = session.nearestTeam
            } else {
                team = args[1]
                if (session.config.get("Team.$team.Color") == null) {
                    sender.sendMessage("${session.prefix}${ChatColor.RED}Could not find team: $team")
                    sender.sendMessage("${session.prefix}Use: /bw createTeam if you want to create one.")
                    session.displayAvailableTeams()
                    nms.sendTitle(
                        sender,
                        " ",
                        "${ChatColor.RED}Could not find any nearby team.",
                        5,
                        60,
                        5
                    )
                    playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
                    return
                }
            }
            // find nearest team to set the generator else send usage msg
            if (team.isEmpty()) {
                sender.sendMessage("${session.prefix}${ChatColor.RED}Could not find any nearby team.")
                sender.sendMessage("${session.prefix}Try using: /bw addGenerator <iron/ gold/ upgrade> <team>")
                return
            }

            var gen = args[0].lowercase()
            gen = if (gen == "upgrade") "Emerald"
            else gen.replaceFirstChar { it.uppercaseChar() }

            createArmorStand(
                "${ChatColor.GOLD}$gen generator added for team: ${session.getColoredTeamName(team)}",
                sender.location,
                session.config.stringLocationArenaFormat(sender.location)
            )
            sender.sendMessage("${session.prefix}$gen generator added for team: ${session.getColoredTeamName(team)}")
            saveTeamGen(sender.location, team, session, gen)
            nms.sendTitle(
                sender,
                " ",
                "${ChatColor.GOLD}$gen${ChatColor.GREEN} generator for ${session.getColoredTeamName(team)}${ChatColor.GREEN} was added!",
                5,
                60,
                5
            )
            playSound(ConfigPath.SOUNDS_BOUGHT, sender)
            return
        }
        if (args.size == 1 && session.setupType == SetupType.ASSISTED) {
            val team = args[0]
            if (session.config.get("Team.$team.Color") == null) {
                sender.sendMessage("${session.prefix}Could not find team: ${ChatColor.RED}$team")
                sender.sendMessage("${session.prefix}Use: /bw createTeam if you want to create one.")
                session.displayAvailableTeams()
                nms.sendTitle(sender, " ", "Could not find team: ${ChatColor.RED}$team", 5, 40, 5)
                playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
                return
            }

            saveTeamGen(sender.location, team, session, "Iron")
            saveTeamGen(sender.location, team, session, "Gold")
            saveTeamGen(sender.location, team, session, "Emerald")
            createArmorStand(
                "${ChatColor.GOLD}Generator set for team: ${session.getColoredTeamName(team)}",
                sender.location,
                session.config.stringLocationArenaFormat(sender.location)
            )
            sender.sendMessage("${session.prefix}Generator set for team: ${session.getColoredTeamName(team)}")
            Bukkit.dispatchCommand(sender, parent.commandName)

            nms.sendTitle(
                sender,
                " ",
                "${ChatColor.GREEN}Generator set for team: ${session.getColoredTeamName(team)}",
                5,
                60,
                5
            )
            playSound(ConfigPath.SOUNDS_BOUGHT, sender)
            return
        }

        if (session.setupType == SetupType.ASSISTED) {
            sender.message(
                session.prefix + "/bw addGenerator (detect team automatically)",
                "Add a team generator.",
                "/${parent.commandName} $name ",
                ClickEvent.Action.SUGGEST_COMMAND
            )
            sender.message(
                session.prefix + "/bw addGenerator <team>",
                "Add a team generator.",
                "/${parent.commandName} $name ",
                ClickEvent.Action.SUGGEST_COMMAND
            )
        }
        if (session.setupType == SetupType.ADVANCED) {
            sender.message(
                session.prefix + "/bw addGenerator <iron/ gold/ upgrade>",
                "Add a team generator.\nThe team will be detected automatically.",
                "/${parent.commandName} $name ",
                ClickEvent.Action.SUGGEST_COMMAND
            )

            sender.message(
                session.prefix + "/bw addGenerator <iron/ gold/ upgrade> <team>",
                "Add a team generator.",
                "/${parent.commandName} $name ",
                ClickEvent.Action.SUGGEST_COMMAND
            )
        }
        sender.message(
            session.prefix + "/bw addGenerator <emerald/ diamond>",
            "Add an emerald/ diamond generator.",
            "/${parent.commandName} $name ",
            ClickEvent.Action.SUGGEST_COMMAND
        )
    }

    override val tabComplete = listOf("Diamond", "Emerald", "Iron", "Gold", "Upgrade")

    companion object {
        /**
         * Save team generator.
         * 
         * @param location    location.
         * @param team    team.
         * @param session   setup session.
         * @param type Iron/ Gold.
         */
        private fun saveTeamGen(location: Location, team: String, session: SetupSession, type: String) {
            val path = "Team.$team.$type"

            val generator = session.config.get(path)
            val locs = mutableListOf(session.config.stringLocationArenaFormat(location))

            if (generator is String) locs += generator
            else locs += session.config.getStringList(path)

            session.config.set(path, locs)
        }
    }
}
