package neko.sm.features.module.modules.render.supereyes.wallhack

import neko.sm.features.module.modules.render.ModuleSuperEyes
import neko.sm.utils.extension.multiply
import neko.sm.utils.interfaces.Accessor
import neko.sm.utils.render.RenderUtils
import today.opai.api.interfaces.game.entity.Player

/**
 * @author yuchenxue
 * @date 2025/03/20
 */

object WallHackRenders : Accessor {

    // 绘制一块矩形
    fun renderSimpleWallHack(player: Player) {
        API.renderUtil.worldToScreen(player) { x, y, width, height ->
            RenderUtils.drawRect(
                x - 4f, y - 4f, width + 8f, height + 8f,
                ModuleSuperEyes.color_1.multiply(opacity = ModuleSuperEyes.color_1_alpha.toFloat())
            )
        }
    }
}