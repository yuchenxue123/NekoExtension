package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.client.CPacket0DCloseWindow

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketCloseWindow(
    packet: CPacket0DCloseWindow
) : Packet<CPacket0DCloseWindow>(packet), CPacket0DCloseWindow {

    constructor(windowId: Int) : this(network.createCloseWindow(windowId))

    override fun getWindowId(): Int {
        return packet.windowId
    }
}