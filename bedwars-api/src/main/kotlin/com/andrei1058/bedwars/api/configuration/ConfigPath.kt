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
package com.andrei1058.bedwars.api.configuration

object ConfigPath {
    const val GAME_END_PATH = "game-end"

    @GameMainOverridable
    val GENERAL_GAME_END_SHOW_ELIMINATED = "$GAME_END_PATH.show-eliminated"

    @GameMainOverridable
    val GENERAL_GAME_END_TELEPORT_ELIMINATED = "$GAME_END_PATH.teleport-eliminated"

    @GameMainOverridable
    val GENERAL_GAME_END_CHAT_TOP_STATISTIC = "$GAME_END_PATH.chat-top.order-by"

    @GameMainOverridable
    val GENERAL_GAME_END_CHAT_TOP_HIDE_MISSING = "$GAME_END_PATH.chat-top.hide-missing"

    @GameMainOverridable
    val GENERAL_GAME_END_SB_TOP_STATISTIC = "$GAME_END_PATH.sb-top.order-by"

    @GameMainOverridable
    val GENERAL_GAME_END_SB_TOP_HIDE_MISSING = "$GAME_END_PATH.sb-top.hide-missing"

    const val GENERATOR_STACK_ITEMS = "stack-items"

    const val GENERATOR_IRON_DELAY = "iron.delay"
    const val GENERATOR_IRON_AMOUNT = "iron.amount"
    const val GENERATOR_IRON_SPAWN_LIMIT = "iron.spawn-limit"

    const val GENERATOR_GOLD_DELAY = "gold.delay"
    const val GENERATOR_GOLD_AMOUNT = "gold.amount"
    const val GENERATOR_GOLD_SPAWN_LIMIT = "gold.spawn-limit"

    const val GENERATOR_DIAMOND_TIER_I_DELAY = "diamond.tierI.delay"
    const val GENERATOR_DIAMOND_TIER_I_AMOUNT = "diamond.tierI.amount"
    const val GENERATOR_DIAMOND_TIER_I_SPAWN_LIMIT = "diamond.tierI.spawn-limit"

    const val GENERATOR_DIAMOND_TIER_II_DELAY = "diamond.tierII.delay"
    const val GENERATOR_DIAMOND_TIER_II_AMOUNT = "diamond.tierII.amount"
    const val GENERATOR_DIAMOND_TIER_II_SPAWN_LIMIT = "diamond.tierII.spawn-limit"
    const val GENERATOR_DIAMOND_TIER_II_START = "diamond.tierII.start"

    const val GENERATOR_DIAMOND_TIER_III_DELAY = "diamond.tierIII.delay"
    const val GENERATOR_DIAMOND_TIER_III_AMOUNT = "diamond.tierIII.amount"
    const val GENERATOR_DIAMOND_TIER_III_SPAWN_LIMIT = "diamond.tierIII.spawn-limit"
    const val GENERATOR_DIAMOND_TIER_III_START = "diamond.tierIII.start"

    const val GENERATOR_EMERALD_TIER_I_DELAY = "emerald.tierI.delay"
    const val GENERATOR_EMERALD_TIER_I_AMOUNT = "emerald.tierI.amount"
    const val GENERATOR_EMERALD_TIER_I_SPAWN_LIMIT = "emerald.tierI.spawn-limit"

    const val GENERATOR_EMERALD_TIER_II_DELAY = "emerald.tierII.delay"
    const val GENERATOR_EMERALD_TIER_II_AMOUNT = "emerald.tierII.amount"
    const val GENERATOR_EMERALD_TIER_II_SPAWN_LIMIT = "emerald.tierII.spawn-limit"
    const val GENERATOR_EMERALD_TIER_II_START = "emerald.tierII.start"

    const val GENERATOR_EMERALD_TIER_III_DELAY = "emerald.tierIII.delay"
    const val GENERATOR_EMERALD_TIER_III_AMOUNT = "emerald.tierIII.amount"
    const val GENERATOR_EMERALD_TIER_III_SPAWN_LIMIT = "emerald.tierIII.spawn-limit"
    const val GENERATOR_EMERALD_TIER_III_START = "emerald.tierIII.start"


