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
package com.andrei1058.bedwars.commands.subcmds.sensitive.setup

import com.andrei1058.bedwars.api.server.SetupType
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class SetWaitingSpawn(parent: MainCommand) : SetupCommand(parent, "setWaitingSpawn") {
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        sender.sendMessage("§6 ▪ §7Waiting spawn set for §e${session.worldName}§7!")
        session.config.saveArenaLoc("waiting.Loc", sender.location)
        Bukkit.dispatchCommand(sender, parent.commandName + if (session.setupType == SetupType.ASSISTED) " autocreateteams" else "")
    }
}
