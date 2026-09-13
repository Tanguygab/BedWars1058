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
package com.andrei1058.bedwars.commands.bedwars.subcmds.regular

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.command.SubCommand
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.Utils.teleportSafe
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdTpStaff : SubCommand("tp", "bw.tp", isShown = false) {

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return true
        if (args.size != 1) {
            sender.sendLangMsg(Messages.COMMAND_TP_USAGE)
            return true
        }

        if (!canUse(sender)) {
            sender.sendLangMsg(Messages.COMMAND_FORCESTART_NO_PERM)
            return true
        }

        val target = Bukkit.getPlayer(args[0])
        if (target == null) {
            sender.sendLangMsg(Messages.COMMAND_TP_PLAYER_NOT_FOUND)
            return true
        }

        val arenaManager = BedWars.plugin.arenaManager
        val targetArena = arenaManager.getArena(target)
        if (targetArena == null) {
            sender.sendLangMsg(Messages.COMMAND_TP_NOT_IN_ARENA)
            return true
        }

        val arena = arenaManager.getArena(sender)
        if (targetArena.status != GameState.PLAYING) {
            sender.sendLangMsg(Messages.COMMAND_TP_NOT_STARTED)
            return true
        }
        if (arena != null) {
            if (arena.isPlayer(sender)) arena.removePlayer(sender, false)
            if (arena.isSpectator(sender)) {
                if (arena.name == targetArena.name) {
                    sender.teleportSafe(target.location)
                    return true
                } else arena.removeSpectator(sender, false)
            }
        }
        targetArena.addSpectator(sender, false, target.location)

        return true
    }

    override val tabComplete get() = BedWars.plugin.arenaManager
        .arenas
        .values
        .flatMap { it.allPlayers }
        .map { it.name }
}
