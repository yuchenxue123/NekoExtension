package neko.sm.features.command.commands

import neko.sm.features.command.PluginCommand
import org.lwjgl.input.Keyboard
import today.opai.api.interfaces.modules.PresetModule
import java.util.stream.Collectors

/**
 * @author yuchenxue
 * @date 2025/02/19
 */

object BindsCommand : PluginCommand(
    arrayOf("binds"),
    "Print all binds in chat gui.",
    ".binds <clear/recover/show>"
) {
    private val map = mutableMapOf<PresetModule, Int>()

    override fun onExecute(args: Array<String>) {

        when (args.size) {
            1 -> showBinds()
            2 -> {
                when (args[1]) {
                    "clear" -> {
                        API.printMessage("§cCleared:")
                        val clears = mutableListOf<PresetModule>()
                        API.moduleManager.modules.forEach { module ->
                            if (module.key > 0) {
                                map[module] = module.key
                                module.key = 0
                                clears.add(module)
                            }
                        }
                        val mods = clears.stream().map { obj: PresetModule -> obj.name }.collect(Collectors.joining(", §7"))
                        API.printMessage("§7$mods")
                    }

                    "recover" -> {
                        if (map.isEmpty()) {
                            API.printMessage("No data!")
                            return
                        }
                        API.printMessage("§eRecovered:")
                        map.forEach { (m, k) ->
                            m.key = k
                            API.printMessage("§7" + m.name + ": §a" + Keyboard.getKeyName(k))
                        }
                        map.clear()
                    }

                    "show" -> showBinds()
                    else -> chatUsage()
                }
            }

            else -> chatUsage()
        }
    }

    private fun showBinds() {
        API.printMessage("§aBinds:")
        for (module in API.moduleManager.modules) {
            if (module.key > 0) {
                API.printMessage("§7" + module.name + ": §f" + Keyboard.getKeyName(module.key))
            }
        }
    }
}
