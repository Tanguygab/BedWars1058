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
package com.andrei1058.bedwars.listeners.joinhandler

import com.andrei1058.bedwars.BedWars
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListenerShared(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val player = e.player

        JoinHandlerCommon.displayCustomerDetails(player)

        // Show commands if player is op and there is no set arenas
        if (player.isOp && plugin.arenaManager.arenas.isEmpty()) {
            player.performCommand(plugin.mainCommand.name)
        }

        plugin.run(delay = 14) {
            // Hide new player to players and spectators, and vice versa
            for (arena in plugin.arenaManager.arenas.values) {
                for (inArena in arena.allPlayers) {
                    if (inArena == player) continue
                    plugin.versionSupport.hidePlayer(player, inArena)
                    plugin.versionSupport.hidePlayer(inArena, player)
                }
            }
        }

        // Give scoreboard
        if (player.world.name.equals(plugin.lobbyWorld, ignoreCase = true)) {
            plugin.scoreboardManager.giveSidebar(player, null, true)
        }
    }
}

