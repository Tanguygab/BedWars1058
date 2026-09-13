package com.andrei1058.bedwars.api.events.team

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.events.ArenaEvent
import org.bukkit.event.HandlerList

/**
 * Called when all player on a team get killed and Bed is broken.
 * @param arena the arena.
 * @param team the eliminated team.
 */
class TeamEliminatedEvent(
    arena: IArena,
    val team: ITeam
) : ArenaEvent(arena) {

    override fun getHandlers() = handlerList

    companion object {
        val handlerList = HandlerList()
    }
}