/*
 * BedWars1058 - A bed wars mini-game.
 * Copyright (C) 2021 Andrei Dascălu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Contact e-mail: andrew.dascalu@gmail.com
 */
package com.andrei1058.bedwars.support.version.v1_18_R2

import com.andrei1058.bedwars.VersionSupport_1882_Plus.getEquipmentPacket
import com.andrei1058.bedwars.VersionSupportCommon
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.v1_18_R2.despawnable.TeamIronGolem
import com.andrei1058.bedwars.support.version.v1_18_R2.despawnable.TeamSilverfish
import com.mojang.math.Vector3fa
import net.minecraft.core.particles.ParticleParamRedstone
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation
import net.minecraft.network.protocol.game.PacketPlayOutEntityVelocity
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles
import net.minecraft.server.MinecraftServer
import net.minecraft.server.dedicated.DedicatedServer
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.item.EntityTNTPrimed
import net.minecraft.world.entity.projectile.IProjectile
import net.minecraft.world.item.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBase
import org.bukkit.Color
import org.bukkit.command.SimpleCommandMap
import org.bukkit.craftbukkit.v1_18_R2.CraftServer
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftFireball
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftLivingEntity
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftTNTPrimed
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftItemStack
import org.bukkit.entity.Fireball
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector

@Suppress("ClassName")
class v1_18_R2(plugin: Plugin, name: String) : VersionSupportCommon(plugin, name, 8) {
    override val despawnableTypes = arrayOf<DespawnableProviderCommon<out LivingEntity>>(TeamIronGolem(), TeamSilverfish())
    override val commandMap: SimpleCommandMap = (plugin.server as CraftServer).commandMap

    private val Player.nms get() = (this as CraftPlayer).handle
    private fun Player.sendPackets(vararg packets: Packet<*>) {
        val connection = nms.b
        packets.forEach { connection.a(it) }
    }

    override fun setSource(tnt: TNTPrimed, owner: Player) {
        EntityTNTPrimed::class.setField(
            (tnt as CraftTNTPrimed).handle,
            "d",
            (owner as CraftLivingEntity).handle
        )
    }

    private fun getItem(stack: ItemStack?) = stack?.nms?.c()
    override fun isArmor(item: ItemStack) = getItem(item).let { it is ItemArmor || it is ItemElytra }
    override fun isTool(item: ItemStack) = getItem(item) is ItemTool
    override fun isSword(item: ItemStack) = getItem(item) is ItemSword
    override fun isAxe(item: ItemStack) = getItem(item) is ItemAxe
    override fun isBow(item: ItemStack) = getItem(item) is ItemBow

    private fun getEntity(item: ItemStack) = CraftItemStack.asNMSCopy(item).F()
    override fun isProjectile(item: ItemStack) = getEntity(item) is IProjectile

    override fun getDamage(item: ItemStack): Double {
        val nmsStack = CraftItemStack.asNMSCopy(item)
        val compound: NBTTagCompound =
            (if (nmsStack.t() != null) nmsStack.t() else NBTTagCompound())!!
        return compound.k("generic.attackDamage")
    }

    override fun voidKill(player: Player) {
        (player as CraftPlayer).handle.a(DamageSource.m, 1000f)
    }

    override fun hideArmor(victim: Player, receiver: Player) = receiver.sendPackets(getEquipmentPacket(victim.nms, full = false, hide = true))
    override fun showArmor(victim: Player, receiver: Player) = receiver.sendPackets(getEquipmentPacket(victim.nms, false))

    override fun registerTntWhitelist(endStoneBlast: Float, glassBlast: Float) {
        val field = BlockBase::class.getField("aH")

        for (glass in arrayOf<Block?>(
            Blocks.bQ,
            Blocks.dg,
            Blocks.dh,
            Blocks.di,
            Blocks.dj,
            Blocks.dk,
            Blocks.dl,
            Blocks.dm,
            Blocks.dn,  //                    Blocks.do,
            Blocks.dp,
            Blocks.dq,
            Blocks.dr,
            Blocks.ds,
            Blocks.dt,
            Blocks.du,
            Blocks.dv,
        )) {
            field.set(glass, glassBlast)
        }
        field.set(Blocks.eq, endStoneBlast)
    }

