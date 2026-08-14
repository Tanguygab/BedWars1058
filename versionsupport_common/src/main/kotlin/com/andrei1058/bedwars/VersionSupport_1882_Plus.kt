package com.andrei1058.bedwars

import com.mojang.datafixers.util.Pair
import net.minecraft.network.protocol.game.PacketPlayOutEntityEquipment
import net.minecraft.server.level.EntityPlayer
import net.minecraft.world.entity.EnumItemSlot
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

// Trying to avoid issues with imports
@Suppress("ClassName")
object VersionSupport_1882_Plus {
    fun getEquipmentPacket(victim: EntityPlayer, full: Boolean, hide: Boolean = false): PacketPlayOutEntityEquipment {
        val air = ItemStack(Item.b(0))
        var armor = arrayOf(EnumItemSlot.f, EnumItemSlot.e, EnumItemSlot.d, EnumItemSlot.c)
        if (full) armor = arrayOf(EnumItemSlot.a, EnumItemSlot.b) + armor

        val items = if (hide) armor.associateWith { air } else armor.associateWith { victim.c(it) }
        return PacketPlayOutEntityEquipment(victim.aj(), items.map { (slot, item) -> Pair(slot, item) })
    }
}