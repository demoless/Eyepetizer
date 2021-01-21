package com.four.buildsrc.compile

import com.android.build.gradle.internal.plugins.AppPlugin
import com.android.build.gradle.internal.plugins.LibraryPlugin
import com.four.buildsrc.compile.intercept.DepInterceptHelper
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.four.buildsrc.PluginSwitch
import com.four.buildsrc.compile.task.AssembleDebugForAar
import com.four.buildsrc.compile.task.ImplHelpTask

/**
 * 为每个project添加task
 *
 * /compile/下的类都是独立存在的，可剥离出去形成库
 */
class AssembleDebugForAarPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.gradle.beforeProject {
            DepInterceptHelper.rootProject = DepInterceptHelper.rootProject ?: findRootProject(target)
        }

        DepInterceptHelper.openAarRun = PluginSwitch.AarRun.isOpenAarRun(target)
        println("aar run ${DepInterceptHelper.openAarRun}")

        target.afterEvaluate {
            val rootProject = findRootProject(target)
            //根project则是所有都添加task，反之只添加当前
            if (rootProject == target) {
                target.allprojects {
                    this.afterEvaluate {
                        if (this.plugins.hasPlugin(LibraryPlugin::class.java)) {
                            addTaskToSubObject(this)
                        }
                    }
                    if (null == tasks.findByName(ImplHelpTask.NAME)) {
                        this.tasks.create(ImplHelpTask.NAME, ImplHelpTask::class.java)
                    }
                }
            } else {
                addTaskToSubObject(target)
            }
        }
    }

    private fun findRootProject(project: Project): Project {
        var rootProject = project
        while (rootProject != rootProject.rootProject) {
            rootProject = rootProject.rootProject
        }
        return rootProject
    }

    private fun addTaskToSubObject(project: Project) {
        if (project != project.rootProject
            && !project.plugins.hasPlugin(AppPlugin::class.java)
            && project.tasks.findByName(AssembleDebugForAar.NAME) == null) {
            project.tasks.create(AssembleDebugForAar.NAME, AssembleDebugForAar::class.java)
        }
    }
}