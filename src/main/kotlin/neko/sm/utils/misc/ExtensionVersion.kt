package neko.sm.utils.misc

import neko.sm.NekoExtension
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

object ExtensionVersion : Accessor {

    fun saveVersion(): Boolean {
        val roaming = System.getenv("APPDATA") ?: return false

        val configDir = roaming + File.separator + "Opai" + File.separator + "NekoExtension"
        val file = File(configDir)
        if (!file.exists() && file.mkdirs()) {
            println("创建成功: $configDir")
        }

        val version = configDir + File.separator + "version.properties"
        val versionFile = File(version)

        val properties = Properties()
        properties.setProperty("version", NekoExtension.EXTENSION_VERSION.toString())

        try {
            FileOutputStream(versionFile).use { outputStream ->
                properties.store(outputStream, "NekoExtension Version")
                return true
            }
        } catch (ignored: IOException) {
            return false
        }
    }

    fun getVersion(): Int {
        val roaming = System.getenv("APPDATA") ?: return -1

        val versionPath = (roaming + File.separator + "Opai"
                + File.separator + "NekoExtension"
                + File.separator + "version.properties")

        val properties = Properties()

        try {
            FileInputStream(versionPath).use { inputStream ->
                properties.load(inputStream)
                val version = properties.getProperty("version")
                return tryParseInt(version)
            }
        } catch (e: IOException) {
            return -1
        }
    }

    private fun tryParseInt(input: String): Int {
        return try {
            input.toInt()
        } catch (e: NumberFormatException) {
            -1
        }
    }
}