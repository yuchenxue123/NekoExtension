package neko.sm.module.modules.combat

import neko.sm.module.PluginModule
import neko.sm.module.modules.combat.velocity.VelocityCancel
import neko.sm.utils.extension.cancel
import today.opai.api.enums.EnumModuleCategory
import today.opai.api.events.EventPacketReceive
import today.opai.api.interfaces.game.network.server.SPacket12Velocity

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object ModuleVelocity : PluginModule(
    "Velocity",
    "Reduce your knock back.",
    EnumModuleCategory.COMBAT
) {

    val mode = choices("Mode",
        arrayOf(
            VelocityCancel
        ), VelocityCancel)

    override fun onPacketReceive(event: EventPacketReceive) {
        val packet = event.packet

        if (packet is SPacket12Velocity && packet.entityId == API.localPlayer.entityId) {
            mode.current.onVelocityReceive(packet) {
                event.cancel()
            }
        }
    }
}