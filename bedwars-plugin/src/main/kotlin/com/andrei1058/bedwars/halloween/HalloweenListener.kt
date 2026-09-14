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
package com.andrei1058.bedwars.halloween

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.getForCurrentVersion
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import com.andrei1058.bedwars.api.events.player.PlayerXpGainEvent
import com.andrei1058.bedwars.api.events.server.ArenaDisableEvent
import com.andrei1058.bedwars.api.events.server.ArenaEnableEvent
import com.andrei1058.bedwars.api.events.server.ArenaRestartEvent
import com.andrei1058.bedwars.levels.internal.PlayerLevel
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.CreatureSpawnEvent
import org.bukkit.event.world.WorldLoadEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue

class HalloweenListener : Listener {
    private val ambienceSound = Sound.valueOf(getForCurrentVersion("AMBIENT_CAVE", "AMBIENCE_CAVE", "AMBIENT_CAVE"))
    private val ghastSound = Sound.valueOf(getForCurrentVersion(
        "ENTITY_GHAST_SCREAM",
        "GHAST_SCREAM2",
        "ENTITY_GHAST_SCREAM"
    ))

    @EventHandler(ignoreCancelled = true)
    fun onCreatureSpawn(e: CreatureSpawnEvent) {
        val entity = e.entity
        if (entity.type == EntityType.ARMOR_STAND) return
        entity.equipment!!.helmet = ItemStack(Material.PUMPKIN)
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onWorldLoad(e: WorldLoadEvent) {
        // check if it is time to disable this special
        if (!HalloweenSpecial.enabled || HalloweenSpecial.checkAvailabilityDate()) return
        CreatureSpawnEvent.getHandlerList().unregister(this)
    }

    @EventHandler
    fun onPlayerDie(e: PlayerKillEvent) {
        if (e.killer == null) return
        val location = e.victim.location.add(0.0, 1.0, 0.0)
        val block = location.block
        if (block.type != Material.AIR) return

        location.world!!.playSound(location, ghastSound, 2f, 1f)
        if (e.arena.isProtected(location)) return

        block.type = Material.valueOf(getForCurrentVersion("COBWEB", "WEB"))
        e.arena.addPlacedBlock(block)
        block.setMetadata("give-bw-exp", FixedMetadataValue(BedWars.INSTANCE, "ok"))
        CobWebRemover.getByArenaWorld(e.arena.worldName)?.addCobWeb(block)
    }

    @EventHandler(ignoreCancelled = true)
    fun onBlockBreak(e: BlockBreakEvent) {
        if (!e.block.hasMetadata("give-bw-exp")) return
        val level = PlayerLevel.getLevelByPlayer(e.player.uniqueId)
        e.block.drops.clear()
        level.addXp(5, PlayerXpGainEvent.XpSource.OTHER)
        e.player.sendMessage("${ChatColor.GOLD}+5 xp!")
    }

    @EventHandler
    fun onJoin(e: PlayerJoinArenaEvent) {
        if (e.isSpectator) return
        BedWars.INSTANCE.run(delay = 20) { e.player.world.playSound(e.player.location, ambienceSound, 3f, 1f) }
    }

    @EventHandler
    fun onGameStateChange(e: GameStateChangeEvent) {
        if (e.newState != GameState.RESTARTING) return
        CobWebRemover.getByArenaWorld(e.arena.worldName)?.destroy()
    }

    @EventHandler
    fun onRestart(e: ArenaRestartEvent) {
        CobWebRemover.getByArenaWorld(e.worldName)?.destroy()
    }

    @EventHandler
    fun onDisable(e: ArenaDisableEvent) {
        CobWebRemover.getByArenaWorld(e.worldName)?.destroy()
    }

    @EventHandler
    fun onEnable(e: ArenaEnableEvent) {
        CobWebRemover(e.arena)
    }
}
