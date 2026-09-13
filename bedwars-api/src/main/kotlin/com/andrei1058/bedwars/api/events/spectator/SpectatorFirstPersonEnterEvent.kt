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
package com.andrei1058.bedwars.api.events.spectator

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.events.ArenaEvent
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList

/**
 * Called when a spectator enters the first person spectating system.
 */
class SpectatorFirstPersonEnterEvent(
    /**
     * Get the spectator
     */
    val spectator: Player,
    /**
     * Get the target player
     */
    val target: Player,
    arena: IArena,
    /**
     * Get first person enter title
     */
    var title: (Player) -> String,
    /**
     * Set first person enter title and subtitle. Leave "" for empty msg
     */
    var subTitle: (Player) -> String
) : ArenaEvent(arena), Cancellable {
    private var cancelled = false

    var fadeIn = 0
        set(value) {
            field = value.coerceAtLeast(0)
        }
    var stay = 40
        set(value) {
            field = value.coerceAtLeast(0)
        }
    var fadeOut = 10
        set(value) {
            field = value.coerceAtLeast(0)
        }

    override fun isCancelled() = cancelled

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

    override fun getHandlers() = handlerList

    companion object {
        val handlerList = HandlerList()
    }
}
