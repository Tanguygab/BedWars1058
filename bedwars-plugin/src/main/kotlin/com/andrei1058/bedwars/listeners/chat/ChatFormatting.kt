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
package com.andrei1058.bedwars.listeners.chat

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.commands.shout.ShoutCommand
import com.andrei1058.bedwars.configuration.Permissions
import com.andrei1058.bedwars.configuration.Permissions.hasPermission
import com.andrei1058.bedwars.support.papi.SupportPAPI
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import kotlin.math.roundToInt

class ChatFormatting(private val plugin: BedWars) : Listener {
    @EventHandler(ignoreCancelled = true)
    fun onChat(e: AsyncPlayerChatEvent) {
        val player = e.player

        // in shared mode we don't want messages from outside the arena to be seen in game
        val arena = plugin.arenaManager.getArena(player)
        if (plugin.serverType == ServerType.SHARED && arena == null) {
            e.recipients.removeIf { plugin.arenaManager.getArena(it) != null }
            return
        }

        // handle chat color. we would need to work on permission inheritance
        if (hasPermission(player,
                Permissions.PERMISSION_CHAT_COLOR,
                Permissions.PERMISSION_VIP,
                Permissions.PERMISSION_ALL
        )) e.message = ChatColor.translateAlternateColorCodes('&', e.message)


        // handle lobby world for multi arena
        if (plugin.serverType == ServerType.MULTIARENA && player.world.name.equals(plugin.lobbyWorld, ignoreCase = true)) {
            setRecipients(e, player.world.players)
        }

        val language = Language.getLanguage(player)

        // handle arena chat
        if (arena != null) {
            when {
                // spectator chat
                arena.isSpectator(player) -> {
                    setRecipients(e, arena.spectators)
                    e.parseAndSetFormat(language.m(Messages.FORMATTING_CHAT_SPECTATOR), player, null)
                    return
                }
                // arena lobby chat
                arena.status.isPreGame() -> {
                    setRecipients(e, arena.players)
                    e.parseAndSetFormat(language.m(Messages.FORMATTING_CHAT_WAITING), player, null)
                    return
                }
            }

            val team = arena.getTeam(player)
            var msg = e.message

            // shout format
            val shoutPrefix = arrayOf("!", "shout", language.m(Messages.MEANING_SHOUT))
                .find { msg.startsWith(it, ignoreCase = true) }
            if (shoutPrefix != null) {
                if (!player.hasPermission(Permissions.PERMISSION_SHOUT_COMMAND) &&
                    !player.hasPermission(Permissions.PERMISSION_ALL)
                ) {
                    e.isCancelled = true
                    player.sendMessage(Language.getMsg(player, Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS))
                    return
                }
                if (ShoutCommand.isShoutCooldown(player)) {
                    e.isCancelled = true
                    player.sendMessage(language
                        .m(Messages.COMMAND_COOLDOWN)
                        .replace("{seconds}", ShoutCommand.getShoutCooldown(player).roundToInt().toString())
                    )
                    return
                }
                ShoutCommand.updateShout(player)
                setRecipients(e, arena.allPlayers)
                msg = msg.substring(shoutPrefix.length).trim()
                if (msg.isEmpty()) {
                    e.isCancelled = true
                    return
                }
                e.message = msg
                e.parseAndSetFormat(language.m(Messages.FORMATTING_CHAT_SHOUT), player, team)
                return
            }

            // player team chat
            setRecipients(e, if (arena.maxInTeam == 1) arena.allPlayers else team!!.members)
            e.parseAndSetFormat(language.m(Messages.FORMATTING_CHAT_TEAM), player, team)
            return
        }

        // multi arena lobby chat
        e.parseAndSetFormat(language.m(Messages.FORMATTING_CHAT_LOBBY), player, null)
    }

    fun setRecipients(e: AsyncPlayerChatEvent, vararg target: List<Player>) {
        if (plugin.mainConfig.getBoolean(ConfigPath.GENERAL_CHAT_GLOBAL)) return

        e.recipients.clear()
        for (list in target) e.recipients.addAll(list)
    }

    private fun AsyncPlayerChatEvent.parseAndSetFormat(content: String, player: Player, team: ITeam?) {
        var content = content
            .replace("{vPrefix}", BedWars.chatSupport.getPrefix(player))
            .replace("{vSuffix}", BedWars.chatSupport.getSuffix(player))
            .replace("{playername}", player.name)
            .replace("{level}", plugin.levelManager.getLevel(player))
            .replace("{player}", player.displayName)
        if (team != null) {
            val teamFormat = Language.getMsg(player, Messages.FORMAT_PAPI_PLAYER_TEAM_TEAM)
                .replace("{TeamColor}", "${team.color.chat}")
                .replace("{TeamName}", team.getDisplayName(Language.getLanguage(player)).uppercase())
            content = content.replace("{team}", teamFormat)
        }
        format = SupportPAPI.support
            .replace(player, content)
            .replace("{message}", $$"%2$s")
    }
}
