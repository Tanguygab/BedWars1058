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
import org.bukkit.event.HandlerList
import java.util.UUID

class GameEndEvent(
    arena: IArena,
    winners: List<UUID>,
    losers: List<UUID>,
    /**
     * Get the winner team
     */
    val winnerTeam: ITeam,
    aliveWinners: List<UUID>
) : ArenaEvent(arena) {
    /**
     * Get a list of winners including eliminated teammates
     */
    val winners = winners.toList()

    /**
     * Get a list with people who played and didn't win.
     * This includes people who leaved the game etc.
     */
    val losers = losers.toList()

    /**
     * Get a list of winners.
     * Teammates killed by final kill excluded.
     */
    val aliveWinners = aliveWinners.toList()

    override fun getHandlers() = handlerList

    companion object {
        val handlerList = HandlerList()
    }
}
