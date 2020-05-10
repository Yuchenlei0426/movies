package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseNetActivity;
import com.bw.movie.bean.code_send.CodeSendShow;
import com.bw.movie.bean.register.RegisterShow;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.utils.Base64;
import com.bw.movie.utils.EmailRegular;
import com.bw.movie.utils.EncryptUtil;
/**
 *@describe(描述)：注册
 *@data（日期）: 2020/5/8
 *@time（时间）: 15:55
 *@author（作者）: 于晨雷
 **/
public class RegisterActivity extends BaseNetActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";

    private EditText mEtNickName;
    private EditText mEtReEmail;
    private EditText mEtRePwd;
    private EditText mEtCode;
    private Button mButCode;
    private Button mButXinRegister;
    private MyCountDownTimer mMyCountDownTimer;

    @Override
    protected int onlayout() {
        return R.layout.activity_register;
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
        mMyCountDownTimer = new MyCountDownTimer(60000, 1000);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof RegisterShow) {
            String message = ((RegisterShow) o).getMessage();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }
        if (o instanceof CodeSendShow) {
            String message = ((CodeSendShow) o).getMessage();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: "+mes);
    }

    private void initView() {
        mEtNickName = (EditText) findViewById(R.id.et_nickName);
        mEtReEmail = (EditText) findViewById(R.id.et_re_email);
        mEtRePwd = (EditText) findViewById(R.id.et_re_pwd);
        mEtCode = (EditText) findViewById(R.id.et_code);
        mButCode = (Button) findViewById(R.id.but_code);
        mButXinRegister = (Button) findViewById(R.id.but_xin_register);
        mButXinRegister.setOnClickListener(this);
        mButCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_xin_register:
                String emails = mEtReEmail.getText().toString();
                String nickName = mEtNickName.getText().toString();
                String code = mEtCode.getText().toString();
                String pwd = mEtRePwd.getText().toString();
                boolean isEmailRe = EmailRegular.isEmail(emails);
                String encode = Base64.encode(pwd.getBytes());
                String encrypt = EncryptUtil.encrypt(encode);
                if (!isEmailRe) {
                    mCPreantent.onRegisterData(nickName,encrypt,emails,code);
                }else {
                    Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.but_code:
                String email = mEtReEmail.getText().toString();
                boolean isEmail = EmailRegular.isEmail(email);
                if (!isEmail) {
                    mMyCountDownTimer.start();
                    mCPreantent.onCodeSendData(email);
                }else {
                    Toast.makeText(this, "邮箱格式不正确", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            mButCode.setClickable(false);
            mButCode.setText(l/500+"秒");

        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            mButCode.setText("重新获取");
            //设置可点击
            mButCode.setClickable(true);
        }
    }

}
