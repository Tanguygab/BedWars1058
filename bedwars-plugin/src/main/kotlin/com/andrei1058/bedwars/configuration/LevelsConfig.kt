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
package com.andrei1058.bedwars.configuration

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigManager

class LevelsConfig(plugin: BedWars) : ConfigManager(plugin, "levels", plugin.dataFolder.path) {

    /**
     * Initialize levels config.
     */
    init {
        options().copyDefaults(true)
        if (isFirstTime) {
            addDefault("levels.1.name", "&7[{number}✩] ")
            addDefault("levels.1.rankup-cost", 1000)

            addDefault("levels.2.name", "&7[{number}✩] ")
            addDefault("levels.2.rankup-cost", 2000)

            addDefault("levels.3.name", "&7[{number}✩] ")
            addDefault("levels.3.rankup-cost", 3000)

            addDefault("levels.4.name", "&7[{number}✩] ")
            addDefault("levels.4.rankup-cost", 3500)
            addDefault("levels.5-10.name", "&e[{number}✩] ")
            addDefault("levels.5-10.rankup-cost", 5000)

            addDefault("levels.others.name", "&7[{number}✩] ")
            addDefault("levels.others.rankup-cost", 5000)
        }

        addDefault("xp-rewards.per-minute", 10)
        addDefault("xp-rewards.per-teammate", 5)
        addDefault("xp-rewards.game-win", 100)
        addDefault("xp-rewards.bed-destroyed", 15)
        addDefault("xp-rewards.regular-kill", 10)
        addDefault("xp-rewards.final-kill", 15)

        addDefault("progress-bar.symbol", "■")
        addDefault("progress-bar.unlocked-color", "&b")
        addDefault("progress-bar.locked-color", "&7")
        addDefault("progress-bar.format", "&8 [{progress}&8]")

        save()
    }

    private fun <T> getLevelData(level: Int, get: (String) -> T?): T {
        for (key in getConfigurationSection("levels")!!.getKeys(false)) {
            if ("-" !in key) continue

            val nrs = key.split("-")
            if (nrs.size != 2) continue
            val nr1 = nrs[0].toIntOrNull() ?: continue
            val nr2 = nrs[1].toIntOrNull() ?: continue

            if (level in nr1..nr2) {
                return get("levels.$key") ?: break
            }
        }
        return get("levels.others")!!
    }

    fun getLevelName(level: Int): String {
        val name = getString("levels.$level.name")
        if (name != null) return name

        return getLevelData(level) { getString("$it.name") }
    }

    fun getNextCost(level: Int): Int {
        if (contains("levels.$level.rankup-cost", true))
            return getInt("levels.$level.rankup-cost")

        return getLevelData(level) { getInt("$it.rankup-cost") }
    }
}
