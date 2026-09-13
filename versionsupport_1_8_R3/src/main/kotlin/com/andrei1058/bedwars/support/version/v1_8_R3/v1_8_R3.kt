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
package com.andrei1058.bedwars.support.version.v1_8_R3

import com.andrei1058.bedwars.VersionSupportCommon
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.v1_8_R3.despawnable.TeamIronGolem
import com.andrei1058.bedwars.support.version.v1_8_R3.despawnable.TeamSilverfish
import net.minecraft.server.v1_8_R3.*
import org.bukkit.Color
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.BlockState
import org.bukkit.command.SimpleCommandMap
import org.bukkit.craftbukkit.v1_8_R3.CraftServer
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftFireball
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftLivingEntity
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftTNTPrimed
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Fireball
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.material.Sign
import org.bukkit.plugin.Plugin
import org.bukkit.potion.Potion
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector

@Deprecated("")
@Suppress("ClassName")
class v1_8_R3(plugin: Plugin, name: String) : VersionSupportCommon(plugin, name, 0) {
    override val despawnableTypes = arrayOf<DespawnableProviderCommon<out LivingEntity>>(TeamIronGolem(), TeamSilverfish())
    override val commandMap: SimpleCommandMap = (plugin.server as CraftServer).commandMap

    private fun Player.sendPackets(vararg packets: Packet<*>) {
        val connection = (this as CraftPlayer).handle.playerConnection
        packets.forEach { connection.sendPacket(it) }
    }

    private fun String.toComponent() = IChatBaseComponent.ChatSerializer.a("{\"text\": \"$this\"}")

