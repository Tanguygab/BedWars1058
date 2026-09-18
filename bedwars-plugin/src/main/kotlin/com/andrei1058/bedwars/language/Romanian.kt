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

class Romanian : Language(BedWars.INSTANCE, "ro", "Română") {
    init {
        val bw = BedWars.MAIN_COMMAND

        addDefault(
            Messages.COMMAND_MAIN,
            listOf(
                "",
                "&2▪ &7/$bw stats",
                "&2▪ &7/$bw join &o<arena/group>",
                "&2▪ &7/$bw leave",
                "&2▪ &7/$bw lang",
                "&2▪ &7/$bw gui",
                "&2▪ &7/$bw start &3(vip)"
            )
        )
        addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL,
            "{prefix}&cArena este plina!\n&aAi putea lua in considerare donarea pentru mai multe facilitati. &7&o(click)"
        )
        addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS,
            "{prefix}&cNe pare rau dar arena este plina.\n&cStim ca esti donator dar arena este deja plina cu persoane care au prioritate."
        )
        addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&e{player} a iesit din joc!")
        addDefault(
            Messages.COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND,
            "{prefix}&cNu exista arene sau grupuri cu numele: {name}"
        )
        addDefault(Messages.COMMAND_JOIN_NO_EMPTY_FOUND, "{prefix}&cNu sunt arene disponibile momentan ;(")
        addDefault(Messages.COMMAND_LEAVE_DENIED_NOT_IN_ARENA, "{prefix}&cNu esti intr-o arena!")
        addDefault(Messages.COMMAND_LANG_LIST_HEADER, "{prefix} &2Limbi disponibile:")
        addDefault(Messages.COMMAND_LANG_LIST_FORMAT, "&a▪  &7{iso} - &f{name}")
        addDefault(Messages.COMMAND_LANG_USAGE, "{prefix}&7Foloseste: /lang &f&o<iso>")
        addDefault(Messages.COMMAND_LANG_SELECTED_NOT_EXIST, "{prefix}&cAceasta limba nu exista!")
        addDefault(Messages.COMMAND_LANG_SELECTED_SUCCESSFULLY, "{prefix}&aLimba a fost schimbata!")
        addDefault(Messages.COMMAND_LANG_USAGE_DENIED, "{prefix}&cNu poti schimba limba in timpul meciului.")
        addDefault(Messages.COMMAND_TP_PLAYER_NOT_FOUND, "{prefix}&cPlayer not found!")
        addDefault(Messages.COMMAND_TP_NOT_IN_ARENA, "{prefix}&cThis player is not in a bedwars arena!")
        addDefault(Messages.COMMAND_TP_NOT_STARTED, "{prefix}&cThe arena where the player is didn't start yet!")
        addDefault(Messages.COMMAND_TP_USAGE, "{prefix}&cUsage: /bw tp <username>")
        addDefault(
            Messages.COMMAND_JOIN_DENIED_PARTY_TOO_BIG,
            "{prefix}&cParty-ul este prea mare pentru a intra ca un singur team :("
        )
        addDefault(Messages.COMMAND_JOIN_DENIED_NOT_PARTY_LEADER, "{prefix}&cDoar leader-ul poate alege arena.")
        addDefault(Messages.COMMAND_JOIN_PLAYER_JOIN_MSG, "{prefix}&7{player} &ea intrat (&b{on}&e/&b{max}&e)!")
        addDefault(Messages.COMMAND_REJOIN_PLAYER_RECONNECTED, "{prefix}&7{player} &es-a reconectat!")
        addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&7{player} &ea iesit!")
        addDefault(Messages.COMMAND_JOIN_USAGE, "§a▪ §7Folosire: /$bw join §o<arena/group>")
        addDefault(Messages.COMMAND_NOT_ALLOWED_IN_GAME, "{prefix}&cNu poti face asta in timpul meciului.")
        addDefault(
            Messages.COMMAND_PARTY_HELP, listOf(
                "&6▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&aComenzi Party:",
                "&e/party help &7- &bArata mesajele de ajutor",
                "&e/party invite <jucatori> &7- &bInvita un jucator in party",
                "&e/party leave &7- &bParaseste un party",
                "&e/party info &7- &bShow party members and owner",
                "&e/party promote <player> &7- &bTransfer party ownership",
                "&e/party remove <player> &7- &bScoate un jucator din party",
                "&e/party accept <player> &7- &bAccepta o invitatie",
                "&e/party disband &7- &bSterge party-ul"
            )
        )
        addDefault(Messages.COMMAND_PARTY_INVITE_USAGE, "{prefix}&eFolosire: &7/party invite <jucator>")
        addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &enu este online!")
        addDefault(Messages.COMMAND_PARTY_INVITE_SENT, "{prefix}&eInvitatia a fost trimisa lui &7{player}&6.")
        addDefault(
            Messages.COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG,
            "{prefix}&b{player} &ete-a invitat intr-un party! &o&7(Click pentru a accepta)"
        )
        addDefault(
            Messages.COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF,
            "{prefix}&cNu te poti invita singur!"
        )
        addDefault(Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE, "{prefix}&cNu ai cereri de acceptat.")
        addDefault(Messages.COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY, "{prefix}&Esti deja intr-un party!")
        addDefault(
            Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS,
            "{prefix}&cDoar detinatorul party-ului poate face asta!"
        )
        addDefault(Messages.COMMAND_PARTY_ACCEPT_USAGE, "{prefix}&eFolosire: &7/party accept <jucator>")
        addDefault(Messages.COMMAND_PARTY_ACCEPT_SUCCESS, "{prefix}&7{player} &ea intrat in party!")
        addDefault(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY, "{prefix}&cNu esti intr-un party!")
        addDefault(
            Messages.COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND,
            "{prefix}&cNu poti iesi din propriul party!\n&eIncearca: &b/party disband"
        )
        addDefault(Messages.COMMAND_PARTY_LEAVE_SUCCESS, "{prefix}&7{player} &ea iesit din party!")
        addDefault(Messages.COMMAND_PARTY_DISBAND_SUCCESS, "{prefix}&eParty-ul a fost sters!")
        addDefault(Messages.COMMAND_PARTY_REMOVE_USAGE, "{prefix}&7Folosire: &e/party remove <jucator>")
        addDefault(Messages.COMMAND_PARTY_REMOVE_SUCCESS, "{prefix}&7{player} &ea fost scos din party.")
        addDefault(
            Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER,
            "{prefix}&7{player} &enu este in acest party!"
        )
        addDefault(
            Messages.COMMAND_PARTY_PROMOTE_SUCCESS,
            "{prefix}&eAi promovat cu succes pe {player} ca proprietar"
        )
        addDefault(Messages.COMMAND_PARTY_PROMOTE_OWNER, "{prefix}&eAi fost promovat ca proprietar al petrecerii")
        addDefault(Messages.COMMAND_PARTY_PROMOTE_NEW_OWNER, "{prefix}&7 &e{player} a fost promovat ca proprietar")
        addDefault(Messages.COMMAND_PARTY_INFO_OWNER, "\n{prefix}&eProprietarul petrecerii este: &7{owner}")
        addDefault(Messages.COMMAND_PARTY_INFO_PLAYERS, "{prefix}&eMembrii sunt:")
        addDefault(Messages.COMMAND_PARTY_INFO_PLAYER, "&7{player}")
        addDefault(
            Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS,
            "{prefix}&cComanda nu a fost gasita sau nu ai permisiunea!"
        )
        addDefault(Messages.COMMAND_FORCESTART_NOT_IN_GAME, "§c▪ §7Nu esti in joc!")
        addDefault(Messages.COMMAND_FORCESTART_SUCCESS, "§c▪ §7Numaratoarea a fost redusa!")
        addDefault(
            Messages.COMMAND_FORCESTART_NO_PERM,
            "{prefix}&7Nu poti forta arena sa inceapa.\n§7Ai putea lua in considerare donarea pentru mai multe facilitati."
        )
        addDefault(
            Messages.COMMAND_JOIN_SPECTATOR_MSG,
            "{prefix}§6Acum urmaresti meciul din §9{arena}§6.\n{prefix}§ePoti parasi arena folosind §c/leave§e."
        )
        addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eeste offline!")
        addDefault(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG, "&cSpectatorii nu sunt permisi in aceasta arena!")
        addDefault(Messages.COMMAND_COOLDOWN, "&cNu poti face asta inca! Mai asteapta {seconds} secunde!")
        addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_NAME, "&8Teleportor")
        addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME, "{vPrefix}{player}")
        addDefault(
            Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE,
            listOf("&7Viata: &f{health}%", "&7Hrana: &f{food}", "", "&7Click-stanca pentru a urmari.")
        )
        addDefault(Messages.ARENA_SPECTATOR_LEAVE_ITEM_NAME, "&c&lIntoarce-te in lobby")
        addDefault(
            Messages.ARENA_SPECTATOR_LEAVE_ITEM_LORE,
            listOf("&7Click-dreapta pentru a merge in lobby!")
        )
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE, "&aUrmareste-l pe &7{player}")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE, "&cSNEAK pentru a iesi")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE, "&eIesi din modul Spectator")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE, "")
        addDefault(
            Messages.ARENA_JOIN_VIP_KICK,
            "{prefix}&cNe pare rau, dar locul tau a fost rezervat unui donator.\n&aAi putea lua in considerare donarea pentru mai multe facilitati. &7&o(click)"
        )
        addDefault(
            Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT,
            "{prefix}§cNu sunt destui jucatori! Numaratoarea a fost oprita!"
        )
        addDefault(Messages.ARENA_RESTART_PLAYER_KICK, "{prefix}&eArena in care te aflai se reporneste.")
        addDefault(Messages.ARENA_STATUS_PLAYING_NAME, "&cIn joc")
        addDefault(Messages.ARENA_STATUS_RESTARTING_NAME, "&4Se reporneste")
        addDefault(Messages.ARENA_STATUS_WAITING_NAME, "&2In Asteptare §c{full}")
        addDefault(Messages.ARENA_STATUS_STARTING_NAME, "&6Porneste §c{full}")
        addDefault(Messages.ARENA_GUI_INV_NAME, "&8Click pentru a intra")
        addDefault(Messages.ARENA_GUI_ARENA_CONTENT_NAME, "&a&l{name}")
        addDefault(
            Messages.ARENA_GUI_ARENA_CONTENT_LORE,
            listOf(
                "",
                "&7Status: {status}",
                "&7Jucatori: &f{on}&7/&f{max}",
                "&7Tip: &a{group}",
                "",
                "&aClick-Stanga pentru a intra.",
                "&eClick-Dreapta pentru a vizona."
            )
        )
        addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_NAME, "&r{serverIp}")
        addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_LORE, emptyList<String>())
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CHAT, "{prefix}&eJocul incepe in &6{time} &esecunde!")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_TITLE, " ")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE, "&a{second}")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-5", "&e❺")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-4", "&e❹")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-3", "&c❸")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-2", "&c❷")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-1", "&c❶")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_TITLE, " ")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_SUB_TITLE, "&cWaiting for more players..")
        addDefault(
            Messages.ARENA_STATUS_START_PLAYER_TUTORIAL, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lBedWars", "",
                "&e&lProtejeaza-ti patul si distruge paturile inamicilor.",
                "&e&l          Cumpara arme si upgrade-uri colectand",
                "&e&l   Fier, Aur, Emeralde si Diamonte de la generatoare.", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        addDefault(Messages.ARENA_STATUS_START_PLAYER_TITLE, "&aSTART")
        addDefault(
            Messages.ARENA_LEAVE_PARTY_DISBANDED,
            "{prefix}§cParty-ul a fost sters pentru ca detinatorul a iesit!"
        )
        addDefault(
            Messages.ARENA_JOIN_DENIED_SELECTOR,
            "{prefix}&cNe pare rau dar nu poti intra chiar acum. Foloseste Click-Dreapta pentru a urmari!"
        )
        addDefault(
            Messages.ARENA_SPECTATE_DENIED_SELECTOR,
            "{prefix}&cNe pare rau dar nu poti urmari arena chiar acum. Foloseste Click-Dreapta pentru a juca!"
        )
        addDefault(
            Messages.ARENA_JOIN_DENIED_NO_PROXY,
            "&cSorry but you must join an arena using BedWarsProxy. \n&eIf you want to setup an arena make sure to give yourself the bw.setup permission so you can join the server directly!"
        )
        addDefault(Messages.GENERATOR_HOLOGRAM_TIER, "&eTier &c{tier}")
        addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND, "&b&lDiamant")
        addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD, "&a&lEmerald")
        addDefault(Messages.GENERATOR_HOLOGRAM_TIMER, "&eSe spawneaza in &c{seconds} &esecunde")
        addDefault(
            Messages.GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT,
            "{prefix}&eGeneratoarele de {generatorType} &eau fost upgradate la nivelul &c{tier}"
        )
        addDefault(Messages.FORMATTING_TEAM_WINNER_FORMAT, "      {TeamColor}{TeamName} &7- {members}")
        addDefault(Messages.FORMATTING_SOLO_WINNER_FORMAT, "                 {TeamColor}{TeamName} &7- {members}")
        addDefault(Messages.FORMATTING_GENERATOR_TIER1, "I")
        addDefault(Messages.FORMATTING_GENERATOR_TIER2, "II")
        addDefault(Messages.FORMATTING_GENERATOR_TIER3, "III")
        addDefault(Messages.FORMATTING_CHAT_LOBBY, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        addDefault(Messages.FORMATTING_CHAT_WAITING, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        addDefault(
            Messages.FORMATTING_CHAT_SHOUT,
            "{level}{vPrefix}&6[SHOUT] {team} &7{player}&f{vSuffix}: {message}"
        )
        addDefault(Messages.FORMATTING_CHAT_TEAM, "{level}{vPrefix}&f{team}&7 {player}{vSuffix} {message}")
        addDefault(Messages.FORMATTING_CHAT_SPECTATOR, "{level}{vPrefix}&7[SPECTATOR] {player}{vSuffix}: {message}")
        addDefault(Messages.FORMATTING_SCOREBOARD_HEALTH, listOf("&c❤", "&aViață"))

        addDefault(Messages.FORMATTING_SCOREBOARD_DATE, "dd/MM/yy")
        addDefault(
            Messages.FORMATTING_SCOREBOARD_TEAM_GENERIC,
            "{TeamColor}{TeamLetter}&f {TeamName}: {TeamStatus}"
        )
        addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ELIMINATED, "&c&l✘")
        addDefault(Messages.FORMATTING_SCOREBOARD_BED_DESTROYED, "&a{remainingPlayers}")
        addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ALIVE, "&a&l✓")
        addDefault(Messages.FORMATTING_SCOREBOARD_NEXEVENT_TIMER, "mm:ss")
        addDefault(Messages.FORMATTING_SCOREBOARD_YOUR_TEAM, "&7 TU")
        addDefault(Messages.FORMATTING_ACTION_BAR_TRACKING, "&fUrmaresti: {team} &f- Distanta: {distance}m")
        addDefault(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH, "▮ ")
        addDefault(Messages.FORMATTING_STATS_DATE_FORMAT, "yyyy/MM/dd HH:mm")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_TEAM, "{TeamColor}[{TeamName}]")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SHOUT, "&6[STRIGAT]")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SPECTATOR, "&7[SPECTATOR]")
        addDefault(Messages.MEANING_SHOUT, "shout")
        addDefault(Messages.MEANING_NOBODY, "Nimeni")
        addDefault(Messages.MEANING_FULL, "Plin")
        addDefault(Messages.MEANING_IRON_SINGULAR, "Fier")
        addDefault(Messages.MEANING_IRON_PLURAL, "Fier")
        addDefault(Messages.MEANING_GOLD_SINGULAR, "Aur")
        addDefault(Messages.MEANING_GOLD_PLURAL, "Aur")
        addDefault(Messages.MEANING_EMERALD_SINGULAR, "Emerald")
        addDefault(Messages.MEANING_EMERALD_PLURAL, "Emeralde")
        addDefault(Messages.MEANING_DIAMOND_SINGULAR, "Diamant")
        addDefault(Messages.MEANING_DIAMOND_PLURAL, "Diamante")
        addDefault(Messages.MEANING_VAULT_SINGULAR, "$")
        addDefault(Messages.MEANING_VAULT_PLURAL, "$")
        addDefault(Messages.MEANING_NEVER, "Niciodata")
        addDefault(
            Messages.INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED,
            "&cNu poti deschide acest cufar pentru ca echipa nu a fost eliminata!"
        )
        addDefault(Messages.INTERACT_CANNOT_PLACE_BLOCK, "{prefix}&cNu poti pune blocuri aici!")
        addDefault(Messages.INTERACT_CANNOT_BREAK_BLOCK, "{prefix}&cYou can only break blocks placed by a player!")
        addDefault(Messages.INTERACT_CANNOT_BREAK_OWN_BED, "&cNu iti poti distruge propriul pat!")
        addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT,
            "\n&f&lPAT DISTRUS > {TeamColor}Patul Echipei {TeamName} &7a fost distrus de {PlayerColor}{PlayerName}&7!\n"
        )
        addDefault(Messages.INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT, "&cPAT DISTRUS!")
        addDefault(Messages.INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT, "&fNu te mai poti respawna!")
        addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM,
            "&f&lPAT DISTRUS > &7Patul tau a fost distrus de {PlayerColor}{PlayerName}&7!"
        )
        addDefault(
            Messages.INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN,
            "&cYou are no longer invisible because you have taken damage!"
        )
        addDefault(Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL, "{PlayerColor}{PlayerName} &7a cazut in void.")
        addDefault(
            Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7a cazut in void. &b&lUCIDERE FINALA!"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7a fost impins in void de {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7a fost impins in void de {KillerColor}{KillerName}&7. &b&lUCIDERE FINALA!"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7a fost impins de {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7a fost impins de {KillerColor}{KillerName}&7. &b&lUCIDERE FINALA!"
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7a fost lovit de tnt-ul lui {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7a fost lovit de tnt-ul lui {KillerColor}{KillerName}&7. &b&lUCIDERE FINALA!"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7a fost ucis de {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7a fost ucis de {KillerColor}{KillerName}&7. &b&lUCIDERE FINALA!"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_REGULAR,
            "{PlayerColor}{PlayerName} &7s-a deconectat in timpul luptei cu {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_FINAL,
            "{PlayerColor}{PlayerName} &7s-a deconectat in timpul luptei cu {KillerColor}{KillerName}&7. &b&lUCIDERE FINALA!"
        )
        addDefault(Messages.PLAYER_DIE_RESPAWN_TITLE, "&cESTI BLEG!")
        addDefault(Messages.PLAYER_DIE_RESPAWN_SUBTITLE, "&eTe vei respawna in &c{time} &esecunde!")
        addDefault(Messages.PLAYER_DIE_RESPAWN_CHAT, "{prefix}&eTe vei respawna in &c{time} &esecunde!")
        addDefault(Messages.PLAYER_DIE_RESPAWNED_TITLE, "&aRESPAWNAT!")
        addDefault(Messages.PLAYER_DIE_ELIMINATED_CHAT, "{prefix}&cAi fost eliminat!")
        addDefault(Messages.PLAYER_HIT_BOW, "{prefix}{TeamColor}{PlayerName} &7a ramas cu &c{amount} &7HP!")
        addDefault(Messages.PLAYER_DIE_UNKNOWN_REASON_REGULAR, "{PlayerColor}{PlayerName} &7a murit.")
        addDefault(
            Messages.PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7a murit. &b&lUCIDERE FINALA!"
        )
        addDefault(
            Messages.PLAYER_DIE_SHOOT_REGULAR,
            "{PlayerColor}{PlayerName} &7a fost impuscat de {KillerColor}{KillerName}&7!"
        )
        addDefault(
            Messages.PLAYER_DIE_SHOOT_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7a fost impuscat de {KillerColor}{KillerName}&7! &b&lUCIDERE FINALA!"
        )
        addDefault(
            Messages.PLAYER_DIE_DEBUG_REGULAR,
            "{PlayerColor}{PlayerName} &7a fost ucis de {KillerColor}Sobolanul Echipei {KillerTeamName}!"
        )
        addDefault(
            Messages.PLAYER_DIE_DEBUG_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7a fost ucis de {KillerColor}Sobolanul Echipei {KillerTeamName! &b&lUCIDERE FINALA!"
        )
        addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_REGULAR,
            "{PlayerColor}{PlayerName} &7a fost ucis de {KillerColor}Gardianul Echipei {KillerTeamName}!"
        )
        addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7a fost ucis de {KillerColor}Gardianul Echipei {KillerTeamName}! &b&lUCIDERE FINALA!"
        )
        addDefault(Messages.PLAYER_DIE_REWARD_DIAMOND, "{prefix}&b+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_EMERALD, "{prefix}&a+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_IRON, "{prefix}&f+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_GOLD, "{prefix}&6+{amount} {meaning}")
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR,
            "{PlayerColor}{PlayerName} &7a fost lovit de o bomba."
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &a fost lovit de o bomba. &b&lUCIDERE FINALA!"
        )
        addDefault(Messages.GAME_END_GAME_OVER_PLAYER_TITLE, "&c&lJOC TERMINAT!")
        addDefault(Messages.GAME_END_VICTORY_PLAYER_TITLE, "&6&lVICTORIE!")
        addDefault(
            Messages.GAME_END_TOP_PLAYER_CHAT, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lBedWars", "", "{winnerFormat}", "", "",
                "&6                 &6⭐ &lPrimul Ucigas &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&e                    &lAl 2-lea Ucigas &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&c                    &lAl 3-lea Ucigas &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        addDefault(Messages.GAME_END_TEAM_WON_CHAT, "{prefix}{TeamColor}Echipa {TeamName} &aa castigat!")
        addDefault(Messages.NPC_NAME_TEAM_UPGRADES, "&bTEAM UPGRADES,&e&lCLICK DREAPTA")
        addDefault(Messages.NPC_NAME_SOLO_UPGRADES, "&bSOLO UPGRADES,&e&lCLICK DREAPTA")
        addDefault(Messages.NPC_NAME_TEAM_SHOP, "&bMAGAZIN TEAM,&e&lCLICK DREAPTA")
        addDefault(Messages.NPC_NAME_SOLO_SHOP, "&bMAGAZIN SOLO,&e&lCLICK DREAPTA")
        addDefault(Messages.BED_HOLOGRAM_DEFEND, "&c&lApara-ti patul!")
        addDefault(Messages.BED_HOLOGRAM_DESTROYED, "&c&lPatul tau a fost distrus!")
        addDefault(
            Messages.TEAM_ELIMINATED_CHAT,
            "\n&f&lTEAM ELIMINAT > {TeamColor}Echipa {TeamName} &ca fost eliminata!\n"
        )
        addDefault(Messages.NEXT_EVENT_BEDS_DESTROY, "&cDistrugerea Paturilor")
        addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_II, "&fDiamant II")
        addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_III, "&fDiamant III")
        addDefault(Messages.NEXT_EVENT_DRAGON_SPAWN, "&fMoarte Brusca")
        addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_II, "&fEmerald II")
        addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_III, "&fEmerald III")
        addDefault(Messages.NEXT_EVENT_GAME_END, "&4Final De Joc")
        addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED, "&cPATUL A FOST DISTRUS!")
        addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED, "&fToate paturile au fost distruse!")
        addDefault(Messages.NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED, "&c&lToate paturile au fost distruse!")
        addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH, "&cMoarte Brusca")
        addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH, "")
        addDefault(
            Messages.NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH,
            "&cMOARTE BRUSCA: &6&b{TeamDragons} {TeamColor}Dragonul {TeamName}!"
        )

        /* Lobby Command Obiecte */
        addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "stats"), "&eStatistici")
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fClick-dreapta pentru", "&fa-ti vedea statisticile!")
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "arena-selector"),
            "&eArena Selector"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "arena-selector"),
            listOf("&fClick-dreapta pentru", "&fa alege o arena.")
        )
        addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "leave"), "&eInapoi in Hub")
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fClick-dreapta pentru a", "&fparasi BedWars!")
        )
        /* Pret Game Command ITEMS */
        addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "stats"), "&eStatistici")
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fClick-dreapta pentru", "&fa-ti vedea statisticile!")
        )
        addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "leave"), "&eInapoi in Hub")
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fClick-dreapta pentru a", "&fparasi BedWars!")
        )
        /* Spectator Command ITEMS */
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "teleporter"),
            "&eTeleportor"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "teleporter"),
            listOf("&fClick-dreapta pentru a urmari", "&fun jucator!")
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "leave"),
            "&eInapoi in Lobby"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fClick-dreapta pentru a", "&fparasi arena!")
        )

        addDefault(Messages.REJOIN_NO_ARENA, "{prefix}&cNu ai o arena pe care sa te reintorci!")
        addDefault(
            Messages.REJOIN_DENIED,
            "{prefix}&cNu mai poti face asta. Jocul s-a terminat sau patul a fost distrus."
        )
        addDefault(Messages.REJOIN_ALLOWED, "{prefix}&eIntri pe arena &a{arena}&e!")

        /* save default Obiecte messages for stats gui */
        addDefault(Messages.PLAYER_STATS_GUI_INV_NAME, "&8Statisticile lui {player}")
        addDefaultStatsMsg("wins", "&6Victorii", "&f{wins}")
        addDefaultStatsMsg("losses", "&6Pierderi", "&f{losses}")
        addDefaultStatsMsg("kills", "&6Ucideri", "&f{kills}")
        addDefaultStatsMsg("deaths", "&6Decese", "&f{deaths}")
        addDefaultStatsMsg("final-kills", "&6Ucideri Finale", "&f{finalKills}")
        addDefaultStatsMsg("final-deaths", "&6Decese Finale", "&f{finalDeaths}")
        addDefaultStatsMsg("beds-destroyed", "&6Paturi Distruse", "&f{bedsDestroyed}")
        addDefaultStatsMsg("first-play", "&6Primul Meci", "&f{firstPlay}")
        addDefaultStatsMsg("last-play", "&6Ultimul Meci", "&f{lastPlay}")
        addDefaultStatsMsg("games-played", "&6Partide Jucate", "&f{gamesPlayed}")

        // start of sidebar
        addDefault(
            Messages.SCOREBOARD_LOBBY, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&fNivelul tau: {level}",
                "",
                "&fProgres: &a{currentXp}&7/&b{requiredXp}",
                "{progress}",
                "",
                "&7{player}",
                "",
                "&fBani: &a{money}",
                "",
                "&fVictorii: &a{wins}",
                "&fUcideri: &a{kills}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fHarta: &a{map}",
                "",
                "&fJucatori: &a{on}/{max}",
                "",
                "&fIn asteptare,&fIn asteptare.,&fIn asteptare..,&fIn asteptare...",
                "",
                "&fMod: &a{group}",
                "&fVersiune: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Esti spectator",
                "&fHarta: &a{map}",
                "",
                "&fJucatori: &a{on}/{max}",
                "",
                "&fIn asteptare,&fIn asteptare.,&fIn asteptare..,&fIn asteptare...",
                "",
                "&fMod: &a{group}",
                "&fVersiune: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fMap: &a{map}",
                "",
                "&fJucatori: &a{on}/{max}",
                "",
                "&fIncepe in &a{time}s",
                "",
                "§fMod: &a{group}",
                "&fVersiune: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Esti spectator",
                "&fMap: &a{map}",
                "",
                "&fJucatori: &a{on}/{max}",
                "",
                "&fIncepe in &a{time}s",
                "",
                "§fMod: &a{group}",
                "&fVersiune: &7{version}",
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
                "&fUcideri: &a{kills}",
                "&fUcideri Finale: &a{finalKills}",
                "&fPaturi Distruse: &a{beds}",
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
                "&fUcideri: &a{kills}",
                "&fUcideri Finale: &a{finalKills}",
                "&fPaturi Distruse: &a{beds}",
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
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&fUcideri: &a{kills}",
                "&fUcideri Finale: &a{finalKills}",
                "&fPaturi Distruse: &a{beds}",
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
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&fUcideri: &a{kills}",
                "&fUcideri Finale: &a{finalKills}",
                "&fPaturi Distruse: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        // end of sidebar

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
        addDefault(Messages.XP_REWARD_PER_MINUTE, "{prefix}&6+{xp} BedWars Experience Received (Play Time).")
        addDefault(Messages.XP_REWARD_WIN, "{prefix}&6+{xp} BedWars Experience Received (Game Win).")
        addDefault(Messages.XP_REWARD_PER_TEAMMATE, "{prefix}&6+{xp} BedWars Experience Received (Team Support).")
        addDefault(Messages.XP_REWARD_BED_DESTROY, "{prefix}&6+{xp} BedWars Experience Received (Bed Destroyed).")
        addDefault(Messages.XP_REWARD_REGULAR_KILL, "{prefix}&6+{xp} BedWars Experience Received (Regular Kill).")
        addDefault(Messages.XP_REWARD_FINAL_KILL, "{prefix}&6+{xp} BedWars Experience Received (Final Kill).")

        addDefault(Messages.MONEY_REWARD_PER_MINUTE, "{prefix}&6+{money} Coins (Play Time).")
        addDefault(Messages.MONEY_REWARD_WIN, "{prefix}&6+{money} Coins (Game Win).")
        addDefault(Messages.MONEY_REWARD_PER_TEAMMATE, "{prefix}&6+{money} Coins (Team Support).")
        addDefault(Messages.MONEY_REWARD_BED_DESTROYED, "{prefix}&6+{money} Coins (Bed Destroyed).")
        addDefault(Messages.MONEY_REWARD_FINAL_KILL, "{prefix}&6+{money} Coins (Final Kill).")
        addDefault(Messages.MONEY_REWARD_REGULAR_KILL, "{prefix}&6+{money} Coins (Regular Kill).")

        //shop
        addDefault(Messages.SHOP_INDEX_NAME, "&8Cumpara Repede")
        addDefault(Messages.SHOP_QUICK_ADD_NAME, "&8Adding to Quick Buy...")
        addDefault(
            Messages.SHOP_INSUFFICIENT_MONEY,
            "{prefix}&cNu ai destul {currency}! Mai ai nevoide de {amount} de {currency}!"
        )
        addDefault(Messages.SHOP_NEW_PURCHASE, "{prefix}&aAi cumparat &6{item}")
        addDefault(Messages.SHOP_ALREADY_BOUGHT, "{prefix}&cAi cumparat deja asta!")
        addDefault(Messages.SHOP_UTILITY_NPC_SILVERFISH_NAME, "{TeamColor}&l{TeamName} &r{TeamColor}Silverfish")
        addDefault(Messages.SHOP_UTILITY_NPC_IRON_GOLEM_NAME, "{TeamColor}{despawn}s &8[ {TeamColor}{health}&8]")
        addDefault(Messages.SHOP_SEPARATOR_NAME, "&8⇧ Categorii")
        addDefault(Messages.SHOP_SEPARATOR_LORE, listOf("&8⇩ Obiecte"))
        addDefault(Messages.SHOP_QUICK_BUY_NAME, "&bCumpara Repede")
        addDefault(Messages.SHOP_QUICK_BUY_LORE, ArrayList<Any?>())
        addDefault(Messages.SHOP_QUICK_EMPTY_NAME, "&cSlot Liber!")
        addDefault(
            Messages.SHOP_QUICK_EMPTY_LORE,
            listOf(
                "&Acesta este un slot pentru Shopul Rapid!",
                "&bShift Click &7pe orice obiect din",
                "&7shop pentru a-l adăuga aici."
            )
        )
        addDefault(Messages.SHOP_CAN_BUY_COLOR, "&a")
        addDefault(Messages.SHOP_CANT_BUY_COLOR, "&c")
        addDefault(Messages.SHOP_LORE_STATUS_CAN_BUY, "&eClick pentru a cumpara!")
        addDefault(Messages.SHOP_LORE_STATUS_CANT_AFFORD, "&cNu ai destul {currency}!")
        addDefault(Messages.SHOP_LORE_STATUS_MAXED, "&aNIVEL MAXIM!")
        addDefault(Messages.SHOP_LORE_QUICK_ADD, "&bShift Click pentru Shop Rapid")
        addDefault(Messages.SHOP_LORE_QUICK_REMOVE, "&bShift Click pentru a șterge din Shop Rapid!")


        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "&8Blocuri",
            "&aBlocuri",
            listOf("&eClick pentru a afișa!")
        )
        addContentMessages(
            this, "wool", ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "{color}Lana", listOf(
                "&7Preț: &f{cost} {currency}", "", "&7Great for bridging across", "&7islands. Turns into your team's",
                "&7color.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "clay",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Lut Întărit",
            listOf(
                "&7Preț: {cost} {currency}",
                "",
                "&7Bloc esențial pentru a proteja patul.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "glass",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Sticlă Anti-Explozibil",
            listOf(
                "&7Preț: {cost} {currency}",
                "",
                "&7Imună împotriva exploziilor.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "stone",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Piatră Din End",
            listOf(
                "&7Preț: {cost} {currency}",
                "",
                "&7Bloc solid pentru a proteja patul.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "ladder",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Scară",
            listOf(
                "&7Preț: {cost} {currency}",
                "",
                "&7Utilă pentru a salva pisicile blocate",
                "&7in copaci.",
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
                "&7Preț: {cost} {currency}",
                "",
                "&7Protecție extrema pentru pat.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "wood",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Lemn",
            listOf(
                "&7Preț: {cost} {currency}",
                "",
                "&7Bloc solid pentru a-ți proteja patul.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "&8Atac",
            "&aAtac",
            listOf("&eClick pentru a afișa!")
        )
        addContentMessages(
            this,
            "stone-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Sabie De Piatră",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "iron-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Sabie De Fier",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "diamond-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Sabie De Diamant",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "stick",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Băț (Împingere I)",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "&8Armură",
            "&aArmură",
            listOf("&eClick pentru a afișa!")
        )

        addContentMessages(
            this,
            "chainmail",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Armură Permanentă De Zale",
            listOf(
                "&7Preț: {cost} {currency}",
                "", "&7Pantaloni și cizme de zale", "&7cu care te vei spawna mereu.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "iron-armor",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Armură Permanentă De Fier",
            listOf(
                "&7Preț: {cost} {currency}",
                "", "&7Pantaloni și cizme de fier", "&7cu care te vei spawna mereu.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "diamond-armor",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Armură Permanentă De Diamant",
            listOf(
                "&7Preț: {cost} {currency}",
                "",
                "&7Pantaloni și cizme de diamant",
                "&7cu care te vei spawna mereu.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_TOOLS,
            "&8Unelte",
            "&aUnelte",
            listOf("&eClick pentru a afișa!")
        )
        addContentMessages(
            this, "shears", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Foarfece Permanente", listOf(
                "&7Preț: {cost} {currency}",
                "",
                "&7Perfecte pentru a tăia lâna. Te vei",
                "&7spawna mereu cu ele in inventar.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "pickaxe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Târnăcop {tier}", listOf(
                "&7Preț: {cost} {currency}",
                "&7Nivel: &e{tier}",
                "",
                "&7Este un obiect upgradabil.",
                "&7Vei pierde cate un nivel atunci cand",
                "&7vei muri!",
                "",
                "&7Te vei respawna mereu",
                "&7cu cel putin primul nivel",
                "&7al acestui obiect.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "axe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Topor {tier}", listOf(
                "&7Preț: {cost} {currency}",
                "&7Tier: &e{tier}",
                "",
                "&7Este un obiect upgradabil.",
                "&7Vei pierde cate un nivel atunci cand",
                "&7vei muri!",
                "",
                "&7Te vei respawna mereu",
                "&7cu cel putin primul nivel",
                "&7al acestui obiect.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "&8Atac La Distanță",
            "&aAtac La Distanță",
            listOf("&eClick pentru a afișa!")
        )
        addContentMessages(
            this,
            "arrow",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Sageți",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow1",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Arc",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow2",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Arc (Putere I)",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow3",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Arc (Putere I, Împingere I)",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "&8Poțiuni",
            "&aPotțiuni",
            listOf("&eClick pentru a afișa!")
        )
        addContentMessages(
            this,
            "speed-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Viteză II (45 secunde)",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "jump-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Săritură V (45 secunde)",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "invisibility",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Invizibilitate (30 secunde)",
            listOf("&7Preț: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "&8Utilaje",
            "&aUtilaje",
            listOf("&eClick pentru a afișa!")
        )
        addContentMessages(
            this,
            "golden-apple",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Măr Auriu",
            listOf(
                "&7Preț: {cost} {currency}",
                "",
                "&7Vindecător bine rotunjit.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "bedbug", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}BedBug", listOf(
                "&7Preț: {cost} {currency}",
                "",
                "&7Spawnează un Silverfish acolo unde",
                "&7aterizează bugărul de zăpadă, pentru a",
                "&7distrage inamicii. Trăiește 15 secunde.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "dream-defender",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Apărătorul Viselor",
            listOf(
                "&7Preț: {cost} {currency}", "", "&7Un Iron Golem care te ajută să",
                "&7îți protejezi baza. Trăiește 4 minute.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "fireball", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Bilă De Foc", listOf(
                "&7Preț: {cost} {currency}", "", "&7Click-dreapta pentru a lansa!",
                "&7Împinge inamicii care merg pe", "&7poduri.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "tnt", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}TNT", listOf(
                "&7Preț: {cost} {currency}", "", "&7Se aprinde instant, potrivit",
                "&7pentru a exploda chestii!", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "ender-pearl", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Ender Pearl", listOf(
                "&7Preț: {cost} {currency}", "", "&7Cel mai rapid mod pentru a invada",
                "&7bazele inamice.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "water-bucket", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Găleată Cu Apă", listOf(
                "&7Preț: {cost} {currency}", "", "&7Util pentru a incetini",
                "&7inamicii. Te poate proteja și", "&7împotriva TNT-ilui.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "bridge-egg", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Bridge Egg", listOf(
                "&7Preț: {cost} {currency}", "", "&7Acest ou creează un pod in",
                "&7direcția in care este aruncat.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "magic-milk", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Lapte Magic", listOf(
                "&7Preț: {cost} {currency}", "", "&7Evită capcanele timp de 60",
                "&7de secunde după ce l-ai băut.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "sponge", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Burete", listOf(
                "&7Preț: {cost} {currency}", "", "&7Util pentru a absorbi apa.",
                "", "{quick_buy}", "{buy_status}"
            )
        )

        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "&aClick pentru a cumpara!")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY, "&cNu ai destul(e) {currency}")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_LOCKED, "&cLBLOCAT")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED, "&aDEBLOCAT")
        addDefault("upgrades.Default.generators.tier1.name", "&eGenerator Fier")
        addDefault(
            "upgrades.Default.generators.tier1.lore",
            listOf(
                "&7Creste rata de spawnare a Fierului",
                "&7si a Aurului cu 50%..",
                "",
                "&7Pret:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.generators.tier2.name", "&eGenerator Aur")
        addDefault(
            "upgrades.Default.generators.tier2.lore",
            listOf(
                "&7Creste rata de spawnare a Fierului",
                "&7si a Aurului cu 100%..",
                "",
                "&7Pret:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.generators.tier3.name", "&eGenerator Emerald")
        addDefault(
            "upgrades.Default.generators.tier3.lore",
            listOf(
                "&7Activeaza generatorul de Emeralde",
                "&7in baza echipei tale.",
                "",
                "&7Pret:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.maniacMiner.tier1.name", "&eManiac Miner")
        addDefault(
            "upgrades.Default.maniacMiner.tier1.lore",
            listOf(
                "&7Toti jucatorii din echipa ta vor primi",
                "&7efectul Graba I permanent.",
                "",
                "&7Pret:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.sharpSword.tier1.name", "&eSharpened Swords")
        addDefault(
            "upgrades.Default.sharpSword.tier1.lore",
            listOf(
                "&7Echipa ta va primi Sharpness I",
                "&7pe toate sabiile!",
                "",
                "&7Pret:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.reinforced.tier1.name", "&eReinforced Armor")
        addDefault(
            "upgrades.Default.reinforced.tier1.lore",
            listOf(
                "&7Echipa ta va primi Protectie I",
                "&7pe toata armura!",
                "",
                "&7Pret:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.trap.tier1.name", "&eEste o capcana!")
        addDefault(
            "upgrades.Default.trap.tier1.lore",
            listOf(
                "&7Urmatorul inamic care va intra in",
                "&7baza ta va primi efectul de Orbire ",
                "&7si de Incetinire!",
                "",
                "&7Pret:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.miningFatigue.tier1.name", "&eMiner Fatigue Trap")
        addDefault(
            "upgrades.Default.miningFatigue.tier1.lore",
            listOf(
                "&7Urmatorul inamic care va intra in",
                "&7baza ta va primi efectul de Oboseala",
                "&7pentru 10 secunde!",
                "",
                "&7Pret:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault("upgrades.Default.healPool.tier1.name", "&eHeal Pool")
        addDefault(
            "upgrades.Default.healPool.tier1.lore",
            listOf(
                "&7Creeaza un camp de Regenerare",
                "&7a vietii in baza ta!",
                "",
                "&7Pret:&b {cost} {currency}",
                "",
                "{loreFooter}"
            )
        )
        addDefault(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT, "&a{player} a cumparat &6{upgradeName}")

        addDefault(Messages.MEANING_NO_TRAP, "No trap!")
        addDefault(Messages.FORMAT_SPECTATOR_TARGET, "{targetTeamColor}{targetDisplayName}")
        addDefault(Messages.FORMAT_UPGRADE_TRAP_COST, "&7Cost: {currencyColor}{cost} {currency}")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD, "&e")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD, "&c")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_UNLOCKED, "&a")
        addDefault(Messages.FORMAT_UPGRADE_TIER_LOCKED, "&7")
        addDefault(Messages.FORMAT_UPGRADE_TIER_UNLOCKED, "&a")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "{color}Click to purchase!")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY, "{color}You don't have enough {currency}")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_LOCKED, "&cLOCKED")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED, "{color}UNLOCKED")
        addDefault(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT, "&a{player} purchased &6{upgradeName}")
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-1"),
            "{color}Iron Forge"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "forge"),
            listOf(
                "&7Upgrade resource spawning on",
                "&7your island.",
                "",
                "{tier_1_color}Tier 1: +50% Resources, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tier 2: +100% Resources, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Tier 3: Spawn emeralds, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Tier 4: +200% Resources, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-2"),
            "{color}Golden Forge"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-3"),
            "{color}Emerald Forge"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-4"),
            "{color}Molten Forge"
        )
        addDefault(Messages.UPGRADES_CATEGORY_ITEM_NAME_PATH + "traps", "&eBuy a trap")
        addDefault(
            Messages.UPGRADES_CATEGORY_ITEM_LORE_PATH + "traps",
            listOf("&7Purchased traps will be", "&7queued on the right.", "", "&eClick to browse!")
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "swords").replace("{tier}", "tier-1"),
            "{color}Sharpened Swords"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "swords"),
            listOf(
                "&7Your team permanently gains",
                "&7Sharpness I on all swords and",
                "&7axes!",
                "",
                "{tier_1_color}Cost: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-1"),
            "{color}Reinforced Armor I"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "armor"),
            listOf(
                "&7Your team permanently gains",
                "&7Protection on all armor pieces!",
                "",
                "{tier_1_color}Tier 1: Protection I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tier 2: Protection II, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Tier 3: Protection III, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Tier 4: Protection IV, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-2"),
            "{color}Reinforced Armor II"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-3"),
            "{color}Reinforced Armor III"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-4"),
            "{color}Reinforced Armor IV"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-1"),
            "{color}Maniac Miner I"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "miner"),
            listOf(
                "&7All players on your team",
                "&7permanently gain Haste.",
                "",
                "{tier_1_color}Tier 1: Haste I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tier 2: Haste II, &b{tier_2_cost} {tier_2_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-2"),
            "{color}Maniac Miner II"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "heal-pool").replace("{tier}", "tier-1"),
            "{color}Heal Pool"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "heal-pool"),
            listOf(
                "&7Creates a Regeneration field",
                "&7around yor base!",
                "",
                "{tier_1_color}Cost: &b{tier_1_cost} {tier_1_currency}",
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
                "&7Your team will have 2 dragons",
                "&7instead of 1 during deathmatch!",
                "",
                "{tier_1_color}Cost: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "glass", "&8⬆&7Purchasable")
        addDefault(Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "glass", listOf("&8⬇&7Traps Queue"))
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "first", "{color}Trap #1: {name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "first",
            listOf("&7The first enemy to walk", "&7into your base will trigger", "&7this trap!")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "first",
            listOf(
                "",
                "&7Purchasing a trap will",
                "&7queue it here. Its cost",
                "&7will scale based on the",
                "&7number of traps queued.",
                "",
                "&7Next trap: &b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "second", "{color}Trap #2: {name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "second",
            listOf("&7The second enemy to walk", "&7into your base will trigger", "&7this trap!")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "second",
            listOf(
                "",
                "&7Purchasing a trap will",
                "&7queue it here. Its cost",
                "&7will scale based on the",
                "&7number of traps queued.",
                "",
                "&7Next trap: &b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "third", "{color}Trap #3: {name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "third",
            listOf("&7The third enemy to walk", "&7into your base will trigger", "&7this trap!")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "third",
            listOf(
                "",
                "&7Purchasing a trap will",
                "&7queue it here. Its cost",
                "&7will scale based on the",
                "&7number of traps queued.",
                "",
                "&7Next trap: &b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "1", "{color}It's a trap!")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "1",
            listOf("&7Inflicts Blindness and Slowness", "&7for 5 seconds.", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "2", "{color}Counter-Offensive Trap")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "2",
            listOf("&7Grants Speed I for 15 seconds to", "&7allied players near your base.", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "3", "{color}Alarm Trap")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "3",
            listOf("&7Reveales invisible players as", "&7well as their name and team.", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "4", "{color}Miner Fatigue Trap")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "4",
            listOf("&7Inflict Mining Fatigue for 10", "&7seconds.", "")
        )
        addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "back", "&aBack")
        addDefault(
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "back",
            listOf("&7To Upgrades & Traps")
        )
        addDefault(Messages.UPGRADES_CATEGORY_GUI_NAME_PATH + "traps", "&8Queue a trap")
        addDefault(Messages.UPGRADES_TRAP_QUEUE_LIMIT, "&cTrap queue full!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_MSG, "&c&l{trap} a fost activată!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_TITLE, "&cCAPCANĂ ACTIVATĂ!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_SUBTITLE, "&fCapcana {trap} a fost activată!")
        addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_MSG + "3",
            "&c&lAlarm trap set off by &7&l{player} &c&lfrom {color}&l{team} &c&lteam!"
        )
        addDefault(Messages.UPGRADES_TRAP_CUSTOM_TITLE + "3", "&c&lALARM!!!")
        addDefault(Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + "3", "&fAlarm trap set off by {color}{team} &fteam!")
        save()
        setPrefix(m(Messages.PREFIX))
    }
}
