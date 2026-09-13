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
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent
import com.andrei1058.bedwars.shop.ShopCache
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class ShopCacheListener : Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    fun onArenaJoin(e: PlayerJoinArenaEvent) {
        if (e.isSpectator) return
        val player = e.player
        destroy(player)
        ShopCache(player.uniqueId)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onArenaLeave(e: PlayerLeaveArenaEvent) = destroy(e.player)

    @EventHandler
    fun onServerLeave(e: PlayerQuitEvent) {
        //if (BedWars.serverType == ServerType.BUNGEE) return
        //don't remove immediately in case of /rejoin
        destroy(e.player)
    }

    private fun destroy(player: Player) = ShopCache.getShopCache(player.uniqueId)?.destroy()
}
