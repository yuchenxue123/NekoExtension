package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.item.ItemStack
import today.opai.api.interfaces.game.network.server.SPacket2FSetSlot

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketSetSlot(
    packet: SPacket2FSetSlot
) : Packet<SPacket2FSetSlot>(packet), SPacket2FSetSlot {
    override fun getWindowId(): Int {
        return packet.windowId
    }

    override fun getSlot(): Int {
        return packet.slot
    }

    override fun getItemStack(): ItemStack {
        return packet.itemStack
    }

    override fun setWindowId(windowId: Int) {
        packet.windowId = windowId
    }

    override fun setSlot(slot: Int) {
        packet.slot = slot
    }

    override fun setItemStack(itemStack: ItemStack) {
        packet.itemStack
    }
}