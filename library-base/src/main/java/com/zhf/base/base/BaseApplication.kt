package com.zhf.base.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.zhf.base.BuildConfig

/**
 * created by demoless on 2021/1/27
 * description:
 */
open class BaseApplication : Application() {
    companion object {
        @JvmStatic
        private var isDebug: Boolean = BuildConfig.DEBUG

        @JvmName("getInstance1")
        fun getInstance() : BaseApplication = INSTANCE

        @JvmStatic
        private val INSTANCE: BaseApplication by lazy(mode = LazyThreadSafetyMode.PUBLICATION) {
            BaseApplication().apply {
                this.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                    override fun onActivityCreated(activity: Activity,
                                                   savedInstanceState: Bundle?) {
                        AppManager.getInstance().addActivity(activity)
                    }

                    override fun onActivityStarted(activity: Activity) {}
                    override fun onActivityResumed(activity: Activity) {}
                    override fun onActivityPaused(activity: Activity) {}
                    override fun onActivityStopped(activity: Activity) {}
                    override fun onActivitySaveInstanceState(
                            activity: Activity, outState: Bundle) {
                    }

                    override fun onActivityDestroyed(activity: Activity) {
                        AppManager.getInstance().removeActivity(activity)
                    }
                })
            }
        }
    }

    fun issDebug(): Boolean = isDebug

    fun setsDebug(debug: Boolean) {
        isDebug = debug
    }
}