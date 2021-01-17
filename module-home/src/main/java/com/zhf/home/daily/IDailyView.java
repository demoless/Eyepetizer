package com.zhf.home.daily;

import com.zhf.base.activity.IBasePagingView;
import com.zhf.common.contract.BaseCustomViewModel;

import java.util.ArrayList;


/**
 * 应用模块: daily
 * <p>
 * 类描述: UI 更新
 * <p>
 */
public interface IDailyView  extends IBasePagingView {

    /**
     * 数据加载完成
     *
     * @param viewModels data
     */
    void onDataLoadFinish(ArrayList<BaseCustomViewModel> viewModels,
                          boolean isFirstPage);

}
