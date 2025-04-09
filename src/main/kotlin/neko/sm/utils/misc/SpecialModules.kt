package neko.sm.utils.misc

import today.opai.api.interfaces.modules.special.*

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

object SpecialModules : Accessor {
    val AntiCheat: ModuleAntiCheat = API.moduleManager.getModule("AntiCheat") as ModuleAntiCheat
    val AntiBot: ModuleAntiBot = API.moduleManager.getModule("AntiBot") as ModuleAntiBot
    val KillAura: ModuleKillAura = API.moduleManager.getModule("KillAura") as ModuleKillAura
    val Disabler: ModuleDisabler = API.moduleManager.getModule("Disabler") as ModuleDisabler
    val Teams: ModuleTeams = API.moduleManager.getModule("Teams") as ModuleTeams
    val HeightLimit: ModuleHeightLimit = API.moduleManager.getModule("HeightLimit") as ModuleHeightLimit
}