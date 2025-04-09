package neko.sm.utils.misc

import neko.sm.NekoExtension
import today.opai.api.OpenAPI
import today.opai.api.interfaces.game.entity.LocalPlayer
import today.opai.api.interfaces.game.world.World

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

interface Accessor {
    val API: OpenAPI
        get() = NekoExtension.openAPI
    val player: LocalPlayer
        get() = API.localPlayer
    val world: World
        get() = API.world
}