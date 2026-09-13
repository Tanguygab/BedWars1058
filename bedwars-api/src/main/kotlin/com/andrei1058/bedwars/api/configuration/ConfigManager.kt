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
package com.andrei1058.bedwars.api.configuration

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException

/**
 * Create a new configuration file.
 *
 * @param plugin config owner.
 * @param fileName   config name. Do not include .yml in it.
 */
open class ConfigManager(
    plugin: Plugin,
    var fileName: String,
    dir: String) : YamlConfiguration() {
    private var file: File

    /**
     * Check if the config file was created for the first time
     * Can be used to add default values
     */
    var isFirstTime = false
        private set

    init {
        val d = File(dir)

        if (!d.exists() && !d.mkdirs()) throw RuntimeException("Could not create $d")

        file = File(dir, "$fileName.yml")
        if (!file.exists()) {
            isFirstTime = true
            plugin.logger.info("Creating $file")
            if (!file.createNewFile()) throw RuntimeException("Could not create $file")
        }

        reload()
        options.copyDefaults(true)
    }

    protected fun default(vararg values: Pair<String, Any>, placeholders: Map<String, Any> = emptyMap()) {
        values.forEach { (key, value) ->
            var key = key
            placeholders.forEach { (placeholder, replacement) -> key = key.replace("%$placeholder%", "$replacement") }
            addDefault(key, value)
        }
    }

    /**
     * Reload configuration.
     */
    fun reload() {
        load(file)
    }

    /**
     * Set data to config
     */
    override fun set(path: String, value: Any?) {
        super.set(path, value)
        save()
    }

    fun relocate(from: String, to: String) {
        val fromData = get(from) ?: return
        set(to, fromData)
        set(from, null)
    }

    /**
     * Save config changes to file
     */
    fun save() {
        try {
            save(file)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * Convert a location to an arena location syntax
     */
    fun stringLocationArenaFormat(loc: Location) = "${loc.x},${loc.y},${loc.z},${loc.yaw.toDouble()},${loc.pitch.toDouble()}"

    /**
     * Convert a location to a string for general use.
     * Use [.stringLocationArenaFormat] for arena locations
     */
    fun stringLocationConfigFormat(loc: Location) = "${stringLocationArenaFormat(loc)},${loc.world!!.name}"

    /**
     * Save a general location to the config.
     * Use [.saveArenaLoc] for arena locations
     */
    fun saveConfigLoc(path: String, loc: Location) {
        set(path, stringLocationConfigFormat(loc))
    }

    /**
     * Save a location for arena use
     */
    fun saveArenaLoc(path: String, loc: Location) {
        set(path, stringLocationArenaFormat(loc))
    }

    /**
     * Get a general location
     * Use [.getArenaLoc] for locations stored using [.saveArenaLoc]
     */
    fun getConfigLoc(path: String): Location? {
        val data = getString(path)
            ?.removeSurrounding("[", "]")
            ?.split(",")
            ?: return null
        return Location(
            Bukkit.getWorld(data[5]),
            data[0].toDouble(),
            data[1].toDouble(),
            data[2].toDouble(),
            data[3].toFloat(),
            data[4].toFloat()
        )
    }

    /**
     * Get a location for arena use
     * Use [.getConfigLoc] (String)} for locations stored using [.saveConfigLoc] (String, Location)}
     */
    fun getArenaLoc(path: String): Location? {
        val data = getString(path)
            ?.removeSurrounding("[", "]")
            ?.split(",")
            ?: return null
        return Location(
            Bukkit.getWorld(fileName),
            data[0].toDouble(),
            data[1].toDouble(),
            data[2].toDouble(),
            data[3].toFloat(),
            data[4].toFloat()
        )
    }

    /**
     * Convert string to arena location syntax
     */
    fun convertStringToArenaLocation(string: String): Location {
        val data = string.split(",")
        return Location(
            Bukkit.getWorld(fileName),
            data[0].toDouble(),
            data[1].toDouble(),
            data[2].toDouble(),
            data[3].toFloat(),
            data[4].toFloat()
        )
    }

    /**
     * Get list of arena locations at given path
     */
    fun getArenaLocations(path: String) = super
        .getStringList(path)
        .map { convertStringToArenaLocation(it) }

    /**
     * Get list of strings at given path
     * 
     * @return a list of string with colors translated
     */
    override fun getStringList(path: String) = super.getStringList(path).map { it.replace("&", "§") }

    /**
     * Compare two arena locations
     * Return true if same location
     */
    fun compareArenaLoc(l1: Location, l2: Location) = l1.blockX == l2.blockX &&
            l1.blockZ == l2.blockZ &&
            l1.blockY == l2.blockY
}
