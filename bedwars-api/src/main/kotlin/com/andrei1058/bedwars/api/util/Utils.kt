package com.andrei1058.bedwars.api.util

import com.andrei1058.bedwars.api.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import io.papermc.lib.PaperLib
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerTeleportEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

object Utils {
    private val isPaper = runCatching { Class.forName("com.destroystokyo.paper.PaperConfig") }.isSuccess


    fun ItemStack.editMeta(run: ItemMeta.() -> Unit) {
        itemMeta = itemMeta?.also(run)
    }

    fun Entity.teleportSafe(location: Location, cause: PlayerTeleportEvent.TeleportCause = PlayerTeleportEvent.TeleportCause.PLUGIN) {
        if (isPaper && BedWars.INSTANCE.configs.main.getBoolean(ConfigPath.GENERAL_CONFIGURATION_PERFORMANCE_PAPER_FEATURES)) {
            PaperLib.teleportAsync(this, location, cause)
            return
        }
        teleport(location, cause)
    }


    /**
     * create TextComponent message
     */
    fun component(msg: String, hover: String, click: String, clickAction: ClickEvent.Action = ClickEvent.Action.RUN_COMMAND) = TextComponent(msg).apply {
        if (hover.isNotEmpty()) hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(hover).create())
        if (click.isNotEmpty()) clickEvent = ClickEvent(clickAction, click)
    }

    /**
     * create TextComponent message
     */
    fun Player.message(
        msg: String,
        hover: String,
        click: String,
        clickAction: ClickEvent.Action = ClickEvent.Action.RUN_COMMAND
    ) = spigot().sendMessage(component(msg, hover, click, clickAction))
}