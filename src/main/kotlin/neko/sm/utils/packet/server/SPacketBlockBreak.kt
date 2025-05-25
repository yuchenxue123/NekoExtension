package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.server.SPacket25BlockBreak
import today.opai.api.interfaces.game.world.Block

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketBlockBreak(
    packet: SPacket25BlockBreak
) : Packet<SPacket25BlockBreak>(packet), SPacket25BlockBreak {
    override fun getEntityId(): Int {
        return packet.entityId
    }

    override fun getBlock(): Block {
        return packet.block
    }

    override fun getProgress(): Int {
        return packet.progress
    }
}