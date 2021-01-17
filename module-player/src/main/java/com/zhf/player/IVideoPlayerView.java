package com.zhf.player;

import java.util.ArrayList;

import com.zhf.base.activity.IBaseView;
import com.zhf.common.contract.BaseCustomViewModel;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 */
public interface IVideoPlayerView extends IBaseView {
    /**
     * 数据加载完成
     *
     * @param viewModels data
     */
    void onDataLoadFinish(ArrayList<BaseCustomViewModel> viewModels);
}
