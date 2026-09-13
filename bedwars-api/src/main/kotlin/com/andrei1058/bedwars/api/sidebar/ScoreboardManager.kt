package com.andrei1058.bedwars.api.sidebar

import com.andrei1058.bedwars.api.arena.IArena
import org.bukkit.entity.Player

/**
 * BedWars scoreboard manager.
 */
interface ScoreboardManager {
    /**
     * Send player scoreboard based on conditions.
     */
    fun giveSidebar(player: Player, arena: IArena?, delay: Boolean)

    /**
     * Remove a player scoreboard.
     */
    fun remove(player: Player)

    /**
     * Refresh title on all scoreboards.
     */
    fun refreshTitles()

    /**
     * Refresh placeholders on all sidebars.
     */
    fun refreshPlaceholders()

    /**
     * Refresh placeholders for sidebars in a given arena;
     */
    fun refreshPlaceholders(arena: IArena)

    /**
     * Refresh all tab-list header and footer strings for every sidebar.
     */
    fun refreshTabList()

    /**
     * Refresh player healths.
     */
    fun refreshHealth()

    fun getSidebar(player: Player): ISidebar?
}
