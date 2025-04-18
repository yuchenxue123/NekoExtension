package neko.sm.utils.resources

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import java.util.stream.Collectors

/**
 * @author yuchenxue
 * @date 2025/04/18
 */

object ResourcesUtils {

    private val textMap = mutableMapOf<String, String>()
    private val jsonMap = mutableMapOf<String, JsonObject>()

    fun getAsString(path: String): String {
        if (textMap.containsKey(path)) {
            return textMap[path]!!
        }

        this.javaClass.classLoader.getResourceAsStream(path)?.use { stream ->
            BufferedReader(InputStreamReader(Objects.requireNonNull(stream))).use { reader ->
                val text = reader.lines().collect(Collectors.joining("\n"))
                textMap[path] = text
                return text
            }
        }

        return ""
    }

    fun getAsJson(path: String): JsonObject? {
        if (jsonMap.containsKey(path)) {
            return jsonMap[path]!!
        }

        val text = getAsString(path)

        if (text.isEmpty()) {
            return null
        }

        try {
            val json = Gson().fromJson(text, JsonObject::class.java)
            jsonMap[path] = json
            return json
        } catch (e: JsonSyntaxException) {
            e.printStackTrace()
            return null
        }
    }
}