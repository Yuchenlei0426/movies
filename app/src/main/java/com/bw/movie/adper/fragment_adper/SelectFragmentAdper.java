package com.bw.movie.adper.fragment_adper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
/**
 *@describe(描述)：选择fragment适配器
 *@data（日期）: 2020/5/8
 *@time（时间）: 16:00
 *@author（作者）: 于晨雷
 **/
public class SelectFragmentAdper extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;

    public SelectFragmentAdper(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    public void addAll(ArrayList<Fragment> fragments){
        this.fragments=fragments;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
