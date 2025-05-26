package neko.sm.features.command.commands

import neko.sm.features.command.PluginCommand
import neko.sm.features.gui.update.UpdateScreen

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

object UpdateCommand : PluginCommand(
    arrayOf("update"),
    "Display update log screen.",
    ".update"
) {
    override fun onExecute(args: Array<String>) {
        UpdateScreen.forceDisplay()
//        API.displayScreen(NewUpdateScreen)
    }
}