    override fun sendTitle(player: Player, title: String?, subtitle: String?, fadeIn: Int, stay: Int, fadeOut: Int) {
        if (!title.isNullOrEmpty()) player.sendPackets(
            PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, title.toComponent()),
            PacketPlayOutTitle(fadeIn, stay, fadeOut)
        )
        if (subtitle != null) player.sendPackets(
            PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitle.toComponent()),
            PacketPlayOutTitle(fadeIn, stay, fadeOut)
        )
    }
    override fun playAction(player: Player, text: String) {
        player.sendPackets(PacketPlayOutChat(text.toComponent(), 2.toByte()))
    }

    override fun getItemInHand(player: Player) = player.itemInHand ?: ItemStack(Material.AIR)

    override fun hidePlayer(victim: Player, receiver: Player) = receiver.hidePlayer(victim)
    override fun showPlayer(victim: Player, receiver: Player) = receiver.showPlayer(victim)

    override fun hideEntity(entity: Entity, player: Player) = player.sendPackets(PacketPlayOutEntityDestroy(entity.entityId))

    private val air = ItemStack(Material.AIR).nms

    private fun getItem(stack: ItemStack) = stack.nms.item
    override fun isArmor(item: ItemStack) = getItem(item) is ItemArmor
    override fun isTool(item: ItemStack) = getItem(item) is ItemTool
    override fun isSword(item: ItemStack) = getItem(item) is ItemSword
    override fun isAxe(item: ItemStack) = getItem(item) is ItemAxe
    override fun isBow(item: ItemStack) = getItem(item) is ItemBow
    override fun isProjectile(item: ItemStack) = getItem(item) is IProjectile

    override fun getDamage(item: ItemStack) = item.nms.tag?.getDouble("generic.attackDamage") ?: 0.0

    override fun isInvisibilityPotion(item: ItemStack): Boolean {
        if (item.type != Material.POTION) return false

        val pm = item.itemMeta as? PotionMeta

        if (pm != null && pm.hasCustomEffects()) {
            return pm.hasCustomEffect(PotionEffectType.INVISIBILITY)
        }

        val potion = Potion.fromItemStack(item)
        val type = potion.type

        return type.effectType == PotionEffectType.INVISIBILITY
    }

    // Avoids string search
    override fun isGlass(type: Material) = type == Material.GLASS || type == Material.STAINED_GLASS

    override fun setCollide(player: Player, arena: IArena, value: Boolean) {
        player.spigot().collidesWithEntities = value
    }

    override fun minusAmount(player: Player, item: ItemStack, amount: Int) {
        if (item.amount - amount <= 0) {
            player.inventory.removeItem(item)
            return
        }
        item.amount -= amount
        player.updateInventory()
    }

    override fun spawnVillager(location: Location) {
        val nmsEntity = VillagerShop(location)
        val tag = nmsEntity.nbtTag ?: NBTTagCompound()

        nmsEntity.c(tag)
        tag.setInt("NoAI", 1)
        nmsEntity.f(tag)
        (nmsEntity.bukkitEntity as CraftLivingEntity).removeWhenFarAway = false
    }

    override fun setSource(tnt: TNTPrimed, owner: Player) {
        EntityTNTPrimed::class.setField(
            (tnt as CraftTNTPrimed).handle,
            "source",
            (owner as CraftLivingEntity).handle
        )
    }

    override fun voidKill(player: Player) {
        (player as CraftPlayer).handle.damageEntity(DamageSource.OUT_OF_WORLD, 1000f)
    }

    override fun hideArmor(victim: Player, receiver: Player) {
        if (victim == receiver) return
        receiver.sendPackets(*(1 .. 4).map { PacketPlayOutEntityEquipment(victim.entityId, it, air) }.toTypedArray())
    }

    override fun showArmor(victim: Player, receiver: Player) {
        if (victim == receiver) return
        val entityPlayer = (victim as CraftPlayer).handle
        receiver.sendPackets(*(1 .. 4).map { PacketPlayOutEntityEquipment(victim.entityId, it, entityPlayer.inventory.armorContents[it-1]) }.toTypedArray())
    }

    override fun spawnDragon(location: Location, team: ITeam) {
        location.world?.spawnEntity(location, EntityType.ENDER_DRAGON)
    }

    override fun colorBed(team: ITeam) {}

    override fun registerTntWhitelist(endStoneBlast: Float, glassBlast: Float) {
        Block::class.getField("durability").apply {
            isAccessible = true
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
        val color = team.color.byte.toShort()
        val type = when (item.type) {
            Material.WOOL, Material.STAINED_CLAY, Material.STAINED_GLASS -> item.type
            Material.GLASS -> Material.STAINED_GLASS
            else -> return item
        }
        return ItemStack(type, item.amount, color)
    }

    override fun createItemStack(material: String, amount: Int, data: Short) = try {
        ItemStack(Material.valueOf(material), amount, data)
    } catch (_: Exception) {
        plugin.logger.severe("$material is not a valid $name material!")
        ItemStack(Material.BEDROCK)
    }

    override fun isPlayerHead(material: Material, data: Int) = material == Material.SKULL_ITEM && data == 3
    override fun materialFireball() = Material.FIREBALL
    override fun materialPlayerHead() = Material.SKULL_ITEM
    override fun materialSnowball() = Material.SNOW_BALL
    override fun materialGoldenHelmet() = Material.GOLD_HELMET
    override fun materialGoldenChestPlate() = Material.GOLD_CHESTPLATE
    override fun materialGoldenLeggings() = Material.GOLD_LEGGINGS
    override fun materialNetheriteHelmet() = Material.DIAMOND_HELMET // Netherite doesn't exist
    override fun materialNetheriteChestPlate() = Material.DIAMOND_CHESTPLATE // Netherite doesn't exist
    override fun materialNetheriteLeggings() = Material.DIAMOND_LEGGINGS // Netherite doesn't exist
    override fun materialElytra() = null // Elytra is 1.9+
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

    override fun setJoinSignBackground(state: BlockState, material: Material) {
        state.location.block.getRelative((state.data as Sign).attachedFace).type = material
    }

    override fun getPlayerHead(player: Player, copyTagFrom: ItemStack?): ItemStack {
        val head = copyTag(copyTagFrom, ItemStack(Material.SKULL_ITEM, 1, 3.toShort()))

        val headMeta = head.itemMeta as SkullMeta
        headMeta::class.setField(headMeta, "profile", (player as CraftPlayer).profile)
        head.itemMeta = headMeta
        return head
    }

    override fun sendPlayerSpawnPackets(player: Player, arena: IArena) {
        if (!arena.isPlayer(player)) return

        // if method was used when the player was still in re-spawning screen
        if (arena.respawnSessions.containsKey(player)) return

        val entityPlayer = (player as CraftPlayer).handle
        val show = PacketPlayOutNamedEntitySpawn(entityPlayer)
        val playerVelocity = PacketPlayOutEntityVelocity(entityPlayer)
        val head = PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.yaw))

        val hand1 = PacketPlayOutEntityEquipment(entityPlayer.id, 0, entityPlayer.inventory.itemInHand)
        val armor = (1 .. 4).map { PacketPlayOutEntityEquipment(entityPlayer.id, it, entityPlayer.inventory.armorContents[it-1]) }.toTypedArray()

        for (p in arena.players) {
            if (p == player) continue
            // if p is in re-spawning screen continue
            if (arena.respawnSessions.containsKey(p)) continue

            val boundTo = (p as CraftPlayer).handle
            if (p.world == player.world && player.location.distance(p.location) <= arena.renderDistance) {
                // send respawned player to regular players

                p.sendPackets(*armor, hand1, head, show, playerVelocity)

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

        for (spectator in arena.spectators) {
            if (spectator == player) continue
            player.hidePlayer(spectator)
            if (spectator.world == player.world && player.location.distance(spectator.location) <= arena.renderDistance) {
                // send respawned player to spectator

                spectator.sendPackets(
                    *armor, hand1, show, playerVelocity,
                    PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.yaw))
                )
            }
        }
    }

    override fun getInventoryName(e: InventoryEvent): String = e.inventory.name

    override fun setUnbreakable(itemMeta: ItemMeta) {
        itemMeta.spigot().isUnbreakable = true
    }

    override val mainLevel: String = (MinecraftServer.getServer() as DedicatedServer).propertyManager.properties.getProperty("level-name")

    override fun setFireballDirection(fireball: Fireball, vector: Vector) = (fireball as CraftFireball).handle.run {
        dirX = vector.x * 0.1
        dirY = vector.y * 0.1
        dirZ = vector.z * 0.1
        bukkitEntity as Fireball
    }

    override fun playRedStoneDot(player: Player) {
        val red = Color.RED.red.toFloat()
        val particlePacket = PacketPlayOutWorldParticles(
            EnumParticle.REDSTONE, true,
            player.location.x.toFloat(), (player.location.y + 2.6).toFloat(), player.location.z.toFloat(),
            red, red, red,
            0f, 0
        )
        player.world.players.forEach { if (it != player) it.sendPackets(particlePacket) }
    }

    override fun clearArrowsFromPlayerBody(player: Player) {
        (player as CraftLivingEntity).handle.dataWatcher.watch(9, (-1).toByte())
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

    override fun playVillagerEffect(player: Player, location: Location) {
        val pwp = PacketPlayOutWorldParticles(
            EnumParticle.VILLAGER_HAPPY, true,
            location.x.toFloat(), location.y.toFloat(), location.z.toFloat(),
            0f, 0f, 0f,
            0f, 1
        )
        player.sendPackets(pwp)
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
