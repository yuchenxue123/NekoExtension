package neko.sm.module.modules.movement.fly

import neko.sm.utils.always.projects.MoveProject
import neko.sm.utils.extension.cancel
import neko.sm.utils.extension.motionX
import neko.sm.utils.extension.motionY
import neko.sm.utils.extension.motionZ
import neko.sm.utils.move.Movement
import today.opai.api.events.EventPacketReceive
import today.opai.api.interfaces.game.network.client.CPacket03Player
import today.opai.api.interfaces.game.network.server.SPacket08SetPosition
import today.opai.api.interfaces.game.network.server.SPacket27Explosion

/**
 * @author yuchenxue
 * @date 2025/03/15
 */

object FlyExplosion : FlyMode("Explosion") {
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
        val packet = event.packet

        if (packet is SPacket27Explosion) {
            fly = true
        }

        if (packet is CPacket03Player) {
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