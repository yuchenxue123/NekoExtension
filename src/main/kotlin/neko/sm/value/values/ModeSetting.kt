package neko.sm.value.values

import neko.sm.utils.misc.API
import neko.sm.value.Setting
import neko.sm.value.SubMode
import today.opai.api.interfaces.modules.values.ModeValue

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

class EnumSetting<E>(
    name: String,
    private val array: Array<E>,
    value: E = array[0],
    displayable: () -> Boolean = { true }
) : ModeSetting(
    API.valueManager.createModes(name, value.modeName, array.map { it.modeName }.toTypedArray())
) where E : Enum<E>, E: SubMode {
    var current: E = value

    init {
        // 改变
        this.setValueCallback { v ->
            val find = array.find { it.modeName.equals(v, true) } ?: array[0]
            current = find
        }

        this.setDisplayable(displayable)
    }
}