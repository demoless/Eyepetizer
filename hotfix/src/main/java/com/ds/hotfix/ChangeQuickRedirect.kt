package com.ds.hotfix

/**
 * 补丁包需要实现该接口 方法执行前会执行修复判断逻辑，需要修复的方法由补丁包中对应方法代理执行
 */
interface ChangeQuickRedirect {
    //执行具体的修复或替换逻辑
    fun accessDispatch(methodSignature: String, paramArrayOfObject: Array<Any>?): Any?

    //判断当前方法是否为补丁包中需要修复的方法
    fun isSupport(methodSignature: String,`object`: Any): Boolean
}