    const val GENERAL_CONFIGURATION_BUNGEE_MODE_GAMES_BEFORE_RESTART = "bungee-settings.games-before-restart"
    const val GENERAL_CONFIGURATION_AUTO_SCALE_LIMIT = "bungee-settings.auto-scale-clone-limit"
    const val GENERAL_CONFIGURATION_BUNGEE_OPTION_RESTART_CMD = "bungee-settings.restart-cmd"
    const val GENERAL_CONFIGURATION_BUNGEE_OPTION_LOBBY_SERVERS = "bungee-settings.lobby-sockets"
    const val GENERAL_CONFIGURATION_BUNGEE_OPTION_SERVER_ID = "bungee-settings.server-id"
    const val GENERAL_CONFIGURATION_BUNGEE_OPTION_BWP_TIME_OUT = "bungee-settings.bwp-time-out"

    const val GENERAL_CONFIGURATION_ALLOW_FIRE_EXTINGUISH = "allow-fire-extinguish"
    const val GENERAL_CONFIGURATION_ENABLE_HALLOWEEN = "enable-halloween-feature"

    const val GENERAL_CONFIGURATION_LOBBY_ITEMS_PATH = "lobby-items"
    const val GENERAL_CONFIGURATION_EXPERIMENTAL_TEAM_ASSIGNER = "use-experimental-team-assigner"

    // Replace %path% with name
    val GENERAL_CONFIGURATION_LOBBY_ITEMS_MATERIAL = "$GENERAL_CONFIGURATION_LOBBY_ITEMS_PATH.%path%.material"
    val GENERAL_CONFIGURATION_LOBBY_ITEMS_DATA = "$GENERAL_CONFIGURATION_LOBBY_ITEMS_PATH.%path%.data"
    val GENERAL_CONFIGURATION_LOBBY_ITEMS_SLOT = "$GENERAL_CONFIGURATION_LOBBY_ITEMS_PATH.%path%.slot"
    val GENERAL_CONFIGURATION_LOBBY_ITEMS_ENCHANTED =
        "$GENERAL_CONFIGURATION_LOBBY_ITEMS_PATH.%path%.enchanted"
    val GENERAL_CONFIGURATION_LOBBY_ITEMS_COMMAND = "$GENERAL_CONFIGURATION_LOBBY_ITEMS_PATH.%path%.command"

    const val GENERAL_CONFIGURATION_SPECTATOR_ITEMS_PATH = "spectator-items"

    // Replace %path% with name
    val GENERAL_CONFIGURATION_SPECTATOR_ITEMS_MATERIAL =
        "$GENERAL_CONFIGURATION_SPECTATOR_ITEMS_PATH.%path%.material"
    val GENERAL_CONFIGURATION_SPECTATOR_ITEMS_DATA = "$GENERAL_CONFIGURATION_SPECTATOR_ITEMS_PATH.%path%.data"
    val GENERAL_CONFIGURATION_SPECTATOR_ITEMS_SLOT = "$GENERAL_CONFIGURATION_SPECTATOR_ITEMS_PATH.%path%.slot"
    val GENERAL_CONFIGURATION_SPECTATOR_ITEMS_ENCHANTED =
        "$GENERAL_CONFIGURATION_SPECTATOR_ITEMS_PATH.%path%.enchanted"
    val GENERAL_CONFIGURATION_SPECTATOR_ITEMS_COMMAND =
        "$GENERAL_CONFIGURATION_SPECTATOR_ITEMS_PATH.%path%.command"

    const val GENERAL_CONFIGURATION_STATS_PATH = "stats-gui"
    val GENERAL_CONFIGURATION_STATS_GUI_SIZE = "$GENERAL_CONFIGURATION_STATS_PATH.inv-size"
    val GENERAL_CONFIGURATION_STATS_ITEMS_MATERIAL = "$GENERAL_CONFIGURATION_STATS_PATH.%path%.material"
    val GENERAL_CONFIGURATION_STATS_ITEMS_DATA = "$GENERAL_CONFIGURATION_STATS_PATH.%path%.data"
    val GENERAL_CONFIGURATION_STATS_ITEMS_SLOT = "$GENERAL_CONFIGURATION_STATS_PATH.%path%.slot"

