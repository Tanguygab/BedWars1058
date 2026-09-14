package com.andrei1058.bedwars.commands.bedwars.subcmds

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.command.SubCommand
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import net.md_5.bungee.api.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class SubCommand(
    override val parent: MainCommand,
    name: String,
    permission: String? = null,
    /**
     * This is the command priority in the sub-commands list
     * You may use this method if you set showInList true
     * Commands with a minor number will be displayed first
     */
    priority: Int = 20
) : SubCommand(parent, name, permission, priority) {

    override val plugin = BedWars.INSTANCE

    override fun canSee(sender: CommandSender) = sender is Player &&
            !plugin.arenaManager.isInArena(sender) &&
            !SetupSession.isInSetupSession(sender.uniqueId) &&
            super.canSee(sender)

    protected fun CommandSender.sendMsg(message: String, error: Boolean = false) {
        sendMessage("${if (error) ChatColor.RED else ChatColor.GOLD}▪ ${ChatColor.GRAY}$message")
    }

    /**
     * Check if lobby location is set, else send a error message to the player
     */
    fun isLobbySet(sender: CommandSender): Boolean {
        if (plugin.serverType == ServerType.BUNGEE) return true
        if (plugin.mainConfig.lobbyWorldName.isEmpty()) {
            sender.sendMessage("§c▪ §7You have to set the lobby location first!")
            return false
        }
        return true
    }
}