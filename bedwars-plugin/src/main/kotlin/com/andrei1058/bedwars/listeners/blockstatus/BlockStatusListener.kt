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
package com.andrei1058.bedwars.listeners.blockstatus

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent
import com.andrei1058.bedwars.api.events.server.ArenaEnableEvent
import org.bukkit.Material
import org.bukkit.block.Sign
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class BlockStatusListener : Listener {
    @EventHandler fun onArenaEnable(e: ArenaEnableEvent) = updateBlock(e.arena)
    @EventHandler fun onStatusChange(e: GameStateChangeEvent) = updateBlock(e.arena)

    companion object {
        /**
         * Update sign block
         */
        fun updateBlock(arena: IArena) {
            val plugin = BedWars.INSTANCE
            val nms = plugin.versionSupport
            val config = plugin.configs.signs
            for (sign in arena.signs) {
                val state = sign.state
                if (state !is Sign) continue
                val (path, data) = when (arena.status) {
                    GameState.WAITING -> ConfigPath.SIGNS_STATUS_BLOCK_WAITING_MATERIAL to ConfigPath.SIGNS_STATUS_BLOCK_WAITING_DATA
                    GameState.PLAYING -> ConfigPath.SIGNS_STATUS_BLOCK_PLAYING_MATERIAL to ConfigPath.SIGNS_STATUS_BLOCK_STARTING_DATA
                    GameState.STARTING -> ConfigPath.SIGNS_STATUS_BLOCK_PLAYING_MATERIAL to ConfigPath.SIGNS_STATUS_BLOCK_PLAYING_DATA
                    GameState.RESTARTING -> ConfigPath.SIGNS_STATUS_BLOCK_RESTARTING_MATERIAL to ConfigPath.SIGNS_STATUS_BLOCK_RESTARTING_DATA
                }
                nms.setJoinSignBackground(state, Material.valueOf(config.getString(path)!!))
                nms.setJoinSignBackgroundBlockData(state, config.getInt(data).toByte())
            }
        }
    }
}
