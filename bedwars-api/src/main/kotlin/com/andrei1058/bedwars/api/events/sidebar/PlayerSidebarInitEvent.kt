package com.andrei1058.bedwars.api.events.sidebar

import com.andrei1058.bedwars.api.sidebar.ISidebar
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerSidebarInitEvent(var player: Player, var sidebar: ISidebar) : Event(), Cancellable {
    private var cancelled = false

    override fun getHandlers() = handlerList

    override fun isCancelled() = cancelled

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

    companion object {
        val handlerList = HandlerList()
    }
}
