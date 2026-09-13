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
package com.andrei1058.bedwars.maprestore.internal

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.server.ISetupSession
import com.andrei1058.bedwars.api.server.RestoreAdapter
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.util.ZipFileUtil
import com.andrei1058.bedwars.arena.VoidChunkGenerator
import com.andrei1058.bedwars.maprestore.internal.files.WorldZipper
import org.bukkit.WorldCreator
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException

class InternalAdapter(plugin: Plugin) : RestoreAdapter<BedWars>(
    plugin,
    "BedWars1058",
    "Internal Restore Adapter"
) {

    override fun onEnable(arena: IArena) {
        run {
            server.getWorld(arena.worldName)?.let {
                arena.init(it)
                return@run
            }

            run(async = true) {
                val bf = File(backupFolder, "${arena.name}.zip")
                val af = File(server.worldContainer, arena.name)
                if (bf.exists()) af.deleteRecursively()

                if (!bf.exists()) {
                    WorldZipper(arena.name, true)
                } else try {
                    ZipFileUtil.unzipFileIntoDirectory(bf, File(server.worldContainer, arena.worldName))
                } catch (e: IOException) {
                    e.printStackTrace()
                }

                deleteWorldTrash(arena.worldName, true)
                run {
                    val wc = WorldCreator(arena.worldName)
                    wc.generateStructures(false)
                    wc.generator(VoidChunkGenerator())
                    val w = server.createWorld(wc)
                    checkNotNull(w) { "World should be null" }
                    w.keepSpawnInMemory = true
                    w.isAutoSave = false
                }
            }
        }
    }

    override fun onRestart(arena: IArena) {
        run {
            if (api.serverType == ServerType.BUNGEE) {
                if (api.arenaManager.gamesBeforeRestart == 0) {
                    if (api.arenaManager.arenas.isEmpty()) {
                        val command = api.configs.mainConfig.getString(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_RESTART_CMD)
                        log.info("Dispatching command: $command")
                        server.dispatchCommand(server.consoleSender, command!!)
                    }
                } else {
                    if (api.arenaManager.gamesBeforeRestart != -1) api.arenaManager.gamesBeforeRestart -= 1
                    server.unloadWorld(arena.worldName, false)
                    if (api.arenaManager.canAutoScale(arena.name)) {
                        run(delay = 80) { api.arenaManager.loadArena(arena.name) }
                    }
                }
            } else {
                server.unloadWorld(arena.worldName, false)
                run(delay = 80) { plugin.arenaManager.loadArena(arena.name) }
            }
            if (arena.worldName != arena.name) {
                deleteWorld(arena.worldName)
            }
        }
    }

    override fun onSetupSessionStart(session: ISetupSession) {
        run(async = true) {
            val bf = File(backupFolder, session.worldName + ".zip")
            val af = File(server.worldContainer, session.worldName)
            if (bf.exists()) {
                af.deleteRecursively()
                try {
                    ZipFileUtil.unzipFileIntoDirectory(bf, File(server.worldContainer, session.worldName))
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            val wc = WorldCreator(session.worldName)
            wc.generator(VoidChunkGenerator())
            wc.generateStructures(false)
            run {
                try {
                    val level = File(server.worldContainer, session.worldName + "/region")
                    if (level.exists()) {
                        session.message("Loading ${session.worldName} from Bukkit worlds container.")
                        deleteWorldTrash(session.worldName, true)
                        server.createWorld(wc)!!.keepSpawnInMemory = true
                    } else {
                        session.message("Creating a new void map: ${session.worldName}")
                        server.createWorld(wc)!!.keepSpawnInMemory = true
                        run(delay = 20) { session.teleportPlayer() }
                    }
                } catch (ex: Exception) {
                    ex.printStackTrace()
                    session.close()
                    return@run
                }
                run(delay = 20) { session.teleportPlayer() }
            }
        }
    }

    override fun onSetupSessionClose(session: ISetupSession) {
        super.onSetupSessionClose(session)
        run(async = true) { WorldZipper(session.worldName, true) }
    }

    override fun isWorld(name: String) = File(server.worldContainer, "$name/region").exists()

    override fun deleteWorld(name: String) = run(async = true) {
        try {
            File(server.worldContainer, name).deleteRecursively()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun cloneArena(name1: String, name2: String) = run(async = true) {
        try {
            File(server.worldContainer, name1)
                .copyRecursively(File(server.worldContainer, name2))
            deleteWorldTrash(name2, true)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override val worldsList: List<String> get() {
            val dir = server.worldContainer
            if (dir.exists()) {
                val worlds = mutableListOf<String>()
                for (fl in dir.listFiles()!!) {
                    if (!fl.isDirectory) continue
                    val dat = File("${fl.name}/region")
                    if (dat.exists() && !fl.name.startsWith("bw_temp")) {
                        worlds.add(fl.name)
                    }
                }
            }
            return emptyList()
        }

    override fun convertWorlds() {
        val (directory, files) = getWorldFiles()

        // lowerCase arena names - new 1.14 standard
        for (file in files) {
            if (file.name == file.name.lowercase()) continue

            val newName = File(directory.path + "/" + file.name.lowercase())
            if (!file.renameTo(newName)) {
                log.severe("Could not rename ${file.name} to ${file.name.lowercase()}! Please do it manually!")
            }

            val folder = File(plugin.server.worldContainer, file.name.removeSuffix(".yml"))
            if (!folder.exists()) continue
            val name = folder.name
            if (name != name.lowercase() || !folder.renameTo(File("${plugin.server.worldContainer.path}/$name".lowercase()))) {
                log.severe("Could not rename $name folder to " + name.lowercase() + "! Please do it manually!")
                continue
            }
        }
        deleteTempWorlds()
    }
}