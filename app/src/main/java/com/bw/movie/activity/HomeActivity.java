package com.bw.movie.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.fragment.CinemaFragment;
import com.bw.movie.fragment.HomeFragment;
import com.bw.movie.fragment.MineFragment;
/**
 *@describe(描述)：fragment切换页
 *@data（日期）: 2020/4/18
 *@time（时间）: 18:13
 *@author（作者）: 于晨雷
 **/
public class HomeActivity extends BaseActivity {

    private FrameLayout frame;
    private RelativeLayout relayViewHome;
    private LinearLayout linMovie;
    private TextView tvMoview;
    private ImageView movieIma;
    private RelativeLayout relayViewCinema;
    private LinearLayout linCinema;
    private TextView tvCinema;
    private ImageView cinemaIma;
    private RelativeLayout relayView_Mine;
    private LinearLayout linMine;
    private TextView tvMine;
    private ImageView mineIma;
    private HomeFragment mHomeFragment;
    private CinemaFragment mCinemaFragment;
    private MineFragment mMineFragment;

    @Override
    protected int onLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void onView() {
        initView();
        mHomeFragment = new HomeFragment();
        mCinemaFragment = new CinemaFragment();
        mMineFragment = new MineFragment();
        // 显示隐藏
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, mHomeFragment)
                .add(R.id.frame, mCinemaFragment)
                .add(R.id.frame, mMineFragment)
                .show(mHomeFragment)
                .hide(mCinemaFragment)
                .hide(mMineFragment)
                .commit();
        linMovie.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onData() {

    }

    private void initView() {
        frame = (FrameLayout) findViewById(R.id.frame);
        relayViewHome = (RelativeLayout) findViewById(R.id.relay_view_home);
        linMovie = (LinearLayout) findViewById(R.id.lin_movie);
        tvMoview = (TextView) findViewById(R.id.tv_moview);
        movieIma = (ImageView) findViewById(R.id.movie_ima);
        relayViewCinema = (RelativeLayout) findViewById(R.id.relay_view_cinema);
        linCinema = (LinearLayout) findViewById(R.id.lin_cinema);
        tvCinema = (TextView) findViewById(R.id.tv_cinema);
        cinemaIma = (ImageView) findViewById(R.id.cinema_ima);
        relayView_Mine = (RelativeLayout) findViewById(R.id.relay_view_mine);
        linMine = (LinearLayout) findViewById(R.id.lin_mine);
        tvMine = (TextView) findViewById(R.id.tv_mine);
        mineIma = (ImageView) findViewById(R.id.mine_ima);

//       点击事件
        movieIma.setOnClickListener(this::onClick);
        cinemaIma.setOnClickListener(this::onClick);
        mineIma.setOnClickListener(this::onClick);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.movie_ima:
                getSupportFragmentManager()
                        .beginTransaction()
                        .show(mHomeFragment)
                        .hide(mCinemaFragment)
                        .hide(mMineFragment)
                        .commit();
                linMovie.setVisibility(View.VISIBLE);
                linCinema.setVisibility(View.GONE);
                linMine.setVisibility(View.GONE);
                movieIma.setVisibility(View.GONE);
                cinemaIma.setVisibility(View.VISIBLE);
                mineIma.setVisibility(View.VISIBLE);

                break;
            case R.id.cinema_ima:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mHomeFragment)
                        .show(mCinemaFragment)
                        .hide(mMineFragment)
                        .commit();
                linMovie.setVisibility(View.GONE);
                linCinema.setVisibility(View.VISIBLE);
                linMine.setVisibility(View.GONE);
                movieIma.setVisibility(View.VISIBLE);
                cinemaIma.setVisibility(View.GONE);
                mineIma.setVisibility(View.VISIBLE);

                break;
            case R.id.mine_ima:
                getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mHomeFragment)
                        .hide(mCinemaFragment)
                        .show(mMineFragment)
                        .commit();
                linMovie.setVisibility(View.GONE);
                linCinema.setVisibility(View.GONE);
                linMine.setVisibility(View.VISIBLE);
                movieIma.setVisibility(View.VISIBLE);
                cinemaIma.setVisibility(View.VISIBLE);
                mineIma.setVisibility(View.GONE);
                break;
        }
    }
}
