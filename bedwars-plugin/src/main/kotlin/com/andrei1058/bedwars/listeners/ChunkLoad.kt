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

import com.andrei1058.bedwars.BedWars
import org.bukkit.ChatColor
import org.bukkit.entity.ArmorStand
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.ChunkLoadEvent

class ChunkLoad(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onChunkLoadEvent(e: ChunkLoadEvent) {
        plugin.run(async = true) {
            for (entity in e.chunk.entities) {
                if (entity !is ArmorStand) continue
                if (!entity.hasMetadata("bw1058-setup")) {
                    if (entity.isVisible || !entity.isMarker || !entity.isCustomNameVisible /*|| entity.hasGravity()*/) continue
                    if (!ChatColor.stripColor(entity.customName)!!.contains(" set", ignoreCase = true)) continue
                }
                plugin.run { entity.remove() }
            }
        }
    }
}
