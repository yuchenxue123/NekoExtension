package neko.sm.utils.resources

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException

/**
 * @author yuchenxue
 * @date 2025/05/26
 */

object AssetsManager {

    private const val DATA_PATH = "assets/data"
    private const val FONT_PATH = "assets/font"

    fun getAsString(path: String): String {
        return javaClass.classLoader.getResourceAsStream(path)
            ?.bufferedReader()
            ?.use { it.readText() }
            ?: ""
    }

    fun getDataAsJsonObject(fileName: String): JsonObject {
        val text = getAsString("$DATA_PATH/$fileName")

        if (text.isEmpty()) return JsonObject()

        return try {
            Gson().fromJson(text, JsonObject::class.java)
        } catch (e: JsonSyntaxException) {
            JsonObject()
        }
    }
}