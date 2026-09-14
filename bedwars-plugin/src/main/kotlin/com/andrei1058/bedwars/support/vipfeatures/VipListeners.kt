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
package com.andrei1058.bedwars.support.vipfeatures

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.vipfeatures.api.IVipFeatures
import com.andrei1058.vipfeatures.api.event.BlockChangeEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.util.Vector

class VipListeners(private val plugin: BedWars, private val api: IVipFeatures) : Listener {
    @EventHandler
    fun onServerJoin(e: PlayerJoinEvent) {
        if (plugin.serverType == ServerType.MULTIARENA) {
            plugin.run(delay = 10) { api.givePlayerItemStack(e.player) }
        }
    }

    @EventHandler
    fun onArenaJoin(e: PlayerJoinArenaEvent) {
        plugin.run(delay = 10) { api.givePlayerItemStack(e.player) }
    }

    @EventHandler
    fun onBockChange(e: BlockChangeEvent) {
        val a = plugin.arenaManager.getArena(e.location.world!!.name) ?: return
        val nms = plugin.versionSupport
        for (t in a.teams) {
            for (x in -1..1) {
                for (z in -1..1) {
                    if (e.location.blockX == t.bed.blockX &&
                        e.location.blockY == t.bed.blockY &&
                        e.location.blockZ == t.bed.blockZ
                    ) {
                        if (nms.isBed(t.bed.clone().add(x.toDouble(), 0.0, z.toDouble()).block.type))
                            e.isCancelled = true
                        return
                    }
                }
            }
        }
        a.placed += Vector(e.location.blockX, e.location.blockY, e.location.blockZ)
    }
}
