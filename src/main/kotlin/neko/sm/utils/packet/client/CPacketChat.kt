package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.client.CPacket01Chat

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketChat(
    packet: CPacket01Chat
) : Packet<CPacket01Chat>(packet), CPacket01Chat {

    constructor(message: String) : this(network.createChat(message))

    override fun getMessage(): String {
        return packet.message
    }

    override fun setMessage(message: String) {
        packet.message = message
    }
}