package neko.sm.features.module

import neko.sm.utils.misc.Accessor
import neko.sm.value.SubMode
import neko.sm.value.choice.Choice
import neko.sm.value.choice.ChoicesSetting
import neko.sm.value.values.*
import today.opai.api.enums.EnumModuleCategory
import today.opai.api.features.ExtensionModule
import today.opai.api.interfaces.EventHandler
import today.opai.api.interfaces.modules.values.*
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2024/12/07
 */

open class PluginModule(
    name: String,
    description: String,
    category: EnumModuleCategory
) : ExtensionModule(name, description, category), EventHandler, Accessor {

    protected fun toggle() {
        isEnabled = !isEnabled
    }

    /**
     * Create a [BooleanSetting]
     */
    fun boolean(
        name: String,
        value: Boolean = false,
        displayable: () -> Boolean = { true }
    ): BooleanSetting {
        val create = API.valueManager.createBoolean(name, value)

        val tsf = BooleanSetting(create)
        tsf.setDisplayable(displayable)

        addValues(create)
        return tsf
    }

    fun boolean(setting: BooleanSetting): BooleanSetting {
        addValues(setting.inner)
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

        val tsf = ModeSetting(create)
        tsf.setDisplayable(displayable)

        addValues(create)
        return tsf
    }

    fun mode(setting: ModeSetting): ModeSetting {
        addValues(setting.inner)
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
            addValues(it.inner)
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

        val tsf = NumberSetting(create)
        tsf.setDisplayable(displayable)

        addValues(create)
        return tsf
    }

    fun number(setting: NumberSetting): NumberSetting {
        addValues(setting.inner)
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

        val tsf = NumberSetting(create)
        tsf.setDisplayable(displayable)

        addValues(create)
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

        val tsf = LabelSetting(create)
        tsf.setDisplayable(displayable)

        addValues(create)
        return tsf
    }

    fun label(setting: LabelSetting): LabelSetting {
        addValues(setting.inner)
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

        val tsf = TextSetting(create)
        tsf.setDisplayable(displayable)

        addValues(create)
        return tsf
    }

    fun text(setting: TextSetting): TextSetting {
        addValues(setting.inner)
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

        val tsf = BindSetting(create)
        tsf.setDisplayable(displayable)

        addValues(create)
        return tsf
    }

    fun bind(setting: BindSetting): BindSetting {
        addValues(setting.inner)
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
        tsf.setDisplayable(displayable)

        addValues(create)
        return tsf
    }

    fun color(setting: ColorSetting): ColorSetting {
        addValues(setting.inner)
        return setting
    }

    fun <T : Choice> choices(
        name: String,
        choices: Array<T>,
        value: T = choices[0],
        displayable: () -> Boolean = { true }
    ) = ChoicesSetting(this, name, choices, value, displayable).also {
        addValues(it.inner)
        it.choices.forEach { choice ->
            choice.values.forEach { value ->
                value.setDisplayable(displayable).setDisplayable {
                    it.current.modeName == choice.modeName
                }
                addValues(value.inner)
            }
        }
    }
}
