package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.server.SPacket02Chat

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketChat(
    packet: SPacket02Chat
) : Packet<SPacket02Chat>(packet), SPacket02Chat {
    override fun getType(): Byte {
        return packet.type
    }

    override fun setType(type: Byte) {
        packet.type = type
    }

    override fun getMessage(): String {
        return packet.message
    }

    override fun setMessage(message: String) {
        packet.message = message
    }
}