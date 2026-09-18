package com.andrei1058.bedwars.listeners

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.arena.data.LastHit
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.ExplosionPrimeEvent
import org.bukkit.event.entity.ProjectileHitEvent

class FireballListener(private val plugin: BedWars) : Listener {
    private val config = plugin.mainConfig
    private val fireballExplosionSize = config.getDouble(ConfigPath.GENERAL_FIREBALL_EXPLOSION_SIZE)
    private val fireballMakeFire = config.getBoolean(ConfigPath.GENERAL_FIREBALL_MAKE_FIRE)
    private val fireballHorizontal = config.getDouble(ConfigPath.GENERAL_FIREBALL_KNOCKBACK_HORIZONTAL) * -1
    private val fireballVertical = config.getDouble(ConfigPath.GENERAL_FIREBALL_KNOCKBACK_VERTICAL)

    private val damageSelf = config.getDouble(ConfigPath.GENERAL_FIREBALL_DAMAGE_SELF)
    private val damageEnemy = config.getDouble(ConfigPath.GENERAL_FIREBALL_DAMAGE_ENEMY)
    private val damageTeammates = config.getDouble(ConfigPath.GENERAL_FIREBALL_DAMAGE_TEAMMATES)

    @EventHandler
    fun fireballHit(e: ProjectileHitEvent) {
        val entity = e.entity
        if (entity !is Fireball) return
        val location = entity.location

        val shooter = entity.shooter
        if (shooter !is Player) return

        val arena = plugin.arenaManager.getArena(shooter) ?: return
        val team = arena.getTeam(shooter)
        val world = location.world ?: return
        val vector = location.toVector()

        val nearbyEntities = world.getNearbyEntities(location, fireballExplosionSize, fireballExplosionSize, fireballExplosionSize)
        for (player in nearbyEntities) {
            if (player !is Player) continue
            if (!plugin.arenaManager.isPlaying(player)) continue


            val playerVector = player.location.toVector()
            val normalizedVector = vector.subtract(playerVector).normalize()
            val horizontalVector = normalizedVector.multiply(fireballHorizontal)

            var y = normalizedVector.y
            if (y < 0) y += 1.5

            if (y <= 0.5) y = fireballVertical * 1.5 // kb for not jumping
            else y *= fireballVertical * 1.5 // kb for jumping

            player.velocity = horizontalVector.setY(y)

            val lh = LastHit.getLastHit(player)
            if (lh != null) {
                lh.damager = shooter
                lh.time = System.currentTimeMillis()
            } else {
                LastHit(player, shooter, System.currentTimeMillis())
            }

            val target = when {
                player == shooter -> damageSelf // damage shooter
                arena.getTeam(player) == team -> damageTeammates // damage teammates
                else -> damageEnemy // damage enemies
            }

            if (target > 0) player.damage(target)
        }
    }

    @EventHandler
    fun fireballDirectHit(e: EntityDamageByEntityEvent) {
        if (e.damager !is Fireball) return
        val player = e.entity
        if (player !is Player) return

        if (plugin.arenaManager.getArena(player) == null) return
        e.isCancelled = true
    }

    @EventHandler
    fun fireballPrime(e: ExplosionPrimeEvent) {
        val shooter = (e.entity as? Fireball)?.shooter
        if (shooter !is Player) return

        if (plugin.arenaManager.getArena(shooter) == null) return
        e.fire = fireballMakeFire
    }
}
