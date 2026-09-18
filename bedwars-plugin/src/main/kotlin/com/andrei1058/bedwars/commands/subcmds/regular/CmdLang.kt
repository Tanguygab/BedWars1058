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
package com.andrei1058.bedwars.commands.subcmds.regular

import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Language.Companion.sendLangMsg
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.commands.MainCommand
import com.andrei1058.bedwars.commands.subcmds.SubCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CmdLang(parent: MainCommand) : SubCommand(parent, "lang", priority = 18) {
    override val description = createDescription("Change your language.")

    override fun execute(args: Array<String>, sender: CommandSender): Boolean {
        if (sender !is Player) return false

        if (plugin.arenaManager.getArena(sender) != null) {
            sender.sendLangMsg(Messages.COMMAND_LANG_USAGE_DENIED)
            return true
        }

        if (args.isNotEmpty()) {
            if (!Language.isLanguageExist(args[0])) {
                sender.sendLangMsg(Messages.COMMAND_LANG_SELECTED_NOT_EXIST)
                return true
            }

            if (Language.setPlayerLanguage(sender.uniqueId, args[0])) {
                plugin.run(delay = 3) { sender.sendLangMsg(Messages.COMMAND_LANG_SELECTED_SUCCESSFULLY) }
                return true
            }
        }

        sender.sendLangMsg(Messages.COMMAND_LANG_LIST_HEADER)
        for (l in Language.languages) {
            sender.sendLangMsg(Messages.COMMAND_LANG_LIST_FORMAT, "iso" to l.iso, "{name}" to l.langName)
        }
        sender.sendLangMsg(Messages.COMMAND_LANG_USAGE)
        return true
    }

    override val tabComplete get() = Language.languages.map { it.iso }
}
