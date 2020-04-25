package com.bw.movie.base;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
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
    Dialog mLoadingDialog;

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
    public void showDialog() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new Dialog(this);
            mLoadingDialog.setCancelable(false);
            View v = View.inflate(this, R.layout.loading_item, null);
            ImageView iv = v.findViewById(R.id.iv_loading);
            Glide.with(this).asGif().load(R.drawable.loading).into(iv);

            mLoadingDialog.addContentView(v, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        mLoadingDialog.show();
    }

    public void hideDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }
    protected abstract int onlayout();

    protected abstract CPreantent onPreantent();

    protected abstract void onView();

    protected abstract void onData();

}
