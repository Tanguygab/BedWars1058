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
package com.andrei1058.bedwars.shop.listeners

import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent
import com.andrei1058.bedwars.api.events.player.PlayerReJoinEvent
import com.andrei1058.bedwars.shop.quickbuy.PlayerQuickBuyCache
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class QuickBuyListener : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun onArenaJoin(e: PlayerJoinArenaEvent) {
        if (e.isSpectator) return
        destroy(e.player)
        PlayerQuickBuyCache(e.player)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onArenaJoin(e: PlayerReJoinEvent) {
        destroy(e.player)
        PlayerQuickBuyCache(e.player)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onQuit(e: PlayerQuitEvent) = destroy(e.player)

    private fun destroy(player: Player) = PlayerQuickBuyCache.getQuickBuyCache(player.uniqueId)?.destroy()
}
