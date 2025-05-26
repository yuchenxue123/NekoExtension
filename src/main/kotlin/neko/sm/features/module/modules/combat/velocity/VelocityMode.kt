package neko.sm.features.module.modules.combat.velocity

import neko.sm.features.module.modules.combat.ModuleVelocity
import neko.sm.value.choice.Choice
import neko.sm.value.choice.ChoicesSetting
import today.opai.api.interfaces.game.network.server.SPacket12Velocity

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

open class VelocityMode(name: String) : Choice(name) {
    override val parent: ChoicesSetting<*>
        get() = ModuleVelocity.mode

    open fun onVelocityReceive(packet: SPacket12Velocity, cancel: () -> Unit) {}
}