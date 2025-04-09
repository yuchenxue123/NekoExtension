package neko.sm.utils.always

import neko.sm.utils.always.projects.*

/**
 * @author yuchenxue
 * @date 2025/03/10
 */

object ProjectManager {

    init {
        val projects = arrayOf(
            WorldProject,
            ScreenProject,
            TargetProject,
            NotificationProject,
            MoveProject,
            TickProject
        )

        projects.forEach(this::add)
    }

    private fun add(vararg projects: Project) {
        AlwaysHandler.realProjects.addAll(projects)
    }

    fun add(project: Project) {
        AlwaysHandler.realProjects.add(project)
    }
}