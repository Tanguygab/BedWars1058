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

import com.andrei1058.bedwars.api.arena.team.TeamColor.Companion.enName
import com.andrei1058.bedwars.api.arena.team.TeamColor.Companion.getChatColor
import com.andrei1058.bedwars.api.server.SetupType
import com.andrei1058.bedwars.api.util.Utils.message
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player

class AutoCreateTeams(parent: MainCommand) : com.andrei1058.bedwars.commands.subcmds.sensitive.setup.SetupCommand(parent, "autoCreateTeams") {

    @Suppress("DEPRECATION")
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        if (session.setupType != SetupType.ASSISTED) return

        if (plugin.versionSupport.version > 5) {
            if (timeOut.containsKey(sender) && timeOut[sender]!! >= System.currentTimeMillis() && teamsFound13.containsKey(sender)) {
                for (tf in teamsFound13[sender]!!) {
                    Bukkit.dispatchCommand(sender, parent.commandName + " createTeam " + enName(tf) + " " + enName(tf))
                }
                if (session.config.get("waiting.Pos1") == null) {
                    sender.sendMessage("")
                    sender.sendMessage("§6§lWAITING LOBBY REMOVAL:")
                    sender.sendMessage("§fIf you'd like the lobby to disappear when the game starts,")
                    sender.sendMessage("§fplease use the following commands like a world edit selection.")
                    sender.message(
                        "§c ▪ §7/" + parent.commandName + " waitingPos 1",
                        "§dSet pos 1",
                        "/${parent.commandName} waitingPos 1"
                    )
                    sender.message(
                        "§c ▪ §7/" + parent.commandName + " waitingPos 2",
                        "§dSet pos 2",
                        "/${parent.commandName} waitingPos 2"
                    )
                    sender.sendMessage("")
                    sender.sendMessage("§7This step is OPTIONAL. If you wan to skip it do §6/" + parent.commandName)
                }
                return
            }
            val found = mutableListOf<String>()
            val w = sender.world
            if (session.config.get("Team") == null) {
                sender.sendMessage("§6 ▪ §7Searching for teams. This may cause lag.")
                for (x in -200..199) {
                    val x = x.toDouble()
                    for (y in 50..129) {
                        val y = y.toDouble()
                        for (z in -200..199) {
                            val b = Location(w, x, y, z.toDouble()).block.type
                            val bStr = b.toString()
                            if ("_WOOL" !in bStr || bStr in found) continue
                            var count = 0
                            for (x1 in -2..1) {
                                for (y1 in -2..1) {
                                    for (z1 in -2..1) {
                                        val b2 = Location(w, x + x1, y + y1, (z + z1).toDouble()).block
                                        if (b2.type == b) ++count
                                    }
                                }
                            }
                            val color = enName(bStr)
                            if (count < 5 || color.isEmpty() || session.config.get("Team.$color") != null) continue
                            found += bStr
                        }
                    }
                }
            }
            if (found.isEmpty()) {
                sender.sendMessage("§6 ▪ §7No new teams were found.\n§6 ▪ §7Manually create teams with: §6/${parent.commandName} createTeam")
                return
            }
            if (timeOut.containsKey(sender)) {
                sender.sendMessage("§c ▪ §7Time out. Type again to search for teams.")
                timeOut.remove(sender)
                return
            }

            timeOut[sender] = System.currentTimeMillis() + 16000
            teamsFound13[sender] = found
            sender.sendMessage("§6§lNEW TEAMS FOUND:")
            for (tf in found) {
                val name = enName(tf)
                sender.sendMessage("§f ▪ ${getChatColor(name)}${name.replace("_", " ")}")
            }
            sender.message(
                "§6 ▪ §7§lClick here to create found teams.",
                "§fClick to create found teams!",
                "/" + parent.commandName + " " + name,
                ClickEvent.Action.RUN_COMMAND
            )
            return
        }

        if (timeOut.containsKey(sender) && timeOut[sender]!! >= System.currentTimeMillis() && teamsFoundOld.containsKey(sender)) {
            for (tf in teamsFoundOld[sender]!!) {
                Bukkit.dispatchCommand(sender, parent.commandName + " createTeam " + enName(tf) + " " + enName(tf))
            }
            if (session.config.get("waiting.Pos1") == null) {
                sender.sendMessage("")
                sender.sendMessage("§6§lWAITING LOBBY REMOVAL:")
                sender.sendMessage("§fIf you'd like the lobby to disappear when the game starts,")
                sender.sendMessage("§fplease use the following commands like a world edit selection.")
                sender.message(
                    "§c ▪ §7/${parent.commandName} waitingPos 1",
                    "§dSet pos 1",
                    "/${parent.commandName} waitingPos 1"
                )
                sender.message(
                    "§c ▪ §7/${parent.commandName} waitingPos 2",
                    "§dSet pos 2",
                    "/${parent.commandName} waitingPos 2"
                )
                sender.sendMessage("")
                sender.sendMessage("§7This step is OPTIONAL. If you wan to skip it do §6/" + parent.commandName)
            }
            return
        }
        val found = mutableListOf<Byte>()
        val w = sender.world
        if (session.config.get("Team") == null) {
            sender.sendMessage("§6 ▪ §7Searching for teams. This may cause lag.")
            for (x in -200..199) {
                val x = x.toDouble()
                for (y in 50..129) {
                    val y = y.toDouble()
                    for (z in -200..199) {
                        val b = Location(w, x, y, z.toDouble()).block
                        if (b.type != Material.valueOf("WOOL") || b.data in found) continue

                        var count = 0
                        for (x1 in -2..1) {
                            for (y1 in -2..1) {
                                for (z1 in -2..1) {
                                    val b2 = Location(w, x + x1, y + y1, (z + z1).toDouble()).block
                                    if (b2.type == b.type && b.data == b2.data) ++count
                                }
                            }
                        }
                        val color = enName(b.data)
                        if (count >= 5 && color.isNotEmpty() && session.config.get("Team.$color") == null) {
                            found += b.data
                        }
                    }
                }
            }
        }
        if (found.isEmpty()) {
            sender.sendMessage("§6 ▪ §7No new teams were found.\n§6 ▪ §7Manually create teams with: §6/" + parent.commandName + " createTeam")
            return
        }
        if (timeOut.containsKey(sender)) {
            sender.sendMessage("§c ▪ §7Time out. Type again to search for teams.")
            timeOut.remove(sender)
            return
        }

        timeOut[sender] = System.currentTimeMillis() + 16000
        teamsFoundOld[sender] = found
        sender.sendMessage("§6§lNEW TEAMS FOUND:")
        for (tf in found) {
            val name = enName(tf)
            sender.sendMessage("§f ▪ ${getChatColor(name)}${name.replace('_', ' ')}")
        }
        sender.message(
            "§6 ▪ §7§lClick here to create found teams.",
            "§fClick to create found teams!",
            "/${parent.commandName} $name",
            ClickEvent.Action.RUN_COMMAND
        )
    }

    companion object {
        private val timeOut = mutableMapOf<Player, Long>()
        private val teamsFoundOld = mutableMapOf<Player, MutableList<Byte>>()
        private val teamsFound13 = mutableMapOf<Player, MutableList<String>>()
    }
}
