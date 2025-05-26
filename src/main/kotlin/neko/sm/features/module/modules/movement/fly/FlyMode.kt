package neko.sm.features.module.modules.movement.fly

import neko.sm.features.module.modules.movement.ModuleAirLine
import neko.sm.value.choice.Choice
import neko.sm.value.choice.ChoicesSetting

/**
 * @author yuchenxue
 * @date 2025/03/15
 */

open class FlyMode(name: String) : Choice(name) {
    override val parent: ChoicesSetting<*>
        get() = ModuleAirLine.mode
}