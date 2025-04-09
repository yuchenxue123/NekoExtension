package neko.sm.widget.widgets

import neko.sm.font.FontManager
import neko.sm.module.modules.render.ModuleSimpleHUD
import neko.sm.utils.always.projects.ScreenProject
import neko.sm.utils.render.RenderUtils
import neko.sm.widget.PluginWidget
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

object ScoreboardWidget : PluginWidget("Scoreboard") {

    private val font = FontManager.ROBOTO_18

    private val paddingVertical = 5f
    private val paddingHorizontal = 8f

    private var preWidth = 0f
    private var preHeight = 0f

    override fun render() {
        sync {
            var currentWidth = 0f
            var currentHeight = 0f

            if (API.world.scoreboardLines.isEmpty() || API.world.scoreboardTitle == null) {
                return@sync currentWidth to currentHeight
            }

            RenderUtils.drawRoundRect(
                x,
                y,
                width,
                height,
                5,
                Color(40, 40, 40)
            )

            currentHeight += paddingVertical

            val title = API.world.scoreboardTitle

            val w1 = font.getWidth(title).also {
                if (it >= currentWidth) {
                    currentWidth = it.toFloat()
                }
            }

            font.drawStringWithShadow(
                title,
                x.toDouble() + (width - w1) / 2f,
                y.toDouble() + currentHeight,
                Color.WHITE.rgb
            )

            currentHeight += font.height + 4f

            API.world.scoreboardLines.forEach { s ->
                font.drawStringWithShadow(
                    s,
                    x.toDouble() + 2f,
                    y.toDouble() + currentHeight,
                    Color.WHITE.rgb
                )

                font.getWidth(s).let {
                    if (it >= currentWidth) {
                        currentWidth = it.toFloat()
                    }
                }

                currentHeight += font.height + 4f
            }

            currentWidth += paddingHorizontal * 2f
            currentHeight += paddingVertical

            return@sync currentWidth to currentHeight
        }.let {
            if (it.first != preWidth) {
                preWidth = it.first
                updatePosition(it.first, it.second)
            }
            if (it.second != preHeight) {
                preHeight = it.second
                updatePosition(it.first, it.second)
            }
        }
    }

    private fun updatePosition(width: Float, height: Float) {
        ScreenProject.screen?.let {
            this.x = it.width - width - 2f
            this.y = it.height / 2f - height / 2f
        }
    }

    override fun renderPredicate(): Boolean {
        return ModuleSimpleHUD.scoreboard
    }
}