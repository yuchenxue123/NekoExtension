package neko.sm.utils.extension

/**
 * @author yuchenxue
 * @date 2025/05/31
 */

fun String.removeBlank(): String {
    return this.replace("\\s+".toRegex(), "")
}