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
package com.andrei1058.bedwars.shop.main

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.util.Utils.editMeta
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.shop.ICategoryContent
import com.andrei1058.bedwars.api.arena.shop.IContentTier
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.shop.ShopBuyEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.configuration.Sounds.playSound
import com.andrei1058.bedwars.shop.ShopCache
import com.andrei1058.bedwars.shop.quickbuy.PlayerQuickBuyCache
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

open class CategoryContent(
    override val identifier: String,
    name: String,
    categoryName: String,
    yml: YamlConfiguration?,
    private val father: ShopCategory
) : ICategoryContent {
    override val slot = yml?.getInt("$identifier.${ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_SLOT}") ?: 0

    /**
     * Check if category content was loaded
     */
    var isLoaded = true
    override val contentTiers = mutableListOf<IContentTier>()
    private val itemNamePath = Messages.SHOP_CONTENT_TIER_ITEM_NAME
        .replace("%category%", categoryName)
        .replace("%content%", name)
    private val itemLorePath = Messages.SHOP_CONTENT_TIER_ITEM_LORE
        .replace("%category%", categoryName)
        .replace("%content%", name)
    override val isPermanent = yml?.getBoolean("$identifier.${ConfigPath.SHOP_CATEGORY_CONTENT_IS_PERMANENT}") ?: false
    override val isDowngradable = yml?.getBoolean("$identifier.${ConfigPath.SHOP_CATEGORY_CONTENT_IS_DOWNGRADABLE}") ?: false
    // huh?
    private val unbreakable = yml?.getBoolean("$identifier.${ConfigPath.SHOP_CATEGORY_CONTENT_IS_UNBREAKABLE}") ?: false
    private val weight = yml?.getInt("$identifier.${ConfigPath.SHOP_CATEGORY_CONTENT_WEIGHT}")?.toByte() ?: 0

    /**
     * Load a new category
     */
    init {
        BedWars.debug("Loading CategoryContent $identifier")

        if (yml?.get("$identifier.${ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_SLOT}") == null) {
            BedWars.INSTANCE.logger.severe("Content slot not set at $identifier")
            isLoaded = false
        }

        val path = "$identifier.${ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_TIERS}"
        val tiers = yml?.getConfigurationSection(path)
        if (tiers == null || tiers.getKeys(false).isEmpty()) {
            BedWars.INSTANCE.logger.severe("No tiers set for $identifier")
            isLoaded = false
        } else if (yml.get("$path.tier1") == null) {
            BedWars.INSTANCE.logger.severe("tier1 not found for $identifier")
            isLoaded = false
        }

        for (s in yml?.getConfigurationSection(path)!!.getKeys(false)) {
            contentTiers += ContentTier("$path.$s", s, identifier, yml)
        }

        for (lang in Language.languages) {
            if (itemNamePath !in lang) lang.set(itemNamePath, "&cName not set")
            if (itemLorePath !in lang) lang.set(itemLorePath, "&cLore not set")
        }
    }

    fun execute(player: Player, shopCache: ShopCache, slot: Int) {
        //check weight
        if (shopCache.getCategoryWeight(father) > weight) return

        if (shopCache.getContentTier(identifier) > contentTiers.size) {
            Bukkit.getLogger().severe("Wrong tier order at: $identifier")
            return
        }

        //check if can re-buy
        val ct = contentTiers[if (shopCache.getContentTier(identifier) == contentTiers.size) {
            if (isPermanent && shopCache.hasCachedItem(this)) {
                player.sendLangMsg(Messages.SHOP_ALREADY_BOUGHT)
                playSound(ConfigPath.SOUNDS_INSUFF_MONEY, player)
                return
            }
            //current tier
            shopCache.getContentTier(identifier) - 1
        } else
            if (!shopCache.hasCachedItem(this)) 0
            else shopCache.getContentTier(identifier)
        ]

        //check money
        val money = calculateMoney(player, ct.currency)
        if (money < ct.price) {
            player.sendMessage(Language
                .getMsg(player, Messages.SHOP_INSUFFICIENT_MONEY)
                .replace("{currency}", Language.getMsg(player, getCurrencyMsgPath(ct)))
                .replace("{amount}", "${ct.price - money}")
            )
            playSound(ConfigPath.SOUNDS_INSUFF_MONEY, player)
            return
        }

        val arena = BedWars.INSTANCE.arenaManager.getArena(player)!!
        val event = ShopBuyEvent(player, arena, this)
        //call shop buy event
        Bukkit.getPluginManager().callEvent(event)
        if (event.isCancelled()) return

        //take money
        takeMoney(player, ct.currency, ct.price)

        //upgrade if possible
        shopCache.upgradeCachedItem(this, slot)


        //give items
        giveItems(player, shopCache, arena)

        //play sound
        playSound(ConfigPath.SOUNDS_BOUGHT, player)

        //send purchase msg
        if (Language.getLanguage(player)[itemNamePath] == null) {
            val displayItemMeta = ct.itemStack.itemMeta
            if (displayItemMeta?.hasDisplayName() == true) {
                player.sendMessage(Language
                    .getMsg(player, Messages.SHOP_NEW_PURCHASE)
                    .replace("{item}", displayItemMeta.displayName)
                )
            }
        } else player.sendMessage(Language
            .getMsg(player, Messages.SHOP_NEW_PURCHASE)
            .replace("{item}", ChatColor.stripColor(Language.getMsg(player, itemNamePath))!!)
            .replace("{color}", "")
            .replace("{tier}", "")
        )


        shopCache.setCategoryWeight(father, weight)
    }

    /**
     * Add tier items to player inventory
     */
    fun giveItems(player: Player, shopCache: ShopCache, arena: IArena) {
        for (bi in contentTiers[shopCache.getContentTier(identifier) - 1].buyItemsList) {
            bi.give(player, arena)
        }
    }

    override fun getItemStack(player: Player): ItemStack {
        val shopCache = ShopCache.getShopCache(player.uniqueId)
        return if (shopCache == null) ItemStack(Material.AIR) else getItemStack(player, shopCache)
    }

    override fun hasQuick(player: Player): Boolean {
        val quickBuyCache = PlayerQuickBuyCache.getQuickBuyCache(player.uniqueId)
        return quickBuyCache != null && hasQuick(quickBuyCache)
    }

    open fun getItemStack(player: Player, shopCache: ShopCache): ItemStack {
        val tier = shopCache.getContentTier(identifier)
        val ct = contentTiers[
            if (tier >= contentTiers.size) contentTiers.size - 1
            else if (shopCache.hasCachedItem(this)) tier
            else tier - 1
        ]

        val item = ct.itemStack
        item.editMeta {
            val canAfford = calculateMoney(player, ct.currency) >= ct.price
            val qbc = PlayerQuickBuyCache.getQuickBuyCache(player.uniqueId)
            val hasQuick = qbc != null && hasQuick(qbc)

            val color = Language.getMsg(player, if (canAfford) Messages.SHOP_CAN_BUY_COLOR else Messages.SHOP_CANT_BUY_COLOR)
            val translatedCurrency = Language.getMsg(player, getCurrencyMsgPath(ct))
            val cColor = getCurrencyColor(ct.currency)

            val tierI = ct.value
            val tier = getRomanNumber(tierI)

            val buyStatus = Language.getMsg(player,
                if (isPermanent && shopCache.getCachedItem(this@CategoryContent)?.tier == contentTiers.size)
                    if (BedWars.INSTANCE.versionSupport.isArmor(item)) Messages.SHOP_LORE_STATUS_ARMOR
                    else Messages.SHOP_LORE_STATUS_MAXED
                else if (canAfford) Messages.SHOP_LORE_STATUS_CAN_BUY
                else Messages.SHOP_LORE_STATUS_CANT_AFFORD //ARMOR
            ).replace("{currency}", translatedCurrency)


            setDisplayName(Language
                .getMsg(player, itemNamePath)
                .replace("{color}", color)
                .replace("{tier}", tier)
            )

            val lore = mutableListOf<String>()
            for (line in Language.getList(player, itemLorePath)) {
                var line = line
                if ("{quick_buy}" in line) {
                    line = if (hasQuick) {
                        if (player.uniqueId in ShopIndex.indexViewers) {
                            Language.getMsg(player, Messages.SHOP_LORE_QUICK_REMOVE)
                        } else continue
                    } else {
                        Language.getMsg(player, Messages.SHOP_LORE_QUICK_ADD)
                    }
                }
                line = line
                    .replace("{tier}", tier)
                    .replace("{color}", color)
                    .replace("{cost}", "$cColor${ct.price}")
                    .replace("{currency}", "$cColor$translatedCurrency")
                    .replace("{buy_status}", buyStatus)
                lore += line
            }
            this.lore = lore
        }
        return item
    }

    fun hasQuick(c: PlayerQuickBuyCache) = c.elements.any { it.categoryContent === this }

    companion object {
        /**
         * Get player's money amount
         */
        fun calculateMoney(player: Player, currency: Material) = if (currency != Material.AIR)
            player.inventory.contents
                .asSequence()
                .filterNotNull()
                .filter { it.type == currency }
                .sumOf { it.amount }
        else BedWars.economy.getMoney(player).toInt()

        /**
         * Get currency as material
         */
        fun getCurrency(currency: String) = when (currency) {
            "gold" -> Material.GOLD_INGOT
            "diamond" -> Material.DIAMOND
            "emerald" -> Material.EMERALD
            "vault" -> Material.AIR
            else -> Material.IRON_INGOT
        }

        fun getCurrencyColor(currency: Material): ChatColor {
            val ore = currency.toString()
            return when {
                "DIAMOND" in ore -> ChatColor.AQUA
                "GOLD" in ore -> ChatColor.GOLD
                "IRON" in ore -> ChatColor.WHITE
                else -> ChatColor.DARK_GREEN
            }
        }

        /**
         * Cet currency path
         */
        fun getCurrencyMsgPath(contentTier: IContentTier): String {
            val currency = contentTier.currency.toString()
            val singular = contentTier.price == 1
            val c = when {
                "IRON" in currency -> if (singular) Messages.MEANING_IRON_SINGULAR else Messages.MEANING_IRON_PLURAL
                "GOLD" in currency -> if (singular) Messages.MEANING_GOLD_SINGULAR else Messages.MEANING_GOLD_PLURAL
                "EMERALD" in currency -> if (singular) Messages.MEANING_EMERALD_SINGULAR else Messages.MEANING_EMERALD_PLURAL
                "DIAMOND" in currency -> if (singular) Messages.MEANING_DIAMOND_SINGULAR else Messages.MEANING_DIAMOND_PLURAL
                else -> if (singular) Messages.MEANING_VAULT_SINGULAR else Messages.MEANING_VAULT_PLURAL
            }
            return c
        }

        /**
         * Get the roman number for an integer
         */
        fun getRomanNumber(n: Int) = when (n) {
            1 -> "I"
            2 -> "II"
            3 -> "III"
            4 -> "IV"
            5 -> "V"
            6 -> "VI"
            7 -> "VII"
            8 -> "VIII"
            9 -> "IX"
            10 -> "X"
            else -> "$n"
        }


        /**
         * Take money from player on buy
         */
        fun takeMoney(player: Player, currency: Material?, amount: Int) {
            if (currency == Material.AIR) {
                if (!BedWars.economy.isEconomy) {
                    player.sendMessage("§4§lERROR: This requires Vault Support! Please install Vault plugin!")
                    return
                }
                BedWars.economy.buyAction(player, amount.toDouble())
                return
            }

            var cost = amount
            val nms = BedWars.INSTANCE.versionSupport
            for (i in player.inventory.contents) {
                if (i == null || i.type != currency) continue

                if (i.amount < cost) {
                    cost -= i.amount
                    nms.minusAmount(player, i, i.amount)
                    player.updateInventory()
                } else {
                    nms.minusAmount(player, i, cost)
                    player.updateInventory()
                    break
                }
            }
        }
    }
}
