package com.andrei1058.bedwars.commands.bedwars.subcmds

import com.andrei1058.bedwars.api.command.SubCommand
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

abstract class CooldownCommand(
    name: String,
    private val delay: Long,
    isShown: Boolean,
    priority: Int
) : SubCommand(name, isShown = isShown, priority = priority) {

    private val cooldowns = ConcurrentHashMap<UUID, Long>()

    protected fun isOnCooldown(player: UUID) = (cooldowns[player] ?: 0) > System.currentTimeMillis() - delay

    protected fun setCooldown(player: UUID) {
        cooldowns[player] = System.currentTimeMillis()
    }

    fun removeCooldown(player: UUID) = cooldowns.remove(player)
}