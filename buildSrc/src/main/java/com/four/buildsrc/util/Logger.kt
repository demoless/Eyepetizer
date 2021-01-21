package com.four.buildsrc.util

object Logger {

    fun log(msg: String) {
        val open = try {
            PropertiesUtil.tryGetGradleProperties()?.getProperty("buildSrc.openLog")?.toBoolean() ?: true
        } catch (e: Exception) {
            false
        }
        if (open) {
            println(msg)
        }
    }
}