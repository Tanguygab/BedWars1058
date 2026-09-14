package com.andrei1058.bedwars.arena.upgrades

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.team.ITeam
import com.andrei1058.bedwars.api.configuration.ConfigPath
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.scheduler.BukkitRunnable
import kotlin.random.Random

class HealPoolTask(private val plugin: BedWars, val team: ITeam) : BukkitRunnable() {
    private val arena = team.arena
    private val spawn = team.spawn!!
    private val radius = team.arena.config.getInt(ConfigPath.ARENA_ISLAND_RADIUS)
    private val maxX = spawn.blockX + radius
    private val minX = spawn.blockX - radius
    private val maxY = spawn.blockY + radius
    private val minY = spawn.blockY - radius
    private val maxZ = spawn.blockZ + radius
    private val minZ = spawn.blockZ - radius

    init {
        runTaskTimerAsynchronously(plugin, 0, 80L)
        healPoolTasks.add(this)
    }

    override fun run() {
        val players = if (plugin.mainConfig.getBoolean(ConfigPath.GENERAL_CONFIGURATION_HEAL_POOL_SEEN_TEAM_ONLY))
            team.members
        else arena.players

        val nms = plugin.versionSupport
        for (x in minX..maxX) {
            for (y in minY..maxY) {
                for (z in minZ..maxZ) {
                    val location = Location(arena.world, x + .5, y + .5, z + .5)
                    if (location.block.type != Material.AIR) continue

                    val chance = Random.nextInt(9)
                    if (chance != 0) continue

                    for (p in players) {
                        nms.playVillagerEffect(p, location)
                    }
                }
            }
        }
    }

    companion object {
        private val healPoolTasks = mutableListOf<HealPoolTask>()

        fun exists(arena: IArena, team: ITeam) = healPoolTasks.any { it.arena === arena && it.team === team }

        fun removeForArena(a: IArena) {
            for (task in healPoolTasks) {
                if (task.arena !== a) continue
                task.cancel()
                healPoolTasks.remove(task)
            }
        }

        fun removeForArena(a: String) {
            for (task in healPoolTasks) {
                if (task.arena.worldName != a) continue
                task.cancel()
                healPoolTasks.remove(task)
            }
        }

        fun removeForTeam(team: ITeam) {
            for (task in healPoolTasks) {
                if (task.team != team) continue
                task.cancel()
                healPoolTasks.remove(task)
            }
        }
    }
}
