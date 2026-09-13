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
package com.andrei1058.bedwars.halloween

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.halloween.shop.PumpkinContent
import com.andrei1058.bedwars.shop.ShopManager
import org.bukkit.ChatColor
import java.time.ZonedDateTime

object HalloweenSpecial {
    var enabled = false
        private set

    /**
     * Initialize Halloween Special.
     */
    fun init(plugin: BedWars) {
        val enable = BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_ENABLE_HALLOWEEN)
        plugin.metrics.appendPie("halloween_special_enable") { "$enable" }

        if (!enable || enabled || !checkAvailabilityDate()) return
        enabled = true

        plugin.logger.info("${ChatColor.AQUA}Loaded Halloween Special <3")
        // pumpkin hats
        plugin.registerEvents(HalloweenListener())

        // pumpkin in shop
        val blockCategory = ShopManager.shop.categoryList.find { it.name == "blocks-category" }
        if (blockCategory == null) return

        val content = PumpkinContent(blockCategory)
        if (!content.isLoaded) return

        blockCategory.categoryContentList += content
    }

    fun checkAvailabilityDate(): Boolean {
        // check date
        val date = ZonedDateTime.now()
        val month = date.monthValue
        val day = date.dayOfMonth

        // allowed between October 21 and November 1
        return (month == 10 && day > 21 || month == 11 && day < 2)
    }
}
