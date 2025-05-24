package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.client.api.AbstractCPacketPlayer
import today.opai.api.dataset.RotationData
import today.opai.api.interfaces.game.network.client.CPacket05Rotation

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketRotation(
    packet: CPacket05Rotation
) : AbstractCPacketPlayer<CPacket05Rotation>(packet), CPacket05Rotation {

    constructor(
        rotation: RotationData,
        onGround: Boolean
    ) : this(network.createPlayerRotation(rotation, onGround))

    constructor(
        yaw: Float,
        pitch: Float,
        onGround: Boolean
    ) : this(network.createPlayerRotation(yaw, pitch, onGround))

    override fun isOnGround(): Boolean {
        return packet.isOnGround
    }

    override fun setOnGround(onGround: Boolean) {
        packet.isOnGround = onGround
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

    override fun setPitch(pitch: Float) {
        packet.pitch = pitch
    }

    override fun setRotation(rotation: RotationData) {
        packet.rotation = rotation
    }
}