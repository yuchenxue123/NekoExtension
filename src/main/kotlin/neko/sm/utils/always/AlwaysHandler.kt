package neko.sm.utils.always

import neko.sm.utils.always.ProjectManager.projects
import neko.sm.utils.interfaces.Accessor
import today.opai.api.enums.EnumNotificationType
import today.opai.api.events.*
import today.opai.api.interfaces.EventHandler
import today.opai.api.interfaces.modules.PresetModule

/**
 * @author yuchenxue
 * @date 2025/02/22
 */

object AlwaysHandler : EventHandler, Accessor {

    override fun onTick() {
        projects.forEach(Project::onTick)
    }

    override fun onPlayerUpdate() {
        projects.forEach(Project::onPlayerUpdate)
    }

    override fun onLoop() {
        projects.forEach(Project::onLoop)
    }

    override fun onLoadWorld() {
        projects.forEach(Project::onLoadWorld)
    }

    override fun onRenderNameTags(event: EventRenderNameTag) {
        projects.forEach { it.onRenderNameTags(event) }
    }

    override fun onNotification(
        notificationType: EnumNotificationType,
        title: String,
        content: String,
        during: Long
    ) {
        projects.forEach { it.onNotification(notificationType, title, content, during) }
    }

    override fun onMotionUpdate(event: EventMotionUpdate) {
        projects.forEach { it.onMotionUpdate(event) }
    }

    override fun onModuleToggle(module: PresetModule, state: Boolean) {
        projects.forEach { it.onModuleToggle(module, state) }
    }

    override fun onStrafe(event: EventStrafe) {
        projects.forEach { it.onStrafe(event) }
    }

    override fun onMoveInput(event: EventMoveInput) {
        projects.forEach { it.onMoveInput(event) }
    }

    override fun onMove(event: EventMove) {
        projects.forEach { it.onMove(event) }
    }

    override fun onKey(keyCode: Int) {
        projects.forEach { it.onKey(keyCode) }
    }

    override fun onSlowdown(event: EventSlowdown) {
        projects.forEach { it.onSlowdown(event) }
    }

    override fun onJump(event: EventJump) {
        projects.forEach { it.onJump(event) }
    }

    override fun onChat(event: EventChatReceived) {
        projects.forEach { it.onChatReceive(event) }
    }

    override fun onChat(event: EventChatSend) {
        projects.forEach { it.onChatSend(event) }
    }

    override fun onPacketSend(event: EventPacketSend) {
        projects.forEach { it.onPacketSend(event) }
    }

    override fun onPacketReceive(event: EventPacketReceive) {
        projects.forEach { it.onPacketReceive(event) }
    }

    override fun onRender2D(event: EventRender2D) {
        projects.forEach { it.onRender2D(event) }
    }

    override fun onRender3D(event: EventRender3D) {
        projects.forEach { it.onRender3D(event) }
    }
}
