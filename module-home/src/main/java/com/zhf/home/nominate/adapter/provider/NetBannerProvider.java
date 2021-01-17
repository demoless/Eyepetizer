package com.zhf.home.nominate.adapter.provider;

import com.zhf.common.adapter.CommonBindingAdapters;
import com.zhf.home.R;
import com.zhpan.bannerview.holder.ViewHolder;

import android.view.View;
import android.widget.ImageView;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 */
public class NetBannerProvider implements ViewHolder<String>
{
    @Override
    public int getLayoutId()
    {
        return R.layout.home_item_banner_item_view;
    }
    
    @Override
    public void onBind(View itemView, String data, int position, int size)
    {
        ImageView imageView = itemView.findViewById(R.id.banner_bg);
        CommonBindingAdapters.loadImage(imageView, data);
    }
    
}
