package com.zhf.base.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.zhf.base.R;

/**
 * 应用模块:
 * <p>
 * 类描述: 网络超时
 * <p>
 */
public class TimeoutCallback extends Callback
{
    @Override
    protected int onCreateView()
    {
        return R.layout.base_layout_timeout;
    }
    
}
