package com.zhf.more;

import java.util.ArrayList;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.zhf.base.fragment.MvvmLazyFragment;
import com.zhf.base.viewmodel.IMvvmBaseViewModel;
import com.zhf.common.router.RouterFragmentPath;
import com.zhf.more.adapter.MoreFragmentPageAdapter;
import com.zhf.more.databinding.MoreFragmentMoreBinding;
import com.zhf.more.message.MessageFragment;
import com.zhf.more.themes.ThemesFragment;
import com.zhf.more.topic.TopicFragment;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 应用模块:
 * <p>
 * 类描述: 更多
 * <p>
 *
 */
@Route(path = RouterFragmentPath.More.PAGER_MORE)
public class MoreFragment
    extends MvvmLazyFragment<MoreFragmentMoreBinding, IMvvmBaseViewModel<?>>
{
    
    private MoreFragmentPageAdapter adapter;
    
    @Override
    public int getLayoutId()
    {
        return R.layout.more_fragment_more;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initView();
        initData();
    }

    private void initView()
    {
        viewDataBinding.tabLayout
            .setupWithViewPager(viewDataBinding.vpHomeContent);
        viewDataBinding.vpHomeContent.addOnPageChangeListener(
            new TabLayout.TabLayoutOnPageChangeListener(
                viewDataBinding.tabLayout));
        viewDataBinding.tabLayout
            .addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
            {
                @Override
                public void onTabSelected(TabLayout.Tab tab)
                {
                    viewDataBinding.vpHomeContent
                        .setCurrentItem(tab.getPosition());
                }
                
                @Override
                public void onTabUnselected(TabLayout.Tab tab)
                {
                    
                }
                
                @Override
                public void onTabReselected(TabLayout.Tab tab)
                {
                    
                }
            });
        adapter = new MoreFragmentPageAdapter(getChildFragmentManager(),
            FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        viewDataBinding.vpHomeContent.setAdapter(adapter);
    }
    
    private void initData()
    {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(ThemesFragment.newInstance());
        fragments.add(MessageFragment.newInstance());
        fragments.add(TopicFragment.newInstance());
        adapter.setData(fragments);
        viewDataBinding.vpHomeContent.setCurrentItem(1);
    }
    
    @Override
    public int getBindingVariable()
    {
        return 0;
    }
    
    @Override
    protected IMvvmBaseViewModel<?> getViewModel()
    {
        return null;
    }
    
    @Override
    protected void onRetryBtnClick()
    {
        
    }
    
}
