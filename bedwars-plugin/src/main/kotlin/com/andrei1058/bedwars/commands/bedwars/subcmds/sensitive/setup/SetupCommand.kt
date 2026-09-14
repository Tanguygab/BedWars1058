package com.andrei1058.bedwars.commands.bedwars.subcmds.sensitive.setup

import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.bedwars.subcmds.SubCommand
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.configuration.Permissions
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class SetupCommand(parent: MainCommand, name: String) : SubCommand(parent, name, Permissions.PERMISSION_SETUP_ARENA) {

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        val session = SetupSession.getSession(sender.uniqueId) ?: return false
        execute(args, sender, session)
        return true
    }

    abstract fun execute(args: Array<String>, sender: Player, session: SetupSession)

    override fun canSee(sender: CommandSender): Boolean {
        return sender is Player && canUse(sender) && SetupSession.isInSetupSession(sender.uniqueId)
    }
}