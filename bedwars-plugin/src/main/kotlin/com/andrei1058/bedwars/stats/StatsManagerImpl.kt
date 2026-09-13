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

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.StatsManager
import org.bukkit.Bukkit
import java.sql.Timestamp
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class StatsManagerImpl : StatsManager {
    private val cache = ConcurrentHashMap<UUID, PlayerStats>()

    init {
        BedWars.plugin.registerEvents(StatsListener(this))
    }

    private val UUID.stats get() = getUnsafe(this)
        ?: BedWars.remoteDatabase.fetchStats(this)
    override fun getPlayerFirstPlay(player: UUID) = Timestamp.from(player.stats.firstPlay)!!
    override fun getPlayerLastPlay(player: UUID) = Timestamp.from(player.stats.lastPlay)!!
    override fun getPlayerWins(player: UUID) = player.stats.wins
    override fun getPlayerKills(player: UUID) = player.stats.kills
    override fun getPlayerTotalKills(player: UUID) = player.stats.totalKills
    override fun getPlayerFinalKills(player: UUID) = player.stats.finalKills
    override fun getPlayerLoses(player: UUID) = player.stats.losses
    override fun getPlayerDeaths(player: UUID) = player.stats.deaths
    override fun getPlayerFinalDeaths(player: UUID) = player.stats.finalDeaths
    override fun getPlayerBedsDestroyed(player: UUID) = player.stats.bedsDestroyed

    override fun getPlayerGamesPlayed(player: UUID) = player.stats.gamesPlayed


    fun remove(uuid: UUID) {
        cache.remove(uuid)
    }

    fun put(uuid: UUID, playerStats: PlayerStats) {
        cache[uuid] = playerStats
    }

    fun get(uuid: UUID) = checkNotNull(cache[uuid]) { "Trying to get stats data of an unloaded player!" }

    fun getUnsafe(uuid: UUID) = cache[uuid]
}
