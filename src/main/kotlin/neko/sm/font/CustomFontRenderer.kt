package neko.sm.font

import today.opai.api.interfaces.render.Font

/**
 * @author yuchenxue
 * @date 2025/03/07
 */

class CustomFontRenderer(private val font: Font) {
    companion object {
        fun of(font: Font): CustomFontRenderer {
            return CustomFontRenderer(font)
        }
    }

    fun drawString(text: String, x: Float, y: Float, color: Int): Float {
        return drawString(text, x.toDouble(), y.toDouble(), color)
    }

    fun drawString(text: String, x: Double, y: Double, color: Int): Float {
        return font.drawString(text, x - 1, y, color)
    }

    fun drawStringWithShadow(text: String, x: Float, y: Float, color: Int): Float {
        return drawStringWithShadow(text, x.toDouble(), y.toDouble(), color)
    }

    fun drawStringWithShadow(text: String, x: Double, y: Double, color: Int): Float {
        return font.drawStringWithShadow(text, x - 1, y, color)
    }

    fun drawCenteredString(text: String, x: Float, y: Float, color: Int): Float {
        return drawCenteredString(text, x.toDouble(), y.toDouble(), color)
    }

    fun drawCenteredString(text: String, x: Double, y: Double, color: Int): Float {
        return font.drawCenteredString(text, x - 1, y, color)
    }

    /**
     * 为什么这个没有返回值
     */
    fun drawCenteredStringWithShadow(text: String, x: Double, y: Double, color: Int) {
        return font.drawCenteredStringWithShadow(text, x - 1, y, color)
    }

    fun getWidth(text: String): Int {
        return font.getWidth(text)
    }

    val height: Int
        get() = font.height

    fun close() {
        font.close()
    }
}