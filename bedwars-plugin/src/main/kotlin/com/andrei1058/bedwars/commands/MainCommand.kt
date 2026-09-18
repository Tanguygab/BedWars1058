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
package com.andrei1058.bedwars.commands

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.api.command.SubCommand
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.subcmds.regular.*
import com.andrei1058.bedwars.commands.subcmds.sensitive.*
import com.andrei1058.bedwars.commands.subcmds.sensitive.setup.*
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.defaults.BukkitCommand
import org.bukkit.entity.Player

class MainCommand(private val plugin: BedWars, override val commandName: String) : BukkitCommand(commandName), ParentCommand {
    override val subCommands = mutableListOf<SubCommand>(
        Level(this),
        CmdList(this), //priority 20
        CmdStats(this),
        CmdLang(this),
        CmdJoin(this),
        CmdLeave(this),


        CmdTeleporter(this),
        CmdStart(this),

        SetupArena(this), //priority 2
        ArenaList(this), //priority 3
        DelArena(this), //priority 4
        EnableArena(this), //priority 5
        DisableArena(this), //priority 6
        CloneArena(this), //priority 7
        ArenaGroup(this), //priority 8
        Build(this), //priority 9

        Reload(this), //priority 11

        /* Arena setup commands (in world) */
        AutoCreateTeams(this),
        SetWaitingSpawn(this),
        SetSpectatorPos(this),
        CreateTeam(this),
        WaitingPos(this),
        RemoveTeam(this),
        SetMaxInTeam(this),
        SetBuildHeight(this),
        SetSpawn(this),
        SetBed(this),
        SetShop(this),
        SetUpgrade(this),
        AddGenerator(this),
        RemoveGenerator(this),
        SetType(this),
        SetKillDropsLoc(this),
        Save(this),

        CmdTpStaff(this),
        CmdUpgrades(this),

        NPCCommand(this)
    )

    init {
        INSTANCE = this
        aliases = listOf("bedwars", "bedwars1058")
        if (plugin.serverType != ServerType.BUNGEE) {
            subCommands += CmdGUI(this)
            subCommands += SetLobby(this) //priority 1
        }
        subCommands.sortBy { it.priority }
    }

    override fun execute(sender: CommandSender, label: String, args: Array<String>): Boolean {
        if (args.isNotEmpty()) {
            val command = getSubCommand(args[0])
            if (command == null || !command.canUse(sender) || !command.execute(args.copyOfRange(1, args.size), sender)) {
                sender.sendMessage(Language.getMsg(sender as? Player, Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS))
            }
            return true
        }

        /* Set op commands*/
        if (!sender.isOp && !sender.hasPermission("$name.*")) {
            if (sender is ConsoleCommandSender) {
                sender.sendMessage("§fNo console commands available atm.")
                return true
            }
            /* Send player commands */
            Bukkit.dispatchCommand(sender, "$name cmds")
            return true
        }

        if (sender is Player) {
            if (SetupSession.isInSetupSession(sender.uniqueId)) {
                Bukkit.dispatchCommand(sender, "$name cmds")
            } else {
                sender.sendMessage("\n§8§l$dot §6${plugin.description.name} v${plugin.description.version} §7- §c Admin Commands\n")
                sendSubCommands(sender)
            }
        } else {
            sender.sendMessage("§f   $name safemode §eenable/ disable")
        }
        return true
    }

    override fun addSubCommand(subCommand: SubCommand) {
        subCommands += subCommand
        subCommands.sortBy { it.priority }
    }

    override fun sendSubCommands(player: Player) = subCommands
        .filter { it.description != null && it.canSee(player) }
        .forEach { player.spigot().sendMessage(it.description) }

    @Throws(IllegalArgumentException::class)
    override fun tabComplete(
        sender: CommandSender,
        alias: String,
        args: Array<String>
    ): List<String> {
        val arg = args.getOrNull(0) ?: ""
        return when (args.size) {
            1 -> subCommands.filter { it.canSee(sender) }.map { it.name }
            2 if getSubCommand(arg)?.canSee(sender) == true -> getSubCommand(arg)!!.tabComplete
            else -> emptyList()
        }
    }


    /**
     * Get sub-command by name
     */
    fun getSubCommand(name: String) = subCommands.find { it.name.equals(name, ignoreCase = true) }

    override fun hasSubCommand(name: String) = getSubCommand(name) != null

    companion object {
        lateinit var INSTANCE: MainCommand
        /**
         * Get a dot symbol
         */
        /* Dot char */
        var dot = 254.toChar()

        fun isArenaGroup(name: String) = name.equals("default", ignoreCase = true) ||
                name in BedWars.INSTANCE.mainConfig.getStringList("arenaGroups")
    }
}