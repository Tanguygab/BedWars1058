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
package com.andrei1058.bedwars.commands

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.arena.SetupSession
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.metadata.FixedMetadataValue

object Misc {
    /**
     * This is used to spawn armorStands during the setup
     * so the player knows what he set
     * 
     * @since api v6
     */
    fun createArmorStand(name: String, location: Location, configLoc: String?) {
        (location.world!!.spawnEntity(location.block.location.add(0.5, 2.0, 0.5), EntityType.ARMOR_STAND) as ArmorStand).apply {
            isVisible = false
            isMarker = true
            setGravity(false)
            isCustomNameVisible = true
            customName = name
            setMetadata("bw1058-setup", FixedMetadataValue(BedWars.plugin, "hologram"))
            if (configLoc != null) {
                setMetadata("bw1058-loc", FixedMetadataValue(BedWars.plugin, configLoc))
            }
        }
    }

    /**
     * Remove an armor stand
     */
    fun removeArmorStand(contains: String?, location: Location, configLoc: String?) {
        for (e in location.getWorld()!!.getNearbyEntities(location, 1.0, 3.0, 1.0)) {
            if (e.hasMetadata("bw1058-setup")) {
                if (!e.hasMetadata("bw1058-loc")) {
                    e.remove()
                    continue
                }
                if (e.getMetadata("bw1058-loc")[0].asString().equals(configLoc, ignoreCase = true)) {
                    if (!contains.isNullOrEmpty() && contains in ChatColor.stripColor(e.customName)!!) {
                        e.remove()
                        return
                    }
                    e.remove()
                }
                continue
            }
            if (e is ArmorStand && !e.isVisible && !contains.isNullOrEmpty() && contains in e.customName!!)
                e.remove()
        }
    }

    /**
     * @param origin block location under player
     */
    fun detectGenerators(origin: Location, setupSession: SetupSession) {
        val origin = origin.block.location
        setupSession.skipAutoCreateGen += origin
        val target = origin.block.type
        val layout_z_minus = origin.clone().add(0.0, 1.0, -1.0).block.type
        val layout_z_plus = origin.clone().add(0.0, 1.0, 1.0).block.type
        val layout_x_minus = origin.clone().add(-1.0, 1.0, 0.0).block.type
        val layout_x_plus = origin.clone().add(1.0, 1.0, 0.0).block.type
        val layout_x_plus_z_plus = origin.clone().add(1.0, 1.0, 1.0).block.type
        val layout_x_plus_z_minus = origin.clone().add(1.0, 1.0, -1.0).block.type
        val layout_x_minus_z_plus = origin.clone().add(-1.0, 1.0, 1.0).block.type
        val layout_x_minus_z_minus = origin.clone().add(-1.0, 1.0, -1.0).block.type

        val path = "generator." + (if (target == Material.DIAMOND_BLOCK) "Diamond" else "Emerald")
        if (layout_z_minus == Material.AIR || layout_z_plus == Material.AIR || layout_x_minus == Material.AIR || layout_x_plus == Material.AIR || layout_x_plus_z_plus == Material.AIR || layout_x_plus_z_minus == Material.AIR || layout_x_minus_z_plus == Material.AIR || layout_x_minus_z_minus == Material.AIR) {
            //It's better not to use it
            return
        }
        val locations = setupSession.config.getArenaLocations(path)
        for (x in -150..149) {
            for (z in -150..149) {
                val b = origin.clone().add(x.toDouble(), 0.0, z.toDouble()).block
                if (b.x == origin.blockX && b.y == origin.blockY && b.z == origin.blockZ) continue
                val l = b.location.clone().add(0.0, 1.0, 0.0)
                for (location in locations) setupSession.config
                    .compareArenaLoc(location, b.location.add(0.0, 1.0, 0.0))

                if (b.type == target) {
                    if (layout_z_minus == l.clone().add(0.0, 0.0, -1.0).block
                            .type && layout_z_plus == l.clone().add(0.0, 0.0, 1.0).block
                            .type && layout_x_minus == l.clone().add(-1.0, 0.0, 0.0).block
                            .type && layout_x_plus == l.clone().add(1.0, 0.0, 0.0).block
                            .type && layout_x_plus_z_minus == l.clone().add(1.0, 0.0, -1.0).block
                            .type && layout_x_plus_z_plus == l.clone().add(1.0, 0.0, 1.0).block
                            .type && layout_x_minus_z_plus == l.clone().add(-1.0, 0.0, 1.0).block
                            .type && layout_x_minus_z_minus == l.clone().add(-1.0, 0.0, -1.0).block.type
                    ) {
                        if (l !in setupSession.skipAutoCreateGen) {
                            setupSession.skipAutoCreateGen += l
                            detectGenerators(b.location, setupSession)
                        }
                    }
                }
            }
        }
    }
}
