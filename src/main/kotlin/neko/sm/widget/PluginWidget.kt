package neko.sm.widget

import neko.sm.utils.misc.Accessor
import today.opai.api.features.ExtensionWidget

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

abstract class PluginWidget(name: String) : ExtensionWidget(name), Accessor {
    fun sync(block: () -> Pair<Float, Float>): Pair<Float, Float> {
        block.invoke().let {
            width = it.first
            height = it.second

            return it
        }
    }
}