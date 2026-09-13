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
package com.andrei1058.bedwars

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigManager
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.levels.Level
import com.andrei1058.bedwars.api.party.Party
import com.andrei1058.bedwars.api.server.RestoreAdapter
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.server.VersionSupport
import com.andrei1058.bedwars.arena.Arena
import com.andrei1058.bedwars.arena.ArenaManagerImpl
import com.andrei1058.bedwars.arena.Misc
import com.andrei1058.bedwars.arena.VoidChunkGenerator
import com.andrei1058.bedwars.arena.despawnables.TargetListener
import com.andrei1058.bedwars.arena.feature.SpoilPlayerTNTFeature
import com.andrei1058.bedwars.arena.spectator.SpectatorListeners
import com.andrei1058.bedwars.arena.stats.DefaultStatsHandler
import com.andrei1058.bedwars.arena.tasks.OneTick
import com.andrei1058.bedwars.arena.tasks.Refresh
import com.andrei1058.bedwars.arena.upgrades.BaseListener
import com.andrei1058.bedwars.arena.upgrades.HealPoolListner
import com.andrei1058.bedwars.commands.bedwars.MainCommand
import com.andrei1058.bedwars.commands.leave.LeaveCommand
import com.andrei1058.bedwars.commands.party.PartyCommand
import com.andrei1058.bedwars.commands.rejoin.RejoinCommand
import com.andrei1058.bedwars.commands.shout.ShoutCommand
import com.andrei1058.bedwars.configuration.*
import com.andrei1058.bedwars.database.Database
import com.andrei1058.bedwars.database.MySQL
import com.andrei1058.bedwars.database.SQLite
import com.andrei1058.bedwars.halloween.HalloweenSpecial
import com.andrei1058.bedwars.language.*
import com.andrei1058.bedwars.levels.internal.InternalLevel
import com.andrei1058.bedwars.levels.internal.LevelListeners
import com.andrei1058.bedwars.listeners.*
import com.andrei1058.bedwars.listeners.arenaselector.ArenaSelectorListener
import com.andrei1058.bedwars.listeners.blockstatus.BlockStatusListener
import com.andrei1058.bedwars.listeners.chat.ChatAFK
import com.andrei1058.bedwars.listeners.chat.ChatFormatting
import com.andrei1058.bedwars.listeners.joinhandler.*
import com.andrei1058.bedwars.lobbysocket.ArenaSocket
import com.andrei1058.bedwars.lobbysocket.LoadedUsersCleaner
import com.andrei1058.bedwars.lobbysocket.SendTask
import com.andrei1058.bedwars.maprestore.internal.InternalAdapter
import com.andrei1058.bedwars.metrics.MetricsManager
import com.andrei1058.bedwars.money.internal.MoneyListeners
import com.andrei1058.bedwars.shop.ShopManager
import com.andrei1058.bedwars.sidebar.ScoreboardManagerImpl
import com.andrei1058.bedwars.support.citizens.CitizensListener
import com.andrei1058.bedwars.support.citizens.JoinNPC
import com.andrei1058.bedwars.Utils.teleportSafe
import com.andrei1058.bedwars.support.papi.PAPISupport
import com.andrei1058.bedwars.support.papi.SupportPAPI
import com.andrei1058.bedwars.support.party.*
import com.andrei1058.bedwars.support.preloadedparty.PrePartyListener
import com.andrei1058.bedwars.support.vault.NoChat
import com.andrei1058.bedwars.support.vault.NoEconomy
import com.andrei1058.bedwars.support.vault.WithChat
import com.andrei1058.bedwars.support.vault.WithEconomy
import com.andrei1058.bedwars.support.vipfeatures.VipFeatures
import com.andrei1058.bedwars.support.vipfeatures.VipListeners
import com.andrei1058.bedwars.upgrades.UpgradesManagerImpl
import com.andrei1058.vipfeatures.api.IVipFeatures
import com.andrei1058.vipfeatures.api.MiniGameAlreadyRegistered
import net.milkbowl.vault.chat.Chat
import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.WorldCreator
import org.bukkit.entity.Monster
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.ServicePriority
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import java.io.File
import java.lang.Deprecated

class BedWars : JavaPlugin() {
    val metrics = MetricsManager(this)
    val afkManager = AFKManagerImpl()
    val arenaManager = ArenaManagerImpl(this)
    lateinit var scoreboardManager: ScoreboardManagerImpl
    lateinit var upgradesManager: UpgradesManagerImpl

    private var levelListeners: LevelListeners? = null
    lateinit var levelsConfig: LevelsConfig
    lateinit var moneyConfig: MoneyConfig

