package com.zhf.community.recommend.adapter;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zhf.community.databinding.CommunityItemSquareItemCardViewBinding;
import com.zhf.community.recommend.bean.SquareContentCard;
import androidx.databinding.DataBindingUtil;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public class SquareCardAdapter extends BaseQuickAdapter<SquareContentCard, BaseViewHolder> {
    public SquareCardAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void onItemViewHolderCreated(@NotNull BaseViewHolder viewHolder, int viewType) {
        DataBindingUtil.bind(viewHolder.itemView);
    }
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, @Nullable SquareContentCard squareContentCard) {
          if (squareContentCard == null){
              return;
          }
        CommunityItemSquareItemCardViewBinding binding = baseViewHolder.getBinding();
          if (binding != null){
              binding.setViewModel(squareContentCard);
              binding.executePendingBindings();
          }
    }
}
