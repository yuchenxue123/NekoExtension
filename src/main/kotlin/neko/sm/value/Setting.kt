package neko.sm.value

import today.opai.api.interfaces.modules.Value
import java.util.function.BooleanSupplier
import java.util.function.Consumer
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * @author yuchenxue
 * @date 2025/03/06
 */

open class Setting<V, T : Value<V>>(
    val inner: T,
) : Value<V>, ReadWriteProperty<Any?, V> {

    private val displayableConditions = mutableListOf<() -> Boolean>()

    override fun getValue(): V {
        return inner.value
    }

    override fun setValue(value: V) {
        inner.value = value
    }

    override fun getName(): String {
        return inner.name
    }

    override fun getDescription(): String {
        return "NONE"
    }

    /**
     * Set hidden
     */
    override fun setHiddenPredicate(hidden: BooleanSupplier) {
        inner.setHiddenPredicate(hidden)
    }

    /**
     * Set displayable
     */
    fun setDisplayable(displayable: () -> Boolean) = apply {
        displayableConditions.add(displayable)
        updateDisplayable()
    }

    private fun updateDisplayable() {
        inner.setHiddenPredicate {
            !displayableConditions.all { it.invoke() }
        }
    }

    override fun setValueCallback(callback: Consumer<V>) {
        inner.setValueCallback(callback)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): V {
        return inner.value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: V) {
        this.inner.value = value
    }
}