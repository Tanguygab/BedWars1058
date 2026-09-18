package com.andrei1058.bedwars.metrics

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.language.Language
import org.bstats.bukkit.Metrics
import org.bstats.charts.SimplePie

class MetricsManager(plugin: BedWars) {
    private val metrics = Metrics(plugin, 1885)

    init {
        // base metrics
        appendPie("server_type") { "${plugin.serverType}" }
        appendPie("default_language") { Language.defaultLanguage.iso }
        appendPie("auto_scale") { "${plugin.autoScale}" }
        appendPie("party_adapter") { plugin.partyUtil.javaClass.name }
        appendPie("chat_adapter") { BedWars.chatSupport.javaClass.name }
        appendPie("level_adapter") { plugin.levelManager.javaClass.name }
        appendPie("db_adapter") { plugin.database.javaClass.name }
        appendPie("map_adapter") { plugin.restoreAdapter.javaClass.name }
        appendPie("citizens_support") { "${plugin.npcSupport != null}" }
    }

    fun appendPie(id: String, callable: () -> String) {
        metrics.addCustomChart(SimplePie(id, callable))
    }
}
