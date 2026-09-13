package com.andrei1058.bedwars.api.tasks

import com.andrei1058.bedwars.api.arena.IArena
import org.bukkit.scheduler.BukkitTask

interface ArenaTask {
    val arena: IArena
    val bukkitTask: BukkitTask
    val task get() = bukkitTask.taskId
    fun cancel()
}