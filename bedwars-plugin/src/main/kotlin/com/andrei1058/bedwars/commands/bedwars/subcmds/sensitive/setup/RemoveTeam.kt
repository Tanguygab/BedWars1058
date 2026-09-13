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
import com.andrei1058.bedwars.api.arena.team.TeamColor.Companion.getChatColor
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.Misc
import com.andrei1058.bedwars.configuration.Sounds.playSound
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.Player

class RemoveTeam : SetupCommand("removeTeam") {

    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        if (args.isEmpty()) {
            sender.sendMessage("${session.prefix}${ChatColor.RED}Usage: /${BedWars.MAIN_COMMAND} removeTeam <teamName>")
            val teams = session.config.getConfigurationSection("Team") ?: return

            sender.sendMessage(session.prefix + "Available teams: ")
            for (team in teams.getKeys(false)) sender.message(
                "${ChatColor.GOLD} ▪ ${getChatColor(team)}$team",
                "${ChatColor.GRAY}Remove ${getChatColor(team)}$team ${ChatColor.GRAY}(click to remove)",
                "/${BedWars.MAIN_COMMAND} removeTeam $team",
                ClickEvent.Action.RUN_COMMAND
            )
            return
        }

        val team = args[0]
        if ("Team.$team.Color" !in session.config) {
            sender.sendMessage("${session.prefix}This team doesn't exist: $team")
            BedWars.nms.sendTitle(sender, " ", "${ChatColor.RED}Team not found: $team", 5, 40, 5)
            playSound(ConfigPath.SOUNDS_INSUFF_MONEY, sender)
            return
        }

        arrayOf("Iron", "Gold", "Emerald").forEach { gen ->
            session.config.getArenaLocations("Team.$team.$gen").forEach {
                removeArmorStand(it)
            }
        }

        arrayOf("Shop", "Upgrade", ConfigPath.ARENA_TEAM_KILL_DROPS_LOC).forEach {
            removeArmorStand(session.config.getArenaLoc("Team.$team.$it"))
        }

        sender.sendMessage(session.prefix + "Team removed: " + session.getColoredTeamName(team))
        BedWars.nms.sendTitle(sender, " ", "${ChatColor.GREEN}Team removed: ${session.getColoredTeamName(team)}", 5, 40, 5)
        playSound(ConfigPath.SOUNDS_BOUGHT, sender)
        session.config["Team.$team"] = null
    }

    private fun removeArmorStand(location: Location?) {
        if (location != null) Misc.removeArmorStand(null, location, null)
    }
}