    const val GENERAL_CONFIGURATION_PRE_GAME_ITEMS_PATH = "pre-game-items"

    // Replace %path% with name
    val GENERAL_CONFIGURATION_PRE_GAME_ITEMS_MATERIAL =
        "$GENERAL_CONFIGURATION_PRE_GAME_ITEMS_PATH.%path%.material"
    val GENERAL_CONFIGURATION_PRE_GAME_ITEMS_DATA = "$GENERAL_CONFIGURATION_PRE_GAME_ITEMS_PATH.%path%.data"
    val GENERAL_CONFIGURATION_PRE_GAME_ITEMS_SLOT = "$GENERAL_CONFIGURATION_PRE_GAME_ITEMS_PATH.%path%.slot"
    val GENERAL_CONFIGURATION_PRE_GAME_ITEMS_ENCHANTED =
        "$GENERAL_CONFIGURATION_PRE_GAME_ITEMS_PATH.%path%.enchanted"
    val GENERAL_CONFIGURATION_PRE_GAME_ITEMS_COMMAND =
        "$GENERAL_CONFIGURATION_PRE_GAME_ITEMS_PATH.%path%.command"

    const val GENERAL_CONFIGURATION_START_COUNTDOWN_REGULAR = "countdowns.game-start-regular"
    const val GENERAL_CONFIGURATION_START_COUNTDOWN_SHORTENED = "countdowns.game-start-shortened"
    const val GENERAL_CONFIGURATION_START_COUNTDOWN_HALF = "countdowns.game-start-half-arena"
    const val GENERAL_CONFIGURATION_RESTART = "countdowns.game-restart"
    const val GENERAL_CONFIGURATION_RE_SPAWN_COUNTDOWN = "countdowns.player-re-spawn"
    const val GENERAL_CONFIGURATION_BEDS_DESTROY_COUNTDOWN = "countdowns.next-event-beds-destroy"
    const val GENERAL_CONFIGURATION_DRAGON_SPAWN_COUNTDOWN = "countdowns.next-event-dragon-spawn"
    const val GENERAL_CONFIGURATION_GAME_END_COUNTDOWN = "countdowns.next-event-game-end"

    const val GENERAL_CONFIGURATION_HUNGER_WAITING = "allow-hunger-depletion.waiting"
    const val GENERAL_CONFIGURATION_HUNGER_INGAME = "allow-hunger-depletion.ingame"

    const val GENERAL_CONFIGURATION_ARENA_GROUPS = "arenaGroups"
    const val GENERAL_CONFIGURATION_REJOIN_TIME = "rejoin-time"
    const val GENERAL_CONFIGURATION_RE_SPAWN_INVULNERABILITY = "re-spawn-invulnerability"

    const val GENERAL_CONFIGURATION_SHOUT_COOLDOWN = "shout-cmd-cooldown"

    const val GENERAL_CONFIGURATION_NPC_LOC_STORAGE = "join-npc-locations"
    const val GENERAL_CONFIGURATION_DEFAULT_ITEMS = "start-items-per-group"

