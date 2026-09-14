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
package com.andrei1058.bedwars.database

import com.andrei1058.bedwars.BedWars
import java.io.File
import java.io.IOException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class SQLite(plugin: BedWars) : CommonSQL() {
    private val url: String

    init {
        val folder = File("${plugin.dataFolder}/Cache")
        if (!folder.exists() && !folder.mkdir()) {
            plugin.logger.severe("Could not create /Cache folder!")
        }
        val dataFolder = File("${folder.path}/shop.db")
        url = "jdbc:sqlite:$dataFolder"

        if (!dataFolder.exists()) {
            try {
                if (!dataFolder.createNewFile()) {
                    plugin.logger.severe("Could not create /Cache/shop.db file!")
                }

                try {
                    Class.forName("org.sqlite.JDBC")
                    DriverManager.getConnection(url)
                } catch (e: SQLException) {
                    e.printStackTrace()
                } catch (e: ClassNotFoundException) {
                    plugin.logger.severe("Could Not Found SQLite Driver on your system!")
                    e.printStackTrace()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun <T> connection(run: Connection.() -> T): T {
        checkConnection()
        return connection!!.use(run)
    }

    @Throws(SQLException::class)
    private fun checkConnection() {
        if (connection?.isClosed != false)
            connection = DriverManager.getConnection(url)
    }
}