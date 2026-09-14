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
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.configuration.Sounds
import net.citizensnpcs.api.CitizensAPI
import net.citizensnpcs.api.event.NPCRemoveEvent
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEntityEvent

class CitizensListener(private val plugin: BedWars) : Listener {
    @EventHandler
    fun removeNPC(e: NPCRemoveEvent) {
        val npc = e.npc
        if (npc?.entity == null) return

        val locations = plugin.mainConfig.getStringList(ConfigPath.GENERAL_CONFIGURATION_NPC_LOC_STORAGE).toMutableList()

        var removed = false
        if (npc.id in JoinNPC.npcs) {
            JoinNPC.npcs -= npc.id
            removed = true
        }

        for (s in locations.toList()) {
            val data = s.split(",")
            if (data.size >= 10 && data[9].toIntOrNull() == npc.id) {
                locations -= s
                removed = true
            }
        }

        for (e2 in npc.entity.getNearbyEntities(0.0, 3.0, 0.0)) {
            if (e2.type == EntityType.ARMOR_STAND) {
                e2.remove()
            }
        }

        if (removed) plugin.mainConfig.set(ConfigPath.GENERAL_CONFIGURATION_NPC_LOC_STORAGE, locations)
    }

    @EventHandler // Citizens support
    fun onNPCInteract(e: PlayerInteractEntityEvent) {
        if (!JoinNPC.isCitizensSupport) return

        val player = e.player
        if (player.isSneaking || !e.rightClicked.hasMetadata("NPC")) return
        val npc = CitizensAPI.getNPCRegistry().getNPC(e.rightClicked) ?: return

        if (npc.id !in JoinNPC.npcs) return
        val sound = if (!plugin.arenaManager.joinRandomFromGroup(player, JoinNPC.npcs[npc.id]!!)) {
            player.sendLangMsg(Messages.COMMAND_JOIN_NO_EMPTY_FOUND)
            "join-denied"
        } else "join-allowed"
        Sounds.playSound(sound, player)
    }
}
