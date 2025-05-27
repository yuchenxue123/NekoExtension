package neko.sm.value.values

import neko.sm.utils.misc.API
import neko.sm.value.Setting
import neko.sm.value.SettingWrapper
import neko.sm.value.SubMode
import today.opai.api.interfaces.modules.values.ModeValue
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

open class ModeSetting(value: ModeValue) : Setting<String, ModeValue>(value), ModeValue {
    override fun getModeCount(): Int {
        return inner.modeCount
    }

    override fun getAllModes(): Array<String> {
        return inner.allModes
    }

    override fun isCurrentMode(mode: String): Boolean {
        return inner.isCurrentMode(mode)
    }
}

// ak
class EnumSetting<E>(
    name: String,
    private val array: Array<E>,
    value: E = array[0],
    displayable: () -> Boolean = { true }
) : ReadWriteProperty<Any?, E>, SettingWrapper<String, ModeValue>(
    API.valueManager.createModes(name, value.modeName, array.map { it.modeName }.toTypedArray())
) where E : Enum<E>, E: SubMode {
    private var current: E = value

    init {
        this.setValueCallback { v ->
            val find = array.find { it.modeName.equals(v, true) } ?: array[0]
            current = find
        }

        this.displayable(displayable)
    }

    fun get(): E = current

    override fun getValue(thisRef: Any?, property: KProperty<*>): E {
        return current
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: E) {
        current = value
    }
}