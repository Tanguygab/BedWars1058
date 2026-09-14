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
package com.andrei1058.bedwars.arena.team

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.util.Utils.editMeta
import com.andrei1058.bedwars.arena.generators.GeneratorOre
import com.andrei1058.bedwars.api.arena.generator.IGenerator
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.arena.team.TeamColor
import com.andrei1058.bedwars.api.arena.team.TeamEnchant
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.player.PlayerFirstSpawnEvent
import com.andrei1058.bedwars.api.events.player.PlayerReSpawnEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.region.Cuboid
import com.andrei1058.bedwars.api.upgrades.EnemyBaseEnterTrap
import com.andrei1058.bedwars.arena.Arena
import com.andrei1058.bedwars.arena.generators.Generator
import com.andrei1058.bedwars.configuration.Sounds
import com.andrei1058.bedwars.shop.ShopCache
import com.andrei1058.bedwars.api.util.Utils.teleportSafe
import org.bukkit.*
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class BedWarsTeam(
    override val name: String,
    override val color: TeamColor,
    override val spawn: Location,
    override val bed: Location,
    override val shop: Location,
    override val teamUpgrades: Location,
    override val arena: Arena
) : ITeam {
    override var members = mutableListOf<Player>()
    override var isBedDestroyed = false
        /**
         * Destroy the bed for a team.
         */
        set(bedDestroyed) {
            field = bedDestroyed
            val plugin = BedWars.INSTANCE
            if (!bedDestroyed) {
                if (plugin.versionSupport.isBed(bed.block.type)) {
                    plugin.logger.severe("Bed not set for team: $name in arena: ${arena.name}")
                    return
                }
                plugin.versionSupport.colorBed(this)
            } else {
                bed.block.type = Material.AIR
            }
            for (bh in beds.values) {
                bh.hide()
                bh.show()
            }
        }
    override val killDropsLocation = getDropsLocation()

    // team generators
    override val generators = mutableListOf<IGenerator>()

    // team upgrade name, tier
    override val teamUpgradeTiers = ConcurrentHashMap<String, Int>()

    // Potion effects for teammates from the upgrades
    var teamEffects = mutableListOf<PotionEffect>()
        private set

    // Potion effects for teammates on base only
    override var baseEffects = mutableListOf<PotionEffect>()

    // Enchantments for bows
    override var bowsEnchantments = mutableListOf<TeamEnchant>()

    // Enchantments for swords
    override var swordsEnchantments = mutableListOf<TeamEnchant>()

    // Enchantments for armors
    override var armorsEnchantments = mutableListOf<TeamEnchant>()

    // Used for show/ hide bed hologram
    val beds = mutableMapOf<UUID, BedHolo>()

    // Queued traps
    override val activeTraps = mutableListOf<EnemyBaseEnterTrap>()

    // Amount of dragons for Sudden Death phase
    override var dragons = 1

    // Player cache, used for losers stats and rejoin
    @Deprecated("")
    override var membersCache = mutableListOf<Player>()
    override val identity: UUID

    init {
        Language.saveIfNotExists(
            ConfigPath.TEAM_NAME_PATH.replace("{arena}", arena.name).replace("{team}", name), name
        )
        arena.regionsList += Cuboid(spawn, arena.config.getInt(ConfigPath.ARENA_SPAWN_PROTECTION), true)

        identity = UUID.randomUUID()
    }

    private fun getDropsLocation(): Vector {
        val drops = arena.config.getArenaLoc("Team.$name.${ConfigPath.ARENA_TEAM_KILL_DROPS_LOC}")
        if (drops != null) return Vector(drops.blockX + 0.5, drops.blockY.toDouble(), drops.blockZ + 0.5)
        val gen = generators.filter { it.type == GeneratorOre.IRON || it.type == GeneratorOre.GOLD }
        if (gen.isEmpty()) return Vector(spawn.x, spawn.y, spawn.z)
        return Vector(
            gen[0].location.x,
            gen[0].location.y,
            gen[0].location.z
        )
    }

    override val size get() = members.size

    /**
     * Add a new member to the team
     */
    override fun addPlayers(vararg players: Player) {
        members.removeIf { it in players }
        members.addAll(players)
        membersCache.removeIf { it in players }
        membersCache.addAll(players)
        players.forEach { BedHolo(it, arena) }
    }

    /**
     * first spawn
     */
    override fun firstSpawn(player: Player) {
        player.apply {
            teleportSafe(spawn)
            gameMode = GameMode.SURVIVAL
            canPickupItems = true
        }
        BedWars.INSTANCE.versionSupport.setCollide(player, arena, true)
        sendDefaultInventory(player, true)
        Bukkit.getPluginManager().callEvent(PlayerFirstSpawnEvent(player, arena))
    }

    /**
     * Spawn shopkeepers for target team (if enabled).
     */
    override fun spawnNPCs() {
        if (members.isEmpty() && arena.config.getBoolean(ConfigPath.ARENA_DISABLE_NPCS_FOR_EMPTY_TEAMS)) return

        val upgradeLoc = arena.config.getArenaLoc("Team.$name.Upgrade")!!
        val shopLoc = arena.config.getArenaLoc("Team.$name.Shop")!!

        val plugin = BedWars.INSTANCE
        val nms = plugin.versionSupport
        plugin.run(delay = 20) {
            nms.colorBed(this)
            nms.spawnShop(
                upgradeLoc,
                (if (arena.maxInTeam > 1) Messages.NPC_NAME_TEAM_UPGRADES else Messages.NPC_NAME_SOLO_UPGRADES),
                arena.players,
                arena
            )
            nms.spawnShop(
                shopLoc,
                (if (arena.maxInTeam > 1) Messages.NPC_NAME_TEAM_SHOP else Messages.NPC_NAME_SOLO_SHOP),
                arena.players,
                arena
            )
        }

        val c1 = Cuboid(
            upgradeLoc,
            arena.config.getInt(ConfigPath.ARENA_UPGRADES_PROTECTION),
            true
        )
        c1.minY -= 1
        c1.maxY += 4
        arena.regionsList += c1

        val c2 = Cuboid(
            shopLoc,
            arena.config.getInt(ConfigPath.ARENA_SHOP_PROTECTION),
            true
        )
        c2.minY -= 1
        c2.maxY += 4
        arena.regionsList += c2
    }

    /**
     * Rejoin a team
     */
    override fun reJoin(player: Player) {
        reJoin(player, BedWars.INSTANCE.mainConfig.getInt(ConfigPath.GENERAL_CONFIGURATION_RE_SPAWN_COUNTDOWN))
    }

    override fun reJoin(player: Player, respawnTime: Int) {
        addPlayers(player)
        arena.startRespawnSession(player, respawnTime)
    }

    /**
     * Gives the start inventory
     */
    override fun sendDefaultInventory(player: Player, clear: Boolean) {
        if (clear) player.inventory.clear()
        var path = "${ConfigPath.GENERAL_CONFIGURATION_DEFAULT_ITEMS}.${arena.group}"
        val plugin = BedWars.INSTANCE
        val config = plugin.mainConfig
        if (path !in config) path = "${ConfigPath.GENERAL_CONFIGURATION_DEFAULT_ITEMS}.Default"

        for (s in config.getStringList(path)) {
            if (s.isEmpty()) continue
            val parm = s.split(",")

            val material = try {
                Material.valueOf(parm[0])
            } catch (_: Exception) {
                plugin.logger.severe("${parm[0]} is not an material at: $s (config)")
                continue
            }
            var amount = if (parm.size > 1) parm[1].toIntOrNull() else 1
            if (amount == null) {
                plugin.logger.severe("${parm[1]} is not an integer at: $s (config)")
                amount = 1
            }

            var data = if (parm.size > 2) parm[2].toShortOrNull() else 0
            if (data == null) {
                plugin.logger.severe("${parm[2]} is not an integer at: $s (config)")
                data = 0
            }
            var item = ItemStack(material, amount, data)

            val nms = plugin.versionSupport
            item.editMeta {
                if (parm.size > 3) setDisplayName(ChatColor.translateAlternateColorCodes('&', parm[3]))
                nms.setUnbreakable(this)
            }

            item = nms.addCustomData(item, "DEFAULT_ITEM")

            val inventory = player.inventory.contents.filter { it != null && item.type != Material.AIR }
            if (when {
                nms.isSword(item) -> inventory.none { nms.isSword(item) }
                nms.isBow(item) -> inventory.none { nms.isBow(it) }
                else -> true
            }) player.inventory.addItem(item)
        }
        sendArmor(player)
    }

    override fun defaultSword(player: Player) {
        var path = "${ConfigPath.GENERAL_CONFIGURATION_DEFAULT_ITEMS}.${arena.group}"
        val plugin = BedWars.INSTANCE
        val config = plugin.mainConfig
        if (path !in config) path = "${ConfigPath.GENERAL_CONFIGURATION_DEFAULT_ITEMS}.Default"

        for (s in config.getStringList(path)) {
            val parm = s.split(",")
            if (parm.isEmpty()) continue

            val material = try {
                Material.valueOf(parm[0])
            } catch (_: Exception) {
                plugin.logger.severe("${parm[0]} is not an material at: $s (config)")
                continue
            }

            var amount = if (parm.size > 1) parm[1].toIntOrNull() else 1
            if (amount == null) {
                plugin.logger.severe("${parm[1]} is not an integer at: $s (config)")
                amount = 1
            }

            var data = if (parm.size > 2) parm[2].toShortOrNull() else 0
            if (data == null) {
                plugin.logger.severe("${parm[2]} is not an integer at: $s (config)")
                data = 0
            }

            var item = ItemStack(material, amount, data)
            val nms = plugin.versionSupport
            item.editMeta {
                if (parm.size > 3) {
                    setDisplayName(ChatColor.translateAlternateColorCodes('&', parm[3]))
                }
                nms.setUnbreakable(this)
            }

            item = nms.addCustomData(item, "DEFAULT_ITEM")

            if (!nms.isSword(item)) continue
            player.inventory.addItem(item)
            break
        }
    }

    /**
     * Spawn iron and gold generators
     */
    fun spawnGenerators() {
        for (type in arrayOf("Iron", "Gold")) {
            val config = arena.config
            val o = config.get("Team.$name.$type")
            val locs = if (o is String)
                listOf(config.getArenaLoc("Team.$name.$type"))
            else config.getArenaLocations("Team.$name.$type")

            for (loc in locs) {
                val gen = Generator(loc ?: continue, arena, GeneratorOre.valueOf(type.uppercase()), this)
//                arena.oreGenerators += gen
                generators += gen
            }
        }
    }

    /**
     * Respawn a member
     */
    override fun respawnMember(player: Player) {
        val plugin = BedWars.INSTANCE
        reSpawnInvulnerability[player.uniqueId] = System.currentTimeMillis() + plugin.mainConfig.getInt(ConfigPath.GENERAL_CONFIGURATION_RE_SPAWN_INVULNERABILITY)

        val nms = plugin.versionSupport
        player.apply {
            teleportSafe(spawn)
            velocity = Vector(0, 0, 0)
            removePotionEffect(PotionEffectType.INVISIBILITY)
            nms.setCollide(this, arena, true)
            allowFlight = false
            isFlying = false
            health = 20.0
        }

        plugin.run(delay = 8) {
            arena.respawnSessions -= player //Fixes https://github.com/andrei1058/BedWars1058/issues/669

            for (inGame in arena.players) {
                if (inGame == player) continue
                nms.showPlayer(player, inGame)
                nms.showPlayer(inGame, player)
            }
            for (spectator in arena.spectators) {
                nms.showPlayer(player, spectator)
            }
        }

        nms.sendTitle(player, Language.getMsg(player, Messages.PLAYER_DIE_RESPAWNED_TITLE), "", 0, 20, 10)

        sendDefaultInventory(player, false)
        ShopCache.getShopCache(player.uniqueId)
            ?.managePermanentsAndDowngradables(arena)

        for (ef in baseEffects + teamEffects) {
            player.addPotionEffect(ef, true)
        }

        if (bowsEnchantments.isNotEmpty()) {
            for (i in player.inventory.contents) {
                if (i == null) continue
                if (i.type == Material.BOW) i.editMeta {
                    for ((enchantment, amplifier) in bowsEnchantments) {
                        addEnchant(enchantment, amplifier, true)
                    }
                }
                player.updateInventory()
            }
        }
        if (swordsEnchantments.isNotEmpty()) {
            for (i in player.inventory.contents) {
                if (i == null) continue
                if (nms.isSword(i)) i.editMeta {
                    for (e in swordsEnchantments) {
                        addEnchant(e.enchantment, e.amplifier, true)
                    }
                }
                player.updateInventory()
            }
        }
        if (armorsEnchantments.isNotEmpty()) {
            for (i in player.inventory.armorContents) {
                if (i == null) continue
                if (nms.isArmor(i)) i.editMeta {
                    for ((enchantment, amplifier) in armorsEnchantments) {
                        addEnchant(enchantment, amplifier, true)
                    }
                }
                player.updateInventory()
            }
        }
        plugin.server.pluginManager.callEvent(PlayerReSpawnEvent(player, arena))
        nms.sendPlayerSpawnPackets(player, arena)

        plugin.run(delay = 10) {
            nms.sendPlayerSpawnPackets(player, arena)

            // #274
            for (on in arena.showTime.keys) {
                nms.hideArmor(on, player)
            }
        }

        /*if (!config.getBoolean(ConfigPath.GENERAL_CONFIGURATION_PERFORMANCE_DISABLE_RESPAWN_PACKETS)) {
            plugin.run(delay = 12) { nms.invisibilityFix(player, arena) }
            plugin.run(delay = 30) { nms.invisibilityFix(player, arena) }
            plugin.run(delay = 25) { arena.players.forEach { nms.showPlayer(it, player) } }
        }*/

        // un-vanish from respawn
        /*plugin.run(delay = 20) {
            arena.players.forEach {
                nms.showPlayer(player, it)
                nms.showArmor(player, it)
                nms.showPlayer(it, player)
                nms.showArmor(it, player)
            }
            arena.spectators.forEach {
                nms.showPlayer(player, it)
                nms.showArmor(player, it)
            }
        }*/
        Sounds.playSound("player-re-spawn", player)
    }

    /**
     * Create a leather armor with team's color
     */
    private fun createArmor(material: Material) = ItemStack(material).apply {
        editMeta {
            this as LeatherArmorMeta?
            setColor(this@BedWarsTeam.color.bukkit)
            BedWars.INSTANCE.versionSupport.setUnbreakable(this)
        }
    }

    /**
     * Equip a player with default armor
     */
    override fun sendArmor(player: Player) {
        player.inventory.apply {
            if (helmet == null) helmet = createArmor(Material.LEATHER_HELMET)
            if (chestplate == null) chestplate = createArmor(Material.LEATHER_CHESTPLATE)
            if (leggings == null) leggings = createArmor(Material.LEATHER_LEGGINGS)
            if (boots == null) boots = createArmor(Material.LEATHER_BOOTS)
        }
    }

    /**
     * Creates a hologram on the team bed's per player
     */
    inner class BedHolo(p: Player, val arena: Arena) {
        private lateinit var armorStand: ArmorStand
        private val p = p.uniqueId
        var isHidden: Boolean = false
            private set
        private var bedDestroyed = false

        init {
            spawn()
            beds[p.uniqueId] = this
        }

        fun spawn() {
            if (!arena.config.getBoolean(ConfigPath.ARENA_USE_BED_HOLO)) return
            armorStand = bed.world!!.spawnEntity(bed.block.location.add(+0.5, 1.0, +0.5), EntityType.ARMOR_STAND) as ArmorStand
            armorStand.apply {
                setGravity(false)
                if (isBedDestroyed) {
                    customName = Language.getMsg(Bukkit.getPlayer(p), Messages.BED_HOLOGRAM_DESTROYED)
                    bedDestroyed = true
                } else {
                    customName = Language.getMsg(Bukkit.getPlayer(p), Messages.BED_HOLOGRAM_DEFEND)
                }
                isCustomNameVisible = true
                removeWhenFarAway = false
                canPickupItems = false
                setArms(false)
                setBasePlate(false)
                isMarker = true
                isVisible = false
            }
            val nms = BedWars.INSTANCE.versionSupport
            for (p2 in arena.world.players) {
                if (p !== p2.uniqueId) {
                    nms.hideEntity(armorStand, p2)
                }
            }
        }

        fun hide() {
            if (!arena.config.getBoolean(ConfigPath.ARENA_USE_BED_HOLO)) return
            if (bedDestroyed) return
            isHidden = true
            armorStand.remove()
        }

        fun destroy() {
            if (!arena.config.getBoolean(ConfigPath.ARENA_USE_BED_HOLO)) return
            armorStand.remove()
            beds -= p
        }

        fun show() {
            if (!arena.config.getBoolean(ConfigPath.ARENA_USE_BED_HOLO)) return
            isHidden = false
            spawn()
        }
    }

    /**
     * Used when someone buys a new potion effect with apply == members
     */
    override fun addTeamEffect(effect: PotionEffectType, amplifier: Int, duration: Int) {
        teamEffects += PotionEffect(effect, duration, amplifier)
        for (p in members) {
            p.addPotionEffect(PotionEffect(effect, duration, amplifier), true)
        }
    }

    /**
     * Used when someone buys a new potion effect with apply == base
     */
    override fun addBaseEffect(effect: PotionEffectType, amplifier: Int, duration: Int) {
        baseEffects += PotionEffect(effect, duration, amplifier)
        for (member in members.toList()) {
            if (member.location.distance(bed) <= arena.islandRadius) {
                for (e in baseEffects) {
                    member.addPotionEffect(e, true)
                }
            }
        }
    }

    /**
     * Used when someone buys a bew enchantment with apply == bow
     */
    override fun addBowEnchantment(enchantment: Enchantment, amplifier: Int) {
        bowsEnchantments += TeamEnchant(enchantment, amplifier)
        for (member in members) {
            for (item in member.inventory.contents) {
                if (item == null || item.type != Material.BOW) continue
                item.editMeta { addEnchant(enchantment, amplifier, true) }
            }
            member.updateInventory()
        }
    }

    /**
     * Used when someone buys a new enchantment with apply == sword
     */
    override fun addSwordEnchantment(e: Enchantment, a: Int) {
        swordsEnchantments += TeamEnchant(e, a)
        val nms = BedWars.INSTANCE.versionSupport
        for (p in members) {
            for (i in p.inventory.contents) {
                if (i == null) continue
                if (nms.isSword(i) || nms.isAxe(i)) {
                    i.editMeta { addEnchant(e, a, true) }
                }
            }
            p.updateInventory()
        }
    }

    /**
     * Used when someone buys a new enchantment with apply == armor
     */
    override fun addArmorEnchantment(e: Enchantment, a: Int) {
        swordsEnchantments += TeamEnchant(e, a)
        val plugin = BedWars.INSTANCE
        val nms = plugin.versionSupport
        for (player in members) {
            for (i in player.inventory.armorContents) {
                if (i == null) continue
                if (nms.isArmor(i)) {
                    i.editMeta { addEnchant(e, a, true) }
                }
            }
            player.updateInventory()
        }

        // #274
        plugin.run(delay = 20) {
            for (member in members) {
                if (!member.hasPotionEffect(PotionEffectType.INVISIBILITY)) continue
                arena.allPlayers.forEach { nms.hideArmor(member, it) }
            }
        }
    }

    override fun isMember(player: Player) = player in members

    /**
     * Getter, setter etc.
     */
    override fun wasMember(u: UUID) = membersCache.any { it.uniqueId == u }

    override fun getDisplayName(language: Language) = language.m(ConfigPath.TEAM_NAME_PATH
        .replace("{arena}", arena.name)
        .replace("{team}", name)
    )

    fun getBedHolo(p: Player) = beds[p.uniqueId]

    @get:Deprecated("")
    override val ironGenerator get() = generators.firstOrNull { it.type == GeneratorOre.IRON }

    @get:Deprecated("")
    override val goldGenerator get() = generators.firstOrNull { it.type == GeneratorOre.GOLD }

    @get:Deprecated("")
    override var emeraldGenerator get() = generators.firstOrNull { it.type == GeneratorOre.EMERALD }
        set(value) {
            generators += value!!
        }

    override fun destroyData() {
        generators.onEach { it.destroyData() }.clear()
        members.clear()
        teamEffects.clear()
        bowsEnchantments.clear()
        swordsEnchantments.clear()
        armorsEnchantments.clear()
        activeTraps.clear()
        membersCache.clear()
    }

    @Deprecated("")
    override fun destroyBedHolo(player: Player) {
        beds[player.uniqueId]?.destroy()
    }

    override fun isBed(location: Location): Boolean {
        for (x in location.blockX - 1..<location.blockX + 1) {
            for (z in location.blockZ - 1..<location.blockZ + 1) {
                if (bed.blockX == x && bed.blockY == location.blockY && bed.blockZ == z) {
                    return true
                }
            }
        }
        return false
    }

    companion object {
        // Invulnerability at re-spawn
        // Fall invulnerability when teammates respawn
        var reSpawnInvulnerability = mutableMapOf<UUID, Long>()
    }
}
