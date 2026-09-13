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
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.configuration.Sounds.playSound
import com.andrei1058.bedwars.shop.ShopCache
import com.andrei1058.bedwars.shop.listeners.InventoryListener
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Sign
import org.bukkit.entity.Fireball
import org.bukkit.entity.ItemFrame
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.PrepareItemCraftEvent
import org.bukkit.event.player.PlayerArmorStandManipulateEvent
import org.bukkit.event.player.PlayerBedEnterEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.material.Openable
import org.bukkit.metadata.FixedMetadataValue

class Interact(private val plugin: BedWars) : Listener {
    private val fireballSpeedMultiplier = BedWars.config.getDouble(ConfigPath.GENERAL_FIREBALL_SPEED_MULTIPLIER)
    private val fireballCooldown = BedWars.config.getDouble(ConfigPath.GENERAL_FIREBALL_COOLDOWN)
    private val fireballExplosionSize = BedWars.config.getDouble(ConfigPath.GENERAL_FIREBALL_EXPLOSION_SIZE).toFloat()

    @EventHandler /* Handle custom items with commands on them */
    fun onItemCommand(e: PlayerInteractEvent) {
        val player = e.player
        if (e.action != Action.RIGHT_CLICK_BLOCK && e.action != Action.RIGHT_CLICK_AIR) return

        val item = BedWars.nms.getItemInHand(player)
        if (!BedWars.nms.isCustomBedWarsItem(item)) return

        val customData = BedWars.nms.getCustomData(item)!!.split("_")
        if (customData.size < 2 || customData[0] != "RUNCOMMAND") return

        e.setCancelled(true)
        BedWars.plugin.run { Bukkit.dispatchCommand(player, customData[1]) }
    }

    @EventHandler(ignoreCancelled = true) //Check if player is opening an inventory
    fun onInventoryInteract(e: PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_BLOCK) return
        val block = e.clickedBlock ?: return

        if ((BedWars.serverType !== ServerType.MULTIARENA ||
                    block.world.name != BedWars.lobbyWorld ||
                    BreakPlace.isBuildSession(e.getPlayer())
            ) && !BedWars.plugin.arenaManager.isInArena(e.player)
        ) return

