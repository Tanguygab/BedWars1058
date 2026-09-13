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
package com.andrei1058.bedwars.arena.team

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.arena.team.ITeamAssigner
import com.andrei1058.bedwars.api.events.gameplay.TeamAssignEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class TeamAssigner : ITeamAssigner {
    private val skip = mutableListOf<Player>()

    override fun assignTeams(arena: IArena) {
        // team up parties first

        if (arena.players.size > arena.maxInTeam && arena.maxInTeam > 1) {
            val teams = mutableListOf<MutableList<Player>>()

            for (player in arena.players) {
                val members = BedWars.party.getMembers(player).toMutableList()
                if (members.isEmpty()) continue
                members.removeIf { !arena.isPlayer(it) }
                teams += members
            }

            // prioritize bigger teams
            if (teams.isNotEmpty()) {
                for (team in arena.teams) {
                    // sort
                    teams.sortBy { it.size }
                    val firstTeam = teams[0]
                    if (firstTeam.isEmpty()) break

                    var i = 0
                    while (i < arena.maxInTeam && team.members.size < arena.maxInTeam) {
                        if (firstTeam.size <= i) break
                        val toAdd = firstTeam.removeAt(0)
                        if (toAdd.assignTeam(team, arena)) skip += toAdd
                        ++i
                    }
                }
            }
        }

        for (remaining in arena.players) {
            if (remaining in skip) continue
            for (team in arena.teams) {
                if (team.members.size >= arena.maxInTeam) continue

                remaining.assignTeam(team, arena)
                break
            }
        }
    }

    private fun Player.assignTeam(team: ITeam, arena: IArena): Boolean {
        val e = TeamAssignEvent(this, team, arena)
        Bukkit.getPluginManager().callEvent(e)
        if (e.isCancelled()) return false

        closeInventory()
        team.addPlayers(this)
        return true
    }
}
