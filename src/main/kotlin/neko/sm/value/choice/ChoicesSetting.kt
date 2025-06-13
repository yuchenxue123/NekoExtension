package neko.sm.value.choice

import neko.sm.features.module.PluginModule
import neko.sm.utils.always.ProjectManager
import neko.sm.utils.misc.API
import neko.sm.value.SettingWrapper
import today.opai.api.interfaces.modules.values.ModeValue
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

class ChoicesSetting<T : Choice>(
    val controller: PluginModule,
    name: String,
    val choices: Array<T>,
    value: T = choices[0],
    displayable: () -> Boolean = { true }
) : ReadWriteProperty<Any?, T>, SettingWrapper<String, ModeValue>(
    API.valueManager.createModes(name, value.modeName, choices.map { it.modeName }.toTypedArray()).apply {
        this.setHiddenPredicate { !displayable.invoke() }
    }
) {

    private var current: T = value

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

    fun get(): T = current

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return current
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        if (choices.contains(value)) {
            current.disable()
            value.enable()
            current = value
        }
    }
}