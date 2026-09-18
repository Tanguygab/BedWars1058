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

class Turkish : Language(BedWars.INSTANCE, "tr", "Türkçe") {
    init {
        val bw = BedWars.MAIN_COMMAND

        options().setHeader(listOf("Turkish translation by https://kuzeeeyk.me [kuzeeeyk#7268 or @kuzeeeyk]"))

        addDefault(
            Messages.COMMAND_MAIN,
            listOf(
                "",
                "&2▪ &7/$bw stats",
                "&2▪ &7/$bw join &o<harita/grup>",
                "&2▪ &7/$bw leave",
                "&2▪ &7/$bw lang",
                "&2▪ &7/$bw gui",
                "&2▪ &7/$bw start &3(vip)"
            )
        )
        addDefault(Messages.COMMAND_LANG_LIST_HEADER, "{prefix} &2Mevcut diller:")
        addDefault(Messages.COMMAND_LANG_LIST_FORMAT, "&a▪  &7{iso} - &f{name}")
        addDefault(Messages.COMMAND_LANG_USAGE, "{prefix}&7Kullanım: /lang &f&o<iso>")
        addDefault(Messages.COMMAND_LANG_SELECTED_NOT_EXIST, "{prefix}&cBu dil bulunmuyor!")
        addDefault(Messages.COMMAND_LANG_SELECTED_SUCCESSFULLY, "{prefix}&aDil değiştirildi!")
        addDefault(Messages.COMMAND_LANG_USAGE_DENIED, "{prefix}&cOyun sırasında dili değiştiremezsin.")
        addDefault(Messages.COMMAND_JOIN_USAGE, "§a▪ §7Kullanım: /$bw join §o<arena/group>")
        addDefault(
            Messages.COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND,
            "{prefix}&c{name} adında bir grup veya harita bulunamadı!"
        )
        addDefault(Messages.COMMAND_JOIN_DENIED_IS_FULL, "{prefix}&cBu harita dolu!")
        addDefault(Messages.COMMAND_JOIN_NO_EMPTY_FOUND, "{prefix}&cŞu anda hiç boş harita bulunamadı ;(")
        addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS,
            "{prefix}&cÜzgünüz fakat bu harita dolu.\n&cBağışçı olduğunu biliyoruz ama bu harita diğer bağışçı ya da yetkililer ile dolu."
        )
        addDefault(
            Messages.COMMAND_JOIN_DENIED_PARTY_TOO_BIG,
            "{prefix}&cPartin bu harita bir takım olarak katılabilmek için çok büyük!"
        )
        addDefault(Messages.COMMAND_JOIN_DENIED_NOT_PARTY_LEADER, "{prefix}&cSadece parti lideri harita seçebilir.")
        addDefault(Messages.COMMAND_JOIN_PLAYER_JOIN_MSG, "{prefix}&7{player} &ekatıldı (&b{on}&e/&b{max}&e)!")
        addDefault(
            Messages.COMMAND_JOIN_SPECTATOR_MSG,
            "{prefix}§6Şu anda §9{arena}§6 haritasını seyrediyorsun.\n{prefix}§eHaritadan ayrılmak için §c/leave§e komudunu kullanabilirsin."
        )
        addDefault(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG, "&cBu harita seyircilere kapalı!")
        addDefault(Messages.COMMAND_TP_PLAYER_NOT_FOUND, "{prefix}&cOyuncu bulunamadı!")
        addDefault(Messages.COMMAND_TP_NOT_IN_ARENA, "{prefix}&cBu oyuncu bir haritada değil!")
        addDefault(Messages.COMMAND_TP_NOT_STARTED, "{prefix}&cOyuncunun bulunduğu haritada oyun henüz başlamadı!")
        addDefault(Messages.COMMAND_TP_USAGE, "{prefix}&cKullanım: /bw tp <username>")
        addDefault(Messages.REJOIN_NO_ARENA, "{prefix}&cYeniden katılabilmen için bir harita bulunmuyor.")
        addDefault(
            Messages.REJOIN_DENIED,
            "{prefix}&cYeniden katılımazsın çünkü yataklar kırılmış ya da oyun bitmiş."
        )
        addDefault(Messages.REJOIN_ALLOWED, "{prefix}&a{arena}&e haritasına yeniden katılıyorsun!")
        addDefault(Messages.COMMAND_REJOIN_PLAYER_RECONNECTED, "{prefix}&7{player} &eyeniden katıldı!")
        addDefault(Messages.COMMAND_LEAVE_DENIED_NOT_IN_ARENA, "{prefix}&cHaritada değilsin!")
        addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&7{player} &eayrıldı!")
        addDefault(Messages.COMMAND_NOT_ALLOWED_IN_GAME, "{prefix}&cBu komutu oyun sırasında kullanamazsın!")
        addDefault(Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS, "{prefix}&cBu komut bulunamadı veya yetkin yok!")
        addDefault(
            Messages.COMMAND_PARTY_HELP, listOf(
                "&6▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&aParti Komutları:",
                "&e/party help &7- &bBu mesajı yazdırır",
                "&e/party invite <player> &7- &bOyuncuyu partine davet eder",
                "&e/party leave &7- &bPartiden ayrılır",
                "&e/party info &7- &bShow party members and owner",
                "&e/party promote <player> &7- &bTransfer party ownership",
                "&e/party remove <player> &7- &bOyuncuyu partiden kovar",
                "&e/party accept <player> &7- &bParti davetini kabul eder",
                "&e/party disband &7- &bPartiyi dağıtır"
            )
        )
        addDefault(Messages.COMMAND_PARTY_INVITE_USAGE, "{prefix}&eKullanım: &7/party invite <player>")
        addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eoyunda değil!")
        addDefault(
            Messages.COMMAND_PARTY_INVITE_SENT,
            "{prefix}&7{player}&6 adlı oyuncuya parti daveti gönderildi."
        )
        addDefault(
            Messages.COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG,
            "{prefix}&b{player} &eadlı oyuncu seni bir partiye davet etti! &o&7(Kabul Et)"
        )
        addDefault(
            Messages.COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF,
            "{prefix}&cKendini davet edemezsin!"
        )
        addDefault(
            Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE,
            "{prefix}&7{player} &eadlı oyuncu çevrimdışı!"
        )
        addDefault(
            Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE,
            "{prefix}&cKabul etmek için bir davet bulunmuyor!"
        )
        addDefault(Messages.COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY, "{prefix}&eZaten bir partidesin!")
        addDefault(
            Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS,
            "{prefix}&cSadece parti lideri bu komutu kullanabilir."
        )
        addDefault(Messages.COMMAND_PARTY_ACCEPT_USAGE, "{prefix}&eKullanım: &7/party accept <player>")
        addDefault(Messages.COMMAND_PARTY_ACCEPT_SUCCESS, "{prefix}&7{player} &eadlı oyuncu partiye katıldı!")
        addDefault(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY, "{prefix}&cHerhangi bir partide değilsin!")
        addDefault(
            Messages.COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND,
            "{prefix}&cKendi partinden ayrılamazsın!\n&ePartiyi dağıtmayı dene: &b/party disband"
        )
        addDefault(Messages.COMMAND_PARTY_LEAVE_SUCCESS, "{prefix}&7{player} &eadlı oyuncu partiden ayrıldı!")
        addDefault(Messages.COMMAND_PARTY_DISBAND_SUCCESS, "{prefix}&eParti dağıtıldı!")
        addDefault(Messages.COMMAND_PARTY_REMOVE_USAGE, "{prefix}&7Kullanım: &e/party remove <player>")
        addDefault(Messages.COMMAND_PARTY_REMOVE_SUCCESS, "{prefix}&7{player} &eadlı oyuncu partiden kovuldu!")
        addDefault(
            Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER,
            "{prefix}&7{player} &eadlı oyuncu parti üyesi değil!"
        )
        addDefault(Messages.COMMAND_PARTY_PROMOTE_SUCCESS, "{prefix}&e{player}'i sahibi yaptın!")
        addDefault(Messages.COMMAND_PARTY_PROMOTE_OWNER, "{prefix}&eGrup sahibi sen oldun!")
        addDefault(Messages.COMMAND_PARTY_PROMOTE_NEW_OWNER, "{prefix}&7 &e{player} artık grup sahibi!")
        addDefault(Messages.COMMAND_PARTY_INFO_OWNER, "\n{prefix}&eGrup sahibi: &7{owner}")
        addDefault(Messages.COMMAND_PARTY_INFO_PLAYERS, "{prefix}&eGrup üyeleri:")
        addDefault(Messages.COMMAND_PARTY_INFO_PLAYER, "&7{player}")
        addDefault(Messages.COMMAND_FORCESTART_NOT_IN_GAME, "§c▪ §7Oyunda değilsin!")
        addDefault(Messages.COMMAND_FORCESTART_SUCCESS, "§c▪ §7Geris sayım kısaltıldı!")
        addDefault(Messages.COMMAND_FORCESTART_NO_PERM, "{prefix}&7Bu işlemi uygulamak için bağışçı olman lazım!")
        addDefault(Messages.COMMAND_COOLDOWN, "&cŞu anda bunu yapamazsın! {seconds} saniye sonra tekrar dene.")
        addDefault(
            Messages.ARENA_JOIN_VIP_KICK,
            "{prefix}&cÜzgünüm, bir bağışçı haritaya katılmaya çalıştığı için haritadan atıldın!"
        )
        addDefault(
            Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT,
            "{prefix}§cOyun başlamıyor! §7Yeterli oyuncu yok!"
        )
        addDefault(Messages.ARENA_RESTART_PLAYER_KICK, "{prefix}&eBulunduğun harita yeniden başlatılıyor")
        addDefault(Messages.ARENA_STATUS_PLAYING_NAME, "&cOynanıyor")
        addDefault(Messages.ARENA_STATUS_RESTARTING_NAME, "&4Yeniden Başlatılıyor")
        addDefault(Messages.ARENA_STATUS_WAITING_NAME, "&2Bekleniyor §c{full}")
        addDefault(Messages.ARENA_STATUS_STARTING_NAME, "&6Başlatılıyor §c{full}")
        addDefault(Messages.ARENA_GUI_INV_NAME, "&8Katılm")
        addDefault(Messages.ARENA_GUI_ARENA_CONTENT_NAME, "&a&l{name}")
        addDefault(
            Messages.ARENA_GUI_ARENA_CONTENT_LORE,
            listOf(
                "",
                "&7Durum: {status}",
                "&7Oyuncular: &f{on}&7/&f{max}",
                "&7Tür: &a{group}",
                "",
                "&aKatılmak için sol tıkla.",
                "&eİzlemek için sağ tıkla."
            )
        )
        addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_NAME, "&r{serverIp}")
        addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_LORE, emptyList<String>())
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CHAT, "{prefix}&eOyun, &6{time} &esaniye içinde başlıyor!")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_TITLE, " ")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE, "&a{second}")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-5", "&e❺")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-4", "&e❹")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-3", "&c❸")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-2", "&c❷")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-1", "&c❶")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_TITLE, " ")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_SUB_TITLE, "&cDaha fazla oyuncu bekleniyor...")
        addDefault(Messages.ARENA_STATUS_START_PLAYER_TITLE, "&aGO")
        addDefault(
            Messages.ARENA_STATUS_START_PLAYER_TUTORIAL, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &l&cBed&r&lWars", "",
                "&e&l    Yatağını savun ve düşman yataklarını parçala!",
                "&e&l      Üreticilerden Demir, Altın, Zümrüt ve Elmas",
                "&e&l   kazanarak yükseltmelere erişim sağla ve takımını",
                "&e&l             güçlendir. Bol şans!", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        addDefault(
            Messages.ARENA_JOIN_DENIED_SELECTOR,
            "{prefix}&cÜzgünüm ama bu haritaya şu anda katılamazsın. Seyirci olmak için sağ tıkla."
        )
        addDefault(
            Messages.ARENA_SPECTATE_DENIED_SELECTOR,
            "{prefix}&cÜzgünüm ama bu haritayı şu anda seyredemezsin. Katılmak için sol tıkla."
        )
        addDefault(
            Messages.ARENA_JOIN_DENIED_NO_PROXY,
            "&cÜzgünüm ama BedWarsProxy kullanmalısın. \n&eEğer haritayı düzenlemek istiyorsan kendine bw.setup yetkisini vermeyi unutma!"
        )
        addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_NAME, "&8Işınlayıcı")
        addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME, "{prefix}{player}")
        addDefault(
            Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE,
            listOf("&7Can: &f{health}%", "&7Açlık: &f{food}", "", "&7Seyretmek için sol tıkla")
        )
        addDefault(Messages.ARENA_SPECTATOR_LEAVE_ITEM_NAME, "&c&lLobiye dön")
        addDefault(
            Messages.ARENA_SPECTATOR_LEAVE_ITEM_LORE,
            listOf("&7Lobiye dönmek için sol tıkla.")
        )
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE, "&7{player} &aseyrediliyor")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE, "&cAyrılmak için eğilme tuşuna bas.")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE, "&eSeyirci modundan ayrılınıyor...")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE, "")
        addDefault(
            Messages.ARENA_LEAVE_PARTY_DISBANDED,
            "{prefix}§cParti sahibi oyundan ayrıldı ve parti kapatıldı."
        )
        addDefault(Messages.GENERATOR_HOLOGRAM_TIER, "&eSeviye &c{tier}")
        addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND, "&b&lElmas")
        addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD, "&a&lZümrüt")
        addDefault(Messages.GENERATOR_HOLOGRAM_TIMER, "&c{seconds} &esaniye sonra üretiliyor")
        addDefault(
            Messages.GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT,
            "{prefix}{generatorType} Üreticileri &eSeviye &c{tier} &eoldu!"
        )
        addDefault(Messages.FORMATTING_CHAT_LOBBY, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        addDefault(Messages.FORMATTING_CHAT_WAITING, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        addDefault(
            Messages.FORMATTING_CHAT_SHOUT,
            "{level}{vPrefix}&6[SHOUT] {team} &7{player}&f{vSuffix}: {message}"
        )
        addDefault(Messages.FORMATTING_CHAT_TEAM, "{level}{vPrefix}&f{team}&7 {player}{vSuffix} {message}")
        addDefault(Messages.FORMATTING_CHAT_SPECTATOR, "{level}{vPrefix}&7[SPECTATOR] {player}{vSuffix}: {message}")
        addDefault(Messages.FORMATTING_SCOREBOARD_HEALTH, listOf("&c❤", "&aCan"))

        addDefault(Messages.FORMATTING_SCOREBOARD_DATE, "dd/MM/yyyy")
        addDefault(
            Messages.FORMATTING_SCOREBOARD_TEAM_GENERIC,
            "{TeamColor}{TeamLetter}&f {TeamName}: {TeamStatus}"
        )
        addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ELIMINATED, "&c&l✘")
        addDefault(Messages.FORMATTING_SCOREBOARD_BED_DESTROYED, "&a{remainingPlayers}")
        addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ALIVE, "&a&l✓")
        addDefault(Messages.FORMATTING_SCOREBOARD_NEXEVENT_TIMER, "mm:ss")
        addDefault(Messages.FORMATTING_SCOREBOARD_YOUR_TEAM, "&7 SEN")
        addDefault(Messages.FORMATTING_ACTION_BAR_TRACKING, "&fHedef: {team} &f- Mesafe: {distance}m")
        addDefault(Messages.FORMATTING_TEAM_WINNER_FORMAT, "      {TeamColor}{TeamName} &7- {members}")
        addDefault(Messages.FORMATTING_SOLO_WINNER_FORMAT, "                 {TeamColor}{TeamName} &7- {members}")
        addDefault(Messages.FORMATTING_GENERATOR_TIER1, "I")
        addDefault(Messages.FORMATTING_GENERATOR_TIER2, "II")
        addDefault(Messages.FORMATTING_GENERATOR_TIER3, "III")
        addDefault(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH, "▮ ")
        addDefault(Messages.FORMATTING_STATS_DATE_FORMAT, "dd/MM/yyyy HH:mm")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_TEAM, "{TeamColor}[{TeamName}]")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SHOUT, "&6[HERKES]")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SPECTATOR, "&7[SEYIRCI]")
        addDefault(Messages.MEANING_FULL, "Tam")
        addDefault(Messages.MEANING_SHOUT, "Herkes")
        addDefault(Messages.MEANING_NOBODY, "Kimse")
        addDefault(Messages.MEANING_NEVER, "Asla")
        addDefault(Messages.MEANING_IRON_SINGULAR, "Demir")
        addDefault(Messages.MEANING_IRON_PLURAL, "Demir")
        addDefault(Messages.MEANING_GOLD_SINGULAR, "Altın")
        addDefault(Messages.MEANING_GOLD_PLURAL, "Altın")
        addDefault(Messages.MEANING_EMERALD_SINGULAR, "Zümrüt")
        addDefault(Messages.MEANING_EMERALD_PLURAL, "Zümrüt")
        addDefault(Messages.MEANING_DIAMOND_SINGULAR, "Elmas")
        addDefault(Messages.MEANING_DIAMOND_PLURAL, "Elmas")
        addDefault(Messages.MEANING_VAULT_SINGULAR, "₺")
        addDefault(Messages.MEANING_VAULT_PLURAL, "₺")
        addDefault(Messages.INTERACT_CANNOT_PLACE_BLOCK, "{prefix}&cBuraya blok koyamazsın!")
        addDefault(
            Messages.INTERACT_CANNOT_BREAK_BLOCK,
            "{prefix}&cSadece oyuncular tarafından yerleştirilen blokları kırabilirsin!"
        )
        addDefault(Messages.INTERACT_CANNOT_BREAK_OWN_BED, "&cKendi yatağını kıramazsın!")
        addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT,
            "\n&f&lYATAK KIRMA > {TeamColor}{TeamName} Yatak&7, {PlayerColor}{PlayerName}&7 tarafından parçalandı!\n"
        )
        addDefault(Messages.INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT, "&cYATAK KIRILDI")
        addDefault(Messages.INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT, "&fArtık yeniden doğamayacaksın!")
        addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM,
            "&f&lBED DESTRUCTION > &7Yatağın {PlayerColor}{PlayerName}&7 tarafından yok edildi!"
        )
        addDefault(
            Messages.INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED,
            "&cElenmemiş bir takımın sandığını açamazsın!"
        )
        addDefault(
            Messages.INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN,
            "&cYou are no longer invisible because you have taken damage!"
        )
        addDefault(Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL, "{PlayerColor}{PlayerName} &7boşluğa düştü.")
        addDefault(
            Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7boşluğa düştü. &b&lFINAL KILL!"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7 adlı oyuncu {KillerColor}{KillerName}&7 tarafından boşluğa atıldı."
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 tarafından boşluğa atıldı. &b&lFINAL KILL!"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_REGULAR,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 ile savaşırken oyundan ayrıldı."
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_FINAL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 ile savaşırken oyundan ayrıldı. &b&lFINAL KILL!"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 tarafından uçuruldu."
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 tarafından uçuruldu. &b&lFINAL KILL!"
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 tarafından bombalandı."
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 tarafından bombalandı. &b&lFINAL KILL!"
        )
        addDefault(Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR, "{PlayerColor}{PlayerName} &7bombalandı.")
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7bombalandı. &b&lFINAL KILL!"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 tarafından öldürüldü."
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 tarafından öldürüldü. &b&lFINAL KILL!"
        )
        addDefault(Messages.PLAYER_DIE_UNKNOWN_REASON_REGULAR, "{PlayerColor}{PlayerName} &7öldü.")
        addDefault(
            Messages.PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7öldü. &b&lFINAL KILL!"
        )
        addDefault(
            Messages.PLAYER_DIE_SHOOT_REGULAR,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 tarafından ok ile vuruldu."
        )
        addDefault(
            Messages.PLAYER_DIE_SHOOT_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerName}&7 tarafından ok ile vuruldu. &b&lFINAL KILL!"
        )
        addDefault(
            Messages.PLAYER_DIE_DEBUG_REGULAR,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerTeamName} Takım&7'ın Yatak Böceği tarafından katledildi!"
        )
        addDefault(
            Messages.PLAYER_DIE_DEBUG_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerTeamName} Takım&7'ın Yatak Böceği tarafından katledildi! &b&lFINAL KILL!"
        )
        addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_REGULAR,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerTeamName} Takım&7'ın Demir Golemi tarafından katledildi!"
        )
        addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7adlı oyuncu {KillerColor}{KillerTeamName} Takım&7'ın Demir Golemi tarafından katledildi! &b&lFINAL KILL!"
        )
        addDefault(Messages.PLAYER_DIE_REWARD_DIAMOND, "{prefix}&b+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_EMERALD, "{prefix}&a+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_IRON, "{prefix}&f+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_GOLD, "{prefix}&6+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_RESPAWN_TITLE, "&cÖLDÜN!")
        addDefault(Messages.PLAYER_DIE_RESPAWN_SUBTITLE, "&c{time} &esaniye sonra yeniden doğacaksın!")
        addDefault(Messages.PLAYER_DIE_RESPAWN_CHAT, "{prefix}&e{time} &esaniye sonra yeniden doğacaksın!")
        addDefault(Messages.PLAYER_DIE_RESPAWNED_TITLE, "&aYENİDEN DOĞDUN!")
        addDefault(Messages.PLAYER_DIE_ELIMINATED_CHAT, "{prefix}&cOyundan elendin!")
        addDefault(
            Messages.PLAYER_HIT_BOW,
            "{prefix}{TeamColor}{PlayerName} &7 adlı oyuncu &c{amount} &7cana sahip!"
        )
        addDefault(Messages.GAME_END_GAME_OVER_PLAYER_TITLE, "&c&lOYUN BİTTİ")
        addDefault(Messages.GAME_END_VICTORY_PLAYER_TITLE, "&6&lGALİBİYET!")
        addDefault(Messages.GAME_END_TEAM_WON_CHAT, "{prefix}{TeamColor}{TeamName} &aoyunu kazandı!")
        addDefault(
            Messages.GAME_END_TOP_PLAYER_CHAT, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &l&cBed&r&lWars",
                "",
                "{winnerFormat}",
                "",
                "",
                "&6                      &6⭐ &l1. Oyuncu &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue} &7öldürme",
                "&e                        &l2. Oyuncu &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue} &7öldürme",
                "&c                        &l3. Oyuncu &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue} &7öldürme",
                "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        addDefault(Messages.BED_HOLOGRAM_DEFEND, "&c&lYatağını savun!")
        addDefault(Messages.BED_HOLOGRAM_DESTROYED, "&c&lYatağın kırıldı!")
        addDefault(Messages.NPC_NAME_TEAM_UPGRADES, "&bTAKIM YÜKSELTMELERİ,&e&lSAĞ TIKLA")
        addDefault(Messages.NPC_NAME_SOLO_UPGRADES, "&bOYUNCU YÜKSELTMELERİ,&e&lSAĞ TIKLA")
        addDefault(Messages.NPC_NAME_TEAM_SHOP, "&bTAKIM MARKETİ,&e&lSAĞ TIKLA")
        addDefault(Messages.NPC_NAME_SOLO_SHOP, "&bEŞYA MARKETİ,&e&lSAĞ TIKLA")
        addDefault(Messages.TEAM_ELIMINATED_CHAT, "\n&f&lTAKIM ELENDİ > {TeamColor}{TeamName} Takım &celendi!\n")
        addDefault(Messages.NEXT_EVENT_BEDS_DESTROY, "&cYatak Kırma")
        addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_II, "&fElmas II")
        addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_III, "&fElmas III")
        addDefault(Messages.NEXT_EVENT_DRAGON_SPAWN, "&fEjderha Saldırısı")
        addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_II, "&fZümrüt II")
        addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_III, "&fZümrüt III")
        addDefault(Messages.NEXT_EVENT_GAME_END, "&4Oyun Sonu")
        addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED, "&cYATAK KIRMA!")
        addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED, "&fTüm yataklar parçalandı!")
        addDefault(Messages.NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED, "&c&lTüm yataklar parçalandı!")
        addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH, "&cEjderha Saldırısı")
        addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH, "")
        addDefault(
            Messages.NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH,
            "&cEJDERHA SALDIRISI: &6&b{TeamDragons} {TeamColor}{TeamName} Ejderha!"
        )
        addDefault(Messages.XP_REWARD_PER_MINUTE, "{prefix}&6+{xp} BedWars puanı kazanıldı (Oynama Süresi)")
        addDefault(Messages.XP_REWARD_WIN, "{prefix}&6+{xp} BBedWars puanı kazanıldı (Galibiyet)")
        addDefault(Messages.XP_REWARD_PER_TEAMMATE, "{prefix}&6+{xp} BedWars puanı kazanıldı (Takım Yardımı)")
        addDefault(Messages.XP_REWARD_BED_DESTROY, "{prefix}&6+{xp} BedWars puanı kazanıldı (Yatak Kırma)")
        addDefault(Messages.XP_REWARD_REGULAR_KILL, "{prefix}&6+{xp} BedWars puanı kazanıldı (Öldürme)")
        addDefault(Messages.XP_REWARD_FINAL_KILL, "{prefix}&6+{xp} BedWars puanı kazanıldı (Final Kill)")

        addDefault(Messages.MONEY_REWARD_PER_MINUTE, "{prefix}&6+{money} Para (Oynama Süresi)")
        addDefault(Messages.MONEY_REWARD_WIN, "{prefix}&6+{money} Para (Galibiyet)")
        addDefault(Messages.MONEY_REWARD_PER_TEAMMATE, "{prefix}&6+{money} Para (Takım Yardımı)")
        addDefault(Messages.MONEY_REWARD_BED_DESTROYED, "{prefix}&6+{money} Para (Yatak Kırma)")
        addDefault(Messages.MONEY_REWARD_FINAL_KILL, "{prefix}&6+{money} Para (Final Kill)")
        addDefault(Messages.MONEY_REWARD_REGULAR_KILL, "{prefix}&6+{money} Para (Öldürme)")

        /* Lobby Command Items */
        addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "stats"), "&eİstatistikler")
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fİstatistiklerini görmek için sağ tıkla!")
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "arena-selector"),
            "&eHarita Seçici"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "arena-selector"),
            listOf("&fHarita seçmek için sağ tıkla!")
        )
        addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "leave"), "&eLobiye Dön")
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fBedWars'tan ayrılmak için sağ tıkla!")
        )
        /* Pre Game Command Items */
        addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "stats"), "&eİstatistikler")
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fİstatistiklerini görmek için sağ tıkla!")
        )
        addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "leave"), "&eLobiye Dön")
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&Lobiye dönmek için sağ tıkla!")
        )
        /* Spectator Command Items */
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "teleporter"),
            "&eIşınlayıcı"
        )
        addDefault(Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "leave"), "&eLobiye Dön")
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fHaritadan ayrılmak için sağ tıkla!")
        )

        /* save default items messages for stats gui */
        addDefault(Messages.PLAYER_STATS_GUI_INV_NAME, "&8{player} İstatistikleri")
        addDefaultStatsMsg("wins", "&6Galibiyet", "&f{wins}")
        addDefaultStatsMsg("losses", "&6Malubiyet", "&f{losses}")
        addDefaultStatsMsg("kills", "&6Öldürme", "&f{kills}")
        addDefaultStatsMsg("deaths", "&6Ölüm", "&f{deaths}")
        addDefaultStatsMsg("final-kills", "&6Final Öldürme", "&f{finalKills}")
        addDefaultStatsMsg("final-deaths", "&6Final Ölüm", "&f{finalDeaths}")
        addDefaultStatsMsg("beds-destroyed", "&6Yatak Kırma", "&f{bedsDestroyed}")
        addDefaultStatsMsg("first-play", "&6İlk Oyun", "&f{firstPlay}")
        addDefaultStatsMsg("last-play", "&6Son Oyun", "&f{lastPlay}")
        addDefaultStatsMsg("games-played", "&6Oynanan Oyun", "&f{gamesPlayed}")

        // Start of Sidebar
        addDefault(
            Messages.SCOREBOARD_LOBBY, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&fSeviyen: {level}",
                "",
                "&fİlerleme: &a{currentXp}&7/&b{requiredXp}",
                "{progress}",
                "",
                "&7{player}",
                "",
                "&fPara: &a{money}",
                "",
                "&fGalibiyet: &a{wins}",
                "&fToplam Öldürme: &a{kills}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fHarita: &a{map}",
                "",
                "&fOyuncular: &a{on}/{max}",
                "",
                "&fBekleniyor,&fBekleniyor.,&fBekleniyor..,&fBekleniyor...",
                "",
                "&fTür: &a{group}",
                "&fSürüm: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Spectating",
                "&fHarita: &a{map}",
                "",
                "&fOyuncular: &a{on}/{max}",
                "",
                "&fBekleniyor,&fBekleniyor.,&fBekleniyor..,&fBekleniyor...",
                "",
                "&fTür: &a{group}",
                "&fSürüm: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fHarita: &a{map}",
                "",
                "&fOyuncular: &a{on}/{max}",
                "",
                "&f&a{time}s sonra başlatılıyor",
                "",
                "&fTür: &a{group}",
                "&fSürüm: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Spectating",
                "&fHarita: &a{map}",
                "",
                "&fOyuncular: &a{on}/{max}",
                "",
                "&f&a{time}s sonra başlatılıyor",
                "",
                "&fTür: &a{group}",
                "&fSürüm: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "",
                "&a{time} sonra &f{nextEvent}",
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
                "&a{time} sonra &f{nextEvent}",
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
                "&a{time} sonra &f{nextEvent}",
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
                "&a{time} sonra &f{nextEvent}",
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
                "&a{time} sonra &f{nextEvent}",
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
                "&a{time} sonra &f{nextEvent}",
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
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "",
                "&a{time} sonra &f{nextEvent}",
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
                "&fÖldürme: &a{kills}",
                "&fFinal Öldürme: &a{finalKills}",
                "&fYatak Kırma: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "3v3v3v3"),
            listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&a{time} sonra &f{nextEvent}",
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
                "&fÖldürme: &a{kills}",
                "&fFinal Öldürme: &a{finalKills}",
                "&fYatak Kırma: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED.replaceFirst("Default".toRegex(), "3v3v3v3"),
            listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&a{time} sonra &f{nextEvent}",
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
            Messages.SCOREBOARD_DEFAULT_PLAYING.replaceFirst("Default".toRegex(), "4v4v4v4"), listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "",
                "&a{time} sonra &f{nextEvent}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&fÖldürme: &a{kills}",
                "&fFinal Öldürme: &a{finalKills}",
                "&fYatak Kırma: &a{beds}",
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
                "&a{time} sonra &f{nextEvent}",
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
                "&a{time} sonra &f{nextEvent}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&fÖldürme: &a{kills}",
                "&fFinal Öldürme: &a{finalKills}",
                "&fYatak Kırma: &a{beds}",
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

        //
        addDefault(Messages.SHOP_INDEX_NAME, "&8Hızlı Alım")
        addDefault(Messages.SHOP_QUICK_ADD_NAME, "&8Hızlı Alıma ekleniyor...")
        addDefault(
            Messages.SHOP_INSUFFICIENT_MONEY,
            "{prefix}&cYeteri kadar {currency} sahibi değilsin! {amount} tane daha bulmalısın!"
        )
        addDefault(Messages.SHOP_NEW_PURCHASE, "{prefix}&6{item} &asatın aldın")
        addDefault(Messages.SHOP_ALREADY_BOUGHT, "{prefix}&cBunu zaten satın aldın!")
        addDefault(Messages.SHOP_UTILITY_NPC_SILVERFISH_NAME, "{TeamColor}&l{TeamName} &r{TeamColor}Böcek")
        addDefault(Messages.SHOP_UTILITY_NPC_IRON_GOLEM_NAME, "{TeamColor}{despawn}s &8[ {TeamColor}{health}&8]")
        addDefault(Messages.SHOP_SEPARATOR_NAME, "&8⇧ Kategoriler")
        addDefault(Messages.SHOP_SEPARATOR_LORE, listOf("&8⇩ Eşyalar"))
        addDefault(Messages.SHOP_QUICK_BUY_NAME, "&bHızlı Alım")
        addDefault(Messages.SHOP_QUICK_BUY_LORE, ArrayList<Any?>())
        addDefault(Messages.SHOP_QUICK_EMPTY_NAME, "&cBoş slot!")
        addDefault(
            Messages.SHOP_QUICK_EMPTY_LORE,
            listOf(
                "&7Bu bir Hızlı Alım slotu!",
                "&7Buraya eşya eklemek için marketteki",
                "&7bir eşyaya eğilme tuşu ile tıkla!"
            )
        )
        addDefault(Messages.SHOP_CAN_BUY_COLOR, "&a")
        addDefault(Messages.SHOP_CANT_BUY_COLOR, "&c")
        addDefault(Messages.SHOP_LORE_STATUS_CAN_BUY, "&eSatın almak için tıkla!")
        addDefault(Messages.SHOP_LORE_STATUS_CANT_AFFORD, "&cYeteri kadar {currency} sahibi değilsin!")
        addDefault(Messages.SHOP_LORE_STATUS_MAXED, "&aEN YÜKSEK SEVİYEDE!")
        addDefault(Messages.SHOP_LORE_QUICK_ADD, "&bHızlı Alıma eklemek için eğilerek tıkla")
        addDefault(Messages.SHOP_LORE_QUICK_REMOVE, "&bHızlı Alımdan kaldırmak için eğilerek tıkla")


        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "&8Bloklar",
            "&aBloklar",
            listOf("&eGörmek için tıkla!")
        )

        addContentMessages(
            this, "wool", ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "{color}Yün", listOf(
                "&7Ücret: &f{cost} {currency}", "", "&7Düşman adalara yol yapmak", "&7için ideal. Takımının",
                "&7rengine bürünür.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "clay",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Kil",
            listOf(
                "&7Ücret: {cost} {currency}",
                "",
                "&7Yatağını savunmak için harika.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "glass",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Cam",
            listOf(
                "&7Ücret: {cost} {currency}",
                "",
                "&7Patlamaları etkisiz kılar.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "stone",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}End Taşı",
            listOf(
                "&7Ücret: {cost} {currency}",
                "",
                "&7Sert bir savunma.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "ladder",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Merdiven",
            listOf(
                "&7Ücret: {cost} {currency}",
                "",
                "&7Ağaçlarda mahsur kalan",
                "&7kedileri kurtarmak için süper",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "obsidian",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Obsidyen",
            listOf(
                "&7Ücret: {cost} {currency}",
                "",
                "&7Rakiplerin senden nefret edecek!",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "wood",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Tahta",
            listOf(
                "&7Ücret: {cost} {currency}",
                "",
                "&7Yatak savunması için ucuz bir çözüm.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "&8Yakın Saldırı",
            "&aYakın Saldırı",
            listOf("&eGörmek için tıkla!")
        )

        addContentMessages(
            this,
            "stone-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Taş Kılıç",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "iron-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Demir Kılıç",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "diamond-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Elmas Kılıç",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "stick",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Çubuk (Savurma I)",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "&8Zırh",
            "&8Zırh",
            listOf("&eGörmek için tıkla!")
        )

        addContentMessages(
            this, "chainmail", ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "{color}Zincir Zırh", listOf(
                "&7Ücret: {cost} {currency}",
                "", "&7Ölünce kaybolmayan", "&7zincir pantolon ve ayakkabı", "", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "iron-armor", ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "{color}Demir Zırh", listOf(
                "&7Ücret: {cost} {currency}",
                "", "&7Ölünce kaybolmayan", "&7demir pantolon ve ayakkabı", "", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "diamond-armor", ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "{color}Elmas Zırh", listOf(
                "&7Ücret: {cost} {currency}",
                "", "&7Ölünce kaybolmayan", "&7elmas pantolon ve ayakkabı", "", "", "{quick_buy}", "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_TOOLS,
            "&8Alet",
            "&8Alet",
            listOf("&eGörmek için tıkla!")
        )

        addContentMessages(
            this, "shears", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Makas", listOf(
                "&7Ücret: {cost} {currency}",
                "",
                "&7Ölünce kaybolmayan bir makas. Yünlerden",
                "&7kurtulmak için dört dörtlük.",
                "",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "pickaxe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Kazma {tier}", listOf(
                "&7Ücret: {cost} {currency}",
                "&7Seviye: &e{tier}",
                "",
                "&7Bu bir yükseltilebilir eşya.",
                "&7Her öldüğünde",
                "1 seviye düşer",
                "",
                "&7Bu eşya, ölsen bile",
                "&7en düşük seviyesi ile",
                "&7üstünde kalır.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "axe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Balta {tier}", listOf(
                "&7Ücret: {cost} {currency}",
                "&7Seviye: &e{tier}",
                "",
                "&7Bu bir yükseltilebilir eşya.",
                "&7Her öldüğünde",
                "1 seviye düşer",
                "",
                "&7Bu eşya, ölsen bile",
                "&7en düşük seviyesi ile",
                "&7üstünde kalır.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "&8Uzun Mesafe",
            "&aUzun Mesafe",
            listOf("&eGörmek için tıkla!")
        )

        addContentMessages(
            this,
            "arrow",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Ok",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow1",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Yay",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow2",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Yay (Güç I)",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow3",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Yay (Güç I, Yumruk I)",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "&8İksirler",
            "&8İksirler",
            listOf("&eGörmek için tıkla!")
        )

        addContentMessages(
            this,
            "speed-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Hız II İksiri (45 saniye)",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "jump-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Zıplama Desteği V İksiri (45 saniye)",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "invisibility",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Görünmezlik İksiri (30 saniye)",
            listOf("&7Ücret: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "&8Diğer",
            "&8Diğer",
            listOf("&eGörmek için tıkla!")
        )

        addContentMessages(
            this,
            "golden-apple",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Altın Elma",
            listOf(
                "&7Ücret: {cost} {currency}",
                "",
                "&7Well-rounded healing.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "bedbug", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}BedBug", listOf(
                "&7Ücret: {cost} {currency}",
                "",
                "&7Kartopunu attığın yerde",
                "&7rakiplerin dikkatini dağıtmak için",
                "&7böcekler doğurur. 15 saniye sonra kaybolur.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "dream-defender", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Demir Golem", listOf(
                "&7Ücret: {cost} {currency}", "", "&7Takımını savunmana yardım",
                "&7eder. 4 dakika sonra kaybolur.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "fireball", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Ateş Topu", listOf(
                "&7Ücret: {cost} {currency}", "", "&7Sağ tıklayarak ateşle! Rakiplerinin",
                "&7yürüdükleri köprüleri patlatmak", "&7için harika bir ürün!", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "tnt", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}TNT", listOf(
                "&7Ücret: {cost} {currency}", "", "&7Direkt ateşlenir, diğer takımların",
                "&7korumaları için iyi bir çözüm", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "ender-pearl", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Ender İncisi", listOf(
                "&7Ücret: {cost} {currency}", "", "&7Diğer takımların adalarına",
                "&7gitmenin en hızlı yolu.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "water-bucket", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Su Kovası", listOf(
                "&7Ücret: {cost} {currency}", "", "&7Patlamaları etkisiz",
                "&7bırakır.", "", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "bridge-egg", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Köprü Yumurtası", listOf(
                "&7Ücret: {cost} {currency}", "", "&7Atılan doğrultuda",
                "&7bir köprü yapar.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "magic-milk", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Sihirli Süt", listOf(
                "&7Ücret: {cost} {currency}", "", "&7İçildikten sonra 60 saniye",
                "&7boyunca düşman tuzaklarından korur.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "sponge", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Sünger", listOf(
                "&7Ücret: {cost} {currency}", "", "&7Suyu yok etmenin basit bir yolu.",
                "", "{quick_buy}", "{buy_status}"
            )
        )

        //
        addDefault(Messages.MEANING_NO_TRAP, "Tuzak yok!")
        addDefault(Messages.FORMAT_SPECTATOR_TARGET, "{targetTeamColor}{targetDisplayName}")
        addDefault(Messages.FORMAT_UPGRADE_TRAP_COST, "&7Ücret: {currencyColor}{cost} {currency}")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD, "&e")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD, "&c")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_UNLOCKED, "&a")
        addDefault(Messages.FORMAT_UPGRADE_TIER_LOCKED, "&7")
        addDefault(Messages.FORMAT_UPGRADE_TIER_UNLOCKED, "&a")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "{color}Satın almak için tıkla!")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY, "{color}Yeteri kadar {currency} yok!")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_LOCKED, "&cKİLİTLENDİ")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED, "{color}KİLİT KALDIRILDI")
        addDefault(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT, "&a{player} adlı oyuncu &6{upgradeName} satın aldı")
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-1"),
            "{color}Demir Ocağı"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "forge"),
            listOf(
                "&7Adanda üretilen kaynak",
                "&7sayısını arttırır.",
                "",
                "{tier_1_color}Seviye 1: +50% Kaynak Artışı, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Seviye 2: +100% Kaynak Artışı, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Seviye 3: Zümrüt üretir, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Seviye 4: +200% Kaynak Artışı, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-2"),
            "{color}Altın Ocağı"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-3"),
            "{color}Zümrüt Ocağı"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-4"),
            "{color}Mistik Ocak"
        )
        addDefault(Messages.UPGRADES_CATEGORY_ITEM_NAME_PATH + "traps", "&eTızak satın al")
        addDefault(
            Messages.UPGRADES_CATEGORY_ITEM_LORE_PATH + "traps",
            listOf("&7Alınan tuzaklar", "&7sağda sıralanacak.", "", "&eGöz atmak için tıkla!")
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "swords").replace("{tier}", "tier-1"),
            "{color}Keskin Kılıçlar"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "swords"),
            listOf(
                "&7Takımının kılıç ve",
                "&7baltalarında kalıcı olarak",
                "&7Keskinlik I olacak!",
                "",
                "{tier_1_color}Ücret: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-1"),
            "{color}Korumalı Zırh I"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "armor"),
            listOf(
                "&7Takımının zırhlarında kalıcı",
                "&7koruma olur!",
                "",
                "{tier_1_color}Seviye 1: Koruma I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Seviye 2: Koruma II, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Seviye 3: Koruma III, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Seviye 4: Koruma IV, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-2"),
            "{color}Korumalı Zırh II"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-3"),
            "{color}Korumalı Zırh III"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-4"),
            "{color}Korumalı Zırh IV"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-1"),
            "{color}Manyak Madenci I"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "miner"),
            listOf(
                "&7Takımındaki oyuncular, kalıcı",
                "&7Acele efektine sahip olurlar.",
                "",
                "{tier_1_color}Seviye 1: Acele I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Seviye 2: Acele II, &b{tier_2_cost} {tier_2_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-2"),
            "{color}Manyak Madenci II"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "miner"),
            listOf(
                "&7Takımındaki oyuncular, kalıcı",
                "&7Acele efektine sahip olurlar.",
                "",
                "{tier_1_color}Seviye 1: Acele I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Seviye 2: Acele II, &b{tier_2_cost} {tier_2_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "heal-pool").replace("{tier}", "tier-1"),
            "{color}Can Havuzu"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "heal-pool"),
            listOf(
                "&7Adanda durduğun sürece",
                "&7canın yenilenir!",
                "",
                "{tier_1_color}Ücret: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "dragon").replace("{tier}", "tier-1"),
            "{color}Dragon Buff"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "dragon"),
            listOf(
                "&7Ejderha Saldırısı sırasında",
                "&7takımın 2 Ejderhaya sahip olur",
                "",
                "{tier_1_color}Ücret: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "glass", "&8⬆&7Satın Alınabilir")
        addDefault(
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "glass",
            listOf("&8⬇&7Tuzak Listesi")
        )
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "first", "{color}Tuzak #1: {name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "first",
            listOf("&7Adana giren ilk", "&7kişi bu tuzağı", "&7aktifleştirir!")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "first",
            listOf(
                "",
                "&7Bu tuzağı satın almak,",
                "&7tuzağı sıraya ekler. Fiyat,",
                "&7adandaki tuzak sayısına",
                "&7göre belirlenir.",
                "",
                "&7Sıradaki tuzak: &b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "second", "{color}Tuzak #2: {name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "second",
            listOf("&7Adana giren ikinci", "&7kişi bu tuzağı", "&7aktifleştirir!")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "second",
            listOf(
                "",
                "&7Bu tuzağı satın almak,",
                "&7tuzağı sıraya ekler. Fiyat,",
                "&7adandaki tuzak sayısına",
                "&7göre belirlenir.",
                "",
                "&7Sıradaki tuzak: &b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "third", "{color}Tuzak #3: {name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "third",
            listOf("&7Adana giren üçüncü", "&7kişi bu tuzağı", "&7aktifleştirir!")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "third",
            listOf(
                "",
                "&7Bu tuzağı satın almak,",
                "&7tuzağı sıraya ekler. Fiyat,",
                "&7adandaki tuzak sayısına",
                "&7göre belirlenir.",
                "",
                "&7Sıradaki tuzak: &b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "1", "{color}Bu bir tuzak!")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "1",
            listOf("&75 saniye boyunca Körlük ve", "&7Yavaşlık verir.", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "2", "{color}Karşı Saldırı Tuzağı")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "2",
            listOf("&7Tetiklendiğinde, adandaki takım arkadaşlarına", "&7Hız efekti verir.", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "3", "{color}Alaram Tuzağı")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "3",
            listOf("&7Görünmez oyuncuları", "&7görünür kılar.", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "4", "{color}Madenci Yorgunluğu Tuzağı")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "4",
            listOf("&710 saniye boyunca", "&7Madenci Yorgunluğu verir.", "")
        )
        addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "back", "&aGeri")
        addDefault(
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "back",
            listOf("&7Yükseltmeler & Tuzaklar")
        )
        addDefault(Messages.UPGRADES_CATEGORY_GUI_NAME_PATH + "traps", "&8Tuzak Ekle")
        addDefault(Messages.UPGRADES_TRAP_QUEUE_LIMIT, "&cTrap sırası dolu!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_MSG, "&c&l{trap} tetiklendi!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_TITLE, "&cTUZAK TETİKLENDİ!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_SUBTITLE, "&f{trap} &ftuzağın tetiklendi!")
        addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_MSG + "3",
            "&c&lAlarm, {color}&l{team} &c&ltakımdaki &7&l{player} &c&ltarafından tetiklendi!"
        )
        addDefault(Messages.UPGRADES_TRAP_CUSTOM_TITLE + "3", "&c&lALARM!!!")
        addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + "3",
            "&fAlarm, {color}{team} &fTakım tarafından tetiklendi!"
        )
        save()
        setPrefix(m(Messages.PREFIX))
    }
}
