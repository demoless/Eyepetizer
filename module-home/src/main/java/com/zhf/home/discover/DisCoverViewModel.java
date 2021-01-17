package com.zhf.home.discover;

import java.util.ArrayList;

import com.zhf.base.model.BaseModel;
import com.zhf.base.model.IModelListener;
import com.zhf.base.viewmodel.MvmBaseViewModel;
import com.zhf.common.contract.BaseCustomViewModel;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public class DisCoverViewModel
    extends MvmBaseViewModel<IDisCoverView, DisCoverModel>
    implements IModelListener<ArrayList<BaseCustomViewModel>>
{
    
    @Override
    public void onLoadFinish(BaseModel model,
                             ArrayList<BaseCustomViewModel> data)
    {
        if (getPageView() != null)
        {
            if (data != null && data.size() > 0)
            {
                getPageView().onDataLoadFinish(data, false);
            }
            else
            {
                getPageView().showEmpty();
            }
        }
    }
    
    @Override
    public void onLoadFail(BaseModel model, String prompt)
    {
        if (getPageView() != null)
        {
            getPageView().showFailure(prompt);
        }
    }
    
    public void tryToRefresh()
    {
        model.load();
    }
    
    @Override
    protected void initModel()
    {
        model = new DisCoverModel();
        model.register(this);
        model.getCacheDataAndLoad();
    }

    @Override
    public void detachUi() {
        super.detachUi();
        if (model != null) {
            model.unRegister(this);
        }

    }
}
