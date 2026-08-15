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
package com.andrei1058.bedwars.api.server

import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigPath
import org.apache.commons.io.FileUtils
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Item
import org.bukkit.plugin.Plugin
import org.bukkit.util.Vector
import java.io.File
import java.io.IOException
import kotlin.math.max
import kotlin.math.min

abstract class RestoreAdapter<T>(val owner: Plugin, protected val pluginName: String, val displayName: String) {
    protected val server get() = owner.server
    @Suppress("UNCHECKED_CAST")
    protected val plugin = server.pluginManager.getPlugin(pluginName) as T
    protected val api = server.servicesManager.getRegistration(BedWars::class.java)!!.provider
    protected val log = owner.logger
    protected val backupFolder = File(owner.dataFolder, "Cache")

    /**
     * Load the world.
     * Arenas will be initialized automatically based on WorldLoadEvent.
     */
    abstract fun onEnable(arena: IArena)

    /**
     * Restore the world.
     * call new Arena when it's done.
     */
    abstract fun onRestart(arena: IArena)

    /**
     * Unload the world.
     * This is usually used for /bw unloadArena name
     */
    open fun onDisable(arena: IArena) {
        run { server.unloadWorld(arena.worldName, false) }
    }

    /**
     * Load the world for setting it up.
     */
    abstract fun onSetupSessionStart(session: ISetupSession)

    /**
     * Unload the world.
     */
    open fun onSetupSessionClose(session: ISetupSession) {
        run {
            server.getWorld(session.worldName)?.save()
            server.unloadWorld(session.worldName, true)
        }
    }


    /**
     * Remove lobby blocks.
     */
    fun onLobbyRemoval(arena: IArena) {
        foreachBlockInRegion(
            arena.config.getArenaLoc(ConfigPath.ARENA_WAITING_POS1),
            arena.config.getArenaLoc(ConfigPath.ARENA_WAITING_POS2)
        ) { it.type = Material.AIR }

        run(delay = 15L) { clearItems(arena.getWorld()) }
    }

    /**
     * Check if given world exists.
     */
    abstract fun isWorld(name: String): Boolean

    /**
     * Delete a world.
     */
    abstract fun deleteWorld(name: String)

    /**
     * Clone an arena world.
     */
    abstract fun cloneArena(name1: String, name2: String)

    /**
     * Get world container.
     */
    abstract val worldsList: List<String>

    /**
     * Convert worlds if it is necessary before loading them.
     * Let them load on BedWars1058 main Thread, so they will be converted before getting loaded.
     */
    abstract fun convertWorlds()

    private fun foreachBlockInRegion(corner1: Location?, corner2: Location?, consumer: (Block) -> Unit) {
        if (null == corner1 || null == corner2) return

        val min = Vector(
            min(corner1.blockX, corner2.blockX),
            min(corner1.blockY, corner2.blockY),
            min(corner1.blockZ, corner2.blockZ)
        )

        val max = Vector(
            max(corner1.blockX, corner2.blockX),
            max(corner1.blockY, corner2.blockY),
            max(corner1.blockZ, corner2.blockZ)
        )

        for (x in min.blockX..<max.blockX) {
            for (y in min.blockY..<max.blockY) {
                for (z in min.blockZ..<max.blockZ) {
                    consumer(corner1.world!!.getBlockAt(x, y, z))
                }
            }
        }
    }

    private fun clearItems(world: World) {
        world.entities.forEach { (it as? Item)?.remove() }
    }

    protected fun run(async: Boolean = false, delay: Long = 0, run: Runnable) {
        if (api.isShuttingDown) return
        if (async) {
            if (delay == 0L) server.scheduler.runTaskAsynchronously(owner, run)
            server.scheduler.runTaskLaterAsynchronously(owner, run, delay)
            return
        }
        if (delay == 0L) server.scheduler.runTask(owner, run)
        server.scheduler.runTaskLater(owner, run, delay)
    }

    protected fun ISetupSession.message(message: String, success: Boolean = true)
    = run { player.sendMessage("${if (success) ChatColor.GREEN else ChatColor.RED}$message") }

    protected fun getWorldFiles(): Pair<File, Array<File>> {
        val directory = File(owner.dataFolder, "/Arenas")
        val files = directory.listFiles { it.isFile && it.name.endsWith(".yml") }
        return Pair(directory, files ?: emptyArray())
    }

    protected fun deleteTempWorlds() = run(async = true) {
        val files = server.worldContainer.listFiles() ?: return@run
        for (f in files) {
            if (f == null || !f.isDirectory || "bw_temp_" !in f.name) continue
            try {
                FileUtils.deleteDirectory(f)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    protected fun deleteWorldTrash(world: String, levelDat: Boolean = false) {
        for (name in arrayOf(
            "level.dat",
            "level.dat_mcr",
            "level.dat_old",
            "session.lock",
            "uid.dat"
        )) {
            if (name == "level.dat" && levelDat) continue
            val file = File(server.worldContainer, "$world/$name")
            if (!file.exists() || file.delete()) continue
            log.warning("Could not delete: ${file.path}")
            log.warning("This may cause issues!")
        }
    }
}
