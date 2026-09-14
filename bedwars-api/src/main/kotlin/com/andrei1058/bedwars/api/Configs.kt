package com.andrei1058.bedwars.api

import com.andrei1058.bedwars.api.configuration.ConfigManager

interface Configs {
    /**
     * Get plugin main configuration.
     */
    val main: ConfigManager
    val signs: ConfigManager
    val generators: ConfigManager
    /**
     * Get shop configuration.
     */
    val shop: ConfigManager
    val upgrades: ConfigManager
}