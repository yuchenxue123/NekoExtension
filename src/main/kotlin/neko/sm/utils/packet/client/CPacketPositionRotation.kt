package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import today.opai.api.dataset.PositionData
import today.opai.api.dataset.RotationData
import today.opai.api.interfaces.dataset.Vector3d
import today.opai.api.interfaces.game.network.client.CPacket06PositionRotation

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketPositionRotation(
    packet: CPacket06PositionRotation
) : CPacketPlayer<CPacket06PositionRotation>(packet), CPacket06PositionRotation {

    constructor(
        position: PositionData,
        rotation: RotationData,
        onGround: Boolean
    ) : this(network.createPlayerPositionRotation(position, rotation, onGround))

    constructor(
        x: Double,
        y: Double,
        z: Double,
        yaw: Float,
        pitch: Float,
        onGround: Boolean
    ) : this(network.createPlayerPositionRotation(x, y, z, yaw, pitch, onGround))

    override fun isOnGround(): Boolean {
        return packet.isOnGround
    }

    override fun setOnGround(onGround: Boolean) {
        packet.isOnGround = onGround
    }

    override fun getX(): Double {
        return packet.x
    }

    override fun getY(): Double {
        return packet.y
    }

    override fun getZ(): Double {
        return packet.z
    }

    override fun getPosition(): Vector3d {
        return packet.position
    }

    override fun setX(x: Double) {
        packet.x = x
    }

    override fun setY(y: Double) {
        packet.y = y
    }

    override fun setZ(z: Double) {
        packet.z = z
    }

    override fun setPosition(position: PositionData) {
        packet.setPosition(position)
    }

    override fun getYaw(): Float {
        return packet.yaw
    }

    override fun getPitch(): Float {
        return packet.pitch
    }

    override fun getRotation(): RotationData {
        return packet.rotation
    }

    override fun setYaw(yaw: Float) {
        packet.yaw = yaw
    }

    override fun setPitch(pitich: Float) {
        packet.pitch = pitch
    }

    override fun setRotation(rotation: RotationData) {
        packet.rotation = rotation
    }
}