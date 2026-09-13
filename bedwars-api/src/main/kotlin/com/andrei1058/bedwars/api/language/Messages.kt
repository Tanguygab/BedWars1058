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
package com.andrei1058.bedwars.api.language

import com.andrei1058.bedwars.api.configuration.ConfigPath

object Messages {
    const val PREFIX = "prefix"

    /**
     * next event related
     */
    const val NEXT_EVENT_DIAMOND_UPGRADE_II = "next-event-diamondII"
    const val NEXT_EVENT_DIAMOND_UPGRADE_III = "next-event-diamondIII"
    const val NEXT_EVENT_EMERALD_UPGRADE_II = "next-event-emeraldII"
    const val NEXT_EVENT_EMERALD_UPGRADE_III = "next-event-emeraldIII"
    const val NEXT_EVENT_BEDS_DESTROY = "next-event-beds-destroy"
    const val NEXT_EVENT_DRAGON_SPAWN = "next-event-dragon-spawn"
    const val NEXT_EVENT_GAME_END = "next-event-game-end"
    const val NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED = "next-event-beds-destroy-title"
    const val NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED = "next-event-beds-destroy-sub-title"
    const val NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED = "next-event-beds-destroy-chat"
    const val NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH = "next-event-sudden-death-title"
    const val NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH = "next-event-sudden-death-sub-title"
    const val NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH = "next-event-sudden-death-chat"

    /**
     * General commands reply
     */
    const val COMMAND_MAIN = "cmd-main-list"
    const val COMMAND_LANG_LIST_HEADER = "cmd-lang-list-header"
    const val COMMAND_LANG_LIST_FORMAT = "cmd-lang-list-format"
    const val COMMAND_LANG_USAGE = "cmd-lang-usage"
    const val COMMAND_LANG_SELECTED_NOT_EXIST = "cmd-lang-not-exist"
    const val COMMAND_LANG_SELECTED_SUCCESSFULLY = "cmd-lang-set"
    const val COMMAND_LANG_USAGE_DENIED = "cmd-lang-not-set"
    const val COMMAND_JOIN_USAGE = "cmd-join-usage"
    const val COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND = "cmd-join-not-found"
    const val COMMAND_JOIN_DENIED_IS_FULL = "cmd-join-arena-full"
    const val COMMAND_JOIN_NO_EMPTY_FOUND = "cmd-join-arenas-full"
    const val COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS = "cmd-join-arena-full-vips"
    const val COMMAND_JOIN_DENIED_PARTY_TOO_BIG = "cmd-join-party-big"
    const val COMMAND_JOIN_DENIED_NOT_PARTY_LEADER = "cmd-join-not-leader"
    const val COMMAND_JOIN_PLAYER_JOIN_MSG = "cmd-join-success"
    const val COMMAND_JOIN_SPECTATOR_MSG = "cmd-join-spectate"
    const val COMMAND_JOIN_SPECTATOR_DENIED_MSG = "cmd-join-spectate-denied"
    const val COMMAND_TP_PLAYER_NOT_FOUND = "cmd-tp-player-not-found"
    const val COMMAND_TP_NOT_IN_ARENA = "cmd-tp-not-in-arena"
    const val COMMAND_TP_NOT_STARTED = "cmd-tp-not-started"
    const val COMMAND_TP_USAGE = "cmd-tp-usage"
    const val REJOIN_NO_ARENA = "cmd-rejoin-no-arena"
    const val REJOIN_DENIED = "cmd-rejoin-denied"
    const val REJOIN_ALLOWED = "cmd-rejoin-allowed"
    const val COMMAND_REJOIN_PLAYER_RECONNECTED = "cmd-rejoin-player-reconnected"
    const val COMMAND_LEAVE_MSG = "cmd-leave"
    const val COMMAND_NOT_ALLOWED_IN_GAME = "cmd-blocked-in-game"
    const val COMMAND_LEAVE_DENIED_NOT_IN_ARENA = "cmd-not-in-arena"
    const val COMMAND_PARTY_HELP = "cmd-party-help"
    const val COMMAND_PARTY_INVITE_USAGE = "cmd-party-invite-usage"
    const val COMMAND_PARTY_INVITE_SENT = "cmd-party-invite"
    const val COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE = "cmd-party-offline"
    const val COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG = "cmd-party-invite-received"
    const val COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF = "cmd-party-invite-self"
    const val COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE = "cmd-party-no-invite"
    const val COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY = "cmd-party-already-in"
    const val COMMAND_PARTY_INSUFFICIENT_PERMISSIONS = "cmd-party-no-perm"
    const val COMMAND_PARTY_ACCEPT_USAGE = "cmd-party-accept-usage"
    const val COMMAND_PARTY_ACCEPT_SUCCESS = "cmd-party-join"
    const val COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY = "cmd-party-not-in"
    const val COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND = "cmd-party-cant-leave"
    const val COMMAND_PARTY_LEAVE_SUCCESS = "cmd-party-leave"
    const val COMMAND_PARTY_DISBAND_SUCCESS = "cmd-party-disband"
    const val COMMAND_PARTY_REMOVE_USAGE = "cmd-party-remove-usage"
    const val COMMAND_PARTY_REMOVE_SUCCESS = "cmd-party-remove"
    const val COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER = "cmd-party-remove-not-in"
    const val COMMAND_PARTY_PROMOTE_SUCCESS = "cmd-party-promote-owner"
    const val COMMAND_PARTY_PROMOTE_OWNER = "cmd-party-owner"
    const val COMMAND_PARTY_PROMOTE_NEW_OWNER = "cmd-party-owner-changed"
    const val COMMAND_PARTY_INFO_OWNER = "cmd-party-info-owner"
    const val COMMAND_PARTY_INFO_PLAYERS = "cmd-party-info-players"
    const val COMMAND_PARTY_INFO_PLAYER = "cmd-party-info-player"
    const val COMMAND_NOT_FOUND_OR_INSUFF_PERMS = "cmd-not-found"
    const val COMMAND_FORCESTART_NOT_IN_GAME = "cmd-start-no-game"
    const val COMMAND_FORCESTART_SUCCESS = "cmd-start"
    const val COMMAND_FORCESTART_NO_PERM = "cmd-start-no-perm"
    const val COMMAND_COOLDOWN = "cmd-cooldown"

