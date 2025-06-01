package neko.sm.features.module.modules.combat

import neko.sm.features.module.PluginModule
import neko.sm.features.module.modules.combat.velocity.AntiVelocityBlocksMC
import neko.sm.features.module.modules.combat.velocity.AntiVelocityCancel
import today.opai.api.enums.EnumModuleCategory

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object ModuleAntiVelocity : PluginModule(
    "Anti Velocity",
    "Reduce your knock back.",
    EnumModuleCategory.COMBAT
) {

    val mode = choices("Mode",
        arrayOf(
            AntiVelocityCancel,
            AntiVelocityBlocksMC,
        ), AntiVelocityCancel
    )

    override fun onEnabled() {
        mode.get().enable()
    }

    override fun onDisabled() {
        mode.get().disable()
    }
}