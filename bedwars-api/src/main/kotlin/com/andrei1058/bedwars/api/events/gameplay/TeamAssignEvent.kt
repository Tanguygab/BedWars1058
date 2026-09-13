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
package com.andrei1058.bedwars.api.events.gameplay

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.events.ArenaEvent
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList

/**
 * Called for each player when the waiting countdown == 0
 * You can cancel each team assign event in order to manage them yourself
 * but make sure to set BedWarsTeam#setBedDestroyed(false) if teams are marked as eliminated when they are not,
 * and use BedWarsTeam#firstSpawn(p) to spawn them. But first assign them to a team BedWarsTeam#addPlayers(p).
 *
 *
 * READ THIS: If you want to assign the player another team there's no ned of a setTeam method in this event.
 * Just use [ITeam.addPlayers] right after using [.setCancelled].
 */
class TeamAssignEvent(
    val player: Player,
    /**
     * Get the team
     * 
     * @return the team assigned to the player
     */
    val team: ITeam,
    arena: IArena
) : ArenaEvent(arena), Cancellable {
    private var cancelled = false

    override fun isCancelled() = cancelled
    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

    override fun getHandlers() = handlerList

    companion object {
        val handlerList = HandlerList()
    }
}
