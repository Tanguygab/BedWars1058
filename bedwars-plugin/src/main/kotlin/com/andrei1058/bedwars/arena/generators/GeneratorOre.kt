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
package com.andrei1058.bedwars.arena.generators

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.generator.GeneratorType
import com.andrei1058.bedwars.api.configuration.ConfigPath
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

enum class GeneratorOre(
    material: Material,
    private val delay: List<String>,
    private val amount: List<String>,
    private val limit: List<String>,
) : GeneratorType {
    IRON(
        Material.IRON_INGOT,
        ConfigPath.GENERATOR_IRON_DELAY,
        ConfigPath.GENERATOR_IRON_AMOUNT,
        ConfigPath.GENERATOR_IRON_SPAWN_LIMIT
    ),
    GOLD(
        Material.GOLD_INGOT,
        ConfigPath.GENERATOR_GOLD_DELAY,
        ConfigPath.GENERATOR_GOLD_AMOUNT,
        ConfigPath.GENERATOR_GOLD_SPAWN_LIMIT
    ),
    DIAMOND(
        Material.DIAMOND,
        listOf(
            ConfigPath.GENERATOR_DIAMOND_TIER_I_DELAY,
            ConfigPath.GENERATOR_DIAMOND_TIER_II_DELAY,
            ConfigPath.GENERATOR_DIAMOND_TIER_III_DELAY
        ),
        listOf(
            ConfigPath.GENERATOR_DIAMOND_TIER_I_AMOUNT,
            ConfigPath.GENERATOR_DIAMOND_TIER_II_AMOUNT,
            ConfigPath.GENERATOR_DIAMOND_TIER_III_AMOUNT
        ),
        listOf(
            ConfigPath.GENERATOR_DIAMOND_TIER_I_SPAWN_LIMIT,
            ConfigPath.GENERATOR_DIAMOND_TIER_II_SPAWN_LIMIT,
            ConfigPath.GENERATOR_DIAMOND_TIER_III_SPAWN_LIMIT
        )
    ),
    EMERALD(
        Material.EMERALD,
        listOf(
            ConfigPath.GENERATOR_EMERALD_TIER_I_DELAY,
            ConfigPath.GENERATOR_EMERALD_TIER_II_DELAY,
            ConfigPath.GENERATOR_EMERALD_TIER_III_DELAY
        ),
        listOf(
            ConfigPath.GENERATOR_EMERALD_TIER_I_AMOUNT,
            ConfigPath.GENERATOR_EMERALD_TIER_II_AMOUNT,
            ConfigPath.GENERATOR_EMERALD_TIER_III_AMOUNT
        ),
        listOf(
            ConfigPath.GENERATOR_EMERALD_TIER_I_SPAWN_LIMIT,
            ConfigPath.GENERATOR_EMERALD_TIER_II_SPAWN_LIMIT,
            ConfigPath.GENERATOR_EMERALD_TIER_III_SPAWN_LIMIT
        )
    );

    constructor(material: Material, delay: String, amount: String, limit: String)
            : this(material, listOf(delay), listOf(amount), listOf(limit))

    private val item = ItemStack(material)
    private fun getArenaProperty(arena: IArena, property: String): Int {
        val config = BedWars.INSTANCE.configs.generators
        return config.getInt("${arena.group}.$property", config.getInt("Default.$property"))
    }

    override fun getItem(arena: IArena, tier: Int) = item
    override fun getDelay(arena: IArena, tier: Int): Int = getArenaProperty(arena, delay[tier-1])
    override fun getAmount(arena: IArena, tier: Int): Int = getArenaProperty(arena, amount[tier-1])
    override fun getLimit(arena: IArena, tier: Int): Int = getArenaProperty(arena, limit[tier-1])
}
