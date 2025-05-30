package neko.sm.font

import neko.sm.utils.interfaces.Accessor

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

object FontManager : Accessor {
    private const val ROBOTO_PATH = "assets/font/roboto.ttf"
    private const val ICONS_PATH = "assets/font/icons.ttf"

    // client
    val MINECRAFT = FontWrapper.of(API.fontUtil.vanillaFont)
    val GOOGLE_16 = FontWrapper.of(API.fontUtil.googleSans16)
    val GOOGLE_18 = FontWrapper.of(API.fontUtil.googleSans18)
    val GOOGLE_B16 = FontWrapper.of(API.fontUtil.googleSansB16)
    val GOOGLE_B18 = FontWrapper.of(API.fontUtil.googleSansB18)
    val PRODUCT_18 = FontWrapper.of(API.fontUtil.product18)
    val TOHOMA_18 = FontWrapper.of(API.fontUtil.tahoma18)

    // extension
    // roboto
    val ROBOTO_14 = getFont(ROBOTO_PATH, 14f)
    val ROBOTO_16 = getFont(ROBOTO_PATH, 16f)
    val ROBOTO_18 = getFont(ROBOTO_PATH, 18f)
    val ROBOTO_20 = getFont(ROBOTO_PATH, 20f)
    val ROBOTO_22 = getFont(ROBOTO_PATH, 22f)
    val ROBOTO_24= getFont(ROBOTO_PATH, 24f)
    // icons
    val ICONS_20 = getFont(ROBOTO_PATH, 20f)
    val ICONS_24 = getFont(ICONS_PATH, 24f)
    val ICONS_32 = getFont(ICONS_PATH, 32f)

    private fun getFont(path: String, size: Float): FontWrapper {
        val resourceAsStream = this.javaClass.classLoader.getResourceAsStream(path)
        val createFont = API.fontUtil.createFont(resourceAsStream, size)
        return FontWrapper.of(createFont)
    }
}