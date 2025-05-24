package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.client.api.AbstractCPacketPlayer
import today.opai.api.interfaces.game.network.client.CPacket03Player

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

open class CPacketPlayer(
    packet: CPacket03Player
) : AbstractCPacketPlayer<CPacket03Player>(packet), CPacket03Player {

    constructor(onGround: Boolean) : this(network.createPlayer(onGround))

    override fun isOnGround(): Boolean {
        return packet.isOnGround
    }

    override fun setOnGround(onGround: Boolean) {
        packet.isOnGround = onGround
    }
}