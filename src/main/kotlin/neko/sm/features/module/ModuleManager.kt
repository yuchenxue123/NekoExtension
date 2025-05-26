package neko.sm.features.module

import neko.sm.features.module.modules.combat.ModuleVelocity
import neko.sm.features.module.modules.misc.ModuleHelper
import neko.sm.features.module.modules.movement.ModuleAirLine
import neko.sm.features.module.modules.render.ModuleSimpleHUD
import neko.sm.features.module.modules.render.ModuleSuperEyes
import neko.sm.utils.misc.Accessor

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object ModuleManager : Accessor {
    private val modules = mutableListOf<PluginModule>()

    fun initialize() {
        arrayOf(
            ModuleSimpleHUD,
            ModuleHelper,
            ModuleSuperEyes,
            ModuleVelocity,
            ModuleAirLine
        ).forEach(this::add)

        modules.forEach(this::register)
    }

    private fun register(module: PluginModule) {
        module.eventHandler = module
        API.registerFeature(module)
    }

    private fun add(module: PluginModule) {
        modules.add(module)
    }

    override fun toString(): String {
        return modules.joinToString(", ") { it.name }
    }
}