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

import com.andrei1058.bedwars.api.Configs
import com.andrei1058.bedwars.api.ShopUtil
import com.andrei1058.bedwars.api.arena.shop.IContentTier
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.levels.Level
import com.andrei1058.bedwars.api.party.Party
import com.andrei1058.bedwars.api.server.RestoreAdapter
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.shop.main.CategoryContent
import com.andrei1058.bedwars.stats.StatsManagerImpl
import org.bukkit.Material
import org.bukkit.entity.Player
import java.io.File
import java.util.UUID

class API(private val plugin: BedWars) : com.andrei1058.bedwars.api.BedWars {
    override var isShuttingDown = false
        internal set
    override val versionSupport get() = BedWars.nms
    override lateinit var defaultLang: Language
        internal set
    override val serverType get() = BedWars.serverType

    override val lobbyWorld get() = BedWars.lobbyWorld
    override val isAutoScale get() = BedWars.autoscale
    override fun getSetupSession(player: UUID) = SetupSession.getSession(player)
    override fun isInSetupSession(player: UUID) = SetupSession.isInSetupSession(player)

    override val arenaManager get() = plugin.arenaManager
    override val statsManager = StatsManagerImpl()
    override val afkManager get() = plugin.afkManager
    override val upgradesManager get() = plugin.upgradesManager

    override val configs = object : Configs {
        override val mainConfig get() = BedWars.config
        override val signsConfig get() = BedWars.signs
        override val generatorsConfig get() = BedWars.generatorsCfg
        override val shopConfig get() = BedWars.shop
        override val upgradesConfig get() = upgradesManager.configuration
    }

    override val shopUtil = object : ShopUtil {
        override fun calculateMoney(player: Player, currency: Material) = CategoryContent.calculateMoney(player, currency)
        override fun getCurrency(currency: String) = CategoryContent.getCurrency(currency)
        override fun getCurrencyColor(currency: Material) = CategoryContent.getCurrencyColor(currency)
        override fun getCurrencyMsgPath(contentTier: IContentTier) = CategoryContent.getCurrencyMsgPath(contentTier)
        override fun getRomanNumber(n: Int) = CategoryContent.getRomanNumber(n)
        override fun takeMoney(player: Player, currency: Material, amount: Int) = CategoryContent.takeMoney(player, currency, amount)
    }

    override val levelsUtil get() = BedWars.levelSupport
    override val partyUtil get() = BedWars.party
    override var restoreAdapter
        get() = Companion.restoreAdapter
        set(value) {
            if (arenaManager.arenas.isNotEmpty()) throw IllegalAccessError("Arenas must be unloaded when changing the adapter")

            Companion.restoreAdapter = value
            if (value.owner === plugin) return
            plugin.logger.warning("${value.owner.name} changed the restore system to its own adapter.")
        }

    override fun setPartyAdapter(partyAdapter: Party) {
        if (partyAdapter == BedWars.party) return
        BedWars.party = partyAdapter
        plugin.logger.warning("One of your plugins changed the Party adapter to: ${partyAdapter.javaClass.name}")
    }

    override fun setLevelAdapter(level: Level) {
        BedWars.levelSupport = level
    }
    override fun getLanguageByIso(isoCode: String) = Language.getLang(isoCode)
    override fun getLanguage(player: Player) = Language.getLanguage(player)
    override fun getLangIso(player: Player) = Language.getLanguage(player).iso

    override val addonsPath = File(plugin.dataFolder, "Addons")
    override val scoreboardManager get() = plugin.scoreboardManager


    override fun isVIP(player: Player) = player.hasPermission(BedWars.MAIN_COMMAND + ".*") || player.hasPermission(BedWars.MAIN_COMMAND + ".vip")

    companion object {
        private lateinit var restoreAdapter: RestoreAdapter<*>
    }
}
