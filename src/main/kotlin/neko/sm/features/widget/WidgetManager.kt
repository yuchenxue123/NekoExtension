package neko.sm.features.widget

import neko.sm.features.widget.widgets.PotionsWidget
import neko.sm.features.widget.widgets.ScoreboardWidget
import neko.sm.features.widget.widgets.TargetWidget
import neko.sm.utils.interfaces.Register
import neko.sm.utils.misc.API

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object WidgetManager : Register<PluginWidget> {
    private val widgets = arrayListOf<PluginWidget>()

    fun initialize() {
        arrayOf(
            ScoreboardWidget,
            PotionsWidget,
            TargetWidget
        ).forEach(this::add)

        widgets.forEach(this::register)
    }

    override fun register(element: PluginWidget) {
        API.registerFeature(element)
    }

    private fun add(widget: PluginWidget) {
        widgets.add(widget)
    }

    override fun toString(): String {
        return widgets.joinToString(", ") { it.name }
    }
}