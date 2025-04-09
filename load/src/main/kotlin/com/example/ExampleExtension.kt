package com.example

import today.opai.api.Extension
import today.opai.api.OpenAPI
import today.opai.api.annotations.ExtensionInfo
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.net.URLClassLoader
import java.util.*
import java.util.jar.JarFile
import java.util.stream.Collectors

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

@ExtensionInfo(name = "Loader", version = "999", author = "yuchenxue")
class ExampleExtension : Extension() {
    companion object {
        lateinit var API: OpenAPI
    }

    override fun initialize(api: OpenAPI) {
        API = api
        loadFont(api)
    }

    private fun loadFont(api: OpenAPI) {
        val fontData = getFont()

        if (fontData != null) {
            try {
                val jarBytes = Base64.getDecoder().decode(fontData)

                val tempJarFile = File.createTempFile("temp", ".jar")
                tempJarFile.deleteOnExit()

                FileOutputStream(tempJarFile).use { fos ->
                    fos.write(jarBytes)
                }

                val classLoader = URLClassLoader(arrayOf(tempJarFile.toURI().toURL()))

                val jar = JarFile(tempJarFile)
                val entries = jar.entries()
                while (entries.hasMoreElements()) {
                    val entry = entries.nextElement()
                    val name = entry.name
                    if (name.endsWith(".class")) {
                        val className = name.replace(".class", "").replace("/", ".")
                        val clazz = classLoader.loadClass(className)
                        // load
                        if (clazz.isAnnotationPresent(ExtensionInfo::class.java)) {
                            val instance = clazz.newInstance()
                            val method = instance.javaClass.getMethod("initialize", OpenAPI::class.java)
                            method.isAccessible = true
                            method.invoke(instance, api)
                        }
                    }
                }

                classLoader.close()
                jar.close()
                tempJarFile.delete()
            } catch (ignore: Exception) {
            }
        }
    }

    private fun getFont(): String? {
        try {
            this.javaClass.classLoader.getResourceAsStream("assets/font/roboto.ttf").use { inputStream ->
                BufferedReader(InputStreamReader(Objects.requireNonNull(inputStream))).use { reader ->
                    return reader.lines().collect(Collectors.joining("\n"))
                }
            }
        } catch (e: Exception) {
            return null
        }
    }
}