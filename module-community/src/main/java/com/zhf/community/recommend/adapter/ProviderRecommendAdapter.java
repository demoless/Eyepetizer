package com.zhf.community.recommend.adapter;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.zhf.common.contract.BaseCustomViewModel;
import com.zhf.community.recommend.adapter.provider.CommunityCardProvider;
import com.zhf.community.recommend.adapter.provider.IRecommendItemType;
import com.zhf.community.recommend.adapter.provider.SquareCardProvider;
import com.zhf.community.recommend.bean.HorizontalScrollCard;
import com.zhf.community.recommend.bean.viewmodel.CloumnsCardViewModel;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public class ProviderRecommendAdapter
    extends BaseProviderMultiAdapter<BaseCustomViewModel>
{

    public ProviderRecommendAdapter()
    {
        super();
        addItemProvider(new SquareCardProvider());
        addItemProvider(new CommunityCardProvider());

        
    }
    
    @Override
    protected int getItemType(@NotNull List<? extends BaseCustomViewModel> data,
        int position)
    {
        if (data.get(position) instanceof HorizontalScrollCard)
        {

            return IRecommendItemType.SQUARE_CARD_VIEW;
        }
        else if (data.get(position) instanceof CloumnsCardViewModel)
        {

            return IRecommendItemType.COMMUNITY_CARD_VIEW;
        }
        return 0;
    }
}
