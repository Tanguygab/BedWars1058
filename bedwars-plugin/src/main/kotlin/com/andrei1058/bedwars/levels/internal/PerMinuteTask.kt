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
package com.andrei1058.bedwars.levels.internal

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.events.player.PlayerXpGainEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.arena.Arena
import org.bukkit.Bukkit

class PerMinuteTask(arena: Arena) {
    private val xp = BedWars.INSTANCE.levelsConfig.getInt("xp-rewards.per-minute")
    private val task = if (xp >= 1) {
        val delay = (60 * 20).toLong()
        Bukkit.getScheduler().runTaskTimer(BedWars.INSTANCE, Runnable {
            for (p in arena.players) {
                PlayerLevel.getLevelByPlayer(p.uniqueId).addXp(xp, PlayerXpGainEvent.XpSource.PER_MINUTE)
                p.sendMessage(Language.getMsg(p, Messages.XP_REWARD_PER_MINUTE).replace("{xp}", "$xp"))
            }
        }, delay, delay)
    } else null

    /**
     * Cancel task.
     */
    fun cancel() {
        task?.cancel()
    }
}
