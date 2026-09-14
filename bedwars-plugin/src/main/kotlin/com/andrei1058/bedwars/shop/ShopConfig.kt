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
package com.andrei1058.bedwars.shop

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.debug
import com.andrei1058.bedwars.BedWars.Companion.getForCurrentVersion
import com.andrei1058.bedwars.api.util.Utils.editMeta
import com.andrei1058.bedwars.api.configuration.ConfigManager
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.shop.listeners.*
import com.andrei1058.bedwars.shop.main.QuickBuyButton
import com.andrei1058.bedwars.shop.main.ShopCategory
import com.andrei1058.bedwars.shop.main.ShopIndex
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

class ShopConfig(private val plugin: BedWars) : ConfigManager(plugin, "shop", plugin.dataFolder.path) {
    init {
        options().copyDefaults(true).setHeader(listOf("Shop with quick buy and tiers"))

        default(
            //quick buy
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_MATERIAL to "NETHER_STAR",
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_AMOUNT to 1,
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_DATA to 0,
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_ENCHANTED to false,

            ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_MATERIAL to getForCurrentVersion("RED_STAINED_GLASS_PANE", "STAINED_GLASS_PANE"),
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_AMOUNT to 1,
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_DATA to 4,
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_EMPTY_ENCHANTED to false,

            //separator
            ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_MATERIAL to getForCurrentVersion("GRAY_STAINED_GLASS_PANE", "STAINED_GLASS_PANE"),
            ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_AMOUNT to 1,
            ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_DATA to 7,
            ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_ENCHANTED to false,

            ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_MATERIAL to getForCurrentVersion("GREEN_STAINED_GLASS_PANE", "STAINED_GLASS_PANE"),
            ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_AMOUNT to 1,
            ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_DATA to 13,
            ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_ENCHANTED to false,

            //specials
            ConfigPath.SHOP_SPECIAL_SILVERFISH_ENABLE to true,
            ConfigPath.SHOP_SPECIAL_SILVERFISH_MATERIAL to getForCurrentVersion("SNOWBALL", "SNOW_BALL"),
            ConfigPath.SHOP_SPECIAL_SILVERFISH_DATA to 0,
            ConfigPath.SHOP_SPECIAL_SILVERFISH_HEALTH to 8.0,
            ConfigPath.SHOP_SPECIAL_SILVERFISH_DAMAGE to 4.0,
            ConfigPath.SHOP_SPECIAL_SILVERFISH_SPEED to 0.25,
            ConfigPath.SHOP_SPECIAL_SILVERFISH_DESPAWN to 15,

            ConfigPath.SHOP_SPECIAL_IRON_GOLEM_ENABLE to true,
            ConfigPath.SHOP_SPECIAL_IRON_GOLEM_MATERIAL to getForCurrentVersion("HORSE_SPAWN_EGG", "MONSTER_EGG"),
            ConfigPath.SHOP_SPECIAL_IRON_GOLEM_DATA to 0,
            ConfigPath.SHOP_SPECIAL_IRON_GOLEM_HEALTH to 100.0,
            ConfigPath.SHOP_SPECIAL_IRON_GOLEM_DESPAWN to 240,
            ConfigPath.SHOP_SPECIAL_IRON_GOLEM_SPEED to 0.25,

            ConfigPath.SHOP_SPECIAL_TOWER_ENABLE to true,
            ConfigPath.SHOP_SPECIAL_TOWER_MATERIAL to "CHEST"
        )

        if (isFirstTime) {
            //quick buy defaults
            listOf(
                Triple(19, "blocks", "wool"),
                Triple(20, "melee", "stone-sword"),
                Triple(21, "armor", "chainmail"),
                Triple(23, "ranged", "bow1"),
                Triple(24, "potions", "speed-potion"),
                Triple(25, "utility", "tnt"),
                Triple(28, "blocks", "wood"),
                Triple(29, "melee", "iron-sword"),
                Triple(30, "armor", "iron-armor"),
                Triple(31, "tools", "shears"),
                Triple(32, "ranged", "arrow"),
                Triple(33, "potions", "jump-potion"),
                Triple(34, "utility", "water-bucket")
            ).forEachIndexed { i, (slot, category, item) ->
                val i = i+1
                default(
                    "${ConfigPath.SHOP_QUICK_DEFAULTS_PATH}.element$i.path" to "$category-category.category-content.$item",
                    "${ConfigPath.SHOP_QUICK_DEFAULTS_PATH}.element$i.path" to slot
                )
            }

            //save default shop categories if the file was just generated
            //so the user can remove categories or add new ones
            //BLOCKS CATEGORY
            val orangeTerracotta = getForCurrentVersion("ORANGE_TERRACOTTA", "STAINED_CLAY")
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, 1, orangeTerracotta)

            val wool = getForCurrentVersion("WHITE_WOOL", "WOOL")
            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "wool", 19, "tier1", wool, 4, "iron", 16)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "wool", "tier1", "wool", wool, 16)

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "clay", 20, "tier1", orangeTerracotta, 12, "iron", 16, 1)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "clay", "tier1", "clay", orangeTerracotta, 16, 1)

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "glass", 21, "tier1", "GLASS", 12, "iron", 4)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "glass", "tier1", "glass", "GLASS", 4)

            val endStone = getForCurrentVersion("END_STONE", "ENDER_STONE")
            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "stone", 22, "tier1", endStone, 24, "iron", 16)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "stone", "tier1", "stone", endStone, 16)

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "ladder", 23, "tier1", "LADDER", 4, "iron", 16)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "ladder", "tier1", "ladder", "LADDER", 16)

            val oak = getForCurrentVersion("OAK_WOOD", "WOOD")
            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "wood", 24, "tier1", oak, 4, "gold", 16)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "wood", "tier1", "wood", oak, 16)

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "obsidian", 25, "tier1", "OBSIDIAN", 4, "emerald", 4)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "obsidian", "tier1", "obsidian", "OBSIDIAN", 4)

            //

            //MELEE CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_MELEE, 2, getForCurrentVersion("GOLDEN_SWORD", "GOLD_SWORD"))

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "stone-sword", 19, "tier1", "STONE_SWORD", 10, "iron", unbreakable = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "stone-sword", "tier1", "sword", "STONE_SWORD")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "iron-sword", 20, "tier1", "IRON_SWORD", 7, "gold", unbreakable = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "iron-sword", "tier1", "sword", "IRON_SWORD")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "diamond-sword", 21, "tier1", "DIAMOND_SWORD", 4, "emerald", unbreakable = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "diamond-sword", "tier1", "sword", "DIAMOND_SWORD")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "stick", 22, "tier1", "STICK", 10, "gold", enchant = true, unbreakable = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_MELEE, "stick", "tier1", "stick", "STICK", enchant = "KNOCKBACK 1")

            //ARMOR CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, 3, "CHAINMAIL_BOOTS")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "chainmail", 19, "tier1", "CHAINMAIL_BOOTS", 40, "iron", permanent = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "chainmail", "tier1", "boots", "CHAINMAIL_BOOTS", autoEquip = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "chainmail", "tier1", "leggings", "CHAINMAIL_LEGGINGS", autoEquip = true)

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "iron-armor", 20, "tier1", "IRON_BOOTS", 12, "gold", permanent = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "iron-armor", "tier1", "boots", "IRON_BOOTS", autoEquip = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "iron-armor", "tier1", "leggings", "IRON_LEGGINGS", autoEquip = true)

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "diamond-armor", 21, "tier1", "DIAMOND_BOOTS", 6, "emerald", permanent = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "diamond-armor", "tier1", "boots", "DIAMOND_BOOTS", autoEquip = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "diamond-armor", "tier1", "leggings", "DIAMOND_LEGGINGS", autoEquip = true)

            //TOOLS CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, 4, "STONE_PICKAXE")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "shears", 19, "tier1", "SHEARS", 20, "iron", permanent = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "shears", "tier1", "shears", "SHEARS")

            //pickaxe
            val woodenPickaxe = getForCurrentVersion("WOODEN_PICKAXE", "WOOD_PICKAXE")
            addCategoryContentTier(
                ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", 20, "tier1", woodenPickaxe, 10, "iron",
                permanent = true, downgradable = true
            )
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", "tier1", "wooden-pickaxe", woodenPickaxe)
            addCategoryContentTier(
                ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", 20, "tier2",
                "IRON_PICKAXE", 10, "iron", enchant = true, permanent = true, downgradable = true
            )
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", "tier2", "iron-pickaxe", "IRON_PICKAXE", enchant = "DIG_SPEED 2")

            val goldenPickaxe = getForCurrentVersion("GOLDEN_PICKAXE", "GOLD_PICKAXE")
            addCategoryContentTier(
                ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", 20, "tier3", goldenPickaxe, 3, "gold",
                enchant = true, permanent = true, downgradable = true
            )
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", "tier3", "gold-pickaxe", goldenPickaxe, enchant = "DIG_SPEED 3,DAMAGE_ALL 2")

            addCategoryContentTier(
                ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", 20, "tier4", "DIAMOND_PICKAXE", 6, "gold",
                enchant = true, permanent = true, downgradable = true
            )
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "pickaxe", "tier4", "diamond-pickaxe", "DIAMOND_PICKAXE", enchant = "DIG_SPEED 3")

            //axe
            val woodenAxe = getForCurrentVersion("WOODEN_AXE", "WOOD_AXE")
            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", 21, "tier1", woodenAxe, 10, "iron", permanent = true, downgradable = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", "tier1", "wooden-axe", woodenAxe, enchant = "DIG_SPEED 1")

            addCategoryContentTier(
                ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", 21, "tier2", "IRON_AXE", 10, "iron",
                enchant = true, permanent = true, downgradable = true
            )
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", "tier2", "iron-axe", "IRON_AXE", enchant = "DIG_SPEED 1")

            val goldenAxe = getForCurrentVersion("GOLDEN_AXE", "GOLD_AXE")
            addCategoryContentTier(
                ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", 21, "tier3", goldenAxe, 3, "gold",
                enchant = true, permanent = true, downgradable = true
            )
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", "tier3", "gold-axe", goldenAxe, enchant = "DIG_SPEED 2")

            addCategoryContentTier(
                ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", 21, "tier4", "DIAMOND_AXE", 6, "gold",
                enchant = true, permanent = true, downgradable = true
            )
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "axe", "tier4", "diamond-axe", "DIAMOND_AXE", enchant = "DIG_SPEED 3")

            //RANGED CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_RANGED, 5, "BOW")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "arrow", 19, "tier1", "ARROW", 2, "gold", 8)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "arrow", "tier1", "arrows", "ARROW", 8)

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow1", 20, "tier1", "BOW", 12, "gold")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow1", "tier1", "bow", "BOW", 1, 0)

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow2", 21, "tier1", "BOW", 24, "gold", enchant = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow2", "tier1", "bow", "BOW", 1, 0, "ARROW_DAMAGE 1")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow3", 22, "tier1", "BOW", 6, "emerald", enchant = true)
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_RANGED, "bow3", "tier1", "bow", "BOW", 1, 0, "ARROW_DAMAGE 1,ARROW_KNOCKBACK 1")

            //POTIONS CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, 6, getForCurrentVersion("BREWING_STAND", "BREWING_STAND_ITEM"))

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "jump-potion", 20, "tier1", "POTION", 1, "emerald")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "jump-potion", "tier1", "jump", "POTION", itemName = "Jump Potion", potion = "JUMP 45 5")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "speed-potion", 19, "tier1", "POTION", 1, "emerald")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "speed-potion", "tier1", "speed", "POTION", itemName = "Speed Potion", potion = "SPEED 45 2")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "invisibility", 21, "tier1", "POTION", 2, "emerald")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_POTIONS, "invisibility", "tier1", "invisibility", "POTION", itemName = "Invisibility Potion", potion =  "INVISIBILITY 30 1")

            //UTILITY CATEGORY
            addDefaultShopCategory(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, 7, "TNT")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "golden-apple", 19, "tier1", "GOLDEN_APPLE", 3, "gold")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "golden-apple", "tier1", "apple", "GOLDEN_APPLE")

            val snowball = getForCurrentVersion("SNOWBALL", "SNOW_BALL")
            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "bedbug", 20, "tier1", snowball, 40, "iron")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "bedbug", "tier1", "bedbug", snowball)

            val horseSpawnEgg = getForCurrentVersion("HORSE_SPAWN_EGG", "MONSTER_EGG")
            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "dream-defender", 21, "tier1", horseSpawnEgg, 120, "iron")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "dream-defender", "tier1", "defender", horseSpawnEgg)

            val fireball = getForCurrentVersion("FIRE_CHARGE", "FIREBALL")
            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "fireball", 22, "tier1", fireball, 40, "iron")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "fireball", "tier1", "fireball", fireball, itemName = "Fireball")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "tnt", 23, "tier1", "TNT", 4, "gold")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "tnt", "tier1", "tnt", "TNT")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "ender-pearl", 24, "tier1", "ENDER_PEARL", 4, "emerald")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "ender-pearl", "tier1", "ender-pearl", "ENDER_PEARL")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "water-bucket", 25, "tier1", "WATER_BUCKET", 4, "gold")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "water-bucket", "tier1", "water-bucket", "WATER_BUCKET")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "bridge-egg", 28, "tier1", "EGG", 3, "emerald")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "bridge-egg", "tier1", "egg", "EGG")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "magic-milk", 29, "tier1", "MILK_BUCKET", 4, "gold")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "magic-milk", "tier1", "milk", "MILK_BUCKET")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "sponge", 30, "tier1", "SPONGE", 3, "gold")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "sponge", "tier1", "sponge", "SPONGE")

            addCategoryContentTier(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "tower", 31, "tier1", "CHEST", 24, "iron")
            addBuyItem(ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "tower", "tier1", "tower", "CHEST")
        }

        listOf(
            "diamond" to 2,
            "iron" to 1
        ).forEach { (material, weight) ->
            val path = "${ConfigPath.SHOP_PATH_CATEGORY_ARMOR}${ConfigPath.SHOP_CATEGORY_CONTENT_PATH}.$material-armor"
            if (contains(path, true)) addDefault("$path.${ConfigPath.SHOP_CATEGORY_CONTENT_WEIGHT}", weight)
        }

        //try materials
        listOf(
            ConfigPath.SHOP_SPECIAL_IRON_GOLEM_MATERIAL,
            ConfigPath.SHOP_SPECIAL_SILVERFISH_MATERIAL
        ).forEach { path ->
            val material = getString(path)
            debug("$path is set to: $material")
            runCatching { Material.valueOf(material!!) }
                .onFailure { plugin.logger.severe("Invalid material at $path") }
        }

        save()

        loadShop()

        listOf(
            InventoryListener(plugin),
            ShopCacheListener(),
            QuickBuyListener(),
            ShopOpenListener(plugin),
            PlayerDropListener(plugin),
            SpecialsListener(plugin)
        ).forEach { plugin.registerEvents(it) }
    }

    private fun createItem(name: String, amount: String, data: String, enchant: String): ItemStack {
        val item = plugin.versionSupport.createItemStack(
            getString(name)!!,
            getInt(amount),
            getInt(data).toShort()
        )
        return if (getBoolean(enchant)) enchantItem(item) else item
    }
    private fun loadShop() {
        //Quick Buy Button
        val button = createItem(
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_MATERIAL,
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_AMOUNT,
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_DATA,
            ConfigPath.SHOP_SETTINGS_QUICK_BUY_BUTTON_ENCHANTED
        )
        val qbb = QuickBuyButton(0, button, Messages.SHOP_QUICK_BUY_NAME, Messages.SHOP_QUICK_BUY_LORE)

        //Separator
        val separatorStandard = createItem(
            ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_MATERIAL,
            ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_AMOUNT,
            ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_DATA,
            ConfigPath.SHOP_SETTINGS_SEPARATOR_REGULAR_ENCHANTED
        )

        val separatorSelected = createItem(
            ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_MATERIAL,
            ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_AMOUNT,
            ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_DATA,
            ConfigPath.SHOP_SETTINGS_SEPARATOR_SELECTED_ENCHANTED
        )

        shop = ShopIndex(
            Messages.SHOP_INDEX_NAME,
            qbb,
            Messages.SHOP_SEPARATOR_NAME,
            Messages.SHOP_SEPARATOR_LORE,
            separatorSelected,
            separatorStandard
        )

        for (s in getConfigurationSection("")!!.getKeys(false)) {
            if (s.equals(ConfigPath.SHOP_SETTINGS_PATH, ignoreCase = true)) continue
            if (s.equals(ConfigPath.SHOP_QUICK_DEFAULTS_PATH, ignoreCase = true)) continue
            if (s.equals(ConfigPath.SHOP_SPECIALS_PATH, ignoreCase = true)) continue
            val category = ShopCategory(s, this)
            if (category.isLoaded) shop.addShopCategory(category)
        }
    }

    /**
     * Initialize a shop category to config
     */
    private fun addDefaultShopCategory(path: String, slot: Int, material: String, data: Int = 0, amount: Int = 1, enchant: Boolean = false) = default(
        path + ConfigPath.SHOP_CATEGORY_SLOT to slot,
        path + ConfigPath.SHOP_CATEGORY_ITEM_MATERIAL to material,
        path + ConfigPath.SHOP_CATEGORY_ITEM_DATA to data,
        path + ConfigPath.SHOP_CATEGORY_ITEM_AMOUNT to amount,
        path + ConfigPath.SHOP_CATEGORY_ITEM_ENCHANTED to enchant
    )

    /**
     * Create a tier for a shop content (unbreakable)
     * Comment: Reason I made a new function; not all items can be unbreakable, thus shouldn't have the option.
     */
    private fun addCategoryContentTier(
        path: String,
        contentName: String,
        contentSlot: Int,
        tierName: String,
        tierMaterial: String,
        tierCost: Int,
        tierCurrency: String,
        amount: Int = 1,
        tierData: Int = 0,
        enchant: Boolean = false,
        permanent: Boolean = false,
        downgradable: Boolean = false,
        unbreakable: Boolean = false
    ) {
        var path = "$path${ConfigPath.SHOP_CATEGORY_CONTENT_PATH}.$contentName."
        default(
            path + ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_SLOT to contentSlot,
            path + ConfigPath.SHOP_CATEGORY_CONTENT_IS_PERMANENT to permanent,
            path + ConfigPath.SHOP_CATEGORY_CONTENT_IS_DOWNGRADABLE to downgradable,
            path + ConfigPath.SHOP_CATEGORY_CONTENT_IS_UNBREAKABLE to unbreakable
        )
        path += "${ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_TIERS}.$tierName"
        default(
            path + ConfigPath.SHOP_CONTENT_TIER_ITEM_MATERIAL to tierMaterial,
            path + ConfigPath.SHOP_CONTENT_TIER_ITEM_DATA to tierData,
            path + ConfigPath.SHOP_CONTENT_TIER_ITEM_AMOUNT to amount,
            path + ConfigPath.SHOP_CONTENT_TIER_ITEM_ENCHANTED to enchant,
            path + ConfigPath.SHOP_CONTENT_TIER_SETTINGS_COST to tierCost,
            path + ConfigPath.SHOP_CONTENT_TIER_SETTINGS_CURRENCY to tierCurrency
        )
    }

    /**
     * Add buy items to a content tier
     */
    private fun addBuyItem(
        path: String,
        contentName: String,
        tierName: String,
        item: String,
        material: String,
        amount: Int = 1,
        data: Int = 0,
        enchant: String = "",
        potion: String = "",
        itemName: String = "",
        autoEquip: Boolean = false
    ) {
        val path = "$path${ConfigPath.SHOP_CATEGORY_CONTENT_PATH}.$contentName.${ConfigPath.SHOP_CATEGORY_CONTENT_CONTENT_TIERS}.$tierName.${ConfigPath.SHOP_CONTENT_BUY_ITEMS_PATH}.$item."
        default(
            path + "material" to material,
            path + "data" to data,
            path + "amount" to amount
        )
        if (enchant.isNotEmpty()) addDefault(path + "enchants", enchant)
        if (potion.isNotEmpty()) default(path + "potion" to potion, path + "potion-color" to "")
        if (autoEquip) addDefault(path + "auto-equip", true)
        if (itemName.isNotEmpty()) addDefault(path + "name", itemName)
    }

    companion object {
        lateinit var shop: ShopIndex

        /**
         * Enchant item stack and hide details
         */
        fun enchantItem(item: ItemStack): ItemStack {
            item.editMeta { addEnchant(Enchantment.ARROW_DAMAGE, 1, true) }
            return hideItemStuff(item)
        }

        fun hideItemStuff(item: ItemStack): ItemStack {
            item.editMeta {
                addItemFlags(
                    ItemFlag.HIDE_ENCHANTS,
                    ItemFlag.HIDE_ATTRIBUTES,
                    ItemFlag.HIDE_UNBREAKABLE,
                    ItemFlag.HIDE_POTION_EFFECTS,
                    ItemFlag.HIDE_DESTROYS,
                    ItemFlag.HIDE_PLACED_ON
                )
            }
            return item
        }
    }
}
