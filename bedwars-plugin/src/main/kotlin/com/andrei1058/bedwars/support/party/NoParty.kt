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
package com.andrei1058.bedwars.support.party

import com.andrei1058.bedwars.api.party.Party
import org.bukkit.entity.Player

class NoParty : Party {
    override val isInternal = false

    override fun hasParty(player: Player) = false

    override fun createParty(owner: Player, vararg members: Player) {}
    override fun disband(owner: Player) {}

    override fun partySize(player: Player) = 0
    override fun isOwner(player: Player) = false
    override fun isMember(owner: Player, player: Player) = false
    override fun getOwner(member: Player): Player? = null
    override fun getMembers(member: Player) = emptyList<Player>()

    override fun addMember(owner: Player, member: Player) {}
    override fun promote(owner: Player, member: Player) {}
    override fun removeMember(owner: Player, member: Player) {}
    override fun removeFromParty(member: Player) {}
}
