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
package com.andrei1058.bedwars.arena

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.Utils.editMeta
import com.andrei1058.bedwars.Utils.message
import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.server.SetupSessionCloseEvent
import com.andrei1058.bedwars.api.events.server.SetupSessionStartEvent
import com.andrei1058.bedwars.api.server.ISetupSession
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.server.SetupType
import com.andrei1058.bedwars.commands.Misc.createArmorStand
import com.andrei1058.bedwars.commands.Misc.detectGenerators
import com.andrei1058.bedwars.configuration.ArenaConfig
import com.andrei1058.bedwars.Utils.teleportSafe
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.Location
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.UUID

class SetupSession(
    override val player: Player,
    override val worldName: String
) : ISetupSession {
    val prefix = "${ChatColor.GREEN}[$worldName${ChatColor.GREEN}] ${ChatColor.GOLD}"
    override var setupType: SetupType? = null
    override lateinit var config: ArenaConfig
    var isStarted = false
    var isAutoCreatedEmerald = false
    var isAutoCreatedDiamond = false
    val skipAutoCreateGen = mutableListOf<Location>()

    init {
        setupSessions.add(this)
        openGUI(player)
    }

    /**
     * Start setup session, loadStructure world etc
     * 
     * @return return is broken. do not use it.
     */
    fun startSetup(): Boolean {
        player.sendMessage("§6 ▪ §7Loading $worldName")
        config = ArenaConfig(BedWars.plugin, worldName, BedWars.plugin.dataFolder.path + "/Arenas")
        BedWars.api.restoreAdapter.onSetupSessionStart(this)
        return true
    }

    /**
     * Cancel setup
     */
    fun cancel() {
        setupSessions.remove(this)
        if (!isStarted) return
        player.sendMessage("§6 ▪ §7$worldName setup cancelled!")
        done()
    }

    override fun close() = cancel()

    /**
     * End setup session
     */
    fun done() {
        BedWars.api.restoreAdapter.onSetupSessionClose(this)
        setupSessions.remove(this)
        if (BedWars.serverType != ServerType.BUNGEE) {
            player.teleportSafe(BedWars.config.getConfigLoc("lobbyLoc") ?: Bukkit.getWorlds()[0].spawnLocation)
        }
        player.removePotionEffect(PotionEffectType.SPEED)
        if (BedWars.serverType == ServerType.MULTIARENA) BedWars.api.arenaManager.sendLobbyCommandItems(player)
        Bukkit.getPluginManager().callEvent(SetupSessionCloseEvent(this))
    }

    override fun teleportPlayer() {
        player.apply {
            inventory.clear()
            teleportSafe(Bukkit.getWorld(worldName)!!.spawnLocation)
            gameMode = GameMode.CREATIVE
            BedWars.plugin.run(delay = 5) {
                allowFlight = true
                isFlying = true
            }
            addPotionEffect(PotionEffect(PotionEffectType.SPEED, Int.MAX_VALUE, 2))
            sendMessage("\n${ChatColor.WHITE}\n")
            sendMessage("\n ".repeat(9))
            sendMessage("${ChatColor.GREEN}You were teleported to the ${ChatColor.GOLD}$worldName${ChatColor.GREEN}'s spawn.")
            if (setupType == SetupType.ASSISTED && "waiting.Loc" !in config) {
                sendMessage("")
                sendMessage("${ChatColor.GREEN}Hello $displayName!")
                sendMessage("${ChatColor.WHITE}Please set the waiting spawn.")
                sendMessage("${ChatColor.WHITE}It is the place where players will wait the game to start.")
                message(
                    "${ChatColor.BLUE}     ▪     ${ChatColor.GOLD}CLICK HERE TO SET THE WAITING LOBBY    ${ChatColor.BLUE} ▪",
                    "${ChatColor.LIGHT_PURPLE}Click to set the waiting spawn.",
                    "/${BedWars.MAIN_COMMAND} setWaitingSpawn",
                )
                message(
                    "${ChatColor.YELLOW}Or type: ${ChatColor.GRAY}/${BedWars.MAIN_COMMAND} to see the command list.",
                    "${ChatColor.WHITE}Show commands list.",
                    "/${BedWars.MAIN_COMMAND}",
                    ClickEvent.Action.SUGGEST_COMMAND
                )
            } else Bukkit.dispatchCommand(this, "${BedWars.MAIN_COMMAND} cmds")
        }


        val w = Bukkit.getWorld(worldName)
        val keptEntities = arrayOf(EntityType.PLAYER, EntityType.PAINTING, EntityType.ITEM_FRAME)
        BedWars.plugin.run(delay = 30) {
            w!!.entities.filter { it.type !in keptEntities }.forEach { it.remove() }
        }
        w!!.isAutoSave = false
        w.setGameRuleValue("doMobSpawning", "false")
        Bukkit.getPluginManager().callEvent(SetupSessionStartEvent(this))
        isStarted = true

        BedWars.plugin.run(delay = 90) {
            for (team in teams) {
                mapOf(
                    "Spawn" to "SPAWN SET",
                    "Bed" to "BED SET",
                    "Shop" to "SHOP SET",
                    "Upgrade" to "UPGRADE SET",
                    ConfigPath.ARENA_TEAM_KILL_DROPS_LOC to "${ChatColor.GOLD}Kill drops $team"
                ).filterKeys { "Team.$team.$it" in config }
                    .forEach { (type, text) ->
                        createArmorStand(
                            "${getTeamColor(team)} ${ChatColor.GOLD}$text",
                            config.getArenaLoc("Team.$team.$type")!!,
                            config.getString("Team.$team.$type"),
                        )
                    }

                for (gen in arrayOf("Iron", "Gold", "Emerald")) {
                    if (config.get("Team.$team.$gen") != null) {
                        for (loc in config.getStringList("Team.$team.Iron")) {
                            createArmorStand(
                                "${ChatColor.GOLD}$gen generator added for team: ${getColoredTeamName(team)}",
                                config.convertStringToArenaLocation(loc),
                                loc
                            )
                        }
                    }
                }
            }
            for (type in arrayOf("Emerald", "Diamond")) {
                if ("generator.$type" !in config) continue
                for (loc in config.getStringList("generator.$type")) {
                    createArmorStand(
                        "${ChatColor.GOLD}$type SET",
                        config.convertStringToArenaLocation(loc),
                        loc
                    )
                }
            }
        }
    }

    /**
     * Get a team color.
     *
     * @param team team name.
     * @return team color.
     */
    fun getTeamColor(team: String) = TeamColor.getChatColor(config.getString("Team.$team.Color")!!)
    fun getColoredTeamName(team: String) = getTeamColor(team).toString() + team

    val teams get() = config.getConfigurationSection("Team")?.getKeys(false) ?: emptyList()

    /**
     * Show available teams.
     */
    fun displayAvailableTeams() {
        val teams = teams
        player.sendMessage("${prefix}Available teams: ")
        for (team in teams) {
            player.sendMessage(prefix + getColoredTeamName(team))
        }
    }

    /**
     * Get nearest team name.
     *
     * @return empty if not found.
     */
    val nearestTeam: String
        get() {
            val radius = config.getInt(ConfigPath.ARENA_ISLAND_RADIUS)
            val team = teams
                .asSequence()
                .map { it to config.getArenaLoc("Team.$it.Spawn")?.distance(player.location) }
                .filter { it.second != null && it.second!! <= radius }
                .minBy { it.second!! }
                .first ?: ""
            return team
        }

    /**
     * Find and set generators
     */
    fun autoSetGen(p: Player, command: String?, setupSession: SetupSession, type: Material?) {
        if (type == Material.EMERALD_BLOCK) {
            if (setupSession.isAutoCreatedEmerald) return
            setupSession.isAutoCreatedEmerald = true
        } else {
            if (setupSession.isAutoCreatedDiamond) return
            setupSession.isAutoCreatedDiamond = true
        }
        detectGenerators(p.location.add(0.0, -1.0, 0.0).block.location, setupSession)
        BedWars.plugin.run(delay = 20) {
            for (location in setupSession.skipAutoCreateGen) {
                BedWars.plugin.run(delay = 20) {
                    p.teleportSafe(location)
                    val block = location.add(0.0, -1.0, 0.0).block
                    Bukkit.dispatchCommand(p, command + block.type.toString().substringBefore("_").lowercase())
                }
            }
        }
    }

    companion object {
        val setupSessions = mutableListOf<SetupSession>()

        /**
         * Gets the setup type gui inv name
         */
        const val INVENTORY_NAME = "§8Choose a setup method"

        /**
         * Get advanced type item slot
         */
        const val ADVANCED_SLOT = 5
        /**
         * Get assisted type item slot
         */

        const val ASSISTED_SLOT = 3

        private fun openGUI(player: Player) {
            val inv = Bukkit.createInventory(
                null, 9,
                INVENTORY_NAME
            )

            mapOf(
                ASSISTED_SLOT to Triple(Material.GLOWSTONE_DUST, "§e§lASSISTED", listOf(
                    "",
                    "§aEasy and quick setup!",
                    "§7For beginners and lazy staff :D",
                    "",
                    "§3Reduced options."
                )),
                ADVANCED_SLOT to Triple(Material.REDSTONE, "§c§lADVANCED", listOf(
                    "",
                    "§aDetailed setup!",
                    "§7For experienced staff :D",
                    "",
                    "§3Advanced options."
                ))
            ).forEach { (slot, meta) ->
                val item = ItemStack(meta.first)
                item.editMeta {
                    setDisplayName("${meta.second} SETUP")
                    lore = meta.third
                }
                inv.setItem(slot, item)
            }

            player.openInventory(inv)
        }

        /**
         * Check if a player is in setup session
         */
        fun isInSetupSession(player: UUID) = setupSessions.any { it.player.uniqueId == player }

        /**
         * Get a player session
         */
        fun getSession(player: UUID) = setupSessions.find { it.player.uniqueId == player }
    }
}
