package com.andrei1058.bedwars.api.events

import com.andrei1058.bedwars.api.arena.IArena
import org.bukkit.entity.Player

abstract class PlayerArenaEvent(
    /**
     * Get player
     */
    val player: Player,
    arena: IArena,
) : ArenaEvent(arena) {
    /**
     * Get the player team.
     */
    val playerTeam = arena.getTeam(player)!!
}