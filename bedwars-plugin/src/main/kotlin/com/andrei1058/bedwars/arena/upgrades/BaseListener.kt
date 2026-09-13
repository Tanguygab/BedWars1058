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
package com.andrei1058.bedwars.arena.upgrades

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.events.player.PlayerBaseEnterEvent
import com.andrei1058.bedwars.api.events.player.PlayerBaseLeaveEvent
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent
import com.andrei1058.bedwars.arena.team.BedWarsTeam
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerTeleportEvent
import java.util.WeakHashMap

class BaseListener(private val plugin: BedWars) : Listener {
    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerMove(e: PlayerMoveEvent) {
        val player = e.player
        val arena = plugin.arenaManager.getArenaByWorld(player.world.name) ?: return
        if (arena.status == GameState.PLAYING) checkEvents(player, arena)
    }

    @EventHandler
    fun onTeleport(e: PlayerTeleportEvent) {
        val player = e.player
        if (player !in isOnABase) return

        val arena = plugin.arenaManager.getArena(player)
        if (arena == null) {
            isOnABase.remove(player)
            return
        }
        checkEvents(player, arena)
    }

    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {
        val a = plugin.arenaManager.getArena(e.entity) ?: return
        checkEvents(e.entity, a)
    }

    @EventHandler
    fun onBaseEnter(e: PlayerBaseEnterEvent) {
        val team = e.team
        if (team.isMember(e.player)) {
            // Give base effects
            for (ef in team.baseEffects) {
                e.player.addPotionEffect(ef, true)
            }
            return
        }
        // Trigger trap
        if (team.activeTraps.isNotEmpty()) {
            if (!team.isBedDestroyed) {
                team.activeTraps[0].trigger(team, e.player)
                team.activeTraps.removeAt(0)
            }
        }

        /* Manage trap */
        /*if (team.isTrapActive()) {
            team.disableTrap();
            for (Player mem : team.getMembers()) {
                if (team.isTrapTitle()) {
                    nms.sendTitle(mem, getMsg(mem, Messages.TRAP_ENEMY_BASE_ENTER_TITLE), null, 0, 50, 0);
                }
                if (team.isTrapSubtitle()) {
                    nms.sendTitle(mem, null, getMsg(mem, Messages.TRAP_ENEMY_BASE_ENTER_SUBTITLE), 0, 50, 0);
                }
                if (team.isTrapAction()) {
                    nms.playAction(mem, getMsg(mem, Messages.TRAP_ENEMY_BASE_ENTER_ACTION));
                }
                if (team.isTrapChat()) {
                    mem.sendMessage(getMsg(mem, Messages.TRAP_ENEMY_BASE_ENTER_CHAT));
                }
            }
        }*/
    }

    @EventHandler
    fun onBaseLeave(e: PlayerBaseLeaveEvent) {
        val team = e.team as BedWarsTeam
        if (team.isMember(e.player)) {
            // Remove effects for members
            for (pef in e.player.activePotionEffects) {
                for (pf in team.baseEffects) {
                    if (pef.type === pf.type) {
                        e.player.removePotionEffect(pf.type)
                    }
                }
            }
        } /* else {
            // Remove effects for enemies
            for (PotionEffect pef : e.getPlayer().getActivePotionEffects()) {
                for (BedWarsTeam.Effect pf : t.getEbseEffectsStatic()) {
                    if (pef.getType() == pf.getPotionEffectType()) {
                        e.getPlayer().removePotionEffect(pf.getPotionEffectType());
                    }
                }
            }
        }*/
    }

    @EventHandler
    fun onArenaLeave(e: PlayerLeaveArenaEvent) {
        isOnABase.remove(e.player)
    }


    /**
     * Check the Enter/ Leave events and call them
     */
    private fun checkEvents(player: Player, arena: IArena) {
        if (arena.isSpectator(player) || arena.isRespawning(player)) return

        var notOnBase = true
        for (bwt in arena.teams) {
            /* BaseEnterEvent */
            if (player.location.distance(bwt.bed) > arena.islandRadius) continue

            notOnBase = false
            if (player in isOnABase) {
                if (isOnABase[player] === bwt) continue
                Bukkit.getPluginManager().callEvent(PlayerBaseLeaveEvent(player, isOnABase[player]!!))
                if (player.uniqueId !in plugin.arenaManager.magicMilk) {
                    Bukkit.getPluginManager().callEvent(PlayerBaseEnterEvent(player, bwt))
                }
                isOnABase.replace(player, bwt)
                continue
            }

            if (player.uniqueId in plugin.arenaManager.magicMilk) continue
            Bukkit.getPluginManager().callEvent(PlayerBaseEnterEvent(player, bwt))
            isOnABase[player] = bwt
        }
        /* BaseLeaveEvent */
        if (!notOnBase || player !in isOnABase) return
        Bukkit.getPluginManager().callEvent(PlayerBaseLeaveEvent(player, isOnABase[player]!!))
        isOnABase.remove(player)
    }
    companion object {
        val isOnABase = WeakHashMap<Player, ITeam>()
    }
}
