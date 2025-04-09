package neko.sm.value.special

import neko.sm.value.Setting
import today.opai.api.interfaces.modules.Value
import today.opai.api.interfaces.modules.values.MultiBooleanValue

/**
 * @author yuchenxue
 * @date 2025/03/07
 */

class MultiBooleanSetting(value: MultiBooleanValue) : Setting<List<Value<Boolean>>, MultiBooleanValue>(value) {
}