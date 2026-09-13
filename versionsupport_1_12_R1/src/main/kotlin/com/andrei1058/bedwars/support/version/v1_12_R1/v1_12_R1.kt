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
package com.andrei1058.bedwars.support.version.v1_12_R1

import com.andrei1058.bedwars.VersionSupportCommon
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.v1_12_R1.despawnable.TeamIronGolem
import com.andrei1058.bedwars.support.version.v1_12_R1.despawnable.TeamSilverfish
import net.minecraft.server.v1_12_R1.*
import net.minecraft.server.v1_12_R1.Item
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.block.Bed
import org.bukkit.block.BlockState
import org.bukkit.command.SimpleCommandMap
import org.bukkit.craftbukkit.v1_12_R1.CraftServer
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftFireball
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftLivingEntity
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftTNTPrimed
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack
import org.bukkit.entity.*
import org.bukkit.entity.Entity
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.material.Sign
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector

@Suppress("ClassName")
class v1_12_R1(plugin: Plugin, name: String) : VersionSupportCommon(plugin, name, 5) {
    override val despawnableTypes = arrayOf<DespawnableProviderCommon<out LivingEntity>>(TeamIronGolem(), TeamSilverfish())
    override val commandMap: SimpleCommandMap = (plugin.server as CraftServer).commandMap

    private fun Player.sendPackets(vararg packets: Packet<*>) {
        val connection = (this as CraftPlayer).handle.playerConnection
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

    private fun getItem(stack: ItemStack) = stack.nms.item
    override fun isArmor(item: ItemStack) = getItem(item).let { it is ItemArmor || it is ItemElytra }
    override fun isTool(item: ItemStack) = getItem(item) is ItemTool
    override fun isSword(item: ItemStack) = getItem(item) is ItemSword
    override fun isAxe(item: ItemStack) = getItem(item) is ItemAxe
    override fun isBow(item: ItemStack) = getItem(item) is ItemBow
    override fun isProjectile(item: ItemStack) = getItem(item) is IProjectile

    override fun getDamage(item: ItemStack) = item.nms.tag?.getDouble("generic.attackDamage") ?: 0.0

    override fun voidKill(player: Player) {
        (player as CraftPlayer).handle.damageEntity(DamageSource.OUT_OF_WORLD, 1000f)
    }

    override fun hideArmor(victim: Player, receiver: Player) {
        val air = ItemStack(Item.getById(0))
        receiver.sendPackets(
            *arrayOf(EnumItemSlot.HEAD, EnumItemSlot.CHEST, EnumItemSlot.LEGS, EnumItemSlot.FEET)
                .map { PacketPlayOutEntityEquipment(victim.entityId, it, air) }
                .toTypedArray()
        )
    }

    override fun showArmor(victim: Player, receiver: Player) {
        receiver.sendPackets(
            *mapOf(
                EnumItemSlot.HEAD to victim.inventory.helmet.nms,
                EnumItemSlot.CHEST to victim.inventory.chestplate.nms,
                EnumItemSlot.LEGS to victim.inventory.leggings.nms,
                EnumItemSlot.FEET to victim.inventory.boots.nms
            ).map { PacketPlayOutEntityEquipment(victim.entityId, it.key, it.value) }.toTypedArray()
        )
    }

    override fun colorBed(team: ITeam) {
        for (x in -1..1) {
            for (z in -1..1) {
                val bed = team.bed.clone().add(x.toDouble(), 0.0, z.toDouble()).block.state
                if (bed is Bed) {
                    bed.color = team.color.dye
                    bed.update()
                }
            }
        }
    }

    override fun registerTntWhitelist(endStoneBlast: Float, glassBlast: Float) {
        Block::class.getField("durability").apply {
            set(Block.getByName("glass"), glassBlast)
            set(Block.getByName("stained_glass"), glassBlast)
            set(Block.getByName("end_stone"), endStoneBlast)
        }
    }

    @Suppress("DEPRECATION")
    override fun setBlockTeamColor(block: org.bukkit.block.Block, teamColor: TeamColor) {
        block.data = teamColor.byte
    }

    override fun colourItem(item: ItemStack, team: ITeam): ItemStack {
        val type = when (item.type) {
            Material.WOOL, Material.STAINED_CLAY, Material.STAINED_GLASS -> item.type
            Material.GLASS -> Material.STAINED_GLASS
            else -> return item
        }
        return ItemStack(type, item.amount, team.color.byte.toShort())
    }

    override fun createItemStack(material: String, amount: Int, data: Short) = try {
        ItemStack(Material.valueOf(material), amount, data)
    } catch (_: Exception) {
        plugin.logger.severe("$material is not a valid $version material!")
        ItemStack(Material.BEDROCK)
    }

    override fun isPlayerHead(material: Material, data: Int) = material == Material.SKULL_ITEM && data == 3
    override fun materialFireball() = Material.FIREBALL
    override fun materialPlayerHead() = Material.SKULL_ITEM
    override fun materialSnowball() =Material.SNOW_BALL
    override fun materialGoldenHelmet() = Material.GOLD_HELMET
    override fun materialGoldenChestPlate() = Material.GOLD_CHESTPLATE
    override fun materialGoldenLeggings() = Material.GOLD_LEGGINGS
    override fun materialNetheriteHelmet() = Material.DIAMOND_HELMET //Netherite doesn't exist
    override fun materialNetheriteChestPlate() = Material.DIAMOND_CHESTPLATE //Netherite doesn't exist
    override fun materialNetheriteLeggings() = Material.DIAMOND_LEGGINGS //Netherite doesn't exist
    override fun materialCake() = Material.CAKE_BLOCK
    override fun materialCraftingTable() = Material.WORKBENCH
    override fun materialEnchantingTable() = Material.ENCHANTMENT_TABLE
    override fun isBed(material: Material) = material == Material.BED_BLOCK || material == Material.BED
    override fun woolMaterial() = Material.WOOL

    @Suppress("DEPRECATION")
    override fun itemStackDataCompare(item: ItemStack, data: Short) = item.data.data.toShort() == data

    @Suppress("DEPRECATION")
    override fun setJoinSignBackgroundBlockData(state: BlockState, data: Byte) {
        state.block.getRelative((state.data as Sign).attachedFace).setData(data, true)
    }

    override fun getPlayerHead(player: Player, copyTagFrom: ItemStack?): ItemStack {
        val head = copyTag(copyTagFrom, ItemStack(Material.SKULL_ITEM, 1, 3.toShort()))

        val meta = head.itemMeta as SkullMeta
        meta::class.setField(meta, "profile", (player as CraftPlayer).profile)
        head.itemMeta = meta
        return head
    }

    override fun sendPlayerSpawnPackets(player: Player, arena: IArena) {
        if (!arena.isPlayer(player)) return

        // if method was used when the player was still in re-spawning screen
        if (arena.isRespawning(player)) return

        val entityPlayer = (player as CraftPlayer).handle
        val show = PacketPlayOutNamedEntitySpawn(entityPlayer)
        val playerVelocity = PacketPlayOutEntityVelocity(entityPlayer)
        val head = PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.yaw))

