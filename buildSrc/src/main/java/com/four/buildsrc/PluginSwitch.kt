package com.four.buildsrc

import com.four.buildsrc.util.PropertiesUtil
import org.gradle.api.Project

/**
 * 插件统一管理
 *
 * 规范：
 *  1. 所有的插件应该在gradle.properties中设置属性并关闭;
 *  2. 为了便于本地调试，可以在local.properties中设置相同属性来打开你的插件;
 *  3. local.properties中属性等级优先于gradle.properties;
 *
 *  例如：
 *  gradle.properties中: compile.openAarRun=false
 *  local.properties中:  compile.openAarRun=true
 *  这样本地依旧使用aarrun，但是线上默认关闭aarrun
 */
object PluginSwitch {

    /**
     * aar run
     */
    object AarRun {
        //aar run总开关
        private const val COMPILE_AAR_RUN = "run.openAarRun"

        //项目引入哪些module，例如 app,common-util
        private const val INCLUDE_PROJECTS = "run.projects"

        @JvmStatic
        fun isOpenAarRun(project : Project) = PropertiesUtil.getBooleanProperty(COMPILE_AAR_RUN, false, project)
    }

    /**
     * 热修
     */
    object Hotfix {

        private const val OPEN_HOTFIX = "hotfix.open"

        @JvmStatic
        fun isOpenHotfix(project: Project) = PropertiesUtil.getBooleanProperty(OPEN_HOTFIX, false, project)
    }
}