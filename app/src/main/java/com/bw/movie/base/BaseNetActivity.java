package com.bw.movie.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bw.movie.R;
import com.bw.movie.contract.IContract;
import com.bw.movie.preantent.CPreantent;
import com.jaeger.library.StatusBarUtil;

/**
 *@describe(描述)：这是网络请求base类
 *@data（日期）: 2020/4/23
 *@time（时间）: 15:15
 *@author（作者）: 于晨雷
 **/
public abstract class BaseNetActivity extends AppCompatActivity implements IContract.IView {
    public CPreantent mCPreantent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onlayout());
        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        StatusBarUtil.setTransparent(this);
        mCPreantent =onPreantent();
        if (mCPreantent != null) {
            mCPreantent.onAcctachView(this);
        }
        onView();
        onData();

    }

    protected abstract int onlayout();

    protected abstract CPreantent onPreantent();

    protected abstract void onView();

    protected abstract void onData();

}