    /**
     * Arena join/ leave related
     */
    const val ARENA_JOIN_VIP_KICK = "arena-kicked-by-vip"
    const val ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT = "arena-countdown-stopped"

    //public static String ARENA_PLAYER_QUIT = "player.quit";
    const val ARENA_RESTART_PLAYER_KICK = "arena-restart-kick"
    const val ARENA_JOIN_DENIED_SELECTOR = "arena-join-denied-selector"
    const val ARENA_JOIN_DENIED_NO_PROXY = "arena-join-denied-no-bwp"
    const val ARENA_SPECTATE_DENIED_SELECTOR = "arena-spectate-denied-selector"
    const val ARENA_LEAVE_PARTY_DISBANDED = "arena-leave-party-disbanded"

    /**
     * Arena status/ status change related
     */
    const val ARENA_STATUS_WAITING_NAME = "arena-status-waiting"
    const val ARENA_STATUS_STARTING_NAME = "arena-status-starting"
    const val ARENA_STATUS_PLAYING_NAME = "arena-status-playing"
    const val ARENA_STATUS_RESTARTING_NAME = "arena-status-restarting"
    const val ARENA_STATUS_START_PLAYER_TITLE = "arena-start-title"
    const val ARENA_STATUS_START_PLAYER_TUTORIAL = "arena-start-tutorial"
    const val ARENA_STATUS_START_COUNTDOWN_CHAT = "arena-start-countdown"
    const val ARENA_STATUS_START_COUNTDOWN_TITLE = "arena-start-countdown-title"
    const val ARENA_STATUS_START_COUNTDOWN_SUB_TITLE = "arena-start-countdown-subtitle"
    const val ARENA_STATUS_START_COUNTDOWN_CANCELLED_TITLE = "arena-countdown-stopped-subtitle"
    const val ARENA_STATUS_START_COUNTDOWN_CANCELLED_SUB_TITLE = "arena-countdown-stopped-subtitle"
    const val ARENA_DISPLAY_GROUP_PATH = "display-group-"

    /**
     * Arena GUI related
     */
    const val ARENA_GUI_INV_NAME = "arena-selector-gui-name"
    const val ARENA_GUI_ARENA_CONTENT_NAME = "arena-selector-content-name"
    const val ARENA_GUI_ARENA_CONTENT_LORE = "arena-selector-content-lore"
    const val ARENA_GUI_SKIPPED_ITEM_NAME = "arena-selector-skipped-item-name"
    const val ARENA_GUI_SKIPPED_ITEM_LORE = "arena-selector-skipped-item-lore"

    /**
     * Spectator related
     */
    const val ARENA_SPECTATOR_TELEPORTER_GUI_NAME = "spectator-tp-gui-name"

