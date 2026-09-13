package com.andrei1058.bedwars.arena.stats

import com.andrei1058.bedwars.api.arena.IArena
import com.andrei1058.bedwars.api.arena.stats.PlayerGameStats
import com.andrei1058.bedwars.api.language.Language
import org.bukkit.Bukkit

/**
 * This cannot be used for live tops.
 */
class StatisticsOrdered(
    private val arena: IArena,
    private val orderBy: String
) {
    private val ordered: List<PlayerGameStats>
    var boundsPolicy = BoundsPolicy.EMPTY

    init {
        if (!arena.statsHolder.hasStatistic(orderBy)) {
            throw RuntimeException("Invalid order by. Provided: $orderBy")
        }
        ordered = arena.statsHolder.getOrderedBy(orderBy)
    }

    fun newParser() = StringParser()

    companion object {
        private val placeholders = arrayOf("{topPlayerName}", "{topPlayerDisplayName}", "{topTeamColor}", "{topTeamName}", "{topValue}")
    }
    inner class StringParser {
        private var index = 0

        /**
         * @param string           string to be placeholder replaced.
         * @param emptyReplacement replace empty top position with this string.
         */
        fun parseString(string: String, lang: Language, emptyReplacement: String): String? {
            var string = string

            if (index >= ordered.size) {
                if (boundsPolicy == BoundsPolicy.SKIP) {
                    if (string.isBlank()) return string

                    val hasPlaceholders = placeholders.any { it in string } ||
                        arena.statsHolder.registered.any { "{topValue-$it}" in string }
                    return if (hasPlaceholders) null else string
                }

                string = string
                    .replace("{topPlayerName}", emptyReplacement)
                    .replace("{topPlayerDisplayName}", emptyReplacement)
                    .replace("{topTeamColor}", "")
                    .replace("{topTeamName}", "")
                    .replace("{topValue}", "{topValue-$orderBy}")

                for (registered in arena.statsHolder.registered) {
                    var displayValue = "null"
                    val provider = arena.statsHolder.getProvider(registered)
                    if (provider != null) {
                        displayValue = provider.getVoidReplacement(lang)
                    }
                    string = string.replace("{topValue-$registered}", displayValue)
                }

                return string
            }

            val stats = ordered[index]


            var increment = "{topPlayerName}" in string || "{topPlayerDisplayName}" in string

            val online = Bukkit.getPlayer(stats.player)
            var team = if (null == online) arena.getExTeam(stats.player) else arena.getTeam(online)
            if (null == team) {
                // if player online but eliminated
                team = arena.getExTeam(stats.player)
            }

            string = string
                .replace("{topPlayerName}", stats.username)
                .replace("{topPlayerDisplayName}", stats.displayPlayer)
                .replace("{topTeamColor}", team?.color?.chat?.toString() ?: "")
                .replace("{topTeamName}", team?.getDisplayName(lang) ?: "")
                .replace("{topValue}", "{topValue-$orderBy}")

            for (registered in arena.statsHolder.registered) {
                val statistic = stats.getStatistic(registered)

                if (!increment && "{topValue-$registered}" in string) {
                    increment = true
                }

                var displayValue = statistic?.getDisplayValue(lang)

                if (displayValue == null) {
                    val provider = arena.statsHolder.getProvider(registered)
                    displayValue = provider?.getVoidReplacement(lang) ?: "null"
                }

                string = string.replace("{topValue-$registered}", displayValue)
            }

            if (increment) ++index
            return string
        }

        fun resetIndex() {
            index = 0
        }
    }

    /**
     * What to do when iterating stats line and there are no more players to show.
     * This is used when there are more placeholders used than the actual player count.
     */
    enum class BoundsPolicy {
        /**
         * Skip line. Do not send it to receivers.
         */
        SKIP,

        /**
         * Send empty line to receivers.
         */
        EMPTY
    }
}
