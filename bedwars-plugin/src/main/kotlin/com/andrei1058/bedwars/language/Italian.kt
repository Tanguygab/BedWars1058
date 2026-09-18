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

class Italian : Language(BedWars.INSTANCE, "it", "Italiano") {
    init {
        val bw = BedWars.MAIN_COMMAND

        options().setHeader(listOf("Traduzione in italiano di Fabian03#4583 aggiornata da Xx_yuri2005_xX"))

        addDefault(
            Messages.COMMAND_MAIN,
            listOf(
                "",
                "&2▪ &7/$bw stats",
                "&2▪ &7/$bw join &o<arena/gruppo>",
                "&2▪ &7/$bw leave",
                "&2▪ &7/$bw lang",
                "&2▪ &7/$bw gui",
                "&2▪ &7/$bw start &3(vip)"
            )
        )
        addDefault(Messages.COMMAND_JOIN_USAGE, "§a▪ §7Utilizzo: /$bw join §o<arena/gruppo>")
        addDefault(Messages.COMMAND_NOT_ALLOWED_IN_GAME, "{prefix}&cNon puoi eseguire questa azione.")
        addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL,
            "{prefix}&cArena piena!\n&aPrendi in considerazione il fatto di donare per entrare anche quando le arene sono piene. &7&o(clicca qui)"
        )
        addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS,
            "{prefix}&cArena piena.\n&cSappiamo che sei un donatore ma al momento questa arena è piena di staffer o donatori."
        )
        addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&e{player} si è disconnesso!")
        addDefault(
            Messages.COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND,
            "{prefix}&cNon esiste nessun gruppo o arena chiamato: {name}"
        )
        addDefault(Messages.COMMAND_JOIN_NO_EMPTY_FOUND, "{prefix}&cNon ci sono arene disponibili al momento ;(")
        addDefault(Messages.COMMAND_LEAVE_DENIED_NOT_IN_ARENA, "{prefix}&cNon sei in gioco!")
        addDefault(Messages.COMMAND_LANG_LIST_HEADER, "{prefix} &2Lingue disponibili:")
        addDefault(Messages.COMMAND_LANG_LIST_FORMAT, "&a▪  &7{iso} - &f{name}")
        addDefault(Messages.COMMAND_LANG_USAGE, "{prefix}&7Utilizzo: /lang &f&o<iso>")
        addDefault(Messages.COMMAND_LANG_SELECTED_NOT_EXIST, "{prefix}&cQuesta lingua non esiste!")
        addDefault(Messages.COMMAND_LANG_SELECTED_SUCCESSFULLY, "{prefix}&aLingua modificata!")
        addDefault(Messages.COMMAND_LANG_USAGE_DENIED, "{prefix}&cNon puoi cambiare la lingua durante il gioco.")
        addDefault(Messages.COMMAND_TP_PLAYER_NOT_FOUND, "{prefix}&cPlayer not found!")
        addDefault(Messages.COMMAND_TP_NOT_IN_ARENA, "{prefix}&cThis player is not in a bedwars arena!")
        addDefault(
            Messages.COMMAND_TP_NOT_STARTED,
            "{prefix}&cL'arena dove si trova il giocatore non è ancora inziata!"
        )
        addDefault(Messages.COMMAND_TP_USAGE, "{prefix}&cUsage: /bw tp <username>")
        addDefault(
            Messages.COMMAND_JOIN_DENIED_PARTY_TOO_BIG,
            "{prefix}&cIl tuo party è troppo grande per entrare in questa arena come una squadra :("
        )
        addDefault(
            Messages.COMMAND_JOIN_DENIED_NOT_PARTY_LEADER,
            "{prefix}&cSolo il creatore del party può scegliere l'arena."
        )
        addDefault(
            Messages.COMMAND_JOIN_PLAYER_JOIN_MSG,
            "{prefix}&7{player} &eè entrato in gioco (&b{on}&e/&b{max}&e)!"
        )
        addDefault(Messages.COMMAND_REJOIN_PLAYER_RECONNECTED, "{prefix}&7{player} &esi è riconnesso!")
        addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&7{player} &esi è disconnesso!")

        addDefault(
            Messages.COMMAND_PARTY_HELP, listOf(
                "&6▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&aComandi del party:",
                "&e/party help &7- &bStampa questa lista di comandi",
                "&e/party invite <player> &7- &bInvita il giocatore nel tuo party",
                "&e/party leave &7- &bLascia il party attuale",
                "&e/party remove <player> &7- &bRimuovi il giocatore dal party",
                "&e/party info &7- &bShow party members and owner",
                "&e/party promote <player> &7- &bTransfer party ownership",
                "&e/party accept <player> &7- &bAccetta un invito al party",
                "&e/party disband &7- &bScioglie il tuo party"
            )
        )
        addDefault(Messages.COMMAND_PARTY_INVITE_USAGE, "{prefix}&eUtilizzo: &7/party invite <player>")
        addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &enon è online!")
        addDefault(Messages.COMMAND_PARTY_INVITE_SENT, "{prefix}&eInvito inviato a &7{player}&6.")
        addDefault(
            Messages.COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG,
            "{prefix}&b{player} &eti ha invitato nel party! &o&7(Clicca per accettare)"
        )
        addDefault(
            Messages.COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF,
            "{prefix}&cNon puoi invitare te stesso!"
        )
        addDefault(
            Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE,
            "{prefix}&cNon ci sono richieste di invito ad un party da accettare"
        )
        addDefault(Messages.COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY, "{prefix}&eSei già in un party!")
        addDefault(
            Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS,
            "{prefix}&cSolo il proprietario del party può eseguire questa azione!"
        )
        addDefault(Messages.COMMAND_PARTY_ACCEPT_USAGE, "{prefix}&eUtilizzo: &7/party accept <player>")
        addDefault(Messages.COMMAND_PARTY_ACCEPT_SUCCESS, "{prefix}&7{player} &eè entrato nel party!")
        addDefault(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY, "{prefix}&cNon sei in un party!")
        addDefault(
            Messages.COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND,
            "{prefix}&cNon puoi uscire dal tuo party!\n&eUtilizzo corretto: &b/party disband"
        )
        addDefault(Messages.COMMAND_PARTY_LEAVE_SUCCESS, "{prefix}&7{player} &eè uscito dal party!")
        addDefault(Messages.COMMAND_PARTY_DISBAND_SUCCESS, "{prefix}&eParty sciolto!")
        addDefault(Messages.COMMAND_PARTY_REMOVE_USAGE, "{prefix}&eUtilizzo: &e/party remove <player>")
        addDefault(Messages.COMMAND_PARTY_REMOVE_SUCCESS, "{prefix}&7{player} &eè stato rimosso dal party.")
        addDefault(
            Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER,
            "{prefix}&7{player} &enon è nel tuo party!"
        )
        addDefault(
            Messages.COMMAND_PARTY_PROMOTE_SUCCESS,
            "{prefix}&eHai promosso con successo {player} a proprietario"
        )
        addDefault(Messages.COMMAND_PARTY_PROMOTE_OWNER, "{prefix}&eSei stato promosso a proprietario del party")
        addDefault(
            Messages.COMMAND_PARTY_PROMOTE_NEW_OWNER,
            "{prefix}&7 &e{player} è stato promosso a proprietario"
        )
        addDefault(Messages.COMMAND_PARTY_INFO_OWNER, "\n{prefix}&eIl proprietario del party è: &7{owner}")
        addDefault(Messages.COMMAND_PARTY_INFO_PLAYERS, "{prefix}&eI membri del party sono:")
        addDefault(Messages.COMMAND_PARTY_INFO_PLAYER, "&7{player}")
        addDefault(
            Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS,
            "{prefix}&cIl comando non è stato trovato o non hai abbastanza permessi!"
        )
        addDefault(Messages.COMMAND_FORCESTART_NOT_IN_GAME, "§c▪ §7Non sei in gioco!")
        addDefault(Messages.COMMAND_FORCESTART_SUCCESS, "§c▪ §7Conto alla rovescia diminuito!")
        addDefault(
            Messages.COMMAND_FORCESTART_NO_PERM,
            "{prefix}&7Non puoi forzare l'inizio del gioco.\n§7Prendi in considerazione il fatto di donare per avere più cose."
        )
        addDefault(
            Messages.COMMAND_JOIN_SPECTATOR_MSG,
            "{prefix}§6Stai spectando l'arena §9{arena}§6.\n{prefix}§ePuoi uscire dall'arena in qualsiasi momento utilizzando §c/leave§e."
        )
        addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eè offline!")
        addDefault(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG, "&cGli spettatori non sono ammessi in questa arena!")
        addDefault(Messages.COMMAND_COOLDOWN, "&cAspetta {seconds} scondi prima di usare ancora questo comando!")
        addDefault(
            Messages.ARENA_JOIN_VIP_KICK,
            "{prefix}&cIl comando non è stato trovato o non hai abbastanza permessi!\n&aPotresti fare una donazione per ottenere permessi vip. &7&o(click)"
        )
        addDefault(
            Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT,
            "{prefix}§cNon ci sono abbastanza giocatori! Conto alla rovescia fermato!"
        )
        addDefault(Messages.ARENA_RESTART_PLAYER_KICK, "{prefix}&eL'arena in cui ti trovavi si sta restartando.")
        addDefault(Messages.ARENA_STATUS_PLAYING_NAME, "&cIn gioco")
        addDefault(Messages.ARENA_STATUS_RESTARTING_NAME, "&4In riavvio")
        addDefault(Messages.ARENA_STATUS_WAITING_NAME, "&2In attesa §c{full}")
        addDefault(Messages.ARENA_STATUS_STARTING_NAME, "&6In avvio §c{full}")
        addDefault(Messages.ARENA_GUI_INV_NAME, "&8Arene disponibili")
        addDefault(Messages.ARENA_GUI_ARENA_CONTENT_NAME, "&a&l{name}")
        addDefault(
            Messages.ARENA_GUI_ARENA_CONTENT_LORE,
            listOf(
                "",
                "&7Stato: {status}",
                "&7Giocatori: &f{on}&7/&f{max}",
                "&7Tipo: &a{group}",
                "",
                "&aClick-Sinistro per entrare.",
                "&eClick-Destro per guardare."
            )
        )
        addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_NAME, "&r{serverIp}")
        addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_LORE, emptyList<String>())
        addDefault(
            Messages.ARENA_STATUS_START_COUNTDOWN_CHAT,
            "{prefix}&eIl gioco avrà inizio in &6{time} &esecondi!"
        )
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_TITLE, " ")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE, "&a{second}")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-5", "&e❺")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-4", "&e❹")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-3", "&c❸")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-2", "&c❷")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-1", "&c❶")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_TITLE, " ")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_SUB_TITLE, "&cAttendendo più giocatori..")
        addDefault(
            Messages.ARENA_STATUS_START_PLAYER_TUTORIAL, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lBedWars", "",
                "&e&l    Proteggi il tuo letto e distruggi quello degli avversari.",
                "&e&l      Potenzia te e il tuo team collezionando",
                "&e&l   Ferro, Oro, Smeraldi, e Diamanti dai generatori",
                "&e&l             per avere accesso a forti potenziamenti.", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        addDefault(Messages.ARENA_STATUS_START_PLAYER_TITLE, "&aVIA")
        addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_NAME, "&8Teletrasporto")
        addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME, "{vPrefix}{player}")
        addDefault(
            Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE,
            listOf("&7Vita: &f{health}%", "&7Cibo: &f{food}", "", "&7Click-Sinistro per guardare")
        )
        addDefault(Messages.ARENA_SPECTATOR_LEAVE_ITEM_NAME, "&c&lRitorna alla lobby")
        addDefault(
            Messages.ARENA_SPECTATOR_LEAVE_ITEM_LORE,
            listOf("&7Click-destro per ritornare alla lobby!")
        )
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE, "&aGuardando &7{player}")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE, "&cSHIFT per uscire")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE, "&eUscita Dalla Modalità Spettatore")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE, "")
        addDefault(
            Messages.ARENA_LEAVE_PARTY_DISBANDED,
            "{prefix}§cIl proprietario del party è uscito e il party è stato sciolto!"
        )
        addDefault(
            Messages.ARENA_JOIN_DENIED_SELECTOR,
            "{prefix}&cCi dispiace, ma attualmente non puoi entrare in quest'arena. Usa Click-destro per guardare!"
        )
        addDefault(
            Messages.ARENA_SPECTATE_DENIED_SELECTOR,
            "{prefix}&cCi dispiace, ma attualmente non puoi guardare questa partita. Usa Click-sinistro per giocare!"
        )
        addDefault(
            Messages.ARENA_JOIN_DENIED_NO_PROXY,
            "&cMi spiace ma devi entrare in un'arena usando BedWarsProxy. \n&eSe vuoi impostare un'arena assicurati di darti il permesso bw.setup in modo da entrare direttamente nel server!"
        )
        addDefault(Messages.GENERATOR_HOLOGRAM_TIER, "&eLivello &c{tier}")
        addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND, "&b&lDiamante")
        addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD, "&a&lSmeraldo")
        addDefault(Messages.GENERATOR_HOLOGRAM_TIMER, "&eDrop in &c{seconds} &esecondi")
        addDefault(
            Messages.GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT,
            "{prefix}&eI generatori di {generatorType} &esono stati potenziati a livello &c{tier}"
        )
        addDefault(Messages.FORMATTING_TEAM_WINNER_FORMAT, "      {TeamColor}{TeamName} &7- {members}")
        addDefault(Messages.FORMATTING_SOLO_WINNER_FORMAT, "                 {TeamColor}{TeamName} &7- {members}")
        addDefault(Messages.FORMATTING_CHAT_LOBBY, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        addDefault(Messages.FORMATTING_CHAT_WAITING, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        addDefault(
            Messages.FORMATTING_CHAT_SHOUT,
            "{level}{vPrefix}&6[SHOUT] {team} &7{player}&f{vSuffix}: {message}"
        )
        addDefault(Messages.FORMATTING_CHAT_TEAM, "{level}{vPrefix}&f{team}&7 {player}{vSuffix}: {message}")
        addDefault(Messages.FORMATTING_CHAT_SPECTATOR, "{level}{vPrefix}&7[SPECTATOR] {player}{vSuffix}: {message}")
        addDefault(Messages.FORMATTING_SCOREBOARD_HEALTH, listOf("&c❤", "&aVita"))
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
        addDefault(Messages.FORMATTING_ACTION_BAR_TRACKING, "&fObbiettivo: {team} &f- Distanza: {distance}m")
        addDefault(Messages.FORMATTING_GENERATOR_TIER1, "I")
        addDefault(Messages.FORMATTING_GENERATOR_TIER2, "II")
        addDefault(Messages.FORMATTING_GENERATOR_TIER3, "III")
        addDefault(Messages.FORMATTING_STATS_DATE_FORMAT, "yyyy/MM/dd HH:mm")
        addDefault(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH, "▮ ")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_TEAM, "{TeamColor}[{TeamName}]")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SHOUT, "&6[SHOUT]")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SPECTATOR, "&7[SPETTATORE]")
        addDefault(Messages.MEANING_SHOUT, "shout")
        addDefault(Messages.MEANING_NOBODY, "Nessuno")
        addDefault(Messages.MEANING_FULL, "Pieno")
        addDefault(Messages.MEANING_IRON_SINGULAR, "Ferro")
        addDefault(Messages.MEANING_IRON_PLURAL, "Ferro")
        addDefault(Messages.MEANING_GOLD_SINGULAR, "Oro")
        addDefault(Messages.MEANING_GOLD_PLURAL, "Oro")
        addDefault(Messages.MEANING_EMERALD_SINGULAR, "Smeraldo")
        addDefault(Messages.MEANING_EMERALD_PLURAL, "Smeraldi")
        addDefault(Messages.MEANING_DIAMOND_SINGULAR, "Diamante")
        addDefault(Messages.MEANING_DIAMOND_PLURAL, "Diamanti")
        addDefault(Messages.MEANING_VAULT_SINGULAR, "$")
        addDefault(Messages.MEANING_VAULT_PLURAL, "$")
        addDefault(Messages.MEANING_NEVER, "Never")
        addDefault(Messages.INTERACT_CANNOT_PLACE_BLOCK, "{prefix}&cNon puoi piazzare blocchi in questa area!")
        addDefault(
            Messages.INTERACT_CANNOT_BREAK_BLOCK,
            "{prefix}&cPuoi rompere solo i blocchi che sono stati piazzati dai giocatori!"
        )
        addDefault(Messages.INTERACT_CANNOT_BREAK_OWN_BED, "&cNon puoi distruggere il tuo letto!")
        addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT,
            "\n&f&lLETTO DISTRUTTO > Il letto del team {TeamColor}{TeamName} &7è stato fatto a pezzi da {PlayerColor}{PlayerName}&7!\n"
        )
        addDefault(Messages.INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT, "&cLETTO DISTRUTTO!")
        addDefault(Messages.INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT, "&fNon verrai più respawnato!")
        addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM,
            "&f&lLETTO DISTRUTTO > &7Il tuo letto è stato distrutto da {PlayerColor}{PlayerName}&7!"
        )
        addDefault(
            Messages.INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED,
            "&cNon puoi aprire questa cesta, perchè il team non è stato eliminato."
        )
        addDefault(
            Messages.INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN,
            "&cNon sei più invisibile perchè hai preso danno!"
        )
        addDefault(Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL, "{PlayerColor}{PlayerName} &7è caduto nel vuoto.")
        addDefault(
            Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7è caduto nel vuoto. &b&lUCCISIONE FINALE!"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7è caduto nel vuoto con l'aiuto di {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7è caduto nel vuoto con l'aiuto di {KillerColor}{KillerName}&7. &b&lUCCISIONE FINALE!"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7è stato spinto da {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7è stato spinto da {KillerColor}{KillerName}&7. &b&lUCCISIONE FINALE!"
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7è saltato in aria a causa di {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7è saltato in aria a causa di {KillerColor}{KillerName}&7. &b&lUCCISIONE FINALE!"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7è stato ucciso da {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7è stato ucciso da {KillerColor}{KillerName}&7. &b&lUCCISIONE FINALE!"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_REGULAR,
            "{PlayerColor}{PlayerName} &7si è disconnesso mentre combatteva con {KillerColor}{KillerName}&7."
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_FINAL,
            "{PlayerColor}{PlayerName} &7si è disconnesso mentre combatteva con {KillerColor}{KillerName}&7. &b&lUCCISIONE FINALE!"
        )
        addDefault(Messages.PLAYER_DIE_RESPAWN_TITLE, "&cSEI MORTO!")
        addDefault(Messages.PLAYER_DIE_RESPAWN_SUBTITLE, "&eVerrai respawnato in &c{time} &esecondi!")
        addDefault(Messages.PLAYER_DIE_RESPAWN_CHAT, "{prefix}&eVerrai respawnato in &c{time} &esecondi!")
        addDefault(Messages.PLAYER_DIE_RESPAWNED_TITLE, "&aRESPAWNATO!")
        addDefault(Messages.PLAYER_DIE_ELIMINATED_CHAT, "{prefix}&cSei stato eliminato!")
        addDefault(Messages.PLAYER_HIT_BOW, "{prefix}{TeamColor}{PlayerName} &7è rimasto con &c{amount} &7HP!")
        addDefault(Messages.PLAYER_DIE_UNKNOWN_REASON_REGULAR, "{PlayerColor}{PlayerName} &7è morto.")
        addDefault(
            Messages.PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7è morto. &b&lUCCISIONE FINALE!"
        )
        addDefault(
            Messages.PLAYER_DIE_SHOOT_REGULAR,
            "{PlayerColor}{PlayerName} &7è stato sparato da {KillerColor}{KillerName}&7!"
        )
        addDefault(
            Messages.PLAYER_DIE_SHOOT_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7è stato sparato da {KillerColor}{KillerName}&7! &b&lUCCISIONE FINALE!"
        )
        addDefault(
            Messages.PLAYER_DIE_DEBUG_REGULAR,
            "{PlayerColor}{PlayerName} &7è stato ucciso dal BedBug {KillerColor}{KillerTeamName}!"
        )
        addDefault(
            Messages.PLAYER_DIE_DEBUG_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7è stato ucciso dal BedBug {KillerColor}{KillerTeamName}! &b&lUCCISIONE FINALE!"
        )
        addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_REGULAR,
            "{PlayerColor}{PlayerName} &7è stato ucciso dal Dream Defender {KillerColor}{KillerTeamName}!"
        )
        addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7è stato ucciso dal Dream Defender {KillerColor}{KillerTeamName}! &b&lUCCISIONE FINALE!"
        )
        addDefault(Messages.PLAYER_DIE_REWARD_DIAMOND, "{prefix}&b+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_EMERALD, "{prefix}&a+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_IRON, "{prefix}&f+{amount} {meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_GOLD, "{prefix}&6+{amount} {meaning}")
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR,
            "{PlayerColor}{PlayerName} &7è stato colpito da una bomba."
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7è stato colpito da una bomba. &b&lUCCISIONE FINALE!"
        )
        addDefault(Messages.GAME_END_GAME_OVER_PLAYER_TITLE, "&c&lGAME OVER!")
        addDefault(Messages.GAME_END_VICTORY_PLAYER_TITLE, "&6&lVITTORIA!")
        addDefault(
            Messages.GAME_END_TOP_PLAYER_CHAT, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lBedWars", "", "{winnerFormat}", "", "",
                "&6                      &6⭐ &l1° Uccisore &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&e                        &l2° Uccisore &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&c                        &l3° Uccisore &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        addDefault(Messages.GAME_END_TEAM_WON_CHAT, "{prefix}{TeamColor}{TeamName} &aha vinto il gioco!")
        addDefault(Messages.BED_HOLOGRAM_DEFEND, "&c&lDifendi il tuo letto!")
        addDefault(Messages.BED_HOLOGRAM_DESTROYED, "&c&lIl tuo letto è stato distrutto!")
        addDefault(Messages.NPC_NAME_TEAM_UPGRADES, "&bTEAM UPGRADES,&e&lCLICK DESTRO")
        addDefault(Messages.NPC_NAME_SOLO_UPGRADES, "&bSOLO UPGRADES,&e&lCLICK DESTRO")
        addDefault(Messages.NPC_NAME_TEAM_SHOP, "&bITEM SHOP,&e&lCLICK DESTRO")
        addDefault(Messages.NPC_NAME_SOLO_SHOP, "&bITEM SHOP,&e&lCLICK DESTRO")
        addDefault(Messages.NEXT_EVENT_BEDS_DESTROY, "&cDistruzione letti")
        addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_II, "&fDiamante II")
        addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_III, "&fDiamante III")
        addDefault(Messages.NEXT_EVENT_DRAGON_SPAWN, "&fMorte Brusca")
        addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_II, "&fEmerald II")
        addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_III, "&fEmerald III")
        addDefault(Messages.NEXT_EVENT_GAME_END, "&4Fine gioco")
        addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED, "&cLETTO DISTRUTTO!")
        addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED, "&Tutti i letti sono stati distrutti!")
        addDefault(
            Messages.NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED,
            "{prefix}&c&lTutti i letti sono stati distrutti!"
        )
        addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH, "&cMorte Brusca")
        addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH, "")
        addDefault(
            Messages.NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH,
            "&cMORTE BRUSCA: &6&b{TeamDragons} Drago {TeamColor}{TeamName}!"
        )
        /* Lobby Command Items */
        addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "stats"), "&eStatistiche")
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fClick-destro per vedere", "le tue statistiche!")
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "arena-selector"),
            "&eSelettore Arena"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "arena-selector"),
            listOf("&fClick-destro Per selezionare un'arena!")
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "leave"),
            "&eTorna nella Lobby"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fClick-destro per lasciare BedWars!")
        )
        /* Pre Game Command Items */
        addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "stats"), "&eStatistiche")
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fClick-destro per vedere", "le tue statistiche!")
        )
        addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "leave"), "&eBack to Lobby")
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fClick-destro to leave the arena!")
        )
        /* Spectator Command Items */
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "teleporter"),
            "&eTeletrasporto"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "teleporter"),
            listOf("&fClick-destro to spectate a player!")
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "leave"),
            "&eTorna nella Lobby"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fClick-destro per lasciare BedWars!")
        )

        addDefault(Messages.REJOIN_NO_ARENA, "{prefix}&cNon c'è un'arena su cui ritornare!")
        addDefault(Messages.REJOIN_DENIED, "{prefix}&cNon puoi tornare più. Partita finita o letto distrutto.")
        addDefault(Messages.REJOIN_ALLOWED, "{prefix}&eStai entrando sull'arena &a{arena}&e!")

        /* save default items messages for stats gui */
        addDefault(Messages.PLAYER_STATS_GUI_INV_NAME, "&8Statistiche di {player}")
        addDefaultStatsMsg("wins", "&6Vittorie", "&f{wins}")
        addDefaultStatsMsg("losses", "&6Sconfitte", "&f{losses}")
        addDefaultStatsMsg("kills", "&6Uccisioni", "&f{kills}")
        addDefaultStatsMsg("deaths", "&6Decessi", "&f{deaths}")
        addDefaultStatsMsg("final-kills", "&6Uccisioni finali", "&f{finalKills}")
        addDefaultStatsMsg("final-deaths", "&6Decessi finali", "&f{finalDeaths}")
        addDefaultStatsMsg("beds-destroyed", "&6Letti distrutti", "&f{bedsDestroyed}")
        addDefaultStatsMsg("first-play", "&6Prima partita", "&f{firstPlay}")
        addDefaultStatsMsg("last-play", "&6Ultima partita", "&f{lastPlay}")
        addDefaultStatsMsg("games-played", "&6Partite giocate", "&f{gamesPlayed}")

        // Start of Sidebar
        addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fMappa: &a{map}",
                "",
                "&fGiocatori: &a{on}/{max}",
                "",
                "&fIn attesa&fIn attesa.,&fIn attesa..,&fIn attesa...",
                "",
                "&fMode: &a{group}",
                "&fVersione: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Guardando",
                "&fMappa: &a{map}",
                "",
                "&fGiocatori: &a{on}/{max}",
                "",
                "&fIn attesa&fIn attesa.,&fIn attesa..,&fIn attesa...",
                "",
                "&fMode: &a{group}",
                "&fVersione: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fMappa: &a{map}",
                "",
                "&fGiocatori: &a{on}/{max}",
                "",
                "&fInizio in &a{time}s",
                "",
                "&fMode: &a{group}",
                "&fVersione: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Guardando",
                "&fMappa: &a{map}",
                "",
                "&fGiocatori: &a{on}/{max}",
                "",
                "&fInizio in &a{time}s",
                "",
                "&fMode: &a{group}",
                "&fVersione: &7{version}",
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
                "&o&7Guardando {spectatorTarget}",
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
                "&o&7Guardando {spectatorTarget}",
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
                "&o&7Guardando {spectatorTarget}",
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
                "&o&7Guardando {spectatorTarget}",
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
                "&fUccisioni: &a{kills}",
                "&fUccisioni Finali: &a{finalKills}",
                "&fLetti Distrutti: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "3v3v3v3"),
            listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Guardando {spectatorTarget}",
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
                "&o&7Guardando {spectatorTarget}",
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
                "&fUccisioni: &a{kills}",
                "&fUccisioni Finali: &a{finalKills}",
                "&fLetti Distrutti: &a{beds}",
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
                "&fUccisioni: &a{kills}",
                "&fUccisioni Finali: &a{finalKills}",
                "&fLetti Distrutti: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "4v4v4v4"),
            listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Guardando {spectatorTarget}",
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
                "&o&7Guardando {spectatorTarget}",
                "&f{nextEvent} in &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&fUccisioni: &a{kills}",
                "&fUccisioni Finali: &a{finalKills}",
                "&fLetti Distrutti: &a{beds}",
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
        addDefault(
            Messages.SCOREBOARD_LOBBY, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&fLivello: {level}",
                "",
                "&fProgresso: &a{currentXp}&7/&b{requiredXp}",
                "{progress}",
                "",
                "&7{player}",
                "",
                "&fSoldi: &a{money}",
                "",
                "&fVittorie: &a{wins}",
                "&fUccisioni: &a{kills}",
                "", "&e{serverIp}"
            )
        )
        addDefault(
            Messages.TEAM_ELIMINATED_CHAT,
            "\n&f&lTEAM ELIMINATO > Il team {TeamColor}{TeamName} &cè stato eliminato\n"
        )
        addDefault(Messages.XP_REWARD_PER_MINUTE, "{prefix}&6+{xp} Esperienza BedWars ricevuta (Tempo di Gioco).")
        addDefault(Messages.XP_REWARD_WIN, "{prefix}&6+{xp} Esperienza BedWars ricevuta (Vittoria).")
        addDefault(Messages.XP_REWARD_PER_TEAMMATE, "{prefix}&6+{xp} Esperienza BedWars ricevuta (Supporto Team).")
        addDefault(
            Messages.XP_REWARD_BED_DESTROY,
            "{prefix}&6+{xp} Esperienza BedWars ricevuta (Distruzione letto)."
        )
        addDefault(Messages.XP_REWARD_REGULAR_KILL, "{prefix}&6+{xp} Esperienza BedWars ricevuta (Uccisione).")
        addDefault(Messages.XP_REWARD_FINAL_KILL, "{prefix}&6+{xp} Esperienza BedWars ricevuta (Uccisione Finale).")

        addDefault(Messages.MONEY_REWARD_PER_MINUTE, "{prefix}&6+{money} Coins (Tempo di Gioco).")
        addDefault(Messages.MONEY_REWARD_WIN, "{prefix}&6+{money} Coins (Vittoria).")
        addDefault(Messages.MONEY_REWARD_PER_TEAMMATE, "{prefix}&6+{money} Coins (Supporto Team).")
        addDefault(Messages.MONEY_REWARD_BED_DESTROYED, "{prefix}&6+{money} Coins (Distruzione letto).")
        addDefault(Messages.MONEY_REWARD_FINAL_KILL, "{prefix}&6+{money} Coins (Uccisione Regolare).")
        addDefault(Messages.MONEY_REWARD_REGULAR_KILL, "{prefix}&6+{money} Coins (Uccisione).")

        //shop
        addDefault(Messages.SHOP_INDEX_NAME, "&8Quick Buy")
        addDefault(Messages.SHOP_QUICK_ADD_NAME, "&8Aggiungendo al Quick Buy...")
        addDefault(
            Messages.SHOP_INSUFFICIENT_MONEY,
            "{prefix}&cNon hai abbastanza {currency}! Te ne occorre {amount} in più!"
        )
        addDefault(Messages.SHOP_NEW_PURCHASE, "{prefix}&aHai comprato &6{item}")
        addDefault(Messages.SHOP_ALREADY_BOUGHT, "{prefix}&Hai già comprato questo!")
        addDefault(Messages.SHOP_UTILITY_NPC_SILVERFISH_NAME, "{TeamColor}&l{TeamName} &r{TeamColor}Silverfish")
        addDefault(Messages.SHOP_UTILITY_NPC_IRON_GOLEM_NAME, "{TeamColor}{despawn}s &8[ {TeamColor}{health}&8]")
        addDefault(Messages.SHOP_SEPARATOR_NAME, "&8⇧ Categorie")
        addDefault(Messages.SHOP_SEPARATOR_LORE, listOf("&8⇩ Items"))
        addDefault(Messages.SHOP_QUICK_BUY_NAME, "&bQuick Buy")
        addDefault(Messages.SHOP_QUICK_BUY_LORE, ArrayList<Any?>())
        addDefault(Messages.SHOP_QUICK_EMPTY_NAME, "&cSlot libero!")
        addDefault(
            Messages.SHOP_QUICK_EMPTY_LORE,
            listOf(
                "&7Questo è uno slot Quick Buy!",
                "&bShift + Click &7su un item nello",
                "&7shop per aggungerlo qui."
            )
        )
        addDefault(Messages.SHOP_CAN_BUY_COLOR, "&a")
        addDefault(Messages.SHOP_CANT_BUY_COLOR, "&c")
        addDefault(Messages.SHOP_LORE_STATUS_CAN_BUY, "&eClick per comprare")
        addDefault(Messages.SHOP_LORE_STATUS_CANT_AFFORD, "&cNon hai abbastanza {currency}!")
        addDefault(Messages.SHOP_LORE_STATUS_MAXED, "&aMAXED!")
        addDefault(Messages.SHOP_LORE_QUICK_ADD, "&bShift Click per aggiungere al Quick Buy")
        addDefault(Messages.SHOP_LORE_QUICK_REMOVE, "&bShift Click per rimuovere dal Quick Buy!")

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "&8Blocchi",
            "&aBlocchi",
            listOf("&eClicca per sfogliare!")
        )
        addContentMessages(
            this,
            "wool",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Lana",
            listOf(
                "&7Costo: &f{cost} {currency}",
                "",
                "&7Buono per costruire ponti tra",
                "&7le isole. Prende il colore",
                "&7del tuo team.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "clay",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Argilla indurita",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Blocco base per proteggere  il letto.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "glass",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Vetro Anti Esplosione",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Immune alle esplosioni.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "stone",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Pietra dell'End",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Blocco solido per difendere il letto.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "ladder",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Scala",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Utile per salvare gatti bloccati",
                "&7sugli alberi.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "wood",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Legna",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Blocco solido per difendere il letto.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "&8Spade",
            "&aSpade",
            listOf("&eClicca per sfogliare!")
        )
        addContentMessages(
            this,
            "stone-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Spada di pietra",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "iron-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Spada di ferro",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "diamond-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Spada di diamante",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "stick",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Bastone (Contraccolpo I)",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "&8Armatura",
            "&aArmatura",
            listOf("&eClicca per sfogliare!")
        )
        addContentMessages(
            this,
            "chainmail",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Armatura di Maglia Permanente",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Chainmail leggings and boots",
                "&7which you will always spawn",
                "&7with.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "iron-armor",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Armatura di Ferro Permanente",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Iron leggings and boots which",
                "&7you will always spawn with.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "diamond-armor",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Armatura di Diamante Permanente",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Diamond leggings and boots which",
                "&7you will always crush with.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_TOOLS,
            "&8Utensili",
            "&aUtensili",
            listOf("&eClicca per sfogliare!")
        )
        addContentMessages(
            this, "shears", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Cesoie Permanenti", listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Utile per tagliare la lana.",
                "&7Respawnerai sempre con questo utensile.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "pickaxe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Piccone {tier}", listOf(
                "&7Costo: {cost} {currency}",
                "&7Tier: &e{tier}",
                "",
                "&7Questo è un item upgradabile.",
                "&7Perderai un tier alla tua morte.",
                "",
                "&7Respawnerai sempre con",
                "&7questo item avendo almeno",
                "&7il tier pià basso.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "axe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Ascia {tier}", listOf(
                "&7Costo: {cost} {currency}",
                "&7Tier: &e{tier}",
                "",
                "&7Questo è un item upgradabile.",
                "&7Perderai un tier alla tua morte.",
                "",
                "&7Respawnerai sempre con",
                "&7questo item avendo almeno",
                "&7il tier pià basso.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "&8Ranged",
            "&aArchi",
            listOf("&eClicca per sfogliare!")
        )
        addContentMessages(
            this,
            "arrow",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Frecce",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow1",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Arco",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow2",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Arco (Potenza I)",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow3",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Arco (Potenza I, Contraccolpo I)",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "&8Pozioni",
            "&aPozioni",
            listOf("&eClicca per sfogliare!")
        )

        addContentMessages(
            this,
            "speed-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Pozione Rapidità II (45 secondi)",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "jump-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Pozione Salto V (45 secondi)",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "invisibility",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Pozione Invisibilità (30 secondi)",
            listOf("&7Costo: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "&8Utilità",
            "&aUtilità",
            listOf("&eClicca per sfogliare!")
        )
        addContentMessages(
            this,
            "golden-apple",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Mela D'oro",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Mela rigeneratrice di vita.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "bedbug",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}BedBug",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Spawna un silverfish dove arriva",
                "&7la palla di neve, per distrare",
                "&7gli avversari. Vive 15 secondi.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "dream-defender",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Dream Defender",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Iron Golem che ti aiuta a proteggere",
                "&7la tua base. Vive 4 minuti.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "fireball",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Fireball",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Click-destro per lanciare! Ottima per",
                "&7spingere gli avversari che camminano",
                "&7sui ponti",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "tnt",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}TNT",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Si accende instantaneamente, utile per",
                "&7far esplodere cose!",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "ender-pearl",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Ender Pearl",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Il metodo pià veloce per invadere",
                "&7la base degli avversari.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "water-bucket",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Secchio D'acqua",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Utile per rallentare gli",
                "&7inamici. Può anche proteggere",
                "&7dalla TNT.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "bridge-egg",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Bridge Egg",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Questo uovo crea un ponte in sua",
                "&7direzione dopo averlo lanciato.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "magic-milk",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Latte Magico",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Evita le trappole per 60 secondi",
                "&7dopo averlo consumato.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "sponge",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Spugna",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Utile per succhiare l'acqua :)).",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "Compact Pop-up Tower",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Torre Pop-up Compatta",
            listOf(
                "&7Costo: {cost} {currency}",
                "",
                "&7Piazza una Torre Pop-up",
                "&7compatta per difenderti!",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addDefault(Messages.MEANING_NO_TRAP, "Nessuna Trappola!")
        addDefault(Messages.FORMAT_SPECTATOR_TARGET, "{targetTeamColor}{targetDisplayName}")
        addDefault(Messages.FORMAT_UPGRADE_TRAP_COST, "&7Costo: {currencyColor}{cost} {currency}")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD, "&e")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD, "&c")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_UNLOCKED, "&a")
        addDefault(Messages.FORMAT_UPGRADE_TIER_LOCKED, "&7")
        addDefault(Messages.FORMAT_UPGRADE_TIER_UNLOCKED, "&a")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "{color}Click per acquistare!")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY, "{color}Non hai abbastanza {currency}")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_LOCKED, "&cBLOCCATO")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED, "{color}SBLOCCATO")
        addDefault(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT, "&a{player} ha acquisistato &6{upgradeName}")
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-1"),
            "{color}Forgia di Ferro"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "forge"),
            listOf(
                "&7Potenzia la generazione di risorse",
                "&7nella tua isola.",
                "",
                "{tier_1_color}Tier 1: +50% Risorse, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tier 2: +100% Risorse, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Tier 3: Genera smeraldi, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Tier 4: +200% Risorse, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-2"),
            "{color}Forgia d'Oro"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-3"),
            "{color}Forgia di Smeraldi"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-4"),
            "{color}Forgia Finale"
        )
        addDefault(Messages.UPGRADES_CATEGORY_ITEM_NAME_PATH + "traps", "&eCompra una trappola")
        addDefault(
            Messages.UPGRADES_CATEGORY_ITEM_LORE_PATH + "traps",
            listOf(
                "&7Le trappole acquistate saranno",
                "&7messe in coda sulla destra.",
                "",
                "&eClick per sfogliare!"
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "swords").replace("{tier}", "tier-1"),
            "{color}Spade Affilate"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "swords"),
            listOf(
                "&7Il tuo team otterrà Affilatezza I",
                "&7permanentemente su tutte le spade",
                "&7e ascie!",
                "",
                "{tier_1_color}Costo: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-1"),
            "{color}Armatura Rinforzata I"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "armor"),
            listOf(
                "&7Il tuo team ottiene Protezione",
                "&7permanentemente su tutti i pezzi d'armatura!",
                "",
                "{tier_1_color}Tier 1: Protezione I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tier 2: Protezione II, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Tier 3: Protezione III, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Tier 4: Protezione IV, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-2"),
            "{color}Armatura Rinforzata II"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-3"),
            "{color}Armatura Rinforzata III"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-4"),
            "{color}Armatura Rinforzata IV"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-1"),
            "{color}Minatore Maniaco I"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "miner"),
            listOf(
                "&7Tutti i giocatori del tuo team",
                "&7otterranno permanentemente Haste.",
                "",
                "{tier_1_color}Tier 1: Haste I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tier 2: Haste II, &b{tier_2_cost} {tier_2_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-2"),
            "{color}Minatore Maniaco II"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "heal-pool").replace("{tier}", "tier-1"),
            "{color}Pozza Rigenerativa"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "heal-pool"),
            listOf(
                "&7Crea un campo di rigenerazione",
                "&7attorno alla tua base!",
                "",
                "{tier_1_color}Costo: &b{tier_1_cost} {tier_1_currency}",
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
                "&7Il tuo team avrà 2 draghi invece",
                "&7di 1 durante il deathmatch!",
                "",
                "{tier_1_color}Costo: &b{tier_1_cost} {tier_1_currency}",
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
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_MSG, "&c&l{trap} è stata attivata!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_TITLE, "&cTRAPPOLA ATTIVATA!")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_SUBTITLE, "&ffLa tua trappola {trap} è stata attivata!")
        addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_MSG + "3",
            "&c&lAllarme attivata da &7&l{player} &c&ldel team {color}&l{team} &c&l!"
        )
        addDefault(Messages.UPGRADES_TRAP_CUSTOM_TITLE + "3", "&c&lALLARME!!!")
        addDefault(Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + "3", "&fAllarme attivata dal team {color}{team}&f!")
        save()
        setPrefix(m(Messages.PREFIX))
    }
}
