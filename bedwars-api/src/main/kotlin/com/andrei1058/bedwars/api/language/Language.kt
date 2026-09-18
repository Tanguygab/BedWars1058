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
package com.andrei1058.bedwars.api.language

import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigManager
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.player.PlayerLangChangeEvent
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.util.UUID

open class Language(
    private val plugin: Plugin,
    /**
     * Get language iso code.
     */
    val iso: String,
    defaultName: String
) : ConfigManager(plugin, "messages_$iso", "${plugin.dataFolder.path}/Languages") {
    private var prefix = ""
    private var serverIp: String? = null

    init {
        // replace old placeholders
        val oldMsg = getStringList(Messages.GAME_END_TOP_PLAYER_CHAT)
        if (oldMsg.isNotEmpty()) {
            val oldPlaceholders = mapOf(
                "{topPlayerName}" to arrayOf("{firstName}", "{secondName}", "{thirdName}"),
                "{topValue}" to arrayOf("{firstKills}", "{secondKills}", "{thirdKills}")
            )

            val newMsg = oldMsg.map { msg ->
                var msg = msg
                oldPlaceholders.forEach { (new, olds) -> olds.forEach { old -> msg = msg.replace(old, new) } }
            }

            set(Messages.GAME_END_TOP_PLAYER_CHAT, newMsg)

            options().copyDefaults(true)
            addDefault(Messages.PREFIX, "")
            addDefault("name", defaultName)

            // this must stay here
            // move message to new path
            relocate("player-die-knocked-regular", Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL)
            relocate("player-die-knocked-final", Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL)
        }

        if (contains("scoreboard")) {
            for (group in getConfigurationSection("scoreboard")!!.getKeys(false)) {
                if (group.equals("lobby", ignoreCase = true)) {
                    relocate("scoreboard.$group", "sidebar.$group")
                } else {
                    mapOf(
                        "waiting" to arrayOf(Messages.SCOREBOARD_DEFAULT_WAITING, Messages.SCOREBOARD_DEFAULT_WAITING_SPEC),
                        "starting" to arrayOf(Messages.SCOREBOARD_DEFAULT_STARTING, Messages.SCOREBOARD_DEFAULT_STARTING_SPEC)
                    ).forEach { (stage, messages) ->
                        relocate("scoreboard.$group.$stage.player", messages[0].replace("Default", group))
                        relocate("scoreboard.$group.$stage", messages[0].replace("Default", group))
                        relocate("scoreboard.$group.$stage.spectator", messages[1].replace("Default", group))
                    }
                    if (contains("scoreboard.$group.playing.alive")) {
                        relocate(
                            "scoreboard.$group.playing.alive",
                            Messages.SCOREBOARD_DEFAULT_PLAYING.replace("Default", group)
                        )
                        relocate(
                            "scoreboard.$group.playing.spectator",
                            Messages.SCOREBOARD_DEFAULT_PLAYING.replace("Default", group)
                        )
                    } else {
                        relocate(
                            "scoreboard.$group.playing",
                            Messages.SCOREBOARD_DEFAULT_PLAYING.replace("Default", group)
                        )
                    }
                }
            }
            set("scoreboard", null)
        }

        languages += this
    }

    /**
     * Set chat prefix.
     */
    fun setPrefix(prefix: String) {
        this.prefix = prefix
    }

    val langName = getString("name")!!

    /**
     * Check if a message was set.
     */
    override fun contains(path: String) = contains(path, true)

    /**
     * Get a color translated message.
     */
    fun m(path: String): String {
        var message = getString(path)
        if (message == null) {
            System.err.println("Missing message key $path in language $iso")
            message = "MISSING_LANG"
        }
        if (serverIp == null) {
            val api = plugin.server.servicesManager.getRegistration(BedWars::class.java)!!.provider
            serverIp = api.configs.main.getString(ConfigPath.GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_SERVER_IP)
        }
        return ChatColor.translateAlternateColorCodes('&', message
            .replace("{prefix}", prefix)
            .replace("{serverIp}", serverIp ?: "")
        )
    }

    /**
     * Get a color translated list.
     */
    fun l(path: String) = getStringList(path).map { ChatColor.translateAlternateColorCodes('&', it) }

    fun addDefaultStatsMsg(path: String, name: String, vararg lore: String) {
        addDefaultMessages(
            this,
            mapOf(
                "name" to name,
                "lore" to lore
            ).mapKeys { "${Messages.PLAYER_STATS_GUI_PATH}-$path-$it" }
        )
    }

    /**
     * Create messages paths for new shop categories
     */
    fun setupUnSetCategories() {
        val api = plugin.server.servicesManager.getRegistration(BedWars::class.java)!!.provider

        for (category in api.configs.shop.getConfigurationSection("")!!.getKeys(false)) {
            if (category.equals(ConfigPath.SHOP_SETTINGS_PATH, ignoreCase = true)) continue
            if (category.equals(ConfigPath.SHOP_SPECIALS_PATH, ignoreCase = true)) continue
            if (category == ConfigPath.SHOP_QUICK_DEFAULTS_PATH) continue

            mapOf(
                Messages.SHOP_CATEGORY_INVENTORY_NAME to "&8Name not set",
                Messages.SHOP_CATEGORY_ITEM_NAME to "&8Name not set",
                Messages.SHOP_CATEGORY_ITEM_LORE to listOf("&8Lore not set")
            ).mapKeys { it.key.replace("%category%", category) }
                .filter { it.key !in this }
                .forEach { set(it.key, it.value) }

            val contents = api.configs.shop.getConfigurationSection(category + ConfigPath.SHOP_CATEGORY_CONTENT_PATH) ?: continue
            for (content in contents.getKeys(false)) {
                mapOf(
                    Messages.SHOP_CONTENT_TIER_ITEM_NAME to "&8Name not set",
                    Messages.SHOP_CONTENT_TIER_ITEM_LORE to listOf("&8Lore not set")
                ).mapKeys { it.key
                    .replace("%category%", category)
                    .replace("%content%", content)
                }.filter { it.key !in this }
                    .forEach { set(it.key, it.value) }
            }
        }
    }

    companion object {
        /**
         * Get loaded languages list.
         */
        val languages = mutableListOf<Language>()

        /**
         * Get server default language.
         */
        lateinit var defaultLanguage: Language

        val langByPlayer = mutableMapOf<UUID, Language>()

        /**
         * Get scoreboard strings.
         */
        fun getScoreboard(player: Player, path: String, alternative: String): List<String> {
            val language = getLanguage(player)
            if (path in language) return language.l(path)

            val sp = path.split(".")
            if (sp.size == 3) {
                var path2 = sp[1]
                path2 = path2[0].toString().uppercase() + path2.substring(1).lowercase()
                path2 = "${sp[0]}.$path2.${sp[2]}"
                if (path2 in language) return language.l(path)

                val path3 = "${sp[0]}.${sp[1].uppercase()}.${sp[2]}"
                if (path3 in language) return language.l(path3)
            }
            return language.l(alternative)
        }

        /**
         * Get message in player's language.
         */
        fun getMsg(player: Player?, path: String): String {
            val lang = player?.let { langByPlayer[it.uniqueId] } ?: defaultLanguage
            return lang.m(path).replace("{prefix}", lang.prefix)
        }

        /**
         * Retrieve a player language.
         */
        fun getLanguage(player: Player) = getLanguage(player.uniqueId)

        fun getLanguage(uuid: UUID) = langByPlayer[uuid] ?: defaultLanguage

        /**
         * Get a string list in player's language.
         */
        fun getList(player: Player, path: String) = getLanguage(player).l(path)

        /**
         * Save a value to file if not exists.
         */
        fun saveIfNotExists(path: String, data: Any) {
            for (l in languages) {
                if (l.get(path) == null) {
                    l.set(path, data)
                }
            }
        }

        /**
         * Check if a language exists.
         */
        fun isLanguageExist(iso: String) = languages.any { it.iso.equals(iso, ignoreCase = true) }

        /**
         * Get language with given info.
         * 
         * @return null if you could not find.
         */
        fun getLanguageByIso(iso: String) = languages.find { it.iso.equals(iso, ignoreCase = true) } ?: defaultLanguage

        private fun addDefaultMessages(
            yml: YamlConfiguration,
            map: Map<String, Any>,
            placeholders: Map<String, String> = emptyMap()
        ) {
            map.mapKeys {
                val path = it.key
                placeholders.forEach { (placeholder, value) -> path.replace("%$placeholder%", value) }
                path
            }.filter { yml.defaults == null || it.key !in yml.defaults!! }
                .forEach { yml.addDefault(it.key, it.value) }
        }

        /**
         * Save messages for unset stats items.
         */
        fun setupCustomStatsMessages() {
            val api = Bukkit.getServer().servicesManager.getRegistration(BedWars::class.java)!!.provider
            for (l in languages) {
                /* save messages for stats gui items if custom items added */
                val stats = api.configs.main.getConfigurationSection(ConfigPath.GENERAL_CONFIGURATION_STATS_PATH) ?: continue
                for (item in stats.getKeys(false)) {
                    if (item in ConfigPath.GENERAL_CONFIGURATION_STATS_GUI_SIZE) continue

                    addDefaultMessages(
                        l,
                        mapOf(
                            "name" to "Name not set",
                            "lore" to listOf("Lore not set")
                        ).mapKeys { "${Messages.PLAYER_STATS_GUI_PATH}-$item-$it" }
                    )
                }
                l.save()
            }
        }

        /**
         * Create missing name/ lore for items: multi arena lobby, waiting, spectating
         */
        fun addDefaultMessagesCommandItems(language: Language?) {
            if (language == null) return
            val api = Bukkit.getServer().servicesManager.getRegistration(BedWars::class.java)!!.provider

            listOf(
                ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_PATH,
                ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_PATH,
                ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_PATH
            ).associateWith { api.configs.main.getConfigurationSection(it) }
                .filterValues { it != null }
                .forEach { (path, section) ->
                    section!!.getKeys(false).forEach { item ->
                        mapOf(
                            "name" to "&cName not set at: &f$path-$item-name",
                            "lore" to listOf("&cLore not set at:", "&f$path-$item-lore")
                        ).filter { language.defaults == null || it.key !in language.defaults!! }
                            .forEach { language.addDefault(it.key, it.value) }
                    }
                }
            language.options().copyDefaults(true)
            language.save()
        }

        /**
         * Add required messages for a shop category to the given yml
         */
        fun addCategoryMessages(
            yml: YamlConfiguration,
            categoryName: String,
            invName: String,
            itemName: String,
            itemLore: List<String>
        ) {
            addDefaultMessages(
                yml,
                mapOf(
                    Messages.SHOP_CATEGORY_INVENTORY_NAME to invName,
                    Messages.SHOP_CATEGORY_ITEM_NAME to itemName,
                    Messages.SHOP_CATEGORY_ITEM_LORE to itemLore
                ),
                mapOf("category" to categoryName),
            )
        }

        /**
         * Add required messages for a shop category to the given yml
         */
        fun addContentMessages(
            yml: YamlConfiguration,
            contentName: String,
            categoryName: String,
            itemName: String,
            itemLore: List<String>
        ) {
            addDefaultMessages(
                yml,
                mapOf(
                    Messages.SHOP_CONTENT_TIER_ITEM_NAME to itemName,
                    Messages.SHOP_CONTENT_TIER_ITEM_LORE to itemLore
                ),
                mapOf(
                    "category" to categoryName,
                    "content" to contentName,
                ),
            )
        }

        /**
         * Change a player language and refresh
         * scoreboard and custom join items.
         */
        fun setPlayerLanguage(uuid: UUID, iso: String): Boolean {
            val newLang = getLanguageByIso(iso)
            val oldLang = getLanguage(uuid)
            if (oldLang.iso == newLang.iso) return false

            val player = Bukkit.getPlayer(uuid)
            if (player != null && player.isOnline) {
                val e = PlayerLangChangeEvent(player, oldLang.iso, newLang.iso)
                Bukkit.getPluginManager().callEvent(e)
                if (e.isCancelled) return false
            }

            if (defaultLanguage.iso == newLang.iso) {
                langByPlayer.remove(uuid)
                return true
            }

            langByPlayer[uuid] = newLang
            return true
        }

        fun getCountDownTitle(language: Language, second: Int) = arrayOf(
            Messages.ARENA_STATUS_START_COUNTDOWN_TITLE,
            Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE
        ).map { language
            .get("$it-$second", language.getString(it))
            .toString()
            .replace("{second}", "$second")
            .ifEmpty { " " }
        }


        fun CommandSender.sendLangMsg(path: String, vararg placeholders: Pair<String, Any>) {
            var message = getMsg(this as? Player, path)
            placeholders.forEach { (placeholder, value) ->
                message = message.replace(placeholder, "$value")
            }
            sendMessage(message)
        }
        fun Collection<Player>.sendLangMsg(path: String, vararg placeholders: Pair<String, Any>) {
            forEach { it.sendLangMsg(path, *placeholders) }
        }
    }
}
