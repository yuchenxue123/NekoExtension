package neko.sm.features.module.modules.misc

import neko.sm.client.module.ModuleAccessor
import neko.sm.client.module.getModeValue
import neko.sm.client.module.set
import neko.sm.features.module.PluginModule
import neko.sm.utils.annotation.DontRegister
import neko.sm.utils.time.TimeTracker
import today.opai.api.enums.EnumModuleCategory
import today.opai.api.interfaces.modules.PresetModule

/**
 * @author yuchenxue
 * @date 2025/02/27
 */

@DontRegister(reason = "Damage long jump patched.")
object ModuleHelper : PluginModule(
    "Helper",
    "Many helpers",
    EnumModuleCategory.MISC
) {
    private val jump by boolean("Long Jump", false)
    private val fireBallBind by bind("FireBall", 48) { jump }
    private val damageBind by bind("Damage", 21) { jump }

    private var state = false
    private var checkNoMove = false
    private val watch = TimeTracker()

    override fun onKey(keyCode: Int) {
        val longJump = ModuleAccessor.LongJump

        if (jump && !longJump.isEnabled) {
            if (keyCode == fireBallBind) {
                longJump.getModeValue("Mode")?.set("Watchdog Fireball")
                setState(longJump, true)
                checkNoMove = true
            }

            if (keyCode == damageBind) {
                longJump.getModeValue("Mode")?.set("Watchdog Damage")
                setState(longJump, true)
                checkNoMove = false
            }
        }
    }

    override fun onPlayerUpdate() {
        if (API.world == null || API.localPlayer == null) {
            return
        }

        // long jump
        val longJump = API.moduleManager.getModule("LongJump")

        if (!longJump.isEnabled && state) {
            state = false
        }

        val stop = player.motion.x == .0 && player.motion.z == .0
        if (!stop) {
            watch.reset()
        }

        if (longJump.isEnabled && checkNoMove && state && stop && watch.hasPassedTime(1000L)) {
            setState(longJump, false)
            watch.reset()
        }
    }

    private fun setState(module: PresetModule, state: Boolean) {
        module.isEnabled = state
        ModuleHelper.state = state
        if (!state) {
            checkNoMove = false
        }
        watch.reset()
    }
}
