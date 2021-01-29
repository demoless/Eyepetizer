package com.zhf.user.services

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zhf.common.contract.UserInfo
import com.zhf.common.router.RouterActivityPath
import com.zhf.common.services.ILoginService
import com.zhf.common.services.config.ServicesConfig

/**
 * created by demoless on 2021/1/29
 * description: 用户登录信息服务类 为各个组件提供app登录信息
 */
@Route(path = ServicesConfig.User.LONGING_SERVICE, name = "登录服务")
class LoginServiceImpl: ILoginService {

    private var loginStatus: Boolean = false

    override fun init(context: Context?) {

    }

    override fun saveStatus(status: Boolean): Boolean {
        loginStatus = status
        return status
    }

    override fun isLogin(): Boolean = loginStatus

    override fun getToken(): String? = null

    override fun getUUID(): String? = null

    override fun refreshUserDetailInfo() {
    }

    override fun login() {
        ARouter.getInstance()
                .build(RouterActivityPath.User.PAGER_LOGIN)
                .navigation()
    }

    override fun login(isMainAccountLogin: Boolean) {
    }

    override fun logout() {
    }

    override fun getUserInfo(): UserInfo {
        return UserInfo.getInstance()
    }

    override fun onLoginCancel() {
    }

    override fun onTokenExpire() {
    }
}