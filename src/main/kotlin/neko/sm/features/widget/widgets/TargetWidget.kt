package neko.sm.features.widget.widgets

import neko.sm.features.module.modules.render.ModuleSimpleHUD
import neko.sm.features.widget.PluginWidget
import neko.sm.font.FontManager
import neko.sm.utils.always.projects.TargetProject
import neko.sm.utils.animation.AnimationType
import neko.sm.utils.animation.SimpleAnimation
import neko.sm.utils.extension.decimals
import neko.sm.utils.extension.multiply
import neko.sm.utils.extension.step
import neko.sm.utils.render.RenderUtils
import today.opai.api.interfaces.game.entity.LivingEntity
import today.opai.api.interfaces.game.entity.Player
import java.awt.Color
import kotlin.math.max

/**
 * @author yuchenxue
 * @date 2025/03/12
 */

object TargetWidget : PluginWidget("TargetHUD") {

    init {
        this.x = 200f
        this.y = 150f
    }

    private val font = FontManager.ROBOTO_20
    private val smallFont = FontManager.ROBOTO_16

    private var scale = 0.6f
    private val animation = SimpleAnimation.create().type(AnimationType.QUAD_OUT).duration(150f)
    private val healthAnimation = SimpleAnimation.create().type(AnimationType.QUAD_OUT).start(20f).target(20f).duration(150f)

    private var show = false
    private var direction = false

    private var entity: LivingEntity? = null

    private var health = 20f
        set(value) {
            val oldValue = field
            if (value != oldValue) {
                if (value > oldValue) {
                    healthAnimation.type(AnimationType.QUAD_OUT).start(oldValue).target(value).reset()
                } else {
                    healthAnimation.type(AnimationType.QUAD_IN).start(oldValue).target(value).reset()
                }

            }
            field = value
        }

    override fun render() {
        sync {
            var currentWidth = 0f
            var currentHeight = 0f

            TargetProject.onChange { last, new ->
                if (last == null && new != null) {
                    update(true)
                }

                if (last != null && new == null) {
                    update(false)
                }

                if (new != null) {
                    entity = new
                }
            }

            if (entity == null) {
                return@sync currentWidth to currentHeight
            }

            currentHeight += 6f

            if (!direction && animation.hasFinished() && healthAnimation.hasFinished()) {
                show = false
            }

            scale = animation.animate()

            val name = entity?.name ?: "NULL"
            health = (entity?.health ?: 20f).step(0.1f)

            if (show) {
                RenderUtils.scale(x + width / 2f, y + height / 2f, scale) {
                    RenderUtils.drawRoundRect(
                        x,
                        y,
                        width,
                        height,
                        5,
                        Color(40, 40, 40).multiply(opacity = ModuleSimpleHUD.targetOpacity.toFloat())
                    )

                    val drawPlayer = entity?.let {
                        if (it is Player) {
                            it
                        } else API.localPlayer
                    } ?: API.localPlayer

                    RenderUtils.drawRoundPlayerHead(
                        x + 6f,
                        y + currentHeight,
                        24f,
                        drawPlayer,
                        4f
                    )

                    font.drawStringWithShadow(
                        name,
                        x.toDouble() + 6f + 24f + 6f,
                        y.toDouble() + 2f + currentHeight,
                        Color.WHITE.rgb
                    )

                    smallFont.drawStringWithShadow(
                        "Health: ${health.step(0.1f).decimals(1)}",
                        x.toDouble() + 6f + 24f + 6f,
                        y.toDouble() + 2f + currentHeight + 6f + font.height,
                        Color.WHITE.rgb
                    )

                    currentHeight += 24f + 6f

                    // 应该是血条背景
                    RenderUtils.drawRoundRect(
                        x + 6f,
                        y + currentHeight,
                        width - 12f,
                        4f,
                        2,
                        Color(0,0,0,200).multiply(opacity = (ModuleSimpleHUD.targetOpacity.toFloat() * 1.2f).coerceAtMost(1f))
                    )

                    val process = entity?.let {
                        (healthAnimation.animate() / it.maxHealth).coerceAtMost(1f) * (width - 12f)
                    } ?: 0f


                    // 应该是血条
                    RenderUtils.drawRoundRect(
                        x + 6f,
                        y + currentHeight,
                        process,
                        4f,
                        2,
                        Color(119, 80,218,150).multiply(opacity = (ModuleSimpleHUD.targetOpacity.toFloat() * 1.2f).coerceAtMost(1f))
                    )

                    currentHeight += 4f + 6f
                }
            }

            currentWidth = max(120f, font.getWidth(name) + 12f)

            return@sync currentWidth to currentHeight
        }
    }

    private fun update(big: Boolean) {
        direction = big
        if (big) {
            animation.type(AnimationType.QUAD_OUT).start(scale).target(1f).reset()
            show = true
        } else {
            animation.type(AnimationType.QUAD_IN).start(scale).target(0.6f).reset()
        }
    }

    override fun renderPredicate(): Boolean {
        return ModuleSimpleHUD.target
    }
}