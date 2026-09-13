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
package com.andrei1058.bedwars.upgrades.menu

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.Utils.editMeta
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.upgrades.UpgradeBuyEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.upgrades.EnemyBaseEnterTrap
import com.andrei1058.bedwars.api.upgrades.MenuContent
import com.andrei1058.bedwars.api.upgrades.TeamUpgrade
import com.andrei1058.bedwars.api.upgrades.TrapAction
import com.andrei1058.bedwars.configuration.Sounds.playSound
import com.andrei1058.bedwars.upgrades.trapaction.DisenchantAction
import com.andrei1058.bedwars.upgrades.trapaction.PlayerEffectAction
import com.andrei1058.bedwars.upgrades.trapaction.RemoveEffectAction
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffectType

/**
 * @param name        is the trap identifier.
 * @param displayItem display item.
 * @param cost        trap cost.
 * @param currency    currency cost.
 */
class MenuBaseTrap(
    override val name: String,
    displayItem: ItemStack,
    private val cost: Int,
    private val currency: Material?
) : MenuContent,
    EnemyBaseEnterTrap, TeamUpgrade {
    override val itemStack = BedWars.nms.addCustomData(displayItem, "MCONT_$name")
    private val trapActions = mutableListOf<TrapAction>()

    override val tierCount get() = trapActions.size
    override val nameMsgPath = Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + name.removePrefix("base-trap-")
    override val loreMsgPath = Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + name.removePrefix("base-trap-")

    init {
        val nPath = name.replace("base-trap-", "")
        Language.saveIfNotExists(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + nPath, "&cName not set")
        Language.saveIfNotExists(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + nPath,
            mutableListOf("&cLore not set")
        )
        if (BedWars.api.upgradesManager.configuration.getBoolean("$name.custom-announce")) {
            Language.saveIfNotExists(
                Messages.UPGRADES_TRAP_CUSTOM_MSG + nPath,
                "Edit path: " + Messages.UPGRADES_TRAP_CUSTOM_MSG + nPath
            )
            Language.saveIfNotExists(
                Messages.UPGRADES_TRAP_CUSTOM_TITLE + nPath,
                "Edit path: " + Messages.UPGRADES_TRAP_CUSTOM_TITLE + nPath
            )
            Language.saveIfNotExists(
                Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + nPath,
                "Edit path: " + Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + nPath
            )
        }


        for (action in BedWars.api.upgradesManager.configuration.getStringList("$name.receive")) {
            val type = action.trim().split(":")
            if (type.size < 2) continue
            val data = type[1].trim().lowercase().split(",")
            trapActions += when (type[0].trim().lowercase()) {
                "player-effect" -> {
                    if (data.size < 4) {
                        BedWars.plugin.logger.warning("Invalid ${type[0]} at upgrades2: $name")
                        continue
                    }
                    val pe = PotionEffectType.getByName(data[0].uppercase())
                    if (pe == null) {
                        BedWars.plugin.logger.warning("Invalid potion effect ${data[0]} at upgrades2: $name")
                        continue
                    }
                    val applyType = when (data[3].lowercase()) {
                        "team" -> PlayerEffectAction.ApplyType.TEAM
                        "base" -> PlayerEffectAction.ApplyType.BASE
                        "enemy", "enemies" -> PlayerEffectAction.ApplyType.ENEMY
                        else -> {
                            BedWars.plugin.logger.warning("Invalid apply type ${data[3]} at upgrades2: $name")
                            continue
                        }
                    }
                    val amp = data[1].toIntOrNull() ?: 1
                    val time = data[2].toIntOrNull() ?: 0
                    PlayerEffectAction(pe, amp, time, applyType)
                }

                "disenchant-item" -> {
                    if (data.size < 2) {
                        BedWars.plugin.logger.warning("Invalid ${type[0]} at upgrades2: $name")
                        continue
                    }
                    val re = Enchantment.getByName(data[0].uppercase())
                    if (re == null) {
                        BedWars.plugin.logger.warning("Invalid enchantment ${data[0]} at upgrades2: $name")
                        continue
                    }
                    val da = when (data[1].lowercase()) {
                        "sword" -> DisenchantAction.ApplyType.SWORD
                        "armor" -> DisenchantAction.ApplyType.ARMOR
                        "bow" -> DisenchantAction.ApplyType.BOW
                        else -> {
                            BedWars.plugin.logger.warning("Invalid apply type ${data[3]} at upgrades2: $name")
                            continue
                        }
                    }
                    DisenchantAction(re, da)
                }

                "remove-effect" -> {
                    if (data.isEmpty()) {
                        BedWars.plugin.logger.warning("Invalid ${type[0]} at upgrades2: $name")
                        continue
                    }
                    val pet = PotionEffectType.getByName(data[0].uppercase())
                    if (pet == null) {
                        BedWars.plugin.logger.warning("Invalid potion effect ${data[0]} at upgrades2: $name")
                        continue
                    }
                    RemoveEffectAction(pet)
                }
                else -> continue
            }
        }
    }

    override fun getDisplayItem(player: Player, team: ITeam): ItemStack {
        var currency = currency
        val upgrades = BedWars.api.upgradesManager
        val arenaGroup = team.arena.group.lowercase()
        if (currency == null) {
            val st = upgrades.configuration.getString("$arenaGroup-upgrades-settings.trap-currency")
                ?: upgrades.configuration.getString("default-upgrades-settings.trap-currency")
            currency = Material.valueOf(st!!.uppercase())
        }

        var cost = this.cost
        if (cost == 0) {
            val multiplier = team.activeTraps.size

            var incrementer = upgrades.configuration.getInt("$arenaGroup-upgrades-settings.trap-increment-price")
            if (incrementer == 0) incrementer = upgrades.configuration.getInt("default-upgrades-settings.trap-increment-price")

            cost = upgrades.configuration.getInt(team.arena.group.lowercase() + "-upgrades-settings.trap-start-price")
            if (cost == 0) cost = upgrades.configuration.getInt("default-upgrades-settings.trap-start-price")
            cost += multiplier * incrementer
        }

        val i = itemStack.clone()

        i.editMeta {
            val afford = upgrades.getMoney(player, currency) >= cost
            val color = Language.getMsg(player, if (afford)
                Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD
                else Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD
            )
            this.setDisplayName(Language.getMsg(player, Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + name.removePrefix("base-trap-"))
                .replace("{color}", color)
            )

            val lore = Language.getList(player, Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + name.removePrefix("base-trap-")).toMutableList()
            val currencyMsg = upgrades.getCurrencyMsg(player, cost, currency)
            lore.add(
                Language.getMsg(player, Messages.FORMAT_UPGRADE_TRAP_COST).replace("{cost}", cost.toString())
                    .replace("{currency}", currencyMsg)
                    .replace("{currencyColor}", upgrades.getCurrencyColor(currency).toString())
            )
            lore.add("")
            lore.add(Language.getMsg(player, if (afford)
                Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY
            else Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY)
                .replace("{currency}", currencyMsg)
                .replace("{color}", color)
            )
            this.lore = lore
            addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        }
        return i
    }

    override fun onClick(player: Player, clickType: ClickType, team: ITeam) {
        val upgrades = BedWars.api.upgradesManager
        val arenaGroup = team.arena.group.lowercase()

        var queueLimit = upgrades.configuration.getInt("$arenaGroup-upgrades-settings.trap-queue-limit")
        if (queueLimit == 0) queueLimit = upgrades.configuration.getInt("default-upgrades-settings.trap-queue-limit")

        if (queueLimit <= team.activeTraps.size) {
            player.sendLangMsg(Messages.UPGRADES_TRAP_QUEUE_LIMIT)
            return
        }

        var currency = currency
        if (currency == null) {
            val st = upgrades.configuration.getString("$arenaGroup-upgrades-settings.trap-currency")
                ?: upgrades.configuration.getString("default-upgrades-settings.trap-currency")
            currency = Material.valueOf(st!!.uppercase())
        }

        var cost = cost
        if (cost == 0) {
            val multiplier = team.activeTraps.size

            var incrementer: Int = upgrades.configuration.getInt("$arenaGroup-upgrades-settings.trap-increment-price")
            if (incrementer == 0) incrementer = upgrades.configuration.getInt("default-upgrades-settings.trap-increment-price")

            cost = upgrades.configuration.getInt("$arenaGroup-upgrades-settings.trap-start-price")
            if (cost == 0) cost = upgrades.configuration.getInt("default-upgrades-settings.trap-start-price")
            cost += multiplier * incrementer
        }

        val money: Int = upgrades.getMoney(player, currency)
        if (money < cost) {
            playSound(ConfigPath.SOUNDS_INSUFF_MONEY, player)
            player.sendMessage(
                Language.getMsg(player, Messages.SHOP_INSUFFICIENT_MONEY)
                    .replace("{currency}", upgrades.getCurrencyMsg(player, cost, currency))
                    .replace("{amount}", (cost - money).toString())
            )
            player.closeInventory()
            return
        }

        val event: UpgradeBuyEvent
        Bukkit.getPluginManager().callEvent(UpgradeBuyEvent(this, player, team).also { event = it })
        if (event.isCancelled()) return

        if (currency == Material.AIR) {
            BedWars.economy.buyAction(player, money.toDouble())
        } else {
            BedWars.api.shopUtil.takeMoney(player, currency, cost)
        }
        playSound(ConfigPath.SOUNDS_BOUGHT, player)
        team.activeTraps += this
        // when a new trap is bought check for enemies on the island #646
        for (arenaPlayer in team.arena.players) {
            if (team.isMember(arenaPlayer)) continue
            if (team.arena.isRespawning(arenaPlayer)) continue
            if (arenaPlayer.location.distance(team.bed) <= team.arena.islandRadius) {
                team.activeTraps.removeAt(0).trigger(team, arenaPlayer)
                break
            }
        }

        for (p1 in team.members) {
            p1.sendLangMsg(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT,
                "{playername}" to player.name,
                "{player}" to player.displayName,
                "{upgradeName}" to ChatColor.stripColor(
                    Language.getMsg(p1, Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + name.removePrefix("base-trap-")
                    ).replace("{color}", "")
                )!!
            )
        }
        upgrades.getMenuForArena(team.arena).open(player)
    }

    override fun trigger(trapTeam: ITeam, player: Player) {
        val upgrades = BedWars.api.upgradesManager
        val soundName = upgrades.configuration.getString("$name.sound")
        val sound = Sound.entries.find { it.name == soundName }
        if (!playSound(sound, trapTeam.members)) {
            playSound("trap-sound", trapTeam.members)
        }

        val enemyTeam = trapTeam.arena.getTeam(player)
        trapActions.forEach { it.onTrigger(player, enemyTeam!!, trapTeam) }

        if (!upgrades.configuration.getBoolean("$name.custom-announce")) {
            for (p in trapTeam.members) {
                val trapName = ChatColor.stripColor(Language.getMsg(p, nameMsgPath))!!.replace("{color}", "")
                p.sendLangMsg(Messages.UPGRADES_TRAP_DEFAULT_MSG, "{trap}" to trapName)
                BedWars.nms.sendTitle(
                    p,
                    Language.getMsg(p, Messages.UPGRADES_TRAP_DEFAULT_TITLE)
                        .replace("{trap}", trapName),
                    Language.getMsg(p, Messages.UPGRADES_TRAP_DEFAULT_SUBTITLE)
                        .replace("{trap}", trapName), 15, 35, 10
                )
            }
            return
        }

        val name2 = name.removePrefix("base-trap-")
        val color = if (trapTeam.arena.getTeam(player) == null) ""
        else trapTeam.arena.getTeam(player)!!.color.chat.toString()

        for (p in trapTeam.members) {
            val trapName = ChatColor.stripColor(Language.getMsg(p, nameMsgPath))!!.replace("{color}", "")
            val enemy = if (trapTeam.arena.getTeam(player) == null) "NULL"
            else trapTeam.arena.getTeam(player)!!.getDisplayName(Language.getLanguage(p))
            p.sendLangMsg(Messages.UPGRADES_TRAP_CUSTOM_MSG + name2,
                "{trap}" to trapName,
                "{player}" to player.name,
                "{team}" to enemy,
                "{color}" to color
            )
            BedWars.nms.sendTitle(
                p,
                Language.getMsg(p, Messages.UPGRADES_TRAP_CUSTOM_TITLE + name2)
                    .replace("{trap}", trapName)
                    .replace("{player}", player.name)
                    .replace("{team}", enemy)
                    .replace("{color}", color),
                Language.getMsg(p, Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + name2)
                    .replace("{trap}", trapName)
                    .replace("{player}", player.name)
                    .replace("{team}", enemy)
                    .replace("{color}", color),
                15, 35, 10)
        }
    }
}
