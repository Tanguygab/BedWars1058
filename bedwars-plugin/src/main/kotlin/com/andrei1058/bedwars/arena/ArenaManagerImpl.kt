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
import com.andrei1058.bedwars.api.arena.ArenaManager
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.configuration.ArenaConfig
import com.andrei1058.bedwars.support.papi.SupportPAPI
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import java.io.File
import java.time.LocalDateTime
import java.util.UUID

class ArenaManagerImpl(private val plugin: BedWars) : ArenaManager {
    override val arenas = mutableMapOf<String, IArena>()

    override var gamesBeforeRestart = plugin.mainConfig.getInt(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_MODE_GAMES_BEFORE_RESTART)
    var magicMilk = mutableMapOf<UUID, Int>()

    /**
     * Player location before joining.
     * The player is teleported to this location if the server is running in SHARED mode.
     */
    val playerLocation = mutableMapOf<Player, Location>()
    override val enableQueue = mutableListOf<IArena>()

    fun load() {
        plugin.restoreAdapter.convertWorlds()

        val dir = File(plugin.dataFolder, "/Arenas")
        if (!dir.exists()) return

        val files = mutableListOf<File>()
        for (fl in dir.listFiles()!!) {
            if (fl.isFile && fl.name.endsWith(".yml")) {
                files.add(fl)
            }
        }

        if (plugin.serverType != ServerType.BUNGEE || plugin.autoScale) {
            files.forEach { loadArena(it.name.removeSuffix(".yml")) }
            return
        }

        if (files.isEmpty()) {
            plugin.logger.warning("Could not find any arena!")
            return
        }
        val name = files.random().name.removeSuffix(".yml")
        loadArena(name)
    }

    override fun getArena(name: String) = arenas[name]
    override fun getArena(player: Player) = arenas.values.find { it.isPlayer(player) || it.isSpectator(player) }
    override fun getArenaByWorld(world: String) = arenas.values.find { it.world.name == world }


    override fun joinRandomArena(p: Player): Boolean {
        val amount = if (!plugin.partyUtil.hasParty(p)) 1
        else plugin.partyUtil.getMembers(p).count { getArena(it)?.isSpectator(it) != false }

        val arenas = getSorted(arenas.values)

        for (a in arenas) {
            if (a.players.size == a.maxPlayers) continue
            if (a.maxPlayers - a.players.size >= amount) {
                if (a.addPlayer(p, false)) break
            }
        }
        return true
    }

    override fun joinRandomFromGroup(player: Player, group: String): Boolean {
        val amount = if (!plugin.partyUtil.hasParty(player)) 1
        else plugin.partyUtil.getMembers(player).count { getArena(it)?.isSpectator(it) != false }

        val arenas = getSorted(arenas.values)
        val groups = group.split("+")

        for (arena in arenas) {
            if (arena.players.size == arena.maxPlayers) continue
            if (groups.any {
                it.equals(arena.group, ignoreCase = true) &&
                arena.maxPlayers - arena.players.size >= amount &&
                arena.addPlayer(player, false)
            }) return true
        }

        return false
    }


    override fun loadArena(worldName: String, player: Player?): Arena? {
        val name = worldName

        if (!plugin.autoScale) {
            for (mm in enableQueue) {
                if (mm.name.equals(name, ignoreCase = true)) {
                    plugin.logger.severe("Tried to load arena $name but it is already in the enable queue.")
                    player?.sendMessage("${ChatColor.RED}Tried to load arena $name but it is already in the enable queue.")
                    return null
                }
            }
            if (name in arenas) {
                plugin.logger.severe("Tried to load arena $name but it is already enabled.")
                player?.sendMessage("${ChatColor.RED}Tried to load arena $name but it is already enabled.")
                return null
            }
        }
        val worldName = if (!plugin.autoScale) name else generateGameID()
        val config = ArenaConfig(plugin, name, plugin.dataFolder.path + "/Arenas")

        if ("Team" !in config) {
            player?.sendMessage("You didn't set any team for arena: $name")
            plugin.logger.severe("You didn't set any team for arena: $name")
            return null
        }

        if (config.getConfigurationSection("Team")!!.getKeys(false).size < 2) {
            player?.sendMessage("§cYou must set at least 2 teams on: $name")
            plugin.logger.severe("You must set at least 2 teams on: $name")
            return null
        }

        if (!plugin.restoreAdapter.isWorld(name)) {
            player?.sendMessage("${ChatColor.RED}There isn't any map called $name")
            plugin.logger.warning("There isn't any map called $name")
            return null
        }

        var error = false
        for (team in config.getConfigurationSection("Team")!!.getKeys(false)) {
            val color = config.getString("Team.$team.Color")
            if (color != null && TeamColor.entries.none { it.name.equals(color, ignoreCase = true) }) {
                player?.sendMessage("§cInvalid color at team: $team in arena: $name")
                plugin.logger.severe("Invalid color at team: $team in arena: $name")
                error = true
            }
            for (property in arrayOf("Color", "Spawn", "Bed", "Shop", "Upgrade", "Iron", "Gold")) {
                if ("Team.$team.$property" in config) continue
                player?.sendMessage("§c$property not set for $team team on: $name")
                plugin.logger.severe("$property not set for $team team on: $name")
                error = true
            }
        }

        if ("generator.Diamond" !in config) {
            player?.sendMessage("§cThere isn't set any Diamond generator on: $name")
            plugin.logger.severe("There isn't set any Diamond generator on: $name")
        }
        if ("generator.Emerald" !in config) {
            player?.sendMessage("§cThere isn't set any Emerald generator on: $name")
            plugin.logger.severe("There isn't set any Emerald generator on: $name")
        }
        if ("waiting.Loc" !in config) {
            player?.sendMessage("§cWaiting spawn not set on: $name")
            plugin.logger.severe("Waiting spawn not set on: $name")
            error = true
        }
        if (error) return null

        return Arena(plugin, this, name, worldName, config).also { addToEnableQueue(it) }
    }

