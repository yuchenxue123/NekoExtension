package neko.sm.features.gui.component.components.arraylist

import neko.sm.client.module.category
import neko.sm.client.module.color
import neko.sm.client.module.suffix
import neko.sm.features.gui.Screen
import neko.sm.features.module.modules.render.ModuleSimpleHUD
import neko.sm.font.FontManager
import neko.sm.utils.animation.AnimationType
import neko.sm.utils.animation.SimpleAnimation
import neko.sm.utils.extension.removeBlank
import neko.sm.utils.render.RenderUtils
import today.opai.api.enums.EnumModuleCategory
import today.opai.api.events.EventRender2D
import today.opai.api.interfaces.modules.PresetModule
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/03/18
 */

abstract class ArrayElement(
    val module: PresetModule,
    var renderX: Float,
    var renderY: Float
) : Screen {
    protected val name: String
        get() = if (ModuleSimpleHUD.nameSpace) module.name else module.name.removeBlank()
    protected val suffix: String
        get() = module.suffix

    val category: EnumModuleCategory = module.category
    val color: Color = category.color

    abstract val width: Float
    abstract val height: Float
    abstract val isHidden: Boolean

    abstract fun update(renderY: Float)
}

class SimpleArrayElement(
    module: PresetModule,
    renderX: Float,
    renderY: Float,
) : ArrayElement(module, renderX, renderY) {
    private val font = FontManager.ROBOTO_18

    companion object {
        private const val SIDE_LINE_WIDTH = 1f
        private const val LEFT_RIGHT_SPACE = 2f
        private const val NAME_SUFFIX_SPACE = 2f
        private const val TOP_BOTTOM_SPACE = 1f
    }

    // cs
    override val width: Float
        get() {
            return if (ModuleSimpleHUD.suffix && suffix.isNotEmpty()) {
                font.getWidth(name) + NAME_SUFFIX_SPACE + font.getWidth(suffix) + LEFT_RIGHT_SPACE * 2
            } else {
                font.getWidth(name) + LEFT_RIGHT_SPACE * 2
            }
        }

    override val height: Float
        get() = font.height + TOP_BOTTOM_SPACE * 2

    // -2f -> 向里一点
    private val hideX: Float
        get() = -width - 2f

    private val showX: Float = renderX

    // animation
    private val animationX = SimpleAnimation.create().type(AnimationType.QUAD_OUT).duration(200f).finish()
    private val animationY = SimpleAnimation.create().type(AnimationType.QUAD_OUT).duration(200f).finish()

    // state
    private var state = false

    override fun render(event: EventRender2D) {
        update()

        if (renderX <= hideX) {
            animationY.finish()
        }

        renderX = animationX.animate()
        renderY = animationY.animate()

        // side
        if (ModuleSimpleHUD.sideline) {
            RenderUtils.drawRect(
                renderX, renderY,
                SIDE_LINE_WIDTH, height,
                color
            )
        }

        val side = if (ModuleSimpleHUD.sideline) SIDE_LINE_WIDTH else 0f

        // back
        RenderUtils.drawRect(
            renderX + side, renderY,
            width, height,
            Color(40, 40, 40, 120)
        )

        // text
        font.drawStringWithShadow(
            name,
            renderX + side + LEFT_RIGHT_SPACE,
            renderY + TOP_BOTTOM_SPACE,
            color.rgb
        )

        if (ModuleSimpleHUD.suffix) {
            font.drawStringWithShadow(
                suffix,
                renderX + side + LEFT_RIGHT_SPACE + font.getWidth(name) + NAME_SUFFIX_SPACE,
                renderY + TOP_BOTTOM_SPACE,
                Color.WHITE.rgb
            )
        }
    }

    private fun update() {
        val newState = module.isEnabled

        if (newState == state) {
             if (state) {
                animationX.target(showX)
            } else {
                animationX.target(hideX)
            }
            return
        }

        if (newState) {
            if (animationX.target != showX) {
                animationX.type(AnimationType.QUAD_OUT)
                    .start(renderX)
                    .target(showX)
                    .reset()
            }
        } else {
            if (animationX.target != hideX) {
                animationX.type(AnimationType.QUAD_IN)
                    .start(renderX)
                    .target(hideX)
                    .reset()
            }
        }
        this.state = newState
    }

    override val isHidden: Boolean
        get() = module.isHidden || (!module.isEnabled && renderX <= hideX)

    override fun update(renderY: Float) {
        if (renderY == animationY.target || renderY == this.renderY) {
            return
        }
        animationY.start(this.renderY).target(renderY).reset()
    }
}