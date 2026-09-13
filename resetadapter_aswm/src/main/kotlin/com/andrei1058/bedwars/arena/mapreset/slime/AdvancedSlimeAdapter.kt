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
package com.andrei1058.bedwars.arena.mapreset.slime

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.server.ISetupSession
import com.grinderwolf.swm.api.world.SlimeWorld
import org.bukkit.Bukkit
import org.bukkit.event.world.WorldInitEvent
import org.bukkit.event.world.WorldLoadEvent
import org.bukkit.plugin.Plugin

class AdvancedSlimeAdapter(plugin: Plugin) : SlimeAdapter(
    plugin,
    "SlimeWorldManager",
    "Advanced Slime World Manager by Paul19988"
) {
    override fun generateWorld(arena: IArena?, session: ISetupSession?, world: SlimeWorld) {
        super.generateWorld(arena, session, world)
        val w = Bukkit.getWorld(arena?.name ?: session!!.worldName)
        if (w == null) {
            if (arena != null) {
                api.arenaManager.removeFromEnableQueue(arena)
                log.severe("Something wrong... removing arena ${arena.name} from queue.")
            }
            return
        }
        server.pluginManager.callEvent(WorldInitEvent(w))
        run { server.pluginManager.callEvent(WorldLoadEvent(w)) }
    }

    override fun deleteWorld(name: String) {
        run(async = true) { super.deleteWorld(name) }
    }

    override fun cloneArena(name1: String, name2: String) {
        run(async = true) { super.cloneArena(name1, name2) }
    }

}
