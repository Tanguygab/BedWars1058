package com.andrei1058.bedwars.api

import com.andrei1058.bedwars.api.arena.shop.IContentTier
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player

interface ShopUtil {
    /**
     * Get player's money amount
     */
    fun calculateMoney(player: Player, currency: Material): Int

    /**
     * Get currency as material
     *
     * @return [Material.AIR] if is vault.
     */
    fun getCurrency(currency: String): Material

    fun getCurrencyColor(currency: Material): ChatColor

    /**
     * Cet currency path
     */
    fun getCurrencyMsgPath(contentTier: IContentTier): String

    /**
     * Get roman number for given int.
     */
    fun getRomanNumber(n: Int): String

    /**
     * Take money from player on buy
     */
    fun takeMoney(player: Player, currency: Material, amount: Int)
}