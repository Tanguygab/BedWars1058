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
package com.andrei1058.bedwars.listeners

import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.support.version.common.VersionCommon
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

@Suppress("ClassName")
class Interact_1_13Plus : Listener {
    @EventHandler(ignoreCancelled = true) //Check if player is opening an inventory
    fun onInventoryInteract(e: PlayerInteractEvent) {
        if (e.action != Action.RIGHT_CLICK_BLOCK) return
        val block = e.clickedBlock ?: return
        val api = VersionCommon.api
        if (block.world.name != api.lobbyWorld && api.arenaManager.getArena(e.player) == null) return

        if (block.type != Material.CHIPPED_ANVIL &&
            block.type != Material.DAMAGED_ANVIL
        ) return

        if (
            api.configs.mainConfig.getBoolean(ConfigPath.GENERAL_CONFIGURATION_DISABLE_ANVIL)
            || api.arenaManager.isSpectating(e.player)
        ) e.isCancelled = true
    }
}
