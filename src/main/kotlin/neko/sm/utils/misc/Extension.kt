package neko.sm.utils.misc

import neko.sm.NekoExtension
import today.opai.api.OpenAPI

/**
 * @author yuchenxue
 * @date 2025/03/08
 */

val API: OpenAPI
    inline get() = NekoExtension.openAPI