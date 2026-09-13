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
package com.andrei1058.bedwars.arena.tasks

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.configuration.ConfigPath

class ReJoinTask(
    val arena: IArena,
    private val team: ITeam
) : Runnable {
    private val task = BedWars.plugin.server.scheduler.runTaskLater(
        BedWars.plugin,
        this,
        BedWars.config.getInt(ConfigPath.GENERAL_CONFIGURATION_REJOIN_TIME) * 20L
    )

    override fun run() {
        if (team.members.isNotEmpty()) return
        team.isBedDestroyed = true
        destroy()
    }

    /**
     * Destroy task
     */
    fun destroy() {
        reJoinTasks.remove(this)
        task.cancel()
    }

    fun cancel() = task.cancel()

    companion object {
        val reJoinTasks = mutableListOf<ReJoinTask>()
    }
}
