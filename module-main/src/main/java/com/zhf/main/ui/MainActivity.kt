package com.zhf.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.ImmersionBar
import com.zhf.base.activity.MvvmBaseActivity
import com.zhf.base.storage.MmkvHelper
import com.zhf.base.viewmodel.IMvvmBaseViewModel
import com.zhf.common.router.RouterActivityPath
import com.zhf.common.router.RouterFragmentPath
import com.zhf.main.R
import com.zhf.main.adapter.MainPageAdapter
import com.zhf.main.databinding.MainActivityMainBinding
import com.zhf.main.utils.ColorUtil

/**
 * created by demoless on 2021/1/27
 * description: App主界面
 */
@Route(path = RouterActivityPath.Main.PAGER_MAIN)
class MainActivity : MvvmBaseActivity<MainActivityMainBinding, IMvvmBaseViewModel<*>>() {

    private val fragments by lazy {
        ArrayList<Fragment>()
    }

    private val adapter by lazy {
        MainPageAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT)
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            MmkvHelper.getInstance().mmkv.encode("first", false)
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ImmersionBar.with(this)
                .statusBarColor(R.color.main_color_bar)
                .navigationBarColor(R.color.main_color_bar)
                .fitsSystemWindows(true)
                .autoDarkModeEnable(true)
                .init()
        initView()
        initFragments()
    }

    private fun initView() {
        val mNavigationController = viewDataBinding.bottomView.material()
                .addItem(R.drawable.main_home,
                        "首页",
                        ColorUtil.getColor(this, R.color.main_bottom_check_color))
                .addItem(R.drawable.main_community,
                        "社区",
                        ColorUtil.getColor(this, R.color.main_bottom_check_color))
                .addItem(R.drawable.main_notify,
                        "通知",
                        ColorUtil.getColor(this, R.color.main_bottom_check_color))
                .addItem(R.drawable.main_user,
                        "我的",
                        ColorUtil.getColor(this, R.color.main_bottom_check_color))
                .setDefaultColor(
                        ColorUtil.getColor(this, R.color.main_bottom_default_color))
                .enableAnimateLayoutChanges()
                .build()
        mNavigationController.setHasMessage(2, true)
        mNavigationController.setMessageNumber(3, 6)
        viewDataBinding.cvContentView.offscreenPageLimit = 1
        viewDataBinding.cvContentView.adapter = adapter
        mNavigationController.setupWithViewPager(viewDataBinding.cvContentView)
    }

    private fun initFragments() {
        fragments.apply {
            //通过ARouter 获取其他组件提供的fragment
            val homeFragment = ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation() as Fragment
            val communityFragment = ARouter.getInstance().build(RouterFragmentPath.Community.PAGER_COMMUNITY).navigation() as Fragment
            val moreFragment = ARouter.getInstance().build(RouterFragmentPath.More.PAGER_MORE).navigation() as Fragment
            val userFragment = ARouter.getInstance().build(RouterFragmentPath.User.PAGER_USER).navigation() as Fragment
            this.add(homeFragment)
            this.add(communityFragment)
            this.add(moreFragment)
            this.add(userFragment)
        }
        adapter.setData(fragments)
    }
    override fun getViewModel(): IMvvmBaseViewModel<*>? = null

    override fun getBindingVariable(): Int = 0

    override fun getLayoutId(): Int = R.layout.main_activity_main

    override fun onRetryBtnClick() {

    }
}