    const val CENERAL_CONFIGURATION_ALLOWED_COMMANDS = "allowed-commands"
    const val SB_CONFIG_SIDEBAR_USE_LOBBY_SIDEBAR = "scoreboard-settings.sidebar.enable-lobby-sidebar"
    const val SB_CONFIG_SIDEBAR_USE_GAME_SIDEBAR = "scoreboard-settings.sidebar.enable-game-sidebar"
    const val SB_CONFIG_SIDEBAR_TITLE_REFRESH_INTERVAL = "scoreboard-settings.sidebar.title-refresh-interval"
    const val SB_CONFIG_SIDEBAR_PLACEHOLDERS_REFRESH_INTERVAL =
        "scoreboard-settings.sidebar.placeholders-refresh-interval"
    const val SB_CONFIG_SIDEBAR_LIST_FORMAT_LOBBY = "scoreboard-settings.player-list.format-lobby-list"
    const val SB_CONFIG_SIDEBAR_LIST_FORMAT_WAITING = "scoreboard-settings.player-list.format-waiting-list"
    const val SB_CONFIG_SIDEBAR_LIST_FORMAT_STARTING = "scoreboard-settings.player-list.format-starting-list"
    const val SB_CONFIG_SIDEBAR_LIST_FORMAT_PLAYING = "scoreboard-settings.player-list.format-playing-list"
    const val SB_CONFIG_SIDEBAR_LIST_FORMAT_RESTARTING =
        "scoreboard-settings.player-list.format-restarting-list"
    const val SB_CONFIG_SIDEBAR_LIST_REFRESH = "scoreboard-settings.player-list.names-refresh-interval"
    const val SB_CONFIG_SIDEBAR_HEALTH_ENABLE = "scoreboard-settings.health.enable"
    const val SB_CONFIG_SIDEBAR_HEALTH_IN_TAB = "scoreboard-settings.health.display-in-tab"
    const val SB_CONFIG_SIDEBAR_HEALTH_REFRESH = "scoreboard-settings.health.animation-refresh-interval"

    const val SB_CONFIG_TAB_HEADER_FOOTER_ENABLE = "scoreboard-settings.tab-header-footer.enable"
    const val SB_CONFIG_TAB_HEADER_FOOTER_REFRESH_INTERVAL =
        "scoreboard-settings.tab-header-footer.refresh-interval"

    const val GENERAL_CONFIGURATION_DISABLED_LANGUAGES = "disabled-languages"

    const val GENERAL_CONFIGURATION_ARENA_SELECTOR_PATH = "arena-gui"
    val GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_SIZE =
        "$GENERAL_CONFIGURATION_ARENA_SELECTOR_PATH.settings.inv-size"
    val GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_SHOW_PLAYING =
        "$GENERAL_CONFIGURATION_ARENA_SELECTOR_PATH.settings.show-playing"
    val GENERAL_CONFIGURATION_ARENA_SELECTOR_SETTINGS_USE_SLOTS =
        "$GENERAL_CONFIGURATION_ARENA_SELECTOR_PATH.settings.use-slots"
    val GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_MATERIAL =
        "$GENERAL_CONFIGURATION_ARENA_SELECTOR_PATH.%path%.material"
    val GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_DATA =
        "$GENERAL_CONFIGURATION_ARENA_SELECTOR_PATH.%path%.data"
    val GENERAL_CONFIGURATION_ARENA_SELECTOR_STATUS_ENCHANTED =
        "$GENERAL_CONFIGURATION_ARENA_SELECTOR_PATH.%path%.enchanted"
    const val GENERAL_CONFIGURATION_DISABLE_CRAFTING = "inventories.disable-crafting-table"
    const val GENERAL_CONFIGURATION_DISABLE_ENCHANTING = "inventories.disable-enchanting-table"
    const val GENERAL_CONFIGURATION_DISABLE_FURNACE = "inventories.disable-furnace"
    const val GENERAL_CONFIGURATION_DISABLE_BREWING_STAND = "inventories.disable-brewing-stand"
    const val GENERAL_CONFIGURATION_DISABLE_ANVIL = "inventories.disable-anvil"
    const val GENERAL_CONFIGURATION_MARK_LEAVE_AS_ABANDON = "mark-leave-as-abandon"
    const val GENERAL_CONFIGURATION_ENABLE_GEN_SPLIT = "enable-gen-split"

    const val GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_SERVER_IP = "server-ip"
    const val GENERAL_CONFIG_PLACEHOLDERS_REPLACEMENTS_POWERED_BY = "powered-by"

    private const val GENERAL_CHAT = "chat-settings."
    val GENERAL_CHAT_FORMATTING = GENERAL_CHAT + "format"
    val GENERAL_CHAT_GLOBAL = GENERAL_CHAT + "global"

