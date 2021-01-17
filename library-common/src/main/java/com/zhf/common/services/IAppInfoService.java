package com.zhf.common.services;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * 应用模块: services
 * <p>
 * 类描述: app相关信息
 * <p>
 */
public interface IAppInfoService extends IProvider
{
    String APP_INFO_SERVICE_NAME = "app_info_service";
    
    String getApplicationName();
    
    String getApplicationVersionName();
    
    String getApplicationVersionCode();
    
    boolean getApplicationDebug();

}
