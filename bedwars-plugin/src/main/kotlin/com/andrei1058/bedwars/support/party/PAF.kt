package com.andrei1058.bedwars.support.party

import com.andrei1058.bedwars.api.party.Party
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager
import de.simonsator.partyandfriends.api.party.PartyManager
import org.bukkit.entity.Player

class PAF : Party {
    //Party and Friends for Spigot Support by JT122406
    override val isInternal = false

    private val Player.paf get() = PAFPlayerManager.getInstance()?.getPlayer(player)

    override fun hasParty(player: Player) = player.paf?.party != null

    override fun createParty(owner: Player, vararg members: Player) {
        val party = PartyManager.getInstance().createParty(owner.paf)
        party.setPrivateState(false)
        for (p1 in members) party.addPlayer(p1.paf)
        party.setPrivateState(true)
    }

    override fun disband(owner: Player) = PartyManager.getInstance().deleteParty(owner.paf?.party)

    override fun partySize(player: Player) = player.paf?.party?.allPlayers?.size ?: 0
    override fun isOwner(player: Player): Boolean {
        val pafPlayer = player.paf ?: return false
        return pafPlayer.party?.isLeader(pafPlayer) == true
    }
    override fun isMember(owner: Player, player: Player) = owner.paf?.party?.isInParty(player.paf) == true
    override fun getOwner(member: Player) = member.paf!!.party.leader.player!!
    override fun getMembers(member: Player) = member.paf?.party?.allPlayers?.map { it.player } ?: emptyList()

    override fun addMember(owner: Player, member: Player) {
        val party = owner.paf?.party ?: return
        party.setPrivateState(false)
        party.addPlayer(member.paf)
        party.setPrivateState(true)
    }
    override fun promote(owner: Player, member: Player) {
        owner.paf!!.party.leader = member.paf
    }
    override fun removeMember(owner: Player, member: Player) {
        owner.paf!!.party.leaveParty(member.paf)
    }
    override fun removeFromParty(member: Player) {
        member.paf?.let { it.party.leaveParty(it) }
    }
}
