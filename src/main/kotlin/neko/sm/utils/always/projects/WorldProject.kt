package neko.sm.utils.always.projects

import neko.sm.utils.always.Project
import today.opai.api.interfaces.game.world.World

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

object WorldProject : Project {

    private var firstLoad = true
    var current: World? = null

    // thing
    private val list = mutableListOf<(WorldProject) -> Unit>()

    override fun onTick() {
        val updatedWorld = API.world

        if (updatedWorld != current) {
            current = updatedWorld
        }

        if (current != null && firstLoad) {
            list.forEach { it.invoke(this) }
            firstLoad = false
        }
    }

    /**
     * Run something at first load
     */
    fun run(thing: (WorldProject) -> Unit) {
        list.add(thing)
    }
}