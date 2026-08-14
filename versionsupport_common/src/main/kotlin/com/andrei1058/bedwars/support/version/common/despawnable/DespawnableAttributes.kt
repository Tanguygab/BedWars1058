package com.andrei1058.bedwars.support.version.common.despawnable

data class DespawnableAttributes(
    val type: DespawnableType,
    val speed: Double,
    val health: Double,
    val damage: Double,
    val despawnSeconds: Int
)
