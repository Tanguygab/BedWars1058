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
package com.andrei1058.bedwars.listeners.arenaselector

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.arena.ArenaGUI.ArenaSelectorHolder
import com.andrei1058.bedwars.configuration.Sounds.playSound
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent

class ArenaSelectorListener(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onArenaSelectorClick(e: InventoryClickEvent) {
        val player = e.whoClicked as Player
        if (player.openInventory.topInventory.holder !is ArenaSelectorHolder) return

        e.isCancelled = true

        val item = e.currentItem ?: return
        if (item.type == Material.AIR || !plugin.versionSupport.isCustomBedWarsItem(item)) return

        val data = plugin.versionSupport.getCustomData(item)!!
        if (data.startsWith("RUNCOMMAND", ignoreCase = true)) {
            Bukkit.dispatchCommand(player, data.split("_")[1])
        }

        if (!data.contains(ARENA_SELECTOR_GUI_IDENTIFIER)) return

        val arenaName = data.split("=")[1]
        val arena = plugin.arenaManager.getArena(arenaName) ?: return
        val status = arena.status

        when (e.click) {
            ClickType.LEFT -> {
                playSound(if (status.isPreGame() && arena.addPlayer(player, false)) "join-allowed"
                else {
                    player.sendMessage(Language.getMsg(player, Messages.ARENA_JOIN_DENIED_SELECTOR))
                    "join-denied"
                }, player)
            }
            ClickType.RIGHT -> {
                playSound(if (status == GameState.PLAYING && arena.addSpectator(player, false, null)) {
                    "spectate-allowed"
                } else {
                    player.sendMessage(Language.getMsg(player, Messages.ARENA_SPECTATE_DENIED_SELECTOR))
                    "spectate-denied"
                }, player)
            }
            // Incorrect click
            else -> return
        }

        player.closeInventory()
    }

    companion object {
        const val ARENA_SELECTOR_GUI_IDENTIFIER = "arena="
    }
}
