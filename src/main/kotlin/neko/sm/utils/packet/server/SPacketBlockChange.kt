package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.dataset.Vec3Data
import today.opai.api.interfaces.game.network.server.SPacket23BlockChange
import today.opai.api.interfaces.game.world.Block

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketBlockChange(
    packet: SPacket23BlockChange
) : Packet<SPacket23BlockChange>(packet), SPacket23BlockChange {
    override fun getPosition(): Vec3Data {
        return packet.position
    }

    override fun getBlock(): Block {
        return packet.block
    }
}