package com.zhf.more.topic;

import androidx.annotation.NonNull;
import com.zhf.base.model.BasePagingModel;
import com.zhf.base.model.IPagingModelListener;
import com.zhf.base.viewmodel.MvmBaseViewModel;
import com.zhf.common.contract.BaseCustomViewModel;

import java.util.List;


/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 */
public class TopicFragmentViewModel
    extends MvmBaseViewModel<ITopicView, TopicModel>
    implements IPagingModelListener<List<BaseCustomViewModel>>
{
    @NonNull
	@Override
    protected TopicModel initModel()
    {
        model = new TopicModel();
        model.register(this);
        model.getCacheDataAndLoad();
        return model;
    }
    
    @Override
    public void onLoadFinish(BasePagingModel model,
                             List<BaseCustomViewModel> data, boolean isEmpty, boolean isFirstPage)
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
                getPageView().onDataLoaded(data, isFirstPage);
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
    
    @Override
    public void detachUi()
    {
        super.detachUi();
        model.unRegister(this);
    }

    public void tryRefresh() {
        model.refresh();
    }

    public void loadMore() {
        model.loadMore();
    }
}
