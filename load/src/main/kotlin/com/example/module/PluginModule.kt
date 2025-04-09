package com.example.module

import today.opai.api.enums.EnumModuleCategory
import today.opai.api.features.ExtensionModule

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

class PluginModule(
    name: String,
    desc: String,
    category: EnumModuleCategory
) : ExtensionModule(name, desc, category) {

    fun toggle() {
        isEnabled = !isEnabled
    }
}