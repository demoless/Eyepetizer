package com.zhf.home.discover.adapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zhf.home.databinding.HomeItemCategoryItemSubjectCardViewBinding;
import com.zhf.home.discover.bean.SquareCard;

import androidx.databinding.DataBindingUtil;

/**
 * 应用模块:
 * <p>
 * 类描述: 专题adapter
 * <p>
 *
 */
public class SubjectItemAdapter
    extends BaseQuickAdapter<SquareCard, BaseViewHolder>
{
    
    public SubjectItemAdapter(int layoutResId)
    {
        super(layoutResId);
    }
    
    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder,
        int viewType)
    {
        DataBindingUtil.bind(viewHolder.itemView);
    }
    
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder,
        @Nullable SquareCard squareCard)
    {
        if (squareCard == null)
        {
            return;
        }
        HomeItemCategoryItemSubjectCardViewBinding binding =
            baseViewHolder.getBinding();
        if (binding != null)
        {
            binding.setViewModel(squareCard);
            binding.executePendingBindings();
        }
    }
}
