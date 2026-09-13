package com.andrei1058.bedwars.api.util

import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.util.NumberConversions
import org.bukkit.util.Vector

/*
    Used for tracing blocks between two vectors.
    Note: this could return the same blocks multiple times.
*/
/**
 * Constructs a BlockRay instance.
 *
 * @param world bukkit world to get the blocks from.
 * @param src   the source vector (where we shoot the ray).
 * @param dst   the destination vector.
 * @param step  how frequent to check the ray (0.25 - 0.5 recommended).
 */
class BlockRay(
    private val world: World,
    src: Vector,
    dst: Vector,
    step: Double
) : Iterator<Block> {

    // How much `pov` needs to change in order to reach the target
    private val delta: Vector = Vector(
        dst.blockX - src.blockX,
        dst.blockY - src.blockY,
        dst.blockZ - src.blockZ
    )

    // How much one part is worth
    private val multiple = 1 / (delta.length() / step)
    // How many sections `delta` could be split up to
    private val parts = NumberConversions.ceil(delta.length() / step)
    private var consumed = 0

    // Coordinates for where we should shoot our ray
    // Exactly from the center.
    private val xOffset = src.blockX + 0.5
    private val yOffset = src.blockY + 0.5
    private val zOffset = src.blockZ + 0.5

    // The last vector components (last offset)
    private var lcx = 0.0
    private var lcy = 0.0
    private var lcz = 0.0

    // Where we hold our components per step
    // 0 -> X component
    // 1 -> Y component
    // 2 -> Z component
    // 3 -> XY component
    // 4 -> XZ component
    // 5 -> ZY component
    // 6 -> Resultant
    private val blockQueue = arrayOfNulls<Block>(7)

    // The current component (gets decremented whenever next() is called)
    private var currentBlock = blockQueue.size - 1

    init {
        require(delta.lengthSquared() != 0.0) { "The source vector is the same as the destination vector" }
        scan()
    }

    override fun hasNext() = consumed <= parts || currentBlock >= 0

    /**
     * This could return similar blocks multiple times.
     * 
     * @return Current block at the ray's position.
     */
    override fun next(): Block {
        if (!hasNext()) throw NoSuchElementException("No more blocks")

        if (currentBlock < 0) {
            scan()
            currentBlock = blockQueue.size - 1
        }

        return blockQueue[currentBlock--]!!
    }

    private fun scan() {
        val cx = (multiple * delta.x * consumed)
        val cy = (multiple * delta.y * consumed)
        val cz = (multiple * delta.z * consumed)

        val lastXFloor = NumberConversions.floor(xOffset + lcx)
        val lastYFloor = NumberConversions.floor(yOffset + lcy)
        val lastZFloor = NumberConversions.floor(zOffset + lcz)

        val currentXFloor = NumberConversions.floor(xOffset + cx)
        val currentYFloor = NumberConversions.floor(yOffset + cy)
        val currentZFloor = NumberConversions.floor(zOffset + cz)


        // Get the X component separately
        blockQueue[0] = world.getBlockAt(currentXFloor, lastYFloor, lastZFloor)

        // Get the Y component separately
        blockQueue[1] = world.getBlockAt(lastXFloor, currentYFloor, lastZFloor)

        // Get the Z component separately
        blockQueue[2] = world.getBlockAt(lastXFloor, lastYFloor, currentZFloor)

        // Get the XY separately
        blockQueue[3] = world.getBlockAt(currentXFloor, currentYFloor, lastZFloor)

        // Get the XZ separately
        blockQueue[4] = world.getBlockAt(currentXFloor, lastYFloor, currentZFloor)

        // Get the ZY separately
        blockQueue[5] = world.getBlockAt(lastXFloor, currentYFloor, currentZFloor)

        // Get the resultant (cx, cy, cz) together
        blockQueue[6] = world.getBlockAt(currentXFloor, currentYFloor, currentZFloor)

        lcx = cx
        lcy = cy
        lcz = cz

        ++consumed
    }
}
