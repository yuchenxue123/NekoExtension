package neko.sm.value.choice

import neko.sm.module.PluginModule
import neko.sm.utils.always.ProjectManager
import neko.sm.utils.misc.API
import neko.sm.value.values.ModeSetting

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

class ChoicesSetting<T : Choice>(
    val parent: PluginModule,
    name: String,
    val choices: Array<T>,
    value: T = choices[0],
    displayable: () -> Boolean = { true }
) : ModeSetting(
    API.valueManager.createModes(name, value.modeName, choices.map { it.modeName }.toTypedArray()).apply {
        this.setHiddenPredicate { !displayable.invoke() }
    }
) {

    var current: T = value

    init {
        // register
        choices.forEach(ProjectManager::add)

        inner.setValueCallback { v ->
            val find = choices.find { it.modeName.equals(v, true) } ?: choices[0]
            current.disable()
            find.enable()
            current = find
        }
    }
}