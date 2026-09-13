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
package com.andrei1058.bedwars.listeners.dropshandler

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent.PlayerKillCause
import com.andrei1058.bedwars.api.language.Language.Companion.getMsg
import com.andrei1058.bedwars.api.language.Messages
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object PlayerDrops {
    private val ORES = arrayOf(
        Material.DIAMOND,
        Material.EMERALD,
        Material.IRON_INGOT,
        Material.GOLD_INGOT
    )
    private val ItemStack.isOre get() = type in ORES
    private fun Player.dropOres(items: List<ItemStack>, location: Location = this.location) {
        items.forEach { if (it.isOre) world.dropItemNaturally(location, it) }
    }

    /**
     * if bedWars should handle drops behavior.
     *
     * @return true if event drops must be cleared.
     */
    fun handlePlayerDrops(
        arena: IArena,
        victim: Player,
        killer: Player?,
        victimsTeam: ITeam,
        killersTeam: ITeam?,
        cause: PlayerKillCause,
        inventory: List<ItemStack?>
    ): Boolean {
        if (cause.isFinalKill) {
            // if is final kill drop items at generator
            val dropsLocation = victimsTeam.killDropsLocation.toLocation(victim.world)
            victim.dropOres(victim.enderChest.filterNotNull(), dropsLocation)
            victim.enderChest.clear()
        }

        if (arena.config.getBoolean(ConfigPath.ARENA_NORMAL_DEATH_DROPS)) return false
        val inventory = inventory.filterNotNull()


        if (cause == PlayerKillCause.PLAYER_PUSH || // if died by fall damage drop items at location
            cause == PlayerKillCause.PLAYER_PUSH_FINAL ||
            killer == null ||                       // or died without an attacker drops items on the floor
            cause.isDespawnable ||                  // or killed by a ironGolem or silverFish drop on floor
            cause.isPvpLogOut                       // or is pvp log out drop at disconnect location
        ) {
            victim.dropOres(inventory)
            return true
        }


        // victim's inventory
        if (victimsTeam == killersTeam && victim == killer) return true

        // if final kill give items at kill drops location (team generator)
        if (victimsTeam.isBedDestroyed) {
            for (i in inventory) {
                if (i.type == Material.AIR) continue
                if (BedWars.nms.isArmor(i) || BedWars.nms.isBow(i) || BedWars.nms.isSword(i) || BedWars.nms.isTool(i)) continue
                if (BedWars.nms.getShopUpgradeIdentifier(i).trim().isNotEmpty()) continue
                if (arena.getTeam(killer) != null) {
                    val v = victimsTeam.killDropsLocation
                    killer.world.dropItemNaturally(Location(arena.world, v.getX(), v.getY(), v.getZ()), i)
                }
            }
            return true
        }
        // add-to-inventory feature if receiver is not respawning
        if (!arena.isPlayer(killer) || arena.isRespawning(killer)) return true
        inventory.asSequence()
            .filter { it.isOre }
            .onEach { killer.inventory.addItem(it) }
            .groupBy { it.type }
            .mapValues { it.value.sumOf { item -> item.amount } }
            .forEach { (material, amount) ->
                val (message, singular, plural) = when (material) {
                    Material.DIAMOND -> Triple(Messages.PLAYER_DIE_REWARD_DIAMOND, Messages.MEANING_DIAMOND_SINGULAR, Messages.MEANING_DIAMOND_PLURAL)
                    Material.EMERALD -> Triple(Messages.PLAYER_DIE_REWARD_EMERALD, Messages.MEANING_EMERALD_SINGULAR, Messages.MEANING_EMERALD_PLURAL)
                    Material.IRON_INGOT -> Triple(Messages.PLAYER_DIE_REWARD_IRON, Messages.MEANING_IRON_SINGULAR, Messages.MEANING_IRON_PLURAL)
                    Material.GOLD_INGOT -> Triple(Messages.PLAYER_DIE_REWARD_GOLD, Messages.MEANING_GOLD_SINGULAR, Messages.MEANING_GOLD_PLURAL)
                    else -> return@forEach
                }

                killer.sendMessage(getMsg(killer, message)
                    .replace("{meaning}", if (amount == 1) singular else plural)
                    .replace("{amount}", "$amount")
                )
            }
        return true
    }
}
