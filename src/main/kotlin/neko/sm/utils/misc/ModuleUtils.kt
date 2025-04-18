package neko.sm.utils.misc

import neko.sm.utils.extension.removeBlank
import neko.sm.utils.misc.ValueUtils.getValue
import neko.sm.utils.resources.ResourcesUtils
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

    private val suffixData = ResourcesUtils.getAsJson("assets/data/module_suffix.json")

    fun getSuffix(module: PresetModule): String {
        val name = module.name.removeBlank()

        val data = suffixData ?: return ""

        if (data.has(name)) {
            val suffix = data.get(name).asString

            val modeValue = getValue(module, suffix)

            if (modeValue != null) {
                return modeValue.value.toString()
            }
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