    //{player} - returns display name, {prefix} - returns the player rank
    const val ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME = "spectator-tp-gui-head-name"

    //{health}, {food}
    const val ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE = "spectator-tp-gui-head-lore"

    const val ARENA_SPECTATOR_LEAVE_ITEM_NAME = "spectator-tp-name"
    const val ARENA_SPECTATOR_LEAVE_ITEM_LORE = "spectator-tp-lore"

    const val ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE = "spectator-first-person-enter-title"
    const val ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE = "spectator-first-person-enter-subtitle"
    const val ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE = "spectator-first-person-quit-title"
    const val ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE = "spectator-first-person-quit-subtitle"

    /**
     * Stats related
     */
    const val PLAYER_STATS_GUI_PATH = "stats"
    const val PLAYER_STATS_GUI_INV_NAME = "$PLAYER_STATS_GUI_PATH-inv-name"

    /**
     * Arena generators related
     */
    const val GENERATOR_HOLOGRAM_TIER = "generator-tier"
    const val GENERATOR_HOLOGRAM_TYPE_DIAMOND = "generator-diamond"
    const val GENERATOR_HOLOGRAM_TYPE_EMERALD = "generator-emerald"
    const val GENERATOR_HOLOGRAM_TIMER = "generator-timer"
    const val GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT = "generator-upgrade-announce"

    /**
     * General formatting
     */
    const val FORMATTING_CHAT_LOBBY = "format-chat-lobby"
    const val FORMATTING_CHAT_WAITING = "format-chat-waiting"
    const val FORMATTING_CHAT_SHOUT = "format-chat-global"
    const val FORMATTING_CHAT_TEAM = "format-chat-team"
    const val FORMATTING_CHAT_SPECTATOR = "format-chat-spectator"
    const val FORMATTING_SCOREBOARD_DATE = "format-sb-date"
    const val FORMATTING_SCOREBOARD_TEAM_GENERIC = "format-sb-team-generic"
    const val FORMATTING_SCOREBOARD_HEALTH = "format-sb-health"

    // TAB
    const val FORMATTING_SB_TAB_BASE = "format-tab"

    // TAB LOBBY
    const val FORMATTING_SB_TAB_LOBBY_HEADER = "$FORMATTING_SB_TAB_BASE.lobby.header"
    const val FORMATTING_SB_TAB_LOBBY_FOOTER = "$FORMATTING_SB_TAB_BASE.lobby.footer"
    const val FORMATTING_SB_TAB_LOBBY_PREFIX = "$FORMATTING_SB_TAB_BASE.lobby.prefix"
    const val FORMATTING_SB_TAB_LOBBY_SUFFIX = "$FORMATTING_SB_TAB_BASE.lobby.suffix"

    // TAB WAITING
    const val FORMATTING_SB_TAB_WAITING_HEADER = "$FORMATTING_SB_TAB_BASE.waiting.player.header"
    const val FORMATTING_SB_TAB_WAITING_FOOTER = "$FORMATTING_SB_TAB_BASE.waiting.player.footer"
    const val FORMATTING_SB_TAB_WAITING_PREFIX = "$FORMATTING_SB_TAB_BASE.waiting.player.prefix"
    const val FORMATTING_SB_TAB_WAITING_SUFFIX = "$FORMATTING_SB_TAB_BASE.waiting.player.suffix"

    // TAB WAITING FOR SPECTATORS
    const val FORMATTING_SB_TAB_WAITING_HEADER_SPEC = "$FORMATTING_SB_TAB_BASE.waiting.spectator.header"
    const val FORMATTING_SB_TAB_WAITING_FOOTER_SPEC = "$FORMATTING_SB_TAB_BASE.waiting.spectator.footer"
    const val FORMATTING_SB_TAB_WAITING_PREFIX_SPEC = "$FORMATTING_SB_TAB_BASE.waiting.spectator.prefix"
    const val FORMATTING_SB_TAB_WAITING_SUFFIX_SPEC = "$FORMATTING_SB_TAB_BASE.waiting.spectator.suffix"

    // TAB STARTING
    const val FORMATTING_SB_TAB_STARTING_HEADER = "$FORMATTING_SB_TAB_BASE.starting.player.header"
    const val FORMATTING_SB_TAB_STARTING_FOOTER = "$FORMATTING_SB_TAB_BASE.starting.player.footer"
    const val FORMATTING_SB_TAB_STARTING_PREFIX = "$FORMATTING_SB_TAB_BASE.starting.player.prefix"
    const val FORMATTING_SB_TAB_STARTING_SUFFIX = "$FORMATTING_SB_TAB_BASE.starting.player.suffix"

