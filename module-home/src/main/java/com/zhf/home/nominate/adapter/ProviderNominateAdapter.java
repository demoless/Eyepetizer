package com.zhf.home.nominate.adapter;

import com.chad.library.adapter.base.BaseProviderMultiAdapter;
import com.zhf.common.contract.BaseCustomViewModel;
import com.zhf.home.nominate.adapter.provider.FollowCardProvider;
import com.zhf.home.nominate.adapter.provider.NominateItemType;
import com.zhf.home.nominate.adapter.provider.SingleTitleProvider;
import com.zhf.home.nominate.adapter.provider.TitleProvider;
import com.zhf.home.nominate.adapter.provider.VideoCardProvider;
import com.zhf.home.nominate.bean.viewmodel.FollowCardViewModel;
import com.zhf.home.nominate.bean.viewmodel.SingleTitleViewModel;
import com.zhf.home.nominate.bean.viewmodel.TitleViewModel;
import com.zhf.home.nominate.bean.viewmodel.VideoCardViewModel;

import java.util.List;

import org.jetbrains.annotations.NotNull;


/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 */
public class ProviderNominateAdapter
    extends BaseProviderMultiAdapter<BaseCustomViewModel>
{
    
    public ProviderNominateAdapter()
    {
        super();
        // 注册Provide
        addItemProvider(new TitleProvider());
        addItemProvider(new FollowCardProvider());
        addItemProvider(new SingleTitleProvider());
        addItemProvider(new VideoCardProvider());
        
    }
    
    @Override
    protected int getItemType(@NotNull List<? extends BaseCustomViewModel> data,
        int position)
    {
        if (data.get(position) instanceof TitleViewModel)
        {
            return NominateItemType.TITLE_VIEW;
        }
        else if (data.get(position) instanceof FollowCardViewModel)
        {
            return NominateItemType.FOLLOW_CARD_VIEW;
        }
        else

        if (data.get(position) instanceof SingleTitleViewModel)
        {
            return NominateItemType.SINGLE_TITLE_VIEW;
        }
        else if (data.get(position) instanceof VideoCardViewModel)
        {
            return NominateItemType.VIDEO_CARD_VIEW;
        }

        return -1;
    }
}
