package neko.sm.features.module.modules.movement

import neko.sm.features.module.PluginModule
import neko.sm.features.module.modules.movement.fly.FlyExplosion
import today.opai.api.enums.EnumModuleCategory

/**
 * @author yuchenxue
 * @date 2025/03/15
 */

object ModuleAirLine : PluginModule(
    "Air Line",
    "fly in sky.",
    EnumModuleCategory.MOVEMENT
) {
    val mode = choices("Mode",
        arrayOf(
            FlyExplosion
        ))
}