package com.zhf.home.discover;

import com.zhf.base.activity.IBaseView;
import com.zhf.common.contract.BaseCustomViewModel;

import java.util.ArrayList;


/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public interface IDisCoverView extends IBaseView
{
    
    /**
     * 数据加载完成
     *
     * @param viewModels data
     */
    void onDataLoadFinish(ArrayList<BaseCustomViewModel> viewModels,
                          boolean isEmpty);
    
}
