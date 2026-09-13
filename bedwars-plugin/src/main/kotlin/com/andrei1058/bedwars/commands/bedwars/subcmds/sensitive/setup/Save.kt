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
import com.andrei1058.bedwars.Utils.message
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.Utils.teleportSafe
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player

class Save(private val parent: ParentCommand) : SetupCommand("save") {
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        //Clear setup armor-stands
        for (e in sender.world.entities) {
            if (e.type == EntityType.ARMOR_STAND) {
                e.remove()
            }
        }

        sender.teleportSafe((Bukkit.getWorld(BedWars.lobbyWorld) ?: Bukkit.getWorlds()[0]).spawnLocation)
        session.done()
        sender.sendMessage("${session.prefix}Arena changes saved!")
        sender.sendMessage("${session.prefix}You can now enable it using:")
        sender.message(
            "${ChatColor.GOLD}/${parent.commandName} enableArena ${session.worldName}${ChatColor.GRAY} (click to enable)",
            "${ChatColor.GREEN}Enable this arena.",
            "/${parent.commandName} enableArena ${session.worldName}"
        )
    }
}
