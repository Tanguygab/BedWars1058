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
package com.andrei1058.bedwars.upgrades.upgradeaction

import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.upgrades.UpgradeAction
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class DispatchCommand(private val commandType: CommandType, private val command: String) : UpgradeAction {
    enum class CommandType {
        ONCE_AS_CONSOLE, FOREACH_MEMBER_AS_CONSOLE, FOREACH_MEMBER_AS_PLAYER;

        fun dispatch(team: ITeam, command: String) {
            var command = command.removePrefix("/")
            when (this) {
                ONCE_AS_CONSOLE -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command)
                FOREACH_MEMBER_AS_CONSOLE -> for (player in team.members) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command
                        .replace("{player}", player.name)
                        .replace("{player_uuid}", "${player.uniqueId}")
                    )
                }
                FOREACH_MEMBER_AS_PLAYER -> {
                    command = "/$command"
                    for (player in team.members) {
                        player.chat(command
                            .replace("{player}", player.name)
                            .replace("{player_uuid}", "${player.uniqueId}")
                        )
                    }
                }
            }
        }
    }


    override fun onBuy(player: Player, team: ITeam) {
        val arena = team.arena
        commandType.dispatch(team, command
            .replace("{buyer}", player.name)
            .replace("{buyer_uuid}", "${player.uniqueId}")
            .replace("{team}", team.name)
            .replace("{team_display}", team.getDisplayName(Language.defaultLanguage))
            .replace("{team_color}", "${team.color.chat}")
            .replace("{arena}", arena.name)
            .replace("{arena_world}", arena.worldName)
            .replace("{arena_display}", arena.displayName)
            .replace("{arena_group}", arena.group)
        )
    }
}
