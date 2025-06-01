package neko.sm.utils.extension

import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/06/01
 */

fun Color.multiply(
    red: Float = 1f,
    green: Float = 1f,
    blue: Float = 1f,
    opacity: Float = 1f
) : Color {
    return Color(
        (this.red * red).toInt().coerceIn(0, 255),
        (this.green * green).toInt().coerceIn(0, 255),
        (this.blue * blue).toInt().coerceIn(0, 255),
        (this.alpha * opacity).toInt().coerceIn(0, 255)
    )
}