package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.base.BaseActivity;
import com.bw.movie.bean.evenbean.MovieEvenShow;
import com.bw.movie.view.DrawerLayout;
import com.facebook.drawee.view.DraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 *@describe(描述)：电影详情
 *@data（日期）: 2020/4/22
 *@time（时间）: 13:54
 *@author（作者）: 于晨雷
 **/
public class MovieDetailsActivity extends BaseActivity {


    private int mMovieId;
    private DrawerLayout done;
    private android.widget.RelativeLayout drawerTow;
    private android.widget.TextView drawerHandle;
    private android.widget.LinearLayout drawerContent;

    @Override
    protected int onLayout() {
        return R.layout.activity_movie_details;
    }

    @Override
    protected void onView() {
        EventBus.getDefault().register(this);
        initView();
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
    }

    @Subscribe(sticky = true)
    public void onShow(MovieEvenShow movieEvenShow){
        mMovieId = movieEvenShow.getMovieId();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        done = (DrawerLayout) findViewById(R.id.done);
        drawerTow = (RelativeLayout) findViewById(R.id.drawerTow);
        drawerHandle = (TextView) findViewById(R.id.drawerHandle);
        drawerContent = (LinearLayout) findViewById(R.id.drawerContent);
    }
}
