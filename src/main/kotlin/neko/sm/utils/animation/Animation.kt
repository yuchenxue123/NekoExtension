package neko.sm.utils.animation

/**
 * @author yuchenxue
 * @date 2025/02/21
 */
interface Animation {
    fun animate(): Float
    fun hasFinished(): Boolean
}
