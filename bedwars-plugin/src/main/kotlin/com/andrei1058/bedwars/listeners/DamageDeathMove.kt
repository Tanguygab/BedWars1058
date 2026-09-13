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
import com.andrei1058.bedwars.Utils.teleportSafe
import com.andrei1058.bedwars.api.arena.GameState
import com.andrei1058.bedwars.api.arena.shop.ShopHolo
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.events.player.PlayerInvisibilityPotionEvent
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent
import com.andrei1058.bedwars.api.events.player.PlayerKillEvent.PlayerKillCause
import com.andrei1058.bedwars.api.events.team.TeamEliminatedEvent
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.api.server.ServerType
import com.andrei1058.bedwars.arena.LastHit
import com.andrei1058.bedwars.arena.SetupSession
import com.andrei1058.bedwars.arena.team.BedWarsTeam
import com.andrei1058.bedwars.configuration.Sounds.playSound
import com.andrei1058.bedwars.listeners.dropshandler.PlayerDrops
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.*
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.potion.PotionEffectType
import java.text.DecimalFormat

class DamageDeathMove : Listener {
    private val tntJumpBarycenterAlterationInY = BedWars.config.getDouble(ConfigPath.GENERAL_TNT_JUMP_BARYCENTER_IN_Y)
    private val tntJumpStrengthReductionConstant = BedWars.config.getDouble(ConfigPath.GENERAL_TNT_JUMP_STRENGTH_REDUCTION)
    private val tntJumpYAxisReductionConstant = BedWars.config.getDouble(ConfigPath.GENERAL_TNT_JUMP_Y_REDUCTION)
    private val tntDamageSelf = BedWars.config.getDouble(ConfigPath.GENERAL_TNT_JUMP_DAMAGE_SELF)
    private val tntDamageTeammates = BedWars.config.getDouble(ConfigPath.GENERAL_TNT_JUMP_DAMAGE_TEAMMATES)
    private val tntDamageOthers = BedWars.config.getDouble(ConfigPath.GENERAL_TNT_JUMP_DAMAGE_OTHERS)

    @EventHandler
    fun onDamage(e: EntityDamageEvent) {
        val entity = e.entity
        if (BedWars.serverType == ServerType.MULTIARENA && entity.location.world!!.name.equals(BedWars.lobbyWorld, ignoreCase = true)) {
            e.isCancelled = true
        }
        if (entity !is Player) return

        val arena = BedWars.plugin.arenaManager.getArena(entity) ?: return

        if (arena.status != GameState.PLAYING || arena.isSpectator(entity) || arena.isRespawning(entity)) {
            e.isCancelled = true
            return
        }

        // todo why did I set this to 1? disabled for now
        /*if (e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
            e.setDamage(1);
            return;
        }*/
        //if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
        val invulnerability = BedWarsTeam.reSpawnInvulnerability[entity.uniqueId] ?: return
        if (invulnerability > System.currentTimeMillis()) {
            e.isCancelled = true
            return
        }
        BedWarsTeam.reSpawnInvulnerability.remove(entity.uniqueId)
        //}
    }

    // show player health on bow hit
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun onBowHit(e: EntityDamageByEntityEvent) {
        val player = e.entity as? Player ?: return
        val arena = BedWars.plugin.arenaManager.getArena(player) ?: return
        if (arena.status != GameState.PLAYING) return

        val projectile = e.damager as? Projectile ?: return
        val shooter = projectile.shooter as? Player ?: return

        // projectile hit message #696, #711
        val team = arena.getTeam(player)
        val lang = Language.getLanguage(shooter)
        val message = lang.m(Messages.PLAYER_HIT_BOW)
        if (message.isEmpty()) return

        shooter.sendMessage(message
            .replace("{amount}", HEALTH_FORMAT.format(player.health - e.finalDamage))
            .replace("{TeamColor}", team!!.color.chat.toString())
            .replace("{TeamName}", team.getDisplayName(lang))
            .replace("{PlayerName}", ChatColor.stripColor(player.displayName)!!)
        )
    }

