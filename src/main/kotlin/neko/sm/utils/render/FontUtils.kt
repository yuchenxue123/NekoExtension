package neko.sm.utils.render

import neko.sm.utils.misc.Accessor
import today.opai.api.interfaces.render.Font
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/02/22
 */

object FontUtils : Accessor {

    /**
     * Draw many lines text
     */
    fun drawSplitString(font: Font, text: String, x: Float, y: Float, color: Color) {
        val split = text.split("\n".toRegex())

        var currentY = y
        for (s in split) {
            font.drawStringWithShadow(
                s,
                x.toDouble(),
                currentY.toDouble(),
                color.rgb
            )
            currentY += font.height + 2f
        }
    }
}