    override fun onLoad() {
        //Spigot support
        try {
            Class.forName("org.spigotmc.SpigotConfig")
        } catch (_: Exception) {
            logger.severe("I can't run on your server software. Please check:")
            logger.severe("https://gitlab.com/andrei1058/BedWars1058/wikis/compatibility")
            enabled = false
            return
        }

        isPaper = try {
            Class.forName("com.destroystokyo.paper.PaperConfig")
            true
        } catch (_: ClassNotFoundException) {
            false
        }

        plugin = this

        /* Load version support */
        val supp = try {
            Class.forName("com.andrei1058.bedwars.support.version.$serverVersion.$serverVersion")
        } catch (_: ClassNotFoundException) {
            enabled = false
            logger.severe("I can't run on your version: $serverVersion")
            return
        }

        api = API(this)
        server.servicesManager.register(
            com.andrei1058.bedwars.api.BedWars::class.java,
            api,
            this,
            ServicePriority.Highest
        )

        try {
            nms = supp.getConstructor(Plugin::class.java, String::class.java)
                .newInstance(this, serverVersion) as VersionSupport
        } catch (e: Exception) {
            e.printStackTrace()
            enabled = false
            logger.severe("Could not load support for server version: $serverVersion")
            return
        }

        logger.info("Loading support for paper/spigot: $serverVersion")

        // Setup languages
        English()
        Romanian()
        Italian()
        Polish()
        Spanish()
        Russian()
        Bangla()
        Persian()
        Hindi()
        Indonesia()
        Portuguese()
        SimplifiedChinese()
        Turkish()

        Companion.config = MainConfig(this)

        generatorsCfg = GeneratorsConfig(this)
        // Initialize signs config after the main config
        if (serverType != ServerType.BUNGEE) {
            signs = SignsConfig(this, "signs", dataFolder.path)
        }
    }

    override fun onEnable() {
        if (!enabled) {
            server.pluginManager.disablePlugin(this)
            return
        }

        nms.registerVersionListeners()

        if (!handleWorldAdapter()) {
            api.restoreAdapter = InternalAdapter(this)
            logger.info("Using internal world restore system.")
        }

        /* Register commands */
        nms.commandMap.register(MAIN_COMMAND, MainCommand(this, MAIN_COMMAND))

        // newer versions do not seem to like delayed registration of commands
        if (nms.version >= 9) {
            registerCommands()
        } else {
            run(delay = 20L) { registerCommands() }
        }

        /* Setup plugin messaging channel */
        server.messenger.registerOutgoingPluginChannel(this, "BungeeCord")

        /* Check if lobby location is set. Required for non Bungee servers */
        val lobbyName = Companion.config.lobbyWorldName
        if (lobbyName.isEmpty() && serverType != ServerType.BUNGEE) {
            logger.warning("Lobby location is not set!")
        }

        /* Load lobby world if not main level
         * when the server finishes loading. */
        if (serverType == ServerType.MULTIARENA) run(delay = 1) {
            if (lobbyName.isEmpty()) return@run

            if (server.getWorld(lobbyName) == null && File(server.worldContainer, "$lobbyName/level.dat").exists() &&
                !lobbyName.equals(server.worlds[0].name, ignoreCase = true)) {
                    run(delay = 100) {
                        server.createWorld(WorldCreator(lobbyName))
                        if (server.getWorld(lobbyName) == null) return@run

                        run(delay = 20) {
                            server.getWorld(lobbyName)!!
                                .entities
                                .filterIsInstance<Monster>()
                                .forEach { it.remove() }
                        }
                    }
            }
            val l = Companion.config.getConfigLoc("lobbyLoc") ?: return@run
            val w = server.getWorld(Companion.config.lobbyWorldName)
            w?.setSpawnLocation(l.blockX, l.blockY, l.blockZ)
        }

        // Register events
        registerEvents(
            EnderPearlLanded(this), QuitAndTeleportListener(this), BreakPlace(this), DamageDeathMove(),
            Inventory(this), Interact(this), RefreshGUI(), HungerWeatherSpawn(this), CmdProcess(this),
            FireballListener(this), EggBridge(this), SpectatorListeners(this), BaseListener(this),
            TargetListener(this), LangListener(this), Warnings(this), ChatAFK(this),
            GameEndListener(), DefaultStatsHandler()
        )

        if (Companion.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_HEAL_POOL_ENABLE)) {
            registerEvents(HealPoolListner())
        }

