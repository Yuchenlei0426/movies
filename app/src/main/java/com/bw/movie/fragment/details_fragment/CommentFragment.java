package com.bw.movie.fragment.details_fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.R;
import com.bw.movie.base.BaseNetFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.preantent.CPreantent;

/**
 *@describe(描述)：评论
 *@data（日期）: 2020/4/27
 *@time（时间）: 14:18
 *@author（作者）: 于晨雷
 **/
public class CommentFragment extends BaseNetFragment {


    @Override
    protected int onLayout() {
        return R.layout.fragment_comment;
    }

    @Override
    protected BasePreantent initPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView(View view) {

    }

    @Override
    protected void onData() {

    }

    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFail(String mes) {

    }
}
