package neko.sm.gui.component

import neko.sm.gui.component.components.ModuleListComponent
import neko.sm.gui.component.components.NotificationsComponent
import neko.sm.gui.component.components.WatermarkComponent
import neko.sm.utils.misc.Accessor
import today.opai.api.events.EventRender2D

/**
 * @author yuchenxue
 * @date 2025/02/21
 */
object ComponentManager : Accessor {

    private val components = mutableListOf<Component>()

    init {
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
