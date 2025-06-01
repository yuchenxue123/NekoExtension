package neko.sm.event

import kotlin.reflect.KClass

/**
 * @author yuchenxue
 * @date 2025/06/01
 */

object EventManager {
    private val registry = HashMap<KClass<out Event>, MutableList<EventHook<in Event>>>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Event> register(clazz: KClass<out Event>, hook: EventHook<in T>) {
        val hooks = registry.computeIfAbsent(clazz) { mutableListOf() }

        if (!hooks.contains(hook)) {
            hooks.add(hook as EventHook<in Event>)
        }
    }

    fun <T : Event> dispatch(event: T): T {
        val hooks = registry[event::class] ?: return event

        hooks.forEach handlers@ { hook ->

            if (!hook.listener.running) return@handlers

            runCatching {
                hook.handler.invoke(event)
            }.onFailure {
                println("Exception while executing handler.")
            }
        }

        return event
    }
}