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
package com.andrei1058.bedwars.support.papi

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.commands.ShoutCommand
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.entity.Player
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant

class PAPISupport(private val plugin: BedWars) : PlaceholderExpansion() {
    override fun getIdentifier() = "bw1058"
    override fun getAuthor() = "andrei1058"
    override fun getVersion() = plugin.description.version
    override fun persist() = true

    override fun onPlaceholderRequest(player: Player?, params: String): String? {
        /* Non-Player required placeholders */
        val arenaManager = plugin.arenaManager
        params.apply { when {
            startsWith("arena_status_") -> return arenaManager.getArena(params.removePrefix("arena_status_"))
                ?.getDisplayStatus(Language.defaultLanguage)
                ?: Language.getMsg(player, Messages.ARENA_STATUS_RESTARTING_NAME)
            startsWith("arena_count_") -> return params
                .removePrefix("arena_count_")
                .split("+")
                .mapNotNull { arenaManager.getArena(it) }
                .sumOf { it.players.size }.toString()
            startsWith("group_count_") -> return arenaManager
                .getPlayers(params.removePrefix("group_count_"))
                .toString()
            startsWith("arena_group_") -> {
                val a = params.removePrefix("arena_group_")
                return arenaManager.getArena(a)?.group ?: "-"
            }
        } }


        /* Player required placeholders */
        if (player == null) return null

        // stats placeholders
        if (params.startsWith("stats_")) {
            val targetedStat = params.removePrefix("stats_")
            if (targetedStat.isBlank()) return null

            val stats = plugin.statsManager.getUnsafe(player.uniqueId) ?: return null
            val stat = when (targetedStat) {
                "firstplay" -> SimpleDateFormat(Language.getMsg(player, Messages.FORMATTING_STATS_DATE_FORMAT))
                    .format(stats.firstPlay?.let { Timestamp.from(it) })
                "lastplay" -> SimpleDateFormat(Language.getMsg(player, Messages.FORMATTING_STATS_DATE_FORMAT))
                    .format(stats.lastPlay?.let { Timestamp.from(it) })
                "total_kills" -> stats.totalKills
                "kills" -> stats.kills
                "wins" -> stats.wins
                "finalkills" -> stats.finalKills
                "deaths" -> stats.deaths
                "losses" -> stats.losses
                "finaldeaths" -> stats.finalDeaths
                "bedsdestroyed" -> stats.bedsDestroyed
                "gamesplayed" -> stats.gamesPlayed
                else -> null
            }
            return stat?.let { "$it" }
        }

        // other placeholders
        val a = arenaManager.getArena(player)
        val levels = plugin.levelManager
        val response = when (params) {
            "current_online" -> arenaManager.arenas.values.sumOf { it.allPlayers.size }
            "current_arenas" -> arenaManager.arenas.size
            "current_playing" -> a?.players?.size

            "player_team_color" -> if (a != null && a.isPlayer(player) && a.status == GameState.PLAYING) {
                a.getTeam(player)?.color?.chat
            } else null

            "player_team" -> if (a != null) {
                if (ShoutCommand.isShouting(player)) Language.getMsg(player, Messages.FORMAT_PAPI_PLAYER_TEAM_SHOUT)
                else if (a.isPlayer(player)) {
                    val bwt = a.getTeam(player)
                    if (a.status == GameState.PLAYING && bwt != null) Language.getMsg(player, Messages.FORMAT_PAPI_PLAYER_TEAM_TEAM)
                        .replace("{TeamName}", bwt.getDisplayName(Language.getLanguage(player)))
                        .replace("{TeamColor}", bwt.color.chat.toString())
                    else ""
                } else Language.getMsg(player, Messages.FORMAT_PAPI_PLAYER_TEAM_SPECTATOR)
            } else ""

            "player_level"              -> levels.getLevel(player)
            "player_level_raw"          -> levels.getPlayerLevel(player)
            "player_progress"           -> levels.getProgressBar(player)
            "player_xp_formatted"       -> levels.getCurrentXpFormatted(player)
            "player_xp"                 -> levels.getCurrentXp(player)
            "player_rerq_xp_formatted"  -> levels.getRequiredXpFormatted(player)
            "player_rerq_xp"            -> levels.getRequiredXp(player)
            "player_status" -> when (a?.status) {
                GameState.WAITING, GameState.STARTING -> "WAITING"
                GameState.PLAYING -> when {
                    a.isPlayer(player) -> "PLAYING"
                    a.isSpectator(player) -> "SPECTATING"
                    else -> "IN_GAME_BUT_NOT" // this shouldn't happen
                }

                GameState.RESTARTING -> "RESTARTING"
                else -> "NONE"
            }

            "current_arena_group" -> a?.group ?: ""

            "elapsed_time" -> {
                val startTime = a?.startTime ?: return ""
                val time = Duration.ofMillis(Instant.now().minusMillis(startTime.toEpochMilli()).toEpochMilli())
                if (time.toHours() == 0L) String.format("%02d:%02d", time.toMinutes(), time.toSeconds())
                else String.format("%02d:%02d:%02d", time.toHours(), time.toMinutes(), time.toSeconds())
            }
            else -> null
        }
        return response?.let { "$it" }
    }
}
