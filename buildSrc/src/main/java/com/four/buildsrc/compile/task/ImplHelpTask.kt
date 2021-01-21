package com.four.buildsrc.compile.task

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class ImplHelpTask: DefaultTask() {

    companion object {
       val NAME = "logAllImpl"
    }

    init {
        group = "aarrun"
    }

    @TaskAction
    fun doAction() {
        project.configurations.forEach {
            it.dependencies.forEach { dep ->
                println(dep.name)
            }
        }
    }


}