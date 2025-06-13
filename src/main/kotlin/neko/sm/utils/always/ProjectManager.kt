package neko.sm.utils.always

import neko.sm.utils.always.projects.ScreenProject
import neko.sm.utils.always.projects.TargetProject
import neko.sm.utils.always.projects.TickProject
import neko.sm.utils.always.projects.WorldProject
import neko.sm.utils.interfaces.Accessor

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

object ProjectManager : Accessor {

    private val _projects = mutableListOf<Project>()

    val projects: MutableList<Project>
        get() = _projects.filter { it.isRunning }.toMutableList()

    fun initialize() {
        val projects = arrayOf(
            WorldProject,
            ScreenProject,
            TargetProject,
            TickProject,
        )
        projects.forEach(this::add)

        API.registerEvent(AlwaysHandler)
    }

    fun add(vararg projects: Project) {
        _projects.addAll(projects)
    }

    fun add(project: Project) {
        _projects.add(project)
    }
}