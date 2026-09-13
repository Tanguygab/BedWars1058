package com.andrei1058.bedwars.configuration

import com.andrei1058.bedwars.api.configuration.ConfigManager
import org.bukkit.plugin.Plugin

class MoneyConfig(plugin: Plugin) : ConfigManager(plugin, "rewards", plugin.dataFolder.path) {

    /**
     * Initialize money config.
     */
    init {
        options().copyDefaults(true)
        default(
            "money-rewards.per-minute" to 5,
            "money-rewards.per-teammate" to 30,
            "money-rewards.game-win" to 90,
            "money-rewards.bed-destroyed" to 60,
            "money-rewards.final-kill" to 40,
            "money-rewards.regular-kill" to 10
        )
        save()
    }
}