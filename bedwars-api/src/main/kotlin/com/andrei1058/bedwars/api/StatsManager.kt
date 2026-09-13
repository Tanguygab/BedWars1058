package com.andrei1058.bedwars.api

import java.sql.Timestamp
import java.util.UUID

interface StatsManager {
    /**
     * Get player first play date.
     * You get data from the local cache.
     */
    fun getPlayerFirstPlay(player: UUID): Timestamp

    /**
     * Get player last play date.
     * You get data from the local cache.
     */
    fun getPlayerLastPlay(player: UUID): Timestamp

    /**
     * Get player total wins.
     * You get data from the local cache.
     */
    fun getPlayerWins(player: UUID): Int

    /**
     * Get player regular kills.
     * You get data from the local cache.
     */
    fun getPlayerKills(player: UUID): Int

    /**
     * Get player total kills.
     * Regular kills + final kills.
     * You get data from the local cache.
     */
    fun getPlayerTotalKills(player: UUID): Int

    /**
     * Get player total final kills.
     * You get data from the local cache.
     */
    fun getPlayerFinalKills(player: UUID): Int

    /**
     * Get player total looses.
     * You get data from the local cache.
     */
    fun getPlayerLoses(player: UUID): Int

    /**
     * Get player total deaths.
     * You get data from the local cache.
     */
    fun getPlayerDeaths(player: UUID): Int

    /**
     * Get player total final deaths.
     * You get data from the local cache.
     */
    fun getPlayerFinalDeaths(player: UUID): Int

    /**
     * Get player beds destroyed.
     * You get data from the local cache.
     */
    fun getPlayerBedsDestroyed(player: UUID): Int

    /**
     * Get player games played.
     * You get data from the local cache.
     */
    fun getPlayerGamesPlayed(player: UUID): Int
}