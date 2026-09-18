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

import com.andrei1058.bedwars.api.arena.team.TeamColor.Companion.getChatColor
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.server.SetupType
import com.andrei1058.bedwars.api.util.Utils.message
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.Misc.createArmorStand
import com.andrei1058.bedwars.Misc.removeArmorStand
import com.andrei1058.bedwars.configuration.Sounds.playSound
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class SetUpgrade(parent: MainCommand) : SetupCommand(parent, "setUpgrade") {

    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        if (args.isEmpty()) {
            val foundTeam = session.nearestTeam
            if (foundTeam.isNotEmpty()) {
                Bukkit.dispatchCommand(sender, "${parent.commandName} $name $foundTeam")
                return
            }

            sender.sendMessage("")
            sender.sendMessage(session.prefix + ChatColor.RED + "Could not find any nearby team.")
            sender.message(
                "${session.prefix}Make sure you set the team's spawn first!",
                "${ChatColor.WHITE}Set a team spawn.",
                "/${parent.commandName} $name ",
                ClickEvent.Action.SUGGEST_COMMAND
            )
            sender.message(
                "${session.prefix}Or if you set the spawn and it wasn't found automatically try using: /bw $name <team>",
                "Set team upgrades NPC for a team.",
                "/${parent.commandName} $name ",
                ClickEvent.Action.SUGGEST_COMMAND
            )
            plugin.versionSupport.sendTitle(sender, " ", "${ChatColor.RED}Could not find any nearby team.", 0, 60, 10)
            playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
            return
        }

        if (session.config.get("Team.${args[0]}") == null) {
            sender.sendMessage("${session.prefix}${ChatColor.RED}This team doesn't exist!")
            val teams = session.config.getConfigurationSection("Team") ?: return

            sender.sendMessage("${session.prefix}Available teams: ")
            for (team in teams.getKeys(false)) sender.message(
                "${ChatColor.GOLD} ▪ ${session.getColoredTeamName(team)} ${ChatColor.getLastColors(session.prefix)}(click to set)",
                "${ChatColor.WHITE}Upgrade npc set for ${getChatColor(session.config.getString("Team.$team.Color")!!)}$team",
                "/${parent.commandName} setUpgrade $team"
            )
            return
        }
        val team = session.getColoredTeamName(args[0])
        val upgrades = session.config.getArenaLoc("Team.${args[0]}.Upgrade")
        if (upgrades != null) removeArmorStand("upgrade", upgrades, null)

        createArmorStand("$team ${ChatColor.GOLD}UPGRADE SET", sender.location, null)
        session.config.saveArenaLoc("Team.${args[0]}.Upgrade", sender.location)
        sender.sendMessage("${session.prefix}Upgrade npc set for: $team")

        if (session.setupType == SetupType.ASSISTED) Bukkit.dispatchCommand(sender, parent.commandName)
    }
}
