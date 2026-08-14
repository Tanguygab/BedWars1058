package com.andrei1058.bedwars.support.version.common.despawnable

import com.andrei1058.bedwars.api.language.Messages
import org.bukkit.entity.EntityType

enum class DespawnableType(
    val displayName: String,
    val bukkit: EntityType,
    val meleeAttack: Double,
    val randomStroll: Double
) {
    IRON_GOLEM(Messages.SHOP_UTILITY_NPC_IRON_GOLEM_NAME, EntityType.IRON_GOLEM, 1.5, 1.0),
    SILVERFISH(Messages.SHOP_UTILITY_NPC_SILVERFISH_NAME, EntityType.SILVERFISH, 1.9, 2.0)
}
