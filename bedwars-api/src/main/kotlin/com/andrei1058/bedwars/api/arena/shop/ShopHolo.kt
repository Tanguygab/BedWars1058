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
package com.andrei1058.bedwars.api.arena.shop

import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.language.Language
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player

class ShopHolo(
    private val iso: String,
    private val a1: ArmorStand?,
    private val a2: ArmorStand?,
    private val l: Location?,
    val a: IArena?
) {

    init {
        var removed = false
        for (sh in shopHolo) {
            if (sh.l === l && sh.iso.equals(iso, ignoreCase = true)) {
                a1?.remove()
                a2?.remove()
                removed = true
                break
            }
        }
        if (!removed) {
            a1?.isMarker = true
            a2?.isMarker = true

            shopHolo += this

            if (api == null) api = Bukkit
                .getServer()
                .servicesManager
                .getRegistration(BedWars::class.java)!!
                .provider
        }
    }

    fun update() {
        if (l == null) {
            Bukkit.broadcastMessage("LOCATION IS NULL")
            return
        }

        l.world!!.players.forEach { updateForPlayer(it, Language.getLanguage(it).iso) }
    }

    fun updateForPlayer(p: Player, lang: String) {
        if (lang.equals(iso, ignoreCase = true)) return
        if (a1 != null) api!!.versionSupport.hideEntity(a1, p)
        if (a2 != null) api!!.versionSupport.hideEntity(a2, p)
    }

    companion object {
        /**
         * Shop holograms per language <iso></iso>, holo>,>
         */
        val shopHolo = mutableListOf<ShopHolo>()
        private var api: BedWars? = null

        fun clearForArena(arena: IArena) {
            for (sh in shopHolo) {
                if (sh.a === arena) {
                    shopHolo.remove(sh)
                }
            }
        }
    }
}
