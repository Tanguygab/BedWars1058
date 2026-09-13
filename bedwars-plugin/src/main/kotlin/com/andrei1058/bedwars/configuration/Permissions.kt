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
package com.andrei1058.bedwars.configuration

import com.andrei1058.bedwars.BedWars.Companion.MAIN_COMMAND
import org.bukkit.entity.Player

object Permissions {
    const val PERMISSION_FORCESTART = "$MAIN_COMMAND.forcestart"
    const val PERMISSION_ALL = "$MAIN_COMMAND.*"
    const val PERMISSION_COMMAND_BYPASS = "$MAIN_COMMAND.cmd.bypass"
    const val PERMISSION_SHOUT_COMMAND = "$MAIN_COMMAND.shout"

    const val PERMISSION_SETUP_ARENA = "$MAIN_COMMAND.setup"
    const val PERMISSION_ARENA_GROUP = "$MAIN_COMMAND.groups"
    const val PERMISSION_BUILD = "$MAIN_COMMAND.build"
    const val PERMISSION_CLONE = "$MAIN_COMMAND.clone"
    const val PERMISSION_DEL_ARENA = "$MAIN_COMMAND.delete"
    const val PERMISSION_ARENA_ENABLE = "$MAIN_COMMAND.enableRotation"
    const val PERMISSION_ARENA_DISABLE = "$MAIN_COMMAND.disable"
    const val PERMISSION_NPC = "$MAIN_COMMAND.npc"
    const val PERMISSION_RELOAD = "$MAIN_COMMAND.reload"
    const val PERMISSION_REJOIN = "$MAIN_COMMAND.rejoin"
    const val PERMISSION_LEVEL = "$MAIN_COMMAND.level"
    const val PERMISSION_CHAT_COLOR = "$MAIN_COMMAND.chatcolor"
    const val PERMISSION_VIP = "$MAIN_COMMAND.vip"

    /**
     * Check if player has one of the given permissions.
     */
    fun hasPermission(player: Player, vararg permissions: String) = permissions.any { player.hasPermission(it) }
}
