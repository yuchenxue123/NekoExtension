package neko.sm.utils.always.projects

import neko.sm.utils.always.Project

/**
 * @author yuchenxue
 * @date 2025/03/16
 */

object TickProject : Project {
    var ticks = 0

    override fun onTick() {
        ticks++
    }
}