    @EventHandler
    fun onDamageByEntity(e: EntityDamageByEntityEvent) {
        val entity = e.entity

        if (BedWars.serverType == ServerType.MULTIARENA && e.entity.location.world!!.name.equals(BedWars.lobbyWorld, ignoreCase = true)) {
            e.isCancelled = true
        }

        if (entity !is Player) {
            if (BedWars.nms.isDespawnable(e.getEntity())) {
                val damager = when (e.damager) {
                    is Player -> e.damager as Player
                    is Projectile -> (e.damager as Projectile).shooter as Player
                    is TNTPrimed -> (e.damager as TNTPrimed).source as? Player ?: return
                    else -> return
                }
                val arena = BedWars.plugin.arenaManager.getArena(damager) ?: return

                // do not hurt own mobs
                if (!arena.isPlayer(damager) || arena.getTeam(damager) === BedWars.nms.despawnables[entity.uniqueId]!!.team) {
                    e.isCancelled = true
                }
            }
            return
        }
        val arena = BedWars.plugin.arenaManager.getArena(entity) ?: return

        if (arena.status != GameState.PLAYING || arena.isSpectator(entity) || arena.isRespawning(entity)) {
            e.isCancelled = true
            return
        }

        val damager = when (e.damager) {
            is Player -> e.damager as Player

            is Projectile -> (e.damager as Projectile).shooter as? Player

            is TNTPrimed -> run {
                val tnt = e.damager as TNTPrimed
                val damager = tnt.source as? Player ?: return@run null

                if (damager != entity) {
                    val currentTeam = arena.getTeam(entity)
                    val damagerTeam = arena.getTeam(damager)
                    val damage = if (currentTeam == damagerTeam) tntDamageTeammates else tntDamageOthers
                    if (damage > -1) e.damage = damage
                    return@run damager
                }

                if (tntDamageSelf > -1) e.damage = tntDamageSelf

                // tnt jump. credits to feargames.it
                val damaged = entity as LivingEntity
                val distance = damaged.location
                    .subtract(0.0, tntJumpBarycenterAlterationInY, 0.0)
                    .toVector()
                    .subtract(tnt.location.toVector())

                val force = tnt.yield * tnt.yield / (tntJumpStrengthReductionConstant + distance.length())
                val resultingForce = distance.clone().normalize().multiply(force)

                resultingForce.y /= distance.length() + tntJumpYAxisReductionConstant
                damaged.velocity = resultingForce
                damager
            }

            is Silverfish, is IronGolem -> {
                LastHit.getLastHit(entity)?.apply {
                    this.damager = e.damager
                    time = System.currentTimeMillis()
                } ?: LastHit(entity, e.damager, System.currentTimeMillis())
                null
            }

            else -> null
        }

        if (damager == null) return
        if (arena.isSpectator(damager) || arena.isRespawning(damager)) {
            e.isCancelled = true
            return
        }

        if (arena.getTeam(entity) == arena.getTeam(damager)) {
            if (e.damager !is TNTPrimed) {
                e.isCancelled = true
            }
            return
        }

        // protection after re-spawn
        val invulnerability = BedWarsTeam.reSpawnInvulnerability[entity.uniqueId]
        if (invulnerability != null && invulnerability > System.currentTimeMillis()) {
            e.isCancelled = true
            return
        }
        // but if the damager is the re-spawning player remove protection
        BedWarsTeam.reSpawnInvulnerability.remove(damager.uniqueId)

        LastHit.getLastHit(entity)?.apply {
            this.damager = damager
            time = System.currentTimeMillis()
        } ?: LastHit(entity, damager, System.currentTimeMillis())

        // #274
        // if player gets hit show him
        if (!arena.showTime.containsKey(entity)) return
        BedWars.plugin.run {
            for (on in arena.world.players) {
                BedWars.nms.showArmor(entity, on)
                //BedWars.nms.showPlayer(p, on);
            }
            arena.showTime.remove(entity)
            entity.removePotionEffect(PotionEffectType.INVISIBILITY)
            entity.sendLangMsg(Messages.INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN)
            Bukkit.getPluginManager().callEvent(PlayerInvisibilityPotionEvent(
                PlayerInvisibilityPotionEvent.Type.REMOVED,
                entity,
                arena
            ))
        }
    }

    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        val victim = e.entity
        var killer = victim.killer
        val arena = BedWars.plugin.arenaManager.getArena(victim) ?: return
        if (BedWars.serverType === ServerType.MULTIARENA && BedWars.lobbyWorld == victim.world.name) e.deathMessage = null

        val victimsTeam = arena.getTeam(victim)
        if (arena.status != GameState.PLAYING || arena.isSpectator(victim) || victimsTeam == null) {
            victim.spigot().respawn()
            return
        }

        BedWars.nms.clearArrowsFromPlayerBody(victim)

        var message = Messages.PLAYER_DIE_UNKNOWN_REASON_REGULAR
        var cause = PlayerKillCause.UNKNOWN
        if (victimsTeam.isBedDestroyed) {
            message = Messages.PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL
            cause = PlayerKillCause.UNKNOWN_FINAL_KILL
        }

