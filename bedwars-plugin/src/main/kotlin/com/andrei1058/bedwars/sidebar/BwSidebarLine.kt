package com.andrei1058.bedwars.sidebar

import com.andrei1058.spigot.sidebar.ScoredLine
import com.andrei1058.spigot.sidebar.SidebarLine

class BwSidebarLine(
    private val content: String,
    private val score: String
) : SidebarLine(), ScoredLine {
    override fun getLine() = content
    override fun getScore() = score
}
