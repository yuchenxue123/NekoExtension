package neko.sm.widget

import neko.sm.utils.misc.API
import neko.sm.widget.widgets.PotionsWidget
import neko.sm.widget.widgets.ScoreboardWidget
import neko.sm.widget.widgets.TargetWidget

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object WidgetManager {
    private val widgets = arrayListOf<PluginWidget>()

    init {
        val widgets = arrayOf(
            ScoreboardWidget,
            PotionsWidget,
            TargetWidget
        )

        widgets.forEach(this::add)
    }

    fun register() {
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