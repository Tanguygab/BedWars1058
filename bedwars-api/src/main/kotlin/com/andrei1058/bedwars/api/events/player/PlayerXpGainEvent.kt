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

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * Called when a player receives new xp.
 * This only works when the internal Level System is used.
 * Developers can "inject" their own level system.
 *
 * @param player   - target player.
 * @param amount   - amount of xp.
 * @param xpSource - where did the player receive xp from.
 */
class PlayerXpGainEvent(
    /**
     * Get the player that have received new xp.
     */
    val player: Player,
    /**
     * Get the amount of xp received.
     */
    val amount: Int,
    /**
     * Get xp source
     */
    val xpSource: XpSource
) : Event() {
    /**
     * Lets you know why did the player received new xp.
     */
    enum class XpSource {
        PER_MINUTE, PER_TEAMMATE, GAME_WIN, BED_DESTROYED, FINAL_KILL, REGULAR_KILL, OTHER
    }

    override fun getHandlers() = handlerList

    companion object {
        val handlerList = HandlerList()
    }
}
