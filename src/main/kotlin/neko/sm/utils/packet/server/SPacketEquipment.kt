package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.item.ItemStack
import today.opai.api.interfaces.game.network.server.SPacket04Equipment

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketEquipment(
    packet: SPacket04Equipment
) : Packet<SPacket04Equipment>(packet), SPacket04Equipment {
    override fun getEntityId(): Int {
        return packet.entityId
    }

    override fun getSlot(): Int {
        return packet.slot
    }

    override fun getItemStack(): ItemStack {
        return packet.itemStack
    }

    override fun setItemStack(itemStack: ItemStack) {
        packet.itemStack = itemStack
    }

    override fun setSlot(slot: Int) {
        packet.slot = slot
    }
}