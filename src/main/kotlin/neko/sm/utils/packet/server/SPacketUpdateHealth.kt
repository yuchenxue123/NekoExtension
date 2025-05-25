package neko.sm.utils.packet.server

import neko.sm.utils.packet.Packet
import today.opai.api.interfaces.game.network.server.SPacket06UpdateHealth

/**
 * @author yuchenxue
 * @date 2025/05/25
 */

class SPacketUpdateHealth(
    packet: SPacket06UpdateHealth
) : Packet<SPacket06UpdateHealth>(packet), SPacket06UpdateHealth {
    override fun getHealth(): Float {
        return packet.health
    }

    override fun getSaturation(): Float {
        return packet.saturation
    }

    override fun getFoodLevel(): Int {
        return packet.foodLevel
    }

    override fun setHealth(health: Float) {
        return packet.setHealth(health)
    }

    override fun setSaturation(saturation: Float) {
        packet.saturation = saturation
    }

    override fun setFoodLevel(foodlevel: Int) {
        packet.foodLevel = foodlevel
    }
}