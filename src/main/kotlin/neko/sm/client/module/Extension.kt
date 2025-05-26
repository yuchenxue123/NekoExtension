package neko.sm.client.module

import neko.sm.utils.extension.removeBlank
import today.opai.api.enums.EnumModuleCategory
import today.opai.api.enums.EnumModuleCategory.*
import today.opai.api.interfaces.modules.PresetModule
import today.opai.api.interfaces.modules.Value
import today.opai.api.interfaces.modules.values.*
import java.awt.Color

/**
 * @author yuchenxue
 * @date 2025/05/26
 */

fun PresetModule.getValue(name: String): Value<*>?
    = this.values.find { it.name.equals(name, true) }

fun PresetModule.getModeValue(name: String): ModeValue?
    = getValue(name) as? ModeValue

fun PresetModule.getBooleanValue(name: String): BooleanValue?
    = getValue(name) as? BooleanValue

fun PresetModule.getBindValue(name: String): BindValue?
    = getValue(name) as? BindValue

fun PresetModule.getNumberValue(name: String): NumberValue?
    = getValue(name) as? NumberValue

fun PresetModule.getTextValue(name: String): TextValue?
    = getValue(name) as? TextValue

val PresetModule.suffix: String
    get() {
        val suffixValueName = ModuleAccessor.suffix_json.get(name.removeBlank())?.asString ?: ""
        this.getValue(suffixValueName)?.let {
            return it.value.toString()
        }
        return ""
    }

val PresetModule.category: EnumModuleCategory
    get() = ModuleAccessor.getCategory(this)

val EnumModuleCategory.color: Color
    get() = when (this) {
        COMBAT -> Color(255, 100, 100)
        MOVEMENT -> Color(100, 150, 255)
        PLAYER -> Color(100, 200, 100)
        VISUAL -> Color(255, 220, 100)
        MISC -> Color(150, 100, 200)
    }
