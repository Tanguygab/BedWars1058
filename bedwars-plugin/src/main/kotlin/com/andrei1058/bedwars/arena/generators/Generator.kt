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
package com.andrei1058.bedwars.arena.generators

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.util.Utils.editMeta
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.generator.GeneratorType
import com.andrei1058.bedwars.api.arena.generator.IGenHolo
import com.andrei1058.bedwars.api.arena.generator.IGenerator
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.gameplay.GeneratorUpgradeEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.region.Cuboid
import com.andrei1058.bedwars.api.server.VersionSupport
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.entity.Item
import org.bukkit.inventory.ItemStack
import org.bukkit.util.EulerAngle
import org.bukkit.util.Vector
import java.util.concurrent.ConcurrentLinkedDeque

class Generator(
    location: Location,
    /**
     * Get the arena assigned to this generator.
     */
    override val arena: IArena,
    override val type: GeneratorType,
    override val team: ITeam?
) : IGenerator {
    override val location = if (type == GeneratorOre.EMERALD || type == GeneratorOre.DIAMOND) Location(
        location.world,
        location.blockX + 0.5,
        location.blockY + 1.3,
        location.blockZ + 0.5
    ) else location.add(0.0, 1.3, 0.0)
    override var delay = type.getDelay(arena)
    override var spawnLimit = type.getLimit(arena)
    override var amount = type.getAmount(arena)
    private var upgradeStage = 1
    override var nextSpawn = delay

    override var ore = type.getItem(arena)
        set(value) {
            BedWars.debug("Changing ore for generator at $location from $field to $value")
            field = value
        }
    private var rotate = 0
    private var dropID = 0
    private var up = true

    /**
     * Generator holograms per language <iso></iso>, holo>,>
     */
    override var languageHolograms = mutableMapOf<String, IGenHolo>()

    override lateinit var hologramHolder: ArmorStand
        private set
    override var isStack = BedWars.INSTANCE.configs.generators.getBoolean(ConfigPath.GENERATOR_STACK_ITEMS)

    init {
        BedWars.debug("Initializing new generator at: $location - $type - ${team?.name ?: "NOTEAM"}")

        val c = Cuboid(location, arena.config.getInt(ConfigPath.ARENA_GENERATOR_PROTECTION), true)
        c.maxY += 5
        c.minY -= 2
        arena.regionsList += c
    }

    override fun upgrade() {
        ++upgradeStage
        delay = type.getDelay(arena, upgradeStage)
        spawnLimit = type.getLimit(arena, upgradeStage)
        amount = type.getAmount(arena, upgradeStage)

        languageHolograms.values.forEach {
            val lang = Language.getLanguageByIso(it.iso)
            val tier = lang.m(if (upgradeStage == 2) Messages.FORMATTING_GENERATOR_TIER2 else Messages.FORMATTING_GENERATOR_TIER3)
            it.setTierName(lang
                .m(Messages.GENERATOR_HOLOGRAM_TIER)
                .replace("{tier}", tier)
            )
        }

        Bukkit.getPluginManager().callEvent(GeneratorUpgradeEvent(this))
    }

    override fun spawn() {
        if (arena.status != GameState.PLAYING) return

        if (nextSpawn != 0) {
            --nextSpawn

            for (hologram in languageHolograms.values) {
                hologram.setTimerName(Language
                    .getLanguageByIso(hologram.iso)
                    .m(Messages.GENERATOR_HOLOGRAM_TIMER)
                    .replace("{seconds}", "$nextSpawn")
                )
            }
            return
        }

        nextSpawn = delay

        if (spawnLimit != 0) {
            var oreCount = 0

            for (entities in location.world!!.getNearbyEntities(location, 3.0, 3.0, 3.0)) {
                val item = entities as? Item ?: continue
                if (item.itemStack.type == ore.type) ++oreCount
                if (oreCount >= spawnLimit) return
            }
            nextSpawn = delay
        }

        if (team == null) {
            dropItem(location)
            return
        }

        if (team.members.size == 1) {
            dropItem(location)
            return
        }

        if (!BedWars.INSTANCE.mainConfig.getBoolean(ConfigPath.GENERAL_CONFIGURATION_ENABLE_GEN_SPLIT)) {
            dropItem(location)
            return
        }

        val players = location.world!!
            .getNearbyEntities(location, 1.0, 1.0, 1.0)
            .filterIsInstance<Player>()
            .filter { arena.isPlayer(it) }
            .toList()

        if (players.size <= 1) {
            dropItem(location)
            return
        }

        for (player in players) {
            val item = ore.clone()
            item.amount = amount
            player.apply {
                playSound(
                    location,
                    Sound.valueOf(BedWars.getForCurrentVersion("ENTITY_ITEM_PICKUP", "ITEM_PICKUP", "ENTITY_ITEM_PICKUP")),
                    .6f,
                    1.3f
                )
                val excess = inventory.addItem(item).values
                excess.forEach { dropItem(location, it.amount) }
            }
        }
    }

    private fun dropItem(location: Location, amount: Int) {
        (0 ..< amount).forEach { _ ->
            val item = ItemStack(ore)
            if (!isStack) item.editMeta {
                setDisplayName("custom${dropID++}")
            }
            location.world!!
                .dropItem(location, item)
                .velocity = Vector(0, 0, 0)
        }
    }

    /**
     * Drop item stack with ID
     */
    override fun dropItem(location: Location) = dropItem(location, amount)

    override fun rotate() {
        if (up) {
            if (rotate >= 540) up = false
            rotate += when {
                rotate > 500 -> 1
                rotate > 470 -> 2
                rotate > 450 -> 3
                else -> 4
            }
        } else {
            if (rotate <= 0) up = true
            rotate -= when {
                rotate > 120 -> 4
                rotate > 90 -> 3
                rotate > 70 -> 2
                else -> 1
            }
        }
        hologramHolder.headPose = EulerAngle(.0, Math.toRadians(rotate.toDouble()), .0)
    }

    override fun disable() {
        if (ore.type == Material.EMERALD || ore.type == Material.DIAMOND)
            rotation.remove(this)
        languageHolograms.values.forEach { it.destroy() }
        languageHolograms.clear()
    }

    override fun updateHolograms(player: Player, iso: String) {
        languageHolograms.forEach { it.value.updateForPlayer(player, iso) }
    }

    override fun enableRotation() {
        //loadDefaults(false);
        //if (getType() == GeneratorType.EMERALD || getType() == GeneratorType.DIAMOND) {
        rotation += this
        for (lang in Language.languages) {
            val iso = lang.iso
            if (iso in languageHolograms) continue
            languageHolograms[iso] = Hologram(this, iso)
        }
        languageHolograms.values.forEach { it.updateForAll() }

        hologramHolder = createArmorStand(null, location.clone().add(0.0, 0.5, 0.0)).apply {
            setHelmet(ItemStack(if (this@Generator.type == GeneratorOre.DIAMOND) Material.DIAMOND_BLOCK else Material.EMERALD_BLOCK))
        }
        //}
    }

    override fun destroyData() {
        rotation.remove(this)
        languageHolograms.clear()
    }

    companion object {
        val rotation = ConcurrentLinkedDeque<Generator>()

        internal fun createArmorStand(name: String?, l: Location) = VersionSupport
            .createArmorStand(name, l)
            .apply {
                removeWhenFarAway = false
                canPickupItems = false
                setArms(false)
                setBasePlate(false)
                isMarker = true
            }
    }
}
