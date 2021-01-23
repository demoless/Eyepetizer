package com.four.buildsrc.hotfix

data class ClassData(val fileName: String,//class文件名
                    val className: String,//class 类名
                    val buildType: Int//构建类型 是普通build 还是用于生成patch
                    )