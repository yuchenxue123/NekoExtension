package neko.sm.utils.time

/**
 * @author yuchenxue
 * @date 2025/02/21
 */

class TimeTracker {
    private var time = System.nanoTime().div(1_000_000)

    /**
     * 检查是否已过指定时间长度
     * @param pass 要检查的时间长度（毫秒）
     *
     * @return [Boolean] true-超出指定时间，false-未超出
     */
    fun hasPassedTime(pass: Long): Boolean {
        return elapsed >= pass
    }

    /**
     * 获取剩余的时间
     * @param total 总时间长度（毫秒）
     *
     * @return 剩余时间（毫秒），超过则返回 0
     */
    fun getRemainingTime(total: Long): Long {
        return (total - elapsed).coerceAtLeast(0)
    }

    /**
     * 已经经过的时间
     */
    val elapsed: Long
        get() = System.nanoTime().div(1_000_000) - time

    /**
     * 重置计时器
     */
    fun reset() = apply {
        time = System.nanoTime().div(1_000_000)
    }
}
