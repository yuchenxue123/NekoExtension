package neko.sm.utils.misc

import neko.sm.utils.extension.removeBlank
import neko.sm.utils.misc.ValueUtils.getValue
import today.opai.api.enums.EnumModuleCategory
import today.opai.api.enums.EnumModuleCategory.*
import today.opai.api.interfaces.modules.PresetModule
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

object ModuleUtils : Accessor {
    private val categoryMap = mutableMapOf<PresetModule, EnumModuleCategory>()

    private val map = mutableMapOf(
        "AntiKB" to "Mode",
        "AntiVoid" to "Mode",
        "NoFall" to "Mode",
        "NoSlow" to "Mode",
        "Disabler" to "Mode",
        "KillAura" to "Mode",
        "Phase" to "Mode",
        "LongJump" to "Mode",
        "Spider" to "Mode",
        "AutoArmor" to "Delay",
        "ChestStealer" to "Delay",
        "InvManager" to "Delay",
        "TargetStrafe" to "Range",
        // extension
        "Velocity" to "Mode",
        "AirLine" to "Mode"
    )
    fun getSuffix(module: PresetModule): String {
        val name = module.name.removeBlank()

        if (name !in map.keys) {
            return ""
        }

        val modeValue = getValue(module, map[name]!!)

        if (modeValue != null) {
            return modeValue.value.toString()
        }

        return ""
    }

    fun getState(name: String): Boolean {
        API.moduleManager.getModule(name)?.let {
            return it.isEnabled
        } ?: return false
    }

    fun getCategory(module: PresetModule): EnumModuleCategory {
        if (categoryMap.containsKey(module)) {
            return categoryMap[module]!!
        }

        val category = getModuleCategory(module)
        categoryMap[module] = category

        return category
    }

    fun getCategoryColor(category: EnumModuleCategory): Color {
        return when (category) {
            COMBAT -> Color(255, 100, 100)
            MOVEMENT -> Color(100, 150, 255)
            PLAYER -> Color(100, 200, 100)
            VISUAL -> Color(255, 220, 100)
            MISC -> Color(150, 100, 200)
        }
    }

    private fun getModuleCategory(module: PresetModule): EnumModuleCategory {
        var category = EnumModuleCategory.MISC

        EnumModuleCategory.entries.forEach { cty ->
            val modules = API.moduleManager.getModulesInCategory(cty)
            if (module.name in modules.map { it.name }) {
                category = cty
            }
        }

        return category
    }
}