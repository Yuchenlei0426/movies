package com.bw.movie.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bw.movie.R;
import com.bw.movie.activity.LoginActivity;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.utils.App;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 *@describe(描述)：我的
 *@data（日期）: 2020/5/4
 *@time（时间）: 18:33
 *@author（作者）: 于晨雷
 **/
public class MineFragment extends BaseFragment implements View.OnClickListener {


    private LinearLayout goLogin;
    private SimpleDraweeView ivHeadPic;
    private TextView tvNickName;
    private RelativeLayout rlDian;
    private ImageView dsz;
    private LinearLayout myBtn1;
    private LinearLayout myBtn2;
    private LinearLayout myBtn3;
    private LinearLayout myBtn4;
    private LinearLayout myBtn5;
    private LinearLayout myBtn6;
    private LinearLayout myBtn7;

    @Override
    protected int onLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onView(View view) {
        initView(view);
        goLogin.setOnClickListener(this);
    }

    @Override
    protected void onData() {
        SharedPreferences mLogin = App.mLogin;
        String sessionId = mLogin.getString("sessionId", "");
        int userId = mLogin.getInt("userId", -1);
        String headPic = mLogin.getString("headPic", "");
        String nickName = mLogin.getString("nickName", "");
        ivHeadPic.setImageURI(headPic);
        tvNickName.setText(nickName);
    }

    private void initView(View view) {
        goLogin = (LinearLayout) view.findViewById(R.id.go_login);
        ivHeadPic = (SimpleDraweeView) view.findViewById(R.id.iv_headPic);
        tvNickName = (TextView) view.findViewById(R.id.tv_nickName);
        rlDian = (RelativeLayout) view.findViewById(R.id.rl_dian);
        dsz = (ImageView) view.findViewById(R.id.dsz);
        myBtn1 = (LinearLayout) view.findViewById(R.id.my_btn1);
        myBtn2 = (LinearLayout) view.findViewById(R.id.my_btn2);
        myBtn3 = (LinearLayout) view.findViewById(R.id.my_btn3);
        myBtn4 = (LinearLayout) view.findViewById(R.id.my_btn4);
        myBtn5 = (LinearLayout) view.findViewById(R.id.my_btn5);
        myBtn6 = (LinearLayout) view.findViewById(R.id.my_btn6);
        myBtn7 = (LinearLayout) view.findViewById(R.id.my_btn7);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
