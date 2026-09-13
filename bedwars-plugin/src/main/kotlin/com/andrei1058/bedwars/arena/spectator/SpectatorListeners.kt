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
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent
import com.andrei1058.bedwars.api.events.spectator.SpectatorFirstPersonEnterEvent
import com.andrei1058.bedwars.api.events.spectator.SpectatorFirstPersonLeaveEvent
import com.andrei1058.bedwars.api.events.spectator.SpectatorTeleportToPlayerEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.configuration.Sounds.playSound
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityEvent
import org.bukkit.event.entity.EntityRegainHealthEvent
import org.bukkit.event.entity.FoodLevelChangeEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.event.player.PlayerToggleSneakEvent

class SpectatorListeners(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onSpectatorItemInteract(e: PlayerInteractEvent) {
        val player = e.player
        val item = BedWars.nms.getItemInHand(player)
        if (item.type == Material.AIR) return
        if (!BedWars.nms.isCustomBedWarsItem(item)) return
        val arena = plugin.arenaManager.getArena(player) ?: return
        if (!arena.isSpectator(player)) return

        // Disable spectator interact
        e.isCancelled = true
    }

    @EventHandler
    fun onSpectatorBlockInteract(e: PlayerInteractEvent) {
        val block = e.clickedBlock ?: return
        if (!plugin.arenaManager.isSpectating(e.player)) return
        if ("DOOR" in block.type.toString())  // Disable spectator interact
            e.setCancelled(true)
    }

    @EventHandler
    fun onSpectatorInventoryClose(e: InventoryCloseEvent) {
        TeleporterGUI.closeGUI(e.player as? Player ?: return)
    }

    @EventHandler
    fun onSpectatorClick(e: InventoryClickEvent) {
        val player = e.whoClicked as? Player ?: return
        if (player.gameMode == GameMode.SPECTATOR) {
            e.isCancelled = true
            return
        }

        val item = e.currentItem ?: return
        if (item.type == Material.AIR) return

        val arena = plugin.arenaManager.getArena(player) ?: return
        if (!arena.isSpectator(player)) return

        // Teleporter heads
        if (!BedWars.nms.isPlayerHead(item.type, 3) || !BedWars.nms.itemStackDataCompare(item, 3.toShort())) return
        val data = BedWars.nms.getCustomData(item) ?: return
        e.isCancelled = true

        if (TeleporterGUI.NBT_SPECTATOR_TELEPORTER_GUI_HEAD !in data) return

        val targetName = data.removePrefix(TeleporterGUI.NBT_SPECTATOR_TELEPORTER_GUI_HEAD)
        val target = Bukkit.getPlayer(targetName) ?: return
        if (target.isDead || !target.isOnline) return

        val event = SpectatorTeleportToPlayerEvent(player, target, arena)
        Bukkit.getPluginManager().callEvent(event)
        if (event.isCancelled()) return

        player.teleport(target)
        playSound("spectator-gui-click", player)
        player.closeInventory()
    }

    // Refresh placeholders from GUIs
    private fun onChange(e: EntityEvent) {
        val player = e.entity as? Player ?: return
        val arena = plugin.arenaManager.getArena(player) ?: return
        if (arena.isPlayer(player)) TeleporterGUI.refreshAllGUIs()
    }
    @EventHandler fun onHealthChange(e: EntityRegainHealthEvent) = onChange(e)
    @EventHandler fun onFoodChange(e: FoodLevelChangeEvent) = onChange(e)

    @EventHandler // Refresh placeholders from GUIs
    fun onPlayerLeave(e: PlayerLeaveArenaEvent) {
        if (!e.arena.isPlayer(e.player)) return
        TeleporterGUI.refreshAllGUIs()
    }

    @EventHandler // Triggered when a spectator starts spectating in first person
    fun onSpectatorInteractPlayer(e: PlayerInteractEntityEvent) {
        val target = e.rightClicked as? Player ?: return
        val spectator = e.player
        val arena = plugin.arenaManager.getArena(spectator) ?: return
        if (arena.isPlayer(spectator)) return
        e.isCancelled = true

        if (!arena.isPlayer(target)) return
        if (spectator.spectatorTarget != null) {
            sendLeaveEventTitle(spectator, arena, true)
        }
        val event = SpectatorFirstPersonEnterEvent(
            spectator,
            target,
            arena,
            { Language.getMsg(it, Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE) },
            { Language.getMsg(it, Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE) }
        )
        Bukkit.getPluginManager().callEvent(event)
        if (event.isCancelled) return

        spectator.apply {
            inventory.heldItemSlot = 5
            gameMode = GameMode.SPECTATOR
            spectatorTarget = target
        }
        BedWars.nms.sendTitle(
            spectator,
            event.title(spectator)
                .replace("{player}", target.displayName)
                .replace("{playername}", spectator.name),
            event.subTitle(spectator)
                .replace("{player}", target.displayName)
                .replace("{playername}", spectator.name),
            event.fadeIn,
            event.stay,
            event.fadeOut
        )
    }

    private fun sendLeaveEventTitle(player: Player, arena: IArena, other: Boolean = false) {
        val event = SpectatorFirstPersonLeaveEvent(
            player,
            arena,
            { Language.getMsg(it, Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE) },
            { Language.getMsg(it, Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE) })
        Bukkit.getPluginManager().callEvent(event)
        if (other) return

        player.gameMode = GameMode.ADVENTURE
        player.allowFlight = true
        player.isFlying = true
        BedWars.nms.sendTitle(
            player,
            event.title(player),
            event.subTitle(player),
            event.fadeIn,
            event.stay,
            event.fadeOut
        )
    }

    private fun onMove(player: Player): Boolean {
        val arena = plugin.arenaManager.getArena(player) ?: return false
        if (!arena.isSpectator(player)) return false
        sendLeaveEventTitle(player, arena)
        return true
    }

    @EventHandler // Triggered when a spectator leaves first person
    fun onSneak(e: PlayerToggleSneakEvent) {
        val player = e.player
        if (player.spectatorTarget == null) return
        onMove(player)
    }

    @EventHandler // Prevent game-mode 3 menu
    fun onTeleport(e: PlayerTeleportEvent) {
        val player = e.player
        if (e.to!!.world == player.world || e.cause != PlayerTeleportEvent.TeleportCause.SPECTATE) return
        if (onMove(player)) e.isCancelled = true
    }

    @EventHandler // Remove from first person on target die
    fun onTargetDeath(e: PlayerKillEvent) {
        for (player in e.arena.spectators) {
            if (player.spectatorTarget == null || player.spectatorTarget !== e.victim) continue
            sendLeaveEventTitle(player, e.arena)
        }
    }

    @EventHandler(ignoreCancelled = true) // Disable hits from spectators
    fun onDamageByEntity(e: EntityDamageByEntityEvent) {
        val arena = plugin.arenaManager.getArenaByWorld(e.entity.world.name) ?: return
        val player = when (val damager = e.damager) {
            is Projectile -> damager.shooter as? Player
            is TNTPrimed -> damager.source as? Player
            is Player -> {
                if (arena.respawnSessions.containsKey(damager)) {
                    e.isCancelled = true
                    return
                }
                damager
            }
            else -> null
        }
        if (player == null || !arena.isSpectator(player)) return
        e.isCancelled = true
    }
}
