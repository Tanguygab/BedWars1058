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
package com.andrei1058.bedwars.upgrades.upgradeaction

import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.upgrades.UpgradeAction
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player

class EnchantItemAction(
    private val enchantment: Enchantment,
    private val amplifier: Int,
    private val type: ApplyType
) : UpgradeAction {
    override fun onBuy(player: Player, team: ITeam) {
        when (type) {
            ApplyType.ARMOR -> team.addArmorEnchantment(enchantment, amplifier)
            ApplyType.SWORD -> team.addSwordEnchantment(enchantment, amplifier)
            ApplyType.BOW -> team.addBowEnchantment(enchantment, amplifier)
        }
    }

    enum class ApplyType {
        SWORD, ARMOR, BOW
    }
}
