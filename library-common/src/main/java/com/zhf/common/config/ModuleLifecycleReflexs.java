package com.zhf.common.config;

/**
 * 应用模块: common
 * <p>
 * 类描述: 组件生命周期初始化类的类目管理者,在这里注册需要初始化的组件,通过反射动态调用各个组件的初始化方法
 * <p>
 *
 */
public class ModuleLifecycleReflexs
{
    /** 基础库 */
    private static final String BaseInit = "com.zhf.common.CommonModuleInit";
    
    /** main组件库 */
    private static final String MainInit =
        "com.zhf.main.application.MainModuleInit";

    /**用户组件初始化*/
    private static final String UserInit = "com.zhf.user.UserModuleInit";
    
    public static String[] initModuleNames = {BaseInit, MainInit,UserInit};
}
