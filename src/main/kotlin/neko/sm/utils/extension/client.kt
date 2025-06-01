package neko.sm.utils.extension

import neko.sm.utils.misc.world
import today.opai.api.dataset.BlockPosition
import today.opai.api.dataset.PositionData
import today.opai.api.dataset.Vec3Data
import today.opai.api.events.EventCancelable
import today.opai.api.interfaces.game.entity.Entity
import today.opai.api.interfaces.game.world.Block

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

fun EventCancelable.cancel() {
    this.isCancelled = true
}

operator fun BlockPosition.plus(other: BlockPosition): BlockPosition {
    return BlockPosition(x + other.x, y + other.y, z + other.z)
}

fun PositionData.toBlockPosition(): BlockPosition {
    return BlockPosition(this.x.toInt(), this.y.toInt(), this.z.toInt())
}

val BlockPosition.block: Block
    get() = world.getBlockFromPosition(this)

val BlockPosition.blockId: Int
    get() = world.getBlock(this)

val Entity.inWater: Boolean
    get() = position.toBlockPosition().blockId in arrayOf(8, 9)

val Entity.inLava: Boolean
    get() = position.toBlockPosition().blockId in arrayOf(10, 11)

// entity
val Entity.x: Double
    get() = position.x

val Entity.y: Double
    get() = position.y

val Entity.z: Double
    get() = position.z

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