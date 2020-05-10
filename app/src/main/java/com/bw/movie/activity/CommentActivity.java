package com.bw.movie.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.base.BaseNetActivity;
import com.bw.movie.bean.comment_write.WriteCommentShow;
import com.bw.movie.bean.evenbean.MovieEvenShow;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.utils.App;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
/**
 *@describe(描述)：评论
 *@data（日期）: 2020/5/8
 *@time（时间）: 15:57
 *@author（作者）: 于晨雷
 **/
public class CommentActivity extends BaseNetActivity implements View.OnClickListener {



    private android.widget.TextView mTvMovieName;
    private android.widget.RatingBar mRbFraction;
    private android.widget.EditText mEtContent;
    private android.widget.Button mButSubmit;
    private int mMovieId;
    private TextView mTvFraction;

    @Override
    protected int onlayout() {
        return R.layout.activity_comment;
    }

    @Override
    protected CPreantent onPreantent() {
        return new CPreantent();
    }

    @Override
    protected void onView() {
        initView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        mButSubmit.setOnClickListener(this);
        mRbFraction.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mTvFraction.setText(rating+"");
            }
        });
    }

    @Override
    protected void onData() {
    }
    @Subscribe(sticky = true)
    public void onShow(MovieEvenShow movieEvenShow) {
        mMovieId = movieEvenShow.getMovieId();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.but_submit:
                SharedPreferences mLogin = App.mLogin;
                int userId = mLogin.getInt("userId", -1);
                String sessionId = mLogin.getString("sessionId", "");
                float rating = mRbFraction.getRating();
                String content = mEtContent.getText().toString().trim();
                mCPreantent.onMovieCommentData(userId,sessionId,mMovieId,content,rating);
                break;
        }
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof WriteCommentShow) {
            String message = ((WriteCommentShow) o).getMessage();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void onFail(String mes) {

    }

    private void initView() {

        mTvMovieName = (TextView) findViewById(R.id.tv_movie_name);
        mRbFraction = (RatingBar) findViewById(R.id.rb_fraction);
        mEtContent = (EditText) findViewById(R.id.et_content);
        mButSubmit = (Button) findViewById(R.id.but_submit);
        mTvFraction = (TextView) findViewById(R.id.tv_fraction);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
