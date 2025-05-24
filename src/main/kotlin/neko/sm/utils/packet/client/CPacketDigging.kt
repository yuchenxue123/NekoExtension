package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.dataset.BlockPosition
import today.opai.api.enums.EnumDiggingAction
import today.opai.api.enums.EnumDirection
import today.opai.api.interfaces.dataset.Vector3i
import today.opai.api.interfaces.game.network.client.CPacket07Digging

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketDigging(
    packet: CPacket07Digging
) : Packet<CPacket07Digging>(packet), CPacket07Digging {

    constructor(
        position: BlockPosition,
        action: EnumDiggingAction,
        direction: EnumDirection
    ) : this(network.createDigging(position, action, direction))



    override fun getPosition(): Vector3i {
        return packet.position
    }

    override fun getAction(): EnumDiggingAction {
        return packet.action
    }

    override fun getDirection(): EnumDirection {
        return packet.direction
    }

}