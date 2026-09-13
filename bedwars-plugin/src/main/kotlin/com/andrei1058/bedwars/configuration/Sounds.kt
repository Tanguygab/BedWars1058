/*
 * BedWars1058 - A bed wars mini-game.
 * Copyright (C) 2021 Andrei Dascălu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * Contact e-mail: andrew.dascalu@gmail.com
 */
package com.andrei1058.bedwars.configuration

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.getForCurrentVersion
import com.andrei1058.bedwars.api.arena.NextEvent
import com.andrei1058.bedwars.api.configuration.ConfigManager
import com.andrei1058.bedwars.api.configuration.ConfigPath
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.entity.Player


object Sounds : ConfigManager(BedWars.plugin, "sounds", BedWars.plugin.dataFolder.path) {

    fun init() {
        addDefSound(
            "game-end",
            getForCurrentVersion("ITEM_TRIDENT_THUNDER", "AMBIENCE_THUNDER", "ENTITY_LIGHTNING_THUNDER")
        )
        addDefSound("rejoin-denied", getForCurrentVersion("ENTITY_VILLAGER_NO", "VILLAGER_NO", "ENTITY_VILLAGER_NO"))
        addDefSound("rejoin-allowed", getForCurrentVersion("ENTITY_SLIME_JUMP", "SLIME_WALK", "ENTITY_SLIME_JUMP"))
        addDefSound("spectate-denied", getForCurrentVersion("ENTITY_VILLAGER_NO", "VILLAGER_NO", "ENTITY_VILLAGER_NO"))
        addDefSound("spectate-allowed", getForCurrentVersion("ENTITY_SLIME_JUMP", "SLIME_WALK", "ENTITY_SLIME_JUMP"))
        addDefSound("join-denied", getForCurrentVersion("ENTITY_VILLAGER_NO", "VILLAGER_NO", "ENTITY_VILLAGER_NO"))
        addDefSound("join-allowed", getForCurrentVersion("ENTITY_SLIME_JUMP", "SLIME_WALK", "ENTITY_SLIME_JUMP"))
        addDefSound("spectator-gui-click", getForCurrentVersion("ENTITY_SLIME_JUMP", "SLIME_WALK", "ENTITY_SLIME_JUMP"))
        addDefSound(
            ConfigPath.SOUNDS_COUNTDOWN_TICK,
            getForCurrentVersion("ENTITY_CHICKEN_EGG", "CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG")
        )
        addDefSound(
            ConfigPath.SOUNDS_COUNTDOWN_TICK_X + "5",
            getForCurrentVersion("ENTITY_CHICKEN_EGG", "CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG")
        )
        addDefSound(
            ConfigPath.SOUNDS_COUNTDOWN_TICK_X + "4",
            getForCurrentVersion("ENTITY_CHICKEN_EGG", "CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG")
        )
        addDefSound(
            ConfigPath.SOUNDS_COUNTDOWN_TICK_X + "3",
            getForCurrentVersion("ENTITY_CHICKEN_EGG", "CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG")
        )
        addDefSound(
            ConfigPath.SOUNDS_COUNTDOWN_TICK_X + "2",
            getForCurrentVersion("ENTITY_CHICKEN_EGG", "CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG")
        )
        addDefSound(
            ConfigPath.SOUNDS_COUNTDOWN_TICK_X + "1",
            getForCurrentVersion("ENTITY_CHICKEN_EGG", "CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG")
        )
        addDefSound(
            ConfigPath.SOUND_GAME_START,
            getForCurrentVersion("BLOCK_SLIME_BLOCK_FALL", "SLIME_ATTACK", "BLOCK_SLIME_FALL")
        )

        addDefSound(
            ConfigPath.SOUNDS_KILL,
            getForCurrentVersion("ENTITY_EXPERIENCE_ORB_PICKUP", "ORB_PICKUP", "ENTITY_EXPERIENCE_ORB_PICKUP")
        )

        addDefSound(
            ConfigPath.SOUNDS_BED_DESTROY,
            getForCurrentVersion("ENTITY_ENDER_DRAGON_GROWL", "ENDERDRAGON_GROWL", "ENTITY_ENDERDRAGON_GROWL")
        )
        addDefSound(
            ConfigPath.SOUNDS_BED_DESTROY_OWN,
            getForCurrentVersion("ENTITY_WITHER_DEATH", "WITHER_DEATH", "ENTITY_WITHER_DEATH")
        )
        addDefSound(
            ConfigPath.SOUNDS_INSUFF_MONEY,
            getForCurrentVersion("ENTITY_VILLAGER_NO", "VILLAGER_NO", "ENTITY_VILLAGER_NO")
        )
        addDefSound(
            ConfigPath.SOUNDS_BOUGHT,
            getForCurrentVersion("ENTITY_VILLAGER_YES", "VILLAGER_YES", "ENTITY_VILLAGER_YES")
        )

        addDefSound(
            NextEvent.BEDS_DESTROY.soundPath,
            getForCurrentVersion("ENTITY_ENDER_DRAGON_GROWL", "ENDERDRAGON_GROWL", "ENTITY_ENDERDRAGON_GROWL")
        )
        addDefSound(
            NextEvent.DIAMOND_GENERATOR_TIER_II.soundPath,
            getForCurrentVersion("ENTITY_PLAYER_LEVELUP", "LEVEL_UP", "ENTITY_PLAYER_LEVELUP")
        )
        addDefSound(
            NextEvent.DIAMOND_GENERATOR_TIER_III.soundPath,
            getForCurrentVersion("ENTITY_PLAYER_LEVELUP", "LEVEL_UP", "ENTITY_PLAYER_LEVELUP")
        )
        addDefSound(
            NextEvent.EMERALD_GENERATOR_TIER_II.soundPath,
            getForCurrentVersion("ENTITY_GHAST_WARN", "GHAST_MOAN", "ENTITY_GHAST_WARN")
        )
        addDefSound(
            NextEvent.EMERALD_GENERATOR_TIER_III.soundPath,
            getForCurrentVersion("ENTITY_GHAST_WARN", "GHAST_MOAN", "ENTITY_GHAST_WARN")
        )
        addDefSound(
            NextEvent.ENDER_DRAGON.soundPath,
            getForCurrentVersion("ENTITY_ENDER_DRAGON_FLAP", "ENDERDRAGON_WINGS", "ENTITY_ENDERDRAGON_FLAP")
        )

        addDefSound(
            "player-re-spawn",
            getForCurrentVersion("BLOCK_SLIME_BLOCK_FALL", "SLIME_ATTACK", "BLOCK_SLIME_FALL")
        )
        addDefSound(
            "arena-selector-open",
            getForCurrentVersion("ENTITY_CHICKEN_EGG", "CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG")
        )
        addDefSound(
            "stats-gui-open",
            getForCurrentVersion("ENTITY_CHICKEN_EGG", "CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG")
        )
        addDefSound(
            "trap-sound",
            getForCurrentVersion("ENTITY_ENDERMAN_TELEPORT", "ENDERMAN_TELEPORT", "ENDERMAN_TELEPORT")
        )
        addDefSound(
            "shop-auto-equip",
            getForCurrentVersion("ITEM_ARMOR_EQUIP_GENERIC", "HORSE_ARMOR", "ITEM_ARMOR_EQUIP_GENERIC")
        )
        addDefSound(
            "egg-bridge-block",
            getForCurrentVersion("ENTITY_CHICKEN_EGG", "CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG")
        )
        addDefSound(
            "ender-pearl-landed",
            getForCurrentVersion("ENTITY_ENDERMAN_TELEPORT", "ENDERMAN_TELEPORT", "ENTITY_ENDERMEN_TELEPORT")
        )
        addDefSound(
            "pop-up-tower-build",
            getForCurrentVersion("ENTITY_CHICKEN_EGG", "CHICKEN_EGG_POP", "ENTITY_CHICKEN_EGG")
        )
        options().copyDefaults(true)

        // remove old paths
        set("bought", null)
        set("insufficient-money", null)
        set("player-kill", null)
        set("countdown", null)
        save()
    }

