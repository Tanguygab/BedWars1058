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
package com.andrei1058.bedwars.support.version.v1_17_R1

import com.andrei1058.bedwars.VersionSupportCommon
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.v1_17_R1.despawnable.TeamIronGolem
import com.andrei1058.bedwars.support.version.v1_17_R1.despawnable.TeamSilverfish
import com.mojang.datafixers.util.Pair
import com.mojang.math.Vector3fa
import net.minecraft.core.particles.ParticleParamRedstone
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.*
import net.minecraft.network.syncher.DataWatcherObject
import net.minecraft.network.syncher.DataWatcherRegistry
import net.minecraft.server.MinecraftServer
import net.minecraft.server.dedicated.DedicatedServer
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EnumItemSlot
import net.minecraft.world.entity.item.EntityTNTPrimed
import net.minecraft.world.entity.projectile.IProjectile
import net.minecraft.world.item.*
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBase
import org.bukkit.Color
import org.bukkit.command.SimpleCommandMap
import org.bukkit.craftbukkit.v1_17_R1.CraftServer
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftFireball
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftTNTPrimed
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack
import org.bukkit.entity.*
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector

@Suppress("ClassName")
@Deprecated("")
class v1_17_R1(plugin: Plugin, name: String) : VersionSupportCommon(plugin, name, 8) {
    override val despawnableTypes = arrayOf<DespawnableProviderCommon<out LivingEntity>>(TeamIronGolem(), TeamSilverfish())
    override val commandMap: SimpleCommandMap = (plugin.server as CraftServer).commandMap

    private fun Player.sendPackets(vararg packets: Packet<*>) {
        val connection = (this as CraftPlayer).handle.b
        packets.forEach { connection.sendPacket(it) }
    }

    override fun hideEntity(entity: Entity, player: Player) {
        player.sendPackets(PacketPlayOutEntityDestroy(entity.entityId))
    }

    override fun setSource(tnt: TNTPrimed, owner: Player) {
        EntityTNTPrimed::class.setField(
            (tnt as CraftTNTPrimed).handle,
            "d",
            (owner as CraftLivingEntity).handle
        )
    }

    private fun getItem(stack: ItemStack) = CraftItemStack.asNMSCopy(stack).item
    override fun isArmor(item: ItemStack) = getItem(item).let { it is ItemArmor || it is ItemElytra }
    override fun isTool(item: ItemStack) = getItem(item) is ItemTool
    override fun isSword(item: ItemStack) = getItem(item) is ItemSword
    override fun isAxe(item: ItemStack) = getItem(item) is ItemAxe
    override fun isBow(item: ItemStack) = getItem(item) is ItemBow

    private fun getEntity(item: ItemStack) = CraftItemStack.asNMSCopy(item).E()
    override fun isProjectile(item: ItemStack) = getEntity(item) is IProjectile

    override fun getDamage(item: ItemStack): Double {
        val nmsStack = CraftItemStack.asNMSCopy(item)
        val compound = if (nmsStack.hasTag()) nmsStack.tag else NBTTagCompound()
        return compound!!.getDouble("generic.attackDamage")
    }

    override fun voidKill(player: Player) {
        (player as CraftPlayer).handle.damageEntity(DamageSource.m, 1000f)
    }

    override fun hideArmor(victim: Player, receiver: Player) {
        val items: MutableList<Pair<EnumItemSlot?, net.minecraft.world.item.ItemStack?>?> =
            ArrayList()
        items.add(
            Pair(
                EnumItemSlot.f, ItemStack(
                    Item.getById(0)
                )
            )
        )
        items.add(
            Pair(
                EnumItemSlot.e, ItemStack(
                    Item.getById(0)
                )
            )
        )
        items.add(
            Pair(
                EnumItemSlot.d, ItemStack(
                    Item.getById(0)
                )
            )
        )
        items.add(
            Pair(
                EnumItemSlot.c, ItemStack(
                    Item.getById(0)
                )
            )
        )
        receiver.sendPackets(PacketPlayOutEntityEquipment(victim.entityId, items))
    }

    override fun showArmor(victim: Player, receiver: Player) {
        val items: MutableList<Pair<EnumItemSlot?, net.minecraft.world.item.ItemStack?>?> =
            ArrayList()
        items.add(
            Pair(
                EnumItemSlot.f,
                CraftItemStack.asNMSCopy(victim.inventory.helmet)
            )
        )
        items.add(
            Pair(
                EnumItemSlot.e,
                CraftItemStack.asNMSCopy(victim.inventory.chestplate)
            )
        )
        items.add(
            Pair(
                EnumItemSlot.d,
                CraftItemStack.asNMSCopy(victim.inventory.leggings)
            )
        )
        items.add(
            Pair(
                EnumItemSlot.c,
                CraftItemStack.asNMSCopy(victim.inventory.boots)
            )
        )
        receiver.sendPackets(PacketPlayOutEntityEquipment(victim.entityId, items))
    }