    override fun sendPlayerSpawnPackets(player: Player, arena: IArena) {
        if (!arena.isPlayer(player)) return

        // if method was used when the player was still in re-spawning screen
        if (arena.respawnSessions.containsKey(player)) return

        val entityPlayer = (player as CraftPlayer).handle
        val show = PacketPlayOutNamedEntitySpawn(entityPlayer)
        val playerVelocity = PacketPlayOutEntityVelocity(entityPlayer)
        val head = PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.bukkitYaw))
        val equipment = getEquipmentPacket(entityPlayer, true)


        for (p in arena.players) {
            if (p == player) continue
            // if p is in re-spawning screen continue
            if (arena.respawnSessions.containsKey(p)) continue

            val boundTo = (p as CraftPlayer).handle
            if (p.world != player.world || player.location.distance(p.location) > arena.renderDistance) continue
            // send respawned player to regular players

            p.sendPackets(show, head, playerVelocity, equipment)

            // send nearby players to respawned player
            // if the player has invisibility hide armor
            if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                hideArmor(p, player)
            } else {
                player.sendPackets(
                    PacketPlayOutNamedEntitySpawn(boundTo),
                    PacketPlayOutEntityVelocity(boundTo),
                    PacketPlayOutEntityHeadRotation(boundTo, getCompressedAngle(boundTo.bukkitYaw))
                )
                showArmor(p, player)
            }
        }

        for (spectator in arena.spectators) {
            if (spectator == player) continue
            player.hidePlayer(plugin, spectator)
            if (spectator.world != player.world || player.location.distance(spectator.location) > arena.renderDistance) continue
            // send respawned player to spectator

            spectator.sendPackets(
                show, playerVelocity, equipment,
                PacketPlayOutEntityHeadRotation(
                    entityPlayer,
                    getCompressedAngle(entityPlayer.bukkitYaw)
                )
            )
        }
    }

    @Suppress("DEPRECATION")
    override val mainLevel: String = (MinecraftServer.getServer() as DedicatedServer).y.a().p

    override fun setFireballDirection(fireball: Fireball, vector: Vector): Fireball {
        val fb = (fireball as CraftFireball).handle
        fb.b = vector.getX() * 0.1
        fb.c = vector.getY() * 0.1
        fb.d = vector.getZ() * 0.1
        return fb.bukkitEntity as Fireball
    }

    override fun playRedStoneDot(player: Player) {
        val color = Color.RED
        val particlePacket = PacketPlayOutWorldParticles(
            ParticleParamRedstone(
                Vector3fa(
                    color.red.toFloat(),
                    color.green.toFloat(),
                    color.blue.toFloat()
                ), 1f
            ),
            true,
            player.location.x,
            player.location.y + 2.6,
            player.location.z,
            0f,
            0f,
            0f,
            0f,
            0
        )
        for (inWorld in player.world.players) {
            if (inWorld == player) continue
            (inWorld as CraftPlayer).handle.b.a(particlePacket)
        }
    }

    private val ItemStack.nms get() = CraftItemStack.asNMSCopy(this)
    private val ItemStack.tag get() = nms.t()
    override fun getTag(item: ItemStack?, key: String?) = item?.tag?.l(key)
    private fun ItemStack.setTag(tag: NBTTagCompound?): ItemStack {
        val item = nms
        item.c(tag)
        return CraftItemStack.asBukkitCopy(item)
    }
    override fun setTag(item: ItemStack, key: String?, value: String?): ItemStack {
        val tag = item.tag ?: NBTTagCompound()
        tag.a(key, value)
        return item.setTag(tag)
    }
    override fun copyTag(from: ItemStack?, to: ItemStack): ItemStack {
        return to.setTag(from?.tag ?: return to)
    }
}
