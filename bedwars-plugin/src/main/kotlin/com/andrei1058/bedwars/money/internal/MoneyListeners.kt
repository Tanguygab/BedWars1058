package com.andrei1058.bedwars.money.internal

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent
import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class MoneyListeners(private val plugin: BedWars) : Listener {
    /**
     * Create a new winner / loser money reward.
     */
    @EventHandler
    fun onGameEnd(e: GameEndEvent) {
        e.winners
            .mapNotNull { Bukkit.getPlayer(it) }
            .forEach { player ->
                rewardPlayer(player, "game-win", Messages.MONEY_REWARD_WIN)
                rewardPlayerPerTeammate(player, e.arena)
            }
        e.losers
            .mapNotNull { Bukkit.getPlayer(it) }
            .forEach { rewardPlayerPerTeammate(it, e.arena) }
    }

    private fun rewardPlayerPerTeammate(player: Player, arena: IArena) {
        val team = arena.getExTeam(player.uniqueId)
        if (team == null || arena.maxInTeam <= 1) return

        rewardPlayer(player, "per-teammate", Messages.MONEY_REWARD_PER_TEAMMATE)
    }

    private fun rewardPlayer(player: Player, name: String, message: String) {
        val reward = plugin.moneyConfig.getDouble("money-rewards.$name")
        if (reward <= 0) return

        BedWars.economy.giveMoney(player, reward)
        player.sendMessage(Language
            .getMsg(player, message)
            .replace("{money}", "$reward")
        )
    }

    /**
     * Create a new bed destroyed money reward.
     */
    @EventHandler
    fun onBreakBed(e: PlayerBedBreakEvent) {
        rewardPlayer(e.player, "bed-destroyed", Messages.MONEY_REWARD_BED_DESTROYED)
    }

    /**
     * Create a kill money reward.
     */
    @EventHandler
    fun onKill(e: PlayerKillEvent) {
        val player = e.killer
        val victim = e.victim
        if (player == null || victim == player) return

        if (e.cause.isFinalKill) {
            rewardPlayer(player, "final-kill", Messages.MONEY_REWARD_FINAL_KILL)
            return
        }
        rewardPlayer(player, "regular-kill", Messages.MONEY_REWARD_REGULAR_KILL)
    }
}