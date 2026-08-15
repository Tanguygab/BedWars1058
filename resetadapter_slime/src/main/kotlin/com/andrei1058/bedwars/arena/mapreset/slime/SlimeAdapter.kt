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
package com.andrei1058.bedwars.arena.mapreset.slime

import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.server.ISetupSession
import com.andrei1058.bedwars.api.server.ServerType
import com.grinderwolf.swm.api.SlimePlugin
import com.grinderwolf.swm.api.exceptions.UnknownWorldException
import com.grinderwolf.swm.api.loaders.SlimeLoader
import com.grinderwolf.swm.api.world.SlimeWorld
import com.grinderwolf.swm.api.world.properties.SlimeProperties
import com.grinderwolf.swm.api.world.properties.SlimePropertyMap
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException

open class SlimeAdapter(
    owner: Plugin,
    plugin: String = "SlimeWorldManager",
    displayName: String = "Slime World Manager by Grinderwolf"
) : SlimeAdapterCommon<SlimePlugin>(owner, plugin, displayName) {
    val loader: SlimeLoader get() = plugin.getLoader("file")

    override fun loadWorld(arena: IArena, spawn: List<String>) {
        // Note that this method should be called asynchronously
        var world = plugin.loadWorld(loader, arena.arenaName, true, buildPropertyMap(spawn))
        if (api.serverType == ServerType.BUNGEE && api.isAutoScale) {
            world = world.clone(arena.worldName)
        }

        // This method must be called synchronously
        run { generateWorld(arena, null, world) }
    }

    open fun generateWorld(arena: IArena?, session: ISetupSession?, world: SlimeWorld) {
        plugin.generateWorld(world)
    }

    override fun onRestart(arena: IArena) {
        if (api.serverType != ServerType.BUNGEE) {
            run {
                server.unloadWorld(arena.worldName, false)
                run(delay = 80) { api.arenaUtil.loadArena(arena.arenaName, null) }
            }
            return
        }
        if (api.arenaUtil.gamesBeforeRestart == 0) {
            if (api.arenaUtil.arenas.size == 1 && api.arenaUtil.arenas[0].status == GameState.restarting) {
                val command = api.configs.mainConfig.getString(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_RESTART_CMD)
                log.info("Dispatching command: $command")
                server.dispatchCommand(server.consoleSender, command)
            }
            return
        }

        if (api.arenaUtil.gamesBeforeRestart != -1) api.arenaUtil.gamesBeforeRestart -= 1
        run {
            server.unloadWorld(arena.worldName, false)
            if (api.arenaUtil.canAutoScale(arena.arenaName)) {
                run(delay = 80) { api.arenaUtil.loadArena(arena.arenaName, null) }
            }
        }
    }

    override fun onSetupSessionStart(session: ISetupSession) {
        run(async = true) {
            val sLoader = loader
            val spm = buildPropertyMap(session.config.getString("waiting.Loc").split(","))
            try {
                if (server.getWorld(session.worldName) != null) {
                    run { server.unloadWorld(session.worldName, false) }
                }

                val world = if (sLoader.worldExists(session.worldName)) {
                    session.message("Loading world from SlimeWorldManager container.")
                    plugin.loadWorld(sLoader, session.worldName, false, spm)
                } else if (File(server.worldContainer, session.worldName + "/level.dat").exists()) {
                    session.message("Importing world to the SlimeWorldManager container.")
                    plugin.importWorld(
                        File(server.worldContainer, session.worldName),
                        session.worldName.lowercase(),
                        sLoader
                    )
                    plugin.loadWorld(sLoader, session.worldName, false, spm)
                } else {
                    session.message("Creating a new void map.")
                    plugin.createEmptyWorld(sLoader, session.worldName, false, spm)
                }

                // This method must be called synchronously
                run {
                    generateWorld(null, session, world)
                    session.teleportPlayer()
                }
            } catch (e: UnknownWorldException) {
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

    override fun cloneArena(name1: String, name2: String) = run(async = true) {
        try {
            // Note that this method should be called asynchronously
            val sLoader = loader
            val world = plugin.loadWorld(sLoader, name1, true, buildPropertyMap(listOf("0", "118", "0")))
            world.clone(name2, sLoader)
        } catch (e: Exception) {
            e.printStackTrace()
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
        if (!worldFolder.exists() || !worldFolder.isDirectory) {
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

    protected fun buildPropertyMap(spawn: List<String>) = SlimePropertyMap().apply {
        setString(SlimeProperties.WORLD_TYPE, "flat")
        setInt(SlimeProperties.SPAWN_X, spawn[0].toDouble().toInt())
        setInt(SlimeProperties.SPAWN_Y, spawn[1].toDouble().toInt())
        setInt(SlimeProperties.SPAWN_Z, spawn[2].toDouble().toInt())
        setBoolean(SlimeProperties.ALLOW_ANIMALS, false)
        setBoolean(SlimeProperties.ALLOW_MONSTERS, false)
        setString(SlimeProperties.DIFFICULTY, "easy")
        setBoolean(SlimeProperties.PVP, true)
    }
}
