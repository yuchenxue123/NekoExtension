package neko.sm.features.module.modules.render

import neko.sm.client.module.ModuleAccessor
import neko.sm.client.user.UserAccessor
import neko.sm.features.module.PluginModule
import neko.sm.features.module.modules.render.supereyes.nametags.NameTags
import neko.sm.utils.extension.cancel
import today.opai.api.enums.EnumModuleCategory
import today.opai.api.events.EventRender2D
import today.opai.api.events.EventRenderNameTag
import today.opai.api.interfaces.game.entity.Player

/**
 * @author yuchenxue
 * @date 2025/03/02
 */

object ModuleSuperEyes : PluginModule(
    "Super Eyes",
    "Super eyes.",
    EnumModuleCategory.VISUAL
) {
    private val name by boolean("Name Tags", true)
    private val nameTagStyle by enum("Name Tag Style", NameTags.SIMPLE) { name }
    // options
    private val clear by boolean("Clear Name", false) { name }
    private val checkLong by boolean("Check Long IRC", true) { name }
    private val bot by boolean("Bot", false) { name }
    private val local by boolean("Local Player", false) { name }

    override fun onRenderNameTags(event: EventRenderNameTag) {
        if (API.isNull) {
            return
        }

        if (name) {
            event.cancel()
        }
    }

    override fun onRender2D(event: EventRender2D) {
        if (API.isNull) {
            return
        }

        if (name) {
            renderNameTags()
        }
    }

    private fun renderNameTags() {
        for (player in API.world.loadedPlayerEntities) {
            val ab = ModuleAccessor.AntiBot

            if (ab.isEnabled && ab.isBot(player) && !bot) {
                continue
            }

            if (player.entityId != API.localPlayer.entityId) {
                renderNameTag(player)
            } else {
                if (local && API.options.thirdPersonViewState != 0) {
                    renderNameTag(player)
                }
            }
        }
    }

    private fun renderNameTag(player: Player) {
        nameTagStyle.render(player)
    }

    fun getRenderName(player: Player): String {
        val isHacker = ModuleAccessor.AntiCheat.isHacker(player)
                && ModuleAccessor.AntiCheat.isEnabled

        val isFriend = API.isFriend(player.name)
        val username = UserAccessor.getUserPlayer(player).username

        val prefix1 = if (isFriend) "§a(Friend)§r " else ""
        val prefix2 = if (isHacker) "§c(Hacker)§r " else ""

        val prefix3 = if (username.isNotEmpty()) {
            when {
                username.length > 24 && checkLong -> "§b(太长了)§r "
                // 这是什么
                username == "yuchenxue" && API.clientUsername != "yuchenxue" -> "§b(补药打我)§r "
                else -> "§b($username)§r "
            }
        } else ""

        val name = if (clear) player.name else player.displayName

        return prefix1 + prefix2 + prefix3 + name
    }
}
