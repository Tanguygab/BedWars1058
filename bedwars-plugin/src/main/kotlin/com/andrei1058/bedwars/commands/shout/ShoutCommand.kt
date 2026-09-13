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
package com.andrei1058.bedwars.commands.shout

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import org.bukkit.command.CommandSender
import org.bukkit.command.defaults.BukkitCommand
import org.bukkit.entity.Player
import java.util.UUID

class ShoutCommand(name: String) : BukkitCommand(name) {
    override fun execute(sender: CommandSender, label: String, args: Array<String>): Boolean {
        if (sender !is Player) return true

        val arena = BedWars.api.arenaManager.getArena(sender)
        if (arena == null || arena.isSpectator(sender)) {
            sender.sendLangMsg(Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS)
            return true
        }

        sender.chat(args.joinToString(" ", prefix = "!"))
        return false
    }

    companion object {
        private val shoutCooldown = mutableMapOf<UUID, Long>()

        fun updateShout(player: Player) {
            if (player.hasPermission("bw.shout.bypass")) return
            shoutCooldown[player.uniqueId] = System.currentTimeMillis() + BedWars.config.getInt(ConfigPath.GENERAL_CONFIGURATION_SHOUT_COOLDOWN) * 1000L
        }

        fun isShoutCooldown(player: Player): Boolean {
            if (player.hasPermission("bw.shout.bypass")) return false
            if (player.uniqueId !in shoutCooldown) return false
            return shoutCooldown[player.uniqueId]!! > System.currentTimeMillis()
        }

        fun getShoutCooldown(p: Player) = ((shoutCooldown[p.uniqueId]!! - System.currentTimeMillis()) / 1000f).toDouble()

        fun isShouting(p: Player) = p.uniqueId in shoutCooldown && shoutCooldown[p.uniqueId]!! + 1000 > System.currentTimeMillis()
    }
}
