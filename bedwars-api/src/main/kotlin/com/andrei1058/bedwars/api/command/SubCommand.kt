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
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.CommandSender

abstract class SubCommand(
    val subCommandName: String,
    private val permission: String? = null,
    val isShown: Boolean = true,
    /**
     * This is the command priority in the sub-commands list
     * You may use this method if you set showInList true
     * Commands with a minor number will be displayed first
     */
    val priority: Int = 20
) {
    /**
     * Get command description for subCommands list
     */
    /**
     * This is the command information in the subCommands list of the target parent
     */
    // Display name/ info in subCommands list
    var displayInfo: TextComponent? = null

    /**
     * Check if player has permission to use the command
     */
    fun canUse(sender: CommandSender) = permission == null || sender.hasPermission("bw.*") || sender.hasPermission(permission)

    /**
     * Check if a sender can see/ use the sub cmd
     *
     * @param api BedWars api instance
     */
    open fun canSee(sender: CommandSender, api: BedWars) = canUse(sender)

    /**
     * Add your sub-command code under this method
     */
    abstract fun execute(args: Array<String>, sender: CommandSender): Boolean

    /**
     * Manage sub-command tab complete
     */
    open val tabComplete = emptyList<String>()

    fun createTC(text: String, suggest: String, hover: String) = TextComponent(text).apply {
        clickEvent = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, suggest)
        hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(hover).create())
    }

    protected fun CommandSender.sendMsg(message: String, error: Boolean = false) {
        sendMessage("${if (error) ChatColor.RED else ChatColor.GOLD}▪ ${ChatColor.GRAY}$message")
    }

}
