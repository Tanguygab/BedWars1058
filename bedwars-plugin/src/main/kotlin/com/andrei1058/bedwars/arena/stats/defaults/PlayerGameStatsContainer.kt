package com.andrei1058.bedwars.arena.stats.defaults

import com.andrei1058.bedwars.api.arena.stats.GameStatistic
import com.andrei1058.bedwars.api.arena.stats.PlayerGameStats
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class PlayerGameStatsContainer(player: Player) : PlayerGameStats {
    override val player = player.uniqueId
    override val username = player.name

    // last tracked display name
    private val lastDisplayName = player.displayName
    private val statsById = mutableMapOf<String, GameStatistic<*>>()

    override val displayPlayer get() = Bukkit.getPlayer(player)?.displayName ?: lastDisplayName

    override fun registerStatistic(id: String, defaultValue: GameStatistic<*>) {
        if (id in statsById) {
            throw RuntimeException("Statistic $id already registered for player $player")
        }
        statsById[id] = defaultValue
    }

    override fun getStatistic(id: String) = statsById[id]

    override val registered get() = statsById.keys
}
