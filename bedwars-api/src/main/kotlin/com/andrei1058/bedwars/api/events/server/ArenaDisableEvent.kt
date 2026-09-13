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
package com.andrei1058.bedwars.api.events.server

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * Called when an arena is disabled.
 * This is not called when you restart the server.
 */
class ArenaDisableEvent(
    /**
     * Get the arena name
     */
    val arenaName: String,
    val worldName: String
) : Event() {
    override fun getHandlers() = handlerList

    companion object {
        val handlerList = HandlerList()
    }
}
