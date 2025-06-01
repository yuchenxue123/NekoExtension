package neko.sm.features.module.modules.movement

import neko.sm.features.module.PluginModule
import neko.sm.features.module.modules.movement.flight.FlightExplosion
import today.opai.api.enums.EnumModuleCategory

/**
 * @author yuchenxue
 * @date 2025/03/15
 */

object ModuleFlight : PluginModule(
    "Flight",
    "Fly in sky.",
    EnumModuleCategory.MOVEMENT
) {
    val mode = choices("Mode",
        arrayOf(
            FlightExplosion
        ))
}