        if (serverType == ServerType.BUNGEE) {
            if (autoscale) {
                //registerEvents(ArenaListeners());
                ArenaSocket.lobbies.addAll(Companion.config.getStringList(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_LOBBY_SERVERS))
                SendTask(this)
                registerEvents(AutoscaleListener(this), PrePartyListener(), JoinListenerBungee(this))
                server.scheduler.runTaskTimerAsynchronously(this, LoadedUsersCleaner(), 60L, 60L)
            } else {
                registerEvents(ServerPingListener(this), JoinListenerBungeeLegacy(this))
            }
        } else if (serverType == ServerType.MULTIARENA || serverType == ServerType.SHARED) {
            registerEvents(
                ArenaSelectorListener(this),
                BlockStatusListener(),
                if (serverType == ServerType.MULTIARENA) JoinListenerMultiArena(this)
                else JoinListenerShared(this)
            )
        }

        registerEvents(WorldLoadListener())

        if (serverType != ServerType.BUNGEE || !autoscale) {
            registerEvents(JoinHandlerCommon())
        }

        // Register setup-holograms fix
        registerEvents(ChunkLoad(this))

        registerEvents(InvisibilityPotionListener(this))

        /* Load join signs. */
        arenaManager.load()

        /* Party support */
        if (Companion.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_ALLOW_PARTIES)) {
            run(delay = 10) {
                val (adapter, log) = when {
                    server.pluginManager.isPluginEnabled("Parties") -> PartiesAdapter() to "Parties (by AlessioDP)"
                    server.pluginManager.isPluginEnabled("PartyAndFriends") -> PAF() to "Party and Friends for Spigot (by Simonsator)"
                    server.pluginManager.isPluginEnabled("Spigot-Party-API-PAF") -> PAFBungeecordRedisApi() to "Spigot Party API for Party and Friends Extended (by Simonsator)"
                    else -> Internal() to null
                }
                party = adapter
                logger.info(log?.let { "Hook into $it support!" } ?: "Loading internal Party system. /party")
            }
        }

        /* Levels support */
        levelSupport = InternalLevel()

        /* Register tasks */
        server.scheduler.runTaskTimer(this, Refresh(), 20L, 20L)

        //new Refresh().runTaskTimer(this, 20L, 20L);
        if (Companion.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_PERFORMANCE_ROTATE_GEN)) {
            //new OneTick().runTaskTimer(this, 120, 1);
            server.scheduler.runTaskTimer(this, OneTick(), 120, 1)
        }

        /* Database support */
        if (Companion.config.getBoolean("database.enable")) {
            val mySQL = MySQL()
            val time = System.currentTimeMillis()
            remoteDatabase = if (!mySQL.connect()) {
                logger.severe("Could not connect to database! Please verify your credentials and make sure that the server IP is whitelisted in MySQL.")
                SQLite()
            } else mySQL
            if (System.currentTimeMillis() - time >= 5000) {
                logger.severe(
                    "It took ${(System.currentTimeMillis() - time) / 1000} ms to establish a database connection!\n" +
                        "Using this remote connection is not recommended!"
                )
            }
        } else remoteDatabase = SQLite()
        remoteDatabase.init()

        /* Citizens support */
        run(delay = 5) {
            if (server.pluginManager.getPlugin("Citizens") == null) return@run
            logger.info("Hook into Citizens support. /bw npc")
            registerEvents(CitizensListener())
            //spawn NPCs
            try {
                JoinNPC.init()
                JoinNPC.isCitizensSupport = true
            } catch (_: Exception) {
                logger.severe("Could not spawn CmdJoin NPCs. Make sure you have right version of Citizens for your server!")
            }
        }

        /* Save messages for stats gui items if custom items added, for each language */
        Language.setupCustomStatsMessages()

        /* PlaceholderAPI Support */
        if (server.pluginManager.getPlugin("PlaceholderAPI") != null) {
            logger.info("Hooked into PlaceholderAPI support!")
            PAPISupport(this).register()
            SupportPAPI.support = SupportPAPI.PAPI()
        }
        /*
         * Vault support
         * The task is to initialize after all plugins have loaded,
         *  to make sure any economy/chat plugins have been loaded and registered.
         */
        run {
            if (server.pluginManager.getPlugin("Vault") == null) return@run
            try {
                val rsp = server.servicesManager.getRegistration(Chat::class.java)
                logger.info(if (rsp != null) {
                    WithChat.chat = rsp.provider
                    chatSupport = WithChat()
                    "Hooked into vault chat support!"
                } else "Vault found, but no chat provider!")
            } catch (_: Exception) {}
            try {
                registerEvents(MoneyListeners(this))
                val rsp = server.servicesManager.getRegistration(Economy::class.java)
                logger.info(if (rsp != null) {
                    WithEconomy.economy = rsp.provider
                    economy = WithEconomy()
                    "Hooked into vault economy support!"
                } else "Vault found, but no economy provider!")
            } catch (_: Exception) {}
        }

        /* Chat support */
        if (Companion.config.getBoolean(ConfigPath.GENERAL_CHAT_FORMATTING)) {
            registerEvents(ChatFormatting(this))
        }

        /* Protect glass walls from tnt explosion */
        nms.registerTntWhitelist(
            Companion.config.getDouble(ConfigPath.GENERAL_TNT_PROTECTION_END_STONE_BLAST).toFloat(),
            Companion.config.getDouble(ConfigPath.GENERAL_TNT_PROTECTION_GLASS_BLAST).toFloat()
        )

        /* Prevent issues on reload */
        server.onlinePlayers.forEach { it.kickPlayer("BedWars1058 was RELOADED! (do not reload plugins)") }

        /* Load sounds configuration */
        Sounds.init()

        /* Initialize shop */
        shop = ShopManager(this)

        //Leave this code at the end of the enable method
        for (l in Language.languages) {
            l.setupUnSetCategories()
            Language.addDefaultMessagesCommandItems(l)
        }

        levelsConfig = LevelsConfig(this)

        /* Load Money Configuration */
        moneyConfig = MoneyConfig(this)

        if (server.pluginManager.getPlugin("VipFeatures") != null) {
            try {
                val vf = server.servicesManager.getRegistration(IVipFeatures::class.java)!!.provider
                vf.registerMiniGame(VipFeatures(this))
                registerEvents(VipListeners(vf))
                logger.info("Hook into VipFeatures support.")
            } catch (_: Exception) {
                logger.warning("Could not load support for VipFeatures.")
            } catch (miniGameAlreadyRegistered: MiniGameAlreadyRegistered) {
                miniGameAlreadyRegistered.printStackTrace()
            }
        }

        run(delay = 100) { logger.info("This server is running in $serverType with auto-scale $autoscale") }

        // Initialize team upgrades
        upgradesManager = UpgradesManagerImpl(this)

        // Initialize sidebar manager
        scoreboardManager = ScoreboardManagerImpl(this)
        if (api.scoreboardManager.sidebarHandler != null) {
            logger.info("Initializing SidebarLib by andrei1058")
        } else {
            logger.severe("SidebarLib by andrei1058 does not support your server version")
            server.pluginManager.disablePlugin(this)
            return
        }

        // Halloween Special
        HalloweenSpecial.init(this)

        // TNT Spoil Feature
        SpoilPlayerTNTFeature.init(this)

        // Warn user if current server version support is deprecated
        performDeprecationCheck()
    }

    /**
     * Try loading custom adapter support.
     * 
     * @return true when custom adapter was registered.
     */
    private fun handleWorldAdapter(): Boolean {
        val description = server.pluginManager.getPlugin("SlimeWorldManager")?.description ?: return false
        val versionString = description.version.split(".")

        try {
            val major = versionString[0].toInt()
            val minor = versionString[1].toInt()
            val release = if (versionString.size > 2) versionString[2].toInt() else 0

            val adapterPath = when {
                major == 2 && minor == 2 && release == 1 -> "SlimeAdapter"
                major == 2 && minor == 8 && release == 0 -> "AdvancedSlimeAdapter"
                major > 2 || major == 2 && minor >= 10 -> "SlimePaperAdapter"
                else -> return false
            }

            val constructor = Class
                .forName("com.andrei1058.bedwars.arena.mapreset.slime.$adapterPath")
                .getConstructor(Plugin::class.java)
            logger.info("Loading restore adapter: $adapterPath ...")

            val candidate = constructor.newInstance(this) as RestoreAdapter<*>
            api.restoreAdapter = candidate
            logger.info("Hook into ${candidate.displayName} as restore adapter.")
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            logger.info("Something went wrong! Using internal reset adapter...")
        }
        return false
    }

    private fun registerCommands() {
        nms.commandMap.run {
            register("shout", ShoutCommand("shout"))
            register("rejoin", RejoinCommand("rejoin"))

            if (serverType == ServerType.BUNGEE) return
            register("leave", LeaveCommand())

            if (!config.getBoolean(ConfigPath.GENERAL_ENABLE_PARTY_CMD)) return
            logger.info("Registering /party command..")
            register("party", PartyCommand("party"))
        }
    }

    override fun onDisable() {
        api.isShuttingDown = true
        if (!enabled) return
        if (serverType == ServerType.BUNGEE) {
            ArenaSocket.disable()
        }
        for (a in arenaManager.arenas.values) {
            try {
                a.disable()
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
        }
    }

    fun performDeprecationCheck() {
        if (nms.javaClass.annotations.none { it is Deprecated }) return
        logger.warning(
            "Support for $serverVersion is scheduled for removal. " +
                    "Please consider upgrading your server software to a newer Minecraft version."
        )
    }

    override fun getDefaultWorldGenerator(worldName: String, id: String?) = VoidChunkGenerator()

    fun registerEvents(vararg listeners: Listener) {
        listeners.forEach { server.pluginManager.registerEvents(it, this) }
    }

    fun run(async: Boolean = false, delay: Long = 0, run: Runnable) {
        if (api.isShuttingDown) return
        if (async) {
            if (delay == 0L) server.scheduler.runTaskAsynchronously(this, run)
            else server.scheduler.runTaskLaterAsynchronously(this, run, delay)
            return
        }
        if (delay == 0L) server.scheduler.runTask(this, run)
        else server.scheduler.runTaskLater(this, run, delay)
    }

    fun repeat(delay: Long, period: Long = 0, async: Boolean = false, run: (BukkitTask) -> Unit) {
        if (!async) server.scheduler.runTaskTimer(this, run, delay, period)
        else server.scheduler.runTaskTimerAsynchronously(this, run, delay, period)
    }

    /**
     * Remove player from world.
     * Contains fall-backs.
     */
    internal fun sendToMainLobby(player: Player, arena: IArena): Boolean {
        val location = when (serverType) {
            ServerType.SHARED -> {
                api.scoreboardManager.remove(player)
                arenaManager.playerLocation[player]
            }
            ServerType.MULTIARENA -> Companion.config.getConfigLoc("lobbyLoc")
            else -> {
                Misc.moveToLobbyOrKick(player, arena)
                return true
            }
        }

        player.teleportSafe(if (location == null) {
            logger.severe("${player.name} was teleported to the main world because lobby location is not set!")
            server.worlds[0].spawnLocation
        } else location)
        return false
    }

    companion object {
        var enabled = true

        @JvmStatic var serverType = ServerType.MULTIARENA
            set(value) {
                field = value
                if (value == ServerType.BUNGEE) autoscale = true
            }
        var debug = true
        @JvmStatic var autoscale = false
        const val MAIN_COMMAND = "bw"
        var link = "https://www.spigotmc.org/resources/50942/"

        @JvmStatic var signs: ConfigManager? = null
        @JvmStatic lateinit var generatorsCfg: ConfigManager
        @JvmStatic lateinit var config: MainConfig
        @JvmStatic lateinit var shop: ShopManager
        @JvmStatic val statsManager get() = api.statsManager
        @JvmStatic lateinit var plugin: BedWars
        @JvmStatic lateinit var nms: VersionSupport

        var isPaper = false

        @JvmStatic var party: Party = NoParty()
        @JvmStatic var chatSupport: com.andrei1058.bedwars.support.vault.Chat = NoChat()
            private set
        @JvmStatic var economy: com.andrei1058.bedwars.support.vault.Economy = NoEconomy()
            private set

        /**
         * Get/Set the level manager.
         * You can use this to add your own levels manager just implement
         * the Level interface so the plugin will be able to display
         * the level internally.
         */
        var levelSupport: Level = InternalLevel()
            set(value) {
                if (value is InternalLevel) {
                    plugin.server.pluginManager.registerEvents(LevelListeners(plugin).also { plugin.levelListeners = it }, plugin)
                } else {
                    plugin.levelListeners?.let { HandlerList.unregisterAll(it) }
                    plugin.levelListeners = null
                }
                field = value
            }

        /**
         * Get the server version
         * Ex: v1_8_R3
         *
         * @since v0.6.5beta
         */
        val serverVersion = Bukkit.getServer().javaClass.name.split(".")[3]
        @JvmStatic var lobbyWorld = ""

        //remote database
        @JvmStatic lateinit var remoteDatabase: Database

        @JvmStatic
        @get:JvmName("getAPI")
        lateinit var api: API

        @JvmStatic
        fun debug(message: String) {
            if (debug) plugin.logger.info("DEBUG: $message")
        }

        @JvmStatic
        fun getForCurrentVersion(v13: String, v8: String, v12: String = v8) = when (serverVersion) {
            "v1_8_R3" -> v8
            "v1_12_R1" -> v12
            else -> v13
        }

    }
}
