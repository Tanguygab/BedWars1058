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

import com.andrei1058.bedwars.api.events.player.PlayerXpGainEvent
import com.andrei1058.bedwars.api.util.Utils.message
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.bedwars.subcmds.SubCommand
import com.andrei1058.bedwars.configuration.Permissions
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Level(parent: MainCommand) : SubCommand(parent, "level", Permissions.PERMISSION_LEVEL, priority = 10) {
    override val description = createDescription(
        "Manage a player level.",
        suffix = "click for details"
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        val arg = if (args.isEmpty()) "" else args[0].lowercase()
        when (arg) {
            "setlevel" -> {
                if (args.size != 3) {
                    sender.sendMessage("${ChatColor.GOLD} ▪ ${ChatColor.GRAY}Usage: /bw level setLevel §o<player> <level>")
                    return true
                }

                val target = Bukkit.getPlayer(args[1])
                if (target == null) {
                    sender.sendMessage("${ChatColor.RED} ▪ ${ChatColor.GRAY}Player not found!")
                    return true
                }

                val level = args[2].toIntOrNull()
                if (level == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "Level must be an integer!")
                    return true
                }

                plugin.levelManager.setLevel(target, level)
                val config = plugin.levelsConfig

                val nextLevelCost = config.getInt("levels.$level.rankup-cost", config.getInt("levels.others.rankup-cost"))

                val levelName = config.getString("levels.$level.name") ?: config.getString("levels.others.name")


                plugin.run(async = true) {
                    plugin.database.setLevelData(target.uniqueId, level, 0, levelName, nextLevelCost)
                    sender.sendMessage("${ChatColor.GOLD} ▪ ${ChatColor.GRAY}${target.name} level was set to: $level")
                    sender.sendMessage("${ChatColor.GOLD} ▪ ${ChatColor.GRAY}The player may need to rejoin to see it updated.")
                }
            }
            "givexp" -> {
                if (args.size != 3) {
                    sender.sendMessage("${ChatColor.GOLD} ▪ ${ChatColor.GRAY}Usage: /bw level giveXp §o<player> <amount>")
                    return true
                }
                val target = Bukkit.getPlayer(args[1])
                if (target == null) {
                    sender.sendMessage("${ChatColor.RED} ▪ ${ChatColor.GRAY}Player not found!")
                    return true
                }

                val amount = args[2].toIntOrNull()

                if (amount == null) {
                    sender.sendMessage("${ChatColor.RED}Amount must be an integer!")
                    return true
                }

                plugin.levelManager.addXp(target, amount, PlayerXpGainEvent.XpSource.OTHER)

                plugin.run(async = true) {
                    val data = plugin.database.getLevelData(target.uniqueId)
                    plugin.database.setLevelData(
                        target.uniqueId,
                        (data[0] as Int?)!!,
                        (data[1] as Int?)!! + amount,
                        data[2] as String?,
                        (data[3] as Int?)!!
                    )
                    sender.sendMessage("${ChatColor.GOLD} ▪ ${ChatColor.GRAY}${amount} xp was given to: ${target.name}")
                    sender.sendMessage("${ChatColor.GOLD} ▪ ${ChatColor.GRAY}The player may need to rejoin to see it updated.")
                }
            }
            else -> {
                if (sender !is Player) {
                    sender.sendMessage("${ChatColor.GOLD}bw level setLevel <player> <level>")
                    sender.sendMessage("${ChatColor.GOLD}bw level giveXp <player> <amount>")
                    return true
                }
                sender.message(
                    "§6 ▪ §7/${parent.commandName} $name setLevel §o<player> <level>",
                    "Set a player level.", "/${parent.commandName} $name setLevel",
                    ClickEvent.Action.SUGGEST_COMMAND
                )
                sender.message(
                    "§6 ▪ §7/${parent.commandName} $name giveXp §o<player> <amount>",
                    "Give Xp to a player.", "/${parent.commandName} $name giveXp",
                    ClickEvent.Action.SUGGEST_COMMAND
                )
            }
        }
        return true
    }
}
