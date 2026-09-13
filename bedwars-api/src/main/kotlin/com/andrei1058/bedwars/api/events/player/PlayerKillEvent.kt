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
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.events.ArenaEvent
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList

/**
 * Called when a Player got killed during the game.
 *
 * @param killer can be NULL.
 */
class PlayerKillEvent(
    arena: IArena,
    /**
     * Get the Player who died.
     */
    val victim: Player,
    val victimTeam: ITeam,
    /**
     * Killer can be NULL (void etc.)
     */
    val killer: Player?,
    val killerTeam: ITeam?,
    /**
     * Get kill cause
     */
    val cause: PlayerKillCause,
    /**
     * Get/Set kill chat message.
     */
    val message: (Player) -> String
) : ArenaEvent(arena) {
    /**
     * Checks/Sets if the killer gets the kill sound
     */
    var playSound = true

    enum class PlayerKillCause(
        val isFinalKill: Boolean,
        /**
         * @return true if killed by a player's ironGolem, silverfish etc.
         */
        val isDespawnable: Boolean,
        val isPvpLogOut: Boolean
    ) {
        UNKNOWN(false, false, false),
        UNKNOWN_FINAL_KILL(true, false, false),
        EXPLOSION(false, false, false),
        EXPLOSION_FINAL_KILL(true, false, false),
        VOID(false, false, false),
        VOID_FINAL_KILL(true, false, false),
        PVP(false, false, false),
        PVP_FINAL_KILL(true, false, false),
        PLAYER_SHOOT(false, false, false),
        PLAYER_SHOOT_FINAL_KILL(true, false, false),
        SILVERFISH(false, true, false),
        SILVERFISH_FINAL_KILL(true, true, false),
        IRON_GOLEM(false, true, false),
        IRON_GOLEM_FINAL_KILL(true, true, false),
        PLAYER_PUSH(false, false, false),

        /**
         * Corresponds to FALL on ground.
         */
        PLAYER_PUSH_FINAL(true, false, false),
        PLAYER_DISCONNECT(false, false, true),
        PLAYER_DISCONNECT_FINAL(true, false, true)
    }

    override fun getHandlers() = handlerList

    companion object {
        val handlerList = HandlerList()
    }
}
