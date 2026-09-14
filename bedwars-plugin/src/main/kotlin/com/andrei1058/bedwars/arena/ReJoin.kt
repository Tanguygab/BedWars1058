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
package com.andrei1058.bedwars.arena

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.debug
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.arena.tasks.ReJoinTask
import com.andrei1058.bedwars.configuration.Sounds
import com.andrei1058.bedwars.lobbysocket.ArenaSocket
import com.andrei1058.bedwars.shop.ShopCache
import com.google.gson.JsonObject
import org.bukkit.GameMode
import org.bukkit.entity.Player

@Suppress("EqualsOrHashCode")
class ReJoin(
    player: Player,
    /**
     * Get arena
     */
    val arena: IArena,
    /**
     * Get player team
     */
    val team: ITeam,
    val permanentsAndNonDowngradables: List<ShopCache.CachedItem>
) {
    /**
     * Get Player
     */
    val pl = player.uniqueId

    val task = if (team.members.isEmpty()) ReJoinTask(arena, team) else null

    /**
     * Make rejoin possible for a player
     */
    init {
        getPlayer(player)?.destroy(true)
        reJoinList += this
        debug("Created ReJoin for ${player.name} ${player.uniqueId} at ${arena.name}")

        if (BedWars.INSTANCE.autoScale) sendMessage(JsonObject().apply {
            addProperty("type", "RC")
            addProperty("uuid", "${player.uniqueId}")
            addProperty("arena_id", arena.worldName)
            addProperty("server", BedWars.INSTANCE.mainConfig.getString(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_SERVER_ID))
        })
    }

    /**
     * Check if can reJoin
     */
    fun canReJoin(): Boolean {
        debug("ReJoin canReJoin check.")
        if (arena.status == GameState.RESTARTING) {
            debug("ReJoin.canReJoin status is restarting $pl")
            destroy(true)
            return false
        }
        if (team.isBedDestroyed) {
            debug("ReJoin.canReJoin bed is destroyed $pl")
            destroy(false)
            return false
        }
        return true
    }

    /**
     * Make a player re-join the arena
     */
    fun reJoin(player: Player): Boolean {
        Sounds.playSound("rejoin-allowed", player)
        player.sendLangMsg(Messages.REJOIN_ALLOWED, "{arena}" to arena.displayName)

        if (player.gameMode != GameMode.SURVIVAL) {
            BedWars.INSTANCE.run(delay = 20) {
                player.run {
                    gameMode = GameMode.SURVIVAL
                    allowFlight = true
                    isFlying = true
                }
            }
        }
        return arena.reJoin(player)
    }

    /**
     * Destroy data and rejoin possibility
     */
    fun destroy(destroyTeam: Boolean) {
        debug("ReJoin.destroy for $pl")
        reJoinList.remove(this)

        sendMessage(JsonObject().apply {
            addProperty("type", "RD")
            addProperty("uuid", "$pl")
            addProperty("server", BedWars.INSTANCE.mainConfig.getString(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_SERVER_ID))
        })

        if (!destroyTeam || team.members.isNotEmpty()) return

        team.isBedDestroyed = true
        arena.allPlayers.forEach {
            it.sendLangMsg(Messages.TEAM_ELIMINATED_CHAT,
                "{TeamColor}" to team.color.chat.toString(),
                "{TeamName}" to team.getDisplayName(Language.getLanguage(it))
            )
        }
        arena.checkWinner()
    }

    private fun sendMessage(json: JsonObject) = ArenaSocket.sendMessage(json.toString())

    override fun equals(other: Any?) = (other as? ReJoin)?.pl == pl

    companion object {
        private val reJoinList = mutableListOf<ReJoin>()

        /**
         * Check if a player has stored data
         */
        fun exists(pl: Player): Boolean {
            debug("ReJoin exists check ${pl.uniqueId}")
            return reJoinList.any {
                debug("ReJoin exists check list scroll: ${it.pl}")
                it.pl == pl.uniqueId
            }
        }

        /**
         * Get a player ReJoin
         */
        fun getPlayer(player: Player): ReJoin? {
            debug("ReJoin getPlayer ${player.uniqueId}")
            return reJoinList.find { it.pl == player.uniqueId }
        }

        fun destroy(arena: IArena) = reJoinList
            .filter { it.arena === arena }
            .forEach { it.destroy(true) }
    }
}
