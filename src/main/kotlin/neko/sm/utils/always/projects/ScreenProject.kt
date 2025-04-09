package neko.sm.utils.always.projects

import neko.sm.utils.always.Project
import today.opai.api.events.EventRender2D
import today.opai.api.interfaces.render.WindowResolution

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

object ScreenProject : Project {

    var screen: WindowResolution? = null

    override fun onRender2D(event: EventRender2D) {
        screen = event.windowResolution
    }
}