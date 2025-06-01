package neko.sm.features.module.modules.combat.velocity

import neko.sm.features.module.modules.combat.ModuleAntiVelocity
import neko.sm.value.choice.Choice
import neko.sm.value.choice.ChoicesSetting

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

open class AntiVelocityMode(name: String) : Choice(name) {
    override val parent: ChoicesSetting<*>
        get() = ModuleAntiVelocity.mode
}