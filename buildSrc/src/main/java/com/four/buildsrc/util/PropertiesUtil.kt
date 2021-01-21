package com.four.buildsrc.util

import org.gradle.api.Project
import java.util.*

object PropertiesUtil {

    private var localProperties: Properties? = null

    private var gradleProperties: Properties? = null

    fun getBooleanProperty(name: String, def: Boolean, project: Project, useLocal: Boolean = true) : Boolean {
        val value = getPropertyValue(name, project, useLocal)
        value ?: return def
        return try {
            value.toString().toBoolean()
        } catch (e: Exception) {
            def
        }
    }

    fun getStringProperty(name: String, def: String, project: Project, useLocal: Boolean = true) : String {
        val value = getPropertyValue(name, project, useLocal)
        value ?: return def
        return try {
            value.toString()
        } catch (e: Exception) {
            def
        }
    }

    fun getIntProperty(name: String, def: Int, project: Project, useLocal: Boolean = true) : Int {
        val value = getPropertyValue(name, project, useLocal)
        value ?: return def
        return try {
            value.toString().toInt()
        } catch (e: Exception) {
            def
        }
    }

    fun tryGetGradleProperties() = gradleProperties

    fun tryGetLocalProperties() = localProperties

    private fun getPropertyValue(name: String, project: Project, useLocal: Boolean = true) : Any? {
        var value: Any? = null
        if (useLocal) {
            value = readLocalProperties(project)[name]
        }
        return value ?: project.properties[name]
    }

    private fun readLocalProperties(project: Project) : Properties {
        if (localProperties != null) {
            return localProperties!!
        }

        val properties = Properties()
        val inputStream = findRootProject(project).file("local.properties").inputStream()
        try {
            properties.load(inputStream)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        localProperties = properties
        return properties
    }

    private fun findRootProject(project: Project) : Project {
        var rootProject = project
        while (rootProject != rootProject.rootProject) {
            rootProject = rootProject.rootProject
        }
        return rootProject
    }
}