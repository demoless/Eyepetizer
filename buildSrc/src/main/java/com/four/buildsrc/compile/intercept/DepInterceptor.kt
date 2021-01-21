package com.four.buildsrc.compile.intercept

import com.four.buildsrc.compile.DepConstant
import com.four.buildsrc.compile.json.DepBean
import org.gradle.api.Project
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.accessors.runtime.addExternalModuleDependencyTo
import org.gradle.kotlin.dsl.project
import java.io.File

/**
 * 依赖拦截
 */
object DepInterceptor {

    fun interceptImplProject(handlerScope: DependencyHandlerScope,
                             path: String,
                             fromKts: Boolean = true) : Boolean {
        return handleDepProject(handlerScope, path, DepConstant.Type.IMPLEMENTATION, fromKts)
    }

    fun interceptImplAar(handlerScope: DependencyHandlerScope,
                         name: String,
                         group: String = "",
                         version: String? = null,
                         fromKts: Boolean = true) : Boolean {
        if (DepInterceptHelper.checkCanIntercept() && !fromKts) {
            depExt(DepConstant.Type.IMPLEMENTATION, DepConstant.Ext.AAR, handlerScope, name, group, version)
            return true
        }
        return false
    }

    fun interceptImplJar(handlerScope: DependencyHandlerScope,
                         files: ConfigurableFileCollection, fromKts: Boolean = true) : Boolean {
        //handlerScope.add(DepConstant.Type.IMPLEMENTATION, files)
        return false
    }

    fun interceptImplRepo(handlerScope: DependencyHandlerScope,
                          variant: String,
                          fromKts: Boolean = true) : Boolean {
        if (DepInterceptHelper.checkCanIntercept() && !fromKts) {
            depRepo(DepConstant.Type.IMPLEMENTATION, variant, handlerScope)
            return true
        }
        return false
    }

    fun interceptApiRepo(handlerScope: DependencyHandlerScope,
                         variant: String,
                         fromKts: Boolean = true): Boolean {
        if (DepInterceptHelper.checkCanIntercept() && !fromKts) {
            depRepo(DepConstant.Type.API, variant, handlerScope)
            return true
        }
        return false
    }

    fun interceptApiProject(handlerScope: DependencyHandlerScope,
                            path: String,
                            fromKts: Boolean = true): Boolean {
        return handleDepProject(handlerScope, path, DepConstant.Type.API, fromKts)
    }

    fun interceptApiAar(handlerScope: DependencyHandlerScope,
                         name: String,
                         group: String = "",
                         version: String? = null,
                         fromKts: Boolean = true) : Boolean {
        if (DepInterceptHelper.checkCanIntercept() && !fromKts) {
            depExt(DepConstant.Type.API, DepConstant.Ext.AAR, handlerScope, name, group, version)
            return true
        }
        return false
    }

    fun interceptApiJar(handlerScope: DependencyHandlerScope,
                         files: ConfigurableFileCollection, fromKts: Boolean = true) : Boolean {
        //handlerScope.add(DepConstant.Type.API, collection)
        return false
    }


    fun interceptAPTRepo(handlerScope: DependencyHandlerScope,
                          variant: String,
                          fromKts: Boolean = true) : Boolean {
        if (DepInterceptHelper.checkCanIntercept() && !fromKts) {
            depRepo(DepConstant.Type.ANNOTATION_PROCESSOR, variant, handlerScope)
            return true
        }
        return false
    }

    fun interceptAPTProject(handlerScope: DependencyHandlerScope,
                             path: String,
                             fromKts: Boolean = true) : Boolean {
        return handleDepProject(handlerScope, path, DepConstant.Type.ANNOTATION_PROCESSOR, fromKts)
    }

    fun interceptAPTAar(handlerScope: DependencyHandlerScope,
                        name: String,
                        group: String = "",
                        version: String? = null,
                        fromKts: Boolean = true) : Boolean {
        if (DepInterceptHelper.checkCanIntercept() && !fromKts) {
            depExt(DepConstant.Type.ANNOTATION_PROCESSOR, DepConstant.Ext.AAR, handlerScope, name, group, version)
            return true
        }
        return false
    }

    fun interceptKAPTRepo(handlerScope: DependencyHandlerScope,
                         variant: String,
                         fromKts: Boolean = true) : Boolean {
        if (DepInterceptHelper.checkCanIntercept() && !fromKts) {
            depRepo(DepConstant.Type.K_APT, variant, handlerScope)
            return true
        }
        return false
    }

    fun interceptKAPTProject(handlerScope: DependencyHandlerScope,
                            path: String,
                            fromKts: Boolean = true) : Boolean {
        return handleDepProject(handlerScope, path, DepConstant.Type.K_APT, fromKts)
    }

    fun interceptKAPTAar(handlerScope: DependencyHandlerScope,
                        name: String,
                        group: String = "",
                        version: String? = null,
                        fromKts: Boolean = true) : Boolean {
        if (DepInterceptHelper.checkCanIntercept() && !fromKts) {
            depExt(DepConstant.Type.K_APT, DepConstant.Ext.AAR, handlerScope, name, group, version)
            return true
        }
        return false
    }