    private fun getSound(path: String): Sound {
        val name = getString("$path.sound")
        val default = getForCurrentVersion(
            "ITEM_TRIDENT_THUNDER",
            "AMBIENCE_THUNDER",
            "ENTITY_LIGHTNING_THUNDER"
        )
        return Sound.entries.find { it.name == name }
            ?: Sound.entries.find { it.name == default }
            ?: Sound.entries.first()
    }

    fun playSound(path: String, players: List<Player>) {
        val sound = getSound(path)
        val volume = getDouble("$path.volume").toFloat()
        val pitch = getDouble("$path.pitch").toFloat()

        players.forEach { it.playSound(it.location, sound, volume, pitch) }
    }

    fun playSound(path: String, vararg player: Player) = playSound(path, player.toList())

    /**
     * @return true if sound is valid and it was played.
     */
    fun playSound(sound: Sound?, players: List<Player>): Boolean {
        if (sound == null) return false
        players.forEach { it.playSound(it.location, sound, 1f, 1f) }
        return true
    }

    private fun addDefSound(path: String, value: String) {
        // convert old paths
        if (get("$path.volume") == null) relocate(path, "$path.sound")
        default(
            "$path.sound" to value,
            "$path.volume" to 1,
            "$path.pitch" to 1
        )
    }

    fun playSoundArea(path: String, location: Location, x: Float, y: Float) {
        location.world!!.playSound(location, getSound(path), x, y)
    }
}
