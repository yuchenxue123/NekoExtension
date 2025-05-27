package neko.sm.value

import today.opai.api.interfaces.modules.Value
import java.util.function.BooleanSupplier
import java.util.function.Consumer

/**
 * @author yuchenxue
 * @date 2025/05/27
 */

open class SettingWrapper<T, V: Value<T>>(
    val inner: V
) : Value<T> {
    override fun getValue(): T {
        return inner.value
    }

    override fun getName(): String {
        return inner.name
    }

    override fun getDescription(): String {
        return inner.description
    }

    /**
     * Set hidden
     */
    override fun setHiddenPredicate(hidden: BooleanSupplier) {
        inner.setHiddenPredicate(hidden)
    }

    private val displayableConditions = mutableListOf<() -> Boolean>()

    /**
     * Set displayable
     */
    fun displayable(displayable: () -> Boolean) = apply {
        displayableConditions.add(displayable)
        updateDisplayable()
    }

    private fun updateDisplayable() {
        inner.setHiddenPredicate {
            !displayableConditions.all { it.invoke() }
        }
    }

    override fun setValueCallback(callback: Consumer<T>) {
        inner.setValueCallback(callback)
    }

    override fun setValue(value: T) {
        inner.value = value
    }

}