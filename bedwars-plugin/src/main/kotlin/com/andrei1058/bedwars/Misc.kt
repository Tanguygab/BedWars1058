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
package com.andrei1058.bedwars

import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.util.Utils.editMeta
import com.google.common.io.ByteStreams
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import java.io.File

object Misc {
    fun moveToLobbyOrKick(player: Player, arena: IArena?, notAbandon: Boolean = true) {
        val plugin = BedWars.INSTANCE

        if (plugin.serverType == ServerType.BUNGEE) {
            forceKick(player, arena, notAbandon)
            return
        }

        if (player.world.name.equals(plugin.mainConfig.lobbyWorldName, ignoreCase = true)) {
            forceKick(player, arena, notAbandon)
            return
        }

        val loc = plugin.mainConfig.getConfigLoc("lobbyLoc")
        if (loc == null) {
            // Can happen when location is not set in config
            forceKick(player, arena, notAbandon)
            return
        }

        try {
            player.teleport(loc)
        } catch (_: Exception) {
            Bukkit.getLogger().severe("Could not teleport player to lobby! Try setting the lobby again with /bw setLobby")
        }

        if (arena == null) return

        if (arena.isSpectator(player)) {
            arena.removeSpectator(player, false)
            return
        }

        arena.removePlayer(player, false)
        abandonGame(player, arena, notAbandon)
    }


    private fun forceKick(player: Player, arena: IArena?, notAbandon: Boolean) {
        val plugin = BedWars.INSTANCE
        player.sendPluginMessage(plugin, "BungeeCord", ByteStreams.newDataOutput().run {
            writeUTF("Connect")
            writeUTF(plugin.mainConfig.getString("lobbyServer")!!)
            toByteArray()
        })
        abandonGame(player, arena, notAbandon)

        if (plugin.serverType != ServerType.BUNGEE) return
        plugin.run(delay = 30) {
            // if lobby server is unreachable
            if (!player.isOnline) return@run
            player.kickPlayer(Language.getMsg(player, Messages.ARENA_RESTART_PLAYER_KICK))
            abandonGame(player, arena, notAbandon)
        }
    }

    private fun abandonGame(player: Player, arena: IArena?, notAbandon: Boolean) {
        if (arena == null || notAbandon || arena.status != GameState.PLAYING) return
        if (BedWars.INSTANCE.mainConfig.getBoolean(ConfigPath.GENERAL_CONFIGURATION_MARK_LEAVE_AS_ABANDON)) {
            arena.abandonGame(player)
        }
    }

    /**
     * Create an item stack
     * 
     * @param material item material
     * @param data     item data
     * @param name     item name
     * @param lore     item lore
     * @param owner    in case of skull, can be null, don't worry
     */
    fun createItem(
        material: Material,
        data: Byte,
        enchanted: Boolean,
        name: String,
        lore: List<String>,
        owner: Player?,
        metaKey: String,
        metaData: String
    ): ItemStack {
        var item = ItemStack(material, 1, data.toShort())
        item.editMeta {
            setDisplayName(name)
            this.lore = lore
            if (enchanted) {
                addEnchant(Enchantment.LUCK, 1, true)
                addItemFlags(ItemFlag.HIDE_ENCHANTS)
            }
        }
        val nms = BedWars.INSTANCE.versionSupport
        if (metaData.isNotEmpty() && metaKey.isNotEmpty()) {
            item = nms.addCustomData(item, metaKey + "_" + metaData)
        }
        if (owner != null && nms.isPlayerHead(material, data.toInt())) {
            item = nms.getPlayerHead(owner, item)
        }
        return item
    }


    fun getArenas() = File(BedWars.INSTANCE.dataFolder, "/Arenas")
        .listFiles { it.isFile && it.name.endsWith(".yml") }
        ?.map { it.nameWithoutExtension }
        ?: emptyList()

    /**
     * This is used to spawn armorStands during the setup
     * so the player knows what he set
     *
     * @since api v6
     */
    fun createArmorStand(name: String, location: Location, configLoc: String?) {
        (location.world!!.spawnEntity(location.block.location.add(0.5, 2.0, 0.5), EntityType.ARMOR_STAND) as ArmorStand).apply {
            isVisible = false
            isMarker = true
            setGravity(false)
            isCustomNameVisible = true
            customName = name
            setMetadata("bw1058-setup", FixedMetadataValue(BedWars.INSTANCE, "hologram"))
            if (configLoc != null) {
                setMetadata("bw1058-loc", FixedMetadataValue(BedWars.INSTANCE, configLoc))
            }
        }
    }

    /**
     * Remove an armor stand
     */
    fun removeArmorStand(contains: String?, location: Location, configLoc: String?) {
        for (e in location.getWorld()!!.getNearbyEntities(location, 1.0, 3.0, 1.0)) {
            if (e.hasMetadata("bw1058-setup")) {
                if (!e.hasMetadata("bw1058-loc")) {
                    e.remove()
                    continue
                }
                if (e.getMetadata("bw1058-loc")[0].asString().equals(configLoc, ignoreCase = true)) {
                    if (!contains.isNullOrEmpty() && contains in ChatColor.stripColor(e.customName)!!) {
                        e.remove()
                        return
                    }
                    e.remove()
                }
                continue
            }
            if (e is ArmorStand && !e.isVisible && !contains.isNullOrEmpty() && contains in e.customName!!)
                e.remove()
        }
    }
}
