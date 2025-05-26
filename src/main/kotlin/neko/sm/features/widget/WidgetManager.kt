package neko.sm.features.widget

import neko.sm.features.widget.widgets.PotionsWidget
import neko.sm.features.widget.widgets.ScoreboardWidget
import neko.sm.features.widget.widgets.TargetWidget
import neko.sm.utils.misc.API

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object WidgetManager {
    private val widgets = arrayListOf<PluginWidget>()

    fun initialize() {
        arrayOf(
            ScoreboardWidget,
            PotionsWidget,
            TargetWidget
        ).forEach(this::add)

        widgets.forEach(this::register)
    }

    private fun register(widget: PluginWidget) {
        API.registerFeature(widget)
    }

    private fun add(widget: PluginWidget) {
        widgets.add(widget)
    }

    override fun toString(): String {
        return widgets.joinToString(", ") { it.name }
    }
}