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

class Persian : Language(BedWars.INSTANCE, "fa") {
    init {
        this.options().header("Translation by Alijk#2951")
        this.options().copyDefaults(true)
        this.addDefault(Messages.PREFIX, "")
        this.addDefault("name", "Persian")

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
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " join &o<arena/group>",
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " leave",
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " lang",
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " gui",
                "&2▪ &7/" + BedWars.MAIN_COMMAND + " start &3(vip)"
            )
        )
        this.addDefault(Messages.COMMAND_LANG_LIST_HEADER, "{prefix} &2Zaban haye mojood:")
        this.addDefault(Messages.COMMAND_LANG_LIST_FORMAT, "&a▪  &7{iso} - &f{name}")
        this.addDefault(Messages.COMMAND_LANG_USAGE, "{prefix}&7Ravesh Estefade: /lang &f&o<iso>")
        this.addDefault(Messages.COMMAND_LANG_SELECTED_NOT_EXIST, "{prefix}&cIn zaban vojood nadarad!")
        this.addDefault(Messages.COMMAND_LANG_SELECTED_SUCCESSFULLY, "{prefix}&aZaban taghir kard!")
        this.addDefault(
            Messages.COMMAND_LANG_USAGE_DENIED,
            "{prefix}&cShoma nemitavanid hengami ke dar game hastid zaban ra agvaz konid."
        )
        this.addDefault(
            Messages.COMMAND_JOIN_USAGE,
            "§a▪ §7Ravesh Estefade: /" + BedWars.MAIN_COMMAND + " join §o<arena/group>"
        )
        this.addDefault(
            Messages.COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND,
            "{prefix}&cHich arena ya arena group i be in nam vojood nadarad: {name}"
        )
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL,
            "{prefix}&cIn arena por shode!\n&aBaraye dashtan ghabeliat haye bishtar mitoonid ma ro donate konid. &7&o(click)"
        )
        this.addDefault(Messages.COMMAND_JOIN_NO_EMPTY_FOUND, "{prefix}&cHich arena i dar hale hazer khali nist ;(")
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS,
            "{prefix}&cMotasefane arena mored nazar por shode.\n&cMa midoonim ke shoma donor hastid ama in arena az ghabl ba staff/donor ha por shode."
        )
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_PARTY_TOO_BIG,
            "{prefix}&cTedad afradi ke dar party shoma hastand monaseb in arena nist :("
        )
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_NOT_PARTY_LEADER,
            "{prefix}&cFaghat saheb party emkan entekhab arena dare."
        )
        this.addDefault(Messages.COMMAND_JOIN_PLAYER_JOIN_MSG, "{prefix}&7{player} &evared shod (&b{on}&e/&b{max}&e)!")
        this.addDefault(
            Messages.COMMAND_JOIN_SPECTATOR_MSG,
            "{prefix}§6Shoma darhale spectate kardan §9{arena} §6hastid.\n{prefix}§eBaraye kharej shodan az arena az §c/leave §eestefade konid."
        )
        this.addDefault(
            Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG,
            "&cEmkan vared shodan spectator be in arena vojood nadarad!"
        )
        this.addDefault(Messages.COMMAND_TP_PLAYER_NOT_FOUND, "{prefix}&cPlayer peida nashod!")
        this.addDefault(Messages.COMMAND_TP_NOT_IN_ARENA, "{prefix}&cIn player dar yek arena bedwars nist!")
        this.addDefault(
            Messages.COMMAND_TP_NOT_STARTED,
            "{prefix}&cArena i ke player dakhelesh hast hanooz start nashode!"
        )
        this.addDefault(Messages.COMMAND_TP_USAGE, "{prefix}&cRavesh estefade: /bw tp <username>")
        this.addDefault(Messages.REJOIN_NO_ARENA, "{prefix}&cArena baraye mojadadan vared shodan mojood nist!")
        this.addDefault(
            Messages.REJOIN_DENIED,
            "{prefix}&cShoma dige nemitoonid mojadadan vared arena beshid. Bazi tamoom ya hazf shode."
        )
        this.addDefault(Messages.REJOIN_ALLOWED, "{prefix}&eDarhale vorood be arena &a{arena}&e!")
        this.addDefault(Messages.COMMAND_REJOIN_PLAYER_RECONNECTED, "{prefix}&7{player} &emojadadan vared shod!")
        this.addDefault(Messages.COMMAND_LEAVE_DENIED_NOT_IN_ARENA, "{prefix}&cShoma dar arena nistid!")
        this.addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&7{player} &ekharej shod!")
        this.addDefault(
            Messages.COMMAND_NOT_ALLOWED_IN_GAME,
            "{prefix}&cShoma nemitavanid inkar ra zamani ke dar game hastid anjam dahid."
        )
        this.addDefault(
            Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS,
            "{prefix}&cCommand yaft nashod ya shoma dastresi lazem ro nadarid!"
        )
        this.addDefault(
            Messages.COMMAND_PARTY_HELP, listOf(
                "&6▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&aDastoorat Party:",
                "&e/party help &7- &bPayam help (hamin payam) ra neshan midahad",
                "&e/party invite <player> &7- &bInvite dadan be player mored nazar",
                "&e/party leave &7- &bKharej shodan az party feli",
                "&e/party remove <player> &7- &bHazf player az party",
                "&e/party info &7- &bShow party members and owner",
                "&e/party promote <player> &7- &bTransfer party ownership",
                "&e/party accept <player> &7- &bGhabool kardan yek invite",
                "&e/party disband &7- &bAz bein bordan party"
            )
        )
        this.addDefault(Messages.COMMAND_PARTY_INVITE_USAGE, "{prefix}&eRavesh Estefade: &7/party invite <player>")
        this.addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eonline nist!")
        this.addDefault(Messages.COMMAND_PARTY_INVITE_SENT, "{prefix}&eInvite be &7{player} &eersal shod&6.")
        this.addDefault(
            Messages.COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG,
            "{prefix}&b{player} &eshoma ro be party davat karde! &o&7(Baraye ghabool kardan click inja konid)"
        )
        this.addDefault(
            Messages.COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF,
            "{prefix}&cShoma nemitoonid khodetoon ro invite bedi!"
        )
        this.addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eoffline hast!")
        this.addDefault(
            Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE,
            "{prefix}&cHich darkhast party i baraye ghabool kardan vojood nadare"
        )
        this.addDefault(
            Messages.COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY,
            "{prefix}&eShoma darhale hazer dar yek party hastid!"
        )
        this.addDefault(
            Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS,
            "{prefix}&cFaghat saheb party emkan in kar ro dare!"
        )
        this.addDefault(Messages.COMMAND_PARTY_ACCEPT_USAGE, "{prefix}&eRavesh Estefade: &7/party accept <player>")
        this.addDefault(Messages.COMMAND_PARTY_ACCEPT_SUCCESS, "{prefix}&7{player} &evared party shod!")
        this.addDefault(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY, "{prefix}&cShoma dar party nistid!")
        this.addDefault(
            Messages.COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND,
            "{prefix}&cShoma nemitoonid az party khodetoon leave bedi!\n&eDastoor baraye hazf party: &b/party disband"
        )
        this.addDefault(Messages.COMMAND_PARTY_LEAVE_SUCCESS, "{prefix}&7{player} &eaz party kharej shod!")
        this.addDefault(Messages.COMMAND_PARTY_DISBAND_SUCCESS, "{prefix}&eParty az bein raft!")
        this.addDefault(Messages.COMMAND_PARTY_REMOVE_USAGE, "{prefix}&7Ravesh Estefade: &e/party remove <player>")
        this.addDefault(Messages.COMMAND_PARTY_REMOVE_SUCCESS, "{prefix}&7{player} &eaz party hazf shod,")
        this.addDefault(
            Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER,
            "{prefix}&7{player} &edakhel party shoma nist!"
        )
        this.addDefault(Messages.COMMAND_PARTY_PROMOTE_SUCCESS, "{prefix}&eYou successfully promoted {player} to owner")
        this.addDefault(Messages.COMMAND_PARTY_PROMOTE_OWNER, "{prefix}&eYou have been promoted to party owner")
        this.addDefault(Messages.COMMAND_PARTY_PROMOTE_NEW_OWNER, "{prefix}&7 &e{player} has been promoted to owner")
        this.addDefault(Messages.COMMAND_PARTY_INFO_OWNER, "\n{prefix}&eOwner of the party is: &7{owner}")
        this.addDefault(Messages.COMMAND_PARTY_INFO_PLAYERS, "{prefix}&eParty members:")
        this.addDefault(Messages.COMMAND_PARTY_INFO_PLAYER, "&7{player}")
        this.addDefault(Messages.COMMAND_FORCESTART_NOT_IN_GAME, "§c▪ §7Shoma bazi nemikonid!")
        this.addDefault(Messages.COMMAND_FORCESTART_SUCCESS, "§c▪ §7Shomaresh makoos kootah shod!")
        this.addDefault(
            Messages.COMMAND_FORCESTART_NO_PERM,
            "{prefix}&7Shoma nemitavanid in arena ro forcestart konid.\n§7Lotfan baraye daryaft ghabeliat haye vizhe server ro donate konid."
        )
        this.addDefault(
            Messages.COMMAND_COOLDOWN,
            "&cShoma hanooz nemitoonid in kar ro anjam bedid! Lotfan {seconds} sanie sabr konid!"
        )
        this.addDefault(
            Messages.ARENA_JOIN_VIP_KICK,
            "{prefix}&cMotasefane shoma bedalil vorood yek donor be arena kick shodid.\n&aLotfan baraye daryaft ghabeliat haye vizhe server ro donate konid. &7&o(click)"
        )
        this.addDefault(
            Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT,
            "{prefix}§cPlayer ha baraye shoroo kafi nistand! Shomaresh makoos motevaghef shod!"
        )
        this.addDefault(
            Messages.ARENA_RESTART_PLAYER_KICK,
            "{prefix}&eArena i ke shoma dakhelesh budid dar hale rah andazi mojadad hast."
        )
        this.addDefault(Messages.ARENA_STATUS_PLAYING_NAME, "&cDakhel Bazi")
        this.addDefault(Messages.ARENA_STATUS_RESTARTING_NAME, "&4Darhal Restart")
        this.addDefault(Messages.ARENA_STATUS_WAITING_NAME, "&2Dar Entezar §c{full}")
        this.addDefault(Messages.ARENA_STATUS_STARTING_NAME, "&6Darhale Shoroo §c{full}")
        this.addDefault(Messages.ARENA_GUI_INV_NAME, "&8Baraye vared shodan click konid")
        this.addDefault(Messages.ARENA_GUI_ARENA_CONTENT_NAME, "&a&l{name}")
        this.addDefault(
            Messages.ARENA_GUI_ARENA_CONTENT_LORE,
            listOf(
                "",
                "&7Halat: {status}",
                "&7Player Ha: &f{on}&7/&f{max}",
                "&7No: &a{group}",
                "",
                "&aBaraye vared shodan Left-Click konid.",
                "&eBaraye spectate kardan Right-Click konid."
            )
        )
        this.addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_NAME, "&r{serverIp}")
        this.addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_LORE, mutableListOf<Any?>())
        this.addDefault(
            Messages.ARENA_STATUS_START_COUNTDOWN_CHAT,
            "{prefix}&eBazi dar &6{time} &esanie digar shoro khahad shod."
        )
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_TITLE, " ")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE, "&a{second}")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-5", "&e❺")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-4", "&e❹")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-3", "&c❸")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-2", "&c❷")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-1", "&c❶")
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_TITLE, " ")
        this.addDefault(
            Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_SUB_TITLE,
            "&cDar entezare player haye bishtar.."
        )
        this.addDefault(Messages.ARENA_STATUS_START_PLAYER_TITLE, "&aBERID")
        this.addDefault(
            Messages.ARENA_STATUS_START_PLAYER_TUTORIAL, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lBedWars", "",
                "&e&l    Az bed khodetoon hefazat konid va bed baghie ro bekanid.",
                "&e&l   Ba daryaft Iron, Gold, Emerald va Diamond az generator ha",
                "&e&l              Khodetoon va teametoon ro ertegha bedid",
                "&e&l                  ta ghodratmand tar beshid.", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        this.addDefault(
            Messages.ARENA_JOIN_DENIED_SELECTOR,
            "{prefix}&cMotasefane darhale hazer nemitoonid vared in arena beshid. Baraye spectate kardan az Right-Click estefade konid!"
        )
        this.addDefault(
            Messages.ARENA_SPECTATE_DENIED_SELECTOR,
            "{prefix}&cMotasefane darhale hazer nemitoonid in arena ro spectate konid. Baraye vared shodan az Left-Click estefade konid!"
        )
        this.addDefault(
            Messages.ARENA_JOIN_DENIED_NO_PROXY,
            "&cShoma bayad az tarighe BedWarsProxy vared bazi beshid. \n&eAgar mikhaid arena jadidi ijad konid motmaen shid ke permission bw.setup darid ta betoonid mostaghiman vared server beshid!"
        )
        this.addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_NAME, "&8Teleporter")
        this.addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME, "{vPrefix}{player}")
        this.addDefault(
            Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE,
            listOf("&7Health: &f{health}%", "&7Food: &f{food}", "", "&7Left-click to spectate")
        )
        this.addDefault(Messages.ARENA_SPECTATOR_LEAVE_ITEM_NAME, "&c&lBazgasht be lobby")
        this.addDefault(
            Messages.ARENA_SPECTATOR_LEAVE_ITEM_LORE,
            listOf("&7Baraye bargasht be lobby Right-click konid!")
        )
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE, "&aDarhale spectate kardan &7{player}")
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE, "&cBaryae khorooj SNEAK konid")
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE, "&eDarhale khorooj az halat Spectator")
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE, "")
        this.addDefault(
            Messages.ARENA_LEAVE_PARTY_DISBANDED,
            "{prefix}§cSaheb party az server kharej shod va party az bein raft!"
        )
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TIER, "&eTier &c{tier}")
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND, "&b&lDiamond")
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD, "&a&lEmerald")
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TIMER, "&eSpawn dar &c{seconds} &esanie")
        this.addDefault(
            Messages.GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT,
            "{prefix}{generatorType} Generator ha &eertegha peida kardand be Tier &c{tier}"
        )
        this.addDefault(Messages.FORMATTING_CHAT_LOBBY, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        this.addDefault(Messages.FORMATTING_CHAT_WAITING, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
        this.addDefault(
            Messages.FORMATTING_CHAT_SHOUT,
            "{level}{vPrefix}&6[SHOUT] {team} &7{player}&f{vSuffix}: {message}"
        )
        this.addDefault(Messages.FORMATTING_CHAT_TEAM, "{level}{vPrefix}&f{team}&7 {player}{vSuffix} {message}")
        this.addDefault(Messages.FORMATTING_CHAT_SPECTATOR, "{level}{vPrefix}&7[SPECTATOR] {player}{vSuffix}: {message}")
        this.addDefault(Messages.FORMATTING_SCOREBOARD_HEALTH, listOf("&c❤", "&aHealth"))

        this.addDefault(Messages.FORMATTING_SCOREBOARD_DATE, "dd/MM/yy")
        this.addDefault(
            Messages.FORMATTING_SCOREBOARD_TEAM_GENERIC,
            "{TeamColor}{TeamLetter}&f {TeamName}: {TeamStatus}"
        )
        this.addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ELIMINATED, "&c&l✘")
        this.addDefault(Messages.FORMATTING_SCOREBOARD_BED_DESTROYED, "&a{remainingPlayers}")
        this.addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ALIVE, "&a&l✓")
        this.addDefault(Messages.FORMATTING_SCOREBOARD_NEXEVENT_TIMER, "mm:ss")
        this.addDefault(Messages.FORMATTING_SCOREBOARD_YOUR_TEAM, "&7 SHOMA")
        this.addDefault(Messages.FORMATTING_ACTION_BAR_TRACKING, "&fDarhale Track: {team} &f- Fasele: {distance}m")
        this.addDefault(Messages.FORMATTING_TEAM_WINNER_FORMAT, "      {TeamColor}{TeamName} &7- {members}")
        this.addDefault(Messages.FORMATTING_SOLO_WINNER_FORMAT, "                 {TeamColor}{TeamName} &7- {members}")
        this.addDefault(Messages.FORMATTING_GENERATOR_TIER1, "I")
        this.addDefault(Messages.FORMATTING_GENERATOR_TIER2, "II")
        this.addDefault(Messages.FORMATTING_GENERATOR_TIER3, "III")
        this.addDefault(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH, "▮ ")
        this.addDefault(Messages.FORMATTING_STATS_DATE_FORMAT, "yyyy/MM/dd HH:mm")
        this.addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_TEAM, "{TeamColor}[{TeamName}]")
        this.addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SHOUT, "&6[SHOUT]")
        this.addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SPECTATOR, "&7[SPECTATOR]")
        this.addDefault(Messages.MEANING_FULL, "Kamel")
        this.addDefault(Messages.MEANING_SHOUT, "shout")
        this.addDefault(Messages.MEANING_NOBODY, "Hichkas")
        this.addDefault(Messages.MEANING_NEVER, "Hichvaght")
        this.addDefault(Messages.MEANING_IRON_SINGULAR, "Iron")
        this.addDefault(Messages.MEANING_IRON_PLURAL, "Iron")
        this.addDefault(Messages.MEANING_GOLD_SINGULAR, "Gold")
        this.addDefault(Messages.MEANING_GOLD_PLURAL, "Gold")
        this.addDefault(Messages.MEANING_EMERALD_SINGULAR, "Emerald")
        this.addDefault(Messages.MEANING_EMERALD_PLURAL, "Emerald")
        this.addDefault(Messages.MEANING_DIAMOND_SINGULAR, "Diamond")
        this.addDefault(Messages.MEANING_DIAMOND_PLURAL, "Diamond")
        this.addDefault(Messages.MEANING_VAULT_SINGULAR, "$")
        this.addDefault(Messages.MEANING_VAULT_PLURAL, "$")
        this.addDefault(Messages.INTERACT_CANNOT_PLACE_BLOCK, "{prefix}&cShoma Nemitavanid Inja Block Bezarid.")
        this.addDefault(
            Messages.INTERACT_CANNOT_BREAK_BLOCK,
            "{prefix}&cFaghat Block Hayi Ke Player Ha Gozashtan Ghabele Kandan Ast."
        )
        this.addDefault(Messages.INTERACT_CANNOT_BREAK_OWN_BED, "&cShoma Nemitavanid Bed Khodetoon Ra Bekanid.")
        this.addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT,
            "\n&f&lBED AZ BEYN RAFT > {TeamColor}{TeamName} Bed &7Tavasote {PlayerColor}{PlayerName} &7Az Beyn Raft!\n"
        )
        this.addDefault(Messages.INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT, "&cBED AZ BEYN RAFT!")
        this.addDefault(Messages.INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT, "&fShoma Digar Respawn Nakhahid Shod.")
        this.addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM,
            "&f&lBED AZ BEYN RAFT > &7Bed Shoma Tavasote {PlayerColor}{PlayerName} &7Az Beyn Raft"
        )
        this.addDefault(
            Messages.INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED,
            "&cShoma Nemitavanid In Chest Ro Be Dalil Inke Player Haye Team Hanoz Namordan Baz Konid!"
        )
        this.addDefault(
            Messages.INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN,
            "&cYou are no longer invisible because you have taken damage!"
        )
        this.addDefault(Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL, "{PlayerColor}{PlayerName} &7Dakhel Void Oftad.")
        this.addDefault(
            Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Dakhel Void Oftad. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote {KillerColor}{KillerName} &7Be Dakhel Void Part Shod."
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote {KillerColor}{KillerName} &7Be Dakhel Void Part Shod. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_REGULAR,
            "{PlayerColor}{PlayerName} &7Hengame Mobareze Ba {KillerColor}{KillerName} &7Az Server Kharej Shod."
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_FINAL,
            "{PlayerColor}{PlayerName} &7Hengame Mobareze Ba {KillerColor}{KillerName} &7Az Server Kharej Shod. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote {KillerColor}{KillerName} &7Part shod."
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote {KillerColor}{KillerName} &7Part Shod. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote {KillerColor}{KillerName} &7Bar Asar Enfejar Mord."
        )
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote {KillerColor}{KillerName} &7Bar Asar Enfejar Mord. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR,
            "{PlayerColor}{PlayerName} &7Bar Asar Enfejar Mord."
        )
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Bar Asar Enfejar Mord. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_REGULAR_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote {KillerColor}{KillerName} &7Koshte Shod!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote {KillerColor}{KillerName} &7Koshte Shod! &b&lFINAL KILL!"
        )
        this.addDefault(Messages.PLAYER_DIE_UNKNOWN_REASON_REGULAR, "{PlayerColor}{PlayerName} &7Mord.")
        this.addDefault(
            Messages.PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Mord. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_SHOOT_REGULAR,
            "{PlayerColor}{PlayerName} &7Tavasote {KillerColor}{KillerName} &7Shoot shod!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_SHOOT_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote {KillerColor}{KillerName} &7Shoot shod! &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_DEBUG_REGULAR,
            "{PlayerColor}{PlayerName} &7Tavasote BedBug {KillerColor}{KillerTeamName} &7Koshte Shod!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_DEBUG_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote BedBug {KillerColor}{KillerTeamName} &7Koshte Shod! &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_REGULAR,
            "{PlayerColor}{PlayerName} &7Tavasote Iron Golem {KillerColor}{KillerTeamName} Koshte Shod!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Tavasote Iron Golem {KillerColor}{KillerTeamName} &7Koshte Shod! &b&lFINAL KILL!"
        )
        this.addDefault(Messages.PLAYER_DIE_REWARD_DIAMOND, "{prefix}&b+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_REWARD_EMERALD, "{prefix}&a+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_REWARD_IRON, "{prefix}&f+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_REWARD_GOLD, "{prefix}&6+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_RESPAWN_TITLE, "&cSHOMA MORDID!")
        this.addDefault(Messages.PLAYER_DIE_RESPAWN_SUBTITLE, "&eShoma dar &c{time} &esanie dige respawn mishid!")
        this.addDefault(Messages.PLAYER_DIE_RESPAWN_CHAT, "{prefix}&eShoma dar &c{time} &esanie dige respawn mishid!")
        this.addDefault(Messages.PLAYER_DIE_RESPAWNED_TITLE, "&aRESPAWN SHID!")
        this.addDefault(Messages.PLAYER_DIE_ELIMINATED_CHAT, "{prefix}&cShoma hazf shodid!")
        this.addDefault(Messages.PLAYER_HIT_BOW, "{prefix}{TeamColor}{PlayerName} &7alan &c{amount} &7HP dare!")
        this.addDefault(Messages.GAME_END_GAME_OVER_PLAYER_TITLE, "&c&lBAZI TAMOOM SHOD!")
        this.addDefault(Messages.GAME_END_VICTORY_PLAYER_TITLE, "&6&lBORD!")
        this.addDefault(Messages.GAME_END_TEAM_WON_CHAT, "{prefix}{TeamColor}{TeamName} &abarande bazi shodan!")
        this.addDefault(
            Messages.GAME_END_TOP_PLAYER_CHAT, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lBedWars", "", "{winnerFormat}", "", "",
                "&6                      &6⭐ &lMagham #1 &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&e                        &lMagham #2 &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&c                        &lMagham #3 &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        this.addDefault(Messages.BED_HOLOGRAM_DEFEND, "&c&lAz Bed khodetoon hefazat konid!")
        this.addDefault(Messages.BED_HOLOGRAM_DESTROYED, "&c&lBed shoma az bein raft!")
        this.addDefault(Messages.NPC_NAME_TEAM_UPGRADES, "&bUPGRADE HAYE TEAM,&e&lRIGHT CLICK")
        this.addDefault(Messages.NPC_NAME_SOLO_UPGRADES, "&bUPGRADE HAYE SOLO,&e&lRIGHT CLICK")
        this.addDefault(Messages.NPC_NAME_TEAM_SHOP, "&bFOROOSHGAH TEAM,&e&lRIGHT CLICK")
        this.addDefault(Messages.NPC_NAME_SOLO_SHOP, "&bFOROOSHGAH ITEM,&e&lRIGHT CLICK")
        this.addDefault(
            Messages.TEAM_ELIMINATED_CHAT,
            "\n&f&lTEAM ELIMINATED > {TeamColor} Team {TeamName} &chazf shod!\n"
        )
        this.addDefault(Messages.NEXT_EVENT_BEDS_DESTROY, "&cHazf Bed Ha")
        this.addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_II, "&fDiamond II")
        this.addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_III, "&fDiamond III")
        this.addDefault(Messages.NEXT_EVENT_DRAGON_SPAWN, "&fHamle Dragon")
        this.addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_II, "&fEmerald II")
        this.addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_III, "&fEmerald III")
        this.addDefault(Messages.NEXT_EVENT_GAME_END, "&4Payan Bazi")
        this.addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED, "&cBED AZ BEIN RAFT!")
        this.addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED, "&fHameye bed ha az bein raftand!")
        this.addDefault(Messages.NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED, "&c&lTamami bed ha az bein raftand!")
        this.addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH, "&cSudden Death")
        this.addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH, "")
        this.addDefault(
            Messages.NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH,
            "&cSUDDEN DEATH: &6&b{TeamDragons} {TeamColor}{TeamName} Dragon!"
        )
        this.addDefault(Messages.XP_REWARD_PER_MINUTE, "{prefix}&6+{xp} Tajrobe BedWars Daryaft Kardid (Zaman Play).")
        this.addDefault(Messages.XP_REWARD_WIN, "{prefix}&6+{xp} Tajrobe BedWars Daryaft Kardid (Bord Bazi).")
        this.addDefault(
            Messages.XP_REWARD_PER_TEAMMATE,
            "{prefix}&6+{xp} Tajrobe BedWars Daryaft Kardid (Hemayat Az Team)."
        )
        this.addDefault(
            Messages.XP_REWARD_BED_DESTROY,
            "{prefix}&6+{xp} Tajrobe BedWars Daryaft Kardid (Az Bein Raftan Bed)."
        )
        this.addDefault(Messages.XP_REWARD_REGULAR_KILL, "{prefix}&6+{xp} Tajrobe BedWars Daryaft Kardid (Kill e Addi).")
        this.addDefault(Messages.XP_REWARD_FINAL_KILL, "{prefix}&6+{xp} Tajrobe BedWars Daryaft Kardid (Kill e Payani).")

        this.addDefault(Messages.MONEY_REWARD_PER_MINUTE, "{prefix}&6+{money} Coin (Zaman Play).")
        this.addDefault(Messages.MONEY_REWARD_WIN, "{prefix}&6+{money} Coin (Bord Bazi).")
        this.addDefault(Messages.MONEY_REWARD_PER_TEAMMATE, "{prefix}&6+{money} Coin (Hemayat Az Team).")
        this.addDefault(Messages.MONEY_REWARD_BED_DESTROYED, "{prefix}&6+{money} Coin (Az Bein Raftan Bed).")
        this.addDefault(Messages.MONEY_REWARD_FINAL_KILL, "{prefix}&6+{money} Coin (Kill e Payani).")
        this.addDefault(Messages.MONEY_REWARD_REGULAR_KILL, "{prefix}&6+{money} Coin (Kill e Addi).")

        /* Lobby Command Items */
        this.addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "stats"), "&eAmar")
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fBaraye didan amaretoon Right-click konid!")
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "arena-selector"),
            "&eEntekhab Arena"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "arena-selector"),
            listOf("&fBaraye entekhab arena Right-click konid!")
        )
        this.addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "leave"), "&eBazgasht be Hub")
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fBaraye raftan az BedWars Right-click konid!")
        )
        /* Pre Game Command Items */
        this.addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "stats"), "&eAmar")
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fBaraye didan amaretoon Right-click konid!")
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "leave"),
            "&eBazgasht be Lobby"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fBaraye raftan az arena Right-click konid!")
        )
        /* Spectator Command Items */
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "teleporter"),
            "&eTeleport Konande"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "leave"),
            "&eBazgasht be Lobby"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fBaraye raftan az arena Right-click konid!")
        )

        /* save default items messages for stats gui */
        this.addDefault(Messages.PLAYER_STATS_GUI_INV_NAME, "&8{player} Amar")
        addDefaultStatsMsg("wins", "&6Bord", "&f{wins}")
        addDefaultStatsMsg("losses", "&6Bakht", "&f{losses}")
        addDefaultStatsMsg("kills", "&6Kill", "&f{kills}")
        addDefaultStatsMsg("deaths", "&6Death", "&f{deaths}")
        addDefaultStatsMsg("final-kills", "&6Kill Haye Payani", "&f{finalKills}")
        addDefaultStatsMsg("final-deaths", "&6Death Haye Payani", "&f{finalDeaths}")
        addDefaultStatsMsg("beds-destroyed", "&6Bed haye Kande Shode", "&f{bedsDestroyed}")
        addDefaultStatsMsg("first-play", "&6Avalin Bazi Anjam Shode", "&f{firstPlay}")
        addDefaultStatsMsg("last-play", "&6Akharin Bazi Anjam Shode", "&f{lastPlay}")
        addDefaultStatsMsg("games-played", "&6Tedad Bazi Anjam Shode", "&f{gamesPlayed}")

        // Start of Sidebar
        this.addDefault(
            Messages.SCOREBOARD_LOBBY, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&fLevel Shoma: {level}",
                "",
                "&fPishraft: &a{currentXp}&7/&b{requiredXp}",
                "{progress}",
                "",
                "&7{player}",
                "",
                "&fCoins: &a{money}",
                "",
                "&fMajmoo Win: &a{wins}",
                "&fMajmoo Kill: &a{kills}",
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
                "&fPlayer Ha: &a{on}/{max}",
                "",
                "&fDar Entezar,&fDar Entezar.,&fDar Entezar..,&fDar Entezar...",
                "",
                "&fNo: &a{group}",
                "&fVersion: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "&o&7Spectating",
                "&fMap: &a{map}",
                "",
                "&fPlayer Ha: &a{on}/{max}",
                "",
                "&fDar Entezar,&fDar Entezar.,&fDar Entezar..,&fDar Entezar...",
                "",
                "&fNo: &a{group}",
                "&fVersion: &7{version}",
                "",
                "&e{serverIp}"
            )
        )
        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date} &8{server}",
                "",
                "&fMap: &a{map}",
                "",
                "&fPlayer Ha: &a{on}/{max}",
                "",
                "&fShoroo dar &a{time}s",
                "",
                "&fNo: &a{group}",
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
                "&fPlayer Ha: &a{on}/{max}",
                "",
                "&fShoroo dar &a{time}s",
                "",
                "&fNo: &a{group}",
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
                "&f{nextEvent} dar &a{time}",
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
                "&f{nextEvent} dar &a{time}",
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
                "&f{nextEvent} dar &a{time}",
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
                "&f{nextEvent} dar &a{time}",
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
                "&f{nextEvent} dar &a{time}",
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
                "&f{nextEvent} dar &a{time}",
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
                "&f{nextEvent} dar &a{time}",
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
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "4v4v4v4"),
            listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} dar &a{time}",
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
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED.replaceFirst("Default".toRegex(), "4v4v4v4"),
            listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} dar &a{time}",
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
        this.addDefault(Messages.SHOP_INDEX_NAME, "&8Kharid Sari")
        this.addDefault(Messages.SHOP_QUICK_ADD_NAME, "&8Adding to Quick Buy...")
        this.addDefault(
            Messages.SHOP_INSUFFICIENT_MONEY,
            "{prefix}&cShoma be andaze kafi {currency} nadarid! Shoma {amount} ta bishtar mikhaid!"
        )
        this.addDefault(Messages.SHOP_NEW_PURCHASE, "{prefix}&aShoma &6{item} &akharidid")
        this.addDefault(Messages.SHOP_ALREADY_BOUGHT, "{prefix}&cShoma az ghabl in ro kharidid!")
        this.addDefault(Messages.SHOP_UTILITY_NPC_SILVERFISH_NAME, "{TeamColor}&l{TeamName} &r{TeamColor}Silverfish")
        this.addDefault(Messages.SHOP_UTILITY_NPC_IRON_GOLEM_NAME, "{TeamColor}{despawn}s &8[ {TeamColor}{health}&8]")
        this.addDefault(Messages.SHOP_SEPARATOR_NAME, "&8⇧ Daste Bandi Ha")
        this.addDefault(Messages.SHOP_SEPARATOR_LORE, listOf("&8⇩ Item Ha"))
        this.addDefault(Messages.SHOP_QUICK_BUY_NAME, "&bKharid Sari")
        this.addDefault(Messages.SHOP_QUICK_BUY_LORE, ArrayList<Any?>())
        this.addDefault(Messages.SHOP_QUICK_EMPTY_NAME, "&cSlot Khali!")
        this.addDefault(
            Messages.SHOP_QUICK_EMPTY_LORE,
            listOf(
                "&7Inja jaye slot Kharid Sari hast!",
                "&7Har item ba &bSneak Click &7bezanid ta",
                "&7inja ezafe beshe"
            )
        )
        this.addDefault(Messages.SHOP_CAN_BUY_COLOR, "&a")
        this.addDefault(Messages.SHOP_CANT_BUY_COLOR, "&c")
        this.addDefault(Messages.SHOP_LORE_STATUS_CAN_BUY, "&eBaraye kharid Click konid!")
        this.addDefault(Messages.SHOP_LORE_STATUS_CANT_AFFORD, "&cShoma be andaze kafi {currency} nadarid!")
        this.addDefault(Messages.SHOP_LORE_STATUS_MAXED, "&aAKHARIN LEVEL!")
        this.addDefault(Messages.SHOP_LORE_QUICK_ADD, "&bBaraye ezafe kardan be Kharid Sari SNEAK Click konid")
        this.addDefault(Messages.SHOP_LORE_QUICK_REMOVE, "&bBaraye hazf az Kharid Sari SNEAK Click konid!")


        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "&8Block Ha",
            "&aBlock Ha",
            listOf("&eBaraye moshahede Click konid!")
        )

        addContentMessages(
            this, "wool", ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "{color}Wool", listOf(
                "&7Gheimat: &f{cost} {currency}", "", "&7Monaseb pol zadan be", "&7island ha. Be range teametoon",
                "&7teametoon dar miad.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "clay",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Clay Mohkam",
            listOf(
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Block sade baraye defa kardan az bed.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "glass",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Glass Zedde Enfejar",
            listOf(
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Zedde enfejar.",
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
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Block sade baraye defa kardan az bed.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "ladder",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Ladder",
            listOf(
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Baraye balaraftan va gir endakhtan enemy ha",
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
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Block ghodratmand baraye defa az bed.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "wood",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Wood",
            listOf(
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Block sade baraye defa kardan az bed",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "&8Melee",
            "&aMelee",
            listOf("&eBaraye Moshahede Click Konid!")
        )

        addContentMessages(
            this,
            "stone-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Stone Sword",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "iron-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Iron Sword",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "diamond-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Diamond Sword",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "stick",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Stick (KnockBack I)",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "&8Armor",
            "&aArmor",
            listOf("&eBaraye Moshahede Click Konid!")
        )

        addContentMessages(
            this,
            "chainmail",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Chainmail Armor Daemi",
            listOf(
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Chainmail legging va boot ke",
                "&7hamishe ba oon ha spawn mishid",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "iron-armor", ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "{color}Iron Armor Daemi", listOf(
                "&7Gheimat: {cost} {currency}",
                "", "&7Iron legging va boot ke", "&hamishe ba oon ha spawn mishid.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "diamond-armor",
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "{color}Diamond Armor Daemi",
            listOf(
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Diamond legging va boot ke",
                "&7hamishe ba oon ha spawn mishid.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_TOOLS,
            "&8Tool Ha",
            "&aTool Ha",
            listOf("&eBaraye Moshahede Click Konid!")
        )

        addContentMessages(
            this, "shears", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Shears Daemi", listOf(
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Monaseb kandan wool. Shoma",
                "&7hamishe ba in shears ha spawn mishid.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "pickaxe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Pickaxe {tier}", listOf(
                "&7Gheimat: {cost} {currency}",
                "&7Tier: &e{tier}",
                "",
                "&7In yek item ghabel erteghast.",
                "&7Hengam marg 1 Tier az dast",
                "&7midid!",
                "",
                "&7Shoma hamishe",
                "&7hadaghal ba paein tarin tier",
                "&7respawn mishid.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "axe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Axe {tier}", listOf(
                "&7Gheimat: {cost} {currency}",
                "&7Tier: &e{tier}",
                "",
                "&7In yek item ghabel erteghast.",
                "&7Hengam marg 1 Tier az dast",
                "&7midid!",
                "",
                "&7Shoma hamishe",
                "&7hadaghal ba paein tarin tier",
                "&7respawn mishid.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "&8Ranged",
            "&aRanged",
            listOf("&eBaraye Moshahede Click Konid!")
        )

        addContentMessages(
            this,
            "arrow",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Arrow",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow1",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Bow",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow2",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Bow (Power I)",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow3",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Bow (Power I, Punch I)",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "&8Potion Ha",
            "&aPotion Ha",
            listOf("&eBaraye Moshahede Click Konid!")
        )

        addContentMessages(
            this,
            "speed-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Speed II Potion (45 sanie)",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "jump-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Jump V Potion (45 sanie)",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "invisibility",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Invisibility Potion (30 sanie)",
            listOf("&7Gheimat: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "&8Utility",
            "&aUtility",
            listOf("&eBaraye Moshahede Click Konid!")
        )

        addContentMessages(
            this,
            "golden-apple",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Golden Apple",
            listOf(
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Monaseb bala raftan HP.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "bedbug", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}BedBug", listOf(
                "&7Gheimat: {cost} {currency}", "", "&7Makani ke partab mishan silver fish",
                "&7baraye part kardan havas team", "&7bemodat 15 sanie spawn mishan.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "dream-defender",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Dream Defender",
            listOf(
                "&7Gheimat: {cost} {currency}", "", "&7Iron Golem be shoma dar hefazat az",
                "&7basetoon bemodat 4 daghighe mishe.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "fireball", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Fireball", listOf(
                "&7Gheimat: {cost} {currency}", "", "&7Partab ba Right-Click! Monaseb",
                "&7part kardan enemy az rooye", "&7pol haei ke roosh rah miran", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "tnt", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}TNT", listOf(
                "&7Gheimat: {cost} {currency}", "", "&7Dar lahze roshan mishe, monaseb",
                "&7monfajer kardan chizaye mokhtalef!", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "ender-pearl", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Ender Pearl", listOf(
                "&7Gheimat: {cost} {currency}", "", "&7Sari tarin ravesh baraye hamle",
                "&7be base enemy ha.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "water-bucket", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Water Bucket", listOf(
                "&7Gheimat: {cost} {currency}",
                "",
                "&7Monaseb baraye dashtan yek forood",
                "&7narm dar base enemy. Hamchenin mitoonid",
                "&7bahash ba TNT moghabele konid.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "bridge-egg", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Bridge Egg", listOf(
                "&7Gheimat: {cost} {currency}", "", "&7In egg yek pol jadid",
                "&7bad az partab shodan misaze.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "magic-milk", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Magic Milk", listOf(
                "&7Gheimat: {cost} {currency}", "", "&7Baraye 60 sanie bad az masraf",
                "&7tale ha rooye shoma kar nemikonan.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "sponge", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Sponge", listOf(
                "&7Gheimat: {cost} {currency}", "", "&7Monaseb jam kardan ab.",
                "", "{quick_buy}", "{buy_status}"
            )
        )

        //
        this.addDefault(Messages.MEANING_NO_TRAP, "Hich tale i nadarid!")
        this.addDefault(Messages.FORMAT_SPECTATOR_TARGET, "{targetTeamColor}{targetDisplayName}")
        this.addDefault(Messages.FORMAT_UPGRADE_TRAP_COST, "&7Gheimat: {currencyColor}{cost} {currency}")
        this.addDefault(Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD, "&e")
        this.addDefault(Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD, "&c")
        this.addDefault(Messages.FORMAT_UPGRADE_COLOR_UNLOCKED, "&a")
        this.addDefault(Messages.FORMAT_UPGRADE_TIER_LOCKED, "&7")
        this.addDefault(Messages.FORMAT_UPGRADE_TIER_UNLOCKED, "&a")
        this.addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "{color}Baraye kharid Click konid!")
        this.addDefault(
            Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY,
            "{color}Shoma be meghdar kafi {currency} nadarid"
        )
        this.addDefault(Messages.UPGRADES_LORE_REPLACEMENT_LOCKED, "&cGHOFL")
        this.addDefault(Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED, "{color}BAZ")
        this.addDefault(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT, "&a{player} yek &6{upgradeName} &akharid")
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-1"),
            "{color}Forge Iron"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "forge"),
            listOf(
                "&7Ertegha resource haei ke dar",
                "&7island shoma spawn mishan.",
                "",
                "{tier_1_color}Tier 1: +50% Resource Bishtar, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tier 2: +100% Resource Bishtar, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Tier 3: Spawn shodan Emerald, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Tier 4: +200% Resource Bishtar, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-2"),
            "{color}Forge Gold"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-3"),
            "{color}Forge Emerald"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-4"),
            "{color}Forge Molten"
        )
        this.addDefault(Messages.UPGRADES_CATEGORY_ITEM_NAME_PATH + "traps", "&eKharid Tale")
        this.addDefault(
            Messages.UPGRADES_CATEGORY_ITEM_LORE_PATH + "traps",
            listOf(
                "&7Tale haye kharide shode",
                "&7dar saf samte rast gharar migiran.",
                "",
                "&eClick to browse!"
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "swords").replace("{tier}", "tier-1"),
            "{color}Sharpened Swords"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "swords"),
            listOf(
                "&7Team shoma daeman",
                "&7Sharpness I rooye hame sword va",
                "&7axe ha migiran!",
                "",
                "{tier_1_color}Gheimat: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-1"),
            "{color}Reinforced Armor I"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "armor"),
            listOf(
                "&7Team shoma daeman",
                "&7Protection rooye tamam armor ha migiran!",
                "",
                "{tier_1_color}Tier 1: Protection I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tier 2: Protection II, &b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}Tier 3: Protection III, &b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}Tier 4: Protection IV, &b{tier_4_cost} {tier_4_currency}",
                ""
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-2"),
            "{color}Reinforced Armor II"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-3"),
            "{color}Reinforced Armor III"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-4"),
            "{color}Reinforced Armor IV"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-1"),
            "{color}Maniac Miner I"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "miner"),
            listOf(
                "&7Tamam player haye team shoma",
                "&7daeman Haste migiran.",
                "",
                "{tier_1_color}Tier 1: Haste I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tier 2: Haste II, &b{tier_2_cost} {tier_2_currency}",
                ""
            )
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-2"),
            "{color}Maniac Miner II"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "heal-pool").replace("{tier}", "tier-1"),
            "{color}Heal Pool"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "heal-pool"),
            listOf(
                "&7Yek mantaghe regeneration",
                "&7atraf base shoma misaze!",
                "",
                "{tier_1_color}Gheimat: &b{tier_1_cost} {tier_1_currency}",
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
                "&7Team shoma bejaye 1 dragon",
                "&72ta dragon migire!",
                "",
                "{tier_1_color}Gheimat: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        this.addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "glass", "&8⬆&7Ghabel Kharid")
        this.addDefault(Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "glass", listOf("&8⬇&7Safe Tale"))
        this.addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "first", "{color}Tale #1: {name}")
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "first",
            listOf("&7Avalin enemy ke vared", "&7base shoma beshe in", "&7tale faal mishe!")
        )
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "first",
            listOf(
                "",
                "&7Bad az kharid yek tale dar saf",
                "&7inja gharar migire. Gheimat tale ha",
                "&7bar asase tedad tale haye mojood",
                "&7taghir peida mikone.",
                "",
                "&7Tale Badi: &b{cost} {currency}"
            )
        )
        this.addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "second", "{color}Tale #2: {name}")
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "second",
            listOf("&7Dovomin enemy ke vared", "&7base shoma beshe in", "&7tale faal mishe!")
        )
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "second",
            listOf(
                "",
                "&7Bad az kharid yek tale dar saf",
                "&7inja gharar migire. Gheimat tale ha",
                "&7bar asase tedad tale haye mojood",
                "&7taghir peida mikone.",
                "",
                "&7Tale Badi: &b{cost} {currency}"
            )
        )
        this.addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "third", "{color}Tale #3: {name}")
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "third",
            listOf("&7Sevomin enemy ke vared", "&7base shoma beshe in", "&7tale faal mishe!")
        )
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "third",
            listOf(
                "",
                "&7Bad az kharid yek tale dar saf",
                "&7inja gharar migire. Gheimat tale ha",
                "&7bar asase tedad tale haye mojood",
                "&7taghir peida mikone.",
                "",
                "&7Tale Badi: &b{cost} {currency}"
            )
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "1", "{color}Tale Sade!")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "1",
            listOf("&7Hengam vorood enemy ha be island", "&7baraye 5 sanie koor va kond mishan.", "")
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "2", "{color}Counter-Offensive Trap")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "2",
            listOf("&7Grants Speed I for 15 seconds to", "&7allied players near your base.", "")
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "3", "{color}Tale Alarm")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "3",
            listOf("&7Hengam Vorood Afrade Invisible", "&7Be Teametoon Hoshdar Dade Mishe.", "")
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "4", "{color}Trap Miner Fatigue")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "4",
            listOf(
                "&7Baraye 10 sanie Miner Fatigue Enemy",
                "&7Haei Ke Vared Base Mishano Faal Mikone.",
                ""
            )
        )
        this.addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "back", "&aBazgasht")
        this.addDefault(
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "back",
            listOf("&7To Upgrades & Traps")
        )
        this.addDefault(Messages.UPGRADES_CATEGORY_GUI_NAME_PATH + "traps", "&8Dar saf gharar dadan Tale")
        this.addDefault(Messages.UPGRADES_TRAP_QUEUE_LIMIT, "&cEmkan Kharid Tale Bishtar Ra Nadarid.")
        this.addDefault(Messages.UPGRADES_TRAP_DEFAULT_MSG, "&c&l{trap} khamoosh shod!")
        this.addDefault(Messages.UPGRADES_TRAP_DEFAULT_TITLE, "&cTALE FAAL SHOD!")
        this.addDefault(Messages.UPGRADES_TRAP_DEFAULT_SUBTITLE, "&f{trap} Shoma Faal Shod.")
        this.addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_MSG + "3",
            "&c&lHoshdar Tale Tavasote &7&l{player} &c&laz team {color}&l{team} &c&lGheyre Faal Shod!"
        )
        this.addDefault(Messages.UPGRADES_TRAP_CUSTOM_TITLE + "3", "&c&lHOSHDAR!!!")
        this.addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + "3",
            "&fAlarm Tale Tavasote Team {color}{team} &fGheyre Faal Shod!"
        )
        save()
        setPrefix(m(Messages.PREFIX))
    }
}
