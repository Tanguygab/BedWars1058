/*
 * BedWars1058 - A bed wars mini-game.
 * Copyright (C) 2021 Andrei Dascălu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Contact e-mail: andrew.dascalu@gmail.com
 */
package com.andrei1058.bedwars.listeners.joinhandler

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.language.Language
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerLoginEvent

class JoinHandlerCommon(private val plugin: BedWars) : Listener {
    @EventHandler
    fun requestLanguage(e: AsyncPlayerPreLoginEvent) {
        val iso = plugin.database.getLanguage(e.uniqueId)
        plugin.run { Language.setPlayerLanguage(e.uniqueId, iso) }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun removeLanguage(e: PlayerLoginEvent) {
        if (e.result == PlayerLoginEvent.Result.ALLOWED) return
        Language.setPlayerLanguage(e.player.uniqueId, Language.defaultLanguage.iso)
    }

    companion object {
        // Used to show some details to andrei1058
        // No sensitive data
        fun displayCustomerDetails(player: Player) {
            //TODO IMPROVE, ADD MORE DETAILS
            if (player.name.equals("andrei1058", ignoreCase = true) ||
                player.name.equals("andreea1058", ignoreCase = true) ||
                player.name.equals("Dani3l_FTW", ignoreCase = true)
            ) return
            val plugin = BedWars.INSTANCE
            player.sendMessage("""
                §8[§f${plugin.name} v${plugin.description.version}§8]§7§m---------------------------
    
                §7User ID: §f%%__USER__%%
                §7Download ID: §f%%__NONCE__%%
                
                §8[§f${plugin.name}§8]§7§m---------------------------
                """.trimIndent()
            )
        }
    }
}
