package neko.sm.utils.animation

/**
 * @author yuchenxue
 * @date 2025/02/20
 */
enum class AnimationType(private val function: (time: Float) -> Float) {
    NONE({ 1f }),
    LINEAR( { time -> time }),
    QUAD_IN({ time -> time * time }),
    QUAD_OUT({ time -> 1.0f - (1.0f - time) * (1.0f - time) }),
    ;

    fun apply(time: Float): Float {
        return function.invoke(time)
    }
}
