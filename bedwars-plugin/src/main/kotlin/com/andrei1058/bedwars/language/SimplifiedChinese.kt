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

class SimplifiedChinese : Language(BedWars.INSTANCE, "zh_cn", "简体中文") {
    init {
        val bw = BedWars.MAIN_COMMAND

        addDefault(
            Messages.COMMAND_MAIN,
            listOf(
                "",
                "&2▪ &7/$bw stats",
                "&2▪ &7/$bw join &o<游戏/模式>",
                "&2▪ &7/$bw leave",
                "&2▪ &7/$bw lang",
                "&2▪ &7/$bw gui",
                "&2▪ &7/$bw start &3（赞助者）"
            )
        )
        addDefault(Messages.COMMAND_LANG_LIST_HEADER, "{prefix} &2可用的语言：")
        addDefault(Messages.COMMAND_LANG_LIST_FORMAT, "&a▪  &7{iso} - &f{name}")
        addDefault(Messages.COMMAND_LANG_USAGE, "{prefix}&用法：/lang &f&o<iso>")
        addDefault(Messages.COMMAND_LANG_SELECTED_NOT_EXIST, "{prefix}&c该语言不存在！")
        addDefault(Messages.COMMAND_LANG_SELECTED_SUCCESSFULLY, "{prefix}&a语言已设置！")
        addDefault(Messages.COMMAND_LANG_USAGE_DENIED, "{prefix}&c你不能在游戏进行时修改语言。")
        addDefault(Messages.COMMAND_JOIN_USAGE, "&a▪ &7用法：/$bw join &o<游戏/模式>")
        addDefault(Messages.COMMAND_JOIN_GROUP_OR_ARENA_NOT_FOUND, "{prefix}&c游戏{name}不存在！")
        addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL,
            "{prefix}&c游戏已满！\n&a请考虑赞助以支持我们！ &7&o(点击查看)"
        )
        addDefault(Messages.COMMAND_JOIN_NO_EMPTY_FOUND, "{prefix}&c现在没有可用的游戏:(")
        addDefault(
            Messages.COMMAND_JOIN_DENIED_IS_FULL_OF_VIPS,
            "{prefix}&c很抱歉，虽然我们已知道你已赞助，但该游戏已满。\n&c此游戏中全是赞助者或管理员。"
        )
        addDefault(
            Messages.COMMAND_JOIN_DENIED_PARTY_TOO_BIG,
            "{prefix}&c你的队伍人数太多了，不能作为一个队伍加入该游戏:("
        )
        addDefault(Messages.COMMAND_JOIN_DENIED_NOT_PARTY_LEADER, "{prefix}&c只有队长才能选择游戏。")
        addDefault(Messages.COMMAND_JOIN_PLAYER_JOIN_MSG, "{prefix}&7{player}&e加入了游戏(&b{on}&e/&b{max}&e)！")
        addDefault(
            Messages.COMMAND_JOIN_SPECTATOR_MSG,
            "{prefix}&6你正在观战&9{arena}&6。\n{prefix}&e输入 &c/leave &e离开。"
        )
        addDefault(Messages.COMMAND_JOIN_SPECTATOR_DENIED_MSG, "&c该游戏不允许旁观！")
        addDefault(Messages.COMMAND_TP_PLAYER_NOT_FOUND, "{prefix}&c无法找到这位玩家！")
        addDefault(Messages.COMMAND_TP_NOT_IN_ARENA, "{prefix}&c该玩家不在任何一场起床战争游戏中！")
        addDefault(Messages.COMMAND_TP_NOT_STARTED, "{prefix}&c该玩家所在的游戏还没开始！")
        addDefault(Messages.COMMAND_TP_USAGE, "{prefix}&c用法：/bw tp <玩家名>")
        addDefault(Messages.REJOIN_NO_ARENA, "{prefix}&c没有可以重新加入的游戏！")
        addDefault(Messages.REJOIN_DENIED, "{prefix}&c由于你所属队伍的床被破坏或游戏已经结束，你不能重新加入。")
        addDefault(Messages.REJOIN_ALLOWED, "{prefix}&e正在重新加入&a{arena}&e！")
        addDefault(Messages.COMMAND_REJOIN_PLAYER_RECONNECTED, "{prefix}&7{player}&e重新连接。")
        addDefault(Messages.COMMAND_LEAVE_DENIED_NOT_IN_ARENA, "{prefix}&c你不在一场起床战争游戏中！")
        addDefault(Messages.COMMAND_LEAVE_MSG, "{prefix}&7{player}&e离开了！")
        addDefault(Messages.COMMAND_NOT_ALLOWED_IN_GAME, "{prefix}&c你在游戏中不可以这么做。")
        addDefault(Messages.COMMAND_NOT_FOUND_OR_INSUFF_PERMS, "{prefix}&c指令无效或你没有权限！")
        addDefault(
            Messages.COMMAND_PARTY_HELP, listOf(
                "&6▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&a队伍指令：",
                "&e/party help &7- &b查看该提示",
                "&e/party invite <玩家> &7- &b邀请一位玩家到你的队伍",
                "&e/party leave &7- &b离开当前队伍",
                "&e/party info &7- &bShow party members and owner",
                "&e/party promote <player> &7- &bTransfer party ownership",
                "&e/party remove <玩家> &7- &b将玩家移出队伍",
                "&e/party accept <玩家> &7- &b接受队伍邀请",
                "&e/party disband &7- &bD解散队伍"
            )
        )
        addDefault(Messages.COMMAND_PARTY_INVITE_USAGE, "{prefix}&e用法：&7/party invite <玩家>")
        addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player}&e不在线！")
        addDefault(Messages.COMMAND_PARTY_INVITE_SENT, "{prefix}&e已向&7{player}&e发送邀请&6。")
        addDefault(
            Messages.COMMAND_PARTY_INVITE_SENT_TARGET_RECEIVE_MSG,
            "{prefix}&b{player}&e邀请你加入队伍！ &o&7(点击接受)"
        )
        addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_CANNOT_INVITE_YOURSELF, "{prefix}&c你不可以邀请你自己！")
        addDefault(Messages.COMMAND_PARTY_INVITE_DENIED_PLAYER_OFFLINE, "{prefix}&7{player}&e不在线！")
        addDefault(Messages.COMMAND_PARTY_ACCEPT_DENIED_NO_INVITE, "{prefix}&c没有可以接受的队伍邀请！")
        addDefault(Messages.COMMAND_PARTY_ACCEPT_DENIED_ALREADY_IN_PARTY, "{prefix}&e你已经在队伍中了！")
        addDefault(Messages.COMMAND_PARTY_INSUFFICIENT_PERMISSIONS, "{prefix}&c只有队长才可以这么做！")
        addDefault(Messages.COMMAND_PARTY_ACCEPT_USAGE, "{prefix}&e用法：&7/party accept <玩家>")
        addDefault(Messages.COMMAND_PARTY_ACCEPT_SUCCESS, "{prefix}&7{player}&e加入了队伍！")
        addDefault(Messages.COMMAND_PARTY_GENERAL_DENIED_NOT_IN_PARTY, "{prefix}&c你不在队伍中！")
        addDefault(
            Messages.COMMAND_PARTY_LEAVE_DENIED_IS_OWNER_NEEDS_DISBAND,
            "{prefix}&c你不可以离开由你带领的队伍！\n&e使用：&b/party disband &e来解散队伍。"
        )
        addDefault(Messages.COMMAND_PARTY_LEAVE_SUCCESS, "{prefix}&7{player}&e离开了队伍！")
        addDefault(Messages.COMMAND_PARTY_DISBAND_SUCCESS, "{prefix}&e队伍已解散！")
        addDefault(Messages.COMMAND_PARTY_REMOVE_USAGE, "{prefix}&7用法：&e/party remove <玩家>")
        addDefault(Messages.COMMAND_PARTY_REMOVE_SUCCESS, "{prefix}&7{player}&e被移出了队伍。")
        addDefault(
            Messages.COMMAND_PARTY_REMOVE_DENIED_TARGET_NOT_PARTY_MEMBER,
            "{prefix}&7{player}&e不在你的队伍中！"
        )
        addDefault(Messages.COMMAND_PARTY_PROMOTE_SUCCESS, "{prefix}&e你成功将 {player} 提升为群主")
        addDefault(Messages.COMMAND_PARTY_PROMOTE_OWNER, "{prefix}&e你已被提升为群主")
        addDefault(Messages.COMMAND_PARTY_PROMOTE_NEW_OWNER, "{prefix}&7 &e{player} 已被提升为群主")
        addDefault(Messages.COMMAND_PARTY_INFO_OWNER, "\n{prefix}&e群主为: &7{owner}")
        addDefault(Messages.COMMAND_PARTY_INFO_PLAYERS, "{prefix}&e群成员有：")
        addDefault(Messages.COMMAND_PARTY_INFO_PLAYER, "&7{player}")
        addDefault(Messages.COMMAND_FORCESTART_NOT_IN_GAME, "&c▪ &7你不在游戏中！")
        addDefault(Messages.COMMAND_FORCESTART_SUCCESS, "&c▪ &7游戏开始倒计时缩短！")
        addDefault(
            Messages.COMMAND_FORCESTART_NO_PERM,
            "{prefix}&7你不可以强制开始游戏！\n&7请考虑赞助以得到对应权限！"
        )
        addDefault(Messages.COMMAND_COOLDOWN, "&c你不能这么做！ 请等待 {seconds} 秒！")
        addDefault(
            Messages.ARENA_JOIN_VIP_KICK,
            "{prefix}&c抱歉，由于有一位赞助者加入该游戏，因此你被移出了该游戏。\n&a请考虑赞助以支持我们！ &7&o(点击查看)"
        )
        addDefault(Messages.ARENA_START_COUNTDOWN_STOPPED_INSUFF_PLAYERS_CHAT, "{prefix}&c玩家不足！ 倒计时取消！")
        addDefault(Messages.ARENA_RESTART_PLAYER_KICK, "{prefix}&e当前游戏正在重启。")
        addDefault(Messages.ARENA_STATUS_PLAYING_NAME, "&c游戏中")
        addDefault(Messages.ARENA_STATUS_RESTARTING_NAME, "&4重启中")
        addDefault(Messages.ARENA_STATUS_WAITING_NAME, "&3等待中 &c{full}")
        addDefault(Messages.ARENA_STATUS_STARTING_NAME, "&6即将开始 &c{full}")
        addDefault(Messages.ARENA_GUI_INV_NAME, "&8点击加入")
        addDefault(Messages.ARENA_GUI_ARENA_CONTENT_NAME, "&a&l{name}")
        addDefault(
            Messages.ARENA_GUI_ARENA_CONTENT_LORE,
            listOf(
                "",
                "&7状态：{status}",
                "&7玩家数：&f{on}&7/&f{max}",
                "&7模式：&a{group}",
                "",
                "&a点击进入",
                "&e右击观赛"
            )
        )
        addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_NAME, "&r{serverIp}")
        addDefault(Messages.ARENA_GUI_SKIPPED_ITEM_LORE, emptyList<String>())
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CHAT, "{prefix}&e游戏将在 &6{time}&e 秒后开始！")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_TITLE, " ")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE, "&a{second}")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-5", "&e❺")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-4", "&e❹")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-3", "&c❸")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-2", "&c❷")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_SUB_TITLE + "-1", "&c❶")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_TITLE, " ")
        addDefault(Messages.ARENA_STATUS_START_COUNTDOWN_CANCELLED_SUB_TITLE, "&c等待更多玩家……")
        addDefault(Messages.ARENA_STATUS_START_PLAYER_TITLE, "&a游戏开始")
        addDefault(
            Messages.ARENA_STATUS_START_PLAYER_TUTORIAL, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                  &l起床战争", "",
                "&e&l                            保护你的床并破坏敌人的床，",
                "&e&l                        从资源点收集铁锭、金锭、绿宝石和钻石，",
                "&e&l                        来购买强力装备和进行升级来使自己变强！",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        addDefault(Messages.ARENA_JOIN_DENIED_SELECTOR, "{prefix}&c抱歉，你现在不能加入该游戏。右键来观赛！")
        addDefault(Messages.ARENA_SPECTATE_DENIED_SELECTOR, "{prefix}&c抱歉，你现在不能观赛。右键来加入游戏！")
        addDefault(
            Messages.ARENA_JOIN_DENIED_NO_PROXY,
            "&c抱歉，你必须通过 BedWarsProxy 来加入游戏。 \n&e如果你想设置游戏，你可以给予你自己 bw.setup 权限来直接进入服务器！"
        )
        addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_NAME, "&8传送")
        addDefault(Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_NAME, "{vPrefix}{player}")
        addDefault(
            Messages.ARENA_SPECTATOR_TELEPORTER_GUI_HEAD_LORE,
            listOf("&7生命值：&f{health}%", "&7饱食度：&f{food}", "", "&7左键传送")
        )
        addDefault(Messages.ARENA_SPECTATOR_LEAVE_ITEM_NAME, "&c&l回到大厅")
        addDefault(Messages.ARENA_SPECTATOR_LEAVE_ITEM_LORE, listOf("&7右键离开起床战争大厅！"))
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_TITLE, "&a正在旁观&7{player}")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_ENTER_SUBTITLE, "&c潜行以退出！")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_TITLE, "&e退出旁观者模式")
        addDefault(Messages.ARENA_SPECTATOR_FIRST_PERSON_LEAVE_SUBTITLE, "")
        addDefault(Messages.ARENA_LEAVE_PARTY_DISBANDED, "{prefix}&c由于队长离开了，队伍解散！")
        addDefault(Messages.GENERATOR_HOLOGRAM_TIER, "&e等级&c{tier}")
        addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND, "&b&l钻石")
        addDefault(Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD, "&a&l绿宝石")
        addDefault(Messages.GENERATOR_HOLOGRAM_TIMER, "&c{seconds}&e 秒后生成")
        addDefault(Messages.GENERATOR_UPGRADE_CHAT_ANNOUNCEMENT, "{prefix}{generatorType}资源点&e升级到&c{tier}级。")
        addDefault(Messages.FORMATTING_CHAT_LOBBY, "{level}{vPrefix}&7{player}{vSuffix}：{message}")
        addDefault(Messages.FORMATTING_CHAT_WAITING, "{level}{vPrefix}&7{player}{vSuffix}：{message}")
        addDefault(
            Messages.FORMATTING_CHAT_SHOUT,
            "{level}{vPrefix}&6[公屏] {team} &7{player}&f{vSuffix}：{message}"
        )
        addDefault(Messages.FORMATTING_CHAT_TEAM, "{level}{vPrefix}&f{team}&7 {player}{vSuffix} {message}")
        addDefault(Messages.FORMATTING_CHAT_SPECTATOR, "{level}{vPrefix}&7[旁观者] {player}{vSuffix}：{message}")
        addDefault(Messages.FORMATTING_SCOREBOARD_HEALTH, listOf("&c❤", "&a生命值"))

        addDefault(Messages.FORMATTING_SCOREBOARD_DATE, "yy/MM/dd")
        addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_GENERIC, "{TeamColor}{TeamLetter}&f {TeamName}：{TeamStatus}")
        addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ELIMINATED, "&c&l✘")
        addDefault(Messages.FORMATTING_SCOREBOARD_BED_DESTROYED, "&a{remainingPlayers}")
        addDefault(Messages.FORMATTING_SCOREBOARD_TEAM_ALIVE, "&a&l✓")
        addDefault(Messages.FORMATTING_SCOREBOARD_NEXEVENT_TIMER, "mm:ss")
        addDefault(Messages.FORMATTING_SCOREBOARD_YOUR_TEAM, "&7 你")
        addDefault(Messages.FORMATTING_ACTION_BAR_TRACKING, "&f正在追踪：{team} &f- 距离：{distance}m")
        addDefault(Messages.FORMATTING_TEAM_WINNER_FORMAT, "      {TeamColor}{TeamName} &7- {members}")
        addDefault(Messages.FORMATTING_SOLO_WINNER_FORMAT, "                 {TeamColor}{TeamName} &7- {members}")
        addDefault(Messages.FORMATTING_GENERATOR_TIER1, "I")
        addDefault(Messages.FORMATTING_GENERATOR_TIER2, "II")
        addDefault(Messages.FORMATTING_GENERATOR_TIER3, "III")
        addDefault(Messages.FORMATTING_DESPAWNABLE_UTILITY_NPC_HEALTH, "▮ ")
        addDefault(Messages.FORMATTING_STATS_DATE_FORMAT, "yyyy/MM/dd HH:mm")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_TEAM, "{TeamColor}[{TeamName}]")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SHOUT, "&6[公屏]")
        addDefault(Messages.FORMAT_PAPI_PLAYER_TEAM_SPECTATOR, "&7[旁观者]")
        addDefault(Messages.MEANING_FULL, "已满")
        addDefault(Messages.MEANING_SHOUT, "公屏")
        addDefault(Messages.MEANING_NOBODY, "无玩家")
        addDefault(Messages.MEANING_NEVER, "从不")
        addDefault(Messages.MEANING_IRON_SINGULAR, "铁锭")
        addDefault(Messages.MEANING_IRON_PLURAL, "铁锭")
        addDefault(Messages.MEANING_GOLD_SINGULAR, "金锭")
        addDefault(Messages.MEANING_GOLD_PLURAL, "金锭")
        addDefault(Messages.MEANING_EMERALD_SINGULAR, "绿宝石")
        addDefault(Messages.MEANING_EMERALD_PLURAL, "绿宝石")
        addDefault(Messages.MEANING_DIAMOND_SINGULAR, "钻石")
        addDefault(Messages.MEANING_DIAMOND_PLURAL, "钻石")
        addDefault(Messages.MEANING_VAULT_SINGULAR, "$")
        addDefault(Messages.MEANING_VAULT_PLURAL, "$")
        addDefault(Messages.INTERACT_CANNOT_PLACE_BLOCK, "{prefix}&c你不能在这里放置方块！")
        addDefault(Messages.INTERACT_CANNOT_BREAK_BLOCK, "{prefix}&c你只能破坏由玩家放置的方块！")
        addDefault(Messages.INTERACT_CANNOT_BREAK_OWN_BED, "&c你不能破坏自己的床！")
        addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT,
            "\n&f&l床被破坏 > {TeamColor}{TeamName}的床&7被{PlayerColor}{PlayerName}&7破坏了！\n"
        )
        addDefault(Messages.INTERACT_BED_DESTROY_TITLE_ANNOUNCEMENT, "&c床被破坏！")
        addDefault(Messages.INTERACT_BED_DESTROY_SUBTITLE_ANNOUNCEMENT, "&f你不能再重生！")
        addDefault(
            Messages.INTERACT_BED_DESTROY_CHAT_ANNOUNCEMENT_TO_VICTIM,
            "&f&l床被破坏 > &7你的床被{PlayerColor}{PlayerName}&7破坏了！\n"
        )
        addDefault(
            Messages.INTERACT_CHEST_CANT_OPEN_TEAM_ELIMINATED,
            "&c此队伍还未被团灭，因此你不能打开该团队箱子！"
        )
        addDefault(Messages.INTERACT_INVISIBILITY_REMOVED_DAMGE_TAKEN, "&c你因受到伤害而被迫退出隐身！")
        addDefault(Messages.PLAYER_DIE_VOID_FALL_REGULAR_KILL, "{PlayerColor}{PlayerName}&7掉进了虚空。")
        addDefault(Messages.PLAYER_DIE_VOID_FALL_FINAL_KILL, "{PlayerColor}{PlayerName}&7掉进了虚空。 &b&l最终击杀！")
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_REGULAR_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerName}&7丢进了虚空。"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_IN_VOID_FINAL_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerName}&7丢进了虚空。 &b&l最终击杀！"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_REGULAR,
            "{PlayerColor}{PlayerName}&7在与{KillerColor}{KillerName}&7战斗时断开连接。"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_LOG_OUT_FINAL,
            "{PlayerColor}{PlayerName}&7在与{KillerColor}{KillerName}&7战斗时断开连接。 &b&l最终击杀！"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_REGULAR_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerName}&7推下了悬崖。"
        )
        addDefault(
            Messages.PLAYER_DIE_KNOCKED_BY_FINAL_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerName}&7推下了悬崖。 &b&l最终击杀！"
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_REGULAR_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerName}&7炸死了。"
        )
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITH_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerName}&7炸死了。 &b&l最终击杀！"
        )
        addDefault(Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_REGULAR, "{PlayerColor}{PlayerName}&7爆炸了。")
        addDefault(
            Messages.PLAYER_DIE_EXPLOSION_WITHOUT_SOURCE_FINAL_KILL,
            "{PlayerColor}{PlayerName}&7爆炸了。 &b&l最终击杀！"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_REGULAR_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerName}&7击杀。"
        )
        addDefault(
            Messages.PLAYER_DIE_PVP_FINAL_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerName}&7击杀。 &b&l最终击杀！"
        )
        addDefault(Messages.PLAYER_DIE_UNKNOWN_REASON_REGULAR, "{PlayerColor}{PlayerName}&7死了。")
        addDefault(Messages.PLAYER_DIE_UNKNOWN_REASON_FINAL_KILL, "{PlayerColor}{PlayerName}&7死了。 &b&l最终击杀！")
        addDefault(
            Messages.PLAYER_DIE_SHOOT_REGULAR,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerName}&7射死了！"
        )
        addDefault(
            Messages.PLAYER_DIE_SHOOT_FINAL_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerName}&7射死了！ &b&l最终击杀！"
        )
        addDefault(
            Messages.PLAYER_DIE_DEBUG_REGULAR,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerTeamName}&7的蠹虫杀死了！"
        )
        addDefault(
            Messages.PLAYER_DIE_DEBUG_FINAL_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerTeamName}&7的蠹虫杀死了！ &b&l最终击杀！"
        )
        addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_REGULAR,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerTeamName}&7的铁傀儡杀死了！"
        )
        addDefault(
            Messages.PLAYER_DIE_IRON_GOLEM_FINAL_KILL,
            "{PlayerColor}{PlayerName}&7被{KillerColor}{KillerTeamName}&7的铁傀儡杀死了！ &b&l最终击杀！"
        )
        addDefault(Messages.PLAYER_DIE_REWARD_DIAMOND, "{prefix}&b+{amount}{meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_EMERALD, "{prefix}&a+{amount}{meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_IRON, "{prefix}&f+{amount}{meaning}")
        addDefault(Messages.PLAYER_DIE_REWARD_GOLD, "{prefix}&6+{amount}{meaning}")
        addDefault(Messages.PLAYER_DIE_RESPAWN_TITLE, "&c你死了！")
        addDefault(Messages.PLAYER_DIE_RESPAWN_SUBTITLE, "&e你将在 &a{time} &e秒后重生！")
        addDefault(Messages.PLAYER_DIE_RESPAWN_CHAT, "{prefix}&e你将在 &a{time} &e秒后重生！")
        addDefault(Messages.PLAYER_DIE_RESPAWNED_TITLE, "&a已重生！")
        addDefault(Messages.PLAYER_DIE_ELIMINATED_CHAT, "{prefix}&c你已被淘汰！")
        addDefault(Messages.PLAYER_HIT_BOW, "{prefix}{TeamColor}{PlayerName}&7还有 &e{amount} &c生命值！")
        addDefault(Messages.GAME_END_GAME_OVER_PLAYER_TITLE, "&c&l游戏结束！")
        addDefault(Messages.GAME_END_VICTORY_PLAYER_TITLE, "&6&l胜利！")
        addDefault(Messages.GAME_END_TEAM_WON_CHAT, "{prefix}{TeamColor}{TeamName}&a赢得了这场游戏！")
        addDefault(
            Messages.GAME_END_TOP_PLAYER_CHAT, listOf(
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "&f                                   &l起床战争", "", "{winnerFormat}", "", "",
                "&6                      &6⭐ &l击杀第一名 &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&e                        &l击杀第二名 &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}",
                "&c                        &l击杀第三名 &7- {topTeamColor}{topPlayerDisplayName} &7- &l{topValue}", "",
                "&a▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            )
        )
        addDefault(Messages.BED_HOLOGRAM_DEFEND, "&c&l保护你的床！")
        addDefault(Messages.BED_HOLOGRAM_DESTROYED, "&c&l你的床被破坏了！")
        addDefault(Messages.NPC_NAME_TEAM_UPGRADES, "&b队伍升级,&e&l右键点击")
        addDefault(Messages.NPC_NAME_SOLO_UPGRADES, "&b单挑升级,&e&l右键点击")
        addDefault(Messages.NPC_NAME_TEAM_SHOP, "&b队伍商店,&e&l右键点击")
        addDefault(Messages.NPC_NAME_SOLO_SHOP, "&b单挑商店,&e&l右键点击")
        addDefault(Messages.TEAM_ELIMINATED_CHAT, "\n&f&l队伍团灭 > {TeamColor}{TeamName}&c已被团灭！\n")
        addDefault(Messages.NEXT_EVENT_BEDS_DESTROY, "&c床被破坏")
        addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_II, "&f钻石II级")
        addDefault(Messages.NEXT_EVENT_DIAMOND_UPGRADE_III, "&f钻石III级")
        addDefault(Messages.NEXT_EVENT_DRAGON_SPAWN, "&f绝杀模式")
        addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_II, "&f绿宝石II级")
        addDefault(Messages.NEXT_EVENT_EMERALD_UPGRADE_III, "&f绿宝石III级")
        addDefault(Messages.NEXT_EVENT_GAME_END, "&4游戏结束！")
        addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_BEDS_DESTROYED, "&c床被破坏！")
        addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_BEDS_DESTROYED, "&f所有床已被破坏！")
        addDefault(Messages.NEXT_EVENT_CHAT_ANNOUNCE_BEDS_DESTROYED, "&c&l所有床已被破坏！")
        addDefault(Messages.NEXT_EVENT_TITLE_ANNOUNCE_SUDDEN_DEATH, "&c绝杀模式")
        addDefault(Messages.NEXT_EVENT_SUBTITLE_ANNOUNCE_SUDDEN_DEATH, "")
        addDefault(
            Messages.NEXT_EVENT_CHAT_ANNOUNCE_SUDDEN_DEATH,
            "&c绝杀模式：&6&b{TeamDragons} {TeamColor}{TeamName}的龙！"
        )
        addDefault(Messages.XP_REWARD_PER_MINUTE, "{prefix}&6+{xp}起床战争经验(游戏时间)")
        addDefault(Messages.XP_REWARD_WIN, "{prefix}&6+{xp}起床战争经验(游戏胜利)")
        addDefault(Messages.XP_REWARD_PER_TEAMMATE, "{prefix}&6+{xp}起床战争经验(团队协作)")
        addDefault(Messages.XP_REWARD_BED_DESTROY, "{prefix}&6+{xp}起床战争经验(破坏床)")
        addDefault(Messages.XP_REWARD_REGULAR_KILL, "{prefix}&6+{xp}起床战争经验(击杀)")
        addDefault(Messages.XP_REWARD_FINAL_KILL, "{prefix}&6+{xp}起床战争经验(最终击杀)")

        addDefault(Messages.MONEY_REWARD_PER_MINUTE, "{prefix}&6+{money}金币(游戏时间)")
        addDefault(Messages.MONEY_REWARD_WIN, "{prefix}&6+{money}金币(游戏胜利)")
        addDefault(Messages.MONEY_REWARD_PER_TEAMMATE, "{prefix}&6+{money}金币(团队协作)")
        addDefault(Messages.MONEY_REWARD_BED_DESTROYED, "{prefix}&6+{money}金币(破坏床)")
        addDefault(Messages.MONEY_REWARD_FINAL_KILL, "{prefix}&6+{money}金币(最终击杀)")
        addDefault(Messages.MONEY_REWARD_REGULAR_KILL, "{prefix}&6+{money}金币(击杀)")

        /* Lobby Command Items */
        addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "stats"), "&e战绩")
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&f右键显示你的战绩！")
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "arena-selector"),
            "&e选择游戏"
        )
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "arena-selector"),
            listOf("&f右键选择游戏！")
        )
        addDefault(Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_NAME.replace("%path%", "leave"), "&e回到主大厅")
        addDefault(
            Messages.GENERAL_CONFIGURATION_LOBBY_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&f右键离开起床战争！")
        )
        /* Pre Game Command Items */
        addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "stats"), "&e战绩")
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "stats"),
            listOf("&f右键显示你的战绩！")
        )
        addDefault(Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_NAME.replace("%path%", "leave"), "&e返回大厅")
        addDefault(
            Messages.GENERAL_CONFIGURATION_WAITING_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&f右键离开游戏！")
        )
        /* Spectator Command Items */
        addDefault(Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "teleporter"), "&e传送")
        addDefault(Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_NAME.replace("%path%", "leave"), "&e回到大厅")
        addDefault(
            Messages.GENERAL_CONFIGURATION_SPECTATOR_ITEMS_LORE.replace("%path%", "leave"),
            listOf("&f右键离开游戏！")
        )

        /* save default items messages for stats gui */
        addDefault(Messages.PLAYER_STATS_GUI_INV_NAME, "&8{player}的战绩")
        addDefaultStatsMsg("wins", "&6胜场数", "&f{wins}")
        addDefaultStatsMsg("losses", "&6失败场数", "&f{losses}")
        addDefaultStatsMsg("kills", "&6击杀数", "&f{kills}")
        addDefaultStatsMsg("deaths", "&6死亡数", "&f{deaths}")
        addDefaultStatsMsg("final-kills", "&6最终击杀数", "&f{finalKills}")
        addDefaultStatsMsg("final-deaths", "&6最终死亡数", "&f{finalDeaths}")
        addDefaultStatsMsg("beds-destroyed", "&6破坏床数", "&f{bedsDestroyed}")
        addDefaultStatsMsg("first-play", "&6首次游玩", "&f{firstPlay}")
        addDefaultStatsMsg("last-play", "&6上次游玩", "&f{lastPlay}")
        addDefaultStatsMsg("games-played", "&6总游玩场数", "&f{gamesPlayed}")

        // Start of Sidebar
        addDefault(
            Messages.SCOREBOARD_LOBBY, listOf(
                "&6&l起床战争,&4&l起&6&l床战争,&6&l起&4&l床&6&l战争,&6&l起床&4&l战&6&l争,&6&l起床战&4&l争,&6&l起床战争",
                "&f等级：{level}",
                "",
                "&f进度：&a{currentXp}&7/&b{requiredXp}",
                "{progress}",
                "",
                "&7{player}",
                "",
                "&f金币：&a{money}",
                "",
                "&f总胜场：&a{wins}",
                "&f总击杀：&a{kills}",
                "", "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING, listOf(
                "&f&l起床战争",
                "&7{date} &8{server}",
                "",
                "&f地图：&a{map}",
                "",
                "&f玩家数：&a{on}/{max}",
                "",
                "&f等待中,&f等待中.,&f等待中..,&f等待中...",
                "",
                "&f模式：&a{group}",
                "&f版本：&7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_WAITING_SPEC, listOf(
                "&f&l起床战争",
                "&7{date} &8{server}",
                "&o&7Spectating",
                "&f地图：&a{map}",
                "",
                "&f玩家数：&a{on}/{max}",
                "",
                "&f等待中,&f等待中.,&f等待中..,&f等待中...",
                "",
                "&f模式：&a{group}",
                "&f版本：&7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING, listOf(
                "&f&l起床战争",
                "&7{date} &8{server}",
                "",
                "&f地图：&a{map}",
                "",
                "&f玩家数：&a{on}/{max}",
                "",
                "&f &a{time} &f秒后开始",
                "",
                "&f模式：&a{group}",
                "&f模式：&7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_STARTING_SPEC, listOf(
                "&f&l起床战争",
                "&7{date} &8{server}",
                "&o&7Spectating",
                "&f地图：&a{map}",
                "",
                "&f玩家数：&a{on}/{max}",
                "",
                "&f &a{time} &f秒后开始",
                "",
                "&f模式：&a{group}",
                "&f模式：&7{version}",
                "",
                "&e{serverIp}"
            )
        )
        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING, listOf(
                "&e&l起床战争",
                "&7{date}",
                "",
                "&f{nextEvent} - &a{time}",
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
                "&e&l起床战争",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} - &a{time}",
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
                "&e&l起床战争",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} - &a{time}",
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
                "&e&l起床战争",
                "&7{date}",
                "",
                "&f{nextEvent} - &a{time}",
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
                "&e&l起床战争",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} - &a{time}",
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
                "&e&l起床战争",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} - &a{time}",
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
                "&e&l起床战争",
                "&7{date}",
                "",
                "&f{nextEvent} - &a{time}",
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
                "&f击杀：&a{kills}",
                "&f最终击杀：&a{finalKills}",
                "&f破坏床：&a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "3v3v3v3"),
            listOf(
                "&e&l起床战争",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} - &a{time}",
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
                "&e&l起床战争",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} - &a{time}",
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
                "&f击杀：&a{kills}",
                "&f最终击杀：&a{finalKills}",
                "&f破坏床：&a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING.replaceFirst("Default".toRegex(), "4v4v4v4"), listOf(
                "&e&l起床战争",
                "&7{date}",
                "",
                "&f{nextEvent} - &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&f击杀：&a{kills}",
                "&f最终击杀：&a{finalKills}",
                "&f破坏床：&a{beds}",
                "",
                "&e{serverIp}"
            )
        )

        addDefault(
            Messages.SCOREBOARD_DEFAULT_PLAYING_SPEC.replaceFirst("Default".toRegex(), "4v4v4v4"),
            listOf(
                "&e&l起床战争",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} - &a{time}",
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
                "&e&l起床战争",
                "&7{date}",
                "&o&7Spectating {spectatorTarget}",
                "&f{nextEvent} - &a{time}",
                "",
                "{team}",
                "{team}",
                "{team}",
                "{team}",
                "",
                "&f击杀：&a{kills}",
                "&f最终击杀：&a{finalKills}",
                "&f破坏床：&a{beds}",
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
        addDefault(Messages.SHOP_INDEX_NAME, "&8快速购买")
        addDefault(Messages.SHOP_QUICK_ADD_NAME, "&8添加到快速购买...")
        addDefault(
            Messages.SHOP_INSUFFICIENT_MONEY,
            "{prefix}&c你没有足够的{currency}！ 还需要 {amount} 个{currency}！"
        )
        addDefault(Messages.SHOP_NEW_PURCHASE, "{prefix}&a购买&6{item}")
        addDefault(Messages.SHOP_ALREADY_BOUGHT, "{prefix}&c你已经购买过了！")
        addDefault(Messages.SHOP_UTILITY_NPC_SILVERFISH_NAME, "{TeamColor}&l{TeamName} &r{TeamColor}蠹虫")
        addDefault(Messages.SHOP_UTILITY_NPC_IRON_GOLEM_NAME, "{TeamColor}{despawn}秒 &8[ {TeamColor}{health}&8]")
        addDefault(Messages.SHOP_SEPARATOR_NAME, "&8⇧ 分类")
        addDefault(Messages.SHOP_SEPARATOR_LORE, listOf("&8⇩ 物品"))
        addDefault(Messages.SHOP_QUICK_BUY_NAME, "&b快速购买")
        addDefault(Messages.SHOP_QUICK_BUY_LORE, ArrayList<Any?>())
        addDefault(Messages.SHOP_QUICK_EMPTY_NAME, "&c空槽位！")
        addDefault(
            Messages.SHOP_QUICK_EMPTY_LORE,
            listOf("&7这是快速购买槽位！", "&bShift+点击 &7商店中的物品", "&7来加到这里。")
        )
        addDefault(Messages.SHOP_CAN_BUY_COLOR, "&a")
        addDefault(Messages.SHOP_CANT_BUY_COLOR, "&c")
        addDefault(Messages.SHOP_LORE_STATUS_CAN_BUY, "&e点击购买！")
        addDefault(Messages.SHOP_LORE_STATUS_CANT_AFFORD, "&c你没有足够的{currency}！")
        addDefault(Messages.SHOP_LORE_STATUS_MAXED, "&a满级！")
        addDefault(Messages.SHOP_LORE_STATUS_ARMOR, "&a已装备！")
        addDefault(Messages.SHOP_LORE_QUICK_ADD, "&bShift+点击 来添加快速购买")
        addDefault(Messages.SHOP_LORE_QUICK_REMOVE, "&bShift+点击 来从快速购买中移除！")


        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "&8方块",
            "&a方块",
            listOf("&e点击查看！")
        )

        addContentMessages(
            this, "wool", ConfigPath.SHOP_PATH_CATEGORY_BLOCKS, "{color}羊毛", listOf(
                "&7花费：&f{cost} {currency}", "", "&7很好的搭路工具",
                "&7购买后将变为你队伍的颜色", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "clay",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}硬化粘土",
            listOf(
                "&7花费：{cost} {currency}",
                "",
                "&7保护床的基本方块。",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "glass",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}防爆玻璃",
            listOf("&7花费：{cost} {currency}", "", "&7免疫爆炸。", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "stone",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}末地石",
            listOf(
                "&7花费：{cost} {currency}",
                "",
                "&7保护床的坚实方块。",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "ladder",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}梯子",
            listOf("&7花费：{cost} {currency}", "", "&7爬树时很有用。", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "obsidian",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}黑曜石",
            listOf(
                "&7花费：{cost} {currency}",
                "",
                "&7对床的终极保护。",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "wood",
            ConfigPath.SHOP_PATH_CATEGORY_BLOCKS,
            "{color}原木",
            listOf(
                "&7花费：{cost} {currency}",
                "",
                "&7保护床的坚实方块。",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "&8战斗",
            "&a战斗",
            listOf("&e点击查看！")
        )

        addContentMessages(
            this,
            "stone-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}石剑",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "iron-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}铁剑",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "diamond-sword",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}钻石剑",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "stick",
            ConfigPath.SHOP_PATH_CATEGORY_MELEE,
            "{color}木棍(击退 I)",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_ARMOR,
            "&8盔甲",
            "&a盔甲",
            listOf("&e点击查看！")
        )

        addContentMessages(
            this, "chainmail", ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "{color}锁链护甲", listOf(
                "&7花费：{cost} {currency}",
                "", "&7锁链靴子和护腿", "&7死亡不掉落", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "iron-armor", ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "{color}铁护甲", listOf(
                "&7花费：{cost} {currency}",
                "", "&7铁靴子和护腿", "&7死亡不掉落", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "diamond-armor", ConfigPath.SHOP_PATH_CATEGORY_ARMOR, "{color}钻石护甲", listOf(
                "&7花费：{cost} {currency}",
                "", "&7钻石靴子和护腿", "&7死亡不掉落", "", "{quick_buy}", "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_TOOLS,
            "&8工具",
            "&a工具",
            listOf("&e点击查看！")
        )

        addContentMessages(
            this, "shears", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}剪刀", listOf(
                "&7花费：{cost} {currency}",
                "", "&7拆羊毛的利器", "&7死亡不掉落。", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "pickaxe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}镐{tier}", listOf(
                "&7花费：{cost} {currency}",
                "&7等级：&e{tier}",
                "",
                "&7该工具可升级。",
                "&7每次死亡都会降一级。",
                "",
                "&7降到最低级为止",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )
        addContentMessages(
            this, "axe", ConfigPath.SHOP_PATH_CATEGORY_TOOLS, "{color}斧{tier}", listOf(
                "&7花费：{cost} {currency}",
                "&7等级：&e{tier}",
                "",
                "&7该工具可升级。",
                "&7每次死亡都会降一级。",
                "",
                "&7降到最低级为止",
                "",
                "{quick_buy}",
                "{buy_status}"
            )
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "&8远程武器",
            "&a远程武器",
            listOf("&e点击查看！")
        )

        addContentMessages(
            this,
            "arrow",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}箭",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow1",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}弓",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow2",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}弓（力量 I）",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "bow3",
            ConfigPath.SHOP_PATH_CATEGORY_RANGED,
            "{color}弓（力量 I, 冲击 I）",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "&8药水",
            "&a药水",
            listOf("&e点击查看！")
        )

        addContentMessages(
            this,
            "speed-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}速度 II 药水（45 秒）",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "jump-potion",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}跳跃提升 V 药水（45 秒）",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this,
            "invisibility",
            ConfigPath.SHOP_PATH_CATEGORY_POTIONS,
            "{color}隐身药水（30 秒）",
            listOf("&7花费：{cost} {currency}", "", "{quick_buy}", "{buy_status}")
        )

        addCategoryMessages(
            this,
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "&8实用工具",
            "&a实用工具",
            listOf("&e点击查看！")
        )

        addContentMessages(
            this,
            "golden-apple",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}金苹果",
            listOf("&7花费：{cost} {currency}", "", "&7均衡治疗", "", "{quick_buy}", "{buy_status}")
        )
        addContentMessages(
            this, "bedbug", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}蠹虫", listOf(
                "&7花费：{cost} {currency}", "",
                "&7在雪球落下的地方召唤蠹虫", "&7来干扰敌人", "&7持续15秒。", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "dream-defender", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}梦境守卫者", listOf(
                "&7花费：{cost} {currency}", "",
                "&7召唤铁傀儡来保护基地", "&7持续 4 分钟", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "fireball", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}火球", listOf(
                "&7花费：{cost} {currency}", "", "&7右键发射！",
                "&7把在窄桥上的敌人打下去！", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "tnt", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}TNT", listOf(
                "&7花费：{cost} {currency}", "",
                "&7立即点燃, 适合炸点东西", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "ender-pearl", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}末影珍珠", listOf(
                "&7花费：{cost} {currency}", "",
                "&7偷家最快的方式", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "water-bucket", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}水桶", listOf(
                "&7花费：{cost} {currency}", "",
                "&7减缓敌人的速度", "&7也可以防止 TNT 破坏方块", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "bridge-egg", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}搭桥蛋", listOf(
                "&7花费：{cost} {currency}", "",
                "&7丢出后在其轨迹形成一座桥", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "magic-milk", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}魔法牛奶", listOf(
                "&7花费：{cost} {currency}", "",
                "&7喝下后 60 秒内不会触发陷阱", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this, "sponge", ConfigPath.SHOP_PATH_CATEGORY_UTILITY, "{color}海绵", listOf(
                "&7花费：{cost} {currency}", "",
                "&7用来吸水不错", "", "{quick_buy}", "{buy_status}"
            )
        )
        addContentMessages(
            this,
            "Compact Pop-up Tower",
            ConfigPath.SHOP_PATH_CATEGORY_UTILITY,
            "{color}袖珍弹出塔",
            listOf(
                "&花费: {cost} {currency}", "",
                "&7放置一个袖珍弹出塔", "&7塔防！", "", "{quick_buy}", "{buy_status}"
            )
        )

        addDefault(Messages.MEANING_NO_TRAP, "无陷阱！")
        addDefault(Messages.FORMAT_SPECTATOR_TARGET, "{targetTeamColor}{targetDisplayName}")
        addDefault(Messages.FORMAT_UPGRADE_TRAP_COST, "&7花费：{currencyColor}{cost} {currency}")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_CAN_AFFORD, "&e")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_CANT_AFFORD, "&c")
        addDefault(Messages.FORMAT_UPGRADE_COLOR_UNLOCKED, "&a")
        addDefault(Messages.FORMAT_UPGRADE_TIER_LOCKED, "&7")
        addDefault(Messages.FORMAT_UPGRADE_TIER_UNLOCKED, "&a")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_CLICK_TO_BUY, "{color}点击购买！")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_INSUFFICIENT_MONEY, "{color}你没有足够的{currency}！")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_LOCKED, "&c已锁定")
        addDefault(Messages.UPGRADES_LORE_REPLACEMENT_UNLOCKED, "{color}已解锁")
        addDefault(Messages.UPGRADES_UPGRADE_BOUGHT_CHAT, "&a{player}购买了&6{upgradeName}")
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-1"),
            "{color}铁锭熔炉"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "forge"),
            listOf(
                "&7增加岛上资源的生成速度", "", "{tier_1_color}等级 1：+50% 生成速率, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}等级 2：+100% 生成速率，&b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}等级 3：生成绿宝石，&b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}等级 4：+200% 生成速率，&b{tier_4_cost} {tier_4_currency}", ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-2"),
            "{color}金锭熔炉"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-3"),
            "{color}绿宝石熔炉"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "forge").replace("{tier}", "tier-4"),
            "{color}无尽熔炉"
        )
        addDefault(Messages.UPGRADES_CATEGORY_ITEM_NAME_PATH + "traps", "&e购买陷阱")
        addDefault(
            Messages.UPGRADES_CATEGORY_ITEM_LORE_PATH + "traps",
            listOf("&7已购买的陷阱将从右边进入队列", "", "&e点击查看！")
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "swords").replace("{tier}", "tier-1"),
            "{color}锋利附魔"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "swords"),
            listOf(
                "&7队伍的所有剑和斧获得锋利 I！",
                "",
                "{tier_1_color}花费：&b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-1"),
            "{color}护甲强化 I"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "armor"),
            listOf(
                "&7队伍的所有护甲获得保护附魔！", "", "{tier_1_color}等级 1：保护 I， &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}等级 2：保护 II，&b{tier_2_cost} {tier_2_currency}",
                "{tier_3_color}等级 3：保护 III，&b{tier_3_cost} {tier_3_currency}",
                "{tier_4_color}等级 4：保护 IV，&b{tier_4_cost} {tier_4_currency}", ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-2"),
            "{color}护甲强化 II"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-3"),
            "{color}护甲强化 III"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "armor").replace("{tier}", "tier-4"),
            "{color}护甲强化 IV"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-1"),
            "{color}疯狂矿工 I"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "miner"),
            listOf(
                "&7队伍获得急迫效果。", "", "{tier_1_color}等级 1：急迫 I, &b{tier_1_cost} {tier_1_currency}",
                "{tier_2_color}等级 2：急迫 II, &b{tier_2_cost} {tier_2_currency}", ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "miner").replace("{tier}", "tier-2"),
            "{color}疯狂矿工 II"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "heal-pool").replace("{tier}", "tier-1"),
            "{color}治愈池"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "heal-pool"),
            listOf(
                "&7在基地附近生成治愈池！",
                "",
                "{tier_1_color}花费：&b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_NAME.replace("{name}", "dragon").replace("{tier}", "tier-1"),
            "{color}末影龙升级"
        )
        addDefault(
            Messages.UPGRADES_UPGRADE_TIER_ITEM_LORE.replace("{name}", "dragon").replace("{tier}", "tier-1"),
            listOf(
                "&7在死斗时你的队伍会有 2 条而不是 1 条龙！",
                "",
                "{tier_1_color}花费：&b{tier_1_cost} {tier_1_currency}",
                ""
            )
        )
        addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "glass", "&8⬆&7可购买")
        addDefault(Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "glass", listOf("&8⬇&7陷阱队列"))
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "first", "{color}陷阱 #1：{name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "first",
            listOf("&7第一个进入你基地的敌人将触发该陷阱！")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "first",
            listOf(
                "",
                "&7下个购买的陷阱将在此进入队列",
                "&7陷阱的花费将会随着队列长度增加",
                "",
                "&7下个陷阱花费：&b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "second", "{color}陷阱 #2：{name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "second",
            listOf("&7第二个进入你基地的敌人将触发该陷阱！")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "second",
            listOf(
                "",
                "&7下个购买的陷阱将在此进入队列",
                "&7陷阱的花费将会随着队列长度增加",
                "",
                "&7下个陷阱花费：&b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_TRAP_SLOT_ITEM_NAME_PATH + "third", "{color}陷阱 #3：{name}")
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE1_PATH + "third",
            listOf("&7第三个进入你基地的敌人将触发该陷阱！")
        )
        addDefault(
            Messages.UPGRADES_TRAP_SLOT_ITEM_LORE2_PATH + "third",
            listOf(
                "",
                "&7下个购买的陷阱将在此进入队列",
                "&7陷阱的花费将会随着队列长度增加",
                "",
                "&7下个陷阱花费：&b{cost} {currency}"
            )
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "1", "{color}这是个陷阱！")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "1",
            listOf("&7造成5秒失明和缓慢", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "2", "{color}反击陷阱")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "2",
            listOf("&7给予基地附近的队友 15 秒速度 I。", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "3", "{color}报警陷阱")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "3",
            listOf("&7显示隐身的敌人及其名字和队伍。", "")
        )
        addDefault(Messages.UPGRADES_BASE_TRAP_ITEM_NAME_PATH + "4", "{color}挖掘疲劳陷阱")
        addDefault(
            Messages.UPGRADES_BASE_TRAP_ITEM_LORE_PATH + "4",
            listOf("&7造成 10 秒挖掘疲劳。", "")
        )
        addDefault(Messages.UPGRADES_SEPARATOR_ITEM_NAME_PATH + "back", "&a返回")
        addDefault(
            Messages.UPGRADES_SEPARATOR_ITEM_LORE_PATH + "back",
            listOf("&7回到升级和陷阱菜单")
        )
        addDefault(Messages.UPGRADES_CATEGORY_GUI_NAME_PATH + "traps", "&8将陷阱加入队列")
        addDefault(Messages.UPGRADES_TRAP_QUEUE_LIMIT, "&c陷阱队列已满！")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_MSG, "&c&l{trap}被触发了！")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_TITLE, "&c陷阱触发！")
        addDefault(Messages.UPGRADES_TRAP_DEFAULT_SUBTITLE, "&f你队伍的{trap}被触发了！")
        addDefault(
            Messages.UPGRADES_TRAP_CUSTOM_MSG + "3",
            "&c&l报警陷阱被{color}&l{team}的&7&l{player}&c&l触发了！"
        )
        addDefault(Messages.UPGRADES_TRAP_CUSTOM_TITLE + "3", "&c&l警报！！！")
        addDefault(Messages.UPGRADES_TRAP_CUSTOM_SUBTITLE + "3", "{color}{team}&f触发了陷阱！")
        save()
        setPrefix(m(Messages.PREFIX))
    }
}
