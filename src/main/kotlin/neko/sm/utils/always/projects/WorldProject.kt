package neko.sm.utils.always.projects

import neko.sm.utils.always.Project

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

object WorldProject : Project {
    private var exetute = true

    private val handlers = mutableListOf<() -> Unit>()

    override fun onLoadWorld() {
        if (exetute) {
            handlers.forEach { it.invoke() }
            exetute = false
        }
    }
}