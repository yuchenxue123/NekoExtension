package neko.sm.utils.reflection

import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * @author yuchenxue
 * @date 2025/05/30
 */

object ReflectUtils {

    fun findField(clazz: Class<*>, fieldName: String): Field? {
        var currentClass: Class<*>? = clazz
        while (currentClass != null) {
            val find = runCatching {
                clazz.getDeclaredField(fieldName)
            }.getOrNull()

            if (find == null) {
                currentClass = currentClass.superclass
            }
        }
        return null
    }

    fun findMethod(clazz: Class<*>, methodName: String): Method? {
        var currentClass: Class<*>? = clazz
        while (currentClass != null) {
            val find = runCatching {
                clazz.getDeclaredMethod(methodName)
            }.getOrNull()

            if (find == null) {
                currentClass = currentClass.superclass
            }
        }
        return null
    }
}