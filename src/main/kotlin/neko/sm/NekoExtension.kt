package neko.sm

import neko.sm.command.CommandManager
import neko.sm.font.FontManager
import neko.sm.gui.component.ComponentManager
import neko.sm.gui.update.UpdateScreen
import neko.sm.module.ModuleManager
import neko.sm.utils.always.AlwaysHandler
import neko.sm.utils.always.ProjectManager
import neko.sm.widget.WidgetManager
import today.opai.api.Extension
import today.opai.api.OpenAPI
import today.opai.api.annotations.ExtensionInfo
import today.opai.api.interfaces.EventHandler

/**
 * @author yuchenxue
 * @date 2025/03/04
 */

@ExtensionInfo(name = "NekoExtension", version = "1.0", author = "yuchenxue")
class NekoExtension : Extension() {
    companion object {
        lateinit var openAPI: OpenAPI
        const val EXTENSION_NAME = "Neko"
        // 每次更新 +1
        const val EXTENSION_VERSION = 17

        lateinit var UPDATE_LOG: String
    }

    private fun lateInit() {
        UPDATE_LOG = ("更新日志" + "\n"
                + "Update-0316" + "\n"
                + "1. 重写ModuleList和Watermark" + "\n"

//                + "Update-0316" + "\n"
//                + "1. Target widget" + "\n"
//                + "2. 忘记了" + "\n"

//                + "Update-0307" + "\n"
//                + "1. Scoreboard widget" + "\n"
//
//                + "Update-0304" + "\n"
//                + "1. SuperEyes" + "\n"
//                + "2. .update 指令显示这个界面" + "\n"
//                + "3. Notification Y轴动画" + "\n"
//
//                + "Update-0302-4" + "\n"
//                + "1. SuperEyes 标记Username" + "\n"
//
//                + "Update-0302-3" + "\n"
//                + "1. SuperEyes 当AntiCheat开启时会标记黑客, 增加一些选项" + "\n"

//                + "Update-0302-2" + "\n"
//                + "1. binds clear/recover" + "\n"

//                + "Update-0302" + "\n"
//                + "1. 更新版本号" + "\n"
//                + "2. [add] binds指令" + "\n"
//                + "3. [add] SuperEyes (NameTags)" + "\n"
//                + "   - 如何关闭原版玩家名绘制: 开NameTags, 里面选项全部关闭" + "\n"

//                + "Update-0301-2" + "\n"
//                + "1. 更新版本号" + "\n"
//                + "2. [fix] Watermark 超出索引范围" + "\n"

//                + "Update-0301" + "\n"
//                + "1. 更新版本号" + "\n"
//                + "2. [fix] ModuleHelper在日志里空指针" + "\n"

//                + "Update-0227" + "\n"
//                + "1. 这个UpdateGUI不再注册为模块" + "\n"
//                + "2. 水印自定义" + "\n"
//                + "3. 列表Suffix" + "\n"
//                + "4. [add] [ModuleHelper] LongJump两种模式绑定不同按键, 并且玩家无移动自动关闭" + "\n"
//                + "5. [fix] [ModuleHelper] 无移动检测排除Damage模式" + "\n"

                + "\n"
                + "模块: $ModuleManager" + "\n"
                + "指令: $CommandManager" + "\n"
                + "组件: $WidgetManager" + "\n"
                )
    }

    override fun initialize(api: OpenAPI) {
        openAPI = api

        FontManager

        // handler
        ProjectManager
        handler(AlwaysHandler)

        UpdateScreen.setup()

        // module
        ModuleManager.register()

        // command
        CommandManager.register()

        // widget
        WidgetManager.register()

        // component
        ComponentManager

        // setup
        lateInit()
    }

    private fun handler(event: EventHandler) {
        openAPI.registerEvent(event)
    }
}