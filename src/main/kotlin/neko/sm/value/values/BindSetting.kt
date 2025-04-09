package neko.sm.value.values

import neko.sm.value.Setting
import today.opai.api.interfaces.modules.values.BindValue

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

class BindSetting(value: BindValue) : Setting<Int, BindValue>(value), BindValue {
    override fun getKeyName(): String {
        return inner.keyName
    }

    override fun isPressed(): Boolean {
        return inner.isPressed
    }
}