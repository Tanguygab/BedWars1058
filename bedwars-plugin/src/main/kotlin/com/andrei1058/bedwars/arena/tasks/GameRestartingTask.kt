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
package com.andrei1058.bedwars.arena.tasks

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.shop.ShopHolo
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.tasks.RestartingTask
import com.andrei1058.bedwars.arena.Arena
import com.andrei1058.bedwars.arena.Misc
import com.andrei1058.bedwars.configuration.Sounds
import com.andrei1058.bedwars.Utils.teleportSafe
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.potion.PotionEffectType
import kotlin.random.Random

class GameRestartingTask(override val arena: Arena) : Runnable, RestartingTask {
    override var restarting = BedWars.config.getInt(ConfigPath.GENERAL_CONFIGURATION_RESTART) + 5
        private set
    override val bukkitTask = Bukkit.getScheduler().runTaskTimer(BedWars.plugin, this, 0, 20L)

    init {
        Sounds.playSound("game-end", arena.allPlayers)

        // teleport to alive players
        if (arena.config.getGameOverridableBoolean(ConfigPath.GENERAL_GAME_END_TELEPORT_ELIMINATED) && arena.players.isNotEmpty()) {
            for (spectator in arena.spectators) {
                val target = arena.players[Random.nextInt(arena.players.size)]
                val loc = target.location.clone()
                loc.setDirection(loc.direction.multiply(-1))
                loc.add(0.0, 2.0, 0.0)

                spectator.teleportSafe(loc)
            }
        }

        // show eliminated players
        if (arena.config.getGameOverridableBoolean(ConfigPath.GENERAL_GAME_END_SHOW_ELIMINATED)) {
            for (spectator in arena.spectators) {
                arena.getExTeam(spectator.uniqueId) ?: continue
                spectator.removePotionEffect(PotionEffectType.INVISIBILITY)
                for (player in arena.players) {
                    BedWars.nms.showPlayer(player, spectator)
                    BedWars.nms.showPlayer(spectator, player)
                }
            }
        }
    }

    override fun run() {
        restarting--

        if (arena.players.isEmpty() && restarting > 9) restarting = 9
        when (restarting) {
            7 -> {
                val bungee = BedWars.serverType == ServerType.BUNGEE
                for (on in arena.players.toList()) {
                    arena.removePlayer(on, bungee)
                }
                for (on in arena.spectators.toList()) {
                    arena.removeSpectator(on, bungee)
                }
            }
            4 -> {
                ShopHolo.clearForArena(arena)
                for (e in arena.world.entities) {
                    val player = e as? Player ?: continue
                    Misc.moveToLobbyOrKick(player, arena)
                    if (arena.isSpectator(player)) arena.removeSpectator(player, false)
                    if (arena.isPlayer(player)) arena.removePlayer(player, false)
                }

                arena.teams
                    .flatMap { it.generators }
                    .plus(arena.oreGenerators)
                    .forEach { it.disable() }
            }
            0 -> {
                arena.restart()
                bukkitTask.cancel()
            }
        }
    }

    override fun cancel() {
        bukkitTask.cancel()
    }
}
