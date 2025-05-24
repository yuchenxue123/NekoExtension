package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.client.api.AbstractCPacketPlayer
import today.opai.api.dataset.PositionData
import today.opai.api.interfaces.dataset.Vector3d
import today.opai.api.interfaces.game.network.client.CPacket04Position

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketPosition(
    packet: CPacket04Position
) : AbstractCPacketPlayer<CPacket04Position>(packet), CPacket04Position {

    constructor(
        position: PositionData,
        onGround: Boolean
    ) : this(network.createPlayerPosition(position, onGround))

    constructor(
        x: Double,
        y: Double,
        z: Double,
        onGround: Boolean
    ) : this(network.createPlayerPosition(x, y, z, onGround))

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
}