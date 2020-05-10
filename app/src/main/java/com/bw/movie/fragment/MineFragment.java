package com.bw.movie.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private LinearLayout myBtnInterest;
    private LinearLayout myBtnReservation;
    private LinearLayout myBtnBuyTicket;
    private LinearLayout myBtnReadMovie;
    private LinearLayout myBtnMyComment;
    private LinearLayout myBtnfeedback;
    private LinearLayout myBtnSetting;

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
        myBtnInterest = (LinearLayout) view.findViewById(R.id.my_btn_interest);
        myBtnReservation = (LinearLayout) view.findViewById(R.id.my_btn_reservation);
        myBtnBuyTicket = (LinearLayout) view.findViewById(R.id.my_btn_buy_ticket);
        myBtnReadMovie = (LinearLayout) view.findViewById(R.id.my_btn_read_movie);
        myBtnMyComment = (LinearLayout) view.findViewById(R.id.my_btn_my_comment);
        myBtnfeedback = (LinearLayout) view.findViewById(R.id.my_btn_feedback);
        myBtnSetting = (LinearLayout) view.findViewById(R.id.my_btn_setting);
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
