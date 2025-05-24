package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.enums.EnumEntityAction
import today.opai.api.interfaces.game.network.client.CPacket0BEntityAction

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketEntityAction(
    packet: CPacket0BEntityAction
) : Packet<CPacket0BEntityAction>(packet), CPacket0BEntityAction {

    constructor(action: EnumEntityAction) : this(network.createEntityAction(action))

    override fun getAction(): EnumEntityAction {
        return packet.action
    }

    override fun setAction(action: EnumEntityAction) {
        packet.action = action
    }
}