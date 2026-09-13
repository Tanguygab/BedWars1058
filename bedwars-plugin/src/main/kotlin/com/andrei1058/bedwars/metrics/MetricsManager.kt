package com.andrei1058.bedwars.metrics

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.support.citizens.JoinNPC
import org.bstats.bukkit.Metrics
import org.bstats.charts.SimplePie

class MetricsManager(plugin: BedWars) {
    private val metrics = Metrics(plugin, 1885)

    init {
        // base metrics
        appendPie("server_type") { BedWars.serverType.toString() }
        appendPie("default_language") { Language.defaultLanguage.iso }
        appendPie("auto_scale") { BedWars.autoscale.toString() }
        appendPie("party_adapter") { BedWars.party.javaClass.getName() }
        appendPie("chat_adapter") { BedWars.chatSupport.javaClass.getName() }
        appendPie("level_adapter") { BedWars.levelSupport.javaClass.getName() }
        appendPie("db_adapter") { BedWars.remoteDatabase.javaClass.getName() }
        appendPie("map_adapter") { BedWars.api.restoreAdapter.javaClass.getName() }
        appendPie("citizens_support") { JoinNPC.isCitizensSupport.toString() }
    }

    fun appendPie(id: String, callable: () -> String) {
        metrics.addCustomChart(SimplePie(id, callable))
    }
}
