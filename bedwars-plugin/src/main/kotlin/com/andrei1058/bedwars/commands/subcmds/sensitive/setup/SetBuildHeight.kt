package com.andrei1058.bedwars.commands.subcmds.sensitive.setup

import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import org.bukkit.entity.Player

class SetBuildHeight(parent: MainCommand) : SetupCommand(parent, "setMaxBuildHeight") {
    /**
     * Add your sub-command code under this method
     * 
     * @param args
     * @param sender
     */
    override fun execute(args: Array<String>, sender: Player, session: SetupSession) {
        if (args.isNotEmpty()) {
            val y = args[0].toIntOrNull()
            if (y != null) {
                session.config.set("max-build-y", y)
                sender.sendMessage("§6 ▪ §7Max build height Y set to §e$y§7!")
                return
            }
        }
        sender.sendMessage("§c▪ §7Usage: /${parent.commandName} setMaxBuildHeight <int>")
    }

    override val tabComplete = listOf("180", "256")
}
