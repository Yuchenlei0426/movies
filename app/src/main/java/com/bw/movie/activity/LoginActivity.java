package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseNetActivity;
import com.bw.movie.bean.evenbean.WXCodeBean;
import com.bw.movie.bean.login_bean.LoginShow;
import com.bw.movie.bean.wechat.WeChatShow;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.utils.App;
import com.bw.movie.utils.Base64;
import com.bw.movie.utils.EmailRegular;
import com.bw.movie.utils.EncryptUtil;
import com.tencent.mm.opensdk.modelmsg.SendAuth;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 *@describe(描述)：登录
 *@data（日期）: 2020/5/8
 *@time（时间）: 15:55
 *@author（作者）: 于晨雷
 **/
public class LoginActivity extends BaseNetActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private EditText mEtEmail;
    private EditText mEtPwd;
    private TextView mTvRegister;
    private Button mButLogin;
    private ImageView mIvWeChat;

    @Override
    protected int onlayout() {
        return R.layout.activity_login;
    }

    @Override
    protected CPreantent onPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView() {
        initView();
    }

    @Override
    protected void onData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }



    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof LoginShow) {
            String message = ((LoginShow) o).getMessage();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
        if (o instanceof WeChatShow) {
            String message = ((WeChatShow) o).getMessage();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: "+mes);
    }

    private void initView() {
        mEtEmail = (EditText) findViewById(R.id.et_email);
        mEtPwd = (EditText) findViewById(R.id.et_pwd);
        mTvRegister = (TextView) findViewById(R.id.tv_register);
        mButLogin = (Button) findViewById(R.id.but_login);
        mIvWeChat = (ImageView) findViewById(R.id.iv_we_chat);
        mButLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mIvWeChat.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_login:
                String email = mEtEmail.getText().toString();
                String pwd = mEtPwd.getText().toString();
                String encode = Base64.encode(pwd.getBytes());
                String encrypt = EncryptUtil.encrypt(encode);
                boolean isEmail = EmailRegular.isEmail(email);
                if (!isEmail) {
                    mCPreantent.onLoginData(email,encrypt);
                }else {
                    Toast.makeText(this, "请输入正确的邮箱", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_register:
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_we_chat:
                doWxLogin();
                break;

        }
    }

    private void doWxLogin() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        App.getWxApi().sendReq(req);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveWXCode(WXCodeBean wxCodeBean) {
        Log.i(TAG, "receiveWXCode: "+wxCodeBean.getCode());
//        EventBus.getDefault().removeStickyEvent(wxCodeBean);
        String code = wxCodeBean.getCode();
        mCPreantent.onweChatData(code);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