    const val ARENA_DISPLAY_NAME = "display-name"
    const val ARENA_CONFIGURATION_MAX_BUILD_Y = "max-build-y"
    const val ARENA_SPAWN_PROTECTION = "spawn-protection"
    const val ARENA_SHOP_PROTECTION = "shop-protection"
    const val ARENA_UPGRADES_PROTECTION = "upgrades-protection"
    const val ARENA_GENERATOR_PROTECTION = "generator-protection"
    const val ARENA_DISABLE_GENERATOR_FOR_EMPTY_TEAMS = "disable-generator-for-empty-teams"
    const val ARENA_DISABLE_NPCS_FOR_EMPTY_TEAMS = "disable-npcs-for-empty-teams"
    const val ARENA_ISLAND_RADIUS = "island-radius"
    const val ARENA_WAITING_POS1 = "waiting.Pos1"
    const val ARENA_WAITING_POS2 = "waiting.Pos2"
    const val ARENA_NORMAL_DEATH_DROPS = "vanilla-death-drops"
    const val ARENA_USE_BED_HOLO = "use-bed-hologram"
    const val ARENA_ALLOW_MAP_BREAK = "allow-map-break"
    const val ARENA_GAME_RULES = "game-rules"
    const val ARENA_SPEC_LOC = "spectator-loc"
    const val ARENA_TEAM_KILL_DROPS_LOC = "kill-drops-loc"
    const val ARENA_Y_LEVEL_KILL = "y-kill-height"

    const val SOUNDS_COUNTDOWN_TICK = "game-countdown-others"
    const val SOUNDS_COUNTDOWN_TICK_X = "game-countdown-s"
    const val SOUND_GAME_START = "game-countdown-start"
    const val SOUNDS_BED_DESTROY = "bed-destroy"
    const val SOUNDS_BED_DESTROY_OWN = "bed-destroy-own"
    const val SOUNDS_INSUFF_MONEY = "shop-insufficient-money"
    const val SOUNDS_BOUGHT = "shop-bought"
    const val SOUNDS_KILL = "kill"

    const val SIGNS_STATUS_BLOCK_WAITING_MATERIAL = "status-block.waiting.material"
    const val SIGNS_STATUS_BLOCK_WAITING_DATA = "status-block.waiting.data"
    const val SIGNS_STATUS_BLOCK_STARTING_MATERIAL = "status-block.starting.material"
    const val SIGNS_STATUS_BLOCK_STARTING_DATA = "status-block.starting.data"
    const val SIGNS_STATUS_BLOCK_PLAYING_MATERIAL = "status-block.playing.material"
    const val SIGNS_STATUS_BLOCK_PLAYING_DATA = "status-block.playing.data"
    const val SIGNS_STATUS_BLOCK_RESTARTING_MATERIAL = "status-block.restarting.material"
    const val SIGNS_STATUS_BLOCK_RESTARTING_DATA = "status-block.restarting.data"

    private const val GENERAL_PARTY_SETTINGS_PATH = "party-settings"
    val GENERAL_ENABLE_PARTY_CMD = "$GENERAL_PARTY_SETTINGS_PATH.enable-party-cmd"
    val GENERAL_CONFIGURATION_ALLOW_PARTIES = "$GENERAL_PARTY_SETTINGS_PATH.allow-parties"
    val GENERAL_ALESSIODP_PARTIES_RANK = "$GENERAL_PARTY_SETTINGS_PATH.alessioDP-choose-arena-rank"

    private const val GENERAL_TNT_JUMP_PATH = "tnt-jump-settings"
    val GENERAL_TNT_JUMP_BARYCENTER_IN_Y = "$GENERAL_TNT_JUMP_PATH.barycenter-alteration-in-y"
    val GENERAL_TNT_JUMP_STRENGTH_REDUCTION = "$GENERAL_TNT_JUMP_PATH.strength-reduction-constant"
    val GENERAL_TNT_JUMP_Y_REDUCTION = "$GENERAL_TNT_JUMP_PATH.y-axis-reduction-constant"
    val GENERAL_TNT_JUMP_DAMAGE_SELF = "$GENERAL_TNT_JUMP_PATH.damage-self"
    val GENERAL_TNT_JUMP_DAMAGE_TEAMMATES = "$GENERAL_TNT_JUMP_PATH.damage-teammates"
    val GENERAL_TNT_JUMP_DAMAGE_OTHERS = "$GENERAL_TNT_JUMP_PATH.damage-others"

