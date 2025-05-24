package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.client.CPacket00KeepAlive

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketKeepAlive(
    packet: CPacket00KeepAlive
) : Packet<CPacket00KeepAlive>(packet), CPacket00KeepAlive {

    constructor(key: Int) : this(network.createKeepAlive(key))

    override fun getKey(): Int {
        return packet.key
    }
}