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
package com.andrei1058.bedwars.listeners

import com.andrei1058.bedwars.api.events.ArenaEvent
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent
import com.andrei1058.bedwars.api.events.server.ArenaDisableEvent
import com.andrei1058.bedwars.api.events.server.ArenaEnableEvent
import com.andrei1058.bedwars.arena.ArenaGUI
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class RefreshGUI : Listener {

    private fun refresh(e: ArenaEvent?) {
        val arena = e?.arena
        Bukkit.getOnlinePlayers().forEach { ArenaGUI.refreshInv(it, arena, arena?.players?.size ?: 0) }
    }

    @EventHandler fun onGameStateChange(e: GameStateChangeEvent) = refresh(e)

    @EventHandler
    fun onPlayerJoinArena(e: PlayerJoinArenaEvent) {
        if (!e.isSpectator) refresh(e)
    }

    @EventHandler
    fun onPlayerLeaveArena(e: PlayerLeaveArenaEvent) {
        if (!e.isSpectator) refresh(e)
    }

    @EventHandler fun onArenaEnable(e: ArenaEnableEvent) = refresh(e)
    @EventHandler fun onArenaDisable(e: ArenaDisableEvent) = refresh(null)
}
