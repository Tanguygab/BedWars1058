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
package com.andrei1058.bedwars.support.version.v1_16_R3

import com.andrei1058.bedwars.VersionSupportCommon
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.v1_16_R3.despawnable.TeamIronGolem
import com.andrei1058.bedwars.support.version.v1_16_R3.despawnable.TeamSilverfish
import com.mojang.datafixers.util.Pair
import net.minecraft.server.v1_16_R3.*
import net.minecraft.server.v1_16_R3.Item
import org.bukkit.Color
import org.bukkit.command.SimpleCommandMap
import org.bukkit.craftbukkit.v1_16_R3.CraftServer
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftFireball
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLivingEntity
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftTNTPrimed
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack
import org.bukkit.entity.*
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector

@Suppress("ClassName")
class v1_16_R3(plugin: Plugin, name: String) : VersionSupportCommon(plugin, name, 8) {
    override val despawnableTypes = arrayOf<DespawnableProviderCommon<out LivingEntity>>(TeamIronGolem(), TeamSilverfish())
    override val commandMap: SimpleCommandMap = (plugin.server as CraftServer).commandMap

    private val Player.nms get() = (this as CraftPlayer).handle
    private fun Player.sendPackets(vararg packets: Packet<*>) {
        val connection = nms.playerConnection
        packets.forEach { connection.sendPacket(it) }
    }

    override fun hideEntity(entity: Entity, player: Player) {
        player.sendPackets(PacketPlayOutEntityDestroy(entity.entityId))
    }

    override fun setSource(tnt: TNTPrimed, owner: Player) {
        EntityTNTPrimed::class.setField(
            (tnt as CraftTNTPrimed).handle,
            "source",
            (owner as CraftLivingEntity).handle
        )
    }

    private fun getItem(stack: ItemStack?) = CraftItemStack.asNMSCopy(stack)?.item
    override fun isArmor(itemStack: ItemStack?) = getItem(itemStack).let { it is ItemArmor || it is ItemElytra }
    override fun isTool(itemStack: ItemStack?) = getItem(itemStack) is ItemTool
    override fun isSword(itemStack: ItemStack?) = getItem(itemStack) is ItemSword
    override fun isAxe(itemStack: ItemStack?) = getItem(itemStack) is ItemAxe
    override fun isBow(itemStack: ItemStack?) = getItem(itemStack) is ItemBow

    private fun getEntity(itemStack: ItemStack?) = CraftItemStack.asNMSCopy(itemStack)?.A()
    override fun isProjectile(itemStack: ItemStack?) = getEntity(itemStack) is IProjectile

    override fun getDamage(i: ItemStack?): Double {
        val nmsStack = CraftItemStack.asNMSCopy(i)
        val compound = if (nmsStack.hasTag()) nmsStack.tag else NBTTagCompound()
        return compound!!.getDouble("generic.attackDamage")
    }

    override fun voidKill(p: Player) {
        p.nms.damageEntity(DamageSource.OUT_OF_WORLD, 1000f)
    }

    override fun hideArmor(victim: Player, receiver: Player) {
        val items: MutableList<Pair<EnumItemSlot?, net.minecraft.server.v1_16_R3.ItemStack?>?> =
            ArrayList()
        items.add(
            Pair(
                EnumItemSlot.HEAD, ItemStack(
                    Item.getById(0)
                )
            )
        )
        items.add(
            Pair(
                EnumItemSlot.CHEST, ItemStack(
                    Item.getById(0)
                )
            )
        )
        items.add(
            Pair(
                EnumItemSlot.LEGS, ItemStack(
                    Item.getById(0)
                )
            )
        )
        items.add(
            Pair(
                EnumItemSlot.FEET, ItemStack(
                    Item.getById(0)
                )
            )
        )
        val packet1 = PacketPlayOutEntityEquipment(victim.entityId, items)
        val pc = (receiver as CraftPlayer).handle
        pc.playerConnection.sendPacket(packet1)
    }

