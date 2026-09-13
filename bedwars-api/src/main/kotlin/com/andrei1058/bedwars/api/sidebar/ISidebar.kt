package com.andrei1058.bedwars.api.sidebar

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.spigot.sidebar.PlaceholderProvider
import com.andrei1058.spigot.sidebar.Sidebar
import com.andrei1058.spigot.sidebar.SidebarLine
import org.bukkit.entity.Player

interface ISidebar {
    /**
     * Sidebar holder.
     */
    val player: Player

    /**
     * Coincides with the arena where the player is on.
     */
    val arena: IArena?

    /**
     * Get sidebar lib handle.
     */
    val handle: Sidebar?

    /**
     * Set sidebar content.
     */
    fun setContent(titleArray: List<String>, lineArray: List<String>, arena: IArena?)

    /**
     * Convert an animated string to an object.
     */
    fun normalizeTitle(titleArray: List<String>): SidebarLine

    /**
     * Convert string lines to string objects.
     */
    fun normalizeLines(lineArray: List<String>): List<SidebarLine>

    /**
     * Will update tab prefix and suffix for the given player on current sidebar.
     *
     * @param player         format given player on current holder's sidebar.
     * @param skipStateCheck will skip checking if tab formatting is disabled.
     * @param spectator when you already know the player is a spectator. E.g. on join. False will let the plugin whether the player is spectator or not.
     */
    fun giveUpdateTabFormat(player: Player, skipStateCheck: Boolean = false, spectator: Boolean = false)

    /**
     * Register a placeholder that is not going to be removed trough game state changes.
     */
    fun registerPersistentPlaceholder(placeholderProvider: PlaceholderProvider): Boolean
}