    // TAB STARTING FOR SPECTATORS
    const val FORMATTING_SB_TAB_STARTING_HEADER_SPEC = "$FORMATTING_SB_TAB_BASE.starting.spectator.header"
    const val FORMATTING_SB_TAB_STARTING_FOOTER_SPEC = "$FORMATTING_SB_TAB_BASE.starting.spectator.footer"
    const val FORMATTING_SB_TAB_STARTING_PREFIX_SPEC = "$FORMATTING_SB_TAB_BASE.starting.player.prefix"
    const val FORMATTING_SB_TAB_STARTING_SUFFIX_SPEC = "$FORMATTING_SB_TAB_BASE.starting.player.suffix"

    // TAB PLAYING
    const val FORMATTING_SB_TAB_PLAYING_HEADER = "$FORMATTING_SB_TAB_BASE.playing.alive.header"
    const val FORMATTING_SB_TAB_PLAYING_FOOTER = "$FORMATTING_SB_TAB_BASE.playing.alive.footer"
    const val FORMATTING_SB_TAB_PLAYING_PREFIX = "$FORMATTING_SB_TAB_BASE.playing.alive.prefix"
    const val FORMATTING_SB_TAB_PLAYING_SUFFIX = "$FORMATTING_SB_TAB_BASE.playing.alive.suffix"

    // TAB PLAYING-ELIMINATED
    const val FORMATTING_SB_TAB_PLAYING_ELM_HEADER = "$FORMATTING_SB_TAB_BASE.playing.eliminated.header"
    const val FORMATTING_SB_TAB_PLAYING_ELM_FOOTER = "$FORMATTING_SB_TAB_BASE.playing.eliminated.footer"
    const val FORMATTING_SB_TAB_PLAYING_ELM_PREFIX = "$FORMATTING_SB_TAB_BASE.playing.eliminated.prefix"
    const val FORMATTING_SB_TAB_PLAYING_ELM_SUFFIX = "$FORMATTING_SB_TAB_BASE.playing.eliminated.suffix"

    // TAB PLAYING FOR SPECTATORS
    const val FORMATTING_SB_TAB_PLAYING_SPEC_HEADER = "$FORMATTING_SB_TAB_BASE.playing.spectator.header"
    const val FORMATTING_SB_TAB_PLAYING_SPEC_FOOTER = "$FORMATTING_SB_TAB_BASE.playing.spectator.footer"
    const val FORMATTING_SB_TAB_PLAYING_SPEC_PREFIX = "$FORMATTING_SB_TAB_BASE.playing.spectator.prefix"
    const val FORMATTING_SB_TAB_PLAYING_SPEC_SUFFIX = "$FORMATTING_SB_TAB_BASE.playing.spectator.suffix"

    // TAB RESTARTING FOR WINNERS ALIVE
    const val FORMATTING_SB_TAB_RESTARTING_WIN1_HEADER = "$FORMATTING_SB_TAB_BASE.restarting.winner-alive.header"
    const val FORMATTING_SB_TAB_RESTARTING_WIN1_FOOTER = "$FORMATTING_SB_TAB_BASE.restarting.winner-alive.footer"
    const val FORMATTING_SB_TAB_RESTARTING_WIN1_PREFIX = "$FORMATTING_SB_TAB_BASE.restarting.winner-alive.prefix"
    const val FORMATTING_SB_TAB_RESTARTING_WIN1_SUFFIX = "$FORMATTING_SB_TAB_BASE.restarting.winner-alive.suffix"

    // TAB RESTARTING FOR WINNERS DEAD
    const val FORMATTING_SB_TAB_RESTARTING_WIN2_HEADER = "$FORMATTING_SB_TAB_BASE.restarting.winner-dead.header"
    const val FORMATTING_SB_TAB_RESTARTING_WIN2_FOOTER = "$FORMATTING_SB_TAB_BASE.restarting.winner-dead.footer"
    const val FORMATTING_SB_TAB_RESTARTING_WIN2_PREFIX = "$FORMATTING_SB_TAB_BASE.restarting.winner-dead.prefix"
    const val FORMATTING_SB_TAB_RESTARTING_WIN2_SUFFIX = "$FORMATTING_SB_TAB_BASE.restarting.winner-dead.suffix"


