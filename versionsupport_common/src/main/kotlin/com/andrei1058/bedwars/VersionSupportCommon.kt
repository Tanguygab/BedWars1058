package com.andrei1058.bedwars

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.shop.ShopHolo
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.api.entity.Despawnable
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.server.VersionSupport
import com.andrei1058.bedwars.support.version.common.VersionCommon
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableAttributes
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableProviderCommon
import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableType
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.block.Block
import org.bukkit.block.BlockFace
import org.bukkit.block.BlockState
import org.bukkit.block.data.type.Bed
import org.bukkit.block.data.type.Ladder
import org.bukkit.block.data.type.WallSign
import org.bukkit.command.Command
import org.bukkit.command.SimpleCommandMap
import org.bukkit.entity.*
import org.bukkit.event.inventory.InventoryEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffectType
import java.lang.reflect.Field
import kotlin.reflect.KClass

abstract class VersionSupportCommon(plugin: Plugin, versionName: String, private val versionId: Int) : VersionSupport(plugin, versionName) {
    private val versionCommon = VersionCommon(this)
    abstract val despawnableTypes: Array<DespawnableProviderCommon<out LivingEntity>>
    abstract val commandMap: SimpleCommandMap

    init {
        loadDefaultEffects()
    }

    protected fun KClass<*>.getField(field: String): Field = java.getDeclaredField(field).apply { isAccessible = true }
    protected fun KClass<*>.setField(obj: Any?, field: String, value: Any?) = getField(field).set(obj, value)

    override fun getVersion() = versionId

    override fun registerVersionListeners() {
        versionCommon.registerListeners(plugin)
        versionCommon.despawnableFactory.addProviders(*despawnableTypes)
    }

    override fun registerCommand(name: String, command: Command) {
        commandMap.register(name, command)
    }

    override fun isBukkitCommandRegistered(command: String) = commandMap.getCommand(command) != null

    override fun sendTitle(p: Player, title: String?, subtitle: String?, fadeIn: Int, stay: Int, fadeOut: Int) {
        p.sendTitle(title ?: " ", subtitle ?: " ", fadeIn, stay, fadeOut)
    }

    override fun playAction(p: Player, text: String) {
        p.spigot().sendMessage(
            ChatMessageType.ACTION_BAR,
            TextComponent(ChatColor.translateAlternateColorCodes('&', text))
        )
    }

    override fun spawnSilverfish(loc: Location, team: ITeam, speed: Double, health: Double, despawn: Int, damage: Double) {
        val attr = DespawnableAttributes(DespawnableType.SILVERFISH, speed, health, damage, despawn)
        Despawnable(
            versionCommon.despawnableFactory.spawn(attr, loc, team),
            team,
            despawn,
            attr.type.name,
            PlayerKillEvent.PlayerKillCause.SILVERFISH_FINAL_KILL,
            PlayerKillEvent.PlayerKillCause.SILVERFISH
        )
    }

    override fun spawnIronGolem(loc: Location, team: ITeam, speed: Double, health: Double, despawn: Int) {
        val attr = DespawnableAttributes(DespawnableType.IRON_GOLEM, speed, health, 4.0, despawn)
        Despawnable(
            versionCommon.despawnableFactory.spawn(attr, loc, team),
            team,
            despawn,
            attr.type.name,
            PlayerKillEvent.PlayerKillCause.IRON_GOLEM_FINAL_KILL,
            PlayerKillEvent.PlayerKillCause.IRON_GOLEM
        )
    }

    override fun getItemInHand(p: Player) = p.inventory.itemInMainHand

    override fun minusAmount(p: Player, item: ItemStack, amount: Int) {
        if (item.amount - amount <= 0) {
            if (p.inventory.itemInOffHand == item) p.inventory.setItemInOffHand(null)
            else p.inventory.removeItem(item)
            return
        }
        item.amount -= amount
    }

    override fun isInvisibilityPotion(item: ItemStack): Boolean {
        if (item.type != Material.POTION) return false

        val pm = item.itemMeta as? PotionMeta ?: return false

        return pm.hasCustomEffects() && pm.hasCustomEffect(PotionEffectType.INVISIBILITY)
    }

    override fun hideEntity(entity: Entity, player: Player) = player.hideEntity(plugin, entity)

    abstract fun copyTag(from: ItemStack?, to: ItemStack): ItemStack

    override fun addCustomData(item: ItemStack?, data: String?): ItemStack? = setTag(item, PLUGIN_TAG_GENERIC_KEY, data)
    override fun getCustomData(item: ItemStack?) = getTag(item, PLUGIN_TAG_GENERIC_KEY)
    override fun isCustomBedWarsItem(item: ItemStack?) = getCustomData(item) != null

    override fun setShopUpgradeIdentifier(item: ItemStack, identifier: String?): ItemStack = setTag(item, PLUGIN_TAG_TIER_KEY, identifier)
    override fun getShopUpgradeIdentifier(item: ItemStack?) = getTag(item, PLUGIN_TAG_TIER_KEY) ?: "null"

