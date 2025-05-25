package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.dataset.Vector3d
import today.opai.api.interfaces.game.network.server.SPacket27Explosion

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketExplosion(
    packet: SPacket27Explosion
) : Packet<SPacket27Explosion>(packet), SPacket27Explosion {
    override fun getStrength(): Float {
        return packet.strength
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

    override fun getMotion(): Vector3d {
        return packet.motion
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

    override fun setMotion(motion: Vector3d) {
        packet.motion = motion
    }
}