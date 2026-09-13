package com.andrei1058.bedwars.arena.generators

import com.andrei1058.bedwars.BedWars
import com.andrei1058.bedwars.api.arena.generator.IGenHolo
import com.andrei1058.bedwars.api.language.Language
import com.andrei1058.bedwars.api.language.Messages
import com.andrei1058.bedwars.arena.generators.Generator.Companion.createArmorStand
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player

class HoloGram(generator: Generator, override var iso: String) : IGenHolo {
    val lang = Language.getLang(iso)
    val tier = createArmorStand(
        lang.m(Messages.GENERATOR_HOLOGRAM_TIER)
            .replace("{tier}", lang.m(Messages.FORMATTING_GENERATOR_TIER1)),
        generator.location.clone().add(0.0, 3.0, 0.0)
    )
    val timer = createArmorStand(
        lang.m(Messages.GENERATOR_HOLOGRAM_TIMER)
            .replace("{seconds}", "${generator.nextSpawn}"),
        generator.location.clone().add(0.0, 2.4, 0.0)
    )
    val name = createArmorStand(
        lang.m(if (generator.type == GeneratorOre.DIAMOND)
            Messages.GENERATOR_HOLOGRAM_TYPE_DIAMOND
        else Messages.GENERATOR_HOLOGRAM_TYPE_EMERALD
        ), generator.location.clone().add(0.0, 2.7, 0.0)
    )

    override fun updateForAll() {
        for (player in timer.world.players) {
            updateForPlayer(player, Language.getLanguage(player).iso)
        }
    }

    override fun updateForPlayer(player: Player, lang: String) {
        if (lang.equals(iso, ignoreCase = true)) return
        BedWars.nms.apply {
            hideEntity(tier, player)
            hideEntity(timer, player)
            hideEntity(this@HoloGram.name, player)
        }
    }

    private fun setName(armorStand: ArmorStand, name: String) {
        if (!armorStand.isDead) armorStand.customName = name
    }

    override fun setTierName(name: String) = setName(tier, name)
    override fun setTimerName(name: String) = setName(timer, name)

    override fun destroy() {
        tier.remove()
        timer.remove()
        name.remove()
    }
}