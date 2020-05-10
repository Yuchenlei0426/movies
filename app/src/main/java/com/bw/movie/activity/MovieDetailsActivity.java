package com.bw.movie.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.adper.fragment_adper.DetailsFragmentAdper;
import com.bw.movie.base.BaseNetActivity;
import com.bw.movie.bean.evenbean.MovieEvenShow;
import com.bw.movie.bean.evenbean.MovieIdShow;
import com.bw.movie.bean.movie_detail.DetailResult;
import com.bw.movie.bean.movie_detail.MovieDetailShow;
import com.bw.movie.fragment.details_fragment.CommentFragment;
import com.bw.movie.fragment.details_fragment.ForeshowFragment;
import com.bw.movie.fragment.details_fragment.IntroduceFragment;
import com.bw.movie.fragment.details_fragment.StillFragment;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.view.DrawerLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @describe(描述)：电影详情
 * @data（日期）: 2020/4/22
 * @time（时间）: 13:54
 * @author（作者）: 于晨雷
 **/
public class MovieDetailsActivity extends BaseNetActivity implements View.OnClickListener {

    private static final String TAG = "MovieDetailsActivity";
    private DrawerLayout done;
    private RelativeLayout drawerTow;
    private TextView drawerHandle;
    private LinearLayout drawerContent;
    ArrayList<Fragment> fragments = new ArrayList<>();
    private SimpleDraweeView mSdvImageUrl;
    private TextView mTvName;
    private TextView mTvMovieType;
    private TextView mTvReleaseTime;
    private TextView mTvPlaceOrigin;
    private DrawerLayout mDone;
    private RelativeLayout mDrawerTow;
    private TextView mDrawerHandle;
    private LinearLayout mDrawerContent;
    private TabLayout mTabDetails;
    private ViewPager mVpDetails;
    private int mMovieId;
    private TextView mTvDuration;
    private Button mButWriteComment;
    private Button mButSeat;


    @Override
    protected int onlayout() {
        return R.layout.activity_movie_details;
    }

    @Override
    protected CPreantent onPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView() {

        initView();
        EventBus.getDefault().register(this);
        mButWriteComment.setOnClickListener(this);
        mButSeat.setOnClickListener(this);
    }
    @Subscribe(sticky = true)
    public void onShow(MovieEvenShow movieEvenShow) {
        mMovieId = movieEvenShow.getMovieId();
    }

    @Override
    protected void onData() {

        done.setInitialState(DrawerLayout.State.Close);

        done.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void drawerOpened() {

            }

            @Override
            public void drawerClosed() {

            }
        });


        mCPreantent.onMovieDetailData(mMovieId);

//        添加fragment到集合
//        介绍
        fragments.add(new IntroduceFragment());
//        预告
        fragments.add(new ForeshowFragment());
//        剧照
        fragments.add(new StillFragment());
//        评论
        fragments.add(new CommentFragment());

//        mCPreantent.onMovieDetailData();
        DetailsFragmentAdper detailsFragmentAdper = new DetailsFragmentAdper(getSupportFragmentManager());
        detailsFragmentAdper.addAll(fragments);
        mVpDetails.setAdapter(detailsFragmentAdper);
        mTabDetails.setupWithViewPager(mVpDetails);
        mTabDetails.getTabAt(0).setText(getResources().getText(R.string.Introduce));
        mTabDetails.getTabAt(1).setText(getResources().getText(R.string.Foreshow));
        mTabDetails.getTabAt(2).setText(getResources().getText(R.string.Still));
        mTabDetails.getTabAt(3).setText(getResources().getText(R.string.Comment));
    }



    private void initView() {
        done = (DrawerLayout) findViewById(R.id.done);
        drawerTow = (RelativeLayout) findViewById(R.id.drawerTow);
        drawerHandle = (TextView) findViewById(R.id.drawerHandle);
        drawerContent = (LinearLayout) findViewById(R.id.drawerContent);
        mSdvImageUrl = (SimpleDraweeView) findViewById(R.id.sdv_imageUrl);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvMovieType = (TextView) findViewById(R.id.tv_movieType);
        mTvReleaseTime = (TextView) findViewById(R.id.tv_releaseTime);
        mTvPlaceOrigin = (TextView) findViewById(R.id.tv_placeOrigin);
        mDone = (DrawerLayout) findViewById(R.id.done);
        mDrawerTow = (RelativeLayout) findViewById(R.id.drawerTow);
        mDrawerHandle = (TextView) findViewById(R.id.drawerHandle);
        mDrawerContent = (LinearLayout) findViewById(R.id.drawerContent);
        mTabDetails = (TabLayout) findViewById(R.id.tab_details);
        mVpDetails = (ViewPager) findViewById(R.id.vp_details);
        mTvDuration = (TextView) findViewById(R.id.tv_duration);
        mButWriteComment = (Button) findViewById(R.id.but_write_comment);
        mButSeat = (Button) findViewById(R.id.but_seat);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof MovieDetailShow) {
            DetailResult result = ((MovieDetailShow) o).getResult();
            String name = result.getName();
            mTvName.setText(name);
            String movieType = result.getMovieType();
            mTvMovieType.setText(movieType);
            String placeOrigin = result.getPlaceOrigin();
            mTvPlaceOrigin.setText(placeOrigin);
            long releaseTime = result.getReleaseTime();
            Date date = new Date(releaseTime);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String format = dateFormat.format(date);
            mTvReleaseTime.setText(format);
            String imageUrl = result.getImageUrl();
            mSdvImageUrl.setImageURI(imageUrl);
            String duration = result.getDuration();
            mTvDuration.setText(duration);
        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: " + mes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_write_comment:
                Intent intent = new Intent(MovieDetailsActivity.this, CommentActivity.class);
                startActivity(intent);
                break;
            case R.id.but_seat:
                Intent intentOne = new Intent(MovieDetailsActivity.this, SelectTheatersActivity.class);
                EventBus.getDefault().postSticky(new MovieIdShow(mMovieId));
                startActivity(intentOne);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
