package neko.sm.utils.animation.multi

import neko.sm.utils.animation.Animation
import neko.sm.utils.animation.SimpleAnimation
import neko.sm.utils.time.TimeWatch

/**
 * @author yuchenxue
 * @date 2025/02/21
 */
class TripleAnimation(
    private val first: SimpleAnimation,
    private val second: SimpleAnimation,
    private val third: SimpleAnimation
) : Animation {
    private val duration = first.duration + second.duration + third.duration

    private val watch = TimeWatch()

    private var finished = false

    override fun animate(): Float {
        val passTime = watch.passTime
        return if (passTime <= first.duration) {
            first.animate()
        } else if (passTime > first.duration && passTime <= first.duration + second.duration) {
            second.animate()
        } else if (passTime > first.duration + second.duration && passTime <= duration) {
            third.animate()
        } else {
            third.target
        }
    }

    override fun hasFinished(): Boolean {
        return watch.passTime >= duration || finished
    }

    fun reset() = apply {
        watch.reset()
        finished = false
        return this
    }

    fun forceFinish() = apply {
        this.finished = true
        return this
    }
}
