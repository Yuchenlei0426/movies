package com.bw.movie.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.bw.movie.R;

/**
 *@describe(描述)：这是普通的基类
 *@data（日期）: 2020/4/24
 *@time（时间）: 20:39
 *@author（作者）: 于晨雷
 **/
public abstract class BaseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(onLayout(), container, false);
        onView(view);

        onData();
        return view;
    }

    protected abstract int onLayout();

    protected abstract void onView(View view);

    protected abstract void onData();
}
