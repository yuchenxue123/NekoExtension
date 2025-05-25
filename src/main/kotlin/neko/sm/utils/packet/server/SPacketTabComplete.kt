package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.server.SPacket3ATabComplete

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketTabComplete(
    packet: SPacket3ATabComplete
) : Packet<SPacket3ATabComplete>(packet), SPacket3ATabComplete {
    override fun getText(): Array<String> {
        return packet.text
    }

    override fun setText(text: Array<String>) {
        packet.text = text
    }
}