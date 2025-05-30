package neko.sm.utils.always

import neko.sm.utils.interfaces.Accessor
import today.opai.api.interfaces.EventHandler

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

interface Project : EventHandler, Accessor {
    val isRunning: Boolean
        get() = true
}