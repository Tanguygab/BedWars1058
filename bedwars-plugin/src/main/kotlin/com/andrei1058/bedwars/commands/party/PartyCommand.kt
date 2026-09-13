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
package com.andrei1058.bedwars.commands.party

import com.andrei1058.bedwars.BedWars.Companion.party
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.command.defaults.BukkitCommand
import org.bukkit.entity.Player
import java.util.UUID

class PartyCommand(name: String) : BukkitCommand(name) {
    override fun execute(s: CommandSender, c: String, args: Array<String>): Boolean {
        if (s is ConsoleCommandSender) return true
        val p = s as Player
        if (args.isEmpty() || args[0].equals("help", ignoreCase = true)) {
            sendPartyCmds(p)
            return true
        }
        when (args[0].lowercase()) {
            "invite" -> {
                if (args.size == 1) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_INVITE_USAGE)
                    return true
                }
                if (party.hasParty(p) && !party.isOwner(p)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS)
                    return true
                }
                val player = Bukkit.getPlayer(args[1])
                if (player != null && player.isOnline) {
                    if (p === player) {
                        p.sendLangMsg(Messages.COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF)
                        return true
                    }
                    p.sendLangMsg(Messages.COMMAND_PARTY_INVITE_SENT,
                        "{playername}" to p.name,
                        "{player}" to args[1]
                    )
                    val tc = TextComponent(Language.getMsg(
                            p,
                            Messages.COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG
                        ).replace("{player}", p.name)
                    )
                    tc.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/party accept " + p.name)
                    player.spigot().sendMessage(tc)
                    partySessionRequest[p.uniqueId] = player.uniqueId
                } else p.sendLangMsg(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{player}" to args[1])
            }

            "accept" -> {
                if (args.size < 2) return true

                val player = Bukkit.getPlayer(args[1])
                if (party.hasParty(p)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY)
                    return true
                }

                if (player == null || !player.isOnline) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{player}" to args[1])
                    return true
                }

                if (player.uniqueId !in partySessionRequest) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE)
                    return true
                }

                if (partySessionRequest[player.uniqueId] == p.uniqueId) {
                    partySessionRequest.remove(player.uniqueId)
                    if (party.hasParty(player)) party.addMember(player, p)
                    else party.createParty(player, p)

                    for (on in party.getMembers(player)) {
                        on.sendLangMsg(Messages.COMMAND_PARTY_ACCEPT_SUCCESS,
                            "{playername}" to p.name,
                            "{player}" to p.displayName
                        )
                    }
                } else p.sendLangMsg(Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE)
            }

            "leave" -> {
                if (!party.hasParty(p)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY)
                    return true
                }

                if (party.isOwner(p)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND)
                    return true
                }
                party.removeFromParty(p)
            }

            "disband" -> {
                if (!party.hasParty(p)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY)
                    return true
                }

                if (!party.isOwner(p)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS)
                    return true
                }
                party.disband(p)
            }

            "remove" -> {
                if (args.size == 1) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_REMOVE_USAGE)
                    return true
                }
                if (party.hasParty(p) && !party.isOwner(p)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS)
                    return true
                }
                val target = Bukkit.getPlayer(args[1])
                if (target == null) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER, "{player}" to args[1])
                    return true
                }
                if (!party.isMember(p, target)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER, "{player}" to args[1])
                    return true
                }
                party.removeMember(p, target)
            }

            "promote" -> {
                if (!party.hasParty(p)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY)
                    return true
                }

                if (!party.isOwner(p)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS)
                    return true
                }

                if (args.size == 1) {
                    sendPartyCmds(p)
                    return true
                }
                val target1 = Bukkit.getPlayer(args[1])
                if (target1 == null || !party.isMember(p, target1)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER, "{player}" to args[1])
                    return true
                }
                party.promote(p, target1)
                for (p1 in party.getMembers(p)) {
                    p1.sendLangMsg(when (p1) {
                            p -> Messages.COMMAND_PARTY_PROMOTE_SUCCESS
                            target1 -> Messages.COMMAND_PARTY_PROMOTE_OWNER
                            else -> Messages.COMMAND_PARTY_PROMOTE_NEW_OWNER
                    }, "{player}" to args[1])
                }
            }

            "info", "list" -> {
                if (!party.hasParty(p)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY)
                    return true
                }
                val owner = party.getOwner(p)!!
                p.sendLangMsg(Messages.COMMAND_PARTY_INFO_OWNER, "{owner}" to owner.name)
                p.sendLangMsg(Messages.COMMAND_PARTY_INFO_PLAYERS)
                for (p1 in party.getMembers(owner)) {
                    p.sendLangMsg(Messages.COMMAND_PARTY_INFO_PLAYER, "{player}" to p1.name)
                }
            }

            else -> sendPartyCmds(p)
        }
        return false
    }

    private fun sendPartyCmds(p: Player) {
        for (s in Language.getList(p, Messages.COMMAND_PARTY_HELP)) {
            p.sendMessage(s)
        }
    }

    companion object {
        //owner, target
        private val partySessionRequest = HashMap<UUID, UUID>()
    }
}
