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
package com.andrei1058.bedwars.arena

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.Utils.editMeta
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.configuration.Sounds.playSound
import com.andrei1058.bedwars.listeners.arenaselector.ArenaSelectorListener
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import java.util.UUID

object ArenaGUI {
    private val yml: YamlConfiguration = BedWars.config

    private val cooldown = HashMap<UUID?, Long?>()

    fun refreshInv(player: Player?, arena: IArena?, players: Int) {
        if (player == null) return
        val inventory = player.openInventory.topInventory
        val ash = inventory.holder as? ArenaSelectorHolder ?: return

        val arenaManager = BedWars.plugin.arenaManager
        var arenas: Collection<IArena> = arenaManager.arenas.values
        if (!ash.group.equals("default", ignoreCase = true)) {
           arenas = arenas.filter { it.group.equals(ash.group, ignoreCase = true) }
        }

        arenas = arenaManager.getSorted(arenas)

        var arenaKey = 0
        for (slot in usedSlots) {
            inventory.setItem(slot, ItemStack(Material.AIR))
            if (arenaKey >= arenas.size) continue

            val a = arenas[arenaKey]
            val status = when (a.status) {
                GameState.WAITING -> "waiting"
                GameState.PLAYING -> "playing"
                GameState.STARTING -> "starting"
                else -> continue
            }

            val item = BedWars.nms.createItemStack(
                yml.getString(ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_MATERIAL.replace("%path%", status))!!,
                1,
                yml.getInt(ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_DATA.replace("%path%", status)).toShort()
            )

            item.editMeta {
                if (yml.getBoolean(ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_ENCHANTED.replace("%path%", status))) {
                    addEnchant(Enchantment.LURE, 1, true)
                    addItemFlags(ItemFlag.HIDE_ENCHANTS)
                }

                setDisplayName(Language.getMsg(player, Messages.ARENA_GUI_ARENA_CONTENT_NAME)
                    .replace("{name}", a.displayName)
                    .replace("{map_name}", a.name)
                )

                val isDefaultGroup = a.group.equals("default", ignoreCase = true)
                lore = Language.getList(player, Messages.ARENA_GUI_ARENA_CONTENT_LORE)
                    .asSequence()
                    .filterNot { "{group}" in it && isDefaultGroup }
                    .map { it
                        .replace("{on}", "${if (arena === a) players else a.players.size}")
                        .replace("{max}", "${a.maxPlayers}")
                        .replace("{status}", a.getDisplayStatus(Language.getLanguage(player)))
                        .replace("{group}", a.getDisplayGroup(player))
                    }.toList()
            }
            inventory.setItem(
                slot,
                BedWars.nms.addCustomData(item, ArenaSelectorListener.ARENA_SELECTOR_GUI_IDENTIFIER + a.name)
            )
            ++arenaKey
        }
        player.updateInventory()
    }

    fun openGui(player: Player, group: String) {
        if (isOnCooldown(player)) return
        setCooldown(player)
        var size = BedWars.config.getInt(ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_SIZE)
        if (size % 9 != 0) size = 27
        if (size > 54) size = 54
        val ash = ArenaSelectorHolder(group)
        val inv = Bukkit.createInventory(ash, size, Language.getMsg(player, Messages.ARENA_GUI_INV_NAME))
        ash.inv = inv

        //ash.setInv(inv);
        val skippedSlotMaterial = BedWars.config.getString(ConfigPath
            .GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_MATERIAL
            .replace("%path%", "skipped-slot")
        )!!.uppercase()
        if (skippedSlotMaterial != "none" && skippedSlotMaterial != "air") {
            var i = BedWars.nms.createItemStack(
                skippedSlotMaterial,
                1,
                BedWars.config.getInt(ConfigPath
                    .GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_DATA
                    .replace("%path%", "skipped-slot")
                ).toShort()
            )
            i = BedWars.nms.addCustomData(i, "RUNCOMMAND_bw join random")

            val serverIP = BedWars.config.getString(ConfigPath.GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_SERVER_IP)!!
            val poweredBy = BedWars.config.getString(ConfigPath.GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_POWERED_BY)!!

            i.editMeta {
                setDisplayName(ChatColor.translateAlternateColorCodes('&', Language
                    .getMsg(player, Messages.ARENA_GUI_SKIPPED_ITEM_NAME)
                    .replace("{serverIp}", serverIP)
                    .replace("{poweredBy}", poweredBy)
                ))

                lore = Language
                    .getList(player, Messages.ARENA_GUI_SKIPPED_ITEM_LORE)
                    .map { it
                        .replace("{serverIp}", serverIP)
                        .replace("{poweredBy}", poweredBy)
                    }
                addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }

            ((0..<inv.size) - usedSlots).forEach { inv.setItem(it, i) }
        }

        player.openInventory(inv)
        refreshInv(player, null, 0)
        playSound("arena-selector-open", player)
    }

    private val usedSlots = BedWars.config
        .getString(ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_USE_SLOTS)!!
        .split(",")
        .mapNotNull { it.toIntOrNull() }
        .toSet()

    private fun isOnCooldown(player: Player): Boolean {
        return (cooldown[player.uniqueId] ?: 0) > System.currentTimeMillis()
    }

    private fun setCooldown(player: Player) {
        cooldown[player.uniqueId] = System.currentTimeMillis() + 2000
    }

    class ArenaSelectorHolder(val group: String?) : InventoryHolder {
        lateinit var inv: Inventory
        override fun getInventory() = inv
    }
}