    override fun isInArena(player: Player) = arenas.values.any { it.isPlayer(player) || it.isSpectator(player) }
    override fun isPlaying(player: Player) = arenas.values.any { it.isPlayer(player) }
    override fun isSpectating(player: Player) = arenas.values.any { it.isSpectator(player) }

    override fun getPlayers(group: String): Int {
        val groups = group.lowercase().split("+")
        return arenas.values
            .asSequence()
            .filter { it.group.lowercase() in groups }
            .sumOf { it.players.size }
    }

    /**
     * Remove an arena from the enable queue.
     */
    override fun removeFromEnableQueue(arena: IArena) {
        enableQueue.remove(arena)
        val queue = enableQueue.firstOrNull() ?: return

        plugin.restoreAdapter.onEnable(queue)
        plugin.logger.info("Loading arena: ${queue.worldName}")
    }

    override fun addToEnableQueue(arena: IArena) {
        enableQueue += arena
        plugin.logger.info("Arena ${arena.worldName} was added to the enable queue.")
        if (enableQueue.size != 1) return

        plugin.restoreAdapter.onEnable(arena)
        plugin.logger.info("Loading arena: ${arena.worldName}")
    }

    // used for auto-scale conditions
    override fun canAutoScale(arena: String): Boolean {
        if (!plugin.autoScale || arenas.isEmpty()) return true
        if (enableQueue.any { it.name.equals(arena, true) }) return false
        if (gamesBeforeRestart != -1 && arenas.size >= gamesBeforeRestart) return false

        var activeClones = 0
        for (ar in arenas.values) {
            if (!ar.name.equals(arena, ignoreCase = true)) continue
            // clone this arena only if there aren't available arena of the same kind
            if (ar.status.isPreGame()) return false
            // count active clones
            ++activeClones
        }

        // check amount of active clones
        return plugin.mainConfig.getInt(ConfigPath.GENERAL_CONFIGURATION_AUTO_SCALE_LIMIT) > activeClones
    }

    fun getSorted(arenas: Collection<IArena>) = arenas.sortedWith { o1, o2 -> when {
        o1.status == GameState.STARTING && o2.status == GameState.STARTING -> o2.players.size.compareTo(o1.players.size)
        o1.status == GameState.STARTING && o2.status != GameState.STARTING -> -1
        o2.status == GameState.STARTING && o1.status != GameState.STARTING -> 1
        o1.status == GameState.WAITING && o2.status == GameState.WAITING -> o2.players.size.compareTo(o1.players.size)
        o1.status == GameState.WAITING && o2.status != GameState.WAITING -> -1
        o2.status == GameState.WAITING && o1.status != GameState.WAITING -> 1
        o1.status == GameState.PLAYING && o2.status == GameState.PLAYING -> 0
        o1.status == GameState.PLAYING && o2.status != GameState.PLAYING -> -1
        else -> 1
    } }

    /**
     * This will give the lobby items to the player.
     * Not used in serverType BUNGEE.
     * This will clear the inventory first.
     */
    fun sendLobbyCommandItems(player: Player) {
        val config = plugin.mainConfig
        val path = config.getConfigurationSection(ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_PATH) ?: return
        if (!plugin.mainConfig.lobbyWorldName.equals(player.world.name, ignoreCase = true)) return
        player.inventory.clear()

        plugin.run(true, 15) {
            if (!config.lobbyWorldName.equals(player.world.name, ignoreCase = true)) return@run

            for (item in path.getKeys(false)) {
                val material = ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_MATERIAL.replace("%path%", item)
                if (material !in config) {
                    plugin.logger.severe("$material is not set!")
                    continue
                }

                val data = ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_DATA.replace("%path%", item)
                if (data !in config) {
                    plugin.logger.severe("$data is not set!")
                    continue
                }

                val slot = ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_SLOT.replace("%path%", item)
                if (slot !in config) {
                    plugin.logger.severe("$slot is not set!")
                    continue
                }

                val enchanted = ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_ENCHANTED.replace("%path%", item)
                if (enchanted !in config) {
                    plugin.logger.severe("$enchanted is not set!")
                    continue
                }

                val command = ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_COMMAND.replace("%path%", item)
                if (command !in config) {
                    plugin.logger.severe("$command is not set!")
                    continue
                }

                player.inventory.setItem(config.getInt(slot), Misc.createItem(
                    Material.valueOf(config.getString(material)!!),
                    config.getInt(data).toByte(),
                    config.getBoolean(enchanted),
                    SupportPAPI.support.replace(player, Language.getMsg(player, Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", item))),
                    SupportPAPI.support.replace(player, Language.getList(player, Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", item))),
                    player,
                    "RUNCOMMAND",
                    config.getString(command)!!
                ))
            }
        }
    }


    private var gid = 0
    private var day = 0
    private var month = 0

    fun generateGameID(): String {
        val datetime = LocalDateTime.now()
        val currentMonth = datetime.monthValue
        val currentDay = datetime.dayOfMonth
        if (currentMonth != month && currentDay != day) {
            month = currentMonth
            day = currentDay
            gid = 0
        }
        return "bw_temp_y${datetime.year}m${month}d${day}g${gid++}"
    }
}
