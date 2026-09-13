package com.andrei1058.bedwars.api.arena.generator

import com.andrei1058.bedwars.api.arena.IArena
import org.bukkit.inventory.ItemStack

interface GeneratorType {
    fun getItem(arena: IArena, tier: Int = 1): ItemStack
    fun getDelay(arena: IArena, tier: Int = 1): Int
    fun getAmount(arena: IArena, tier: Int = 1): Int
    fun getLimit(arena: IArena, tier: Int = 1): Int
}