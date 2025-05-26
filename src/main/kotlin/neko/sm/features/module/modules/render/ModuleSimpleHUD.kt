package neko.sm.features.module.modules.render

import neko.sm.features.gui.component.ComponentManager
import neko.sm.features.module.PluginModule
import neko.sm.value.SubMode
import today.opai.api.enums.EnumModuleCategory
import today.opai.api.events.EventRender2D

/**
 * @author yuchenxue
 * @date 2025/02/19
 */

object ModuleSimpleHUD : PluginModule(
    "Simple HUD",
    "None",
    EnumModuleCategory.VISUAL
) {
    // Watermark
    val watermark by boolean("Watermark", true)
    val title by text("Watermark Text", "%client% [1.8.9] [%time%]") { watermark }

    // ModuleList
    val array by boolean("Array List", true)

    val sortMode = enum("Sort Mode", SortMode.WIDTH) { array }

    enum class SortMode(override val modeName: String) : SubMode {
        WIDTH("Width"),
        CATEGORY("Category")
    }

    val sideline by boolean("Sideline", false)
    val nameSpace by boolean("Name Space", true) { array }
    val suffix by boolean("Array Suffix", false) { array }

    // Notification
    val notification by boolean("Notification", true)
    val notificationType = enum("Notification Type", NotificationType.SIMPLE) { notification }

    enum class NotificationType(override val modeName: String) : SubMode {
        SIMPLE("Simple"),
        BLACK("Black")
    }

    // widgets
    val scoreboard by boolean("Scoreboard", false)
    val potions by boolean("Potions", false)
    val target by boolean("Target", true)

    override fun onRender2D(event: EventRender2D) {
        ComponentManager.render(event)
    }
}
