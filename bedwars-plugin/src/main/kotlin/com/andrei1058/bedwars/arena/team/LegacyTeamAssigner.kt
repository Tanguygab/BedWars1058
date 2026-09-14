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

object LegacyTeamAssigner : ITeamAssigner {
    override fun assignTeams(arena: IArena) {
        val plugin = BedWars.INSTANCE
        //Check who is having parties
        val skip = mutableListOf<Player>()
        val owners = mutableListOf<Player>()
        val party = plugin.partyUtil
        for (p in arena.players) {
            if (party.hasParty(p) && party.isOwner(p)) {
                owners += p
            }
        }

        //Mix teams order
        arena.teams.shuffle()

        //Team-up parties
        for (player in arena.players) {
            if (!owners.contains(player)) continue

            for (team in arena.teams) {
                if (skip.contains(player)) continue
                if (team.size + party.partySize(player) > arena.maxInTeam) continue

                skip += player
                player.closeInventory()
                player.assignTeam(team, arena)

                for (member in party.getMembers(player)) {
                    if (member === player) continue
                    val ia = plugin.arenaManager.getArena(member) ?: continue
                    if (ia != arena) continue

                    player.assignTeam(team, arena)
                    skip.add(member)
                    member.closeInventory()
                }
            }
        }

        //Give a team to players without a party
        for (player in arena.players) {
            if (player in skip) continue

            val team = arena.teams
                .asSequence()
                .filter { it.members.size < arena.maxInTeam }
                .minByOrNull { it.members.size }
                ?: arena.teams.first()
            player.assignTeam(team, arena)
            player.closeInventory()
        }
    }

    fun Player.assignTeam(team: ITeam, arena: IArena) {
        val e = TeamAssignEvent(this, team, arena)
        Bukkit.getPluginManager().callEvent(e)
        if (!e.isCancelled()) team.addPlayers(this)
    }
}
