package com.andrei1058.bedwars.support.version.v1_20_R3

import com.andrei1058.bedwars.VersionSupportCommon
import com.andrei1058.bedwars.VersionSupport_1882_Plus.getEquipmentPacket
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.v1_20_R3.despawnable.TeamIronGolem
import com.andrei1058.bedwars.support.version.v1_20_R3.despawnable.TeamSilverfish
import net.minecraft.core.particles.ParticleParamRedstone
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.protocol.Packet
import net.minecraft.network.protocol.game.PacketPlayOutEntityHeadRotation
import net.minecraft.network.protocol.game.PacketPlayOutEntityVelocity
import net.minecraft.network.protocol.game.PacketPlayOutSpawnEntity
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles
import net.minecraft.server.MinecraftServer
import net.minecraft.server.dedicated.DedicatedServer
import net.minecraft.world.entity.item.EntityTNTPrimed
import net.minecraft.world.entity.projectile.IProjectile
import net.minecraft.world.item.*
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockBase
import org.bukkit.Color
import org.bukkit.command.SimpleCommandMap
import org.bukkit.craftbukkit.v1_20_R3.CraftServer
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftFireball
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftLivingEntity
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftTNTPrimed
import org.bukkit.craftbukkit.v1_20_R3.inventory.CraftItemStack
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
open class v1_20_R3(plugin: Plugin, name: String) : VersionSupportCommon(plugin, name, 10) {
    override val despawnableTypes = arrayOf<DespawnableProviderCommon<out LivingEntity>>(TeamIronGolem(), TeamSilverfish())
    override val commandMap: SimpleCommandMap = (plugin.server as CraftServer).commandMap

    private val Player.nms get() = (this as CraftPlayer).handle
    private fun Player.sendPackets(vararg packets: Packet<*>) {
        val connection = nms.c
        packets.forEach { connection.a(it) }
    }

    override fun setSource(tnt: TNTPrimed, owner: Player) {
        EntityTNTPrimed::class.setField(
            (tnt as CraftTNTPrimed).handle,
            "g",
            (owner as CraftLivingEntity).handle
        )
    }

    private fun getItem(itemStack: ItemStack?) = CraftItemStack.asNMSCopy(itemStack)?.d()
    override fun isArmor(itemStack: ItemStack?) = getItem(itemStack).let { it is ItemArmor || it is ItemElytra }
    override fun isTool(itemStack: ItemStack?) = getItem(itemStack) is ItemTool
    override fun isSword(itemStack: ItemStack?) = getItem(itemStack) is ItemSword
    override fun isAxe(itemStack: ItemStack?) = getItem(itemStack) is ItemAxe
    override fun isBow(itemStack: ItemStack?) = getItem(itemStack) is ItemBow

    private fun getEntity(itemStack: ItemStack?) = CraftItemStack.asNMSCopy(itemStack).H()
    override fun isProjectile(itemStack: ItemStack?) = getEntity(itemStack) is IProjectile

    override fun getDamage(i: ItemStack) = i.tag?.k("generic.attackDamage") ?: 0.0

    override fun voidKill(player: Player) {
        player.nms.apply { a(dN().m(), 1000f) }
    }

    override fun hideArmor(victim: Player, receiver: Player) = receiver.sendPackets(getEquipmentPacket(victim.nms, full = false, hide = true))
    override fun showArmor(victim: Player, receiver: Player) = receiver.sendPackets(getEquipmentPacket(victim.nms, false))

    override fun registerTntWhitelist(endStoneBlast: Float, glassBlast: Float) {
        // blast resistance
        val field = BlockBase::class.getField("aH")
        // end stone
        field.set(Blocks.fz, endStoneBlast)
        // obsidian
//            field.set(Blocks.co, glassBlast);
        // standard glass

        arrayOf(
            Blocks.aQ,
            Blocks.ej, Blocks.ek, Blocks.el, Blocks.em,
            Blocks.en, Blocks.eo, Blocks.ep, Blocks.eq,
            Blocks.er, Blocks.es, Blocks.et, Blocks.eu,
            Blocks.ev, Blocks.ew, Blocks.ex, Blocks.ey,  // tinted glass
            Blocks.qB,
        ).forEach { field.set(it, glassBlast) }
    }

