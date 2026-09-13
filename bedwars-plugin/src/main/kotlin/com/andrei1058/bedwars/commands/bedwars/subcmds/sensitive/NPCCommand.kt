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

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.command.ParentCommand
import com.andrei1058.bedwars.api.command.SubCommand
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.arena.Misc.msgHoverClick
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.configuration.Permissions
import com.andrei1058.bedwars.support.citizens.JoinNPC
import net.citizensnpcs.api.CitizensAPI
import net.citizensnpcs.api.npc.NPC
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.util.BlockIterator

class NPCCommand(parent: ParentCommand) : SubCommand("npc", Permissions.PERMISSION_NPC, priority = 12) {
    //main usage
    private val mainUsage = arrayOf(
        msgHoverClick(
            "§f\n§c▪ §7Usage: §e/${BedWars.MAIN_COMMAND} $subCommandName add",
            "§fUse this command to create a join NPC.\n§fClick to see the syntax.",
            "/${parent.commandName} $subCommandName add",
            ClickEvent.Action.RUN_COMMAND
        ),
        msgHoverClick(
            "§c▪ §7Usage: §e/${BedWars.MAIN_COMMAND} $subCommandName remove",
            "§fStay in front of a NPC in order to remove it.",
            "/${parent.commandName} $subCommandName remove",
            ClickEvent.Action.SUGGEST_COMMAND
        )
    )
    private val addUsage = arrayOf(
        msgHoverClick(
            "f\n§c▪ §7Usage: §e§o/${parent.commandName} $subCommandName add <skin> <arenaGroup> <§7line1§9\\n§7line2§e>\n§7You can use §e{players} §7for the players count in this arena §7group.",
            "Click to use.",
            "/${parent.commandName} $subCommandName add",
            ClickEvent.Action.SUGGEST_COMMAND
        )
    )

    init {
        displayInfo = msgHoverClick(
            "§6 ▪ §7/${parent.commandName} $subCommandName         §8   - §ecreate a join NPC",
            "§fCreate a join NPC  \n§fClick for more details.",
            "/${parent.commandName} $subCommandName",
            ClickEvent.Action.RUN_COMMAND
        )
    }

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player || !JoinNPC.isCitizensSupport) return false
        when (args.getOrNull(0)) {
            "add" -> {
                if (args.size < 4) {
                    sender.spigot().sendMessage(*addUsage)
                    return true
                }
                val npcs = if (BedWars.config.get(ConfigPath.GENERAL_CONFIGURATION_NPC_LOC_STORAGE) == null) mutableListOf()
                else BedWars.config.getStringList(ConfigPath.GENERAL_CONFIGURATION_NPC_LOC_STORAGE).toMutableList()

                val name = args.joinToString(" ").replace("${args[0]} ${args[1]} ${args[2]} ", "")
                val npc = JoinNPC.spawnNPC(sender.location, name, args[2], args[1], null)
                npcs.add("${BedWars.config.stringLocationConfigFormat(sender.location)},${args[1]},$name,${args[2]},${npc.id}")

                sender.sendMessage("§a§c▪ §bNPC: ${name.replace("&", "§").replace("\\n", " ")} §bwas set!")
                sender.sendMessage("§a§c▪ §bTarget groups: " + ChatColor.GOLD + args[2])
                BedWars.config.set(ConfigPath.GENERAL_CONFIGURATION_NPC_LOC_STORAGE, npcs)
            }
            "remove" -> {
                val e = sender.getNearbyEntities(4.0, 4.0, 4.0)
                val noNPCsMsg = "§c▪ §bThere aren't any NPCs nearby."
                if (e.isEmpty()) {
                    sender.sendMessage(noNPCsMsg)
                    return true
                }
                if (BedWars.config.get(ConfigPath.GENERAL_CONFIGURATION_NPC_LOC_STORAGE) == null) {
                    sender.sendMessage("§c▪ §bThere aren't any NPCs set yet!")
                    return true
                }
                val npc = getTarget(sender)
                if (npc == null) {
                    sender.sendMessage(noNPCsMsg)
                    return true
                }

                val locations = BedWars.config
                    .getStringList(ConfigPath.GENERAL_CONFIGURATION_NPC_LOC_STORAGE)
                    .filter { !it.split(",")[4].equals(npc.id.toString(), ignoreCase = true) }
                JoinNPC.npcs.remove(npc.id)
                npc.entity.getNearbyEntities(.0, 3.0, .0).forEach {
                    if (it.type == EntityType.ARMOR_STAND) it.remove()
                }
                BedWars.config.set(ConfigPath.GENERAL_CONFIGURATION_NPC_LOC_STORAGE, locations)
                npc.destroy()
                sender.sendMessage("§c▪ §bThe target NPC was removed!")
            }
            else -> sender.spigot().sendMessage(*mainUsage)
        }
        return true
    }

    override val tabComplete = listOf("remove", "add")


    override fun canSee(sender: CommandSender, api: com.andrei1058.bedwars.api.BedWars): Boolean {
        if (sender !is Player || !JoinNPC.isCitizensSupport) return false

        if (api.arenaManager.isInArena(sender)) return false

        if (SetupSession.isInSetupSession(sender.uniqueId)) return false
        return canUse(sender)
    }

    companion object {
        /**
         * Create an armor-stand hologram
         */
        fun createArmorStand(loc: Location) = loc.world!!.spawn(loc, ArmorStand::class.java).apply {
            setGravity(false)
            isVisible = false
            isCustomNameVisible = false
            isMarker = true
        }

        /**
         * Get target NPC
         */
        fun getTarget(player: Player): NPC? {
            BlockIterator(player.world, player.location.toVector(), player.eyeLocation.direction, .0, 100).forEach {
                for (entity in player.getNearbyEntities(100.0, 100.0, 100.0)) {
                    val acc = 2
                    for (x in -acc..<acc) {
                        for (z in -acc..<acc) {
                            for (y in -acc..<acc) {
                                if (entity.location.block.getRelative(x, y, z) != it || !entity.hasMetadata("NPC")) continue
                                return CitizensAPI.getNPCRegistry().getNPC(entity) ?: continue
                            }
                        }
                    }
                }
            }
            return null
        }
    }
}
