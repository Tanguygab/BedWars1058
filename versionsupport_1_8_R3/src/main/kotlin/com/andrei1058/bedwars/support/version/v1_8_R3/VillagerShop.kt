package com.andrei1058.bedwars.support.version.v1_8_R3

import net.minecraft.server.v1_8_R3.DamageSource
import net.minecraft.server.v1_8_R3.Entity
import net.minecraft.server.v1_8_R3.EntityVillager
import net.minecraft.server.v1_8_R3.GenericAttributes
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector
import org.bukkit.Location
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld
import org.bukkit.craftbukkit.v1_8_R3.util.UnsafeList
import org.bukkit.event.entity.CreatureSpawnEvent

internal class VillagerShop(loc: Location): EntityVillager((loc.world as CraftWorld).handle) {
    init {
        try {
            PathfinderGoalSelector::class.java.getDeclaredField("b").apply {
                isAccessible = true
                set(goalSelector, UnsafeList<Any?>())
                set(targetSelector, UnsafeList<Any?>())
            }

            PathfinderGoalSelector::class.java.getDeclaredField("c").apply {
                isAccessible = true
                set(goalSelector, UnsafeList<Any?>())
                set(targetSelector, UnsafeList<Any?>())
            }
        } catch (_: Exception) {}
        setLocation(loc.x, loc.y, loc.z, loc.yaw, loc.pitch)
        setPositionRotation(loc.x, loc.y, loc.z, loc.yaw, loc.pitch)
        world.addEntity(this, CreatureSpawnEvent.SpawnReason.CUSTOM)
        persistent = true
    }

    override fun move(d0: Double, d1: Double, d2: Double) {}
    override fun collide(entity: Entity?) {}
    override fun damageEntity(damagesource: DamageSource?, f: Float) = false
    override fun g(d0: Double, d1: Double, d2: Double) {}
    override fun makeSound(s: String?, f: Float, f1: Float) {}

    override fun initAttributes() {
        super.initAttributes()
        getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).value = 0.0
    }
}