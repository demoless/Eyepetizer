package com.zhf.main.application;

import com.blankj.utilcode.util.Utils;
import com.zhf.base.base.BaseApplication;
import com.zhf.base.loadsir.EmptyCallback;
import com.zhf.base.loadsir.ErrorCallback;
import com.zhf.base.loadsir.LoadingCallback;
import com.zhf.base.loadsir.TimeoutCallback;
import com.zhf.common.IModuleInit;
import com.zhf.common.adapter.ScreenAutoAdapter;
import com.kingja.loadsir.core.LoadSir;
import com.orhanobut.logger.Logger;
import com.zhf.http.EasyHttp;
import com.zhf.http.cache.converter.GsonDiskConverter;
import com.zhf.http.cache.model.CacheMode;

/**
 * 应用模块: main
 * <p>
 * 类描述: main组件的业务初始化
 * <p>
 */
public class MainModuleInit implements IModuleInit
{
    @Override
    public boolean onInitAhead(BaseApplication application)
    {
        ScreenAutoAdapter.setup(application);
        EasyHttp.init(application);
        if (application.issDebug())
        {
            EasyHttp.getInstance().debug("easyhttp", true);
        }
        EasyHttp.getInstance()
            .setBaseUrl("http://baobab.kaiyanapp.com")
            .setReadTimeOut(15 * 1000)
            .setWriteTimeOut(15 * 1000)
            .setConnectTimeout(15 * 1000)
            .setRetryCount(3)
            .setCacheDiskConverter(new GsonDiskConverter())
            .setCacheMode(CacheMode.FIRSTREMOTE);
        LoadSir.beginBuilder()
            .addCallback(new ErrorCallback())
            .addCallback(new LoadingCallback())
            .addCallback(new EmptyCallback())
            .addCallback(new TimeoutCallback())
            .setDefaultCallback(LoadingCallback.class)
            .commit();
         Utils.init(application);
        Logger.i("main组件初始化完成 -- onInitAhead");
        return false;
    }
    
    @Override
    public boolean onInitLow(BaseApplication application)
    {
        return false;
    }
}
