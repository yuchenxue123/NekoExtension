package neko.sm.features.command

import neko.sm.features.command.commands.BindsCommand
import neko.sm.features.command.commands.UpdateCommand
import neko.sm.utils.interfaces.Accessor
import neko.sm.utils.interfaces.Register

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object CommandManager : Register<PluginCommand>, Accessor {
    private val commands = mutableListOf<PluginCommand>()

    fun initialize() {
        arrayOf(
            BindsCommand,
            UpdateCommand
        ).forEach(this::add)

        commands.forEach(this::register)
    }

    override fun register(element: PluginCommand) {
        API.registerFeature(element)
    }

    private fun add(command: PluginCommand) {
        commands.add(command)
    }

    override fun toString(): String {
        return commands.joinToString(", ") { it.names[0] }
    }
}