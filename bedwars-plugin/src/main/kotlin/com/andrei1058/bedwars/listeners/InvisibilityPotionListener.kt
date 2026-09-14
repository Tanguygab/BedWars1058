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
package com.andrei1058.bedwars.listeners

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.events.player.PlayerInvisibilityPotionEvent
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack

/**
 * This is used to hide and show player name tag above head when he drinks an invisibility
 * potion or when the potion is gone. It is required because it is related to scoreboards.
 */
class InvisibilityPotionListener(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onPotion(e: PlayerInvisibilityPotionEvent) {
        plugin.scoreboardManager.handleInvisibility(
            e.playerTeam,
            e.player,
            e.type == PlayerInvisibilityPotionEvent.Type.ADDED
        )
    }

    @EventHandler
    fun onDrink(e: PlayerItemConsumeEvent) {
        val player = e.player
        val arena = plugin.arenaManager.getArena(player) ?: return

        val item = e.item
        if (item.type != Material.POTION) return
        // remove potion bottle
        val nms = plugin.versionSupport
        plugin.run(delay = 5) { nms.minusAmount(player, ItemStack(Material.GLASS_BOTTLE), 1) }

        if (!nms.isInvisibilityPotion(item)) return

        plugin.run(delay = 5) {
            val invisibility = player.activePotionEffects
                .find { "INVISIBILITY" in it.type.toString() }
                ?: return@run

            // keep trace of invisible players to send hide armor packet when required
            // because potions do not hide armors
            arena.showTime[player] = invisibility.duration / 20

            val team = arena.getTeam(player)!!
            // call custom event
            plugin.server.pluginManager.callEvent(PlayerInvisibilityPotionEvent(
                PlayerInvisibilityPotionEvent.Type.ADDED,
                player,
                team.arena
            ))

            // if is already invisible
            if (arena.showTime.containsKey(player)) return@run

            // if not already invisible
            for (p1 in player.world.players) {
                // hide player armor to spectators & other teams
                if (arena.isSpectator(p1) || team !== arena.getTeam(p1)) {
                    nms.hideArmor(player, p1)
                }
            }
        }
    }
}
