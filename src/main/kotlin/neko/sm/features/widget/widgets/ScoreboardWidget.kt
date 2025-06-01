package neko.sm.features.widget.widgets

import neko.sm.event.EventListener
import neko.sm.event.events.ScreenResizeEvent
import neko.sm.event.handler
import neko.sm.features.module.modules.render.ModuleSimpleHUD
import neko.sm.features.widget.PluginWidget
import neko.sm.font.FontManager
import neko.sm.utils.always.projects.ScreenProject
import neko.sm.utils.render.RenderUtils
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

object ScoreboardWidget : PluginWidget("Scoreboard"), EventListener {

    private val font = FontManager.ROBOTO_18

    private const val SPACE_TOP_BOTTOM = 6f
    private const val SPACE_LEFT_RIGHT = 4f
    private const val SPACE_TITLE_LINE = 5f
    private const val SPACE_LINES = 4f

    override fun render() {
        sync {
            var width = 0f
            var height = 0f

            if (API.world.scoreboardLines.isEmpty() || API.world.scoreboardTitle == null) {
                return@sync width to height
            }

            // background
            RenderUtils.drawRoundRect(
                x,
                y,
                this.width,
                this.height,
                5,
                Color(40, 40, 40)
            )

            height += SPACE_TOP_BOTTOM

            val title = API.world.scoreboardTitle

            // title width
            val w1 = font.getWidth(title)

            width = w1.toFloat().coerceAtLeast(width)

            // render title
            font.drawStringWithShadow(
                title,
                x.toDouble() + (this.width - w1) / 2f,
                y.toDouble() + height,
                Color(155, 255, 255).rgb
            )

            height += font.height + SPACE_TITLE_LINE

            // render lines
            API.world.scoreboardLines.forEach { text ->
                font.drawStringWithShadow(
                    text,
                    x.toDouble() + SPACE_LEFT_RIGHT,
                    y.toDouble() + height,
                    Color.WHITE.rgb
                )

                width = font.getWidth(text).toFloat().coerceAtLeast(width)

                height += font.height + SPACE_LINES
            }

            height += SPACE_TOP_BOTTOM

            width += SPACE_LEFT_RIGHT * 2

            // position
            if (width != this.width || height != this.height) {
                ScreenProject.resolution?.let {
                    this.x = it.width - width - 2f
                    this.y = it.height / 2f - height / 2f
                }
            }

            return@sync width to height
        }
    }

    private val onScreenResize = handler<ScreenResizeEvent> { event ->
        this.x = event.resolution.width - width - 2f
        this.y = event.resolution.height / 2f - height / 2f
    }

    override fun renderPredicate(): Boolean {
        return ModuleSimpleHUD.scoreboard
    }
}