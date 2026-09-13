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
package com.andrei1058.bedwars.api.region

import org.bukkit.Location
import kotlin.math.max
import kotlin.math.min

class Cuboid(loc: Location, radius: Int, override var isProtected: Boolean) : Region {
    private val minX: Int
    private val maxX: Int
    var minY: Int
    var maxY: Int
    private val minZ: Int
    private val maxZ: Int

    init {
        val l1 = loc.clone().subtract(radius.toDouble(), radius.toDouble(), radius.toDouble())
        val l2 = loc.clone().add(radius.toDouble(), radius.toDouble(), radius.toDouble())

        minX = min(l1.blockX, l2.blockX)
        maxX = max(l1.blockX, l2.blockX)

        minY = min(l1.blockY, l2.blockY)
        maxY = max(l1.blockY, l2.blockY)

        minZ = min(l1.blockZ, l2.blockZ)
        maxZ = max(l1.blockZ, l2.blockZ)
    }

    override fun isInRegion(location: Location) = location.blockX in minX..maxX &&
            location.blockY in minY..maxY &&
            location.blockZ in minZ..maxZ
}
