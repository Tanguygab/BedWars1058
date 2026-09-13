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
import com.andrei1058.bedwars.BedWars.Companion.statsManager
import com.andrei1058.bedwars.Utils
import com.andrei1058.bedwars.Utils.editMeta
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.configuration.Sounds
import com.andrei1058.bedwars.support.papi.SupportPAPI
import com.google.common.io.ByteStreams
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.*
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import java.io.File
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant

object Misc {
    fun moveToLobbyOrKick(player: Player, arena: IArena?, notAbandon: Boolean = true) {
        if (BedWars.serverType == ServerType.BUNGEE) {
            forceKick(player, arena, notAbandon)
            return
        }

        if (player.world.name.equals(BedWars.config.lobbyWorldName, ignoreCase = true)) {
            forceKick(player, arena, notAbandon)
            return
        }

        val loc = BedWars.config.getConfigLoc("lobbyLoc")
        if (loc == null) {
            // Can happen when location is not set in config
            forceKick(player, arena, notAbandon)
            return
        }

        try {
            player.teleport(loc)
        } catch (_: Exception) {
            Bukkit.getLogger().severe("Could not teleport player to lobby! Try setting the lobby again with /bw setLobby")
        }

        if (arena == null) return

        if (arena.isSpectator(player)) {
            arena.removeSpectator(player, false)
            return
        }

        arena.removePlayer(player, false)
        abandonGame(player, arena, notAbandon)
    }


    private fun forceKick(player: Player, arena: IArena?, notAbandon: Boolean) {
        player.sendPluginMessage(BedWars.plugin, "BungeeCord", ByteStreams.newDataOutput().run {
            writeUTF("Connect")
            writeUTF(BedWars.config.getString("lobbyServer")!!)
            toByteArray()
        })
        abandonGame(player, arena, notAbandon)

        if (BedWars.serverType != ServerType.BUNGEE) return
        BedWars.plugin.run(delay = 30) {
            // if lobby server is unreachable
            if (!player.isOnline) return@run
            player.kickPlayer(Language.getMsg(player, Messages.ARENA_RESTART_PLAYER_KICK))
            abandonGame(player, arena, notAbandon)
        }
    }

