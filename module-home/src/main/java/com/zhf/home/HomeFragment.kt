package com.zhf.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.tabs.TabLayout
import com.zhf.base.fragment.MvvmLazyFragment
import com.zhf.base.viewmodel.IMvvmBaseViewModel
import com.zhf.common.router.RouterFragmentPath
import com.zhf.home.adapter.HomeFragmentPageAdapter
import com.zhf.home.daily.DailyFragment
import com.zhf.home.databinding.HomeFragmentHomeBinding
import com.zhf.home.discover.DisCoverFragment
import com.zhf.home.nominate.NominateFragment
import java.util.*

/**
 * created by demoless on 2021/1/28
 * description:首页Fragment
 */
@Route(path = RouterFragmentPath.Home.PAGER_HOME)
class HomeFragment : MvvmLazyFragment<HomeFragmentHomeBinding, IMvvmBaseViewModel<*>>() {

    private val pageAdapter by lazy {
        HomeFragmentPageAdapter(childFragmentManager,
                FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT)
    }

    private fun initView() {
        viewDataBinding.vpHomeContent.adapter = pageAdapter
        viewDataBinding.tabLayout
                .setupWithViewPager(viewDataBinding.vpHomeContent)
        viewDataBinding.vpHomeContent.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(
                        viewDataBinding.tabLayout))
        viewDataBinding.tabLayout
                .addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabSelected(tab: TabLayout.Tab) {
                        viewDataBinding.vpHomeContent.currentItem = tab.position
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab) {}
                    override fun onTabReselected(tab: TabLayout.Tab) {}
                })
        viewDataBinding.ivNetSearch.setOnClickListener {

        }
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        initView()
        val fragments: MutableList<Fragment> = ArrayList()
        fragments.add(DisCoverFragment.newInstance())
        fragments.add(NominateFragment.newInstance())
        fragments.add(DailyFragment.newInstance())
        pageAdapter.setData(fragments)
        viewDataBinding.vpHomeContent.currentItem = 1
    }
    override fun getLayoutId(): Int = R.layout.home_fragment_home

    override fun getBindingVariable(): Int = 0

    override fun getViewModel(): IMvvmBaseViewModel<*>? = null

    override fun onRetryBtnClick() {

    }
}