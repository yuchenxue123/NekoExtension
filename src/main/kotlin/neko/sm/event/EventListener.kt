package neko.sm.event

/**
 * @author yuchenxue
 * @date 2025/06/01
 */

typealias Handler<T> = (event: T) -> Unit

data class EventHook<T : Event>(
    val listener: EventListener,
    val handler : Handler<T>,
)

interface EventListener {
    val running: Boolean
        get() = true
}

inline fun <reified T : Event> EventListener.handler(
    noinline handler: Handler<T>
) {
    EventManager.register(T::class, EventHook(this, handler))
}