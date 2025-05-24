package neko.sm.utils.packet

import neko.sm.utils.packet.client.*
import today.opai.api.interfaces.game.network.NetPacket
import today.opai.api.interfaces.game.network.client.*

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

@Suppress("UNCHECKED_CAST")
fun NetPacket.wrap(): Packet<NetPacket> {
    return transform(this) as Packet<NetPacket>
}

private fun transform(packet: NetPacket): Packet<out NetPacket> {
    return when(packet) {
        is CPacket00KeepAlive -> CPacketKeepAlive(packet)
        is CPacket0ASwing -> CPacketAnimation(packet)
        is CPacket0BEntityAction -> CPacketEntityAction(packet)
        is CPacket0DCloseWindow -> CPacketCloseWindow(packet)
        is CPacket0FTransaction -> CPacketTransaction(packet)
        is CPacket0EClickWindow -> CPacketClickWindow(packet)
        is CPacket01Chat -> CPacketChat(packet)

        is CPacket06PositionRotation -> CPacketPositionRotation(packet)
        is CPacket05Rotation -> CPacketRotation(packet)
        is CPacket04Position -> CPacketPosition(packet)
        is CPacket03Player -> CPacketPlayer(packet)

        is CPacket07Digging -> CPacketDigging(packet)
        is CPacket08Placement -> CPacketPlacement(packet)
        is CPacket09SlotChange -> CPacketSlotChange(packet)

        else -> Packet(packet)
    }
}