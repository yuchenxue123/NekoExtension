package neko.sm.features.gui.component

import neko.sm.features.gui.Screen

/**
 * @author yuchenxue
 * @date 2025/02/21
 */

interface Component : Screen {
    fun init() {}

    val condition: Boolean
}
