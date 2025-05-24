package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.client.CPacket09SlotChange

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketSlotChange(
    packet: CPacket09SlotChange
) : Packet<CPacket09SlotChange>(packet), CPacket09SlotChange {

    constructor(
        slot: Int
    ) : this(network.createSwitchItem(slot))

    override fun getSlot(): Int {
        return packet.slot
    }

    override fun setSlot(slot: Int) {
        packet.slot = slot
    }
}