    const val GENERAL_TNT_BLAST_PROTECTION = "blast-protection"
    val GENERAL_TNT_PROTECTION_END_STONE_BLAST = "$GENERAL_TNT_BLAST_PROTECTION.end-stone"
    val GENERAL_TNT_PROTECTION_GLASS_BLAST = "$GENERAL_TNT_BLAST_PROTECTION.glass"
    val GENERAL_TNT_RAY_BLOCKED_BY_GLASS = "$GENERAL_TNT_BLAST_PROTECTION.ray-blocked-by-glass"

    const val GENERAL_TNT_PRIME = "tnt-prime-settings"
    val GENERAL_TNT_AUTO_IGNITE = "$GENERAL_TNT_PRIME.auto-ignite"
    val GENERAL_TNT_FUSE_TICKS = "$GENERAL_TNT_PRIME.fuse-ticks"

    private const val GENERAL_FIREBALL_PATH = "fireball"
    val GENERAL_FIREBALL_EXPLOSION_SIZE = "$GENERAL_FIREBALL_PATH.explosion-size"
    val GENERAL_FIREBALL_SPEED_MULTIPLIER = "$GENERAL_FIREBALL_PATH.speed-multiplier"
    val GENERAL_FIREBALL_MAKE_FIRE = "$GENERAL_FIREBALL_PATH.make-fire"
    private val GENERAL_FIREBALL_KNOCKBACK_PATH = "$GENERAL_FIREBALL_PATH.knockback"
    val GENERAL_FIREBALL_KNOCKBACK_VERTICAL = "$GENERAL_FIREBALL_KNOCKBACK_PATH.vertical"
    val GENERAL_FIREBALL_KNOCKBACK_HORIZONTAL = "$GENERAL_FIREBALL_KNOCKBACK_PATH.horizontal"
    val GENERAL_FIREBALL_COOLDOWN = "$GENERAL_FIREBALL_PATH.cooldown"
    private val GENERAL_FIREBALL_DAMAGE_PATH = "$GENERAL_FIREBALL_PATH.damage"
    val GENERAL_FIREBALL_DAMAGE_SELF = "$GENERAL_FIREBALL_DAMAGE_PATH.self"
    val GENERAL_FIREBALL_DAMAGE_ENEMY = "$GENERAL_FIREBALL_DAMAGE_PATH.enemy"
    val GENERAL_FIREBALL_DAMAGE_TEAMMATES = "$GENERAL_FIREBALL_DAMAGE_PATH.teammates"


    const val GENERAL_CONFIGURATION_PERFORMANCE_PATH = "performance-settings"
    val GENERAL_CONFIGURATION_PERFORMANCE_ROTATE_GEN =
        "$GENERAL_CONFIGURATION_PERFORMANCE_PATH.rotate-generators"
    val GENERAL_CONFIGURATION_PERFORMANCE_SPOIL_TNT_PLAYERS =
        "$GENERAL_CONFIGURATION_PERFORMANCE_PATH.spoil-tnt-players"

    val GENERAL_CONFIGURATION_PERFORMANCE_PAPER_FEATURES =
        "$GENERAL_CONFIGURATION_PERFORMANCE_PATH.paper-features"

    private val GENERAL_CONFIGURATION_HEAL_POOL = "$GENERAL_CONFIGURATION_PERFORMANCE_PATH.heal-pool"
    val GENERAL_CONFIGURATION_HEAL_POOL_ENABLE = "$GENERAL_CONFIGURATION_HEAL_POOL.enable"
    val GENERAL_CONFIGURATION_HEAL_POOL_SEEN_TEAM_ONLY = "$GENERAL_CONFIGURATION_HEAL_POOL.seen-by-team-only"
    const val SHOP_SETTINGS_PATH = "shop-settings"
    const val SHOP_SPECIALS_PATH = "shop-specials"
    const val SHOP_QUICK_DEFAULTS_PATH = "quick-buy-defaults"

