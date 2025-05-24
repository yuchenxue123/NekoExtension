package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.client.CPacket0ASwing

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketAnimation(
    packet: CPacket0ASwing
) : Packet<CPacket0ASwing>(packet), CPacket0ASwing {

    constructor() : this(network.createSwing())
}