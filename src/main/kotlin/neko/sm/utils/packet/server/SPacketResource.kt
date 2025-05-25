package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.server.SPacket48ResourcePacket

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketResource(
    packet: SPacket48ResourcePacket
) : Packet<SPacket48ResourcePacket>(packet), SPacket48ResourcePacket {
    override fun getUrl(): String {
        return packet.url
    }

    override fun getHash(): String {
        return packet.hash
    }

    override fun setUrl(url: String) {
        packet.url = url
    }

    override fun setHash(hash: String) {
        packet.hash = hash
    }
}