package com.andrei1058.bedwars.sidebar

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.economy
import com.andrei1058.bedwars.BedWars.Companion.serverType
import com.andrei1058.bedwars.BedWars.Companion.statsManager
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.NextEvent
import com.andrei1058.bedwars.api.arena.stats.DefaultStatistics
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.getMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.sidebar.ISidebar
import com.andrei1058.bedwars.arena.stats.StatisticsOrdered
import com.andrei1058.bedwars.levels.internal.PlayerLevel.Companion.getLevelByPlayer
import com.andrei1058.spigot.sidebar.*
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.text.SimpleDateFormat
import java.util.Date
import java.util.LinkedList
import java.util.TimeZone
import java.util.concurrent.ConcurrentLinkedQueue

class BwSidebar(override val player: Player) : ISidebar {
    override var arena: IArena? = null
        private set
    override var handle: Sidebar? = null
        private set

    var headerFooter: TabHeaderFooter? = null
    private val dateFormat = SimpleDateFormat(getMsg(player, Messages.FORMATTING_SCOREBOARD_DATE))
    private val nextEventDateFormat = SimpleDateFormat(getMsg(player, Messages.FORMATTING_SCOREBOARD_NEXEVENT_TIMER))
    private val persistentProviders = ConcurrentLinkedQueue<PlaceholderProvider>()

    private val tabList = BwTabList(this)

    var topStatistics: StatisticsOrdered? = null

    init {
        nextEventDateFormat.timeZone = TimeZone.getTimeZone("UTC")

        // Persistent placeholders
        mapOf(
            "poweredBy" to ConfigPath.GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_POWERED_BY,
            "serverId" to ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_SERVER_ID,
            "serverIp" to ConfigPath.GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_SERVER_IP
        ).forEach { (placeholder, value) -> registerPersistentPlaceholder(PlaceholderProvider("{$placeholder}") { BedWars.config.getString(value) }) }
    }

    fun remove() {
        if (handle == null) return
        tabList.onSidebarRemoval()
        handle!!.remove(player)
    }

    override fun setContent(titleArray: List<String>, lineArray: List<String>, arena: IArena?) {
        this.arena = arena
        val title = normalizeTitle(titleArray)
        val lines = normalizeLines(lineArray)

        if (arena == null) {
            // clean up
            topStatistics = null
        }

        val placeholders = getPlaceholders(player)
        placeholders.addAll(persistentProviders)

        // if it is the first time setting content we create the handle
        val handle = handle
        if (handle == null) {
            this.handle = BedWars.api.scoreboardManager.sidebarHandler!!.createSidebar(title, lines, placeholders)
            this.handle!!.add(player)
        } else {
            handle.clearLines()
            BedWars.plugin.run(delay = 2) {
                handle.placeholders.toList().forEach {
                    handle.removePlaceholder(it.placeholder)
                }
                placeholders.forEach { handle.addPlaceholder(it) }
                handle.setTitle(title)
                lines.forEach { handle.addLine(it) }
            }
        }
        tabList.handlePlayerList()
        assignTabHeaderFooter()
    }

    override fun normalizeTitle(titleArray: List<String>): SidebarLine {
        return if (titleArray.isEmpty()) EMPTY_TITLE
        else SidebarLineAnimated(titleArray.toTypedArray())
    }

