package com.bw.movie.fragment.details_fragment;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adper.detalis_adper.ShortFilmAdper;
import com.bw.movie.base.BaseNetFragment;
import com.bw.movie.base.BasePreantent;
import com.bw.movie.bean.evenbean.MovieEvenShow;
import com.bw.movie.bean.movie_detail.DetailResult;
import com.bw.movie.bean.movie_detail.MovieDetailShow;
import com.bw.movie.bean.movie_detail.ShortFilmList;
import com.bw.movie.preantent.CPreantent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @describe(描述)：预告
 * @data（日期）: 2020/4/25
 * @time（时间）: 16:30
 * @author（作者）: 于晨雷
 **/
public class ForeshowFragment extends BaseNetFragment {

    private int mMovieId;
    private RecyclerView mRvShort;

    @Override
    protected int onLayout() {
        return R.layout.fragment_foreshow;
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
        mCPreantent.onMovieDetailData(mMovieId);
    }

    @Override
    public void onSuccess(Object o) {
        if (o instanceof MovieDetailShow) {
            DetailResult result = ((MovieDetailShow) o).getResult();
            List<ShortFilmList> shortFilmList = result.getShortFilmList();
            ShortFilmAdper shortFilmAdper = new ShortFilmAdper();
            shortFilmAdper.addAll(shortFilmList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            mRvShort.setLayoutManager(linearLayoutManager);
            mRvShort.setAdapter(shortFilmAdper);
        }
    }

    @Override
    public void onFail(String mes) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    private void initView(View view) {
        mRvShort = (RecyclerView) view.findViewById(R.id.rv_short);
    }
}
