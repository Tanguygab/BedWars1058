package com.andrei.bedwars1058.arena.mapreset.slime

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.server.RestoreAdapter
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.util.FileUtil
import com.andrei1058.bedwars.api.util.ZipFileUtil
import com.flowpowered.nbt.CompoundMap
import com.flowpowered.nbt.CompoundTag
import com.flowpowered.nbt.IntTag
import com.flowpowered.nbt.stream.NBTInputStream
import com.flowpowered.nbt.stream.NBTOutputStream
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import kotlin.jvm.optionals.getOrNull

abstract class SlimeAdapterCommon<T>(owner: Plugin, plugin: String, displayName: String): RestoreAdapter<T>(owner, plugin, displayName) {

    abstract fun loadWorld(arena: IArena, spawn: List<String>)
    override fun onEnable(arena: IArena) {
        if (api.versionSupport.mainLevel.equals(arena.worldName, ignoreCase = true) && (api.serverType != ServerType.BUNGEE || api.arenaUtil.gamesBeforeRestart != 1)) {
            FileUtil.setMainLevel("ignore_main_level", api.versionSupport)
            log.severe("Cannot use level-name as arenas. Automatically creating a new void map for level-name.")
            log.severe("The server is restarting...")
            server.spigot().restart()
            return
        }

        run(async = true) {
            server.getWorld(arena.worldName)?.let {
                run { arena.init(it) }
                return@run
            }
            val spawn = arena.config.getString("waiting.Loc").split(",")
            try {
                loadWorld(arena, spawn)
            } catch (thisShouldNotHappenSWM: ConcurrentModificationException) {
                // this should not happen since they say to use #load async
                // https://github.com/Grinderwolf/Slime-World-Manager/blob/develop/.docs/api/load-world.md
                thisShouldNotHappenSWM.printStackTrace()
                api.arenaUtil.removeFromEnableQueue(arena)
                log.severe("This is a $pluginName issue!")
                log.severe("I've submitted a bug report: https://github.com/Grinderwolf/Slime-World-Manager/issues/174")
                log.severe("Trying again to load arena: " + arena.arenaName)

                // hope not to get an overflow
                onEnable(arena)
            } catch (e: Exception) {
                api.arenaUtil.removeFromEnableQueue(arena)
                e.printStackTrace()
            }
        }
    }

    /**
     * Convert vanilla worlds to the slime format.
     */
    override fun convertWorlds() {
        val (directory, files) = getWorldFiles()

        for (file in files) {
            val name = file.name.removeSuffix(".yml").lowercase()
            val ff = File(server.worldContainer, file.name.removeSuffix(".yml"))
            try {
                if (isWorld(name)) continue

                if (file.name != name && !file.renameTo(File(directory, "$name.yml"))) {
                    log.warning("Could not rename ${file.name}.yml to $name.yml")
                }
                val bc = File(backupFolder, "${ff.name}.zip")
                if (ff.exists() && bc.exists()) {
                    FileUtil.delete(ff)
                    ZipFileUtil.unzipFileIntoDirectory(bc, File(server.worldContainer, name))
                }
                // clean up world folder
                deleteWorldTrash(name)
                // check if level.dat is missing in world folder
                handleLevelDat(name)
                // start Slime conversion
                convertWorld(name)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        deleteTempWorlds()
    }

    abstract fun convertWorld(name: String)

    /**
     * Create level.dat in world folder before converting to Slime format.
     * References:
     * [Slime importer requirements](https://github.com/cijaaimee/Slime-World-Manager/blob/develop/slimeworldmanager-importer/src/main/java/com/grinderwolf/swm/importer/SWMImporter.java)
     * [level.dat NBT TAG](https://wiki.vg/Map_Format#level.dat)
     *
     * @param world folder name.
     */
    @Throws(IOException::class)
    protected fun handleLevelDat(world: String) {
        val worldFolder = File(server.worldContainer, world)
        if (!worldFolder.exists() || !worldFolder.isDirectory) return

        val levelFile = File(worldFolder, "level.dat")
        if (levelFile.exists()) return

        // try detecting world version from region files
        val regionFolder = File(worldFolder, "region")
        val regionFiles = regionFolder.listFiles()

        if (!regionFolder.exists() || regionFiles == null || !regionFolder.isDirectory) {
            log.severe("Tried detecting world version, but it has no regions! ($world)")
            return
        }

        val firstRegion = regionFiles.find { it.isFile && it.name.endsWith(".mca") }
        var dataVersion: Int? = null

        // try getting world version from NBT TAG
        if (firstRegion != null) {
            try {
                NBTInputStream(FileInputStream(firstRegion))
                    .use { it.readTag().asCompoundTag }
                    .getOrNull()
                    ?.getAsCompoundTag("Chunk")
                    ?.flatMap { it.getIntValue("DataVersion") }
                    ?.ifPresent {
                        dataVersion = it
                        server.logger.info("Detected world version from region file for level.dat creation: v$it ($world)")
                    }
            } catch (_: Exception) {}
        }

        val errorMessage = "Cannot create level.dat in $worldFolder"

        // if world version was not detected we assume it is 1.8.8 and move on :)
        // create new level.dat file and write TAG
        if (!levelFile.createNewFile()) {
            log.severe(errorMessage)
            return
        }

        try {
            NBTOutputStream(FileOutputStream(levelFile)).use {
                val cm = CompoundMap(arrayOf(
                    IntTag("spawnX", 0),
                    IntTag("spawnY", 255),
                    IntTag("spawnZ", 0)
                ).associateBy { tag -> tag.name })
                if (dataVersion != null) cm.put(IntTag("DataVersion", dataVersion))

                val rootTag = CompoundMap()
                rootTag.put(CompoundTag("Data", cm))
                it.writeTag(CompoundTag("", rootTag))
                it.flush()
            }
        } catch (_: Exception) {
            try {
                // clean up empty file
                // noinspection ResultOfMethodCallIgnored
                levelFile.delete()
            } catch (_: Exception) {}
            log.severe(errorMessage)
        }
    }
}