package com.zhf.home.adapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * 应用模块: home
 * <p>
 * 类描述: 首页 - viewpager 适配器
 * <p>
 */
public class HomeFragmentPageAdapter extends FragmentPagerAdapter
{
    
    private static final String[] tables = {"发现", "推荐", "日报"};
    private List<Fragment> fragments ;

    public HomeFragmentPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }



    public void setData(List<Fragment> fragment){
        if (fragments == null){
            fragments = new ArrayList<>();
        }
        fragments.addAll(fragment);
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public Fragment getItem(int position)
    {
        if (fragments != null && fragments.size() >0){
            return fragments.get(position);
        }
        return null;
    }
    
    @Override
    public int getCount()
    {
       if (fragments != null && fragments.size() >0){
           return fragments.size();
       }
       return 0;
    }
    
    @Nullable
    @Override
    public CharSequence getPageTitle(int position)
    {
        if (position >= tables.length) {
            return null;
        }
        return tables[position];
    }
}
