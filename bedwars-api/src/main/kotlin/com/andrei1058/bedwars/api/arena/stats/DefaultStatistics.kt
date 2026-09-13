package com.andrei1058.bedwars.api.arena.stats

enum class DefaultStatistics(private val id: String, val isIncrementable: Boolean) {
    KILLS("kills", true),
    KILLS_FINAL("finalKills", true),
    DEATHS("deaths", true),
    DEATHS_FINAL("finalDeaths", true),
    BEDS_DESTROYED("bedsDestroyed", true);

    override fun toString() = id
}
