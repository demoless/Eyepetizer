package com.four.buildsrc.compile.task

import com.android.build.gradle.internal.plugins.AppPlugin
import com.four.buildsrc.compile.DepConstant
import com.four.buildsrc.compile.json.*
import com.four.buildsrc.util.*
import com.google.gson.Gson
import org.gradle.api.DefaultTask
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency
import org.gradle.api.internal.artifacts.dependencies.DefaultProjectDependency
import org.gradle.api.internal.artifacts.dependencies.DefaultSelfResolvingDependency
import org.gradle.api.tasks.TaskAction
import java.io.File

/**
 * 拷贝aar，并且将依赖库生成json文件
 * aar的版本指定为1.0
 */
open class AssembleDebugForAar : DefaultTask() {

    companion object {
        const val NAME = "assembleDebugForAar"

        const val BUILD_AAR_DIR = "/aarrun/aars"

        const val BUILD_JSON_DIR = "/aarrun/jsons"

        val JSON_HELPER = Gson()
    }

    init {
        enabled =
            if (project != project.rootProject && !project.plugins.hasPlugin(AppPlugin::class.java)) {
                this.dependsOn("${project.path}:assembleDebug")
                true
            } else {
                false
            }

        group = "aarrun"
    }

    /**
     * 简化版，project依赖就不考虑版本问题
     */
    @TaskAction
    fun doAction() {
        val copyAarPath = "${project.buildDir}/outputs/aar/${project.name}-debug.aar"
        val buildAarPath = "${project.rootDir}$BUILD_AAR_DIR"
        val buildJsonPath = "${project.rootDir}$BUILD_JSON_DIR"
        val aarPath = "$buildAarPath/${project.name}-${DepConstant.Default.VERSION}.aar"
        val depJsonPath = "$buildJsonPath/${project.name}-${DepConstant.Default.VERSION}.json"

        if (!File(copyAarPath).exists()) {
            Logger.log("${project.name} aar file not find!!!")
            Logger.log("${project.name} aar file not find!!!")
            Logger.log("${project.name} aar file not find!!!")
            return
        }

        //将aar copy到/aar_build
        FileUtil.copyFileByOverlay(aarPath, copyAarPath)

        val target = DepBean()
        target.apiList = createJSONObjects(DepConstant.Type.API)
        target.implList = createJSONObjects(DepConstant.Type.IMPLEMENTATION)
        target.testImplList = createJSONObjects(DepConstant.Type.TEST_IMPLEMENTATION)
        target.androidTestImplList = createJSONObjects(DepConstant.Type.ANDROID_TEST_IMPLEMENTATION)

        //写入json
        FileUtil.writeStringByOverlay(depJsonPath, JSON_HELPER.toJson(target))
    }

    private fun createJSONObjects(configName: String): List<DepBean.Data> {
        val dataList = mutableListOf<DepBean.Data>()
        project.configurations.asMap[configName]?.allDependencies?.apply {
            forEach {
                val data = DepBean.Data()
                data.group = it.group ?: ""
                data.name = it.name
                if (it.version.isNullOrEmpty() || it.version == "unspecified") {
                    data.version = DepConstant.Default.VERSION
                } else {
                    data.version = it.version!!
                }

                //暂时只考虑了aar project jar repo依赖的情况
                //aar 的版本只为1.0
                when (it) {
                    is DefaultProjectDependency -> {
                        data.group = DepConstant.Default.GROUP
                        data.ext = DepConstant.Ext.PROJECT
                        data.projectPath =  it.dependencyProject.path
                            ?: throw NullPointerException("project path is null")
                    }
                    is DefaultExternalModuleDependency -> {
                        if (it.artifacts.size != 0)  {
                            it.artifacts.forEach out@ { art ->
                                data.ext =  art.type
                                return@out
                            }
                        } else {
                            data.ext = DepConstant.Ext.REPO
                        }
                    }
                    //jar
                    is DefaultSelfResolvingDependency -> {
                        it.files.elements.get().forEach { file ->
                            data.ext = DepConstant.Ext.JAR
                            data.filePath = file.asFile.absolutePath
                        }
                    }
                    else -> {
                        data.ext = DepConstant.Ext.REPO
                    }
                }
                dataList.add(data)
            }
        }
        return dataList
    }
}