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
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.vipfeatures.api.MiniGame
import org.bukkit.entity.Player

class VipFeatures(private val plugin: BedWars) : MiniGame(plugin) {
    override fun getDisplayName() = "BedWars1058"
    override fun hasBoosters() = false

    override fun isPlaying(p: Player?): Boolean {
        if (p == null) return false
        val a = plugin.arenaManager.getArena(p)
        return a != null && a.status != GameState.WAITING && a.status != GameState.STARTING
    }
}
