package neko.sm.utils.packet.client.api

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.client.CPacket03Player

/**
 * @author yuchenxue
 * @date 2025/05/24
 */

abstract class AbstractCPacketPlayer<T : CPacket03Player>(
    packet: T
) : Packet<T>(packet), CPacket03Player