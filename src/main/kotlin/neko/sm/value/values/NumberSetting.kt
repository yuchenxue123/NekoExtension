package neko.sm.value.values

import neko.sm.value.Setting
import today.opai.api.interfaces.modules.values.NumberValue

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

class NumberSetting(value: NumberValue) : Setting<Double, NumberValue>(value), NumberValue {
    override fun getMinimum(): Double {
        return inner.minimum
    }

    override fun getMaximum(): Double {
        return inner.maximum
    }

    override fun getStep(): Double {
        return inner.step
    }

    override fun setSuffix(suffix: String): NumberValue {
        return inner.setSuffix(suffix)
    }
}