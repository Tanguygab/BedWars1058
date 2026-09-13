package com.andrei1058.bedwars.support.version.v1_20_R1

import com.andrei1058.bedwars.VersionSupport_1882_Plus.getEquipmentPacket
import com.andrei1058.bedwars.VersionSupportCommon
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.v1_20_R1.despawnable.TeamIronGolem
import com.andrei1058.bedwars.support.version.v1_20_R1.despawnable.TeamSilverfish
import net.minecraft.core.particles.ParticleParamRedstone
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation
import net.minecraft.network.protocol.game.PacketPlayOutEntityVelocity
import net.minecraft.network.protocol.game.PacketPlayOutNamedEntitySpawn
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles
import net.minecraft.server.MinecraftServer
import net.minecraft.server.dedicated.DedicatedServer
import net.minecraft.world.entity.item.EntityTNTPrimed
import net.minecraft.world.entity.projectile.IProjectile
import net.minecraft.world.item.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBase
import org.bukkit.Color
import org.bukkit.command.SimpleCommandMap
import org.bukkit.craftbukkit.v1_20_R1.CraftServer
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftFireball
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftLivingEntity
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftTNTPrimed
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack
import org.bukkit.entity.Fireball
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector
import org.joml.Vector3f

@Suppress("ClassName")
@Deprecated("")
class v1_20_R1(plugin: Plugin, name: String) : VersionSupportCommon(plugin, name, 9) {
    override val despawnableTypes = arrayOf<DespawnableProviderCommon<out LivingEntity>>(TeamIronGolem(), TeamSilverfish())
    override val commandMap: SimpleCommandMap = (plugin.server as CraftServer).commandMap

    private val Player.nms get() = (player as CraftPlayer).handle
    private fun Player.sendPackets(vararg packets: Packet<*>) {
        val connection = (this as CraftPlayer).handle.c
        packets.forEach { connection.a(it) }
    }

    override fun setSource(tnt: TNTPrimed, owner: Player) {
        EntityTNTPrimed::class.setField(
            (tnt as CraftTNTPrimed).handle,
            "d",
            (owner as CraftLivingEntity).handle
        )
    }

    private fun getItem(item: ItemStack) = item.nms.d()
    override fun isArmor(item: ItemStack) = getItem(item).let { it is ItemArmor || it is ItemElytra }
    override fun isTool(item: ItemStack) = getItem(item) is ItemTool
    override fun isSword(item: ItemStack) = getItem(item) is ItemSword
    override fun isAxe(item: ItemStack) = getItem(item) is ItemAxe
    override fun isBow(item: ItemStack) = getItem(item) is ItemBow

    private fun getEntity(item: ItemStack) = item.nms.H()
    override fun isProjectile(item: ItemStack) = getEntity(item) is IProjectile

    override fun getDamage(item: ItemStack) = item.tag?.k("generic.attackDamage") ?: 0.0

    override fun voidKill(player: Player) {
        player.nms.apply { a(dJ().l(), 1000f) }
    }

    override fun hideArmor(victim: Player, receiver: Player) = receiver.sendPackets(getEquipmentPacket(victim.nms, full = false, hide = true))
    override fun showArmor(victim: Player, receiver: Player) = receiver.sendPackets(getEquipmentPacket(victim.nms, false))

    override fun registerTntWhitelist(endStoneBlast: Float, glassBlast: Float) {
        // blast resistance
        val field = BlockBase::class.getField("aF")
        // end stone
        field.set(Blocks.fz, endStoneBlast)
        // obsidian
//            field.set(Blocks.co, glassBlast);

        val glass = arrayOf<Block?>(
            Blocks.aQ,
            Blocks.ej, Blocks.ek, Blocks.el, Blocks.em,
            Blocks.en, Blocks.eo, Blocks.ep, Blocks.eq,
            Blocks.er, Blocks.es, Blocks.et, Blocks.eu,
            Blocks.ev, Blocks.ew, Blocks.ex, Blocks.ey,  // tinted glass

            Blocks.qB,
        )

        glass.forEach { field.set(it, glassBlast) }
    }

    override fun sendPlayerSpawnPackets(player: Player, arena: IArena) {
        if (!arena.isPlayer(player)) return

        // if method was used when the player was still in re-spawning screen
        if (arena.respawnSessions.containsKey(player)) return

        val entityPlayer = player.nms
        val show = PacketPlayOutNamedEntitySpawn(entityPlayer)
        val playerVelocity = PacketPlayOutEntityVelocity(entityPlayer)
        // we send head rotation packet because sometimes on respawn others see him with bad rotation
        val head = PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.bukkitYaw))

        // retrieve current armor and in-hand items
        // we send a packet later for timing issues where other players do not see them
        val equipment = getEquipmentPacket(entityPlayer, true)


        for (p in arena.players) {
            if (p == player) continue
            // if p is in re-spawning screen continue
            if (arena.respawnSessions.containsKey(p)) continue

            val boundTo = p.nms
            if (p.world == player.world) {
                if (player.location.distance(p.location) <= arena.renderDistance) {
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
            }
        }

        for (spectator in arena.spectators) {
            if (spectator == player) continue
            player.hidePlayer(plugin, spectator)
            if (spectator.world != player.world || player.location.distance(spectator.location) > arena.renderDistance) continue
            // send respawned player to spectator

            spectator.sendPackets(
                show, playerVelocity, equipment,
                PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.bukkitYaw))
            )
        }
    }

    @Suppress("DEPRECATION")
    override val mainLevel: String = (MinecraftServer.getServer() as DedicatedServer).a().m

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
                Vector3f(
                    color.red.toFloat(),
                    color.green.toFloat(),
                    color.blue.toFloat()
                ), 1f
            ),
            true,
            player.location.x,
            player.location.y + 2.6,
            player.location.z,
            0f, 0f, 0f, 0f, 0
        )
        player.world.players.forEach { if (it != player) it.sendPackets(particlePacket) }
    }

    private val ItemStack.nms get() = CraftItemStack.asNMSCopy(this)
    private val ItemStack.tag get() = nms.v()
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