    override fun registerTntWhitelist(endStoneBlast: Float, glassBlast: Float) {
        val field = BlockBase::class.getField("aI")

        arrayOf(
            Blocks.au, Blocks.dg, Blocks.dh, Blocks.di,
            Blocks.dj, Blocks.dk, Blocks.dl, Blocks.dm,
            Blocks.dn, Blocks.do_, Blocks.dp, Blocks.dq,
            Blocks.dr, Blocks.ds, Blocks.dt, Blocks.du,
            Blocks.dv,
        ).forEach { field.set(it, glassBlast) }
        field.set(Blocks.eq, endStoneBlast)
    }

    override fun getPlayerHead(player: Player, copyTagFrom: ItemStack?): ItemStack {
        val head = copyTag(copyTagFrom, ItemStack(materialPlayerHead()))

        val meta = head.itemMeta as SkullMeta
        meta.owningPlayer = player
        head.itemMeta = meta
        return head
    }

    override fun sendPlayerSpawnPackets(player: Player, arena: IArena) {
        if (!arena.isPlayer(player)) return

        // if method was used when the player was still in re-spawning screen
        if (arena.respawnSessions.containsKey(player)) return

        val entityPlayer = (player as CraftPlayer).handle
        val show = PacketPlayOutNamedEntitySpawn(entityPlayer)
        val playerVelocity = PacketPlayOutEntityVelocity(entityPlayer)
        val head = PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.bukkitYaw))

        val list: MutableList<Pair<EnumItemSlot?, net.minecraft.world.item.ItemStack?>?> =
            ArrayList()
        list.add(
            Pair(
                EnumItemSlot.a,
                entityPlayer.itemInMainHand
            )
        )
        list.add(
            Pair(
                EnumItemSlot.b,
                entityPlayer.itemInOffHand
            )
        )
        list.add(
            Pair(
                EnumItemSlot.f,
                entityPlayer.inventory.armorContents[3]
            )
        )
        list.add(
            Pair(
                EnumItemSlot.e,
                entityPlayer.inventory.armorContents[2]
            )
        )
        list.add(
            Pair(
                EnumItemSlot.d,
                entityPlayer.inventory.armorContents[1]
            )
        )
        list.add(
            Pair(
                EnumItemSlot.c,
                entityPlayer.inventory.armorContents[0]
            )
        )


        for (p in arena.players) {
            if (p == player) continue
            // if p is in re-spawning screen continue
            if (arena.respawnSessions.containsKey(p)) continue

            val boundTo = (p as CraftPlayer).handle
            if (p.world == player.world) {
                if (player.location.distance(p.location) <= arena.renderDistance) {
                    // send respawned player to regular players

                    boundTo.b.sendPacket(show)
                    boundTo.b.sendPacket(head)
                    boundTo.b.sendPacket(playerVelocity)
                    boundTo.b.sendPacket(PacketPlayOutEntityEquipment(entityPlayer.id, list))

                    // send nearby players to respawned player
                    // if the player has invisibility hide armor
                    if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                        hideArmor(p, player)
                    } else {
                        val show2 = PacketPlayOutNamedEntitySpawn(boundTo)
                        val playerVelocity2 = PacketPlayOutEntityVelocity(boundTo)
                        val head2 = PacketPlayOutEntityHeadRotation(boundTo, getCompressedAngle(boundTo.bukkitYaw))
                        entityPlayer.b.sendPacket(show2)
                        entityPlayer.b.sendPacket(playerVelocity2)
                        entityPlayer.b.sendPacket(head2)
                        showArmor(p, player)
                    }
                }
            }
        }

        for (spectator in arena.spectators) {
            if (spectator == player) continue
            val boundTo = (spectator as CraftPlayer).handle
            player.hidePlayer(plugin, spectator)
            if (spectator.world == player.world) {
                if (player.location.distance(spectator.location) <= arena.renderDistance) {
                    // send respawned player to spectator

                    boundTo.b.sendPacket(show)
                    boundTo.b.sendPacket(playerVelocity)
                    boundTo.b.sendPacket(PacketPlayOutEntityEquipment(entityPlayer.id, list))
                    boundTo.b.sendPacket(
                        PacketPlayOutEntityHeadRotation(
                            entityPlayer,
                            getCompressedAngle(entityPlayer.bukkitYaw)
                        )
                    )
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    override val mainLevel: String = (MinecraftServer.getServer() as DedicatedServer).world

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
            (inWorld as CraftPlayer).handle.b.sendPacket(particlePacket)
        }
    }

    override fun clearArrowsFromPlayerBody(player: Player) {
        (player as CraftLivingEntity).handle.dataWatcher.set(DataWatcherObject(12, DataWatcherRegistry.b), -1)
    }

    private val ItemStack.nms get() = CraftItemStack.asNMSCopy(this)
    private val ItemStack.tag get() = nms.tag
    override fun getTag(item: ItemStack?, key: String?) = item?.tag?.getString(key)
    private fun ItemStack.setTag(tag: NBTTagCompound?): ItemStack {
        val item = nms
        item.tag = tag
        return CraftItemStack.asBukkitCopy(item)
    }
    override fun setTag(item: ItemStack, key: String?, value: String?): ItemStack {
        val tag = item.tag ?: NBTTagCompound()
        tag.setString(key, value)
        return item.setTag(tag)
    }
    override fun copyTag(from: ItemStack?, to: ItemStack): ItemStack {
        return to.setTag(from?.tag ?: return to)
    }
}
