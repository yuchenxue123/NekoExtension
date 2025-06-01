package neko.sm.features.gui.update

import neko.sm.NekoExtension
import neko.sm.features.gui.PluginScreen
import neko.sm.utils.always.projects.ScreenProject
import neko.sm.utils.misc.ExtensionVersion
import neko.sm.utils.render.FontUtils.drawSplitString
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/03/09
 */

object NewUpdateScreen : PluginScreen() {

    override fun drawScreen(mouseX: Int, mouseY: Int) {
        val font = API.fontUtil.googleSans18

        var halfWidth = 0f
        var halfHeight = 0f

        ScreenProject.resolution?.let {
            halfWidth = it.width / 2f
            halfHeight = it.height / 2f
        } ?: return

        val text = NekoExtension.UPDATE_LOG

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

    fun checkIsUpdated(): Boolean {
        val version = ExtensionVersion.getVersion()
        ExtensionVersion.saveVersion()

        if (version <= 0) {
            return false
        }

        return version < NekoExtension.EXTENSION_VERSION
    }
}