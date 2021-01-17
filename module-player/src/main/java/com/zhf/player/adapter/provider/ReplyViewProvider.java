package com.zhf.player.adapter.provider;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zhf.common.contract.BaseCustomViewModel;
import com.zhf.common.utils.DateTimeUtils;
import com.zhf.player.R;
import com.zhf.player.bean.viewmodel.ReplyViewModel;
import com.zhf.player.databinding.PlayerItemReplyViewBinding;

import androidx.databinding.DataBindingUtil;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public class ReplyViewProvider extends BaseItemProvider<BaseCustomViewModel>
{
    @Override
    public int getItemViewType()
    {
        return IVideoItemType.REPLY_VIEW;
    }
    
    @Override
    public int getLayoutId()
    {
        return R.layout.player_item_reply_view;
    }
    
    @Override
    public void onViewHolderCreated(@NotNull BaseViewHolder viewHolder,
        int viewType)
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
        PlayerItemReplyViewBinding binding = baseViewHolder.getBinding();
        if (binding != null)
        {
            ReplyViewModel reply = (ReplyViewModel) baseCustomViewModel;
            binding.tvUserReleaseTime.setText("发布于 " + DateTimeUtils.getDate(
                String.valueOf(reply.releaseTime),
                "HH:mm"));
            binding.setViewModel(reply);
            binding.executePendingBindings();
        }
    }
}
