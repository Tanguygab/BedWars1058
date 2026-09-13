package com.andrei1058.bedwars.money.internal

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.arena.Arena
import org.bukkit.Bukkit

class MoneyPerMinuteTask(arena: Arena) {
    private val money = BedWars.plugin.moneyConfig.getDouble("money-rewards.per-minute")

    private val task = if (money < 1) Bukkit.getScheduler().runTaskTimer(BedWars.plugin, Runnable {
        for (player in arena.players) {
            BedWars.economy.giveMoney(player, money)
            player.sendMessage(Language
                .getMsg(player, Messages.MONEY_REWARD_PER_MINUTE)
                .replace("{money}", "$money")
            )
        }
    }, 60 * 20, 60 * 20)
    else null

    /**
     * Cancel task.
     */
    fun cancel() = task?.cancel()
}
