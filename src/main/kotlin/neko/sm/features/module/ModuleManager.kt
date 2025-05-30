package neko.sm.features.module

import neko.sm.features.module.modules.combat.ModuleAntiVelocity
import neko.sm.features.module.modules.misc.ModuleHelper
import neko.sm.features.module.modules.movement.ModuleAirLine
import neko.sm.features.module.modules.render.ModuleSimpleHUD
import neko.sm.features.module.modules.render.ModuleSuperEyes
import neko.sm.utils.annotation.DontRegister
import neko.sm.utils.interfaces.Accessor
import neko.sm.utils.interfaces.Register

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object ModuleManager : Register<PluginModule>, Accessor {
    private val modules = mutableListOf<PluginModule>()

    fun initialize() {
        arrayOf(
            ModuleSimpleHUD,
            ModuleHelper,
            ModuleSuperEyes,
            ModuleAntiVelocity,
            ModuleAirLine,
        ).forEach(this::add)

        modules.forEach(this::register)
    }

    override fun register(element: PluginModule) {
        if (element.javaClass.isAnnotationPresent(DontRegister::class.java)) {
            return
        }

        element.eventHandler = element
        API.registerFeature(element)
    }

    private fun add(module: PluginModule) {
        modules.add(module)
    }

    override fun toString(): String = modules.joinToString(", ") { it.name }
}