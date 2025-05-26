package neko.sm.features.gui.component.components.watermark

import neko.sm.features.gui.Screen
import neko.sm.features.gui.component.components.WatermarkComponent
import neko.sm.features.module.modules.render.ModuleSimpleHUD
import neko.sm.font.FontManager
import neko.sm.utils.render.RenderUtils
import today.opai.api.events.EventRender2D
import java.awt.Color
import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * @author yuchenxue
 * @date 2025/03/19
 */

abstract class Watermark(
    val component: WatermarkComponent
) : Screen {
    protected val text: String
        get() {
            // replace
            val now: LocalTime = LocalTime.now()
            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val time: String = now.format(formatter)

            return ModuleSimpleHUD.title
                .replace("%client%".toRegex(), "Opai")
                .replace("%time%".toRegex(), time)
                .replace("%username%".toRegex(), API.clientUsername)
        }

    val renderX = component.X_POSITION
    val renderY = component.Y_POSITION

    abstract val width: Float
    abstract val height: Float
}

class SimpleWatermark(
    component: WatermarkComponent
) : Watermark(component) {
    private val font = FontManager.ROBOTO_20

    companion object {
        private const val SIDE_LINE_WIDTH = 2f
        private const val LEFT_RIGHT_SPACE = 2f
        private const val TOP_BOTTOM_SPACE = 4f
    }

    override val width: Float
        get() = font.getWidth(text) + SIDE_LINE_WIDTH * 2

    override val height: Float
        get() = font.height + TOP_BOTTOM_SPACE * 2

    private val color = Color(255, 90, 0)

    override fun render(event: EventRender2D) {
        if (text.isEmpty()) {
            return
        }

        // side
        RenderUtils.drawRect(
            renderX,
            renderY,
            SIDE_LINE_WIDTH,
            height,
            color
        )

        RenderUtils.drawRect(
            renderX + SIDE_LINE_WIDTH,
            renderY,
            width,
            height,
            Color(40, 40, 40, 120)
        )

        val head = text.first().toString()

        font.drawStringWithShadow(
            head,
            renderX + SIDE_LINE_WIDTH + LEFT_RIGHT_SPACE,
            renderY + TOP_BOTTOM_SPACE,
            color.rgb
        )

        font.drawStringWithShadow(
            text.drop(1),
            renderX + SIDE_LINE_WIDTH + LEFT_RIGHT_SPACE + font.getWidth(head),
            renderY + TOP_BOTTOM_SPACE,
            Color.WHITE.rgb
        )
    }
}