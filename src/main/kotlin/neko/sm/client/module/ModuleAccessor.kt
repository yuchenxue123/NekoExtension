package neko.sm.client.module

import neko.sm.utils.interfaces.Accessor
import neko.sm.utils.resources.AssetsManager
import today.opai.api.enums.EnumModuleCategory
import today.opai.api.features.ExtensionModule
import today.opai.api.interfaces.managers.ModuleManager
import today.opai.api.interfaces.modules.PresetModule
import today.opai.api.interfaces.modules.special.*

/**
 * @author yuchenxue
 * @date 2025/05/26
 */

object ModuleAccessor : ModuleManager, Accessor {
    val LongJump: PresetModule = getModule("LongJump")
    val Speed: PresetModule = getModule("Speed")

    // Special
    val AntiCheat: ModuleAntiCheat = getModule("AntiCheat") as ModuleAntiCheat
    val AntiBot: ModuleAntiBot = getModule("AntiBot") as ModuleAntiBot
    val KillAura: ModuleKillAura = getModule("KillAura") as ModuleKillAura
    val Disabler: ModuleDisabler = getModule("Disabler") as ModuleDisabler
    val Teams: ModuleTeams = getModule("Teams") as ModuleTeams
    val HeightLimit: ModuleHeightLimit = getModule("HeightLimit") as ModuleHeightLimit

    // suffix
    val suffix_json = AssetsManager.getDataAsJsonObject("module_suffix.json")

    // category
    private val categoryMap = mutableMapOf<PresetModule, EnumModuleCategory>()

    // get module category
    fun getCategory(module: PresetModule): EnumModuleCategory {
        if (module is ExtensionModule) {
            categoryMap[module] = module.category
            return module.category
        }

        val category = EnumModuleCategory.entries.find { category ->
            API.moduleManager.getModulesInCategory(category).any {
                it.name.equals(module.name, true)
            }
        } ?: EnumModuleCategory.MISC

        categoryMap[module] = category

        return category
    }

    override fun getModule(name: String): PresetModule {
        return API.moduleManager.getModule(name)
    }

    override fun getModulesInCategory(category: EnumModuleCategory): MutableCollection<PresetModule> {
        return API.moduleManager.getModulesInCategory(category)
    }

    override fun getModules(): MutableCollection<PresetModule> {
        return API.moduleManager.modules
    }
}