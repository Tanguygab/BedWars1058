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
package com.andrei1058.bedwars.listeners

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.NextEvent
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.api.util.BlastProtectionUtil
import com.andrei1058.bedwars.configuration.Sounds
import com.andrei1058.bedwars.popuptower.PopupTowerBuilder
import com.andrei1058.bedwars.api.util.Utils.teleportSafe
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.BlockFace
import org.bukkit.block.Sign
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.TNTPrimed
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.*
import org.bukkit.event.entity.EntityChangeBlockEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.ItemSpawnEvent
import org.bukkit.event.hanging.HangingBreakByEntityEvent
import org.bukkit.event.player.PlayerBucketEmptyEvent
import org.bukkit.event.player.PlayerBucketFillEvent
import org.bukkit.event.player.PlayerInteractEvent
import java.io.File

class BreakPlace(private val plugin: BedWars) : Listener {
    private val allowFireBreak = plugin.mainConfig.getBoolean(ConfigPath.GENERAL_CONFIGURATION_ALLOW_FIRE_EXTINGUISH)
    private val blastProtection = BlastProtectionUtil(plugin.versionSupport, plugin.mainConfig.getBoolean(ConfigPath.GENERAL_TNT_RAY_BLOCKED_BY_GLASS))

    @EventHandler
    fun onIceMelt(e: BlockFadeEvent) {
        val block = e.block
        if (plugin.serverType == ServerType.MULTIARENA && block.location.world?.name.equals(plugin.lobbyWorld, ignoreCase = true) ||
            block.type == Material.ICE && plugin.arenaManager.getArenaByWorld(block.world.name) != null)
            e.isCancelled = true
    }

    @EventHandler
    fun onCactus(e: BlockPhysicsEvent) {
        if (e.block.type == Material.CACTUS && plugin.arenaManager.getArenaByWorld(e.block.world.name) != null)
            e.isCancelled = true
    }


