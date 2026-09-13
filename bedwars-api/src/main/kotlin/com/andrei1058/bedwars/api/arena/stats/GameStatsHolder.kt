package com.andrei1058.bedwars.api.arena.stats

import com.andrei1058.bedwars.api.arena.IArena
import org.bukkit.entity.Player
import java.util.UUID

interface GameStatsHolder {
    val arena: IArena

    /**
     * Register statistic.
     * Throws a runtime exception if statistic is already registered.
     * @param statistic new statistic.
     */
    fun register(statistic: GameStatisticProvider<*>)

    /**
     * Initialize game session stats for given player.
     * @param player stats holder.
     */
    fun init(player: Player): PlayerGameStats

    /**
     * Remove player tracked data.
     * @param uuid holder.
     */
    fun unregisterPlayer(uuid: UUID)

    /**
     * Get existing or initialize statistic for given player.
     * @param holder player holder.
     * @return Existing or new statistic.
     */
    fun getCreate(holder: Player): PlayerGameStats

    /**
     * Get existing or initialize statistic for given player.
     * @param holder player holder.
     * @return Existing or new statistic.
     */
    fun get(holder: UUID): PlayerGameStats?

    /**
     * Get existing or initialize statistic for given player.
     * @param holder player holder.
     * @return Existing or new statistic.
     */
    fun get(holder: Player) = get(holder.uniqueId)

    /**
     * Get tracked players.
     * 
     * @return Unmodifiable list of tracked players.
     */
    val trackedPlayers: Collection<PlayerGameStats>


    /**
     * @param statistic Order collection by given statistic.
     * @return top list.
     */
    fun getOrderedBy(statistic: DefaultStatistics) = getOrderedBy(statistic.toString())

    /**
     * @param statistic Order collection by given statistic.
     * @return top list.
     */
    fun getOrderedBy(statistic: String): List<PlayerGameStats>

    /**
     * Check if given statistic is registered.
     */
    fun hasStatistic(orderBy: String): Boolean

    /**
     * @return unmodifiable list of registered game statistics.
     */
    val registered: List<String>

    /**
     * Get statistic provider.
     */
    fun getProvider(registered: String): GameStatisticProvider<*>?
}
