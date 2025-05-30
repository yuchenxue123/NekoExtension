package neko.sm.features.module.modules.combat.velocity

import neko.sm.utils.extension.cancel
import neko.sm.utils.packet.client.CPacketEntityAction
import neko.sm.utils.packet.server.SPacketVelocity
import neko.sm.utils.packet.wrap
import today.opai.api.enums.EnumEntityAction
import today.opai.api.events.EventPacketReceive

/**
 * @author yuchenxue
 * @date 2025/05/30
 */

object AntiVelocityBlocksMC : AntiVelocityMode("BlocksMC") {

    private var hasReceiveVelocity = false

    override fun onPacketReceive(event: EventPacketReceive) {
        val packet = event.packet.wrap()

        if (packet is SPacketVelocity && packet.entityId == player.entityId) {
            hasReceiveVelocity = true
            event.cancel()
            CPacketEntityAction(EnumEntityAction.START_SNEAKING).sendPacket()
            CPacketEntityAction(EnumEntityAction.STOP_SNEAKING).sendPacket()
        }

        if (hasReceiveVelocity && packet is CPacketEntityAction) {
            hasReceiveVelocity = false
            event.cancel()
        }
    }
}