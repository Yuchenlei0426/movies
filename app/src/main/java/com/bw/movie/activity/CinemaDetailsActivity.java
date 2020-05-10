package com.bw.movie.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adper.fragment_adper.FragmentCinemaAdper;
import com.bw.movie.base.BaseNetActivity;
import com.bw.movie.bean.evenbean.CineamaIdShow;
import com.bw.movie.bean.evenbean.SeatseleCineamaIdShow;
import com.bw.movie.bean.findHotMovieList.CinemaDetails;
import com.bw.movie.bean.findHotMovieList.CinemaInfoDetailsShow;
import com.bw.movie.fragment.cinema_details_fragment.CinemaCommentFragment;
import com.bw.movie.fragment.cinema_details_fragment.CinemaDetailsFragment;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.utils.App;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

/**
 * @describe(描述)：影院详情,之搞完布局
 * @data（日期）: 2020/5/8
 * @time（时间）: 15:53
 * @author（作者）: 于晨雷
 **/
public class CinemaDetailsActivity extends BaseNetActivity {
    private static final String TAG = "CinemaDetailsActivity";
    private int mCinemaId;
    private android.widget.ImageView mIvCmBuck;
    private android.widget.TextView mTvCmName;
    private android.widget.TextView mTvLabel;
    private com.google.android.material.tabs.TabLayout mTabCm;
    private androidx.viewpager.widget.ViewPager mVpDet;
    private android.widget.Button mButSchedule;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected int onlayout() {
        return R.layout.activity_cinema_details;
    }

    @Override
    protected CPreantent onPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView() {
        initView();
        EventBus.getDefault().register(this);
        mButSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CinemaDetailsActivity.this,ScheduleActivity.class);
                SeatseleCineamaIdShow seatseleCineamaIdShow = new SeatseleCineamaIdShow(mCinemaId);
                EventBus.getDefault().postSticky(seatseleCineamaIdShow);
                startActivity(intent);
            }
        });
    }

    @Subscribe(sticky = true)
    public void cinemaIdShow(CineamaIdShow cineamaIdShow) {
        mCinemaId = cineamaIdShow.getCinemaId();
    }

    @Override
    protected void onData() {
        fragments.add(new CinemaDetailsFragment());
        fragments.add(new CinemaCommentFragment());
        FragmentCinemaAdper fragmentCinemaAdper = new FragmentCinemaAdper(getSupportFragmentManager());
        fragmentCinemaAdper.addAll(fragments);
        mVpDet.setAdapter(fragmentCinemaAdper);
        mTabCm.setupWithViewPager(mVpDet);
        mTabCm.getTabAt(0).setText("影院详情");
        mTabCm.getTabAt(1).setText("影院评价");
        SharedPreferences mLogin = App.mLogin;
        String sessionId = mLogin.getString("sessionId", "");
        int userId = mLogin.getInt("userId", -1);
        mCPreantent.onCinemaInfoData(userId, sessionId, mCinemaId);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof CinemaInfoDetailsShow) {
            CinemaDetails result = ((CinemaInfoDetailsShow) o).getResult();
            String name = result.getName();
            mTvCmName.setText(name);
            String label = result.getLabel();
            mTvLabel.setText(label);
        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: "+mes);
    }

    private void initView() {
        mIvCmBuck = (ImageView) findViewById(R.id.iv_cm_buck);
        mTvCmName = (TextView) findViewById(R.id.tv_cm_name);
        mTvLabel = (TextView) findViewById(R.id.tv_label);
        mTabCm = (TabLayout) findViewById(R.id.tab_cm);
        mVpDet = (ViewPager) findViewById(R.id.vp_det);
        mButSchedule = (Button) findViewById(R.id.but_schedule);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
