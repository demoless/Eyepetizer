package com.zhf.more.message;

import java.util.List;

import com.zhf.base.fragment.MvvmLazyFragment;
import com.zhf.common.contract.BaseCustomViewModel;
import com.zhf.common.recyclerview.RecyclerItemDecoration;
import com.zhf.common.utils.DensityUtils;
import com.zhf.more.R;
import com.zhf.more.databinding.MoreFragmentMessageBinding;
import com.zhf.more.message.adapter.MessageAdapter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;

import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 */
public class MessageFragment extends
    MvvmLazyFragment<MoreFragmentMessageBinding, MessageFragmentViewModel>
    implements IMessageView
{
    
    private MessageAdapter adapter;
    
    public static MessageFragment newInstance()
    {
        return new MessageFragment();
    }
    
    @Override
    public int getLayoutId()
    {
        return R.layout.more_fragment_message;
    }
    
    @Override
    protected void onFragmentFirstVisible()
    {
        super.onFragmentFirstVisible();
        initView();
    }
    
    private void initView()
    {
        int margin = DensityUtils.dp2px(getContext(), 10);
        viewDataBinding.rvMessageView.setHasFixedSize(true);
        viewDataBinding.rvMessageView.addItemDecoration(
            new RecyclerItemDecoration(margin, margin, margin, 0));
        viewDataBinding.rvMessageView
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
        adapter = new MessageAdapter();
        viewDataBinding.rvMessageView.setAdapter(adapter);
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
    protected MessageFragmentViewModel getViewModel()
    {
        return ViewModelProviders.of(this).get(MessageFragmentViewModel.class);
    }
    
    private View getFooterView()
    {
        return LayoutInflater.from(getContext())
            .inflate(R.layout.more_item_foote_view,
                viewDataBinding.rvMessageView,
                false);
    }
    
    @Override
    protected void onRetryBtnClick()
    {
        
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
    
    @Override
    public void onDataLoaded(List<BaseCustomViewModel> data,
        boolean isFirstPage)
    {
        if (data == null)
        {
            return;
        }
        if (isFirstPage)
        {
            adapter.setNewData(data);
            showContent();
            viewDataBinding.refreshLayout.finishRefresh(true);
        }
        else
        {
            adapter.addData(data);
            showContent();
            viewDataBinding.refreshLayout.finishLoadMore(true);
        }
    }
}
