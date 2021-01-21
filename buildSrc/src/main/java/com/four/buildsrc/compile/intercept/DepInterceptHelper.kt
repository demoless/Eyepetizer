package com.four.buildsrc.compile.intercept

import com.four.buildsrc.compile.task.AssembleDebugForAar
import com.four.buildsrc.compile.DepConstant
import com.four.buildsrc.compile.json.DepBean
import com.google.gson.Gson
import org.gradle.api.Project
import java.io.File
import java.nio.charset.StandardCharsets


object DepInterceptHelper {

    // 需要初始化
    var rootProject: Project? = null

    var openAarRun = false

    private val gson = Gson()

    fun checkCanIntercept() = rootProject != null && openAarRun

    fun findProject(path: String): Project? = rootProject?.allprojects?.find { it.path == path }

    fun getDataByProjectPath(path: String) : DepBean {
        val name = getModuleName(path)
        val buildAarPath = "${rootProject!!.rootDir}${AssembleDebugForAar.BUILD_AAR_DIR}"
        val buildJsonPath = "${rootProject!!.rootDir}${AssembleDebugForAar.BUILD_JSON_DIR}"
        val aarPath = "$buildAarPath/${getFileName(name)}.aar"
        val depJsonPath = "$buildJsonPath/${getFileName(name)}.json"

        val aarFile = File(aarPath)
        val jsonFile = File(depJsonPath)
        if (!aarFile.exists() || !jsonFile.exists()) {
            throw RuntimeException("aar file or json file is not exist. \n $aarPath \n$depJsonPath")
        }

        return gson.fromJson(jsonFile.readText(StandardCharsets.UTF_8), DepBean::class.java)
    }

    fun getModuleName(path: String) : String {
        val index = path.lastIndexOf(':')
        if (index < 0 || (index + 1) == path.length) {
            throw RuntimeException("$path, path formatting error.")
        }
        return path.substring(index + 1)
    }

    fun getFileName(moduleName: String) = "$moduleName-${DepConstant.Default.VERSION}"

    fun getFiles(filePath: String) = rootProject!!.files(filePath)
}