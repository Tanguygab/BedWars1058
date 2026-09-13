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
package com.andrei1058.bedwars.shop.main

import com.andrei1058.bedwars.BedWars.Companion.debug
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.shop.IBuyItem
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.language.Language
import org.bukkit.Bukkit
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player

class BuyCommand(path: String, yml: YamlConfiguration, override val upgradeIdentifier: String) : IBuyItem {
    override val isLoaded = true
    override val itemStack = null
    override val isAutoEquip = false
    override val isPermanent = false
    override val isUnbreakable = false
    private val asPlayer = yml.getStringList("$path.as-player").map { if (it.startsWith("/")) it else "/$it" }
    private val asConsole = yml.getStringList("$path.as-console").map { it.removePrefix("/") }

    init {
        debug("Loading BuyCommand: $path")
    }

    override fun give(player: Player, arena: IArena) {
        debug("Giving BuyCMD: $upgradeIdentifier to: ${player.name}")
        val team = arena.getTeam(player)

        for (playerCmd in asPlayer) {
            player.chat(parsePlaceholders(playerCmd, player, team, arena))
        }
        for (consoleCmd in asConsole) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parsePlaceholders(consoleCmd, player, team, arena))
        }
    }

    fun parsePlaceholders(string: String, player: Player, team: ITeam?, arena: IArena) = string
        .replace("{player}", player.name)
        .replace("{player_uuid}", "${player.uniqueId}")
        .replace("{team}", team?.name ?: "null")
        .replace("{team_display}", team?.getDisplayName(Language.getLanguage(player)) ?: "null")
        .replace("{team_color}", team?.color?.chat.toString())
        .replace("{arena}", arena.name)
        .replace("{arena_world}", arena.worldName)
        .replace("{arena_display}", arena.displayName)
        .replace("{arena_group}", arena.group)
}
