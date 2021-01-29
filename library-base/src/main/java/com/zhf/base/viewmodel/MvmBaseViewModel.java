package com.zhf.base.viewmodel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.zhf.base.model.SuperBaseModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 应用模块: viewModel
 * <p>
 * 类描述: 管理 v M
 * <p>
 *
 */
public  abstract class MvmBaseViewModel<V, M extends SuperBaseModel> extends ViewModel
    implements IMvvmBaseViewModel<V>
{
    
    private Reference<V> mUiRef;

    @NonNull
    protected M model = initModel();
    
    @Override
    public void attachUi(V view)
    {
        mUiRef = new WeakReference<>(view);
    }

    @Nullable
    @Override
    public V getPageView()
    {
        if (mUiRef != null && null != mUiRef.get())
        {
            return mUiRef.get();
        }
        return null;
    }
    
    @Override
    public boolean isUiAttach()
    {
        return null != mUiRef && null != mUiRef.get();
    }
    
    @Override
    public void detachUi()
    {
        if (null != mUiRef)
        {
            mUiRef.clear();
            mUiRef = null;
        }
        model.cancel();
    }


    protected void loadData(){

    }

    @NonNull
    protected  abstract M initModel();
}