    val SHOP_SPECIAL_TOWER_ENABLE = "$SHOP_SPECIALS_PATH.tower.enable"
    val SHOP_SPECIAL_TOWER_MATERIAL = "$SHOP_SPECIALS_PATH.tower.material"
    val SHOP_SPECIAL_SILVERFISH_ENABLE = "$SHOP_SPECIALS_PATH.silverfish.enable"
    val SHOP_SPECIAL_SILVERFISH_MATERIAL = "$SHOP_SPECIALS_PATH.silverfish.material"
    val SHOP_SPECIAL_SILVERFISH_DATA = "$SHOP_SPECIALS_PATH.silverfish.data"
    val SHOP_SPECIAL_SILVERFISH_HEALTH = "$SHOP_SPECIALS_PATH.silverfish.health"
    val SHOP_SPECIAL_SILVERFISH_DAMAGE = "$SHOP_SPECIALS_PATH.silverfish.damage"
    val SHOP_SPECIAL_SILVERFISH_SPEED = "$SHOP_SPECIALS_PATH.silverfish.speed"
    val SHOP_SPECIAL_SILVERFISH_DESPAWN = "$SHOP_SPECIALS_PATH.silverfish.despawn"

    val SHOP_SPECIAL_IRON_GOLEM_ENABLE = "$SHOP_SPECIALS_PATH.iron-golem.enable"
    val SHOP_SPECIAL_IRON_GOLEM_MATERIAL = "$SHOP_SPECIALS_PATH.iron-golem.material"
    val SHOP_SPECIAL_IRON_GOLEM_DATA = "$SHOP_SPECIALS_PATH.iron-golem.data"
    val SHOP_SPECIAL_IRON_GOLEM_HEALTH = "$SHOP_SPECIALS_PATH.iron-golem.health"
    val SHOP_SPECIAL_IRON_GOLEM_DESPAWN = "$SHOP_SPECIALS_PATH.iron-golem.despawn"
    val SHOP_SPECIAL_IRON_GOLEM_SPEED = "$SHOP_SPECIALS_PATH.iron-golem.speed"

    val SHOP_SETTINGS_QUICK_BUY_CATEGORY_PATH = "$SHOP_SETTINGS_PATH.quick-buy-category"
    val SHOP_SETTINGS_QUICK_BUY_BUTTON_MATERIAL = "$SHOP_SETTINGS_QUICK_BUY_CATEGORY_PATH.material"
    val SHOP_SETTINGS_QUICK_BUY_BUTTON_AMOUNT = "$SHOP_SETTINGS_QUICK_BUY_CATEGORY_PATH.amount"
    val SHOP_SETTINGS_QUICK_BUY_BUTTON_DATA = "$SHOP_SETTINGS_QUICK_BUY_CATEGORY_PATH.data"
    val SHOP_SETTINGS_QUICK_BUY_BUTTON_ENCHANTED = "$SHOP_SETTINGS_QUICK_BUY_CATEGORY_PATH.enchanted"

    val SHOP_SETTINGS_SEPARATOR_REGULAR_PATH = "$SHOP_SETTINGS_PATH.regular-separator-item"
    val SHOP_SETTINGS_SEPARATOR_REGULAR_MATERIAL = "$SHOP_SETTINGS_SEPARATOR_REGULAR_PATH.material"
    val SHOP_SETTINGS_SEPARATOR_REGULAR_AMOUNT = "$SHOP_SETTINGS_SEPARATOR_REGULAR_PATH.amount"
    val SHOP_SETTINGS_SEPARATOR_REGULAR_DATA = "$SHOP_SETTINGS_SEPARATOR_REGULAR_PATH.data"
    val SHOP_SETTINGS_SEPARATOR_REGULAR_ENCHANTED = "$SHOP_SETTINGS_SEPARATOR_REGULAR_PATH.enchanted"

    val SHOP_SETTINGS_SEPARATOR_SELECTED_PATH = "$SHOP_SETTINGS_PATH.selected-separator-item"
    val SHOP_SETTINGS_SEPARATOR_SELECTED_MATERIAL = "$SHOP_SETTINGS_SEPARATOR_SELECTED_PATH.material"
    val SHOP_SETTINGS_SEPARATOR_SELECTED_AMOUNT = "$SHOP_SETTINGS_SEPARATOR_SELECTED_PATH.amount"
    val SHOP_SETTINGS_SEPARATOR_SELECTED_DATA = "$SHOP_SETTINGS_SEPARATOR_SELECTED_PATH.data"
    val SHOP_SETTINGS_SEPARATOR_SELECTED_ENCHANTED = "$SHOP_SETTINGS_SEPARATOR_SELECTED_PATH.enchanted"

