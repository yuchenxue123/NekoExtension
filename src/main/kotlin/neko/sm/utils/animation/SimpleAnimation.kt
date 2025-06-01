package neko.sm.utils.animation

import neko.sm.utils.time.TimeTracker

/**
 * @author yuchenxue
 * @date 2025/02/20
 */

class SimpleAnimation : Animation<Float, SimpleAnimation> {

    companion object {
        fun create(): SimpleAnimation {
            return SimpleAnimation()
        }
    }

    /**
     * Animation type
     */
    var type: AnimationType = AnimationType.NONE
        private set

    /**
     * The animation start value.
     */
    var start: Float = 0f
        private set

    /**
     * The animation target value.
     */
    var target: Float = 0f
        private set

    /**
     * The duration we complete the animation.
     */
    var duration: Float = 200f
        private set


    fun type(type: AnimationType) = apply {
        this.type = type
    }

    fun start(start: Float) = apply {
        this.start = start
    }

    fun target(target: Float) = apply {
        this.target = target
    }

    fun duration(duration: Float) = apply {
        this.duration = duration
    }

    val tracker: TimeTracker = TimeTracker()
    private var finished = false

    override fun animate(): Float {
        if (hasFinished()) {
            return target
        }

        val time = tracker.elapsed / duration
        val result = start + (target - start) * type.apply(time)

        return result
    }

    override fun hasFinished(): Boolean {
        return tracker.elapsed >= duration || finished
    }

    override fun reset() = apply {
        tracker.reset()
        finished = false
    }

    override fun finish() = apply {
        finished = true
    }

    override fun toString(): String {
        return "target:$target start:$start duration:$duration finished:$finished"
    }
}