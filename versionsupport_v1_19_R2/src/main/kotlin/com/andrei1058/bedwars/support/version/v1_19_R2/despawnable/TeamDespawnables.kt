package com.andrei1058.bedwars.support.version.v1_19_R2.despawnable

import com.andrei1058.bedwars.support.version.common.despawnable.DespawnableType
import org.bukkit.entity.IronGolem
import org.bukkit.entity.Silverfish

class TeamIronGolem : DespawnableProvider<IronGolem>(DespawnableType.IRON_GOLEM)
class TeamSilverfish : DespawnableProvider<Silverfish>(DespawnableType.SILVERFISH)