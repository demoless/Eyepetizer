package com.four.buildsrc.compile

object DepConstant {

    object Type {
        const val API = "api"

        const val K_APT = "kapt"

        const val IMPLEMENTATION = "implementation"

        const val TEST_IMPLEMENTATION = "testImplementation"

        const val ANNOTATION_PROCESSOR = "annotationProcessor"

        const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"
    }

    object Ext {
        const val PROJECT = "project"
        const val AAR = "aar"
        const val JAR = "jar"
        const val REPO = "repo"
    }

    object Default {
        const val GROUP = ""
        const val VERSION = "1.0"
    }
}