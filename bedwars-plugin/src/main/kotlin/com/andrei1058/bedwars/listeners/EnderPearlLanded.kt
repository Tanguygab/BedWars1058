package com.andrei1058.bedwars.listeners

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.configuration.Sounds.playSound
import org.bukkit.entity.EnderPearl
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent

class EnderPearlLanded(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onPearlHit(e: ProjectileHitEvent) {
        val pearl = e.entity as? EnderPearl ?: return
        val player = pearl.shooter as? Player ?: return
        val arena = plugin.arenaManager.getArena(player) ?: return
        if (arena.isSpectator(player)) return

        playSound("ender-pearl-landed", arena.players)
    }
}
