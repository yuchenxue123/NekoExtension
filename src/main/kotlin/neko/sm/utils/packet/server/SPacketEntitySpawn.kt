package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.server.SPacket2CEntitySpawn

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketEntitySpawn(
    packet: SPacket2CEntitySpawn
) : Packet<SPacket2CEntitySpawn>(packet), SPacket2CEntitySpawn {
    override fun getEntityId(): Int {
        return packet.entityId
    }

    override fun getX(): Int {
        return packet.x
    }

    override fun getY(): Int {
        return packet.y
    }

    override fun getZ(): Int {
        return packet.z
    }

    override fun getType(): Int {
        return packet.type
    }

    override fun setEntityId(id: Int) {
        packet.entityId = id
    }

    override fun setX(x: Int) {
        packet.x = x
    }

    override fun setY(y: Int) {
        packet.y = y
    }

    override fun setZ(z: Int) {
        packet.z = z
    }

    override fun setType(type: Int) {
        packet.type = type
    }
}