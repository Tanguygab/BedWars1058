package com.andrei1058.bedwars.sidebar

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.sidebar.PlayerSidebarInitEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.sidebar.ScoreboardManager
import com.andrei1058.spigot.sidebar.SidebarManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID
import kotlin.math.ceil

class ScoreboardManagerImpl(private val plugin: BedWars) : ScoreboardManager {
    val sidebarHandler = SidebarManager.init()
    private val sidebars = mutableMapOf<UUID, BwSidebar>()

    init {
        val log = plugin.logger

        val config = plugin.mainConfig
        val playerListRefreshInterval = config.getLong(ConfigPath.SB_CONFIG_SIDEBAR_LIST_REFRESH)
        if (playerListRefreshInterval < 1) {
            log.info("Scoreboard names list refresh is disabled. (It is set to $playerListRefreshInterval).")
        } else {
            if (playerListRefreshInterval < 20) {
                log.warning("Scoreboard names list refresh interval is set to: $playerListRefreshInterval")
                log.warning("It is not recommended to use a value under 20 ticks.")
                log.warning("If you expect performance issues please increase its timer.")
            }
            plugin.repeat(1L, playerListRefreshInterval) { refreshTabList() }
        }
        val metrics = plugin.metrics
        metrics.appendPie("sb_list_refresh_interval") { "$playerListRefreshInterval" }

        val placeholdersRefreshInterval = config.getLong(ConfigPath.SB_CONFIG_SIDEBAR_PLACEHOLDERS_REFRESH_INTERVAL)
        if (placeholdersRefreshInterval < 1) {
            log.info("Scoreboard placeholders refresh is disabled. (It is set to $placeholdersRefreshInterval).")
        } else {
            if (placeholdersRefreshInterval < 20) {
                log.warning("Scoreboard placeholders refresh interval is set to: $placeholdersRefreshInterval")
                log.warning("It is not recommended to use a value under 20 ticks.")
                log.warning("If you expect performance issues please increase its timer.")
            }
            plugin.repeat(1, placeholdersRefreshInterval) { refreshPlaceholders() }
        }
        metrics.appendPie("sb_placeholder_refresh_interval") { "$placeholdersRefreshInterval" }

        val titleRefreshInterval = config.getLong(ConfigPath.SB_CONFIG_SIDEBAR_TITLE_REFRESH_INTERVAL)
        if (titleRefreshInterval < 1) {
            log.info("Scoreboard title refresh is disabled. (It is set to $titleRefreshInterval).")
        } else {
            if (titleRefreshInterval < 4) {
                log.warning("Scoreboard title refresh interval is set to: $titleRefreshInterval")
                log.warning("If you expect performance issues please increase its timer.")
            }
            plugin.repeat(1, titleRefreshInterval, true) { refreshTitles() }
        }
        metrics.appendPie("sb_title_refresh_interval") { "$titleRefreshInterval" }

        val healthAnimationInterval = config.getLong(ConfigPath.SB_CONFIG_SIDEBAR_HEALTH_REFRESH)
        if (healthAnimationInterval < 1) {
            log.info("Scoreboard health animation refresh is disabled. (It is set to $healthAnimationInterval).")
        } else {
            if (healthAnimationInterval < 20) {
                log.warning("Scoreboard health animation refresh interval is set to: $healthAnimationInterval")
                log.warning("It is not recommended to use a value under 20 ticks.")
                log.warning("If you expect performance issues please increase its timer.")
            }
            plugin.repeat(1, healthAnimationInterval) { refreshHealth() }
        }
        metrics.appendPie("sb_health_refresh_interval") { "$healthAnimationInterval" }

        val tabHeaderFooterRefreshInterval = config.getLong(ConfigPath.SB_CONFIG_TAB_HEADER_FOOTER_REFRESH_INTERVAL)
        if (tabHeaderFooterRefreshInterval < 1 || !config.getBoolean(ConfigPath.SB_CONFIG_TAB_HEADER_FOOTER_ENABLE)) {
            log.info("Scoreboard Tab header-footer refresh is disabled.")
        } else {
            if (tabHeaderFooterRefreshInterval < 20) {
                log.warning("Scoreboard tab header-footer refresh interval is set to: $tabHeaderFooterRefreshInterval")
                log.warning("It is not recommended to use a value under 20 ticks.")
                log.warning("If you expect performance issues please increase its timer.")
            }
            plugin.repeat(1, tabHeaderFooterRefreshInterval) { refreshTabHeaderFooter() }
        }
        metrics.appendPie("sb_header_footer_refresh_interval") { "$tabHeaderFooterRefreshInterval" }

        val lobbySidebar = config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_USE_LOBBY_SIDEBAR) && plugin.serverType == ServerType.MULTIARENA
        metrics.appendPie("sb_lobby_enable") { "$lobbySidebar" }
        val gameSidebar = config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_USE_GAME_SIDEBAR)
        metrics.appendPie("sb_game_enable") { "$gameSidebar" }

        plugin.registerEvents(ScoreboardListener(plugin, this))
    }

    override fun giveSidebar(player: Player, arena: IArena?, delay: Boolean) {
        var sidebar = sidebars.getOrDefault(player.uniqueId, null)

        val config = plugin.mainConfig
        // check if we might need to remove the existing sidebar
        if (null != sidebar) {
            // if sidebar is disabled in lobby on shared or multi-arena mode
            if (null == arena && (
                !config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_USE_LOBBY_SIDEBAR) ||
                plugin.serverType == ServerType.SHARED
            ) ||
                // if sidebar is disabled in game
                !config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_USE_GAME_SIDEBAR)
            ) {
                remove(sidebar)
                return
            }
        }

        // if sidebar was null but still disabled for lobbies
        if (!config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_USE_LOBBY_SIDEBAR) && null == arena) {
            return
        }
        // if sidebar was null but still disabled in game
        if (!config.getBoolean(ConfigPath.SB_CONFIG_SIDEBAR_USE_GAME_SIDEBAR) && null != arena) {
            return
        }

        // set sidebar lines based on game state or lobby

        var lines = if (arena != null) {
            val arenaGroupSidebar = "sidebar.${arena.group}"
            var (path, alternative) = when (arena.status) {
                GameState.WAITING -> {
                    val (path, alternative) = if (arena.isSpectator(player))
                        "spectator" to Messages.SCOREBOARD_DEFAULT_WAITING_SPEC
                    else "player" to Messages.SCOREBOARD_DEFAULT_WAITING
                    "waiting.$path" to alternative
                }

                GameState.STARTING -> {
                    val (path, alternative) = if (arena.isSpectator(player))
                        "spectator" to Messages.SCOREBOARD_DEFAULT_STARTING_SPEC
                    else "player" to Messages.SCOREBOARD_DEFAULT_STARTING
                    "starting.$path" to alternative
                }

                GameState.PLAYING -> {
                    val isSpectator = arena.isSpectator(player)
                    val exTeam = arena.getExTeam(player.uniqueId)
                    val (path, alternative) = when {
                        isSpectator && exTeam == null -> "spectator" to Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC
                        isSpectator -> "eliminated" to Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED
                        else -> "alive" to Messages.SCOREBOARD_DEFAULT_PLAYING
                    }
                    "playing.$path" to alternative
                }

                GameState.RESTARTING -> {
                    val team = arena.getTeam(player)
                    val exTeam = if (team == null) arena.getExTeam(player.uniqueId) else null

                    val (path, alternative) = when {
                        team == null && exTeam == null -> "spectator" to Messages.SCOREBOARD_DEFAULT_RESTARTING_SPEC
                        team == null && exTeam == arena.winner -> "winner-eliminated" to Messages.SCOREBOARD_DEFAULT_RESTARTING_WIN2
                        exTeam == null && team == arena.winner -> "winner-alive" to Messages.SCOREBOARD_DEFAULT_RESTARTING_WIN1
                        else -> "loser" to Messages.SCOREBOARD_DEFAULT_RESTARTING_LOSER
                    }

                    "restarting.$path" to alternative
                }

            }
            Language.getScoreboard(player, "$arenaGroupSidebar.$path", alternative)
        } else if (plugin.serverType == ServerType.SHARED) emptyList()
        else Language.getList(player, Messages.SCOREBOARD_LOBBY)

        // if we do not have lines we eventually remove the sidebar
        if (lines.isEmpty()) {
            if (sidebar != null) remove(sidebar)
            return
        }

        // title is the first line from array
        val title = lines[0].split(",")
        lines = lines.drop(1)

        // at this point we are sure we need a sidebar instance
        var newlyAdded = false
        if (sidebar == null) {
            sidebar = BwSidebar(plugin, player)
            newlyAdded = true

            val event = PlayerSidebarInitEvent(player, sidebar)
            Bukkit.getPluginManager().callEvent(event)
            if (event.isCancelled) return
        }
        sidebar.setContent(title, lines, arena)
        if (newlyAdded) sidebars[player.uniqueId] = sidebar
    }

    /**
     * Kill a sidebar lifecycle.
     */
    fun remove(sidebar: BwSidebar) = remove(sidebar.player)

    override fun remove(player: Player) {
        sidebars.remove(player.uniqueId)?.remove()
    }

    override fun refreshTitles() = sidebars.values.forEach { it.handle!!.refreshTitle() }
    override fun refreshPlaceholders() = sidebars.values.forEach { it.handle!!.refreshPlaceholders() }
    override fun refreshTabList() = sidebars.values.forEach { it.handle!!.playerTabRefreshAnimation() }

    fun refreshTabHeaderFooter() {
        sidebars.values.forEach {
            if (it.headerFooter != null) {
                sidebarHandler!!.sendHeaderFooter(it.player, it.headerFooter)
            }
        }
    }

    override fun refreshHealth() {
        sidebars.values.forEach {
            if (null == it.arena) return@forEach
            it.handle!!.playerHealthRefreshAnimation()
            for (player in it.arena!!.players) {
                it.handle!!.setPlayerHealth(player, ceil(player.health).toInt())
            }
        }
    }

    override fun getSidebar(player: Player) = sidebars[player.uniqueId]
    private fun getSidebars(arena: IArena?) = sidebars.values.asSequence().filter { it.arena == arena }

    override fun refreshPlaceholders(arena: IArena) {
        getSidebars(arena).forEach { it.handle!!.refreshPlaceholders() }
    }

    fun refreshHealth(arena: IArena, player: Player, health: Int) {
        getSidebars(arena).forEach { it.handle!!.setPlayerHealth(player, health) }
    }

    fun handleReJoin(arena: IArena, player: Player) {
        getSidebars(arena).forEach { it.giveUpdateTabFormat(player) }
    }

    fun handleJoin(arena: IArena, player: Player, spectator: Boolean) {
        getSidebars(arena).forEach {
            if (it.player != player) {
                it.giveUpdateTabFormat(player, spectator = spectator)
            }
        }
    }

    fun applyLobbyTab(player: Player) {
        getSidebars(null).forEach {
            if (it.player != player) {
                it.giveUpdateTabFormat(player)
            }
        }
    }

    fun handleInvisibility(team: ITeam, player: Player, toggle: Boolean) {
        getSidebars(team.arena).forEach { it.handleInvisibilityPotion(player) }
    }
}
