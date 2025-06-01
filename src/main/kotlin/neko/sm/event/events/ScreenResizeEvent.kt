package neko.sm.event.events

import neko.sm.event.Event
import today.opai.api.interfaces.render.WindowResolution

/**
 * @author yuchenxue
 * @date 2025/06/01
 */

class ScreenResizeEvent(val resolution: WindowResolution) : Event()