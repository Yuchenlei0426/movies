package com.bw.movie.fragment.details_fragment;


import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.detalis_adper.CommentAdper;
import com.bw.movie.base.BaseNetFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.bean.comment_details.CommentShow;
import com.bw.movie.bean.comment_details.Commentresult;
import com.bw.movie.bean.evenbean.MovieEvenShow;
import com.bw.movie.preantent.CPreantent;
import com.bw.movie.utils.App;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @describe(描述)：评论
 * @data（日期）: 2020/4/27
 * @time（时间）: 14:18
 * @author（作者）: 于晨雷
 **/
public class CommentFragment extends BaseNetFragment {

    private static final String TAG = "CommentFragment";
    private int mMovieId;
    private RecyclerView mRvComm;

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
        EventBus.getDefault().register(this);
        initView(view);
    }

    @Subscribe(sticky = true)
    public void onShow(MovieEvenShow movieEvenShow) {
        mMovieId = movieEvenShow.getMovieId();
    }

    @Override
    protected void onData() {
        SharedPreferences mLogin = App.mLogin;
        String sessionId = mLogin.getString("sessionId", "");
        int userId = mLogin.getInt("userId", -1);
        mCPreantent.onCommentData(userId,sessionId,  mMovieId, 1, 5);

    }


    @Override
    public void onSuccess(Object o) {
        if (o instanceof CommentShow) {
            List<Commentresult> result = ((CommentShow) o).getResult();
            CommentAdper commentAdper = new CommentAdper();
            commentAdper.addAll(result);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            mRvComm.setLayoutManager(linearLayoutManager);
            mRvComm.setAdapter(commentAdper);
        }
    }

    @Override
    public void onFail(String mes) {
        Log.i(TAG, "onFail: "+mes);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private void initView(View view) {
        mRvComm = (RecyclerView) view.findViewById(R.id.rv_comm);
    }
}