    /**
     * Normalize lines where subject player is sidebar holder.
     */
    override fun normalizeLines(lineArray: List<String>): LinkedList<SidebarLine> {
        val lines = LinkedList<SidebarLine>()

        var teamCount = 0
        val language: Language = Language.getLanguage(player)
        val genericTeamFormat = language.m(Messages.FORMATTING_SCOREBOARD_TEAM_GENERIC)

        val statParser = topStatistics?.newParser()

        for (line in lineArray) {
            // convert old placeholders
            var line = line.replace("{server_ip}", "{serverIp}")
            var scoreLine = ""

            // generic team placeholder {team}
            val arena = arena
            if (arena != null) {
                if (line.trim() == "{team}") {
                    if (arena.teams.size > teamCount) {
                        val team = arena.teams[teamCount++]
                        val teamName = team.getDisplayName(language)
                        val teamLetter = teamName.firstOrNull()?.toString() ?: ""

                        line = genericTeamFormat
                            .replace("{TeamLetter}", teamLetter)
                            .replace("{TeamColor}", team.color.chat.toString())
                            .replace("{TeamName}", teamName)

                        val status = "{Team${team.name}Status}"
                        line = line.replace("{TeamStatus}", if ("{TeamStatus}" in line && BedWars.api.versionSupport.version >= 10) {
                            scoreLine = status
                            ""
                        } else status)
                    } else {
                        // skip line
                        continue
                    }
                }

                line = line
                    .replace("{map}", arena.displayName)
                    .replace("{map_name}", arena.name)
                    .replace("{group}", arena.getDisplayGroup(player))

                for (currentTeam in arena.teams) {
                    val color = currentTeam.color.chat
                    val teamName = currentTeam.getDisplayName(language)
                    val teamLetter = teamName.firstOrNull()?.toString() ?: ""

                    // Static team placeholders
                    line = line
                        .replace("{Team${currentTeam.name}Color}", color.toString())
                        .replace("{Team${currentTeam.name}Name}", teamName)
                        .replace("{Team${currentTeam.name}Letter}", teamLetter)


                    val isMember = currentTeam.isMember(player) || currentTeam.wasMember(player.uniqueId)
                    if (isMember) {
                        val replacements = tabList.getTeamReplacements(currentTeam)
                        for ((key, value) in replacements) {
                            line = line.replace(key, value)
                        }
                    }
                }
                if (arena.winner != null) {
                    val winnerDisplayName = arena.winner!!.getDisplayName(Language.getLanguage(player))
                    val color = arena.winner!!.color.chat.toString()
                    line = line
                        .replace("{winnerTeamName}", winnerDisplayName)
                        .replace("{winnerTeamLetter}", color + winnerDisplayName.substring(0, 1))
                        .replace("{winnerTeamColor}", color)
                }

                if (topStatistics != null && statParser != null) {
                    line = statParser.parseString(line, language, language.m(Messages.MEANING_NOBODY)) ?: continue
                }
            }

            // General static placeholders
            line = line
                .replace("{serverIp}", BedWars.config.getString(ConfigPath.GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_SERVER_IP)!!)
                .replace("{poweredBy}", BedWars.config.getString(ConfigPath.GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_POWERED_BY)!!)
                .replace("{version}", BedWars.plugin.description.version)
                .replace("{server}", BedWars.config.getString(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_SERVER_ID)!!)

            // Add the line to the sidebar
            val divided = line.split(",")
            lines += if (divided.size > 1) normalizeTitle(divided)
            else BwSidebarLine(line, scoreLine)
        }
        return lines
    }

    override fun giveUpdateTabFormat(player: Player, skipStateCheck: Boolean, spectator: Boolean) {
        tabList.giveUpdateTabFormat(player, skipStateCheck, spectator)
    }

