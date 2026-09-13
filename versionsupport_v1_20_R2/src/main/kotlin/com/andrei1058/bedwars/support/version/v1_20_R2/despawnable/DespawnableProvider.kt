package com.andrei1058.bedwars.support.version.v1_20_R2.despawnable

import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.server.VersionSupport
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableAttributes
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableType
import net.minecraft.world.entity.EntityCreature
import net.minecraft.world.entity.EntityLiving
import net.minecraft.world.entity.ai.attributes.GenericAttributes
import net.minecraft.world.entity.ai.goal.PathfinderGoalFloat
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStroll
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget
import net.minecraft.world.entity.player.EntityHuman
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftEntity
import org.bukkit.entity.LivingEntity

abstract class DespawnableProvider<T: LivingEntity>(type: DespawnableType): DespawnableProviderCommon<T>(type) {

    private fun getGoalSelector(entityLiving: EntityCreature) = entityLiving.bO
    private fun getTargetSelector(entityLiving: EntityCreature) = entityLiving.bP

    private fun clearSelectors(entityLiving: EntityCreature) {
        getGoalSelector(entityLiving).b().clear()
        getTargetSelector(entityLiving).b().clear()
    }

    private fun getTargetGoal(entity: EntityCreature, team: ITeam, api: VersionSupport)
    = PathfinderGoalNearestAttackableTarget(entity, EntityLiving::class.java, 20, true, false) {
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
        entity.a(GenericAttributes.a)!!.a(attr.health)
        entity.a(GenericAttributes.d)!!.a(attr.speed)
        entity.a(GenericAttributes.f)!!.a(attr.damage)
    }

    override fun spawn(
        attr: DespawnableAttributes,
        location: Location,
        team: ITeam,
        api: VersionSupport
    ): T {
        @Suppress("UNCHECKED_CAST")
        val bukkitEntity = location.getWorld()!!.spawnEntity(location, type.bukkit) as T
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
            a(1, PathfinderGoalHurtByTarget(entity))
            a(2, getTargetGoal(entity, team, api))
        }

        return bukkitEntity
    }
}