    override fun showArmor(victim: Player, receiver: Player) {
        val items: MutableList<Pair<EnumItemSlot?, net.minecraft.server.v1_16_R3.ItemStack?>?> =
            ArrayList()
        items.add(
            Pair(
                EnumItemSlot.HEAD,
                CraftItemStack.asNMSCopy(victim.inventory.helmet)
            )
        )
        items.add(
            Pair(
                EnumItemSlot.CHEST,
                CraftItemStack.asNMSCopy(victim.inventory.chestplate)
            )
        )
        items.add(
            Pair(
                EnumItemSlot.LEGS,
                CraftItemStack.asNMSCopy(victim.inventory.leggings)
            )
        )
        items.add(
            Pair(
                EnumItemSlot.FEET,
                CraftItemStack.asNMSCopy(victim.inventory.boots)
            )
        )
        val packet1 = PacketPlayOutEntityEquipment(victim.entityId, items)
        val pc = (receiver as CraftPlayer).handle
        pc.playerConnection.sendPacket(packet1)
    }

    override fun registerTntWhitelist(endStoneBlast: Float, glassBlast: Float) {
        val field = BlockBase::class.getField("durability")
        for (glass in arrayOf(
            Blocks.WHITE_STAINED_GLASS,
            Blocks.ORANGE_STAINED_GLASS,
            Blocks.MAGENTA_STAINED_GLASS,
            Blocks.LIGHT_BLUE_STAINED_GLASS,
            Blocks.YELLOW_STAINED_GLASS,
            Blocks.LIME_STAINED_GLASS,
            Blocks.PINK_STAINED_GLASS,
            Blocks.GRAY_STAINED_GLASS,
            Blocks.LIGHT_GRAY_STAINED_GLASS,
            Blocks.CYAN_STAINED_GLASS,
            Blocks.PURPLE_STAINED_GLASS,
            Blocks.BLUE_STAINED_GLASS,
            Blocks.BROWN_STAINED_GLASS,
            Blocks.GREEN_STAINED_GLASS,
            Blocks.RED_STAINED_GLASS,
            Blocks.BLACK_STAINED_GLASS,
            Blocks.GLASS,
        )) {
            field.set(glass, glassBlast)
        }
        field.set(Blocks.END_STONE, endStoneBlast)
    }

    override fun getPlayerHead(player: Player, copyTagFrom: ItemStack?): ItemStack {
        val head = copyTag(copyTagFrom, ItemStack(materialPlayerHead()))

        val headMeta = head.itemMeta as SkullMeta
        headMeta::class.getField("profile").set(headMeta, (player as CraftPlayer).profile)
        head.itemMeta = headMeta
        return head
    }

