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
package com.andrei1058.bedwars.api.arena.team

import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.DyeColor
import org.bukkit.Material

enum class TeamColor(
    val chat: ChatColor,
    val dye: DyeColor,
    val bukkit: Color,
    val byte: Byte
) {
    RED(ChatColor.RED, DyeColor.RED, Color.RED, 14),
    BLUE(ChatColor.BLUE, DyeColor.BLUE, Color.BLUE, 11),
    GREEN(ChatColor.GREEN, DyeColor.LIME, Color.LIME, 5),
    YELLOW(ChatColor.YELLOW, DyeColor.YELLOW, Color.YELLOW, 4),
    AQUA(ChatColor.AQUA, DyeColor.LIGHT_BLUE, Color.AQUA, 9),
    WHITE(ChatColor.WHITE, DyeColor.WHITE, Color.WHITE, 0),
    PINK(ChatColor.LIGHT_PURPLE, DyeColor.PINK, Color.FUCHSIA, 6),
    GRAY(ChatColor.GRAY, DyeColor.GRAY, Color.GRAY, 8),
    DARK_GREEN(ChatColor.DARK_GREEN, DyeColor.GREEN, Color.GREEN, 13),
    DARK_GRAY(ChatColor.DARK_GRAY, DyeColor.GRAY, Color.fromBGR(74, 74, 74), 7);

    private val materialColor get() = when (this) {
        AQUA -> "LIGHT_BLUE"
        GREEN -> "LIME"
        DARK_GREEN -> "GREEN"
        GRAY -> "LIGHT_GRAY"
        DARK_GRAY -> "GRAY"
        else -> this.toString()
    }
    /**
     * Get bed with color. Used for 1.13+.
     * 
     * @return 1.13+ bed material. Return RED_BED if not found.
     */
    fun bedMaterial() = Material.valueOf("${materialColor}_BED")

    /**
     * Get glass with team color. Used for 1.13+ team glass.
     * 
     * @return 1.13+ glass material.
     */
    fun glassMaterial() = Material.valueOf("${materialColor}_STAINED_GLASS")

    /**
     * Retrieve glass pane with team color.
     * 
     * @return glass pane material for 1.13+.
     */
    fun glassPaneMaterial() = Material.valueOf("${materialColor}_STAINED_GLASS_PANE")

    /**
     * Get glazed terracotta with team color.
     * 
     * @return 1.13+ material.
     */
    fun glazedTerracottaMaterial() = Material.valueOf("${materialColor}_TERRACOTTA")

    /**
     * Get wool with team color.
     * 
     * @return 1.13+ material.
     */
    fun woolMaterial() = Material.valueOf("${materialColor}_WOOL")

    companion object {
        /**
         * Get chat color by team color.
         * 
         * @param color - [TeamColor] string.
         * @return [ChatColor] for given team.
         */
        @JvmStatic
        fun getChatColor(color: String) = valueOf(color.uppercase()).chat

        /**
         * Get the english for material as color name.
         * 
         * @param material material string.
         * @return the english color name for given material. EMPTY if item is not supported.
         */
        @JvmStatic
        fun enName(material: String) = when (material.uppercase()) {
            "PINK_WOOL" -> "Pink"
            "RED_WOOL" -> "Red"
            "LIGHT_GRAY_WOOL" -> "Gray"
            "BLUE_WOOL" -> "Blue"
            "WHITE_WOOL" -> "White"
            "LIGHT_BLUE_WOOL" -> "Aqua"
            "LIME_WOOL" -> "Green"
            "YELLOW_WOOL" -> "Yellow"
            "GRAY_WOOL" -> "Dark Gray"
            else -> ""
        }

        /**
         * Get the english for byte as color name.
         * 
         * @param b color byte. Used for 1.12 and older.
         * @return the english color name for given material. EMPTY if item is not supported.
         */
        @JvmStatic
        fun enName(b: Byte) = when (b.toInt()) {
            6 -> "Pink"
            14 -> "Red"
            9 -> "Aqua"
            5 -> "Green"
            4 -> "Yellow"
            11 -> "Blue"
            0 -> "White"
            8 -> "Dark Gray"
            7 -> "Gray"
            else -> ""
        }
    }
}
