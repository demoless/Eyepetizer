package com.zhf.more.topic;

import java.util.List;

import com.zhf.base.fragment.MvvmLazyFragment;
import com.zhf.common.contract.BaseCustomViewModel;
import com.zhf.more.R;
import com.zhf.more.databinding.MoreFragmentTopicBinding;
import com.zhf.more.topic.adapter.TopicAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;

import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * 应用模块:
 * <p>
 * 类描述: 话题广场
 * <p>
 */
public class TopicFragment
    extends MvvmLazyFragment<MoreFragmentTopicBinding, TopicFragmentViewModel>
    implements ITopicView
{
    
    private TopicAdapter adapter;

    public static TopicFragment newInstance(){
        return new TopicFragment();
    }
    @Override
    public int getLayoutId()
    {
        return R.layout.more_fragment_topic;
    }
    
    @Override
    protected void onFragmentFirstVisible()
    {
        super.onFragmentFirstVisible();
        initView();
    }
    
    private void initView()
    {
        viewDataBinding.rvTopicView.setHasFixedSize(true);
        viewDataBinding.rvTopicView
            .setLayoutManager(new LinearLayoutManager(getContext()));
        viewDataBinding.refreshLayout
            .setRefreshHeader(new ClassicsHeader(getContext()));
        viewDataBinding.refreshLayout
            .setRefreshFooter(new ClassicsFooter(getContext()));
        viewDataBinding.refreshLayout.setOnRefreshListener(refreshLayout -> {
            viewModel.tryRefresh();
        });
        viewDataBinding.refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            viewModel.loadMore();
        });
        adapter = new TopicAdapter(R.layout.more_item_themes_view);
        viewDataBinding.rvTopicView.setAdapter(adapter);
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
    protected TopicFragmentViewModel getViewModel()
    {
        return ViewModelProviders.of(this).get(TopicFragmentViewModel.class);
    }
    
    @Override
    protected void onRetryBtnClick()
    {
        
    }
    
    private View getFooterView()
    {
        return LayoutInflater.from(getContext())
            .inflate(R.layout.more_item_foote_view,
                viewDataBinding.rvTopicView,
                false);
    }
    
    @Override
    public void onDataLoaded(List<BaseCustomViewModel> data,
        boolean isFirstPage)
    {
        if (data == null)
        {
            return;
        }
        if (adapter != null) {
            adapter.setNewData(data);
            showContent();
            viewDataBinding.refreshLayout.finishRefresh(isFirstPage);
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
        adapter.addFooterView(getFooterView());
        viewDataBinding.refreshLayout.finishLoadMoreWithNoMoreData();
    }
}
