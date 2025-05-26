package neko.sm.features.module.modules.combat

import neko.sm.features.module.PluginModule
import neko.sm.features.module.modules.combat.velocity.VelocityCancel
import neko.sm.utils.extension.cancel
import neko.sm.utils.packet.server.SPacketVelocity
import neko.sm.utils.packet.wrap
import today.opai.api.enums.EnumModuleCategory
import today.opai.api.events.EventPacketReceive

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
        ), VelocityCancel
    )

    override fun onPacketReceive(event: EventPacketReceive) {
        val packet = event.packet.wrap()

        if (packet is SPacketVelocity && packet.entityId == API.localPlayer.entityId) {
            mode.current.onVelocityReceive(packet) {
                event.cancel()
            }
        }
    }
}