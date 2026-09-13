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
package com.andrei1058.bedwars.configuration

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.getForCurrentVersion
import com.andrei1058.bedwars.api.arena.stats.DefaultStatistics
import com.andrei1058.bedwars.api.configuration.ConfigManager
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.server.ServerType
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.plugin.Plugin
import java.io.File
import java.io.IOException

class MainConfig(plugin: Plugin) : ConfigManager(plugin, "config", plugin.dataFolder.path) {
    init {
        options()
            .copyDefaults(true)
            .setHeader(listOf(
                "${plugin.description.name} by andrei1058. https://www.spigotmc.org/members/39904/",
                "Documentation here: https://gitlab.com/andrei1058/BedWars1058/wikis/home",
                ""
            ))

        default(
            "serverType" to "MULTIARENA",
            "language" to "en",
            ConfigPath.GENERAL_CONFIGURATION_DISABLED_LANGUAGES to listOf("your language iso here"),
            "storeLink" to "https://www.spigotmc.org/resources/authors/39904/",
            "lobbyServer" to "hub",
            ConfigPath.GENERAL_CONFIGURATION_ENABLE_HALLOWEEN to true,
            ConfigPath.GENERAL_CHAT_GLOBAL to (get("globalChat") ?: false),
            ConfigPath.GENERAL_CHAT_FORMATTING to (get("formatChat") ?: true),
            "debug" to false,
            ConfigPath.GENERAL_CONFIGURATION_MARK_LEAVE_AS_ABANDON to false,

            // parties category
            ConfigPath.GENERAL_ENABLE_PARTY_CMD to true,
            ConfigPath.GENERAL_CONFIGURATION_ALLOW_PARTIES to true,
            ConfigPath.GENERAL_ALESSIODP_PARTIES_RANK to 10,

            ConfigPath.SB_CONFIG_SIDEBAR_USE_LOBBY_SIDEBAR to true,
            ConfigPath.SB_CONFIG_SIDEBAR_USE_GAME_SIDEBAR to true,
            ConfigPath.SB_CONFIG_SIDEBAR_TITLE_REFRESH_INTERVAL to 4,
            ConfigPath.SB_CONFIG_SIDEBAR_PLACEHOLDERS_REFRESH_INTERVAL to 20,
            ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_LOBBY to false,
            ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_WAITING to false,
            ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_STARTING to false,
            ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_PLAYING to true,
            ConfigPath.SB_CONFIG_SIDEBAR_LIST_FORMAT_RESTARTING to true,
            ConfigPath.SB_CONFIG_SIDEBAR_LIST_REFRESH to 1200,
            ConfigPath.SB_CONFIG_SIDEBAR_HEALTH_ENABLE to true,
            ConfigPath.SB_CONFIG_SIDEBAR_HEALTH_IN_TAB to true,
            ConfigPath.SB_CONFIG_SIDEBAR_HEALTH_REFRESH to 300,
            ConfigPath.SB_CONFIG_TAB_HEADER_FOOTER_ENABLE to true,
            ConfigPath.SB_CONFIG_TAB_HEADER_FOOTER_REFRESH_INTERVAL to 10,

            ConfigPath.GENERAL_CONFIGURATION_REJOIN_TIME to 60 * 5,
            ConfigPath.GENERAL_CONFIGURATION_RE_SPAWN_INVULNERABILITY to 4000,
            ConfigPath.GENERAL_CONFIGURATION_BUNGEE_MODE_GAMES_BEFORE_RESTART to 30,
            ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_RESTART_CMD to "restart",
            ConfigPath.GENERAL_CONFIGURATION_AUTO_SCALE_LIMIT to 5,
            ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_LOBBY_SERVERS to listOf("0.0.0.0:2019"),
            ConfigPath.GENERAL_CONFIGURATION_START_COUNTDOWN_REGULAR to 40,
            ConfigPath.GENERAL_CONFIGURATION_START_COUNTDOWN_HALF to 25,
            ConfigPath.GENERAL_CONFIGURATION_START_COUNTDOWN_SHORTENED to 5,
            ConfigPath.GENERAL_CONFIGURATION_RESTART to 45,
            ConfigPath.GENERAL_CONFIGURATION_RE_SPAWN_COUNTDOWN to 5,
            ConfigPath.GENERAL_CONFIGURATION_BEDS_DESTROY_COUNTDOWN to 360,
            ConfigPath.GENERAL_CONFIGURATION_DRAGON_SPAWN_COUNTDOWN to 600,
            ConfigPath.GENERAL_CONFIGURATION_GAME_END_COUNTDOWN to 120,
            ConfigPath.GENERAL_CONFIGURATION_SHOUT_COOLDOWN to 30,
            ConfigPath.GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_SERVER_IP to "yourServer.Com",
            ConfigPath.GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_POWERED_BY to "BedWars1058",
            ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_SERVER_ID to "bw1",
            ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_BWP_TIME_OUT to 5000,

            ConfigPath.GENERAL_CONFIGURATION_HUNGER_WAITING to false,
            ConfigPath.GENERAL_CONFIGURATION_HUNGER_INGAME to false,

            ConfigPath.GENERAL_CONFIGURATION_ALLOW_FIRE_EXTINGUISH to true,

            //heal pool category
            ConfigPath.GENERAL_CONFIGURATION_HEAL_POOL_ENABLE to true,
            ConfigPath.GENERAL_CONFIGURATION_HEAL_POOL_SEEN_TEAM_ONLY to true,

            // tnt jump category
            ConfigPath.GENERAL_TNT_JUMP_BARYCENTER_IN_Y to 0.5,
            ConfigPath.GENERAL_TNT_JUMP_STRENGTH_REDUCTION to 5,
            ConfigPath.GENERAL_TNT_JUMP_Y_REDUCTION to 2,
            ConfigPath.GENERAL_TNT_JUMP_DAMAGE_SELF to 1,
            ConfigPath.GENERAL_TNT_JUMP_DAMAGE_TEAMMATES to 5,
            ConfigPath.GENERAL_TNT_JUMP_DAMAGE_OTHERS to 10,

            // tnd block blast resistance
            // on 1.8.8 it has to be around 69, on 1.20 and 1.18 it works fine with 12 (tested)
            ConfigPath.GENERAL_TNT_PROTECTION_END_STONE_BLAST to if (BedWars.nms.version == 0) 69f else 12f,
            ConfigPath.GENERAL_TNT_PROTECTION_GLASS_BLAST to 300f,
            ConfigPath.GENERAL_TNT_RAY_BLOCKED_BY_GLASS to true,

            // tnt prime settings
            ConfigPath.GENERAL_TNT_AUTO_IGNITE to true,
            ConfigPath.GENERAL_TNT_FUSE_TICKS to 45,

            // fireball category
            ConfigPath.GENERAL_FIREBALL_EXPLOSION_SIZE to 3,
            ConfigPath.GENERAL_FIREBALL_SPEED_MULTIPLIER to 10,
            ConfigPath.GENERAL_FIREBALL_MAKE_FIRE to false,
            ConfigPath.GENERAL_FIREBALL_KNOCKBACK_HORIZONTAL to 1.0,
            ConfigPath.GENERAL_FIREBALL_KNOCKBACK_VERTICAL to 0.65,
            ConfigPath.GENERAL_FIREBALL_COOLDOWN to 0.5,
            ConfigPath.GENERAL_FIREBALL_DAMAGE_SELF to 2.0,
            ConfigPath.GENERAL_FIREBALL_DAMAGE_ENEMY to 2.0,
            ConfigPath.GENERAL_FIREBALL_DAMAGE_TEAMMATES to 0.0,

            "database.enable" to false,
            "database.host" to "localhost",
            "database.port" to 3306,
            "database.database" to "bedwars1058",
            "database.user" to "root",
            "database.pass" to "cheese",
            "database.ssl" to false,

            ConfigPath.GENERAL_CONFIGURATION_PERFORMANCE_ROTATE_GEN to true,
            ConfigPath.GENERAL_CONFIGURATION_PERFORMANCE_SPOIL_TNT_PLAYERS to true,
            ConfigPath.GENERAL_CONFIGURATION_PERFORMANCE_PAPER_FEATURES to true,

            ConfigPath.GENERAL_CONFIGURATION_DISABLE_CRAFTING to true,
            ConfigPath.GENERAL_CONFIGURATION_DISABLE_ENCHANTING to true,
            ConfigPath.GENERAL_CONFIGURATION_DISABLE_FURNACE to true,
            ConfigPath.GENERAL_CONFIGURATION_DISABLE_BREWING_STAND to true,
            ConfigPath.GENERAL_CONFIGURATION_DISABLE_ANVIL to true
        )

        val playerHead = getForCurrentVersion("PLAYER_HEAD", "SKULL_ITEM")
        /* Multi-Arena Lobby Command Items */
        saveLobbyCommandItem("stats", false, playerHead, 3, 0)
        saveLobbyCommandItem("arena-selector", true, "CHEST", 5, 4, "bw gui")
        saveLobbyCommandItem("leave", false, getForCurrentVersion("RED_BED", "BED"), 0, 8)

        /* Pre Game Command Items */
        savePreGameCommandItem("stats", false, playerHead, 3, 0)
        savePreGameCommandItem("leave", false, getForCurrentVersion("RED_BED", "BED"), 0, 8)

        /* Spectator Command Items */
        saveSpectatorCommandItem("teleporter", false, playerHead, 3, 0)
        saveSpectatorCommandItem("leave", false, getForCurrentVersion("RED_BED", "BED"), 0, 8)

        default(
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_SIZE to 27,
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_SHOW_PLAYING to true,
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_USE_SLOTS to "10,11,12,13,14,15,16",
        )
        default(
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_MATERIAL to
                    getForCurrentVersion("LIME_CONCRETE", "STAINED_GLASS_PANE", "CONCRETE"),
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_DATA to 5,
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_ENCHANTED to false,
            placeholders = mapOf("path" to "waiting")
        )
        default(
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_MATERIAL to
                getForCurrentVersion("YELLOW_CONCRETE", "STAINED_GLASS_PANE", "CONCRETE"),

            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_DATA to 4,
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_ENCHANTED to true,
            placeholders = mapOf("path" to "starting")
        )
        default(
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_MATERIAL to
                    getForCurrentVersion("RED_CONCRETE", "STAINED_GLASS_PANE", "CONCRETE"),
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_DATA to 14,
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_ENCHANTED to false,
            placeholders = mapOf("path" to "playing")
        )
        default(
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_MATERIAL to
                getForCurrentVersion("BLACK_STAINED_GLASS_PANE", "STAINED_GLASS_PANE"),
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_DATA to 15,
            ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_ENCHANTED to false,
            placeholders = mapOf("path" to "skipped-slot")
        )

        /* default stats GUI items */
        addDefault(ConfigPath.GENERAL_CONFIGURATION_STATS_GUI_SIZE, 27)
        if (isFirstTime) {
            addDefaultStatsItem("wins", 10, "DIAMOND")
            addDefaultStatsItem("losses", 11, "REDSTONE")
            addDefaultStatsItem("kills", 12, "IRON_SWORD")
            addDefaultStatsItem("deaths", 13, getForCurrentVersion("SKELETON_SKULL", "SKULL_ITEM"))
            addDefaultStatsItem("final-kills", 14, "DIAMOND_SWORD", 0)
            addDefaultStatsItem("final-deaths", 15, getForCurrentVersion("WITHER_SKELETON_SKULL", "SKULL_ITEM"), 1)
            addDefaultStatsItem("beds-destroyed", 16, getForCurrentVersion("RED_BED", "BED"))
            addDefaultStatsItem("first-play", 21, getForCurrentVersion("BLACK_STAINED_GLASS_PANE", "STAINED_GLASS_PANE"))
            addDefaultStatsItem("games-played", 22, "CHEST")
            addDefaultStatsItem("last-play", 23, getForCurrentVersion("BLACK_STAINED_GLASS_PANE", "STAINED_GLASS_PANE"))
        }

        default(
            ConfigPath.GENERAL_CONFIGURATION_DEFAULT_ITEMS + ".Default" to listOf(getForCurrentVersion("WOODEN_SWORD", "WOOD_SWORD")),
            ConfigPath.CENERAL_CONFIGURATION_ALLOWED_COMMANDS to listOf("shout", "bw", "leave"),
            ConfigPath.GENERAL_CONFIGURATION_EXPERIMENTAL_TEAM_ASSIGNER to true,
            ConfigPath.GENERAL_CONFIGURATION_ENABLE_GEN_SPLIT to true,

            ConfigPath.LOBBY_VOID_TELEPORT_ENABLED to true,
            ConfigPath.LOBBY_VOID_TELEPORT_HEIGHT to 0,
            ConfigPath.GENERAL_GAME_END_SHOW_ELIMINATED to true,
            ConfigPath.GENERAL_GAME_END_TELEPORT_ELIMINATED to true,
            ConfigPath.GENERAL_GAME_END_CHAT_TOP_STATISTIC to DefaultStatistics.KILLS.toString(),
            ConfigPath.GENERAL_GAME_END_CHAT_TOP_HIDE_MISSING to true,

            ConfigPath.GENERAL_GAME_END_SB_TOP_STATISTIC to DefaultStatistics.KILLS.toString(),
            ConfigPath.GENERAL_GAME_END_SB_TOP_HIDE_MISSING to true
        )
        save()

        convert()

        //set default server language
        val language = getString("language")
        val defIso = File(plugin.dataFolder, "/Languages")
            .listFiles { it.isFile && it.name.startsWith("messages_") && it.name.endsWith(".yml") }
            ?.map { it.name.removeSurrounding("messages_", ".yml") }
            ?.find { language.equals(it, ignoreCase = true) }
            ?: "en"

        val def = Language.getLang(defIso)

        BedWars.api.defaultLang = def

        //remove languages if disabled
        //server language can't be disabled
        for (iso in getStringList(ConfigPath.GENERAL_CONFIGURATION_DISABLED_LANGUAGES)) {
            val l = Language.getLang(iso)
            if (l !== def) Language.languages.remove(l)
        }


        BedWars.debug = getBoolean("debug")
        ConfigManager(plugin, "bukkit", Bukkit.getWorldContainer().path).set("ticks-per.autosave", -1)

        Bukkit.spigot().config.set("commands.send-namespaced", false)
        try {
            Bukkit.spigot().config.save("spigot.yml")
        } catch (e: IOException) {
            e.printStackTrace()
        }

        try {
            BedWars.serverType = ServerType.valueOf(getString("serverType")!!.uppercase())
        } catch (_: Exception) {
            if (getString("serverType").equals("BUNGEE_LEGACY", ignoreCase = true)) {
                BedWars.serverType = ServerType.BUNGEE
                BedWars.autoscale = false
            } else {
                set("serverType", "MULTIARENA")
            }
        }

        BedWars.lobbyWorld = lobbyWorldName
    }

