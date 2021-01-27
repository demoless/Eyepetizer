package com.zhf.main.application

import com.blankj.utilcode.util.Utils
import com.kingja.loadsir.core.LoadSir
import com.orhanobut.logger.Logger
import com.zhf.base.base.BaseApplication
import com.zhf.base.loadsir.EmptyCallback
import com.zhf.base.loadsir.ErrorCallback
import com.zhf.base.loadsir.LoadingCallback
import com.zhf.base.loadsir.TimeoutCallback
import com.zhf.common.IModuleInit
import com.zhf.common.adapter.ScreenAutoAdapter
import com.zhf.http.EasyHttp
import com.zhf.http.cache.converter.GsonDiskConverter
import com.zhf.http.cache.model.CacheMode

/**
 * created by demoless on 2021/1/27
 * description: main组件的业务初始化
 */
class MainModuleInit : IModuleInit {
    override fun onInitAhead(application: BaseApplication?): Boolean {
        ScreenAutoAdapter.setup(application!!)
        EasyHttp.init(application)
        if (application.issDebug()) {
            EasyHttp.getInstance().debug("easyhttp", true)
        }
        EasyHttp.getInstance()
                .setBaseUrl("http://baobab.kaiyanapp.com")
                .setReadTimeOut((15 * 1000).toLong())
                .setWriteTimeOut((15 * 1000).toLong())
                .setConnectTimeout((15 * 1000).toLong())
                .setRetryCount(3)
                .setCacheDiskConverter(GsonDiskConverter())
                .setCacheMode(CacheMode.FIRSTREMOTE)
        LoadSir.beginBuilder()
                .addCallback(ErrorCallback())
                .addCallback(LoadingCallback())
                .addCallback(EmptyCallback())
                .addCallback(TimeoutCallback())
                .setDefaultCallback(LoadingCallback::class.java)
                .commit()
        Utils.init(application)
        Logger.i("main组件初始化完成 -- onInitAhead")
        return false
    }

    override fun onInitLow(application: BaseApplication?): Boolean = false
}