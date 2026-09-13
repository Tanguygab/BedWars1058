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
package com.andrei1058.bedwars.upgrades

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.Utils.editMeta
import com.andrei1058.bedwars.api.UpgradesManager
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.upgrades.MenuContent
import com.andrei1058.bedwars.api.upgrades.UpgradesIndex
import com.andrei1058.bedwars.configuration.UpgradesConfig
import com.andrei1058.bedwars.upgrades.listeners.InventoryListener
import com.andrei1058.bedwars.upgrades.listeners.UpgradeOpenListener
import com.andrei1058.bedwars.upgrades.menu.*
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import java.io.File
import java.util.UUID

class UpgradesManagerImpl(plugin: BedWars) : UpgradesManager {
    private val upgradeViewers = mutableSetOf<UUID>()

    //store lower case names
    private val menuContentByName = mutableMapOf<String, MenuContent>()

    //store lower case names
    private val menuByName = mutableMapOf<String, UpgradesIndex>()

    private val customMenuForArena = HashMap<IArena, UpgradesIndex>()

    val configuration = UpgradesConfig(plugin)

    init {
        val oldFile = File(plugin.dataFolder, "/upgrades.yml")
        oldFile.delete()

        var name: String?
        for (index in configuration.getConfigurationSection("")!!.getKeys(false)) {
            name = index
            if (index.startsWith("upgrade-")) {
                //name = index.replace("upgrade-", "");
                //if (!name.isEmpty()) {
                if (getMenuContent(name!!) == null && !loadUpgrade(name)) {
                    Bukkit.getLogger().warning("Could not load upgrade: $name")
                }
                //}
            } else if (index.startsWith("separator-")) {
                //name = index.replace("separator-", "");
                //if (!name.isEmpty()) {
                if (getMenuContent(name!!) == null && !loadSeparator(name)) {
                    Bukkit.getLogger().warning("Could not load separator: $name")
                }
                //}
            } else if (index.startsWith("category-")) {
                //name = index.replace("category-", "");
                //if (!name.isEmpty()) {
                if (getMenuContent(name!!) == null && !loadCategory(name)) {
                    Bukkit.getLogger().warning("Could not load category: $name")
                }
                //}
            } else if (index.startsWith("base-trap-")) {
                //name = index.replace("category-", "");
                //if (!name.isEmpty()) {
                if (getMenuContent(name!!) == null && !loadBaseTrap(name)) {
                    Bukkit.getLogger().warning("Could not base trap: $name")
                }
                //}
            } else if (index.endsWith("-upgrades-settings")) {
                name = index.replace("-upgrades-settings", "")
                if (name.isNotEmpty()) {
                    if (!loadMenu(name)) {
                        Bukkit.getLogger().warning("Could not load menu: $name")
                    }
                }
            }
        }

        plugin.registerEvents(InventoryListener(plugin), UpgradeOpenListener(plugin))
    }

    /**
     * @return true if has the upgrades GUI opened.
     */
    override fun isWatchingGUI(player: Player) = player.uniqueId in upgradeViewers

    /**
     * Set watching upgrades GUI.
     */
    override fun setWatchingGUI(player: Player) {
        upgradeViewers += player.uniqueId
    }

    /**
     * Remove from upgrades GUI.
     */
    override fun removeWatchingGUI(uuid: UUID) {
        upgradeViewers.remove(uuid)
    }

    override fun getTotalUpgradeTiers(arena: IArena) = getMenuForArena(arena).countTiers()

    /**
     * Load a menu for a group.
     * 
     * @param groupName arena group name.
     * @return false if cannot be loaded.
     */
    fun loadMenu(groupName: String): Boolean {
        val groupName = groupName.lowercase()
        if (!configuration.isSet("$groupName-upgrades-settings.menu-content")) return false
        if (menuByName.containsKey(groupName)) return false
        val um = InternalMenu(groupName)
        for (component in configuration.getStringList("$groupName-upgrades-settings.menu-content")) {
            val data = component.split(",")
            if (data.size <= 1) continue

            var mc = getMenuContent(data[0])
            if (mc == null && data[0].run { when {
                    startsWith("category-") -> loadCategory(this)
                    startsWith("upgrade-") -> loadUpgrade(this)
                    startsWith("trap-slot-") -> loadTrapSlot(this)
                    startsWith("separator-") -> loadSeparator(this)
                    startsWith("base-trap-") -> loadBaseTrap(this)
                    else -> false
            } }) mc = getMenuContent(data[0])
            if (mc == null) continue
            for (i in 1..<data.size) {
                um.addContent(mc, data[i].toIntOrNull() ?: continue)
            }
        }
        menuByName[groupName] = um
        BedWars.debug("Registering upgrade menu: $groupName")
        return true
    }

