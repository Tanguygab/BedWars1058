package com.andrei1058.bedwars.listeners

import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Item
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class GameEndListener : Listener {
    @EventHandler
    fun cleanInventoriesAndDroppedItems(e: GameEndEvent) {
        if (e.arena.players.isEmpty()) return

        // clear inventories
        for (p in e.aliveWinners) {
            Bukkit.getPlayer(p)!!.inventory.clear()
        }

        // clear dropped items
        e.arena.world.entities.forEach { (it as? Item)?.remove() }
    }
}
