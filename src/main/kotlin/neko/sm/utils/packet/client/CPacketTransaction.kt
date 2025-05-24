package neko.sm.utils.packet.client

import neko.sm.utils.misc.network
import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.client.CPacket0FTransaction

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

class CPacketTransaction(
    packet: CPacket0FTransaction
) : Packet<CPacket0FTransaction>(packet), CPacket0FTransaction {

    constructor(
        windowId: Int,
        uid: Short,
        accepted: Boolean
    ) : this(network.createTransaction(windowId, uid, accepted))

    override fun getWindowId(): Int {
        return packet.windowId
    }

    override fun getUid(): Short {
        return packet.uid
    }

    override fun setUid(uid: Short) {
        packet.uid = uid
    }

    override fun isAccepted(): Boolean {
        return packet.isAccepted
    }
}