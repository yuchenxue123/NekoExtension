package neko.sm.utils.animation

/**
 * @author yuchenxue
 * @date 2025/05/27
 */

class AnimationSetting private constructor() {

    companion object {
        fun create(): AnimationSetting {
            return AnimationSetting()
        }
    }

    /**
     * Animation type
     */
    private var type: AnimationType = AnimationType.NONE

    /**
     * The animation start value.
     */
    private var start: Float = 0f

    /**
     * The animation target value.
     */
    private var target: Float = 0f

    /**
     * The duration we complete the animation.
     */
    private var duration: Float = 200f


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

    fun to(animation: SimpleAnimation) {
    }
}