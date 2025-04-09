package neko.sm.command

import neko.sm.command.commands.BindsCommand
import neko.sm.command.commands.UpdateCommand
import neko.sm.utils.misc.Accessor

/**
 * @author yuchenxue
 * @date 2025/03/13
 */

object CommandManager : Accessor {
    private val commands = mutableListOf<PluginCommand>()

    init {
        val commands = arrayOf(
            BindsCommand,
            UpdateCommand
        )

        commands.forEach(this::add)
    }

    fun register() {
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