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
import com.andrei1058.bedwars.Misc
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.NextEvent
import com.andrei1058.bedwars.api.arena.generator.IGenerator
import com.andrei1058.bedwars.api.arena.shop.ShopHolo
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent
import com.andrei1058.bedwars.api.events.gameplay.NextEventChangeEvent
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent
import com.andrei1058.bedwars.api.events.player.PlayerReJoinEvent
import com.andrei1058.bedwars.api.events.server.ArenaDisableEvent
import com.andrei1058.bedwars.api.events.server.ArenaEnableEvent
import com.andrei1058.bedwars.api.events.server.ArenaRestartEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.region.Region
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.tasks.PlayingTask
import com.andrei1058.bedwars.api.tasks.RestartingTask
import com.andrei1058.bedwars.api.tasks.StartingTask
import com.andrei1058.bedwars.api.util.Utils
import com.andrei1058.bedwars.arena.generators.GeneratorOre
import com.andrei1058.bedwars.arena.generators.Generator
import com.andrei1058.bedwars.arena.stats.GameStatsManager
import com.andrei1058.bedwars.arena.stats.StatisticsOrdered
import com.andrei1058.bedwars.arena.tasks.GamePlayingTask
import com.andrei1058.bedwars.arena.tasks.GameRestartingTask
import com.andrei1058.bedwars.arena.tasks.GameStartingTask
import com.andrei1058.bedwars.arena.tasks.ReJoinTask
import com.andrei1058.bedwars.arena.team.BedWarsTeam
import com.andrei1058.bedwars.arena.team.LegacyTeamAssigner
import com.andrei1058.bedwars.arena.team.TeamAssigner
import com.andrei1058.bedwars.arena.upgrades.BaseListener
import com.andrei1058.bedwars.configuration.ArenaConfig
import com.andrei1058.bedwars.configuration.Sounds
import com.andrei1058.bedwars.levels.internal.InternalLevelManager
import com.andrei1058.bedwars.levels.internal.PerMinuteTask
import com.andrei1058.bedwars.listeners.blockstatus.BlockStatusListener
import com.andrei1058.bedwars.listeners.dropshandler.PlayerDrops
import com.andrei1058.bedwars.money.internal.MoneyPerMinuteTask
import com.andrei1058.bedwars.shop.ShopCache
import com.andrei1058.bedwars.sidebar.BwSidebar
import com.andrei1058.bedwars.api.util.Utils.teleportSafe
import com.andrei1058.bedwars.arena.data.LastHit
import com.andrei1058.bedwars.arena.data.PlayerGoods
import com.andrei1058.bedwars.arena.data.ReJoin
import com.andrei1058.bedwars.support.papi.PAPISupport
import com.andrei1058.bedwars.support.vault.WithEconomy
import net.md_5.bungee.api.chat.ClickEvent
import org.bukkit.*
import org.bukkit.block.Block
import org.bukkit.block.Sign
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector
import java.time.Instant
import java.util.LinkedList
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Suppress("EqualsOrHashCode")
class Arena(
    private val plugin: BedWars,
    private val manager: ArenaManagerImpl,
    override val name: String,
    override var worldName: String,
    override val config: ArenaConfig
) : IArena {
    override val displayName = config.getString(ConfigPath.ARENA_DISPLAY_NAME)?.trim() ?: name
        .replaceFirstChar { it.uppercaseChar() }
        .replace("_", " ")
        .replace("-", " ")

    override var players = mutableListOf<Player>()
    override var spectators = mutableListOf<Player>()
    override var signs = mutableListOf<Block>()
    override var status = GameState.RESTARTING
        set(value) {
            if (field != GameState.PLAYING && value == GameState.PLAYING) {
                startTime = Instant.now()
            }
            // if countdown cancelled
            if (field == GameState.STARTING && value == GameState.WAITING) {
                for (player in players) {
                    val lang = Language.getLanguage(player)
                    plugin.versionSupport.sendTitle(
                        player,
                        lang.m(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_TITLE),
                        lang.m(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_SUB_TITLE),
                        0, 40, 10
                    )
                }
            }
            field = value
        }
    private var minPlayers = config.getInt("minPlayers", 2)

    /**
     * Get the max number of teammates in a team
     */
    override val maxInTeam = config.getInt("maxInTeam", 1)

    /**
     * Get the max number of players that can play on this arena.
     */
    override val maxPlayers = config.getConfigurationSection("Team")?.getKeys(false)?.size?.let { it * maxInTeam } ?: 10

    /**
     * Get the island radius
     */
    override val islandRadius = config.getInt(ConfigPath.ARENA_ISLAND_RADIUS, 10)
    override val yHeightLimit = config.getInt(ConfigPath.ARENA_CONFIGURATION_MAX_BUILD_Y)
    override var upgradeDiamondsCount = 0
    override var upgradeEmeraldsCount = 0
    override var isAllowSpectate = config.getBoolean("allowSpectate", true)
    override lateinit var world: World
    override var group = config.getString("group").let { if (it != null && it in plugin.mainConfig.getStringList("arenaGroups")) it else "Default" }
    override var teams = mutableListOf<BedWarsTeam>()
    override var placed = LinkedList<Vector>()
    override var nextEvents = mutableListOf<String>()
    override var regionsList = mutableListOf<Region>()
    override var renderDistance = 0
        private set

    override val leavingPlayers = mutableListOf<Player>()

    /**
     * Current event, used at scoreboard
     */
    override var nextEvent = NextEvent.DIAMOND_GENERATOR_TIER_II
        set(value) {
            Sounds.playSound(field.soundPath, players)
            Sounds.playSound(field.soundPath, spectators)
            Bukkit.getPluginManager().callEvent(NextEventChangeEvent(this, value, field))
            field = value
        }
    override var diamondTier = 1
        private set
    override var emeraldTier = 1
        private set

    /**
     * Players in respawn session
     */
    override var respawnSessions = ConcurrentHashMap<Player, Int>()

    /**
     * Invisibility for armor when you drink an invisibility potion
     */
    override var showTime = ConcurrentHashMap<Player, Int>()

    override val statsHolder = GameStatsManager(this)


    /* ARENA TASKS */
    override var startingTask: StartingTask? = null
    override var playingTask: PlayingTask? = null
    override var restartingTask: RestartingTask? = null

    /* ARENA GENERATORS */
    override var oreGenerators = mutableListOf<IGenerator>()

    private var perMinuteTask: PerMinuteTask? = null

    private var moneyperMinuteTask: MoneyPerMinuteTask? = null

    override lateinit var respawnLocation: Location
    override lateinit var spectatorLocation: Location
    override lateinit var waitingLocation: Location
        private set
    val worldBorder = config.getInt("worldBorder").toDouble()
    override val yKillHeight = config.getInt(ConfigPath.ARENA_Y_LEVEL_KILL)
    override var startTime: Instant? = null
    override var teamAssigner = if (plugin.mainConfig.getBoolean(ConfigPath.GENERAL_CONFIGURATION_EXPERIMENTAL_TEAM_ASSIGNER))
        TeamAssigner()
    else LegacyTeamAssigner
        set(value) {
            field = value
            plugin.logger.warning("Using ${value.javaClass.simpleName} team assigner on arena: $name")
        }

    override fun resetTeamAssigner() {
        teamAssigner = if (plugin.mainConfig.getBoolean(ConfigPath.GENERAL_CONFIGURATION_EXPERIMENTAL_TEAM_ASSIGNER))
            TeamAssigner()
        else LegacyTeamAssigner
    }

    override val isAllowMapBreak = config.getBoolean(ConfigPath.ARENA_ALLOW_MAP_BREAK)
    override var winner: ITeam? = null


    override fun getDisplayGroup(language: Language) = language.m(Messages.ARENA_DISPLAY_GROUP_PATH + group.lowercase())
    override fun getDisplayGroup(player: Player) = getDisplayGroup(Language.getLanguage(player))


    init {
        Language.saveIfNotExists(
            Messages.ARENA_DISPLAY_GROUP_PATH + group.lowercase(),
            group.lowercase().replaceFirstChar { it.uppercaseChar() }
        )
    }

    /**
     * Use this method when the world was loaded successfully.
     */
    override fun init(world: World) {
        if (!plugin.autoScale && manager.getArena(name) != null) return
        manager.removeFromEnableQueue(this)

        BedWars.debug("Initialized arena " + name + " with map " + world.name)
        this.world = world
        worldName = world.name
        config.fileName = worldName

        // Re Spawn Session Location
        respawnLocation = config.getArenaLoc(ConfigPath.ARENA_SPEC_LOC)
            ?: config.getArenaLoc("waiting.Loc")
            ?: world.spawnLocation
        spectatorLocation = respawnLocation
        waitingLocation = spectatorLocation

        world.entities
            .asSequence()
            .filter { it.type != EntityType.PLAYER }
            .filter { it.type != EntityType.PAINTING }
            .filter { it.type != EntityType.ITEM_FRAME }
            .forEach { it.remove() }

        for (s in config.getStringList(ConfigPath.ARENA_GAME_RULES)) {
            val rule = s.split(":")
            if (rule.size == 2) world.setGameRuleValue(rule[0], rule[1])
        }

        world.isAutoSave = false

        /* Clear setup armor-stands */
        world.entities.forEach { (it as? ArmorStand)?.remove() }

        //Create teams
        for (team in config.getConfigurationSection("Team")!!.getKeys(false)) {
            if (getTeam(team) != null) {
                plugin.logger.severe("A team with name: $team was already loaded for arena: $name")
                continue
            }
            val bwt = BedWarsTeam(
                team,
                TeamColor.valueOf(config.getString("Team.$team.Color")!!.uppercase()),
                config.getArenaLoc("Team.$team.Spawn")!!,
                config.getArenaLoc("Team.$team.Bed")!!,
                config.getArenaLoc("Team.$team.Shop")!!,
                config.getArenaLoc("Team.$team.Upgrade")!!,
                this
            )
            teams += bwt
            bwt.spawnGenerators()
        }

        //Load diamond/ emerald generators
        for (type in listOf("Diamond", "Emerald")) {
            for (s in config.getStringList("generator.$type")) {
                val location = config.convertStringToArenaLocation(s)
                oreGenerators += Generator(location, this, GeneratorOre.valueOf(type.uppercase()), null)
            }
        }

        manager.arenas[name] = this
        world.worldBorder.center = waitingLocation
        world.worldBorder.size = worldBorder

        /* Check if lobby removal is set */
        if (!config.isSet(ConfigPath.ARENA_WAITING_POS1) && config.isSet(ConfigPath.ARENA_WAITING_POS2)) {
            plugin.logger.severe("Lobby Pos1 isn't set! The arena's lobby won't be removed!")
        }
        if (config.isSet(ConfigPath.ARENA_WAITING_POS1) && !config.isSet(ConfigPath.ARENA_WAITING_POS2)) {
            plugin.logger.severe("Lobby Pos2 isn't set! The arena's lobby won't be removed!")
        }

        /* Register arena signs */
        registerSigns()
        //Call event
        plugin.server.pluginManager.callEvent(ArenaEnableEvent(this))


        changeStatus(GameState.WAITING)

        nextEvents += NextEvent.entries.map { it.name }

        listOf(
            ::upgradeDiamondsCount to ConfigPath.GENERATOR_DIAMOND_TIER_II_START,
            ::upgradeEmeraldsCount to ConfigPath.GENERATOR_EMERALD_TIER_II_START
        ).forEach { (property, path) ->
            property.set(plugin.configs.generators.getInt(
                "$group.$path",
                plugin.configs.generators.getInt("Default.$path")
            ))
        }
        plugin.logger.info("Load done: $name")


        // entity tracking range - player
        val spigot = plugin.server.spigot().config
        renderDistance = spigot.getInt(
            "world-settings.$worldName.entity-tracking-range.players",
            spigot.getInt("world-settings.default.entity-tracking-range.players")
        )
    }

    /**
     * Add a player to the arena
     * 
     * @param player              - Player to add.
     * @param skipOwnerCheck - True if you want to skip the party checking for this player. This
     * @return true if was added.
     */
    override fun addPlayer(player: Player, skipOwnerCheck: Boolean): Boolean {
        BedWars.debug("Player added: " + player.name + " arena: " + name)
        /* used for base enter/leave event */
        BaseListener.isOnABase.remove(player)
        //
        if (manager.getArena(player) != null) return false

        val party = plugin.partyUtil
        if (party.hasParty(player)) {
            if (!skipOwnerCheck) {
                if (!party.isOwner(player)) {
                    player.sendLangMsg(Messages.COMMAND_JOIN_DENIED_NOT_PARTY_LEADER)
                    return false
                }

                val partySize = party.getMembers(player).count {
                    manager.getArena(it)?.isSpectator(it) != false
                }

                if (partySize > maxInTeam * teams.size - players.size) {
                    player.sendLangMsg(Messages.COMMAND_JOIN_DENIED_PARTY_TOO_BIG)
                    return false
                }

                for (mem in party.getMembers(player)) {
                    if (mem === player) continue
                    val arena = manager.getArena(mem)
                    if (arena != null) {
                        /*if (arena.isPlayer(mem)) {
                            arena.removePlayer(mem, false)
                        } else*/
                        if (arena.isSpectator(mem)) {
                            arena.removeSpectator(mem, false)
                        }
                    }
                    addPlayer(mem, true)
                }
            }
        }

        leavingPlayers.remove(player)

        if (status == GameState.WAITING || (status == GameState.STARTING && (startingTask != null && startingTask!!.countdown > 1))) {
            if (players.size >= maxPlayers && !plugin.isVIP(player)) {
                player.spigot().sendMessage(Utils.component(
                    Language.getMsg(player, Messages.COMMAND_JOIN_DENIED_IS_FULL),
                    "",
                    plugin.mainConfig.getString("storeLink")!!,
                    ClickEvent.Action.OPEN_URL
                ))
                return false
            } else if (players.size >= maxPlayers && plugin.isVIP(player)) {
                var canJoin = false
                for (on in players.toList()) {
                    if (!plugin.isVIP(on)) {
                        canJoin = true
                        removePlayer(on, false)
                        player.spigot().sendMessage(Utils.component(
                            Language.getMsg(player, Messages.ARENA_JOIN_VIP_KICK),
                            "",
                            plugin.mainConfig.getString("storeLink")!!,
                            ClickEvent.Action.OPEN_URL
                        ))
                        break
                    }
                }
                if (!canJoin) {
                    player.sendLangMsg(Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS)
                    return false
                }
            }

            val ev = PlayerJoinArenaEvent(this, player, false)
            Bukkit.getPluginManager().callEvent(ev)
            if (ev.isCancelled()) return false

            //Remove from ReJoin
            val rejoin = ReJoin.getPlayer(player)
            rejoin?.destroy(true)

            player.apply {
                closeInventory()
                players += this
                isFlying = false
                allowFlight = false
                health = 20.0
            }
            for (on in players) {
                on.sendMessage(Language
                    .getMsg(on, Messages.COMMAND_JOIN_PLAYER_JOIN_MSG)
                    .replace("{vPrefix}", BedWars.chatSupport.getPrefix(player))
                    .replace("{vSuffix}", BedWars.chatSupport.getSuffix(player))
                    .replace("{playername}", player.name)
                    .replace("{player}", player.displayName)
                    .replace("{on}", "${players.size}")
                    .replace("{max}", "$maxPlayers")
                )
            }

            /* check if you can start the arena */
            var isStatusChange = false
            if (status == GameState.WAITING) {
                var teams = 0
                var teammates = 0
                for (on in players) {
                    if (party.isOwner(on)) teams++
                    if (party.hasParty(on)) teammates++
                }
                if (minPlayers <= players.size && teams > 0 && players.size != teammates / teams) {
                    changeStatus(GameState.STARTING)
                    isStatusChange = true
                } else if (players.size >= minPlayers && teams == 0) {
                    changeStatus(GameState.STARTING)
                    isStatusChange = true
                }
            }

            //half full arena time shorten
            if (players.size >= maxPlayers / 2 && players.size > minPlayers) {
                val startingTask = startingTask
                if (startingTask != null && Bukkit.getScheduler().isCurrentlyRunning(startingTask.task)) {
                    val countdown = plugin.mainConfig.getInt(ConfigPath.GENERAL_CONFIGURATION_START_COUNTDOWN_HALF)
                    if (startingTask.countdown > countdown) {
                        startingTask.countdown = countdown
                    }
                }
            }

            /* save player inventory etc */
            if (plugin.serverType != ServerType.BUNGEE) {
                PlayerGoods += player
                manager.playerLocation[player] = player.location
            }
            player.teleportSafe(waitingLocation)

            if (!isStatusChange) {
                plugin.scoreboardManager.giveSidebar(player, this, false)
            }
            sendPreGameCommandItems(player)
            for (pf in player.activePotionEffects) {
                player.removePotionEffect(pf.type)
            }
        } else if (status == GameState.PLAYING) {
            addSpectator(player, false, null)
            /* stop code if status playing*/
            return false
        }

        player.inventory.setArmorContents(null)
        val nms = plugin.versionSupport
        plugin.run(delay = 17) {
            // bungee mode invisibility issues
            if (plugin.serverType == ServerType.BUNGEE) {
                // fix invisibility issue
                //if (BedWars.nms.getVersion() == 7) {
                nms.sendPlayerSpawnPackets(player, this)
                //}
            }
            for (on in Bukkit.getOnlinePlayers()) {
                if (on == null || on == player) continue
                if (isPlayer(on)) {
                    nms.showPlayer(player, on)
                    nms.showPlayer(on, player)
                } else {
                    nms.hidePlayer(player, on)
                    nms.hidePlayer(on, player)
                }
            }
            if (plugin.serverType == ServerType.BUNGEE) {
                // fix invisibility issue
                //if (BedWars.nms.getVersion() == 7) {
                nms.sendPlayerSpawnPackets(player, this)
                //}
            }
        }

        if (plugin.serverType == ServerType.BUNGEE) {
            player.enderChest.clear()
        }

        if (players.size >= maxPlayers) {
            val startingTask = startingTask
            if (startingTask != null && Bukkit.getScheduler().isCurrentlyRunning(startingTask.task)) {
                val countdown = plugin.mainConfig.getInt(ConfigPath.GENERAL_CONFIGURATION_START_COUNTDOWN_SHORTENED)
                if (startingTask.countdown > countdown)
                    startingTask.countdown = countdown
            }
        }

        refreshSigns()
        plugin.npcSupport?.updateNPCs(group)
        return true
    }

    /**
     * Add a player as Spectator
     * 
     * @param player            Player to be added
     * @param playerBefore True if the player has played in this arena before and he died so now should be a spectator.
     */
    override fun addSpectator(player: Player, playerBefore: Boolean, staffTeleport: Location?): Boolean {
        if (isAllowSpectate || playerBefore || staffTeleport != null) {
            BedWars.debug("Spectator added: " + player.name + " arena: " + name)

            if (!playerBefore) {
                val ev = PlayerJoinArenaEvent(this, player, true)
                Bukkit.getPluginManager().callEvent(ev)
                if (ev.isCancelled()) return false
            }

            //Remove from ReJoin
            ReJoin.getPlayer(player)?.destroy(true)

            leavingPlayers.remove(player)

            player.closeInventory()
            spectators += player
            players.remove(player)

            updateSpectatorCollideRule(player, false)

            if (!playerBefore) {
                /* save player inv etc if isn't saved yet*/
                if (plugin.serverType != ServerType.BUNGEE) {
                    PlayerGoods += player
                    manager.playerLocation[player] = player.location
                }
            }

            plugin.scoreboardManager.giveSidebar(player, this, false)
            val nms = plugin.versionSupport
            nms.setCollide(player, this, false)

            if (!playerBefore) {
                player.teleportSafe(staffTeleport ?: spectatorLocation)
            }

            player.gameMode = GameMode.ADVENTURE

            plugin.run(delay = 5) {
                if (player in leavingPlayers) return@run
                player.allowFlight = true
                player.isFlying = true
            }

            if (player.passenger != null && player.passenger!!.type == EntityType.ARMOR_STAND) player.passenger!!.remove()

            plugin.run {
                if (player in leavingPlayers) return@run

                for (on in Bukkit.getOnlinePlayers()) {
                    if (on === player) continue
                    when (on) {
                        in spectators -> {
                            nms.showPlayer(player, on)
                            nms.showPlayer(on, player)
                        }
                        in players -> {
                            nms.hidePlayer(player, on)
                            nms.showPlayer(on, player)
                        }
                        else -> {
                            nms.hidePlayer(player, on)
                            nms.hidePlayer(on, player)
                        }
                    }
                }


                player.apply {
                    teleportSafe(if (!playerBefore && staffTeleport != null) staffTeleport else spectatorLocation)
                    allowFlight = true
                    isFlying = true

                    /* Spectator items */
                    sendSpectatorCommandItems(this)
                    // make invisible because it's annoying when there are many spectators around the map
                    addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Int.MAX_VALUE, 1, false))
                    inventory.setArmorContents(null)
                }
            }

            leavingPlayers.remove(player)

            player.sendLangMsg(Messages.COMMAND_JOIN_SPECTATOR_MSG, "{arena}" to displayName)

            /* update generator holograms for spectators */
            val iso = Language.getLanguage(player).iso
            allGenerators.forEach { it.updateHolograms(player, iso) }

            for (sh in ShopHolo.shopHolo) {
                if (sh.a === this) {
                    sh.updateForPlayer(player, iso)
                }
            }
        } else {
            player.sendLangMsg(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG)
            return false
        }

        showTime.remove(player)
        refreshSigns()
        plugin.npcSupport?.updateNPCs(group)
        return true
    }

    /**
     * Remove a player from the arena
     * 
     * @param player          Player to be removed
     * @param disconnect True if the player was disconnected
     */
    override fun removePlayer(player: Player, disconnect: Boolean) {
        if (player in leavingPlayers) return

        leavingPlayers += player
        BedWars.debug("Player removed: ${player.name} arena: $name")
        respawnSessions.remove(player)

        plugin.afkManager.setAFK(player, null)

        val team = if (status == GameState.PLAYING) getTeam(player) else null
        if (team != null) {
            team.members.remove(player)
            team.destroyBedHolo(player)
        }

        val cacheList = ShopCache.getShopCache(player.uniqueId)?.cachedPermanents ?: emptyList()

        val lastHit = LastHit.getLastHit(player)
        // accept damager in last 13 seconds only.
        val lastDamager = if (lastHit == null || lastHit.time < System.currentTimeMillis() - 13000) null else lastHit.damager as? Player
        Bukkit.getPluginManager().callEvent(PlayerLeaveArenaEvent(player, this, lastDamager))
        //players.remove must be under call event in order to check if the player is a spectator or not
        players.remove(player)

        for (pf in player.activePotionEffects) {
            player.removePotionEffect(pf.type)
        }

        if (player.passenger != null && player.passenger!!.type == EntityType.ARMOR_STAND) player.passenger!!.remove()

        val party = plugin.partyUtil
        var hasParty = players.any { party.hasParty(it) }

        val allPlayers = allPlayers

        if (status == GameState.STARTING && (maxInTeam > players.size && hasParty || players.size < minPlayers && !hasParty)) {
            changeStatus(GameState.WAITING)
            players.sendLangMsg(Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT)
        } else if (status == GameState.PLAYING) {
            BedWars.debug("removePlayer debug1")
            val teamsAlive = teams.count { it.members.isNotEmpty() }
            if (teamsAlive == 1 && !plugin.isShuttingDown) {
                checkWinner()
                plugin.run(delay = 10) { changeStatus(GameState.RESTARTING) }

                if (team != null && !team.isBedDestroyed) {
                    allPlayers.forEach {
                        it.sendLangMsg(
                            Messages.TEAM_ELIMINATED_CHAT,
                            "{TeamColor}" to team.color.chat.toString(),
                            "{TeamName}" to team.getDisplayName(Language.getLanguage(it))
                        )
                    }
                }
            } else if (teamsAlive == 0 && !plugin.isShuttingDown) {
                plugin.run(delay = 10) { changeStatus(GameState.RESTARTING) }
            } else if (!plugin.isShuttingDown && team != null && !team.isBedDestroyed) {
                //ReJoin feature
                ReJoin(player, this, team, cacheList)
            }

            // pvp log out
            if (team != null && lastDamager != null && lastDamager in players) {
                val killerTeam = getTeam(lastDamager)
                if (killerTeam != null) {
                    val (cause, message) = if (team.isBedDestroyed)
                        PlayerKillEvent.PlayerKillCause.PLAYER_DISCONNECT_FINAL to Messages.PLAYER_DIE_PVP_LOG_OUT_FINAL
                    else PlayerKillEvent.PlayerKillCause.PLAYER_DISCONNECT to Messages.PLAYER_DIE_PVP_LOG_OUT_REGULAR

                    val event = PlayerKillEvent(this, player, team, lastDamager, killerTeam, cause) {
                        Language.getMsg(it, message)
                    }
                    plugin.server.pluginManager.callEvent(event)

                    allPlayers.forEach {
                        val lang = Language.getLanguage(it)
                        it.sendMessage(event.message(it)
                            .replace("{PlayerColor}", "${team.color.chat}")
                            .replace("{PlayerName}", player.displayName)
                            .replace("{PlayerTeamName}", team.getDisplayName(lang))
                            .replace("{KillerColor}", "${killerTeam.color.chat}")
                            .replace("{KillerName}", lastDamager.displayName)
                            .replace("{KillerTeamName}", killerTeam.getDisplayName(lang))
                        )
                    }

                    PlayerDrops.handlePlayerDrops(this, player, lastDamager, team, killerTeam, cause, player.inventory.contents.toList())
                }
            }
        }
        allPlayers.sendLangMsg(Messages.COMMAND_LEAVE_MSG,
            "{vPrefix}" to BedWars.chatSupport.getPrefix(player),
            "{vSuffix}" to BedWars.chatSupport.getSuffix(player),
            "{playername}" to player.name,
            "{player}" to player.displayName
        )

        if (plugin.sendToMainLobby(player, this)) return

        /* restore player inventory */
        if (player !in PlayerGoods) {
            // if there is no previous backup of the inventory send lobby items if multi arena
            if (plugin.serverType == ServerType.MULTIARENA) {
                // Send items
                manager.sendLobbyCommandItems(player)
            }
        } else PlayerGoods -= player
        manager.playerLocation.remove(player)
        for (pf in player.activePotionEffects) {
            player.removePotionEffect(pf.type)
        }

        if (!plugin.isShuttingDown) {
            plugin.run(delay = 5) {
                val nms = plugin.versionSupport
                for (on in Bukkit.getOnlinePlayers()) {
                    if (on == player) continue
                    if (manager.getArena(on) == null) {
                        nms.showPlayer(player, on)
                        nms.showPlayer(on, player)
                    } else {
                        nms.hidePlayer(player, on)
                        nms.hidePlayer(on, player)
                    }
                }
                if (!disconnect) plugin.scoreboardManager.giveSidebar(player, null, false)
            }
        }

        /* Remove also the party */
        if (status != GameState.RESTARTING && party.hasParty(player) && party.isOwner(player)) {
            if (party.isInternal) {
                party.getMembers(player).sendLangMsg(Messages.ARENA_LEAVE_PARTY_DISBANDED)
            }
            party.disband(player)

            // prevent arena from staring with a single player
            hasParty = players.any { party.hasParty(it) }
            if (status == GameState.STARTING && (maxInTeam > players.size && hasParty || players.size < minPlayers && !hasParty)) {
                changeStatus(GameState.WAITING)
                players.sendLangMsg(Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT)
            }
        }
        player.isFlying = false
        player.allowFlight = false

        //Remove from ReJoin if game ended
        if (status == GameState.RESTARTING) {
            val rejoin = ReJoin.getPlayer(player)
            if (rejoin?.arena === this) {
                rejoin.destroy(false)
            }
        }

        //Remove from magic milk
        val taskId = manager.magicMilk.remove(player.uniqueId)
        if (taskId != null && taskId > 0) {
            Bukkit.getScheduler().cancelTask(taskId)
        }

        showTime.remove(player)

        refreshSigns()
        plugin.npcSupport?.updateNPCs(group)
        lastHit?.remove()

        // fix #340
        // remove player from party if leaves and the owner is still in the arena while waiting or starting
        if (!status.isPreGame() || !party.hasParty(player) || party.isOwner(player)) return

        val owner = party.getOwner(player)
        if (owner == null || !owner.world.name.equals(name, ignoreCase = true)) return

        party.removeFromParty(player)
    }

    /**
     * Remove a spectator from the arena
     * 
     * @param player          Player to be removed
     * @param disconnect True if the player was disconnected
     */
    override fun removeSpectator(player: Player, disconnect: Boolean) {
        BedWars.debug("Spectator removed: ${player.name} arena: $name")
        if (player in leavingPlayers) return
        leavingPlayers += player

        plugin.server.pluginManager.callEvent(PlayerLeaveArenaEvent(player, this, null))
        spectators -= player
        player.inventory.clear()
        player.inventory.setArmorContents(null)

        val nms = plugin.versionSupport
        nms.setCollide(player, this, true)

        plugin.afkManager.setAFK(player, null)

        if (plugin.sendToMainLobby(player, this)) return

        for (pf in player.activePotionEffects) {
            player.removePotionEffect(pf.type)
        }

        /* restore player inventory */
        if (player !in PlayerGoods) {
            // if there is no previous backup of the inventory send lobby items if multi arena
            if (plugin.serverType == ServerType.MULTIARENA) {
                // Send items
                manager.sendLobbyCommandItems(player)
            }
        } else PlayerGoods -= player


        manager.playerLocation.remove(player)

        if (!plugin.isShuttingDown) plugin.run {
            for (on in Bukkit.getOnlinePlayers()) {
                if (on == player) continue
                if (manager.getArena(on) == null) {
                    nms.showPlayer(player, on)
                    nms.showPlayer(on, player)
                } else {
                    nms.hidePlayer(player, on)
                    nms.hidePlayer(on, player)
                }
            }
            if (!disconnect) plugin.scoreboardManager.giveSidebar(player, null, false)
        }

        /* Remove also the party */
        val party = plugin.partyUtil
        if (status != GameState.RESTARTING && party.hasParty(player) && party.isOwner(player)) {
            if (party.isInternal) {
                for (mem in party.getMembers(player)) {
                    mem.sendLangMsg(Messages.ARENA_LEAVE_PARTY_DISBANDED)
                }
            }
            party.disband(player)
        }

        player.isFlying = false
        player.allowFlight = false

        //Remove from ReJoin if game ended
        val rejoin = ReJoin.getPlayer(player)
        if (rejoin?.arena === this)
            rejoin.destroy(false)

        //Remove from magic milk
        val milk = manager.magicMilk[player.uniqueId]
        if (milk != null && milk > 0) {
            Bukkit.getScheduler().cancelTask(milk)
        }

        refreshSigns()
        plugin.npcSupport?.updateNPCs(group)
    }

    /**
     * Rejoin an arena
     */
    override fun reJoin(player: Player): Boolean {
        val reJoin = ReJoin.getPlayer(player) ?: return false
        if (reJoin.arena !== this) return false
        if (!reJoin.canReJoin()) return false

        reJoin.task?.destroy()

        val ev = PlayerReJoinEvent(player, this, plugin.mainConfig.getInt(ConfigPath.GENERAL_CONFIGURATION_RE_SPAWN_COUNTDOWN))
        Bukkit.getPluginManager().callEvent(ev)
        if (ev.isCancelled()) return false

        for (on in Bukkit.getOnlinePlayers()) {
            if (on == player) continue
            if (manager.isInArena(on)) continue
            plugin.versionSupport.hidePlayer(on, player)
            plugin.versionSupport.hidePlayer(player, on)
        }

        player.closeInventory()
        players += player
        allPlayers.sendLangMsg(Messages.COMMAND_REJOIN_PLAYER_RECONNECTED,
            "{playername}" to player.name,
            "{player}" to player.displayName,
            "{on}" to "${players.size}",
            "{max}" to "$maxPlayers"
        )
        /* save player inventory etc */
        if (plugin.serverType != ServerType.BUNGEE) {
            // no need to backup inventory because it's empty
            //new PlayerGoods(p, true, true);
            manager.playerLocation[player] = player.location
        }
        player.teleportSafe(spectatorLocation)
        player.inventory.clear()

        //restore items before re-spawning in team
        var sc = ShopCache.getShopCache(player.uniqueId)
        sc?.destroy()
        sc = ShopCache(player.uniqueId)
        for (ci in reJoin.permanentsAndNonDowngradables) {
            sc.cachedItems += ci
        }

        reJoin.team.reJoin(player, ev.respawnTime)
        reJoin.destroy(false)

        plugin.scoreboardManager.giveSidebar(player, this, true)
        return true
    }

    /**
     * Disable the arena.
     * This will automatically kick/ remove the people from the arena.
     */
    override fun disable() {
        players.forEach { removePlayer(it, false) }
        spectators.forEach { removeSpectator(it, false) }

        plugin.logger.warning("Disabling arena: $name")
        Bukkit.getPluginManager().callEvent(ArenaDisableEvent(name, worldName))
        destroyData()
        plugin.restoreAdapter.onDisable(this)
    }

    /**
     * Restart the arena.
     */
    override fun restart() {
        plugin.logger.fine("Restarting arena: $name")
        Bukkit.getPluginManager().callEvent(ArenaRestartEvent(name, worldName))
        destroyData()
        plugin.restoreAdapter.onRestart(this)
    }

    /**
     * Get the display status for an arena.
     * A message that can be used on signs etc.
     */
    override fun getDisplayStatus(lang: Language) = lang.m(when (status) {
        GameState.WAITING -> Messages.ARENA_STATUS_WAITING_NAME
        GameState.STARTING -> Messages.ARENA_STATUS_STARTING_NAME
        GameState.RESTARTING -> Messages.ARENA_STATUS_RESTARTING_NAME
        GameState.PLAYING -> Messages.ARENA_STATUS_PLAYING_NAME
    }).replace("{full}", if (players.size == maxPlayers) lang.m(Messages.MEANING_FULL) else "")

    override fun addPlacedBlock(block: Block) {
        placed += block.location.toVector()
    }

    override fun removePlacedBlock(block: Block) {
        if (!isBlockPlaced(block)) return
        placed -= block.location.toVector()
    }

    override fun isBlockPlaced(block: Block) = block.location.toVector() in placed

    /**
     * Change game status starting tasks.
     */
    override fun changeStatus(status: GameState) {
        // prevent called twice #https://github.com/andrei1058/BedWars1058/issues/774
        if (status == this.status) return

        plugin.server.pluginManager.callEvent(GameStateChangeEvent(this, this.status, status))
        this.status = status

        refreshSigns()

        val allPlayers = allPlayers
        if (status == GameState.PLAYING) {
            allPlayers.forEach { plugin.afkManager.setAFK(it, null) }

            // Initialize game stats
            players.forEach { statsHolder.init(it) }
        }

        //Stop active tasks to prevent issues
        startingTask?.cancel()
        startingTask = null

        playingTask?.cancel()
        playingTask = null

        restartingTask?.cancel()
        restartingTask = null

        moneyperMinuteTask?.cancel()
        perMinuteTask?.cancel()

        allPlayers.forEach { plugin.scoreboardManager.giveSidebar(it, this, false) }

        when (status) {
            GameState.STARTING -> startingTask = GameStartingTask(this)
            GameState.PLAYING -> {
                if (plugin.levelManager is InternalLevelManager) {
                    perMinuteTask = PerMinuteTask(this)
                }
                if (BedWars.economy is WithEconomy) {
                    moneyperMinuteTask = MoneyPerMinuteTask(this)
                }
                playingTask = GamePlayingTask(this)
            }
            GameState.RESTARTING -> restartingTask = GameRestartingTask(this)
            else -> {}
        }
    }

    /**
     * Check if a player is playing.
     */
    override fun isPlayer(player: Player) = player in players

    /**
     * Check if a player is spectating.
     */
    override fun isSpectator(player: Player) = player in spectators

    override fun isSpectator(player: UUID) = spectators.any { it.uniqueId == player }

    override fun isRespawning(player: UUID) = respawnSessions.keys.any { it.uniqueId == player }

    /**
     * Add a join sign for the arena.
     */
    override fun addSign(loc: Location) {
        val block = loc.block
        if (!block.type.toString().endsWith("_SIGN")) return

        signs += block
        refreshSigns()
        BlockStatusListener.updateBlock(this)
    }

    /**
     * Refresh signs.
     */
    @Synchronized
    override fun refreshSigns() {
        for (b in signs) {
            val s = b.state as? Sign ?: return
            for ((line, string) in plugin.configs.signs.getStringList("format").withIndex()) {
                s.setLine(line, string
                    .replace("[on]", "${players.size}")
                    .replace("[max]", "$maxPlayers")
                    .replace("[arena]", displayName)
                    .replace("[status]", getDisplayStatus(Language.defaultLanguage))
                    .replace("[type]", "$maxInTeam")
                )
            }
            try {
                s.update(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * This will give the pre-game command Items.
     * This will clear the inventory first.
     */
    override fun sendPreGameCommandItems(player: Player) {
        val config = plugin.mainConfig
        val preGameItems = config.getConfigurationSection(ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_PATH) ?: return
        player.inventory.clear()

        for (item in preGameItems.getKeys(false)) {
            val material = ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_MATERIAL.replace("%path%", item)
            if (material !in config) {
                plugin.logger.severe("$material is not set!")
                continue
            }

            val slot = ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_SLOT.replace("%path%", item)
            if (slot !in config) {
                plugin.logger.severe("$slot is not set!")
                continue
            }

            val command = ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_COMMAND.replace("%path%", item)
            if (command !in config) {
                plugin.logger.severe("$command is not set!")
                continue
            }

            player.inventory.setItem(
                config.getInt(slot),
                Misc.createItem(
                    Material.valueOf(config.getString(material)!!),
                    config.getInt(ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_DATA.replace("%path%", item)).toByte(),
                    config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_ENCHANTED.replace("%path%", item)),
                    PAPISupport.support.replace(player,
                        Language.getMsg(player, Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", item))
                    ),
                    PAPISupport.support.replace(player,
                        Language.getList(player, Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", item))
                    ),
                    player,
                    "RUNCOMMAND",
                    config.getString(command)!!
                )
            )

        }
    }

    /**
     * This will give the spectator command Items.
     * This will clear the inventory first.
     */
    override fun sendSpectatorCommandItems(player: Player) {
        val config = plugin.mainConfig
        val spectatorItems = config.getConfigurationSection(ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_PATH) ?: return
        player.inventory.clear()

        for (item in spectatorItems.getKeys(false)) {
            val material = ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_MATERIAL.replace("%path%", item)
            if (material !in config) {
                plugin.logger.severe("$material is not set!")
                continue
            }

            val slot = ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_SLOT.replace("%path%", item)
            if (slot !in config) {
                plugin.logger.severe("$slot is not set!")
                continue
            }

            val command = ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_COMMAND.replace("%path%", item)
            if (command !in config) {
                plugin.logger.severe("$command is not set!")
                continue
            }

            player.inventory.setItem(
                config.getInt(slot),
                Misc.createItem(
                    Material.valueOf(config.getString(material)!!),
                    config.getInt(ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_DATA.replace("%path%", item)).toByte(),
                    config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_ENCHANTED.replace("%path%", item)),
                    PAPISupport.support.replace(player,
                        Language.getMsg(player, Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", item))
                    ),
                    PAPISupport.support.replace(player,
                        Language.getList(player, Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", item))
                    ),
                    player,
                    "RUNCOMMAND",
                    config.getString(command)!!
                )
            )
        }
    }

    /**
     * Get team by player.
     * Make sure the player is in this arena first.
     */
    override fun getTeam(player: Player) = teams.find { it.isMember(player) }

    /**
     * Get ex team by player.
     * Check the team where he played before leaving or losing.
     */
    override fun getExTeam(player: UUID) = teams.find { it.wasMember(player) }

    /**
     * Check winner. You can always do that.
     * It will manage the arena restart and the needed stuff.
     */
    override fun checkWinner() {
        if (status == GameState.RESTARTING) return
        val winnerTeams = teams.filter { it.members.isNotEmpty() }

        if (winnerTeams.size == 1) {
            val winner = winnerTeams.first()
            this.winner = winner

            for (p in winner.members) p.inventory.clear()

            val winnersNames = winner.membersCache.joinToString(" ") { it.displayName }

            val topInChat = StatisticsOrdered(this, config.getGameOverridableString(ConfigPath.GENERAL_GAME_END_CHAT_TOP_STATISTIC))
            // hide stats row completely when placeholders cannot be replaced
            if (config.getGameOverridableBoolean(ConfigPath.GENERAL_GAME_END_CHAT_TOP_HIDE_MISSING)) {
                topInChat.boundsPolicy = StatisticsOrdered.BoundsPolicy.SKIP
            }

            // this is assigned to scoreboards
            val topInSidebar = StatisticsOrdered(this, config.getGameOverridableString(ConfigPath.GENERAL_GAME_END_SB_TOP_STATISTIC))

            // hide stats row completely when placeholders cannot be replaced
            if (config.getGameOverridableBoolean(ConfigPath.GENERAL_GAME_END_SB_TOP_HIDE_MISSING)) {
                topInSidebar.boundsPolicy = StatisticsOrdered.BoundsPolicy.SKIP
            }

            val statParser = topInChat.newParser()

            val nms = plugin.versionSupport
            allPlayers.forEach {
                val lang = Language.getLanguage(it)

                val winnerTeamChat = lang.m(Messages.GAME_END_TEAM_WON_CHAT)
                // check if message disabled
                if (winnerTeamChat.isNotBlank()) it.sendMessage(winnerTeamChat
                    .replace("{TeamColor}", winner.color.chat.toString())
                    .replace("{TeamName}", winner.getDisplayName(lang))
                )

                nms.sendTitle(it, lang.m(
                    if (winner.members.contains(it) || winner.wasMember(it.uniqueId)) Messages.GAME_END_VICTORY_PLAYER_TITLE
                    else Messages.GAME_END_GAME_OVER_PLAYER_TITLE
                ), null, 0, 70, 20)

                statParser.resetIndex()

                // check if message is disabled
                val topChat = lang.l(Messages.GAME_END_TOP_PLAYER_CHAT)
                if (topChat.isEmpty() || topChat.size == 1 && topChat[0]!!.isEmpty()) return@forEach

                for (s in topChat) {
                    var msg = statParser.parseString(s, lang, lang.m(Messages.MEANING_NOBODY))
                        ?: continue

                    msg = msg.replace(
                        "{winnerFormat}",
                        if (maxInTeam > 1) lang.m(Messages.FORMATTING_TEAM_WINNER_FORMAT)
                            .replace("{members}", winnersNames) else lang.m(
                            Messages.FORMATTING_SOLO_WINNER_FORMAT
                        ).replace("{members}", winnersNames)
                    )
                        .replace("{TeamColor}", winner.color.chat.toString())
                        .replace("{TeamName}", winner.getDisplayName(lang))

                    it.sendMessage(PAPISupport.support.replace(it, msg))
                }

                val sidebar = plugin.scoreboardManager.getSidebar(it)
                if (sidebar is BwSidebar) sidebar.topStatistics = topInSidebar
            }
            changeStatus(GameState.RESTARTING)

            //Game end event
            val winners = winner.membersCache.map { it.uniqueId }
            val aliveWinners = players.map { it.uniqueId }
            val losers = (teams - winner).flatMap { it.membersCache }.map { it.uniqueId }

            plugin.server.pluginManager.callEvent(GameEndEvent(this, winners, losers, winner, aliveWinners))
        }
        if (players.isEmpty() && status != GameState.RESTARTING) {
            changeStatus(GameState.RESTARTING)
        }
    }

    override fun updateNextEvent() {
        BedWars.debug("---")
        BedWars.debug("updateNextEvent called")
        val generators = plugin.configs.generators
        val (generatorType, nextEvent) = when (nextEvent) {
            NextEvent.EMERALD_GENERATOR_TIER_II if upgradeEmeraldsCount == 0 -> {
                // next diamond time < next emerald time
                upgradeEmeraldsCount = generators.getInt(
                    "$group.${ConfigPath.GENERATOR_EMERALD_TIER_III_START}",
                    generators.getInt("Default.${ConfigPath.GENERATOR_EMERALD_TIER_III_START}")
                )
                emeraldTier = 2
                GeneratorOre.EMERALD to when (diamondTier) {
                    1 if upgradeDiamondsCount < upgradeEmeraldsCount -> NextEvent.DIAMOND_GENERATOR_TIER_II
                    2 if upgradeDiamondsCount < upgradeEmeraldsCount -> NextEvent.DIAMOND_GENERATOR_TIER_III
                    else -> NextEvent.EMERALD_GENERATOR_TIER_III
                }
            }
            NextEvent.DIAMOND_GENERATOR_TIER_II if upgradeDiamondsCount == 0 -> {
                upgradeDiamondsCount = generators.getInt(
                    "$group.${ConfigPath.GENERATOR_DIAMOND_TIER_III_START}",
                    generators.getInt("Default.${ConfigPath.GENERATOR_DIAMOND_TIER_III_START}")
                )
                diamondTier = 2
                GeneratorOre.DIAMOND to when {
                    upgradeEmeraldsCount < upgradeDiamondsCount && emeraldTier == 1 -> NextEvent.EMERALD_GENERATOR_TIER_II
                    upgradeEmeraldsCount < upgradeDiamondsCount && emeraldTier == 2 -> NextEvent.EMERALD_GENERATOR_TIER_III
                    else -> NextEvent.DIAMOND_GENERATOR_TIER_III
                }
            }
            NextEvent.EMERALD_GENERATOR_TIER_III if upgradeEmeraldsCount == 0 -> {
                emeraldTier = 3
                GeneratorOre.EMERALD to when (diamondTier) {
                    1 if upgradeDiamondsCount > 0 -> NextEvent.DIAMOND_GENERATOR_TIER_II
                    2 if upgradeDiamondsCount > 0 -> NextEvent.DIAMOND_GENERATOR_TIER_III
                    else -> NextEvent.BEDS_DESTROY
                }
            }
            NextEvent.DIAMOND_GENERATOR_TIER_III if upgradeDiamondsCount == 0 -> {
                diamondTier = 3
                GeneratorOre.DIAMOND to when (emeraldTier) {
                    1 if upgradeEmeraldsCount > 0 -> NextEvent.EMERALD_GENERATOR_TIER_II
                    2 if upgradeEmeraldsCount > 0 -> NextEvent.EMERALD_GENERATOR_TIER_III
                    else -> NextEvent.BEDS_DESTROY
                }
            }
            NextEvent.BEDS_DESTROY if playingTask?.bedsDestroyCountdown == 0 -> null to NextEvent.ENDER_DRAGON
            NextEvent.ENDER_DRAGON if playingTask?.dragonSpawnCountdown == 0 -> null to NextEvent.GAME_END
            else -> null to nextEvent
        }
        if (generatorType != null) {
            when (generatorType) {
                GeneratorOre.EMERALD -> sendEmeraldsUpgradeMessages()
                GeneratorOre.DIAMOND -> sendDiamondsUpgradeMessages()
                else -> {}
            }
            for (o in oreGenerators) {
                if (o.type == generatorType && o.team == null) {
                    o.upgrade()
                }
            }
        }
        this.nextEvent = nextEvent

        BedWars.debug("---")
        BedWars.debug("$nextEvent")
    }

    /**
     * Register join-signs for arena
     */
    private fun registerSigns() {
        if (plugin.serverType == ServerType.BUNGEE) return

        for (st in plugin.configs.signs.getStringList("locations")) {
            val data = st.split(",")
            if (data[0] != name) continue

            val x = data[1].toDoubleOrNull()
            val y = data[2].toDoubleOrNull()
            val z = data[3].toDoubleOrNull()

            if (x == null || y == null || z == null) {
                plugin.logger.severe("Could not load sign at: $data")
                continue
            }

            addSign(Location(Bukkit.getWorld(data[6]), x, y, z))
        }
    }

    /**
     * Get a team by name
     */
    override fun getTeam(name: String) = teams.find { it.name == name }

    // TODO: Why is this commented? Should this be removed or updated to use BwSidebar?
    override fun updateSpectatorCollideRule(player: Player, collide: Boolean) {
/*        if (!isSpectator(player)) return
        for (sb in BedWarsScoreboard.getScoreboards().values) {
            if (sb.arena === this) {
                sb.updateSpectator(player, collide)
            }
        }*/
    }


    private fun sendUpgradeMessages(type: String, tier: String) {
        allPlayers.forEach {
            it.sendLangMsg(Messages.GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT,
                "{generatorType}" to Language.getMsg(it, type),
                "{tier}" to Language.getMsg(it, tier)
            )
        }

    }
    /**
     * Show upgrade announcement to players.
     * Change diamondTier value first.
     */
    override fun sendDiamondsUpgradeMessages() = sendUpgradeMessages(
        Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND,
        if (diamondTier == 2) Messages.FORMATTING_GENERATOR_TIER2
        else Messages.FORMATTING_GENERATOR_TIER3
    )

    /**
     * Show upgrade announcement to players.
     * Change emeraldTier value first.
     */
    override fun sendEmeraldsUpgradeMessages() = sendUpgradeMessages(
        Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD,
        if (emeraldTier == 2) Messages.FORMATTING_GENERATOR_TIER2
        else Messages.FORMATTING_GENERATOR_TIER3
    )


    override val fireballCooldowns = mutableMapOf<UUID, Long>()



    override fun destroyData() {
        world.players.forEach { it.kickPlayer("You're not supposed to be here.") }
        manager.arenas.remove(name)
        destroyReJoins()
        for (despawnable in plugin.versionSupport.despawnables.values.toList()) {
            if (despawnable.team.arena === this) {
                despawnable.destroy()
            }
        }
        teams.forEach { it.destroyData() }

        oreGenerators.forEach { it.destroyData() }
        oreGenerators.clear()

        BaseListener.isOnABase.entries.removeIf { it.value.arena == this }
        manager.playerLocation.entries.removeIf { it.value.world!!.name.equals(worldName, ignoreCase = true) }

        players.clear()
        spectators.clear()
        signs.clear()
        teams.clear()
        placed.clear()
        nextEvents.clear()
        regionsList.clear()
        respawnSessions.clear()
        showTime.clear()
        leavingPlayers.clear()
        fireballCooldowns.clear()

        startingTask?.cancel()
        startingTask = null

        playingTask?.cancel()
        playingTask = null

        restartingTask?.cancel()
        restartingTask = null

        perMinuteTask?.cancel()
        perMinuteTask = null

        moneyperMinuteTask?.cancel()
        moneyperMinuteTask = null
    }

    override fun startRespawnSession(player: Player, seconds: Int): Boolean {
        if (respawnSessions[player] != null) return false

        val arena = manager.getArena(player) ?: return false
        if (!arena.isPlayer(player)) return false

        player.inventory.clear()
        if (seconds <= 1) {
            getTeam(player)!!.respawnMember(player)
            return true
        }

        // hide to others
        val nms = plugin.versionSupport
        for (playing in arena.players) {
            if (playing == player) continue
            nms.hidePlayer(player, playing)
        }
        player.apply {
            teleportSafe(this@Arena.respawnLocation)
            allowFlight = true
            isFlying = true
        }

        respawnSessions[player] = seconds
        plugin.run(delay = 10) {
            player.allowFlight = true
            player.isFlying = true

            nms.setCollide(player, this, false)
            // #274
            for (invisible in showTime.keys) {
                nms.hideArmor(invisible, player)
            }

            updateSpectatorCollideRule(player, false)
            player.teleportSafe(respawnLocation)
        }
        return true
    }

    override fun isRespawning(player: Player) = respawnSessions.containsKey(player)

    override fun equals(other: Any?) = (other as? Arena)?.worldName == worldName

    private fun destroyReJoins() {
        ReJoin.destroy(this)
        for (rjt in ReJoinTask.reJoinTasks) {
            if (rjt.arena === this) {
                rjt.destroy()
            }
        }
    }

    override fun isProtected(location: Location) = regionsList.any { it.isInRegion(location) } ||
            allGenerators.any { it.location.distance(location) <= config.getInt(ConfigPath.ARENA_GENERATOR_PROTECTION) } ||
            isOutsideOfBorder(location)

    fun isOutsideOfBorder(location: Location): Boolean {
        val border = location.world!!.worldBorder
        if (plugin.versionSupport.version > 0) return !border.isInside(location)
        val radius = border.size / 2 + border.warningDistance
        return border.center.distance(location) >= radius
    }

    override fun abandonGame(player: Player) {
        val team = getExTeam(player.uniqueId) ?: return
        team.membersCache.removeIf { it.uniqueId == player.uniqueId }
        ReJoin.getPlayer(player)?.destroy(team.members.isEmpty())
    }

    override fun getTeamBed(location: Location): ITeam? {
        if (location.world!!.name != worldName) {
            throw RuntimeException("Given location is not on this game world.")
        }

        if (!plugin.versionSupport.isBed(location.block.type)) return null

        return teams.find { it.isBed(location) }
    }

    override fun isTeamBed(location: Location) = getTeamBed(location) != null
}
