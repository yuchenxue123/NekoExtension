package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.dataset.Vec3Data
import today.opai.api.interfaces.game.network.server.SPacket29Sound

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketSound(
    packet: SPacket29Sound
) : Packet<SPacket29Sound>(packet), SPacket29Sound {
    override fun getSound(): String {
        return packet.sound
    }

    override fun getPosition(): Vec3Data {
        return packet.position
    }

    override fun getVolume(): Float {
        return packet.volume
    }

    override fun getPitch(): Float {
        return packet.pitch
    }

    override fun setSound(sound: String) {
        packet.sound = sound
    }

    override fun setPosition(position: Vec3Data) {
        packet.position = position
    }

    override fun setVolume(volume: Float) {
        packet.volume = volume
    }
}