    private fun abandonGame(player: Player, arena: IArena?, notAbandon: Boolean) {
        if (arena == null || notAbandon || arena.status != GameState.PLAYING) return
        if (BedWars.config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_MARK_LEAVE_AS_ABANDON)) {
            arena.abandonGame(player)
        }
    }

    /**
     * Win fireworks
     */
    @Suppress("unused")
    fun launchFirework(p: Player) {
        launchFirework(p.eyeLocation).velocity = p.eyeLocation.getDirection()
    }

    private val FIREWORK_COLORS = arrayOf(
        Color.WHITE, Color.AQUA, Color.BLUE, Color.FUCHSIA, Color.GRAY, Color.GREEN, Color.LIME, Color.RED,
        Color.YELLOW, Color.BLACK, Color.MAROON, Color.NAVY, Color.OLIVE, Color.ORANGE, Color.PURPLE
    )
    fun launchFirework(l: Location): Firework {
        val firework = l.world!!.spawn(l, Firework::class.java)
        val meta = firework.fireworkMeta
        meta.power = 1
        meta.addEffect(FireworkEffect
            .builder()
            .withFade(FIREWORK_COLORS.random())
            .withTrail()
            .withColor(FIREWORK_COLORS.random())
            .with(FireworkEffect.Type.BALL_LARGE)
            .build()
        )
        firework.fireworkMeta = meta
        return firework
    }

    /*public static void checkLobbyServer() {
        if (spigot.getBoolean("settings.bungeecord")) {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("GetServers");
            plugin.getServer().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
            debug("Requesting bungee servers.");
        } else {
            if (getServerType() == ServerType.BUNGEE) {
                plugin.getLogger().severe("Please set bungeecord to true in spigot.yml");
            }
        }
    }*/
    /**
     * Create an item stack
     * 
     * @param material item material
     * @param data     item data
     * @param name     item name
     * @param lore     item lore
     * @param owner    in case of skull, can be null, don't worry
     */
    fun createItem(
        material: Material,
        data: Byte,
        enchanted: Boolean,
        name: String,
        lore: List<String>,
        owner: Player?,
        metaKey: String,
        metaData: String
    ): ItemStack {
        var item = ItemStack(material, 1, data.toShort())
        item.editMeta {
            setDisplayName(name)
            this.lore = lore
            if (enchanted) {
                addEnchant(Enchantment.LUCK, 1, true)
                addItemFlags(ItemFlag.HIDE_ENCHANTS)
            }
        }
        if (metaData.isNotEmpty() && metaKey.isNotEmpty()) {
            item = BedWars.nms.addCustomData(item, metaKey + "_" + metaData)
        }
        if (owner != null && BedWars.nms.isPlayerHead(material, data.toInt())) {
            item = BedWars.nms.getPlayerHead(owner, item)
        }
        return item
    }

    fun isProjectile(material: Material) = material in arrayOf(Material.EGG, BedWars.nms.materialFireball(), BedWars.nms.materialSnowball(), Material.ARROW)

    /**
     * create TextComponent message
     */
    fun msgHoverClick(msg: String, hover: String, click: String, clickAction: ClickEvent.Action = ClickEvent.Action.RUN_COMMAND)
    = Utils.msgHoverClick(msg, hover, click, clickAction)

    /**
     * open stats GUI to player
     */
    fun openStatsGUI(p: Player) {
        BedWars.plugin.run {
            /* create inventory */
            val inv = Bukkit.createInventory(
                null,
                BedWars.config.getInt(ConfigPath.GENERAL_CONFIGURATION_STATS_GUI_SIZE),
                replaceStatsPlaceholders(
                    p,
                    Language.getMsg(
                        p,
                        Messages.PLAYER_STATS_GUI_INV_NAME
                    ),
                    true
                )!!
            )

            /* add custom items to gui */
            for (s in BedWars.config.getConfigurationSection(ConfigPath.GENERAL_CONFIGURATION_STATS_PATH)!!.getKeys(false)) {
                /* skip inv size, it isn't a content */
                if (ConfigPath.GENERAL_CONFIGURATION_STATS_GUI_SIZE.contains(s)) continue
                /* create new itemStack for content */
                val i = BedWars.nms.createItemStack(
                    BedWars.config
                        .getString(ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_MATERIAL.replace("%path%", s))!!
                        .uppercase(),
                    1,
                    BedWars.config.getInt(ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_DATA.replace("%path%", s))
                        .toShort()
                )
                i.editMeta {
                    addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    setDisplayName(
                        replaceStatsPlaceholders(
                            p,
                            Language.getMsg(p, Messages.PLAYER_STATS_GUI_PATH + "-" + s + "-name"),
                            true
                        )
                    )

                    val lore: MutableList<String?> = ArrayList<String?>()
                    for (string in Language.getList(p, Messages.PLAYER_STATS_GUI_PATH + "-" + s + "-lore")) {
                        lore.add(replaceStatsPlaceholders(p, string, true))
                    }
                    this.lore = lore
                }
                inv.setItem(
                    BedWars.config.getInt(
                        ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_SLOT.replace(
                            "%path%",
                            s
                        )
                    ), i
                )
            }

            p.openInventory(inv)
            Sounds.playSound("stats-gui-open", p)
        }
    }

    fun replaceStatsPlaceholders(player: Player, s: String, papiReplacements: Boolean): String? {
        var s = s
        val stats = statsManager.get(player.uniqueId)

        if (s.contains("{kills}")) s = s.replace("{kills}", stats.kills.toString())
        if (s.contains("{deaths}")) s = s.replace("{deaths}", stats.deaths.toString())
        if (s.contains("{losses}")) s = s.replace("{losses}", stats.losses.toString())
        if (s.contains("{wins}")) s = s.replace("{wins}", stats.wins.toString())
        if (s.contains("{finalKills}")) s = s.replace("{finalKills}", stats.finalKills.toString())
        if (s.contains("{finalDeaths}")) s = s.replace("{finalDeaths}", stats.finalDeaths.toString())
        if (s.contains("{bedsDestroyed}")) s = s.replace("{bedsDestroyed}", stats.bedsDestroyed.toString())
        if (s.contains("{gamesPlayed}")) s = s.replace("{gamesPlayed}", stats.gamesPlayed.toString())
        if (s.contains("{firstPlay}")) s = s.replace(
            "{firstPlay}", SimpleDateFormat(Language.getMsg(player, Messages.FORMATTING_STATS_DATE_FORMAT)).format(
                if (stats.firstPlay != null) Timestamp.from(stats.firstPlay) else Timestamp.from(
                    Instant.now()
                )
            )
        )
        if (s.contains("{lastPlay}")) s = s.replace(
            "{lastPlay}", SimpleDateFormat(Language.getMsg(player, Messages.FORMATTING_STATS_DATE_FORMAT)).format(
                if (stats.lastPlay != null) Timestamp.from(stats.lastPlay) else Timestamp.from(
                    Instant.now()
                )
            )
        )
        if (s.contains("{player}")) s = s.replace("{player}", player.displayName)
        if (s.contains("{playername")) s = s.replace("{playername}", player.name)
        if (s.contains("{prefix}")) s = s.replace("{prefix}", BedWars.chatSupport.getPrefix(player))

        return if (papiReplacements) SupportPAPI.support.replace(player, s) else s
    }

    /**
     * Check if a location is outside the World Border
     * 
     * @since API 8
     */
    fun isOutsideOfBorder(l: Location): Boolean {
        val border = l.world!!.worldBorder
        val radius = (border.size / 2) + border.warningDistance
        val center = border.center
        return center.distance(l) >= radius
    }

    fun getArenas() = File(BedWars.plugin.dataFolder, "/Arenas")
        .listFiles { it.isFile && it.name.endsWith(".yml") }
        ?.map { it.nameWithoutExtension }
        ?: emptyList()
}
