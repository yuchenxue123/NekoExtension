package neko.sm.utils.render

import neko.sm.utils.interfaces.Accessor
import org.lwjgl.opengl.GL11.*
import today.opai.api.interfaces.game.entity.Player
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/03/12
 */

object RenderUtils : Accessor {

    fun drawRoundRect(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: Int,
        color: Color
    ) {
        API.renderUtil.drawRoundRect(x, y, width, height, radius, color)
    }

    fun drawRect(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        color: Color
    ) {
        API.renderUtil.drawRect(x, y, width, height, color)
    }

    fun drawPlayerHead(
        x: Float,
        y: Float,
        size: Float,
        player: Player?
    ) {
        API.renderUtil.drawPlayerHead(x, y, size, player)
    }

    fun drawRoundPlayerHead(
        x: Float,
        y: Float,
        size: Float,
        player: Player?,
        radius: Float
    ) {
        API.renderUtil.drawRoundPlayerHead(x, y, size, player, radius)
    }

    inline fun scale(
        x: Float,
        y: Float,
        scale: Float,
        draw: () -> Unit
    ) {
        glPushMatrix()
        glTranslatef(x, y, 0f)
        glScalef(scale, scale, 1f)
        glTranslatef(-x, -y, 0f)

        draw()

        glPopMatrix()
    }
}