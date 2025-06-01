package neko.sm.utils.always.projects

import neko.sm.event.EventManager
import neko.sm.event.events.ScreenResizeEvent
import neko.sm.utils.always.Project
import today.opai.api.events.EventRender2D
import today.opai.api.interfaces.render.WindowResolution

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

object ScreenProject : Project {

    var resolution: WindowResolution? = null

    override fun onRender2D(event: EventRender2D) {
        val resolution = event.windowResolution

        val last = this.resolution

        if (last == null || (resolution.width != last.width || resolution.height != last.height)) {
            EventManager.dispatch(ScreenResizeEvent(resolution))
        }

        this.resolution = resolution
    }
}