    // TAB RESTARTING FOR LOSERS
    const val FORMATTING_SB_TAB_RESTARTING_ELM_HEADER = "$FORMATTING_SB_TAB_BASE.restarting.loser.header"
    const val FORMATTING_SB_TAB_RESTARTING_ELM_FOOTER = "$FORMATTING_SB_TAB_BASE.restarting.loser.footer"
    const val FORMATTING_SB_TAB_RESTARTING_ELM_PREFIX = "$FORMATTING_SB_TAB_BASE.restarting.loser.prefix"
    const val FORMATTING_SB_TAB_RESTARTING_ELM_SUFFIX = "$FORMATTING_SB_TAB_BASE.restarting.loser.suffix"

    // TAB RESTARTING FOR SPECTATORS
    const val FORMATTING_SB_TAB_RESTARTING_SPEC_HEADER = "$FORMATTING_SB_TAB_BASE.restarting.spectator.header"
    const val FORMATTING_SB_TAB_RESTARTING_SPEC_FOOTER = "$FORMATTING_SB_TAB_BASE.restarting.spectator.footer"
    const val FORMATTING_SB_TAB_RESTARTING_SPEC_PREFIX = "$FORMATTING_SB_TAB_BASE.restarting.spectator.prefix"
    const val FORMATTING_SB_TAB_RESTARTING_SPEC_SUFFIX = "$FORMATTING_SB_TAB_BASE.restarting.spectator.suffix"

    const val FORMATTING_SCOREBOARD_TEAM_ELIMINATED = "format-sb-team-eliminated"
    const val FORMATTING_SCOREBOARD_BED_DESTROYED = "format-sb-bed-destroyed"
    const val FORMATTING_SCOREBOARD_TEAM_ALIVE = "format-sb-team-alive"
    const val FORMATTING_SCOREBOARD_NEXEVENT_TIMER = "format-sb-generator-timer"
    const val FORMATTING_SCOREBOARD_YOUR_TEAM = "format-sb-you"
    const val FORMATTING_ACTION_BAR_TRACKING = "format-action-tracking"
    const val FORMATTING_TEAM_WINNER_FORMAT = "format-winner-team"
    const val FORMATTING_SOLO_WINNER_FORMAT = "format-winner-solo"

    //public static String FORMATTING_TAB_LIST = "format.tablist";
    const val FORMATTING_GENERATOR_TIER1 = "format-tier1"
    const val FORMATTING_GENERATOR_TIER2 = "format-tier2"
    const val FORMATTING_GENERATOR_TIER3 = "format-tier3"
    const val FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH = "format-despawnable-health"
    const val FORMATTING_STATS_DATE_FORMAT = "format-stats-time"
    const val FORMAT_PAPI_PLAYER_TEAM_TEAM = "format-papi-player-team"
    const val FORMAT_PAPI_PLAYER_TEAM_SHOUT = "format-papi-player-shout"
    const val FORMAT_PAPI_PLAYER_TEAM_SPECTATOR = "format-papi-player-spectator"
    const val FORMAT_UPGRADE_TIER_LOCKED = "format-tier-color-locked"
    const val FORMAT_UPGRADE_TIER_UNLOCKED = "format-tier-color-unlocked"
    const val FORMAT_UPGRADE_COLOR_CANT_AFFORD = "format-upgrade-color-cant-afford"
    const val FORMAT_UPGRADE_COLOR_CAN_AFFORD = "format-upgrade-color-can-afford"
    const val FORMAT_UPGRADE_COLOR_UNLOCKED = "format-upgrade-color-unlocked"
    const val FORMAT_UPGRADE_TRAP_COST = "format-upgrade-trap-cost"
    const val FORMAT_SPECTATOR_TARGET = "format-spectator-target"

    /**
     * Meaning/ Translations
     */
    const val MEANING_FULL = "meaning-full"
    const val MEANING_SHOUT = "meaning-shout"
    const val MEANING_NEVER = "meaning-never"
    const val MEANING_NOBODY = "meaning-nobody"
    const val MEANING_IRON_SINGULAR = "meaning-iron-singular"
    const val MEANING_IRON_PLURAL = "meaning-iron-plural"
    const val MEANING_GOLD_SINGULAR = "meaning-gold-singular"
    const val MEANING_GOLD_PLURAL = "meaning-gold-plural"
    const val MEANING_EMERALD_SINGULAR = "meaning-emerald-singular"
    const val MEANING_EMERALD_PLURAL = "meaning-emerald-plural"
    const val MEANING_DIAMOND_SINGULAR = "meaning-diamond-singular"
    const val MEANING_DIAMOND_PLURAL = "meaning-diamond-plural"
    const val MEANING_VAULT_SINGULAR = "meaning-vault-singular"
    const val MEANING_VAULT_PLURAL = "meaning-vault-plural"
    const val MEANING_NO_TRAP = "meaning-no-trap"

