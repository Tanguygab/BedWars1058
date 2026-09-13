package com.andrei1058.bedwars.listeners

import com.andrei1058.bedwars.BedWars
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class Warnings(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val player = e.player
        if (!player.isOp) return

        if (plugin.server.pluginManager.isPluginEnabled("Multiverse-Core")) {
            player.sendDelayedMessage("Multiverse-Core detected! Please remove it or make sure it won't touch BedWars maps!")
        }

        if (plugin.server.spawnRadius <= 0) return
        player.sendDelayedMessage("Your spawn-protection in server.properties is enabled. " +
                "${ChatColor.YELLOW}This might mess with BedWars arenas! " +
                "${ChatColor.GRAY}It is highly reccomend setting it to 0."
        )
    }

    private fun Player.sendDelayedMessage(message: String) = plugin.run(delay = 5) {
        sendMessage("${ChatColor.RED}[BedWars1058] $message")
    } // run after 5 ticks to make sure its after any update spam on join
}
