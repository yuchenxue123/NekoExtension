package neko.sm.value

import today.opai.api.interfaces.modules.Value
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

open class Setting<T, V : Value<T>>(
    inner: V,
) : SettingWrapper<T, V>(inner), ReadWriteProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return inner.value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.inner.value = value
    }
}