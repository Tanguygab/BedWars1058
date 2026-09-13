package com.andrei1058.bedwars

import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.entity.Despawnable
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import com.andrei1058.bedwars.api.server.VersionSupport
import com.andrei1058.bedwars.support.version.common.VersionCommon
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableAttributes
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableType
import org.bukkit.Location
import org.bukkit.entity.LivingEntity
import org.bukkit.plugin.Plugin
import java.lang.reflect.Field
import kotlin.reflect.KClass

abstract class VersionSupportCommon(plugin: Plugin, versionName: String, version: Int) : VersionSupport(plugin, versionName, version) {
    private val versionCommon = VersionCommon(this)
    abstract val despawnableTypes: Array<DespawnableProviderCommon<out LivingEntity>>

    protected fun KClass<*>.getField(field: String): Field = java.getDeclaredField(field).apply { isAccessible = true }
    protected fun KClass<*>.setField(obj: Any?, field: String, value: Any?) = getField(field).set(obj, value)

    override fun registerVersionListeners() {
        versionCommon.registerListeners(plugin)
        versionCommon.despawnableFactory.addProviders(*despawnableTypes)
    }

    override fun spawnSilverfish(loc: Location, team: ITeam, speed: Double, health: Double, despawn: Int, damage: Double) {
        val attr = DespawnableAttributes(DespawnableType.SILVERFISH, speed, health, damage, despawn)
        Despawnable(
            versionCommon.despawnableFactory.spawn(attr, loc, team),
            team,
            despawn,
            attr.type.name,
            PlayerKillEvent.PlayerKillCause.SILVERFISH_FINAL_KILL,
            PlayerKillEvent.PlayerKillCause.SILVERFISH
        )
    }

    override fun spawnIronGolem(loc: Location, team: ITeam, speed: Double, health: Double, despawn: Int) {
        val attr = DespawnableAttributes(DespawnableType.IRON_GOLEM, speed, health, 4.0, despawn)
        Despawnable(
            versionCommon.despawnableFactory.spawn(attr, loc, team),
            team,
            despawn,
            attr.type.name,
            PlayerKillEvent.PlayerKillCause.IRON_GOLEM_FINAL_KILL,
            PlayerKillEvent.PlayerKillCause.IRON_GOLEM
        )
    }
}