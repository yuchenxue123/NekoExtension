package neko.sm.utils.misc

import neko.sm.NekoExtension
import today.opai.api.OpenAPI
import today.opai.api.interfaces.game.entity.LocalPlayer
import today.opai.api.interfaces.game.network.PacketUtil
import today.opai.api.interfaces.game.world.World

/**
 * @author yuchenxue
 * @date 2025/03/08
 */

val API: OpenAPI
    inline get() = NekoExtension.openAPI

val player: LocalPlayer
    inline get() = API.localPlayer

val world: World
    inline get() = API.world

val network: PacketUtil
    inline get() = API.packetUtil