    /**
     * Scoreboard related
     */
    const val SCOREBOARD_LOBBY = "sidebar.lobby"
    const val SCOREBOARD_DEFAULT_WAITING = "sidebar.Default.waiting.player"
    const val SCOREBOARD_DEFAULT_WAITING_SPEC = "sidebar.Default.waiting.spectator"
    const val SCOREBOARD_DEFAULT_STARTING = "sidebar.Default.starting.player"
    const val SCOREBOARD_DEFAULT_STARTING_SPEC = "sidebar.Default.starting.spectator"
    const val SCOREBOARD_DEFAULT_PLAYING = "sidebar.Default.playing.alive"
    const val SCOREBOARD_DEFAULT_PLAYING_SPEC = "sidebar.Default.playing.spectator"
    const val SCOREBOARD_DEFAULT_PLAYING_SPEC_ELIMINATED = "sidebar.Default.playing.eliminated"

    const val SCOREBOARD_DEFAULT_RESTARTING_SPEC = "sidebar.Default.restarting.spectator"
    const val SCOREBOARD_DEFAULT_RESTARTING_WIN1 = "sidebar.Default.restarting.winner-alive"
    const val SCOREBOARD_DEFAULT_RESTARTING_WIN2 = "sidebar.Default.restarting.winner-eliminated"
    const val SCOREBOARD_DEFAULT_RESTARTING_LOSER = "sidebar.Default.restarting.loser"

    /**
     * Player interact related
     */
    const val INTERACT_CANNOT_PLACE_BLOCK = "interact-cant-place"
    const val INTERACT_CANNOT_BREAK_BLOCK = "interact-cant-break"
    const val INTERACT_CANNOT_BREAK_OWN_BED = "interact-cant-destroy-bed"
    const val INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT = "interact-bed-destroy-chat"
    const val INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT = "interact-bed-destroy-title"
    const val INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT = "interact-bed-destroy-subtitle"
    const val INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM = "interact-bed-destroy-team"
    const val INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED = "interact-cant-open-chest"
    const val INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN = "interact-invisibility-removed-damaged"

    /**
     * PvP related
     */
    const val PLAYER_DIE_RESPAWN_TITLE = "player-respawn-title"
    const val PLAYER_DIE_RESPAWN_SUBTITLE = "player-respawn-subtitle"
    const val PLAYER_DIE_RESPAWN_CHAT = "player-respawn-timer-chat"
    const val PLAYER_DIE_RESPAWNED_TITLE = "player-respawned-title"
    const val PLAYER_DIE_ELIMINATED_CHAT = "player-eliminated-chat"

    const val PLAYER_DIE_VOID_FALL_REGULAR_KILL = "player-die-void-regular"
    const val PLAYER_DIE_VOID_FALL_FINAL_KILL = "player-die-void-final"
    const val PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL = "player-die-knocked-void-regular"
    const val PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL = "player-die-knocked-void-final"
    const val PLAYER_DIE_KNOCKED_BY_REGULAR_KILL = "player-die-knocked-fall-regular"
    const val PLAYER_DIE_KNOCKED_BY_FINAL_KILL = "player-die-knocked-fall-final"
    const val PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL = "player-die-bomb-regular"
    const val PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL = "player-die-bomb-final"
    const val PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR = "player-die-bomb2-regular"
    const val PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL = "player-die-bomb2-final"
    const val PLAYER_DIE_PVP_REGULAR_KILL = "player-die-attack-regular"
    const val PLAYER_DIE_PVP_FINAL_KILL = "player-die-attack-final"
    const val PLAYER_DIE_UNKNOWN_REASON_REGULAR = "player-die-unknown-regular"
    const val PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL = "player-die-unknown-final"
    const val PLAYER_DIE_SHOOT_REGULAR = "player-die-shoot-regular"
    const val PLAYER_DIE_SHOOT_FINAL_KILL = "player-die-shoot-final"
    const val PLAYER_DIE_DEBUG_REGULAR = "player-die-bedbug-regular"
    const val PLAYER_DIE_DEBUG_FINAL_KILL = "player-die-bedbug-final"
    const val PLAYER_DIE_IRON_GOLEM_REGULAR = "player-die-golem-regular"
    const val PLAYER_DIE_IRON_GOLEM_FINAL_KILL = "player-die-golem-final"
    const val PLAYER_DIE_PVP_LOG_OUT_REGULAR = "player-die-pvp-log-out-regular"
    const val PLAYER_DIE_PVP_LOG_OUT_FINAL = "player-die-pvp-log-out-final"

