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
package com.andrei1058.bedwars.api.command

import org.bukkit.entity.Player

interface ParentCommand {
    /**
     * Get parent name
     */
    val commandName: String

    /**
     * Check if a parent command has the target sub-command
     */
    fun hasSubCommand(name: String): Boolean

    /**
     * Add a subCommand
     */
    fun addSubCommand(subCommand: SubCommand)

    /**
     * Send sub-commands list to a player
     * This includes subCommands with showInList true only
     * He can see only commands which he have permission
     */
    fun sendSubCommands(player: Player)

    /**
     * Get available subCommands
     */
    val subCommands: List<SubCommand>
}
