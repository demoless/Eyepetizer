package com.zhf.user;

import com.zhf.base.base.BaseApplication;
import com.zhf.common.IModuleInit;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 *
 */
public class UserModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(BaseApplication application) {
        return false;
    }

    @Override
    public boolean onInitLow(BaseApplication application) {
        return false;
    }
}
