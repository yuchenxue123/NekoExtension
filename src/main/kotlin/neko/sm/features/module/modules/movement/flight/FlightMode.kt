package neko.sm.features.module.modules.movement.flight

import neko.sm.features.module.modules.movement.ModuleFlight
import neko.sm.value.choice.Choice
import neko.sm.value.choice.ChoicesSetting

/**
 * @author yuchenxue
 * @date 2025/03/15
 */

open class FlightMode(name: String) : Choice(name) {
    override val parent: ChoicesSetting<*>
        get() = ModuleFlight.mode
}