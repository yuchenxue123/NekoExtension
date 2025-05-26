package neko.sm.features.gui.component

import neko.sm.features.gui.component.components.ModuleListComponent
import neko.sm.features.gui.component.components.NotificationsComponent
import neko.sm.features.gui.component.components.WatermarkComponent
import neko.sm.utils.misc.Accessor
import today.opai.api.events.EventRender2D

/**
 * @author yuchenxue
 * @date 2025/02/21
 */
object ComponentManager : Accessor {

    private val components = mutableListOf<Component>()

    fun initialize() {
        val components = arrayOf(
//            ArrayComponent,
            WatermarkComponent,
            NotificationsComponent,
            ModuleListComponent
        )
        components.forEach(this::add)

        components.forEach(Component::init)
    }

    fun render(event: EventRender2D) {
        for (component in components) {
            if (!component.condition) {
                continue
            }
            component.render(event)
        }
    }

    private fun add(component: Component) {
        components.add(component)
    }
}
