package neko.sm.utils.extension

import today.opai.api.dataset.BlockPosition
import today.opai.api.dataset.Vec3Data
import today.opai.api.interfaces.game.entity.Entity
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.round

/**
 * @author yuchenxue
 * @date 2025/03/09
 */

operator fun BlockPosition.plus(other: BlockPosition): BlockPosition {
    return BlockPosition(x + other.x, y + other.y, z + other.z)
}

fun Double.decimals(n: Int): Double {
    val bigDecimal = BigDecimal(this)
    val rounded = bigDecimal.setScale(n, RoundingMode.HALF_UP)
    return rounded.toDouble()
}

fun Float.toRadians() = this * 0.017453292f
fun Double.toRadians(): Double = this * 0.017453292519943295
fun Double.step(step: Double): Double = round(this / step) * step
fun Float.step(step: Float): Float = round(this / step) * step

fun String.removeBlank(): String {
    return this.replace("\\s+".toRegex(), "")
}

// entity
var Entity.motionX: Double
    get() = this.motion.x
    set(value) {
        this.setMotion(Vec3Data(value, this.motion.y, this.motion.z))
    }

var Entity.motionY: Double
    get() = this.motion.y
    set(value) {
        this.setMotion(Vec3Data(this.motion.x, value, this.motion.z))
    }

var Entity.motionZ: Double
    get() = this.motion.z
    set(value) {
        this.setMotion(Vec3Data(this.motion.x, this.motion.y, value))
    }