    /**
     * Get placeholders for given player.
     * 
     * @param player subject.
     * @return placeholders.
     */
    fun getPlaceholders(player: Player): ConcurrentLinkedQueue<PlaceholderProvider> {
        val level = getLevelByPlayer(player.uniqueId)
        val placeholders = mutableMapOf(
            "player" to player::getDisplayName,
            "money" to { economy.getMoney(player).toString() },
            "playerName" to player::getCustomName,
            "date" to { dateFormat.format(Date(System.currentTimeMillis())) },
            // fixme 29/08/2023: disabled for now because this is not a dynamic placeholder. Let's see what's the impact.
            "severIp" to { BedWars.config.getString(ConfigPath.GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_SERVER_IP) },
            "version" to BedWars.plugin.description::getVersion,
            "progress" to level::progress,
            "level" to level::levelName,
            "levelUnformatted" to { level.level.toString() },
            "currentXp" to level::formattedCurrentXp,
            "requiredXp" to level::formattedRequiredXp
        )

        val arena = arena
        if (arena != null) {
            placeholders += mapOf(
                "on" to { arena.players.size.toString() },
                "max" to { arena.maxPlayers.toString() },
                "nextEvent" to ::nextEventName
            )

            if (arena.isSpectator(player)) {
                val lang = Language.getLanguage(player)
                val targetFormat = lang.m(Messages.FORMAT_SPECTATOR_TARGET)

                placeholders += "spectatorTarget" to Callable@{
                    val target = player.spectatorTarget as? Player ?: return@Callable ""
                    val targetTeam = arena.getTeam(target) ?: return@Callable ""
                    targetFormat
                        .replace("{targetTeamColor}", targetTeam.color.chat.toString())
                        .replace("{targetDisplayName}", target.displayName)
                        .replace("{targetName}", target.displayName)
                        .replace("{targetTeamName}", targetTeam.getDisplayName(lang))
                }
            }

            placeholders += "time" to { when (arena.status) {
                GameState.PLAYING, GameState.RESTARTING -> nextEventTime
                GameState.STARTING if arena.startingTask != null -> "${arena.startingTask!!.countdown + 1}"
                else -> dateFormat.format(Date(System.currentTimeMillis()))
            } }

            arena.statsHolder.get(player)?.apply {
                mapOf(
                    "kills" to DefaultStatistics.KILLS,
                    "finalKills" to DefaultStatistics.KILLS_FINAL,
                    "beds" to DefaultStatistics.BEDS_DESTROYED,
                    "deaths" to DefaultStatistics.DEATHS
                ).forEach { (placeholder, stat) -> getStatistic(stat)
                    ?.run { placeholders += placeholder to { getDisplayValue(Language.defaultLanguage) } }
                }
            }

            // Dynamic team placeholders
            for (currentTeam in arena.teams) {
                val isMember = currentTeam.isMember(player) || currentTeam.wasMember(player.uniqueId)

                placeholders += "Team${currentTeam.name}Status" to {
                    var result = if (currentTeam.isBedDestroyed) {
                        if (currentTeam.size > 0) getMsg(player, Messages.FORMATTING_SCOREBOARD_BED_DESTROYED)
                            .replace("{remainingPlayers}", currentTeam.size.toString())
                        else getMsg(player, Messages.FORMATTING_SCOREBOARD_TEAM_ELIMINATED)
                    } else getMsg(player, Messages.FORMATTING_SCOREBOARD_TEAM_ALIVE)
                    if (isMember) result += getMsg(player, Messages.FORMATTING_SCOREBOARD_YOUR_TEAM)
                    result
                }

                if (isMember) placeholders += "teamStatus" to {
                    if (currentTeam.isBedDestroyed)
                        if (currentTeam.size > 0) getMsg(player, Messages.FORMATTING_SCOREBOARD_BED_DESTROYED)
                            .replace("{remainingPlayers}", currentTeam.size.toString())
                        else getMsg(player, Messages.FORMATTING_SCOREBOARD_TEAM_ELIMINATED)
                    else getMsg(player, Messages.FORMATTING_SCOREBOARD_TEAM_ALIVE)
                }
            }
        } else {
            placeholders += "on" to { Bukkit.getOnlinePlayers().size.toString() }
            placeholders += statsManager.get(player.uniqueId).run { mapOf(
                "kills" to { "$kills" },
                "finalKills" to { "$finalKills" },
                "beds" to { "$bedsDestroyed" },
                "deaths" to { "$deaths" },
                "finalDeaths" to { "$finalDeaths" },
                "wins" to { "$wins" },
                "losses" to { "$losses" },
                "gamesPlayed" to { "$gamesPlayed" }
            ) }
        }

        val providers = ConcurrentLinkedQueue<PlaceholderProvider>()
        placeholders.forEach { (placeholder, value) -> providers += PlaceholderProvider("{$placeholder}", value) }
        return providers
    }

    private val nextEventName: String get() = getMsg(player, when (arena?.nextEvent) {
        null -> return "-"
        NextEvent.DIAMOND_GENERATOR_TIER_II -> Messages.NEXT_EVENT_DIAMOND_UPGRADE_II
        NextEvent.EMERALD_GENERATOR_TIER_II -> Messages.NEXT_EVENT_EMERALD_UPGRADE_II
        NextEvent.DIAMOND_GENERATOR_TIER_III -> Messages.NEXT_EVENT_DIAMOND_UPGRADE_III
        NextEvent.EMERALD_GENERATOR_TIER_III -> Messages.NEXT_EVENT_EMERALD_UPGRADE_III
        NextEvent.BEDS_DESTROY -> Messages.NEXT_EVENT_BEDS_DESTROY
        NextEvent.ENDER_DRAGON -> Messages.NEXT_EVENT_DRAGON_SPAWN
        NextEvent.GAME_END -> Messages.NEXT_EVENT_GAME_END
    })