    const val PLAYER_DIE_REWARD_DIAMOND = "player-loot-diamond"
    const val PLAYER_DIE_REWARD_IRON = "player-loot-iron"
    const val PLAYER_DIE_REWARD_GOLD = "player-loot-gold"
    const val PLAYER_DIE_REWARD_EMERALD = "player-loot-emerald"

    const val PLAYER_HIT_BOW = "player-hit-bow"

    /**
     * Misc
     */
    const val GAME_END_GAME_OVER_PLAYER_TITLE = "game-end-game-over-title"
    const val GAME_END_VICTORY_PLAYER_TITLE = "game-end-victory-title"
    const val GAME_END_TOP_PLAYER_CHAT = "game-end-top-chat"
    const val GAME_END_TEAM_WON_CHAT = "game-end-winner-team"
    const val XP_REWARD_WIN = "xp-reward-game-win"
    const val XP_REWARD_PER_TEAMMATE = "xp-reward-per-teammate"
    const val XP_REWARD_PER_MINUTE = "xp-reward-per-minute"
    const val XP_REWARD_BED_DESTROY = "xp-reward-bed-destroyed"
    const val XP_REWARD_REGULAR_KILL = "xp-reward-regular-kill"
    const val XP_REWARD_FINAL_KILL = "xp-reward-final-kill"

    const val MONEY_REWARD_WIN = "money-reward-game-win"
    const val MONEY_REWARD_PER_MINUTE = "money-reward-per-minute"
    const val MONEY_REWARD_PER_TEAMMATE = "money-reward-per-teammate"
    const val MONEY_REWARD_BED_DESTROYED = "money-reward-bed-destroyed"
    const val MONEY_REWARD_FINAL_KILL = "money-reward-final-kill"
    const val MONEY_REWARD_REGULAR_KILL = "money-reward-regular-kill"

    const val BED_HOLOGRAM_DEFEND = "bed-hologram-defend"
    const val BED_HOLOGRAM_DESTROYED = "bed-hologram-destroyed"
    const val TEAM_ELIMINATED_CHAT = "team-eliminated"


    /**
     * Upgrades/ Shop
     */
    const val NPC_NAME_TEAM_UPGRADES = "npc-team-upgrades"
    const val NPC_NAME_TEAM_SHOP = "npc-team-shop"
    const val NPC_NAME_SOLO_UPGRADES = "npc-solo-upgrades"
    const val NPC_NAME_SOLO_SHOP = "npc-solo-shop"

    const val UPGRADES_MENU_GUI_NAME_PATH = "upgrades-menu-gui-name-"
    const val UPGRADES_CATEGORY_GUI_NAME_PATH = "upgrades-category-gui-name-"
    const val UPGRADES_CATEGORY_ITEM_NAME_PATH = "upgrades-category-item-name-"
    const val UPGRADES_CATEGORY_ITEM_LORE_PATH = "upgrades-category-item-lore-"
    const val UPGRADES_SEPARATOR_ITEM_NAME_PATH = "upgrades-separator-item-name-"
    const val UPGRADES_SEPARATOR_ITEM_LORE_PATH = "upgrades-separator-item-lore-"
    const val UPGRADES_TRAP_SLOT_ITEM_NAME_PATH = "upgrades-trap-slot-item-name-"
    const val UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH = "upgrades-trap-slot-item-lore1-"
    const val UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH = "upgrades-trap-slot-item-lore2-"
    const val UPGRADES_UPGRADE_TIER_ITEM_NAME = "upgrades-upgrade-name-{name}-{tier}"
    const val UPGRADES_UPGRADE_TIER_ITEM_LORE = "upgrades-upgrade-lore-{name}"
    const val UPGRADES_BASE_TRAP_ITEM_NAME_PATH = "upgrades-base-trap-name-"
    const val UPGRADES_BASE_TRAP_ITEM_LORE_PATH = "upgrades-base-trap-lore-"
    const val UPGRADES_TRAP_CUSTOM_TITLE = "upgrades-base-trap-title-"
    const val UPGRADES_TRAP_CUSTOM_SUBTITLE = "upgrades-base-trap-subtitle-"
    const val UPGRADES_TRAP_CUSTOM_MSG = "upgrades-base-trap-msg-"