    fun interceptTestImpl(handlerScope: DependencyHandlerScope,
                          variant: String,
                          fromKts: Boolean = true) : Boolean {
        if (DepInterceptHelper.checkCanIntercept() && !fromKts) {
            depRepo(DepConstant.Type.TEST_IMPLEMENTATION, variant, handlerScope)
            return true
        }
        return false
    }

    fun interceptAndroidTestImpl(handlerScope: DependencyHandlerScope,
                                 variant: String,
                                 fromKts: Boolean = true) : Boolean {
        if (DepInterceptHelper.checkCanIntercept() && !fromKts) {
            depRepo(DepConstant.Type.ANDROID_TEST_IMPLEMENTATION, variant, handlerScope)
            return true
        }
        return false
    }

    private fun depAll(bean: DepBean, handlerScope: DependencyHandlerScope) {
        val fromKts = false
        //jar包直接打进aar
        bean.implList.forEach {
            when (it.ext) {
                DepConstant.Ext.REPO -> {
                    interceptImplRepo(handlerScope, "${it.group}:${it.name}:${it.version}", fromKts)
                }
                DepConstant.Ext.PROJECT -> {
                    interceptImplProject(handlerScope, it.projectPath!!, fromKts)
                }
                DepConstant.Ext.JAR -> {
                    //interceptImplJar(handlerScope, DepInterceptHelper.getFiles(it.filePath!!), fromKts)
                }
                else -> {
                    interceptImplAar(handlerScope, it.name, it.group, it.version, fromKts)
                }
            }
        }
        bean.apiList.forEach {
            when (it.ext) {
                DepConstant.Ext.REPO -> {
                    interceptApiRepo(handlerScope, "${it.group}:${it.name}:${it.version}", fromKts)
                }
                DepConstant.Ext.PROJECT -> {
                    interceptApiProject(handlerScope, it.projectPath!!, fromKts)
                }
                DepConstant.Ext.JAR -> {
                    //interceptApiJar(handlerScope, DepInterceptHelper.getFiles(it.filePath!!), fromKts)
                }
                else -> {
                    interceptApiAar(handlerScope, it.name, it.group, it.version, fromKts)
                }
            }
        }
        bean.testImplList.forEach {
            if (it.ext == DepConstant.Ext.REPO) {
                interceptTestImpl(handlerScope, "${it.group}:${it.name}:${it.version}", fromKts)
            }
        }
        bean.androidTestImplList.forEach {
            if (it.ext == DepConstant.Ext.REPO) {
                interceptAndroidTestImpl(handlerScope, "${it.group}:${it.name}:${it.version}", fromKts)
            }
        }
    }

    private fun depRepo(type: String, variant: String, handlerScope: DependencyHandlerScope) {
        handlerScope.dependencies.add(type, variant)
    }

    private fun depProject(type: String, path: String, handlerScope: DependencyHandlerScope) {
        handlerScope.dependencies.add(type, handlerScope.dependencies.project(path))
    }

    private fun depExt(type: String, ext: String, scope: DependencyHandlerScope,
               name: String, group: String = "", version: String? = null) {
        addExternalModuleDependencyTo(scope.dependencies, type, group, name, version,
            null, null, ext, null)
    }

    private fun handleDepProject(handlerScope: DependencyHandlerScope,
                                 path: String,
                                 type: String,
                                 fromKts: Boolean = true) : Boolean {
        if (!DepInterceptHelper.checkCanIntercept()) {
            return false
        }

        val project: Project? = DepInterceptHelper.findProject(path)
        //不是来自于脚本，则需要手动添加project
        if (!fromKts && project != null) {
            depProject(type, project.path, handlerScope)
            return true
        }

        //不存在则依赖它的aar
        return if (project == null) {
            val bean = DepInterceptHelper.getDataByProjectPath(path)
            //依赖所有
            depAll(bean, handlerScope)
            //依赖自己的aar
            val fileName = DepInterceptHelper.getFileName(DepInterceptHelper.getModuleName(path))
            val fromKts = false
            when (type) {
                DepConstant.Type.IMPLEMENTATION -> {
                    interceptImplAar(handlerScope, fileName, DepConstant.Default.GROUP,
                        DepConstant.Default.VERSION, fromKts)
                }
                DepConstant.Type.API -> {
                    interceptApiAar(handlerScope, fileName, DepConstant.Default.GROUP,
                        DepConstant.Default.VERSION, fromKts)
                }
                DepConstant.Type.ANNOTATION_PROCESSOR -> {
                    interceptAPTAar(handlerScope, fileName, DepConstant.Default.GROUP,
                        DepConstant.Default.VERSION, fromKts)
                }
                DepConstant.Type.K_APT -> {
                    interceptKAPTAar(handlerScope, fileName, DepConstant.Default.GROUP,
                        DepConstant.Default.VERSION, fromKts)
                }
            }
            true
        } else false
    }
}