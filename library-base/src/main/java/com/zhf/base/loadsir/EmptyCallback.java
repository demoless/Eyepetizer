package com.zhf.base.loadsir;

import com.kingja.loadsir.callback.Callback;
import com.zhf.base.R;

/**
 * 应用模块: loadSir
 * <p>
 * 类描述: 空页面
 * <p>
 *
 */
public class EmptyCallback extends Callback
{
    @Override
    protected int onCreateView()
    {
        return R.layout.base_layout_empty;
    }
}
