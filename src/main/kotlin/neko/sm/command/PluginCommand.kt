package neko.sm.command

import neko.sm.utils.misc.Accessor
import today.opai.api.features.ExtensionCommand

/**
 * @author yuchenxue
 * @date 2025/02/19
 */
abstract class PluginCommand(
    names: Array<String>,
    description: String
    , usage: String
) : ExtensionCommand(names, description, usage), Accessor {

    protected fun chatUsage() {
        API.printMessage(usage)
    }
}
