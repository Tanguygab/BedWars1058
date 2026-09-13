package com.andrei1058.bedwars.api.arena.stats

import java.util.UUID

/**
 * Player stats container for a game.
 */
interface PlayerGameStats {
    val player: UUID

    /**
     * @return player display name.
     */
    val displayPlayer: String

    /**
     * @return player username.
     */
    val username: String

    fun registerStatistic(id: String, defaultValue: GameStatistic<*>)

    fun getStatistic(id: String): GameStatistic<*>?

    fun getStatistic(id: DefaultStatistics) = getStatistic("$id")

    val registered: Set<String>
}
