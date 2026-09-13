package com.andrei1058.bedwars.arena.stats

import com.andrei1058.bedwars.BedWars.Companion.debug
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.stats.DefaultStatistics
import com.andrei1058.bedwars.api.arena.stats.GameStatisticProvider
import com.andrei1058.bedwars.api.arena.stats.GameStatsHolder
import com.andrei1058.bedwars.api.arena.stats.PlayerGameStats
import com.andrei1058.bedwars.arena.stats.defaults.PlayerGameStatsContainer
import org.bukkit.entity.Player
import java.util.UUID

class GameStatsManager(override val arena: IArena) : GameStatsHolder {
    private val registeredStats = mutableMapOf<String, GameStatisticProvider<*>>()
    override val registered get() = registeredStats.keys.toList()

    private val tracked = mutableMapOf<UUID, PlayerGameStats>()
    override val trackedPlayers get() = tracked.values.toList()

    override fun get(holder: UUID) = tracked[holder]

    override fun hasStatistic(orderBy: String) = orderBy in registeredStats
    override fun getProvider(registered: String) = registeredStats[registered]

    init {
        for (statistic in DefaultStatistics.entries) {
            if (!statistic.isIncrementable) continue
            register(object : GenericStatistic() {
                override val identifier = statistic.toString()
            })
        }
    }

    override fun register(statistic: GameStatisticProvider<*>) {
        if (statistic.identifier.isEmpty()) {
            throw RuntimeException("Identifier cannot be blank: ${statistic.javaClass.name}")
        }
        if (statistic.identifier != statistic.identifier) {
            throw RuntimeException("Identifier should not start/end with white spaces: ${statistic.javaClass.name}")
        }
        if (statistic.identifier in registeredStats) {
            throw RuntimeException("Statistic already registered: " + statistic.identifier)
        }
        registeredStats[statistic.identifier] = statistic
        debug("Registered new game statistic: " + statistic.identifier)
    }

    override fun init(player: Player): PlayerGameStats {
        if (player.uniqueId in tracked) {
            throw RuntimeException(player.name + " is already registered for game stats!")
        }

        val stats = PlayerGameStatsContainer(player)
        registeredStats.forEach { (id, provider) -> stats.registerStatistic(id, provider.default) }

        tracked[player.uniqueId] = stats
        return stats
    }

    override fun unregisterPlayer(uuid: UUID) {
        if (arena.status == GameState.RESTARTING) {
            throw RuntimeException("You cannot unregister player stats during restarting phase!")
        }
        tracked.remove(uuid)
    }

    override fun getCreate(holder: Player): PlayerGameStats {
        val ps = tracked[holder.uniqueId]
        if (ps != null) return ps

        val stats = init(holder)
        tracked[holder.uniqueId] = stats
        return stats
    }

    override fun getOrderedBy(statistic: String): List<PlayerGameStats> {
        val list = tracked.values
            .filter { it.getStatistic(statistic) != null }
            .toMutableList()
        list.sortBy { it.getStatistic(statistic) }
        list.reverse()
        return list
    }
}
