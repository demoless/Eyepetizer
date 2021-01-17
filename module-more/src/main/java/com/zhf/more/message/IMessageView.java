package com.zhf.more.message;

import java.util.List;

import com.zhf.base.activity.IBasePagingView;
import com.zhf.common.contract.BaseCustomViewModel;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public interface IMessageView extends IBasePagingView {
    /**
     * @param data 数据
     * @param isFirstPage 是否是第一页
     * */
    void onDataLoaded(List<BaseCustomViewModel> data, boolean isFirstPage);
}
