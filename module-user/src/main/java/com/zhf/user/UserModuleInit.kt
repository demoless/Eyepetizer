package com.zhf.user

import com.zhf.base.base.BaseApplication
import com.zhf.common.IModuleInit

/**
 * created by demoless on 2021/1/29
 * description:
 */
class UserModuleInit : IModuleInit {
    override fun onInitAhead(application: BaseApplication?): Boolean = false

    override fun onInitLow(application: BaseApplication?): Boolean = false
}