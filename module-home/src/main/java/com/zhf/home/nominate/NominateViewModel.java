package com.zhf.home.nominate;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import com.zhf.base.model.BasePagingModel;
import com.zhf.base.model.IPagingModelListener;
import com.zhf.base.viewmodel.MvmBaseViewModel;
import com.zhf.common.contract.BaseCustomViewModel;

/**
 * 应用模块: 首页
 * <p>
 * 类描述: 处理业务结果 & UI刷新
 * <p>
 */
public class NominateViewModel
    extends MvmBaseViewModel<INominateView, NominateModel>
    implements IPagingModelListener<ArrayList<BaseCustomViewModel>>
{

    @Override
    public void onLoadFinish(BasePagingModel model,
                             ArrayList<BaseCustomViewModel> data, boolean isEmpty,
                             boolean isFirstPage)
    {
        if (getPageView() != null)
        {

            // 如果数据为空
            if (isEmpty)
            {
                // 是否是上拉刷新
                if (isFirstPage)
                {
                    // 显示空页面
                    getPageView().showEmpty();
                }
                else
                {
                    // 没有更多了
                    getPageView().onLoadMoreEmpty();
                }
            }
            else
            {
                getPageView().onDataLoadFinish(data,isFirstPage);
            }
        }
        
    }
    
    @Override
    public void onLoadFail(BasePagingModel model, String prompt,
        boolean isFirstPage)
    {
        if (getPageView() != null)
        {
            if (isFirstPage)
            {
                // 刷新失败
                getPageView().showFailure(prompt);
            }
            else
            {
                // 加载更多失败
                getPageView().onLoadMoreFailure(prompt);
                
            }
        }
    }
    
    @Override
    protected void loadData()
    {
        
    }

    @NonNull
	@Override
    protected NominateModel initModel() {
        model = new NominateModel();
        model.register(this);
        model.getCacheDataAndLoad();
        return model;
    }

    @Override
    public void detachUi() {
        super.detachUi();
        model.unRegister(this);
    }

    public void tryToRefresh()
    {
        model.refresh();
    }
    
    public void loadMore()
    {
        model.loadMore();
    }
    
}
