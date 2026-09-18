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
package com.andrei1058.bedwars.support.citizens

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.commands.subcmds.sensitive.NPCCommand
import net.citizensnpcs.api.CitizensAPI
import net.citizensnpcs.api.npc.NPC
import net.citizensnpcs.npc.skin.SkinnableEntity
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.event.player.PlayerTeleportEvent

class CitizensSupport(private val plugin: BedWars) {

    /* Here are stored NPC holograms without colors and placeholders translated used for refresh*/
    var holograms = mutableMapOf<ArmorStand, List<String>>()

    /* Here are stored all the NPCs*/
    var npcs = mutableMapOf<Int, String>()


    /**
     * Spawn all the CmdJoin-NPCs
     */
    init {
        val locations = plugin.mainConfig.getStringList(ConfigPath.GENERAL_CONFIGURATION_NPC_LOC_STORAGE)
        for (s in locations) {
            val data = s.split(",")
            if (data.size < 10) continue
            val location = Location(
                Bukkit.getWorld(data[5]),
                data[0].toDoubleOrNull() ?: continue,
                data[1].toDoubleOrNull() ?: continue,
                data[2].toDoubleOrNull() ?: continue,
                data[3].toFloatOrNull() ?: continue,
                data[4].toFloatOrNull() ?: continue
            )
            val skin = data[6]
            val name = data[7]
            val group = data[8]
            val id = data[9].toIntOrNull() ?: continue
            val npc = CitizensAPI.getNPCRegistry().getById(id)
            if (npc == null) {
                plugin.logger.severe("Invalid npc id: $id")
                continue
            }
            spawnNPC(location, name, group, skin, npc)
        }
    }

    /**
     * Spawn a join-NPC
     *
     * @param group Arena Group
     * @param l     Location where to be spawned
     * @param name  Display name
     * @param skin  A player name to get his skin
     */
    fun spawnNPC(l: Location, name: String, group: String, skin: String, spawnExisting: NPC?): NPC {
        val npc = spawnExisting ?: CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "")
        return npc.apply {
            if (!isSpawned) spawn(l)

            (entity as? SkinnableEntity)?.skinName = skin
            isProtected = true
            this.name = ""

            for (e in l.world!!.getNearbyEntities(l, 1.0, 3.0, 1.0)) {
                if (e.type == EntityType.ARMOR_STAND) e.remove()
            }

            teleport(l, PlayerTeleportEvent.TeleportCause.PLUGIN)
            npcs[id] = group

            val positions = mutableListOf(.25)
            val name = name.split("\\n")
            if (name.size > 1) positions.addFirst(.05)

            positions.forEachIndexed { index, y ->
                NPCCommand.createArmorStand(l.clone().add(.0, y, .0)).run {
                    isMarker = false
                    isCustomNameVisible = true
                    customName = ChatColor.translateAlternateColorCodes('&', name[index])
                        .replace("{players}", plugin.arenaManager.getPlayers(group).toString())
                    holograms[this] = listOf(group, name[index])
                }
            }
        }
    }

    /**
     * Update CmdJoin NPCs for a group
     * 
     * @param group arena group
     */
    fun updateNPCs(group: String) {
        val players = plugin.arenaManager.getPlayers(group).toString()
        for ((key, value) in holograms) {
            if (!value[0].equals(group, ignoreCase = true) || key.isDead) continue
            key.customName = ChatColor.translateAlternateColorCodes('&', value[1].replace("{players}", players))
        }
    }
}
