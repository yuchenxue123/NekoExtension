package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.server.SPacket0BAnimation

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketAnimation(
    packet: SPacket0BAnimation
) : Packet<SPacket0BAnimation>(packet), SPacket0BAnimation {
    override fun getEntityId(): Int {
        return packet.entityId
    }

    override fun getAnimation(): Byte {
        return packet.animation
    }

    override fun setAnimation(animation: Byte) {
        packet.animation = animation
    }
}