package com.andrei1058.bedwars

import com.andrei1058.bedwars.api.AFKManager
import com.andrei1058.bedwars.api.events.player.PlayerAfkEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

class AFKManagerImpl : AFKManager {
    companion object {
        private const val DELAY = 45
    }
    private val afkPlayers = mutableMapOf<UUID, Int>()

    override fun isAFK(player: Player) = (afkPlayers[player.uniqueId] ?: 0) > DELAY

    override fun setAFK(player: Player, seconds: Int?) {
        if (seconds != null) {
            if (!isAFK(player)) {
                afkPlayers[player.uniqueId] = seconds
                if (seconds > DELAY) callEvent(player, PlayerAfkEvent.AFKType.START)
            }
            return
        }
        if (isAFK(player)) callEvent(player, PlayerAfkEvent.AFKType.END)
        afkPlayers.remove(player.uniqueId)
    }

    private fun callEvent(player: Player, type: PlayerAfkEvent.AFKType) {
        Bukkit.getPluginManager().callEvent(PlayerAfkEvent(player, type))
    }

    override fun getAFKTime(player: Player) = afkPlayers[player.uniqueId] ?: 0
}