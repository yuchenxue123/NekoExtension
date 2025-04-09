package neko.sm.utils.move

import neko.sm.utils.extension.motionX
import neko.sm.utils.extension.motionY
import neko.sm.utils.extension.motionZ
import neko.sm.utils.extension.toRadians
import neko.sm.utils.misc.Accessor
import today.opai.api.enums.EnumKeybind
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * @author yuchenxue
 * @date 2025/03/15
 */

object Movement : Accessor {

    val speed: Float
        get() = sqrt(player.motionX * player.motionX + player.motionZ * player.motionZ).toFloat()

    val isMoving: Boolean
        get() = !API.isNull && player.motionX == .0 && player.motionY == .0

    val hasPressButton: Boolean
        get() {
            val options = API.options
            return options.isPressed(EnumKeybind.FORWARD) ||
                    options.isPressed(EnumKeybind.BACK)||
                    options.isPressed(EnumKeybind.LEFT) ||
                    options.isPressed(EnumKeybind.RIGHT)
        }

    fun strafe(speed: Float = this.speed, strength: Float = 1f, fastStop: Boolean = false) {
        if (!isMoving) return

        if (fastStop && !hasPressButton) {
            player.motionX = .0
            player.motionZ = .0
            return
        }

        val prevX = player.motionX * (1.0 - strength)
        val prevZ = player.motionZ * (1.0 - strength)
        val useSpeed = speed * strength

        val yaw = direction
        player.motionX = -sin(yaw) * useSpeed + prevX
        player.motionZ = cos(yaw) * useSpeed + prevZ
    }

    val direction: Double
        get() = getDirection()

    fun getDirection(
        rotationYaw: Float = player.rotation.yaw
    ): Double {
        var yaw = rotationYaw
        var forward = 1f

        if (player.moveForward < 0f) {
            yaw += 180f
            forward = -0.5f
        } else if (player.moveForward > 0f)
            forward = 0.5f

        if (player.moveStrafing < 0f) yaw += 90f * forward
        else if (player.moveStrafing > 0f) yaw -= 90f * forward

        return yaw.toRadians().toDouble()
    }
}