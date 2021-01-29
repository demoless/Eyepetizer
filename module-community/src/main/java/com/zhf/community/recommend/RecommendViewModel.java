package com.zhf.community.recommend;


import androidx.annotation.NonNull;

import com.zhf.base.model.BasePagingModel;
import com.zhf.base.model.IPagingModelListener;
import com.zhf.base.viewmodel.MvmBaseViewModel;
import com.zhf.common.contract.BaseCustomViewModel;

import java.util.ArrayList;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public class RecommendViewModel
    extends MvmBaseViewModel<IRecommendView, RecommendModel>
    implements IPagingModelListener<ArrayList<BaseCustomViewModel>>
{
    

	@NonNull
    @Override
    protected RecommendModel initModel()
    {
        model = new RecommendModel();
        model.register(this);
        model.getCacheDataAndLoad();
        return model;
    }

    @Override
    public void detachUi() {
        super.detachUi();
        model.unRegister(this);
    }

    public void tryRefresh()
    {
        model.refresh();
    }
    
    public void loadMore()
    {
        model.loadMore();
    }
    
    @Override
    public void onLoadFinish(BasePagingModel model,
                             ArrayList<BaseCustomViewModel> data, boolean isEmpty,
                             boolean isFirstPage)
    {
        if (getPageView() != null)
        {
            if (isEmpty)
            {
                if (isFirstPage)
                {
                    getPageView().showEmpty();
                }
                else
                {
                    getPageView().onLoadMoreEmpty();
                }
            }
            else
            {
                getPageView().onDataLoadFinish(data, isFirstPage);
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
                getPageView().showFailure(prompt);
            }
            else
            {
                getPageView().onLoadMoreFailure(prompt);
            }
        }
    }
}
