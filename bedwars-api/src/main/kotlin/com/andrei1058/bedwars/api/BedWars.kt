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
package com.andrei1058.bedwars.api

import com.andrei1058.bedwars.api.arena.ArenaManager
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.api.levels.LevelManager
import com.andrei1058.bedwars.api.party.Party
import com.andrei1058.bedwars.api.server.ISetupSession
import com.andrei1058.bedwars.api.server.RestoreAdapter
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.server.VersionSupport
import com.andrei1058.bedwars.api.sidebar.ScoreboardManager
import org.bukkit.entity.Player
import java.io.File
import java.util.UUID

interface BedWars {
    companion object {
        var loaded = false
        lateinit var INSTANCE: BedWars
    }


    /**
     * Get stats utils.
     */
    val statsManager: StatsManager


    /**
     * Get afk system methods. It will only work if the game is started.
     */
    val afkManager: AFKManager

    val arenaManager: ArenaManager

    val configs: Configs

    /**
     * Get shop util.
     */
    val shopManager: ShopManager

    val upgradesManager: UpgradesManager

    /**
     * Get/Set level manager.
     */
    var levelManager: LevelManager

    /**
     * Get/Set the party interface.
     * You may need to unregister /party command yourself.
     */
    val partyUtil: Party

    /**
     * Get active setup session.
     * 
     * @param player player uuid.
     * @return null if no session was found.
     */
    fun getSetupSession(player: UUID): ISetupSession?

    /**
     * Check if a player is in setup session.
     */
    fun isInSetupSession(player: UUID): Boolean

    /**
     * Get server type.
     */
    val serverType: ServerType

    /**
     * Get the restore adapter.
     */
    @set:Throws(IllegalAccessError::class)
    var restoreAdapter: RestoreAdapter<*>

    /**
     * Get nms operations.
     */
    val versionSupport: VersionSupport

    /**
     * Get lobby world name.
     */
    val lobbyWorld: String

    val autoScale: Boolean

    val addonsPath: File

    val mainCommand: ParentCommand

    /**
     * Scoreboard options.
     */
    val scoreboardManager: ScoreboardManager


    val isShuttingDown: Boolean

    /**
     * Check if a player has vip perms
     */
    fun isVIP(player: Player): Boolean
}
