package neko.sm.value.values

import neko.sm.value.Setting
import today.opai.api.interfaces.modules.values.BooleanValue

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

class BooleanSetting(value: BooleanValue) : Setting<Boolean, BooleanValue>(value), BooleanValue