    @EventHandler(ignoreCancelled = true)
    fun onBurn(e: BlockBurnEvent) {
        val arena = plugin.arenaManager.getArenaByWorld(e.block.world.name) ?: return
        if (!arena.isAllowMapBreak) {
            e.isCancelled = true
            return
        }
        if (arena.isTeamBed(e.block.location)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        if (e.isCancelled) return
        val block = e.blockPlaced

        //Prevent player from placing during the removal from the arena
        val worldArena = plugin.arenaManager.getArenaByWorld(block.world.name)
        if (worldArena != null) {
            if (worldArena.status != GameState.PLAYING) {
                e.isCancelled = true
                return
            }
            if (e.itemInHand.type == plugin.versionSupport.materialFireball() && block.type == Material.FIRE) {
                e.isCancelled = true
            }
        }
        val player = e.player
        val arena = plugin.arenaManager.getArena(player)
        if (arena != null) {
            if (arena.isSpectator(player) ||
                arena.respawnSessions.containsKey(player) ||
                arena.status != GameState.PLAYING ||
                block.location.blockY >= arena.config.getInt(ConfigPath.ARENA_CONFIGURATION_MAX_BUILD_Y)
            ) {
                e.isCancelled = true
                return
            }

            for (r in arena.regionsList) {
                if (r.isInRegion(block.location) && r.isProtected) {
                    e.isCancelled = true
                    player.sendLangMsg(Messages.INTERACT_CANNOT_PLACE_BLOCK)
                    return
                }
            }

            // prevent modifying wood if protected
            // issue #531
            if (block.type.toString().let { it.startsWith("STRIPPED_") && it.endsWith("_WOOD") } &&
                worldArena != null && !worldArena.isAllowMapBreak) {
                e.isCancelled = true
                return
            }

            arena.addPlacedBlock(block)
            if (block.type == Material.TNT) {
                if (plugin.mainConfig.getBoolean(ConfigPath.GENERAL_TNT_AUTO_IGNITE)) {
                    e.blockPlaced.type = Material.AIR
                    val tnt = block.location.world!!.spawn(block.location.add(0.5, 0.0, 0.5), TNTPrimed::class.java)
                    tnt.fuseTicks = plugin.mainConfig.getInt(ConfigPath.GENERAL_TNT_FUSE_TICKS)
                    plugin.versionSupport.setSource(tnt, player)
                    return
                }
            } else if (plugin.shopManager.config.getBoolean(ConfigPath.SHOP_SPECIAL_TOWER_ENABLE)) {
                if (block.type == Material.valueOf(plugin.shopManager.config.getString(ConfigPath.SHOP_SPECIAL_TOWER_MATERIAL)!!)) {
                    e.isCancelled = true
                    PopupTowerBuilder.handleTowerPlace(player, block)
                }
            }
            return
        }
        if (plugin.serverType == ServerType.MULTIARENA &&
            block.location.world!!.name.equals(plugin.lobbyWorld, ignoreCase = true) &&
            !isBuildSession(player)
        ) e.isCancelled = true
    }

    @EventHandler(ignoreCancelled = true)
    fun onInteract(e: PlayerInteractEvent) {
        val player = e.player
        val block = e.clickedBlock ?: return
        if (plugin.serverType == ServerType.MULTIARENA &&
            player.world.name.equals(plugin.lobbyWorld, ignoreCase = true) &&
            block.getRelative(BlockFace.UP).type == Material.FIRE && !isBuildSession(player)
        ) e.setCancelled(true)
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onBlockBreakMonitor(e: BlockBreakEvent) {
        val arena = plugin.arenaManager.getArena(e.player)
        arena?.removePlacedBlock(e.block)
    }

    @EventHandler
    fun onBlockDrop(e: ItemSpawnEvent) {
        //WHEAT_SEEDS AND BEDs
        val entity = e.entity
        plugin.arenaManager.getArenaByWorld(entity.world.name) ?: return
        val material = entity.itemStack.type
        if (plugin.versionSupport.isBed(material) || material.toString() == "SEEDS" || material.toString() == "WHEAT_SEEDS") {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onBlockBreak(e: BlockBreakEvent) {
        if (e.isCancelled) return
        val player = e.player
        val block = e.block
        if (plugin.serverType == ServerType.MULTIARENA &&
            block.location.world!!.name.equals(plugin.lobbyWorld, ignoreCase = true) &&
            !isBuildSession(player)
        ) {
            e.isCancelled = true
            return
        }
        val arena = plugin.arenaManager.getArena(player) ?: return

        if (!arena.isPlayer(player) || arena.respawnSessions.containsKey(player) || arena.status != GameState.PLAYING) {
            e.isCancelled = true
            return
        }

        // allow breaking of grass
        // drops are removed in another event
        when (e.block.type.toString()) {
            "LONG_GRASS", "TALL_GRASS", "TALL_SEAGRASS", "SEAGRASS", "SUGAR_CANE", "SUGAR_CANE_BLOCK", "GRASS_PATH", "DOUBLE_PLANT" -> {
                e.isCancelled = false
                return
            }
            "FIRE" if allowFireBreak -> {
                e.isCancelled = false
                return
            }
        }

        if (!plugin.versionSupport.isBed(block.type)) return

        for (team in arena.teams) {
            for (x in block.x - 2..<block.x + 2) {
                if (team.bed.blockX != x) continue

                for (y in block.y - 2..<block.y + 2) {
                    if (team.bed.blockY != y) continue

                    for (z in block.z - 2..<block.z + 2) {
                        if (team.bed.blockZ != z || team.isBedDestroyed) continue
                        if (team.isMember(player)) {
                            player.sendLangMsg(Messages.INTERACT_CANNOT_BREAK_OWN_BED)
                            e.isCancelled = true
                            if ("BED" in player.location.block.type.toString()) {
                                player.teleportSafe(player.location.add(0.0, 0.5, 0.0))
                            }
                            continue
                        }

                        e.isCancelled = false
                        team.isBedDestroyed = true
                        val breakEvent = PlayerBedBreakEvent(
                            player,
                            team,
                            arena,
                            {
                                Language.getMsg(it, if (team.isMember(it))
                                    Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM
                                else Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT)
                            }, {
                                if (team.isMember(it)) {
                                    Language.getMsg(it, Messages.INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT)
                                } else null
                            },
                            {
                                if (team.isMember(it)) {
                                    Language.getMsg(it, Messages.INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT)
                                } else null
                            })
                        Bukkit.getPluginManager().callEvent(breakEvent)
                        for (on in arena.world.players) {
                            val message = breakEvent.message(on)
                            if (message != null) on.sendMessage(message
                                .replace("{TeamColor}", team.color.chat.toString())
                                .replace("{TeamName}", team.getDisplayName(Language.getLanguage(on)))
                                .replace("{PlayerColor}", arena.getTeam(player)!!.color.chat.toString())
                                .replace("{PlayerName}", player.displayName)
                                .replace("{PlayerNameUnformatted}", player.name)
                            )
                            val title = breakEvent.title(on)
                            val subTitle = breakEvent.subTitle(on)
                            if (title != null && subTitle != null) {
                                plugin.versionSupport.sendTitle(on, title, subTitle, 0, 40, 10)
                            }
                            Sounds.playSound(
                                if (team.isMember(on)) ConfigPath.SOUNDS_BED_DESTROY_OWN
                                else ConfigPath.SOUNDS_BED_DESTROY,
                                on
                            )
                        }
                        return
                    }
                }
            }
        }

        for (r in arena.regionsList) {
            if (!r.isInRegion(block.location) || !r.isProtected) continue
            e.isCancelled = true
            player.sendLangMsg(Messages.INTERACT_CANNOT_BREAK_BLOCK)
            return
        }

        if (arena.isAllowMapBreak || arena.isBlockPlaced(block)) return
        player.sendLangMsg(Messages.INTERACT_CANNOT_BREAK_BLOCK)
        e.isCancelled = true
    }

    /**
     * update game signs
     */
    @EventHandler
    fun onSignChange(e: SignChangeEvent) {
        val player = e.player
        if (!e.getLine(0).equals("[${plugin.mainCommand.name}]", ignoreCase = true)) return

        val dir = File(plugin.dataFolder, "/Arenas")
        if (!dir.exists()) {
            player.sendMessage("§c▪ §7You didn't set any arena yet!")
            return
        }
        val exists = !dir.listFiles { it.isFile && it.name.endsWith(".yml") && e.getLine(1) == it.name.removeSuffix(".yml") }.isNullOrEmpty()

        val block = e.block
        val config = plugin.configs.signs
        val sings = config.getStringList("locations").toMutableList()
        if (exists) {
            sings += e.getLine(1) + "," + config.stringLocationConfigFormat(block.location)
            config["locations"] = sings
        }
        val arena = plugin.arenaManager.getArena(e.getLine(1) ?: "") ?: return

        player.sendMessage("§a▪ §7Sign saved for arena: " + e.getLine(1))
        arena.addSign(block.location)

        val sign = block.state as Sign
        config.getStringList("format").forEachIndexed { line, string ->
            e.setLine(line, string
                .replace("[on]", "${arena.players.size}")
                .replace("[max]", "${arena.maxPlayers}")
                .replace("[arena]", arena.displayName)
                .replace("[status]", arena.getDisplayStatus(Language.defaultLanguage))
                .replace("[type]", "${arena.maxInTeam}")
            )
        }
        sign.update(true) // is this needed?
    }

    @EventHandler
    fun onBucketFill(e: PlayerBucketFillEvent) {
        if (e.isCancelled) return
        val player = e.player
        if (plugin.serverType == ServerType.MULTIARENA &&
            player.location.world!!.name.equals(plugin.lobbyWorld, ignoreCase = true) &&
            !isBuildSession(player)
        ) e.isCancelled = true

        val arena = plugin.arenaManager.getArena(player) ?: return
        if (arena.isSpectator(player) ||
            arena.status != GameState.PLAYING ||
            arena.respawnSessions.containsKey(player)
        ) e.isCancelled = true
    }

    @EventHandler
    fun onBucketEmpty(e: PlayerBucketEmptyEvent) {
        if (e.isCancelled) return
        val player = e.player

        // Lobby protection in MULTIARENA
        if (plugin.serverType == ServerType.MULTIARENA &&
            player.location.world!!.name.equals(plugin.lobbyWorld, ignoreCase = true) &&
            !isBuildSession(player)
        ) e.isCancelled = true

        val block = e.blockClicked
        // Prevent player from placing during the removal from the arena
        val blockArena = plugin.arenaManager.getArenaByWorld(block.world.name)
        if (blockArena != null && blockArena.status != GameState.PLAYING) {
            e.isCancelled = true
            return
        }

        val arena = plugin.arenaManager.getArena(player) ?: return

        // Restriction checks (spectator, respawning, not playing)
        if (isPlayerRestrictedInArena(arena, player)) {
            e.isCancelled = true
            return
        }

        // Water placement target location
        val waterLocation = block.getRelative(e.blockFace).location

        // Build height limit
        if (isAboveMaxBuildY(arena, waterLocation)) {
            e.isCancelled = true
            return
        }

        // Protected areas around spawns/shops/upgrades/generators
        if (isProtectedLocation(arena, waterLocation)) {
            e.isCancelled = true
            player.sendLangMsg(Messages.INTERACT_CANNOT_PLACE_BLOCK)
            return
        }

        // Remove one empty bucket from player's hand after a short delay
        plugin.run(delay = 3) { plugin.versionSupport.minusAmount(player, e.itemStack!!, 1) }
    }

    @EventHandler
    fun onBlow(e: EntityExplodeEvent) {
        if (e.isCancelled || e.blockList().isEmpty()) return

        val arena = plugin.arenaManager.getArenaByWorld(e.location.getWorld()!!.name) ?: return
        if (arena.status == GameState.PLAYING) {
            e.blockList().removeIf { blastProtection.isProtected(arena, e.location, it, .3) }
            return
        }
        e.blockList().clear()
    }

    @EventHandler
    fun onBlockExplode(e: BlockExplodeEvent) {
        if (e.isCancelled || e.blockList().isEmpty()) return

        val a = plugin.arenaManager.getArenaByWorld(e.block.world.name) ?: return
        if (a.nextEvent == NextEvent.GAME_END) return

        val block = e.block.location
        e.blockList().removeIf { blastProtection.isProtected(a, block, it, 0.3) }
    }

    @EventHandler
    fun onPaintingRemove(e: HangingBreakByEntityEvent) {
        val a = plugin.arenaManager.getArenaByWorld(e.entity.world.name)
        if (a == null) {
            if (plugin.serverType == ServerType.SHARED) return
            if (plugin.lobbyWorld != e.entity.world.name) return
        }
        if (e.entity.type == EntityType.PAINTING || e.entity.type == EntityType.ITEM_FRAME) {
            e.isCancelled = true
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    fun onBlockCanBuildEvent(e: BlockCanBuildEvent) {
        if (e.isBuildable) return
        val block = e.block
        val arena = plugin.arenaManager.getArenaByWorld(block.world.name) ?: return

        var bed = false
        for (team in arena.teams) {

            if (team.bed.blockY == block.y) {
                for (x in block.x - 1..<block.x + 1) {
                    if (team.bed.blockX != x) continue

                    for (z in block.z - 1..<block.z + 1) {
                        if (team.bed.blockZ != z) continue

                        e.isBuildable = false
                        bed = true
                        break
                    }
                }
            }

            //Check bed hologram
            if (bed ||
                team.bed.blockX != block.x ||
                team.bed.blockY + 1 != block.y ||
                team.bed.blockZ != block.z
            ) continue
            e.isBuildable = true
            break
        }
        //if (bed) return
        /*if (block.type == Material.AIR && block.world
            .getNearbyEntities(block.location, 1.0, 1.0, 1.0)
            .any { it is Player && arena.isSpectator(it) }
        ) e.isBuildable = true*/
    }

    //prevent farm breaking farm stuff
    @EventHandler
    fun soilChangeEntity(e: EntityChangeBlockEvent) {
        val block = e.block
        if (e.to != Material.DIRT || block.type.toString() != "FARMLAND" && block.type.toString() != "SOIL") return
        val world = block.world.name
        if (world == plugin.lobbyWorld || plugin.arenaManager.getArenaByWorld(world) != null)
            e.isCancelled = true
    }

    private fun isPlayerRestrictedInArena(a: IArena, p: Player): Boolean {
        return a.isSpectator(p) || a.respawnSessions.containsKey(p) || a.status != GameState.PLAYING
    }

    private fun isAboveMaxBuildY(a: IArena, location: Location) = location.blockY >= a.config.getInt(ConfigPath.ARENA_CONFIGURATION_MAX_BUILD_Y)

    companion object {
        private val buildSession = mutableListOf<Player>()
        fun isBuildSession(player: Player) = player in buildSession

        fun addBuildSession(player: Player) {
            buildSession += player
        }

        fun removeBuildSession(player: Player) = buildSession.remove(player)

        fun isProtectedLocation(arena: IArena, location: Location): Boolean {
            return arena.teams.flatMap { team ->
                listOf(
                    team.spawn to ConfigPath.ARENA_SPAWN_PROTECTION,
                    team.shop to ConfigPath.ARENA_SHOP_PROTECTION,
                    team.teamUpgrades to ConfigPath.ARENA_UPGRADES_PROTECTION,
                ).plus(team.generators.map { it.location to ConfigPath.ARENA_GENERATOR_PROTECTION })
            }.plus(arena.oreGenerators.map { it.location to ConfigPath.ARENA_GENERATOR_PROTECTION })
                .any { (zone, radius) -> zone != null && zone.distance(location) <= arena.config.getInt(radius) }
        }
    }
}
