package neko.sm.utils.always.projects

import neko.sm.utils.always.Project
import today.opai.api.interfaces.modules.PresetModule

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

object NotificationProject : Project {
    override fun onModuleToggle(module: PresetModule, state: Boolean) {
        // 这里不好
    }
}