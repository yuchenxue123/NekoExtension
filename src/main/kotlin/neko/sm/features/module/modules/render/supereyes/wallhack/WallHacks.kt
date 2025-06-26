package neko.sm.features.module.modules.render.supereyes.wallhack

import neko.sm.features.module.modules.render.supereyes.SuperEye
import neko.sm.value.SubMode
import today.opai.api.interfaces.game.entity.Player

/**
 * @author yuchenxue
 * @date 2025/03/20
 */

enum class WallHacks(
    override val modeName: String
) : SuperEye, SubMode {
    SIMPLE("Simple") {
        override fun render(player: Player) {
            WallHackRenders.renderSimpleWallHack(player)
        }
    }
}