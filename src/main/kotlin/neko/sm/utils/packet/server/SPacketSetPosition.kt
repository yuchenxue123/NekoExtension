package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.dataset.Vector2f
import today.opai.api.interfaces.dataset.Vector3d
import today.opai.api.interfaces.game.network.server.SPacket08SetPosition

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketSetPosition(
    packet: SPacket08SetPosition
) : Packet<SPacket08SetPosition>(packet), SPacket08SetPosition {
    override fun getPosition(): Vector3d {
        return packet.position
    }

    override fun getRotation(): Vector2f {
        return packet.rotation
    }

    override fun setPosition(position: Vector3d) {
        packet.position = position
    }

    override fun setRotation(rotation: Vector2f) {
        packet.rotation = rotation
    }
}