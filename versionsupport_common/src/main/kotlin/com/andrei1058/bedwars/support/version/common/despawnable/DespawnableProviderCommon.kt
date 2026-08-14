package com.andrei1058.bedwars.support.version.common.despawnable

import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.VersionSupport
import org.bukkit.Location
import org.bukkit.entity.LivingEntity

abstract class DespawnableProviderCommon<T: LivingEntity>(val type: DespawnableType) {

    fun getDisplayName(attr: DespawnableAttributes, team: ITeam): String {
        val lang = Language.getDefaultLanguage()
        return lang.m(type.displayName)
            .replace("{despawn}", attr.despawnSeconds.toString())
            .replace("{health}", (lang.m(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH) + " ").repeat(10))
            .replace("{TeamColor}", team.getColor().chat().toString())
    }

    abstract fun spawn(attr: DespawnableAttributes, location: Location, team: ITeam, api: VersionSupport): T

    fun applyDefaultSettings(
        bukkitEntity: LivingEntity, attr: DespawnableAttributes,
        team: ITeam
    ) {
        bukkitEntity.removeWhenFarAway = false
        bukkitEntity.isPersistent = true
        bukkitEntity.isCustomNameVisible = true
        bukkitEntity.customName = getDisplayName(attr, team)

        applyDefaultNMSSettings(bukkitEntity, attr)
    }
    abstract fun applyDefaultNMSSettings(bukkitEntity: LivingEntity, attr: DespawnableAttributes)
}