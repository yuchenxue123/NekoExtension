package neko.sm.utils.time

import neko.sm.utils.always.projects.TickProject

/**
 * @author yuchenxue
 * @date 2025/03/16
 */

class TickWatch {
    private var tick = TickProject.ticks

    fun hasPassTicks(amount: Int): Boolean {
        return TickProject.ticks - tick >= amount
    }

    fun reset() = apply {
        tick = TickProject.ticks
    }
}