        val type = block.type
        if (type == BedWars.nms.materialCraftingTable() && BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_DISABLE_CRAFTING) ||
            type == BedWars.nms.materialEnchantingTable() && BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_DISABLE_ENCHANTING) ||
            type == Material.FURNACE && BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_DISABLE_FURNACE) ||
            type == Material.BREWING_STAND && BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_DISABLE_BREWING_STAND) ||
            type == Material.ANVIL && BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_DISABLE_ANVIL)
        ) e.setCancelled(true)
    }

    @EventHandler
    fun onInteract(e: PlayerInteractEvent) {
        val player = e.player
        plugin.afkManager.setAFK(player, null)
        val arenaManager = plugin.arenaManager
        if (e.action == Action.RIGHT_CLICK_BLOCK) {
            val block = e.clickedBlock ?: return
            if (block.type == Material.AIR) return
            val arena = arenaManager.getArena(player)
            if (arena != null) {
                if (arena.respawnSessions.containsKey(player)) {
                    e.setCancelled(true)
                    return
                }
                if (BedWars.nms.isBed(block.type) && (!player.isSneaking || BedWars.nms.getItemInHand(player).type == Material.AIR)) {
                    e.setCancelled(true)
                    return
                }
                if (block.type == Material.CHEST) {
                    if (arena.isSpectator(player) || arena.respawnSessions.containsKey(player)) {
                        e.setCancelled(true)
                        return
                    }
                    //make it so only team members can open chests while team is alive, and all when is eliminated
                    val isRad = arena.config.getInt(ConfigPath.ARENA_ISLAND_RADIUS)
                    val owner = arena.teams.find { it.spawn!!.distance(block.location) <= isRad }
                    if (owner != null && !owner.isMember(player) && (owner.members.isNotEmpty() || !owner.isBedDestroyed)) {
                        e.setCancelled(true)
                        player.sendLangMsg(Messages.INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED)
                    }
                }
                if (arena.isSpectator(player) || arena.respawnSessions.containsKey(player)) {
                    when (block.type) {
                        Material.CHEST,
                        Material.ENDER_CHEST,
                        Material.ANVIL,
                        BedWars.nms.materialCraftingTable(),
                        Material.HOPPER,
                        Material.TRAPPED_CHEST -> e.setCancelled(true)
                        else -> {}
                    }
                    if (block.state is Openable) e.setCancelled(true)
                }
            }
            if (block.state is Sign) {
                for (a1 in arenaManager.arenas.values) {
                    if (!a1.signs.contains(block)) continue
                    playSound(
                        "join-${if (a1.addPlayer(player, false)) "allowed" else "denied"}",
                        player
                    )
                    return
                }
            }
        }

        //check hand
        if (e.action != Action.RIGHT_CLICK_BLOCK && e.action != Action.RIGHT_CLICK_AIR) return
        val inHand = e.item ?: return
        val a = arenaManager.getArena(player) ?: return
        if (!a.isPlayer(player) || inHand.type != BedWars.nms.materialFireball()) return
        e.setCancelled(true)

        if (System.currentTimeMillis() - (a.fireballCooldowns[player.uniqueId] ?: 0) <= fireballCooldown * 1000) return
        a.fireballCooldowns[player.uniqueId] = System.currentTimeMillis()

        var fb = player.launchProjectile(Fireball::class.java)
        val direction = player.eyeLocation.direction
        fb = BedWars.nms.setFireballDirection(fb, direction)
        fb.velocity = fb.direction.multiply(fireballSpeedMultiplier)
        //fb.setIsIncendiary(false); // apparently this on <12 makes the fireball not explode on hit. wtf bukkit?
        fb.yield = fireballExplosionSize
        fb.setMetadata("bw1058", FixedMetadataValue(BedWars.plugin, "ceva"))
        BedWars.nms.minusAmount(player, inHand, 1)
    }


    @EventHandler
    fun disableItemFrameRotation(e: PlayerInteractEntityEvent) {
        val frame = e.rightClicked as? ItemFrame ?: return
        val player = e.player

        if (frame.item.type == Material.AIR) {
            //prevent from putting upgradable items in it
            val item = BedWars.nms.getItemInHand(player)
            if (item.type != Material.AIR) {
                val sc = ShopCache.getShopCache(player.uniqueId) ?: return
                if (!InventoryListener.shouldCancelMovement(item, sc)) return
                e.isCancelled = true
            }
            return
        }
        val arena = BedWars.plugin.arenaManager.getArenaByWorld(player.world.name)
        if (arena != null ||
            (BedWars.serverType == ServerType.MULTIARENA &&
            BedWars.lobbyWorld == player.world.name &&
            !BreakPlace.isBuildSession(player))
        ) e.isCancelled = true
    }

    @EventHandler
    fun onEntityInteract(e: PlayerInteractEntityEvent) {
        val arena = BedWars.plugin.arenaManager.getArena(e.player) ?: return
        val location = e.rightClicked.location
        for (team in arena.teams) {
            val shop = team.shop!!
            val upgrades = team.teamUpgrades!!
            if (location.blockX == shop.blockX && location.blockY == shop.blockY && location.blockZ == shop.blockZ ||
                location.blockX == upgrades.blockX && location.blockY == upgrades.blockY && location.blockZ == upgrades.blockZ
            ) e.isCancelled = true
        }
    }

    @EventHandler
    fun onBedEnter(e: PlayerBedEnterEvent) {
        if (BedWars.plugin.arenaManager.isInArena(e.player)) e.isCancelled = true
    }

    @EventHandler(ignoreCancelled = true)
    fun onArmorManipulate(e: PlayerArmorStandManipulateEvent) {
        //prevent from breaking generators
        val player = e.player
        if (BedWars.plugin.arenaManager.isInArena(player)) e.isCancelled = true

        //prevent from stealing from armor stands in lobby
        if (BedWars.serverType === ServerType.MULTIARENA &&
            player.location.world!!.name.equals(BedWars.lobbyWorld, ignoreCase = true) &&
            !BreakPlace.isBuildSession(player)
        ) e.isCancelled = true
    }

    @EventHandler
    fun onCrafting(e: PrepareItemCraftEvent) {
        if (!BedWars.plugin.arenaManager.isInArena(e.view.player as? Player ?: return)) return
        if (!BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_DISABLE_CRAFTING)) return
        e.inventory.result = ItemStack(Material.AIR)
    }
}
