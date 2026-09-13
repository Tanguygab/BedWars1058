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
package com.andrei1058.bedwars.arena.spectator

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.chatSupport
import com.andrei1058.bedwars.Utils.editMeta
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import kotlin.math.ceil

object TeleporterGUI {
    //Don't remove "_" because it's used as a separator somewhere
    const val NBT_SPECTATOR_TELEPORTER_GUI_HEAD = "spectatorTeleporterGUIhead_"

    /**
     * Get a HashMap of players with Teleporter GUI opened
     */
    private val refresh = mutableMapOf<Player, Inventory>()

    /**
     * Refresh the Teleporter GUI for a player
     */
    fun refreshInv(player: Player, inv: Inventory) {
        val arena = BedWars.plugin.arenaManager.getArena(player)
        if (arena == null) {
            player.closeInventory()
            return
        }

        val players = arena.players
        for (i in 0 ..< inv.size) {
            inv.setItem(i, if (i < players.size)
                createHead(players[i], player, arena)
            else ItemStack(Material.AIR))
        }
    }

    /**
     * Opens the Teleporter GUI to a Player
     */
    fun openGUI(player: Player) {
        val arena = BedWars.plugin.arenaManager.getArena(player) ?: return

        val playerCount = arena.players.size
        val size = if ((playerCount % 9) == 0) playerCount else (ceil(playerCount / 9.0).toInt()) * 9

        val inv = Bukkit.createInventory(player, size.coerceAtLeast(54), Language.getMsg(player, Messages.ARENA_SPECTATOR_TELEPORTER_GUI_NAME))
        refreshInv(player, inv)
        refresh[player] = inv
        player.openInventory(inv)
    }

    /**
     * Refresh the Teleporter GUI for all players with it opened
     */
    fun refreshAllGUIs() = refresh.forEach(::refreshInv)

    /**
     * Create a player head
     */
    private fun createHead(player: Player, viewer: Player, arena: IArena): ItemStack {
        val team = arena.getTeam(player)!!

        val item = BedWars.nms.getPlayerHead(player, null)
        item.editMeta {
            setDisplayName(Language.getMsg(viewer, Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME)
                .replace("{vPrefix}", chatSupport.getPrefix(player))
                .replace("{vSuffix}", chatSupport.getSuffix(player))
                .replace("{team}", team.getDisplayName(Language.getLanguage(viewer)))
                .replace("{teamColor}", team.color.chat.toString())
                .replace("{player}", player.displayName)
                .replace("{playername}", player.name)
            )
            val health = player.health.toInt() * 100 / player.healthScale
            setLore(Language.getList(viewer, Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE)
                .map { it.replace("{health}", "$health").replace("{food}", "${player.foodLevel}") }
            )
        }
        return BedWars.nms.addCustomData(item, NBT_SPECTATOR_TELEPORTER_GUI_HEAD + player.name)
    }

    /**
     * Remove a player from the refresh list and close gui
     */
    fun closeGUI(player: Player) {
        if (player !in refresh) return
        refresh.remove(player)
        player.closeInventory()
    }
}
