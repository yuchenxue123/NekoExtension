package neko.sm.module

import neko.sm.module.modules.combat.ModuleVelocity
import neko.sm.module.modules.misc.ModuleHelper
import neko.sm.module.modules.movement.ModuleAirLine
import neko.sm.module.modules.render.ModuleSimpleHUD
import neko.sm.module.modules.render.ModuleSuperEyes
import neko.sm.utils.misc.Accessor

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object ModuleManager : Accessor {
    private val modules = mutableListOf<PluginModule>()

    init {
        val modules = arrayOf(
            ModuleSimpleHUD,
            ModuleHelper,
            ModuleSuperEyes,
            ModuleVelocity,
            ModuleAirLine
        )

        modules.forEach(this::add)
    }

    fun register() {
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