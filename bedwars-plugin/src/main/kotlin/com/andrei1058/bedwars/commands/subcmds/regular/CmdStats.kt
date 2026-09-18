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
package com.andrei1058.bedwars.commands.subcmds.regular

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.util.Utils.editMeta
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.commands.subcmds.CooldownCommand
import com.andrei1058.bedwars.configuration.Sounds
import com.andrei1058.bedwars.support.papi.PAPISupport
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Instant

class CmdStats(parent: MainCommand) : CooldownCommand(parent, "stats", 3000, priority = 16) {
    override val description = createDescription("Opens the stats GUI.")

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false
        val arena = plugin.arenaManager.getArena(sender)
        if (arena != null && arena.status != GameState.STARTING && arena.status != GameState.WAITING && !arena.isSpectator(sender)) {
            return false
        }
        if (isOnCooldown(sender.uniqueId)) return true
        setCooldown(sender.uniqueId)

        val config = plugin.config
        plugin.run {
            /* create inventory */
            val inv = Bukkit.createInventory(
                null,
                config.getInt(ConfigPath.GENERAL_CONFIGURATION_STATS_GUI_SIZE),
                replaceStatsPlaceholders(sender, Language.getMsg(sender, Messages.PLAYER_STATS_GUI_INV_NAME))
            )

            /* add custom items to gui */
            for (stat in config.getConfigurationSection(ConfigPath.GENERAL_CONFIGURATION_STATS_PATH)!!.getKeys(false)) {
                /* skip inv size, it isn't a content */
                if (ConfigPath.GENERAL_CONFIGURATION_STATS_GUI_SIZE.contains(stat)) continue
                /* create new itemStack for content */
                val item = plugin.versionSupport.createItemStack(
                    config.getString(ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_MATERIAL.replace("%path%", stat))!!.uppercase(),
                    1,
                    config.getInt(ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_DATA.replace("%path%", stat)).toShort()
                )
                item.editMeta {
                    addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    setDisplayName(replaceStatsPlaceholders(
                        sender,
                        Language.getMsg(sender, "${Messages.PLAYER_STATS_GUI_PATH}-$stat-name")
                    ))

                    lore = Language.getList(sender, "${Messages.PLAYER_STATS_GUI_PATH}-$stat-lore")
                        .map { replaceStatsPlaceholders(sender, it) }
                }
                inv.setItem(config.getInt(ConfigPath.GENERAL_CONFIGURATION_STATS_ITEMS_SLOT.replace("%path%", stat)), item)
            }

            sender.openInventory(inv)
            Sounds.playSound("stats-gui-open", sender)
        }
        return true
    }

    private fun replaceStatsPlaceholders(player: Player, s: String): String {
        val stats = BedWars.INSTANCE.statsManager.get(player.uniqueId)

        val dateFormat = SimpleDateFormat(Language.getMsg(player, Messages.FORMATTING_STATS_DATE_FORMAT))
        val s = stats.run { s.replace("{kills}", "$kills")
            .replace("{deaths}", "$deaths")
            .replace("{losses}", "$losses")
            .replace("{wins}", "$wins")
            .replace("{finalKills}", "$finalKills")
            .replace("{finalDeaths}", "$finalDeaths")
            .replace("{bedsDestroyed}", "$bedsDestroyed")
            .replace("{gamesPlayed}", "$gamesPlayed")
            .replace("{firstPlay}", dateFormat.format(Timestamp.from(firstPlay ?: Instant.now())))
            .replace("{lastPlay}", dateFormat.format(Timestamp.from(lastPlay ?: Instant.now())))
            .replace("{player}", player.displayName)
            .replace("{playername}", player.name)
            .replace("{prefix}", BedWars.chatSupport.getPrefix(player))
        }
        return PAPISupport.support.replace(player, s)
    }
}
