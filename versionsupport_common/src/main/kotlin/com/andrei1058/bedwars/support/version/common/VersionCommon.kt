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
package com.andrei1058.bedwars.support.version.common

import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.server.VersionSupport
import com.andrei1058.bedwars.listeners.Interact_1_13Plus
import com.andrei1058.bedwars.listeners.ItemDropPickListener
import com.andrei1058.bedwars.listeners.ItemDropPickListener.ArrowCollect
import com.andrei1058.bedwars.listeners.SwapItem
import com.andrei1058.bedwars.shop.defaultrestore.ShopItemRestoreListener
import com.andrei1058.bedwars.shop.defaultrestore.ShopItemRestoreListener.DefaultRestoreInvClose
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableFactory
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

class VersionCommon(versionSupport: VersionSupport) {
    lateinit var despawnableFactory: DespawnableFactory
    private val listeners = mutableListOf<Listener>()

    init {
        api = Bukkit.getServicesManager().getRegistration(BedWars::class.java)!!.getProvider()

        // 9 and newer
        val v = versionSupport.version
        if (v > 1) listeners += arrayOf(SwapItem(), ArrowCollect())

        listeners += if (v < 5) arrayOf( // 11 and older
            ItemDropPickListener.PlayerPickup(),
            ShopItemRestoreListener.PlayerPickup()
        ) else arrayOf( // 1.12 and newer
            ItemDropPickListener.EntityPickup(),
            ShopItemRestoreListener.EntityPickup()
        )

        // 13 and newer
        if (v > 5) listeners += arrayOf(
            Interact_1_13Plus(),
            ShopItemRestoreListener.EntityDrop(),
            ItemDropPickListener.EntityDrop()
        )

        // 1.12 drop listeners
        //if (v == 5)
        // common
        listeners += arrayOf(
            ItemDropPickListener.PlayerDrop(),
            ShopItemRestoreListener.PlayerDrop()
        )

        // 1.19+
        if (v >= 9) despawnableFactory = DespawnableFactory(versionSupport)

        // common
        listeners += DefaultRestoreInvClose()
    }

    fun registerListeners(plugin: Plugin) {
        listeners.forEach { plugin.server.pluginManager.registerEvents(it, plugin) }
    }

    companion object {
        lateinit var api: BedWars
    }
}