    /**
     * Load a category with given name from the shop file.
     * 
     * @param name category name. Must start with "category-".
     * @return false if cannot be loaded.
     */
    private fun loadCategory(name: String?): Boolean {
        if (name == null) return false
        if (!name.startsWith("category-")) return false
        if (configuration.get(name) == null) return false
        if (getMenuContent(name) != null) return false
        val uc = MenuCategory(name, createDisplayItem(name))
        for (component in configuration.getStringList("$name.category-content")) {
            val data = component.split(",")
            if (data.size <= 1) continue

            var mc: MenuContent? = null
            if (data[0].startsWith("category-")) {
                mc = getMenuContent(data[0])
                if (mc == null && loadCategory(data[0])) {
                    mc = getMenuContent(data[0])
                }
            } else if (data[0].startsWith("upgrade-")) {
                mc = getMenuContent(data[0])
                if (mc == null && loadUpgrade(data[0])) {
                    mc = getMenuContent(data[0])
                }
            } else if (data[0].startsWith("trap-slot-")) {
                mc = getMenuContent(data[0])
                if (mc == null && loadTrapSlot(data[0])) {
                    mc = getMenuContent(data[0])
                }
            } else if (data[0].startsWith("separator-")) {
                mc = getMenuContent(data[0])
                if (mc == null && loadSeparator(data[0])) {
                    mc = getMenuContent(data[0])
                }
            } else if (data[0].startsWith("base-trap-")) {
                mc = getMenuContent(data[0])
                if (mc == null && loadBaseTrap(data[0])) {
                    mc = getMenuContent(data[0])
                }
            }
            if (mc == null) continue
            for (i in 1..<data.size) {
                uc.addContent(mc, data[i].toIntOrNull() ?: continue)
            }
        }
        menuContentByName[name.lowercase()] = uc
        BedWars.debug("Registering upgrade: $name")
        return true
    }

    /**
     * Load an upgrade element with given name.
     * 
     * @param name upgrade name. Must start with "upgrade-".
     * @return false if can't be loaded.
     */
    private fun loadUpgrade(name: String?): Boolean {
        if (name == null) return false
        if (!name.startsWith("upgrade-")) return false
        if (configuration.get(name) == null) return false
        if (configuration.get("$name.tier-1") == null) return false
        if (getMenuContent(name) != null) return false
        val mu = MenuUpgrade(name)

        for (s in configuration.getConfigurationSection(name)!!.getKeys(false)) {
            if (!s.startsWith("tier-")) continue
            if (configuration.get("$name.$s.receive") == null) {
                BedWars.debug("Could not load Upgrade $name tier: $s. Receive not set.")
                continue
            }
            if (configuration.get("$name.$s.display-item") == null) {
                BedWars.debug("Could not load Upgrade $name tier: $s. Display item not set.")
                continue
            }
            if (configuration.get("$name.$s.cost") == null) {
                BedWars.debug("Could not load Upgrade $name tier: $s. Cost not set.")
                continue
            }
            if (configuration.get("$name.$s.currency") == null) {
                BedWars.debug("Could not load Upgrade $name tier: $s. Currency not set.")
                continue
            }
            val ut = UpgradeTier(
                name,
                s,
                createDisplayItem("$name.$s"),
                configuration.getInt("$name.$s.cost"),
                getCurrency(configuration.getString("$name.$s.currency"))!! // TODO: check if it's not null?
            )
            if (!mu.addTier(ut)) {
                Bukkit.getLogger().warning("Could not load tier: $s at upgrade: $name")
            }
        }
        BedWars.debug("Registering upgrade: $name")
        menuContentByName[name.lowercase()] = mu
        return true
    }

