package neko.sm.gui.component.components

import neko.sm.font.FontManager
import neko.sm.gui.Screen
import neko.sm.gui.component.Component
import neko.sm.module.modules.render.ModuleSimpleHUD
import neko.sm.module.modules.render.ModuleSimpleHUD.NotificationType.BLACK
import neko.sm.module.modules.render.ModuleSimpleHUD.NotificationType.SIMPLE
import neko.sm.utils.always.projects.ScreenProject
import neko.sm.utils.animation.AnimationType
import neko.sm.utils.animation.SimpleAnimation
import neko.sm.utils.render.RenderUtils
import today.opai.api.events.EventRender2D
import today.opai.api.interfaces.modules.PresetModule
import today.opai.api.interfaces.render.WindowResolution
import java.awt.Color
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.math.max

/**
 * @author yuchenxue
 * @date 2025/02/21
 */

object NotificationsComponent : Component {
    private val font = FontManager.ROBOTO_20

    private val map = mutableMapOf<PresetModule, Boolean>()
    private val firstUpdateMap = mutableMapOf<PresetModule, Boolean>()

    private val notifications = CopyOnWriteArrayList<Notification>()

    override fun init() {
        API.moduleManager.modules.forEach { module -> map[module] = false }
        API.moduleManager.modules.forEach { module -> firstUpdateMap[module] = true }
    }

    override val condition: Boolean
        get() = ModuleSimpleHUD.notification

    override fun render(event: EventRender2D) {
        if (API.world == null) {
            return
        }

        refresh()

        for (notification in notifications) {
            notification.render(event)
        }
    }

    private fun refresh() {
        // 模块状态
        for (module in API.moduleManager.modules) {
            updateModuleState(module)
        }
    }

    private fun updateModuleState(module: PresetModule) {
        val state= module.isEnabled

        if (map.containsKey(module) && state == map[module]) {
            firstUpdateMap[module] = false
            map[module] = state
            return
        }

        if (firstUpdateMap.containsKey(module) && map.containsKey(module) && firstUpdateMap[module]!!) {
            firstUpdateMap[module] = false
            map[module] = state
            return
        }

        notifications.add(Notification(module, module.isEnabled))

        map[module] = state
    }

    private class Notification(
        private val module: PresetModule,
        private val state: Boolean
    ) : Screen {
        private val stateText = if (state) "§a" + "Enabled" else "§c" + "Disabled"
        private val context = module.name + " is " + stateText + "§r !"
        private val iconText = if (state) "§ao" else "§cp"

        private val icons = FontManager.ICONS_32
        private val smallFont = FontManager.ROBOTO_16

        private var xPos = 0f
        private var yPos = 0f

        private val width: Float
            get() = getCurrentWidth()
        private val height: Float
            get() = getCurrentHeight()

        // animation
        private val showAnimation = SimpleAnimation(AnimationType.QUAD_OUT).setDuration(400f)
        private val stayAnimation = SimpleAnimation(AnimationType.NONE).setDuration(500f)
        private val fadeAnimation = SimpleAnimation(AnimationType.QUAD_IN).setDuration(400f)

        private val yAnimation = SimpleAnimation(AnimationType.QUAD_OUT).setDuration(200f)

        private var stay = false
        private var fade = false

        init {
            ScreenProject.screen?.let {
                this.xPos = it.width.toFloat()
                updateYPos(it, true)

                showAnimation.setStart(it.width.toFloat())
                    .setTarget(it.width - width - 2f)
                    .reset()
            }
        }

        fun getAllHeight(): Float {
            val i = notifications.indexOf(this)
            return if (i == -1) -999f else ((i + 1) * (height + 5f) - 5f)
        }

        private fun updateYPos(sr: WindowResolution, init: Boolean = false) {
            val updatedY = sr.height - getAllHeight() - 10f
            if (updatedY == yPos || updatedY == yAnimation.target) {
                return
            }

            if (init) {
                this.yPos = updatedY
                return
            }

            yAnimation.setStart(yPos).setTarget(updatedY).reset()
        }

        override fun render(event: EventRender2D) {
            updateYPos(event.windowResolution)

            this.yPos = yAnimation.animate()

            if (!stay && !fade) {
                xPos = showAnimation.animate()
                if (showAnimation.hasFinished()) {
                    stay = true
                    stayAnimation.setTarget(event.windowResolution.width - width - 2f).reset()
                }
            }

            if (stay) {
                xPos = stayAnimation.animate()
                if (stayAnimation.hasFinished()) {
                    fade = true
                    stay = false
                    fadeAnimation.setStart(event.windowResolution.width - width - 2f)
                        .setTarget(event.windowResolution.width.toFloat()).reset()
                }
            }

            if (fade) {
                xPos = fadeAnimation.animate()
                if (fadeAnimation.hasFinished()) {
                    fade = false
                    notifications.remove(this)
                }
            }

            // 绘制
            when(ModuleSimpleHUD.notificationType.current) {
                SIMPLE -> {
                    API.renderUtil.drawRect(
                        xPos,
                        yPos,
                        width,
                        height,
                        Color(0, 0, 0, 120)
                    )

                    val process = calculateProcess()
                    API.renderUtil.drawRect(
                        xPos,
                        yPos + height - 1f,
                        width * process,
                        1f,
                        Color.BLUE
                    )

                    font.drawStringWithShadow(
                        context,
                        (xPos + (width - font.getWidth(context)) / 2f).toDouble(),
                        (yPos + (height - font.height) / 2f).toDouble(),
                        Color(255, 255, 255, 255).rgb
                    )
                }

                BLACK -> {
                    RenderUtils.drawRoundRect(
                        xPos,
                        yPos,
                        width,
                        height,
                        4,
                        Color(40, 40, 40)
                    )

                    icons.drawStringWithShadow(
                        iconText,
                        xPos + 5.0,
                        yPos + (height - icons.height) / 2.0,
                        Color(255, 255, 255, 255).rgb
                    )

                    val mid = icons.getWidth(iconText) + 4.0

                    font.drawStringWithShadow(
                        stateText,
                        xPos + 5.0 + mid,
                        (yPos + (height - font.height - smallFont.height - 2f) / 2f).toDouble(),
                        Color(255, 255, 255, 255).rgb
                    )

                    smallFont.drawStringWithShadow(
                        module.name,
                        xPos + 5.0 + mid,
                        (yPos + (height - font.height - smallFont.height - 2f) / 2f) + font.height + 2.0,
                        Color(255, 255, 255, 255).rgb
                    )
                }
            }
        }

        fun calculateProcess(): Float {
            val total = showAnimation.duration + stayAnimation.duration + fadeAnimation.duration
            if (!stay && !fade) {
                return showAnimation.watch.passTime / total
            }
            if (stay) {
                return (showAnimation.duration + stayAnimation.watch.passTime) / total
            }
            return (showAnimation.duration + stayAnimation.duration + fadeAnimation.watch.passTime) / total
        }

        private fun getCurrentHeight(): Float {
            return when (ModuleSimpleHUD.notificationType.current) {
                SIMPLE -> 20f
                BLACK -> 28f
            }
        }

        private fun getCurrentWidth(): Float {
            return when (ModuleSimpleHUD.notificationType.current) {
                SIMPLE -> font.getWidth(context) + 16f
                BLACK -> max(font.getWidth(module.name) + icons.getWidth(iconText) + 4f + 16f, 120f)
            }
        }
    }
}
