package neko.sm.utils.animation

/**
 * @author yuchenxue
 * @date 2025/02/21
 */
interface Animation<T, S: Animation<T, S>> {

    /**
     * 运行动画
     *
     * @return 插值结果
     */
    fun animate(): T

    /**
     * 检查动画是否结束
     */
    fun hasFinished(): Boolean

    /**
     * 重置这个动画
     */
    fun reset(): S

    /**
     * 强制结束动画，会直接到目标值
     */
    fun finish(): S
}
