package neko.sm.utils.always

import neko.sm.utils.interfaces.Accessor
import today.opai.api.enums.EnumNotificationType
import today.opai.api.events.*
import today.opai.api.interfaces.modules.PresetModule

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

interface Project : Accessor {
    val isRunning: Boolean
        get() = true

    fun onTick() {}
    fun onPlayerUpdate() {}
    fun onLoop() {}
    fun onLoadWorld() {}

    fun onRenderNameTags(event: EventRenderNameTag) {}

    fun onNotification(type: EnumNotificationType, title: String, content: String, duration: Long) {}

    fun onMotionUpdate(event: EventMotionUpdate) {}

    fun onModuleToggle(module: PresetModule, state: Boolean) {}

    fun onStrafe(event: EventStrafe) {}

    fun onMoveInput(event: EventMoveInput) {}

    fun onMove(event: EventMove) {}

    fun onKey(keyCode: Int) {}

    fun onSlowdown(event: EventSlowdown) {}

    fun onJump(event: EventJump) {}

    fun onChatReceive(event: EventChatReceived) {}

    fun onChatSend(event: EventChatSend) {}

    fun onPacketSend(event: EventPacketSend) {}

    fun onPacketReceive(event: EventPacketReceive) {}

    fun onRender2D(event: EventRender2D) {}

    fun onRender3D(event: EventRender3D) {}
}