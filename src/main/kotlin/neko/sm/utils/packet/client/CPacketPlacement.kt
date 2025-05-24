package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.dataset.Vector3i
import today.opai.api.interfaces.game.item.ItemStack
import today.opai.api.interfaces.game.network.client.CPacket08Placement

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketPlacement(
    packet: CPacket08Placement
) : Packet<CPacket08Placement>(packet), CPacket08Placement {

    constructor(
        itemStack: ItemStack,
    ) : this(network.createUseItem(itemStack))

    override fun getItemStack(): ItemStack {
        return packet.itemStack
    }

    override fun getPosition(): Vector3i {
        return packet.position
    }

    override fun getPlaceDirection(): Int {
        return packet.placeDirection
    }

    override fun getOffsetX(): Float {
        return packet.offsetX
    }

    override fun getOffsetY(): Float {
        return packet.offsetY
    }

    override fun getOffsetZ(): Float {
        return packet.offsetZ
    }
}