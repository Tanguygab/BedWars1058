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
import com.andrei1058.bedwars.arena.ReJoin
import com.andrei1058.bedwars.Utils.teleportSafe
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListenerMultiArena(private val plugin: BedWars) : Listener {
    @EventHandler(priority = EventPriority.HIGH)
    fun onJoin(e: PlayerJoinEvent) {
        e.joinMessage = null
        val player = e.player
        player.inventory.setArmorContents(null)

        JoinHandlerCommon.displayCustomerDetails(player)

        // Show commands if player is op and there is no set arenas
        if (player.isOp && plugin.arenaManager.arenas.isEmpty()) {
            player.performCommand(BedWars.MAIN_COMMAND)
        }

        val reJoin = ReJoin.getPlayer(player)

        plugin.run(delay = 14) {
            // Hide new player to players and spectators, and vice versa
            // Players from lobby will remain visible
            for (online in Bukkit.getOnlinePlayers()) {
                if (plugin.arenaManager.isInArena(online)) {
                    BedWars.nms.hidePlayer(online, player)
                    BedWars.nms.hidePlayer(player, online)
                } else {
                    BedWars.nms.showPlayer(online, player)
                    BedWars.nms.showPlayer(player, online)
                }
            }

            // To prevent invisibility issues handle ReJoin after sending invisibility packets
            if (reJoin == null) return@run
            if (reJoin.canReJoin()) reJoin.reJoin(player)
            else reJoin.destroy(false)
        }

        if (reJoin != null && reJoin.canReJoin()) return

        // Teleport to lobby location
        val lobbyLocation = BedWars.config.getConfigLoc("lobbyLoc")
        if (lobbyLocation?.world != null) {
            player.teleportSafe(lobbyLocation)
        }

        // Send items
        plugin.arenaManager.sendLobbyCommandItems(player)

        plugin.scoreboardManager.giveSidebar(player, null, true)

        player.run {
            healthScale = player.maxHealth
            exp = 0f
            healthScale = 20.0
            foodLevel = 20
        }
    }
}

