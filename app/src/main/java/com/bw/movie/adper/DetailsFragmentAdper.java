package com.bw.movie.adper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class DetailsFragmentAdper extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments;
    public DetailsFragmentAdper(FragmentManager supportFragmentManager) {
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
