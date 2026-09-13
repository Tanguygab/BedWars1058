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
package com.andrei1058.bedwars.arena.tasks

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.api.events.gameplay.EggBridgeBuildEvent
import com.andrei1058.bedwars.configuration.Sounds
import com.andrei1058.bedwars.listeners.EggBridge
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Egg
import org.bukkit.entity.Player
import org.bukkit.util.Vector

class EggBridgeTask(
    val arena: IArena,
    val player: Player,
    val projectile: Egg,
    val teamColor: TeamColor
) : Runnable {
    private val task = Bukkit.getScheduler().runTaskTimer(BedWars.plugin, this, 0, 1)

    private val deltas = arrayOf(
        Vector(0.0, 2.0, 0.0),
        Vector(1.0, 2.0, 0.0),
        Vector(0.0, 2.0, 1.0)
    )

    override fun run() {
        val loc = projectile.location

        if (projectile.isDead ||
            !arena.isPlayer(player) ||
            player.location.distance(projectile.location) > 27 ||
            player.location.y - projectile.location.y > 9
        ) {
            EggBridge.removeEgg(projectile)
            return
        }

        if (player.location.distance(loc) <= 4.0) return

        deltas.forEach { placeBlock(loc.clone().subtract(it)) }
    }

    private fun placeBlock(location: Location) {
        if (arena.isProtected(location)) return

        val block = location.block
        if (block.type != Material.AIR) return

        block.type = BedWars.nms.woolMaterial()
        BedWars.nms.setBlockTeamColor(block, teamColor)
        arena.addPlacedBlock(block)
        Bukkit.getPluginManager().callEvent(EggBridgeBuildEvent(teamColor, arena, block))
        location.world!!.playEffect(location, BedWars.nms.eggBridge, 3)
        Sounds.playSound("egg-bridge-block", player)
    }

    fun cancel() {
        task.cancel()
    }
}