    val lobbyWorldName: String get() {
        val data = getString("lobbyLoc")
            ?.removeSurrounding("[", "]")
            ?.split(",")
            ?: return ""
        return data[data.size - 1]
    }

    //remove old config
    private fun convert() {
        set("formatChat", null)
        set("globalChat", null)

        relocate("bungee-settings.lobby-servers", ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_LOBBY_SERVERS)

        if (get("arenaGui") != null) {
            relocate("arenaGui.settings.showPlaying", ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_SHOW_PLAYING)
            relocate("arenaGui.settings.size", ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_SIZE)
            relocate("arenaGui.settings.useSlots", ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_USE_SLOTS)

            val values = mapOf(
                "itemStack" to ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_MATERIAL,
                "data" to ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_DATA,
                "enchanted" to  ConfigPath.GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_ENCHANTED
            )

            for (path in getConfigurationSection("arenaGui")!!.getKeys(false)) {
                if (path.equals("settings", ignoreCase = true)) continue
                val newPath = if (path == "skippedSlot") "skipped-slot" else path

                values.forEach { (from, to) -> relocate("arenaGui.$path" + from.replace("%path%", newPath), to) }
            }
            set("arenaGui", null)
        }

        set("fireball.damage-multiplier", null)

        relocate("npcLoc", ConfigPath.GENERAL_CONFIGURATION_NPC_LOC_STORAGE)
        relocate("disableCrafting", ConfigPath.GENERAL_CONFIGURATION_DISABLE_CRAFTING)

        if (get("statsGUI") != null) {
            relocate("statsGUI.invSize", ConfigPath.GENERAL_CONFIGURATION_STATS_GUI_SIZE)

            val values = mapOf(
                "itemStack" to ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_MATERIAL,
                "data" to ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_DATA,
                "slot" to ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_SLOT
            )
            for (stats in getConfigurationSection("statsGUI")!!.getKeys(false)) {
                val newPath = when (stats) {
                    "gamesPlayed" -> "games-played"
                    "lastPlay" -> "last-play"
                    "firstPlay" -> "first-play"
                    "bedsDestroyed" -> "beds-destroyed"
                    "finalDeaths" -> "final-deaths"
                    "finalKills" -> "final-kills"
                    else -> stats
                }

                values.forEach { (from, to) -> relocate("arenaGui.$stats" + from.replace("%path%", newPath), to) }
            }
            set("statsGUI", null)
        }

        relocate("server-name", ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_SERVER_ID)
        relocate("lobby-scoreboard", ConfigPath.SB_CONFIG_SIDEBAR_USE_LOBBY_SIDEBAR)
        relocate("game-scoreboard", ConfigPath.SB_CONFIG_SIDEBAR_USE_GAME_SIDEBAR)
        relocate("enable-party-cmd", ConfigPath.GENERAL_ENABLE_PARTY_CMD)
        relocate("allow-parties", ConfigPath.GENERAL_CONFIGURATION_ALLOW_PARTIES)

        set("startItems", null)
        set("generators", null)
        set("bedsDestroyCountdown", null)
        set("dragonSpawnCountdown", null)
        set("gameEndCountdown", null)
        set("blockedCmds", null)
        set("lobbyScoreboard", null)
        set("items", null)
        set("start-items-per-arena", null)
        set("safeMode", null)
        set("performance-settings.disable-armor-packets", null)
        set("performance-settings.disable-respawn-packets", null)
    }

