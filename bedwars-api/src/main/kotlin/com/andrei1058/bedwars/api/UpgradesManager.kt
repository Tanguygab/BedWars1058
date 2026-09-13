package com.andrei1058.bedwars.api

import com.andrei1058.bedwars.api.arena.IArena
import org.bukkit.entity.Player
import java.util.UUID

interface UpgradesManager {
    /**
     * Check if a player is watching the team upgrades menu.
     */
    fun isWatchingGUI(player: Player): Boolean

    /**
     * Set a player watching the team upgrades menu.
     */
    fun setWatchingGUI(player: Player)

    /**
     * Remove from upgrades GUI.
     */
    fun removeWatchingGUI(uuid: UUID)

    /**
     * Get total tiers in team upgrades to be bought in the given arena.
     * Sum of tiers in team upgrades.
     * @param arena arena
     * @return count
     */
    fun getTotalUpgradeTiers(arena: IArena): Int
}