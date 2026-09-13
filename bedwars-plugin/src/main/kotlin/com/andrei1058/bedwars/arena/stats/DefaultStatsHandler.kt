package com.andrei1058.bedwars.arena.stats

import com.andrei1058.bedwars.api.arena.stats.DefaultStatistics
import com.andrei1058.bedwars.api.arena.stats.Incrementable
import com.andrei1058.bedwars.api.arena.stats.PlayerGameStats
import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * Used to increase default session stats.
 */
class DefaultStatsHandler : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onBedWarsKill(event: PlayerKillEvent) {
        val statsHolder = event.arena.statsHolder

        val killer = event.killer
        val victim = event.victim

        // INCREMENT KILLER STATS
        if ((killer != null && event.victimTeam != event.killerTeam) && victim != killer) {
            val killerStats = statsHolder.get(killer)
            killerStats?.increment(DefaultStatistics.KILLS)
            if (event.cause.isFinalKill) killerStats?.increment(DefaultStatistics.KILLS_FINAL)
        }

        // INCREMENT VICTIM STATS
        val victimStats = statsHolder.get(victim) ?: return
        victimStats.increment(DefaultStatistics.DEATHS)
        if (event.cause.isFinalKill) victimStats.increment(DefaultStatistics.DEATHS_FINAL)
    }

    @EventHandler(ignoreCancelled = true)
    fun onBedWarsBedBreak(event: PlayerBedBreakEvent) {
        val stats = event.arena.statsHolder.get(event.player) ?: return
        stats.increment(DefaultStatistics.BEDS_DESTROYED)
    }

    fun PlayerGameStats.increment(stat: DefaultStatistics) = (getStatistic(stat) as? Incrementable)?.increment()
}
