package com.four.buildsrc.util

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

object FileUtil {

    fun copyFileByOverlay(targetPath: String, copyPath: String) {
        println("------------------targetPath:${targetPath} copyPath:${copyPath} ---------------------")
        generateFileSafely(targetPath)
        val copyFile = File(copyPath)
        if (!copyFile.exists()) {
            throw FileNotFoundException()
        }

        try {
            copyFile.copyTo(File(targetPath), true)
        } catch (e: IOException) {
            throw e
        }
    }

    fun generateFileSafely(path: String) {
        val file = File(path)
        if (!file.exists()) {
            val last = path.lastIndexOf('/') + 1
            if (last <= 0) {
                throw RuntimeException("the path is error. $path")
            } else {
                val dirFile = File(path.substring(0, last))
                if (!dirFile.exists()) {
                    dirFile.mkdirs()
                }
                file.createNewFile()
            }
        }
    }

    fun writeStringByOverlay(path: String, string: String) {
        generateFileSafely(path)
        val file = File(path)
        try {
            file.writeText(string)
        } catch (e: IOException) {
            throw e
        }
    }
}