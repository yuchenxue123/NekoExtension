package neko.sm.features.module.modules.render.supereyes.nametags

import neko.sm.features.module.modules.render.ModuleSuperEyes.getRenderName
import neko.sm.font.FontManager
import neko.sm.utils.extension.step
import neko.sm.utils.interfaces.Accessor
import neko.sm.utils.render.RenderUtils
import today.opai.api.interfaces.game.entity.Player
import java.awt.Color
import kotlin.math.max

/**
 * @author yuchenxue
 * @date 2025/03/20
 */

object NameTagRenders : Accessor {

    fun renderSimpleNameTag(player: Player) {
        val big = FontManager.ROBOTO_22
        val small = FontManager.ROBOTO_14

        API.renderUtil.worldToScreen(player) { x, y, width, _ ->

            val name = getRenderName(player)

            val renderWidth = max(100.0, (big.getWidth(name) + 20f).toDouble()).toFloat()
            val renderHeight = 4f + 10f + big.height + small.height
            val startX = (x - (renderWidth - width) / 2f).toFloat()
            val startY = (y - renderHeight).toFloat()

            RenderUtils.drawRect(
                startX,
                startY,
                renderWidth,
                renderHeight,
                Color(0, 0, 0, 100)
            )

            var offsetY = 4f

            big.drawStringWithShadow(
                name,
                (startX + 2f).toDouble(),
                (startY + offsetY).toDouble(),
                Color.WHITE.rgb
            )

            offsetY += 5f + big.height

            val health = player.health.step(0.1f)

            small.drawStringWithShadow(
                "Health: $health",
                (startX + 2f).toDouble(),
                (startY + offsetY).toDouble(),
                Color.WHITE.rgb
            )

            offsetY += 3f + small.height

            val percent = player.health / player.maxHealth
            RenderUtils.drawRect(
                startX,
                startY + offsetY,
                renderWidth * percent,
                2f,
                Color(255, 255, 255, 180)
            )
        }
    }

    fun renderBlackNameTag(player: Player) {
        val font = FontManager.ROBOTO_18

        API.renderUtil.worldToScreen(player) { x, y, width, _ ->

            val name = getRenderName(player)

            val renderWidth = font.getWidth(name) + 16f
            val renderHeight = 8f + font.height
            val startX = (x - (renderWidth - width) / 2f).toFloat()
            val startY = (y - renderHeight).toFloat()

            RenderUtils.drawRoundRect(
                startX,
                startY,
                renderWidth,
                renderHeight,
                (renderHeight / 2).toInt(),
                Color(40, 40, 40, 255)
            )

            font.drawStringWithShadow(
                name,
                startX + (renderWidth - font.getWidth(name)) / 2.0,
                startY + (renderHeight - font.height) / 2.0,
                Color.WHITE.rgb
            )
        }
    }
}