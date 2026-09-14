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
package com.andrei1058.bedwars.api.command

import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.util.Utils
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.CommandSender

abstract class SubCommand(
    protected open val parent: ParentCommand,
    val name: String,
    private val permission: String? = null,
    /**
     * This is the command priority in the sub-commands list
     * You may use this method if you set showInList true
     * Commands with a minor number will be displayed first
     */
    val priority: Int = 20
) {
    protected open val plugin = BedWars.INSTANCE
    /**
     * Get command description for subCommands list
     */
    /**
     * This is the command information in the subCommands list of the target parent
     */
    // Display name/ info in subCommands list
    open val description: TextComponent? = null

    private fun String.spaced(prefix: String = " ") = if (isEmpty()) "" else "$prefix$this"
    fun createDescription(
        description: String = "",
        syntax: String = "",
        status: String = "",
        suffix: String = "",
        argument: String = "",
        error: Boolean = false
    ): TextComponent {
        val suggest = syntax.isEmpty()
        val prefix = if (error) "§c▪ §7Usage: §e" else "§6 ▪ §7"
        return Utils.component(
            "$prefix/${parent.commandName} $name${argument.spaced()}${syntax.spaced("§6 ")}${status.spaced()}${suffix.spaced("         §8 - §e")}",
            "§f$description",
            "/${parent.commandName} $name${if (suggest) "" else " "}",
            if (suggest) ClickEvent.Action.SUGGEST_COMMAND else ClickEvent.Action.RUN_COMMAND
        )
    }

    /**
     * Check if player has permission to use the command
     */
    fun canUse(sender: CommandSender) = permission == null || sender.hasPermission("bw.*") || sender.hasPermission(permission)

    /**
     * Check if a sender can see/ use the sub cmd
     */
    open fun canSee(sender: CommandSender) = canUse(sender)

    /**
     * Add your sub-command code under this method
     */
    abstract fun execute(args: Array<String>, sender: CommandSender): Boolean

    /**
     * Manage sub-command tab complete
     */
    open val tabComplete = emptyList<String>()

}
