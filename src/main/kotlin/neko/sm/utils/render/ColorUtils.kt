package neko.sm.utils.render

import neko.sm.utils.misc.Accessor
import today.opai.api.interfaces.modules.PresetModule
import java.awt.Color
import java.util.*

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

object ColorUtils : Accessor {
    private val colors: MutableSet<Color> = HashSet()

    private val moduleColorMap = mutableMapOf<PresetModule, Color>()

    fun randomLightColor(): Color {
        val random = Random()

        val red = 128 + random.nextInt(128)
        val green = 128 + random.nextInt(128)
        val blue = random.nextInt(180)

        val color = Color(red, green, blue)

        if (colors.contains(color)) {
            return randomLightColor()
        } else {
            colors.add(color)
            return color
        }
    }

    fun getModuleColor(module: PresetModule): Color {
        if (module in moduleColorMap) {
            return moduleColorMap[module]!!
        }
        val color = randomLightColor()
        moduleColorMap[module] = color
        return color
    }
}