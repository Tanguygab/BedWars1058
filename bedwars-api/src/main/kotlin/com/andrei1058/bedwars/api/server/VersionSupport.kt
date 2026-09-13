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
package com.andrei1058.bedwars.api.server

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.shop.ShopHolo
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.api.entity.Despawnable
import com.andrei1058.bedwars.api.language.Language
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Effect
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.BlockState
import org.bukkit.block.data.type.Bed
import org.bukkit.block.data.type.Ladder
import org.bukkit.block.data.type.WallSign
import org.bukkit.command.SimpleCommandMap
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EnderDragon
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.entity.Villager
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

abstract class VersionSupport(val plugin: Plugin, val name: String?, val version: Int) {
    /**
     * Egg bridge particles
     */
    val eggBridge = Effect.MOBSPAWNER_FLAMES

    abstract val commandMap: SimpleCommandMap

    /**
     * Send title, subtitle. null for empty
     */
    open fun sendTitle(player: Player, title: String?, subtitle: String?, fadeIn: Int, stay: Int, fadeOut: Int) {
        player.sendTitle(title ?: " ", subtitle ?: " ", fadeIn, stay, fadeOut)
    }

    /**
     * Send action-bar message
     */
    open fun playAction(player: Player, text: String) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, *TextComponent.fromLegacyText(text))
    }

    /**
     * Get in had item-stack
     */
    open fun getItemInHand(player: Player) = player.inventory.itemInMainHand

    /**
     * Hide an entity
     */
    open fun hideEntity(entity: Entity, player: Player) = player.hideEntity(plugin, entity)

    abstract fun isArmor(item: ItemStack): Boolean
    abstract fun isTool(item: ItemStack): Boolean
    abstract fun isSword(item: ItemStack): Boolean
    abstract fun isAxe(item: ItemStack): Boolean
    abstract fun isBow(item: ItemStack): Boolean
    abstract fun isProjectile(item: ItemStack): Boolean
    open fun isInvisibilityPotion(item: ItemStack): Boolean {
        if (item.type != Material.POTION) return false

        val pm = item.itemMeta as? PotionMeta ?: return false

        return pm.hasCustomEffects() && pm.hasCustomEffect(PotionEffectType.INVISIBILITY)
    }
    open fun isGlass(type: Material) = type != Material.AIR && (type == Material.GLASS || type.toString().contains("_GLASS"))

    /**
     * Spawn shop NPC
     */
    fun spawnShop(loc: Location, name1: String, players: List<Player>, arena: IArena) {
        if (loc.world == null) return
        spawnVillager(loc)

        val single = arrayOf(loc.clone().add(0.0, 1.85, 0.0))
        val multiple = arrayOf(loc.clone().add(0.0, 2.1, 0.0)) + single

        for (p in players) {
            val name = Language.getMsg(p, name1).split(",")
            val armorStands = (if (name.size == 1) single else multiple).mapIndexed { index, location -> createArmorStand(name[index], location) }
            ShopHolo(Language.getLanguage(p).iso, armorStands[0], armorStands.getOrNull(1), loc.clone(), arena)
        }

        for (sh in ShopHolo.shopHolo) {
            if (sh.a === arena) sh.update()
        }
    }
    open fun spawnVillager(location: Location) {
        (location.world!!.spawnEntity(location, EntityType.VILLAGER) as Villager).apply {
            setAI(false)
            removeWhenFarAway = false
            isCollidable = false
            isInvulnerable = true
            isSilent = true
        }
    }

    /**
     * Get item-stack damage amount
     */
    abstract fun getDamage(item: ItemStack): Double

    /**
     * Spawn silverfish for a team
     */
    abstract fun spawnSilverfish(loc: Location, team: ITeam, speed: Double, health: Double, despawn: Int, damage: Double)

    /**
     * Spawn an iron-golem for a team
     */
    abstract fun spawnIronGolem(loc: Location, team: ITeam, speed: Double, health: Double, despawn: Int)

    /**
     * Is despawnable entity
     */
    fun isDespawnable(entity: Entity) = despawnables.containsKey(entity.uniqueId)
    val despawnables = Companion.despawnables

    /**
     * Change item amount
     */
    open fun minusAmount(player: Player, item: ItemStack, amount: Int) {
        if (item.amount - amount <= 0) {
            if (player.inventory.itemInOffHand == item) player.inventory.setItemInOffHand(null)
            else player.inventory.removeItem(item)
            return
        }
        item.amount -= amount
    }

    /**
     * Set tnt source
     */
    abstract fun setSource(tnt: TNTPrimed, owner: Player)

    /**
     * Void damage with cause
     */
    abstract fun voidKill(player: Player)

    /**
     * Hide player armor to a player
     */
    abstract fun hideArmor(victim: Player, receiver: Player)

    /**
     * Show a player armor
     */
    abstract fun showArmor(victim: Player, receiver: Player)

    /**
     * Spawn ender dragon
     */
    open fun spawnDragon(location: Location, team: ITeam) {
        val dragon = location.world!!.spawnEntity(location, EntityType.ENDER_DRAGON) as EnderDragon
        dragon.phase = EnderDragon.Phase.CIRCLING
    }

    /**
     * Color a bed 1.16+
     */
    open fun colorBed(team: ITeam) {
        val block = team.bed.block
        val bed = block.blockData as Bed
        val other = block.getRelative(if (bed.part == Bed.Part.HEAD) bed.facing.oppositeFace else bed.facing)
        arrayOf(block, other).forEach { it.type = team.color.bedMaterial() }
    }

    /**
     * Modify block blast resistance.
     */
    abstract fun registerTntWhitelist(endStoneBlast: Float, glassBlast: Float)

    /**
     * Set block data
     * For 1.13 support
     */
    open fun setBlockTeamColor(block: Block, teamColor: TeamColor) {
        block.type = block.type.toString().run { when {
            contains("STAINED_GLASS") || equals("GLASS") -> teamColor.glassMaterial()
            contains("_TERRACOTTA") -> teamColor.glazedTerracottaMaterial()
            contains("_WOOL") -> teamColor.woolMaterial()
            else -> return
        } }
    }

    /**
     * Disable collisions in 1.9+
     */
    open fun setCollide(player: Player, arena: IArena, value: Boolean) {
        player.isCollidable = value
        arena.updateSpectatorCollideRule(player, value)
    }

    /**
     * Get a custom item tag.
     *
     * @return null if not present.
     */
    abstract fun getTag(item: ItemStack?, key: String?): String?
    abstract fun setTag(item: ItemStack, key: String?, value: String?): ItemStack
    abstract fun copyTag(from: ItemStack?, to: ItemStack): ItemStack

    /**
     * Add custom data to an ItemStack
     */
    fun addCustomData(item: ItemStack, data: String?) = setTag(item, PLUGIN_TAG_GENERIC_KEY, data)
    /**
     * Get the NBTTag from a BedWars1058 item
     */
    fun getCustomData(item: ItemStack) = getTag(item, PLUGIN_TAG_GENERIC_KEY)
    /**
     * Check if an item has a BedWars1058 NBTTag
     */
    fun isCustomBedWarsItem(item: ItemStack) = getCustomData(item) != null


    /**
     * Color an item if possible with the team's color
     */
    open fun colourItem(item: ItemStack, team: ITeam): ItemStack {
        val color = team.color
        val type = item.type.toString().run { when {
            isBed(item.type) -> color.bedMaterial()
            contains("_STAINED_GLASS_PANE") -> color.glassPaneMaterial()
            contains("STAINED_GLASS") || equals("GLASS") -> color.glassMaterial()
            contains("_TERRACOTTA") -> color.glazedTerracottaMaterial()
            contains("_WOOL") -> color.woolMaterial()
            else -> return item
        } }
        return ItemStack(type, item.amount)
    }

    open fun createItemStack(material: String, amount: Int, data: Short): ItemStack {
        val mat = Material.getMaterial(material)
        return if (mat != null) ItemStack(mat, amount)
        else {
            plugin.logger.severe("$material is not a valid $name material!")
            ItemStack(Material.BEDROCK)
        }
    }

    /**
     * Check if is a player head
     */
    open fun isPlayerHead(material: Material, data: Int) = material == Material.PLAYER_HEAD

    open fun materialFireball() = Material.FIRE_CHARGE
    open fun materialPlayerHead() = Material.PLAYER_HEAD
    open fun materialSnowball() = Material.SNOWBALL
    open fun materialGoldenHelmet() = Material.GOLDEN_HELMET
    open fun materialGoldenChestPlate() = Material.GOLDEN_CHESTPLATE
    open fun materialGoldenLeggings() = Material.GOLDEN_LEGGINGS
    open fun materialNetheriteHelmet() = Material.NETHERITE_HELMET
    open fun materialNetheriteChestPlate() = Material.NETHERITE_CHESTPLATE
    open fun materialNetheriteLeggings() = Material.NETHERITE_LEGGINGS
    open fun materialCake() = Material.CAKE
    open fun materialCraftingTable() = Material.CRAFTING_TABLE
    open fun materialEnchantingTable() = Material.ENCHANTING_TABLE
    open fun woolMaterial() = Material.WHITE_WOOL
    /**
     * Get elytra - supports: 1.12.2+
     */
    open fun materialElytra(): Material? = Material.ELYTRA

    /**
     * Check if bed
     */
    open fun isBed(material: Material) = "_BED" in material.toString()

    /**
     * Item Data compare
     * This will always return true on versions major or equal 1.13
     */
    open fun itemStackDataCompare(item: ItemStack, data: Short) = true

    /**
     * Set block data
     * For versions before 1.13
     */
    open fun setJoinSignBackgroundBlockData(state: BlockState, data: Byte) {}

    /**
     * Change the block behind the join sign.
     */
    open fun setJoinSignBackground(state: BlockState, material: Material) {
        state.block.getRelative((state.blockData as? WallSign ?: return).facing.oppositeFace).type = material
    }


    /**
     * Get an ItemStack identifier
     * will return null text if it does not have an identifier
     */
    fun setShopUpgradeIdentifier(item: ItemStack, identifier: String?) = setTag(item, PLUGIN_TAG_TIER_KEY, identifier)

    /**
     * Set an upgrade identifier
     */
    fun getShopUpgradeIdentifier(item: ItemStack?) = getTag(item, PLUGIN_TAG_TIER_KEY) ?: "null"

    /**
     * Get player head with skin.
     * 
     * @param copyTagFrom will copy nbt tag from this item.
     */
    open fun getPlayerHead(player: Player, copyTagFrom: ItemStack?): ItemStack {
        val head = copyTag(copyTagFrom, ItemStack(materialPlayerHead()))

        val meta = head.itemMeta as SkullMeta
        meta.ownerProfile = player.playerProfile
        head.itemMeta = meta
        return head
    }

    /**
     * This will send the player spawn packet after a player re-spawn.
     * 
     * 
     * Show the target player to players and spectators in the arena.
     */
    abstract fun sendPlayerSpawnPackets(player: Player, arena: IArena)

    /**
     * Get inventory name.
     */
    open fun getInventoryName(e: InventoryEvent) = e.view.title

    /**
     * Make item unbreakable.
     */
    open fun setUnbreakable(itemMeta: ItemMeta) {
        itemMeta.isUnbreakable = true
    }

    abstract fun registerVersionListeners()

    /**
     * Get main level name.
     */
    abstract val mainLevel: String

    fun getCompressedAngle(value: Float) = (value * 256.0f / 360.0f).toInt().toByte()

    open fun showPlayer(victim: Player, receiver: Player) = receiver.showPlayer(plugin, victim)
    open fun hidePlayer(victim: Player, receiver: Player) = receiver.hidePlayer(plugin, victim)

    /**
     * Make fireball go straight.
     * 
     * @param fireball fireball instance;
     * @param vector   fireball direction to normalize.
     * @return modified fireball.
     */
    abstract fun setFireballDirection(fireball: Fireball, vector: Vector): Fireball

    abstract fun playRedStoneDot(player: Player)

    open fun clearArrowsFromPlayerBody(player: Player) {
        // minecraft clears them on death on newer version
    }

    open fun placeTowerBlocks(b: Block, a: IArena, color: TeamColor, x: Int, y: Int, z: Int) {
        b.getRelative(x, y, z).type = color.woolMaterial()
        a.addPlacedBlock(b.getRelative(x, y, z))
    }

    open fun placeLadder(block: Block, x: Int, y: Int, z: Int, arena: IArena, ladderData: Int) {
        val block = block.getRelative(x, y, z) // ladder block
        block.type = Material.LADDER
        val ladder = block.blockData as Ladder
        arena.addPlacedBlock(block)
        val facing = when (ladderData) {
            2 -> BlockFace.NORTH
            3 -> BlockFace.SOUTH
            4 -> BlockFace.WEST
            5 -> BlockFace.EAST
            else -> return
        }
        ladder.facing = facing
        block.blockData = ladder
    }

    open fun playVillagerEffect(player: Player, location: Location) {
        player.spawnParticle(Particle.VILLAGER_HAPPY, location, 1)
    }

    companion object {
        const val PLUGIN_TAG_GENERIC_KEY = "BedWars1058"
        const val PLUGIN_TAG_TIER_KEY = "tierIdentifier"

        /**
         * Get list of entities that are going to despawn based on a timer.
         */
        val despawnables = ConcurrentHashMap<UUID, Despawnable>()

        fun createArmorStand(name: String?, loc: Location) = loc
            .world!!
            .spawn(loc, ArmorStand::class.java)
            .apply {
                setGravity(false)
                isVisible = false
                if (name != null) {
                    isCustomNameVisible = true
                    customName = name
                }
            }
    }
}
