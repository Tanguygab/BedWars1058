package com.andrei1058.bedwars.api.arena.stats

import com.andrei1058.bedwars.api.language.Language
import org.bukkit.plugin.Plugin

interface GameStatisticProvider<T : GameStatistic<*>> {
    /**
     * Unique statistic identifier.
     */
    val identifier: String

    /**
     * Plugin provider.
     * @return statistic owner.
     */
    val owner: Plugin

    /**
     * Default value used when initializing game stats.
     */
    val default: T

    /**
     * Display value for undetermined values.
     * @param language desired translation.
     */
    fun getVoidReplacement(language: Language): String
}
