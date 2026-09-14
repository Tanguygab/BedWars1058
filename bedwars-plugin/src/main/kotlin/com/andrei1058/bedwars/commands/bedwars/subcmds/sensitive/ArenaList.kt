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
package com.andrei1058.bedwars.commands.bedwars.subcmds.sensitive

import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.arena.Misc
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.bedwars.subcmds.SubCommand
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import kotlin.math.min

class ArenaList(parent: MainCommand) : SubCommand(parent, "arenaList", priority = 3) {
    override val description: TextComponent get() {
        val arenas = Misc.getArenas().size
        return createDescription(
            "Show available arenas",
            status = "§${if (arenas == 0) "c" else "a"}($arenas set)",
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false

        var page = args.getOrNull(1)?.toIntOrNull()?.coerceAtLeast(1) ?: 1
        var start = (page - 1) * ARENAS_PER_PAGE
        val arenas = plugin.arenaManager.arenas.values.toList()
        if (arenas.size <= start) {
            page = 1
            start = 0
        }

        sender.sendMessage(" \n§1|| §3${plugin.name}§7 Instantiated games: \n ")

        if (arenas.isEmpty()) {
            sender.sendMessage("${ChatColor.RED}No arenas to display.")
            return true
        }

        val limit = min(arenas.size, start + ARENAS_PER_PAGE)

        arenas.subList(start, limit).forEach {
            val gameState = it.getDisplayStatus(Language.getLanguage(sender))
            val world = Bukkit.getWorld(it.worldName) != null
            val msg = "ID: §e${it.worldName} §fG: §e${it.getDisplayGroup(sender)} §fP: §e${it.allPlayers.size} §fS: $gameState §fWl: §e$world"
            sender.sendMessage(msg)
        }

        sender.sendMessage(" ")

        if (arenas.size <= ARENAS_PER_PAGE * page) return true
        sender.sendMessage("${ChatColor.GRAY}Type /${ChatColor.GREEN}${parent.commandName} arenaList ${++page}${ChatColor.GRAY} for next page.")
        return true
    }

    override fun canSee(sender: CommandSender): Boolean {
        if (sender is Player) {
            if (plugin.arenaManager.isInArena(sender)) return false

            if (SetupSession.isInSetupSession(sender.uniqueId)) return false
        }

        return canUse(sender)
    }

    companion object {
        private const val ARENAS_PER_PAGE = 10
    }
}
