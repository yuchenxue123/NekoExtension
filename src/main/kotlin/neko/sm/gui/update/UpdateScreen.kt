package neko.sm.gui.update

import neko.sm.NekoExtension
import neko.sm.utils.always.projects.WorldProject
import neko.sm.utils.misc.Accessor
import neko.sm.utils.misc.ExtensionVersion
import neko.sm.utils.render.FontUtils.drawSplitString
import neko.sm.utils.time.TimeWatch
import today.opai.api.events.EventRender2D
import today.opai.api.interfaces.EventHandler
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

object UpdateScreen : EventHandler, Accessor {
    private var version = -1
    private var display = false

    private var disable = false

    private val watch = TimeWatch()

    private fun enable() {
        version = ExtensionVersion.getVersion()
        ExtensionVersion.saveVersion()

        if (version <= 0) {
            display = true
            watch.reset()
            return
        }

        if (version >= NekoExtension.EXTENSION_VERSION) {
            disable = true
        } else {
            display = true
            watch.reset()
        }
    }

    private fun disable() {
        display = false
        disable = false
        version = -1
    }

    override fun onKey(keyCode: Int) {
        if (watch.hasPassTime(8000L) && display && keyCode == 16) {
            disable = true
        }
    }

    override fun onRender2D(event: EventRender2D) {
        if (disable) {
            setEnabled(false)
        }

        if ((version > 0 || display) && version >= NekoExtension.EXTENSION_VERSION) {
            disable = true
            return
        }

        if (!display) {
            return
        }

        val font = API.fontUtil.googleSans18
        val halfWidth = event.windowResolution.width / 2f
        val halfHeight = event.windowResolution.height / 2f

        val under = if (watch.hasPassTime(8000L)) "按Q键关闭" else "等待" + watch.hasLeftTime(8000L) / 1000L + "秒..."

        val text = "${NekoExtension.UPDATE_LOG}\n$under"

        val width = 360f
        val height = 280f

        API.renderUtil.drawRect(
            halfWidth - width / 2f,
            halfHeight - height / 2f,
            width,
            height,
            Color.GRAY
        )

        drawSplitString(
            font,
            text,
            halfWidth - width / 2f + 5f,
            halfHeight - height / 2f + 5f,
            Color.WHITE
        )
    }

    private fun setEnabled(enabled: Boolean) {
        if (enabled) {
            enable()
        } else {
            disable()
        }
    }

    fun forceDisplay() {
        display = true
    }

    fun setup() {
        WorldProject.run {
            it.API.registerEvent(this)
            this.setEnabled(true)
        }
    }
}