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
import org.bukkit.ChatColor
import org.bukkit.Location
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
            setMetadata("bw1058-setup", FixedMetadataValue(BedWars.INSTANCE, "hologram"))
            if (configLoc != null) {
                setMetadata("bw1058-loc", FixedMetadataValue(BedWars.INSTANCE, configLoc))
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
}
