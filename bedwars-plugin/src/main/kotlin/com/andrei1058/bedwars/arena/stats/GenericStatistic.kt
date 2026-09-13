package com.andrei1058.bedwars.arena.stats

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.stats.GameStatistic
import com.andrei1058.bedwars.api.arena.stats.GameStatisticProvider
import com.andrei1058.bedwars.api.arena.stats.Incrementable
import com.andrei1058.bedwars.api.language.Language

abstract class GenericStatistic : GameStatisticProvider<GenericStatistic.Value> {
    override val default = Value()

    override fun getVoidReplacement(language: Language) = "0"

    override val owner get() = BedWars.plugin

    class Value : GameStatistic<Int>, Incrementable, Comparable<GameStatistic<Int>> {
        override var value = 0
            private set

        override fun getDisplayValue(language: Language) = "$value"

        override fun compareTo(other: GameStatistic<Int>) = value.compareTo(other.value)

        override fun increment() {
            ++value
        }
    }
}
