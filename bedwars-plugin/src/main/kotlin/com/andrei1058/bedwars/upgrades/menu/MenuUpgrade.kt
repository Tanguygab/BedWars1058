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
import com.andrei1058.bedwars.api.upgrades.MenuContent
import com.andrei1058.bedwars.api.upgrades.TeamUpgrade
import com.andrei1058.bedwars.configuration.Sounds
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

/**
 * Create a new upgrade element.
 *
 * @param name identifier.
 */
class MenuUpgrade(override val name: String) : MenuContent, TeamUpgrade {

    /**
     * @return tiers list.
     */
    private val tiers = mutableListOf<UpgradeTier>()
    override val tierCount get() = tiers.size

    override fun getDisplayItem(player: Player, team: ITeam): ItemStack {
        if (tiers.isEmpty()) return ItemStack(Material.BEDROCK)

        var tier = -1
        if (team.teamUpgradeTiers.containsKey(name)) {
            tier = team.teamUpgradeTiers[name]!!
        }

        val highest = tiers.size == tier + 1 && team.teamUpgradeTiers.containsKey(name)
        if (!highest) tier += 1
        val ut = tiers[tier]
        val afford = BedWars.api.upgradesManager.getMoney(player, ut.currency) >= ut.cost

        val i = ItemStack(tiers[tier].displayItem)
        i.editMeta {
            val color = Language.getMsg(player, if (!highest)
                if (afford) Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD
                else Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD
            else Messages.FORMAT_UPGRADE_COLOR_UNLOCKED)

            setDisplayName(Language.getMsg(
                player,
                Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", name.replace("upgrade-", ""))
                    .replace("{tier}", ut.name)
            ).replace("{color}", color))

            val lore = mutableListOf<String>()
            val currencyMsg = BedWars.api.upgradesManager.getCurrencyMsg(player, ut)
            for (s in Language.getList(
                player,
                Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", name.replace("upgrade-", ""))
            )) {
                if ("{tier_" in s) {
                    // Get tier number from placeholder
                    val result = s.replace(".*_([0-9]+)_.*".toRegex(), "$1")

                    var tierColor = Messages.FORMAT_UPGRADE_TIER_LOCKED
                    if (result.toInt() - 1 <= team.teamUpgradeTiers.getOrDefault(name, -1)) {
                        tierColor = Messages.FORMAT_UPGRADE_TIER_UNLOCKED
                    }

                    //get current tier. Note: placeholder number doesn't match array index.
                    val upgradeTier = tiers[result.toInt() - 1]

                    lore.add(s
                        .replace("{tier_${result}_cost}", upgradeTier.cost.toString())
                        .replace("{tier_${result}_currency}", currencyMsg)
                        .replace("{tier_${result}_color}", Language.getMsg(player, tierColor))
                    )
                } else lore.add(s.replace("{color}", color))
            }
            lore.add(
                Language.getMsg(
                    player, if (highest) Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED
                    else if (afford) Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY
                    else Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY
                ).replace("{currency}", currencyMsg).replace("{color}", color)
            )
            this.lore = lore
            addItemFlags(ItemFlag.HIDE_ATTRIBUTES)

        }
        return i
    }

    override fun onClick(player: Player, clickType: ClickType, team: ITeam) {
        val tier = team.teamUpgradeTiers[name] ?: -1
        if (tiers.size - 1 <= tier) return

        val ut = tiers[tier + 1]

        val money = BedWars.api.upgradesManager.getMoney(player, ut.currency)
        if (money < ut.cost) {
            Sounds.playSound(ConfigPath.SOUNDS_INSUFF_MONEY, player)
            player.sendLangMsg(Messages.SHOP_INSUFFICIENT_MONEY,
                "{currency}" to BedWars.api.upgradesManager.getCurrencyMsg(player, ut),
                "{amount}" to ut.cost - money
            )
            player.closeInventory()
            return
        }

        val event = UpgradeBuyEvent(this, player, team)
        Bukkit.getPluginManager().callEvent(event)
        if (event.isCancelled()) return

        if (ut.currency == Material.AIR) {
            BedWars.economy.buyAction(player, ut.cost.toDouble())
        } else {
            BedWars.api.shopUtil.takeMoney(player, ut.currency, ut.cost)
        }

        team.teamUpgradeTiers[name] = if (!team.teamUpgradeTiers.containsKey(name)) 0
        else team.teamUpgradeTiers[name]!! + 1

        Sounds.playSound(ConfigPath.SOUNDS_BOUGHT, player)
        ut.upgradeActions.forEach { it.onBuy(player, team) }

        for (p1 in team.members) {
            p1.sendLangMsg(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT,
                "{playername}" to player.name,
                "{player}" to player.displayName,
                "{upgradeName}" to ChatColor.stripColor(
                    Language.getMsg(
                        p1,
                        Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace(
                            "{name}", name.replace("upgrade-", "")
                        ).replace("{tier}", ut.name)
                    )
                )!!,
                "color" to ""
            )
        }

        val arena = BedWars.api.arenaManager.getArena(player)!!
        val menuContentBySlot = BedWars.api.upgradesManager.getMenuForArena(arena).menuContentBySlot
        val inv = player.openInventory.topInventory
        for ((key, value) in menuContentBySlot) {
            inv.setItem(key, value.getDisplayItem(player, team))
        }
    }

    /**
     * Load an upgrade element tiers.
     * 
     * @param upgradeTier tier.
     * @return false if something went wrong.
     */
    fun addTier(upgradeTier: UpgradeTier): Boolean {
        if (tiers.any { it.name == upgradeTier.name }) return false
        tiers.add(upgradeTier)
        return true
    }

}
