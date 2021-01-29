package com.zhf.more.themes.childpager;

import androidx.annotation.NonNull;
import androidx.annotation.RecentlyNonNull;

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
 * @author darryrzhoong
 * @since 2020-02-23
 */
public class ThemesContentViewModel
    extends MvmBaseViewModel<IThemeContentView, ThemesContentModel>
    implements IPagingModelListener<ArrayList<BaseCustomViewModel>>
{
    @NonNull
	@RecentlyNonNull
	@Override
    protected void initModel()
    {

    }

    public void initModel(String typeName,String apiUrl){
        model = new ThemesContentModel(typeName,apiUrl);
        model.register(this);
        model.getCacheDataAndLoad();
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
        if (model != null)
        {
            model.unRegister(this);
        }
    }
    
    public void tryRefresh()
    {
        model.refresh();
    }
    
    public void loadMore()
    {
        model.loadMore();
    }
}
