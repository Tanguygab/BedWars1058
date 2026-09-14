/*
 * BedWars1058 - A bed wars mini-game.
 * Copyright (C) 2023 Andrei Dascălu
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
package com.andrei1058.bedwars.arena.mapreset.slime

import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.server.ISetupSession
import com.andrei1058.bedwars.api.server.ServerType
import com.infernalsuite.aswm.api.SlimePlugin
import com.infernalsuite.aswm.api.loaders.SlimeLoader
import com.infernalsuite.aswm.api.world.properties.SlimeProperties
import com.infernalsuite.aswm.api.world.properties.SlimePropertyMap
import org.bukkit.event.world.WorldInitEvent
import org.bukkit.event.world.WorldLoadEvent
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException

class SlimePaperAdapter(plugin: Plugin) : SlimeAdapterCommon<SlimePlugin>(
    plugin,
    "SlimeWorldManager",
    "Advanced Slime Paper by InfernalSuite"
) {
    private val loader: SlimeLoader get() = plugin.getLoader("file")

    override fun loadWorld(arena: IArena, spawn: List<String>) {
        // Note that this method should be called asynchronously
        var world = plugin.loadWorld(loader, arena.name, true, buildPropertyMap(spawn))
        if (api.serverType == ServerType.BUNGEE && api.autoScale) {
            world = world.clone(arena.worldName)
        }

        // This method must be called synchronously
        run {
            val loaded = plugin.loadWorld(world)
            if (loaded == null) {
                api.arenaManager.removeFromEnableQueue(arena)
                log.severe("Something wrong... removing arena ${arena.name} from queue.")
                return@run
            }
            val world = server.getWorld(loaded.name)
            if (world == null) {
                api.arenaManager.removeFromEnableQueue(arena)
                log.severe("Something wrong... removing arena ${arena.name} from queue.")
                return@run
            }
            server.pluginManager.callEvent(WorldInitEvent(world))
            run { server.pluginManager.callEvent(WorldLoadEvent(world)) }
        }
    }

    override fun onRestart(arena: IArena) {
        if (api.serverType == ServerType.BUNGEE) {
            if (api.arenaManager.gamesBeforeRestart == 0) {
                if (api.arenaManager.arenas.values.firstOrNull()?.status == GameState.RESTARTING) {
                    val command = api.configs.main.getString(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_RESTART_CMD)!!
                    log.info("Dispatching command: $command")
                    server.dispatchCommand(server.consoleSender, command)
                }
            } else {
                if (api.arenaManager.gamesBeforeRestart != -1) api.arenaManager.gamesBeforeRestart -= 1
                run {
                    server.unloadWorld(arena.worldName, false)
                    if (api.arenaManager.canAutoScale(arena.name)) {
                        run(delay = 80) { api.arenaManager.loadArena(arena.name) }
                    }
                }
            }
        } else {
            run {
                server.unloadWorld(arena.worldName, false)
                run(delay = 80) { api.arenaManager.loadArena(arena.name) }
            }
        }
    }

    override fun onSetupSessionStart(session: ISetupSession) {
        run(async = true) {
            val spm = buildPropertyMap(session.config.getString("waiting.Loc")?.split(",") ?: listOf("0", "50", "0"))
            try {
                if (server.getWorld(session.worldName) != null) {
                    run { server.unloadWorld(session.worldName, false) }
                }

                val sLoader = loader
                val world = if (sLoader.worldExists(session.worldName)) {
                    session.message("Loading world from SlimeWorldManager container.")
                    plugin.loadWorld(sLoader, session.worldName, false, spm)
                } else {
                    if (File(server.worldContainer, "${session.worldName}/level.dat").exists()) {
                        session.message("Importing world to the SlimeWorldManager container.")
                        plugin.importWorld(
                            File(server.worldContainer, session.worldName),
                            session.worldName.lowercase(),
                            sLoader
                        )
                        plugin.loadWorld(sLoader, session.worldName, false, spm)
                    } else {
                        session.message("Creating anew void map.")
                        plugin.createEmptyWorld(sLoader, session.worldName, false, spm)
                    }
                }

                if (world == null) return@run
                // This method must be called synchronously
                run {
                    val w = server.getWorld(world.name) ?: return@run
                    server.pluginManager.callEvent(WorldInitEvent(w))
                    run { server.pluginManager.callEvent(WorldLoadEvent(w)) }
                    session.teleportPlayer()
                }
            } catch (e: Exception) {
                session.message("An error occurred! Please check console.", success = false)
                e.printStackTrace()
                session.close()
            }
        }
    }

    override fun isWorld(name: String) = try {
        loader.worldExists(name)
    } catch (e: IOException) {
        e.printStackTrace()
        false
    }

    override fun deleteWorld(name: String) = run(async = true) {
        try {
            loader.deleteWorld(name)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun cloneArena(name1: String, name2: String) {
        run(async = true) {
            try {
                // Note that this method should be called asynchronously
                val world = plugin.loadWorld(
                    loader,
                    name1,
                    true,
                    buildPropertyMap(listOf("0", "118", "0"))
                )
                world.clone(name2, loader)
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    override val worldsList: List<String> get() = try {
        loader.listWorlds()
    } catch (e: IOException) {
        e.printStackTrace()
        emptyList()
    }

    override fun convertWorld(name: String) {
        val worldFolder = File(server.worldContainer, name)
        if (!worldFolder.exists() || !worldFolder.isDirectory()) {
            log.severe("Tried converting arena $name to Slime format, but couldn't find any bukkit world folder.")
            return
        }

        // todo allow data loaders
        try {
            log.info("Converting $name to the Slime format.")
            plugin.importWorld(File(server.worldContainer, name), name, loader)
        } catch (e: Exception) {
            log.warning("Could not convert $name to the Slime format.")
            e.printStackTrace()
        }
    }

    private fun buildPropertyMap(spawn: List<String>) = SlimePropertyMap().apply {
        setValue(SlimeProperties.WORLD_TYPE, "flat")
        setValue(SlimeProperties.SPAWN_X, spawn[0].toDouble().toInt())
        setValue(SlimeProperties.SPAWN_Y, spawn[1].toDouble().toInt())
        setValue(SlimeProperties.SPAWN_Z, spawn[2].toDouble().toInt())
        setValue(SlimeProperties.ALLOW_ANIMALS, false)
        setValue(SlimeProperties.ALLOW_MONSTERS, false)
        setValue(SlimeProperties.DIFFICULTY, "easy")
        setValue(SlimeProperties.PVP, true)
    }
}
