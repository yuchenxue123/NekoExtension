package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.server.SPacket45Title

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketTitle(
    packet: SPacket45Title
) : Packet<SPacket45Title>(packet), SPacket45Title {
    override fun getType(): String {
        return packet.type
    }

    override fun getMessage(): String {
        return packet.message
    }

    override fun getFadeInTime(): Int {
        return packet.fadeInTime
    }

    override fun getDisplayTime(): Int {
        return packet.displayTime
    }

    override fun getFadeOutTime(): Int {
        return packet.fadeOutTime
    }

    override fun setType(type: String) {
        packet.type = type
    }

    override fun setMessage(message: String) {
        packet.message = message
    }

    override fun setFadeInTime(fadeInTime: Int) {
        packet.fadeInTime = fadeInTime
    }

    override fun setDisplayTime(displayTime: Int) {
        packet.displayTime = displayTime
    }

    override fun setFadeOutTime(fadeOutTime: Int) {
        packet.fadeOutTime = fadeOutTime
    }
}