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

class Indonesia : Language(BedWars.plugin, "id") {
    init {
        this.options().copyDefaults(true)
        this.addDefault(Messages.PREFIX, "")
        this.addDefault("name", "Indonesia")

        // this must stay here
        // move message to new path
        if (this.get("player-die-knocked-regular") != null && this.get(Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL) == null) {
            this.set(Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL, this.getString("player-die-knocked-regular"))
            this.set("player-die-knocked-regular", null)
        }
        if (this.get("player-die-knocked-final") != null && this.get(Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL) == null) {
            this.set(Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL, this.getString("player-die-knocked-final"))
            this.set("player-die-knocked-final", null)
        }

        this.addDefault(
            Messages.COMMAND_MAIN,
            Arrays.asList<String?>(
                "",
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " stats",
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " join &o<arena/grup>",
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " leave",
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " lang",
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " gui",
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " start &3(vip)"
            )
        )
        this.addDefault(Messages.COMMAND_LANG_LIST_HEADER, "{prefix} &2Bahasa Tersedia:")
        this.addDefault(Messages.COMMAND_LANG_LIST_FORMAT, "&a▪  &7{iso} - &f{name}")
        this.addDefault(Messages.COMMAND_LANG_USAGE, "{prefix}&7Gunakan: /lang &f&o<bahasa>")
        this.addDefault(Messages.COMMAND_LANG_SELECTED_NOT_EXIST, "{prefix}&cBahasa ini tidak tersedia!")
        this.addDefault(Messages.COMMAND_LANG_SELECTED_SUCCESSFULLY, "{prefix}&aBahasa terganti!")
        this.addDefault(
            Messages.COMMAND_LANG_USAGE_DENIED,
            "{prefix}&cAnda tidak bisa ganti bahasa saat dalam permainan."
        )
        this.addDefault(Messages.COMMAND_JOIN_USAGE, "§a▪ §7Gunakan: /" + BedWars.MAIN_COMMAND + " join §o<arena/grup>")
        this.addDefault(
            Messages.COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND,
            "{prefix}&cTidak ada arena atau group yang bernama: {name}"
        )
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL,
            "{prefix}&cArena tersebut penuh!\n&aSilakan pertimbangkan untuk menyumbang untuk lebih banyak fitur. &7&o(click)"
        )
        this.addDefault(
            Messages.COMMAND_JOIN_NO_EMPTY_FOUND,
            "{prefix}&cTidak ada arena yang tersedia untuk sekarang ;("
        )
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS,
            "{prefix}&cKami meminta maaf tapi arena sedang penuh.\n&cKami tahu Anda seorang donatur tetapi sebenarnya arena ini penuh dengan staf atau/dan donatur."
        )
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_PARTY_TOO_BIG,
            "{prefix}&cParty anda terlalu ramai untuk memasuki arena sebagai team :("
        )
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_NOT_PARTY_LEADER,
            "{prefix}&cHanya pemimpin yang dapat memilih arena."
        )
        this.addDefault(Messages.COMMAND_JOIN_PLAYER_JOIN_MSG, "{prefix}&7{player} &etelah masuk (&b{on}&e/&b{max}&e)!")
        this.addDefault(
            Messages.COMMAND_JOIN_SPECTATOR_MSG,
            "{prefix}§6Kamu sekarang menonton §9{arena}§6.\n{prefix}§eKamu bisa keluar arena kapan pun dengan cara §c/leave§e."
        )
        this.addDefault(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG, "&cPenonton tidak diperbolehkan di arena tersebut!")
        this.addDefault(Messages.COMMAND_TP_PLAYER_NOT_FOUND, "{prefix}&cPemain tidak ditemukan!")
        this.addDefault(Messages.COMMAND_TP_NOT_IN_ARENA, "{prefix}&cPlayer tersebut tidak ada di arena!")
        this.addDefault(Messages.COMMAND_TP_NOT_STARTED, "{prefix}&cArena dimana player tersebut berada belum dimulai!")
        this.addDefault(Messages.COMMAND_TP_USAGE, "{prefix}&cGunakan: /bw tp <nama pengguna>")
        this.addDefault(Messages.REJOIN_NO_ARENA, "{prefix}&cTidak ada arena untuk bergabung kembali!")
        this.addDefault(
            Messages.REJOIN_DENIED,
            "{prefix}&cKamu tidak dapat bergabung ke arena kembali. Pertandingan selesai atau bed kamu telah dihancurkan."
        )
        this.addDefault(Messages.REJOIN_ALLOWED, "{prefix}&eMemasuki arena &a{arena}&e!")
        this.addDefault(Messages.COMMAND_REJOIN_PLAYER_RECONNECTED, "{prefix}&7{player} &etelah terhubung kembali!")
        this.addDefault(Messages.COMMAND_LEAVE_DENIED_NOT_IN_ARENA, "{prefix}&cKamu sedang tidak di arena!")
        this.addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&7{player} &etelah keluar!")
        this.addDefault(
            Messages.COMMAND_NOT_ALLOWED_IN_GAME,
            "{prefix}&cAnda tidak dapat melakukan ini selama pertandingan."
        )
        this.addDefault(
            Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS,
            "{prefix}&cPerintah tidak ditemukan atau Anda tidak memiliki izin!"
        )
        this.addDefault(
            Messages.COMMAND_PARTY_HELP, listOf(
                "&6▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&aPerintah Party:",
                "&e/party help &7- &bCetak pesan bantuan ini",
                "&e/party invite <player> &7- &bMengundang pemain ke party Anda",
                "&e/party leave &7- &bMeninggalkan party yang anda masuki",
                "&e/party info &7- &bShow party members and owner",
                "&e/party promote <player> &7- &bTransfer party ownership",
                "&e/party remove <player> &7- &bKeluarkan pemain dari party",
                "&e/party accept <player> &7- &bTerima undangan party",
                "&e/party disband &7- &bBubarkan party"
            )
        )
        this.addDefault(Messages.COMMAND_PARTY_INVITE_USAGE, "{prefix}&eGunakan: &7/party invite <pemain>")
        this.addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eis not online!")
        this.addDefault(Messages.COMMAND_PARTY_INVITE_SENT, "{prefix}&eInvite sent to &7{player}&6.")
        this.addDefault(
            Messages.COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG,
            "{prefix}&b{player} &ehas invited you to a party! &o&7(Click to accept)"
        )
        this.addDefault(
            Messages.COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF,
            "{prefix}&cKamu tidak dapat mengundang diri anda sendiri!"
        )
        this.addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &esedang offline!")
        this.addDefault(
            Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE,
            "{prefix}&eTidak ada undangan party yang perlu kamu terima!"
        )
        this.addDefault(Messages.COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY, "{prefix}&eKamu sedang berada di party!")
        this.addDefault(
            Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS,
            "{prefix}&cHanya pemilik party yang dapat melakukan ini!"
        )
        this.addDefault(Messages.COMMAND_PARTY_ACCEPT_USAGE, "{prefix}&eGunakan: &7/party accept <pemain>")
        this.addDefault(Messages.COMMAND_PARTY_ACCEPT_SUCCESS, "{prefix}&7{player} &etelah memasuki party!")
        this.addDefault(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY, "{prefix}&cKamu tidak berada di party!")
        this.addDefault(
            Messages.COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND,
            "{prefix}&cKamu tidak dapat keluar dari party kamu sendiri!\n&eCoba gunakan: &b/party disband"
        )
        this.addDefault(Messages.COMMAND_PARTY_LEAVE_SUCCESS, "{prefix}&7{player} &etelah keluar party!")
        this.addDefault(Messages.COMMAND_PARTY_DISBAND_SUCCESS, "{prefix}&eParty dibubarkan!")
        this.addDefault(Messages.COMMAND_PARTY_REMOVE_USAGE, "{prefix}&7Gunakan: &e/party remove <pemain>")
        this.addDefault(Messages.COMMAND_PARTY_REMOVE_SUCCESS, "{prefix}&7{player} &etelah dikeluarkan dari party,")
        this.addDefault(
            Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER,
            "{prefix}&7{player} &etidak ada di party!"
        )
        this.addDefault(Messages.COMMAND_PARTY_PROMOTE_SUCCESS, "{prefix}&eYou successfully promoted {player} to owner")
        this.addDefault(Messages.COMMAND_PARTY_PROMOTE_OWNER, "{prefix}&eYou have been promoted to party owner")
        this.addDefault(Messages.COMMAND_PARTY_PROMOTE_NEW_OWNER, "{prefix}&7 &e{player} has been promoted to owner")
        this.addDefault(Messages.COMMAND_PARTY_INFO_OWNER, "\n{prefix}&eOwner of the party is: &7{owner}")
        this.addDefault(Messages.COMMAND_PARTY_INFO_PLAYERS, "{prefix}&eParty members:")
        this.addDefault(Messages.COMMAND_PARTY_INFO_PLAYER, "&7{player}")
        this.addDefault(Messages.COMMAND_FORCESTART_NOT_IN_GAME, "§c▪ §7Kamu sedang tidak bermain!")
        this.addDefault(Messages.COMMAND_FORCESTART_SUCCESS, "§c▪ §7Hitung mundur dipersingkat!")
        this.addDefault(
            Messages.COMMAND_FORCESTART_NO_PERM,
            "{prefix}&7Anda tidak dapat memaksa memulai arena.\n§7Harap pertimbangkan untuk menyumbang untuk fitur VIP."
        )
        this.addDefault(Messages.COMMAND_COOLDOWN, "&cAnda belum bisa melakukannya! Tunggu {seconds} detik lagi!")
        this.addDefault(
            Messages.ARENA_JOIN_VIP_KICK,
            "{prefix}&cMaaf, tapi Anda dikeluarkan karena donatur bergabung ke arena.\n&aHarap pertimbangkan untuk menyumbang untuk lebih banyak fitur. &7&o(click)"
        )
        this.addDefault(
            Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT,
            "{prefix}§cTidak ada cukup pemain! Hitung mundur dihentikan!"
        )
        this.addDefault(Messages.ARENA_RESTART_PLAYER_KICK, "{prefix}&eArena tempat Anda berada sedang dimulai ulang.")
        this.addDefault(Messages.ARENA_STATUS_PLAYING_NAME, "&cBermain")
        this.addDefault(Messages.ARENA_STATUS_RESTARTING_NAME, "&4Memulai Kembali")
        this.addDefault(Messages.ARENA_STATUS_WAITING_NAME, "&2Menunggu §c{full}")
        this.addDefault(Messages.ARENA_STATUS_STARTING_NAME, "&6Mulai §c{full}")
        this.addDefault(Messages.ARENA_GUI_INV_NAME, "&8Klik untuk bergabung!")
        this.addDefault(Messages.ARENA_GUI_ARENA_CONTENT_NAME, "&a&l{name}")
        this.addDefault(
            Messages.ARENA_GUI_ARENA_CONTENT_LORE,
            listOf(
                "",
                "&7Status: {status}",
                "&7Pemain: &f{on}&7/&f{max}",
                "&7Tipe: &a{group}",
                "",
                "&aKlik Kiri untuk bergabung.",
                "&eKlik Kanan untuk menonton."
            )
        )
        this.addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_NAME, "&r{serverIp}")
        this.addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_LORE, mutableListOf<Any?>())
        this.addDefault(
            Messages.ARENA_STATUS_START_COUNTDOWN_CHAT,
            "{prefix}&ePermainan dimulai dalam &6{time} &edetik!"
        )
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_TITLE, " ")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE, "&a{second}")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-5", "&e❺")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-4", "&e❹")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-3", "&c❸")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-2", "&c❷")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-1", "&c❶")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_TITLE, " ")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_SUB_TITLE, "&cMenunggu lebih banyak pemain..")
        this.addDefault(Messages.ARENA_STATUS_START_PLAYER_TITLE, "&aMULAI")
        this.addDefault(
            Messages.ARENA_STATUS_START_PLAYER_TUTORIAL, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lPerang Kasur", "",
                "&e&l    Lindungi kasur Anda dan hancurkan kasur musuh.",
                "&e&l  Tingkatkan diri Anda dan tim Anda dengan mengumpulkan",
                "&e&l   Besi, Emas, Zamrud, dan Berlian dari generator",
                "&e&l   untuk mendapatkan akses peningkatan yang kuat.", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        this.addDefault(
            Messages.ARENA_JOIN_DENIED_SELECTOR,
            "{prefix}&cMaaf, tetapi sementara Anda tidak dapat bergabung ke arena tersebut. Gunakan Klik Kanan untuk menonton!"
        )
        this.addDefault(
            Messages.ARENA_SPECTATE_DENIED_SELECTOR,
            "{prefix}&cMaaf tapi sementara kamu tidak bisa menonton arena ini. Gunakan Klik Kiri untuk bergabung!"
        )
        this.addDefault(
            Messages.ARENA_JOIN_DENIED_NO_PROXY,
            "&cMaaf, tetapi Anda harus bergabung ke arena menggunakan BedWarsProxy. \n&eJika Anda ingin menyiapkan arena, pastikan untuk memberi diri Anda izin bw.setup sehingga Anda dapat bergabung dengan server secara langsung!"
        )
        this.addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_NAME, "&8Teleporter")
        this.addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME, "{prefix}{player}")
        this.addDefault(
            Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE,
            listOf(
                "&7Hati: &f{health}%",
                "&7Bar Kelaparan: &f{food}",
                "",
                "&7Klik kiri untuk menonton!"
            )
        )
        this.addDefault(Messages.ARENA_SPECTATOR_LEAVE_ITEM_NAME, "&c&lKembali ke lobi")
        this.addDefault(
            Messages.ARENA_SPECTATOR_LEAVE_ITEM_LORE,
            listOf("&7Klik kanan untuk keluar ke lobi!")
        )
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE, "&aMenonton &7{player}")
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE, "&cSNEAK untuk keluar")
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE, "&eKeluar dari mode Penonton")
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE, "")
        this.addDefault(
            Messages.ARENA_LEAVE_PARTY_DISBANDED,
            "{prefix}§cPemilik party telah pergi dan party itu dibubarkan!"
        )
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TIER, "&eTingkat &c{tier}")
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND, "&b&lBerlian")
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD, "&a&lZamrud")
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TIMER, "&eMuncul dalam &c{seconds} &edetik")
        this.addDefault(
            Messages.GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT,
            "{prefix}{generatorType} Generator &etelah ditingkatkan ke Tingkat &c{tier}"
        )
        this.addDefault(Messages.FORMATTING_CHAT_LOBBY, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        this.addDefault(Messages.FORMATTING_CHAT_WAITING, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        this.addDefault(
            Messages.FORMATTING_CHAT_SHOUT,
            "&6[SHOUT] {team} {level}{vPrefix}&7{player}&f{vSuffix}: {message}"
        )
        this.addDefault(Messages.FORMATTING_CHAT_TEAM, "&f{team} {level}{vPrefix}&7{player}{vSuffix} {message}")
        this.addDefault(Messages.FORMATTING_CHAT_SPECTATOR, "&7[PENONTON] {level}{vPrefix}{player}{vSuffix}: {message}")
        this.addDefault(Messages.FORMATTING_SCOREBOARD_HEALTH, listOf("&c❤"))

        this.addDefault(Messages.FORMATTING_SCOREBOARD_DATE, "dd/MM/yy")
        this.addDefault(
            Messages.FORMATTING_SCOREBOARD_TEAM_GENERIC,
            "{TeamColor}{TeamLetter}&f {TeamName}: {TeamStatus}"
        )
        this.addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ELIMINATED, "&c&l✘")
        this.addDefault(Messages.FORMATTING_SCOREBOARD_BED_DESTROYED, "&a{remainingPlayers}")
        this.addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ALIVE, "&a&l✓")
        this.addDefault(Messages.FORMATTING_SCOREBOARD_NEXEVENT_TIMER, "mm:ss")
        this.addDefault(Messages.FORMATTING_SCOREBOARD_YOUR_TEAM, "&7 KAMU")
        this.addDefault(Messages.FORMATTING_ACTION_BAR_TRACKING, "&fMelacak: {team} &f- Jarak: {distance}m")
        this.addDefault(Messages.FORMATTING_TEAM_WINNER_FORMAT, "      {TeamColor}{TeamName} &7- {members}")
        this.addDefault(Messages.FORMATTING_SOLO_WINNER_FORMAT, "                 {TeamColor}{TeamName} &7- {members}")
        this.addDefault(Messages.FORMATTING_GENERATOR_TIER1, "I")
        this.addDefault(Messages.FORMATTING_GENERATOR_TIER2, "II")
        this.addDefault(Messages.FORMATTING_GENERATOR_TIER3, "III")
        this.addDefault(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH, "▮ ")
        this.addDefault(Messages.FORMATTING_STATS_DATE_FORMAT, "yyyy/MM/dd HH:mm")
        this.addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_TEAM, "{TeamColor}[{TeamName}]")
        this.addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SHOUT, "&6[SHOUT]")
        this.addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SPECTATOR, "&7[PENONTON]")
        this.addDefault(Messages.MEANING_FULL, "Penuh")
        this.addDefault(Messages.MEANING_SHOUT, "shout")
        this.addDefault(Messages.MEANING_NOBODY, "Bukan Siapa Siapa")
        this.addDefault(Messages.MEANING_NEVER, "Tidak Pernah")
        this.addDefault(Messages.MEANING_IRON_SINGULAR, "Besi")
        this.addDefault(Messages.MEANING_IRON_PLURAL, "Besi")
        this.addDefault(Messages.MEANING_GOLD_SINGULAR, "Emas")
        this.addDefault(Messages.MEANING_GOLD_PLURAL, "Emas")
        this.addDefault(Messages.MEANING_EMERALD_SINGULAR, "Zamrud")
        this.addDefault(Messages.MEANING_EMERALD_PLURAL, "Zamrud")
        this.addDefault(Messages.MEANING_DIAMOND_SINGULAR, "Zamrud")
        this.addDefault(Messages.MEANING_DIAMOND_PLURAL, "Zamrud")
        this.addDefault(Messages.MEANING_VAULT_SINGULAR, "$")
        this.addDefault(Messages.MEANING_VAULT_PLURAL, "$")
        this.addDefault(Messages.INTERACT_CANNOT_PLACE_BLOCK, "{prefix}&cAnda tidak dapat menempatkan blok di sini!")
        this.addDefault(
            Messages.INTERACT_CANNOT_BREAK_BLOCK,
            "{prefix}&cAnda hanya dapat memecahkan blok yang ditempatkan oleh pemain!"
        )
        this.addDefault(Messages.INTERACT_CANNOT_BREAK_OWN_BED, "&cAnda tidak dapat menghancurkan kasur Anda sendiri!")
        this.addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT,
            "\n&f&lPENGHANCURAN KASUR > {TeamColor}{TeamName} Kasur &7telah dihancurkan oleh {PlayerColor}{PlayerName}&7!\n"
        )
        this.addDefault(Messages.INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT, "&cKASUR HANCUR!")
        this.addDefault(Messages.INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT, "&fAnda tidak akan respawn kembali!")
        this.addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM,
            "&f&lPENGHANCURKAN KASUR N > &7Kasur Anda telah dihancurkan oleh {PlayerColor}{PlayerName}&7!"
        )
        this.addDefault(
            Messages.INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED,
            "&cAnda tidak dapat membuka peti ini karena tim ini belum tereliminasi!"
        )
        this.addDefault(
            Messages.INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN,
            "&cYou are no longer invisible because you have taken damage!"
        )
        this.addDefault(Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL, "{PlayerColor}{PlayerName} &7jatuh ke void.")
        this.addDefault(
            Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7jatuh ke void. &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7terlempar ke dalam void oleh {KillerColor}{KillerName}&7."
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7terlempar ke dalam void oleh {KillerColor}{KillerName}&7. &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_REGULAR,
            "{PlayerColor}{PlayerName} &7terputus saat bertarung dengan {KillerColor}{KillerName}&7."
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_FINAL,
            "{PlayerColor}{PlayerName} &7terputus saat bertarung dengan {KillerColor}{KillerName}&7. &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7didorong oleh {KillerColor}{KillerName}&7."
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7didorong oleh {KillerColor}{KillerName}&7. &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7terkena bom cinta dari {KillerColor}{KillerName}&7."
        )
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7terkena bom cinta dari {KillerColor}{KillerName}&7. &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR, "{PlayerColor}{PlayerName} &7terkena bom.")
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7terkena bom. &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7dibunuh oleh {KillerColor}{KillerName}&7."
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7dibunuh oleh {KillerColor}{KillerName}&7. &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(Messages.PLAYER_DIE_UNKNOWN_REASON_REGULAR, "{PlayerColor}{PlayerName} &7mati.")
        this.addDefault(
            Messages.PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7mati. &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_SHOOT_REGULAR,
            "{PlayerColor}{PlayerName} &7ditembak oleh {KillerColor}{KillerName}&7."
        )
        this.addDefault(
            Messages.PLAYER_DIE_SHOOT_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7ditembak oleh {KillerColor}{KillerName}&7! &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_DEBUG_REGULAR,
            "{PlayerColor}{PlayerName} &7dibunuh oleh {KillerColor}BedBug si {KillerTeamName}!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_DEBUG_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7dibunuh oleh {KillerColor}{KillerTeamName}'s &7BedBug! &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_REGULAR,
            "{PlayerColor}{PlayerName} &7dibunuh oleh {KillerColor}Iron Golem si {KillerTeamName}!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7dibunuh oleh {KillerColor}Iron Golem si {KillerTeamName}! &b&lPEMBUNUHAN TERAKHIR!"
        )
        this.addDefault(Messages.PLAYER_DIE_REWARD_DIAMOND, "{prefix}&b+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_REWARD_EMERALD, "{prefix}&a+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_REWARD_IRON, "{prefix}&f+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_REWARD_GOLD, "{prefix}&6+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_RESPAWN_TITLE, "&cKAMU MATI!")
        this.addDefault(Messages.PLAYER_DIE_RESPAWN_SUBTITLE, "&eAnda akan muncul kembali dalam &c{time} &edetik!")
        this.addDefault(Messages.PLAYER_DIE_RESPAWN_CHAT, "{prefix}&eAnda akan muncul kembali dalam &c{time} &edetik!")
        this.addDefault(Messages.PLAYER_DIE_RESPAWNED_TITLE, "&aMUNCUL KEMBALI!")
        this.addDefault(Messages.PLAYER_DIE_ELIMINATED_CHAT, "{prefix}&cKamu telah dieliminasi!")
        this.addDefault(Messages.PLAYER_HIT_BOW, "{prefix}{TeamColor}{PlayerName} &7berada di &c{amount} &7HP!")
        this.addDefault(Messages.GAME_END_GAME_OVER_PLAYER_TITLE, "&c&lPERMAINAN SELESAI!")
        this.addDefault(Messages.GAME_END_VICTORY_PLAYER_TITLE, "&6&lKEMENANGAN!")
        this.addDefault(Messages.GAME_END_TEAM_WON_CHAT, "{prefix}{TeamColor}{TeamName} &atelah memenangkan permainan!")
        this.addDefault(
            Messages.GAME_END_TOP_PLAYER_CHAT, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lPerang Kasur",
                "",
                "{winnerFormat}",
                "",
                "",
                "&6                      &6⭐ &lPembunuh Pertama &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&e                        &lPembunuh Kedua &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&c                        &lPembunuh Ketiga &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        this.addDefault(Messages.BED_HOLOGRAM_DEFEND, "&c&lPertahankan Kasur Anda!")
        this.addDefault(Messages.BED_HOLOGRAM_DESTROYED, "&c&lKasur anda telah dihancurkan!")
        this.addDefault(Messages.NPC_NAME_TEAM_UPGRADES, "&bPENINGKATAN TIM,&e&LKLIK KANAN")
        this.addDefault(Messages.NPC_NAME_SOLO_UPGRADES, "&bPENINGKATAN INDIVIDU,&e&lKLIK KANAN")
        this.addDefault(Messages.NPC_NAME_TEAM_SHOP, "&bTOKO TIM,&e&lKLIK KANAN")
        this.addDefault(Messages.NPC_NAME_SOLO_SHOP, "&bTOKO BARANG,&e&lKLIK KANAN")
        this.addDefault(
            Messages.TEAM_ELIMINATED_CHAT,
            "\n&f&lTIM ELIMINASI > {TeamColor}Tim {TeamName} &ctelah dieliminasi!\n"
        )
        this.addDefault(Messages.NEXT_EVENT_BEDS_DESTROY, "&cPenghancuran Kasur")
        this.addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_II, "&fBerlian II")
        this.addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_III, "&fBerlian III")
        this.addDefault(Messages.NEXT_EVENT_DRAGON_SPAWN, "&fKematian Mendadak")
        this.addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_II, "&fZamrud II")
        this.addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_III, "&fZamrud III")
        this.addDefault(Messages.NEXT_EVENT_GAME_END, "&4Permainan Berakhir")
        this.addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED, "&cKASUR HANCUR!")
        this.addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED, "&fSemua kasur telah dihancurkan!")
        this.addDefault(Messages.NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED, "&c&lSemua kasur telah dihancurkan!")
        this.addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH, "&cKematian Mendadak")
        this.addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH, "")
        this.addDefault(
            Messages.NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH,
            "&cKEMATIAN MENDADAK: &6&b{TeamDragons} {TeamColor}Naga {TeamName}!"
        )
        this.addDefault(Messages.XP_REWARD_PER_MINUTE, "{prefix}&6+{xp} Experience Bed Wars Diterima (Waktu Bermain).")
        this.addDefault(Messages.XP_REWARD_WIN, "{prefix}&6+{xp} Experience Bed Wars Diterima (Memenangkan Permainan).")
        this.addDefault(Messages.XP_REWARD_PER_TEAMMATE, "{prefix}&6+{xp} Experience Bed Wars Diterima (Dukungan Tim).")
        this.addDefault(
            Messages.XP_REWARD_BED_DESTROY,
            "{prefix}&6+{xp} Experience Bed Wars Diterima (Menghancurkan Kasur)."
        )
        this.addDefault(
            Messages.XP_REWARD_REGULAR_KILL,
            "{prefix}&6+{xp} Experience Bed Wars Diterima (Pembunuhan Biasa)."
        )
        this.addDefault(
            Messages.XP_REWARD_FINAL_KILL,
            "{prefix}&6+{xp} Experience Bed Wars Diterima (Pembunuhan Terakhir)."
        )

        this.addDefault(Messages.MONEY_REWARD_PER_MINUTE, "{prefix}&6+{money} Koin (Waktu Bermain).")
        this.addDefault(Messages.MONEY_REWARD_WIN, "{prefix}&6+{money} Koin (Memenangkan Permainan).")
        this.addDefault(Messages.MONEY_REWARD_PER_TEAMMATE, "{prefix}&6+{money} Koin (Dukungan Tim).")
        this.addDefault(Messages.MONEY_REWARD_BED_DESTROYED, "{prefix}&6+{money} Koin (Menghancurkan Kasur).")
        this.addDefault(Messages.MONEY_REWARD_FINAL_KILL, "{prefix}&6+{money} Koin (Pembunuhan Terakhir).")
        this.addDefault(Messages.MONEY_REWARD_REGULAR_KILL, "{prefix}&6+{money} Koin (Pembunuhan Biasa).")

        /* Lobby Command Items */
        this.addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "stats"), "&eStatistik")
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fKlik Kanan untuk melihat statistik!")
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "arena-selector"),
            "&ePemilih Arena"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "arena-selector"),
            listOf("&fKlik Kanan untuk memilih Arena!")
        )
        this.addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "leave"), "&eKembali ke Pusat")
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fKlik Kanan untuk keluar dari Bed Wars!")
        )
        /* Pre Game Command Items */
        this.addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "stats"), "&eStatistik")
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fKlik Kanan untuk melihat statistik!")
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "leave"),
            "&eKembali ke Lobi"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fKlik kanan untuk keluar Arena!")
        )
        /* Spectator Command Items */
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "teleporter"),
            "&eTeleporter"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "leave"),
            "&eKembali ke Lobi"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fKlik kanan untuk keluar Arena!")
        )

        /* save default items messages for stats gui */
        this.addDefault(Messages.PLAYER_STATS_GUI_INV_NAME, "&8Statistik {player}")
        addDefaultStatsMsg("wins", "&6Kememenangan", "&f{wins}")
        addDefaultStatsMsg("losses", "&6Kekalahan", "&f{losses}")
        addDefaultStatsMsg("kills", "&6Membunuh", "&f{kills}")
        addDefaultStatsMsg("deaths", "&6Mati", "&f{deaths}")
        addDefaultStatsMsg("final-kills", "&6Pembunuhan Terakhir", "&f{finalKills}")
        addDefaultStatsMsg("final-deaths", "&6Kematian Terakhir", "&f{finalDeaths}")
        addDefaultStatsMsg("beds-destroyed", "&6Menghancurkan Kasur", "&f{bedsDestroyed}")
        addDefaultStatsMsg("first-play", "&6Pertama Kali Bermain", "&f{firstPlay}")
        addDefaultStatsMsg("last-play", "&6Terakhir Kali Bermain", "&f{lastPlay}")
        addDefaultStatsMsg("games-played", "&6Pertandingan Dimainkan", "&f{gamesPlayed}")

        // Start of Sidebar
        this.addDefault(
            Messages.SCOREBOARD_LOBBY, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&fLevel Anda: {level}",
                "",
                "&fProgres: &a{currentXp}&7/&b{requiredXp}",
                "{progress}",
                "",
                "&7{player}",
                "",
                "&fKoin: &a{money}",
                "",
                "&fTotal Kemenangan: &a{wins}",
                "&fTotal Membunuh: &a{kills}",
                "",
                "&e{serverIp}"
            )
        )
        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fMap: &a{map}",
                "",
                "&fPlayers: &a{on}/{max}",
                "",
                "&fWaiting,&fWaiting.,&fWaiting..,&fWaiting...",
                "",
                "&fMode: &a{group}",
                "&fVersion: &7{version}",
                "", "&e{serverIp}"
            )
        )
        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Spectating",
                "&fMap: &a{map}",
                "",
                "&fPlayers: &a{on}/{max}",
                "",
                "&fWaiting,&fWaiting.,&fWaiting..,&fWaiting...",
                "",
                "&fMode: &a{group}",
                "&fVersion: &7{version}",
                "", "&e{serverIp}"
            )
        )
        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fMap: &a{map}",
                "",
                "&fPlayers: &a{on}/{max}",
                "",
                "&fStarting in &a{time}s",
                "",
                "&fMode: &a{group}",
                "&fVersion: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Spectating",
                "&fMap: &a{map}",
                "",
                "&fPlayers: &a{on}/{max}",
                "",
                "&fStarting in &a{time}s",
                "",
                "&fMode: &a{group}",
                "&fVersion: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        this.addDefault(
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

        this.addDefault(
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
        this.addDefault(
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
        this.addDefault(
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

        this.addDefault(
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

        this.addDefault(
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

        this.addDefault(
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

        this.addDefault(
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

        this.addDefault(
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
        this.addDefault(
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

        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING.replaceFirst("Default".toRegex(), "3v3v3v3"), listOf(
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
                "&fKills: &a{kills}",
                "&fFinal Kills: &a{finalKills}",
                "&fBeds Broken: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "3v3v3v3"),
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
                "",
                "&e{serverIp}"
            )
        )
        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED.replaceFirst("Default".toRegex(), "3v3v3v3"),
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
                "&fKills: &a{kills}",
                "&fFinal Kills: &a{finalKills}",
                "&fBeds Broken: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        this.addDefault(
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
                "&fKills: &a{kills}",
                "&fPembunuhan Terakhir: &a{finalKills}",
                "&fBed Dihancurkan: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        this.addDefault(
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
        this.addDefault(
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
                "&fKills: &a{kills}",
                "&fPembunuhan Terakhir: &a{finalKills}",
                "&fBed Dihancurkan: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        // End of Sidebar

        // start of TAB
        // main lobby tab format
        this.addDefault(
            Messages.FORMATTING_SB_TAB_LOBBY_HEADER, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                ""
            )
        )
        this.addDefault(
            Messages.FORMATTING_SB_TAB_LOBBY_FOOTER, listOf(
                "",
                "&fThere are {on} players on this lobby",
                "Powered by {poweredBy},&a{serverIp}",
                ""
            )
        )
        this.addDefault(Messages.FORMATTING_SB_TAB_LOBBY_PREFIX, listOf("{vPrefix}"))
        this.addDefault(Messages.FORMATTING_SB_TAB_LOBBY_SUFFIX, listOf(" {level}"))
        // player waiting lobby
        this.addDefault(
            Messages.FORMATTING_SB_TAB_WAITING_HEADER, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                ""
            )
        )
        this.addDefault(
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
        this.addDefault(Messages.FORMATTING_SB_TAB_WAITING_PREFIX, listOf("{vPrefix}"))
        this.addDefault(Messages.FORMATTING_SB_TAB_WAITING_SUFFIX, listOf(" {level}"))
        // spectator waiting lobby
        this.addDefault(
            Messages.FORMATTING_SB_TAB_WAITING_HEADER_SPEC, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                ""
            )
        )
        this.addDefault(
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
        this.addDefault(Messages.FORMATTING_SB_TAB_WAITING_PREFIX_SPEC, listOf("{vPrefix}"))
        this.addDefault(Messages.FORMATTING_SB_TAB_WAITING_SUFFIX_SPEC, listOf(" {level}"))
        // player starting lobby
        this.addDefault(
            Messages.FORMATTING_SB_TAB_STARTING_HEADER, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                ""
            )
        )
        this.addDefault(
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
        this.addDefault(Messages.FORMATTING_SB_TAB_STARTING_PREFIX, listOf("{vPrefix} "))
        this.addDefault(Messages.FORMATTING_SB_TAB_STARTING_SUFFIX, listOf(" {level}"))
        // spectator starting lobby
        this.addDefault(
            Messages.FORMATTING_SB_TAB_STARTING_HEADER_SPEC, listOf(
                "                                                                                                        ",
                "&a{serverIp}",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                ""
            )
        )
        this.addDefault(
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
        this.addDefault(Messages.FORMATTING_SB_TAB_STARTING_PREFIX_SPEC, listOf("{vPrefix} "))
        this.addDefault(Messages.FORMATTING_SB_TAB_STARTING_SUFFIX_SPEC, listOf(" {level}"))
        // player playing
        this.addDefault(
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
        this.addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_FOOTER, listOf(
                "",
                "&fYou are playing on the {teamColor}{teamName} Team",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        this.addDefault(Messages.FORMATTING_SB_TAB_PLAYING_PREFIX, listOf("{teamColor}{teamName} "))
        this.addDefault(Messages.FORMATTING_SB_TAB_PLAYING_SUFFIX, listOf(" {vPrefix}", " {level}"))
        // player eliminated - playing state
        this.addDefault(
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
        this.addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_ELM_FOOTER, listOf(
                "",
                "&fYou have played in the {teamColor}{teamName} Team",
                "&a{serverIp}",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        this.addDefault(Messages.FORMATTING_SB_TAB_PLAYING_ELM_PREFIX, listOf("&f&oSpectator "))
        this.addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_ELM_SUFFIX,
            listOf(
                " &c&oEliminated {teamColor}&o{teamName}",
                " {teamColor}&oEliminated {vPrefix}",
                "{teamColor}&oEliminated {level}"
            )
        )
        // spectator - playing state
        this.addDefault(
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
        this.addDefault(
            Messages.FORMATTING_SB_TAB_PLAYING_SPEC_FOOTER, listOf(
                "",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        this.addDefault(Messages.FORMATTING_SB_TAB_PLAYING_SPEC_PREFIX, listOf("&f&oSpectator "))
        this.addDefault(Messages.FORMATTING_SB_TAB_PLAYING_SPEC_SUFFIX, listOf(" {vPrefix}", " {level}"))
        // winner alive - restarting state
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_HEADER, listOf(
                "                                                                                                        ",
                "&6⭐ {winnerTeamColor}&lYour team won the game! &6⭐",
                "&7{date}", "&7Map: &f{map} &7Mode: &f{group}",
                ""
            )
        )
        this.addDefault(
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
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_PREFIX,
            listOf("&6&l⭐ {teamColor}{teamName} ")
        )
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN1_SUFFIX,
            listOf(" {vPrefix}", " {level}")
        )
        // winner dead - restarting state
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_HEADER, listOf(
                "                                                                                                        ",
                "&6⭐ {winnerTeamColor}&l{winnerTeamName} Team won the game! &6⭐",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                ""
            )
        )
        this.addDefault(
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
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_PREFIX,
            listOf("&6&l⭐ {teamColor}{teamName} ")
        )
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_WIN2_SUFFIX,
            listOf(" {vPrefix}", " &c&oEliminated", " {level}", " &c&oEliminated")
        )
        // loser - restarting state
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_ELM_HEADER, listOf(
                "                                                                                                        ",
                "&6⭐ {winnerTeamColor}&l{winnerTeamName} Team won the game! &6⭐",
                "&7{date}",
                "&7Map: &f{map} &7Mode: &f{group}",
                ""
            )
        )
        this.addDefault(
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
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_ELM_PREFIX,
            listOf("{teamColor}{teamName} ")
        )
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_ELM_SUFFIX,
            listOf(" {vPrefix}", " &c&oEliminated", " {level}", " &c&oEliminated")
        )
        // spectator - restarting state
        this.addDefault(
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
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_FOOTER, listOf(
                "",
                "&fPowered by {poweredBy}",
                ""
            )
        )
        this.addDefault(Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_PREFIX, listOf("&f&oSpectator "))
        this.addDefault(
            Messages.FORMATTING_SB_TAB_RESTARTING_SPEC_SUFFIX,
            listOf(" {vPrefix}", " {level}")
        )

        // end of tab

        //
        this.addDefault(Messages.SHOP_INDEX_NAME, "&8Quick Buy")
        this.addDefault(Messages.SHOP_QUICK_ADD_NAME, "&8Adding to Quick Buy...")
        this.addDefault(
            Messages.SHOP_INSUFFICIENT_MONEY,
            "{prefix}&cKamu tidak mempunyai {currency} yang cukup! Butuh {amount} lagi!"
        )
        this.addDefault(Messages.SHOP_NEW_PURCHASE, "{prefix}&aKamu membeli &6{item}")
        this.addDefault(Messages.SHOP_ALREADY_BOUGHT, "{prefix}&cKamu telah membeli itu!!")
        this.addDefault(Messages.SHOP_UTILITY_NPC_SILVERFISH_NAME, "{TeamColor}&l{TeamName} &r{TeamColor}Silverfish")
        this.addDefault(Messages.SHOP_UTILITY_NPC_IRON_GOLEM_NAME, "{TeamColor}{despawn}s &8[ {TeamColor}{health}&8]")
        this.addDefault(Messages.SHOP_SEPARATOR_NAME, "&8⇧ Categories")
        this.addDefault(Messages.SHOP_SEPARATOR_LORE, listOf("&8⇩ Items"))
        this.addDefault(Messages.SHOP_QUICK_BUY_NAME, "&bQuick Buy")
        this.addDefault(Messages.SHOP_QUICK_BUY_LORE, ArrayList<Any?>())
        this.addDefault(Messages.SHOP_QUICK_EMPTY_NAME, "&cEmpty slot!")
        this.addDefault(
            Messages.SHOP_QUICK_EMPTY_LORE,
            listOf(
                "&7This is a Quick Buy Slot!",
                "&bSneak Click &7any item in",
                "&7the shop to add it here."
            )
        )
        this.addDefault(Messages.SHOP_CAN_BUY_COLOR, "&a")
        this.addDefault(Messages.SHOP_CANT_BUY_COLOR, "&c")
        this.addDefault(Messages.SHOP_LORE_STATUS_CAN_BUY, "&eKlik untuk membeli!")
        this.addDefault(Messages.SHOP_LORE_STATUS_CANT_AFFORD, "&cKamu tidak mempunyai {currency} yang cukup!")
        this.addDefault(Messages.SHOP_LORE_STATUS_MAXED, "&aMAKSIMAL!")
        this.addDefault(Messages.SHOP_LORE_QUICK_ADD, "&bJongkok Klik untuk menambahkan ke Beli Cepat!")
        this.addDefault(Messages.SHOP_LORE_QUICK_REMOVE, "&bJongkok Klik untuk menhapus dari Beli Cepat!")


        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "&8Blok",
            "&aBlok",
            listOf("&eKlik untuk melihat!")
        )

        addContentMessages(
            this, "wol", ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "{color}Wol", listOf(
                "&7Biaya: &f{cost} {currency}", "", "&7Bagus untuk menyebrang", "&7pulau. Berubah menjadi tim Anda",
                "&7color.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "clay",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Hardened Clay",
            listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Blok dasar untuk mempertahankan kasur Anda",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "glass",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Kaca Anti Ledakan",
            listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Kebal terhadap ledakan",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "stone",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}End Stone",
            listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Blok keras untuk mempertahankan kasur Anda",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "ladder",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Tangga",
            listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Berguna untuk menyelamatkan kucing yang terjebak",
                "&7di pohon.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "obsidian",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Obsidian",
            listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Perlindungan ekstrim untuk kasur Anda",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "wood",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Kayu",
            listOf(
                "&7Biaya: {cos} {currency}",
                "",
                "&7Blok keras untuk mempertahankan kasur Anda",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "&8Senjata Jarak Dekat",
            "&aSenjata Jarak Dekat",
            listOf("&eKlik untuk melihat!")
        )

        addContentMessages(
            this,
            "stone-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Stone Sword",
            listOf("&7Biaya: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "iron-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Iron Sword",
            listOf("&7Biaya: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "diamond-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Diamond Sword",
            listOf("&7Biaya: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "stick",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Stik (Knockback I)",
            listOf("&7Biaya: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "&8Armor",
            "&aArmor",
            listOf("&eClick to view!")
        )

        addContentMessages(
            this,
            "chainmail",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Armor Chainmail Permanenr",
            listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Celana dan sepatu chainmail yang dapat membuat",
                "&7kamu akan selalu muncul dengan ini",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "iron-armor",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Armor Besi Permanen",
            listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Celana dan sepatu besi yang dapat membuat",
                "&7kamu akan selalu muncul dengan ini.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "diamond-armor",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Armor Berlian Permanen",
            listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Celana dan sepatu berlian yang dapat membuat",
                "&7kamu akan selalu naksir dengan ini.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_TOOLS,
            "&8Tools",
            "&aTools",
            listOf("&eKlik untuk melihat!")
        )

        addContentMessages(
            this, "shears", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Gunting Permanen", listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Bagus untuk menyingkirkan wol. Kamu",
                "&7akan selalu muncul dengan gunting ini.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "pickaxe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Pickaxe {tier}", listOf(
                "&7Biaya: {cost} {currency}",
                "&7Tingkat: &e{tier}",
                "",
                "&7Ini adalah item yang dapat diupgrade.",
                "&7Itu akan kehilangan 1 tingkat setelah.",
                "&7mati!",
                "",
                "&7Anda akan secara permanen",
                "&7respawn dengan setidaknya",
                "&7tingkat terendah.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "axe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Kapak {tier}", listOf(
                "&7Biaya: {cost} {currency}",
                "&7Tingkat: &e{tier}",
                "",
                "&7Ini adalah item yang dapat diupgrade.",
                "&7Itu akan kehilangan 1 tingkat setelah.",
                "&7mati!",
                "",
                "&7Anda akan secara permanen",
                "&7respawn dengan setidaknya",
                "&7tingkat terendah.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "&8Senjata Jarak Jauh",
            "&aSenjata Jarak Jauh",
            listOf("&eKlik untuk melihat!")
        )

        addContentMessages(
            this,
            "arrow",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Anak Panah",
            listOf("&7Biaya: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow1",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Panah",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow2",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Panah (Power I)",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow3",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Panah (Power I, Punch I)",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "&8Ramuan",
            "&aRamuan",
            listOf("&eKlik untuk melihat!")
        )

        addContentMessages(
            this,
            "speed-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Ramuan Kecepatan II (45 detik)",
            listOf("&7Biaya: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "jump-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Ramuan Lompat V (45 detik)",
            listOf("&7Biaya: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "invisibility",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Ramuan Tak Kasat Mata (30 detik)",
            listOf("&7Biaya: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "&8Utilitas",
            "&aUtilitas",
            listOf("&eKlik untuk melihat!")
        )

        addContentMessages(
            this,
            "golden-apple",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Apel Emas",
            listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Penyembuhan menyeluruh.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "bedbug", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Kutu Kasur", listOf(
                "&7Biaya: {cost} {currency}",
                "",
                "&7Memunculkan ikan gabus di mana",
                "&7bola salju mendarat untuk mengalihkan perhatian",
                "&7musuh. Berlangsung 15 detik.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "dream-defender",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Pertahanan Impian",
            listOf(
                "&7Biaya: {cost} {currency}", "", "&7Iron Golem untuk membantu mempertahankan",
                "&7base. Berlangsung 4 menit.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "fireball", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Bola Api", listOf(
                "&7Biaya: {cost} {currency}", "", "&7Klik kanan untuk meluncurkan! Bagus untuk",
                "&7memukul mundur musuh yang berjalan", "&7di jembatan tipis", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "tnt", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}TNT", listOf(
                "&7Biaya: {cost} {currency}", "", "&7Langsung menyala, cocok",
                "&7untuk meledakkan sesuatu!", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "ender-pearl", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Ender Pearl", listOf(
                "&7Biaya: {cost} {currency}", "", "&7Cara tercepat untuk menyerang base",
                "&7musuh.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "water-bucket", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Water Bucket", listOf(
                "&7Biaya: {cost} {currency}", "", "&7Bagus untuk memperlambat musuh.",
                "&7Bisa juga melindungi", "&7dari serangan TNT.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "bridge-egg", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Telur Jembatan", listOf(
                "&7Biaya: {cost} {currency}", "", "&7Telur ini menciptakan jembatan di",
                "&7percobaan setelah dilempar.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "magic-milk", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Susu Ajaib", listOf(
                "&7Biaya: {cost} {currency}", "", "&7Hindari memicu jebakan untuk 60",
                "&7detik setelah diminum.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "sponge", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Spons", listOf(
                "&7Biaya: {cost} {currency}", "", "&7Bagus untuk menyerap air.",
                "", "{quick_buy}", "{buy_status}"
            )
        )

        //
        this.addDefault(Messages.MEANING_NO_TRAP, "Tidak ada Perangkap!")
        this.addDefault(Messages.FORMAT_SPECTATOR_TARGET, "{targetTeamColor}{targetDisplayName}")
        this.addDefault(Messages.FORMAT_UPGRADE_TRAP_COST, "&7Biaya: {currencyColor}{cost} {currency}")
        this.addDefault(Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD, "&e")
        this.addDefault(Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD, "&c")
        this.addDefault(Messages.FORMAT_UPGRADE_COLOR_UNLOCKED, "&a")
        this.addDefault(Messages.FORMAT_UPGRADE_TIER_LOCKED, "&7")
        this.addDefault(Messages.FORMAT_UPGRADE_TIER_UNLOCKED, "&a")
        this.addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "{color}Klik untuk membeli!")
        this.addDefault(
            Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY,
            "{color}Anda tidak punya {currency} yang cukup!"
        )
        this.addDefault(Messages.UPGRADES_LORE_REPLACEMENT_LOCKED, "&cTERKUNCI")
        this.addDefault(Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED, "{color}TERBUKA")
        this.addDefault(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT, "&a{player} membeli &6{upgradeName}")
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-1"),
            "{color}Iron Forge"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "forge"),
            listOf(
                "&7Tingkatkan pemijahan sumber daya aktif",
                "&7di pulau mu.",
                "",
                "{tier_1_color}Tingkat 1: +50% Sumber Daya, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tingkat 2: +100% Sumber Daya, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Tingkat 3: Spawn zamrud, &b{tier_3_cost} B{tier_3_currency}",
                "{tier_4_color}Tingkat 4: +200% Sumber Daya, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-2"),
            "{color}Golden Forge"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-3"),
            "{color}Emerald Forge"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-4"),
            "{color}Molten Forge"
        )
        this.addDefault(Messages.UPGRADES_CATEGORY_ITEM_NAME_PATH + "traps", "&aBeli perangkap")
        this.addDefault(
            Messages.UPGRADES_CATEGORY_ITEM_LORE_PATH + "traps",
            listOf(
                "&7Perangkap yang dibeli akan menjadi",
                "&7antri di sebelah kanan.",
                "",
                "&eKlik untuk menelusuri!"
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "swords").replace("{tier}", "tier-1"),
            "{color}Pedang yang Diasah"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "swords"),
            listOf(
                "&7Tim Anda memperoleh keuntungan secara permanen",
                "&7Ketajaman I pada semua pedang dan",
                "&7kapak!",
                "",
                "{tier_1_color}Biaya: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-1"),
            "{color}Armor Bertulang I"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "armor"),
            listOf(
                "&7Tim Anda memperoleh keuntungan secara permanen",
                "&7Perlindungan pada semua bagian armor!",
                "",
                "{tier_1_color}Tingkat 1: Perlindungan I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tingkat 2: Perlindungan II, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Tingkat 3: Perlindungan III, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Tingkat 4: Perlindungan IV, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-2"),
            "{color}Armor Bertulang II"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-3"),
            "{color}Armor Bertulang III"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-4"),
            "{color}Armor Bertulang IV"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-1"),
            "{color}Penambang Maniak I"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "miner"),
            listOf(
                "&7Semua pemain di tim Anda",
                "&7mendapatkan Haste secara permanen.",
                "",
                "{tier_1_color}Tingkat 1: Haste I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tingkat 2: Haste II, &b{tier_2_cost} {tier_2_currency}",
                ""
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-2"),
            "{color}Penambang Maniak II"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "heal-pool").replace("{tier}", "tier-1"),
            "{color}Kolam Penyembuhan"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "heal-pool"),
            listOf(
                "&7Membuat bidang Regenerasi",
                "&7di sekitar base Anda!",
                "",
                "{tier_1_color}Biaya: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "dragon").replace("{tier}", "tier-1"),
            "{color}Dragon Buff"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "dragon"),
            listOf(
                "&7Tim Anda akan memiliki 2 naga",
                "&7bukannya 1 selama deathmatch!",
                "",
                "{tier_1_color}Biaya: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        this.addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "glass", "&8⬆&7Dapat Dibeli")
        this.addDefault(
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "glass",
            listOf("&8⬇&7Antrian Perangkap")
        )
        this.addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "first", "{color}Perangkap #1: {name}")
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "first",
            listOf("&7Musuh pertama yang berjalan", "&7ke base Anda akan memicu", "&7perangkap ini!")
        )
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "first",
            listOf(
                "",
                "&7Membeli perangkap akan",
                "&7antri disini. biayanya",
                "&7akan skala berdasarkan",
                "&7jumlah jebakan yang diantrekan.",
                "",
                "&7Perangkap Selanjutnya: &b{cost} {currency}"
            )
        )
        this.addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "second", "{color}Perangkap #2: {name}")
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "second",
            listOf("&7Musuh kedua yang berjalan", "&7ke base Anda akan memicu", "&7perangkap ini!")
        )
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "second",
            listOf(
                "",
                "&7Membeli perangkap akan",
                "&7antri disini. biayanya",
                "&7akan skala berdasarkan",
                "&7jumlah jebakan yang diantrekan.",
                "",
                "&7Perangkap Selanjutnya: &b{cost} {currency}"
            )
        )
        this.addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "third", "{color}Perangkap #3: {name}")
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "third",
            listOf("&7Musuh ketiga yang berjalan", "&7ke base Anda akan memicu", "&7perangkap ini!")
        )
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "third",
            listOf(
                "",
                "&7Membeli perangkap akan",
                "&7antri disini. biayanya",
                "&7akan skala berdasarkan",
                "&7jumlah jebakan yang diantrekan.",
                "",
                "&7Perangkap Selanjutnya: &b{cost} {currency}"
            )
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "1", "{color}It's a trap!")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "1",
            listOf("&7Menimbulkan Kebutaan dan Kelambatan", "&7selama 5 detik.", "")
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "2", "{color}Counter-Offensive Trap")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "2",
            listOf(
                "&7Memberikan Kecepatan I selama 15 detik untuk",
                "&7pemain sekutu di dekat base Anda.",
                ""
            )
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "3", "{color}Alarm Trap")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "3",
            listOf("&7Mengungkapkan pemain tak terlihat serta", "&7nama dan tim mereka.", "")
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "4", "{color}Perangkap Kelelahan Penambang")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "4",
            listOf("&7Menimbulkan Kelelahan Penambangan selama 10", "&7detik.", "")
        )
        this.addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "back", "&aKembali")
        this.addDefault(
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "back",
            listOf("&7Ke Upgrades & Perangkap")
        )
        this.addDefault(Messages.UPGRADES_CATEGORY_GUI_NAME_PATH + "traps", "&8Antrian Jebakan")
        this.addDefault(Messages.UPGRADES_TRAP_QUEUE_LIMIT, "&cAntrian jebakan penuh!")
        this.addDefault(Messages.UPGRADES_TRAP_DEFAULT_MSG, "&c&l{trap} telah berangkat!")
        this.addDefault(Messages.UPGRADES_TRAP_DEFAULT_TITLE, "&cPERANGKAP TERPICU!")
        this.addDefault(Messages.UPGRADES_TRAP_DEFAULT_SUBTITLE, "&fPerangkap {trap} telah terpicu!")
        this.addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_MSG + "3",
            "&c&lPerangkap alarm telah dipicu oleh &7&l{player} &c&ldari tim {color}&l{team}!"
        )
        this.addDefault(Messages.UPGRADES_TRAP_CUSTOM_TITLE + "3", "&c&lALARM!!!")
        this.addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + "3",
            "&fPerangkap alarm telah dipicu oleh tim {color}{team}&f!"
        )
        save()
        setPrefix(m(Messages.PREFIX))
    }
}
