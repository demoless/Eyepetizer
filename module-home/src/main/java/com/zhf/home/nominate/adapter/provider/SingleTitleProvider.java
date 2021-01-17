package com.zhf.home.nominate.adapter.provider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zhf.common.contract.BaseCustomViewModel;
import com.zhf.home.R;
import com.zhf.home.databinding.HomeItemSingleTitleViewBinding;
import com.zhf.home.nominate.bean.viewmodel.SingleTitleViewModel;

import androidx.databinding.DataBindingUtil;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public class SingleTitleProvider extends BaseItemProvider<BaseCustomViewModel>
{
    
    @Override
    public int getItemViewType()
    {
        return NominateItemType.SINGLE_TITLE_VIEW;
    }
    
    @Override
    public int getLayoutId()
    {
        return R.layout.home_item_single_title_view;
    }
    
    @Override
    public void onViewHolderCreated(BaseViewHolder viewHolder, int viewType)
    {
        DataBindingUtil.bind(viewHolder.itemView);
    }
    
    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder,
        @Nullable BaseCustomViewModel baseCustomViewModel)
    {
        if (baseCustomViewModel == null)
        {
            return;
        }
        
        HomeItemSingleTitleViewBinding binding = baseViewHolder.getBinding();
        if (binding != null)
        {
            binding.setViewModel((SingleTitleViewModel)baseCustomViewModel);
            binding.executePendingBindings();
        }
    }
}
