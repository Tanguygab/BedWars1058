/*
 * BedWars1058 - A bed wars mini-game.
 * Copyright (C) 2023 Andrei Dascălu
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
package com.andrei1058.bedwars.sidebar

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.chatSupport
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.spigot.sidebar.PlayerTab
import com.andrei1058.spigot.sidebar.SidebarLine
import com.andrei1058.spigot.sidebar.SidebarLineAnimated
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffectType
import java.util.UUID
import kotlin.math.ceil
import kotlin.math.min

class BwTabList(private val plugin: BedWars, private val sidebar: BwSidebar) {
    // Player list container. Used to manipulate deployed player tab: lines ecc.
    // Key is player uuid.
    private val deployedPerPlayerTabList = mutableMapOf<UUID, PlayerTab>()

    // playing-restarting team order prefix for tab
    // this is concatenated to player identifier to keep tab-list ordered
    // and still let players have individual placeholders
    private val teamOrderPrefix = mutableMapOf<UUID, String>()
    private var teamOrderIndex = 0

    // unique string used for tab ordering. Does not track team here.
    private val playerTabIdentifier = mutableMapOf<UUID, String>()

    // used to prevent tab identifier duplication. Keeps an index of concurrent identifiers
    // concatenated later to playerTabIdentifier
    private val playerTabIdentifierDuplication = mutableMapOf<String, Int>()

    private val config = plugin.mainConfig
    /**
     * Triggered when sidebar context changes.
     * Arena/ game state change.
     */
    fun handlePlayerList() {
        // clear existing formatted player tab-lists

        if (sidebar.handle != null) {
            deployedPerPlayerTabList.clear()
            sidebar.handle!!.removeTabs()
        }

        handleHealthIcon()

        if (isTabFormattingDisabled) return

        val arena = sidebar.arena
        if (arena == null) {
            // if tab formatting is enabled in lobby world
            if (config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_LOBBY) &&
                config.lobbyWorldName.isNotBlank()
            ) {
                val lobby = Bukkit.getWorld(config.lobbyWorldName) ?: return
                lobby.players.forEach { giveUpdateTabFormat(it) }
            }
            // sometimes due to timing issues player is not listed yet in lobby players
            giveUpdateTabFormat(sidebar.player)
            return
        }

        handleHealthIcon()

        (arena.players + arena.spectators).forEach { giveUpdateTabFormat(it) }
    }

    fun handleHealthIcon() {
        val handle = sidebar.handle ?: return
        val arena = sidebar.arena
        if (arena == null || arena.status != GameState.PLAYING) {
            handle.hidePlayersHealth()
            return
        }

        val animation = Language.getList(sidebar.player, Messages.FORMATTING_SCOREBOARD_HEALTH)
        if (animation.isEmpty()) return
        val line = if (animation.size > 1) {
            SidebarLineAnimated(animation.toTypedArray())
        } else {
            val text = animation[0]
            object : SidebarLine() {
                override fun getLine() = text
            }
        }

        if (config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_HEALTH_ENABLE)) {
            handle.showPlayersHealth(line, config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_HEALTH_IN_TAB))
        }

        plugin.run(delay = 10) {
            val handle = sidebar.handle ?: return@run
            val arena = sidebar.arena ?: return@run
            arena.players.forEach { handle.setPlayerHealth(it, ceil(it.health).toInt()) }

            if (!arena.isSpectator(sidebar.player)) return@run
            arena.spectators.forEach { handle.setPlayerHealth(it, ceil(it.health).toInt()) }
        }
    }

    /**
     * @return true if tab formatting is disabled for current sidebar/ arena stage
     */
    val isTabFormattingDisabled: Boolean get() {
            val arena = sidebar.arena
            if (arena == null) {
                if (plugin.serverType == ServerType.SHARED &&
                    config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_LOBBY) &&
                    config.lobbyWorldName.isNotBlank()
                ) {
                    val lobby = Bukkit.getWorld(config.lobbyWorldName)
                    return lobby?.name != sidebar.player.world.name
                }

                return !config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_LOBBY)
            }
            // if tab formatting is disabled in status
            return !config.getBoolean(when (arena.status) {
                GameState.PLAYING -> ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_PLAYING
                GameState.STARTING -> ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_STARTING
                GameState.WAITING -> ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_WAITING
                GameState.RESTARTING -> ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_RESTARTING
            })
        }

    /**
     * Handle given player in sidebar owner tab list.
     * Will remove existing tab and give a new one based on game conditions list like spectator, team red, etc.
     * Will handle invisibility potion as well.
     */
    fun giveUpdateTabFormat(player: Player, skipStateCheck: Boolean = true, spectator: Boolean = false) {
        // if sidebar was not created
        val handle = sidebar.handle ?: return

        // unique tab list name
        val playerTabId = getCreatePlayerTabIdentifier(player)

        // clear existing tab formatting for given player
        val playerTab = deployedPerPlayerTabList[player.uniqueId]
        if (playerTab != null) {
            handle.removeTab(playerTab.identifier)
            deployedPerPlayerTabList.remove(player.uniqueId)
        }

        if (!skipStateCheck && isTabFormattingDisabled) return

        val arena = sidebar.arena

        if (null == arena) {
            deployedPerPlayerTabList[player.uniqueId] = handle.playerTabCreate(
                playerTabId,
                player,
                getTabText(Messages.FORMATTING_SB_TAB_LOBBY_PREFIX, player),
                getTabText(Messages.FORMATTING_SB_TAB_LOBBY_SUFFIX, player),
                PlayerTab.PushingRule.NEVER,
                sidebar.getPlaceholders(player)
            )
            return
        }

        // in-game tab has a special treatment
        if (arena.isSpectator(player) || spectator) {
            // if has been eliminated from a team

            val exTeam = arena.getExTeam(player.uniqueId)

            // when player leaves but decides to join to spectate later
            if (exTeam != null) {
                val replacements = getTeamReplacements(exTeam)

                val (prefix, suffix) = if (arena.status == GameState.RESTARTING && null != arena.winner)
                    if (arena.winner == exTeam)
                        Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_PREFIX to Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_SUFFIX
                    else Messages.FORMATTING_SB_TAB_RESTARTING_ELM_PREFIX to Messages.FORMATTING_SB_TAB_RESTARTING_ELM_SUFFIX
                else Messages.FORMATTING_SB_TAB_PLAYING_ELM_PREFIX to Messages.FORMATTING_SB_TAB_PLAYING_ELM_SUFFIX

                deployedPerPlayerTabList[player.uniqueId] = handle.playerTabCreate(
                    getPlayerTabIdentifierEliminatedInTeam(exTeam, playerTabId),
                    player,
                    getTabText(prefix, player, replacements),
                    getTabText(suffix, player, replacements),
                    PlayerTab.PushingRule.NEVER,
                    sidebar.getPlaceholders(player)
                )
                return
            }

            val (prefix, suffix) = when (arena.status) {
                GameState.WAITING -> Messages.FORMATTING_SB_TAB_WAITING_PREFIX_SPEC to Messages.FORMATTING_SB_TAB_WAITING_SUFFIX_SPEC
                GameState.STARTING -> Messages.FORMATTING_SB_TAB_STARTING_PREFIX_SPEC to Messages.FORMATTING_SB_TAB_STARTING_SUFFIX_SPEC
                GameState.PLAYING -> Messages.FORMATTING_SB_TAB_PLAYING_SPEC_PREFIX to Messages.FORMATTING_SB_TAB_PLAYING_SPEC_SUFFIX
                GameState.RESTARTING -> Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_PREFIX to Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_SUFFIX
            }

            deployedPerPlayerTabList[player.uniqueId] = handle.playerTabCreate(
                getPlayerTabIdentifierSpectator(null, playerTabId),
                player,
                getTabText(prefix, player),
                getTabText(suffix, player),
                PlayerTab.PushingRule.NEVER,
                sidebar.getPlaceholders(player)
            )
            return
        }

        // this is reached only by alive players
        val status = arena.status
        if (status != GameState.PLAYING) {
            var currentTabId = playerTabId
            var replacements = emptyMap<String, String>()

            val (prefix, suffix) = when (status) {
                GameState.WAITING -> Messages.FORMATTING_SB_TAB_WAITING_PREFIX to Messages.FORMATTING_SB_TAB_WAITING_SUFFIX
                GameState.STARTING -> Messages.FORMATTING_SB_TAB_STARTING_PREFIX to Messages.FORMATTING_SB_TAB_STARTING_SUFFIX
                GameState.RESTARTING -> {
                    val team = arena.getTeam(player)
                    replacements = getTeamReplacements(team)
                    currentTabId = getPlayerTabIdentifierAliveInTeam(team!!, playerTabId)
                    Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_PREFIX to Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_SUFFIX
                }

            }
            deployedPerPlayerTabList[player.uniqueId] = handle.playerTabCreate(
                currentTabId,
                player,
                getTabText(prefix, player, replacements),
                getTabText(suffix, player, replacements),
                PlayerTab.PushingRule.NEVER,
                sidebar.getPlaceholders(player)
            )
            return
        }

        // if status is playing and player is alive
        val team = arena.getTeam(player)
        // tab list of playing state
        val replacements = getTeamReplacements(team)

        val teamTab = handle.playerTabCreate(
            getPlayerTabIdentifierAliveInTeam(team!!, playerTabId),
            player,
            getTabText(Messages.FORMATTING_SB_TAB_PLAYING_PREFIX, player, replacements),
            getTabText(Messages.FORMATTING_SB_TAB_PLAYING_SUFFIX, player, replacements),
            PlayerTab.PushingRule.PUSH_OTHER_TEAMS,
            sidebar.getPlaceholders(player)
        )
        deployedPerPlayerTabList[player.uniqueId] = teamTab
        if (player.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
            teamTab.setNameTagVisibility(PlayerTab.NameTagVisibility.NEVER)
        }
    }

    private fun getTabText(path: String, targetPlayer: Player, replacements: Map<String, String> = emptyMap()): SidebarLine {
        var lines = Language.getList(sidebar.player, path)
        if (lines.isEmpty()) return object : SidebarLine() { override fun getLine() = "" }

        lines = mutableListOf<String>()
        for (line in Language.getList(sidebar.player, path)) {
            var parsed = line
                .replace("{vPrefix}", chatSupport.getPrefix(targetPlayer))
                .replace("{vSuffix}", chatSupport.getSuffix(targetPlayer))

            for ((key, value) in replacements) {
                parsed = parsed.replace(key, value)
            }

            lines.add(parsed)
        }

        if (lines.size == 1) {
            val line = lines[0]
            return object : SidebarLine() { override fun getLine() = line }
        }
        return SidebarLineAnimated(lines.toTypedArray())
    }

    /**
     * Gets/generates a prefix string to be concatenated to the player tab-list identifier, it keeps tab-list ordered by team.
     * 
     * @param team target.
     * @return prefix string.
     */
    private fun getCreateTeamTabOrderPrefix(team: ITeam): String {
        teamOrderPrefix[team.identity]?.let { return it }

        val prefix = "${teamOrderIndex++}"
        teamOrderPrefix[team.identity] = prefix
        if (prefix.length > 3) {
            throw RuntimeException("Could not generate new order prefixes. Char limit exceeded. Max value is 999.")
        }

        // todo how do we clean up index? when arena became null?
        return prefix
    }

    /**
     * Get existing or create player unique identifier for tab ordering
     * 
     * @param player target.
     * @return unique tab identifier for given player.
     */
    private fun getCreatePlayerTabIdentifier(player: Player): String {
        playerTabIdentifier[player.uniqueId]?.let { return it }

        var id = player.name.substring(0, min(player.name.length, 9))

        if (hasPlayerIdentifier(id)) {
            var lastDuplicationIndex = playerTabIdentifierDuplication[id] ?: 0

            lastDuplicationIndex++
            id += "$lastDuplicationIndex"
            playerTabIdentifierDuplication[id] = lastDuplicationIndex
        }

        playerTabIdentifier[player.uniqueId] = id
        return id
    }

    private fun hasPlayerIdentifier(id: String) = playerTabIdentifier.containsValue(id)

    private fun getPlayerTabIdentifierAliveInTeam(team: ITeam, playerId: String): String {
        return getCreateTeamTabOrderPrefix(team) + playerId
    }

    private fun getPlayerTabIdentifierEliminatedInTeam(team: ITeam, playerId: String?): String {
        return ELIMINATED_FROM_TEAM_PREFIX.toString() + getCreateTeamTabOrderPrefix(team) + playerId
    }

    private fun getPlayerTabIdentifierSpectator(team: ITeam?, playerId: String): String {
        return if (team == null) SPECTATOR_PREFIX.toString() + playerId
        else getPlayerTabIdentifierEliminatedInTeam(team, playerId)
    }

    fun getTeamReplacements(team: ITeam?): Map<String, String> {
        val displayName = team?.getDisplayName(Language.getLanguage(sidebar.player)) ?: ""
        return mapOf(
            "{teamName}" to displayName,
            "{teamLetter}" to if (null == team) "" else "${team.color.chat}${displayName.substring(0, 1)}",
            "{teamColor}" to (team?.color?.chat?.toString() ?: "")
        )
    }

    /**
     * Clear tab lines from instance.
     */
    fun onSidebarRemoval() {
        sidebar.handle!!.clearLines()
        deployedPerPlayerTabList.clear()
        playerTabIdentifier.clear()
        playerTabIdentifierDuplication.clear()
        teamOrderPrefix.clear()
        teamOrderIndex = 0
    }

    companion object {
        private const val SPECTATOR_PREFIX = 'z'
        private const val ELIMINATED_FROM_TEAM_PREFIX = 'z'
    }
}