    private val nextEventTime: String
        get() {
            val arena = arena ?: return nextEventDateFormat.format(0)
            val time = when (arena.nextEvent) {
                NextEvent.EMERALD_GENERATOR_TIER_II,
                NextEvent.EMERALD_GENERATOR_TIER_III -> arena.upgradeEmeraldsCount

                NextEvent.DIAMOND_GENERATOR_TIER_II,
                NextEvent.DIAMOND_GENERATOR_TIER_III -> arena.upgradeDiamondsCount

                NextEvent.GAME_END -> arena.playingTask!!.gameEndCountdown
                NextEvent.BEDS_DESTROY -> arena.playingTask!!.bedsDestroyCountdown
                NextEvent.ENDER_DRAGON -> arena.playingTask!!.dragonSpawnCountdown
            }
            return if (time == 0) "0" else nextEventDateFormat.format(Date(time * 1000L))
        }

    // Provide header and footer for current game state
    private fun assignTabHeaderFooter() {
        if (!BedWars.config.getBoolean(ConfigPath.SB_CONFIG_TAB_HEADER_FOOTER_ENABLE)) return
        if (arena == null || serverType == ServerType.SHARED) {
            headerFooter = null
            return
        }

        val lang = Language.getLanguage(player)
        val arena = arena
        val (headerPath, footerPath) = if (arena != null) {
            if (arena.isSpectator(player)) {
                val exTeam = arena.getExTeam(player.uniqueId)
                if (null == exTeam) when (arena.status) {
                        GameState.WAITING -> Messages.FORMATTING_SB_TAB_WAITING_HEADER_SPEC to Messages.FORMATTING_SB_TAB_WAITING_FOOTER_SPEC
                        GameState.STARTING -> Messages.FORMATTING_SB_TAB_STARTING_HEADER_SPEC to Messages.FORMATTING_SB_TAB_STARTING_FOOTER_SPEC
                        GameState.PLAYING -> Messages.FORMATTING_SB_TAB_PLAYING_SPEC_HEADER to Messages.FORMATTING_SB_TAB_PLAYING_SPEC_FOOTER
                        GameState.RESTARTING -> Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_HEADER to Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_FOOTER
                } else if (arena.status == GameState.RESTARTING)
                    if (null != arena.winner && arena.winner == exTeam)
                        Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_HEADER to Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_FOOTER
                    else Messages.FORMATTING_SB_TAB_RESTARTING_ELM_HEADER to Messages.FORMATTING_SB_TAB_RESTARTING_ELM_FOOTER
                else Messages.FORMATTING_SB_TAB_PLAYING_ELM_HEADER to Messages.FORMATTING_SB_TAB_PLAYING_ELM_FOOTER
            } else when (arena.status) {
                GameState.WAITING -> Messages.FORMATTING_SB_TAB_WAITING_HEADER to Messages.FORMATTING_SB_TAB_WAITING_FOOTER
                GameState.STARTING -> Messages.FORMATTING_SB_TAB_STARTING_HEADER to Messages.FORMATTING_SB_TAB_STARTING_FOOTER
                GameState.PLAYING -> Messages.FORMATTING_SB_TAB_PLAYING_HEADER to Messages.FORMATTING_SB_TAB_PLAYING_FOOTER
                GameState.RESTARTING -> Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_HEADER to Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_FOOTER
            }
        } else Messages.FORMATTING_SB_TAB_LOBBY_HEADER to Messages.FORMATTING_SB_TAB_LOBBY_FOOTER

        headerFooter = TabHeaderFooter(
            normalizeLines(lang.l(headerPath)),
            normalizeLines(lang.l(footerPath)),
            getPlaceholders(player)
        )

        BedWars.api.scoreboardManager.sidebarHandler!!.sendHeaderFooter(player, headerFooter)
    }

    override fun registerPersistentPlaceholder(placeholderProvider: PlaceholderProvider): Boolean {
        persistentProviders += placeholderProvider
        return true
    }

    /**
     * Hide player name tag on head when he drinks an invisibility potion.
     * This is required because not all clients hide it automatically.
     */
    fun handleInvisibilityPotion(player: Player) {
        if (arena == null) {
            throw RuntimeException("This can only be used when the player is in arena")
        }
        giveUpdateTabFormat(player)
    }

    companion object {
        private val EMPTY_TITLE = object : SidebarLine() {
            override fun getLine() = ""
        }
    }
}
