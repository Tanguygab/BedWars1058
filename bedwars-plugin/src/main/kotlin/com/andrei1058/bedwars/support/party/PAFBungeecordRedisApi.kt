package com.andrei1058.bedwars.support.party

import com.andrei1058.bedwars.api.party.Party
import de.simonsator.partyandfriends.spigot.api.pafplayers.PAFPlayerManager
import de.simonsator.partyandfriends.spigot.api.party.PartyManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class PAFBungeecordRedisApi : Party {
    //Party and Friends Extended for BungeeCord Support by JT122406
    override val isInternal = false

    private val Player.paf get() = PAFPlayerManager.getInstance().getPlayer(uniqueId)
    private val Player.party get() = PartyManager.getInstance().getParty(paf)

    override fun hasParty(player: Player) = player.party != null

    override fun createParty(owner: Player, vararg members: Player) {}
    override fun disband(owner: Player) {}

    override fun partySize(player: Player) = getMembers(player).size
    override fun isOwner(player: Player) = player.party.isLeader(player.paf)
    override fun isMember(owner: Player, player: Player) = owner.party.isInParty(player.paf)
    override fun getOwner(member: Player) = Bukkit.getPlayer(member.party.leader.uniqueId)
    override fun getMembers(member: Player) = member.party.allPlayers.mapNotNull { Bukkit.getPlayer(it.uniqueId) }

    override fun addMember(owner: Player, member: Player) {}
    override fun promote(owner: Player, member: Player) {}
    override fun removeMember(owner: Player, member: Player) {}
    override fun removeFromParty(member: Player) {}
}
