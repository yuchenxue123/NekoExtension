package neko.sm.widget.widgets

import neko.sm.font.FontManager
import neko.sm.module.modules.render.ModuleSimpleHUD
import neko.sm.utils.always.projects.TargetProject
import neko.sm.utils.animation.AnimationType
import neko.sm.utils.animation.SimpleAnimation
import neko.sm.utils.extension.decimals
import neko.sm.utils.extension.step
import neko.sm.utils.render.RenderUtils
import neko.sm.widget.PluginWidget
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
        this.x = 100f
        this.y = 50f
    }

    private val font = FontManager.ROBOTO_20
    private val smallFont = FontManager.ROBOTO_16

    private var scale = 0.6f
    private val animation = SimpleAnimation(AnimationType.QUAD_OUT).setDuration(150f)
    private val healthAnimation = SimpleAnimation(AnimationType.QUAD_OUT).setStart(20f).setTarget(20f).setDuration(150f)

    private var show = false
    private var direction = false

    private var entity: LivingEntity? = null

    private var health = 20f
        set(value) {
            val oldValue = field
            if (value != oldValue) {
                if (value > oldValue) {
                    healthAnimation.setType(AnimationType.QUAD_OUT).setStart(oldValue).setTarget(value).reset()
                } else {
                    healthAnimation.setType(AnimationType.QUAD_IN).setStart(oldValue).setTarget(value).reset()
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
                        Color(40, 40, 40)
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

                    RenderUtils.drawRoundRect(
                        x + 6f,
                        y + currentHeight,
                        width - 12f,
                        4f,
                        2,
                        Color(0,0,0,200)
                    )

                    val process = entity?.let {
                        (healthAnimation.animate() / it.maxHealth).coerceAtMost(1f) * (width - 12f)
                    } ?: 0f

                    RenderUtils.drawRoundRect(
                        x + 6f,
                        y + currentHeight,
                        process,
                        4f,
                        2,
                        Color(119, 80,218,150)
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
            animation.setType(AnimationType.QUAD_OUT).setStart(scale).setTarget(1f).reset()
            show = true
        } else {
            animation.setType(AnimationType.QUAD_IN).setStart(scale).setTarget(0.6f).reset()
        }
    }

    override fun renderPredicate(): Boolean {
        return ModuleSimpleHUD.target
    }
}