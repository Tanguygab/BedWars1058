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

import com.andrei1058.bedwars.arena.generators.GeneratorOre
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.upgrades.UpgradeAction
import com.andrei1058.bedwars.arena.generators.Generator
import org.bukkit.entity.Player

class GeneratorEditAction(
    private val type: ApplyType,
    private val amount: Int,
    private val delay: Int,
    private val limit: Int
) : UpgradeAction {
    override fun onBuy(player: Player, team: ITeam) {
        val gens = team.generators
        val generators = when (type) {
            ApplyType.IRON -> gens.filter { it.type == GeneratorOre.IRON }
            ApplyType.GOLD -> gens.filter { it.type == GeneratorOre.GOLD }
            ApplyType.EMERALD -> {
                var emeralds = team.arena.config.getArenaLocations("Team.${team.name}.Emerald")
                if (emeralds.isEmpty()) emeralds = listOf(gens[0].location.clone())
                emeralds.map {
                    Generator(it, team.arena, GeneratorOre.EMERALD, team).also { team.generators += it }
//                        team.arena.oreGenerators.add(gen)
                }
            }
        }
        for (g in generators) {
            g.amount = amount
            g.delay = delay
            g.spawnLimit = limit
        }
    }


    enum class ApplyType {
        IRON, GOLD, EMERALD
    }
}
