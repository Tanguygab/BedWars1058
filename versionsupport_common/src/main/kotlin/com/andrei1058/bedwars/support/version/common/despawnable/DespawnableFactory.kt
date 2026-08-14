package com.andrei1058.bedwars.support.version.common.despawnable

import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.server.VersionSupport
import org.bukkit.Location
import org.bukkit.entity.LivingEntity

class DespawnableFactory(private val versionSupport: VersionSupport) {
    private val providers = mutableMapOf<DespawnableType, DespawnableProviderCommon<*>>()

    fun addProviders(vararg providers: DespawnableProviderCommon<out LivingEntity>) {
        providers.forEach { this.providers[it.type] = it }
    }

    fun spawn(attr: DespawnableAttributes, location: Location, team: ITeam)
    = providers[attr.type]!!.spawn(attr, location, team, versionSupport)
}
