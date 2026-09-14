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
package com.andrei1058.bedwars.levels.internal

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.events.player.PlayerLevelUpEvent
import com.andrei1058.bedwars.api.events.player.PlayerXpGainEvent
import org.bukkit.ChatColor
import java.text.NumberFormat
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class PlayerLevel private constructor(
    /**
     * Get player uuid.
     */
    val uuid: UUID,
    level: Int,
    currentXp: Int
) {

    /**
     * Get player level as int.
     */
    var level = level.coerceAtLeast(1)
        set(value) {
            field = value
            val config = BedWars.INSTANCE.levelsConfig
            nextLevelCost = config.getNextCost(value)
            levelName = ChatColor.translateAlternateColorCodes('&', config.getLevelName(value)).replace("{number}", "$value")
            formattedRequiredXp = if (nextLevelCost >= 1000)
                if (nextLevelCost % 1000 == 0) "${nextLevelCost / 1000}k"
                else "${nextLevelCost.toDouble() / 1000}k"
            else "$nextLevelCost"
            updateProgressBar()
            modified = true
        }
    /**
     * Get the amount of xp required to level up.
     */
    var nextLevelCost = 0
        private set

    /**
     * Get player current level display name.
     */
    lateinit var levelName: String
        private set

    /**
     * Get player xp.
     */
    var currentXp = currentXp.coerceAtLeast(0)
        private set

    /**
     * Get progress bar for player.
     */
    lateinit var progress: String
        private set

    /**
     * Get target xp already formatted.
     * Like: 2000 is 2k
     */
    lateinit var formattedRequiredXp: String
        private set

    /**
     * Get player xp already formatted.
     * Like: 1000 is 1k
     */
    lateinit var formattedCurrentXp: String
        private set

    // keep trace if current level is different from the one in database
    private var modified = false

    /**
     * Cache a player level.
     */
    init {
        setLevelName(level)
        setNextLevelCost(level, true)
        updateProgressBar()
        //requiredXp = nextLevelCost >= 1000 ? nextLevelCost % 1000 == 0 ? nextLevelCost / 1000 + "k" : (double) nextLevelCost / 1000 + "k" : String.valueOf(nextLevelCost);
        //formattedCurrentXp = currentXp >= 1000 ? currentXp % 1000 == 0 ? currentXp / 1000 + "k" : (double) currentXp / 1000 + "k" : String.valueOf(currentXp);
    }

    fun setLevelName(level: Int) {
        levelName = ChatColor.translateAlternateColorCodes('&', BedWars.INSTANCE.levelsConfig.getLevelName(level))
            .replace("{number}", "$level")
    }

    fun setNextLevelCost(level: Int, initialize: Boolean) {
        if (!initialize) modified = true
        nextLevelCost = BedWars.INSTANCE.levelsConfig.getNextCost(level)
    }

    fun lazyLoad(level: Int, currentXp: Int) {
        modified = false
        this.level = level.coerceAtLeast(1)
        this.currentXp = currentXp.coerceAtLeast(0)
        setLevelName(this.level)
        setNextLevelCost(this.level, true)
        updateProgressBar()

        modified = false
    }

    /**
     * Update the player progress bar.
     */
    private fun updateProgressBar() {
        val l1 = (nextLevelCost - currentXp) / (nextLevelCost).toDouble() * 10
        var locked = l1.toInt()
        var unlocked = 10 - locked
        if (locked < 0 || unlocked < 0) {
            locked = 10
            unlocked = 0
        }
        val config = BedWars.INSTANCE.levelsConfig
        val symbol = config.getString("progress-bar.symbol") ?: ""
        val unlockedColor = config.getString("progress-bar.unlocked-color") ?: ""
        val lockedColor = config.getString("progress-bar.locked-color") ?: ""
        val format = config.getString("progress-bar.format") ?: ""
        val progress = unlockedColor + symbol.repeat(unlocked) + lockedColor + symbol.repeat(locked)
        this.progress = ChatColor.translateAlternateColorCodes(
            '&',
            format.replace("{progress}", progress)
        )
        formattedRequiredXp = formatNumber(nextLevelCost)
        formattedCurrentXp = formatNumber(currentXp)
    }


    /**
     * Add xp to player with source.
     */
    fun addXp(xp: Int, source: PlayerXpGainEvent.XpSource) {
        if (xp < 0) return
        val plugin = BedWars.INSTANCE
        plugin.server.pluginManager.callEvent(PlayerXpGainEvent(plugin.server.getPlayer(uuid)!!, xp, source))
        setXp(currentXp + xp)
    }

    /**
     * Set player xp.
     */
    fun setXp(xp: Int) {
        currentXp = xp.coerceAtLeast(0)
        upgradeLevel()
        updateProgressBar()
        modified = true
    }

    /**
     * Used to upgrade player level.
     */
    fun upgradeLevel() {
        if (currentXp < nextLevelCost) return

        currentXp -= nextLevelCost
        level++
        val plugin = BedWars.INSTANCE
        nextLevelCost = plugin.levelsConfig.getNextCost(level)
        levelName = ChatColor.translateAlternateColorCodes('&', plugin.levelsConfig.getLevelName(level))
            .replace("{number}", "$level")
        formattedRequiredXp = formatNumber(nextLevelCost)
        formattedCurrentXp = formatNumber(currentXp)
        plugin.server.pluginManager.callEvent(PlayerLevelUpEvent(
            plugin.server.getPlayer(uuid)!!,
            level,
            nextLevelCost
        ))
        modified = true
    }

    private fun formatNumber(score: Int) = NumberFormat.getInstance().run {
        setMaximumFractionDigits(2)
        setMinimumFractionDigits(0)
        if (score >= 1000) format(score / 1000.0) + "k"
        else format(score.toLong())
    }

    /**
     * Destroy data.
     */
    fun destroy() {
        levelByPlayer.remove(uuid)
        val plugin = BedWars.INSTANCE
        plugin.database.setLevelData(
            uuid,
            level,
            currentXp,
            plugin.levelsConfig.getString("levels.$level.name")
                ?: plugin.levelsConfig.getString("levels.others.name"),
            nextLevelCost
        )
        updateDatabase()
    }

    fun updateDatabase() {
        if (!modified) return
        val plugin = BedWars.INSTANCE
        plugin.run(async = true) {
            plugin.database.setLevelData(
                uuid, level, currentXp,
                plugin.levelsConfig.getLevelName(level),
                nextLevelCost
            )
        }
        modified = false
    }

    companion object {
        private val levelByPlayer = ConcurrentHashMap<UUID, PlayerLevel>()


        /**
         * Get PlayerLevel by player.
         */
        fun getLevelByPlayer(player: UUID) = levelByPlayer.computeIfAbsent(player) { PlayerLevel(player, 1, 0) }
    }
}