        var killersTeam: ITeam? = null

        val damageEvent = victim.lastDamageCause
        when (damageEvent?.cause) {
            EntityDamageEvent.DamageCause.ENTITY_EXPLOSION -> {
                val lh = LastHit.getLastHit(victim)
                if (lh != null) {
                    if (lh.time >= System.currentTimeMillis() - 15000) {
                        if (lh.damager is Player) killer = lh.damager as Player?
                        if (killer != null && killer.uniqueId == victim.uniqueId) killer = null
                    }
                }
                message = if (killer == null)
                    if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL else Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR
                else if (killer !== victim)
                    if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL else Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL
                else if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL else Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR
                cause = if (victimsTeam.isBedDestroyed) PlayerKillCause.EXPLOSION_FINAL_KILL else PlayerKillCause.EXPLOSION
            }
            EntityDamageEvent.DamageCause.VOID -> {
                val lh = LastHit.getLastHit(victim)
                if (lh != null && lh.time >= System.currentTimeMillis() - 15000) {
                    if (lh.damager is Player) killer = lh.damager as Player
                    if (killer != null && killer.uniqueId == victim.uniqueId) killer = null
                }
                message = if (killer == null)
                    if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL else Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL
                else if (killer !== victim)
                    if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL else Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL
                else if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL else Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL
                cause = if (victimsTeam.isBedDestroyed) PlayerKillCause.VOID_FINAL_KILL else PlayerKillCause.VOID
            }
            EntityDamageEvent.DamageCause.ENTITY_ATTACK -> run {
                if (killer != null) {
                    message = if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_PVP_FINAL_KILL else Messages.PLAYER_DIE_PVP_REGULAR_KILL
                    cause = if (victimsTeam.isBedDestroyed) PlayerKillCause.PVP_FINAL_KILL else PlayerKillCause.PVP
                    return@run
                }

                val lh = LastHit.getLastHit(victim) ?: return@run
                if (lh.time < System.currentTimeMillis() - 15000 || !BedWars.nms.isDespawnable(lh.damager!!)) return@run

                val d = BedWars.nms.despawnables[lh.damager!!.uniqueId]!!
                killersTeam = d.team
                message = if (d.entity.type == EntityType.IRON_GOLEM)
                    if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_IRON_GOLEM_FINAL_KILL else Messages.PLAYER_DIE_IRON_GOLEM_REGULAR
                else if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_DEBUG_FINAL_KILL else Messages.PLAYER_DIE_DEBUG_REGULAR
                cause = if (victimsTeam.isBedDestroyed) d.deathFinalCause else d.deathRegularCause
            }
            EntityDamageEvent.DamageCause.PROJECTILE if killer != null -> {
                message = if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_SHOOT_FINAL_KILL else Messages.PLAYER_DIE_SHOOT_REGULAR
                cause = if (victimsTeam.isBedDestroyed) PlayerKillCause.PLAYER_SHOOT_FINAL_KILL else PlayerKillCause.PLAYER_SHOOT
            }
            EntityDamageEvent.DamageCause.FALL -> run {
                val lh = LastHit.getLastHit(victim) ?: return@run
                // check if kicked off in the last 10 seconds
                if (lh.time < System.currentTimeMillis() - 10000) return@run

                if (lh.damager is Player) killer = lh.damager as Player

                if (killer != null && killer.uniqueId == victim.uniqueId) killer = null
                cause = if (victimsTeam.isBedDestroyed) PlayerKillCause.PLAYER_PUSH_FINAL else PlayerKillCause.PLAYER_PUSH
                if (killer == null) return@run

                message = if (killer !== victim)
                    if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_KNOCKED_BY_FINAL_KILL else Messages.PLAYER_DIE_KNOCKED_BY_REGULAR_KILL
                else if (victimsTeam.isBedDestroyed) Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL else Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL
            }
            else -> {}
        }

        if (killer != null) killersTeam = arena.getTeam(killer)

        val playerKillEvent = PlayerKillEvent(arena, victim, victimsTeam, killer, killersTeam, cause) { Language.getMsg(it, message) }
        Bukkit.getPluginManager().callEvent(playerKillEvent)

        if (killer != null && playerKillEvent.playSound) {
            playSound(ConfigPath.SOUNDS_KILL, killer)
        }

