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
package com.andrei1058.bedwars.api.entity

import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent.PlayerKillCause
import com.andrei1058.bedwars.api.language.Messages
import org.bukkit.Bukkit
import org.bukkit.entity.LivingEntity

class Despawnable(
    val entity: LivingEntity,
    val team: ITeam,
    despawn: Int,
    private val namePath: String,
    val deathFinalCause: PlayerKillCause,
    val deathRegularCause: PlayerKillCause
) {
    var despawn = 250
        private set

    init {
        if (despawn != 0) {
            this.despawn = despawn
        }
        if (api == null) api = Bukkit.getServer().servicesManager
            .getRegistration(BedWars::class.java)!!
            .provider
        api!!.versionSupport.despawnables[entity.uniqueId] = this
        setName()
    }

    fun refresh() {
        if (entity.isDead) {
            api!!.versionSupport.despawnables.remove(entity.uniqueId)
            return
        }
        setName()
        if (--despawn == 0) destroy()
    }

    private fun setName() {
        val lang = api!!.defaultLang
        val percentage = ((entity.health * 100) / entity.maxHealth / 10).toInt()
        val healthLang = lang.m(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH)
        val name = lang.m(namePath)
            .replace("{despawn}", "$despawn")
            .replace("{health}", healthLang.repeat(percentage) + "§7" + healthLang.repeat(10 - percentage))
            .replace("{TeamColor}", "${team.color.chat}")
            .replace("{TeamName}", team.getDisplayName(api!!.defaultLang)
        )
        entity.customName = name
    }

    fun destroy() {
        entity.damage(Int.MAX_VALUE.toDouble())
        api!!.versionSupport.despawnables.remove(entity.uniqueId)
    }

    companion object {
        private var api: BedWars? = null
    }
}
