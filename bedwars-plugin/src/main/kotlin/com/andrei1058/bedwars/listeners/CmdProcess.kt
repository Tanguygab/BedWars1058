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
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.configuration.Permissions
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class CmdProcess(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onCmd(e: PlayerCommandPreprocessEvent) {
        val player = e.player
        val command = e.message

        if (command == "/party sethome" || command == "/party home") {
            player.sendLangMsg(Messages.COMMAND_NOT_ALLOWED_IN_GAME)
            e.isCancelled = true
        }

        if (player.hasPermission(Permissions.PERMISSION_COMMAND_BYPASS)) return

        val cmd = command.removePrefix("/").split(" ")
        if (cmd.isEmpty() || !plugin.arenaManager.isInArena(player)) return
        if (plugin.mainConfig.getStringList(ConfigPath.CENERAL_CONFIGURATION_ALLOWED_COMMANDS).contains(cmd[0])) return

        player.sendLangMsg(Messages.COMMAND_NOT_ALLOWED_IN_GAME)
        e.isCancelled = true
    }
}
