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
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.Misc.removeArmorStand
import com.andrei1058.bedwars.configuration.Sounds.playSound
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.Player

class RemoveGenerator(parent: MainCommand) : SetupCommand(parent, "removeGenerator") {
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        if (args.isNotEmpty()) return

        val toRemove = arrayOf("", "", "")
        var nearest: Location? = null
        val teams = session.config.getConfigurationSection("Team")
        if (teams != null) {
            for (team in teams.getKeys(false)) {
                for (type in arrayOf("Iron", "Gold", "Emerald")) {
                    if (session.config.get("Team.$team.$type") == null) continue
                    for (loc in session.config.getStringList("Team.$team.$type")) {
                        val loc2 = session.config.convertStringToArenaLocation(loc)
                        if (sender.location.distance(loc2) > 2) continue
                        if (nearest == null || sender.location.distance(nearest) > sender.location.distance(loc2)) {
                            nearest = loc2
                            toRemove[0] = type
                            toRemove[1] = loc
                            toRemove[2] = team
                        }
                    }
                }
            }
        }

        if (session.config.get("generator") != null) {
            for (type in arrayOf("Emerald", "Diamond")) {
                if (session.config.get("generator.$type") == null) continue
                for (loc in session.config.getStringList("generator.$type")) {
                    val loc2 = session.config.convertStringToArenaLocation(loc)
                    if (sender.location.distance(loc2) > 2) continue
                    if (nearest == null || sender.location.distance(nearest) > sender.location.distance(loc2)) {
                        nearest = loc2
                        toRemove[0] = type
                        toRemove[1] = loc
                        toRemove[2] = ""
                    }
                }
            }
        }

        val nms = plugin.versionSupport
        if (nearest == null) {
            sender.sendMessage("${session.prefix}Could not find any nearby generator (Range 2x2).")
            sender.sendMessage("${session.prefix}You mast stand close to the generator hologram's you want to remove.")
            nms.sendTitle(sender, " ", "${ChatColor.RED}Could not find any nearby generator.", 5, 40, 5)
            playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
            return
        }

        if (toRemove[2].isEmpty()) {
            val list = session.config.getStringList("generator.${toRemove[0]}")
            session.config.set("generator.${toRemove[0]}", list - toRemove[1])
            sender.sendMessage("${session.prefix}Removed ${toRemove[0]} generator at location: X:${nearest.blockX} Y:${nearest.blockY} Z:${nearest.z}")
            nms.sendTitle(sender, " ", "${ChatColor.GREEN}${toRemove[0]} generator removed.", 5, 40, 5)
            playSound(ConfigPath.SOUNDS_BOUGHT, sender)
            removeArmorStand(toRemove[0], nearest, toRemove[1])
            return
        }

        if (session.setupType == SetupType.ASSISTED) {
            session.config.set("Team.${toRemove[2]}.Emerald", emptyList<Any>())
            session.config.set("Team.${toRemove[2]}.Iron", emptyList<Any>())
            session.config.set("Team.${toRemove[2]}.Gold", emptyList<Any>())
            nms.sendTitle(sender, " ", "${session.getColoredTeamName(toRemove[2])} generator was removed.", 5, 40, 5)
            playSound(ConfigPath.SOUNDS_BOUGHT, sender)
            removeArmorStand(null, nearest, toRemove[1])
            sender.sendMessage("${session.prefix}${session.getColoredTeamName(toRemove[2])}${ChatColor.getLastColors(session.prefix)} generators were removed!")
            return
        }

        val list = session.config.getStringList("Team.${toRemove[2]}.${toRemove[0]}")
        session.config.set("Team.${toRemove[2]}.${toRemove[0]}", list - toRemove[1])
        sender.sendMessage("${session.prefix}Removed ${session.getColoredTeamName(toRemove[2])} ${ChatColor.getLastColors(session.prefix)}${toRemove[0]} generator at location: X:${nearest.blockX} Y:${nearest.blockY} Z:${nearest.z}")
        nms.sendTitle(sender, " ", "${session.getColoredTeamName(toRemove[2])} ${ChatColor.GREEN}${toRemove[0]} generator removed.", 5, 40, 5)
        playSound(ConfigPath.SOUNDS_BOUGHT, sender)
        removeArmorStand(toRemove[0], nearest, toRemove[1])
    }
}
