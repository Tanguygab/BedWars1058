package com.andrei1058.bedwars.api.events

import com.andrei1058.bedwars.api.arena.IArena
import org.bukkit.event.Event

abstract class ArenaEvent(
    /**
     * Get the arena
     */
    val arena: IArena
) : Event()