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
package com.andrei1058.bedwars.api.server

import com.andrei1058.bedwars.api.configuration.ConfigManager
import org.bukkit.entity.Player

interface ISetupSession {
    /**
     * Get used world name.
     */
    val worldName: String

    /**
     * Get player doing the setup.
     */
    val player: Player

    /**
     * Get setup type.
     */
    val setupType: SetupType?

    /**
     * Get arena config.
     */
    val config: ConfigManager

    /**
     * Teleport player target world.
     */
    fun teleportPlayer()

    /**
     * Close setup session.
     */
    fun close()
}
