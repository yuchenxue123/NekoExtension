package neko.sm.features.module.modules.movement.flight

import neko.sm.utils.always.projects.MoveProject
import neko.sm.utils.extension.cancel
import neko.sm.utils.extension.motionX
import neko.sm.utils.extension.motionY
import neko.sm.utils.extension.motionZ
import neko.sm.utils.move.Movement
import neko.sm.utils.packet.client.CPacketPlayer
import neko.sm.utils.packet.server.SPacketExplosion
import neko.sm.utils.packet.wrap
import today.opai.api.events.EventPacketReceive
import today.opai.api.interfaces.game.network.server.SPacket08SetPosition

/**
 * @author yuchenxue
 * @date 2025/03/15
 */

object FlightExplosion : FlightMode("Explosion") {
    private var fly = false

    override fun onPlayerUpdate() {
        val player = API.localPlayer ?: return

        if (!player.isOnGround && fly) {
            val airTicks = MoveProject.airTicks

            if (airTicks <= 0) {
                return
            }

            when (airTicks) {
                in 1..4 -> {
                    player.motionY = 0.7 + 0.02 * airTicks
                    Movement.strafe(0.56f)
                }

                in 5..28 -> {
                    player.motionX = 0.0
                    player.motionY = 0.0
                    player.motionZ = 0.0
                    Movement.strafe(2.7774f - 2f / (airTicks - 3))
                }

                else -> {
                    fly = false
                }
            }
        }
    }

    override fun onPacketReceive(event: EventPacketReceive) {
        if (API.isNull) {
            return
        }
        val packet = event.packet.wrap()

        if (packet is SPacketExplosion) {
            fly = true
        }

        if (packet is CPacketPlayer) {
            if (packet.isOnGround && fly) {
                event.cancel()
                packet.isOnGround = false
                packet.sendPacket()
            }
        }

        if (packet is SPacket08SetPosition) {
            fly = false
        }
    }
}