    /**
     * Load a separator with given name.
     * 
     * @param name name. Must start with "separator-".
     * @return false if cannot be loaded.
     */
    private fun loadSeparator(name: String?): Boolean {
        if (name == null) return false
        if (!name.startsWith("separator-")) return false
        if (configuration.get(name) == null) return false
        if (getMenuContent(name) != null) return false
        val ms = MenuSeparator(name, createDisplayItem(name))
        menuContentByName[name.lowercase()] = ms
        BedWars.debug("Registering upgrade: $name")
        return true
    }

    /**
     * Load a trap slot with given name.
     * 
     * @param name name. Must start with "trap-slot-".
     * @return false if cannot be loaded.
     */
    private fun loadTrapSlot(name: String?): Boolean {
        if (name == null) return false
        val name = name.lowercase()
        if (!name.startsWith("trap-slot-")) return false
        if (configuration.get(name) == null) return false
        if (getMenuContent(name) != null) return false
        val mts = MenuTrapSlot(name, createDisplayItem(name))
        menuContentByName[name] = mts
        BedWars.debug("Registering upgrade: $name")
        return true
    }

    private fun loadBaseTrap(name: String?): Boolean {
        if (name == null) return false
        if (!name.startsWith("base-trap-")) return false
        if (configuration.get(name) == null) return false
        if (configuration.get("$name.receive") == null) {
            BedWars.debug("Could not load BaseTrap. Receive not set.")
            return false
        }
        if (configuration.get("$name.display-item") == null) {
            BedWars.debug("Could not load BaseTrap. Display item not set.")
            return false
        }

        val bt = MenuBaseTrap(
            name,
            createDisplayItem(name),
            configuration.getInt("$name.cost"),
            getCurrency(configuration.getString("$name.currency"))
        )
        BedWars.debug("Registering upgrade: $name")
        menuContentByName[name.lowercase()] = bt
        return true
    }

    /**
     * Check the money amount in a given currency of a player.
     * 
     * @param player   target player.
     * @param currency [Material.AIR] is used for vault, [Material.IRON_INGOT] for iron, [Material.GOLD_INGOT] for gold, [Material.DIAMOND] for diamond, [Material.EMERALD] for emerald.
     * @return the amount of money.
     */
    fun getMoney(player: Player, currency: Material): Int {
        if (currency == Material.AIR) {
            val amount = BedWars.economy.getMoney(player)
            return if (amount % 2 == 0.0) amount.toInt() else (amount - 1).toInt()
        }
        return BedWars.api.shopUtil.calculateMoney(player, currency)
    }

    /**
     * @param name the string to be converted.
     * @return NULL if not a currency. [Material.AIR] is used for vault, [Material.IRON_INGOT] for iron, [Material.GOLD_INGOT] for gold, [Material.DIAMOND] for diamond, [Material.EMERALD] for emerald.
     */
    fun getCurrency(name: String?): Material? {
        if (name.isNullOrEmpty()) return null
        return BedWars.api.shopUtil.getCurrency(name)
    }

    /**
     * Check if is upgradable item.
     * Used in inventory click.
     * 
     * @param item item to be checked.
     * @retrun [MenuContent] NULL if isn't an element.
     */
    fun getMenuContent(item: ItemStack?): MenuContent? {
        if (item == null) return null

        var identifier = BedWars.nms.getCustomData(item) ?: return null
        if (!identifier.startsWith("MCONT_")) return null

        identifier = identifier.removePrefix("MCONT_")
        if (identifier.isEmpty()) return null

        return getMenuContent(identifier)
    }

    /**
     * Get menu content by identifier.
     * 
     * @retrun null if not found.
     */
    fun getMenuContent(identifier: String) = menuContentByName[identifier.lowercase()]

    /**
     * Set a custom menu that will override the group menu for an arena.
     * 
     * @param arena target arena.
     * @param menu  custom menu.
     */
    @Suppress("unused")
    fun setCustomMenuForArena(arena: IArena, menu: UpgradesIndex) {
        BedWars.debug(if (arena !in customMenuForArena)
            "Registering custom menu for arena: ${arena.name}. Using index: ${menu.name}"
        else "Overriding custom menu for arena: ${arena.name}. Using index: ${menu.name} Old index: ${customMenuForArena[arena]!!.name}")
        customMenuForArena[arena] = menu
    }