        for (on in arena.allPlayers) {
            val lang = Language.getLanguage(on)
            on.sendMessage(playerKillEvent.message(on)
                .replace("{PlayerColor}", "${victimsTeam.color.chat}")
                .replace("{PlayerName}", victim.displayName)
                .replace("{PlayerNameUnformatted}", victim.name)
                .replace("{PlayerTeamName}", victimsTeam.getDisplayName(lang))
                .replace("{KillerColor}", killersTeam?.color?.chat?.toString() ?: "")
                .replace("{KillerName}", killer?.displayName ?: "")
                .replace("{KillerNameUnformatted}", killer?.name ?: "")
                .replace("{KillerTeamName}", killersTeam?.getDisplayName(lang) ?: "")
            )
        }

        // handle drops
        if (PlayerDrops.handlePlayerDrops(arena, victim, killer, victimsTeam, killersTeam, cause, e.drops)) {
            e.drops.clear()
        }

        // send respawn packet
        BedWars.plugin.run(delay = 3) { victim.spigot().respawn() }

        // reset last damager
        LastHit.getLastHit(victim)?.damager = null


        if (victimsTeam.isBedDestroyed && victimsTeam.size == 1 && arena.config.getBoolean(ConfigPath.ARENA_DISABLE_GENERATOR_FOR_EMPTY_TEAMS)) {
            victimsTeam.generators.forEach { it.disable() }
            victimsTeam.generators.clear()
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    fun onRespawn(e: PlayerRespawnEvent) {
        val player = e.player
        val arena = BedWars.plugin.arenaManager.getArena(player)

        if (arena == null) {
            val ss = SetupSession.getSession(player.uniqueId)
            if (ss != null) e.setRespawnLocation(player.world.spawnLocation)
            return
        }

        if (arena.isSpectator(player)) {
            e.setRespawnLocation(arena.spectatorLocation)
            val iso = Language.getLanguage(player).iso
            arena.teams
                .flatMap { it.generators }
                .plus(arena.oreGenerators)
                .forEach { it.updateHolograms(player, iso) }
            for (sh in ShopHolo.shopHolo) {
                if (sh.a === arena) {
                    sh.updateForPlayer(player, iso)
                }
            }
            arena.sendSpectatorCommandItems(player)
            return
        }

        val t = arena.getTeam(player)
        if (t == null) {
            e.setRespawnLocation(arena.respawnLocation)
            BedWars.plugin.logger.severe("${player.name} re-spawn error on ${arena.name}[${arena.worldName}] because the team was NULL and he was not spectating!")
            BedWars.plugin.logger.severe("This is caused by one of your plugins: remove or configure any re-spawn related plugins.")
            arena.removePlayer(player, false)
            arena.removeSpectator(player, false)
            return
        }

        if (t.isBedDestroyed) {
            e.setRespawnLocation(arena.spectatorLocation)
            arena.addSpectator(player, true, null)
            t.members.remove(player)
            player.sendLangMsg(Messages.PLAYER_DIE_ELIMINATED_CHAT)
            if (t.members.isNotEmpty()) return

            Bukkit.getPluginManager().callEvent(TeamEliminatedEvent(arena, t))
            arena.world.players.forEach { it.sendLangMsg(Messages.TEAM_ELIMINATED_CHAT,
                "{TeamColor}" to "${t.color.chat}",
                "{TeamName}" to t.getDisplayName(Language.getLanguage(it))
            ) }
            BedWars.plugin.run(delay = 40) { arena.checkWinner() }
            return
        }

        //respawn session
        val respawnTime = BedWars.config.getInt(ConfigPath.GENERAL_CONFIGURATION_RE_SPAWN_COUNTDOWN)
        if (respawnTime > 1) {
            e.setRespawnLocation(arena.respawnLocation)
            arena.startRespawnSession(player, respawnTime)
            return
        }

        // instant respawn configuration
        e.setRespawnLocation(t.spawn!!)
        t.respawnMember(player)
    }

    @EventHandler
    fun onMove(e: PlayerMoveEvent) {
        val to = e.to ?: return
        val player = e.player
        val arena = BedWars.plugin.arenaManager.getArena(player)
        if (arena == null) {
            if (BedWars.serverType != ServerType.MULTIARENA ||
                !BedWars.config.getBoolean(ConfigPath.LOBBY_VOID_TELEPORT_ENABLED) ||
                !player.world.name.equals(BedWars.config.lobbyWorldName, ignoreCase = true) ||
                to.y >= BedWars.config.getInt(ConfigPath.LOBBY_VOID_TELEPORT_HEIGHT)
            ) return

            player.teleportSafe(BedWars.config.getConfigLoc("lobbyLoc") ?: return)
            return
        }

        val from = e.from.chunk
        // todo check on x y z change... not head rotation because this is really spammy
        if (from.x != to.chunk.x ||
            from.z != to.chunk.z ||
            from.world != to.world
        ) {
            /* update armor-stands hidden by nms */

            val iso = Language.getLanguage(player).iso
            arena.teams
                .flatMap { it.generators }
                .plus(arena.oreGenerators)
                .forEach { it.updateHolograms(player, iso) }

            for (sh in ShopHolo.shopHolo) {
                if (sh.a === arena) {
                    sh.updateForPlayer(player, iso)
                }
            }

            // hide armor for those with invisibility potions
            if (!arena.showTime.isEmpty()) {
                // generic hide packets
                for ((key, value) in arena.showTime) {
                    if (value > 1) BedWars.nms.hideArmor(key, player)
                }
                // if the moving player has invisible armor
                if (arena.showTime.containsKey(player)) {
                    for (p in arena.allPlayers) {
                        BedWars.nms.hideArmor(player, p)
                    }
                }
            }
        }

        if (arena.isSpectator(player) || arena.isRespawning(player)) {
            if (to.y < 0) player.apply {
                teleportSafe(if (arena.isSpectator(this)) arena.spectatorLocation else arena.respawnLocation)
                allowFlight = true
                isFlying = true
                // how to remove fall velocity?
            }
            return
        }
        if (arena.status == GameState.PLAYING) {
            if (player.location.blockY <= arena.yKillHeight) {
                BedWars.nms.voidKill(player)
            }
            for (t in arena.teams) {
                if (player.location.distance(t.bed) < 4) {
                    if (t.isMember(player) && t is BedWarsTeam) {
                        if (t.getBedHolo(player) == null) continue
                        if (!t.getBedHolo(player)!!.isHidden) {
                            t.getBedHolo(player)!!.hide()
                        }
                    }
                } else {
                    if (t.isMember(player) && t is BedWarsTeam) {
                        if (t.getBedHolo(player) == null) continue
                        if (t.getBedHolo(player)!!.isHidden) {
                            t.getBedHolo(player)!!.show()
                        }
                    }
                }
            }
            if (e.from !== to) {
                BedWars.plugin.afkManager.setAFK(player, null)
            }
            return
        }
        if (player.location.blockY <= 0) {
            player.teleportSafe(arena.getTeam(player)?.spawn ?: arena.spectatorLocation)
        }
    }

    @EventHandler
    fun onProjectileHit(e: ProjectileHitEvent) {
        val projectile = e.entity as? Snowball ?: return
        val shooter = projectile.shooter as? Player ?: return

        val arena = BedWars.plugin.arenaManager.getArena(shooter) ?: return
        if (!arena.isPlayer(shooter)) return

        spawnSilverFish(projectile.location, arena.getTeam(shooter)!!)
    }

    @EventHandler
    fun onItemFrameDamage(e: EntityDamageByEntityEvent) {
        val frame = e.entity as? ItemFrame ?: return

        val arena = BedWars.plugin.arenaManager.getArenaByWorld(e.entity.world.name)
        if (arena != null || BedWars.serverType == ServerType.MULTIARENA && BedWars.lobbyWorld == frame.world.name) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onEntityDeath(e: EntityDeathEvent) {
        val entity = e.entity
        // clean if necessary
        BedWars.nms.despawnables.remove(entity.uniqueId)

        if (BedWars.plugin.arenaManager.getArenaByWorld(entity.location.world!!.name) == null) return
        if (entity.type != EntityType.IRON_GOLEM && entity.type != EntityType.SILVERFISH) return

        e.drops.clear()
        e.droppedExp = 0
    }

    @EventHandler
    fun onEat(e: PlayerItemConsumeEvent) {
        if (e.item.type != BedWars.nms.materialCake()) return
        if (BedWars.plugin.arenaManager.getArenaByWorld(e.player.world.name) != null) {
            e.isCancelled = true
        }
    }

    companion object {
        private val HEALTH_FORMAT = DecimalFormat("00.#")

        private fun spawnSilverFish(loc: Location, t: ITeam) = BedWars.nms.spawnSilverfish(
            loc,
            t,
            BedWars.shop.getDouble(ConfigPath.SHOP_SPECIAL_SILVERFISH_SPEED),
            BedWars.shop.getDouble(ConfigPath.SHOP_SPECIAL_SILVERFISH_HEALTH),
            BedWars.shop.getInt(ConfigPath.SHOP_SPECIAL_SILVERFISH_DESPAWN),
            BedWars.shop.getDouble(ConfigPath.SHOP_SPECIAL_SILVERFISH_DAMAGE)
        )
    }
}
