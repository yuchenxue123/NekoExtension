package neko.sm.features.module.modules.combat.velocity

import neko.sm.utils.extension.cancel
import neko.sm.utils.packet.server.SPacketVelocity
import neko.sm.utils.packet.wrap
import today.opai.api.events.EventPacketReceive

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object AntiVelocityCancel : AntiVelocityMode("Cancel") {

    override fun onPacketReceive(event: EventPacketReceive) {
        val packet = event.packet.wrap()

        if (packet is SPacketVelocity && packet.entityId == player.entityId) {
            event.cancel()
        }
    }
}