package com.four.buildsrc.hotfix

import java.util.HashSet

//用于记录一些class信息
object ClassInfo {

    //类中注解标注的方法签名信息 并发采用PUBLICATION处理 底层使用CAS
    val classSets: HashSet<String> by lazy(mode = LazyThreadSafetyMode.PUBLICATION) {
        HashSet<String>()
    }
}