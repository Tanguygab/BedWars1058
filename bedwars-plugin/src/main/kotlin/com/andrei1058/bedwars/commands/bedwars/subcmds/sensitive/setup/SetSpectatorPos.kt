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
package com.andrei1058.bedwars.commands.bedwars.subcmds.sensitive.setup

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.arena.SetupSession
import net.md_5.bungee.api.ChatColor
import org.bukkit.entity.Player

class SetSpectatorPos : SetupCommand("setSpectSpawn") {
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        sender.sendMessage(session.prefix + if (args.isEmpty()) {
            session.config.saveArenaLoc(ConfigPath.ARENA_SPEC_LOC, sender.location)
            "Spectator location set!"
        } else "${ChatColor.RED}Usage: /${BedWars.MAIN_COMMAND} $subCommandName")
    }
}
