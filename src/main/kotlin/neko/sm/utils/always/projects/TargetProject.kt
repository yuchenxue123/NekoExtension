package neko.sm.utils.always.projects

import neko.sm.client.module.ModuleAccessor
import neko.sm.utils.always.Project
import today.opai.api.interfaces.game.entity.LivingEntity

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

object TargetProject : Project {

    var lastTarget: LivingEntity? = null
    var target: LivingEntity? = null

    private val changeHandlers = mutableListOf<(last: LivingEntity?, new: LivingEntity?) -> Unit>()

    override fun onTick() {
        val updatedTarget: LivingEntity? = ModuleAccessor.KillAura.target
        if (updatedTarget != target) {
            lastTarget = target
            target = updatedTarget

            changeHandlers.forEach { it(lastTarget , target) }
        }
    }

    fun onChange(block: (last: LivingEntity?, new: LivingEntity?) -> Unit) {
        changeHandlers.add(block)
    }
}