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
package com.andrei1058.bedwars.halloween

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import org.bukkit.Bukkit
import org.bukkit.block.Block

class CobWebRemover(private val arena: IArena) {
    val taskId: Int
    private val cobWebs = mutableMapOf<Block, Long>()

    init {
        taskByArena[arena.worldName]
        taskId = Bukkit.getScheduler().runTaskTimer(BedWars.INSTANCE, Runnable {
            val currentTime = System.currentTimeMillis()
            cobWebs.filter { (block, time) -> time <= currentTime }
                .forEach { (block, _) ->
                    if ("WEB" in block.type.toString()) block.breakNaturally()
                    cobWebs.remove(block)
                }
        }, 20L, 20L).taskId
    }

    fun addCobWeb(block: Block) {
        cobWebs[block] = System.currentTimeMillis() + 7500L
    }

    fun destroy() {
        Bukkit.getScheduler().cancelTask(this.taskId)
        taskByArena.remove(arena.worldName)
    }

    companion object {
        private val taskByArena = mutableMapOf<String, CobWebRemover>()

        fun getByArenaWorld(world: String) = taskByArena[world]
    }
}
