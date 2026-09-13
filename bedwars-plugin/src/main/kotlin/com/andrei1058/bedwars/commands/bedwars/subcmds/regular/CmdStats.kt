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

import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.arena.Misc
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.bedwars.subcmds.CooldownCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdStats(parent: ParentCommand) : CooldownCommand("stats", 3000, isShown = false, priority = 16) {
    init {
        displayInfo = createTC(
            "§6 ▪ §7/${parent.commandName} $subCommandName",
            "/${parent.commandName} $subCommandName",
            "§fOpens the stats GUI."
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        val arena = BedWars.INSTANCE.arenaManager.getArena(sender)
        if (arena != null && arena.status != GameState.STARTING && arena.status != GameState.WAITING && !arena.isSpectator(sender)) {
            return false
        }
        if (isOnCooldown(sender.uniqueId)) return true
        setCooldown(sender.uniqueId)
        Misc.openStatsGUI(sender)
        return true
    }

    override fun canSee(sender: CommandSender, api: BedWars): Boolean {
        if (sender !is Player) return false

        if (api.arenaManager.isInArena(sender)) return false

        if (SetupSession.isInSetupSession(sender.uniqueId)) return false
        return canUse(sender)
    }
}
