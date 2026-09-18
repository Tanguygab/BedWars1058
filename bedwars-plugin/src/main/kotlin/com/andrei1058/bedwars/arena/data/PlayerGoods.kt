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
package com.andrei1058.bedwars.arena.data

import com.andrei1058.bedwars.BedWars
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.entity.Player
import java.util.UUID

/**
 * This is where player stuff are stored so he can have them back after a game
 */
internal class PlayerGoods private constructor(player: Player) {
    private val uuid = player.uniqueId
    private val level = player.level
    private val food = player.foodLevel
    private val health = player.health
    private val healthScale = player.healthScale
    private val experience = player.exp
    private val items = player.inventory.contents.copyOf()
    private val potionEffects = player.activePotionEffects.toList()
    private val armor = player.inventory.armorContents.copyOf()
    private val enderchest = player.enderChest.contents.copyOf()
    private val gamemode = player.gameMode
    private val allowFlying = player.allowFlight
    private val flying = player.isFlying
    private val displayName = player.displayName
    private val tabName = player.playerListName

    /**
     * restore player
     */
    fun restore(): Boolean {
        val player = Bukkit.getPlayer(uuid) ?: return false

        player.apply {
            gameMode = gamemode

            health = this@PlayerGoods.health.coerceIn(.0, player.maxHealth)
            healthScale = this@PlayerGoods.healthScale
            foodLevel = food

            level = this@PlayerGoods.level
            exp = experience

            allowFlight = allowFlying
            isFlying = flying

            activePotionEffects.forEach { removePotionEffect(it.type) }

            inventory.contents = items
            inventory.armorContents = armor
            enderChest.contents = enderchest
            updateInventory()

            potionEffects.forEach { player.addPotionEffect(it) }
            setDisplayName(this@PlayerGoods.displayName)
            setPlayerListName(tabName)
        }
        return true
    }

    companion object {
        /**
         * a list where you can get PlayerGoods by player
         */
        private val goods = mutableMapOf<UUID, PlayerGoods>()

        /**
         * check if a player has a vault
         */
        operator fun contains(p: Player) = p.uniqueId in goods

        operator fun plusAssign(player: Player) {
            if (player in this) {
                BedWars.INSTANCE.logger.severe(player.name + " is already having a PlayerGoods vault :|")
                return
            }
            goods[player.uniqueId] = PlayerGoods(player)

            /* prepare for arena */
            player.apply {
                player.activePotionEffects.forEach { player.removePotionEffect(it.type) }
                exp = 0f
                level = 0
                healthScale = 20.0
                health = 20.0
                foodLevel = 20
                inventory.clear()
                inventory.setArmorContents(null)
                enderChest.clear()
                gameMode = GameMode.SURVIVAL
                allowFlight = false
                isFlying = false
            }
        }

        /**
         * restore a player vault
         */
        operator fun minusAssign(player: Player) {
            val goods = goods[player.uniqueId] ?: return
            if (goods.restore()) this.goods.remove(player.uniqueId)
        }
    }
}
