package neko.sm.features.gui.component.components.notification

import neko.sm.features.gui.Screen
import today.opai.api.events.EventRender2D

/**
 * @author yuchenxue
 * @date 2025/03/18
 */

abstract class Notification : Screen {
}

class SimpleNotification : Notification() {
    override fun render(event: EventRender2D) {
    }

}