    override fun materialFireball() = Material.FIRE_CHARGE
    override fun materialPlayerHead() = Material.PLAYER_HEAD
    override fun materialSnowball() = Material.SNOWBALL
    override fun materialGoldenHelmet() = Material.GOLDEN_HELMET
    override fun materialGoldenChestPlate() = Material.GOLDEN_CHESTPLATE
    override fun materialGoldenLeggings() = Material.GOLDEN_LEGGINGS
    override fun materialNetheriteHelmet() = Material.NETHERITE_HELMET
    override fun materialNetheriteChestPlate() = Material.NETHERITE_CHESTPLATE
    override fun materialNetheriteLeggings() = Material.NETHERITE_LEGGINGS
    override fun materialElytra(): Material? = Material.ELYTRA
    override fun materialCake() = Material.CAKE
    override fun materialCraftingTable() = Material.CRAFTING_TABLE
    override fun materialEnchantingTable() = Material.ENCHANTING_TABLE
    override fun woolMaterial() = Material.WHITE_WOOL

    override fun colourItem(item: ItemStack?, team: ITeam): ItemStack? {
        if (item == null) return null
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

    override fun colorBed(team: ITeam) {
        for (x in -1..1) {
            for (z in -1..1) {
                val bed = team.bed.clone().add(x.toDouble(), 0.0, z.toDouble()).block.state
                if (bed is Bed) {
                    bed.type = team.color.bedMaterial()
                    bed.update()
                }
            }
        }
    }

    override fun setBlockTeamColor(block: Block, teamColor: TeamColor) {
        block.type = block.type.toString().run { when {
            contains("STAINED_GLASS") || equals("GLASS") -> teamColor.glassMaterial()
            contains("_TERRACOTTA") -> teamColor.glazedTerracottaMaterial()
            contains("_WOOL") -> teamColor.woolMaterial()
            else -> return
        } }
    }

    override fun placeLadder(b: Block, x: Int, y: Int, z: Int, a: IArena, ladderData: Int) {
        val block = b.getRelative(x, y, z) // ladder block
        block.type = Material.LADDER
        val ladder = block.blockData as Ladder
        a.addPlacedBlock(block)
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

    override fun clearArrowsFromPlayerBody(player: Player) {
        // minecraft clears them on death on newer version
    }

    override fun playVillagerEffect(player: Player, location: Location) {
        player.spawnParticle(Particle.VILLAGER_HAPPY, location, 1)
    }

    override fun placeTowerBlocks(b: Block, a: IArena, color: TeamColor, x: Int, y: Int, z: Int) {
        b.getRelative(x, y, z).type = color.woolMaterial()
        a.addPlacedBlock(b.getRelative(x, y, z))
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

    override fun spawnShop(loc: Location, name1: String?, players: List<Player>, arena: IArena?) {
        if (loc.world == null) return
        spawnVillager(loc)

        val single = arrayOf(loc.clone().add(0.0, 1.85, 0.0))
        val multiple = arrayOf(loc.clone().add(0.0, 2.1, 0.0)) + single

        for (p in players) {
            val name = Language.getMsg(p, name1).split(",")
            val armorStands = (if (name.size == 1) single else multiple).mapIndexed { index, location -> createArmorStand(name[index], location) }
            ShopHolo(Language.getPlayerLanguage(p).iso, armorStands[0], armorStands.getOrNull(1), loc.clone(), arena)
        }

        for (sh in ShopHolo.getShopHolo()) {
            if (sh.a === arena) sh.update()
        }
    }

    override fun setCollide(player: Player, arena: IArena?, value: Boolean) {
        player.isCollidable = value
        arena?.updateSpectatorCollideRule(player, value)
    }

    override fun spawnDragon(location: Location, team: ITeam) {
        val dragon = location.world!!.spawnEntity(location, EntityType.ENDER_DRAGON) as EnderDragon
        dragon.phase = EnderDragon.Phase.CIRCLING
    }

    override fun getInventoryName(e: InventoryEvent) = e.view.title

    override fun setUnbreakable(itemMeta: ItemMeta) {
        itemMeta.isUnbreakable = true
    }

    override fun setJoinSignBackground(b: BlockState, material: Material) {
        b.block.getRelative((b.blockData as? WallSign ?: return).facing.oppositeFace).type = material
    }

    override fun createItemStack(material: String, amount: Int, data: Short) = try {
        ItemStack(Material.valueOf(material), amount)
    } catch (_: Exception) {
        plugin.logger.severe("$material is not a valid ${getName()} material!")
        ItemStack(Material.BEDROCK)
    }

    override fun getPlayerHead(player: Player, copyTagFrom: ItemStack?): ItemStack {
        val head = copyTag(copyTagFrom, ItemStack(materialPlayerHead()))

        val meta = head.itemMeta as SkullMeta
        meta.ownerProfile = player.playerProfile
        head.itemMeta = meta
        return head
    }

    companion object {
        fun createArmorStand(name: String?, loc: Location?) = loc
            ?.world
            ?.spawn(loc, ArmorStand::class.java)
            ?.apply {
                setGravity(false)
                isVisible = false
                isCustomNameVisible = true
                customName = name
            }
    }
}