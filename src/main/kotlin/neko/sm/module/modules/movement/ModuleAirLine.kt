package neko.sm.module.modules.movement

import neko.sm.module.PluginModule
import neko.sm.module.modules.movement.fly.FlyExplosion
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

    override fun getSuffix(): String {
        return mode.current.modeName
    }



}