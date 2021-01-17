package com.zhf.home.daily;

import java.util.ArrayList;

import com.zhf.base.fragment.MvvmLazyFragment;
import com.zhf.common.contract.BaseCustomViewModel;
import com.zhf.home.R;
import com.zhf.home.daily.adapter.ProviderDailyAdapter;
import com.zhf.home.databinding.HomeFragmentDailyBinding;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * 应用模块:
 * <p>
 * 类描述: 日报
 * <p>
 */
public class DailyFragment extends
        MvvmLazyFragment<HomeFragmentDailyBinding, DailyViewModel> implements IDailyView
{
    
    private ProviderDailyAdapter adapter;
    
    public static DailyFragment newInstance()
    {
        DailyFragment fragment = new DailyFragment();
        return fragment;
    }
    
    @Override
    public int getLayoutId()
    {
        return R.layout.home_fragment_daily;
    }
    
    @Override
    protected void onFragmentFirstVisible()
    {
        super.onFragmentFirstVisible();
        initView();
    }
    
    private void initView()
    {
        // 确定Item的改变不会影响RecyclerView的宽高
        viewDataBinding.rvDailyView.setHasFixedSize(true);
        viewDataBinding.rvDailyView
            .setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProviderDailyAdapter();
        viewDataBinding.rvDailyView.setAdapter(adapter);
        viewDataBinding.refreshLayout
            .setRefreshHeader(new ClassicsHeader(getContext()));
        viewDataBinding.refreshLayout.setEnableLoadMore(true);
        viewDataBinding.refreshLayout
            .setRefreshFooter(new ClassicsFooter(getContext()));
        viewDataBinding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            viewModel.tryToRefresh();
        });
        viewDataBinding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            viewModel.loadMore();
        });
        setLoadSir(viewDataBinding.refreshLayout);
        showLoading();
        viewModel.initModel();
        
    }
    
    @Override
    public int getBindingVariable()
    {
        return 0;
    }
    
    @Override
    protected DailyViewModel getViewModel()
    {
        return ViewModelProviders.of(this).get(DailyViewModel.class);
    }
    
    @Override
    protected void onRetryBtnClick()
    {

    }
    
    @Override
    public void onDataLoadFinish(ArrayList<BaseCustomViewModel> viewModels,
        boolean isFirstPage)
    {
        if (isFirstPage)
        {

            adapter.setNewData(viewModels);
            showContent();
            viewDataBinding.refreshLayout.finishRefresh(true);
        }
        else
        {

            adapter.addData(viewModels);
            showContent();
            viewDataBinding.refreshLayout.finishLoadMore(true);
        }
    }
    
    @Override
    public void onLoadMoreFailure(String message)
    {
        viewDataBinding.refreshLayout.finishLoadMore(false);
    }
    
    @Override
    public void onLoadMoreEmpty()
    {
        viewDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
    }
}
