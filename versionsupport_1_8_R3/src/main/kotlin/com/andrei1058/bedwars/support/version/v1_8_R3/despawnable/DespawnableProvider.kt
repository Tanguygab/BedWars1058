package com.andrei1058.bedwars.support.version.v1_8_R3.despawnable

import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.server.VersionSupport
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableAttributes
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableType
import net.minecraft.server.v1_8_R3.EntityCreature
import net.minecraft.server.v1_8_R3.EntityHuman
import net.minecraft.server.v1_8_R3.EntityLiving
import net.minecraft.server.v1_8_R3.GenericAttributes
import net.minecraft.server.v1_8_R3.PathfinderGoalFloat
import net.minecraft.server.v1_8_R3.PathfinderGoalHurtByTarget
import net.minecraft.server.v1_8_R3.PathfinderGoalMeleeAttack
import net.minecraft.server.v1_8_R3.PathfinderGoalNearestAttackableTarget
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomLookaround
import net.minecraft.server.v1_8_R3.PathfinderGoalRandomStroll
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList
import org.bukkit.entity.LivingEntity

abstract class DespawnableProvider<T: LivingEntity>(type: DespawnableType): DespawnableProviderCommon<T>(type) {

    private fun getGoalSelector(entityLiving: EntityCreature) = entityLiving.goalSelector
    private fun getTargetSelector(entityLiving: EntityCreature) = entityLiving.targetSelector

    private fun clearSelectors(entityLiving: EntityCreature) {
        try {
            PathfinderGoalSelector::class.java.getDeclaredField("b").apply {
                setAccessible(true)
                set(getGoalSelector(entityLiving), UnsafeList<Any?>())
                set(getTargetSelector(entityLiving), UnsafeList<Any?>())
            }
            PathfinderGoalSelector::class.java.getDeclaredField("c").apply {
                setAccessible(true)
                set(getGoalSelector(entityLiving), UnsafeList<Any?>())
                set(getTargetSelector(entityLiving), UnsafeList<Any?>())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getTargetGoal(entity: EntityCreature, team: ITeam, api: VersionSupport)
    = PathfinderGoalNearestAttackableTarget(entity, EntityLiving::class.java, 20, true, false) {
        if (it == null) return@PathfinderGoalNearestAttackableTarget false
        val uuid = it.bukkitEntity.uniqueId
        it is EntityHuman && !(
            it.bukkitEntity.isDead ||
            team.wasMember(uuid) ||
            team.arena.isRespawning(uuid) ||
            team.arena.isSpectator(uuid)
        ) || api.despawnables[it.bukkitEntity.uniqueId]?.team !== team
    }

    override fun applyDefaultNMSSettings(bukkitEntity: LivingEntity, attr: DespawnableAttributes) {
        val entity = (bukkitEntity as CraftEntity).handle as EntityCreature
        entity.getAttributeInstance(GenericAttributes.maxHealth)!!.value = attr.health
        entity.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED)!!.value = attr.speed
        entity.getAttributeInstance(GenericAttributes.ATTACK_DAMAGE)!!.value = attr.damage
    }

    override fun spawn(
        attr: DespawnableAttributes,
        location: Location,
        team: ITeam,
        api: VersionSupport
    ): T {
        @Suppress("UNCHECKED_CAST")
        val bukkitEntity = location.world!!.spawnEntity(location, type.bukkit) as T
        applyDefaultSettings(bukkitEntity, attr, team)

        val entity = (bukkitEntity as CraftEntity).handle as EntityCreature

        clearSelectors(entity)
        getGoalSelector(entity).apply {
            a(1, PathfinderGoalFloat(entity))
            a(2, PathfinderGoalMeleeAttack(entity, type.meleeAttack, false))
            a(3, PathfinderGoalRandomStroll(entity, type.randomStroll))
            a(4, PathfinderGoalRandomLookaround(entity))
        }
        getTargetSelector(entity).apply {
            a(1, PathfinderGoalHurtByTarget(entity, true))
            a(2, getTargetGoal(entity, team, api))
        }

        return bukkitEntity
    }
}
