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

class English : Language(BedWars.INSTANCE, "en") {
    init {
        val mainCmd = BedWars.MAIN_COMMAND
        this.apply {
            options().copyDefaults(true)
            addDefault(Messages.PREFIX, "")
            addDefault("name", "English")

            // this must stay here
            // move message to new path
            if (get("player-die-knocked-regular") != null && get(Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL) == null) {
                set(Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL, getString("player-die-knocked-regular"))
                set("player-die-knocked-regular", null)
            }
            if (get("player-die-knocked-final") != null && get(Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL) == null) {
                set(Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL, getString("player-die-knocked-final"))
                set("player-die-knocked-final", null)
            }

            addDefault(
                Messages.COMMAND_MAIN,
                listOf(
                    "",
                    "&2▪ &7/$mainCmd stats",
                    "&2▪ &7/$mainCmd join &o<arena/group>",
                    "&2▪ &7/$mainCmd leave",
                    "&2▪ &7/$mainCmd lang",
                    "&2▪ &7/$mainCmd gui",
                    "&2▪ &7/$mainCmd start &3(vip)"
                )
            )
            addDefault(Messages.COMMAND_LANG_LIST_HEADER, "{prefix} &2Available languages:")
            addDefault(Messages.COMMAND_LANG_LIST_FORMAT, "&a▪  &7{iso} - &f{name}")
            addDefault(Messages.COMMAND_LANG_USAGE, "{prefix}&7Usage: /lang &f&o<iso>")
            addDefault(Messages.COMMAND_LANG_SELECTED_NOT_EXIST, "{prefix}&cThis language doesn't exist!")
            addDefault(Messages.COMMAND_LANG_SELECTED_SUCCESSFULLY, "{prefix}&aLanguage changed!")
            addDefault(
                Messages.COMMAND_LANG_USAGE_DENIED,
                "{prefix}&cYou can't change the language during the game."
            )
            addDefault(Messages.COMMAND_JOIN_USAGE, "§a▪ §7Usage: /$mainCmd join §o<arena/group>")
            addDefault(
                Messages.COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND,
                "{prefix}&cThere isn't any arena or arena group called: {name}"
            )
            addDefault(
                Messages.COMMAND_JOIN_DENIED_IS_FULL,
                "{prefix}&cThis arena is full!\n&aPlease consider donating for more features. &7&o(click)"
            )
            addDefault(
                Messages.COMMAND_JOIN_NO_EMPTY_FOUND,
                "{prefix}&cThere isn't any arena available right now ;("
            )
            addDefault(
                Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS,
                "{prefix}&cWe apologise but this arena is full.\n&cWe know you're a donor but actually this arena is full of staff or/and donors."
            )
            addDefault(
                Messages.COMMAND_JOIN_DENIED_PARTY_TOO_BIG,
                "{prefix}&cYour party is too big for joining this arena as a team :("
            )
            addDefault(
                Messages.COMMAND_JOIN_DENIED_NOT_PARTY_LEADER,
                "{prefix}&cOnly the leader can choose the arena."
            )
            addDefault(
                Messages.COMMAND_JOIN_PLAYER_JOIN_MSG,
                "{prefix}&7{player} &ehas joined (&b{on}&e/&b{max}&e)!"
            )
            addDefault(
                Messages.COMMAND_JOIN_SPECTATOR_MSG,
                "{prefix}§6You are now spectating §9{arena}§6.\n{prefix}§eYou can leave the arena at any time doing §c/leave§e."
            )
            addDefault(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG, "&cSpectators are not allowed in this arena!")
            addDefault(Messages.COMMAND_TP_PLAYER_NOT_FOUND, "{prefix}&cPlayer not found!")
            addDefault(Messages.COMMAND_TP_NOT_IN_ARENA, "{prefix}&cThis player is not in a bedwars arena!")
            addDefault(Messages.COMMAND_TP_NOT_STARTED, "{prefix}&cThe arena where the player is didn't start yet!")
            addDefault(Messages.COMMAND_TP_USAGE, "{prefix}&cUsage: /bw tp <username>")
            addDefault(Messages.REJOIN_NO_ARENA, "{prefix}&cThere is no arena to rejoin!")
            addDefault(
                Messages.REJOIN_DENIED,
                "{prefix}&cYou can't rejoin the arena anymore. Game ended or bed destroyed."
            )
            addDefault(Messages.REJOIN_ALLOWED, "{prefix}&eJoining arena &a{arena}&e!")
            addDefault(Messages.COMMAND_REJOIN_PLAYER_RECONNECTED, "{prefix}&7{player} &ehas reconnected!")
            addDefault(Messages.COMMAND_LEAVE_DENIED_NOT_IN_ARENA, "{prefix}&cYou're not in arena!")
            addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&7{player} &ehas quit!")
            addDefault(Messages.COMMAND_NOT_ALLOWED_IN_GAME, "{prefix}&cYou can't do this during the game.")
            addDefault(
                Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS,
                "{prefix}&cCommand not found or you don't have permission!"
            )
            addDefault(
                Messages.COMMAND_PARTY_HELP, listOf(
                    "&6▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                    "&aParty Commands:",
                    "&e/party help &7- &bPrints this help message",
                    "&e/party invite <player> &7- &bInvites the player to your party",
                    "&e/party leave &7- &bLeaves the current party",
                    "&e/party remove <player> &7- &bRemove the player from the party",
                    "&e/party info &7- &bShow party members and owner",
                    "&e/party promote <player> &7- &bTransfer party ownership",
                    "&e/party accept <player> &7- &bAccept a party invite",
                    "&e/party disband &7- &bDisbands the party"
                )
            )
            addDefault(Messages.COMMAND_PARTY_INVITE_USAGE, "{prefix}&eUsage: &7/party invite <player>")
            addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eis not online!")
            addDefault(Messages.COMMAND_PARTY_INVITE_SENT, "{prefix}&eInvite sent to &7{player}&6.")
            addDefault(
                Messages.COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG,
                "{prefix}&b{player} &ehas invited you to a party! &o&7(Click to accept)"
            )
            addDefault(
                Messages.COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF,
                "{prefix}&cYou cannot invite yourself!"
            )
            addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player} &eis offline!")
            addDefault(
                Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE,
                "{prefix}&cThere are no party requests to accept!"
            )
            addDefault(
                Messages.COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY,
                "{prefix}&eYou're already in a party!"
            )
            addDefault(
                Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS,
                "{prefix}&cOnly the party owner can do this!"
            )
            addDefault(Messages.COMMAND_PARTY_ACCEPT_USAGE, "{prefix}&eUsage: &7/party accept <player>")
            addDefault(Messages.COMMAND_PARTY_ACCEPT_SUCCESS, "{prefix}&7{player} &ehas joined the party!")
            addDefault(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY, "{prefix}&cYou're not in a party!")
            addDefault(
                Messages.COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND,
                "{prefix}&cYou can't leave your own party!\n&eTry using: &b/party disband"
            )
            addDefault(Messages.COMMAND_PARTY_LEAVE_SUCCESS, "{prefix}&7{player} &ehas left the party!")
            addDefault(Messages.COMMAND_PARTY_DISBAND_SUCCESS, "{prefix}&eParty disbanded!")
            addDefault(Messages.COMMAND_PARTY_REMOVE_USAGE, "{prefix}&7Usage: &e/party remove <player>")
            addDefault(Messages.COMMAND_PARTY_REMOVE_SUCCESS, "{prefix}&7{player} &ewas removed from the party,")
            addDefault(
                Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER,
                "{prefix}&7{player} &eis not in your party!"
            )
            addDefault(
                Messages.COMMAND_PARTY_PROMOTE_SUCCESS,
                "{prefix}&eYou successfully promoted {player} to owner"
            )
            addDefault(Messages.COMMAND_PARTY_PROMOTE_OWNER, "{prefix}&eYou have been promoted to party owner")
            addDefault(Messages.COMMAND_PARTY_PROMOTE_NEW_OWNER, "{prefix}&7 &e{player} has been promoted to owner")
            addDefault(Messages.COMMAND_PARTY_INFO_OWNER, "\n{prefix}&eOwner of the party is: &7{owner}")
            addDefault(Messages.COMMAND_PARTY_INFO_PLAYERS, "{prefix}&eParty members:")
            addDefault(Messages.COMMAND_PARTY_INFO_PLAYER, "&7{player}")
            addDefault(Messages.COMMAND_FORCESTART_NOT_IN_GAME, "§c▪ §7You're not playing!")
            addDefault(Messages.COMMAND_FORCESTART_SUCCESS, "§c▪ §7Countdown shortened!")
            addDefault(
                Messages.COMMAND_FORCESTART_NO_PERM,
                "{prefix}&7You can't forcestart the arena.\n§7Please consider donating for VIP features."
            )
            addDefault(Messages.COMMAND_COOLDOWN, "&cYou can't do that yet! Wait {seconds} more seconds!")
            addDefault(
                Messages.ARENA_JOIN_VIP_KICK,
                "{prefix}&cSorry, but you were kicked out because a donor joined the arena.\n&aPlease consider donating for more features. &7&o(click)"
            )
            addDefault(
                Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT,
                "{prefix}§cThere aren't enough players! Countdown stopped!"
            )
            addDefault(Messages.ARENA_RESTART_PLAYER_KICK, "{prefix}&eThe arena you were in is restarting.")
            addDefault(Messages.ARENA_STATUS_PLAYING_NAME, "&cPlaying")
            addDefault(Messages.ARENA_STATUS_RESTARTING_NAME, "&4Restarting")
            addDefault(Messages.ARENA_STATUS_WAITING_NAME, "&2Waiting §c{full}")
            addDefault(Messages.ARENA_STATUS_STARTING_NAME, "&6Starting §c{full}")
            addDefault(Messages.ARENA_GUI_INV_NAME, "&8Click to join")
            addDefault(Messages.ARENA_GUI_ARENA_CONTENT_NAME, "&a&l{name}")
            addDefault(
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
            addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_NAME, "&r{serverIp}")
            addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_LORE, emptyList<String>())
            addDefault(
                Messages.ARENA_STATUS_START_COUNTDOWN_CHAT,
                "{prefix}&eThe game starts in &6{time} &eseconds!"
            )
            addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_TITLE, " ")
            addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE, "&a{second}")
            addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-5", "&e❺")
            addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-4", "&e❹")
            addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-3", "&c❸")
            addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-2", "&c❷")
            addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-1", "&c❶")
            addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_TITLE, " ")
            addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_SUB_TITLE, "&cWaiting for more players..")
            addDefault(Messages.ARENA_STATUS_START_PLAYER_TITLE, "&aGO")
            addDefault(
                Messages.ARENA_STATUS_START_PLAYER_TUTORIAL, listOf(
                    "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                    "&f                                   &lBedWars", "",
                    "&e&l    Protect your bed and destroy the enemy beds.",
                    "&e&l      Upgrade yourself and your team by collecting",
                    "&e&l   Iron, Gold, Emerald, and Diamond from generators",
                    "&e&l             to access powerful upgrades.", "",
                    "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
                )
            )
            addDefault(
                Messages.ARENA_JOIN_DENIED_SELECTOR,
                "{prefix}&cSorry but you can't join this arena at this moment. Use Right-Click to spectate!"
            )
            addDefault(
                Messages.ARENA_SPECTATE_DENIED_SELECTOR,
                "{prefix}&cSorry but you can't spectate this arena at this moment. Use Left-Click to join!"
            )
            addDefault(
                Messages.ARENA_JOIN_DENIED_NO_PROXY,
                "&cSorry but you must join an arena using BedWarsProxy. \n&eIf you want to setup an arena make sure to give yourself the bw.setup permission so you can join the server directly!"
            )
            addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_NAME, "&8Teleporter")
            addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME, "{vPrefix}{player}")
            addDefault(
                Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE,
                listOf("&7Health: &f{health}%", "&7Food: &f{food}", "", "&7Left-click to spectate")
            )
            addDefault(Messages.ARENA_SPECTATOR_LEAVE_ITEM_NAME, "&c&lReturn to lobby")
            addDefault(
                Messages.ARENA_SPECTATOR_LEAVE_ITEM_LORE,
                listOf("&7Right-click to leave to the lobby!")
            )
            addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE, "&aSpectating &7{player}")
            addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE, "&cSNEAK to exit")
            addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE, "&eExiting Spectator mode")
            addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE, "")
            addDefault(
                Messages.ARENA_LEAVE_PARTY_DISBANDED,
                "{prefix}§cThe party owner has left and the party was disbanded!"
            )
            addDefault(Messages.GENERATOR_HOLOGRAM_TIER, "&eTier &c{tier}")
            addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND, "&b&lDiamond")
            addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD, "&a&lEmerald")
            addDefault(Messages.GENERATOR_HOLOGRAM_TIMER, "&eSpawns in &c{seconds} &eseconds")
            addDefault(
                Messages.GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT,
                "{prefix}{generatorType} Generators &ehave been upgraded to Tier &c{tier}"
            )
            addDefault(Messages.FORMATTING_CHAT_LOBBY, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
            addDefault(Messages.FORMATTING_CHAT_WAITING, "{level}{vPrefix}&7{player}{vSuffix}: {message}")
            addDefault(
                Messages.FORMATTING_CHAT_SHOUT,
                "{level}{vPrefix}&6[SHOUT] {team} &7{player}&f{vSuffix}: {message}"
            )
            addDefault(Messages.FORMATTING_CHAT_TEAM, "{level}{vPrefix}&f{team}&7 {player}{vSuffix} {message}")
            addDefault(
                Messages.FORMATTING_CHAT_SPECTATOR,
                "{level}{vPrefix}&7[SPECTATOR] {player}{vSuffix}: {message}"
            )
            addDefault(Messages.FORMATTING_SCOREBOARD_HEALTH, listOf("&c❤", "&aHealth"))

            addDefault(Messages.FORMATTING_SCOREBOARD_DATE, "dd/MM/yy")
            addDefault(
                Messages.FORMATTING_SCOREBOARD_TEAM_GENERIC,
                "{TeamColor}{TeamLetter}&f {TeamName}: {TeamStatus}"
            )
            addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ELIMINATED, "&c&l✘")
            addDefault(Messages.FORMATTING_SCOREBOARD_BED_DESTROYED, "&a{remainingPlayers}")
            addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ALIVE, "&a&l✓")
            addDefault(Messages.FORMATTING_SCOREBOARD_NEXEVENT_TIMER, "mm:ss")
            addDefault(Messages.FORMATTING_SCOREBOARD_YOUR_TEAM, "&7 YOU")
            addDefault(Messages.FORMATTING_ACTION_BAR_TRACKING, "&fTracking: {team} &f- Distance: {distance}m")
            addDefault(Messages.FORMATTING_TEAM_WINNER_FORMAT, "      {TeamColor}{TeamName} &7- {members}")
            addDefault(
                Messages.FORMATTING_SOLO_WINNER_FORMAT,
                "                 {TeamColor}{TeamName} &7- {members}"
            )
            addDefault(Messages.FORMATTING_GENERATOR_TIER1, "I")
            addDefault(Messages.FORMATTING_GENERATOR_TIER2, "II")
            addDefault(Messages.FORMATTING_GENERATOR_TIER3, "III")
            addDefault(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH, "▮ ")
            addDefault(Messages.FORMATTING_STATS_DATE_FORMAT, "yyyy/MM/dd HH:mm")
            addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_TEAM, "{TeamColor}[{TeamName}]")
            addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SHOUT, "&6[SHOUT]")
            addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SPECTATOR, "&7[SPECTATOR]")
            addDefault(Messages.MEANING_FULL, "Full")
            addDefault(Messages.MEANING_SHOUT, "shout")
            addDefault(Messages.MEANING_NOBODY, "Nobody")
            addDefault(Messages.MEANING_NEVER, "Never")
            addDefault(Messages.MEANING_IRON_SINGULAR, "Iron")
            addDefault(Messages.MEANING_IRON_PLURAL, "Iron")
            addDefault(Messages.MEANING_GOLD_SINGULAR, "Gold")
            addDefault(Messages.MEANING_GOLD_PLURAL, "Gold")
            addDefault(Messages.MEANING_EMERALD_SINGULAR, "Emerald")
            addDefault(Messages.MEANING_EMERALD_PLURAL, "Emeralds")
            addDefault(Messages.MEANING_DIAMOND_SINGULAR, "Diamond")
            addDefault(Messages.MEANING_DIAMOND_PLURAL, "Diamonds")
            addDefault(Messages.MEANING_VAULT_SINGULAR, "$")
            addDefault(Messages.MEANING_VAULT_PLURAL, "$")
            addDefault(Messages.INTERACT_CANNOT_PLACE_BLOCK, "{prefix}&cYou can't place blocks here!")
            addDefault(
                Messages.INTERACT_CANNOT_BREAK_BLOCK,
                "{prefix}&cYou can only break blocks placed by a player!"
            )
            addDefault(Messages.INTERACT_CANNOT_BREAK_OWN_BED, "&cYou can't destroy your own bed!")
            addDefault(
                Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT,
                "\n&f&lBED DESTRUCTION > {TeamColor}{TeamName} Bed &7was deep fried by {PlayerColor}{PlayerName}&7!\n"
            )
            addDefault(Messages.INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT, "&cBED DESTROYED!")
            addDefault(Messages.INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT, "&fYou will no longer respawn!")
            addDefault(
                Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM,
                "&f&lBED DESTRUCTION > &7Your bed was iced by {PlayerColor}{PlayerName}&7!"
            )
            addDefault(
                Messages.INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED,
                "&cYou can't open this chest because this team wasn't eliminated!"
            )
            addDefault(
                Messages.INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN,
                "&cYou are no longer invisible because you have taken damage!"
            )
            addDefault(
                Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL,
                "{PlayerColor}{PlayerName} &7fell into the void."
            )
            addDefault(
                Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL,
                "{PlayerColor}{PlayerName} &7fell into the void. &b&lFINAL KILL!"
            )
            addDefault(
                Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL,
                "{PlayerColor}{PlayerName} &7was knocked into the void by {KillerColor}{KillerName}&7."
            )
            addDefault(
                Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL,
                "{PlayerColor}{PlayerName} &7was knocked into the void by {KillerColor}{KillerName}&7. &b&lFINAL KILL!"
            )
            addDefault(
                Messages.PLAYER_DIE_PVP_LOG_OUT_REGULAR,
                "{PlayerColor}{PlayerName} &7disconnected while fighting with {KillerColor}{KillerName}&7."
            )
            addDefault(
                Messages.PLAYER_DIE_PVP_LOG_OUT_FINAL,
                "{PlayerColor}{PlayerName} &7disconnected while fighting with {KillerColor}{KillerName}&7. &b&lFINAL KILL!"
            )
            addDefault(
                Messages.PLAYER_DIE_KNOCKED_BY_REGULAR_KILL,
                "{PlayerColor}{PlayerName} &7was pushed by {KillerColor}{KillerName}&7."
            )
            addDefault(
                Messages.PLAYER_DIE_KNOCKED_BY_FINAL_KILL,
                "{PlayerColor}{PlayerName} &7was pushed by {KillerColor}{KillerName}&7. &b&lFINAL KILL!"
            )
            addDefault(
                Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL,
                "{PlayerColor}{PlayerName} &7was hit off by a love bomb from {KillerColor}{KillerName}&7."
            )
            addDefault(
                Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL,
                "{PlayerColor}{PlayerName} &7was hit off by a love bomb from {KillerColor}{KillerName}&7. &b&lFINAL KILL!"
            )
            addDefault(
                Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR,
                "{PlayerColor}{PlayerName} &7was hit off by a bomb."
            )
            addDefault(
                Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL,
                "{PlayerColor}{PlayerName} &7was hit off by a bomb. &b&lFINAL KILL!"
            )
            addDefault(
                Messages.PLAYER_DIE_PVP_REGULAR_KILL,
                "{PlayerColor}{PlayerName} &7was killed by {KillerColor}{KillerName}&7."
            )
            addDefault(
                Messages.PLAYER_DIE_PVP_FINAL_KILL,
                "{PlayerColor}{PlayerName} &7was killed by {KillerColor}{KillerName}&7. &b&lFINAL KILL!"
            )
            addDefault(Messages.PLAYER_DIE_UNKNOWN_REASON_REGULAR, "{PlayerColor}{PlayerName} &7died.")
            addDefault(
                Messages.PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL,
                "{PlayerColor}{PlayerName} &7died. &b&lFINAL KILL!"
            )
            addDefault(
                Messages.PLAYER_DIE_SHOOT_REGULAR,
                "{PlayerColor}{PlayerName} &7was shoot by {KillerColor}{KillerName}&7!"
            )
            addDefault(
                Messages.PLAYER_DIE_SHOOT_FINAL_KILL,
                "{PlayerColor}{PlayerName} &7was shoot by {KillerColor}{KillerName}&7! &b&lFINAL KILL!"
            )
            addDefault(
                Messages.PLAYER_DIE_DEBUG_REGULAR,
                "{PlayerColor}{PlayerName} &7was killed by {KillerColor}{KillerTeamName}'s &7BedBug!"
            )
            addDefault(
                Messages.PLAYER_DIE_DEBUG_FINAL_KILL,
                "{PlayerColor}{PlayerName} &7was killed by {KillerColor}{KillerTeamName}'s &7BedBug! &b&lFINAL KILL!"
            )
            addDefault(
                Messages.PLAYER_DIE_IRON_GOLEM_REGULAR,
                "{PlayerColor}{PlayerName} &7was killed by {KillerColor}{KillerTeamName}'s &7Iron Golem!"
            )
            addDefault(
                Messages.PLAYER_DIE_IRON_GOLEM_FINAL_KILL,
                "{PlayerColor}{PlayerName} &7was killed by {KillerColor}{KillerTeamName}'s &7Iron Golem! &b&lFINAL KILL!"
            )
            addDefault(Messages.PLAYER_DIE_REWARD_DIAMOND, "{prefix}&b+{amount} {meaning}")
            addDefault(Messages.PLAYER_DIE_REWARD_EMERALD, "{prefix}&a+{amount} {meaning}")
            addDefault(Messages.PLAYER_DIE_REWARD_IRON, "{prefix}&f+{amount} {meaning}")
            addDefault(Messages.PLAYER_DIE_REWARD_GOLD, "{prefix}&6+{amount} {meaning}")
            addDefault(Messages.PLAYER_DIE_RESPAWN_TITLE, "&cYOU DIED!")
            addDefault(Messages.PLAYER_DIE_RESPAWN_SUBTITLE, "&eYou will respawn in &c{time} &eseconds!")
            addDefault(Messages.PLAYER_DIE_RESPAWN_CHAT, "{prefix}&eYou will respawn in &c{time} &eseconds!")
            addDefault(Messages.PLAYER_DIE_RESPAWNED_TITLE, "&aRESPAWNED!")
            addDefault(Messages.PLAYER_DIE_ELIMINATED_CHAT, "{prefix}&cYou have been eliminated!")
            addDefault(Messages.PLAYER_HIT_BOW, "{prefix}{TeamColor}{PlayerName} &7is on &c{amount} &7HP!")
            addDefault(Messages.GAME_END_GAME_OVER_PLAYER_TITLE, "&c&lGAME OVER!")
            addDefault(Messages.GAME_END_VICTORY_PLAYER_TITLE, "&6&lVICTORY!")
            addDefault(Messages.GAME_END_TEAM_WON_CHAT, "{prefix}{TeamColor}{TeamName} &ahas won the game!")
            addDefault(
                Messages.GAME_END_TOP_PLAYER_CHAT, listOf(
                    "",
                    "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                    "&f                                   &lBedWars", "", "{winnerFormat}", "", "",
                    "&6                       &6⭐ &l1st Killer &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                    "&e                          &l2nd Killer &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                    "&c                          &l3rd Killer &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                    "",
                    "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
                )
            )
            addDefault(Messages.BED_HOLOGRAM_DEFEND, "&c&lDefend your bed!")
            addDefault(Messages.BED_HOLOGRAM_DESTROYED, "&c&lYour bed was destroyed!")
            addDefault(Messages.NPC_NAME_TEAM_UPGRADES, "&bTEAM UPGRADES,&e&lRIGHT CLICK")
            addDefault(Messages.NPC_NAME_SOLO_UPGRADES, "&bSOLO UPGRADES,&e&lRIGHT CLICK")
            addDefault(Messages.NPC_NAME_TEAM_SHOP, "&bTEAM SHOP,&e&lRIGHT CLICK")
            addDefault(Messages.NPC_NAME_SOLO_SHOP, "&bITEM SHOP,&e&lRIGHT CLICK")
            addDefault(
                Messages.TEAM_ELIMINATED_CHAT,
                "\n&f&lTEAM ELIMINATED > {TeamColor}{TeamName} Team &chas been eliminated!\n"
            )
            addDefault(Messages.NEXT_EVENT_BEDS_DESTROY, "&cBed Destruction")
            addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_II, "&fDiamond II")
            addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_III, "&fDiamond III")
            addDefault(Messages.NEXT_EVENT_DRAGON_SPAWN, "&fSudden Death")
            addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_II, "&fEmerald II")
            addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_III, "&fEmerald III")
            addDefault(Messages.NEXT_EVENT_GAME_END, "&4Game End")
            addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED, "&cBED DESTROYED!")
            addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED, "&fAll beds have been destroyed!")
            addDefault(Messages.NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED, "&c&lAll beds have been destroyed!")
            addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH, "&cSudden Death")
            addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH, "")
            addDefault(
                Messages.NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH,
                "&cSUDDEN DEATH: &6&b{TeamDragons} {TeamColor}{TeamName} Dragon!"
            )
            addDefault(Messages.XP_REWARD_PER_MINUTE, "{prefix}&6+{xp} BedWars Experience Received (Play Time).")
            addDefault(Messages.XP_REWARD_WIN, "{prefix}&6+{xp} BedWars Experience Received (Game Win).")
            addDefault(
                Messages.XP_REWARD_PER_TEAMMATE,
                "{prefix}&6+{xp} BedWars Experience Received (Team Support)."
            )
            addDefault(
                Messages.XP_REWARD_BED_DESTROY,
                "{prefix}&6+{xp} BedWars Experience Received (Bed Destroyed)."
            )
            addDefault(
                Messages.XP_REWARD_REGULAR_KILL,
                "{prefix}&6+{xp} BedWars Experience Received (Regular Kill)."
            )
            addDefault(Messages.XP_REWARD_FINAL_KILL, "{prefix}&6+{xp} BedWars Experience Received (Final Kill).")

            addDefault(Messages.MONEY_REWARD_PER_MINUTE, "{prefix}&6+{money} Coins (Play Time).")
            addDefault(Messages.MONEY_REWARD_WIN, "{prefix}&6+{money} Coins (Game Win).")
            addDefault(Messages.MONEY_REWARD_PER_TEAMMATE, "{prefix}&6+{money} Coins (Team Support).")
            addDefault(Messages.MONEY_REWARD_BED_DESTROYED, "{prefix}&6+{money} Coins (Bed Destroyed).")
            addDefault(Messages.MONEY_REWARD_FINAL_KILL, "{prefix}&6+{money} Coins (Final Kill).")
            addDefault(Messages.MONEY_REWARD_REGULAR_KILL, "{prefix}&6+{money} Coins (Regular Kill).")

            /* Lobby Command Items */
            addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "stats"), "&eStats")
            addDefault(
                Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "stats"),
                listOf("&fRight-click to see your stats!")
            )
            addDefault(
                Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "arena-selector"),
                "&eArena Selector"
            )
            addDefault(
                Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "arena-selector"),
                listOf("&fRight-click to choose an arena!")
            )
            addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "leave"), "&eBack to Hub")
            addDefault(
                Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "leave"),
                listOf("&fRight-click to leave BedWars!")
            )
            /* Pre Game Command Items */
            addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "stats"), "&eStats")
            addDefault(
                Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "stats"),
                listOf("&fRight-click to see your stats!")
            )
            addDefault(
                Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "leave"),
                "&eBack to Lobby"
            )
            addDefault(
                Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "leave"),
                listOf("&fRight-click to leave the arena!")
            )
            /* Spectator Command Items */
            addDefault(
                Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "teleporter"),
                "&eTeleporter"
            )
            addDefault(
                Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "leave"),
                "&eBack to Lobby"
            )
            addDefault(
                Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "leave"),
                listOf("&fRight-click to leave the arena!")
            )

            /* save default items messages for stats gui */
            addDefault(Messages.PLAYER_STATS_GUI_INV_NAME, "&8{player} Stats")
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
            addDefault(
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

            addDefault(
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
            addDefault(
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
            addDefault(
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
                Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED, listOf(
                    "&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&lBED WARS,&f&l{poweredBy},&f&l{poweredBy},&f&l{poweredBy},&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&lBED WARS,&e&l{poweredBy},&e&l{poweredBy},&e&l{poweredBy}",
                    "&7{date}",
                    "&o&7Spectating {spectatorTarget}",
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
                Messages.SCOREBOARD_DEFAULT_PLAYING.replaceFirst("Default".toRegex(), "Doubles"),
                listOf(
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
                Messages.SCOREBOARD_DEFAULT_PLAYING.replaceFirst("Default".toRegex(), "3v3v3v3"),
                listOf(
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
                    "&fKills: &a{kills}",
                    "&fFinal Kills: &a{finalKills}",
                    "&fBeds Broken: &a{beds}",
                    "",
                    "&e{serverIp}"
                )
            )

            addDefault(
                Messages.SCOREBOARD_DEFAULT_PLAYING.replaceFirst("Default".toRegex(), "4v4v4v4"),
                listOf(
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
                    "&fFinal Kills: &a{finalKills}",
                    "&fBeds Broken: &a{beds}",
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
                    "",
                    "&7&oYou are spectating"
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
                    "",
                    "&7&oYou are spectating"
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
            addDefault(
                Messages.FORMATTING_SB_TAB_PLAYING_SPEC_SUFFIX,
                listOf(" {vPrefix}", " {level}")
            )
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
            addDefault(Messages.SHOP_INDEX_NAME, "&8Quick Buy")
            addDefault(Messages.SHOP_QUICK_ADD_NAME, "&8Adding to Quick Buy...")
            addDefault(
                Messages.SHOP_INSUFFICIENT_MONEY,
                "{prefix}&cYou don't have enough {currency}! Need {amount} more!"
            )
            addDefault(Messages.SHOP_NEW_PURCHASE, "{prefix}&aYou purchased &6{item}")
            addDefault(Messages.SHOP_ALREADY_BOUGHT, "{prefix}&cYou've already bought that!")
            addDefault(Messages.SHOP_UTILITY_NPC_SILVERFISH_NAME, "{TeamColor}&l{TeamName} &r{TeamColor}Silverfish")
            addDefault(
                Messages.SHOP_UTILITY_NPC_IRON_GOLEM_NAME,
                "{TeamColor}{despawn}s &8[ {TeamColor}{health}&8]"
            )
            addDefault(Messages.SHOP_SEPARATOR_NAME, "&8⇧ Categories")
            addDefault(Messages.SHOP_SEPARATOR_LORE, listOf("&8⇩ Items"))
            addDefault(Messages.SHOP_QUICK_BUY_NAME, "&bQuick Buy")
            addDefault(Messages.SHOP_QUICK_BUY_LORE, emptyList<String>())
            addDefault(Messages.SHOP_QUICK_EMPTY_NAME, "&cEmpty slot!")
            addDefault(
                Messages.SHOP_QUICK_EMPTY_LORE,
                listOf(
                    "&7This is a Quick Buy Slot!",
                    "&bSneak Click &7any item in",
                    "&7the shop to add it here."
                )
            )
            addDefault(Messages.SHOP_CAN_BUY_COLOR, "&a")
            addDefault(Messages.SHOP_CANT_BUY_COLOR, "&c")
            addDefault(Messages.SHOP_LORE_STATUS_CAN_BUY, "&eClick to purchase!")
            addDefault(Messages.SHOP_LORE_STATUS_CANT_AFFORD, "&cYou don't have enough {currency}!")
            addDefault(Messages.SHOP_LORE_STATUS_MAXED, "&aMAXED!")
            addDefault(Messages.SHOP_LORE_STATUS_ARMOR, "&aEQUIPPED!")
            addDefault(Messages.SHOP_LORE_QUICK_ADD, "&bSneak Click to add to Quick Buy")
            addDefault(Messages.SHOP_LORE_QUICK_REMOVE, "&bSneak Click to remove from Quick Buy!")


            addCategoryMessages(
                this,
                ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
                "&8Blocks",
                "&aBlocks",
                listOf("&eClick to view!")
            )

            addContentMessages(
                this, "wool", ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "{color}Wool", listOf(
                    "&7Cost: &f{cost} {currency}",
                    "",
                    "&7Great for bridging across",
                    "&7islands. Turns into your team's",
                    "&7color.",
                    "",
                    "{quick_buy}",
                    "{buy_status}"
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
                    "&7Cost: {cost} {currency}",
                    "",
                    "&7Spawns silverfish where the",
                    "&7snowball lands to distract your",
                    "&7enemies. Lasts 15 seconds.",
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
                this,
                "water-bucket",
                ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
                "{color}Water Bucket",
                listOf(
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
            addContentMessages(
                this,
                "Compact Pop-up Tower",
                ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
                "{color}Compact Pop-up Tower",
                listOf(
                    "&7Cost: {cost} {currency}",
                    "",
                    "&7Place a compact pop-up",
                    "&7tower defense!",
                    "",
                    "{quick_buy}",
                    "{buy_status}"
                )
            )

            //
            addDefault(Messages.MEANING_NO_TRAP, "No trap!")
            addDefault(Messages.FORMAT_SPECTATOR_TARGET, "{targetTeamColor}{targetDisplayName}")
            addDefault(Messages.FORMAT_UPGRADE_TRAP_COST, "&7Cost: {currencyColor}{cost} {currency}")
            addDefault(Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD, "&e")
            addDefault(Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD, "&c")
            addDefault(Messages.FORMAT_UPGRADE_COLOR_UNLOCKED, "&a")
            addDefault(Messages.FORMAT_UPGRADE_TIER_LOCKED, "&7")
            addDefault(Messages.FORMAT_UPGRADE_TIER_UNLOCKED, "&a")
            addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "{color}Click to purchase!")
            addDefault(
                Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY,
                "{color}You don't have enough {currency}"
            )
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
            addDefault(
                Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "glass",
                listOf("&8⬇&7Traps Queue")
            )
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
            addDefault(Messages.UPGRADES_TRAP_DEFAULT_MSG, "&c&l{trap} was set off!")
            addDefault(Messages.UPGRADES_TRAP_DEFAULT_TITLE, "&cTRAP TRIGGERED!")
            addDefault(Messages.UPGRADES_TRAP_DEFAULT_SUBTITLE, "&fYour {trap} has been triggered!")
            addDefault(
                Messages.UPGRADES_TRAP_CUSTOM_MSG + "3",
                "&c&lAlarm trap set off by &7&l{player} &c&lfrom {color}&l{team} &c&lteam!"
            )
            addDefault(Messages.UPGRADES_TRAP_CUSTOM_TITLE + "3", "&c&lALARM!!!")
            addDefault(
                Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + "3",
                "&fAlarm trap set off by {color}{team} &fteam!"
            )
        }
        save()
        setPrefix(m(Messages.PREFIX))
    }
}
