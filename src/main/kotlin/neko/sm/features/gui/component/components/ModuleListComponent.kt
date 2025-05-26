package neko.sm.features.gui.component.components

import neko.sm.features.gui.component.Component
import neko.sm.features.gui.component.components.arraylist.ArrayElement
import neko.sm.features.gui.component.components.arraylist.SimpleArrayElement
import neko.sm.features.module.modules.render.ModuleSimpleHUD
import neko.sm.features.module.modules.render.ModuleSimpleHUD.SortMode
import today.opai.api.events.EventRender2D

/**
 * @author yuchenxue
 * @date 2025/03/18
 */

object ModuleListComponent : Component {
    override val condition: Boolean
        get() = ModuleSimpleHUD.array

    // position
    private const val X_POSITION = 0f
    private const val Y_POSITION = 10f

    private val elements = mutableListOf<ArrayElement>()

    init {
        build()
    }

    override fun render(event: EventRender2D) {
        refresh()

        for (element in elements) {
            if (element.isHidden) {
                continue
            }

            element.render(event)
        }
    }

    private fun refresh() {
        if (API.moduleManager.modules.size != elements.size) {
            build()
        }

        var start = getStartY()
        for (element in elements) {
            if (element.isHidden) {
                continue
            }
            element.update(start)
            start += element.height
        }

        sort()
    }

    private fun sort() {
        when (ModuleSimpleHUD.sortMode.current) {
            SortMode.WIDTH -> {
                elements.sortWith(compareBy { -it.width })
            }

            SortMode.CATEGORY -> {
                elements.sortWith(compareBy<ArrayElement> { it.category.name }
                    .thenBy { -it.width })
            }
        }
    }

    private fun build() {
        elements.clear()

        var start = getStartY()
        for (module in API.moduleManager.modules) {
            val element = SimpleArrayElement(module, X_POSITION, Y_POSITION)
            elements.add(element)
            start += element.height
        }

        refresh()
    }

    private fun getStartY(): Float {
        if (ModuleSimpleHUD.watermark) {
            return WatermarkComponent.Y_POSITION + WatermarkComponent.watermark.height + Y_POSITION
        }

        return Y_POSITION
    }
}