package com.andrei1058.bedwars.api

import org.bukkit.entity.Player

interface AFKManager {
    /**
     * Check if a player is AFK.
     */
    fun isAFK(player: Player): Boolean

    /**
     * Set a player afk.
     */
    fun setAFK(player: Player, seconds: Int?)

    /**
     * Get the seconds since the player is AFK
     */
    fun getAFKTime(player: Player): Int
}