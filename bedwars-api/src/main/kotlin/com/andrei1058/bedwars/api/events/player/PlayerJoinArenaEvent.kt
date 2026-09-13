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
package com.andrei1058.bedwars.api.events.player

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.events.ArenaEvent
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList

/**
 * This event is called when a player joins the arena as a player or spectator.
 * The event is not triggered for players who died and become spectators. Listen the kill event for this.
 */
class PlayerJoinArenaEvent(
    arena: IArena,
    /**
     * Get player
     */
    val player: Player,
    /**
     * Check if the player has joined as spectator
     */
    val isSpectator: Boolean
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
