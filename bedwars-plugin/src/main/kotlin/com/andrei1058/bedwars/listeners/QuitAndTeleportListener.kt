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
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.arena.data.LastHit
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.arena.team.BedWarsTeam
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.commands.subcmds.CooldownCommand
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerQuitEvent

class QuitAndTeleportListener(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onLeave(e: PlayerQuitEvent) {
        val player = e.player
        // Remove from arena
        plugin.arenaManager.getArena(player)?.remove(player)

        //Save preferred language
        val uuid = player.uniqueId
        if (uuid in Language.langByPlayer) {
            plugin.run(async = true) {
                var iso = Language.langByPlayer[uuid]!!.iso
                if (Language.isLanguageExist(iso)) {
                    if (iso in plugin.mainConfig.getStringList(ConfigPath.GENERAL_CONFIGURATION_DISABLED_LANGUAGES))
                        iso = Language.defaultLanguage.iso
                    plugin.database.setLanguage(uuid, iso)
                }
                Language.langByPlayer.remove(uuid)
            }
        }

        if (plugin.serverType != ServerType.SHARED) {
            e.quitMessage = null
        }
        // Manage internal parties
        val party = plugin.partyUtil
        if (party.isInternal) {
            if (party.hasParty(player)) {
                party.removeFromParty(player)
            }
        }
        // Check if was doing a setup and remove the session
        val ss = SetupSession.getSession(uuid)
        ss?.cancel()

        plugin.scoreboardManager.remove(player)

        BedWarsTeam.reSpawnInvulnerability.remove(uuid)

        LastHit.getLastHit(player)?.remove()

        MainCommand.INSTANCE.subCommands
            .filterIsInstance<CooldownCommand>()
            .forEach { it.removeCooldown(uuid) }
    }

    /**
     * Handle players teleported outside.
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onWorldChange(e: PlayerChangedWorldEvent) {
        // if player was teleported outside arena

        val player = e.player
        val arena = plugin.arenaManager.getArena(player) ?: return

        if (player.world.name == arena.worldName) return

        // it will teleport you to the lobby world or cached location
        arena.remove(player)
    }

    fun IArena.remove(player: Player) {
        if (isPlayer(player)) removePlayer(player, false)
        if (isSpectator(player)) removeSpectator(player, false)
    }
}
