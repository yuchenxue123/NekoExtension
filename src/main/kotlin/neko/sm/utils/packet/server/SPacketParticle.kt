package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.dataset.Vec3Data
import today.opai.api.interfaces.game.network.server.SPacket2AParticles

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketParticle(
    packet: SPacket2AParticles
) : Packet<SPacket2AParticles>(packet), SPacket2AParticles {
    override fun getType(): String {
        return packet.type
    }

    override fun getPosition(): Vec3Data {
        return packet.position
    }

    override fun getOffset(): Vec3Data {
        return packet.offset
    }

    override fun getSpeed(): Float {
        return packet.speed
    }

    override fun getCount(): Int {
        return packet.count
    }

    override fun isLongDistance(): Boolean {
        return packet.isLongDistance
    }

    override fun getArgs(): IntArray {
        return packet.args
    }

    override fun setType(type: String) {
        packet.type = type
    }

    override fun setPosition(position: Vec3Data) {
        packet.position = position
    }

    override fun setOffset(offset: Vec3Data) {
        packet.offset = offset
    }

    override fun setSpeed(speed: Float) {
        packet.speed = speed
    }

    override fun setCount(count: Int) {
        packet.count = count
    }

    override fun setLongDistance(longDistance: Boolean) {
        packet.isLongDistance = longDistance
    }

    override fun setArgs(args: IntArray) {
        packet.args = args
    }
}