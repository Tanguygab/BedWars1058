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

import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.util.Utils.message
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.bedwars.subcmds.SubCommand
import com.andrei1058.bedwars.configuration.ArenaConfig
import com.andrei1058.bedwars.configuration.Permissions
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.io.File

class ArenaGroup(parent: MainCommand) : SubCommand(
    parent,
    "arenaGroup",
    Permissions.PERMISSION_ARENA_GROUP,
    priority = 8
) {
    override val description = createDescription(
        "Manage arena groups.",
        suffix = "click for details",
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        if (!isLobbySet(sender)) return true

        val arg = if (args.isEmpty()) "" else args[0].lowercase()
        val groups = plugin.mainConfig.getStringList(ConfigPath.GENERAL_CONFIGURATION_ARENA_GROUPS)

        when (arg) {
            "create" -> {
                if (args.size < 2) {
                    sendArenaGroupCmdList(sender)
                    return true
                }
                val group = args[1]
                if ("+" in group) {
                    sender.sendMessage("§c▪ §7$group mustn't contain this symbol: ${ChatColor.RED}+")
                    return true
                }
                if (group in groups) {
                    sender.sendMessage("§c▪ §7This group already exists!")
                    return true
                }

                plugin.mainConfig[ConfigPath.GENERAL_CONFIGURATION_ARENA_GROUPS] = groups + group
                sender.sendMessage("§6 ▪ §7Group created!")
            }
            "remove" -> {
                if (args.size < 2) {
                    sendArenaGroupCmdList(sender)
                    return true
                }
                val group = args[1]
                if (group !in groups) {
                    sender.sendMessage("§c▪ §7This group doesn't exist!")
                    return true
                }
                plugin.mainConfig[ConfigPath.GENERAL_CONFIGURATION_ARENA_GROUPS] = groups - group
                sender.sendMessage("§6 ▪ §7Group deleted!")
            }
            "list" -> {
                sender.sendMessage("§7Available arena groups:")
                sender.sendMessage("§6 ▪ §fDefault")
                groups.forEach {  sender.sendMessage("§6 ▪ §f$it") }
            }
            "set" -> {
                if (args.size < 3) {
                    sendArenaGroupCmdList(sender)
                    return true
                }

                val arenaName = args[1]
                val group = args[2]

                if (group !in groups) {
                    sender.sendMessage("§6 ▪ §7There isn't any group called: $group")
                    Bukkit.dispatchCommand(sender, "/bw list")
                    return true
                }

                val arenaFile = File(plugin.dataFolder, "/Arenas/$arenaName.yml")
                if (!arenaFile.exists()) {
                    sender.sendMessage("§c▪ §7Arena $arenaName doesn't exist!")
                    return true
                }
                val cm = ArenaConfig(plugin, arenaName, plugin.dataFolder.path + "/Arenas")
                cm["group"] = group
                val arena = plugin.arenaManager.getArena(arenaName)
                if (arena != null) {
                    arena.group = group
                }
                sender.sendMessage("§6 ▪ §7$arena was added to the group: $group")
            }
            else -> sendArenaGroupCmdList(sender)
        }
        return true
    }

    override val tabComplete = listOf("create", "remove", "list", "set")

    private fun sendArenaGroupCmdList(sender: Player) {
        sender.sendCommand("create §o<groupName>", "Create an arena group. More details on our wiki.")
        sender.sendCommand("list", "View available groups.", ClickEvent.Action.RUN_COMMAND)
        sender.sendCommand("remove §o<groupName>", "Remove an arena group. More details on our wiki.")
        sender.sendCommand("set §o<arenaName> <groupName>", "Set the arena group. More details on our wiki.")
    }

    private fun Player.sendCommand(usage: String, description: String, action: ClickEvent.Action = ClickEvent.Action.SUGGEST_COMMAND) = message(
        "§6 ▪ §7/${parent.commandName} ${this@ArenaGroup.name} $usage",
        description,
        "/${parent.commandName} ${this@ArenaGroup.name} ${usage.split(" ", limit = 2).first()}",
        action
    )
}
