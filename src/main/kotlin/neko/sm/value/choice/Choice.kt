package neko.sm.value.choice

import neko.sm.utils.always.Project
import neko.sm.value.Configurable
import neko.sm.value.SubMode

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

abstract class Choice(override val modeName: String) : Project, SubMode, Configurable() {
    abstract val parent: ChoicesSetting<*>

    open fun enable() {}
    open fun disable() {}

    override val isRunning: Boolean
        get() = parent.current == this && parent.parent.isEnabled
}