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
import com.andrei1058.bedwars.configuration.Sounds
import com.andrei1058.bedwars.lobbysocket.LoadedUser
import com.andrei1058.bedwars.Utils.teleportSafe
import com.andrei1058.bedwars.support.preloadedparty.PreLoadedParty
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerTeleportEvent

class JoinListenerBungee(val plugin: BedWars) : Listener {
    @EventHandler
    fun onLogin(e: PlayerLoginEvent) {
        val player = e.player

        val proxyUser = LoadedUser.getPreLoaded(player.uniqueId)

        // If NOT logging in trough BedWarsProxy
        if (proxyUser == null) {
            if (!player.hasPermission("bw.setup")) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, Language.getMsg(player, Messages.ARENA_JOIN_DENIED_NO_PROXY))
            }
            return
        }

        // If logging in trough BedWarsProxy
        val lang = proxyUser.language

        // Check if there is an arena to rejoin
        val reJoin = ReJoin.getPlayer(player)
        if (reJoin != null) {
            // If is not allowed to rejoin
            if (!player.hasPermission(Permissions.PERMISSION_REJOIN) && !reJoin.canReJoin()) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, lang.m(Messages.REJOIN_DENIED))
                reJoin.destroy(true)
            }
            // Stop here, rejoin handled. More will be handled at PlayerJoinEvent
            return
        }

        val arena = plugin.arenaManager.getArenaByWorld(proxyUser.arenaIdentifier)
        val status = arena?.status

        // check if arena is not available, time out etc.
        if (arena == null || proxyUser.isTimedOut || status == GameState.RESTARTING) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, lang.m(Messages.ARENA_STATUS_RESTARTING_NAME))
            proxyUser.destroy("Time out or game unavailable at PlayerLoginEvent")
            return
        }

        // Player logic
        when (status) {
            // Vip join/kick feature
            GameState.STARTING, GameState.WAITING -> if (arena.players.size >= arena.maxPlayers && BedWars.api.isVIP(player)) {
                var canJoin = false
                for (inGame in arena.players) {
                    if (!BedWars.api.isVIP(inGame)) {
                        canJoin = true
                        inGame.kickPlayer(Language.getMsg(inGame, Messages.ARENA_JOIN_VIP_KICK))
                        break
                    }
                }
                if (!canJoin) e.disallow(
                    PlayerLoginEvent.Result.KICK_FULL,
                    lang.m(Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS)
                )
            }

            // Spectator logic
            GameState.PLAYING -> if (!arena.isAllowSpectate) {
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, lang.m(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG))
            }

            else -> throw IllegalStateException("Unhandled game status!")
        }
    }

    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        e.joinMessage = null
        val player = e.player

        val proxyUser = LoadedUser.getPreLoaded(player.uniqueId)

        // If didn't join trough BedWarsProxy
        if (proxyUser == null) {
            // If is an admin let him in to do the setup
            if (player.hasPermission("bw.setup")) {
                JoinHandlerCommon.displayCustomerDetails(player)
                plugin.server.dispatchCommand(player, "bw")
                val mainWorld = plugin.server.worlds[0]
                if (mainWorld != null) {
                    player.teleportSafe(mainWorld.spawnLocation)
                }
                // hide admin to in game users
                for (inGame in plugin.server.onlinePlayers) {
                    if (inGame == player) continue
                    if (plugin.arenaManager.isInArena(inGame)) {
                        BedWars.nms.hidePlayer(player, inGame)
                        BedWars.nms.hidePlayer(inGame, player)
                    }
                }
            } else {
                // The player is not an admin and he joined using /server or equivalent
                player.kickPlayer(Language.getMsg(player, Messages.ARENA_JOIN_DENIED_NO_PROXY))
            }
            return
        }

        // The player joined using BedWarsProxy
        val playerLang = proxyUser.language

        // Check if has an arena to ReJoin
        val reJoin = ReJoin.getPlayer(player)
        if (reJoin != null) {
            // Check if can re-join
            if (reJoin.canReJoin()) {
                JoinHandlerCommon.displayCustomerDetails(player)
                reJoin.reJoin(player)
                // Cache player language
                Language.setPlayerLanguage(player.uniqueId, playerLang.iso)
            } else player.kickPlayer(playerLang.m(Messages.REJOIN_DENIED))
            // ReJoin handled, stop here
            proxyUser.destroy("Rejoin handled. PreLoaded user no longer needed.")
            return
        }

        // There's nothing to re-join, so he might want to join an arena
        val arena = plugin.arenaManager.getArenaByWorld(proxyUser.arenaIdentifier)
        val status = arena?.status

        // Check if the arena is still available or request time-out etc.
        if (arena == null || proxyUser.isTimedOut || status == GameState.RESTARTING) {
            player.kickPlayer(playerLang.m(Messages.ARENA_STATUS_RESTARTING_NAME))
            proxyUser.destroy("Time out or game unavailable at PlayerLoginEvent")
            return
        }

        // Join allowed, cache player language
        Language.setPlayerLanguage(player.uniqueId, playerLang.iso)
        JoinHandlerCommon.displayCustomerDetails(player)

        // Join as player
        when (status) {
            GameState.STARTING, GameState.WAITING -> {
                Sounds.playSound("join-allowed", player)

                // If has no party
                if (proxyUser.partyOwnerOrSpectateTarget == null) {
                    // Add to arena
                    if (!arena.addPlayer(player, true)) {
                        player.kickPlayer(Language.getMsg(player, Messages.ARENA_JOIN_DENIED_NO_PROXY))
                    }
                } else {
                    // If is member or owner of a remote party

                    val partyOwner = plugin.server.getPlayer(proxyUser.partyOwnerOrSpectateTarget)
                    // If party owner is connected
                    if (partyOwner?.isOnline == true) {
                        // If joiner is the party owner create the party
                        if (partyOwner == player) {
                            BedWars.party.createParty(player)

                            // Handle to-be-teamed-up players. A list used if some party members join before the party owner.
                            PreLoadedParty.getPartyByOwner(partyOwner.name)?.teamUp()
                        } else {
                            // Add to a existing party
                            BedWars.party.addMember(partyOwner, player)
                        }
                    } else {
                        // If a party member joined before the party owner create a waiting list
                        // to-be-teamed-up players, when the owner will join
                        val preLoadedParty = PreLoadedParty.getPartyByOwner(proxyUser.partyOwnerOrSpectateTarget)
                            ?: PreLoadedParty(proxyUser.partyOwnerOrSpectateTarget)
                        preLoadedParty += player
                    }
                    if (!arena.addPlayer(player, true)) {
                        player.kickPlayer(Language.getMsg(player, Messages.ARENA_JOIN_DENIED_NO_PROXY))
                    }
                }
            }

            GameState.PLAYING -> {
                // Join as spectator
                Sounds.playSound("spectate-allowed", player)
                val spectatorTarget = proxyUser.partyOwnerOrSpectateTarget?.let { plugin.server.getPlayer(it) }?.location
                arena.addSpectator(player, false, spectatorTarget)
            }

            else -> throw IllegalStateException("Unhandled game status!")
        }
        proxyUser.destroy("Joined as player or spectator. PreLoaded user no longer needed.")
    }
}

