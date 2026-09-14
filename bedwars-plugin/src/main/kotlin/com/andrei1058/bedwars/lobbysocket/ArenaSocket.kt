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
package com.andrei1058.bedwars.lobbysocket

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.BedWars.Companion.debug
import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.configuration.ConfigPath
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import org.bukkit.Bukkit
import java.io.IOException
import java.io.PrintWriter
import java.net.Socket
import java.util.Scanner
import java.util.concurrent.ConcurrentHashMap

object ArenaSocket {
    var lobbies = mutableListOf<String>()
    private val sockets = ConcurrentHashMap<String, RemoteLobby>()
    private val serverName get() = BedWars.INSTANCE.mainConfig.getString(ConfigPath.GENERAL_CONFIGURATION_BUNGEE_OPTION_SERVER_ID)

    /**
     * Send arena data to the lobbies.
     */
    fun sendMessage(message: String?) {
        if (message.isNullOrEmpty()) return

        for (lobby in lobbies) {
            val l = lobby.split(":")
            if (l.size != 2) continue
            val port = l[1].toIntOrNull() ?: continue

            if (sockets.containsKey(lobby)) {
                sockets[lobby]!!.sendMessage(message)
                continue
            }
            try {
                val socket = Socket(l[0], port)
                val rl = RemoteLobby(socket, lobby)
                if (rl.out != null) {
                    sockets[lobby] = rl
                    rl.sendMessage(message)
                }
            } catch (_: IOException) {}
        }
    }

    /**
     * Format message before sending it to lobbies.
     */
    fun formatUpdateMessage(arena: IArena?): String {
        if (arena?.worldName == null) return ""
        return JsonObject().apply {
            addProperty("type", "UPDATE")
            addProperty("server_name", serverName)
            addProperty("arena_name", arena.name)
            addProperty("arena_identifier", arena.worldName)
            addProperty("arena_status", arena.status.toString().uppercase())
            addProperty("arena_current_players", arena.players.size)
            addProperty("arena_max_players", arena.maxPlayers)
            addProperty("arena_max_in_team", arena.maxInTeam)
            addProperty("arena_group", arena.group.uppercase())
            addProperty("spectate", arena.isAllowSpectate)
        }.toString()
    }

    /**
     * Close active sockets.
     */
    fun disable() {
        sockets.values.toList().forEach { it.disable() }
    }

    private class RemoteLobby(private val socket: Socket, private val lobby: String) {
        val out = try { PrintWriter(socket.getOutputStream(), true) } catch (_: IOException) { null }
        private val `in` = try { Scanner(socket.getInputStream()) } catch (_: IOException) { null }
        private var compute = true

        init {
            if (`in` != null && out != null) {
                debug("RemoteLobby created: $lobby $socket")
                BedWars.INSTANCE.run(async = true) {
                    while (compute) {
                        if (`in`.hasNext()) {
                            val msg = `in`.next()
                            debug(msg)
                            if (msg.isEmpty()) continue
                            val json = try {
                                JsonParser.parseString(msg).getAsJsonObject() ?: continue
                            } catch (_: JsonSyntaxException) {
                                BedWars.INSTANCE.logger.warning("Received bad data from: ${socket.inetAddress}")
                                continue
                            }
                            if (!json.has("type")) continue

                            when (json.get("type").asString.uppercase()) {
                                "PLD" -> LoadedUser(
                                    json.get("uuid").asString,
                                    json.get("arena_identifier").asString,
                                    json.get("lang_iso").asString,
                                    json.get("target").asString
                                )

                                "Q" -> {
                                    val p = Bukkit.getPlayer(json.get("name").asString)
                                    if (p == null || !p.isOnline) continue
                                    val a = BedWars.INSTANCE.arenaManager.getArena(p) ?: continue
                                    out.println(JsonObject().apply {
                                        addProperty("type", "Q")
                                        addProperty("name", p.name)
                                        addProperty("requester", json.get("requester").asString)
                                        addProperty("server_name", serverName)
                                        addProperty("arena_id", a.worldName)
                                    })
                                }
                            }
                        } else disable()
                    }
                }
            }
        }

        /**
         * Send a message to the given host with target port.
         * 
         * @return true if message was sent successfully.
         */
        fun sendMessage(message: String?): Boolean {
            if (!socket.isConnected || out == null || `in` == null || out.checkError()) {
                disable()
                return false
            }
            out.println(message)
            return true
        }

        fun disable() {
            compute = false
            debug("Disabling socket: $socket")
            sockets.remove(lobby)
            try {
                socket.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            `in`?.close()
            out?.close()
        }
    }
}
