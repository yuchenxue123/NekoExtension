package neko.sm.gui.component.components

import neko.sm.font.FontManager
import neko.sm.gui.component.Component
import neko.sm.gui.component.components.watermark.SimpleWatermark
import neko.sm.gui.component.components.watermark.Watermark
import neko.sm.module.modules.render.ModuleSimpleHUD
import today.opai.api.events.EventRender2D

/**
 * @author yuchenxue
 * @date 2025/02/21
 */
object WatermarkComponent : Component {
    override val condition: Boolean
        get() = ModuleSimpleHUD.watermark

    private val font = FontManager.ROBOTO_20

    // position
    const val X_POSITION = 0f
    const val Y_POSITION = 5f

    // watermark
    val watermark: Watermark
        get() = SimpleWatermark(this)

    override fun render(event: EventRender2D) {

        watermark.render(event)

//        val getText: String = ModuleSimpleHUD.title
//
//        // replace
//        val now: LocalTime = LocalTime.now()
//        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
//        val time: String = now.format(formatter)
//
//        val newText: String = getText
//            .replace("%client%".toRegex(), "Opai")
//            .replace("%time%".toRegex(), time)
//            .replace("%username%".toRegex(), API.clientUsername)
//
//
//        if (newText.isEmpty()) {
//            return
//        }
//
//        val head = newText.toCharArray()[0].toString()
//        val text = newText.substring(1)
//
//        font.drawStringWithShadow(
//            head,
//            X_POSITION,
//            Y_POSITION,
//            Color(255, 90, 0, 255).rgb
//        )
//
//        font.drawStringWithShadow(
//            text,
//            (X_POSITION + font.getWidth(head)),
//            Y_POSITION,
//            Color(255, 255, 255, 255).rgb
//        )
    }

    fun getHeight(): Float {
        return FontManager.ROBOTO_20.height.toFloat()
    }
}
