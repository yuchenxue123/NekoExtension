package neko.sm.utils.always.projects

import neko.sm.utils.always.Project

/**
 * @author yuchenxue
 * @date 2025/03/15
 */

object MoveProject : Project {
    // ground
    var airTicks = 0
    var groundTicks = 0

    override fun onPlayerUpdate() {
        if (API.isNull) {
            return
        }

        if (player.isOnGround) {
            airTicks = 0
            groundTicks++
        } else {
            groundTicks = 0
            airTicks++
        }
    }
}