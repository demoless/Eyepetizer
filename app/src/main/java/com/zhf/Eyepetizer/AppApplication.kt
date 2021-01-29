package com.zhf.Eyepetizer

import com.zhf.base.base.BaseApplication
import com.zhf.common.config.ModuleLifecycleConfig

/**
 * created by demoless on 2021/1/29
 * description:
 */
class AppApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        setsDebug(BuildConfig.DEBUG)
        // 初始化需要初始化的组件
        ModuleLifecycleConfig.getInstance().initModuleAhead(this)
    }
}