    override fun sendPlayerSpawnPackets(respawned: Player?, arena: IArena?) {
        if (respawned == null) return
        if (arena == null) return
        if (!arena.isPlayer(respawned)) return

        // if method was used when the player was still in re-spawning screen
        if (arena.getRespawnSessions().containsKey(respawned)) return

        val entityPlayer = (respawned as CraftPlayer).handle
        val show = PacketPlayOutNamedEntitySpawn(entityPlayer)
        val playerVelocity = PacketPlayOutEntityVelocity(entityPlayer)
        val head = PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.yaw))

        val list: MutableList<Pair<EnumItemSlot?, net.minecraft.server.v1_16_R3.ItemStack?>?> =
            ArrayList()
        list.add(
            Pair(
                EnumItemSlot.MAINHAND,
                entityPlayer.itemInMainHand
            )
        )
        list.add(
            Pair(
                EnumItemSlot.OFFHAND,
                entityPlayer.itemInOffHand
            )
        )
        list.add(
            Pair(
                EnumItemSlot.HEAD,
                entityPlayer.inventory.armorContents[3]
            )
        )
        list.add(
            Pair(
                EnumItemSlot.CHEST,
                entityPlayer.inventory.armorContents[2]
            )
        )
        list.add(
            Pair(
                EnumItemSlot.LEGS,
                entityPlayer.inventory.armorContents[1]
            )
        )
        list.add(
            Pair(
                EnumItemSlot.FEET,
                entityPlayer.inventory.armorContents[0]
            )
        )


        for (p in arena.getPlayers()) {
            if (p == null) continue
            if (p == respawned) continue
            // if p is in re-spawning screen continue
            if (arena.getRespawnSessions().containsKey(p)) continue

            val boundTo = p.nms
            if (p.world == respawned.world) {
                if (respawned.location.distance(p.location) <= arena.getRenderDistance()) {
                    // send respawned player to regular players

                    boundTo.playerConnection.sendPacket(show)
                    boundTo.playerConnection.sendPacket(head)
                    boundTo.playerConnection.sendPacket(playerVelocity)
                    boundTo.playerConnection.sendPacket(PacketPlayOutEntityEquipment(entityPlayer.id, list))

                    // send nearby players to respawned player
                    // if the player has invisibility hide armor
                    if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                        hideArmor(p, respawned)
                    } else {
                        val show2 = PacketPlayOutNamedEntitySpawn(boundTo)
                        val playerVelocity2 = PacketPlayOutEntityVelocity(boundTo)
                        val head2 = PacketPlayOutEntityHeadRotation(boundTo, getCompressedAngle(boundTo.yaw))
                        entityPlayer.playerConnection.sendPacket(show2)
                        entityPlayer.playerConnection.sendPacket(playerVelocity2)
                        entityPlayer.playerConnection.sendPacket(head2)
                        showArmor(p, respawned)
                    }
                }
            }
        }

        for (spectator in arena.getSpectators()) {
            if (spectator == null) continue
            if (spectator == respawned) continue
            respawned.hidePlayer(plugin, spectator)
            if (spectator.world == respawned.world) {
                if (respawned.location.distance(spectator.location) <= arena.getRenderDistance()) {
                    // send respawned player to spectator

                    spectator.sendPackets(
                        show, playerVelocity,
                        PacketPlayOutEntityEquipment(entityPlayer.id, list),
                        PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.yaw))
                    )
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    override fun getMainLevel(): String = (MinecraftServer.getServer() as DedicatedServer).dedicatedServerProperties.levelName

    override fun setFireballDirection(fireball: Fireball, vector: Vector): Fireball? {
        val fb = (fireball as CraftFireball).handle
        fb.dirX = vector.getX() * 0.1
        fb.dirY = vector.getY() * 0.1
        fb.dirZ = vector.getZ() * 0.1
        return fb.bukkitEntity as Fireball?
    }

    override fun playRedStoneDot(player: Player) {
        val color = Color.RED
        val particlePacket = PacketPlayOutWorldParticles(
            ParticleParamRedstone(color.red.toFloat(), color.blue.toFloat(), color.green.toFloat(), 1f),
            true,
            player.location.x.toFloat().toDouble(),
            (player.location.y + 2.6).toFloat().toDouble(),
            player.location.z.toFloat().toDouble(),
            0f,
            0f,
            0f,
            0f,
            0
        )
        for (inWorld in player.world.players) {
            if (inWorld == player) continue
            inWorld.sendPackets(particlePacket)
        }
    }

    override fun clearArrowsFromPlayerBody(player: Player) {
        (player as CraftLivingEntity).handle.dataWatcher.set(DataWatcherObject(11, DataWatcherRegistry.b), -1)
    }

    private val ItemStack.nms get() = CraftItemStack.asNMSCopy(this)
    private val ItemStack.tag get() = nms.tag
    override fun getTag(item: ItemStack?, key: String?) = item?.tag?.getString(key)
    private fun ItemStack.setTag(tag: NBTTagCompound?): ItemStack {
        val item = nms
        item.tag = tag
        return CraftItemStack.asBukkitCopy(item)
    }
    override fun setTag(itemStack: ItemStack, key: String?, value: String?): ItemStack {
        val tag = itemStack.tag ?: NBTTagCompound()
        tag.setString(key, value)
        return itemStack.setTag(tag)
    }
    override fun copyTag(from: ItemStack?, to: ItemStack): ItemStack {
        return to.setTag(from?.tag ?: return to)
    }
}
