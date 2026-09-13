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
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.SQLException
import java.util.concurrent.TimeUnit

class MySQL : CommonSQL() {
    private val host = BedWars.config.getString("database.host")
    private val database = BedWars.config.getString("database.database")
    private val user = BedWars.config.getString("database.user")
    private val pass = BedWars.config.getString("database.pass")
    private val port = BedWars.config.getInt("database.port")
    private val ssl = BedWars.config.getBoolean("database.ssl")
    private val certificateVerification = BedWars.config.getBoolean("database.verify-certificate", true)
    private val poolSize = BedWars.config.getInt("database.pool-size", 10)
    private val maxLifetime = BedWars.config.getInt("database.max-lifetime", 1800)

    /**
     * Creates the SQL connection pool and tries to connect.
     * 
     * @return true if connected successfully.
     */
    fun connect() = HikariConfig().run {
        poolName = "BedWars1058MySQLPool"

        maximumPoolSize = poolSize
        maxLifetime = this@MySQL.maxLifetime * 1000L

        setJdbcUrl("jdbc:mysql://$host:$port/$database")

        username = user
        password = pass

        if (!certificateVerification) {
            addDataSourceProperty("verifyServerCertificate", false)
        }
        mapOf(
            "useSSL" to ssl,
            "characterEncoding" to "utf8",
            "encoding" to "UTF-8",
            "useUnicode" to true,
            "rewriteBatchedStatements" to "true",
            "jdbcCompliantTruncation" to "false",
            "cachePrepStmts" to "true",
            "prepStmtCacheSize" to "275",
            "prepStmtCacheSqlLimit" to "2048",

            // Recover if connection gets interrupted
            "socketTimeout" to TimeUnit.SECONDS.toMillis(30)
        ).forEach { (key, value) -> addDataSourceProperty(key, value) }

        val dataSource = HikariDataSource(this)

        try {
            connection = dataSource.connection
            true
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }
}
