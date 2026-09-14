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
package com.andrei1058.bedwars.maprestore.internal.files

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.util.ZipFileUtil
import org.bukkit.Bukkit
import java.io.File
import java.io.IOException

class WorldZipper(private val worldName: String, private val replace: Boolean) {
    init {
        execute()
    }

    private fun execute() {
        if (!exists() || replace) {
            try {
                zipWorldFolder()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private val worldFolder get() = File(Bukkit.getWorldContainer(), worldName)
    private val backupFile get() = File(File(BedWars.INSTANCE.dataFolder, "Cache"), "$worldName.zip")

    private fun exists() = worldFolder.isDirectory()

    @Throws(IOException::class)
    private fun zipWorldFolder() {
        ZipFileUtil.zipDirectory(worldFolder, backupFile)
    }
}
