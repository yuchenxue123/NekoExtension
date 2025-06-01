package neko.sm.utils.extension

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.round

/**
 * @author yuchenxue
 * @date 2025/03/09
 */

fun Double.decimals(n: Int): Double {
    val bigDecimal = BigDecimal(this)
    val rounded = bigDecimal.setScale(n, RoundingMode.HALF_UP)
    return rounded.toDouble()
}

fun Float.decimals(n: Int): Float {
    val bigDecimal = BigDecimal(this.toDouble())
    val rounded = bigDecimal.setScale(n, RoundingMode.HALF_UP)
    return rounded.toFloat()
}

fun Float.toRadians() = this * 0.017453292f
fun Double.toRadians(): Double = this * 0.017453292519943295
fun Double.step(step: Double): Double = round(this / step) * step
fun Float.step(step: Float): Float = round(this / step) * step