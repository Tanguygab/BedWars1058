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
package com.andrei1058.bedwars.language

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import java.util.*

class Russian : Language(BedWars.INSTANCE, "ru", "Pусский") {
    init {
        val bw = BedWars.MAIN_COMMAND

        addDefault(
            Messages.COMMAND_MAIN,
            listOf(
                "",
                "&2▪ &7/$bw stats",
                "&2▪ &7/$bw join &o<арена/тип>",
                "&2▪ &7/$bw leave",
                "&2▪ &7/$bw lang",
                "&2▪ &7/$bw gui",
                "&2▪ &7/$bw start &3(vip)"
            )
        )
        addDefault(
            Messages.ARENA_JOIN_VIP_KICK,
            "{prefix}&cИзвините, но вас выгнали, потому что к арене присоединился донатер.\n&aЧтобы иметь больше возможностей - купите донат. &7&o(жми)"
        )
        addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL,
            "{prefix}&cЭта арена полная!\n&aЧтобы иметь больше возможностей - купите донат. &7&o(жми)"
        )
        addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS,
            "{prefix}&cПриносим извинения, но эта арена полная.\n&cМы знаем, что вы являетесь донатером, но на самом деле эта арена полна сотрудников и/или донатеров."
        )
        addDefault(
            Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT,
            "{prefix}§cНедостаточно игроков! Обратный отсчет остановился!"
        )
        addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&e{player} вышел из игры!")
        addDefault(Messages.ARENA_RESTART_PLAYER_KICK, "{prefix}&eАрена, в которой вы были, перезапускается.")
        addDefault(Messages.ARENA_STATUS_PLAYING_NAME, "&cИдет Игра")
        addDefault(Messages.ARENA_STATUS_RESTARTING_NAME, "&4Перезапуск")
        addDefault(Messages.ARENA_STATUS_WAITING_NAME, "&2Ожидание §c{full}")
        addDefault(Messages.ARENA_STATUS_STARTING_NAME, "&6Начало §c{full}")
        addDefault(
            Messages.COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND,
            "{prefix}&cНе существует какой-либо арены или арены: {name}"
        )
        addDefault(Messages.COMMAND_JOIN_NO_EMPTY_FOUND, "{prefix}&cСейчас нет какой-либо арены ;(")
        addDefault(Messages.COMMAND_LEAVE_DENIED_NOT_IN_ARENA, "{prefix}&cТы не на арене!")
        addDefault(Messages.ARENA_GUI_INV_NAME, "&8Доступные арены")
        addDefault(Messages.ARENA_GUI_ARENA_CONTENT_NAME, "&a&l{name}")
        addDefault(
            Messages.ARENA_GUI_ARENA_CONTENT_LORE,
            listOf(
                "",
                "&7Статус: {status}",
                "&7Игроков: &f{on}&7/&f{max}",
                "&7Тип: &a{group}",
                "",
                "&aЛКМ для входа.",
                "&eПКМ для слежки."
            )
        )
        addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_NAME, "&r{serverIp}")
        addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_LORE, emptyList<String>())
        addDefault(Messages.COMMAND_LANG_LIST_HEADER, "{prefix} &2Доступные языки:")
        addDefault(Messages.COMMAND_LANG_LIST_FORMAT, "&a▪  &7{iso} - &f{name}")
        addDefault(Messages.COMMAND_LANG_USAGE, "{prefix}&7Используйте: /lang &f&o<iso>")
        addDefault(Messages.COMMAND_LANG_SELECTED_NOT_EXIST, "{prefix}&cЭтот язык не существует!")
        addDefault(Messages.COMMAND_LANG_SELECTED_SUCCESSFULLY, "{prefix}&aЯзык сменен!")
        addDefault(Messages.COMMAND_LANG_USAGE_DENIED, "{prefix}&cВы не можете изменить язык во время игры.")
        addDefault(Messages.COMMAND_TP_PLAYER_NOT_FOUND, "{prefix}&cИгрок не найден!")
        addDefault(Messages.COMMAND_TP_NOT_IN_ARENA, "{prefix}&cЭтот игрок не на bedwars арене!")
        addDefault(
            Messages.COMMAND_TP_NOT_STARTED,
            "{prefix}&cИгра на арене, где находится игрок, еще не началась!"
        )
        addDefault(Messages.COMMAND_TP_USAGE, "{prefix}&cUsage: /bw tp <ник>")
        addDefault(
            Messages.COMMAND_JOIN_DENIED_PARTY_TOO_BIG,
            "{prefix}&cВаша партия слишком велика для того, чтобы присоединиться к этой арене как команде :("
        )
        addDefault(Messages.COMMAND_JOIN_DENIED_NOT_PARTY_LEADER, "{prefix}&cТолько лидер может выбрать арену.")
        addDefault(Messages.COMMAND_REJOIN_PLAYER_RECONNECTED, "{prefix}&7{player} &eпереподключился!")
        addDefault(Messages.GENERATOR_HOLOGRAM_TIER, "&eУровень &c{tier}")
        addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND, "&b&lАлмаз")
        addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD, "&a&lЭмеральд")
        addDefault(Messages.GENERATOR_HOLOGRAM_TIMER, "&eСпавн через &c{seconds} &eсекунд")
        addDefault(Messages.COMMAND_JOIN_PLAYER_JOIN_MSG, "{prefix}&7{player} &eвошел в игру (&b{on}&e/&b{max}&e)!")
        addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&7{player} &eвышел из игры!")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CHAT, "{prefix}&eИгра начнется через &6{time} &eсекунд!")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_TITLE, " ")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE, "&a{second}")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-5", "&e❺")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-4", "&e❹")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-3", "&c❸")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-2", "&c❷")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-1", "&c❶")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_TITLE, " ")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_SUB_TITLE, "&cОжидание игроков..")
        addDefault(Messages.FORMATTING_CHAT_LOBBY, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        addDefault(Messages.FORMATTING_CHAT_WAITING, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        addDefault(
            Messages.FORMATTING_CHAT_SHOUT,
            "{level}{vPrefix}&6[ВСЕМ] {team} &7{player}&f{vSuffix}: {message}"
        )
        addDefault(Messages.FORMATTING_CHAT_TEAM, "{level}{vPrefix}&f{team}&7 {player}{vSuffix} {message}")
        addDefault(
            Messages.FORMATTING_CHAT_SPECTATOR,
            "{level}{vPrefix}&7[НАБЛЮДАТЕЛЬ] {player}{vSuffix}: {message}"
        )
        addDefault(
            Messages.ARENA_STATUS_START_PLAYER_TUTORIAL, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lBedWars", "",
                "&e&l Защищайте свою кровать и уничтожайте кровати врагов.",
                "&e&l      Улучшайте себя и свою команду, собирая",
                "&e&l   Железо, Золото, Эмеральды и Алмазы из генераторов",
                "&e&l     чтобы получить доступ к мощным улучшениям.", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        addDefault(Messages.MEANING_SHOUT, "shout")
        addDefault(Messages.ARENA_STATUS_START_PLAYER_TITLE, "&aПОГНАЛИ")

        // Start of Sidebar
        addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fКарта: &a{map}",
                "",
                "&fИгроков: &a{on}/{max}",
                "",
                "&fОжидание,&fОжидание.,&fОжидание..,&fОжидание...",
                "",
                "&fТип: &a{group}",
                "&fВерсия: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Spectating",
                "&fКарта: &a{map}",
                "",
                "&fИгроков: &a{on}/{max}",
                "",
                "&fОжидание,&fОжидание.,&fОжидание..,&fОжидание...",
                "",
                "&fТип: &a{group}",
                "&fВерсия: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fКарта: &a{map}",
                "",
                "&fИгроков: &a{on}/{max}",
                "",
                "&fСтарт через &a{time}s",
                "",
                "&fТип: &a{group}",
                "&fВерсия: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Spectating",
                "&fКарта: &a{map}",
                "",
                "&fИгроков: &a{on}/{max}",
                "",
                "&fСтарт через &a{time}s",
                "",
                "&fТип: &a{group}",
                "&fВерсия: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_RESTARTING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "",
                "&6Winner: {winnerTeamColor}{winnerTeamName} &6⭐",
                "",
                "&7&lTop Kills:",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_RESTARTING_WIN1, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "",
                "&6Winner: {winnerTeamColor}{winnerTeamName} &6⭐",
                "",
                "&7&lTop Kills:",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_RESTARTING_WIN2, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "",
                "&6Winner: {winnerTeamColor}{winnerTeamName} &6⭐",
                "",
                "&7&lTop Kills:",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_RESTARTING_LOSER, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "",
                "&6Winner: {winnerTeamColor}{winnerTeamName} &6⭐",
                "",
                "&7&lTop Kills:",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "&f{topTeamColor}{topPlayerDisplayName}&7 - &l{topValue}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING.replaceFirst("Default".toRegex(), "Doubles"), listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "Doubles"),
            listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED.replaceFirst("Default".toRegex(), "Doubles"),
            listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING.replaceFirst("Default".toRegex(), "3v3v3v3"), listOf(
                "&e&lBED WARS",
                "&7{date}",
                "",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&fУбийств: &a{kills}",
                "&fФинальных убийств: &a{finalKills}",
                "&fКроватей уничтожено: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "3v3v3v3"),
            listOf(
                "&e&lBED WARS",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED.replaceFirst("Default".toRegex(), "3v3v3v3"),
            listOf(
                "&e&lBED WARS",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&fУбийств: &a{kills}",
                "&fФинальных убийств: &a{finalKills}",
                "&fКроватей уничтожено: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING.replaceFirst("Default".toRegex(), "4v4v4v4"), listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&fУбийств: &a{kills}",
                "&fФинальных убийств: &a{finalKills}",
                "&fКроватей уничтожено: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "4v4v4v4"),
            listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED.replaceFirst("Default".toRegex(), "4v4v4v4"),
            listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&fУбийств: &a{kills}",
                "&fФинальных убийств: &a{finalKills}",
                "&fКроватей уничтожено: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        // End of Sidebar

        // start of TAB
        // main lobby tab format
        addDefault(
            Messages.FORMATTING_SB_TAB_LOBBY_HEADER, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_LOBBY_FOOTER, listOf(
                "",
                "&fThere are {on} players on this lobby",
                "Powered by {poweredBy},&a{serverIp}",
                ""
            )
        )
        addDefault(Messages.FORMATTING_SB_TAB_LOBBY_PREFIX, listOf("{vPrefix}"))
        addDefault(Messages.FORMATTING_SB_TAB_LOBBY_SUFFIX, listOf(" {level}"))
        // player waiting lobby
        addDefault(
            Messages.FORMATTING_SB_TAB_WAITING_HEADER, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_WAITING_FOOTER, listOf(
                "",
                "Waiting for more players,Waiting for more players.,Waiting for more players.., Waiting for more players...",
                "&f{on}&a/&f{max}",
                "",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(Messages.FORMATTING_SB_TAB_WAITING_PREFIX, listOf("{vPrefix}"))
        addDefault(Messages.FORMATTING_SB_TAB_WAITING_SUFFIX, listOf(" {level}"))
        // spectator waiting lobby
        addDefault(
            Messages.FORMATTING_SB_TAB_WAITING_HEADER_SPEC, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_WAITING_FOOTER_SPEC, listOf(
                "",
                "&7&oYou are spectating",
                "Waiting for more players,Waiting for more players.,Waiting for more players.., Waiting for more players...",
                "&f{on}&a/&f{max}",
                "",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(Messages.FORMATTING_SB_TAB_WAITING_PREFIX_SPEC, listOf("{vPrefix}"))
        addDefault(Messages.FORMATTING_SB_TAB_WAITING_SUFFIX_SPEC, listOf(" {level}"))
        // player starting lobby
        addDefault(
            Messages.FORMATTING_SB_TAB_STARTING_HEADER, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_STARTING_FOOTER, listOf(
                "",
                "&fStarting in &a{time} &fseconds,&fStarting in &a{time} &fseconds.,&fStarting in &a{time} &fseconds..,&fStarting in &a{time} &fseconds..",
                "&f{on}&a/&f{max}",
                "",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(Messages.FORMATTING_SB_TAB_STARTING_PREFIX, listOf("{vPrefix} "))
        addDefault(Messages.FORMATTING_SB_TAB_STARTING_SUFFIX, listOf(" {level}"))
        // spectator starting lobby
        addDefault(
            Messages.FORMATTING_SB_TAB_STARTING_HEADER_SPEC, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_STARTING_FOOTER_SPEC, listOf(
                "",
                "&fStarting in &a{time} &fseconds,&fStarting in &a{time} &fseconds.,&fStarting in &a{time} &fseconds..,&fStarting in &a{time} &fseconds..",
                "&f{on}&a/&f{max}",
                "",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(Messages.FORMATTING_SB_TAB_STARTING_PREFIX_SPEC, listOf("{vPrefix} "))
        addDefault(Messages.FORMATTING_SB_TAB_STARTING_SUFFIX_SPEC, listOf(" {level}"))
        // player playing
        addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_HEADER, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                "",
                "{nextEvent} in {time}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_FOOTER, listOf(
                "",
                "&fYou are playing on the {teamColor}{teamName} Team",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(Messages.FORMATTING_SB_TAB_PLAYING_PREFIX, listOf("{teamColor}{teamName} "))
        addDefault(Messages.FORMATTING_SB_TAB_PLAYING_SUFFIX, listOf(" {vPrefix}", " {level}"))
        // player eliminated - playing state
        addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_ELM_HEADER, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                "",
                "{nextEvent} in {time}",
                "",
                "&7&oAYou've been eliminated,&f&oAYou've been eliminated"
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_ELM_FOOTER, listOf(
                "",
                "&fYou have played in the {teamColor}{teamName} Team",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(Messages.FORMATTING_SB_TAB_PLAYING_ELM_PREFIX, listOf("&f&oSpectator "))
        addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_ELM_SUFFIX,
            listOf(
                " &c&oEliminated {teamColor}&o{teamName}",
                " {teamColor}&oEliminated {vPrefix}",
                "{teamColor}&oEliminated {level}"
            )
        )
        // spectator - playing state
        addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_SPEC_HEADER, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                "",
                "{nextEvent} in {time}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_SPEC_FOOTER, listOf(
                "",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(Messages.FORMATTING_SB_TAB_PLAYING_SPEC_PREFIX, listOf("&f&oSpectator "))
        addDefault(Messages.FORMATTING_SB_TAB_PLAYING_SPEC_SUFFIX, listOf(" {vPrefix}", " {level}"))
        // winner alive - restarting state
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_HEADER, listOf(
                "                                                                                                        ",
                "&6⭐ {winnerTeamColor}&lYour team won the game! &6⭐",
                "&7{date}", "&7Map: &f{map} &7Mode: &f{group}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_FOOTER, listOf(
                "",
                "&6&lYou won in the {teamColor}&l{teamName} Team&6&l!,&6&lYou won in the {teamColor}&l{teamName} Team&6&l!,&f&lYou won in the {teamColor}&l{teamName} Team&f&l!",
                "&7Statistics from this match",
                "&eKills: &f{kills} &8| &eFinal Kills: &f{finalKills} &8| &eBeds Destroyed: &f{beds} &8| &eDeaths: &f{deaths},&eKills: &7{kills} &8| &eFinal Kills: &7{finalKills} &8| &eBeds Destroyed: &7{beds} &8| &eDeaths: &7{deaths}",
                "",
                "&fThanks for playing {player}!",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_PREFIX,
            listOf("&6&l⭐ {teamColor}{teamName} ")
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_SUFFIX,
            listOf(" {vPrefix}", " {level}")
        )
        // winner dead - restarting state
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_HEADER, listOf(
                "                                                                                                        ",
                "&6⭐ {winnerTeamColor}&l{winnerTeamName} Team won the game! &6⭐",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_FOOTER, listOf(
                "",
                "&6&lYou won in the {teamColor}&l{teamName} Team&6&l!,&6&lYou won in the {teamColor}&l{teamName} Team&6&l!,&f&lYou won in the {teamColor}&l{teamName} Team&f&l!",
                "&7Statistics from this match",
                "&eKills: &f{kills} &8| &eFinal Kills: &f{finalKills} &8| &eBeds Destroyed: &f{beds} &8| &eDeaths: &f{deaths},&eKills: &7{kills} &8| &eFinal Kills: &7{finalKills} &8| &eBeds Destroyed: &7{beds} &8| &eDeaths: &7{deaths}",

                "&fThanks for playing {player}!",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_PREFIX,
            listOf("&6&l⭐ {teamColor}{teamName} ")
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_SUFFIX,
            listOf(" {vPrefix}", " &c&oEliminated", " {level}", " &c&oEliminated")
        )
        // loser - restarting state
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_ELM_HEADER, listOf(
                "                                                                                                        ",
                "&6⭐ {winnerTeamColor}&l{winnerTeamName} Team won the game! &6⭐",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_ELM_FOOTER, listOf(
                "",
                "&fYou have lost in the {teamColor}{teamName} Team",
                "&7Statistics from this match",
                "&eKills: &f{kills} &8| &eFinal Kills: &f{finalKills} &8| &eBeds Destroyed: &f{beds} &8| &eDeaths: &f{deaths},&eKills: &7{kills} &8| &eFinal Kills: &7{finalKills} &8| &eBeds Destroyed: &7{beds} &8| &eDeaths: &7{deaths}",
                "&fThanks for playing {player}!",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_ELM_PREFIX,
            listOf("{teamColor}{teamName} ")
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_ELM_SUFFIX,
            listOf(" {vPrefix}", " &c&oEliminated", " {level}", " &c&oEliminated")
        )
        // spectator - restarting state
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_HEADER, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                "&6⭐ {winnerTeamColor}&l{winnerTeamName} Team won the game! &6⭐",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                "",
                "&fThanks for playing {player}!",
                ""
            )
        )
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_FOOTER, listOf(
                "",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        addDefault(Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_PREFIX, listOf("&f&oSpectator "))
        addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_SUFFIX,
            listOf(" {vPrefix}", " {level}")
        )

        // end of tab
        addDefault(Messages.FORMATTING_SCOREBOARD_HEALTH, listOf("&c❤", "&aЗдоровье"))
        addDefault(Messages.FORMATTING_SCOREBOARD_DATE, "dd/MM/yy")
        addDefault(
            Messages.FORMATTING_SCOREBOARD_TEAM_GENERIC,
            "{TeamColor}{TeamLetter}&f {TeamName}: {TeamStatus}"
        )
        addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ELIMINATED, "&c&l✘")
        addDefault(Messages.FORMATTING_SCOREBOARD_BED_DESTROYED, "&a{remainingPlayers}")
        addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ALIVE, "&a&l✓")
        addDefault(Messages.FORMATTING_SCOREBOARD_NEXEVENT_TIMER, "mm:ss")
        addDefault(Messages.FORMATTING_SCOREBOARD_YOUR_TEAM, "&7 Вы")
        addDefault(Messages.FORMATTING_ACTION_BAR_TRACKING, "&fОтслеживание: {team} &f- Дистанция: {distance}m")
        addDefault(
            Messages.TEAM_ELIMINATED_CHAT,
            "\n&f&lКОМАНДНАЯ ЛИКВИДАЦИЯ > {TeamColor}{TeamName} команда &cбыла уничтожена!\n"
        )
        addDefault(Messages.BED_HOLOGRAM_DEFEND, "&c&lЗащищайте свою кровать!")
        addDefault(
            Messages.INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED,
            "&cТы не можешь открыть этот сундук, так как команда уничтожена!"
        )
        addDefault(Messages.INTERACT_CANNOT_PLACE_BLOCK, "{prefix}&cВы не можете ставить блоки здесь!")
        addDefault(Messages.INTERACT_CANNOT_BREAK_BLOCK, "{prefix}&cВы можете ломать блоки только игроков!")
        addDefault(Messages.INTERACT_CANNOT_BREAK_OWN_BED, "&cВы не можете разрушить свою кровать!")
        addDefault(
            Messages.TEAM_ELIMINATED_CHAT,
            "\n&f&lУНИЧТОЖЕНИЕ КОММАНДЫ > {TeamColor}{TeamName} команда &cбыла уничтожена!\n"
        )
        addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT,
            "\n&f&lУНИЧТОЖЕНИЕ КРОВАТИ > {TeamColor}{TeamName} Кровать &7разушена игроком {PlayerColor}{PlayerName}&7!\n"
        )
        addDefault(Messages.INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT, "&cВАША КРОВАТЬ УНИЧТОЖЕНА!")
        addDefault(Messages.INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT, "&fВы больше не сможете возродиться!")
        addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM,
            "&f&lУНИЧТОЖЕНИЕ КРОВАТИ > &7Ваша кровать разрушена игроком {PlayerColor}{PlayerName}&7!"
        )
        addDefault(
            Messages.INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN,
            "&cYou are no longer invisible because you have taken damage!"
        )
        addDefault(Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL, "{PlayerColor}{PlayerName} &7упал.")
        addDefault(
            Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7упал. &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7был скинут в бездну {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7был скинут в бездну игроком {KillerColor}{KillerName}&7. &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7был сбит игроком {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7был сбит игроком {KillerColor}{KillerName}&7. &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7был поражен любимой бомбой игрока {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7был поражен любимой бомбой игрока {KillerColor}{KillerName}&7. &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7был убит игроком {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7был убит игроком {KillerColor}{KillerName}&7. &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_REGULAR,
            "{PlayerColor}{PlayerName} &7откючился пока сражался с {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_FINAL,
            "{PlayerColor}{PlayerName} &7откючился пока сражался с {KillerColor}{KillerName}&7. &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(Messages.BED_HOLOGRAM_DESTROYED, "&c&lВаша кровать сломана!")
        addDefault(Messages.PLAYER_DIE_RESPAWN_TITLE, "&cВЫ ПОГИБЛИ!")
        addDefault(Messages.PLAYER_DIE_RESPAWN_SUBTITLE, "&eВы возродитесь через &c{time} &eсекунд!")
        addDefault(Messages.PLAYER_DIE_RESPAWN_CHAT, "{prefix}&eВы возродитесь через &c{time} &eсекунд!")
        addDefault(Messages.PLAYER_DIE_RESPAWNED_TITLE, "&aВОЗРОЖДЕН!")
        addDefault(Messages.PLAYER_DIE_ELIMINATED_CHAT, "{prefix}&cВы были устранены!")
        addDefault(Messages.PLAYER_HIT_BOW, "&7У {prefix}{TeamColor}{PlayerName} &7теперь &c{amount} &7HP!")
        addDefault(Messages.GAME_END_GAME_OVER_PLAYER_TITLE, "&c&lИГРА ОКОНЧЕНА!")
        addDefault(Messages.GAME_END_VICTORY_PLAYER_TITLE, "&6&lПОБЕДА!")
        addDefault(
            Messages.GAME_END_TOP_PLAYER_CHAT, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lBedWars", "", "{winnerFormat}", "", "",
                "&6                      &6⭐ &l1-1 Убийца &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&e                        &l2-й Убийца &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&c                        &l3-й Убийца &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        //addDefault(gameOverReward, Arrays.asList("&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
        //        "&f                                   &lReward Summary", "", "",
        //        "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"));
        addDefault(Messages.GAME_END_TEAM_WON_CHAT, "{prefix}{TeamColor}{TeamName} &aвыиграл игру!")
        addDefault(Messages.FORMATTING_TEAM_WINNER_FORMAT, "      {TeamColor}{TeamName} &7- {members}")
        addDefault(Messages.FORMATTING_SOLO_WINNER_FORMAT, "                 {TeamColor}{TeamName} &7- {members}")
        //addDefault(tablistFormat, "{TeamColor}&l{TeamLetter}&r {TeamColor}{PlayerName} &e{PlayerHealth}");//{TeamColor}{TeamName}{TeamHealth}{PlayerName}{PlayerHealth}
        addDefault(Messages.MEANING_NOBODY, "Никто")
        addDefault(Messages.FORMATTING_GENERATOR_TIER1, "I")
        addDefault(Messages.FORMATTING_GENERATOR_TIER2, "II")
        addDefault(Messages.FORMATTING_GENERATOR_TIER3, "III")
        addDefault(
            Messages.GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT,
            "{prefix}{generatorType} Генератор &eбыл улучшен до уровня &c{tier}"
        )
        addDefault(Messages.NPC_NAME_TEAM_UPGRADES, "&bКОМАНДНЫЕ УЛУЧШЕНИЯ, &e&lПКМ")
        addDefault(Messages.NPC_NAME_SOLO_UPGRADES, "&bСОЛО УЛУЧШЕНИЯ, &e&lПКМ")
        addDefault(Messages.NPC_NAME_TEAM_SHOP, "&bМАГАЗИН, &e&lПКМ")
        addDefault(Messages.NPC_NAME_SOLO_SHOP, "&bМАГАЗИН, &e&lПКМ")
        addDefault(Messages.XP_REWARD_PER_MINUTE, "{prefix}&6+{xp} BedWars Опыта получено (Игровое время).")
        addDefault(Messages.XP_REWARD_WIN, "{prefix}&6+{xp} BedWars Опыта получено (Победа в игре).")
        addDefault(Messages.XP_REWARD_PER_TEAMMATE, "{prefix}&6+{xp} BedWars Опыта получено (Поддержка команды).")
        addDefault(Messages.XP_REWARD_BED_DESTROY, "{prefix}&6+{xp} BedWars Опыта получено (Разрушение кровати).")
        addDefault(Messages.XP_REWARD_REGULAR_KILL, "{prefix}&6+{xp} BedWars Опыта получено (Убийство).")
        addDefault(Messages.XP_REWARD_FINAL_KILL, "{prefix}&6+{xp} BedWars Опыта получено (Финальное убийство).")

        addDefault(Messages.MONEY_REWARD_PER_MINUTE, "{prefix}&6+{money} Монет (Игровое время).")
        addDefault(Messages.MONEY_REWARD_WIN, "{prefix}&6+{money} Монет (Победа в игре).")
        addDefault(Messages.MONEY_REWARD_PER_TEAMMATE, "{prefix}&6+{money} Монет (Поддержка команды).")
        addDefault(Messages.MONEY_REWARD_BED_DESTROYED, "{prefix}&6+{money} Монет (Разрушение кровати).")
        addDefault(Messages.MONEY_REWARD_FINAL_KILL, "{prefix}&6+{money} Монет (Финальное убийство).")
        addDefault(Messages.MONEY_REWARD_REGULAR_KILL, "{prefix}&6+{money} Монет (Убийство).")

        //SHOP
        addDefault(Messages.SHOP_INDEX_NAME, "&8Быстрая покупка")
        addDefault(Messages.SHOP_INSUFFICIENT_MONEY, "{prefix}&cТебе нехватает {currency}! Нужно еще {amount}!")
        addDefault(Messages.SHOP_NEW_PURCHASE, "{prefix}&aТы купил &6{item}")
        addDefault(Messages.SHOP_ALREADY_BOUGHT, "{prefix}&cТы уже купил это!")
        addDefault(Messages.SHOP_QUICK_ADD_NAME, "&8Добавление в быструю покупку...")
        addDefault(Messages.SHOP_UTILITY_NPC_SILVERFISH_NAME, "{TeamColor}&l{TeamName} &r{TeamColor}Silverfish")
        addDefault(Messages.SHOP_UTILITY_NPC_IRON_GOLEM_NAME, "{TeamColor}{despawn}s &8[ {TeamColor}{health}&8]")
        addDefault(Messages.SHOP_SEPARATOR_NAME, "&8⇧ Категории")
        addDefault(Messages.SHOP_SEPARATOR_LORE, listOf("&8⇩ Предметы"))
        addDefault(Messages.SHOP_QUICK_BUY_NAME, "&bБыстрая покупка")
        addDefault(Messages.SHOP_QUICK_BUY_LORE, ArrayList<Any?>())
        addDefault(Messages.SHOP_QUICK_EMPTY_NAME, "&cПустой слот!")
        addDefault(
            Messages.SHOP_QUICK_EMPTY_LORE,
            listOf(
                "&7Это слот быстрой покупки!",
                "&bШифт-клик &7на любой предмет",
                "&7для добавления его сюда."
            )
        )
        addDefault(Messages.SHOP_CAN_BUY_COLOR, "&a")
        addDefault(Messages.SHOP_CANT_BUY_COLOR, "&c")
        addDefault(Messages.SHOP_LORE_STATUS_CAN_BUY, "&eНажми для покупки!")
        addDefault(Messages.SHOP_LORE_STATUS_CANT_AFFORD, "&cТебе нехватает {currency}!")
        addDefault(Messages.SHOP_LORE_STATUS_MAXED, "&aМАКСИМАЛЬНО!")
        addDefault(Messages.SHOP_LORE_QUICK_ADD, "&bШифт-клик для добавления в быструю покупку")
        addDefault(Messages.SHOP_LORE_QUICK_REMOVE, "&bШифт-клик для удаления из быстрой покупки!")


        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "&8Блоки",
            "&aБлоки",
            listOf("&eНажмите для просмотра!")
        )

        addContentMessages(
            this, "wool", ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "{color}Шерсть", listOf(
                "&7Цена: &f{cost} {currency}", "", "&7Great for bridging across", "&7islands. Turns into your team's",
                "&7color.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "clay",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Глина",
            listOf(
                "&7Цена: {cost} {currency}",
                "",
                "&7Стандартный блок для защиты кровати.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "glass",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Взрывоустойчивое стекло",
            listOf(
                "&7Cost: {cost} {currency}",
                "",
                "&7Имеет имунитет к взрывам.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "stone",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Эндерняк",
            listOf(
                "&7Цена: {cost} {currency}",
                "",
                "&7Солидный блок для защиты кровати.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "ladder",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Лестницы",
            listOf(
                "&7Цена: {cost} {currency}",
                "",
                "&7Полезно когда кошки застревают",
                "&7на деревьях.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "obsidian",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Обсидиан",
            listOf(
                "&7Цена: {cost} {currency}",
                "",
                "&7Лучший блок для защиты кровати.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "wood",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Дерево",
            listOf(
                "&7Цена: {cost} {currency}",
                "",
                "&7Хороший блок для защиты кровати.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "&8Оружие",
            "&aОружие",
            listOf("&eНажмите для просмотра!")
        )

        addContentMessages(
            this,
            "stone-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Каменный меч",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "iron-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Железный меч",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "diamond-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Алмазный меч",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "stick",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Палка (Отдача I)",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "&8Броня",
            "&aБроня",
            listOf("&eНажмите для просмотра!")
        )

        addContentMessages(
            this, "chainmail", ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "{color}Кольчужная броня", listOf(
                "&7Цена: {cost} {currency}",
                "",
                "&7Кольчужные штаны и сапоги",
                "&7Вы всегда будете появляться",
                "&7с ними при смерти.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "iron-armor", ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "{color}Железная броня", listOf(
                "&7Цена: {cost} {currency}",
                "",
                "&Железные штаны и сапоги",
                "&7Вы всегда будете появляться",
                "&7с ними при смерти.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "diamond-armor",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Permanent Diamond Armor",
            listOf(
                "&7Цена: {cost} {currency}",
                "",
                "&7Алмазные штаны и сапоги",
                "&7Вы всегда будете появляться",
                "&7с ними при смерти.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_TOOLS,
            "&8Инструменты",
            "&aИнструменты",
            listOf("&eНажмите для просмотра!")
        )

        addContentMessages(
            this, "shears", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Ножницы", listOf(
                "&7Цена: {cost} {currency}",
                "",
                "&7Отличный инструмент для ломания шерсти.",
                "&7Вы всегда будете появляться с ножницами.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "pickaxe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Кирка {tier}", listOf(
                "&7Цена: {cost} {currency}",
                "&7Фаза: &e{tier}",
                "",
                "&7Это улучшаемый предмет.",
                "&7Если вы умрёте, то он.",
                "&7потеряет одну фазу!",
                "",
                "&7Если вы ухудшите свой",
                "&7предметы до последней фазы",
                "&7то вы всегда будете появляться с самой нижней фазой.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "axe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Топор {tier}", listOf(
                "&7Цена: {cost} {currency}",
                "&7Фаза: &e{tier}",
                "",
                "&7Это улучшаемый предмет.",
                "&7Если вы умрёте, то он.",
                "&7потеряет одну фазу!",
                "",
                "&7Если вы ухудшите свой",
                "&7предметы до последней фазы",
                "&7то вы всегда будете появляться с самой нижней фазой.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "&8Луки",
            "&aЛуки",
            listOf("&eНажмите для просмотра!")
        )

        addContentMessages(
            this,
            "arrow",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Стрела",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow1",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Лук",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow2",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Лук (Сила I)",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow3",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Лук (Сила  I, Отдача I)",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "&8Зелья",
            "&aЗелья",
            listOf("&eНажмите для просмотра!")
        )

        addContentMessages(
            this,
            "speed-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Зелье на Скорость II (45 секунд)",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "jump-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Зелье на Прыжок V (45 секунд)",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "invisibility",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Зелье невидимости (30 секунд)",
            listOf("&7Цена: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "&8Разное",
            "&aРазное",
            listOf("&eНажмите для просмотра!")
        )

        addContentMessages(
            this,
            "golden-apple",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Золотое Яблоко",
            listOf(
                "&7Цена: {cost} {currency}",
                "",
                "&7Хорошо восстанавливает здоровье.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "bedbug", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Чешуйница", listOf(
                "&7Цена: {cost} {currency}", "", "&7Спавнит чешуйниц при",
                "&7бросания снежка.", "&7Может скинуть врагов, действует 15 секунд.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "dream-defender",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Железный Голем",
            listOf(
                "&7Цена: {cost} {currency}", "", "&7Железный голем для защиты вашей",
                "&7базы. Действует 4 минуты.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "fireball", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Фаербол", listOf(
                "&7Цена: {cost} {currency}", "", "&7Нажми ПКМ чтобы запустить!",
                "&7Огненный заряд хорошенько поджарит", "&7мосты ваших противников!", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "tnt", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Динамит", listOf(
                "&7Цена: {cost} {currency}", "", "&7Автоматически поджигаеться, при",
                "&7размещении!", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "ender-pearl", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Эндер-пёрл", listOf(
                "&7Цена: {cost} {currency}", "", "&7Самый быстрый способ добраться",
                "&7до базы вашего врага.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "water-bucket", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Ведро Воды", listOf(
                "&7Цена: {cost} {currency}", "", "&7Отличный метод замедлить ваших",
                "&7противников. Также может", "&7защитить от взрыва динамита.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "bridge-egg", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Яйцо", listOf(
                "&7Цена: {cost} {currency}", "", "&7Создаёт мост, по заданной",
                "&7траектории после броска.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "magic-milk",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Магическое Молоко",
            listOf(
                "&7Цена: {cost} {currency}", "", "&7Снимает эффекты всех ловушек,",
                "&7поставленных на базу противника, в течении 60 секунд.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "sponge", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Губка", listOf(
                "&7Cost: {cost} {currency}", "", "&7Вжимает в себя воду.",
                "", "{quick_buy}", "{buy_status}"
            )
        )


        addDefault(Messages.MEANING_FULL, "Полный")
        addDefault(Messages.MEANING_IRON_SINGULAR, "Железо")
        addDefault(Messages.MEANING_IRON_PLURAL, "Железа")
        addDefault(Messages.MEANING_GOLD_SINGULAR, "Золото")
        addDefault(Messages.MEANING_GOLD_PLURAL, "Золота")
        addDefault(Messages.MEANING_EMERALD_SINGULAR, "Изумруд")
        addDefault(Messages.MEANING_EMERALD_PLURAL, "Изумрудов")
        addDefault(Messages.MEANING_DIAMOND_SINGULAR, "Алмаз")
        addDefault(Messages.MEANING_DIAMOND_PLURAL, "Алмазов")
        addDefault(Messages.MEANING_VAULT_SINGULAR, "$")
        addDefault(Messages.MEANING_VAULT_PLURAL, "$")

        addDefault(Messages.COMMAND_JOIN_USAGE, "§a▪ §7Используйте: /$bw join §o<арена/тип>")
        addDefault(Messages.COMMAND_NOT_ALLOWED_IN_GAME, "{prefix}&cВы не можете этого сделать.")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "&aНажмите, чтобы купить!")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY, "&cУ вас недостаточно {currency}!")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_LOCKED, "&cЗАБЛОКИРОВАНО")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED, "&aРАЗБЛОКИРОВАНО")
        addDefault("upgrades.Default.generators.tier1.name", "&eЖелезная кузница")
        addDefault(
            "upgrades.Default.generators.tier1.lore",
            listOf(
                "&7Увеличивает скорость спавна",
                "&7Железа и золота на 50%..",
                "",
                "&7Цена:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.generators.tier2.name", "&eЗолотая кузница")
        addDefault(
            "upgrades.Default.generators.tier2.lore",
            listOf(
                "&7Увеличивает скорость спавна",
                "&7железа и золота на 100%..",
                "",
                "&7Цена:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.generators.tier3.name", "&eИзумрудная кузница")
        addDefault(
            "upgrades.Default.generators.tier3.lore",
            listOf(
                "&7Активирует заклинателя «Изумруд»",
                "&7В кузнице вашей команды.",
                "",
                "&7Цена:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.maniacMiner.tier1.name", "&eМаньяк-майнер")
        addDefault(
            "upgrades.Default.maniacMiner.tier1.lore",
            listOf(
                "&7Все игроки вашей команды",
                "&7Навсегда получат ускорение I",
                "",
                "&7Цена:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.sharpSword.tier1.name", "&eЗаостренные мечи")
        addDefault(
            "upgrades.Default.sharpSword.tier1.lore",
            listOf(
                "&7Ваша команда получит Резкость I",
                "&7На всех мечах!",
                "",
                "&7Цена:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.reinforced.tier1.name", "&eУкрепленная броня")
        addDefault(
            "upgrades.Default.reinforced.tier1.lore",
            listOf(
                "&7Ваша команда поолучит Защиту I",
                "&7На всей броне!",
                "",
                "&7Цена:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.trap.tier1.name", "&eЭто ловушка!")
        addDefault(
            "upgrades.Default.trap.tier1.lore",
            listOf(
                "&7Враг, пытающийся зайти на вашу базу",
                "&7Получит слепоту",
                "&7и медлительность!",
                "",
                "&7Цена:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.miningFatigue.tier1.name", "&eШахтерская усталостная ловушка")
        addDefault(
            "upgrades.Default.miningFatigue.tier1.lore",
            listOf(
                "&7Враг, пытающийся зайти на вашу базу",
                "&7В течении 10 секунд получит",
                "&7усталость!",
                "",
                "&7Цена:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.healPool.tier1.name", "&eИсцеляющее поле")
        addDefault(
            "upgrades.Default.healPool.tier1.lore",
            listOf(
                "&7Создает поле регенирации",
                "&7вокруг вашей базы!",
                "",
                "&7Цена:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT, "&a{player} купил улучшение &6{upgradeName}")
        addDefault(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH, "▮ ")
        addDefault(Messages.PLAYER_DIE_UNKNOWN_REASON_REGULAR, "{PlayerColor}{PlayerName} &7умер.")
        addDefault(
            Messages.PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7умер. &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(
            Messages.PLAYER_DIE_SHOOT_REGULAR,
            "{PlayerColor}{PlayerName} &7был убит выстрелом игрока {KillerColor}{KillerName}&7!"
        )
        addDefault(
            Messages.PLAYER_DIE_SHOOT_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7был убит выстрелом игрока {KillerColor}{KillerName}&7! &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(
            Messages.PLAYER_DIE_DEBUG_REGULAR,
            "{PlayerColor}{PlayerName} &7был убит чешуйницей игрока {KillerColor}{KillerTeamName}!"
        )
        addDefault(
            Messages.PLAYER_DIE_DEBUG_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7был убит Чешуйницей игрока {KillerColor}{KillerTeamName}! &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_REGULAR,
            "{PlayerColor}{PlayerName} &7был убит Железным Големом игрока {KillerColor}{KillerTeamName}!"
        )
        addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7был убит Железным Големом игрока {KillerColor}{KillerTeamName}! &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(Messages.PLAYER_DIE_REWARD_DIAMOND, "{prefix}&b+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_EMERALD, "{prefix}&a+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_IRON, "{prefix}&f+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_GOLD, "{prefix}&6+{amount} {meaning}")

        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR,
            "{PlayerColor}{PlayerName} &7был взорван бомбой."
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7был взорван бомбой. &b&lФИНАЛЬНОЕ УБИЙСТВО!"
        )
        addDefault(Messages.PLAYER_STATS_GUI_INV_NAME, "&8Статистика игрока {player}")

        /* save default items messages for stats gui */
        addDefaultStatsMsg("wins", "&6Побед", "&f{wins}")
        addDefaultStatsMsg("losses", "&6Проигрышей", "&f{losses}")
        addDefaultStatsMsg("kills", "&6Убийств", "&f{kills}")
        addDefaultStatsMsg("deaths", "&6Смертей", "&f{deaths}")
        addDefaultStatsMsg("final-kills", "&6Финальных убийств", "&f{finalKills}")
        addDefaultStatsMsg("final-deaths", "&6Финальных смертей", "&f{finalDeaths}")
        addDefaultStatsMsg("beds-destroyed", "&6Кроватей уничтожено", "&f{bedsDestroyed}")
        addDefaultStatsMsg("first-play", "&6Первая игра", "&f{firstPlay}")
        addDefaultStatsMsg("last-play", "&6Последняя игра", "&f{lastPlay}")
        addDefaultStatsMsg("games-played", "&6Игр сыграно", "&f{gamesPlayed}")
        addDefault(Messages.FORMATTING_STATS_DATE_FORMAT, "yyyy/MM/dd HH:mm")

        addDefault(Messages.MEANING_NEVER, "Никогда")
        addDefault(
            Messages.SCOREBOARD_LOBBY, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&fВаш уровень: {level}",
                "",
                "&fПрогресс: &a{currentXp}&7/&b{requiredXp}",
                "{progress}",
                "",
                "&7{player}",
                "",
                "&fМонет: &a{money}",
                "",
                "&fВсего побед: &a{wins}",
                "&fВсего убйиств: &a{kills}",
                "",
                "&e{serverIp}"
            )
        )

        /* party commands */
        addDefault(
            Messages.COMMAND_PARTY_HELP, listOf(
                "&6▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&aКоманды пати:",
                "&e/party help &7- &bПоказывает все доступные команды",
                "&e/party invite <игрок> &7- &bПригласить игрока в ваше пати",
                "&e/party leave &7- &bВыйти из пати",
                "&e/party info &7- &bShow party members and owner",
                "&e/party promote <player> &7- &bTransfer party ownership",
                "&e/party remove <игрок> &7- &bВыгнать игрока из пати",
                "&e/party accept <игрок> &7- &bПринять приглашение в пати",
                "&e/party disband &7- &bРасфирмировать текующую группу"
            )
        )
        addDefault(Messages.COMMAND_PARTY_INVITE_USAGE, "{prefix}&eИспользуйте: &7/party invite <игрок>")
        addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eне онлайн!")
        addDefault(Messages.COMMAND_PARTY_INVITE_SENT, "{prefix}&eПриглашение отправлено &7{player}&6.")
        addDefault(
            Messages.COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG,
            "{prefix}&b{player} &eпригласил вас в пати! &o&7(Нажмите, чтобы принять)"
        )
        addDefault(
            Messages.COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF,
            "{prefix}&cВы не можете пригласить самого себя!"
        )
        addDefault(Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE, "{prefix}&cНет приглашений в пати.")
        addDefault(Messages.COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY, "{prefix}&eВы уже в пати!")
        addDefault(
            Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS,
            "{prefix}&cТолько владелец партии может это сделать!"
        )
        addDefault(Messages.COMMAND_PARTY_ACCEPT_USAGE, "{prefix}&eИспользуйте: &7/party accept <игрок>")
        addDefault(Messages.COMMAND_PARTY_ACCEPT_SUCCESS, "{prefix}&7{player} &eприсоединился к группе!")
        addDefault(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY, "{prefix}&cВы не в группе!")
        addDefault(
            Messages.COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND,
            "{prefix}&cВы не можете покинуть свою собственное пати!\n&eПопробуйте: &b/party disband"
        )
        addDefault(Messages.COMMAND_PARTY_LEAVE_SUCCESS, "{prefix}&7{player} &eвышел из пати!")
        addDefault(Messages.COMMAND_PARTY_DISBAND_SUCCESS, "{prefix}&eПати расфармированно!")
        addDefault(Messages.COMMAND_PARTY_REMOVE_USAGE, "{prefix}&7Используйте: &e/party remove <игрок>")
        addDefault(Messages.COMMAND_PARTY_REMOVE_SUCCESS, "{prefix}&7{player} &eбыл удален из пати.")
        addDefault(
            Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER,
            "{prefix}&7{player} &eне состоит в вашем пати!"
        )
        addDefault(Messages.COMMAND_PARTY_PROMOTE_SUCCESS, "{prefix}&eВы успешно повысили {player} до владельца")
        addDefault(Messages.COMMAND_PARTY_PROMOTE_OWNER, "{prefix}&eВы были повышены до владельца группы")
        addDefault(
            Messages.COMMAND_PARTY_PROMOTE_NEW_OWNER,
            "{prefix}&7 &e{player} был повышен до владельца группы"
        )
        addDefault(Messages.COMMAND_PARTY_INFO_OWNER, "\n{prefix}&eВладелец группы: &7{owner}")
        addDefault(Messages.COMMAND_PARTY_INFO_PLAYERS, "{prefix}&eУчастники группы:")
        addDefault(Messages.COMMAND_PARTY_INFO_PLAYER, "&7{player}")
        addDefault(Messages.NEXT_EVENT_BEDS_DESTROY, "&cУничтожение кроватей")
        addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_II, "&fАлмазы II")
        addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_III, "&fАлмазы III")
        addDefault(Messages.NEXT_EVENT_DRAGON_SPAWN, "&fВнезапная смерть")
        addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_II, "&fИзумруды II")
        addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_III, "&fИзумруды III")
        addDefault(Messages.NEXT_EVENT_GAME_END, "&4Игра закончиться")
        addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED, "&cВАША КРОВАТЬ УНИЧТОЖЕНА!")
        addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED, "&fВсе кровати были уничтожены!")
        addDefault(Messages.NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED, "&c&lВсе кровати были уничтожены!")
        addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH, "&cСмерть")
        addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH, "")
        addDefault(
            Messages.NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH,
            "&cСМЕРТЬ: &6&b{TeamDragons} {TeamColor}{TeamName} Dragon!"
        )
        addDefault(Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS, "{prefix}&cКоманда не найдена или у вас нет прав!")
        addDefault(Messages.COMMAND_FORCESTART_NOT_IN_GAME, "§c▪ §7Вы не играете!")
        addDefault(Messages.COMMAND_FORCESTART_SUCCESS, "§c▪ §7Обратный отсчет сокращен!")
        addDefault(
            Messages.COMMAND_FORCESTART_NO_PERM,
            "{prefix}&7Вы не можете сократить время старта.\n§7Подумайте о том, чтобы купить донат."
        )
        addDefault(
            Messages.COMMAND_JOIN_SPECTATOR_MSG,
            "{prefix}§6Ты теперь наблюдаешь за §9{arena}§6.\n{prefix}§eДля выхода с арены используй §c/leave§e."
        )
        addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_NAME, "&8Телепортер") // Да-да :D
        addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME, "{vPrefix}{player}")
        addDefault(
            Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE,
            listOf("&7Здоровье: &f{health}%", "&7Насыщенность: &f{food}", "", "&7ЛКМ для слежки")
        )
        addDefault(Messages.ARENA_SPECTATOR_LEAVE_ITEM_NAME, "&c&lВернутья в лобби")
        addDefault(
            Messages.ARENA_SPECTATOR_LEAVE_ITEM_LORE,
            listOf("&7Щелкните правой кнопкой мыши, чтобы вернуться в лобби!")
        )
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE, "&aСлежка за &7{player}")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE, "&cШИФТ для выхода")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE, "&eПокидание режима слежки")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE, "")
        addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eоффлайн!")
        addDefault(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG, "&cНа этой арене отключена слежка!")
        addDefault(
            Messages.ARENA_LEAVE_PARTY_DISBANDED,
            "{prefix}§cВладелец пати покинут сервер, пати распущена!"
        ) // Можно сменить на команду в целом, сленговое слово пати тоже норм.

        // Добавлено Matveev_: согл, сленговое лучше, харош

        /* Lobby Command Items */
        addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "stats"), "&eСтатистика")
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fПКМ, чтобы увидеть свою статистику!")
        )
        addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "arena-selector"), "&eАрены")
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "arena-selector"),
            listOf("&fВыберите арену!")
        )
        addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "leave"), "&eВернутья в лобби")
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fЩелкните правой кнопкой мыши, чтобы вернуться в лобби!")
        )
        /* Pre Game Command Items */
        addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "stats"), "&eСтатистика")
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fПКМ, чтобы увидеть свою статистику!")
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "leave"),
            "&eВернутья в лобби"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fЩелкните правой кнопкой мыши, чтобы вернуться в лобби!")
        )
        /* Spectator Command Items */
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "teleporter"),
            "&eТелепортер"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "teleporter"),
            listOf("&fПКМ, чтобы наблюдать за игроками!")
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "leave"),
            "&eВернутья в лобби"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fЩелкните правой кнопкой мыши, чтобы вернуться в лобби!")
        )

        addDefault(Messages.COMMAND_COOLDOWN, "&cВы не можете сделать этого! Подождите еще {seconds} секунд!")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_TEAM, "{TeamColor}[{TeamName}]")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SHOUT, "&6[ВСЕМ]")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SPECTATOR, "&7[НАБЛЮДАТЕЛЬ]")
        addDefault(
            Messages.ARENA_JOIN_DENIED_SELECTOR,
            "{prefix}&cИзвините, но вы не можете пресоедениться к этой арене прямо сейчас. Используйте ПКМ для режима наблюдателей!"
        )
        addDefault(
            Messages.ARENA_SPECTATE_DENIED_SELECTOR,
            "{prefix}&cИзвините, но вы не можете наблюдать за этой ареной прямо сейчас. Используйте ЛКМ для входа в игру!"
        )
        addDefault(
            Messages.ARENA_JOIN_DENIED_NO_PROXY,
            "&cИзвините, но вы должны подключиться к арене, используя BedWarsProxy!"
        )

        addDefault(Messages.REJOIN_NO_ARENA, "{prefix}&cАрен для перезахода не найдено!")
        addDefault(
            Messages.REJOIN_DENIED,
            "{prefix}&cВы больше не можете перезайти. Игра окончена или ваша кровать была разрушена."
        )
        addDefault(Messages.REJOIN_ALLOWED, "{prefix}&eПереносим вас в игру, на карту &a{arena}&e!")


        addDefault(Messages.MEANING_NO_TRAP, "Ловушки отсутствуют!")
        addDefault(Messages.FORMAT_SPECTATOR_TARGET, "{targetTeamColor}{targetDisplayName}")
        addDefault(Messages.FORMAT_UPGRADE_TRAP_COST, "&7Цена: {currencyColor}{cost} {currency}")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD, "&e")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD, "&c")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_UNLOCKED, "&a")
        addDefault(Messages.FORMAT_UPGRADE_TIER_LOCKED, "&7")
        addDefault(Messages.FORMAT_UPGRADE_TIER_UNLOCKED, "&a")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "{color}Нажми для покупки!")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY, "{color}Тебе не хватает {currency}")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_LOCKED, "&cЗАБЛОКИРОВАНО")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED, "{color}РАЗБЛОКИРОВАНО")
        addDefault(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT, "&a{player} купил &6{upgradeName}")
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-1"),
            "{color}Железная Печь"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "forge"),
            listOf(
                "&7Делает быстрее спавнер",
                "&7ресурсов вашего острова.",
                "",
                "{tier_1_color}Фаза 1: +50% К ресурсам, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Фаза 2: +100% К ресурсам, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Фаза 3: Спавнит изумруды, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Фаза 4: +200% К ресурсам, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-2"),
            "{color}Золотая Печь"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-3"),
            "{color}Изумрудная Печь"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-4"),
            "{color}Алмазная Печь"
        )
        addDefault(Messages.UPGRADES_CATEGORY_ITEM_NAME_PATH + "traps", "&eКупить ловушку")
        addDefault(
            Messages.UPGRADES_CATEGORY_ITEM_LORE_PATH + "traps",
            listOf(
                "&7Купленные ловушки будут",
                "&7размещенны в очереди.",
                "",
                "&eНажмите чтобы посмотреть!"
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "swords").replace("{tier}", "tier-1"),
            "{color}Заострённые Мечи"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "swords"),
            listOf(
                "&7Ваша команда получит",
                "&7Остроту I на все мечи,",
                "&7а также топоры!",
                "",
                "{tier_1_color}Цена: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-1"),
            "{color}Защищёная Броня I"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "armor"),
            listOf(
                "&7Ваша команда получит",
                "&7Защиту I на всю броню!",
                "",
                "{tier_1_color}Фаза 1: Защита I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Фаза 2: Защита II, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Фаза 3: Защита III, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Фаза 4: Защита IV, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-2"),
            "{color}Защищёная Броня II"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-3"),
            "{color}Защищёная Броня III"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-4"),
            "{color}Защищёная Броня IV"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-1"),
            "{color}Быстрый Шахтёр I"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "miner"),
            listOf(
                "&7Ваша команда получит",
                "&7Спешку I.",
                "",
                "{tier_1_color}Фаза 1: Спешка I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Фаза 2: Спешка II, &b{tier_2_cost} {tier_2_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-2"),
            "{color}Быстрый Шахтёр II"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "heal-pool").replace("{tier}", "tier-1"),
            "{color}Исцеляющее Поле"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "heal-pool"),
            listOf(
                "&7Создаёт исцеляющее поле",
                "&7вокруг вашей базы!",
                "",
                "{tier_1_color}Цена: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "dragon").replace("{tier}", "tier-1"),
            "{color}Драконы"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "dragon"),
            listOf(
                "&7Ваша команда будет иметь 2 драконов,",
                "&7вместо 1 во время конца игры!",
                "",
                "{tier_1_color}Цена: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "glass", "&8⬆&7Покупаемое")
        addDefault(
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "glass",
            listOf("&8⬇&7Очередь Ловушек")
        )
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "first", "{color}Ловушка #1: {name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "first",
            listOf("&7Первый человек который зайдёт", "&7на базу, активирует", "&7эту ловушку!")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "first",
            listOf(
                "",
                "&7Покупка ловушки,",
                "&7добавит её в очередь. Её цена",
                "&7будет увеличиваться по мере",
                "&7числа купленных ловушек.",
                "",
                "&7Следущяя ловушка: &b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "second", "{color}Ловушка #2: {name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "second",
            listOf("&7Второй человек который зайдёт", "&7на базу, активирует", "&7эту ловушку!")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "second",
            listOf(
                "",
                "&7Покупка ловушки,",
                "&7добавит её в очередь. Её цена",
                "&7будет увеличиваться по мере",
                "&7числа купленных ловушек.",
                "",
                "&7Следущяя ловушка: &b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "third", "{color}Ловушка #3: {name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "third",
            listOf("&7Третий человек который зайдёт", "&7на базу, активирует", "&7эту ловушку!")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "third",
            listOf(
                "",
                "&7Покупка ловушки,",
                "&7добавит её в очередь. Её цена",
                "&7будет увеличиваться по мере",
                "&7числа купленных ловушек.",
                "",
                "&7Следущяя ловушка: &b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "1", "{color}Это ловушка!")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "1",
            listOf("&7Накладывает Слепоту и Медлительность", "&7на 5 секунд.", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "2", "{color}Ловушка-Помощник")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "2",
            listOf("&7Даёт вам и вашим союзникам", "&7скорость I на 15 секунд.", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "3", "{color}Ловушка с Тревогой")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "3",
            listOf("&7Показывает игроков, которые были", "&7с зельем невидимости.", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "4", "{color}Ловушка Медленный Шахтёр")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "4",
            listOf("&7Замедляет копание на", "&710 секунд.", "")
        )
        addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "back", "&aНазад")
        addDefault(
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "back",
            listOf("&7К Улучшениям и Ловушкам")
        )
        addDefault(Messages.UPGRADES_CATEGORY_GUI_NAME_PATH + "traps", "&8Очередь ловушек")
        addDefault(Messages.UPGRADES_TRAP_QUEUE_LIMIT, "&cОчередь ловушек переполнена!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_MSG, "&c&lВаша {trap} был(а) отключена!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_TITLE, "&cЛОВУШКА БЫЛА АКТИВИРОВАНА!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_SUBTITLE, "&fВаша {trap} был(а) активирован(а)!")
        addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_MSG + "3",
            "&c&lЛовушка была активирована игроком &7&l{player} &c&lиз {color}&l{team} &c&lкоманды!"
        ) // set off = активирована?
        // Добавлено Matveev_: не ебу
        addDefault(Messages.UPGRADES_TRAP_CUSTOM_TITLE + "3", "&c&lВНИМАНИЕ!!!")
        addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + "3",
            "&fЛовушка была активирована командой {color}{team} &f!"
        )
        save()
        setPrefix(m(Messages.PREFIX))
    }
}
