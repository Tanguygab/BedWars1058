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
package com.andrei1058.bedwars.api.arena

import com.andrei1058.bedwars.api.arena.generator.IGenerator
import com.andrei1058.bedwars.api.arena.stats.GameStatsHolder
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.arena.team.ITeamAssigner
import com.andrei1058.bedwars.api.configuration.ConfigManager
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.region.Region
import com.andrei1058.bedwars.api.tasks.PlayingTask
import com.andrei1058.bedwars.api.tasks.RestartingTask
import com.andrei1058.bedwars.api.tasks.StartingTask
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.Player
import org.bukkit.util.Vector
import java.time.Instant
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

@Suppress("unused")
interface IArena {
    /**
     * Get used world name.
     */
    val name: String

    /**
     * Initialize the arena after loading the world.
     * This needs to be called in order to allow players to join.
     */
    fun init(world: World)

    /**
     * Get arena config.
     */
    val config: ConfigManager

    /**
     * Check if a player is spectating on this arena.
     */
    fun isSpectator(player: Player): Boolean

    /**
     * Check if user is playing.
     */
    fun isPlayer(player: Player): Boolean

    /**
     * Check if a player is spectating on this arena.
     */
    fun isSpectator(player: UUID): Boolean

    /**
     * Check if a player is spectating on this arena.
     */
    fun isRespawning(player: UUID): Boolean

    /**
     * Get a list of spectators.
     */
    val spectators: List<Player>

    val allPlayers get() = players + spectators

    /**
     * Get the player's team.
     * This will work if the player is alive only.
     *
     * Use [.getExTeam] to get the team where the player has played in current match.
     */
    fun getTeam(player: Player): ITeam?

    /**
     * Get the team where the player has played in current match.
     * To be used if the player was eliminated.
     */
    fun getExTeam(player: UUID): ITeam?

    /**
     * Get the arena name as a message that can be used on signs etc.
     * 
     * @return A string with - and _ replaced by a space.
     */
    val displayName: String

    /**
     * Get arena status.
     */
    /**
     * Set game status without starting stats.
     */
    var status: GameState

    /**
     * Get players in arena.
     */
    val players: List<Player>

    /**
     * Get maximum allowed players amount.
     */
    val maxPlayers: Int

    /**
     * Get arena group.
     */
    var group: String

    /**
     * Get maximum players allowed in a team.
     */
    val maxInTeam: Int

    /**
     * Get list of players in respawn screen.
     * Player is the actual player in re-spawn screen.
     * Integer is the remaining time.
     */
    val respawnSessions: ConcurrentHashMap<Player, Int>


    /**
     * Disable spectator collisions.
     * 
     * @param player - spectator.
     * Use false when the spectator got removed from the arena.
     */
    fun updateSpectatorCollideRule(player: Player, collide: Boolean)

    /**
     * This will attempt to upgrade the next event if it is the case.
     */
    fun updateNextEvent()

    /**
     * Add a player to the arena
     * 
     * @param player              - Player to add.
     * @param skipOwnerCheck - True if you want to skip the party checking for this player. This
     * @return true if was added.
     */
    fun addPlayer(player: Player, skipOwnerCheck: Boolean): Boolean

    /**
     * Add a player as Spectator
     * 
     * @param player            Player to be added
     * @param playerBefore True if the player has played in this arena before and he died so now should be a spectator.
     */
    fun addSpectator(player: Player, playerBefore: Boolean, staffTeleport: Location?): Boolean

    /**
     * Remove a player from the arena
     * 
     * @param player          Player to be removed
     * @param disconnect True if the player was disconnected
     */
    fun removePlayer(player: Player, disconnect: Boolean)

    /**
     * Remove a spectator from the arena
     * 
     * @param player          Player to be removed
     * @param disconnect True if the player was disconnected
     */
    fun removeSpectator(player: Player, disconnect: Boolean)

    /**
     * Rejoin an arena
     * 
     * @return true if can rejoin
     */
    fun reJoin(player: Player): Boolean

    /**
     * Disable the arena.
     * This will automatically kick/ remove the people from the arena.
     */
    fun disable()

    /**
     * Restart the arena.
     */
    fun restart()

    /**
     * Get the arena world
     */
    val world: World

    /**
     * Get the display status for an arena.
     * A message that can be used on signs etc.
     */
    fun getDisplayStatus(lang: Language): String

    /**
     * Get arena display group for given player.
     * 
     * @return translated group.
     */
    fun getDisplayGroup(player: Player): String

    /**
     * Get arena display group for given language.
     * 
     * @return translated group.
     */
    @Suppress("unused")
    fun getDisplayGroup(language: Language): String

    val teams: MutableList<out ITeam>

    /**
     * Add placed block to cache.
     * So players will be able to remove blocks placed by players only.
     */
    fun addPlacedBlock(block: Block)

    /**
     * Gets the cooldowns for fireballs
     * @return The cooldowns for fireballs
     */
    val fireballCooldowns: MutableMap<UUID, Long>

