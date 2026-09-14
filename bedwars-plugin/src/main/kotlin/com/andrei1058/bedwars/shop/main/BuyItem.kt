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
package com.andrei1058.bedwars.shop.main

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.util.Utils.editMeta
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.shop.IBuyItem
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.configuration.Sounds.playSound
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.PotionMeta
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class BuyItem(
    path: String,
    yml: YamlConfiguration,
    /**
     * Get upgrade identifier.
     * Used to remove old tier items.
     */
    override val upgradeIdentifier: String,
    parent: ContentTier
) : IBuyItem {
    override var itemStack: ItemStack
        private set
    override val isAutoEquip = yml.getBoolean("$path.auto-equip")
    override val isPermanent = yml.getBoolean("$upgradeIdentifier.${ConfigPath.SHOP_CATEGORY_CONTENT_IS_PERMANENT}")
    override val isUnbreakable = yml.getBoolean("$upgradeIdentifier.${ConfigPath.SHOP_CATEGORY_CONTENT_IS_UNBREAKABLE}")

    /**
     * Check if object created properly
     */
    override var isLoaded = true

    /**
     * Create a shop item
     */
    init {
        BedWars.debug("Loading BuyItems: $path")

        val plugin = BedWars.INSTANCE
        var material = yml.getString("$path.material")
        if (material == null) {
            plugin.logger.severe("BuyItem: Material not set at $path")
            material = "AIR"
            isLoaded = false
        }

        val nms = plugin.versionSupport
        itemStack = nms.createItemStack(
            material,
            yml.getInt("$path.amount", 1),
            yml.getInt("$path.data", 1).toShort()
        )

        val name = yml.getString("$path.name")
        if (name != null) itemStack.editMeta {
            setDisplayName(ChatColor.translateAlternateColorCodes('&', "&r$name"))
        }

        val enchants = yml.getString("$path.enchants")
        if (enchants != null) itemStack.editMeta {
            for (enchantment in enchants.split(",")) {
                val array = enchantment.split(" ")

                val enchant = try {
                    Enchantment.getByName(array[0])!!
                } catch (_: Exception) {
                    plugin.logger.severe("BuyItem: Invalid enchants ${array[0]} at: $path.enchants")
                    continue
                }

                val level = if (array.size > 1) try { array[1].toInt() }
                catch (_: Exception) {
                    plugin.logger.severe("BuyItem: Invalid int ${array[1]} at: $path.enchants")
                    continue
                } else 1
                addEnchant(enchant, level, true)
            }
        }

        val potionEffects = yml.getString("$path.potion")
        if (potionEffects != null && itemStack.type == Material.POTION) {
            // 1.16+ custom color
            val potionColor = yml.getString("$path.potion-color")
            if (!potionColor.isNullOrEmpty()) {
                itemStack = nms.setTag(itemStack, "CustomPotionColor", potionColor)
            }

            var customEffectsList = emptyList<PotionEffect>()
            itemStack.editMeta {
                val effects = potionEffects.split(",")
                for (effect in effects) {
                    val array = effect.split(" ")
                    val type = try {
                        PotionEffectType.getByName(array[0].uppercase())!!
                    } catch (_: Exception) {
                        plugin.logger.severe("BuyItem: Invalid potion effect ${array[0]} at: $path.potion")
                        continue
                    }

                    val duration = if (array.size > 1) array[1].toIntOrNull() else 50
                    if (duration == null) {
                        plugin.logger.severe("BuyItem: Invalid int (duration) ${array[1]} at: $path.potion")
                        continue
                    }

                    val amplifier = if (array.size > 2) array[2].toIntOrNull() else 1
                    if (amplifier == null) {
                        plugin.logger.severe("BuyItem: Invalid int (amplifier) ${array[2]} at: $path.potion")
                        continue
                    }

                    if (this !is PotionMeta) return@editMeta
                    addCustomEffect(PotionEffect(type, duration * 20, amplifier), true)
                    customEffectsList = customEffects
                }
            }

            itemStack = nms.setTag(itemStack, "Potion", "minecraft:water")
            if (parent.itemStack.type == Material.POTION && customEffectsList.isNotEmpty()) {
                var parentItemStack = parent.itemStack
                parentItemStack.editMeta {
                    if (this !is PotionMeta) return@editMeta
                    customEffectsList.forEach { addCustomEffect(it, true) }
                }
                parentItemStack = nms.setTag(parentItemStack, "Potion", "minecraft:water")
                parent.itemStack = parentItemStack
            }
        }
    }

    /**
     * Give to a player
     */
    override fun give(player: Player, arena: IArena) {
        var item = itemStack.clone()
        BedWars.debug("Giving BuyItem: " + upgradeIdentifier + " to: " + player.name)

        val team = arena.getTeam(player)
        if (team == null) {
            BedWars.debug("Could not give BuyItem to ${player.name} - TEAM IS NULL")
            return
        }

        val plugin = BedWars.INSTANCE
        val nms = plugin.versionSupport
        item.editMeta {
            if (isPermanent) nms.setUnbreakable(this)
        }

        if (isAutoEquip && nms.isArmor(itemStack)) {
            item.editMeta {
                team.armorsEnchantments.forEach {
                    addEnchant(it.enchantment, it.amplifier, true)
                }
            }

            if (isPermanent) item = nms.setShopUpgradeIdentifier(item, upgradeIdentifier)
            when (item.type) {
                Material.LEATHER_HELMET,
                Material.CHAINMAIL_HELMET,
                Material.IRON_HELMET, Material.DIAMOND_HELMET,
                nms.materialGoldenHelmet(),
                nms.materialNetheriteHelmet() -> player.inventory.helmet = item

                Material.LEATHER_CHESTPLATE,
                Material.CHAINMAIL_CHESTPLATE,
                Material.IRON_CHESTPLATE,
                Material.DIAMOND_CHESTPLATE,
                nms.materialGoldenChestPlate(),
                nms.materialNetheriteChestPlate(),
                nms.materialElytra() -> player.inventory.chestplate = item

                Material.LEATHER_LEGGINGS,
                Material.CHAINMAIL_LEGGINGS,
                Material.IRON_LEGGINGS,
                Material.DIAMOND_LEGGINGS,
                nms.materialGoldenLeggings(),
                nms.materialNetheriteLeggings() -> player.inventory.leggings = item

                else -> player.inventory.boots = item
            }
            player.updateInventory()
            playSound("shop-auto-equip", player)

            plugin.run(delay = 20) {
                // #274
                if (!player.hasPotionEffect(PotionEffectType.INVISIBILITY)) return@run
                arena.players.forEach { nms.hideArmor(player, it) }
            }
            return
        }

        item = nms.colourItem(item, team)
        item.editMeta {
            if (isUnbreakable) nms.setUnbreakable(this)
            mapOf(
                (item.type == Material.BOW) to team.bowsEnchantments,
                (nms.isSword(item) || nms.isAxe(item)) to team.swordsEnchantments
            ).asSequence()
                .filter { it.key }
                .flatMap { it.value }
                .forEach { addEnchant(it.enchantment, it.amplifier, true) }
        }

        if (isPermanent) {
            item = nms.setShopUpgradeIdentifier(item, upgradeIdentifier)
        }

        //Remove swords with lower damage
        if (nms.isSword(item)) player
            .inventory
            .contents
            .asSequence()
            .filterNotNull()
            .filter { it.type != Material.AIR || it === item }
            .filter { nms.isSword(it) }
            .filter { nms.isCustomBedWarsItem(it) }
            .filter { nms.getCustomData(it) == "DEFAULT_ITEM" }
            .filter { nms.getDamage(it) <= nms.getDamage(item) }
            .forEach { player.inventory.remove(it) }
        //
        player.inventory.addItem(item)
        player.updateInventory()
    }
}
