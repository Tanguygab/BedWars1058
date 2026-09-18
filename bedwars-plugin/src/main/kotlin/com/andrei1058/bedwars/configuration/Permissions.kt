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

import com.andrei1058.bedwars.BedWars
import org.bukkit.entity.Player

object Permissions {
    private const val BW = BedWars.MAIN_COMMAND
    const val PERMISSION_FORCESTART = "$BW.forcestart"
    const val PERMISSION_ALL = "$BW.*"
    const val PERMISSION_COMMAND_BYPASS = "$BW.cmd.bypass"
    const val PERMISSION_SHOUT_COMMAND = "$BW.shout"

    const val PERMISSION_SETUP_ARENA = "$BW.setup"
    const val PERMISSION_ARENA_GROUP = "$BW.groups"
    const val PERMISSION_BUILD = "$BW.build"
    const val PERMISSION_CLONE = "$BW.clone"
    const val PERMISSION_DEL_ARENA = "$BW.delete"
    const val PERMISSION_ARENA_ENABLE = "$BW.enableRotation"
    const val PERMISSION_ARENA_DISABLE = "$BW.disable"
    const val PERMISSION_NPC = "$BW.npc"
    const val PERMISSION_RELOAD = "$BW.reload"
    const val PERMISSION_REJOIN = "$BW.rejoin"
    const val PERMISSION_LEVEL = "$BW.level"
    const val PERMISSION_CHAT_COLOR = "$BW.chatcolor"
    const val PERMISSION_VIP = "$BW.vip"

    /**
     * Check if player has one of the given permissions.
     */
    fun hasPermission(player: Player, vararg permissions: String) = permissions.any { player.hasPermission(it) }
}