    /**
     * Get upgrade menu index for an arena.
     * 
     * @param arena target arena.
     * @return the default menu if the arena doesn't have an arena group menu set or a custom menu by an addon.
     */
    fun getMenuForArena(arena: IArena) = customMenuForArena[arena]
        ?: menuByName[arena.group.lowercase()]
        ?: menuByName["default"]!!

    /**
     * Create a display item.
     * 
     * @param path config path.
     * @return bedrock if given material is null
     */
    private fun createDisplayItem(path: String?): ItemStack {
        val m = try {
            Material.valueOf(configuration.getString("$path.display-item.material") ?: "BEDROCK")
        } catch (_: Exception) { Material.BEDROCK }
        val i = ItemStack(
            m,
            configuration.getString("$path.display-item.amount")?.toInt() ?: 1,
            configuration.getInt("$path.display-item.data").toShort()
        )
        if (configuration.getBoolean("$path.display-item.enchanted")) {
            i.addUnsafeEnchantment(Enchantment.DURABILITY, 1)
            i.editMeta { addItemFlags(ItemFlag.HIDE_ENCHANTS) }
        }
        return i
    }

    /**
     * Get currency msg.
     */
    fun getCurrencyMsg(p: Player?, ut: UpgradeTier): String {
        val c = when (ut.currency) {
            Material.IRON_INGOT -> if (ut.cost == 1) Messages.MEANING_IRON_SINGULAR else Messages.MEANING_IRON_PLURAL
            Material.GOLD_INGOT -> if (ut.cost == 1) Messages.MEANING_GOLD_SINGULAR else Messages.MEANING_GOLD_PLURAL
            Material.EMERALD -> if (ut.cost == 1) Messages.MEANING_EMERALD_SINGULAR else Messages.MEANING_EMERALD_PLURAL
            Material.DIAMOND -> if (ut.cost == 1) Messages.MEANING_DIAMOND_SINGULAR else Messages.MEANING_DIAMOND_PLURAL
            Material.AIR -> if (ut.cost == 1) Messages.MEANING_VAULT_SINGULAR else Messages.MEANING_VAULT_PLURAL
            else -> ""
        }
        return Language.getMsg(p, c)
    }

    fun getCurrencyMsg(p: Player?, money: Int, currency: String?): String {
        return getCurrencyMsg(p, money, when (currency?.lowercase()) {
            "iron" -> Material.IRON_INGOT
            "gold" -> Material.GOLD_INGOT
            "emerald" -> Material.EMERALD
            "diamond" -> Material.DIAMOND
            else -> Material.AIR
        })
    }

    fun getCurrencyMsg(p: Player?, money: Int, currency: Material?): String {
        val c = when (currency) {
            Material.IRON_INGOT -> if (money == 1) Messages.MEANING_IRON_SINGULAR else Messages.MEANING_IRON_PLURAL
            Material.GOLD_INGOT -> if (money == 1) Messages.MEANING_GOLD_SINGULAR else Messages.MEANING_GOLD_PLURAL
            Material.EMERALD -> if (money == 1) Messages.MEANING_EMERALD_SINGULAR else Messages.MEANING_EMERALD_PLURAL
            Material.DIAMOND -> if (money == 1) Messages.MEANING_DIAMOND_SINGULAR else Messages.MEANING_DIAMOND_PLURAL
            else -> if (money == 1) Messages.MEANING_VAULT_SINGULAR else Messages.MEANING_VAULT_PLURAL
        }

        return Language.getMsg(p, c)
    }

    fun getCurrencyColor(currency: Material) = when (currency) {
        Material.DIAMOND -> ChatColor.AQUA
        Material.GOLD_INGOT -> ChatColor.GOLD
        Material.IRON_INGOT -> ChatColor.WHITE
        Material.EMERALD -> ChatColor.GREEN
        else -> ChatColor.DARK_GREEN
    }
}
