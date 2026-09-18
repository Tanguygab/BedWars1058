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
import com.andrei1058.bedwars.commands.Misc.createArmorStand
import com.andrei1058.bedwars.commands.Misc.removeArmorStand
import com.andrei1058.bedwars.configuration.Sounds.playSound
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class SetBed(parent: MainCommand) : SetupCommand(parent, "setBed") {

    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        val nms = plugin.versionSupport
        if (args.isEmpty()) {
            val foundTeam = session.nearestTeam
            if (foundTeam.isNotEmpty()) {
                Bukkit.dispatchCommand(sender, "${parent.commandName} $name $foundTeam")
                return
            }

            sender.sendMessage("\n${session.prefix}${ChatColor.RED}Could not find any nearby team.")
            sender.message(
                "${session.prefix}Make sure you set the team's spawn first!",
                "${ChatColor.WHITE}Set a team bed.",
                "/${parent.commandName} $name ",
                ClickEvent.Action.SUGGEST_COMMAND
            )
            sender.message(
                "${session.prefix}Or if you set the spawn and it wasn't found automatically try using: /bw $name <team>",
                "Add a team bed.",
                "/${parent.commandName} $name ",
                ClickEvent.Action.SUGGEST_COMMAND
            )
            nms.sendTitle(sender, " ", "${ChatColor.RED}Could not find any nearby team.", 5, 60, 5)
            playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
            session.displayAvailableTeams()
            return
        }
        val noBed = arrayOf(
            sender.location.clone().add(0.0, -0.5, 0.0),
            sender.location.clone().add(0.0, 0.5, 0.0),
            sender.location
        ).none { nms.isBed(it.block.type) }

        if (noBed) {
            sender.sendMessage("${session.prefix}${ChatColor.RED}You must stay on a bed while using this command!")
            nms.sendTitle(sender, " ", "${ChatColor.RED}You must stay on a bed.", 5, 40, 5)
            playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
            return
        }

        val teamName = args[0]
        if ("Team.$teamName" !in session.config) {
            sender.sendMessage("${session.prefix}${ChatColor.RED}This team doesn't exist!")
            val teams = session.config.getConfigurationSection("Team") ?: return

            sender.sendMessage("${session.prefix}Available teams: ")
            for (team in teams.getKeys(false)) sender.message(
                "${ChatColor.GOLD} ▪ ${session.getColoredTeamName(team)}",
                "${ChatColor.WHITE}Set bed for ${session.getColoredTeamName(team)}",
                "/${parent.commandName} setBed $team",
            )
            return
        }

        val team = session.getColoredTeamName(teamName)

        val bed = session.config.getArenaLoc("Team.$teamName.Bed")
        if (bed != null) removeArmorStand("bed", bed, null)

        createArmorStand("$team ${ChatColor.GOLD}BED SET", sender.location.add(0.5, 0.0, 0.5), null)
        session.config.saveArenaLoc("Team.$teamName.Bed", sender.location)
        sender.sendMessage("${session.prefix}Bed set for: $team")

        nms.sendTitle(sender, " ", "${ChatColor.GREEN}Bed set for: $team", 5, 40, 5)
        playSound(ConfigPath.SOUNDS_BOUGHT, sender)

        if (session.setupType == SetupType.ASSISTED) Bukkit.dispatchCommand(sender, parent.commandName)
    }
}
