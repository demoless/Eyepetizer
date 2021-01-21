package com.four.buildsrc

import org.gradle.api.JavaVersion

/**
 * 打包配置的全部属性
 */
object Env {

    const val VERSION = "1.0.0"
    const val VERSION_CODE = 1

    const val COMPILE_VERSION = 30
    const val MIN_SDK_VERSION = 21
    const val TARGET_SDK_VERSION = 30

    const val APPLICATION_ID = "com.four.ds"

    const val KOTLIN_VERSION = "1.4.10"
    const val KOTLIN_JVM_TARGET = "1.8"
    val JAVA_VERSION = JavaVersion.VERSION_1_8.toString()
}