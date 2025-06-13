package neko.sm.utils.always.projects

import neko.sm.utils.always.Project
import today.opai.api.enums.EnumEventStage
import today.opai.api.events.EventMotionUpdate

/**
 * @author yuchenxue
 * @date 2025/03/16
 */

object TickProject : Project {
    var ticks = 0

    var airTicks = 0
    var groundTicks = 0

    override fun onTick() {
        ticks++
    }

    override fun onMotionUpdate(event: EventMotionUpdate) {
        if (event.stage == EnumEventStage.POST) {
            if (player.isOnGround) {
                airTicks = 0
                groundTicks++
            } else {
                groundTicks = 0
                airTicks++
            }
        }
    }
}