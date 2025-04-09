package neko.sm.utils.animation

import neko.sm.utils.time.TimeWatch

/**
 * @author yuchenxue
 * @date 2025/02/20
 */
class SimpleAnimation(private var type: AnimationType) : Animation {

    var start = 0f
        private set
    var target = 0f
        private set
    var duration= 200f
        private set

    val watch: TimeWatch = TimeWatch()

    private var finished = false

    override fun animate(): Float {
        val time = watch.passTime / duration
        val result = start + (target - start) * type.apply(time)

//        if (abs(result - target) <= 0.01) {
//            finished = true;
//            return target;
//        }

        if (hasFinished()) {
            return target
        }

        return result
    }

    override fun hasFinished(): Boolean {
        return watch.passTime >= duration || finished
    }

    fun setStart(start: Float) = apply {
        this.start = start
    }

    fun setTarget(target: Float) = apply {
        this.target = target
    }

    fun setDuration(duration: Float) = apply {
        this.duration = duration
    }

    fun setType(type: AnimationType) = apply {
        this.type = type
    }

    fun reset() = apply {
        watch.reset()
        finished = false
    }

    fun forceFinish() = apply {
        finished = true
    }

    override fun toString(): String {
        return "target:$target start:$start duration:$duration finished:$finished"
    }
}