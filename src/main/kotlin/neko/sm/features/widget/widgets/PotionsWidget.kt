package neko.sm.features.widget.widgets

import neko.sm.features.module.modules.render.ModuleSimpleHUD
import neko.sm.features.widget.PluginWidget
import neko.sm.font.FontManager
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/03/08
 */

object PotionsWidget : PluginWidget("Potion") {

    private val font = FontManager.ROBOTO_18

    private val paddingVertical = 5f
    private val paddingHorizontal = 8f

    override fun render() {
        sync {
            var currentWidth = 0f
            var currentHeight = 0f

            if (API.localPlayer.potionEffects.isEmpty()) {
                return@sync currentWidth to currentHeight
            }

            API.renderUtil.drawRoundRect(
                x,
                y,
                width,
                height,
                5,
                Color(40, 40, 40)
            )

            currentHeight += paddingVertical

            val title = "Potions"

            font.getWidth(title).also {
                if (it >= currentWidth) {
                    currentWidth = it.toFloat()
                }
            }

            font.drawStringWithShadow(
                title,
                x.toDouble() + 2f,
                y.toDouble() + currentHeight,
                Color.WHITE.rgb
            )

            currentHeight += font.height + 4f

            API.localPlayer.potionEffects.forEach { effect ->
                val text = effect.name + effect.amplifier + ": " + effect.duration
                font.drawStringWithShadow(
                    text,
                    x.toDouble() + 2f,
                    y.toDouble() + currentHeight,
                    Color.WHITE.rgb
                )

                font.getWidth(text).let {
                    if (it >= currentWidth) {
                        currentWidth = it.toFloat()
                    }
                }

                currentHeight += font.height + 4f
            }

            currentWidth += paddingHorizontal * 2f
            currentHeight += paddingVertical

            return@sync currentWidth to currentHeight
        }
    }

    override fun renderPredicate(): Boolean {
        return ModuleSimpleHUD.potions
    }
}