    val SHOP_SETTINGS_QUICK_BUY_EMPTY_PATH = "$SHOP_SETTINGS_PATH.quick-buy-empty-item"
    val SHOP_SETTINGS_QUICK_BUY_EMPTY_MATERIAL = "$SHOP_SETTINGS_QUICK_BUY_EMPTY_PATH.material"
    val SHOP_SETTINGS_QUICK_BUY_EMPTY_AMOUNT = "$SHOP_SETTINGS_QUICK_BUY_EMPTY_PATH.amount"
    val SHOP_SETTINGS_QUICK_BUY_EMPTY_DATA = "$SHOP_SETTINGS_QUICK_BUY_EMPTY_PATH.data"
    val SHOP_SETTINGS_QUICK_BUY_EMPTY_ENCHANTED = "$SHOP_SETTINGS_QUICK_BUY_EMPTY_PATH.enchanted"

    const val SHOP_CATEGORY_ITEM_MATERIAL = ".category-item.material"
    const val SHOP_CATEGORY_ITEM_DATA = ".category-item.data"
    const val SHOP_CATEGORY_ITEM_AMOUNT = ".category-item.amount"
    const val SHOP_CATEGORY_ITEM_ENCHANTED = ".category-item.enchanted"

    const val SHOP_CONTENT_TIER_SETTINGS_COST = ".tier-settings.cost"
    const val SHOP_CONTENT_TIER_SETTINGS_CURRENCY = ".tier-settings.currency"

    const val SHOP_CONTENT_TIER_ITEM_MATERIAL = ".tier-item.material"
    const val SHOP_CONTENT_TIER_ITEM_DATA = ".tier-item.data"
    const val SHOP_CONTENT_TIER_ITEM_AMOUNT = ".tier-item.amount"
    const val SHOP_CONTENT_TIER_ITEM_ENCHANTED = ".tier-item.enchanted"

    const val SHOP_CATEGORY_SLOT = ".category-slot"
    const val SHOP_CONTENT_BUY_ITEMS_PATH = "buy-items"
    const val SHOP_CONTENT_BUY_CMDS_PATH = "buy-cmds"
    const val SHOP_CATEGORY_CONTENT_CONTENT_SLOT = "content-settings.content-slot"
    const val SHOP_CATEGORY_CONTENT_IS_PERMANENT = "content-settings.is-permanent"
    const val SHOP_CATEGORY_CONTENT_IS_DOWNGRADABLE = "content-settings.is-downgradable"
    const val SHOP_CATEGORY_CONTENT_IS_UNBREAKABLE = "content-settings.is-unbreakable"
    const val SHOP_CATEGORY_CONTENT_WEIGHT = "content-settings.weight"
    const val SHOP_CATEGORY_CONTENT_CONTENT_TIERS = "content-tiers"
    const val SHOP_CATEGORY_CONTENT_PATH = ".category-content"

    const val SHOP_PATH_CATEGORY_BLOCKS = "blocks-category"
    const val SHOP_PATH_CATEGORY_MELEE = "melee-category"
    const val SHOP_PATH_CATEGORY_ARMOR = "armor-category"
    const val SHOP_PATH_CATEGORY_TOOLS = "tools-category"
    const val SHOP_PATH_CATEGORY_RANGED = "ranged-category"
    const val SHOP_PATH_CATEGORY_POTIONS = "potions-category"
    const val SHOP_PATH_CATEGORY_UTILITY = "utility-category"

    const val TEAM_NAME_PATH = "team-name-{arena}-{team}"

    const val LOBBY_VOID_TELEPORT_ENABLED = "lobby-settings.void-tp"
    const val LOBBY_VOID_TELEPORT_HEIGHT = "lobby-settings.void-height"
}
