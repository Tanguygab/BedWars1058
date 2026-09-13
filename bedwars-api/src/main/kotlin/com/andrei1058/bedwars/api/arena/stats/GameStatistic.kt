package com.andrei1058.bedwars.api.arena.stats

import com.andrei1058.bedwars.api.language.Language

/**
 * Game statistic.
 * @param <T> statistic type. We usually work with integers.
</T> */
interface GameStatistic<T> : Comparable<GameStatistic<T>> {
    /**
     * Current value.
     */
    val value: T

    /**
     * Value displayed in tops etc.
     * @param language - message receiver.
     */
    fun getDisplayValue(language: Language): String

    /**
     * Comparison for tops.
     * @param other the object to be compared.
     */
    override fun compareTo(other: GameStatistic<T>): Int
}
