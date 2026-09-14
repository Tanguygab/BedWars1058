package com.andrei1058.bedwars.api.util

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.server.VersionSupport
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.util.Vector

class BlastProtectionUtil(private val versionSupport: VersionSupport, private val rayBlockedByGlass: Boolean) {
    /**
     * Check if block is protected by blast-proof glass or an unbreakable block from a point of view
     * 
     * 
     * if pov is null, block is checked by [VersionSupport.isGlass] and [IArena.isBlockPlaced] instead.
     * Otherwise, a ray tracing is performed to check whether there is any glass block or an unbreakable block.
     * 
     * 
     * If arena is null, then glass only be checked.
     * 
     * @param arena Arena instance.
     * @param pov   the point of view.
     * @param block the block instance.
     * @param step  how frequent to check the ray (0.25 - 0.5 recommended).
     * @return whether there's unbreakable block between the pov and the block
     */
    fun isProtected(arena: IArena, pov: Location, block: Block, step: Double): Boolean {
        if (arena.isProtected(block.location) || arena.isTeamBed(block.location)) return true

        // Trace blocks from pov to the block location
        val targetVectors = mutableListOf<Vector>()

        val alteredRayStep = 0.73
        // x
        var xRayRadius = alteredRayStep * -1
        while (xRayRadius <= alteredRayStep) {
            // y
            var yRayRadius = alteredRayStep * -1
            while (yRayRadius <= alteredRayStep) {
                // z
                var zRayRadius = alteredRayStep * -1
                while (zRayRadius <= alteredRayStep) {
                    targetVectors.add(pov.clone().toVector().toBlockVector().add(Vector(xRayRadius, yRayRadius, zRayRadius)))
                    zRayRadius += alteredRayStep
                }
                yRayRadius += alteredRayStep
            }
            xRayRadius += alteredRayStep
        }

        val destination = block.location.toVector()
        val protectedTimes = targetVectors.count { targetVector -> try {
            val ray = BlockRay(block.world, targetVector, destination, step)
            ray.asSequence().any { it.type != Material.AIR && (
                    rayBlockedByGlass && versionSupport.isGlass(it.type) ||
                    !arena.isBlockPlaced(it) && !arena.isAllowMapBreak
            ) }
        } catch (_: IllegalArgumentException) { false } }

        return targetVectors.size - protectedTimes < 6
    }
}