    override fun sendPlayerSpawnPackets(respawned: Player?, arena: IArena?) {
        if (respawned == null || arena == null || !arena.isPlayer(respawned)) return

        // if method was used when the player was still in re-spawning screen
        if (arena.respawnSessions.containsKey(respawned)) return

        val entityPlayer = respawned.nms
        val show = PacketPlayOutSpawnEntity(entityPlayer)
        val playerVelocity = PacketPlayOutEntityVelocity(entityPlayer)
        // we send head rotation packet because sometimes on respawn others see him with bad rotation
        val head = PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.bukkitYaw))

        // retrieve current armor and in-hand items
        // we send a packet later for timing issues where other players do not see them
        val equipment = getEquipmentPacket(entityPlayer, true)


        for (p in arena.getPlayers()) {
            if (p == null || p == respawned) continue
            // if p is in re-spawning screen continue
            if (arena.respawnSessions.containsKey(p)) continue

            val boundTo = p.nms
            if (p.world == respawned.world && respawned.location.distance(p.location) <= arena.renderDistance) {
                // send respawned player to regular players

                p.sendPackets(
                    show, head, playerVelocity,
                    equipment
                )

                // send nearby players to respawned player
                // if the player has invisibility hide armor
                if (p.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                    hideArmor(p, respawned)
                } else {
                    val show2 = PacketPlayOutSpawnEntity(boundTo)
                    val playerVelocity2 = PacketPlayOutEntityVelocity(boundTo)
                    val head2 = PacketPlayOutEntityHeadRotation(boundTo, getCompressedAngle(boundTo.bukkitYaw))
                    respawned.sendPackets(show2, playerVelocity2, head2)

                    showArmor(p, respawned)
                }
            }
        }

        for (spectator in arena.spectators) {
            if (spectator == null || spectator == respawned) continue
            respawned.hidePlayer(plugin, spectator)

            if (spectator.world != respawned.world || respawned.location.distance(spectator.location) > arena.renderDistance) continue
            // send respawned player to spectator
            spectator.sendPackets(
                show, playerVelocity,
                equipment,
                PacketPlayOutEntityHeadRotation(entityPlayer, getCompressedAngle(entityPlayer.bukkitYaw))
            )
        }
    }

    @Suppress("DEPRECATION")
    override fun getMainLevel(): String = (MinecraftServer.getServer() as DedicatedServer).a().m

    override fun setFireballDirection(fireball: Fireball, vector: Vector) = (fireball as CraftFireball).handle.run {
        b = vector.x * 0.1
        c = vector.y * 0.1
        d = vector.z * 0.1
        bukkitEntity as Fireball
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
        for (inWorld in player.world.players) {
            if (inWorld == player) continue
            inWorld.sendPackets(particlePacket)
        }
    }

    private val ItemStack.nms get() = CraftItemStack.asNMSCopy(this)
    private val ItemStack.tag get() = nms.v()
    override fun getTag(item: ItemStack?, key: String?) = item?.tag?.l(key)
    private fun ItemStack.setTag(tag: NBTTagCompound?): ItemStack {
        val item = nms
        item.c(tag)
        return CraftItemStack.asBukkitCopy(item)
    }
    override fun setTag(itemStack: ItemStack, key: String?, value: String?): ItemStack {
        val tag = itemStack.tag ?: NBTTagCompound()
        tag.a(key, value)
        return itemStack.setTag(tag)
    }
    override fun copyTag(from: ItemStack?, to: ItemStack): ItemStack {
        return to.setTag(from?.tag ?: return to)
    }
}