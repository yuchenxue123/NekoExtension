package neko.sm.utils.misc

import neko.sm.NekoExtension
import today.opai.api.OpenAPI
import today.opai.api.interfaces.game.network.PacketUtil

/**
 * @author yuchenxue
 * @date 2025/03/08
 */

val API: OpenAPI
    inline get() = NekoExtension.openAPI
val network: PacketUtil
    inline get() = API.packetUtil