    /**
     * add default stats gui item
     */
    fun addDefaultStatsItem(path: String, slot: Int, material: String, data: Int = 0) {
        default(
            ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_MATERIAL to material,
            ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_DATA to data,
            ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_SLOT to slot,
            placeholders = mapOf("path" to path),
        )
    }

    private fun saveDefaults(path: String, vararg settings: Pair<String, Any>) {
        if (!isFirstTime) return
        settings.forEach { (key, value) -> addDefault(key.replace("%path%", path), value) }
        options.copyDefaults(true)
        save()
    }

    /**
     * Add Multi Arena Lobby Command Item To
     * This won't create the item back if you delete it.
     */
    fun saveLobbyCommandItem(
        name: String,
        enchanted: Boolean,
        material: String,
        data: Int,
        slot: Int,
        cmd: String = "bw $name"
    ) = saveDefaults(name,
        ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_COMMAND to cmd,
        ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_MATERIAL to material,
        ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_DATA to data,
        ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_ENCHANTED to enchanted,
        ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_SLOT to slot
    )

    /**
     * Add Pre Game Command Item To
     * This won't create the item back if you delete it.
     */
    fun savePreGameCommandItem(
        name: String,
        enchanted: Boolean,
        material: String,
        data: Int,
        slot: Int,
        cmd: String = "bw $name"
    ) = saveDefaults(name,
        ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_COMMAND to cmd,
        ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_MATERIAL to material,
        ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_DATA to data,
        ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_ENCHANTED to enchanted,
        ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_SLOT to slot
    )

    /**
     * Add Spectator Command Item To
     * This won't create the item back if you delete it.
     */
    fun saveSpectatorCommandItem(
        name: String,
        enchanted: Boolean,
        material: String,
        data: Int,
        slot: Int,
        cmd: String = "bw $name"
    ) = saveDefaults(name,
        ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_COMMAND to cmd,
        ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_MATERIAL to material,
        ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_DATA to data,
        ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_ENCHANTED to enchanted,
        ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_SLOT to slot
    )
}
