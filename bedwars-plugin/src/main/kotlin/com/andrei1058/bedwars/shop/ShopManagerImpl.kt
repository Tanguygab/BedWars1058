package com.andrei1058.bedwars.shop

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.ShopManager
import com.andrei1058.bedwars.api.arena.shop.IContentTier
import com.andrei1058.bedwars.shop.main.CategoryContent
import org.bukkit.Material
import org.bukkit.entity.Player

class ShopManagerImpl(plugin: BedWars) : ShopManager {

    val config = ShopConfig(plugin)

    override fun calculateMoney(player: Player, currency: Material) = CategoryContent.calculateMoney(player, currency)
    override fun getCurrency(currency: String) = CategoryContent.getCurrency(currency)
    override fun getCurrencyColor(currency: Material) = CategoryContent.getCurrencyColor(currency)
    override fun getCurrencyMsgPath(contentTier: IContentTier) = CategoryContent.getCurrencyMsgPath(contentTier)
    override fun getRomanNumber(n: Int) = CategoryContent.getRomanNumber(n)
    override fun takeMoney(player: Player, currency: Material, amount: Int) = CategoryContent.takeMoney(player, currency, amount)
}