package com.andrei1058.bedwars.arena.upgrades

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent
import com.andrei1058.bedwars.api.events.player.PlayerLeaveArenaEvent
import com.andrei1058.bedwars.api.events.server.ArenaDisableEvent
import com.andrei1058.bedwars.api.events.team.TeamEliminatedEvent
import com.andrei1058.bedwars.api.events.upgrades.UpgradeBuyEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class HealPoolListner(private val plugin: BedWars) : Listener {
    @EventHandler
    fun onTeamUpgrade(e: UpgradeBuyEvent) {
        if ("heal-pool" !in e.teamUpgrade.name) return
        val arena = e.arena
        val team = arena.getTeam(e.player) ?: return
        if (HealPoolTask.exists(arena, team)) return
        HealPoolTask(plugin, team)
    }

    @EventHandler
    fun onDisable(e: ArenaDisableEvent) = HealPoolTask.removeForArena(e.worldName)

    @EventHandler
    fun onEnd(e: GameEndEvent) = HealPoolTask.removeForArena(e.arena)

    @EventHandler
    fun teamDead(e: TeamEliminatedEvent) = HealPoolTask.removeForTeam(e.team)

    @EventHandler
    fun lastLeave(event: PlayerLeaveArenaEvent) {
        if (event.arena.players.isEmpty()) HealPoolTask.removeForArena(event.arena)
    }
}
