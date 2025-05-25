package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.client.CPacket03Player

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

// special
open class CPacketPlayer<T : CPacket03Player>(
    packet: T
) : Packet<T>(packet), CPacket03Player {

    companion object {
        fun create(onGround: Boolean) : CPacketPlayer<CPacket03Player> {
            return CPacketPlayer(network.createPlayer(onGround))
        }
    }

    override fun isOnGround(): Boolean {
        return packet.isOnGround
    }

    override fun setOnGround(onGround: Boolean) {
        packet.isOnGround = onGround
    }
}