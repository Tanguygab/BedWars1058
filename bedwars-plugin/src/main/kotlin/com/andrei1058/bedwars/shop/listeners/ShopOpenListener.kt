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

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.shop.ShopConfig
import com.andrei1058.bedwars.shop.quickbuy.PlayerQuickBuyCache
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractAtEntityEvent

class ShopOpenListener(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onShopOpen(e: PlayerInteractAtEntityEvent) {
        val player = e.player
        val arena = plugin.arenaManager.getArena(player) ?: return
        if (arena.status != GameState.PLAYING) return

        val loc = e.rightClicked.location
        for (team in arena.teams) {
            val shopLoc = team.shop
            if (loc.blockX != shopLoc!!.blockX ||
                loc.blockY != shopLoc.blockY ||
                loc.blockZ != shopLoc.blockZ
            ) continue

            e.isCancelled = true
            if (!arena.isPlayer(player)) continue
            val cache = PlayerQuickBuyCache.getQuickBuyCache(player.uniqueId) ?: return
            ShopConfig.shop.open(player, cache, true)
        }
    }
}
