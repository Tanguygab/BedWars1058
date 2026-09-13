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

import com.alessiodp.parties.api.Parties
import com.alessiodp.parties.api.interfaces.PartiesAPI
import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class PartiesAdapter : com.andrei1058.bedwars.api.party.Party {
    //Support for Parties by AlessioDP
    private val api: PartiesAPI = Parties.getApi()
    private val requiredRankToSelect = BedWars.config.getInt(ConfigPath.GENERAL_ALESSIODP_PARTIES_RANK)
    override val isInternal = false

    private val Player.partyPlayer get() = api.getPartyPlayer(uniqueId)
    private val Player.party get() = api.getPartyOfPlayer(uniqueId)

    override fun hasParty(player: Player) = api.isPlayerInParty(player.uniqueId)

    override fun createParty(owner: Player, vararg members: Player) {
        //party creation handled on bungee side
        if (api.isBungeeCordEnabled || hasParty(owner)) return

        if (api.createParty(null, owner.partyPlayer)) {
            val party = owner.party!!
            members.forEach {
                val pp = it.partyPlayer
                if (pp != null && !pp.isInParty) party.addMember(pp)
            }
        }
    }
    override fun disband(owner: Player) {
        owner.party?.delete()
    }

    override fun partySize(player: Player) = player.party?.onlineMembers?.size ?: 0
    override fun isOwner(player: Player) = (player.partyPlayer?.rank ?: -1) >= requiredRankToSelect
    override fun isMember(owner: Player, player: Player) = api.areInTheSameParty(owner.uniqueId, player.uniqueId)
    override fun getOwner(member: Player) = Bukkit.getPlayer(member.party!!.leader!!)
    override fun getMembers(member: Player) = member.party?.onlineMembers?.mapNotNull { Bukkit.getPlayer(it.playerUUID) } ?: emptyList()

    override fun addMember(owner: Player, member: Player) {
        //party operations handled on bungee server side
        if (api.isBungeeCordEnabled) return
        val partyMember = member.partyPlayer ?: return
        owner.party?.addMember(partyMember)
    }
    override fun promote(owner: Player, member: Player) {}
    override fun removeMember(owner: Player, member: Player) {
        val targetPlayer = member.partyPlayer?: return
        owner.party?.removeMember(targetPlayer)
    }
    override fun removeFromParty(member: Player) {
        val partyMember = member.partyPlayer ?: return
        member.party?.removeMember(partyMember)
    }

}
