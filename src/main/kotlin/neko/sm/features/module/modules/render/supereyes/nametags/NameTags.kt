package neko.sm.features.module.modules.render.supereyes.nametags

import neko.sm.features.module.modules.render.supereyes.SuperEye
import neko.sm.value.SubMode
import today.opai.api.interfaces.game.entity.Player

/**
 * @author yuchenxue
 * @date 2025/03/20
 */


enum class NameTags(
    override val modeName: String
) : SuperEye, SubMode {
    // simple
    SIMPLE("Simple") {
        override fun render(player: Player) {
            NameTagRenders.renderSimpleNameTag(player)
        }
    },
    BLACK("Black") {
        override fun render(player: Player) {
            NameTagRenders.renderBlackNameTag(player)
        }
    }
    ;
}