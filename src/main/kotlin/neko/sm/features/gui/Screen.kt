package neko.sm.features.gui

import neko.sm.utils.misc.Accessor
import today.opai.api.events.EventRender2D

/**
 * @author yuchenxue
 * @date 2025/03/05
 */

interface Screen : Accessor {
    fun render(event: EventRender2D)
}