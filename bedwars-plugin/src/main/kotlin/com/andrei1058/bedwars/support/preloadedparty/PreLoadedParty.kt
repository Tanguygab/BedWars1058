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
package com.andrei1058.bedwars.support.preloadedparty

import com.andrei1058.bedwars.BedWars
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.concurrent.ConcurrentHashMap

class PreLoadedParty(private val owner: String) {
    private val members = mutableListOf<Player>()

    init {
        preLoadedParties[owner] = this
    }

    operator fun plusAssign(player: Player) {
        members += player
    }

    fun teamUp() {
        val owner = Bukkit.getPlayer(owner) ?: return
        if (!owner.isOnline) return

        for (player in members) {
            if (!player.name.equals(this.owner, ignoreCase = true)) {
                BedWars.INSTANCE.partyUtil.addMember(owner, player)
            }
        }
        preLoadedParties.remove(this.owner)
    }

    companion object {
        private val preLoadedParties = ConcurrentHashMap<String, PreLoadedParty>()
        fun getPartyByOwner(name: String) = preLoadedParties[name]
        fun remove(owner: String?) = preLoadedParties.remove(owner)
    }
}
