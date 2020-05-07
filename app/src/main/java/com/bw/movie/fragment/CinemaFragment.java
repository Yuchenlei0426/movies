package com.bw.movie.fragment;


import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.R;
import com.bw.movie.adper.cinema_adper.CinemaFragmentAdper;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.fragment.cinema_fragment.NearbyFragment;
import com.bw.movie.fragment.cinema_fragment.RecommendFragment;
import com.bw.movie.fragment.cinema_fragment.RegionFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 *@describe(描述)：影院
 *@data（日期）: 2020/5/4
 *@time（时间）: 18:33
 *@author（作者）: 于晨雷
 **/
public class CinemaFragment extends BaseFragment {
    private static final String TAG = "CinemaFragment";

    private TabLayout mTabCineam;
    private ViewPager mVpCineam;
    ArrayList<Fragment> fragments =new ArrayList<>();

    private String mDistrict;

    @Override
    protected int onLayout() {
        return R.layout.fragment_cinema;
    }

    @Override
    protected void onView(View view) {
        initView(view);

    }

    @Override
    protected void onData() {
        fragments.add(new RecommendFragment());
        fragments.add(new NearbyFragment());
        fragments.add(new RegionFragment());
        CinemaFragmentAdper cinemaFragmentAdper = new CinemaFragmentAdper(getChildFragmentManager());
        cinemaFragmentAdper.addAll(fragments);
        mVpCineam.setAdapter(cinemaFragmentAdper);
        mTabCineam.setupWithViewPager(mVpCineam);
        mTabCineam.getTabAt(0).setText("推荐影院");
        mTabCineam.getTabAt(1).setText("附近影院");
        mTabCineam.getTabAt(2).setText("海淀区");
    }

    private void initView(View view) {
        mTabCineam = (TabLayout) view.findViewById(R.id.tab_cinema);
        mVpCineam = (ViewPager) view.findViewById(R.id.vp_cinema);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
