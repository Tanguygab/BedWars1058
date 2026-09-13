package com.andrei1058.bedwars.api.arena

import org.bukkit.entity.Player

interface ArenaManager {

    /**
     * Get the list of arenas
     */
    val arenas: Map<String, IArena>

    /**
     * Get/Set how many games till the next restart.
     * This is used only if {@link com.andrei1058.bedwars.api.server.ServerType#BUNGEE}
     */
    var gamesBeforeRestart: Int

    /**
     * Arena enable queue.
     */
    val enableQueue: List<IArena>

    /**
     * Get an arena by arena name
     *
     * @param name arena name
     */
    fun getArena(name: String): IArena?

    /**
     * Get an arena by a player. Spectator or Player.
     *
     * @param player Target player
     * @return The arena where the player is in. Can be null.
     */
    fun getArena(player: Player): IArena?

    /**
     * Get an arena by world name
     *
     * @param world world name
     */
    fun getArenaByWorld(world: String): IArena?


    /**
     * Add a player to the most filled arena.
     * Check if is the party owner first.
     */
    fun joinRandomArena(p: Player): Boolean

    /**
     * Add a player to the most filled arena from a group.
     */
    fun joinRandomFromGroup(player: Player, group: String): Boolean

    /**
     * Load an arena.
     * Add it to the enable queue.
     *
     * @param player If you want to send feedback. Use null otherwise.
     */
    fun loadArena(worldName: String, player: Player? = null): IArena?

    /**
     * Check if a player is in the arena.
     *
     * @return true if playing or spectating.
     */
    fun isInArena(player: Player): Boolean

    /**
     * Check if a player is playing.
     */
    fun isPlaying(player: Player): Boolean

    /**
     * Check if a player is spectating.
     */
    fun isSpectating(player: Player): Boolean

    /**
     * Get players count for a group
     */
    fun getPlayers(group: String): Int

    /**
     * Check if an arena can be auto-scaled.
     *
     * @return always true if auto-scale is disabled.
     */
    fun canAutoScale(arena: String): Boolean



    /**
     * Add a custom arena to the enable queue.
     */
    fun addToEnableQueue(arena: IArena)

    /**
     * Remove an arena from the enable queue.
     */
    fun removeFromEnableQueue(arena: IArena)
}