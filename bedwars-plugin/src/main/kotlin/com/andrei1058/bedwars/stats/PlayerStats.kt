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
package com.andrei1058.bedwars.stats

import java.time.Instant
import java.util.UUID

class PlayerStats(val uuid: UUID) {
    var firstPlay: Instant? = null
    var lastPlay: Instant? = null
    var wins: Int = 0
    var kills = 0
        set(value) {
            field = value
            totalKills += value
        }
    var finalKills = 0
        set(value) {
            field = value
            totalKills += value
        }
    var totalKills: Int = 0
        private set
    var losses: Int = 0
    var deaths: Int = 0
    var finalDeaths: Int = 0
    var bedsDestroyed: Int = 0
    var gamesPlayed: Int = 0
}
