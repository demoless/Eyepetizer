package com.zhf.more.themes;

import java.util.ArrayList;

import com.zhf.base.activity.IBaseView;
import com.zhf.more.themes.bean.Tabs;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 */
public interface IThemeView extends IBaseView {

    /**
     * 数据加载完成
     * @param tabs tabs
     * */
    void onDataLoaded(ArrayList<Tabs> tabs);
}
