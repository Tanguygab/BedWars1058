package com.andrei1058.bedwars.commands.bedwars.subcmds

import com.andrei1058.bedwars.commands.bedwars.MainCommand
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

abstract class CooldownCommand(
    parent: MainCommand,
    name: String,
    private val delay: Long,
    priority: Int
) : SubCommand(parent, name, priority = priority) {

    private val cooldowns = ConcurrentHashMap<UUID, Long>()

    protected fun isOnCooldown(player: UUID) = (cooldowns[player] ?: 0) > System.currentTimeMillis() - delay

    protected fun setCooldown(player: UUID) {
        cooldowns[player] = System.currentTimeMillis()
    }

    fun removeCooldown(player: UUID) = cooldowns.remove(player)
}