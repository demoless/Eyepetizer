package com.zhf.common.services;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * 应用模块:
 * <p>
 * 类描述: 与app设置相关
 * <p>
 *
 */
public interface ISettingService extends IProvider {
    String SETTINGS_SERVICE_NAME = "settings_service";

    /**
     * 语言
     **/
    public static final int CODE_LANGUAGE = 1;

    /**
     * 主题
     */
    public static final int CODE_THEME = 2;

    /**
     * 字体
     */
    public static final int CODE_FONT_SCHEME = 3;

    /***
     * 获取主题
     * @return 0:暗色  1.亮色  2.纯黑
     */
    public int getThemeValue();

    public void setThemeValue(String theme);



}
