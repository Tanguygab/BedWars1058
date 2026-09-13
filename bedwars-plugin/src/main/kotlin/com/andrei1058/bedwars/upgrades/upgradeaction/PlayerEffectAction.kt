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
package com.andrei1058.bedwars.upgrades.upgradeaction

import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.upgrades.UpgradeAction
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffectType

class PlayerEffectAction(
    private val potionEffectType: PotionEffectType,
    private val amplifier: Int,
    private var duration: Int,
    private val type: ApplyType
) : UpgradeAction {
    init {
        if (duration < 0) duration *= -1
        /*if (type == ApplyType.ENEMY_BASE_ENTER && duration <= 0){
            this.duration = 20;
        }*/
        if (duration == 0) duration = Int.MAX_VALUE
        else duration *= 20
    }


    override fun onBuy(player: Player, team: ITeam) {
        when (type) {
            ApplyType.BASE -> team.addBaseEffect(potionEffectType, amplifier, duration)
            ApplyType.TEAM -> team.addTeamEffect(potionEffectType, amplifier, duration)
        }
    }

    enum class ApplyType {
        TEAM, BASE
    }
}
