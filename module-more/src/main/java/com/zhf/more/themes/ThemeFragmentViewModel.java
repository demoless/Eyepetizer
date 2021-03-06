package com.zhf.more.themes;

import androidx.annotation.NonNull;

import com.zhf.base.model.BaseModel;
import com.zhf.base.model.IModelListener;
import com.zhf.base.viewmodel.MvmBaseViewModel;
import com.zhf.more.themes.bean.Tabs;

import java.util.ArrayList;


/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 */
public class ThemeFragmentViewModel
    extends MvmBaseViewModel<IThemeView, ThemeModel>
    implements IModelListener<ArrayList<Tabs>>
{
    
    @NonNull
	@Override
    protected ThemeModel initModel()
    {
        model = new ThemeModel();
        model.register(this);
        model.getCacheDataAndLoad();
        return model;
    }

    @Override
    public void detachUi() {
        super.detachUi();
        model.unRegister(this);
    }

    @Override
    public void onLoadFinish(BaseModel model, ArrayList<Tabs> data)
    {
        if (getPageView() != null)
        {
            if (data != null)
            {
                getPageView().onDataLoaded(data);
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
}
