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
// This Language file was created by Monzu77, contact on discord for any questions regarding this language file. Dicsord: Itsmemonzu#6732
package com.andrei1058.bedwars.language

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import java.util.*


class Hindi : Language(BedWars.plugin, "hi") {
    init {
        this.options().copyDefaults(true)
        this.addDefault(Messages.PREFIX, "")
        this.addDefault("name", "Hindi")

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
        this.addDefault(Messages.COMMAND_LANG_LIST_HEADER, "{prefix} &2Upalabdh bhaasa:")
        this.addDefault(Messages.COMMAND_LANG_LIST_FORMAT, "&a▪  &7{iso} - &f{name}")
        this.addDefault(Messages.COMMAND_LANG_USAGE, "{prefix}&7Usage: /lang &f&o<iso>")
        this.addDefault(Messages.COMMAND_LANG_SELECTED_NOT_EXIST, "{prefix}&cYe bhaasa maujud nahi hai!")
        this.addDefault(Messages.COMMAND_LANG_SELECTED_SUCCESSFULLY, "{prefix}&aBhaasa badal gaya!")
        this.addDefault(Messages.COMMAND_LANG_USAGE_DENIED, "{prefix}&cAp game ke beech mein bhaasa nahin badal sakte.")
        this.addDefault(Messages.COMMAND_JOIN_USAGE, "§a▪ §7Usage: /" + BedWars.MAIN_COMMAND + " join §o<arena/group>")
        this.addDefault(
            Messages.COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND,
            "{prefix}&cKa koi arena or arena group nahin hein"
        )
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL,
            "{prefix}&cYe arena full hai!\n&aAur features ke liye donate karein. &7&o(click)"
        )
        this.addDefault(Messages.COMMAND_JOIN_NO_EMPTY_FOUND, "{prefix}&cIs samay koi bhee arena upalabdh nahin hai ;(")
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS,
            "{prefix}&cHam khama chahte hein lekin ye arena full hai.\n&cHam jante hei ki ap ek donor ho lekin ye arena staff or/aur donator se bhara hai."
        )
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_PARTY_TOO_BIG,
            "{prefix}&cIs arena join karne liye apka party bohot bada hai. :("
        )
        this.addDefault(
            Messages.COMMAND_JOIN_DENIED_NOT_PARTY_LEADER,
            "{prefix}&cKeeval Leader arena select kar sakte hai."
        )
        this.addDefault(Messages.COMMAND_JOIN_PLAYER_JOIN_MSG, "{prefix}&7{player} &ejoin kiye (&b{on}&e/&b{max}&e)!")
        this.addDefault(
            Messages.COMMAND_JOIN_SPECTATOR_MSG,
            "{prefix}§6Ap abhi §9{arena} §6spectate kar rahe hai.\n{prefix}§§eAp kabhi bhi §c/leave §euse karke leave kar sakte hai."
        )
        this.addDefault(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG, "&cIs arena me spectators allowed nahi hain!")
        this.addDefault(Messages.COMMAND_TP_PLAYER_NOT_FOUND, "{prefix}&cPlayer nahi miley!")
        this.addDefault(Messages.COMMAND_TP_NOT_IN_ARENA, "{prefix}&cYe player ek arena me nahi hein!")
        this.addDefault(
            Messages.COMMAND_TP_NOT_STARTED,
            "{prefix}&cYe player jis arena me hai wo arena abhi bhi start nahi hua!"
        )
        this.addDefault(Messages.COMMAND_TP_USAGE, "{prefix}&cUsage: /bw tp <username>")
        this.addDefault(Messages.REJOIN_NO_ARENA, "{prefix}&cRejoin karne ke liye koi arena nahi hai!")
        this.addDefault(
            Messages.REJOIN_DENIED,
            "{prefix}&cAp is arena ko aur rejoin nahi kar sakte. Game khatam ho gaya ya apka Bistar destroy ho gaya."
        )
        this.addDefault(Messages.REJOIN_ALLOWED, "{prefix}&eAp &a{arena} &earena join kar rahe hai!")
        this.addDefault(Messages.COMMAND_REJOIN_PLAYER_RECONNECTED, "{prefix}&7{player} &ereconnect kiye!")
        this.addDefault(Messages.COMMAND_LEAVE_DENIED_NOT_IN_ARENA, "{prefix}&cAp arena me nahi ho!")
        this.addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&7{player} &eleave kiye!")
        this.addDefault(Messages.COMMAND_NOT_ALLOWED_IN_GAME, "{prefix}&cAp game ke dauraan ye nahi kar sakte.")
        this.addDefault(
            Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS,
            "{prefix}&cCommand nahi mila ya apka command use karne ka permission nahi hai"
        )
        this.addDefault(
            Messages.COMMAND_PARTY_HELP, listOf(
                "&6▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&aParty Commands:",
                "&e/party help &7- &bPrints this help message",
                "&e/party invite <player> &7- &bPlayer ko party me invite karte hai",
                "&e/party leave &7- &bParty leave karte hai",
                "&e/party remove <player> &7- &bPlayer ko party se remove karte hai",
                "&e/party info &7- &bShow party members and owner",
                "&e/party promote <player> &7- &bTransfer party ownership",
                "&e/party accept <player> &7- &bParty invite accept karte hai",
                "&e/party disband &7- &bParty disband karte hai"
            )
        )
        this.addDefault(Messages.COMMAND_PARTY_INVITE_USAGE, "{prefix}&eUsage: &7/party invite <player>")
        this.addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eonline nahi hai!")
        this.addDefault(Messages.COMMAND_PARTY_INVITE_SENT, "{prefix}&7{player} &ako party pe invite kiye&6.")
        this.addDefault(
            Messages.COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG,
            "{prefix}&b{player} &eapko party pe invite kiye! &o&7(Accept karne ke liye click kare)"
        )
        this.addDefault(
            Messages.COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF,
            "{prefix}&cAp khud ko invite nahi kar sakte!"
        )
        this.addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eoffline hai!")
        this.addDefault(Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE, "{prefix}&cKoi bhi party request nahi hai")
        this.addDefault(
            Messages.COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY,
            "{prefix}&eAp pehle se hee ekta party pe hai!"
        )
        this.addDefault(Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS, "{prefix}&cSirf party owner ye kar sakte hai!")
        this.addDefault(Messages.COMMAND_PARTY_ACCEPT_USAGE, "{prefix}&eUsage: &7/party accept <player>")
        this.addDefault(Messages.COMMAND_PARTY_ACCEPT_SUCCESS, "{prefix}&7{player} &eparty join kiye!")
        this.addDefault(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY, "{prefix}&cAp party me nahi hai!")
        this.addDefault(
            Messages.COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND,
            "{prefix}&cAp khudke party leave nahi kar sakte!\n&b/party disband &euse karne ki koshir kare."
        )
        this.addDefault(Messages.COMMAND_PARTY_LEAVE_SUCCESS, "{prefix}&7{player} &eparty leave kiye!")
        this.addDefault(Messages.COMMAND_PARTY_DISBAND_SUCCESS, "{prefix}&eParty disband ho gaya!")
        this.addDefault(Messages.COMMAND_PARTY_REMOVE_USAGE, "{prefix}&7Usage: &e/party remove <player>")
        this.addDefault(Messages.COMMAND_PARTY_REMOVE_SUCCESS, "{prefix}&7{player} &eparty se hata diya gaya,")
        this.addDefault(
            Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER,
            "{prefix}&7{player} &eapke party pe nahi hai!"
        )
        this.addDefault(Messages.COMMAND_PARTY_PROMOTE_SUCCESS, "{prefix}&eYou successfully promoted {player} to owner")
        this.addDefault(Messages.COMMAND_PARTY_PROMOTE_OWNER, "{prefix}&eYou have been promoted to party owner")
        this.addDefault(Messages.COMMAND_PARTY_PROMOTE_NEW_OWNER, "{prefix}&7 &e{player} has been promoted to owner")
        this.addDefault(Messages.COMMAND_PARTY_INFO_OWNER, "\n{prefix}&eOwner of the party is: &7{owner}")
        this.addDefault(Messages.COMMAND_PARTY_INFO_PLAYERS, "{prefix}&eParty members:")
        this.addDefault(Messages.COMMAND_PARTY_INFO_PLAYER, "&7{player}")
        this.addDefault(Messages.COMMAND_FORCESTART_NOT_IN_GAME, "§c▪ §7Ap khel nahi rahe!")
        this.addDefault(Messages.COMMAND_FORCESTART_SUCCESS, "§c▪ §7Countdown chota ho gaya!")
        this.addDefault(
            Messages.COMMAND_FORCESTART_NO_PERM,
            "{prefix}&7Ap arena forcestart nahi kar sakte.\n§7Aur features ke liye donate karein."
        )
        this.addDefault(
            Messages.COMMAND_COOLDOWN,
            "&cAp ise abhi bhi kar nahi sakte! Aur {seconds} seconds wait karein!"
        )
        this.addDefault(
            Messages.ARENA_JOIN_VIP_KICK,
            "{prefix}&cKhama kare, lekin apako bahar nikaal diya gaya kyonki ek donor arena mein shaamil ho gaye.\n&aAur features ke liye donate karein. &7&o(click)"
        )
        this.addDefault(
            Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT,
            "{prefix}§cParyaapt players nahi hai! Countdown stop ho gaya!"
        )
        this.addDefault(Messages.ARENA_RESTART_PLAYER_KICK, "{prefix}&eApka join kiya hua arena restart no hara hai.")
        this.addDefault(Messages.ARENA_STATUS_PLAYING_NAME, "&cPlaying")
        this.addDefault(Messages.ARENA_STATUS_RESTARTING_NAME, "&4Restarting ho raha hai")
        this.addDefault(Messages.ARENA_STATUS_WAITING_NAME, "&2Waiting §c{full}")
        this.addDefault(Messages.ARENA_STATUS_STARTING_NAME, "&6Starting §c{full}")
        this.addDefault(Messages.ARENA_GUI_INV_NAME, "&8Join karne ke liye click kariye!")
        this.addDefault(Messages.ARENA_GUI_ARENA_CONTENT_NAME, "&a&l{name}")
        this.addDefault(
            Messages.ARENA_GUI_ARENA_CONTENT_LORE,
            listOf(
                "",
                "&7Status: {status}",
                "&7Players: &f{on}&7/&f{max}",
                "&7Type: &a{group}",
                "",
                "&aLeft-Click to join.",
                "&eRight-Click to spectate."
            )
        )
        this.addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_NAME, "&r{serverIp}")
        this.addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_LORE, mutableListOf<Any?>())
        this.addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CHAT, "{prefix}&eYe game &6{time} &emey start hoga!")
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
            "&cAur players ke liye wait kar rahe hai.."
        )
        this.addDefault(Messages.ARENA_STATUS_START_PLAYER_TITLE, "&aGO")
        this.addDefault(
            Messages.ARENA_STATUS_START_PLAYER_TUTORIAL, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                &lBedWars", "",
                "&e&l    Apke bistar ka rakhsha kare aur dushman ka bistar nasht kare.",
                "&e&l             Shaktishali upgrade upayog karane ke liye",
                "&e&l     Generator se Iron, Gold, Emerald aur Diamond ikattha karke",
                "&e&l              Khudko aur apke team ko upgrade kare.", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        this.addDefault(
            Messages.ARENA_JOIN_DENIED_SELECTOR,
            "{prefix}&cKhama kare lekin is samay ap ye arena me join nahi ho sakte. Spectate karne ke liye Right-Click kare!"
        )
        this.addDefault(
            Messages.ARENA_SPECTATE_DENIED_SELECTOR,
            "{prefix}&cKhama kare lekin is samay ap ye arena ko spectate nahi kar sakte. Join karne ke liye Left-Click kare!"
        )
        this.addDefault(
            Messages.ARENA_JOIN_DENIED_NO_PROXY,
            "&cKhama kare lekin aapako BedWarsProxy ka upayog karke ek arena join karna padega. \n&eAgar ap ek arena setup karna chahte hai to ap khudko bw.setup permission dein taaki ap directly server join kar sake!"
        )
        this.addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_NAME, "&8Teleporter")
        this.addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME, "{vPrefix}{player}")
        this.addDefault(
            Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE,
            listOf(
                "&7Health: &f{health}%",
                "&7Food: &f{food}",
                "",
                "&7Spectate karne ke liye Left-click kare"
            )
        )
        this.addDefault(Messages.ARENA_SPECTATOR_LEAVE_ITEM_NAME, "&c&lLobby pe wapas jaye")
        this.addDefault(
            Messages.ARENA_SPECTATOR_LEAVE_ITEM_LORE,
            listOf("&7Lobby leave karne ke liye Right-click kare!")
        )
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE, "&aSpectating &7{player}")
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE, "&cExit karne ke liye SNEAK kare")
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE, "&eSpectator mode se bahar nikal rahe hai")
        this.addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE, "")
        this.addDefault(
            Messages.ARENA_LEAVE_PARTY_DISBANDED,
            "{prefix}§cParty ke owner leave kiye aur party disband ho gaya!"
        )
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TIER, "&eTier &c{tier}")
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND, "&b&lDiamond")
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD, "&a&lEmerald")
        this.addDefault(Messages.GENERATOR_HOLOGRAM_TIMER, "&eSpawns in &c{seconds} &eseconds")
        this.addDefault(
            Messages.GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT,
            "{prefix}{generatorType} Generators &ehave been upgraded to Tier &c{tier}"
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
        this.addDefault(Messages.FORMATTING_SCOREBOARD_YOUR_TEAM, "&7 YOU")
        this.addDefault(Messages.FORMATTING_ACTION_BAR_TRACKING, "&fTracking: {team} &f- Doori: {distance}m")
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
        this.addDefault(Messages.MEANING_FULL, "Full")
        this.addDefault(Messages.MEANING_SHOUT, "shout")
        this.addDefault(Messages.MEANING_NOBODY, "Nobody")
        this.addDefault(Messages.MEANING_NEVER, "Never")
        this.addDefault(Messages.MEANING_IRON_SINGULAR, "Iron")
        this.addDefault(Messages.MEANING_IRON_PLURAL, "Iron")
        this.addDefault(Messages.MEANING_GOLD_SINGULAR, "Gold")
        this.addDefault(Messages.MEANING_GOLD_PLURAL, "Gold")
        this.addDefault(Messages.MEANING_EMERALD_SINGULAR, "Emerald")
        this.addDefault(Messages.MEANING_EMERALD_PLURAL, "Emeralds")
        this.addDefault(Messages.MEANING_DIAMOND_SINGULAR, "Diamond")
        this.addDefault(Messages.MEANING_DIAMOND_PLURAL, "Diamonds")
        this.addDefault(Messages.MEANING_VAULT_SINGULAR, "$")
        this.addDefault(Messages.MEANING_VAULT_PLURAL, "$")
        this.addDefault(Messages.INTERACT_CANNOT_PLACE_BLOCK, "{prefix}&cAp yahape blocks place nahi kar sakte!")
        this.addDefault(
            Messages.INTERACT_CANNOT_BREAK_BLOCK,
            "{prefix}&cAp sirf ek player dvaara place kiye gaye blocks tod sakte hai!"
        )
        this.addDefault(Messages.INTERACT_CANNOT_BREAK_OWN_BED, "&cAp khudke bistar nahi tod sakte!")
        this.addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT,
            "\n&f&lBED DESTRUCTION > {TeamColor}{TeamName} {PlayerColor}{PlayerName} &7Ke bistar nasht kiye!\n"
        )
        this.addDefault(Messages.INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT, "&cBED TUTA!")
        this.addDefault(Messages.INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT, "&fAp aur repsawn nahi ho sakte!")
        this.addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM,
            "&f&lBED DESTRUCTION > {PlayerColor}{PlayerName} &7Apka bistar nasht kiye!"
        )
        this.addDefault(
            Messages.INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED,
            "&cAp ye chest open nahi kar sakte kyuki ye team eliminate nahi hua hai!"
        )
        this.addDefault(
            Messages.INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN,
            "&cAp ab invisible nahi rahe kyuku apne damage kiya hein!"
        )
        this.addDefault(Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL, "{PlayerColor}{PlayerName} &7void me gire.")
        this.addDefault(
            Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7void me gire. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL,
            "{KillerColor}{KillerName} {PlayerColor}{PlayerName} &7ko void me gira diye."
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL,
            "{KillerColor}{KillerName} {PlayerColor}{PlayerName} &7ko void me gira diye. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_REGULAR,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerName} &7Ke sath fight karne ke waqt disconnect kiye."
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_FINAL,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerName} &7Ke sath fight karne ke waqt disconnect kiye. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_REGULAR_KILL,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerName} &7dvaara diye gaye."
        )
        this.addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_FINAL_KILL,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerName} &7dvaara diye gaye. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerName} &7Ke bomb dvaara gire."
        )
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerName} &7Ke bomb dvaara gire. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR,
            "{PlayerColor}{PlayerName} &7Ek bomb se mar gaye."
        )
        this.addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7Ek bomb se mar gaye. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_REGULAR_KILL,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerName} &7Dvaara mare gaye."
        )
        this.addDefault(
            Messages.PLAYER_DIE_PVP_FINAL_KILL,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerName} &7Dvaara mare gaye. &b&lFINAL KILL!"
        )
        this.addDefault(Messages.PLAYER_DIE_UNKNOWN_REASON_REGULAR, "{PlayerColor}{PlayerName} &7mar gaye.")
        this.addDefault(
            Messages.PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL,
            "{PlayerColor}{PlayerName} &7mar gaye. &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_SHOOT_REGULAR,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerName} &7Ke teer se mare!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_SHOOT_FINAL_KILL,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerName} &7Ke teer se mare! &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_DEBUG_REGULAR,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerTeamName}&7KKe BedBug se mare!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_DEBUG_FINAL_KILL,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerTeamName}&7KKe BedBug se mare! &b&lFINAL KILL!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_REGULAR,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerTeamName}&7KKe Iron Golem se mare!"
        )
        this.addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_FINAL_KILL,
            "{PlayerColor}{PlayerName} {KillerColor}{KillerTeamName}&7KKe Iron Golem se mare! &b&lFINAL KILL!"
        )
        this.addDefault(Messages.PLAYER_DIE_REWARD_DIAMOND, "{prefix}&b+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_REWARD_EMERALD, "{prefix}&a+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_REWARD_IRON, "{prefix}&f+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_REWARD_GOLD, "{prefix}&6+{amount} {meaning}")
        this.addDefault(Messages.PLAYER_DIE_RESPAWN_TITLE, "&cAP MAR GAYE!")
        this.addDefault(Messages.PLAYER_DIE_RESPAWN_SUBTITLE, "&eAp &c{time} &eseconds me respawn honge!")
        this.addDefault(Messages.PLAYER_DIE_RESPAWN_CHAT, "{prefix}&eAp &c{time} &eseconds me respawn honge!")
        this.addDefault(Messages.PLAYER_DIE_RESPAWNED_TITLE, "&aAP RESPAWN HO GAYE!")
        this.addDefault(Messages.PLAYER_DIE_ELIMINATED_CHAT, "{prefix}&cAp eliminate ho gaye!")
        this.addDefault(Messages.PLAYER_HIT_BOW, "{prefix}{TeamColor}{PlayerName} &7Ka &c{amount} &7HP baki hai!")
        this.addDefault(Messages.GAME_END_GAME_OVER_PLAYER_TITLE, "&c&lGAME KHATAM HO GAYA!")
        this.addDefault(Messages.GAME_END_VICTORY_PLAYER_TITLE, "&6&lVIJAY!")
        this.addDefault(Messages.GAME_END_TEAM_WON_CHAT, "{prefix}{TeamColor}{TeamName} &ateam game jeete!")
        this.addDefault(
            Messages.GAME_END_TOP_PLAYER_CHAT, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &lBedWars",
                "",
                "{winnerFormat}",
                "",
                "",
                "&6                      &6⭐ &lPehla Killer &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&e                        &lDusra Killer &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&c                        &lTeesra Killer &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        this.addDefault(Messages.BED_HOLOGRAM_DEFEND, "&c&lApke bistar ko rakhsha kare!")
        this.addDefault(Messages.BED_HOLOGRAM_DESTROYED, "&c&lApka bistar tut gaya!")
        this.addDefault(Messages.NPC_NAME_TEAM_UPGRADES, "&bTEAM UPGRADES,&e&lRIGHT CLICK")
        this.addDefault(Messages.NPC_NAME_SOLO_UPGRADES, "&bSOLO UPGRADES,&e&lRIGHT CLICK")
        this.addDefault(Messages.NPC_NAME_TEAM_SHOP, "&bTEAM SHOP,&e&lRIGHT CLICK")
        this.addDefault(Messages.NPC_NAME_SOLO_SHOP, "&bITEM SHOP,&e&lRIGHT CLICK")
        this.addDefault(
            Messages.TEAM_ELIMINATED_CHAT,
            "\n&f&lTEAM ELIMINATED > {TeamColor}{TeamName} Team &celiminate ho gaya!\n"
        )
        this.addDefault(Messages.NEXT_EVENT_BEDS_DESTROY, "&cBeds Destruction")
        this.addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_II, "&fDiamond II")
        this.addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_III, "&fDiamond III")
        this.addDefault(Messages.NEXT_EVENT_DRAGON_SPAWN, "&fSudden Death")
        this.addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_II, "&fEmerald II")
        this.addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_III, "&fEmerald III")
        this.addDefault(Messages.NEXT_EVENT_GAME_END, "&4Game End")
        this.addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED, "&cBED TUTA!")
        this.addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED, "&fSabhi bistar nasht ho gaya!")
        this.addDefault(Messages.NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED, "&c&lSabhi bistar nasht ho gaya!")
        this.addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH, "&cSudden Death")
        this.addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH, "")
        this.addDefault(
            Messages.NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH,
            "&cSUDDEN DEATH: &6&b{TeamDragons} {TeamColor}{TeamName} Dragon!"
        )
        this.addDefault(Messages.XP_REWARD_PER_MINUTE, "{prefix}&6+{xp} BedWars praapt kiye (Play Time).")
        this.addDefault(Messages.XP_REWARD_WIN, "{prefix}&6+{xp} BedWars praapt kiye (Game Win).")
        this.addDefault(
            Messages.XP_REWARD_PER_TEAMMATE,
            "{prefix}&6+{xp} BedWars Experience praapt kiye (Team Support)."
        )
        this.addDefault(
            Messages.XP_REWARD_BED_DESTROY,
            "{prefix}&6+{xp} BedWars Experience praapt kiye (Bed Destroyed)."
        )
        this.addDefault(
            Messages.XP_REWARD_REGULAR_KILL,
            "{prefix}&6+{xp} BedWars Experience praapt kiye (Regular Kill)."
        )
        this.addDefault(Messages.XP_REWARD_FINAL_KILL, "{prefix}&6+{xp} BedWars Experience praapt kiye (Final Kill).")

        this.addDefault(Messages.MONEY_REWARD_PER_MINUTE, "{prefix}&6+{money} Coins mila (Play Time).")
        this.addDefault(Messages.MONEY_REWARD_WIN, "{prefix}&6+{money} Coins mila (Game Win).")
        this.addDefault(Messages.MONEY_REWARD_PER_TEAMMATE, "{prefix}&6+{money} Coins mila (Team Support).")
        this.addDefault(Messages.MONEY_REWARD_BED_DESTROYED, "{prefix}&6+{money} Coins mila (Bed Destroyed).")
        this.addDefault(Messages.MONEY_REWARD_FINAL_KILL, "{prefix}&6+{money} Coins mila (Final Kill).")
        this.addDefault(Messages.MONEY_REWARD_REGULAR_KILL, "{prefix}&6+{money} Coins mila (Regular Kill).")

        /* Lobby Command Items */
        this.addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "stats"), "&eStats")
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fStats dekhne ke liye Right-click kare!")
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "arena-selector"),
            "&eArena Selector"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "arena-selector"),
            listOf("&fArena choose karne ke liye Right-click kare!")
        )
        this.addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "leave"), "&eBack to Hub")
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fBedwars leave karne ke liye Right-click kare!")
        )
        /* Pre Game Command Items */
        this.addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "stats"), "&eStats")
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&fStats dekhne ke liye Right-click kare!")
        )
        this.addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "leave"), "&eBack to Lobby")
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fBedwars leave karne ke liye Right-click kare!")
        )
        /* Spectator Command Items */
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "teleporter"),
            "&eTeleporter"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "leave"),
            "&eLobby wapas jaye"
        )
        this.addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&fArena leavev karne ke liye Right-click kare!")
        )

        /* save default items messages for stats gui */
        this.addDefault(Messages.PLAYER_STATS_GUI_INV_NAME, "&8{player} Stats")
        addDefaultStatsMsg("wins", "&6Wins", "&f{wins}")
        addDefaultStatsMsg("losses", "&6Losses", "&f{losses}")
        addDefaultStatsMsg("kills", "&6Kills", "&f{kills}")
        addDefaultStatsMsg("deaths", "&6Deaths", "&f{deaths}")
        addDefaultStatsMsg("final-kills", "&6Final Kills", "&f{finalKills}")
        addDefaultStatsMsg("final-deaths", "&6Final Deaths", "&f{finalDeaths}")
        addDefaultStatsMsg("beds-destroyed", "&6Beds Destroyed", "&f{bedsDestroyed}")
        addDefaultStatsMsg("first-play", "&6First Play", "&f{firstPlay}")
        addDefaultStatsMsg("last-play", "&6Last Play", "&f{lastPlay}")
        addDefaultStatsMsg("games-played", "&6Games Played", "&f{gamesPlayed}")

        // Start of Sidebar
        this.addDefault(
            Messages.SCOREBOARD_LOBBY, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                "&fYour Level: {level}",
                "",
                "&fProgress: &a{currentXp}&7/&b{requiredXp}",
                "{progress}",
                "",
                "&7{player}",
                "",
                "&fCoins: &a{money}",
                "",
                "&fTotal Wins: &a{wins}",
                "&fTotal Kills: &a{kills}",
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
                "",
                "&e{serverIp}"
            )
        )
        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING_SPEC, listOf(
                "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",

                "&7{date} &8{server}",
                "&o&lSpectating",
                "&fMap: &a{map}",
                "",
                "&fPlayers: &a{on}/{max}",
                "",
                "&fWaiting,&fWaiting.,&fWaiting..,&fWaiting...",
                "",
                "&fMode: &a{group}",
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
                "&fPlayers: &a{on}/{max}",
                "",
                "&fStarting in &a{time}s",
                "",
                "§fMode: &a{group}",
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
                "§fMode: &a{group}",
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
                "&e&lBED WARS",
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
                "&fFinal Kills: &a{finalKills}",
                "&fBeds Broken: &a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "4v4v4v4"),
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
                "",
                "",
                "&e{serverIp}"
            )
        )
        this.addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED.replaceFirst("Default".toRegex(), "4v4v4v4"),
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
        this.addDefault(Messages.SHOP_INDEX_NAME, "&8Quick Buy")
        this.addDefault(
            Messages.SHOP_INSUFFICIENT_MONEY,
            "{prefix}&cApke paas paryaapt {currency} nahi hai! Apko aur {amount} jarurat hai!"
        )
        this.addDefault(Messages.SHOP_NEW_PURCHASE, "{prefix}&aApne &6{item} &akharida")
        this.addDefault(Messages.SHOP_ALREADY_BOUGHT, "{prefix}&cAp ise pehle se khareed chuke hain!")
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
                "&7Ye ek Quick Buy Slot hai!",
                "&7Koi item add karne ke liye",
                "&7Yahape &bSneal Click &7kare."
            )
        )
        this.addDefault(Messages.SHOP_CAN_BUY_COLOR, "&a")
        this.addDefault(Messages.SHOP_CANT_BUY_COLOR, "&c")
        this.addDefault(Messages.SHOP_LORE_STATUS_CAN_BUY, "&ePurchase karne ke liye click kare!")
        this.addDefault(Messages.SHOP_LORE_STATUS_CANT_AFFORD, "&cApke paas paryaapt {currency} nahi hai!")
        this.addDefault(Messages.SHOP_LORE_STATUS_MAXED, "&aMAXED!")
        this.addDefault(Messages.SHOP_LORE_QUICK_ADD, "&7Quick Buy pe add karne ke liye &bSneak Click &7kare")
        this.addDefault(Messages.SHOP_LORE_QUICK_REMOVE, "&7Quick Buy se remove karne ke liye &bSneak Click &7kare")


        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "&8Blocks",
            "&aBlocks",
            listOf("&eClick to view!")
        )

        addContentMessages(
            this, "wool", ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "{color}Wool", listOf(
                "&7Cost: &f{cost} {currency}", "", "&7Great for bridging across", "&7islands. Turns into your team's",
                "&7color.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "clay",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Hardened Clay",
            listOf(
                "&7Cost: {cost} {currency}",
                "",
                "&7Basic block to defend your bed.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "glass",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}Blast-Proof Glass",
            listOf(
                "&7Cost: {cost} {currency}",
                "",
                "&7Immune to explosions.",
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
                "&7Cost: {cost} {currency}",
                "",
                "&7Solid block to defend your bed.",
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
                "&7Cost: {cost} {currency}",
                "",
                "&7Useful to save cats stuck in",
                "&7trees.",
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
                "&7Cost: {cost} {currency}",
                "",
                "&7Extreme protection for your bed.",
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
                "&7Cost: {cost} {currency}",
                "",
                "&7Solid block to defend your bed",
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
            listOf("&eClick to view!")
        )

        addContentMessages(
            this,
            "stone-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Stone Sword",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "iron-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Iron Sword",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "diamond-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Diamond Sword",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "stick",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}Stick (KnockBack I)",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
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
            "{color}Permanent Chainmail Armor",
            listOf(
                "&7Cost: {cost} {currency}",
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
            "{color}Permanent Iron Armor",
            listOf(
                "&7Cost: {cost} {currency}",
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
            "{color}Permanent Diamond Armor",
            listOf(
                "&7Cost: {cost} {currency}",
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
            "&8Tools",
            "&aTools",
            listOf("&eClick to view!")
        )

        addContentMessages(
            this, "shears", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Permanent Shears", listOf(
                "&7Cost: {cost} {currency}",
                "",
                "&7Great to get rid of wool. You",
                "&7will always spawn with these shears.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "pickaxe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Pickaxe {tier}", listOf(
                "&7Cost: {cost} {currency}",
                "&7Tier: &e{tier}",
                "",
                "&7This is an upgradable item.",
                "&7It will lose 1 tier upon.",
                "&7death!",
                "",
                "&7You will permanently",
                "&7respawn with at least the",
                "&7lowest tier.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "axe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}Axe {tier}", listOf(
                "&7Cost: {cost} {currency}",
                "&7Tier: &e{tier}",
                "",
                "&7This is an upgradable item.",
                "&7It will lose 1 tier upon.",
                "&7death!",
                "",
                "&7You will permanently",
                "&7respawn with at least the",
                "&7lowest tier.",
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
            listOf("&eClick to view!")
        )

        addContentMessages(
            this,
            "arrow",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Arrow",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow1",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Bow",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow2",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Bow (Power I)",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow3",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}Bow (Power I, Punch I)",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "&8Potions",
            "&aPotions",
            listOf("&eClick to view!")
        )

        addContentMessages(
            this,
            "speed-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Speed II Potion (45 seconds)",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "jump-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Jump V Potion (45 seconds)",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "invisibility",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}Invisibility Potion (30 seconds)",
            listOf("&7Cost: {cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "&8Utility",
            "&aUtility",
            listOf("&eClick to view!")
        )

        addContentMessages(
            this,
            "golden-apple",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Golden Apple",
            listOf(
                "&7Cost: {cost} {currency}",
                "",
                "&7Well-rounded healing.",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "bedbug", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}BedBug", listOf(
                "&7Cost: {cost} {currency}", "", "&7Spawns silverfish where the",
                "&7snowball lands to distract your", "&7enemies. Lasts 15 seconds.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "dream-defender",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}Dream Defender",
            listOf(
                "&7Cost: {cost} {currency}", "", "&7Iron Golem to help defend your",
                "&7base. Lasts 4 minutes.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "fireball", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Fireball", listOf(
                "&7Cost: {cost} {currency}", "", "&7Right-click to launch! Great to",
                "&7knock back enemies walking on", "&7thin bridges", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "tnt", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}TNT", listOf(
                "&7Cost: {cost} {currency}", "", "&7Instantly ignites, appropriate",
                "&7to explode things!", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "ender-pearl", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Ender Pearl", listOf(
                "&7Cost: {cost} {currency}", "", "&7The quickest way to invade enemy",
                "&7bases.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "water-bucket", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Water Bucket", listOf(
                "&7Cost: {cost} {currency}", "", "&7Great to slow down approaching",
                "&7enemies. Can also protect", "&7against TNT.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "bridge-egg", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Bridge Egg", listOf(
                "&7Cost: {cost} {currency}", "", "&7This egg creates a bridge in its",
                "&7trial after being thrown.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "magic-milk", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Magic Milk", listOf(
                "&7Cost: {cost} {currency}", "", "&7Avoid triggering traps for 60",
                "&7seconds after consuming.", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "sponge", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}Sponge", listOf(
                "&7Cost: {cost} {currency}", "", "&7Great for soaking up water.",
                "", "{quick_buy}", "{buy_status}"
            )
        )

        //
        this.addDefault(Messages.MEANING_NO_TRAP, "No trap!")
        this.addDefault(Messages.FORMAT_SPECTATOR_TARGET, "{targetTeamColor}{targetDisplayName}")
        this.addDefault(Messages.FORMAT_UPGRADE_TRAP_COST, "&7Cost: {currencyColor}{cost} {currency}")
        this.addDefault(Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD, "&e")
        this.addDefault(Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD, "&c")
        this.addDefault(Messages.FORMAT_UPGRADE_COLOR_UNLOCKED, "&a")
        this.addDefault(Messages.FORMAT_UPGRADE_TIER_LOCKED, "&7")
        this.addDefault(Messages.FORMAT_UPGRADE_TIER_UNLOCKED, "&a")
        this.addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "{color}Purchase karne ke liye Click kare!")
        this.addDefault(
            Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY,
            "{color}Apke paas paryaapt {currency} nahi hai"
        )
        this.addDefault(Messages.UPGRADES_LORE_REPLACEMENT_LOCKED, "&cLOCKED")
        this.addDefault(Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED, "{color}UNLOCKED")
        this.addDefault(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT, "&a{player} purchased &6{upgradeName}")
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-1"),
            "{color}Iron Forge"
        )
        this.addDefault(
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
        this.addDefault(Messages.UPGRADES_CATEGORY_ITEM_NAME_PATH + "traps", "&eBuy a trap")
        this.addDefault(
            Messages.UPGRADES_CATEGORY_ITEM_LORE_PATH + "traps",
            listOf("&7Purchased traps will be", "&7queued on the right.", "", "&eClick to browse!")
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "swords").replace("{tier}", "tier-1"),
            "{color}Sharpened Swords"
        )
        this.addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "swords"),
            listOf(
                "&7Your team permanently gains",
                "&7Sharpness I on all swords and",
                "&7axes!",
                "",
                "&7Cost: &b{tier_1_cost} {tier_1_currency}",
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
                "&7All players on your team",
                "&7permanently gain Haste.",
                "",
                "{tier_1_color}Tier 1: Haste I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}Tier 2: Haste II, &b{tier_2_color} {tier_2_currency}",
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
                "&7Creates a Regeneration field",
                "&7around yor base!",
                "",
                "{tier_1_color}Cost: &b{tier_1_cost} {tier_1_currency}",
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
                "&7Your team will have 2 dragons",
                "&7instead of 1 during deathmatch!",
                "",
                "{tier_1_color}Cost: &b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        this.addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "glass", "&8⬆&7Purchasable")
        this.addDefault(Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "glass", listOf("&8⬇&7Traps Queue"))
        this.addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "first", "{color}Trap #1: {name}")
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "first",
            listOf("&7The first enemy to walk", "&7into your base will trigger", "&7this trap!")
        )
        this.addDefault(
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
        this.addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "second", "{color}Trap #2: {name}")
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "second",
            listOf("&7The second enemy to walk", "&7into your base will trigger", "&7this trap!")
        )
        this.addDefault(
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
        this.addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "third", "{color}Trap #3: {name}")
        this.addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "third",
            listOf("&7The third enemy to walk", "&7into your base will trigger", "&7this trap!")
        )
        this.addDefault(
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
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "1", "{color}It's a trap!")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "1",
            listOf("&7Inflicts Blindness and Slowness", "&7for 5 seconds.", "")
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "2", "{color}Counter-Offensive Trap")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "2",
            listOf("&7Grants Speed I for 15 seconds to", "&7allied players near your base.", "")
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "3", "{color}Alarm Trap")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "3",
            listOf("&7Reveales invisible players as", "&7well as their name and team.", "")
        )
        this.addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "4", "{color}Miner Fatigue Trap")
        this.addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "4",
            listOf("&7Inflict Mining Fatigue for 10", "&7seconds.", "")
        )
        this.addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "back", "&aBack")
        this.addDefault(
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "back",
            listOf("&7To Upgrades & Traps")
        )
        this.addDefault(Messages.UPGRADES_CATEGORY_GUI_NAME_PATH + "traps", "&8Queue a trap")
        this.addDefault(Messages.UPGRADES_TRAP_QUEUE_LIMIT, "&cTrap queue full!")
        this.addDefault(Messages.UPGRADES_TRAP_DEFAULT_MSG, "&c&l{trap} was set off!")
        this.addDefault(Messages.UPGRADES_TRAP_DEFAULT_TITLE, "&cTRAP TRIGGERED!")
        this.addDefault(Messages.UPGRADES_TRAP_DEFAULT_SUBTITLE, "&fYour {trap} has been triggered!")
        this.addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_MSG + "3",
            "&c&lAlarm trap set off by &7&l{player} &c&lfrom {color}&l{team} &c&lteam!"
        )
        this.addDefault(Messages.UPGRADES_TRAP_CUSTOM_TITLE + "3", "&c&lALARM!!!")
        this.addDefault(Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + "3", "&fAlarm trap set off by {color}{team} &fteam!")
        save()
        setPrefix(m(Messages.PREFIX))
    }
}
