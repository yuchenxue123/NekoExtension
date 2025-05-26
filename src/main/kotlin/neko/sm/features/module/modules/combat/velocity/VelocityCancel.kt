package neko.sm.features.module.modules.combat.velocity

import today.opai.api.interfaces.game.network.server.SPacket12Velocity

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object VelocityCancel : VelocityMode("Cancel") {
    override fun onVelocityReceive(packet: SPacket12Velocity, cancel: () -> Unit) {
        cancel.invoke()
    }
}