        val hand1 = PacketPlayOutEntityEquipment(entityPlayer.id, EnumItemSlot.MAINHAND, entityPlayer.inventory.itemInHand)
        val hand2 = PacketPlayOutEntityEquipment(entityPlayer.id, EnumItemSlot.OFFHAND, entityPlayer.itemInOffHand)
        val armor = entityPlayer.inventory.armorContents
        val armorPackets = arrayOf(
            EnumItemSlot.FEET,
            EnumItemSlot.LEGS,
            EnumItemSlot.CHEST,
            EnumItemSlot.HEAD
        ).mapIndexed { index, slot ->
            PacketPlayOutEntityEquipment(entityPlayer.id, slot, armor[index])
        }.toTypedArray()


        for (p in arena.players) {
            if (p == player) continue
            // if p is in re-spawning screen continue
            if (arena.isRespawning(p)) continue

            val boundTo = (p as CraftPlayer).handle
            if (p.world == player.world) {
                if (player.location.distance(p.location) <= arena.renderDistance) {
                    // send respawned player to regular players

                    p.sendPackets(show, playerVelocity, hand1, *armorPackets, hand2, head)

                    // send nearby players to respawned player
                    // if the player has invisibility hide armor
                    if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                        hideArmor(p, player)
                    } else {
                        player.sendPackets(
                            PacketPlayOutNamedEntitySpawn(boundTo),
                            PacketPlayOutEntityVelocity(boundTo),
                            PacketPlayOutEntityHeadRotation(boundTo, getCompressedAngle(boundTo.yaw))
                        )
                        showArmor(p, player)
                    }
                }
            }
        }

        for (spectator in arena.spectators) {
            if (spectator == player) continue
            player.hidePlayer(plugin, spectator)
            if (spectator.world != player.world || player.location.distance(spectator.location) > arena.renderDistance) continue
            // send respawned player to spectator

            spectator.sendPackets(
                show,
                playerVelocity,
                PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.yaw)),
                hand1, *armorPackets, hand2
            )
        }
    }

    override fun getInventoryName(e: InventoryEvent): String = e.inventory.name

    @Suppress("DEPRECATION")
    override val mainLevel: String = (MinecraftServer.getServer() as DedicatedServer).propertyManager.properties.getProperty("level-name")

    override fun setJoinSignBackground(state: BlockState, material: Material) {
        state.location.block.getRelative((state.data as Sign).attachedFace).type = material
    }

    override fun setFireballDirection(fireball: Fireball, vector: Vector): Fireball {
        val fb = (fireball as CraftFireball).handle
        fb.dirX = vector.getX() * 0.1
        fb.dirY = vector.getY() * 0.1
        fb.dirZ = vector.getZ() * 0.1
        return fb.getBukkitEntity() as Fireball
    }

    override fun playRedStoneDot(player: Player) {
        val color = Color.RED
        val particlePacket = PacketPlayOutWorldParticles(
            EnumParticle.REDSTONE,
            true,
            player.location.x.toFloat(),
            (player.location.y + 2.6).toFloat(),
            player.location.z.toFloat(),
            color.red.toFloat(),
            color.red.toFloat(),
            color.red.toFloat(),
            0f,
            0
        )
        for (inWorld in player.world.players) {
            if (inWorld == player) continue
            (inWorld as CraftPlayer).handle.playerConnection.sendPacket(particlePacket)
        }
    }

    override fun clearArrowsFromPlayerBody(player: Player) {
        (player as CraftLivingEntity).handle.dataWatcher.set(DataWatcherObject(10, DataWatcherRegistry.b), -1)
    }

    override fun placeTowerBlocks(b: org.bukkit.block.Block, a: IArena, color: TeamColor, x: Int, y: Int, z: Int) {
        super.placeTowerBlocks(b, a, color, x, y ,z)
        setBlockTeamColor(b.getRelative(x, y, z), color)
    }

    @Suppress("DEPRECATION")
    override fun placeLadder(block: org.bukkit.block.Block, x: Int, y: Int, z: Int, arena: IArena, ladderData: Int) {
        block.getRelative(x, y, z).type = Material.LADDER
        block.getRelative(x, y, z).data = ladderData.toByte()
        arena.addPlacedBlock(block.getRelative(x, y, z))
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
