package neko.sm

import neko.sm.client.module.ModuleAccessor
import neko.sm.features.command.CommandManager
import neko.sm.features.gui.component.ComponentManager
import neko.sm.features.module.ModuleManager
import neko.sm.features.widget.WidgetManager
import neko.sm.font.FontManager
import neko.sm.utils.always.AlwaysHandler
import neko.sm.utils.always.ProjectManager
import today.opai.api.Extension
import today.opai.api.OpenAPI
import today.opai.api.annotations.ExtensionInfo
import today.opai.api.interfaces.EventHandler

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

@ExtensionInfo(name = "NekoExtension", version = "1.0", author = "yuchenxue")
class NekoExtension : Extension() {

    companion object {
        lateinit var openAPI: OpenAPI
        const val EXTENSION_NAME = "Neko"
        const val EXTENSION_VERSION = 20
    }


    override fun initialize(api: OpenAPI) {
        openAPI = api

        FontManager
        ModuleAccessor

        // handler
        ProjectManager
        handler(AlwaysHandler)

        // module
        ModuleManager.initialize()

        // command
        CommandManager.initialize()

        // widget
        WidgetManager.initialize()

        // component
        ComponentManager.initialize()
    }

    private fun handler(event: EventHandler) {
        openAPI.registerEvent(event)
    }
}