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

import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.party.Party
import org.bukkit.entity.Player

class Internal : Party {
    override val isInternal = true

    override fun hasParty(player: Player) = parties.any { player in it }

    private fun getParty(owner: Player) = parties.find { owner == it.owner }
    override fun createParty(owner: Player, vararg members: Player) {
        parties += Party(owner, *members)
    }
    override fun disband(owner: Player) {
        val pa = getParty(owner) ?: return
        for (p in pa.members) {
            p.sendLangMsg(Messages.COMMAND_PARTY_DISBAND_SUCCESS)
        }
        pa.members.clear()
        parties.remove(pa)
    }

    override fun partySize(player: Player) = parties.find { player in it }?.members?.size ?: 0
    override fun isOwner(player: Player) = parties.any { player == it.owner }
    override fun isMember(owner: Player, player: Player) = parties.find { owner == it.owner }?.contains(player) == true
    override fun getOwner(member: Player) = parties.find { member in it }?.owner
    override fun getMembers(member: Player) = parties.find { member in it }?.members ?: emptyList()

    override fun addMember(owner: Player, member: Player) {
        val p = getParty(owner) ?: return
        p += member
    }
    override fun promote(owner: Player, member: Player) {
        getParty(owner)?.owner = member
    }
    override fun removeMember(owner: Player, member: Player) {
        val party = getParty(owner) ?: return
        if (member !in party) return

        for (mem in party.members) {
            mem.sendLangMsg(
                Messages.COMMAND_PARTY_REMOVE_SUCCESS,
                "{player}" to member.name
            )
        }
        party.members.remove(owner)
        if (party.members.isEmpty() || party.members.size == 1) {
            disband(party.owner)
        }
    }
    override fun removeFromParty(member: Player) {
        val party = parties.find { member in it } ?: return

        if (member == party.owner) {
            disband(member)
            return
        }

        for (member in party.members) {
            member.sendLangMsg(
                Messages.COMMAND_PARTY_LEAVE_SUCCESS,
                "{playername}" to member.name,
                "{player}" to member.displayName
            )
        }
        party.members.remove(member)
        if (party.members.size <= 1) disband(party.owner)
    }

    internal class Party(var owner: Player, vararg members: Player) {
        val members = mutableListOf(owner, *members)

        operator fun plusAssign(p: Player) {
            members.add(p)
        }
        operator fun contains(p: Player) = p in members
    }

    companion object {
        private val parties = mutableListOf<Party>()
    }
}