    const val UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY = "upgrades-lore-insuff-money"
    const val UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY = "upgrades-lore-click-buy"
    const val UPGRADES_LORE_REPLACEMENT_UNLOCKED = "upgrades-lore-unlocked"
    const val UPGRADES_LORE_REPLACEMENT_LOCKED = "upgrades-lore-locked"
    const val UPGRADES_UPGRADE_BOUGHT_CHAT = "upgrades-new-purchase"
    const val UPGRADES_TRAP_QUEUE_LIMIT = "upgrades-trap-queue-full"
    const val UPGRADES_TRAP_DEFAULT_TITLE = "upgrades-trap-default-title"
    const val UPGRADES_TRAP_DEFAULT_SUBTITLE = "upgrades-trap-default-subtitle"
    const val UPGRADES_TRAP_DEFAULT_MSG = "upgrades-trap-default-msg"

    const val SHOP_NEW_PURCHASE = "shop-new-purchase"
    const val SHOP_UTILITY_NPC_SILVERFISH_NAME = "shop-utility-silverfish"
    const val SHOP_UTILITY_NPC_IRON_GOLEM_NAME = "shop-utility-iron-golem"
    const val SHOP_INSUFFICIENT_MONEY = "shop-insuff-money"
    const val SHOP_ALREADY_BOUGHT = "shop-already-bought"
    const val SHOP_PATH = "shop-items-messages"
    const val SHOP_LORE_STATUS_CANT_AFFORD = "shop-lore-status-cant-afford"
    const val SHOP_LORE_STATUS_CAN_BUY = "shop-lore-status-can-buy"
    const val SHOP_LORE_STATUS_MAXED = "shop-lore-status-tier-maxed"
    const val SHOP_LORE_STATUS_ARMOR = "shop-lore-status-armor"
    const val SHOP_LORE_QUICK_ADD = "shop-lore-quick-add"
    const val SHOP_LORE_QUICK_REMOVE = "shop-lore-quick-remove"
    const val SHOP_INDEX_NAME = "$SHOP_PATH.inventory-name"
    const val SHOP_QUICK_ADD_NAME = "$SHOP_PATH.quick-buy-add-inventory-name"
    const val SHOP_SEPARATOR_NAME = "$SHOP_PATH.separator-item-name"
    const val SHOP_SEPARATOR_LORE = "$SHOP_PATH.separator-item-lore"
    const val SHOP_QUICK_BUY_NAME = "$SHOP_PATH.quick-buy-item-name"
    const val SHOP_QUICK_BUY_LORE = "$SHOP_PATH.quick-buy-item-lore"
    const val SHOP_QUICK_EMPTY_NAME = "$SHOP_PATH.quick-buy-empty-item-name"
    const val SHOP_QUICK_EMPTY_LORE = "$SHOP_PATH.quick-buy-empty-item-lore"

    const val SHOP_CATEGORY_INVENTORY_NAME = "$SHOP_PATH.%category%.inventory-name"
    const val SHOP_CATEGORY_ITEM_NAME = "$SHOP_PATH.%category%.category-item-name"
    const val SHOP_CATEGORY_ITEM_LORE = "$SHOP_PATH.%category%.category-item-lore"
    const val SHOP_CONTENT_TIER_ITEM_NAME = "$SHOP_PATH.%category%.content-item-%content%-name"
    const val SHOP_CONTENT_TIER_ITEM_LORE = "$SHOP_PATH.%category%.content-item-%content%-lore"
    const val SHOP_CAN_BUY_COLOR = "$SHOP_PATH.can-buy-color"
    const val SHOP_CANT_BUY_COLOR = "$SHOP_PATH.cant-buy-color"

    /* MultiArena Lobby Item Messages */
    const val GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME = "${ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_PATH}-%path%-name"
    const val GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE = "${ConfigPath.GENERAL_CONFIGURATION_LOBBY_ITEMS_PATH}-%path%-lore"

    /* Spectator Items Messages */
    const val GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME = "${ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_PATH}-%path%-name"
    const val GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE = "${ConfigPath.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_PATH}-%path%-lore"

    /* Arena waiting Items Messages */
    const val GENERAL_CONFIGURATION_WAITING_ITEMS_NAME = "${ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_PATH}-%path%-name"
    const val GENERAL_CONFIGURATION_WAITING_ITEMS_LORE = "${ConfigPath.GENERAL_CONFIGURATION_PRE_GAME_ITEMS_PATH}-%path%-lore"
}
