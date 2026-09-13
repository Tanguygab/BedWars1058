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
package com.andrei1058.bedwars.upgrades.trapaction

import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.upgrades.TrapAction
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class PlayerEffectAction(
    private val potionEffectType: PotionEffectType,
    private val amplifier: Int,
    private var duration: Int,
    private val type: ApplyType
) : TrapAction {
    override val name = "player-effect"

    init {
        if (duration < 0) duration *= -1
        /*if (type == ApplyType.ENEMY_BASE_ENTER && duration <= 0){
            this.duration = 20;
        }*/
        if (duration == 0)
            duration = Int.MAX_VALUE
        else duration *= 20
    }

    override fun onTrigger(player: Player, playerTeam: ITeam, targetTeam: ITeam) {
        when (type) {
            ApplyType.TEAM -> targetTeam.members
            ApplyType.BASE -> targetTeam.members.filter { it.location.distance(targetTeam.bed) <= targetTeam.arena.islandRadius }
            ApplyType.ENEMY -> listOf(player)
        }.forEach { it.addPotionEffect(PotionEffect(potionEffectType, duration, amplifier), true) }
    }

    enum class ApplyType {
        TEAM, BASE, ENEMY
    }
}
