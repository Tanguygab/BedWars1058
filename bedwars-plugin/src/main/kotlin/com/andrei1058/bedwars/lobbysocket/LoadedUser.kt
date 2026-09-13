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
package com.andrei1058.bedwars.lobbysocket

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.debug
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import org.bukkit.Bukkit
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class LoadedUser(
    uuid: String,
    val arenaIdentifier: String,
    langIso: String,
    // if arena is started is used as staff teleport target
    val partyOwnerOrSpectateTarget: String?
) {
    val uuid: UUID = UUID.fromString(uuid)

    private val toleranceTime = System.currentTimeMillis() + waitSeconds
    val language = Language.getLang(langIso)

    init {
        if (Bukkit.getWorld(arenaIdentifier) != null) loaded[this.uuid] = this
    }

    val isTimedOut get() = System.currentTimeMillis() > toleranceTime

    fun destroy(reason: String) {
        debug("Destroyed PreLoaded User: $uuid Reason: $reason. Tolerance: $waitSeconds")
        loaded.remove(uuid)
    }

    companion object {
        private val waitSeconds = BedWars.config.getLong(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_BWP_TIME_OUT)

        val loaded = ConcurrentHashMap<UUID, LoadedUser>()

        fun getPreLoaded(uuid: UUID) = loaded[uuid]
    }
}
