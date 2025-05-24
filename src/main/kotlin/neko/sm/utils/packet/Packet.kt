package neko.sm.utils.packet

import today.opai.api.interfaces.game.network.NetPacket

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

open class Packet<T : NetPacket>(
    protected val packet: T
) : NetPacket {
    override fun sendPacket() {
        packet.sendPacket()
    }

    override fun sendPacketNoEvent() {
        packet.sendPacketNoEvent()
    }

    fun unwrap(): NetPacket = packet
}