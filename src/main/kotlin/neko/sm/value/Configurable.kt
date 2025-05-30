package neko.sm.value

import neko.sm.utils.interfaces.Accessor
import neko.sm.value.values.*
import today.opai.api.interfaces.modules.values.ColorValue
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

open class Configurable : Accessor {
    val values = mutableListOf<SettingWrapper<*,*>>()

    /**
     * Create a [BooleanSetting]
     */
    fun boolean(
        name: String,
        value: Boolean,
        displayable: () -> Boolean = { true }
    ): BooleanSetting {
        val create = API.valueManager.createBoolean(name, value)

        val tsf = BooleanSetting(create)
        tsf.displayable(displayable)

        values.add(tsf)
        return tsf
    }

    fun boolean(setting: BooleanSetting): BooleanSetting {
        values.add(setting)
        return setting
    }

    /**
     * Create a [ModeSetting]
     */
    fun mode(
        name: String,
        modes: Array<String>,
        value: String,
        displayable: () -> Boolean = { true }
    ): ModeSetting {
        val create = API.valueManager.createModes(name, value, modes)

        create.setHiddenPredicate { !displayable.invoke() }
        val tsf = ModeSetting(create)

        values.add(tsf)
        return tsf
    }

    fun mode(setting: ModeSetting): ModeSetting {
        values.add(setting)
        return setting
    }

    /**
     * Crate a [EnumSetting]
     */
    inline fun <reified E> enum(
        name: String,
        value: E,
        noinline displayable: () -> Boolean = { true }
    ): EnumSetting<E> where E: Enum<E>, E: SubMode {
        return EnumSetting(name, enumValues<E>(), displayable = displayable).also {
            values.add(it)
        }
    }

    /**
     * Create a [NumberSetting]
     */
    fun number(
        name: String,
        value: Double,
        min: Double,
        max: Double,
        step: Double = 0.1,
        suffix: String = "",
        displayable: () -> Boolean = { true }
    ): NumberSetting {
        val create = API.valueManager
            .createDouble(name, value, min, max, step)
            .setSuffix(suffix)

        create.setHiddenPredicate { !displayable.invoke() }
        val tsf = NumberSetting(create)

        values.add(tsf)
        return tsf
    }

    fun number(setting: NumberSetting): NumberSetting {
        values.add(setting)
        return setting
    }

    /**
     * Create a [IntSetting]
     */
    fun int(
        name: String,
        value: Int,
        min: Int,
        max: Int,
        step: Int = 1,
        suffix: String = "",
        displayable: () -> Boolean = { true }
    ): NumberSetting {
        val create = API.valueManager
            .createDouble(name, value.toDouble(), min.toDouble(), max.toDouble(), step.toDouble())
            .setSuffix(suffix)

        create.setHiddenPredicate { !displayable.invoke() }
        val tsf = NumberSetting(create)

        values.add(tsf)
        return tsf
    }

    /**
     * Create a [LabelSetting]
     */
    fun label(
        name: String,
        displayable: () -> Boolean = { false }
    ): LabelSetting {
        val create = API.valueManager.createLabel(name)

        create.setHiddenPredicate { !displayable.invoke() }
        val tsf = LabelSetting(create)

        values.add(tsf)
        return tsf
    }

    fun label(setting: LabelSetting): LabelSetting {
        values.add(setting)
        return setting
    }

    /**
     * Create a [TextSetting]
     */
    fun text(
        name: String,
        text: String,
        displayable: () -> Boolean = { true }
    ): TextSetting {
        val create = API.valueManager.createInput(name, text)

        create.setHiddenPredicate { !displayable.invoke() }
        val tsf = TextSetting(create)

        values.add(tsf)
        return tsf
    }

    fun text(setting: TextSetting): TextSetting {
        values.add(setting)
        return setting
    }

    /**
     * Create a [BindSetting]
     */
    fun bind(
        name: String,
        defaultBind: Int,
        displayable: () -> Boolean = { true }
    ): BindSetting {
        val create = API.valueManager.createKeyBind(name, defaultBind)

        create.setHiddenPredicate { !displayable.invoke() }
        val tsf = BindSetting(create)

        values.add(tsf)
        return tsf
    }

    fun bind(setting: BindSetting): BindSetting {
        values.add(setting)
        return setting
    }

    /**
     * Create a [ColorValue]
     */
    fun color(
        name: String,
        color: Color,
        displayable: () -> Boolean = { true }
    ) : ColorSetting {
        val create = API.valueManager.createColor(name, color)

        val tsf = ColorSetting(create)
        tsf.displayable(displayable)

        values.add(tsf)
        return tsf
    }

    fun color(setting: ColorSetting): ColorSetting {
        values.add(setting)
        return setting
    }
}