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
package com.andrei1058.bedwars.api.arena.team

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.generator.IGenerator
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.upgrades.EnemyBaseEnterTrap
import org.bukkit.Location
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

interface ITeam {
    /**
     * Runtime identifier.
     */
    val identity: UUID

    /**
     * Get team color.
     */
    val color: TeamColor

    /**
     * Get team name.
     */
    val name: String

    /**
     * Get team display name.
     * 
     * @param language get the display name in target language.
     */
    fun getDisplayName(language: Language): String

    /**
     * Check if is member.
     * 
     * @param player target player.
     */
    fun isMember(player: Player): Boolean

    /**
     * Get the team arena.
     */
    val arena: IArena

    /**
     * Get alive team members.
     */
    val members: MutableList<Player>

    /**
     * Restore lost default sword.
     * 
     * @param player target player.
     */
    fun defaultSword(player: Player)

    /**
     * Get bed location.
     */
    val bed: Location

    /**
     * Get list of team upgrades.
     * Upgrade identifier, tier.
     */
    val teamUpgradeTiers: ConcurrentHashMap<String, Int>

    /**
     * Get enchantments to be applied on bows.
     */
    val bowsEnchantments: List<TeamEnchant>

    /**
     * Get enchantments to be applied on swords.
     */
    val swordsEnchantments: List<TeamEnchant>

    /**
     * Get enchantments to be applied on armors.
     */
    val armorsEnchantments: List<TeamEnchant>


    /**
     * Get team current size.
     */
    val size: Int

    /**
     * Add a new member to the team.
     * Be careful! This will cache players on this team until the game is finished
     * so you can retrieve who played here with [.wasMember].
     * 
     * @param players players to be added.
     */
    fun addPlayers(vararg players: Player)

    /**
     * Spawn a player for the first spawn.
     * 
     * @param player target player.
     */
    fun firstSpawn(player: Player)

    /**
     * Spawn shopkeepers for target team (if enabled).
     */
    fun spawnNPCs()

    /**
     * Rejoin a team.
     * 
     * @param player target player.
     */
    fun reJoin(player: Player)

    /**
     * Rejoin a team.
     * 
     * @param player target player.
     * @param respawnTime the time until the player should respawn.
     */
    fun reJoin(player: Player, respawnTime: Int)

    /**
     * Gives the start inventory
     * 
     * @param player     target player.
     * @param clear true to clear inventory.
     */
    fun sendDefaultInventory(player: Player, clear: Boolean)

    /**
     * Respawn a member. This is after respawn countdown.
     * 
     * @param player target player.
     */
    fun respawnMember(player: Player)

    /**
     * Equip a player with default armor.
     * 
     * @param player target player.
     */
    fun sendArmor(player: Player)

    /**
     * Used when someone buys a new potion effect with apply == members
     * 
     * @param effect    effect.
     * @param amplifier amplifier.
     * @param duration  duration.
     */
    fun addTeamEffect(effect: PotionEffectType, amplifier: Int, duration: Int)

    /**
     * Used when someone buys a new potion effect with apply == base
     * 
     * @param effect    effect.
     * @param amplifier amplifier.
     * @param duration  duration.
     */
    fun addBaseEffect(effect: PotionEffectType, amplifier: Int, duration: Int)

    /**
     * Get list of effects that you gen when you enter the base.
     */
    val baseEffects: List<PotionEffect>

    /**
     * Used when someone buys a bew enchantment with apply == bow.
     * 
     * @param enchantment enchant.
     * @param amplifier   amplifier.
     */
    fun addBowEnchantment(enchantment: Enchantment, amplifier: Int)

    /**
     * Used when someone buys a new enchantment with apply == sword.
     * 
     * @param e enchant.
     * @param a amplifier.
     */
    fun addSwordEnchantment(e: Enchantment, a: Int)

    /**
     * Used when someone buys a new enchantment with apply == armor.
     * 
     * @param e enchant.
     * @param a amplifier.
     */
    fun addArmorEnchantment(e: Enchantment, a: Int)

    /**
     * Check if target has played in this match.
     * 
     * @param u player uuid.
     */
    fun wasMember(u: UUID): Boolean

    /**
     * Check if team's bed was destroyed.
     * Team members will no longer be able to respawn if you set this to true
     * This will also disable team generators if true.
     */
    var isBedDestroyed: Boolean

    /**
     * Get team spawn location.
     * 
     * @return spawn point.
     */
    val spawn: Location?

    /**
     * Get shopkeeper location.
     * 
     * @return shop point.
     */
    val shop: Location?

    /**
     * Get team upgrades location.
     * 
     * @return upgrades point.
     */
    val teamUpgrades: Location?

    @Deprecated("")
    val ironGenerator: IGenerator?

    @Deprecated("")
    val goldGenerator: IGenerator?

    @Deprecated("")
    var emeraldGenerator: IGenerator?

    /**
     * Get team generators.
     * 
     * @return team generators.
     */
    val generators: MutableList<IGenerator>

    /**
     * Get team dragons amount for the sudden death phase.
     */
    /**
     * Set a team dragons amount for the sudden death phase.
     */
    var dragons: Int

    @get:Deprecated("")
    val membersCache: List<Player>

    /**
     * Destroy team data when the arena restarts.
     * This must be called by BedWars1058 only.
     */
    fun destroyData()

    /**
     * Destroy bed hologram for player
     * 
     * @param player target player.
     */
    @Deprecated("")
    fun destroyBedHolo(player: Player)

    /**
     * Get queued traps for a team.
     * 
     * @return active traps list.
     */
    val activeTraps: MutableList<EnemyBaseEnterTrap>

    /**
     * Get the location where enemy items are dropped after you kill him.
     * 
     * @return x, y, z.
     */
    val killDropsLocation: Vector

    /**
     * Check if bed is placed at given location.
     * Or partial.
     */
    fun isBed(location: Location): Boolean

    /**
     * What happens when one of team beds is destroyed at given location.
     */
    fun onBedDestroy(location: Location) {
        throw RuntimeException("Not implemented yet")
    }
}
