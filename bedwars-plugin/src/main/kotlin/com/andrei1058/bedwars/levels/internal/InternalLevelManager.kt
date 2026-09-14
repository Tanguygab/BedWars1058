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

import com.andrei1058.bedwars.api.events.player.PlayerXpGainEvent.XpSource
import com.andrei1058.bedwars.api.levels.LevelManager
import org.bukkit.entity.Player

class InternalLevelManager : LevelManager {
    private val Player.lvl get() = PlayerLevel.getLevelByPlayer(uniqueId)

    override fun getLevel(player: Player) = player.lvl.levelName
    override fun getPlayerLevel(player: Player) = player.lvl.level
    override fun getRequiredXpFormatted(player: Player) = player.lvl.formattedRequiredXp
    override fun getProgressBar(player: Player) = player.lvl.progress
    override fun getCurrentXp(player: Player) = player.lvl.currentXp
    override fun getCurrentXpFormatted(player: Player) = player.lvl.formattedCurrentXp
    override fun getRequiredXp(player: Player) = player.lvl.nextLevelCost

    override fun addXp(player: Player, xp: Int, source: XpSource) {
        player.lvl.addXp(xp, source)
    }

    override fun setXp(player: Player, currentXp: Int) {
        player.lvl.setXp(currentXp)
    }

    override fun setLevel(player: Player, level: Int) {
        player.lvl.level = level
    }
}
