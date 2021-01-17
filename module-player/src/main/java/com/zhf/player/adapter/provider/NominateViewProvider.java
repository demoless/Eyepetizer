package com.zhf.player.adapter.provider;

import android.content.Intent;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.zhf.base.livedatabus.LiveDatabus;
import com.zhf.common.contract.BaseCustomViewModel;
import com.zhf.common.contract.VideoHeaderBean;
import com.zhf.player.R;
import com.zhf.player.VideoPlayerActivity;
import com.zhf.player.bean.viewmodel.VideoCardViewModel;
import com.zhf.player.databinding.PlayerItemVideoCardWhiteViewBinding;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import org.jetbrains.annotations.NotNull;


/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public class NominateViewProvider extends BaseItemProvider<BaseCustomViewModel>
{
    @Override
    public int getItemViewType()
    {
        return IVideoItemType.NOMINATE_VIEW;
    }
    
    @Override
    public int getLayoutId()
    {
        return R.layout.player_item_video_card_white_view;
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
        PlayerItemVideoCardWhiteViewBinding binding = baseViewHolder.getBinding();
        if (binding != null)
        {
            VideoCardViewModel cardViewModel = (VideoCardViewModel)baseCustomViewModel;
            binding.ivVideoCover.setOnClickListener(v -> {
                VideoHeaderBean headerBean = new VideoHeaderBean(
                        cardViewModel.title, cardViewModel.description,
                        cardViewModel.video_description,
                        cardViewModel.collectionCount, cardViewModel.shareCount,
                        cardViewModel.authorUrl, cardViewModel.nickName,
                        cardViewModel.userDescription, cardViewModel.playerUrl,
                        cardViewModel.blurredUrl, cardViewModel.videoId);
                LiveDatabus.getInstance().withSticky("player",VideoHeaderBean.class).setValue(headerBean);
                getContext().startActivity(new Intent(getContext(), VideoPlayerActivity.class));
            });
            binding.setViewModel(cardViewModel);
            binding.executePendingBindings();
        }
    }
}
