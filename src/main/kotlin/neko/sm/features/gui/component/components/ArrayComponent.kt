package neko.sm.features.gui.component.components

import neko.sm.client.module.getModeValue
import neko.sm.client.module.suffix
import neko.sm.features.gui.Screen
import neko.sm.features.gui.component.Component
import neko.sm.features.module.modules.render.ModuleSimpleHUD
import neko.sm.font.FontManager
import neko.sm.utils.animation.AnimationType
import neko.sm.utils.animation.SimpleAnimation
import neko.sm.utils.render.ColorUtils
import today.opai.api.events.EventRender2D
import today.opai.api.interfaces.modules.PresetModule
import java.awt.Color
import java.util.stream.Collectors

/**
 * @author yuchenxue
 * @date 2025/02/21
 */

object ArrayComponent : Component {
    private val font = FontManager.ROBOTO_18

    private val elements = mutableListOf<ArrayElement>()

    init {
        buildElements()
    }

    override val condition: Boolean
        get() = ModuleSimpleHUD.array

    override fun render(event: EventRender2D) {
        refreshElements()

        for (element in elements) {
            if (element.isHidden) {
                continue
            }

            element.render(event)
        }
    }

    private fun refreshElements() {
        var y = renderY
        for (element in elements) {
            if (element.isHidden) {
                continue
            }

            element.setRenderY(y)
            y += element.height
        }

        val modules: List<PresetModule> = API.moduleManager.modules.stream()
            .filter { m ->
                !elements.stream().map { element: ArrayElement -> element.module }
                    .collect(Collectors.toList())
                    .contains(m)
            }.collect(Collectors.toList())

        if (modules.isNotEmpty()) {
            modules.forEach { m: PresetModule -> elements.add(ArrayElement(m)) }
        }

        sort()
    }

    private fun sort() {
        if (ModuleSimpleHUD.suffix) {
            elements.sortWith { o1, o2 ->
                val s1: String = o1.module.suffix
                val n1 = (o1.module.name
                        + (if (s1.isEmpty()) "" else toRenderSuffix(s1)))

                val s2: String = o2.module.suffix
                val n2 = (o2.module.name
                        + (if (s2.isEmpty()) "" else toRenderSuffix(s2)))

                font.getWidth(n2) - font.getWidth(n1)
            }
        } else {
            elements.sortWith { o1: ArrayElement, o2: ArrayElement ->
                val n1 = o1.module.name
                val n2 = o2.module.name
                font.getWidth(n2) - font.getWidth(n1)
            }
        }
    }

    private fun buildElements() {
        elements.clear()

        val modules: List<PresetModule> = API.moduleManager.modules.stream()
            .sorted(({ o1, o2 -> font.getWidth(o2.name) - font.getWidth(o1.name) }
                    )).collect(Collectors.toList())

        var y = renderY
        for (module in modules) {
            val element = ArrayElement(module)
            element.setRenderY(y)
            elements.add(element)
            y += element.height
        }

        refreshElements()
    }

    private fun toRenderSuffix(suffix: String): String {
        return "  [$suffix]"
    }

    private val renderX: Float
        get() = WatermarkComponent.X_POSITION

    private val renderY: Float
        get() = WatermarkComponent.Y_POSITION + WatermarkComponent.getHeight() + 5f


    // subtract
    private class ArrayElement(val module: PresetModule) : Screen {
        // position
        private var xPos: Float = renderX
        private var yPos: Float = renderY

        private var targetY = -999f

        // set
        private var width = 0f
        val height = font.height + 2f

        // animation
        private val animationX = SimpleAnimation.create().type(AnimationType.QUAD_OUT)
        private val animationY = SimpleAnimation.create().type(AnimationType.QUAD_OUT)

        // state
        private var state = false

        private var hideX = 0f
        private var showX = 0f

        init {
            this.width = font.getWidth(module.name).toFloat() + 4f
            this.xPos = -width - 10f

            this.hideX = -width - 10f
            this.showX = renderX

            updateModuleState()
        }

        override fun render(event: EventRender2D) {
            updateModuleState()

            if (xPos <= hideX) {
                // 直接从正确位置出来
                animationY.finish()
            }
            xPos = animationX.animate()
            yPos = animationY.animate()

            val text = module.name

            font.drawStringWithShadow(
                text,
                xPos.toDouble() + (width - font.getWidth(text)) / 2f,
                yPos.toDouble() + (height - font.height) / 2f,
                ColorUtils.getModuleColor(module).rgb
            )

            if (ModuleSimpleHUD.suffix) {
                val modeValue = module.getModeValue("Mode")
                if (modeValue != null) {
                    val mode = toRenderSuffix(modeValue.value)
                    font.drawStringWithShadow(
                        mode,
                        (xPos + font.getWidth(text)).toDouble(),
                        yPos.toDouble(),
                        Color(215, 215, 215).rgb
                    )
                }
            }
        }

        val isHidden: Boolean
            get() = module.isHidden || (!module.isEnabled && xPos <= hideX)

        fun setRenderY(y: Float) {
            if (y == targetY || y == yPos) {
                return
            }
            animationY.start(yPos).target(y).reset()
            targetY = y
        }

        fun updateModuleState() {
            val state = module.isEnabled

            if (this.state == state) {
                return
            }

            if (state) {
                if (animationX.target != renderX) {
                    animationX.type(AnimationType.QUAD_OUT)
                        .start(xPos)
                        .target(showX)
                        .reset()
                }
            } else {
                if (animationX.target != -width - 10f) {
                    animationX.type(AnimationType.QUAD_IN)
                        .start(xPos)
                        .target(hideX)
                        .reset()
                }
            }

            this.state = state
        }
    }
}
