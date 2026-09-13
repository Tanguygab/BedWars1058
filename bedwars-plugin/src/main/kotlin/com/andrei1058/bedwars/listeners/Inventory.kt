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
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.server.SetupType
import com.andrei1058.bedwars.arena.SetupSession
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryAction
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffectType

class Inventory(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onClose(e: InventoryCloseEvent) {
        val player = e.player as? Player ?: return
        if (BedWars.nms.getInventoryName(e) != SetupSession.INVENTORY_NAME) return

        val ss = SetupSession.getSession(player.uniqueId) ?: return
        if (ss.setupType == null) ss.cancel()
    }

    /**
     * Manage command-items when clicked in inventory
     */
    @EventHandler
    fun onCommandItemClick(e: InventoryClickEvent) {
        //block moving from hotBar
        if (e.action == InventoryAction.HOTBAR_SWAP && e.click == ClickType.NUMBER_KEY && e.hotbarButton > -1) {
            val item = e.whoClicked.inventory.getItem(e.hotbarButton)
            if (item != null && isCommandItem(item)) {
                e.isCancelled = true
                return
            }
        }

        //block moving cursor item outside
        testItem(e, e.cursor)

        val item = e.currentItem
        //block moving current item outside
        testItem(e, item)

        //block moving with shift
        if (e.action != InventoryAction.MOVE_TO_OTHER_INVENTORY) return
        if (item != null && isCommandItem(item)) e.isCancelled = true
    }

    private fun testItem(e: InventoryClickEvent, item: ItemStack?) {
        if (item == null || item.type == Material.AIR) return
        if (!isCommandItem(item)) return
        e.isCancelled = true
        val player = e.whoClicked
        if (e.clickedInventory?.type != player.inventory.type)
            player.closeInventory()
    }

    @EventHandler
    fun onClick(e: InventoryClickEvent) {
        //issue #225
        val player = e.whoClicked as? Player ?: return
        val arena = plugin.arenaManager.getArena(player)

        if (e.slotType == InventoryType.SlotType.ARMOR && arena != null && player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            player.closeInventory()
            player.world.players.forEach { BedWars.nms.hideArmor(player, it) }
        }

        val item = e.currentItem ?: return
        if (item.type == Material.AIR) return

        /*//Prevent moving of command items
        if (nms.isCustomBedWarsItem(i)) {
            if (e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
                e.setCancelled(true);
                return;
            }
            String[] customData = nms.getCustomData(i).split("_");
            if (customData.length >= 2) {
                if (customData[0].equals("RUNCOMMAND")) {
                    e.setCancelled(true);
                    return;
                }
            }
        }*/
        if (arena != null) {
            //Prevent players from moving items in stats GUI
            if (BedWars.nms.getInventoryName(e) == Language
                .getMsg(player, Messages.PLAYER_STATS_GUI_INV_NAME)
                .replace("{playername}", player.name)
                .replace("{player}", player.displayName)
            ) {
                e.isCancelled = true
                return
            }

            /* Make it so they can't toggle their armor */
            if (e.slotType == InventoryType.SlotType.ARMOR) {
                e.isCancelled = true
                return
            }
        }

        if (!item.hasItemMeta() || !item.itemMeta!!.hasDisplayName()) return
        if (BedWars.serverType == ServerType.MULTIARENA && player.location.world!!.name.equals(BedWars.lobbyWorld, ignoreCase = true)) {
            e.isCancelled = true
        }

        /* Check setup gui items */
        val ss = SetupSession.getSession(player.uniqueId)
        if (ss != null && BedWars.nms.getInventoryName(e) == SetupSession.INVENTORY_NAME) {
            ss.setupType = when (e.slot) {
                SetupSession.ADVANCED_SLOT -> SetupType.ADVANCED
                SetupSession.ASSISTED_SLOT -> SetupType.ASSISTED
                else -> ss.setupType
            }
            if (!ss.startSetup()) {
                ss.player.sendMessage("${ChatColor.RED}Could not start setup session. Pleas check the console.")
            }
            player.closeInventory()
            return
        }

        if (arena == null || !arena.isSpectator(player)) return
        e.isCancelled = true
        return
    }

    @EventHandler
    fun onGameEnd(e: GameStateChangeEvent) {
        if (e.newState != GameState.RESTARTING) return
        // close any open guis when the game ends (e.g. shop)
        e.arena.players.forEach { it.closeInventory() }
    }

    companion object {
        /**
         * Check if an item is command-item
         */
        private fun isCommandItem(item: ItemStack): Boolean {
            if (item.type == Material.AIR) return false
            if (!BedWars.nms.isCustomBedWarsItem(item)) return false

            val customData = BedWars.nms.getCustomData(item)!!.split("_")
            return customData.size >= 2 && customData[0] == "RUNCOMMAND"
        }
    }
}
