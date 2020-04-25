package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.bw.movie.bean.login_bean.LoginShow;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.utils.Base64;
import com.bw.movie.utils.EmailRegular;
import com.bw.movie.utils.EncryptUtil;

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

                break;

        }
    }
}
