package neko.sm.value.values

import neko.sm.value.Setting
import today.opai.api.interfaces.modules.values.ColorValue
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/03/20
 */

class ColorSetting(value: ColorValue) : Setting<Color, ColorValue>(value), ColorValue {
    override fun isAlphaAllowed(): Boolean {
        return inner.isAlphaAllowed
    }

    override fun setAlphaAllowed(p0: Boolean): ColorValue {
        return inner.setAlphaAllowed(p0)
    }
}