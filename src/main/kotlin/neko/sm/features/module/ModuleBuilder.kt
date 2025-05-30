package neko.sm.features.module

import today.opai.api.enums.EnumModuleCategory
import today.opai.api.events.EventMotionUpdate
import today.opai.api.interfaces.EventHandler

/**
 * @author yuchenxue
 * @date 2025/05/30
 */

// for some simple module
class ModuleBuilder {
    companion object {
        fun create(): ModuleBuilder {
            return ModuleBuilder()
        }
    }

    private var name: String = "Empty"
    private var description: String = "None"
    private var category: EnumModuleCategory = EnumModuleCategory.MISC

    fun name(name: String): ModuleBuilder = apply {
        this.name = name
    }

    fun description(description: String): ModuleBuilder = apply {
        this.description = description
    }

    fun category(category: EnumModuleCategory): ModuleBuilder = apply {
        this.category = category
    }

    private var onEnable: () -> Unit = {}

    fun onEnable(onEnable: () -> Unit): ModuleBuilder = apply {
        this.onEnable = onEnable
    }

    private var onDisable: () -> Unit = {}

    fun onDisable(onDisable: () -> Unit): ModuleBuilder = apply {
        this.onDisable = onDisable
    }

    private var onTick: () -> Unit = {}

    fun onTick(onTick: () -> Unit): ModuleBuilder = apply {
        this.onTick = onTick
    }

    private var onUpdate: () -> Unit = {}

    fun onUpdate(onUpdate: () -> Unit): ModuleBuilder = apply {
        this.onUpdate = onUpdate
    }

    private var onLoop: () -> Unit = {}

    fun onLoop(onLoop: () -> Unit): ModuleBuilder = apply {
        this.onLoop = onLoop
    }

    private var onMotionUpdate: (event: EventMotionUpdate) -> Unit = {}

    fun onMotionUpdate(onMotion: (event: EventMotionUpdate) -> Unit): ModuleBuilder = apply {
        this.onMotionUpdate = onMotion
    }

    fun build(): PluginModule = object : PluginModule(name, description, category), EventHandler {

        override fun onEnabled() {
            onEnable.invoke()
        }

        override fun onDisabled() {
            onDisable.invoke()
        }

        override fun onPlayerUpdate() {
            onUpdate.invoke()
        }

        override fun onLoop() {
            onLoop.invoke()
        }

        override fun onMotionUpdate(event: EventMotionUpdate) {
            onMotionUpdate.invoke(event)
        }
    }
}