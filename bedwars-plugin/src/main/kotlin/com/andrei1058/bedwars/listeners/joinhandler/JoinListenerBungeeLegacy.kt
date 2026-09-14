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
package com.andrei1058.bedwars.listeners.joinhandler

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.arena.ReJoin
import com.andrei1058.bedwars.configuration.Permissions
import com.andrei1058.bedwars.configuration.Sounds.playSound
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent

class JoinListenerBungeeLegacy(private val plugin: BedWars) : Listener {
    @EventHandler(priority = EventPriority.HIGH)
    fun onLogin(e: PlayerLoginEvent) {
        val p = e.getPlayer()

        // Do not allow login if the arena wasn't loaded yet
        if (plugin.arenaManager.arenas.isEmpty()) {
            if (plugin.arenaManager.enableQueue.isNotEmpty()) {
                e.disallow(
                    PlayerLoginEvent.Result.KICK_WHITELIST,
                    Language.getMsg(e.getPlayer(), Messages.ARENA_STATUS_RESTARTING_NAME)
                )
                return
            }
        }

        // Check if there is an arena to rejoin
        val reJoin = ReJoin.getPlayer(p)
        if (reJoin != null) {
            // If is not allowed to rejoin
            if (!(p.hasPermission(Permissions.PERMISSION_REJOIN) || reJoin.canReJoin())) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, Language.defaultLanguage.m(Messages.REJOIN_DENIED))
                reJoin.destroy(true)
            }
            // Stop here, rejoin handled. More will be handled at PlayerJoinEvent
            return
        }

        val arena = plugin.arenaManager.arenas.values.firstOrNull() ?: return
        // Player logic

        if (arena.status == GameState.PLAYING) {
            // Spectator logic
            if (!arena.isAllowSpectate) {
                e.disallow(
                    PlayerLoginEvent.Result.KICK_OTHER,
                    Language.defaultLanguage.m(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG)
                )
            }
            return
        }

        if (arena.status != GameState.WAITING && !(arena.status == GameState.STARTING && arena.startingTask!!.countdown > 1)) {
            e.disallow(
                PlayerLoginEvent.Result.KICK_OTHER,
                Language.defaultLanguage.m(Messages.ARENA_STATUS_RESTARTING_NAME)
            )
            return
        }

        // If arena is full
        if (arena.players.size >= arena.maxPlayers) {
            // Vip join feature
            if (plugin.isVIP(p)) {
                var canJoin = false
                for (inGame in arena.players) {
                    if (!plugin.isVIP(inGame)) {
                        canJoin = true
                        inGame.kickPlayer(Language.getMsg(inGame, Messages.ARENA_JOIN_VIP_KICK))
                        break
                    }
                }
                if (!canJoin) {
                    e.disallow(
                        PlayerLoginEvent.Result.KICK_FULL,
                        Language.defaultLanguage.m(Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS)
                    )
                }
            } else {
                e.disallow(
                    PlayerLoginEvent.Result.KICK_OTHER,
                    Language.getMsg(e.getPlayer(), Messages.COMMAND_JOIN_DENIED_IS_FULL)
                )
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onJoin(e: PlayerJoinEvent) {
        e.joinMessage = null
        val player = e.player

        val arenaManager = plugin.arenaManager
        // Do not allow login if the arena wasn't loaded yet
        // I know this code is already in the login event but other plugins may allow login
        if (arenaManager.arenas.isEmpty() && arenaManager.enableQueue.isNotEmpty()) {
            player.kickPlayer(Language.getMsg(player, Messages.ARENA_STATUS_RESTARTING_NAME))
            return
        }

        JoinHandlerCommon.displayCustomerDetails(player)

        if (arenaManager.arenas.isEmpty()) {
            // Show setup commands if there is no arena available
            if (player.hasPermission("bw.setup")) {
                player.performCommand(plugin.mainCommand.name)
            }
            return
        }

        val arena = arenaManager.arenas.values.first()
        // Add player if the game is in waiting
        if (arena.status.isPreGame()) {
            if (arena.addPlayer(player, false)) playSound("join-allowed", player)
            else player.kickPlayer(Language.getMsg(player, Messages.COMMAND_JOIN_DENIED_IS_FULL))
            return
        }

        // Check ReJoin
        val reJoin = ReJoin.getPlayer(player)
        if (reJoin != null) {
            if (reJoin.canReJoin()) {
                reJoin.reJoin(player)
                reJoin.destroy(false)
                return
            }
            player.sendMessage(Language.getMsg(player, Messages.REJOIN_DENIED))
            reJoin.destroy(true)
        }

        // Add spectator
        if (arena.addSpectator(player, false, null)) {
            playSound("spectate-allowed", player)
            return
        }
        player.kickPlayer(Language.getMsg(player, Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG))
    }
}

