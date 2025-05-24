package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.item.ItemStack
import today.opai.api.interfaces.game.network.client.CPacket0EClickWindow

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketClickWindow(
    packet: CPacket0EClickWindow
) : Packet<CPacket0EClickWindow>(packet), CPacket0EClickWindow {

    constructor(
        windowId: Int,
        slot: Int,
        button: Int,
        mode: Int,
        item: ItemStack,
        actionNumber: Short
    ) : this(network.createClickWindow(windowId, slot, button, mode, item, actionNumber))

    override fun getWindowId(): Int {
        return packet.windowId
    }

    override fun getSlotId(): Int {
        return packet.windowId
    }

    override fun getUsedButton(): Int {
        return packet.usedButton
    }

    override fun getActionNumber(): Int {
        return packet.actionNumber
    }

    override fun getItem(): ItemStack {
        return packet.item
    }

    override fun getMode(): Int {
        return packet.mode
    }
}