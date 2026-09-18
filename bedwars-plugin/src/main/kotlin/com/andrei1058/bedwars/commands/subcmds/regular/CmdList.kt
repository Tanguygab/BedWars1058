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
package com.andrei1058.bedwars.commands.subcmds.regular

import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.SetupType
import com.andrei1058.bedwars.api.util.Utils.message
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.commands.subcmds.SubCommand
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdList(parent: MainCommand) : SubCommand(parent, "cmds", priority = 11) {
    override val description = createDescription(
        "View player commands.",
        suffix = "view player cmds"
    )

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false

        if (SetupSession.isInSetupSession(sender.uniqueId)) {
            setupList(sender)
            return true
        }

        val arenas = plugin.arenaManager.arenas.size
        sender.message("${ChatColor.BLUE}${ChatColor.BOLD}${MainCommand.dot} " +
                "${ChatColor.GOLD}${plugin.name} " +
                "${ChatColor.GRAY}v${plugin.description.version} by andrei1058",
            "${ChatColor.GRAY}Arenas: ${if (arenas == 0) ChatColor.RED else ChatColor.GREEN}$arenas",
            plugin.description.website!!,
            ClickEvent.Action.OPEN_URL
        )
        sender.sendMessage(Language.getList(sender, Messages.COMMAND_MAIN).joinToString("\n"))
        return true
    }

    private fun setupList(player: Player) {
        val ss = SetupSession.getSession(player.uniqueId)!!

        val config = ss.config
        config.reload()

        val spawnNotSetNames = StringBuilder()
        val bedNotSet = StringBuilder()
        val shopNotSet = StringBuilder()
        val killDropsNotSet = StringBuilder()
        val upgradeNotSet = StringBuilder()
        val spawnNotSet = StringBuilder()
        val generatorNotSet = StringBuilder()
        var teams = 0

        val teamSection = config.getConfigurationSection("Team")
        if (teamSection != null) {
            for (team in teamSection.getKeys(true)) {
                val prefix = "Team.$team"
                if ("$prefix.Color" !in config) continue

                val color = TeamColor.getChatColor(config.getString("$prefix.Color")!!)
                val teamChar = "$color▋"
                if ("$prefix.Spawn" in config) {
                    spawnNotSet.append(teamChar)
                    spawnNotSetNames.append("$color$team ")
                }
                if ("$prefix.Bed" !in config) bedNotSet.append(teamChar)
                if ("$prefix.Shop" !in config) shopNotSet.append(teamChar)
                if ("$prefix.Upgrade" !in config) upgradeNotSet.append(teamChar)
                if ("$prefix${ConfigPath.ARENA_TEAM_KILL_DROPS_LOC}." !in config) killDropsNotSet.append(teamChar)
                if ("$prefix.Iron" !in config || "$prefix.Gold" !in config) generatorNotSet.append(teamChar)
                teams++
            }
        }

        val g2 = config.getString("group")
        val group = if (g2 == null || g2.equals("default", ignoreCase = true))
            "${ChatColor.RED}(NOT SET)"
        else "${ChatColor.GREEN}($g2)"

        val maxInTeam = ss.config.getInt("maxInTeam")

        val setSpectatorSpawn = getMessage("setSpectSpawn", ConfigPath.ARENA_SPEC_LOC in config)

        player.sendMessage(
            "\n${ChatColor.GRAY}${ChatColor.BOLD}${MainCommand.dot}${ChatColor.GOLD}${plugin.description.name} " +
                    "v${plugin.description.version}${ChatColor.GRAY}- " +
                    "${ChatColor.GREEN}${ss.worldName} commands"
        )

        player.sendComponent(
            getMessage("setWaitingSpawn", "waiting.Loc" in config),
            listOf(
                "Set the place where players have",
                "to wait before the game starts."
            ),
            "setWaitingSpawn",
            ss.setupType != SetupType.ASSISTED
        )

        val pos1 = "waiting.Pos1" in config
        val pos2 = "waiting.Pos2" in config
        player.sendComponent(
            "${if (pos1 && pos2) "${ChatColor.STRIKETHROUGH}" else ""}waitingPos 1/2${ChatColor.RESET} ${when {
                pos1 && pos2 -> "${ChatColor.GREEN}(SET)"
                pos1 -> "${ChatColor.RED}(POS 2 NOT SET)"
                pos2 -> "${ChatColor.RED}(POS 1 NOT SET)"
                else -> "${ChatColor.GRAY}(NOT SET) ${ChatColor.ITALIC}OPTIONAL"
            }}",
            listOf(
                "Make it so the waiting lobby will disappear at start.",
                "Select it as a world edit region."
            ),
            "waitingPos ",
        )
        if (ss.setupType == SetupType.ADVANCED) player.sendComponent(
            setSpectatorSpawn,
            "Set where to spawn spectators.",
            "setSpectSpawn",
            false
        )
        player.sendComponent(
            "autoCreateTeams ${ChatColor.YELLOW}(auto detect)",
            "Create teams based on islands colors.",
            "autoCreateTeams"
        )
        player.sendComponent(
            "createTeam <name> <color> ${ChatColor.YELLOW}($teams CREATED)",
            "Create a team.",
            "createTeam "
        )
        player.sendComponent(
            "removeTeam <name>",
            "Remove a team by name.",
            "removeTeam "
        )


        player.sendComponent(
            getMessage("setSpawn <teamName>", spawnNotSet),
            listOf(
                "Set a team spawn.",
                "Teams without a spawn set:",
                spawnNotSetNames.toString()
            ),
            "setSpawn "
        )
        player.sendComponent(
            getMessage("setBed", bedNotSet),
            listOf(
                "Set a team's bed location.",
                "You don't have to specify the team name."
            ),
            "setBed",
            ss.setupType != SetupType.ASSISTED
        )
        player.sendComponent(
            getMessage("setShop", shopNotSet),
            listOf(
                "Set a team's NPC.",
                "You don't have to specify the team name.",
                "It will be spawned only when the game starts."
            ),
            "setShop",
            ss.setupType != SetupType.ASSISTED
        )
        player.sendComponent(
            getMessage("setUpgrade", upgradeNotSet),
            listOf(
                "Set a team's upgrade NPC.",
                "You don't have to specify the team name.",
                "It will be spawned only when the game starts."
            ),
            "setUpgrade",
            ss.setupType != SetupType.ASSISTED
        )
        if (ss.setupType == SetupType.ADVANCED) player.sendComponent(
            getMessage("setKillDrops", killDropsNotSet),
            listOf(
                "Set a the location where to drop",
                "enemy items after you kill them."
            ),
            "setKillDrops "
        )

        val genHover = (if (ss.setupType == SetupType.ADVANCED) listOf(
            "Add a generator spawn point.",
            "${ChatColor.YELLOW}/${parent.commandName} addGenerator <Iron/ Gold/ Emerald, Diamond>"
        ) else listOf(
            "Add a generator spawn point.",
            "${ChatColor.YELLOW}Stay in on a team island to set a team generator"
        )) + listOf(
            "Stay on a diamond block to set the diamond generator.",
            "Stay on a emerald block to set an emerald generator."
        )

        player.sendComponent(
            getMessage("addGenerator", generatorNotSet) + "${ChatColor.YELLOW}(" +
                "${ChatColor.DARK_GREEN}E$${config.getStringList("generator.Emerald").size} " +
                "${ChatColor.AQUA}D${config.getStringList("generator.Diamond").size}" +
            "${ChatColor.YELLOW})",
            genHover,
            "addGenerator ",
            ss.setupType != SetupType.ASSISTED
        )
        player.sendComponent(
            "removeGenerator",
            genHover,
            "removeGenerator",
            ss.setupType != SetupType.ASSISTED
        )

        if (ss.setupType == SetupType.ADVANCED) {
            player.sendComponent(
                "setMaxInTeam <int> (IS SET TO $maxInTeam)",
                "Set the max team size.",
                "setMaxInTeam ",
            )
            player.sendComponent(
                "arenaGroup $group",
                "Set the arena group.",
                "arenaGroup ",
            )
        } else {
            player.sendComponent(
                "setType <type> $group",
                "Add the arena to a group.",
                "setType",
                false
            )
        }

        player.sendComponent(
            "save",
            "Save arena and go back to lobby",
            "save",
        )
    }

    private fun getMessage(command: String, set: Boolean): String {
        var msg = "$command "
        if (!set) msg = "${ChatColor.STRIKETHROUGH}$msg${ChatColor.RESET}${ChatColor.RED}(NOT SET)"
        else msg += "${ChatColor.GRAY}(SET)"
        return msg
    }
    private fun getMessage(command: String, remaining: StringBuilder, emptyCompleted: Boolean = false): String {
        var msg = "$command "
        if (remaining.isEmpty()) msg = if (emptyCompleted) msg else "${ChatColor.STRIKETHROUGH}$msg${ChatColor.RESET}${ChatColor.GREEN}(ALL SET)"
        else msg += "${ChatColor.RED}(Remaining: $remaining${ChatColor.RED})"
        return msg
    }

    val dot = "${ChatColor.BLUE} ▪ ${ChatColor.GRAY}/${parent.commandName} "
    private fun Player.sendComponent(message: String, hover: String, subCommand: String, suggest: Boolean = true)
    = sendComponent(message, listOf(hover), subCommand, suggest)
    private fun Player.sendComponent(message: String, hover: List<String>, subCommand: String, suggest: Boolean = true) = message(
        "$dot$message",
        hover.joinToString("\n") { "${ChatColor.WHITE}$it" },
        "/${parent.commandName} $subCommand",
        if (suggest) ClickEvent.Action.SUGGEST_COMMAND else ClickEvent.Action.RUN_COMMAND
    )
}
