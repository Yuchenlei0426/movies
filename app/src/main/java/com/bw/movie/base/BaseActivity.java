package com.bw.movie.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bw.movie.R;
import com.jaeger.library.StatusBarUtil;

/**
 *@describe(描述)：这是普通base类
 *@data（日期）: 2020/4/23
 *@time（时间）: 15:15
 *@author（作者）: 于晨雷
 **/
public  abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayout());
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        StatusBarUtil.setTransparent(this);
        onView();
        onData();
    }

    protected abstract int onLayout();

    protected abstract void onView();

    protected abstract void onData();
}