    /**
     * Remove placed block.
     */
    fun removePlacedBlock(block: Block)

    fun isBlockPlaced(block: Block): Boolean

    /**
     * Session stats.
     * @return stats container for this game.
     */
    val statsHolder: GameStatsHolder

    /**
     * Get the join signs for this arena
     * 
     * @return signs.
     */
    val signs: List<Block>

    /**
     * Get the island radius
     */
    val islandRadius: Int

    /**
     * Change game status starting tasks.
     */
    fun changeStatus(status: GameState)

    /**
     * Add a join sign for the arena.
     */
    fun addSign(loc: Location)

    /**
     * Refresh signs.
     */
    fun refreshSigns()

    /**
     * Check winner. Will check if the game has a winner in certain conditions. Manage your win conditions.
     * Call the arena restart and the needed stuff.
     */
    fun checkWinner()

    /**
     * Get next event.
     */
    /**
     * Set next event for the arena.
     */
    var nextEvent: NextEvent

    /**
     * This will give the pre-game command Items.
     * This will clear the inventory first.
     */
    fun sendPreGameCommandItems(player: Player)

    /**
     * This will give the spectator command Items.
     * This will clear the inventory first.
     */
    fun sendSpectatorCommandItems(player: Player)

    /**
     * Get a team by name
     */
    fun getTeam(name: String): ITeam?

    val startingTask: StartingTask?

    val playingTask: PlayingTask?

    val restartingTask: RestartingTask?

    /**
     * Get Ore Generators.
     */
    val oreGenerators: MutableList<IGenerator>

    /**
     * Get the list of next events to come.
     * Not ordered.
     */
    val nextEvents: List<String>

    /**
     * Show upgrade announcement to players.
     * Change diamondTier value first.
     */
    fun sendDiamondsUpgradeMessages()

    /**
     * Show upgrade announcement to players.
     * Change emeraldTier value first.
     */
    fun sendEmeraldsUpgradeMessages()

    /**
     * List of placed blocks.
     */
    val placed: MutableList<Vector>

    /**
     * This is used to destroy arena data when it restarts.
     */
    fun destroyData()

    val upgradeDiamondsCount: Int

    val upgradeEmeraldsCount: Int

    val regionsList: MutableList<Region>

    /**
     * Get invisibility for armor
     */
    val showTime: ConcurrentHashMap<Player, Int>

    var isAllowSpectate: Boolean

    /**
     * Change world name for auto-scaling.
     */
    var worldName: String

    /**
     * Get player render distance in blocks.
     */
    val renderDistance: Int

    /**
     * Put a player in re-spawning countdown.
     * 
     * @param player  target player.
     * @param seconds countdown in seconds. 0 for instant re-spawn.
     * @return false if the player is not actually in game or if is in another re-spawn session.
     */
    fun startRespawnSession(player: Player, seconds: Int): Boolean

    /**
     * Check if a player is in re-spawning screen/ countdown.
     */
    fun isRespawning(player: Player): Boolean

    /**
     * Get re-spawning screen location.
     */
    val respawnLocation: Location

    /**
     * Where spectators will spawn.
     */
    val spectatorLocation: Location

    /**
     * Location where to spawn at join (waiting/ starting).
     */
    val waitingLocation: Location

    /**
     * Check if the given location is protected.
     * Border checks, regions, island spawn protection, npc protections, generator protections.
     */
    fun isProtected(location: Location): Boolean

    /**
     * This is triggered when a player has abandoned a game.
     * This should remove its assist from existing team and re-join session.
     * This does not replace [.removePlayer].
     */
    fun abandonGame(player: Player)

    /**
     * -1 won't handle void kill.
     * Instant kill when player y is under this number.
     */
    val yKillHeight: Int

    /**
     * y limit for players to be able to build.
     */
    val yHeightLimit: Int

    val startTime: Instant?

    var teamAssigner: ITeamAssigner
    fun resetTeamAssigner()

    val leavingPlayers: List<Player>


    /**
     * Check if breaking map is allowed, otherwise only placed blocks are allowed.
     * Some blocks like have a special protections, like blocks under shopkeepers, bed, ecc.
     */
    /**
     * Toggle map block break rule.
     */
    val isAllowMapBreak: Boolean

    /**
     * Check if there is a player bed at given location.
     */
    fun isTeamBed(location: Location): Boolean

    /**
     * Get owner team of a bed based on location.
     */
    fun getTeamBed(location: Location): ITeam?

    /**
     * Provides the winner team.
     * This is populated on restarting phase.
     */
    val winner: ITeam?

    /**
     * Get the current diamond generator tier for this arena.
     * @return the diamond generator tier (e.g., 1, 2, 3, 4, ...)
     */
    val diamondTier: Int

    /**
     * Get the current emerald generator tier for this arena.
     * @return the emerald generator tier (e.g., 1, 2, 3, 4, ...)
     */
    val emeraldTier: Int
}
