package com.zhf.more.topic;

import java.util.ArrayList;
import java.util.List;

import com.zhf.base.model.BasePagingModel;
import com.zhf.base.utils.GsonUtils;
import com.zhf.common.contract.BaseCustomViewModel;
import com.zhf.more.themes.childpager.bean.ThemesItemViewModel;
import com.zhf.more.topic.bean.TopicBean;
import com.zhf.http.EasyHttp;
import com.zhf.http.cache.model.CacheMode;
import com.zhf.http.callback.SimpleCallBack;
import com.zhf.http.exception.ApiException;

import android.text.TextUtils;

import io.reactivex.disposables.Disposable;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 */
public class TopicModel<T> extends BasePagingModel<T>
{
    
    private Disposable disposable;
    
    private Disposable disposable1;
    
    @Override
    protected void load()
    {
        disposable = EasyHttp.get("/api/v7/topic/list")
            .cacheKey(getClass().getSimpleName())
            .execute(new SimpleCallBack<String>()
            {
                @Override
                public void onError(ApiException e)
                {
                    loadFail(e.getMessage(), isRefresh);
                }
                
                @Override
                public void onSuccess(String s)
                {
                    parseData(s);
                }
            });
    }
    
    private void loadMore(String nextPageUrl)
    {
        disposable1 = EasyHttp.get(nextPageUrl)
            .cacheMode(CacheMode.NO_CACHE)
            .execute(new SimpleCallBack<String>()
            {
                @Override
                public void onError(ApiException e)
                {
                    loadFail(e.getMessage(), isRefresh);
                }
                
                @Override
                public void onSuccess(String s)
                {
                    parseData(s);
                }
            });
    }
    
    private void parseData(String s)
    {
        TopicBean topicBean = GsonUtils.fromLocalJson(s,TopicBean.class);
        List<BaseCustomViewModel> viewModels = new ArrayList<>();
        if (topicBean != null){
            nextPageUrl = topicBean.getNextPageUrl();
            for (TopicBean.ItemListBean itemData : topicBean.getItemList()){
                ThemesItemViewModel viewModel = new ThemesItemViewModel();
                viewModel.coverUrl = itemData.getData().getImageUrl();
                viewModel.title = itemData.getData().getTitle();
                viewModel.description = itemData.getData().getViewCount()+" 人浏览 / "+itemData.getData().getJoinCount()+"人参与";
                viewModels.add(viewModel);
            }
        }
        loadSuccess((T) viewModels,viewModels.size() == 0,isRefresh);
    }
    
    public void refresh()
    {
        isRefresh = true;
        load();
    }
    
    public void loadMore()
    {
        isRefresh = false;
        if (!TextUtils.isEmpty(nextPageUrl))
        {
            loadMore(nextPageUrl);
        }
        else
        {
            loadSuccess(null, true, isRefresh);
        }
    }

    @Override
    public void cancel() {
        super.cancel();
        EasyHttp.cancelSubscription(disposable);
        EasyHttp.cancelSubscription(disposable1);
    }
}
