package com.andrei1058.bedwars.support.version.v1_12_R1.despawnable

import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableType
import org.bukkit.entity.IronGolem
import org.bukkit.entity.Silverfish

class TeamIronGolem : DespawnableProvider<IronGolem>(DespawnableType.IRON_GOLEM)
class TeamSilverfish : DespawnableProvider<Silverfish>(DespawnableType.SILVERFISH)