package com.andrei1058.bedwars.database

import com.andrei1058.bedwars.api.language.Language.Companion.defaultLanguage
import com.andrei1058.bedwars.shop.quickbuy.QuickBuyElement
import com.andrei1058.bedwars.stats.PlayerStats
import org.intellij.lang.annotations.Language
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.SQLException
import java.sql.Timestamp
import java.util.UUID

abstract class CommonSQL: Database {
    protected var connection: Connection? = null

    protected open fun <T> connection(run: Connection.() -> T) = connection!!.use(run)
    private fun <T> prepare(@Language("SQL") sql: String, run: PreparedStatement.() -> T) = connection { prepare(sql, run) }
    private fun <T> Connection.prepare(@Language("SQL") sql: String, run: PreparedStatement.() -> T) = prepareStatement(sql).use(run)
    private fun Connection.statement(@Language("SQL") sql: String) = createStatement().use { it.executeUpdate(sql) }
    private fun PreparedStatement.setUUID(parameterIndex: Int, uuid: UUID) = setString(parameterIndex, uuid.toString())

    override fun init() {
        try {
            connection {
                statement("CREATE TABLE IF NOT EXISTS global_stats (" +
                        "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                        "name VARCHAR(200)," +
                        "uuid VARCHAR(36)," +
                        "first_play TIMESTAMP NULL DEFAULT NULL," +
                        "last_play TIMESTAMP NULL DEFAULT NULL," +
                        "wins INT(10)," +
                        "kills INT(10)," +
                        "final_kills INT(10)," +
                        "looses INT(10)," +
                        "deaths INT(10)," +
                        "final_deaths INT(10)," +
                        "beds_destroyed INT(10)," +
                        "games_played INT(10)" +
                        ");")
                statement("CREATE TABLE IF NOT EXISTS quick_buy_2 (" +
                        "uuid VARCHAR(36) PRIMARY KEY, " +
                        "slot_19 VARCHAR(200)," +
                        "slot_20 VARCHAR(200)," +
                        "slot_21 VARCHAR(200)," +
                        "slot_22 VARCHAR(200)," +
                        "slot_23 VARCHAR(200)," +
                        "slot_24 VARCHAR(200)," +
                        "slot_25 VARCHAR(200)," +
                        "slot_28 VARCHAR(200)," +
                        "slot_29 VARCHAR(200)," +
                        "slot_30 VARCHAR(200)," +
                        "slot_31 VARCHAR(200)," +
                        "slot_32 VARCHAR(200)," +
                        "slot_33 VARCHAR(200)," +
                        "slot_34 VARCHAR(200)," +
                        "slot_37 VARCHAR(200)," +
                        "slot_38 VARCHAR(200)," +
                        "slot_39 VARCHAR(200)," +
                        "slot_40 VARCHAR(200)," +
                        "slot_41 VARCHAR(200)," +
                        "slot_42 VARCHAR(200)," +
                        "slot_43 VARCHAR(200)" +
                        ");")
                statement("CREATE TABLE IF NOT EXISTS player_levels (" +
                        "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "uuid VARCHAR(200), " +
                        "level INT(200)," +
                        "xp INT(200)," +
                        "name VARCHAR(200)," +
                        "next_cost INT(200)" +
                        ");")
                statement("CREATE TABLE IF NOT EXISTS player_language (" +
                        "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "uuid VARCHAR(200)," +
                        "iso VARCHAR(200)" +
                        ");")
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    override fun getLanguage(player: UUID): String {
        var lang = defaultLanguage.iso
        try {
            prepare("SELECT iso FROM player_language WHERE uuid = ?;") {
                setUUID(1, player)
                executeQuery().use { if (it.next()) lang = it.getString("iso") }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return lang
    }
    override fun setLanguage(player: UUID, iso: String) {
        try {
            connection {
                prepare("SELECT iso FROM player_language WHERE uuid = ?;") {
                    setUUID(1, player)
                    executeQuery().use {
                        if (it.next()) {
                            prepare("UPDATE player_language SET iso = ? WHERE uuid = ?;") {
                                setString(1, iso)
                                setUUID(2, player)
                                executeUpdate()
                            }
                            return@use
                        }
                        prepare("INSERT INTO player_language(uuid, iso) VALUES (?, ?);") {
                            setUUID(1, player)
                            setString(2, iso)
                            executeUpdate()
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    override fun hasStats(player: UUID): Boolean {
        return try {
            prepare("SELECT uuid FROM global_stats WHERE uuid = ?;") {
                setUUID(1, player)
                executeQuery().use { it.next() }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }
    override fun fetchStats(player: UUID): PlayerStats {
        val stats = PlayerStats(player)
        try {
            prepare("SELECT first_play, last_play, wins, kills, final_kills, looses, deaths, final_deaths," +
                    "beds_destroyed, games_played FROM global_stats WHERE uuid = ?;") {
                setUUID(1, player)
                executeQuery().use { result ->
                    if (result.next()) stats.apply {
                        firstPlay = result.getTimestamp("first_play")?.toInstant()
                        lastPlay = result.getTimestamp("last_play")?.toInstant()
                        wins = result.getInt("wins")
                        kills = result.getInt("kills")
                        finalKills = result.getInt("final_kills")
                        losses = result.getInt("looses")
                        deaths = result.getInt("deaths")
                        finalDeaths = result.getInt("final_deaths")
                        bedsDestroyed = result.getInt("beds_destroyed")
                        gamesPlayed = result.getInt("games_played")
                    }
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return stats
    }
    override fun saveStats(stats: PlayerStats) {
        try {
            if (hasStats(stats.uuid)) {
                prepare("UPDATE global_stats SET first_play=?, last_play=?, wins=?, kills=?, final_kills=?, looses=?, deaths=?, final_deaths=?, beds_destroyed=?, games_played=? WHERE uuid = ?;") {
                    setTimestamp(1, stats.firstPlay?.let { Timestamp.from(it) })
                    setTimestamp(2, stats.lastPlay?.let { Timestamp.from(it) })
                    setInt(3, stats.wins)
                    setInt(4, stats.kills)
                    setInt(5, stats.finalKills)
                    setInt(6, stats.losses)
                    setInt(7, stats.deaths)
                    setInt(8, stats.finalDeaths)
                    setInt(9, stats.bedsDestroyed)
                    setInt(10, stats.gamesPlayed)
//                    setString(11, stats.name)
                    setUUID(12, stats.uuid)
                    executeUpdate()
                }
            } else {
                prepare("INSERT INTO global_stats (name, uuid, first_play, last_play, wins, kills, final_kills, looses, deaths, final_deaths, beds_destroyed, games_played) VALUES ('', ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);") {
//                    setString(1, stats.name)
                    setUUID(2, stats.uuid)
                    setTimestamp(3, Timestamp.from(stats.firstPlay))
                    setTimestamp(4, Timestamp.from(stats.lastPlay))
                    setInt(5, stats.wins)
                    setInt(6, stats.kills)
                    setInt(7, stats.finalKills)
                    setInt(8, stats.losses)
                    setInt(9, stats.deaths)
                    setInt(10, stats.finalDeaths)
                    setInt(11, stats.bedsDestroyed)
                    setInt(12, stats.gamesPlayed)
                    executeUpdate()
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    override fun getLevelData(player: UUID): Array<Any> {
        var data = arrayOf<Any>(1, 0, "", 0)
        try {
            prepare("SELECT level, xp, name, next_cost FROM player_levels WHERE uuid = ?;") {
                setUUID(1, player)
                executeQuery().use {
                    if (it.next()) data = arrayOf(
                        it.getInt("level"),
                        it.getInt("xp"),
                        it.getString("name"),
                        it.getInt("next_cost")
                    )
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return data
    }
    override fun setLevelData(player: UUID, level: Int, xp: Int, displayName: String?, nextCost: Int) {
        try {
            connection {
                prepare("SELECT uuid from player_levels WHERE uuid = ?;") {
                    setUUID(1, player)
                    executeQuery().use {
                        if (!it.next()) {
                            prepare("INSERT INTO player_levels VALUES (uuid, level, xp, name, next_cost);") {
                                setUUID(1, player)
                                setInt(2, level)
                                setInt(3, xp)
                                setString(4, displayName)
                                setInt(5, nextCost)
                                executeUpdate()
                            }
                            return@use
                        }
                        val sql = if (displayName == null) "UPDATE player_levels SET level=?, xp=? WHERE uuid = ?;"
                        else "UPDATE player_levels SET level=?, xp=?, name=?, next_cost=? WHERE uuid = ?;"
                        prepare(sql) {
                            setInt(1, level)
                            setInt(2, xp)
                            if (displayName != null) {
                                setString(3, displayName)
                                setInt(4, nextCost)
                                setUUID(5, player)
                            } else {
                                setUUID(3, player)
                            }
                            executeUpdate()
                        }
                    }
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }


    override fun hasQuickBuy(player: UUID): Boolean {
        return try {
            prepare("SELECT uuid FROM quick_buy_2 WHERE uuid = ?;") {
                setUUID(1, player)
                executeQuery().use { it.next() }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            false
        }
    }
    override fun getQuickBuySlot(player: UUID, slot: Int): String {
        var result = ""
        try {
            prepare("SELECT slot_$slot FROM quick_buy_2 WHERE uuid = ?;") {
                setUUID(1, player)
                executeQuery().use { if (it.next()) result = it.getString("slot_$slot") }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return result
    }
    override fun getQuickBuySlots(player: UUID, slot: IntArray): Map<Int, String> {
        if (slot.isEmpty()) return emptyMap()
        return try {
            prepare("SELECT * FROM quick_buy_2 WHERE uuid = ?;") {
                setString(1, "$player")
                executeQuery().use { result ->
                    if (!result.next()) return@use emptyMap()
                    slot.associateWith { result.getString("slot_$it") }
                        .filter { it.value != null }
                }
            }
        } catch (e: SQLException) {
            e.printStackTrace()
            emptyMap()
        }
    }
    override fun pushQuickBuyChanges(updateSlots: MutableMap<Int, String>, player: UUID, elements: List<QuickBuyElement>) {
        if (updateSlots.isEmpty()) return

        val hasQuick = hasQuickBuy(player)
        if (!hasQuick) {
            for (element in elements) {
                if (element.isLoaded && element.slot !in updateSlots) {
                    updateSlots[element.slot] = element.categoryContent!!.identifier
                }
            }
        }
        val slots = updateSlots.keys.map { "slot_$it" }
        val sql = if (hasQuick) "UPDATE quick_buy_2 SET ${slots.joinToString(", ") { "$it=?"}} WHERE uuid=?;"
        else "INSERT INTO quick_buy_2 (uuid,${slots.joinToString(", ")}) VALUES (?,${slots.joinToString(", ") { "?" }});"

        try {
            prepare(sql) {
                var index = if (hasQuick) 0 else 1
                for ((_, value) in updateSlots) {
                    index++
                    setString(index, value.ifBlank { null })
                }
                setUUID(if (hasQuick) updateSlots.size + 1 else 1, player)
                execute()
            }
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }
}