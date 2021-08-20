package com.willfp.ecoskills.skills.skills

import com.willfp.ecoskills.api.PlayerSkillExpGainEvent
import com.willfp.ecoskills.skills.Skill
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import java.util.*

class SkillArmory : Skill(
    "armory"
) {
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun handleLevelling(event: EntityDamageByEntityEvent) {
        val player = event.entity
        if (player !is Player) {
            return
        }

        val xp = event.damage * this.config.getDouble("xp-per-hp")
        val gainEvent = PlayerSkillExpGainEvent(player, this, xp)
        Bukkit.getPluginManager().callEvent(gainEvent)
    }
}