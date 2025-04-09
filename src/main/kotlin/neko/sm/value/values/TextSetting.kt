package neko.sm.value.values

import neko.sm.value.Setting
import today.opai.api.interfaces.modules.values.TextValue

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

class TextSetting(value: TextValue) : Setting<String, TextValue>(value), TextValue