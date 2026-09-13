package com.andrei1058.bedwars.api

import com.andrei1058.bedwars.api.configuration.ConfigManager

interface Configs {
    /**
     * Get plugin main configuration.
     */
    val mainConfig: ConfigManager
    val signsConfig: ConfigManager?
    val generatorsConfig: ConfigManager
    /**
     * Get shop configuration.
     */
    val shopConfig: ConfigManager
    val upgradesConfig: ConfigManager
}