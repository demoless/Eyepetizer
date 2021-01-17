package com.zhf.more.themes.adapter;

import java.util.ArrayList;

import com.zhf.more.themes.bean.Tabs;
import com.zhf.more.themes.childpager.ThemesContentFragment;

import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * 应用模块:
 * <p>
 * 类描述:
 * <p>
 */
public class ThemesFragmentPageAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Tabs> tabs;

    public ThemesFragmentPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void setData(ArrayList<Tabs> data){
        if (data == null){
            return;
        }
        this.tabs = data;
       notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
       return ThemesContentFragment.newInstance(tabs.get(position).getName(),tabs.get(position).getApiUrl());
    }

    @Override
    public int getCount() {
       if (tabs != null && tabs.size() >0){
           return tabs.size();
       }
        return 0;
    }

    @Override
    public void restoreState(@Nullable Parcelable state, @Nullable ClassLoader loader) {
        super.restoreState(state, loader);
    }
}
