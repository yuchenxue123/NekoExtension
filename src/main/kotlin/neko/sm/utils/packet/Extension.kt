package neko.sm.utils.packet

import neko.sm.utils.packet.client.*
import neko.sm.utils.packet.server.*
import today.opai.api.interfaces.game.network.NetPacket
import today.opai.api.interfaces.game.network.client.*
import today.opai.api.interfaces.game.network.server.*

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

fun NetPacket.wrap(): Packet<out NetPacket> {
    return transform(this)
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


        is SPacket0BAnimation -> SPacketAnimation(packet)
        is SPacket2AParticles -> SPacketParticle(packet)
        is SPacket2CEntitySpawn -> SPacketEntitySpawn(packet)
        is SPacket2FSetSlot -> SPacketSetSlot(packet)
        is SPacket02Chat -> SPacketChat(packet)
        is SPacket3ATabComplete -> SPacketTabComplete(packet)
        is SPacket3ETeams -> SPacketTeams(packet)
        is SPacket04Equipment -> SPacketEquipment(packet)
        is SPacket06UpdateHealth -> SPacketUpdateHealth(packet)
        is SPacket08SetPosition -> SPacketSetPosition(packet)
        is SPacket12Velocity -> SPacketVelocity(packet)
        is SPacket23BlockChange -> SPacketBlockChange(packet)
        is SPacket25BlockBreak -> SPacketBlockBreak(packet)
        is SPacket27Explosion -> SPacketExplosion(packet)
        is SPacket29Sound -> SPacketSound(packet)
        is SPacket45Title -> SPacketTitle(packet)
        is SPacket48ResourcePacket -> SPacketResource(packet)


        else -> Packet(packet)
    }
}