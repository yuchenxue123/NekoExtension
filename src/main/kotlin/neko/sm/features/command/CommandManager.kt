package neko.sm.features.command

import neko.sm.features.command.commands.BindsCommand
import neko.sm.features.command.commands.UpdateCommand
import neko.sm.utils.misc.Accessor

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object CommandManager : Accessor {
    private val commands = mutableListOf<PluginCommand>()

    fun initialize() {
        arrayOf(
            BindsCommand,
            UpdateCommand
        ).forEach(this::add)

        commands.forEach(this::register)
    }

    private fun register(command: PluginCommand) {
        API.registerFeature(command)
    }

    private fun add(command: PluginCommand) {
        commands.add(command)
    }

    override fun toString(): String {
        return commands.joinToString(", ") { it.names[0] }
    }
}